/*
 * cpx.h
 * 0140-07748-0103 Telium CPX and EMV Emulation.pdf Page 12/195
 *  Created on: 2017Äê11ÔÂ23ÈÕ
 *      Author: nfeng
 */

#ifndef CPX_H_
#define CPX_H_

#ifdef __cplusplus
extern "C" {
#endif

#define CPX31_PIN_ENTRY_DUKPT 				"31."
#define CPX40_LOAD_MASTER_SESSION_KEY 		"40."
#define CPX41_PIN_ENTRY_MASTER_SESSION 		"41."
#define	CPX42_DATA_ENCRYPTION				"42."
#define CPX43_MAC_CALCULATION_HEX			"43."
#define CPX44_MAC_CALCULATION_CHARACTER		"44."
#define CPX45_MAC_VERIFICATION_HEX			"45."
#define CPX46_MAC_VERIFICATION_CHARACTER	"46."
#define CPX47_DECRYPT_DISPLAY				"47."
#define CPX50_CANCEL						"50."
#define CPX51_INQUIRE_SERIAL_NUMBERS		"51."
#define CPX53_DIAGNOSTIC_KEY_CHECKWORD		"53."
#define CPX56_PROMPT_REGISTER				"56."
#define CPX57_AMOUNT_HANDLING				"57."
#define CPX58_DISPLAY_HANDLING				"58."
#define CPX59_CLEAR_LCR_DISPLAY				"59."
#define	CPX5B_BEEP							"5B."
#define CPX5C_DEVICE_STORAGE_BLOCK			"5C."
#define CPX5D_DEVICE_INFORMATION			"5D."
#define	CPX5F_KEYBOARD_CHECK				"5F."
#define CPX60_ACCESS_DATA_TABLE				"60."
#define	CPX61_RESET_DATA_TABLE_INDEXES		"61."
#define CPX62_INQUIRE_DATA_TABLE_INDEXES	"62."
#define CPX64_MAC_CALCULATION				"64."
#define CPX66_MAC_VERIFICATION				"66."
#define CPX67_ACTIVATE_MSR_WITH_DISPLAY		"67."
#define CPX68_CLEAR_TEXT_ENTRY_WITH_STORED_PROMPT	"68."
#define CPX69_PIN_ENTRY_MASTER_SESSION_WITH_DISPLAY	"69."
#define CPX6A_INTERAC_DEBIT_SEQUENCE_INITIALIZATION	"6A."
#define CPX6B_INTERAC_DEBIT_SEQUENCE		"6B."
#define CPX6C_SCROLLING_SELECTION_LIST		"6C."
#define CPX6D_TIMED_MULTI_DISPLAY			"6D."
#define CPX6E_EXTENDED_MAC_CALCULATION		"6E."
#define CPX6G_EXTENDED_MAC_VERIFICATION		"6G."
#define CPX6H_PIN_ENTRY_MASTER_SESSION_WITH_DISPLAY_AND_PIN_ENTRY_CONTROL	"6H."
#define CPX6S_ENABLE_DISABLE_LED_CONTROL	"6S."
#define CPX6T_READ_SET_DATE_AND_TIME		"6T."
#define	CPX6U_GRAPHIC_DISPLAY				"6U."
#define CPX6V_PRINTING						"6V."
#define	CPX6W_ENABLE_DISABLE_HEADER			"6W."
#define CPX6X_ENABLE_DISABLE_BACKLIGHT		"6X."
#define CPX72_PIN_ENTRY_CANCEL				"72."
#define CPXER_DEVICE_REBOOT					"ER."
#define CPXF0_MSR							"F0."
#define CPXF1_EMV							"F1."

#define MAX_RESEND				3
#define MAX_VEGA_PACKET_SIZE 	498
#define READ_TIMEOUT 			3 // seconds
#define POLL_TIME	 			10 // 10 milliseconds
#define BAUD_RATE				9600
#define MODE_DATABITS8_PARITY_NONE_STOPBITS1	"8N1"
//#define COM_PORT_NUMBER			24 // UBUNTU is /dev/ttyACM0
#define COM_PORT_NUMBER			38 // MacOSX USB Left is /dev/tty.usbmodem1411
//#define COM_PORT_NUMBER			39 // MacOSX USB Right is /dev/tty.usbmodem1421

#define P_APP_NAME "B2_PTxEngine"
#define E_APP_NAME "CA0C00_ApVis"

#define STATUS_NORMAL	0
#define EMV_VERSION		1
#define OPEN_SESSION 	2
#define CLOSE_SESSION 	3
#define ASYN_EMV			4
#define ASYN_EMV_ACK 	5

int ack();

int sendBytes(char * buf, int size);

int cpx58display01A(char mode, char toggle, char lines, char lineStartIndex,
		char * prompt1, char * prompt2, char * prompt3, char * prompt4);

int cpx58display27(char mode, char lineStartIndex, char startPosition,
		char * prompt, char * promptIndex, char * maxInputLength);

int cpx31DukptpinEncryption(char timeoutValue, char displayLineNumber,
		char * primaryAccountNumber, char * pinDisplayPrompt);

int cpx40LoadSessionKey(char sessionKeyType, char masterkeyType,
		char * masterKeyOrSessionKey, char * checkValue, char * keySerialNumber);

int cpx50Cancel();

int cpx51InquireSerial();

int cpx53DiagnosticKeyCheckword(char keyIndicator);

int cpx59ClearDisplay(char lineNumber);

int cpx5BBeep(char beepLength, char beepFrequency);

int cpx5DDeviceInformation(char option);

int cpx64MacCalculation(char masterKeyIndicator, char sessionKeyLengthFlag,
		char * encryptedSessionKey, char * checkValue, char * macData);

int cpx66MacVerification(char masterKeyIndicator, char sessionKeyLengthFlag,
		char * encryptedSessionKey, char * checkValue, char * macField,
		char * macData);

int cpx67ActivateMsr(char trackNumber, char * timeout, char functionKeysActive,
		char lines, char lineNumber, char * prompt1, char * prompt2,
		char * prompt3, char * prompt4);

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
		char * pinErrorMessagePromptFrench, char * serviceCodeList);

int cpx6BInteracDebitSequence(char language, char * swipeCardTimeout,
		char trackNumber, char serviceCodeFlag, char * dataEntryTimeout,
		char pinEntryTimeout, char * pinDisplayprompt, char * amount,
		char tipEntryEnabled, char cashbackEnabled, char masterKeyIndicator,
		char sessionKeyLength, char * encryptedSessionKey, char * checkValue,
		char * primaryAccountNumber);

int cpx6CScrollSelect(char commandMode, char nextFunctionKey,
		char previousFunctionKey, char showImages, char timeout,
		char invalidBeep, char * defaultSelection, char * titleString,
		char * nextOrPreviousString, char * prevOnlyString,
		char * nextOnlyString, char *selectListStrings[]);

int cpx6DTimedMultiDisplay(char mode, char timeDisplay, char funcsKeysActive,
		char lineNumber, char *prompts[]);

int cpx6HMasterSessionPinDataEntry(char pinEntryTimeout,
		char pinEntryLineNumber, char pinEntryMinimum, char pinEntryMaximum,
		char masterKeyIndicator, char * primaryAccountNumber,
		char sessionKeyLength, char * encrytedSessionKey, char * checkValue,
		char * pinDisplayPrompt, char lines, char promptLineNumber,
		char * promptIndex, char * prompt1, char * prompt2, char * prompt3);

int cpx6IE2EEActivateMSR(char trackNumber, char * timeout,
		char functionKeysActive, char LinesOrTimeDelay, char lineNumber,
		char * prompt1, char * prompt2, char * prompt3, char * prompt4);

int cpx6KE2EEManualCardEntry(char lineNumber, char * prompt1,
		char * prompt1index, char * prompt2, char * prompt2index);

int cpx6LE2EEPinEntry(char pinEntryTimeout, char pinEntryLineNumber,
		char keyType, char pinKeySlotIndicator, char panEncryptedFlag,
		char * clearPanLength, char * pan, char lines, char promptLineNumber,
		char * promptIndex, char * prompt1, char * prompt2, char * prompt3,
		char pinBypassMode);

int cpx6NE2EEEnable(char e2eeMode, char outputFormat, char keyType,
		char keyNumber, char localStorageKey);

int cpx6TSetDateTime(char mode, char * year, char * month, char * day,
		char * hour, char * minute, char * second);

typedef struct F0Command {
	char * lgt; // 2 bytes, length of application field information
	char type; // 1 byte, message type
	char * to; // 2 bytes, maximum timeout of execution supported by the terminal (in multiplies of 10 ms)
	char cmd; // 1 byte, command number for the driver selected
	char * dataE; // (LGT - 4) bytes DATA_E . data information field for the driver command selected
} F0Command;

F0Command * f0MsrRead(char * to, char cmd);

F0Command * f0CancelMsrRead(char * to);

F0Command * f0DefineRemoveCardPrompt(char * to);

F0Command * f0MSRwithSCDetectCancel(char * to);

F0Command * f0WaitForSmartCardInsertion(char * to);

F0Command * f0PowerUpCard(char * to);

F0Command * f0WaitInsertAndPowerUp(char * to);

F0Command * f0PowerUpCardAndControlsForATR(char * to);

F0Command * f0PowerOffCard(char * to);

F0Command * f0WaitForRemovalOfCard(char * to);

F0Command * f0PowerOffCardAndWaitForSmartCardRemoval(char * to);

F0Command * f0SmartCardCommandCancel(char * to);

F0Command * f0SmartCardStatus(char * to);

F0Command * f0(char type, char * to, char cmd);

int cpxF0MsrRead(F0Command * f0cmd);

typedef struct F1Command {
	char * lgt; // 2 bytes, length of application field information
	char type; // 1 byte, message type
	char msgSeqId; // 1 byte, reference number for this message (if MSG ID, may be user set, if SEQ ID for Asynchronous messages, it is incremented after each successful send).
	char msgVer; // 1 byte, message version to allow for future expansion and legacy message handling
	char * pAppName; // 12 bytes, request message source -> payment application name
	char * eAppName; // 12 bytes, application name of the EMV application where CPX will direct its request
	char * dataE; // (lgt-27) bytes, EMV data information field for the CPX EMV command selected
} F1Command;

F1Command * f1Version(char msgSeqId);

F1Command * f1OpenSession();

F1Command * f1CloseSession(char msgSeqId);

F1Command * f1(char type, char msgSeqId);

int cpxF1(F1Command * f1cmd);

int openSession();

int closeSession(int msgSeqId);

int asynEmvAck(char msgSeqId);

typedef struct F1AsyncCommand {
	char * lgt; // 2 bytes, length of application field information
	char type; // 1 byte, message type
	char msgSeqId; // 1 byte, reference number for this message (if MSG ID, may be user set, if SEQ ID for Asynchronous messages, it is incremented after each successful send).
	char status; // 1 byte,
	char msgVer; // 1 byte, message version to allow for future expansion and legacy message handling
	char * pAppName; // 12 bytes, request message source -> payment application name
	char * eAppName; // 12 bytes, application name of the EMV application where CPX will direct its request
	char * dataE; // (lgt-28) bytes, EMV data information field for the CPX EMV command selected
} F1AsyncCommand;

F1AsyncCommand * f1Async(char msgSeqId, char * dataE, int len);

int cpxF1Async(F1AsyncCommand * f1Async);

typedef struct F1Result {
	char * lgt; // 2 bytes, length of application field information
	char type; // 1 byte, message type
	char msgSeqId; // 1 byte, reference number for this message (if MSG ID, may be user set, if SEQ ID for Asynchronous messages, it is incremented after each successful send).
	char status; // 1 byte, execution status
	char msgVer; // 1 byte, message version to allow for future expansion and legacy message handling
	char * eAppName; // 12 bytes, response message source -> EMV application name
	char * pAppName; // 12 bytes, application name of the payment application where CPX will direct its response
	char * dataR; // (lgt-28) bytes, data information response field for the command selected
} F1Result;

int vegaInit(char * s, int size);

int parseResponse(char * s, int n, char * t);

Msg * getRespMsg(const char * type, Msg * h);

Msg * waitAsyncEmvAck(char msgSeqId, Msg * h);

#ifdef __cplusplus
}
#endif

#endif /* CPX_H_ */
