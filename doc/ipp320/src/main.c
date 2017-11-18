/*
 * main.c
 *
 *  Created on: 2017Äê11ÔÂ11ÈÕ
 *      Author: Administrator
 */

#include "ipp320.h"

int main(void) {
	puts("!!!Hello World!!!"); /* prints !!!Hello World!!! */
	char a = '\0';
	printf("%d\n", a);

	char * s = malloc(11);
	s = "Hello world";
	char * out = hex(s, 0, 11);
	char c = lrc(s, 0, 11);
	puts(s);
	puts(out);
	printf("%d\n", c);

	int encodedLength = 0;

	char * encoded = malloc(22);

	encodedLength = cpx16Encode(s, 0, 11, encoded, 0);

	out = hex(encoded, 0, encodedLength);
	puts(out);

	char * s2 = malloc(12);
	int decodedLength = 0;

	decodedLength = cpx16Decode(encoded, 0, encodedLength, s2, 0);
	s2[decodedLength] = 0;
	puts(s2);
	printf("%d\n", decodedLength);

	char * fileName = "E:/nfeng/hub/PTxCore/doc/131540886937708660.init.dat";

	int fileSize = getFileSize(fileName);
	printf("%d\n", fileSize);

	char * fileData = loadFile(fileName);

	out = hex(fileData, 0, fileSize);

	//puts(out);

	//printf("%s\n", out);

//	print(out, 80);

	char * li = malloc(2);
	li[0] = 0x8e;
	li[1] = 0x05;

	printf("%d\n", littleEndianInt(li));

	li = littleEndianBin(1422);

	printf("%d\n", littleEndianInt(li));

	printf("%s\n", hex(li, 0, 2));

	VegaInitData * v = VegaInitDataFromBin(fileData);
	printf("%d\n", littleEndianInt(v->aidDataTotalLength));
	printf("%d\n", littleEndianInt(v->ridDataTotalLength));
	printf("%d\n", littleEndianInt(v->terminalDataTotalLength));


	fileData = TerminalSpecificDataToBin(v->terminalSpecificData);
//	out = hex(fileData, 0, littleEndianInt(v->terminalDataTotalLength));
//	puts(out);
//	print(out, 80);

//	fileData = RIDListToBin(v->ridSpecificData);
//	out = hex(fileData, 0, littleEndianInt(v->ridDataTotalLength));

//	TerminalSpecificData * tsd = TerminalSpecificDataFromBin(fileData);

//	out = TerminalSpecificDataToXML(tsd);
	//out = OfflinePINEntryConfigurationToXML(v->terminalSpecificData->offlinePINEntryConfiguration);

//	AID * aidp = v->aidSpecificData;
//	out = AIDListToBin(aidp);
//	aidp = AIDListFromBin(out, littleEndianInt(v->aidDataTotalLength));
//	while(NULL != aidp) {
//		out = AIDToXML(aidp);
//		puts(out);
//		aidp = aidp->next;
//	}

	//out = KeyDataToXML(v->ridSpecificData->keyDatas, littleEndianInt(v->ridSpecificData->keyDataTotalLength)/276);


//	LengthThenTags * ltg = v->ridSpecificData->endOfTransactionTags;
	//out = LengthThenTagsToXML(ltg, 7);

	//Tag * tags = v->ridSpecificData->goOnlineTags;




//	RID * ridp = v->ridSpecificData;
//	out = RIDListToBin(ridp);
//	ridp = RIDListFromBin(out, littleEndianInt(v->ridDataTotalLength));
//	while(NULL != ridp) {
//		out = RIDToXML(ridp);
//		puts(out);
//		ridp = ridp->next;
//	}

	out = hex(VegaInitDataToBin(v), 0, fileSize);
//	v = VegaInitDataFromBin(out);
//	out = VegaInitDataToXML(v);
//	puts(out);
//	fflush(stdout);

	print(out, 80);

//	fileData = VegaInitDataToBin(v);
//	out = hex(fileData, 0, VegaInitDataLength(v));
//	puts(out);
//	print(out, 80);

	return EXIT_SUCCESS;
}
