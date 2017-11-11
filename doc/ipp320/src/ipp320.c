/*
 ============================================================================
 Name        : ipp320.c
 Author      : Nick Feng
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include "rs232.h"
#include "ipp320.h"

char * hex(char * s, int offset, int length) {
	int hexLen = length * 2 + 1;
	char * hex = calloc(hexLen, sizeof(char));
	hex[hexLen] = '\0';
	for(int i = 0; i < length; i++) {
		hex[i*2] = hexChar(((s[i]&0xf0)>>4)&0x0f);
		hex[i*2+1] = hexChar(s[i]&0x0f);
	}
	return hex;
}

char hexChar(char c) {
	return c>9 ? c-10+'a' : c+'0';
}

int main(void) {
	puts("!!!Hello World!!!"); /* prints !!!Hello World!!! */
	char a = '\0';
	printf("%d\n", a);

	char * s = calloc(11, sizeof(char));
	s = "Hello world";
	char * out = hex(s, 0, 11);
	puts(s);
	puts(out);
	return EXIT_SUCCESS;
}
