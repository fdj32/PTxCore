/*
 * ipp320.h
 *
 *  Created on: 2017��11��10��
 *      Author: Administrator
 */

#ifndef IPP320_H_
#define IPP320_H_

#define RFU 0x00
#define STX 0x02
#define ETX 0x03
#define NAK 0x05
#define ACK 0x06
#define FS  0x1C
#define RS  0x1E

enum EMV_TRANSACTION_TYPE {
	EMV_PURCHASE,
	EMV_PURCHASE_CORRECTION,
	EMV_PURCHASE_AND_CASHBACK,
	EMV_REFUND,
	EMV_REFUND_CORRECTION,
	EMV_PREAUTH,
	EMV_PREAUTH_COMPLETION
};

enum EMV_TRANSACTION_STEP {
	EMV_LANGUAGE_SELECTION,
	EMV_APP_SELECTION,
	EMV_INIT_CONTEXT,
	EMV_TRANSACTION_PREPARATION,
	EMV_DATA_AUTHENTICATION,
	EMV_CARDHOLDER_VERIFICATION,
	EMV_TRANSACTION_VALIDATION,
	EMV_ACTION_ANALYSIS,
	EMV_TRANSACTION_COMPLETION
};

struct AID {
	unsigned char applicationSelectionIndicator;
	unsigned char lengthTLVData;
	char * tlvData;
	unsigned char aidLength;
	char * aid;
	char * rid;
	char * applicationVersionNumber;
	char * tacDefault;
	char * tacDenial;
	char * tacOnline;
	unsigned char maximumTargetPercentage;
	unsigned char targetPercentage;
	char * thresholdValue;
	char * terminalFloorLimit;
	unsigned short defaultTDOLLength;
	char * defaultTDOL;
	unsigned short defaultDDOLLength;
	char * defaultDDOL;
	struct AID * next;
};

#endif /* IPP320_H_ */
