package serial.enums;

public interface EmvServiceCode {

	public static final int EMV_INIT = 0;
	public static final int EMV_INQUIRE_EMV = 1;
	public static final int EMV_START = 2;
	public static final int EMV_SELECT_APPLICATION = 3;
	public static final int EMV_SELECT_ACCOUNT = 4;
	public static final int EMV_SELECT_LANGUAGE = 5;
	public static final int EMV_GET_AMOUNT = 6;
	public static final int EMV_CONFIRM = 7;
	public static final int EMV_GET_ONLINE_PIN = 8;
	public static final int EMV_RELEASE_SSA = 9;
	public static final int EMV_GET_PREVIOUS_AMOUNT = 10;
	public static final int EMV_PROMPT_FORCE_ONLINE = 11;
	public static final int EMV_CHECK_HOT_LIST = 12;
	public static final int EMV_PROMPT_PIN_BYPASS = 13;
	public static final int EMV_GO_ONLINE = 14;
	public static final int EMV_AS_REQUESTED = 15;
	public static final int EMV_GET_TLV = 16;
	public static final int EMV_STOP_TRANSACTION = 17;
	public static final int EMV_CALL_BACK = 18;
	public static final int EMV_GET_VERSIONS = 19;
	public static final int EMV_PASS_ICC_CVM_LIST = 20;
	public static final int EMV_UPDATE_DATA_ITEM = 21;
	public static final int EMV_GET_SW = 22;
	public static final int EMV_DO_PROPRIETARY_CVM = 23;
	public static final int EMV_PREPARE_CONTACTLESS = 24;
	public static final int EMV_START_CONTACTLESS = 25;
	public static final int EMV_GET_EXT_READER_FIRMWARE_VERSION = 26;
	public static final int EMV_GET_INITDATA_SHA1 = 27;
	public static final int EMV_GET_OFFLINE_PIN = 28;
	public static final int ENV_CLESS_PROCESS_SCRIPTS = 29;
	public static final int EMV_INIT_PAYWAVE_DRL = 30;
	public static final int EMV_CLESS_DATA_EXCHANGE = 31;
	public static final int EMV_MOBILE_PHONE_MESSAGE = 32;
	public static final int EMV_LOG_FILE = 33;
	public static final int EMV_INIT_PAYPASS3_PARAM = 34;
	public static final int MAX_N_EMV_SERVICES = 35;
	public static final int EMV_SERVICE_NONE = -1;

}
