/*
 * object.c
 * object builders and parsers
 *  Created on: 2017Äê11ÔÂ11ÈÕ
 *      Author: Administrator
 */
#include "ipp320.h"

int AIDLength(AID * o) {
	return 55 + o->lengthTLVData + littleEndianInt(o->defaultDDOLLength)
			+ littleEndianInt(o->defaultTDOLLength);
}

char * AIDToBin(AID * o) {
	int length = AIDLength(o);
	char * s = malloc(length);
	memset(s, 0, length);
	memcpy(s, o->applicationSelectionIndicator, 1);
	int tlv = o->lengthTLVData;
	memcpy(s[1], tlv, 1);
	memcpy(s[2], o->tlvData, tlv);
	memcpy(s[2+tlv], o->aidLength, 1);
	memcpy(s[3+tlv], o->aid, o->aidLength); // 16
	memcpy(s[19+tlv], o->rid, 5);
	memcpy(s[24+tlv], o->applicationVersionNumber, 2);
	memcpy(s[26+tlv], o->tacDefault, 5);
	memcpy(s[31+tlv], o->tacDenial, 5);
	memcpy(s[36+tlv], o->tacOnline, 5);
	memcpy(s[41+tlv], o->maximumTargetPercentage, 1);
	memcpy(s[42+tlv], o->targetPercentage, 1);
	memcpy(s[43+tlv], o->thresholdValue, 4);
	memcpy(s[47+tlv], o->terminalFloorLimit, 4);
	memcpy(s[51+tlv], o->defaultTDOLLength, 2);
	int tdol = littleEndianInt(o->defaultTDOLLength);
	memcpy(s[53+tlv], o->defaultTDOL, tdol);
	int ddol = littleEndianInt(o->defaultDDOLLength);
	memcpy(s[53+tlv+tdol], o->defaultDDOLLength, 2);
	memcpy(s[55+tlv+tdol], o->defaultDDOL, ddol);
	return s;
}

AID * AIDFromBin(char * s) {
	AID * o = malloc(sizeof(AID));

	return o;
}
