/*
 * object.c
 * object builders and parsers
 *  Created on: 2017��11��11��
 *      Author: Administrator
 */
#include "ipp320.h"

int AIDLength(AID * o) {
	return NULL == o ?
			0 :
			55 + o->lengthTLVData + littleEndianInt(o->defaultDDOLLength)
					+ littleEndianInt(o->defaultTDOLLength);
}

char * AIDToXML(AID * o) {
	if (NULL == o)
		return NULL;
	char f[1024] = "<AID>";
	strcat(f,
			"<applicationSelectionIndicator>%s</applicationSelectionIndicator>");
	strcat(f, "<lengthTLVData>%s</lengthTLVData>");
	strcat(f, "<tlvData>%s</tlvData>");
	strcat(f, "<aidLength>%s</aidLength>");
	strcat(f, "<aid>%s</aid>");
	strcat(f, "<rid>%s</rid>");
	strcat(f, "<applicationVersionNumber>%s</applicationVersionNumber>");
	strcat(f, "<tacDefault>%s</tacDefault>");
	strcat(f, "<tacDenial>%s</tacDenial>");
	strcat(f, "<tacOnline>%s</tacOnline>");
	strcat(f, "<maximumTargetPercentage>%s</maximumTargetPercentage>");
	strcat(f, "<targetPercentage>%s</targetPercentage>");
	strcat(f, "<thresholdValue>%s</thresholdValue>");
	strcat(f, "<terminalFloorLimit>%s</terminalFloorLimit>");
	strcat(f, "<defaultTDOLLength>%s</defaultTDOLLength>");
	strcat(f, "<defaultTDOL>%s</defaultTDOL>");
	strcat(f, "<defaultDDOLLength>%s</defaultDDOLLength>");
	strcat(f, "<defaultDDOL>%s</defaultDDOL>");
	strcat(f, "</AID>");

	return format(f, hex(&o->applicationSelectionIndicator, 0, 1),
			hex(&o->lengthTLVData, 0, 1),
			hex(o->tlvData, 0, littleEndianInt(&o->lengthTLVData)),
			hex(&o->aidLength, 0, 1), hex(o->aid, 0, 16), hex(o->rid, 0, 5),
			hex(o->applicationVersionNumber, 0, 2), hex(o->tacDefault, 0, 5),
			hex(o->tacDenial, 0, 5), hex(o->tacOnline, 0, 5),
			hex(&o->maximumTargetPercentage, 0, 1),
			hex(&o->targetPercentage, 0, 1), hex(o->thresholdValue, 0, 4),
			hex(o->terminalFloorLimit, 0, 4), hex(o->defaultTDOLLength, 0, 2),
			hex(o->defaultTDOL, 0, littleEndianInt(o->defaultTDOLLength)),
			hex(o->defaultDDOLLength, 0, 2),
			hex(o->defaultDDOL, 0, littleEndianInt(o->defaultDDOLLength)));
}

char * AIDToBin(AID * o) {
	if (NULL == o)
		return NULL;
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
	if (NULL == s)
		return NULL;
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

char * AIDArrayToBin(AID * o, int size) {
	if (NULL == o || 0 == size)
		return NULL;
	int length = 0;
	for (int i = 0; i < size; i++) {
		length += AIDLength(o + i);
	}
	char * s = malloc(length);
	memset(s, 0, length);
	length = 0;
	for (int i = 0; i < size; i++) {
		int tempLength = AIDLength(o + i);
		memcpy(s + length, AIDToBin(o + i), tempLength);
		length += tempLength;
	}
	return s;
}

AID * AIDArrayFromBin(char * s, int length) {
	if (NULL == s || 0 >= length)
		return NULL;
	AID * o = AIDFromBin(s);
	int size = 1;
	int index = AIDLength(o);
	while (index < length) {
		size++;
		o = realloc(o, size);
		o[size - 1] = *(AIDFromBin(s + index));
		index += AIDLength(o + size - 1);
	}
	return o;
}

char * AIDListToBin(AID * o) {
	if (NULL == o)
		return NULL;
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
	if (NULL == s || 0 >= length)
		return NULL;
	AID * o = AIDFromBin(s);
	AID * tail = o;
	int index = AIDLength(o);
	while (index < length) {
		tail->next = AIDFromBin(s + index);
		index += AIDLength(tail->next);
		tail = tail->next;
	}
	tail->next = NULL;
	return o;
}

char * KeyDataToBin(KeyData * o, int size) {
	if (NULL == o || 0 == size)
		return NULL;
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
	if (NULL == s || 0 >= length)
		return NULL;
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
	if (NULL == tags || 0 == size)
		return NULL;
	int length = size << 1; // *2bytes
	char * s = malloc(length);
	memset(s, 0, length);
	for (int i = 0; i < size; i++) {
		memcpy(s + i * 2, littleEndianBin(tags[i].id), 2);
	}
	return s;
}

Tag * TagsFromBin(char * s, int length) {
	if (NULL == s || 0 >= length)
		return NULL;
	int size = length >> 1; // /2bytes
	Tag * tags = calloc(size, sizeof(Tag));
	for (int i = 0; i < size; i++) {
		tags[i].id = littleEndianInt(s + i * 2);
	}
	return tags;
}

char * LengthThenTagsToBin(LengthThenTags * o) {
	if (NULL == o)
		return NULL;
	int length = 1 + o->length;
	char * s = malloc(length);
	memset(s, 0, length);
	s[0] = o->length;
	if (0 != o->length) {
		int size = length >> 1;
		memcpy(s + 1, TagsToBin(o->tags, size), length);
	}
	return s;
}

LengthThenTags * LengthThenTagsFromBin(char * s) {
	if (NULL == s)
		return NULL;
	LengthThenTags * o = malloc(sizeof(LengthThenTags));
	o->length = s[0];
	o->tags = TagsFromBin(s + 1, o->length);
	return o;
}

int RIDLength(RID * o) {
	return NULL == o ?
			0 :
			30 + littleEndianInt(o->keyDataTotalLength)
					+ littleEndianInt(o->lengthGoOnlineTags)
					+ littleEndianInt(o->lengthEndOfTransactionTags)
					+ littleEndianInt(o->lengthGetPreviousAmountTags)
					+ littleEndianInt(o->lengthExtendedAPIData)
					+ littleEndianInt(o->lengthIgnoredTags)
					+ littleEndianInt(o->lengthTLVData);
}

char * RIDToBin(RID * o) {
	if (NULL == o)
		return NULL;
	int length = RIDLength(o);
	char * s = malloc(length);
	memset(s, 0, length);
	memcpy(s, o->rid, 5);
	memcpy(s + 5, o->keyDataTotalLength, 2);
	int key = littleEndianInt(o->keyDataTotalLength);
	memcpy(s + 7, KeyDataToBin(o->keyDatas, key / 276), key);
	memcpy(s + 7 + key, o->lengthGoOnlineTags, 2);
	int online = littleEndianInt(o->lengthGoOnlineTags);
	memcpy(s + 9 + key, TagsToBin(o->goOnlineTags, online >> 1), online);
	memcpy(s + 9 + key + online, o->lengthEndOfTransactionTags, 2);
	int end = littleEndianInt(o->lengthEndOfTransactionTags);
	int index = 11 + key + online;
	for (int i = 0; i < 7; i++) { // EMV Transaction Type
		memcpy(s + index, LengthThenTagsToBin(o->endOfTransactionTags + i),
				1 + o->endOfTransactionTags[i].length);
		index += o->endOfTransactionTags[i].length;
	}
	memcpy(s + 11 + key + online + end, o->endOfTransactionStep, 7);
	memcpy(s + 18 + key + online + end, o->lengthGetPreviousAmountTags, 2);
	int previous = littleEndianInt(o->lengthGetPreviousAmountTags);
	memcpy(s + 20 + key + online + end,
			TagsToBin(o->getPreviousAmountTags, previous >> 1), previous);
	memcpy(s + 20 + key + online + end + previous, o->lengthExtendedAPIData, 2);
	int extended = littleEndianInt(o->lengthExtendedAPIData);
	// ExtendedAPIData.LengthEMVStepTags = extended - 2
	memcpy(s + 22 + key + online + end + previous,
			littleEndianBin(extended - 2), 2);
	index = 24 + key + online + end + previous;
	for (int i = 0; i < 7; i++) {
		for (int j = 0; j < 8; j++) {
			// RFU*2
			index += 2;
			memcpy(s + index, LengthThenTagsToBin(o->extendedAPIData + i * j),
					1 + o->extendedAPIData[i * j].length);
			index += 1 + o->extendedAPIData[i * j].length;
			memcpy(s + index,
					LengthThenTagsToBin(o->extendedAPIData + i * j + 1),
					1 + o->extendedAPIData[i * j + 1].length);
			index += 1 + o->extendedAPIData[i * j + 1].length;
		}
	}
	// LengthProprietaryRIDData = 0x0000, ProprietaryRIDData = NULL
	memcpy(s + 22 + key + online + end + previous + extended,
			o->lengthProprietaryRIDData, 2);
	int proprietary = littleEndianInt(o->lengthProprietaryRIDData);
	if (0 != proprietary) {
		memcpy(s + 24 + key + online + end + previous + extended,
				o->proprietaryRIDData, proprietary);
	}
	memcpy(s + 24 + key + online + end + previous + extended + proprietary,
			o->lengthIgnoredTags, 2);
	int ignore = littleEndianInt(o->lengthIgnoredTags);
	memcpy(s + 26 + key + online + end + previous + extended + proprietary,
			TagsToBin(o->ignoreTags, ignore >> 1), ignore);
	// RFU*1
	s[27 + key + online + end + previous + extended + proprietary + ignore] =
			o->miscellaneousOptions;
	memcpy(
			s + 28 + key + online + end + previous + extended + proprietary
					+ ignore, o->lengthTLVData, 2);
	int tlv = littleEndianInt(o->lengthTLVData);
	memcpy(
			s + 30 + key + online + end + previous + extended + proprietary
					+ ignore, o->tlvData, tlv);
	return s;
}

RID * RIDFromBin(char * s) {
	if (NULL == s)
		return NULL;
	RID * o = malloc(sizeof(RID));
	o->rid = s;
	o->keyDataTotalLength = s + 5;
	int key = littleEndianInt(o->keyDataTotalLength);
	o->keyDatas = KeyDataFromBin(s + 7, key);
	o->lengthGoOnlineTags = s + 7 + key;
	int online = littleEndianInt(o->lengthGoOnlineTags);
	o->goOnlineTags = TagsFromBin(s + 9 + key, online);
	o->lengthEndOfTransactionTags = s + 9 + key + online;
	int end = littleEndianInt(o->lengthEndOfTransactionTags);
	o->endOfTransactionTags = calloc(7, sizeof(LengthThenTags));
	int index = 11 + key + online;
	for (int i = 0; i < 7; i++) {
		o->endOfTransactionTags[i] = *LengthThenTagsFromBin(s + index);
		index += 1 + o->endOfTransactionTags[i].length;
	}
	o->endOfTransactionStep = s + 11 + key + online + end;
	o->lengthGetPreviousAmountTags = s + 18 + key + online + end;
	int previous = littleEndianInt(o->lengthGetPreviousAmountTags);
	o->getPreviousAmountTags = TagsFromBin(s + 20 + key + online + end,
			previous);
	o->lengthExtendedAPIData = s + 20 + key + online + end + previous;
	int extended = littleEndianInt(o->lengthExtendedAPIData);
	index = 22 + key + online + end + previous + 2; // ExtendedAPIData.LengthEMVStepTags
	o->extendedAPIData = calloc(7 * 8 * 2, sizeof(LengthThenTags));
	for (int i = 0; i < 7; i++) {
		for (int j = 0; j < 8; j++) {
			// RFU*2
			index += 2;
			o->extendedAPIData[i * j] = *LengthThenTagsFromBin(s + index);
			index += 1 + o->extendedAPIData[i * j].length;
			o->extendedAPIData[i * j + 1] = *LengthThenTagsFromBin(s + index);
			index += 1 + o->extendedAPIData[i * j + 1].length;
		}
	}
	// LengthProprietaryRIDData = 0x0000, ProprietaryRIDData = NULL
	o->lengthProprietaryRIDData = s + 22 + key + online + end + previous
			+ extended;
	o->proprietaryRIDData = NULL;
	o->lengthIgnoredTags = s + 24 + key + online + end + previous + extended;
	int ignore = littleEndianInt(o->lengthIgnoredTags);
	o->ignoreTags = TagsFromBin(
			s + 26 + key + online + end + previous + extended, ignore);
	//RFU*1
	o->miscellaneousOptions = s[27 + key + online + end + previous + extended
			+ ignore];
	o->lengthTLVData = s + 28 + key + online + end + previous + extended
			+ ignore;
	o->tlvData = s + 30 + key + online + end + previous + extended + ignore;
	return o;
}

char * RIDListToBin(RID * o) {
	if (NULL == o)
		return NULL;
	int length = 0;
	RID * tail = o;
	while (NULL != tail) {
		length += RIDLength(tail);
		tail = tail->next;
	}
	char * s = malloc(length);
	memset(s, 0, length);
	length = 0;
	tail = o;
	while (NULL != tail) {
		int tempLength = RIDLength(tail);
		memcpy(s + length, RIDToBin(tail), tempLength);
		tail = tail->next;
		length += tempLength;
	}
	return s;
}

RID * RIDListFromBin(char * s, int length) {
	if (NULL == s || 0 >= length)
		return NULL;
	RID * o = RIDFromBin(s);
	RID * tail = o;
	int index = RIDLength(o);
	while (index < length) {
		tail->next = RIDFromBin(s + index);
		index += RIDLength(tail->next);
		tail = tail->next;
	}
	tail->next = NULL;
	return o;
}

char * OfflinePINEntryConfigurationToXML(OfflinePINEntryConfiguration * o) {
	if (NULL == o)
		return NULL;
	char f[1024] = "<OfflinePINEntryConfiguration>";
	strcat(f, "<textFont>%s</textFont>");
	strcat(f, "<prompt>%s</prompt>");
	strcat(f, "<promptMAC>%s</promptMAC>");
	strcat(f, "<promptX>%s</promptX>");
	strcat(f, "<promptY>%s</promptY>");
	strcat(f, "<editX>%s</editX>");
	strcat(f, "<editY>%s</editY>");
	strcat(f, "<formatType>%s</formatType>");
	strcat(f, "<formatSpMAC>%s</formatSpMAC>");
	strcat(f, "<formatSpecifier>%s</formatSpecifier>");
	strcat(f, "<minimumKeys>%s</minimumKeys>");
	strcat(f, "<maximumKeys>%s</maximumKeys>");
	strcat(f, "<echoCharacter>%s</echoCharacter>");
	strcat(f, "<direction>%s</direction>");
	strcat(f, "<beepInvalidKey>%s</beepInvalidKey>");
	strcat(f, "<timeOutFirstKey>%s</timeOutFirstKey>");
	strcat(f, "<timeOutInterKey>%s</timeOutInterKey>");
	strcat(f, "<keyType>%s</keyType>");
	strcat(f, "<keyIndex>%s</keyIndex>");
	strcat(f, "<noEnterLessMin>%s</noEnterLessMin>");
	strcat(f, "<addReqSettings>%s</addReqSettings>");
	strcat(f, "</OfflinePINEntryConfiguration>");
	return format(f, hex(&o->textFont, 0, 1),
			hex(o->prompt, 0, 1000),
			hex(o->promptMAC, 0, 36),
			hex(o->promptX, 0, 4),
			hex(o->promptY, 0, 4),
			hex(o->editX, 0, 4),
			hex(o->editY, 0, 4),
			hex(&o->formatType, 0, 1),
			hex(o->formatSpMAC, 0, 9),
			hex(o->formatSpecifier, 0, 50),
			hex(&o->minimumKeys, 0, 1),
			hex(&o->maximumKeys, 0, 1),
			hex(&o->echoCharacter, 0, 1),
			hex(&o->cursorType, 0, 1),
			hex(&o->direction, 0, 1),
			hex(o->beepInvalidKey, 0, 4),
			hex(o->timeOutFirstKey, 0, 4),
			hex(o->timeOutInterKey, 0, 4),
			hex(&o->keyType, 0, 1),
			hex(&o->keyIndex, 0, 1),
			hex(o->noEnterLessMin, 0, 4),
			hex(o->addReqSettings, 0, 2)
	);
}

char * OfflinePINEntryConfigurationToBin(OfflinePINEntryConfiguration * o) {
	if (NULL == o)
		return NULL;
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
	if (NULL == s)
		return NULL;
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
	return NULL == o ?
			0 :
			134 + littleEndianInt(o->lengthTLVData)
					+ littleEndianInt(o->lengthOfflinePINEntryConfiguration)
					+ littleEndianInt(o->lengthDiagnosticsTags)
					+ littleEndianInt(o->lengthAppSelectionTags)
					+ littleEndianInt(o->lengthRIDApps);
}

char * TerminalSpecificDataToXML(TerminalSpecificData * o) {
	if (NULL == o)
		return NULL;
	char f[1024] = "<TerminalSpecificData>";
	strcat(f, "<terminalCapabilities>%s</terminalCapabilities>");
	strcat(f,
			"<additionalTerminalCapabilities>%s</additionalTerminalCapabilities>");
	strcat(f, "<terminalCountryCode>%s</terminalCountryCode>");
	strcat(f, "<terminalType>%s</terminalType>");
	strcat(f, "<transactionCurrencyCode>%s</transactionCurrencyCode>");
	strcat(f, "<transactionCurrencyExponent>%s</transactionCurrencyExponent>");
	strcat(f,
			"<transactionReferenceCurrencyCode>%s</transactionReferenceCurrencyCode>");
	strcat(f,
			"<transactionReferenceCurrencyExponent>%s</transactionReferenceCurrencyExponent>");
	strcat(f,
			"<transactionReferenceCurrencyConversion>%s</transactionReferenceCurrencyConversion>");
	strcat(f, "<acquirerIdentifier>%s</acquirerIdentifier>");
	strcat(f, "<merchantCategoryCode>%s</merchantCategoryCode>");
	strcat(f, "<merchantIdentifier>%s</merchantIdentifier>");
	strcat(f, "<terminalIdentification>%s</terminalIdentification>");
	strcat(f, "<terminalRiskManagementData>%s</terminalRiskManagementData>");
	strcat(f, "<ifdSerialNumber>%s</ifdSerialNumber>");
	strcat(f,
			"<authorizationResponseCodeList>%s</authorizationResponseCodeList>");
	strcat(f, "<miscellaneousOptions>%s</miscellaneousOptions>");
	strcat(f, "<miscellaneousOptions1>%s</miscellaneousOptions1>");

	// *** stack smashing detected ***: <unknown> terminated
	// to fix this issue, we need to split this into 3 times of method calling

	char * head = format(f, hex(o->terminalCapabilities, 0, 3),
			hex(o->additionalTerminalCapabilities, 0, 5),
			hex(o->terminalCountryCode, 0, 2),
			hex(&o->terminalType, 0, 1),
			hex(o->transactionCurrencyCode, 0, 2),
			hex(&o->transactionCurrencyExponent, 0, 1),
			hex(o->transactionReferenceCurrencyCode, 0, 2),
			hex(&o->transactionReferenceCurrencyExponent, 0, 1),
			hex(o->transactionReferenceCurrencyConversion, 0, 4),
			hex(o->acquirerIdentifier, 0, 6),
			hex(o->merchantCategoryCode, 0, 2),
			hex(o->merchantIdentifier, 0, 15),
			hex(o->terminalIdentification, 0, 8),
			hex(o->terminalRiskManagementData, 0, 8),
			hex(o->ifdSerialNumber, 0, 8),
			hex(o->authorizationResponseCodeList, 0, 20),
			hex(&o->miscellaneousOptions, 0, 1),
			hex(&o->miscellaneousOptions1, 0, 1));
	memset(f, 0, 1024);
	strcat(f, "<lengthTLVData>%s</lengthTLVData>");
	strcat(f, "<tlvData>%s</tlvData>");
	strcat(f,
			"<lengthOfflinePINEntryConfiguration>%s</lengthOfflinePINEntryConfiguration>");
	strcat(f, "%s");
	strcat(f, "<terminalLanguages>%s</terminalLanguages>");
	strcat(f, "<lengthDiagnosticsTags>%s</lengthDiagnosticsTags>");
	strcat(f, "<diagnosticsTags>%s</diagnosticsTags>");
	strcat(f, "<lengthAppSelectionTags>%s</lengthAppSelectionTags>");
	strcat(f, "<appSelectionTags>%s</appSelectionTags>");
	strcat(f, "<lengthRIDApps>%s</lengthRIDApps>");
	strcat(f, "<ridApps>%s</ridApps>");
	strcat(f, "</TerminalSpecificData>");

	char * tail = format(f, hex(o->lengthTLVData, 0, 2),
			hex(o->tlvData, 0, littleEndianInt(o->lengthTLVData)),
			hex(o->lengthOfflinePINEntryConfiguration, 0, 2),
			OfflinePINEntryConfigurationToXML(o->offlinePINEntryConfiguration),
			hex(o->terminalLanguages, 0, 8),
			hex(o->lengthDiagnosticsTags, 0, 2),
			hex(o->diagnosticsTags, 0, littleEndianInt(o->lengthDiagnosticsTags)),
			hex(o->lengthAppSelectionTags, 0, 2),
			hex(o->appSelectionTags, 0, littleEndianInt(o->lengthAppSelectionTags)),
			hex(o->lengthRIDApps, 0, 2),
			hex(o->ridApps, 0, littleEndianInt(o->ridApps)));

	return format("%s%s", head, tail);
}

char * TerminalSpecificDataToBin(TerminalSpecificData * o) {
	if (NULL == o)
		return NULL;
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
	memcpy(s + 132 + tlv + offline + diag, o->appSelectionTags, app);
	memcpy(s + 132 + tlv + offline + diag + app, o->lengthRIDApps, 2);
	int rid = littleEndianInt(o->lengthRIDApps);
	memcpy(s + 134 + tlv + offline + diag + app, o->ridApps, rid);
	return s;
}

TerminalSpecificData * TerminalSpecificDataFromBin(char * s) {
	if (NULL == s)
		return NULL;
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

int VegaInitDataLength(VegaInitData * o) {
	// append 0x00 at the end of the binary data.
	return NULL == o ?
			0 :
			7 + littleEndianInt(o->aidDataTotalLength)
					+ littleEndianInt(o->ridDataTotalLength)
					+ littleEndianInt(o->terminalDataTotalLength);
}

char * VegaInitDataToBin(VegaInitData * o) {
	if (NULL == o)
		return NULL;
	int length = VegaInitDataLength(o);
	char * s = malloc(length);
	memset(s, 0, length);
	int terminal = littleEndianInt(o->terminalDataTotalLength);
	memcpy(s, o->terminalDataTotalLength, 2);
	memcpy(s + 2, TerminalSpecificDataToBin(o->terminalSpecificData), terminal);

	memcpy(s + 2 + terminal, o->ridDataTotalLength, 2);
	int rid = littleEndianInt(o->ridDataTotalLength);
	memcpy(s + 4 + terminal, RIDListToBin(o->ridSpecificData), rid);

	memcpy(s + 4 + terminal + rid, o->aidDataTotalLength, 2);
	int aid = littleEndianInt(o->aidDataTotalLength);
	memcpy(s + 6 + terminal + rid, AIDListToBin(o->aidSpecificData), aid);
	return s;
}

VegaInitData * VegaInitDataFromBin(char * s) {
	if (NULL == s)
		return NULL;
	VegaInitData * o = malloc(sizeof(VegaInitData));
	o->terminalDataTotalLength = s;
	int terminal = littleEndianInt(o->terminalDataTotalLength);
	o->terminalSpecificData = TerminalSpecificDataFromBin(s + 2);

	o->ridDataTotalLength = s + 2 + terminal;
	int rid = littleEndianInt(o->ridDataTotalLength);
	o->ridSpecificData = RIDListFromBin(s + 4 + terminal, rid);

	o->aidDataTotalLength = s + 4 + terminal + rid;
	int aid = littleEndianInt(o->aidDataTotalLength);
	o->aidSpecificData = AIDListFromBin(s + 6 + terminal + rid, aid);
	return o;
}
