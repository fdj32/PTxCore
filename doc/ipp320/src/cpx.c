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
	char * s = malloc(512);
	memset(s, 0, 512);
	s[0] = STX;
	s[1] = '6';
	s[2] = '4';
	s[3] = '.';
	s[4] = masterKeyIndicator;
	s[5] = sessionKeyLengthFlag;
	strcat(s, encryptedSessionKey);
	if ('2' == sessionKeyLengthFlag && NULL != checkValue) {
		strcat(s, checkValue);
	}
	strcat(s, macData);
	int len = strlen(s);
	s[len] = ETX;
	len = strlen(s);
	s[len] = lrc(s, 0, len);
	len = strlen(s);
	return send(s, len, recvBuf);
}

int cpx66MacVerification(char masterKeyIndicator, char sessionKeyLengthFlag,
		char * encryptedSessionKey, char * checkValue, char * macField,
		char * macData, char * recvBuf) {
	char * s = malloc(512);
	memset(s, 0, 512);
	s[0] = STX;
	s[1] = '6';
	s[2] = '6';
	s[3] = '.';
	s[4] = masterKeyIndicator;
	s[5] = sessionKeyLengthFlag;
	strcat(s, encryptedSessionKey);
	if ('2' == sessionKeyLengthFlag && NULL != checkValue) {
		strcat(s, checkValue);
	}
	strcat(s, macField);
	strcat(s, macData);
	int len = strlen(s);
	s[len] = ETX;
	len = strlen(s);
	s[len] = lrc(s, 0, len);
	len = strlen(s);
	return send(s, len, recvBuf);
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
		char * cashBackPromptFrench, char totalOKpromptLineNumber,
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

int cpx6CScrollSelect(char commandMode, char nextFunctionKey,
		char previousFunctionKey, char showImages, char timeout,
		char invalidBeep, char * defaultSelection, char * titleString,
		char * nextOrPreviousString, char * prevOnlyString,
		char * nextOnlyString, char *selectListStrings[], char * recvBuf) {
	char * s = malloc(1024);
	memset(s, 0, 1024);
	s[0] = STX;
	s[1] = '6';
	s[2] = 'C';
	s[3] = '.';
	s[4] = commandMode;
	s[5] = nextFunctionKey;
	s[6] = previousFunctionKey;
	s[7] = showImages;
	s[8] = timeout;
	s[9] = invalidBeep;
	strcat(s, defaultSelection);
	strcat(s, stringRightPad(titleString, SPACE, 16));
	int len = strlen(s);
	s[len] = RS;

	strcat(s, stringRightPad(nextOrPreviousString, SPACE, 16));
	len = strlen(s);
	s[len] = RS;

	strcat(s, stringRightPad(prevOnlyString, SPACE, 16));
	len = strlen(s);
	s[len] = RS;

	strcat(s, stringRightPad(nextOnlyString, SPACE, 16));
	len = strlen(s);
	s[len] = RS;

	for (int i = 0; i < 20; i++) {
		if (NULL == selectListStrings[i])
			break;
		strcat(s, stringRightPad(selectListStrings[i], SPACE, 32));
		len = strlen(s);
		s[len] = RS;
	}

	len = strlen(s);
	s[len] = ETX;
	len = strlen(s);
	s[len] = lrc(s, 0, len);
	len = strlen(s);
	return send(s, len, recvBuf);
}

int cpx6DTimedMultiDisplay(char mode, char timeDisplay, char funcsKeysActive,
		char lineNumber, char *prompts[], char * recvBuf) {
	char * s = malloc(1024);
	memset(s, 0, 1024);
	s[0] = STX;
	s[1] = '6';
	s[2] = 'D';
	s[3] = '.';
	s[4] = mode;
	s[5] = timeDisplay;
	s[6] = funcsKeysActive;
	s[7] = lineNumber;
	int len = 0;
	for (int i = 0; i < 10; i++) {
		if (NULL == prompts[i])
			break;
		strcat(s, stringRightPad(prompts[i], 0, 64));
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

int cpx6HMasterSessionPinDataEntry(char pinEntryTimeout,
		char pinEntryLineNumber, char pinEntryMinimum, char pinEntryMaximum,
		char masterKeyIndicator, char * primaryAccountNumber,
		char sessionKeyLength, char * encrytedSessionKey, char * checkValue,
		char * pinDisplayPrompt, char lines, char promptLineNumber,
		char * promptIndex, char * prompt1, char * prompt2, char * prompt3,
		char * recvBuf) {
	char * s = malloc(128);
	memset(s, 0, 128);
	s[0] = STX;
	s[1] = '6';
	s[2] = 'H';
	s[3] = '.';
	s[4] = pinEntryTimeout;
	s[5] = pinEntryLineNumber;
	s[6] = pinEntryMinimum;
	s[7] = pinEntryMaximum;
	s[8] = masterKeyIndicator;
	strcat(s, primaryAccountNumber);
	int len = strlen(s);
	s[len] = sessionKeyLength;
	strcat(s, encrytedSessionKey);
	if ('D' == sessionKeyLength) {
		strcat(s, checkValue);
	}
	strcat(s, pinDisplayPrompt);
	len = strlen(s);
	s[len] = lines;
	len = strlen(s);
	s[len] = promptLineNumber;
	strcat(s, promptIndex);
	strcat(s, prompt1);
	len = strlen(s);
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
	len = strlen(s);
	s[len] = ETX;
	len = strlen(s);
	s[len] = lrc(s, 0, len);
	len = strlen(s);
	return send(s, len, recvBuf);
}

int cpx6IE2EEActivateMSR(char trackNumber, char * timeout,
		char functionKeysActive, char LinesOrTimeDelay, char lineNumber,
		char * prompt1, char * prompt2, char * prompt3, char * prompt4,
		char * recvBuf) {
	char * s = malloc(128);
	memset(s, 0, 128);
	s[0] = STX;
	s[1] = '6';
	s[2] = 'I';
	s[3] = '.';
	s[4] = trackNumber;
	strcat(s, timeout);
	s[7] = functionKeysActive;
	s[8] = LinesOrTimeDelay;
	s[9] = lineNumber;
	strcat(s, stringRightPad(prompt1, SPACE, 16));
	int len = strlen(s);
	s[len] = FS;
	if (NULL != prompt2) {
		strcat(s, stringRightPad(prompt2, SPACE, 16));
		len = strlen(s);
		s[len] = FS;
	}
	if (NULL != prompt3) {
		strcat(s, stringRightPad(prompt3, SPACE, 16));
		len = strlen(s);
		s[len] = FS;
	}
	if (NULL != prompt4) {
		strcat(s, stringRightPad(prompt4, SPACE, 16));
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

int cpx6KE2EEManualCardEntry(char lineNumber, char * prompt1,
		char * prompt1index, char * prompt2, char * prompt2index,
		char * recvBuf) {
	char * s = malloc(75);
	memset(s, 0, 75);
	s[0] = STX;
	s[1] = '6';
	s[2] = 'K';
	s[3] = '.';
	s[4] = lineNumber;
	strcat(s, stringRightPad(prompt1, SPACE, 32));
	strcat(s, prompt1index);
	strcat(s, stringRightPad(prompt2, SPACE, 32));
	strcat(s, prompt2index);
	s[73] = ETX;
	s[74] = lrc(s, 0, 74);
	return send(s, 75, recvBuf);
}

int cpx6LE2EEPinEntry(char pinEntryTimeout, char pinEntryLineNumber,
		char keyType, char pinKeySlotIndicator, char panEncryptedFlag,
		char * clearPanLength, char * pan, char lines, char promptLineNumber,
		char * promptIndex, char * prompt1, char * prompt2, char * prompt3,
		char pinBypassMode, char * recvBuf) {
	char * s = malloc(75);
	memset(s, 0, 75);
	s[0] = STX;
	s[1] = '6';
	s[2] = 'L';
	s[3] = '.';
	s[4] = pinEntryTimeout;
	s[5] = pinEntryLineNumber;
	s[6] = keyType;
	s[7] = pinKeySlotIndicator;
	s[8] = panEncryptedFlag;
	strcat(s, clearPanLength);
	strcat(s, pan);
	int len = strlen(s);
	s[len] = lines;
	len = strlen(s);
	s[len] = promptLineNumber;
	strcat(s, promptIndex);
	strcat(s, prompt1);
	len = strlen(s);
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
	len = strlen(s);
	s[len] = ETX;
	len = strlen(s);
	s[len] = lrc(s, 0, len);
	len = strlen(s);
	return send(s, len, recvBuf);
}

int cpx6NE2EEEnable(char e2eeMode, char outputFormat, char keyType,
		char keyNumber, char localStorageKey, char * recvBuf) {
	char * s = malloc(16);
	memset(s, 0, 16);
	s[0] = STX;
	s[1] = '6';
	s[2] = 'N';
	s[3] = '.';
	s[4] = e2eeMode;
	s[5] = outputFormat;
	s[6] = keyType;
	s[7] = keyNumber;
	s[8] = localStorageKey; // optional, 0 is not set
	int len = strlen(s);
	s[len] = ETX;
	len = strlen(s);
	s[len] = lrc(s, 0, len);
	len = strlen(s);
	return send(s, len, recvBuf);
}

int cpx6TSetDateTime(char mode, char * year, char * month, char * day,
		char * hour, char * minute, char * second, char * recvBuf) {
	char * s = malloc(21);
	memset(s, 0, 21);
	s[0] = STX;
	s[1] = '6';
	s[2] = 'T';
	s[3] = '.';
	s[4] = mode;
	strcat(s, year);
	strcat(s, month);
	strcat(s, day);
	strcat(s, hour);
	strcat(s, minute);
	strcat(s, second);
	s[19] = ETX;
	s[20] = lrc(s, 0, 20);
	return send(s, 21, recvBuf);
}

F0Command * f0MsrRead(char * to, char cmd) {
	return f0(4, to, cmd);
}

F0Command * f0CancelMsrRead(char * to) {
	return f0(4, to, 0x12);
}

F0Command * f0DefineRemoveCardPrompt(char * to) {
	return f0(5, to, 0x10);
}

F0Command * f0MSRwithSCDetectCancel(char * to) {
	return f0(5, to, 0x12);
}

F0Command * f0WaitForSmartCardInsertion(char * to) {
	return f0(7, to, 4);
}

F0Command * f0PowerUpCard(char * to) {
	return f0(7, to, 5);
}

F0Command * f0WaitInsertAndPowerUp(char * to) {
	return f0(7, to, 7);
}

F0Command * f0PowerUpCardAndControlsForATR(char * to) {
	return f0(7, to, 8);
}

F0Command * f0PowerOffCard(char * to) {
	return f0(7, to, 0x0a);
}

F0Command * f0WaitForRemovalOfCard(char * to) {
	return f0(7, to, 0x0b);
}

F0Command * f0PowerOffCardAndWaitForSmartCardRemoval(char * to) {
	return f0(7, to, 0x0c);
}

F0Command * f0SmartCardCommandCancel(char * to) {
	return f0(7, to, 0x12);
}

F0Command * f0SmartCardStatus(char * to) {
	return f0(7, to, 0x18);
}

F0Command * f0(char type, char * to, char cmd) {
	F0Command * f0cmd = malloc(sizeof(F0Command));
	f0cmd->lgt = bigEndianBin(4);
	f0cmd->type = type;
	f0cmd->to = to;
	f0cmd->cmd = cmd;
	f0cmd->dataE = NULL;
	return f0cmd;
}

int cpxF0(F0Command * f0cmd, char * recvBuf) {
	int len = bigEndianInt(f0cmd->lgt);
	char * f0 = malloc(len + 2);
	memset(f0, 0, len + 2);
	memcpy(f0, f0cmd->lgt, 2);
	f0[2] = f0cmd->type;
	memcpy(f0 + 3, f0cmd->to, 2);
	f0[5] = f0cmd->cmd;
	if (len > 4 && NULL != f0cmd->dataE) {
		memcpy(f0 + 6, f0cmd->dataE, len - 4);
	}
	int encode = (len + 2) * 2 + 6;
	char * s = malloc(encode);
	memset(s, 0, encode);
	s[0] = STX;
	s[1] = 'F';
	s[2] = '0';
	s[3] = '.';
	cpx16Encode(f0, 0, len + 2, s, 4);
	len = strlen(s);
	s[len] = ETX;
	len = strlen(s);
	s[len] = lrc(s, 0, len);
	len = strlen(s);
	return send(s, len, recvBuf);
}

F1Command * f1OpenSession(char msgSeqId) {
	return f1(OPEN_SESSION, msgSeqId);
}

F1Command * f1CloseSession(char msgSeqId) {
	return f1(CLOSE_SESSION, msgSeqId);
}

F1Command * f1(char type, char msgSeqId) {
	F1Command * f1cmd = malloc(sizeof(F1Command));
	f1cmd->lgt = bigEndianBin(27);
	f1cmd->type = type;
	f1cmd->msgSeqId = msgSeqId;
	f1cmd->msgVer = EMV_VERSION;
	f1cmd->pAppName = P_APP_NAME;
	f1cmd->eAppName = E_APP_NAME;
	f1cmd->dataE = NULL;
	return f1cmd;
}

int cpxF1(F1Command * f1cmd, char * recvBuf) {
	int len = bigEndianInt(f1cmd->lgt);
	char * f1 = malloc(len + 2);
	memset(f1, 0, len + 2);
	memcpy(f1, f1cmd->lgt, 2);
	f1[2] = f1cmd->type;
	f1[3] = f1cmd->msgSeqId;
	f1[4] = f1cmd->msgVer;
	memcpy(f1 + 5, f1cmd->pAppName, 12);
	memcpy(f1 + 17, f1cmd->eAppName, 12);
	if (len > 27 && NULL != f1cmd->dataE) {
		memcpy(f1 + 29, f1cmd->dataE, len - 27);
	}
	int encode = (len + 2) * 2 + 6;
	char * s = malloc(encode);
	memset(s, 0, encode);
	s[0] = STX;
	s[1] = 'F';
	s[2] = '1';
	s[3] = '.';
	cpx16Encode(f1, 0, len + 2, s, 4);
	len = strlen(s);
	s[len] = ETX;
	len = strlen(s);
	s[len] = lrc(s, 0, len);
	len = strlen(s);
	return send(s, len, recvBuf);
}

int asynEmvAck(char msgSeqId, char * recvBuf) {
	char * s = malloc(4);
	memset(s, 0, 4);
	s[1] = 2;
	s[2] = ASYN_EMV_ACK;
	s[3] = msgSeqId;
	char * t = malloc(16);
	memset(t, 0, 16);
	t[0] = STX;
	t[1] = 'F';
	t[2] = '1';
	t[3] = '.';
	cpx16Encode(s, 0, 4, t, 4);
	int len = strlen(t);
	t[len] = ETX;
	len = strlen(t);
	t[len] = lrc(t, 0, len);
	len = strlen(t);
	return send(t, len, recvBuf);
}

F1AsyncCommand * f1Async(char msgSeqId, char * dataE, int len) {
	F1AsyncCommand * f1Async = malloc(sizeof(F1AsyncCommand));
	f1Async->lgt = bigEndianBin(28 + len);
	f1Async->type = ASYN_EMV;
	f1Async->msgSeqId = msgSeqId;
	f1Async->status = STATUS_NORMAL;
	f1Async->msgVer = EMV_VERSION;
	f1Async->pAppName = P_APP_NAME;
	f1Async->eAppName = E_APP_NAME;
	f1Async->dataE = dataE;
	return f1Async;
}

int cpxF1Async(F1AsyncCommand * f1Async, char * recvBuf) {
	int len = bigEndianInt(f1Async->lgt);
	char * f1 = malloc(len + 2);
	memset(f1, 0, len + 2);
	memcpy(f1, f1Async->lgt, 2);
	f1[2] = f1Async->type;
	f1[3] = f1Async->msgSeqId;
	f1[4] = f1Async->status;
	f1[5] = f1Async->msgVer;
	memcpy(f1 + 6, f1Async->pAppName, 12);
	memcpy(f1 + 18, f1Async->eAppName, 12);
	if (len > 28 && NULL != f1Async->dataE) {
		memcpy(f1 + 30, f1Async->dataE, len - 28);
	}
	int encode = (len + 2) * 2 + 6;
	char * s = malloc(encode);
	memset(s, 0, encode);
	s[0] = STX;
	s[1] = 'F';
	s[2] = '1';
	s[3] = '.';
	cpx16Encode(f1, 0, len + 2, s, 4);
	len = strlen(s);
	s[len] = ETX;
	len = strlen(s);
	s[len] = lrc(s, 0, len);
	len = strlen(s);
	return send(s, len, recvBuf);
}
