package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.35/184} {31. DUKPT PIN
 * Encryption}
 * 
 * @author nfeng
 *
 */
public class Cpx31DukptpinEncryptionResponse extends CpxResponse {

	/**
	 * 2 characters, "01"=ANSI
	 */
	private String pinBlockFormat;
	/**
	 * 16 ASCII hex characters
	 */
	private String encryptedPinBlock;
	/**
	 * 20 ASCII hex characters
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
	
	public static Cpx31DukptpinEncryptionResponse parse(String str) {
		if(StringUtils.isEmpty(str)) {
			return null;
		}
		if((3+1+2+16+20) != str.length() || !str.startsWith("31.")) {
			return null;
		}
		Cpx31DukptpinEncryptionResponse resp = new Cpx31DukptpinEncryptionResponse();
		resp.setMessageType(str.substring(0, 3));
		resp.setStatus(str.substring(3, 4));
		resp.setPinBlockFormat(str.substring(4, 6));
		resp.setEncryptedPinBlock(str.substring(6, 22));
		resp.setKeySerialNumber(str.substring(22, 42));
		return resp;
	}

}
