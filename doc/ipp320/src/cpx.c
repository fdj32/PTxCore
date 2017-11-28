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

int send(char * buf, int size, char * recvBuf) {
	if (RS232_OpenComport(COM_PORT_NUMBER, BAUD_RATE,
	MODE_DATABITS8_PARITY_NONE_STOPBITS1)) {
		printf("Can not open comport\n");
		return (0);
	}
	int n = RS232_SendBuf(COM_PORT_NUMBER, buf, size);
	long start = clock();
	while ((clock() - start) < READ_TIMEOUT * CLOCKS_PER_SEC) {
		n = RS232_PollComport(COM_PORT_NUMBER, recvBuf, 1023);
		if (n > 0) {
			printf("received %i bytes: %s\n", n, (char *) recvBuf);
			printf("received %i bytes: %s\n", n, hex((char *) recvBuf, 0, n));
			if (ACK == recvBuf[0]) {
				if (n > 1) {
					ack();
				}
				break;
			}
		}
#ifdef _WIN32
		Sleep(POLL_TIME);
#else
		usleep(POLL_TIME * 1000); /* sleep for 100 milliSeconds */
#endif
	}
	RS232_CloseComport(COM_PORT_NUMBER);
	return n;
}

int cpx58display01A(char mode, char toggle, char lines, char lineStartIndex,
		char * prompt1, char * prompt2, char * prompt3, char * prompt4,
		char * recvBuf) {
	char * s = malloc(74);
	memset(s, 0, 74);
	s[0] = STX;
	s[1] = '5';
	s[2] = '8';
	s[3] = '.';
	s[4] = mode;
	s[5] = toggle;
	s[6] = lines;
	s[7] = lineStartIndex;
	memset(s + 8, SPACE, 64);
	int n = strlen(prompt1);
	memcpy(s + 8, prompt1, ((n > 16) ? 16 : n));
	if (NULL != prompt2) {
		n = strlen(prompt2);
		memcpy(s + 24, prompt2, ((n > 16) ? 16 : n));
	}
	if (NULL != prompt3) {
		n = strlen(prompt3);
		memcpy(s + 40, prompt3, ((n > 16) ? 16 : n));
	}
	if (NULL != prompt4) {
		n = strlen(prompt4);
		memcpy(s + 56, prompt4, ((n > 16) ? 16 : n));
	}
	s[72] = ETX;
	s[73] = lrc(s, 0, 73);
	return send(s, 74, recvBuf);
}

int cpx58display27(char mode, char lineStartIndex, char startPosition,
		char * prompt, char * promptIndex, char * maxInputLength,
		char * recvBuf) {
	char * s = malloc(45);
	memset(s, 0, 45);
	s[0] = STX;
	s[1] = '5';
	s[2] = '8';
	s[3] = '.';
	s[4] = mode;
	s[5] = lineStartIndex;
	s[6] = startPosition;
	memcpy(s + 7, prompt, 32);
	memcpy(s + 39, promptIndex, 2);
	memcpy(s + 41, maxInputLength, 2);
	s[43] = ETX;
	s[44] = lrc(s, 0, 44);
	return send(s, 45, recvBuf);
}

int cpx31DukptpinEncryption(char timeoutValue, char displayLineNumber,
		char * primaryAccountNumber, char * pinDisplayPrompt, char * recvBuf) {
	char * s = malloc(28);
	memset(s, 0, 28);
	s[0] = STX;
	s[1] = '3';
	s[2] = '1';
	s[3] = '.';
	s[4] = timeoutValue;
	s[5] = displayLineNumber;
	memcpy(s + 6, primaryAccountNumber, 16);
	memcpy(s + 22, pinDisplayPrompt, 4);
	s[26] = ETX;
	s[27] = lrc(s, 0, 27);
	return send(s, 28, recvBuf);
}

int cpx40LoadSessionKey(char sessionKeyType, char masterkeyType,
		char * masterKeyOrSessionKey, char * checkValue, char * keySerialNumber,
		char * recvBuf) {
	char * s = malloc(48);
	memset(s, 0, 48);
	s[0] = STX;
	s[1] = '4';
	s[2] = '0';
	s[3] = '.';
	s[4] = sessionKeyType;
	s[5] = masterkeyType;
	memcpy(s + 6, masterKeyOrSessionKey, 16);
	memcpy(s + 22, checkValue, 8);
	memcpy(s + 30, keySerialNumber, 16);
	s[46] = ETX;
	s[47] = lrc(s, 0, 47);
	return send(s, 48, recvBuf);
}

int cpx50Cancel(char * recvBuf) {
	char * s = malloc(6);
	memset(s, 0, 6);
	s[0] = STX;
	s[1] = '5';
	s[2] = '0';
	s[3] = '.';
	s[4] = ETX;
	s[5] = lrc(s, 0, 5);
	return send(s, 6, recvBuf);
}

int cpx51InquireSerial(char * recvBuf) {
	char * s = malloc(7);
	memset(s, 0, 7);
	s[0] = STX;
	s[1] = '5';
	s[2] = '1';
	s[3] = '.';
	s[4] = 'S';
	s[5] = ETX;
	s[6] = lrc(s, 0, 6);
	return send(s, 7, recvBuf);
}

int cpx53DiagnosticKeyCheckword(char keyIndicator, char * recvBuf) {
	char * s = malloc(7);
	memset(s, 0, 7);
	s[0] = STX;
	s[1] = '5';
	s[2] = '3';
	s[3] = '.';
	s[4] = keyIndicator;
	s[5] = ETX;
	s[6] = lrc(s, 0, 6);
	return send(s, 7, recvBuf);
}

int cpx59ClearDisplay(char lineNumber, char * recvBuf) {
	char * s = malloc(7);
	memset(s, 0, 7);
	s[0] = STX;
	s[1] = '5';
	s[2] = '9';
	s[3] = '.';
	s[4] = lineNumber;
	s[5] = ETX;
	s[6] = lrc(s, 0, 6);
	return send(s, 7, recvBuf);
}

int cpx5BBeep(char beepLength, char beepFrequency, char * recvBuf) {
	char * s = malloc(8);
	memset(s, 0, 8);
	s[0] = STX;
	s[1] = '5';
	s[2] = 'B';
	s[3] = '.';
	s[4] = beepLength;
	s[5] = beepFrequency;
	s[6] = ETX;
	s[7] = lrc(s, 0, 7);
	return send(s, 8, recvBuf);
}

int cpx5DDeviceInformation(char option, char * recvBuf) {
	char * s = malloc(7);
	memset(s, 0, 7);
	s[0] = STX;
	s[1] = '5';
	s[2] = 'D';
	s[3] = '.';
	s[4] = option;
	s[5] = ETX;
	s[6] = lrc(s, 0, 6);
	return send(s, 7, recvBuf);
}

int cpx64MacCalculation(char masterKeyIndicator, char sessionKeyLengthFlag,
		char * encryptedSessionKey, char * checkValue, char * macData,
		char * recvBuf) {
	int sessionKeyLength = sessionKeyLengthFlag == '1' ? 16 : 32;
	int checkValueLength = sessionKeyLengthFlag == '1' ? 0 : 8;
	int macDataLength = strlen(macData);
	char * s = malloc(8 + sessionKeyLength + checkValueLength + macDataLength);
	memset(s, 0, 8 + sessionKeyLength + checkValueLength + macDataLength);
	s[0] = STX;
	s[1] = '6';
	s[2] = '4';
	s[3] = '.';
	s[4] = masterKeyIndicator;
	s[5] = sessionKeyLengthFlag;
	memcpy(s + 6, encryptedSessionKey, sessionKeyLength);
	if ('2' == sessionKeyLengthFlag) {
		memcpy(s + 6 + sessionKeyLength, checkValue, checkValueLength);
	}
	memcpy(s + 6 + sessionKeyLength + checkValueLength, macData, macDataLength);
	s[6 + sessionKeyLength + checkValueLength + macDataLength] = ETX;
	s[7 + sessionKeyLength + checkValueLength + macDataLength] = lrc(s, 0,
			7 + sessionKeyLength + checkValueLength + macDataLength);
	return send(s, 8 + sessionKeyLength + checkValueLength + macDataLength,
			recvBuf);
}

int cpx66MacVerification(char masterKeyIndicator, char sessionKeyLengthFlag,
		char * encryptedSessionKey, char * checkValue, char * macField,
		char * macData, char * recvBuf) {
	int sessionKeyLength = sessionKeyLengthFlag == '1' ? 16 : 32;
	int checkValueLength = sessionKeyLengthFlag == '1' ? 0 : 8;
	int macDataLength = strlen(macData);
	char * s = malloc(16 + sessionKeyLength + checkValueLength + macDataLength);
	memset(s, 0, 16 + sessionKeyLength + checkValueLength + macDataLength);
	s[0] = STX;
	s[1] = '6';
	s[2] = '6';
	s[3] = '.';
	s[4] = masterKeyIndicator;
	s[5] = sessionKeyLengthFlag;
	memcpy(s + 6, encryptedSessionKey, sessionKeyLength);
	if ('2' == sessionKeyLengthFlag) {
		memcpy(s + 6 + sessionKeyLength, checkValue, checkValueLength);
	}
	memcpy(s + 6 + sessionKeyLength + checkValueLength, macField, 8);
	memcpy(s + 14 + sessionKeyLength + checkValueLength, macData,
			macDataLength);
	s[14 + sessionKeyLength + checkValueLength + macDataLength] = ETX;
	s[15 + sessionKeyLength + checkValueLength + macDataLength] = lrc(s, 0,
			15 + sessionKeyLength + checkValueLength + macDataLength);
	return send(s, 16 + sessionKeyLength + checkValueLength + macDataLength,
			recvBuf);
}

int cpx67ActivateMsr(char trackNumber, char * timeout, char functionKeysActive,
		char lines, char lineNumber, char * prompt1, char * prompt2,
		char * prompt3, char * prompt4, char * recvBuf) {
	char * s = malloc(100);
	memset(s, 0, 100);
	s[0] = STX;
	s[1] = '6';
	s[2] = '7';
	s[3] = '.';
	s[4] = trackNumber;
	memcpy(s + 5, timeout, 2);
	s[7] = functionKeysActive;
	s[8] = lines;
	s[9] = lineNumber;
	strcat(s, prompt1);
	int len = strlen(s);
	s[len] = FS;
	if (NULL != prompt2) {
		strcat(s, prompt2);
		len = strlen(s);
		s[len] = FS;
	}
	if (NULL != prompt3) {
		strcat(s, prompt3);
		len = strlen(s);
		s[len] = FS;
	}
	if (NULL != prompt4) {
		strcat(s, prompt4);
		len = strlen(s);
		s[len] = FS;
	}
	len = strlen(s);
	s[len] = ETX;
	len = strlen(s);
	s[len] = lrc(s, 0, len);
	len = strlen(s);
	return send(s, len, recvBuf);
}

int cpx6AInteracDebitSequenceInit(char swipeCardPromptLineNumber,
		char * swipeCardPromptEnglish, char * swipeCardPromptFrench,
		char amountOKpromptLineNumber, char * amountOKPromptEnglish,
		char * amountOKPromptFrench, char enterTipPromptLineNumber,
		char * enterTipPromptIndexOfMACEnglish, char * enterTipPromptEnglish,
		char * enterTipPromptIndexOfMACFrench, char * enterTipPromptFrench,
		char cashBackPromptLineNumber, char * cashBackPromptIndexOfMACEnglish,
		char * cashBackPromptEnglish, char * cashBackPromptIndexOfMACFrench,
		char cashBackPromptFrench, char totalOKpromptLineNumber,
		char * totalOKPromptEnglish, char * totalOKPromptFrench,
		char selectAccountpromptLineNumber, char * selectAccountPromptEnglish,
		char * selectAccountPromptFrench, char pinEntryPromptLineNumber,
		char * pinEntryPromptIndexOfMACEnglish, char * pinEntryPromptEnglish,
		char * pinEntryPromptIndexOfMACFrench, char * pinEntryPromptFrench,
		char pinErrorMessageLineNumber, char * pinErrorMessagePromptEnglish,
		char * pinErrorMessagePromptFrench, char * serviceCodeList,
		char * recvBuf) {
	char * s = malloc(1024);
	memset(s, 0, 1024);
	s[0] = STX;
	s[1] = '6';
	s[2] = 'A';
	s[3] = '.';
	s[4] = swipeCardPromptLineNumber;
	strcat(s, swipeCardPromptEnglish);
	int len = strlen(s);
	s[len] = FS;
	strcat(s, swipeCardPromptFrench);
	len = strlen(s);
	s[len] = FS;

	len = strlen(s);
	s[len] = amountOKpromptLineNumber;
	strcat(s, amountOKPromptEnglish);
	len = strlen(s);
	s[len] = FS;
	strcat(s, amountOKPromptFrench);
	len = strlen(s);
	s[len] = FS;

	len = strlen(s);
	s[len] = enterTipPromptLineNumber;
	strcat(s, enterTipPromptIndexOfMACEnglish);
	strcat(s, enterTipPromptEnglish);
	len = strlen(s);
	s[len] = FS;
	strcat(s, enterTipPromptIndexOfMACFrench);
	strcat(s, enterTipPromptFrench);
	len = strlen(s);
	s[len] = FS;

	len = strlen(s);
	s[len] = cashBackPromptLineNumber;
	strcat(s, cashBackPromptIndexOfMACEnglish);
	strcat(s, cashBackPromptEnglish);
	len = strlen(s);
	s[len] = FS;
	strcat(s, cashBackPromptIndexOfMACFrench);
	strcat(s, cashBackPromptFrench);
	len = strlen(s);
	s[len] = FS;

	len = strlen(s);
	s[len] = totalOKpromptLineNumber;
	strcat(s, totalOKPromptEnglish);
	len = strlen(s);
	s[len] = FS;
	strcat(s, totalOKPromptFrench);
	len = strlen(s);
	s[len] = FS;

	len = strlen(s);
	s[len] = selectAccountpromptLineNumber;
	strcat(s, selectAccountPromptEnglish);
	len = strlen(s);
	s[len] = FS;
	strcat(s, selectAccountPromptFrench);
	len = strlen(s);
	s[len] = FS;

	len = strlen(s);
	s[len] = pinEntryPromptLineNumber;
	strcat(s, pinEntryPromptIndexOfMACEnglish);
	strcat(s, pinEntryPromptEnglish);
	len = strlen(s);
	s[len] = FS;
	strcat(s, pinEntryPromptIndexOfMACFrench);
	strcat(s, pinEntryPromptFrench);
	len = strlen(s);
	s[len] = FS;

	len = strlen(s);
	s[len] = pinErrorMessageLineNumber;
	strcat(s, pinErrorMessagePromptEnglish);
	len = strlen(s);
	s[len] = FS;
	strcat(s, pinErrorMessagePromptFrench);
	len = strlen(s);
	s[len] = FS;

	strcat(s, serviceCodeList);
	len = strlen(s);
	s[len] = ETX;
	len = strlen(s);
	s[len] = lrc(s, 0, len);
	len = strlen(s);
	return send(s, len, recvBuf);
}

int cpx6BInteracDebitSequence(char language, char * swipeCardTimeout,
		char trackNumber, char serviceCodeFlag, char * dataEntryTimeout,
		char pinEntryTimeout, char * pinDisplayprompt, char * amount,
		char tipEntryEnabled, char cashbackEnabled, char masterKeyIndicator,
		char sessionKeyLength, char * encryptedSessionKey, char * checkValue,
		char * primaryAccountNumber, char * recvBuf) {
	char * s = malloc(128);
	memset(s, 0, 128);
	s[0] = STX;
	s[1] = '6';
	s[2] = 'B';
	s[3] = '.';
	s[4] = language;
	strcat(s, swipeCardTimeout);
	s[7] = trackNumber;
	s[8] = serviceCodeFlag;
	strcat(s, dataEntryTimeout);
	s[11] = pinEntryTimeout;
	strcat(s, pinDisplayprompt);
	strcat(s, amount);
	s[25] = tipEntryEnabled;
	s[26] = cashbackEnabled;
	s[27] = masterKeyIndicator;
	s[28] = sessionKeyLength;
	strcat(s, encryptedSessionKey);
	if ('D' == masterKeyIndicator && NULL != checkValue) {
		strcat(s, checkValue);
	}
	strcat(s, primaryAccountNumber);
	int len = strlen(s);
	s[len] = ETX;
	len = strlen(s);
	s[len] = lrc(s, 0, len);
	len = strlen(s);
	return send(s, len, recvBuf);
}
