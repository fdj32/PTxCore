/*
 * ams.c
 *
 *  Created on: Dec 12, 2017
 *      Author: nickfeng
 */

#include "ipp320.h"

StringList * string2StringList(char * s, char c) {
	if (NULL == s) {
		return NULL;
	}
	int length = strlen(s);
	if (0 == length) {
		return NULL;
	}
	int start = 0, end = 0;
	StringList * sl = malloc(sizeof(StringList));
	sl->next = NULL;
	StringList * slp = sl;
	while (end <= length) {
		if (s[end] == c || (end == length && end != start)) {
			StringList * tmp = malloc(sizeof(StringList));
			tmp->next = NULL;
			int tmpLen = end - start;
			char * sTmp = malloc(tmpLen + 1);
			memset(sTmp, 0, tmpLen + 1);
			memcpy(sTmp, s + start, tmpLen);
			tmp->s = sTmp;
			slp->next = tmp;
			slp = tmp;
			start = end + 1;
			while (s[start] == c) {
				start++;
				end++;
			}
		}
		end++;
	}
	return sl;
}

char * StringList2string(StringList * sl, char * c) {
	if (NULL == sl) {
		return NULL;
	}
	int buffLength = 1024;
	char * s = malloc(buffLength);
	memset(s, 0, buffLength);
	StringList * slp = sl;
	while (NULL != slp->next) {
		if (strlen(s) + strlen(slp->s) >= buffLength) {
			buffLength *= 2;
			s = realloc(s, buffLength);
		}
		strcat(s, slp->s);
		strcat(s, c);
		slp = slp->next;
	}
	return s;
}

IntList * string2IntList(char * s, char c) {
	if (NULL == s) {
		return NULL;
	}
	int length = strlen(s);
	if (0 == length) {
		return NULL;
	}
	int start = 0, end = 0;
	IntList * il = malloc(sizeof(IntList));
	il->next = NULL;
	IntList * ilp = il;
	while (end <= length) {
		if (s[end] == c || (end == length && end != start)) {
			IntList * tmp = malloc(sizeof(IntList));
			tmp->next = NULL;
			int tmpLen = end - start;
			char * sTmp = malloc(tmpLen + 1);
			memset(sTmp, 0, tmpLen + 1);
			memcpy(sTmp, s + start, tmpLen);
			tmp->i = atoi(sTmp);
			ilp->next = tmp;
			ilp = tmp;
			start = end + 1;
			while (s[start] == c) {
				start++;
				end++;
			}
		}
		end++;
	}
	return il;
}

char * IntList2string(IntList * il, char * c) {
	if (NULL == il) {
		return NULL;
	}
	int buffLength = 1024;
	char * s = malloc(buffLength);
	memset(s, 0, buffLength);
	IntList * ilp = il;
	while (NULL != ilp->next) {
		char tmp[16];
//		itoa(ilp->i, tmp, 10); // itoa is not supported in mac
		sprintf(tmp, "%d", ilp->i);
		if (strlen(s) + strlen(tmp) >= buffLength) {
			buffLength *= 2;
			s = realloc(s, buffLength);
		}
		strcat(s, tmp);
		strcat(s, c);
		ilp = ilp->next;
	}
	return s;
}

BINRange * parseBINRanges(xmlNodePtr BINRanges) {
	xmlNodePtr binRange = BINRanges->children;
	BINRange * head = malloc(sizeof(BINRange));
	head->next = NULL;
	BINRange * ptr = head;
	while (NULL != binRange) {
		if (binRange->type == XML_ELEMENT_NODE
				&& strcmp(binRange->name, "BINRange") == 0) {
			puts("BINRange\n");
			BINRange * tmp = malloc(sizeof(BINRange));

			xmlNodePtr node = binRange->children;
			while (NULL != node) {
				if (node->type == XML_ELEMENT_NODE) {
					if (strcmp(node->name, "Card") == 0) {
						tmp->card = node->children->content;
						printf("Card:%s\n", tmp->card);
					} else if (strcmp(node->name, "Lengths") == 0) {
						if (NULL == node->children) {
							tmp->lengths = NULL;
						} else {
							tmp->lengths = string2IntList(
									node->children->content, ',');
						}
					} else if (strcmp(node->name, "Prefixes") == 0) {
						if (NULL == node->children) {
							tmp->prefixes = NULL;
						} else {
							tmp->prefixes = string2StringList(
									node->children->content, ',');
						}
					}
				}
				node = node->next;
			}

			tmp->next = NULL;
			ptr->next = tmp;
			ptr = tmp;
		}
		binRange = binRange->next;
	}
	return head;
}

char * BINRange2string(BINRange * o) {
	if (NULL == o) {
		return NULL;
	}
	int buffLength = 102400;
	char * s = malloc(buffLength);
	memset(s, 0, buffLength);
	BINRange * p = o->next;
	while (NULL != p) {
		strcat(s, "<BINRange>");
		strcat(s, "<Card>");
		strcat(s, p->card);
		strcat(s, "</Card>");
		strcat(s, "<Lengths>");
		char * tmp = IntList2string(p->lengths, ",");
		strcat(s, tmp == NULL ? "" : tmp);
		strcat(s, "</Lengths>");
		strcat(s, "<Prefixes>");
		tmp = StringList2string(p->prefixes, ",");
		strcat(s, tmp == NULL ? "" : tmp);
		strcat(s, "</Prefixes>");
		strcat(s, "</BINRange>");
		p = p->next;
	}
	return s;
}

RIDSetting * parseRIDSetting(xmlNodePtr PTxCoreSettings) {
	xmlNodePtr rid = PTxCoreSettings->children;
	RIDSetting * head = malloc(sizeof(RIDSetting));
	head->next = NULL;
	RIDSetting * ptr = head;
	while (NULL != rid) {
		if (rid->type == XML_ELEMENT_NODE && strcmp(rid->name, "RID") == 0) {
			puts("RID\n");
			RIDSetting * tmp = malloc(sizeof(RIDSetting));
			xmlNodePtr node = rid->children;
			while (NULL != node) {
				if (node->type == XML_ELEMENT_NODE) {
					if (strcmp(node->name, "TACDenial") == 0) {
						tmp->tacDenial = node->children->content;
					} else if (strcmp(node->name, "TACOnline") == 0) {
						tmp->tacOnline = node->children->content;
					} else if (strcmp(node->name, "TACDefault") == 0) {
						tmp->tacDefault = node->children->content;
					} else if (strcmp(node->name, "Value") == 0) {
						tmp->value = node->children->content;
					} else if (strcmp(node->name, "Name") == 0) {
						tmp->name = node->children->content;
					} else if (strcmp(node->name, "AID") == 0) {
						xmlNodePtr nodeAID = node->children;
						while (NULL != nodeAID) {
							if (strcmp(nodeAID->name, "EnableFallback") == 0) {
								tmp->enableFallback =
										nodeAID->children->content;
							} else if (strcmp(nodeAID->name, "FloorLimit")
									== 0) {
								tmp->floorLimit = atoi(
										nodeAID->children->content);
							} else if (strcmp(nodeAID->name, "Threshold")
									== 0) {
								tmp->threshold = atoi(
										nodeAID->children->content);
							}
							nodeAID = nodeAID->next;
						}
					}
				}
				node = node->next;
			}

			tmp->next = NULL;
			ptr->next = tmp;
			ptr = tmp;
		}
		rid = rid->next;
	}
	return head;
}

char * RIDSetting2string(RIDSetting * o) {
	if (NULL == o) {
		return NULL;
	}
	int buffLength = 102400;
	char * s = malloc(buffLength);
	memset(s, 0, buffLength);
	RIDSetting * p = o->next;
	while (NULL != p) {
		strcat(s, "<RID>");
		strcat(s, "<TACDenial>");
		strcat(s, p->tacDenial);
		strcat(s, "</TACDenial>");

		strcat(s, "<TACOnline>");
		strcat(s, p->tacOnline);
		strcat(s, "</TACOnline>");

		strcat(s, "<TACDefault>");
		strcat(s, p->tacDefault);
		strcat(s, "</TACDefault>");

		strcat(s, "<Value>");
		strcat(s, p->value);
		strcat(s, "</Value>");

		strcat(s, "<Name>");
		strcat(s, p->name);
		strcat(s, "</Name>");

		strcat(s, "<AID>");

		strcat(s, "<EnableFallback>");
		strcat(s, p->enableFallback);
		strcat(s, "</EnableFallback>");

		strcat(s, "<FloorLimit>");
		char floorLimit[16];
		sprintf(floorLimit, "%d", p->floorLimit);
		strcat(s, floorLimit);
		strcat(s, "</FloorLimit>");

		strcat(s, "<Threshold>");
		char threshold[16];
		sprintf(threshold, "%d", p->threshold);
		strcat(s, threshold);
		strcat(s, "</Threshold>");

		strcat(s, "</AID>");

		strcat(s, "</RID>");
		p = p->next;
	}
	return s;
}

CAKey * parseCAKey(xmlNodePtr PTxCoreCAKeys) {
	xmlNodePtr caKey = PTxCoreCAKeys->children;
	CAKey * head = malloc(sizeof(CAKey));
	head->next = NULL;
	CAKey * ptr = head;
	while (NULL != caKey) {
		if (caKey->type == XML_ELEMENT_NODE
				&& strcmp(caKey->name, "CAKey") == 0) {
			puts("CAKey\n");
			CAKey * tmp = malloc(sizeof(CAKey));
			xmlNodePtr node = caKey->children;
			while (NULL != node) {
				if (node->type == XML_ELEMENT_NODE) {
					if (strcmp(node->name, "RID") == 0) {
						tmp->rid = node->children->content;
					} else if (strcmp(node->name, "Index") == 0) {
						tmp->index = unHexByte(node->children->content);
					} else if (strcmp(node->name, "Modulus") == 0) {
						tmp->modulus = node->children->content;
					} else if (strcmp(node->name, "Exponent") == 0) {
						tmp->exponent = unHexByte(node->children->content);
					} else if (strcmp(node->name, "Hash") == 0) {
						tmp->hash = node->children->content;
					}
				}
				node = node->next;
			}

			tmp->next = NULL;
			ptr->next = tmp;
			ptr = tmp;
		}
		caKey = caKey->next;
	}
	return head;
}

char * CAKey2string(CAKey * o) {
	if (NULL == o) {
		return NULL;
	}
	int buffLength = 102400;
	char * s = malloc(buffLength);
	memset(s, 0, buffLength);
	CAKey * p = o->next;
	while (NULL != p) {
		strcat(s, "<CAKey>");
		strcat(s, "<RID>");
		strcat(s, p->rid);
		strcat(s, "</RID>");

		strcat(s, "<Index>");
		strcat(s, hexByte(p->index));
		strcat(s, "</Index>");

		strcat(s, "<Modulus>");
		strcat(s, p->modulus);
		strcat(s, "</Modulus>");

		strcat(s, "<Exponent>");
		strcat(s, hexByte(p->exponent));
		strcat(s, "</Exponent>");

		strcat(s, "<Hash>");
		strcat(s, p->hash);
		strcat(s, "</Hash>");

		strcat(s, "</CAKey>");
		p = p->next;
	}
	return s;
}

Param * parseParam(char *content, int length) {
	Param * p = NULL;
	xmlDocPtr doc;
	doc = xmlReadMemory(content, length, "noname.xml", NULL, 0);
	if (doc == NULL) {
		fprintf(stderr, "Failed to parse document\n");
		return NULL;
	} else {
		/*Get the root element node */
		xmlNodePtr root_element = xmlDocGetRootElement(doc);
		p = malloc(sizeof(Param));
		xmlNodePtr child = root_element->children;
		while (NULL != child) {
			if (child->type == XML_ELEMENT_NODE) {
				if (strcmp(child->name, "BINRanges") == 0) {
					puts("found BINRanges\n");
					p->binRangeHead = parseBINRanges(child);
				} else if (strcmp(child->name, "PTxCoreSettings") == 0) {
					puts("found PTxCoreSettings\n");
					p->ridHead = parseRIDSetting(child);
				} else if (strcmp(child->name, "PTxCoreCAKeys") == 0) {
					puts("found PTxCoreCAKeys\n");
					p->caKeyHead = parseCAKey(child);
				}
			}
			child = child->next;
		}
	}
	xmlFreeDoc(doc);
	return p;
}

char * Param2string(Param * o) {
	if (NULL == o) {
		return NULL;
	}
	int buffLength = 102400;
	char * s = malloc(buffLength);
	memset(s, 0, buffLength);
	strcat(s, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	strcat(s, "<EmvParameterDownloadResponse>");
	strcat(s, "<BINRanges>");
	strcat(s, BINRange2string(o->binRangeHead));
	strcat(s, "</BINRanges>");
	strcat(s, "<PTxCoreSettings>");
	strcat(s, RIDSetting2string(o->ridHead));
	strcat(s, "</PTxCoreSettings>");
	strcat(s, "<PTxCoreCAKeys>");
	strcat(s, CAKey2string(o->caKeyHead));
	strcat(s, "</PTxCoreCAKeys>");
	strcat(s, "</EmvParameterDownloadResponse>");
	return s;
}
