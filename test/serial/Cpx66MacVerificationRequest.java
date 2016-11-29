package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.112/184} {66. MAC Verification
 * (ASCII character mode) ), One Variable Size Frame Format}
 * 
 * @author nfeng
 *
 */
public class Cpx66MacVerificationRequest extends CpxRequest {
	/**
	 * 1 ASCII character value, Application-Based:"0" - "2" – field is ignored
	 * (see note below). Terminal-Based: "0" -"o" in ASCII (64 Keys)
	 */
	private String masterKeyIndicator;
	/**
	 * 1 ASCII character "1" – single length; "2" – double length
	 */
	private String sessionKeyLengthFlag;
	/**
	 * 16 hex ASCII characters (If the SK is stored this field will be ignored
	 * although the length is checked)
	 * 
	 * 32 hex ASCII characters (If the SK is stored this field will be ignored
	 * although the length is checked)
	 */
	private String encryptedSessionKey;
	/**
	 * 8 hex ASCII characters check value to be verified before the key is used
	 */
	private String checkValue;
	/**
	 * 8 chars (32 bits) Result of 64 command
	 */
	private String macField;
	/**
	 * n ASCII characters 1 <= n <= 200 (Double length key)
	 */
	private String macData;

	public String getMasterKeyIndicator() {
		return masterKeyIndicator;
	}

	public void setMasterKeyIndicator(String masterKeyIndicator) {
		this.masterKeyIndicator = masterKeyIndicator;
	}

	public String getSessionKeyLengthFlag() {
		return sessionKeyLengthFlag;
	}

	public void setSessionKeyLengthFlag(String sessionKeyLengthFlag) {
		this.sessionKeyLengthFlag = sessionKeyLengthFlag;
	}

	public String getEncryptedSessionKey() {
		return encryptedSessionKey;
	}

	public void setEncryptedSessionKey(String encryptedSessionKey) {
		this.encryptedSessionKey = encryptedSessionKey;
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	public String getMacField() {
		return macField;
	}

	public void setMacField(String macField) {
		this.macField = macField;
	}

	public String getMacData() {
		return macData;
	}

	public void setMacData(String macData) {
		this.macData = macData;
	}

	public Cpx66MacVerificationRequest() {
		super();
		this.setMessageType("66.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getMasterKeyIndicator();
		msg += this.getSessionKeyLengthFlag();
		msg += this.getEncryptedSessionKey();
		msg += StringUtils.defaultString(this.getCheckValue());
		msg += this.getMacField();
		msg += this.getMacData();
		return msg;
	}

}
