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

char lrc(char * s , int offset, int length) {
	char c = s[offset];
	int startIndex = offset + 1;
	if(0x02 == c) {
		c = s[startIndex];
		startIndex++;
	}
	for(int i = startIndex; i < length; i++) {
		c ^= s[i];
	}
	return c;
}

int cpx16Encode(char * in, int inOffset, int inLength, char * out, int outOffset) {
	int outLength = 0;
	char b;
	for(int i=inOffset; i<inLength; i++) {
		switch(i%3) {
		case 0:
			b = ((in[i]>>2)&0x3f)|0x40;
			out[outOffset+outLength] = b;
			outLength++;
			if(i==inLength - 1) {
				b = ((in[i]&0x03)<<4)|0x40;
				out[outOffset+outLength] = b;
				outLength++;
			}
			break;
		case 1:
			b = (in[i-1]&0x03)<<4;
			b |= (in[i]>>4)&0x0f;
			b |= 0x40;
			out[outOffset+outLength] = b;
			outLength++;
			if(i==inLength - 1) {
				b = ((in[i]&0x0f)<<2)|0x40;
				out[outOffset+outLength] = b;
				outLength++;
			}
			break;
		case 2:
			b = (in[i-1]&0x0f)<<2;
			b |= (in[i]>>6)&0x03;
			b |= 0x40;
			out[outOffset+outLength] = b;
			outLength++;
			b = (in[i]&0x3f)|0x40;
			out[outOffset+outLength] = b;
			outLength++;
			break;
		}
	}
	return outLength;
}

int cpx16Decode(char * in, int inOffset, int inLength, char * out, int outOffset) {
	int outLength = 0;
	return outLength;
}

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

	return EXIT_SUCCESS;
}
