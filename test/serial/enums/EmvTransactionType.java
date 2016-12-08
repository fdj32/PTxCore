package serial.enums;

public interface EmvTransactionType {

	public static final int EMV_PURCHASE = 0;
	public static final int EMV_PURCHASE_CORRECTION = 1;
	public static final int EMV_PURCHASE_AND_CASHBACK = 2;
	public static final int EMV_REFUND = 3;
	public static final int EMV_REFUND_CORRECTION = 4;
	public static final int EMV_PREAUTH = 5;
	public static final int EMV_PREAUTH_COMPLETION = 6;
	public static final int MAX_N_EMV_TRANSACTION_TYPES = 7;
	public static final int EMV_TRANSACTION_NONE = -1;

}
