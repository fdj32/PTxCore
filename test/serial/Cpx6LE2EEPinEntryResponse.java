package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0142-07942-0225 Telium CPX E2EE solution v2.25.pdf {Page.21/35} {3.7. E2EE
 * PIN Entry}
 * 
 * @author nfeng
 * 
 */
public class Cpx6LE2EEPinEntryResponse extends CpxResponse {

	/**
	 * 2 chars "01"=ANSI
	 */
	private String pinBlockFormat;
	/**
	 * 16 ASCII hex chars
	 */
	private String encryptedPinBlock;
	/**
	 * 20 ASCII hex optional unless PIN encryption Key type ="D" for DUKPT
	 */
	private String keySerialNumber;

	public String getPinBlockFormat() {
		return pinBlockFormat;
	}

	public void setPinBlockFormat(String pinBlockFormat) {
		this.pinBlockFormat = pinBlockFormat;
	}

	public String getEncryptedPinBlock() {
		return encryptedPinBlock;
	}

	public void setEncryptedPinBlock(String encryptedPinBlock) {
		this.encryptedPinBlock = encryptedPinBlock;
	}

	public String getKeySerialNumber() {
		return keySerialNumber;
	}

	public void setKeySerialNumber(String keySerialNumber) {
		this.keySerialNumber = keySerialNumber;
	}

	public Cpx6LE2EEPinEntryResponse() {
		super();
		this.setMessageType("6L.");
	}

	public static Cpx6LE2EEPinEntryResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1 + 2 + 16 + 20) != str.length() || !str.startsWith("6K.")) {
			return null;
		}
		Cpx6LE2EEPinEntryResponse resp = new Cpx6LE2EEPinEntryResponse();
		resp.setStatus(str.substring(3, 4));
		resp.setPinBlockFormat(str.substring(4, 6));
		resp.setEncryptedPinBlock(str.substring(6, 22));
		resp.setKeySerialNumber(str.substring(22, 42));
		return resp;
	}

}
