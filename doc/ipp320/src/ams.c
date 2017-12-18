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
				}
			}
			child = child->next;
		}
	}
	xmlFreeDoc(doc);
	return p;
}
