/*
 * object.c
 * object builders and parsers
 *  Created on: 2017Äê11ÔÂ11ÈÕ
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
	char * s = malloc(10240);
	memset(o, 0, 10240);
	int i = 0;
	i += sprintf(s + i, "<AID>\n<applicationSelectionIndicator>%s</applicationSelectionIndicator>\n", hexByte(o->applicationSelectionIndicator));
	i += sprintf(s + i, "<lengthTLVData>%s</lengthTLVData>\n", hexByte(o->lengthTLVData));
	i += sprintf(s + i, "<tlvData>%s</tlvData>\n", hex(o->tlvData, 0, (int)(o->lengthTLVData)));
	i += sprintf(s + i, "<aidLength>%s</aidLength>\n", hexByte(o->aidLength));
	i += sprintf(s + i, "<aid>%s</aid>\n", hex(o->aid, 0, 16));
	i += sprintf(s + i, "<rid>%s</rid>\n", hex(o->rid, 0, 5));
	i += sprintf(s + i, "<applicationVersionNumber>%s</applicationVersionNumber>\n", hex(o->applicationVersionNumber, 0, 2));
	i += sprintf(s + i, "<tacDefault>%s</tacDefault>\n", hex(o->tacDefault, 0, 5));
	i += sprintf(s + i, "<tacDenial>%s</tacDenial>\n", hex(o->tacDenial, 0, 5));
	i += sprintf(s + i, "<tacOnline>%s</tacOnline>\n", hex(o->tacOnline, 0, 5));
	i += sprintf(s + i, "<maximumTargetPercentage>%s</maximumTargetPercentage>\n", hexByte(o->maximumTargetPercentage));
	i += sprintf(s + i, "<targetPercentage>%s</targetPercentage>\n", hexByte(o->targetPercentage));
	i += sprintf(s + i, "<thresholdValue>%s</thresholdValue>\n", hex(o->thresholdValue, 0, 4));
	i += sprintf(s + i, "<terminalFloorLimit>%s</terminalFloorLimit>\n", hex(o->terminalFloorLimit, 0, 4));
	i += sprintf(s + i, "<defaultTDOLLength>%s</defaultTDOLLength>\n", hex(o->defaultTDOLLength, 0, 2));
	i += sprintf(s + i, "<defaultTDOL>%s</defaultTDOL>\n", hex(o->defaultTDOL, 0, littleEndianInt(o->defaultTDOLLength)));
	i += sprintf(s + i, "<defaultDDOLLength>%s</defaultDDOLLength>\n", hex(o->defaultDDOLLength, 0, 2));
	i += sprintf(s + i, "<defaultDDOL>%s</defaultDDOL>\n</AID>\n", hex(o->defaultDDOL, 0, littleEndianInt(o->defaultDDOLLength)));
	return s;
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
	memset(o, 0, sizeof(AID));
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

char * KeyDataToXML(KeyData * o, int size) {
	if(NULL == o)
		return NULL;
	char * s = malloc(102400);
	memset(s, 0, 102400);
	int i = 0;
	for(int j = 0; j < size; j++) {
		i += sprintf(s+i, "<KeyData>\n<keyIndex>%s</keyIndex>\n", hexByte((o+j)->keyIndex));
		i += sprintf(s+i, "<keyAlgorithmIndicator>%s</keyAlgorithmIndicator>\n", hexByte((o+j)->keyAlgorithmIndicator));
		i += sprintf(s+i, "<hashAlgorithmIndicator>%s</hashAlgorithmIndicator>\n", hexByte((o+j)->hashAlgorithmIndicator));
		i += sprintf(s+i, "<keyLength>%s</keyLength>\n", hexByte((o+j)->keyLength));
		i += sprintf(s+i, "<key>%s</key>\n", hex((o+j)->key, 0, 248));
		i += sprintf(s+i, "<keyExponentLength>%s</keyExponentLength>\n", hexByte((o+j)->keyExponentLength));
		i += sprintf(s+i, "<keyExponent>%s</keyExponent>\n", hex((o+j)->keyExponent, 0, 3));
		i += sprintf(s+i, "<keyCheckSum>%s</keyCheckSum>\n</KeyData>\n", hex((o+j)->keyCheckSum, 0, 20));
	}
	return s;
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

char * TagsToXML(Tag * tags, int size) {
	if(NULL == tags)
		return NULL;
	char * s = malloc(512);
	memset(s, 0, 512);
	int j = 0;
	for(int i =0; i < size; i++) {
		j += sprintf(s, "<Tag id=\"%s\">", hex(littleEndianBin((tags+i)->id), 0, 2));
	}
	return s;
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

char * LengthThenTagsToXML(LengthThenTags * o, int size) {
	if(NULL == o)
		return NULL;
	char * s = malloc(102400);
	memset(s, 0, 102400);
	for(int i=0; i<size; i++) {
		if(0 == (o+i)->length) {
			strcat(s, "<LengthThenTags><Length>00</Length></LengthThenTags>\n");
			continue;
		}
		strcat(s, "<LengthThenTags>\n<Length>");
		strcat(s, hexByte((o+i)->length));
		strcat(s, "</Length>\n");
		int tagSize = (o+i)->length>>1;
		strcat(s, TagsToXML((o+i)->tags, tagSize));
		strcat(s, "</LengthThenTags>\n");
	}
	return s;
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
	memset(o, 0, sizeof(LengthThenTags));
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

char * RIDToXML(RID * o) {
	if (NULL == o)
		return NULL;
	char * s = malloc(102400);
	memset(s, 0, 102400);
	int i = 0;
	i += sprintf(s+i, "<RID>\n<rid>%s</rid>\n", hex(o->rid, 0, 5));
	i += sprintf(s+i, "<keyDataTotalLength>%s</keyDataTotalLength>\n", hex(o->keyDataTotalLength, 0, 2));
	i += sprintf(s+i, "<keyDatas>%s</keyDatas>\n", KeyDataToXML(o->keyDatas, littleEndianInt(o->keyDataTotalLength)/276));
	i += sprintf(s+i, "<lengthGoOnlineTags>%s</lengthGoOnlineTags>\n", hex(o->lengthGoOnlineTags, 0, 2));
	i += sprintf(s+i, "<goOnlineTags>%s</goOnlineTags>\n", TagsToXML(o->goOnlineTags, littleEndianInt(o->lengthGoOnlineTags)>>1));
	i += sprintf(s+i, "<lengthEndOfTransactionTags>%s</lengthEndOfTransactionTags>\n", hex(o->lengthEndOfTransactionTags, 0, 2));
	i += sprintf(s+i, "<endOfTransactionTags>%s</endOfTransactionTags>\n", LengthThenTagsToXML(o->endOfTransactionTags, 7));
	i += sprintf(s+i, "<endOfTransactionStep>%s</endOfTransactionStep>\n", hex(o->endOfTransactionStep, 0, 7));
	i += sprintf(s+i, "<lengthGetPreviousAmountTags>%s</lengthGetPreviousAmountTags>\n", hex(o->lengthGetPreviousAmountTags, 0, 2));
	i += sprintf(s+i, "<getPreviousAmountTags>%s</getPreviousAmountTags>\n", TagsToXML(o->getPreviousAmountTags, littleEndianInt(o->lengthGetPreviousAmountTags)>>1));
	i += sprintf(s+i, "<lengthExtendedAPIData>%s</lengthExtendedAPIData>\n", hex(o->lengthExtendedAPIData, 0, 2));
	i += sprintf(s+i, "<extendedAPIData>%s</extendedAPIData>\n", LengthThenTagsToXML(o->extendedAPIData, 112));
	i += sprintf(s+i, "<lengthProprietaryRIDData>%s</lengthProprietaryRIDData>\n", hex(o->lengthProprietaryRIDData, 0, 2));
	i += sprintf(s+i, "<proprietaryRIDData>%s</proprietaryRIDData>\n", hex(o->proprietaryRIDData, 0, littleEndianInt(o->lengthProprietaryRIDData)));
	i += sprintf(s+i, "<lengthIgnoredTags>%s</lengthIgnoredTags>\n", hex(o->lengthIgnoredTags, 0, 2));
	i += sprintf(s+i, "<ignoreTags>%s</ignoreTags>\n", TagsToXML(o->ignoreTags, littleEndianInt(o->lengthIgnoredTags)>>1));
	i += sprintf(s+i, "<miscellaneousOptions>%s</miscellaneousOptions>\n", hexByte(o->miscellaneousOptions));
	i += sprintf(s+i, "<lengthTLVData>%s</lengthTLVData>\n", hex(o->lengthTLVData, 0, 2));
	i += sprintf(s+i, "<tlvData>%s</tlvData>\n</RID>\n", hex(o->tlvData, 0, littleEndianInt(o->lengthTLVData)));
	return s;
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
	memset(o, 0, sizeof(RID));
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
	char * s = malloc(10240);
	memset(s, 0, 102400);
	int i = 0;
	i += sprintf(s+i, "<OfflinePINEntryConfiguration>\n<textFont>%s</textFont>\n", hexByte(o->textFont));
	i += sprintf(s+i, "<prompt>%s</prompt>\n", hex(o->prompt, 0, 1000));
	i += sprintf(s+i, "<promptMAC>%s</promptMAC>\n", hex(o->prompt, 0, 36));
	i += sprintf(s+i, "<promptX>%s</promptX>\n", hex(o->promptX, 0, 4));
	i += sprintf(s+i, "<promptY>%s</promptY>\n", hex(o->promptY, 0, 4));
	i += sprintf(s+i, "<editX>%s</editX>\n", hex(o->editX, 0, 4));
	i += sprintf(s+i, "<editY>%s</editY>\n", hex(o->editY, 0, 4));
	i += sprintf(s+i, "<formatType>%s</formatType>\n", hexByte(o->formatType));
	i += sprintf(s+i, "<formatSpMAC>%s</formatSpMAC>\n", hex(o->formatSpMAC, 0, 9));
	i += sprintf(s+i, "<formatSpecifier>%s</formatSpecifier>\n", hex(o->formatSpecifier, 0, 50));
	i += sprintf(s+i, "<minimumKeys>%s</minimumKeys>\n", hexByte(o->minimumKeys));
	i += sprintf(s+i, "<maximumKeys>%s</maximumKeys>\n", hexByte(o->maximumKeys));
	i += sprintf(s+i, "<echoCharacter>%s</echoCharacter>\n", hexByte(o->echoCharacter));
	i += sprintf(s+i, "<cursorType>%s</cursorType>\n", hexByte(o->cursorType));
	i += sprintf(s+i, "<direction>%s</direction>\n", hexByte(o->direction));
	i += sprintf(s+i, "<beepInvalidKey>%s</beepInvalidKey>\n", hex(o->beepInvalidKey, 0, 4));
	i += sprintf(s+i, "<timeOutFirstKey>%s</timeOutFirstKey>\n", hex(o->timeOutFirstKey, 0, 4));
	i += sprintf(s+i, "<timeOutInterKey>%s</timeOutInterKey>\n", hex(o->timeOutInterKey, 0, 4));
	i += sprintf(s+i, "<keyType>%s</keyType>\n", hexByte(o->keyType));
	i += sprintf(s+i, "<keyIndex>%s</keyIndex>\n", hexByte(o->keyIndex));
	i += sprintf(s+i, "<noEnterLessMin>%s</noEnterLessMin>\n", hex(o->noEnterLessMin, 0, 4));
	i += sprintf(s+i, "<addReqSettings>%s</addReqSettings>\n<OfflinePINEntryConfiguration>\n", hex(o->addReqSettings, 0, 2));
	return s;
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
	memset(o, 0, sizeof(OfflinePINEntryConfiguration));
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
	char * s = malloc(10240);
	memset(s, 0, 10240);
	int i = 0;
	i += sprintf(s+i, "<TerminalSpecificData>\n<terminalCapabilities>%s</terminalCapabilities>\n", hex(o->terminalCapabilities, 0, 3));
	i += sprintf(s+i, "<additionalTerminalCapabilities>%s</additionalTerminalCapabilities>\n", hex(o->additionalTerminalCapabilities, 0, 5));
	i += sprintf(s+i, "<terminalCountryCode>%s</terminalCountryCode>\n", hex(o->terminalCountryCode, 0, 2));
	i += sprintf(s+i, "<terminalType>%s</terminalType>\n", hexByte(o->terminalType));
	i += sprintf(s+i, "<transactionCurrencyCode>%s</transactionCurrencyCode>\n", hex(o->transactionCurrencyCode, 0, 2));
	i += sprintf(s+i, "<transactionCurrencyExponent>%s</transactionCurrencyExponent>\n", hexByte(o->transactionCurrencyExponent));
	i += sprintf(s+i, "<transactionReferenceCurrencyCode>%s</transactionReferenceCurrencyCode>\n", hex(o->transactionReferenceCurrencyCode, 0, 2));
	i += sprintf(s+i, "<transactionReferenceCurrencyExponent>%s</transactionReferenceCurrencyExponent>\n", hexByte(o->transactionReferenceCurrencyExponent));
	i += sprintf(s+i, "<transactionReferenceCurrencyConversion>%s</transactionReferenceCurrencyConversion>\n", hex(o->transactionReferenceCurrencyConversion, 0, 4));
	i += sprintf(s+i, "<acquirerIdentifier>%s</acquirerIdentifier>\n", hex(o->acquirerIdentifier, 0, 6));
	i += sprintf(s+i, "<merchantCategoryCode>%s</merchantCategoryCode>\n", hex(o->merchantCategoryCode, 0, 2));
	i += sprintf(s+i, "<merchantIdentifier>%s</merchantIdentifier>\n", hex(o->merchantIdentifier, 0, 15));
	i += sprintf(s+i, "<terminalIdentification>%s</terminalIdentification>\n", hex(o->terminalIdentification, 0, 8));
	i += sprintf(s+i, "<terminalRiskManagementData>%s</terminalRiskManagementData>\n", hex(o->terminalRiskManagementData, 0, 8));
	i += sprintf(s+i, "<ifdSerialNumber>%s</ifdSerialNumber>\n", hex(o->ifdSerialNumber, 0, 8));
	i += sprintf(s+i, "<authorizationResponseCodeList>%s</authorizationResponseCodeList>\n", hex(o->authorizationResponseCodeList, 0, 20));
	i += sprintf(s+i, "<miscellaneousOptions>%s</miscellaneousOptions>\n", hexByte(o->miscellaneousOptions));
	i += sprintf(s+i, "<miscellaneousOptions1>%s</miscellaneousOptions1>\n", hexByte(o->miscellaneousOptions1));
	i += sprintf(s+i, "<lengthTLVData>%s</lengthTLVData>\n", hex(o->lengthTLVData, 0, 2));
	i += sprintf(s+i, "<tlvData>%s</tlvData>\n", hex(o->tlvData, 0, littleEndianInt(o->lengthTLVData)));
	i += sprintf(s+i, "<lengthOfflinePINEntryConfiguration>%s</lengthOfflinePINEntryConfiguration>\n", hex(o->lengthOfflinePINEntryConfiguration, 0, 2));
	i += sprintf(s+i, "%s", OfflinePINEntryConfigurationToXML(o->offlinePINEntryConfiguration));
	i += sprintf(s+i, "<terminalLanguages>%s</terminalLanguages>\n", hex(o->terminalLanguages, 0, 8));
	i += sprintf(s+i, "<lengthDiagnosticsTags>%s</lengthDiagnosticsTags>\n", hex(o->lengthDiagnosticsTags, 0, 2));
	i += sprintf(s+i, "<diagnosticsTags>%s</diagnosticsTags>\n", hex(o->diagnosticsTags, 0, littleEndianInt(o->lengthDiagnosticsTags)));
	i += sprintf(s+i, "<lengthAppSelectionTags>%s</lengthAppSelectionTags>\n", hex(o->lengthAppSelectionTags, 0, 2));
	i += sprintf(s+i, "<appSelectionTags>%s</appSelectionTags>\n", hex(o->appSelectionTags, 0, littleEndianInt(o->lengthAppSelectionTags)));
	i += sprintf(s+i, "<lengthRIDApps>%s</lengthRIDApps>\n", hex(o->lengthRIDApps, 0, 2));
	i += sprintf(s+i, "<ridApps>%s</ridApps>\n</TerminalSpecificData>\n", hex(o->ridApps, 0, littleEndianInt(o->lengthRIDApps)));
	return s;
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
	memset(o, 0, sizeof(TerminalSpecificData));
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

char * VegaInitDataToXML(VegaInitData * o) {
	if(NULL == o)
		return NULL;
	char * s = malloc(102400);
	memset(s, 0, 102400);
	strcat(s, "<VegaInitData>");

	strcat(s, "<terminalDataTotalLength>");
	strcat(s, hex(o->terminalDataTotalLength, 0, 2));
	strcat(s, "</terminalDataTotalLength>\n");

	strcat(s, "<terminalSpecificData>\n");
	strcat(s, TerminalSpecificDataToXML(o->terminalSpecificData));
	strcat(s, "</terminalSpecificData>\n");

	strcat(s, "<ridDataTotalLength>");
	strcat(s, hex(o->ridDataTotalLength, 0, 2));
	strcat(s, "</ridDataTotalLength>\n");

	strcat(s, "<ridSpecificData>\n");
	RID * ridp = o->ridSpecificData;
	while(NULL != ridp) {
		strcat(s, RIDToXML(ridp));
		ridp = ridp->next;
	}
	strcat(s, "</ridSpecificData>\n");

	strcat(s, "<aidDataTotalLength>");
	strcat(s, hex(o->aidDataTotalLength, 0, 2));
	strcat(s, "</aidDataTotalLength>\n");

	strcat(s, "<aidSpecificData>\n");
	AID * aidp = o->aidSpecificData;
	while(NULL != aidp) {
		strcat(s, AIDToXML(aidp));
		aidp = aidp->next;
	}
	strcat(s, "</aidSpecificData>\n");

	strcat(s, "</VegaInitData>\n");
	return s;
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
	memset(o, 0, sizeof(VegaInitData));
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
