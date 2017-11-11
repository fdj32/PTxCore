/*
 * object.h
 * object definitions
 *  Created on: 2017��11��11��
 *      Author: Administrator
 */

#ifndef OBJECT_H_
#define OBJECT_H_

#define RFU 0x00
#define STX 0x02
#define ETX 0x03
#define NAK 0x05
#define ACK 0x06
#define SEPARATOR 0x0a
#define FS  0x1c
#define RS  0x1e
#define SPACE 0x20

typedef unsigned char byte;

typedef unsigned short byte2;

typedef enum EmvReasonCode {
	EMV_USER_CANCELLED,
	EMV_OK,
	EMV_APPROVED,
	EMV_DECLINED,
	EMV_END_OF_EMV_DIALOG,
	EMV_DIFFERENT_RID,
	EMV_RESELECTION,
	EMV_END_OF_DIAGNOSTICS_FLOW,
	EMV_FROM_FILE,
	EMV_OK_NO_MAC_KEY,
	EMV_ACK,
	EMV_OK_ACTION,
	EMV_OFFLINE_PIN_OK,
	EMV_CV_BYPASSED,
	EMV_CONTACTLESS_PERMITTED,
	EMV_CONTACTLESS_NOT_PERMITTED,
	EMV_REVERT,
	EMV_CONTACT_OK_CONTACTLESS_FAIL,
	EMV_NO_MAC_KEY_CONTACT_OK_CONTACTLESS_FAIL,
	EMV_MOBILE_PHONE_TAPPED,
	EMV_REPRESENT_CLESS_CARD,
	EMV_ISP_OK,
	EMV_ON_INFORM_ZEROLEN,
	EMV_KEYBOARD_INPUT_ZEROLEN,
	EMV_CHIP_DATA_INACCESSIBLE = 0x9c,
	EMV_NO_AID_MATCH,
	EMV_PIN_TRY_EXCEEDED,
	EMV_CARD_REMOVED,
	EMV_TIME_OUT,
	EMV_FAIL,
	EMV_HOST_NOT_AVAILABLE,
	EMV_CARD_OR_APP_BLOCKED,
	EMV_NOT_INITIALIZED,
	EMV_KEYBOARD_INPUT_TOO_SHORT,
	EMV_INVALID_PIN,
	EMV_LAST_PIN_TRY,
	EMV_KEYBOARD_INPUT_EMPTY,
	EMV_CARD_PROCESS_ERROR,
	EMV_FATAL_ERROR,
	EMV_SERVICE_NOT_SUPPORTED,
	EMV_INVALID_KEY,
	EMV_AID_BLOCKED,
	EMV_MAC_FAIL,
	EMV_NO_SSA_HANDLE,
	EMV_NAK,
	EMV_AUTH_INVALID_DATA,
	EMV_AUTH_TAG_NOT_SUPPORTED,
	EMV_MANDATORY_TAG_MISSING,
	EMV_INVALID_PARAMETER,
	EMV_CONTACTLESS_MULTIPLE_CARDS_NOT_ALLOWED,
	EMV_COMM_ERROR_WITH_EXTERNAL_READER,
	EMV_INVALID_AMOUNT,
	EMV_CARD_EXPIRED,
	EMV_CARD_NOT_SUPPORTED,
	EMV_NO_CARD_RESPONSE,
	EMV_DATA_AUTHENTICATION_FAIL,
	EMV_E2EE_ERROR,
	EMV_ISP_FAIL,
	EMV_UNDEF = 0xff
} EmvReasonCode;

typedef enum EmvServiceCode {
	EMV_INIT,
	EMV_INQUIRE_EMV,
	EMV_START,
	EMV_SELECT_APPLICATION,
	EMV_SELECT_ACCOUNT,
	EMV_SELECT_LANGUAGE,
	EMV_GET_AMOUNT,
	EMV_CONFIRM,
	EMV_GET_ONLINE_PIN,
	EMV_RELEASE_SSA,
	EMV_GET_PREVIOUS_AMOUNT,
	EMV_PROMPT_FORCE_ONLINE,
	EMV_CHECK_HOT_LIST,
	EMV_PROMPT_PIN_BYPASS,
	EMV_GO_ONLINE,
	EMV_AS_REQUESTED,
	EMV_GET_TLV,
	EMV_STOP_TRANSACTION,
	EMV_CALL_BACK,
	EMV_GET_VERSIONS,
	EMV_PASS_ICC_CVM_LIST,
	EMV_UPDATE_DATA_ITEM,
	EMV_GET_SW,
	EMV_DO_PROPRIETARY_CVM,
	EMV_PREPARE_CONTACTLESS,
	EMV_START_CONTACTLESS,
	EMV_GET_EXT_READER_FIRMWARE_VERSION,
	EMV_GET_INITDATA_SHA1,
	EMV_GET_OFFLINE_PIN,
	ENV_CLESS_PROCESS_SCRIPTS,
	EMV_INIT_PAYWAVE_DRL,
	EMV_CLESS_DATA_EXCHANGE,
	EMV_MOBILE_PHONE_MESSAGE,
	EMV_LOG_FILE,
	EMV_INIT_PAYPASS3_PARAM,
	EMV_SERVICE_NONE = 0xFF
} EmvServiceCode;

typedef enum EmvTransactionType {
	EMV_PURCHASE,
	EMV_PURCHASE_CORRECTION,
	EMV_PURCHASE_AND_CASHBACK,
	EMV_REFUND,
	EMV_REFUND_CORRECTION,
	EMV_PREAUTH,
	EMV_PREAUTH_COMPLETION
} EmvTransactionType;

typedef enum EmvTransactionStep {
	EMV_LANGUAGE_SELECTION,
	EMV_APP_SELECTION,
	EMV_INIT_CONTEXT,
	EMV_TRANSACTION_PREPARATION,
	EMV_DATA_AUTHENTICATION,
	EMV_CARDHOLDER_VERIFICATION,
	EMV_TRANSACTION_VALIDATION,
	EMV_ACTION_ANALYSIS,
	EMV_TRANSACTION_COMPLETION
} EmvTransactionStep;

typedef struct AID {
	byte applicationSelectionIndicator;
	byte lengthTLVData;
	char * tlvData;
	byte aidLength;
	char * aid;
	char * rid;
	char * applicationVersionNumber;
	char * tacDefault;
	char * tacDenial;
	char * tacOnline;
	byte maximumTargetPercentage;
	byte targetPercentage;
	char * thresholdValue;
	char * terminalFloorLimit;
	byte2 defaultTDOLLength;
	char * defaultTDOL;
	byte2 defaultDDOLLength;
	char * defaultDDOL;
	struct AID * next;
} AID;

typedef struct KeyData {
	byte keyIndex;
	byte keyAlgorithmIndicator;
	byte hashAlgorithmIndicator;
	byte keyLength;
	char * key;
	byte keyExponentLength;
	char * keyExponent;
	char * keyCheckSum;
	struct KeyData * next;
} KeyData;

typedef struct Tag {
	int id;
	int length;
	char * value;
	struct Tag * next;
} Tag;

typedef struct EndOfTransactionTags {
	EmvTransactionType type;
	Tag * tags;
	struct EndOfTransactionTags * next;
} EndOfTransactionTags;

typedef struct EmvTransactionStepTags {
	EmvTransactionStep step;
	Tag * tags;
	struct EmvTransactionStepTags * next;
} EmvTransactionStepTags;

typedef struct EmvTransactionTypeStepTags {
	EmvTransactionType type;
	EmvTransactionStepTags * stepTags;
	struct EmvTransactionTypeStepTags * next;
} EmvTransactionTypeStepTags;

typedef struct ExtendedAPIData {
	byte2 lengthStepTags;
	EmvTransactionTypeStepTags * tagListToAskFor;
	EmvTransactionTypeStepTags * tagListInCallBack;
} ExtendedAPIData;

typedef struct RID {
	char * rid;
	byte2 keyDataTotalLength;
	struct KeyData * keyDatas;
	byte2 lengthGoOnlineTags;
	struct Tag * goOnlineTags;
	byte2 lengthEndOfTransactionTags;
	struct EndOfTransactionTags endOfTransactionTags;
	char * endOfTransactionStep;
	byte2 lengthGetPreviousAmountTags;
	struct Tag * getPreviousAmountTags;
	byte2 lengthExtendedAPIData;
	ExtendedAPIData * extendedAPIData;
	byte2 lengthProprietaryRIDData;
	char * proprietaryRIDData;
	byte2 lengthIgnoredTags;
	Tag * ignoreTags;
	byte miscellaneousOptions;
	byte2 lengthTLVData;
	char * tlvData;
	struct RID * next;
} RID;

typedef struct OfflinePINEntryConfiguration {
	byte textFont;
	char ** prompt;
	char ** promptMAC;
	char * promptX;
	char * promptY;
	char * editX;
	char * editY;
	byte formatType;
	char * formatSpMAC;
	char * formatSpecifier;
	byte minimumKeys;
	byte maximumKeys;
	byte echoCharacter;
	byte cursorType;
	byte direction;
	char * beepInvalidKey;
	char * timeOutFirstKey;
	char * timeOutInterKey;
	byte keyType;
	byte keyIndex;
	char * noEnterLessMin;
	char * addReqSettings;
} OfflinePINEntryConfiguration;

typedef struct TerminalSpecificData {
	//RFU*1
	char * terminalCapabilities;
	char * additionalTerminalCapabilities;
	byte2 terminalCountryCode;
	byte terminalType;
	char * transactionCurrencyCode;
	byte transactionCurrencyExponent;
	byte2 transactionReferenceCurrencyCode;
	byte transactionReferenceCurrencyExponent;
	char * transactionReferenceCurrencyConversion;
	char * acquirerIdentifier;
	byte2 merchantCategoryCode;
	char * merchantIdentifier;
	char * terminalIdentification;
	char * terminalRiskManagementData;
	char * ifdSerialNumber;
	char * authorizationResponseCodeList;
	byte miscellaneousOptions;
	byte miscellaneousOptions1;
	// RFU*1
	byte2 lengthTLVData;
	char * tlvData;
	// RFU*20
	byte2 lengthOfflinePINEntryConfiguration;
	OfflinePINEntryConfiguration offlinePINEntryConfiguration;
	char * terminalLanguages;
	// RFU*2*2
	byte2 lengthDiagnosticsTags;
	char * diagnosticsTags;
	byte2 lengthAppSelectionTags;
	char * appSelectionTags;
	byte2 lengthRIDApps;
	char * ridApps;
} TerminalSpecificData;

typedef struct VegaInitData {
	byte2 terminalDataTotalLength;
	TerminalSpecificData terminalSpecificData;
	byte2 ridDataTotalLength;
	RID * ridSpecificData;
	byte2 aidDataTotalLength;
	AID * aidSpecificData;
} VegaInitData;


#endif /* OBJECT_H_ */