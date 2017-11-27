/*
 * main.c
 *
 *  Created on: 2017Äê11ÔÂ11ÈÕ
 *      Author: Administrator
 */

#include "ipp320.h"

int main(void) {
	puts("!!!Hello World!!!"); /* prints !!!Hello World!!! */

	unsigned char * recvBuf = malloc(16);
	memset(recvBuf, 0, 16);
	int n = cpx58display01A('0', '0', '4', '1', "Hello", "     China", "Shaanxi", "       Xi'an", recvBuf);
	putchar(n);
	return EXIT_SUCCESS;
}
