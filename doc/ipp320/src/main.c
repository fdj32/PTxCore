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

	char * s = calloc(11, sizeof(char));
	s = "Hello world";
	char * out = hex(s, 0, 11);
	char c = lrc(s, 0, 11);
	puts(s);
	puts(out);
	printf("%d\n", c);

	int encodedLength = 0;

	char * encoded = calloc(22, sizeof(char));

	encodedLength = cpx16Encode(s, 0, 11, encoded, 0);

	out = hex(encoded, 0, encodedLength);
	puts(out);

	char * s2 = calloc(12, sizeof(char));
	int decodedLength = 0;

	decodedLength = cpx16Decode(encoded, 0, encodedLength, s2, 0);
	s2[decodedLength] = 0;
	puts(s2);
	printf("%d\n", decodedLength);
	return EXIT_SUCCESS;
}
