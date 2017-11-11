/*
 * util.h
 * utilities definitions
 *  Created on: 2017��11��11��
 *      Author: Administrator
 */

#ifndef UTIL_H_
#define UTIL_H_

char * hex(char * s, int offset, int length);

char hexChar(char c);

char lrc(char * s , int offset, int length);

int cpx16Encode(char * in, int inOffset, int inLength, char * out, int outOffset);

int cpx16Decode(char * in, int inOffset, int inLength, char * out, int outOffset);

int getFileSize(char * fileName);

char * loadFile(char * fileName);

void print(char * s, int lineLength);

int littleEndianInt(char * s);

char * littleEndianBin(int i);

int bigEndianInt(char * s);

char * bigEndianBin(int i);

#endif /* UTIL_H_ */
