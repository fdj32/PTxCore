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
						tmp->lengths = string2IntList(node->children->content,
								',');
					} else if (strcmp(node->name, "Prefixes") == 0) {
						tmp->prefixes = string2StringList(
								node->children->content, ',');
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
