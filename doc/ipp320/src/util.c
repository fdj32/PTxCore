/*
 * util.c
 * encoders and decoders, base algorithm utilities
 *  Created on: 2017��11��11��
 *      Author: Administrator
 */
#include "ipp320.h"

char * hex(char * s, int offset, int length) {
	int hexLen = length * 2 + 1;
	char * hex = malloc(hexLen);
	hex[hexLen] = '\0';
	for (int i = 0; i < length; i++) {
		hex[i * 2] = hexChar(((s[i] & 0xf0) >> 4) & 0x0f);
		hex[i * 2 + 1] = hexChar(s[i] & 0x0f);
	}
	return hex;
}

char hexChar(char c) {
	return c > 9 ? c - 10 + 'a' : c + '0';
}

char unHexChar(char c) {
	return c >= '0' && c <= '9' ? (c - '0') : (c - 'a');
}

char * unHex(char * s, int offset, int length) {
	if (NULL == s || 0 == length)
		return NULL;
	int size = length >> 1;
	char * t = malloc(size);
	memset(t, 0, size);
	for (int i = 0; i < size; i++) {
		t[i] = (char) ((unHexChar(s[i]) << 4) + unHexChar(s[i + 1]));
	}
	return t;
}

char lrc(char * s, int offset, int length) {
	char c = s[offset];
	int startIndex = offset + 1;
	if (0x02 == c) {
		c = s[startIndex];
		startIndex++;
	}
	for (int i = startIndex; i < length; i++) {
		c ^= s[i];
	}
	return c;
}

int cpx16Encode(char * in, int inOffset, int inLength, char * out,
		int outOffset) {
	int outLength = 0;
	char b;
	for (int i = inOffset; i < inLength; i++) {
		switch (i % 3) {
		case 0:
			b = ((in[i] >> 2) & 0x3f) | 0x40;
			out[outOffset + outLength] = b;
			outLength++;
			if (i == inLength - 1) {
				b = ((in[i] & 0x03) << 4) | 0x40;
				out[outOffset + outLength] = b;
				outLength++;
			}
			break;
		case 1:
			b = (in[i - 1] & 0x03) << 4;
			b |= (in[i] >> 4) & 0x0f;
			b |= 0x40;
			out[outOffset + outLength] = b;
			outLength++;
			if (i == inLength - 1) {
				b = ((in[i] & 0x0f) << 2) | 0x40;
				out[outOffset + outLength] = b;
				outLength++;
			}
			break;
		case 2:
			b = (in[i - 1] & 0x0f) << 2;
			b |= (in[i] >> 6) & 0x03;
			b |= 0x40;
			out[outOffset + outLength] = b;
			outLength++;
			b = (in[i] & 0x3f) | 0x40;
			out[outOffset + outLength] = b;
			outLength++;
			break;
		}
	}
	return outLength;
}

int cpx16Decode(char * in, int inOffset, int inLength, char * out,
		int outOffset) {
	int outLength = 0;
	char a, b;
	for (int i = inOffset; i < inLength; i++) {
		switch (i % 4) {
		case 0:
			// do nothing
			break;
		case 1:
			a = (in[i - 1] << 2) & 0xfc;
			b = (in[i] >> 4) & 0x03;
			out[outOffset + outLength] = a | b;
			outLength++;
			break;
		case 2:
			a = (in[i - 1] << 4) & 0xf0;
			b = (in[i] >> 2) & 0x0f;
			out[outOffset + outLength] = a | b;
			outLength++;
			break;
		case 3:
			a = (in[i - 1] << 6) & 0xc0;
			b = in[i] & 0x3f;
			out[outOffset + outLength] = a | b;
			outLength++;
			break;
		}
	}
	return outLength;
}

int getFileSize(char * fileName) {
	int size = 0;
	FILE *fp = fopen(fileName, "rb");
	if (fp) {
		fseek(fp, 0, SEEK_END);
		size = ftell(fp);
		fclose(fp);
	}
	return size;
}

char * loadFile(char * fileName) {
	char * s;
	int size = 0;
	FILE *fp = fopen(fileName, "rb");
	if (fp) {
		fseek(fp, 0, SEEK_END);
		size = ftell(fp);
		s = malloc(size);
		fseek(fp, 0, SEEK_SET);
		fread(s, sizeof(char), size, fp);
	} else {
		return 0;
	}
	fclose(fp);
	return s;
}

void print(char * s, int lineLength) {
	int i = 0;
	while ('\0' != s[i]) {
		printf("%c", s[i]);
		i++;
		if (i % lineLength == 0)
			printf("\n");
	}
}

int littleEndianInt(char * s) {
	return s[0] + (s[1] << 8);
}

char * littleEndianBin(int i) {
	char * s = malloc(2);
	s[0] = i;
	s[1] = i >> 8;
	return s;
}

int bigEndianInt(char * s) {
	return s[1] + (s[0] << 8);
}

char * bigEndianBin(int i) {
	char * s = malloc(2);
	s[0] = i >> 8;
	s[1] = i;
	return s;
}
