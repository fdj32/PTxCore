package serial.enums;

public interface EmvTransactionStep {

	public static final int EMV_LANGUAGE_SELECTION = 0;
	public static final int EMV_APP_SELECTION = 1;
	public static final int EMV_INIT_CONTEXT = 2;
	public static final int EMV_TRANSACTION_PREPARATION = 3;
	public static final int EMV_DATA_AUTHENTICATION = 4;
	public static final int EMV_CARDHOLDER_VERIFICATION = 5;
	public static final int EMV_TRANSACTION_VALIDATION = 6;
	public static final int EMV_ACTION_ANALYSIS = 7;
	public static final int EMV_TRANSACTION_COMPLETION = 8;
	public static final int MAX_N_EMV_STEPS = 9;
	public static final int EMV_STEP_NONE = -1;

}
