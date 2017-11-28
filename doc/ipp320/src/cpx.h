/*
 * cpx.h
 * 0140-07748-0103 Telium CPX and EMV Emulation.pdf Page 12/195
 *  Created on: 2017Äê11ÔÂ23ÈÕ
 *      Author: nfeng
 */

#ifndef CPX_H_
#define CPX_H_

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
#define READ_TIMEOUT 			8 // 8 second
#define POLL_TIME	 			100 // 100 millisecond
#define BAUD_RATE				9600
#define MODE_DATABITS8_PARITY_NONE_STOPBITS1	"8N1"
#define COM_PORT_NUMBER			24 // UBUNTU is /dev/ttyACM0

#define P_APP_NAME "B2_PTxEngine"
#define E_APP_NAME "CA0C00_ApVis"

int ack();

int send(char * buf, int size, char * recvBuf);

int cpx58display01A(char mode, char toggle, char lines, char lineStartIndex,
		char * prompt1, char * prompt2, char * prompt3, char * prompt4,
		char * recvBuf);

int cpx58display27(char mode, char lineStartIndex, char startPosition,
		char * prompt, char * promptIndex, char * maxInputLength,
		char * recvBuf);

int cpx31DukptpinEncryption(char timeoutValue, char displayLineNumber,
		char * primaryAccountNumber, char * pinDisplayPrompt, char * recvBuf);

int cpx40LoadSessionKey(char sessionKeyType, char masterkeyType,
		char * masterKeyOrSessionKey, char * checkValue, char * keySerialNumber,
		char * recvBuf);

int cpx50Cancel(char * recvBuf);

int cpx51InquireSerial(char * recvBuf);

int cpx53DiagnosticKeyCheckword(char keyIndicator, char * recvBuf);

int cpx59ClearDisplay(char lineNumber, char * recvBuf);

int cpx5BBeep(char beepLength, char beepFrequency, char * recvBuf);

int cpx5DDeviceInformation(char option, char * recvBuf);

int cpx64MacCalculation(char masterKeyIndicator, char sessionKeyLengthFlag,
		char * encryptedSessionKey, char * checkValue, char * macData,
		char * recvBuf);

int cpx66MacVerification(char masterKeyIndicator, char sessionKeyLengthFlag,
		char * encryptedSessionKey, char * checkValue, char * macField,
		char * macData, char * recvBuf);

int cpx67ActivateMsr(char trackNumber, char * timeout, char functionKeysActive,
		char lines, char lineNumber, char * prompt1, char * prompt2,
		char * prompt3, char * prompt4, char * recvBuf);

typedef struct CpxF0Command {
	char * lgt; // 2 bytes, length of application field information
	char type; // 1 byte, message type
	char * to; // 2 bytes, maximum timeout of execution supported by the terminal (in multiplies of 10 ms)
	char cmd; // 1 byte, command number for the driver selected
	char * dataE; // (LGT - 4) bytes DATA_E . data information field for the driver command selected
} CpxF0Command;

typedef struct CpxF1Command {
	char * lgt; // 2 bytes, length of application field information
	char type; // 1 byte, message type
	char msgSeqId; // 1 byte, reference number for this message (if MSG ID, may be user set, if SEQ ID for Asynchronous messages, it is incremented after each successful send).
	char msgVer; // 1 byte, message version to allow for future expansion and legacy message handling
	char * pAppName; // 12 bytes, request message source -> payment application name
	char * eAppName; // 12 bytes, application name of the EMV application where CPX will direct its request
	char * dataE; // (lgt-27) bytes, EMV data information field for the CPX EMV command selected
} CpxF1Command;

typedef struct CpxF1AsyncCommand {
	char * lgt; // 2 bytes, length of application field information
	char type; // 1 byte, message type
	char msgSeqId; // 1 byte, reference number for this message (if MSG ID, may be user set, if SEQ ID for Asynchronous messages, it is incremented after each successful send).
	char status; // 1 byte,
	char msgVer; // 1 byte, message version to allow for future expansion and legacy message handling
	char * pAppName; // 12 bytes, request message source -> payment application name
	char * eAppName; // 12 bytes, application name of the EMV application where CPX will direct its request
	char * dataE; // (lgt-28) bytes, EMV data information field for the CPX EMV command selected
} CpxF1AsyncCommand;

typedef struct CpxF1Result {
	char * lgt; // 2 bytes, length of application field information
	char type; // 1 byte, message type
	char msgSeqId; // 1 byte, reference number for this message (if MSG ID, may be user set, if SEQ ID for Asynchronous messages, it is incremented after each successful send).
	char status; // 1 byte, execution status
	char msgVer; // 1 byte, message version to allow for future expansion and legacy message handling
	char * eAppName; // 12 bytes, response message source -> EMV application name
	char * pAppName; // 12 bytes, application name of the payment application where CPX will direct its response
	char * dataR; // (lgt-28) bytes, data information response field for the command selected
} CpxF1Result;

#endif /* CPX_H_ */
