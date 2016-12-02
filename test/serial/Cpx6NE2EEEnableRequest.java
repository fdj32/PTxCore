package serial;

/**
 * 0142-07942-0225 Telium CPX E2EE solution v2.25.pdf {Page.25/35} {3.9. E2EE
 * Enable}
 * 
 * @author nfeng
 * 
 */
public class Cpx6NE2EEEnableRequest extends CpxRequest {

	/**
	 * 1 character "1" - KME E2EE mode is enabled, "2" - IngeCrypt E2EE mode is
	 * enabled
	 */
	private String e2eeMode;
	/**
	 * 1 character "A" - (Type A) Base 24 will return track 2 only and support
	 * Base 24 framing . "B" - (Type B) IngeCrypt will return track 2 only with
	 * no framing and the KSN for the cryptogram.
	 */
	private String outputFormat;
	/**
	 * 1 character "M" - Master/Session key "D" for E2EE DUKPT.
	 */
	private String keyType;
	/**
	 * 1 character "0"-"9" for Telium key pattern 1 "0" – "o" for Telium key
	 * pattern 2 "0"-"5" for Telium key pattern 4 . Note: its value should be
	 * '2' to specify KME key .
	 */
	private String keyNumber;
	/**
	 * 1 char(optional) "0"-"9" Key number of optional TDES local storage data
	 * encryption key. The format of the LS data block is always that of the
	 * manual entry definition.
	 */
	private String localStorageKey;

	public String getE2eeMode() {
		return e2eeMode;
	}

	public void setE2eeMode(String e2eeMode) {
		this.e2eeMode = e2eeMode;
	}

	public String getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getKeyNumber() {
		return keyNumber;
	}

	public void setKeyNumber(String keyNumber) {
		this.keyNumber = keyNumber;
	}

	public String getLocalStorageKey() {
		return localStorageKey;
	}

	public void setLocalStorageKey(String localStorageKey) {
		this.localStorageKey = localStorageKey;
	}

	public Cpx6NE2EEEnableRequest() {
		super();
		this.setMessageType("6N.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getE2eeMode();
		msg += this.getOutputFormat();
		msg += this.getKeyType();
		msg += this.getKeyNumber();
		msg += this.getLocalStorageKey();
		return msg;
	}
}
