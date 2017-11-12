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

char * KeyDataToBin(KeyData * o) {
	int length = 276;
	int total = 0;
	char * s = NULL;
	KeyData head;
	head.next = o;
	KeyData * cursor = &head;
	while ((*cursor).next != NULL) {
		total += length;
		char * temp = malloc(total);
		memset(temp, 0, total);
		if (NULL != s) {
			memcpy(temp, s, total - length);
			free(s);
		}
		temp[total - length] = o->keyIndex;
		temp[total - length + 1] = o->keyAlgorithmIndicator;
		temp[total - length + 2] = o->hashAlgorithmIndicator;
		temp[total - length + 3] = o->keyLength;
		memcpy(temp + total - length + 4, o->key, 248);
		temp[total - length + 252] = o->keyExponentLength;
		memcpy(temp + total - length + 253, o->keyExponent, 3);
		memcpy(temp + total - length + 256, o->keyCheckSum, 3);
		cursor = cursor->next;
		s = temp;
	}
	return s;
}

KeyData * KeyDataFromBin(char * s, int length) {
	int index = 0;
	KeyData * o = NULL;
	KeyData * tail = NULL;
	while (index < length) {
		KeyData * temp = malloc(sizeof(KeyData));
		temp->keyIndex = s[index];
		temp->keyAlgorithmIndicator = s[index + 1];
		temp->hashAlgorithmIndicator = s[index + 2];
		temp->keyLength = s[index + 3];
		temp->key = s + index + 4;
		temp->keyExponentLength = s[index + 252];
		temp->keyExponent = s + index + 253;
		temp->keyCheckSum = s + index + 256;

		if (NULL == o) {
			o = temp;
		} else {
			tail->next = temp;
		}
		tail = temp;
		index += 276;

	}
	return o;
}

char * OfflinePINEntryConfigurationToBin(OfflinePINEntryConfiguration * o) {
	char * s = malloc(1138);
	memset(s, 0, 1138);
	s[0] = o->textFont;
	memcpy(s + 1, o->prompt, 1000);
	memcpy(s + 1001, o->promptMAC, 36);
	memcpy(s + 1037, o->promptX, 4);
	memcpy(s + 1041, o->promptY, 4);
	memcpy(s + 1045, o->editX, 4);
	memcpy(s + 1049, o->editY, 4);
	s[1053] = o->formatType;
	memcpy(s + 1054, o->formatSpMAC, 9);
	memcpy(s + 1063, o->formatSpecifier, 50);
	s[1113] = o->minimumKeys;
	s[1114] = o->maximumKeys;
	s[1115] = o->echoCharacter;
	s[1116] = o->cursorType;
	s[1117] = o->direction;
	memcpy(s + 1118, o->beepInvalidKey, 4);
	memcpy(s + 1122, o->timeOutFirstKey, 4);
	memcpy(s + 1126, o->timeOutInterKey, 4);
	s[1130] = o->keyType;
	s[1131] = o->keyIndex;
	memcpy(s + 1132, o->noEnterLessMin, 4);
	memcpy(s + 1136, o->addReqSettings, 2);
	return s;
}

OfflinePINEntryConfiguration * OfflinePINEntryConfigurationFromBin(char * s) {
	OfflinePINEntryConfiguration * o = malloc(
			sizeof(OfflinePINEntryConfiguration));
	o->textFont = s[0];
	o->prompt = s + 1;
	o->promptMAC = s + 1001;
	o->promptX = s + 1037;
	o->promptY = s + 1041;
	o->editX = s + 1045;
	o->editY = s + 1049;
	o->formatType = s[1053];
	o->formatSpMAC = s + 1054;
	o->formatSpecifier = s + 1063;
	o->minimumKeys = s[1113];
	o->maximumKeys = s[1114];
	o->echoCharacter = s[1115];
	o->cursorType = s[1116];
	o->direction = s[1117];
	o->beepInvalidKey = s + 1118;
	o->timeOutFirstKey = s + 1122;
	o->timeOutInterKey = s + 1126;
	o->keyType = s[1130];
	o->keyIndex = s[1131];
	o->noEnterLessMin = s + 1132;
	o->addReqSettings = s + 1136;
	return o;
}

int TerminalSpecificDataLength(TerminalSpecificData * o) {
	return 134 + littleEndianInt(o->lengthTLVData)
			+ littleEndianInt(o->lengthOfflinePINEntryConfiguration)
			+ littleEndianInt(o->lengthDiagnosticsTags)
			+ littleEndianInt(o->lengthAppSelectionTags)
			+ littleEndianInt(o->lengthRIDApps);
}

char * TerminalSpecificDataToBin(TerminalSpecificData * o) {

}

TerminalSpecificData * TerminalSpecificDataFromBin(char * s) {

}

