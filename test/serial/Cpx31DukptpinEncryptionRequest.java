package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.35/184} {31. DUKPT PIN
 * Encryption}
 * 
 * @author nfeng
 *
 */
public class Cpx31DukptpinEncryptionRequest extends CpxRequest {

	/**
	 * 1 ASCII character, "0" - "F" increments of 5 seconds, lowercase 'a'-'f'
	 * is treated as the correspond uppercase letter.
	 */
	private String timeoutValue;
	/**
	 * 1 ASCII character, "0" - "4"
	 */
	private String displayLineNumber;
	/**
	 * 16 ASCII hex characters
	 */
	private String primaryAccountNumber;
	/**
	 * 4 ASCII characters, Field ignored for U32 devices if a predefined pin
	 * entry prompt has already been displayed (i.e. via a "58.0" display
	 * command) . Otherwise for U32 devices, this prompt is displayed on the
	 * left of the line.
	 */
	private String pinDisplayPrompt;

	public String getTimeoutValue() {
		return timeoutValue;
	}

	public void setTimeoutValue(String timeoutValue) {
		this.timeoutValue = timeoutValue;
	}

	public String getDisplayLineNumber() {
		return displayLineNumber;
	}

	public void setDisplayLineNumber(String displayLineNumber) {
		this.displayLineNumber = displayLineNumber;
	}

	public String getPrimaryAccountNumber() {
		return primaryAccountNumber;
	}

	public void setPrimaryAccountNumber(String primaryAccountNumber) {
		this.primaryAccountNumber = primaryAccountNumber;
	}

	public String getPinDisplayPrompt() {
		return pinDisplayPrompt;
	}

	public void setPinDisplayPrompt(String pinDisplayPrompt) {
		this.pinDisplayPrompt = pinDisplayPrompt;
	}

	
	
	public Cpx31DukptpinEncryptionRequest() {
		super();
		this.setMessageType("31.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getTimeoutValue();
		msg += this.getDisplayLineNumber();
		msg += this.getPrimaryAccountNumber();
		msg += this.getPinDisplayPrompt();
		return msg;
	}

}
