/*
 * aio.c
 *
 *  Created on: Dec 1, 2017
 *      Author: nickfeng
 */

#include "ipp320.h"

int openComPort() {
	if (RS232_OpenComport(COM_PORT_NUMBER, BAUD_RATE,
	MODE_DATABITS8_PARITY_NONE_STOPBITS1)) {
		printf("Can not open serial port comports[%d]\n", COM_PORT_NUMBER);
		return EXIT_FAILURE;
	}
	return EXIT_SUCCESS;
}

void closeComPort() {
	RS232_CloseComport(COM_PORT_NUMBER);
}

void * recvMsg(Msg * h) {
	int n = 0;
	char * buf = malloc(1024);
	memset(buf, 0, 1024);
	while (1) {
		n = RS232_PollComport(COM_PORT_NUMBER, buf, 1023);
		char * msg = malloc(n);
		memset(msg, 0, n);
		memcpy(msg, buf, n);
		Msg * m = malloc(sizeof(Msg));
		m->msg = msg;
		m->len = n;

		if(NULL == h->next) {
			m->next = NULL;
		} else {
			m->next = h->next;
		}
		h->next = m;
#ifdef _WIN32
		Sleep(POLL_TIME);
#else
		usleep(POLL_TIME * 1000); /* sleep for 100 milliSeconds */
#endif
	}
}

