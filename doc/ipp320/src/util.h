/*
 * util.h
 * utilities definitions
 *  Created on: 2017Äê11ÔÂ11ÈÕ
 *      Author: Administrator
 */

#ifndef UTIL_H_
#define UTIL_H_

#ifdef __cplusplus
extern "C" {
#endif

void sleepM(int t);

char * hex(char * s, int offset, int length);

char * hexByte(char b);

char hexChar(char c);

char unHexChar(char c);

char * unHex(char * s, int offset, int length);

char lrc(char * s , int offset, int length);

int cpx16Encode(char * in, int inOffset, int inLength, char * out, int outOffset);

int cpx16Decode(char * in, int inOffset, int inLength, char * out, int outOffset);

int getFileSize(char * fileName);

char * loadFile(char * fileName);

void print(char * s, int lineLength);

int unsignedInt(char c);

int littleEndianInt(char * s);

char * littleEndianBin(int i);

int bigEndianInt(char * s);

char * bigEndianBin(int i);

char * format(const char * f, ...);

char * stringRightPad(char * s, char c, int length);

char * stringLeftPad(char * s, char c, int length);

void output(char * s, int length);

#ifdef __cplusplus
}
#endif

#endif /* UTIL_H_ */
