/*
 * main.c
 *
 *  Created on: 2017��11��11��
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

	char * fileName = "D:/hub/fdj32/PTxCore/doc/131540886937708660.init.dat";

	int fileSize = getFileSize(fileName);
	printf("%d\n", fileSize);

	char * fileData = loadFile(fileName);

	out = hex(fileData, 0, fileSize);

	//puts(out);

	//printf("%s\n", out);

	// print(out, 80);

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

	fileData = VegaInitDataToBin(v);
	out = hex(fileData, 0, VegaInitDataLength(v));
	puts(out);

	return EXIT_SUCCESS;
}
