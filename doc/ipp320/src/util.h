/*
 * util.h
 *
 *  Created on: 2017Äê11ÔÂ11ÈÕ
 *      Author: Administrator
 */

#ifndef UTIL_H_
#define UTIL_H_

char * hex(char * s, int offset, int length);

char hexChar(char c);

char lrc(char * s , int offset, int length);

int cpx16Encode(char * in, int inOffset, int inLength, char * out, int outOffset);

int cpx16Decode(char * in, int inOffset, int inLength, char * out, int outOffset);

#endif /* UTIL_H_ */
