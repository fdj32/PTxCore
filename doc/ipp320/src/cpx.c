/*
 * cpx.c
 *
 *  Created on: 2017��11��23��
 *      Author: nfeng
 */
#include "ipp320.h"

int ack() {
	return RS232_SendByte(COM_PORT_NUMBER, ACK);
}

int send(unsigned char * buf, int size, unsigned char * recvBuf) {
	if(RS232_OpenComport(COM_PORT_NUMBER, BAUD_RATE, MODE_DATABITS8_PARITY_NONE_STOPBITS1)) {
		printf("Can not open comport\n");
		return(0);
	}
	int n = RS232_SendBuf(COM_PORT_NUMBER, buf, size);
	long start = clock();
	while((clock()-start)<READ_TIMEOUT*CLOCKS_PER_SEC) {
	    n = RS232_PollComport(COM_PORT_NUMBER, recvBuf, 1023);
	    if(n > 0) {
	    	printf("received %i bytes: %s\n", n, (char *)recvBuf);
	    	printf("received %i bytes: %s\n", n, hex((char *)recvBuf, 0, n));
	    	if(ACK == recvBuf[0]) {
	    		if(n > 1) {
	    			ack();
	    		}
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

int cpx58display01A(char mode, char toggle, char lines, char lineStartIndex, char * prompt1, char * prompt2, char * prompt3, char * prompt4, unsigned char * recvBuf) {
	unsigned char * s = malloc(74);
	memset(s, 0, 74);
	s[0] = STX;
	s[1] = '5';
	s[2] = '8';
	s[3] = '.';
	s[4] = mode;
	s[5] = toggle;
	s[6] = lines;
	s[7] = lineStartIndex;
	memset(s+8, SPACE, 64);
	int n = strlen(prompt1);
	memcpy(s+8, prompt1, ((n > 16) ? 16 : n));
	if(NULL != prompt2) {
		n = strlen(prompt2);
		memcpy(s+24, prompt2, ((n > 16) ? 16 : n));
	}
	if(NULL != prompt3) {
		n = strlen(prompt3);
		memcpy(s+40, prompt3, ((n > 16) ? 16 : n));
	}
	if(NULL != prompt4) {
		n = strlen(prompt4);
		memcpy(s+56, prompt4, ((n > 16) ? 16 : n));
	}
	s[72] = ETX;
	s[73] = lrc(s, 0, 73);
	return send(s, 74, recvBuf);
}

int cpx58display27(char mode, char lineStartIndex, char startPosition, char * prompt, char * promptIndex, char * maxInputLength, unsigned char * recvBuf) {
	unsigned char * s = malloc(74);
	memset(s, 0, 45);
	s[0] = STX;
	s[1] = '5';
	s[2] = '8';
	s[3] = '.';
	s[4] = mode;
	s[5] = lineStartIndex;
	s[6] = startPosition;
	memcpy(s+7, prompt, 32);
	memcpy(s+39, promptIndex, 2);
	memcpy(s+41, maxInputLength, 2);
	s[43] = ETX;
	s[44] = lrc(s, 0, 44);
	return send(s, 45, recvBuf);
}

int cpx31DukptpinEncryption(char timeoutValue, char displayLineNumber, char * primaryAccountNumber, char * pinDisplayPrompt, unsigned char * recvBuf) {
	unsigned char * s = malloc(74);
	memset(s, 0, 28);
	s[0] = STX;
	s[1] = '3';
	s[2] = '1';
	s[3] = '.';
	s[4] = timeoutValue;
	s[5] = displayLineNumber;
	memcpy(s+6, primaryAccountNumber, 16);
	memcpy(s+22, pinDisplayPrompt, 4);
	s[26] = ETX;
	s[27] = lrc(s, 0, 27);
	return send(s, 28, recvBuf);
}
