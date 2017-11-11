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
	s[0] = o->applicationSelectionIndicator;
	s[1] = o->lengthTLVData;
	int tlv = o->lengthTLVData;
	memcpy(s+2, o->tlvData, tlv);
	s[2+tlv] = o->aidLength;
	memcpy(s+3+tlv, o->aid, o->aidLength); // 16
	memcpy(s+19+tlv, o->rid, 5);
	memcpy(s+24+tlv, o->applicationVersionNumber, 2);
	memcpy(s+26+tlv, o->tacDefault, 5);
	memcpy(s+31+tlv, o->tacDenial, 5);
	memcpy(s+36+tlv, o->tacOnline, 5);
	s[41+tlv] = o->maximumTargetPercentage;
	s[42+tlv] = o->targetPercentage;
	memcpy(s+43+tlv, o->thresholdValue, 4);
	memcpy(s+47+tlv, o->terminalFloorLimit, 4);
	memcpy(s+51+tlv, o->defaultTDOLLength, 2);
	int tdol = littleEndianInt(o->defaultTDOLLength);
	memcpy(s+53+tlv, o->defaultTDOL, tdol);
	int ddol = littleEndianInt(o->defaultDDOLLength);
	memcpy(s+53+tlv+tdol, o->defaultDDOLLength, 2);
	memcpy(s+55+tlv+tdol, o->defaultDDOL, ddol);
	return s;
}

AID * AIDFromBin(char * s) {
	AID * o = malloc(sizeof(AID));
	o->applicationSelectionIndicator = s[0];
	o->lengthTLVData = s[1];
	o->tlvData = s+2;
	o->aidLength = s[2+o->lengthTLVData];
	o->aid = s+3+o->lengthTLVData;
	o->rid = s+19+o->lengthTLVData;
	o->applicationVersionNumber = s+24+o->lengthTLVData;
	o->tacDefault = s+26+o->lengthTLVData;
	o->tacDenial = s+31+o->lengthTLVData;
	o->tacOnline = s+36+o->lengthTLVData;
	o->maximumTargetPercentage = s[41+o->lengthTLVData];
	o->targetPercentage = s[42+o->lengthTLVData];
	o->thresholdValue = s+43+o->lengthTLVData;
	o->terminalFloorLimit = s+47+o->lengthTLVData;
	o->defaultTDOLLength = s+51+o->lengthTLVData;
	o->defaultTDOL = s+53+o->lengthTLVData;
	o->defaultDDOLLength = s+53+o->lengthTLVData+littleEndianInt(o->defaultTDOLLength);
	o->defaultDDOL = s+55+o->lengthTLVData+littleEndianInt(o->defaultTDOLLength);
	return o;
}
