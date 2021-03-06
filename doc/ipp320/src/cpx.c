/*
 * cpx.c
 *
 *  Created on: 2017��11��23��
 *      Author: nfeng
 */
#include "ipp320.h"

int ack() {
	printf("Answer ACK\n");
	return RS232_SendByte(COM_PORT_NUMBER, ACK);
}

int sendBytes(char * buf, int size) {
	printf("sent %i bytes: %s\n", size, hex((char *) buf, size));
	output("sent:", buf, size);
	int n = RS232_SendBuf(COM_PORT_NUMBER, buf, size);
	return n;
}

int cpx58display01A(char mode, char toggle, char lines, char lineStartIndex,
		char * prompt1, char * prompt2, char * prompt3, char * prompt4) {
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
	return sendBytes(s, 74);
}

int cpx58display27(char mode, char lineStartIndex, char startPosition,
		char * prompt, char * promptIndex, char * maxInputLength) {
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
	return sendBytes(s, 45);
}

int cpx31DukptpinEncryption(char timeoutValue, char displayLineNumber,
		char * primaryAccountNumber, char * pinDisplayPrompt) {
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
	return sendBytes(s, 28);
}

int cpx40LoadSessionKey(char sessionKeyType, char masterkeyType,
		char * masterKeyOrSessionKey, char * checkValue, char * keySerialNumber) {
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
	return sendBytes(s, 48);
}

int cpx50Cancel() {
	char * s = malloc(6);
	memset(s, 0, 6);
	s[0] = STX;
	s[1] = '5';
	s[2] = '0';
	s[3] = '.';
	s[4] = ETX;
	s[5] = lrc(s, 0, 5);
	return sendBytes(s, 6);
}

int cpx51InquireSerial() {
	char * s = malloc(7);
	memset(s, 0, 7);
	s[0] = STX;
	s[1] = '5';
	s[2] = '1';
	s[3] = '.';
	s[4] = 'S';
	s[5] = ETX;
	s[6] = lrc(s, 0, 6);
	return sendBytes(s, 7);
}

int cpx53DiagnosticKeyCheckword(char keyIndicator) {
	char * s = malloc(7);
	memset(s, 0, 7);
	s[0] = STX;
	s[1] = '5';
	s[2] = '3';
	s[3] = '.';
	s[4] = keyIndicator;
	s[5] = ETX;
	s[6] = lrc(s, 0, 6);
	return sendBytes(s, 7);
}

int cpx59ClearDisplay(char lineNumber) {
	char * s = malloc(7);
	memset(s, 0, 7);
	s[0] = STX;
	s[1] = '5';
	s[2] = '9';
	s[3] = '.';
	s[4] = lineNumber;
	s[5] = ETX;
	s[6] = lrc(s, 0, 6);
	return sendBytes(s, 7);
}

int cpx5BBeep(char beepLength, char beepFrequency) {
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
	return sendBytes(s, 8);
}

int cpx5DDeviceInformation(char option) {
	char * s = malloc(7);
	memset(s, 0, 7);
	s[0] = STX;
	s[1] = '5';
	s[2] = 'D';
	s[3] = '.';
	s[4] = option;
	s[5] = ETX;
	s[6] = lrc(s, 0, 6);
	return sendBytes(s, 7);
}

int cpx64MacCalculation(char masterKeyIndicator, char sessionKeyLengthFlag,
		char * encryptedSessionKey, char * checkValue, char * macData) {
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
	return sendBytes(s, len);
}

int cpx66MacVerification(char masterKeyIndicator, char sessionKeyLengthFlag,
		char * encryptedSessionKey, char * checkValue, char * macField,
		char * macData) {
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
	return sendBytes(s, len);
}

int cpx67ActivateMsr(char trackNumber, char * timeout, char functionKeysActive,
		char lines, char lineNumber, char * prompt1, char * prompt2,
		char * prompt3, char * prompt4) {
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
	return sendBytes(s, len);
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
		char * pinErrorMessagePromptFrench, char * serviceCodeList) {
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
	return sendBytes(s, len);
}

int cpx6BInteracDebitSequence(char language, char * swipeCardTimeout,
		char trackNumber, char serviceCodeFlag, char * dataEntryTimeout,
		char pinEntryTimeout, char * pinDisplayprompt, char * amount,
		char tipEntryEnabled, char cashbackEnabled, char masterKeyIndicator,
		char sessionKeyLength, char * encryptedSessionKey, char * checkValue,
		char * primaryAccountNumber) {
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
	return sendBytes(s, len);
}

int cpx6CScrollSelect(char commandMode, char nextFunctionKey,
		char previousFunctionKey, char showImages, char timeout,
		char invalidBeep, char * defaultSelection, char * titleString,
		char * nextOrPreviousString, char * prevOnlyString,
		char * nextOnlyString, char *selectListStrings[]) {
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
	return sendBytes(s, len);
}

int cpx6DTimedMultiDisplay(char mode, char timeDisplay, char funcsKeysActive,
		char lineNumber, char *prompts[]) {
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
	return sendBytes(s, len);
}

int cpx6HMasterSessionPinDataEntry(char pinEntryTimeout,
		char pinEntryLineNumber, char pinEntryMinimum, char pinEntryMaximum,
		char masterKeyIndicator, char * primaryAccountNumber,
		char sessionKeyLength, char * encrytedSessionKey, char * checkValue,
		char * pinDisplayPrompt, char lines, char promptLineNumber,
		char * promptIndex, char * prompt1, char * prompt2, char * prompt3) {
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
	return sendBytes(s, len);
}

int cpx6IE2EEActivateMSR(char trackNumber, char * timeout,
		char functionKeysActive, char LinesOrTimeDelay, char lineNumber,
		char * prompt1, char * prompt2, char * prompt3, char * prompt4) {
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
	return sendBytes(s, len);
}

int cpx6KE2EEManualCardEntry(char lineNumber, char * prompt1,
		char * prompt1index, char * prompt2, char * prompt2index) {
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
	return sendBytes(s, 75);
}

int cpx6LE2EEPinEntry(char pinEntryTimeout, char pinEntryLineNumber,
		char keyType, char pinKeySlotIndicator, char panEncryptedFlag,
		char * clearPanLength, char * pan, char lines, char promptLineNumber,
		char * promptIndex, char * prompt1, char * prompt2, char * prompt3,
		char pinBypassMode) {
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
	return sendBytes(s, len);
}

int cpx6NE2EEEnable(char e2eeMode, char outputFormat, char keyType,
		char keyNumber, char localStorageKey) {
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
	return sendBytes(s, len);
}

int cpx6TSetDateTime(char mode, char * year, char * month, char * day,
		char * hour, char * minute, char * second) {
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
	return sendBytes(s, 21);
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

int cpxF0(F0Command * f0cmd) {
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
	return sendBytes(s, len);
}

F1Command * f1Version(char msgSeqId) {
	return f1(EMV_VERSION, msgSeqId);
}

F1Command * f1OpenSession() {
	return f1(OPEN_SESSION, 0);
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

int cpxF1(F1Command * f1cmd) {
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
	printf("sent:F1.%s\n", hex(f1, len + 2));
	cpx16Encode(f1, 0, len + 2, s, 4);
	len = strlen(s);
	s[len] = ETX;
	len = strlen(s);
	s[len] = lrc(s, 0, len);
	len = strlen(s);
	return sendBytes(s, len);
}

int openSession() {
	return cpxF1(f1OpenSession());
}

int closeSession(int msgSeqId) {
	return cpxF1(f1CloseSession(msgSeqId));
}

int asynEmvAck(char msgSeqId) {
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
	printf("Asynchronous EMV ACK msgSeqId=%u\n", msgSeqId);
	return sendBytes(t, len);
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

int cpxF1Async(F1AsyncCommand * f1Async) {
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
	printf("sent:F1.%s\n", hex(f1, len + 2));
	cpx16Encode(f1, 0, len + 2, s, 4);
	len = strlen(s);
	s[len] = ETX;
	len = strlen(s);
	s[len] = lrc(s, 0, len);
	len = strlen(s);
	return sendBytes(s, len);
}

int vegaInit(char * s, int size) {
	int n = openComPort();
	if (0 != n) {
		closeComPort();
		return EXIT_FAILURE;
	}

	pthread_t t;
	Msg * h = malloc(sizeof(Msg));
	h->next = NULL;
	pthread_create(&t, NULL, recvMsg, h);

	n = cpx58display01A('0', '0', '4', '1', "", "Initializing", "", "");
	Msg * m = getRespMsg("58", h);
	printf("cpx58display01A m->msg[3] = %c\n", m->msg[3]);
	if (NULL == m || m->msg[3] != '0') {
		return EXIT_FAILURE;
	}

	n = openSession();
	m = getRespMsg("F1", h);
	printf("openSession m->msg[7] = %d\n", m->msg[7]);
	if (NULL == m || m->msg[7] != 0) {
		if (m->msg[7] == 7) {
			// already open, close it
			n = closeSession(0);
			m = getRespMsg("F1", h);
			printf("closeSession m->msg[7] = %d\n", m->msg[7]);
			if (NULL == m || m->msg[7] != 0) {
				return EXIT_FAILURE;
			}
			// open again
			n = openSession();
			m = getRespMsg("F1", h);
			printf("openSession again m->msg[7] = %d\n", m->msg[7]);
			if (NULL == m || m->msg[7] != 0) {
				return EXIT_FAILURE;
			}
		}
	}

	unsigned char msgId = 0;
	int index = 0;
	const int initPacketSize = MAX_VEGA_PACKET_SIZE - 1;
	int dataPacketSize = 0;
	while (index < size) {
		char * p = malloc(128);
		memset(p, 0, 128);
		char * dataPacket = malloc(MAX_VEGA_PACKET_SIZE + 4);
		memset(dataPacket, 0, MAX_VEGA_PACKET_SIZE + 4);
		dataPacket[0] = 0; // EmvServiceCode.EMV_INIT
		dataPacket[1] = 0xff; // EmvReasonCode.EMV_UNDEF
		if (size - index > initPacketSize) {
			// More to follow
			dataPacketSize = initPacketSize;
			dataPacket[4] = 1;
		} else {
			// Last one
			dataPacket[4] = 0;
			dataPacketSize = size - index;
		}
		memcpy(dataPacket + 2, littleEndianBin(dataPacketSize + 1), 2);
		memcpy(dataPacket + 5, s + index, dataPacketSize);
		n = cpxF1Async(f1Async(msgId, dataPacket, dataPacketSize + 5));

		m = waitAsyncEmvAck((char) msgId, h);
		if (NULL != m) {
			printf("recv: Async Emv Ack : %d\n", msgId);
			msgId++;
		}

		m = getRespMsg("F1", h);
		printf("cpxF1Async m->msg[7] = %d\n", m->msg[7]);
		if (NULL == m || 0 != m->msg[7]) {
			closeSession(msgId);
			return EXIT_FAILURE;
		} else {
			printf("msgId=%u\n", (unsigned char) (m->msg[6]));
			asynEmvAck(m->msg[6]);
		}
		index += initPacketSize;
	}

	n = cpx58display01A('0', '0', '4', '1', "", "Initialized OK", "", "");
	m = getRespMsg("58", h);
	printf("cpx58display01A m->msg[3] = %c\n", m->msg[3]);
	if (NULL == m || m->msg[3] != '0') {
		return EXIT_FAILURE;
	}
	closeComPort();
	return n;
}

int parseResponse(char * s, int n, char * t) {
	char * p = s;
	int len = n;
	for (int i = 0; i < n; i++) {
		if (*p != STX) { // find the start STX
			p++;
			len--;
		} else {
			break;
		}
		if (i == n - 1) {
			return -1;
		}
	}
	//printf("\n%c\n", p[4]);
	if ('F' != p[1]) {
		memcpy(t, p + 1, len - 3); // STX, ETX, LRC
		return (len - 3);
	} else { // F0 & F1 cpx16Decode
		memcpy(t, p + 1, 3); // F, 0or1, .
		// STX, F, 0or1, ., ..., ETX, LRC
		return (3 + cpx16Decode(p, 4, len - 2, t, 3));
	}
}

Msg * getRespMsg(const char * type, Msg * h) {
	Msg * p = h;
	Msg * m = NULL;
	while (m == NULL) {
		if (p->next != NULL && p->next->msg[0] == type[0]
				&& p->next->msg[1] == type[1]) {
			m = p->next;
			p->next = p->next->next; // delete
			m->next = NULL;
			break;
		}
		if (p->next == NULL) {
			p = h;
		} else {
			p = p->next;
		}
		sleepM(POLL_TIME);
	}
	return m;
}

Msg * waitAsyncEmvAck(char msgSeqId, Msg * h) {
	Msg * p = h;
	Msg * m = NULL;
	while (m == NULL) {
		if (p->next != NULL && p->next->len == 7 && p->next->msg[0] == 'F'
				&& p->next->msg[1] == '1' && p->next->msg[2] == '.'
				&& p->next->msg[3] == 0 && p->next->msg[4] == 2
				&& p->next->msg[5] == 5 && p->next->msg[6] == msgSeqId) {
			m = p->next;
			p->next = p->next->next; // delete async emv ack
			m->next = NULL;
			break;
		}
		if (p->next == NULL) {
			p = h;
		} else {
			p = p->next;
		}
		sleepM(POLL_TIME);
	}
	return m;
}
