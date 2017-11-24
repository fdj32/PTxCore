/*
 * cpx.c
 *
 *  Created on: 2017Äê11ÔÂ23ÈÕ
 *      Author: nfeng
 */
#include "ipp320.h"

int ack() {
	return RS232_SendByte(COM_PORT_NUMBER, ACK);
}

int send(char * buf, int size) {
	if(RS232_OpenComport(COM_PORT_NUMBER, BAUD_RATE, MODE_DATABITS8_PARITY_NONE_STOPBITS1)) {
		printf("Can not open comport\n");
		return(0);
	}
	int n = RS232_SendBuf(COM_PORT_NUMBER, buf, size);
	unsigned char recvBuf[4096];
	long start = clock();
	while((clock()-start)<READ_TIMEOUT*CLOCKS_PER_SEC) {
	    n = RS232_PollComport(COM_PORT_NUMBER, recvBuf, 4095);
	    if(n > 0) {
	    	printf("received %i bytes: %s\n", n, (char *)recvBuf);
	    	if(ACK == recvBuf[0]) {
	    		ack();
	    		break;
	    	}
	    }
		#ifdef _WIN32
			Sleep(POLL_TIME);
		#else
			usleep(POLL_TIME * 1000);  /* sleep for 100 milliSeconds */
		#endif
	}
	RS232_CloseComport(COM_PORT_NUMBER);
	return n;
}

