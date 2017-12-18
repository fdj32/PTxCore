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
