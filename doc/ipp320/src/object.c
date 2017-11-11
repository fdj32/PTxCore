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
	memcpy(s + 2, o->tlvData, tlv);
	s[2 + tlv] = o->aidLength;
	memcpy(s + 3 + tlv, o->aid, o->aidLength); // 16
	memcpy(s + 19 + tlv, o->rid, 5);
	memcpy(s + 24 + tlv, o->applicationVersionNumber, 2);
	memcpy(s + 26 + tlv, o->tacDefault, 5);
	memcpy(s + 31 + tlv, o->tacDenial, 5);
	memcpy(s + 36 + tlv, o->tacOnline, 5);
	s[41 + tlv] = o->maximumTargetPercentage;
	s[42 + tlv] = o->targetPercentage;
	memcpy(s + 43 + tlv, o->thresholdValue, 4);
	memcpy(s + 47 + tlv, o->terminalFloorLimit, 4);
	memcpy(s + 51 + tlv, o->defaultTDOLLength, 2);
	int tdol = littleEndianInt(o->defaultTDOLLength);
	memcpy(s + 53 + tlv, o->defaultTDOL, tdol);
	int ddol = littleEndianInt(o->defaultDDOLLength);
	memcpy(s + 53 + tlv + tdol, o->defaultDDOLLength, 2);
	memcpy(s + 55 + tlv + tdol, o->defaultDDOL, ddol);
	return s;
}

AID * AIDFromBin(char * s) {
	AID * o = malloc(sizeof(AID));
	o->applicationSelectionIndicator = s[0];
	o->lengthTLVData = s[1];
	o->tlvData = s + 2;
	int tlv = o->lengthTLVData;
	o->aidLength = s[2 + tlv];
	o->aid = s + 3 + tlv;
	o->rid = s + 19 + tlv;
	o->applicationVersionNumber = s + 24 + tlv;
	o->tacDefault = s + 26 + tlv;
	o->tacDenial = s + 31 + tlv;
	o->tacOnline = s + 36 + tlv;
	o->maximumTargetPercentage = s[41 + tlv];
	o->targetPercentage = s[42 + tlv];
	o->thresholdValue = s + 43 + tlv;
	o->terminalFloorLimit = s + 47 + tlv;
	o->defaultTDOLLength = s + 51 + tlv;
	o->defaultTDOL = s + 53 + tlv;
	int tdol = littleEndianInt(o->defaultTDOLLength);
	o->defaultDDOLLength = s + 53 + tlv + tdol;
	o->defaultDDOL = s + 55 + tlv + tdol;
	return o;
}
