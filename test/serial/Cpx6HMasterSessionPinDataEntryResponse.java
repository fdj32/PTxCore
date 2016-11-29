package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.140/184} {6H . Master/Session
 * PIN Data Entry with Display and Pin Entry Control}
 * 
 * @author nfeng
 *
 */
public class Cpx6HMasterSessionPinDataEntryResponse extends CpxResponse {

	/**
	 * 2 characters "01"=ANSI
	 */
	private String pinBlockFormat;
	/**
	 * 16 ASCII hex characters
	 */
	private String encryptedPinBlock;

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

	public Cpx6HMasterSessionPinDataEntryResponse() {
		super();
		this.setMessageType("6H.");
	}

	public static Cpx6HMasterSessionPinDataEntryResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1 + 2 + 16) != str.length() || !str.startsWith("6H.")) {
			return null;
		}
		Cpx6HMasterSessionPinDataEntryResponse resp = new Cpx6HMasterSessionPinDataEntryResponse();
		resp.setStatus(str.substring(3, 4));
		resp.setPinBlockFormat(str.substring(4, 6));
		resp.setEncryptedPinBlock(str.substring(6, 22));
		return resp;
	}

}
