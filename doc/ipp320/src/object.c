/*
 * object.c
 * object builders and parsers
 *  Created on: 2017Äê11ÔÂ11ÈÕ
 *      Author: Administrator
 */
#include "ipp320.h"

int AIDLength(AID * o) {
	return 55+o->lengthTLVData+littleEndianInt(o->defaultDDOLLength)+littleEndianInt(o->defaultTDOLLength);
}

char * AIDToBin(AID * o) {
	int length = AIDLength(o);
	char * s = calloc(length, sizeof(char));
	return s;
}

AID * AIDFromBin(char * s) {
	AID * o = malloc(sizeof(AID));

	return o;
}
