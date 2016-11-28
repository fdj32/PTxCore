package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.111/184} {64. MAC Calculation
 * (ASCII character mode), One Variable Size Frame Format}
 * 
 * @author nfeng
 *
 */
public class Cpx64MacCalculationRequest extends CpxRequest {

	/**
	 * 1 ASCII character value, Application-Based:"0" - "2" – field is ignored
	 * (see note below). Terminal-Based:"0" - "o" in ASCII (64 Keys)
	 */
	private String masterKeyIndicator;
	/**
	 * 1 ASCII character "1" – single length "2" – double length
	 */
	private String sessionKeyLengthFlag;
	/**
	 * Single Length: 16 hex ASCII characters (If the SK is stored this field
	 * will be ignored although the length is checked; Double Length: 32 hex
	 * ASCII characters (If the SK is stored this field will be ignored although
	 * the length is che cked)
	 */
	private String encryptedSessionKey;
	/**
	 * Single Length: ""; Double Length: 8 hex ASCII characters check value to
	 * be verified before the key is used
	 */
	private String checkValue;
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

	public String getMacData() {
		return macData;
	}

	public void setMacData(String macData) {
		this.macData = macData;
	}

	public Cpx64MacCalculationRequest() {
		super();
		this.setMessageType("64.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getMasterKeyIndicator();
		msg += this.getSessionKeyLengthFlag();
		msg += this.getEncryptedSessionKey();
		msg += StringUtils.defaultString(this.getCheckValue());
		msg += this.getMacData();
		return msg;
	}
}
