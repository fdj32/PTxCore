/*
 * main.c
 *
 *  Created on: 2017Äê11ÔÂ11ÈÕ
 *      Author: Administrator
 */

#include "ipp320.h"

int main(void) {
	puts("!!!Hello World!!!"); /* prints !!!Hello World!!! */

	char s[74];
	char * msg =
			"58.0041Goodjob,            Nick!                                       ";
	s[0] = 0x02;
	s[72] = 0x03;
	s[73] = 'c';
	for (int i = 1; i < 72; i++) {
		s[i] = msg[i - 1];
	}

	unsigned char * recvBuf = malloc(1024);
	memset(recvBuf, 0, 1024);
	int n = send(s, 74, recvBuf);

	printf("received %i bytes: %s\n", n, (char *)recvBuf);
	printf("received %i bytes: %s\n", n, hex((char *)recvBuf, 0, n));

	return EXIT_SUCCESS;
}
