/*
 * util.c
 * encoders and decoders, base algorithm utilities
 *  Created on: 2017��11��11��
 *      Author: Administrator
 */
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
	char a, b;
	for(int i=inOffset; i<inLength; i++) {
		switch(i%4) {
		case 0:
			// do nothing
			break;
		case 1:
			a = (in[i-1]<<2)&0xfc;
			b = (in[i]>>4)&0x03;
			out[outOffset+outLength] = a|b;
			outLength++;
			break;
		case 2:
			a = (in[i-1]<<4)&0xf0;
			b = (in[i]>>2)&0x0f;
			out[outOffset+outLength] = a|b;
			outLength++;
			break;
		case 3:
			a = (in[i-1]<<6)&0xc0;
			b = in[i]&0x3f;
			out[outOffset+outLength] = a|b;
			outLength++;
			break;
		}
	}
	return outLength;
}

int getFileSize(char * fileName) {
	int size = 0;
	FILE *fp = fopen(fileName, "rb");
	if(fp) {
		fseek(fp, 0, SEEK_END);
		size = ftell(fp);
		fclose(fp);
	}
	return size;
}
