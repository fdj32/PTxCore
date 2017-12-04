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

		output(buf, n);

		int stx = 0, etx = 0, index = 0, len = 0;
		while (index < n) {
			if (buf[index] == STX) {
				stx = index;
			}
			if (buf[index] == ETX) {
				etx = index;
				len = etx - stx - 1;
				char * msg = malloc(len);
				memset(msg, 0, len);
				if (buf[stx + 1] == 'F') {
					memcpy(msg, buf + stx + 1, 3);
					len = 3
							+ cpx16Decode(buf + stx + 4, 0, etx - stx - 4,
									msg + 3, 0);
				} else {
					memcpy(msg, buf + stx + 1, len);
				}

				Msg * m = malloc(sizeof(Msg));
				m->msg = msg;
				m->len = len;

				output(msg, len);

				if (NULL == h->next) {
					m->next = NULL;
				} else {
					m->next = h->next;
				}
				h->next = m;
			}
			index++;
		}
		sleepM(POLL_TIME);
	}
}

