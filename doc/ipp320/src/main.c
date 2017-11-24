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
	send(s, 74);


	return EXIT_SUCCESS;
}
