package B2PS.PaymentTransactionEngine.PINPadDrivers.IngenicoCPX;

public class IngenicoCPXDriverConfig {
	/**
	 * <summary>Specifies the baud rate of the attached PINPad.  The default value is 9600.  <b>Must be specified by user</b>.</summary>
	 */
	private int baudRate;
	/**
	 * <summary>Of type <see cref="IngenicoCPXPINPad.InitMode"/>, specifies how the initialization process is performed.  The default value is “RebuildAndLoad”</summary>
	 */
	private IngenicoCPXPINPad.InitMode initMode;
	/**
	 * <summary>Stores the TxnSeqCounter, which is used by the driver for various EMV commands.</summary>
	 */
	private int txnSeqCounter;
	/**
	 * <summary>Specifies whether to send the command to set the PINPad date and time, during PINPad initialization.  The default value is “false”.</summary>
	 */
	private boolean setDateTime;
	/**
	 * <summary>Stores the Key Management Mode of the attached PINPad.  This value is obtained when the PINPad Serial Number is obtained.  Manually changing this value may cause the PINPad to behave incorrectly with regard to key storage and key functionality.</summary>
	 */
	private String cpxKeyMgmtMode;
	/**
	 * <summary>Stores the Key Management Flag of the attached PINPad.  This value is obtained when the PINPad Serial Number is obtained.  Manually changing this value may cause the PINPad to behave incorrectly with regard to key storage and key functionality.</summary>
	 */
	private char cpxKeyMgmtFlag;
	
	public int getBaudRate() {
		return baudRate;
	}
	public void setBaudRate(int baudRate) {
		this.baudRate = baudRate;
	}
	public IngenicoCPXPINPad.InitMode getInitMode() {
		return initMode;
	}
	public void setInitMode(IngenicoCPXPINPad.InitMode initMode) {
		this.initMode = initMode;
	}
	public int getTxnSeqCounter() {
		return txnSeqCounter;
	}
	public void setTxnSeqCounter(int txnSeqCounter) {
		this.txnSeqCounter = txnSeqCounter;
	}
	public boolean isSetDateTime() {
		return setDateTime;
	}
	public void setSetDateTime(boolean setDateTime) {
		this.setDateTime = setDateTime;
	}
	public String getCpxKeyMgmtMode() {
		return cpxKeyMgmtMode;
	}
	public void setCpxKeyMgmtMode(String cpxKeyMgmtMode) {
		this.cpxKeyMgmtMode = cpxKeyMgmtMode;
	}
	public char getCpxKeyMgmtFlag() {
		return cpxKeyMgmtFlag;
	}
	public void setCpxKeyMgmtFlag(char cpxKeyMgmtFlag) {
		this.cpxKeyMgmtFlag = cpxKeyMgmtFlag;
	}
	public IngenicoCPXDriverConfig() {
        this.baudRate = 9600;
        this.initMode = IngenicoCPXPINPad.InitMode.RebuildAndLoad;
        this.setDateTime = true;
        this.cpxKeyMgmtMode = "0";
        this.cpxKeyMgmtFlag = '0';
	}
	@Override
    public String toString()
    {
        return ("Ingenico CPX PINPad Driver Data");
    }
	
}
