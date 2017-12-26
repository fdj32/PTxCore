/*
 * object.h
 * object definitions
 *  Created on: 2017Äê11ÔÂ11ÈÕ
 *      Author: Administrator
 */

#ifndef OBJECT_H_
#define OBJECT_H_

#ifdef __cplusplus
extern "C" {
#endif

#define RFU 0x00
#define STX 0x02
#define ETX 0x03
#define EOT 0x04
#define ENQ 0x05
#define ACK 0x06
#define LF  0x0a
#define NAK 0x15
#define CAN 0x18
#define ESC 0x1b
#define FS  0x1c
#define RS  0x1e
#define SPACE 0x20

#define TERMINAL_CAPABILITIES_US "e0f8c8" // need to support Online PIN for US
#define TERMINAL_CAPABILITIES_CA "e0b8c8" // does not support Online PIN for CA
#define ADDITIONAL_TERMINAL_CAPABILITIES "6000f0a001"
#define TERMINAL_COUNTRY_CODE_US	"0840"
#define TERMINAL_COUNTRY_CODE_CA	"0124"
#define TERMINAL_TYPE "22"
#define TRANSACTION_CURRENCY_CODE_US "0840"
#define TRANSACTION_CURRENCY_CODE_CA "0124"
#define TRANSACTION_CURRENCY_EXPONENT "02"
#define TERMINAL_CURRENCY_CODE_US	"0840"
#define TERMINAL_CURRENCY_CODE_CA	"0124"
#define TRANSACTION_REFERENCE_CURRENCY_CODE_US "0840"
#define TRANSACTION_REFERENCE_CURRENCY_CODE_CA "0124"
#define TRANSACTION_REFERENCE_CURRENCY_EXPONENT "02"
#define TRANSACTION_REFERENCE_CURRENCY_CONVERSION "61000000"
#define ACQUIRER_IDENTIFIER "000000001234"
#define MERCHANT_CATEGORY_CODE "0000"
#define TERMINAL_RISK_MANAGEMENT_DATA "0000000000000000"
#define IFD_SERIAL_NUMBER_DEFAULT "2020202020202020"
#define AUTHORIZATION_RESPONSE_CODE_LIST "Y1Z1Y2Z2Y3Z300050104" // HEX 59315a3159325a3259335a333030303530313034

#define EMV_ALWAYS_CALL_APP_SELECTION 0x80
#define EMV_PASS_AID_ON_APPLICATION_SELECTION 0x20
#define EMV_DISABLE_DOMESTIC_VISA_DEBIT 0x08
#define EMV_PA_GET_OFFLINE_PIN 0x10
#define EMV_USE_MULTIMESSAGING 0x02
#define ADD_INP_ONELINE_EDIT 0x1000
#define MISCELLANEOUS_OPTIONS (char)(EMV_ALWAYS_CALL_APP_SELECTION|EMV_PASS_AID_ON_APPLICATION_SELECTION)
#define MISCELLANEOUS_OPTIONS_1 EMV_USE_MULTIMESSAGING
#define LENGTH_GO_ONLINE_TAGS "1e00" // 0x001e = 30
#define GO_ONLINE_TAGS "95009b00029f089f099f109f1a9f269f279f349f369f379f399f419f1dff"
#define LENGTH_GO_ONLINE_TAGS_CONTACTLESS "4400" // 0x0044 = 68
#define GO_ONLINE_TAGS_CONTACTLESS "4f0057008200840095009a009b009c002a5f305f345f029f089f099f109f1a9f269f279f349f359f369f379f399f419f429f669f6b9f6c9f6e9f719f7c9f0bff18ff1dff"
#define LENGTH_END_OF_TRANSACTION_TAGS "64" // 0x64 = 100
#define END_OF_TRANSACTION_TAGS "4f00500057005a0082008a008f0095009a009b009c00245f285f2a5f2d5f305f345f029f039f079f089f099f0d9f0e9f0f9f109f119f129f1a9f1d9f269f279f349f359f369f379f399f419f429f669f6b9f6c9f6e9f719f7c9f11df0bff18ff1dff29ff"
// ExtendedAPIData.TagListToAskFor & TagListInCallBack
#define LENGTH_SELECTED_APP_TAGS "3a" // 0x3a = 58
#define SELECTED_APP_TAGS "4f00500057005a00820084009a009c00205f245f285f2a5f305f345f069f079f0d9f0e9f0f9f119f129f1a9f1e9f339f349f359f399f429f1dff"
#define LENGTH_IGNORE_TAGS "0200" // byte length
#define IGNORE_TAGS "169f"

#define RID_VISA 		"A000000003"
#define RID_MASTERCARD 	"A000000004"
#define RID_AMEX			"A000000025"
#define RID_INTERAC		"A000000277"
#define RID_DISCOVER 	"A000000324"
#define RID_DINERSCLUB	"A000000152"

#define VISA_TTQ "b2204000" // Terminal Transaction Qualifiers

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
	char applicationSelectionIndicator;
	char lengthTLVData;
	char * tlvData;
	char aidLength;
	char * aid;
	char * rid;
	char * applicationVersionNumber;
	char * tacDefault;
	char * tacDenial;
	char * tacOnline;
	char maximumTargetPercentage;
	char targetPercentage;
	char * thresholdValue;
	char * terminalFloorLimit;
	char * defaultTDOLLength;
	char * defaultTDOL;
	char * defaultDDOLLength;
	char * defaultDDOL;
	struct AID * next;
} AID;

int AIDLength(AID * o);

char * AIDToXML(AID * o);

char * AIDToBin(AID * o);

AID * AIDFromBin(char * s);

char * AIDArrayToBin(AID * o, int size);

AID * AIDArrayFromBin(char * s, int length);

char * AIDListToBin(AID * o);

AID * AIDListFromBin(char * s, int length);

AID * buildAidSpecificData(Param * param);

typedef struct KeyData {
	char keyIndex;
	char keyAlgorithmIndicator;
	char hashAlgorithmIndicator;
	char keyLength;
	char * key;
	char keyExponentLength;
	char * keyExponent;
	char * keyCheckSum;
} KeyData;

char * KeyDataToXML(KeyData * o, int size);

char * KeyDataToBin(KeyData * o, int size);

KeyData * KeyDataFromBin(char * s, int length);

typedef struct Tag {
	int id;
	int length;
	char * value;
	struct Tag * next;
} Tag;

char * TagsToXML(Tag * tags, int size);

char * TagsToBin(Tag * tags, int size);

Tag * TagsFromBin(char * s, int length);

int TLVToBin(Tag * tags, char * s);

Tag * TLVFromBin(char * s, int length);

int buildTerminalTlvData(char * s, char * terminalCapabilities, char * supportedRids);

typedef struct LengthThenTags {
	char length;
	Tag * tags;
} LengthThenTags;

char * LengthThenTagsToXML(LengthThenTags * o, int size);

char * LengthThenTagsToBin(LengthThenTags * o);

LengthThenTags * LengthThenTagsFromBin(char * s);

typedef LengthThenTags EndOfTransactionTags;

typedef LengthThenTags ExtendedAPIData;

typedef struct RID {
	char * rid;
	char * keyDataTotalLength;
	struct KeyData * keyDatas;
	char * lengthGoOnlineTags;
	struct Tag * goOnlineTags;
	char * lengthEndOfTransactionTags;
	EndOfTransactionTags * endOfTransactionTags; // size=7
	char * endOfTransactionStep;
	char * lengthGetPreviousAmountTags;
	struct Tag * getPreviousAmountTags;
	char * lengthExtendedAPIData;
	ExtendedAPIData * extendedAPIData; // size=2*7*8
	char * lengthProprietaryRIDData;
	char * proprietaryRIDData;
	char * lengthIgnoredTags;
	Tag * ignoreTags;
	char miscellaneousOptions;
	char * lengthTLVData;
	char * tlvData;
	struct RID * next;
} RID;

int RIDLength(RID * o);

char * RIDToXML(RID * o);

char * RIDToBin(RID * o);

RID * RIDFromBin(char * s);

char * RIDListToBin(RID * o);

RID * RIDListFromBin(char * s, int length);

typedef struct OfflinePINEntryConfiguration {
	char textFont;
	char * prompt;
	char * promptMAC;
	char * promptX;
	char * promptY;
	char * editX;
	char * editY;
	char formatType;
	char * formatSpMAC;
	char * formatSpecifier;
	char minimumKeys;
	char maximumKeys;
	char echoCharacter;
	char cursorType;
	char direction;
	char * beepInvalidKey;
	char * timeOutFirstKey;
	char * timeOutInterKey;
	char keyType;
	char keyIndex;
	char * noEnterLessMin;
	char * addReqSettings;
} OfflinePINEntryConfiguration;

char * OfflinePINEntryConfigurationToXML(OfflinePINEntryConfiguration * o);

char * OfflinePINEntryConfigurationToBin(OfflinePINEntryConfiguration * o);

OfflinePINEntryConfiguration * OfflinePINEntryConfigurationFromBin(char * s);

OfflinePINEntryConfiguration * buildOfflinePINEntryConfiguration();

typedef struct TerminalSpecificData {
	//RFU*1
	char * terminalCapabilities;
	char * additionalTerminalCapabilities;
	char * terminalCountryCode;
	char terminalType;
	char * transactionCurrencyCode;
	char transactionCurrencyExponent;
	char * transactionReferenceCurrencyCode;
	char transactionReferenceCurrencyExponent;
	char * transactionReferenceCurrencyConversion;
	char * acquirerIdentifier;
	char * merchantCategoryCode;
	char * merchantIdentifier;
	char * terminalIdentification;
	char * terminalRiskManagementData;
	char * ifdSerialNumber;
	char * authorizationResponseCodeList;
	char miscellaneousOptions;
	char miscellaneousOptions1;
	// RFU*1
	char * lengthTLVData;
	char * tlvData;
	// RFU*20
	char * lengthOfflinePINEntryConfiguration;
	OfflinePINEntryConfiguration * offlinePINEntryConfiguration;
	char * terminalLanguages;
	// RFU*2*2
	char * lengthDiagnosticsTags;
	char * diagnosticsTags;
	char * lengthAppSelectionTags;
	char * appSelectionTags;
	char * lengthRIDApps;
	char * ridApps;
} TerminalSpecificData;

int TerminalSpecificDataLength(TerminalSpecificData * o);

char * TerminalSpecificDataToXML(TerminalSpecificData * o);

char * TerminalSpecificDataToBin(TerminalSpecificData * o);

TerminalSpecificData * TerminalSpecificDataFromBin(char * s);

TerminalSpecificData * buildTerminalSpecificData(char * country,
		char * merchantIdentifier, char * terminalIdentification,
		char * ifdSerialNumber, char * ridApps);

typedef struct VegaInitData {
	char * terminalDataTotalLength;
	TerminalSpecificData * terminalSpecificData;
	char * ridDataTotalLength;
	RID * ridSpecificData;
	char * aidDataTotalLength;
	AID * aidSpecificData;
} VegaInitData;

int VegaInitDataLength(VegaInitData * o);

char * VegaInitDataToXML(VegaInitData * o);

char * VegaInitDataToBin(VegaInitData * o);

VegaInitData * VegaInitDataFromBin(char * s);

#ifdef __cplusplus
}
#endif

#endif /* OBJECT_H_ */
