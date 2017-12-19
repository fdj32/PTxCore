/*
 * ams.h
 *
 *  Created on: Dec 12, 2017
 *      Author: nickfeng
 */

#ifndef AMS_H_
#define AMS_H_

#ifdef __cplusplus
extern "C" {
#endif

typedef struct StringList {
	char * s;
	struct StringList * next;
} StringList;

StringList * string2StringList(char * s, char c);

char * StringList2string(StringList * sl, char * c);

typedef struct IntList {
	int i;
	struct IntList * next;
} IntList;

IntList * string2IntList(char * s, char c);

char * IntList2string(IntList * il, char * c);

typedef struct BINRange {
	char * card;
	IntList * lengths;
	StringList * prefixes;
	struct BINRange * next;
} BINRange;

BINRange * parseBINRanges(xmlNodePtr BINRanges);

char * BINRange2string(BINRange * o);

typedef struct RIDSetting {
	char * tacDenial;
	char * tacOnline;
	char * tacDefault;
	char * value;
	char * name;
	char * enableFallback;
	int floorLimit;
	int threshold;
	struct RIDSetting * next;
} RIDSetting;

RIDSetting * parseRIDSetting(xmlNodePtr PTxCoreSettings);

char * RIDSetting2string(RIDSetting * o);

typedef struct CAKey {
	char * rid;
	char index;
	char * modulus;
	char exponent;
	char * hash;
	struct CAKey * next;
} CAKey;

CAKey * parseCAKey(xmlNodePtr PTxCoreCAKeys);

char * CAKey2string(CAKey * o);

typedef struct Param {
	BINRange * binRangeHead;
	RIDSetting * ridHead;
	CAKey * caKeyHead;
} Param;

Param * parseParam(char *content, int length);

char * Param2string(Param * o);

#ifdef __cplusplus
}
#endif

#endif /* AMS_H_ */
