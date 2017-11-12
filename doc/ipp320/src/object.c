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

char * AIDListToBin(AID * o) {
	int length = 0;
	AID * tail = o;
	while (NULL != tail) {
		length += AIDLength(tail);
		tail = tail->next;
	}
	char * s = malloc(length);
	memset(s, 0, length);
	length = 0;
	tail = o;
	while (NULL != tail) {
		int tempLength = AIDLength(tail);
		memcpy(s + length, AIDToBin(tail), tempLength);
		tail = tail->next;
		length += tempLength;
	}
	return s;
}

AID * AIDListFromBin(char * s, int length) {
	AID * o = AIDFromBin(s);
	AID * tail = o;
	int index = AIDLength(o);
	while (index < length - 1) {
		tail->next = AIDListFromBin(s + index, length - index);
		index += AIDLength(tail->next);
		tail = tail->next;
	}
	return o;
}

char * KeyDataToBin(KeyData * o, int size) {
	int length = 276 * size;
	char * s = malloc(length);
	memset(s, 0, length);
	for (int i = 0; i < size; i++) {
		s[i * 276] = o[i].keyIndex;
		s[i * 276 + 1] = o[i].keyAlgorithmIndicator;
		s[i * 276 + 2] = o[i].hashAlgorithmIndicator;
		s[i * 276 + 3] = o[i].keyLength;
		memcpy(s + i * 276 + 4, o[i].key, 248);
		s[i * 276 + 252] = o[i].keyExponentLength;
		memcpy(s + i * 276 + 253, o[i].keyExponent, 3);
		memcpy(s + i * 276 + 256, o[i].keyCheckSum, 20);
	}
	return s;
}

KeyData * KeyDataFromBin(char * s, int length) {
	int size = length / 276;
	KeyData * o = calloc(size, sizeof(KeyData));
	for (int i = 0; i < size; i++) {
		o[i].keyIndex = s[i * 276];
		o[i].keyAlgorithmIndicator = s[i * 276 + 1];
		o[i].hashAlgorithmIndicator = s[i * 276 + 2];
		o[i].keyLength = s[i * 276 + 3];
		o[i].key = s + i * 276 + 4;
		o[i].keyExponentLength = s[i * 276 + 252];
		o[i].keyExponent = s + i * 276 + 253;
		o[i].keyCheckSum = s + i * 276 + 256;
	}
	return o;
}

char * TagsToBin(Tag * tags, int size) {
	int length = size << 1; // *2bytes
	char * s = malloc(length);
	memset(s, 0, length);
	for (int i = 0; i < size; i++) {
		memcpy(s + i * 2, littleEndianBin(tags[i].id), 2);
	}
	return s;
}

Tag * TagsFromBin(char * s, int length) {
	int size = length >> 1; // /2bytes
	Tag * tags = calloc(size, sizeof(Tag));
	for (int i = 0; i < size; i++) {
		tags[i].id = littleEndianInt(s + i * 2);
	}
	return tags;
}

char * LengthThenTagsToBin(LengthThenTags * o) {
	int length = 1 + o->length;
	char * s = malloc(length);
	memset(s, 0, length);
	s[0] = o->length;
	int size = length >> 1;
	memcpy(s + 1, TagsToBin(o->tags, size), length);
	return s;
}

LengthThenTags * LengthThenTagsFromBin(char * s) {
	LengthThenTags * o = malloc(sizeof(LengthThenTags));
	o->length = s[0];
	o->tags = TagsFromBin(s + 1, o->length);
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
	int length = TerminalSpecificDataLength(o);
	char * s = malloc(length);
	memset(s, 0, length);
	//RFU*1
	memcpy(s + 1, o->terminalCapabilities, 3);
	memcpy(s + 4, o->additionalTerminalCapabilities, 5);
	memcpy(s + 9, o->terminalCountryCode, 2);
	s[11] = o->terminalType;
	memcpy(s + 12, o->transactionCurrencyCode, 2);
	s[14] = o->transactionCurrencyExponent;
	memcpy(s + 15, o->transactionReferenceCurrencyCode, 2);
	s[17] = o->transactionReferenceCurrencyExponent;
	memcpy(s + 18, o->transactionReferenceCurrencyConversion, 4);
	memcpy(s + 22, o->acquirerIdentifier, 6);
	memcpy(s + 28, o->merchantCategoryCode, 2);
	memcpy(s + 30, o->merchantIdentifier, 15);
	memcpy(s + 45, o->terminalIdentification, 8);
	memcpy(s + 53, o->terminalRiskManagementData, 8);
	memcpy(s + 61, o->ifdSerialNumber, 8);
	memcpy(s + 69, o->authorizationResponseCodeList, 20);
	s[89] = o->miscellaneousOptions;
	s[90] = o->miscellaneousOptions1;
	//RFU*1
	memcpy(s + 92, o->lengthTLVData, 2);
	int tlv = littleEndianInt(o->lengthTLVData);
	memcpy(s + 94, o->tlvData, tlv);
	//RFU*20
	memcpy(s + 114 + tlv, o->lengthOfflinePINEntryConfiguration, 2);
	int offline = littleEndianInt(o->lengthOfflinePINEntryConfiguration);
	memcpy(s + 116 + tlv,
			OfflinePINEntryConfigurationToBin(o->offlinePINEntryConfiguration),
			offline);
	memcpy(s + 116 + tlv + offline, o->terminalLanguages, 8);
	// RFU*2*2
	memcpy(s + 128 + tlv + offline, o->lengthDiagnosticsTags, 2);
	int diag = littleEndianInt(o->lengthDiagnosticsTags);
	memcpy(s + 130 + tlv + offline, o->diagnosticsTags, diag);
	memcpy(s + 130 + tlv + offline + diag, o->lengthAppSelectionTags, 2);
	int app = littleEndianInt(o->lengthAppSelectionTags);
	memcpy(s + 132 + tlv + offline + diag, o->diagnosticsTags, app);
	memcpy(s + 132 + tlv + offline + diag + app, o->lengthRIDApps, 2);
	int rid = littleEndianInt(o->lengthRIDApps);
	memcpy(s + 134 + tlv + offline + diag + app, o->ridApps, rid);
	return s;
}

TerminalSpecificData * TerminalSpecificDataFromBin(char * s) {
	TerminalSpecificData * o = malloc(sizeof(TerminalSpecificData));
	// RFU*1
	o->terminalCapabilities = s + 1;
	o->additionalTerminalCapabilities = s + 4;
	o->terminalCountryCode = s + 9;
	o->terminalType = s[11];
	o->transactionCurrencyCode = s + 12;
	o->transactionCurrencyExponent = s[14];
	o->transactionReferenceCurrencyCode = s + 15;
	o->transactionReferenceCurrencyExponent = s[17];
	o->transactionReferenceCurrencyConversion = s + 18;
	o->acquirerIdentifier = s + 22;
	o->merchantCategoryCode = s + 28;
	o->merchantIdentifier = s + 30;
	o->terminalIdentification = s + 45;
	o->terminalRiskManagementData = s + 53;
	o->ifdSerialNumber = s + 61;
	o->authorizationResponseCodeList = s + 69;
	o->miscellaneousOptions = s[89];
	o->miscellaneousOptions1 = s[90];
	//RFU*1
	o->lengthTLVData = s + 92;
	int tlv = littleEndianInt(o->lengthTLVData);
	o->tlvData = s + 94;
	//RFU*20
	o->lengthOfflinePINEntryConfiguration = s + 114 + tlv;
	int offline = littleEndianInt(o->lengthOfflinePINEntryConfiguration);
	o->offlinePINEntryConfiguration = OfflinePINEntryConfigurationFromBin(
			s + 116 + tlv);
	o->terminalLanguages = s + 116 + tlv + offline;
	// RFU*2*2
	o->lengthDiagnosticsTags = s + 128 + tlv + offline;
	int diag = littleEndianInt(o->lengthDiagnosticsTags);
	o->diagnosticsTags = s + 130 + tlv + offline;
	o->lengthAppSelectionTags = s + 130 + tlv + offline + diag;
	int app = littleEndianInt(o->lengthAppSelectionTags);
	o->appSelectionTags = s + 132 + tlv + offline + diag;
	o->lengthRIDApps = s + 132 + tlv + offline + diag + app;
	o->ridApps = s + 134 + tlv + offline + diag + app;
	return o;
}

