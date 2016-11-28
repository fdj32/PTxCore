package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.54/184} {51. Inquire Current
 * Serial Number}
 * 
 * @author nfeng
 *
 */
public class Cpx51InquireSerialResponse extends CpxResponse {

	/**
	 * 1 ASCII number, "S"
	 */
	private String keyType;
	
	/**
	 * Device serial #, 16 ASCII character
	 */
	private String deviceSerialNumber;

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}

	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}

	public Cpx51InquireSerialResponse() {
		super();
		this.setMessageType("51.");
	}
	
	public static Cpx51InquireSerialResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1 + 1 + 16) != str.length() || !str.startsWith("51.")) {
			return null;
		}
		Cpx51InquireSerialResponse resp = new Cpx51InquireSerialResponse();
		resp.setStatus(str.substring(3, 4));
		resp.setKeyType(str.substring(4, 5));
		resp.setDeviceSerialNumber(str.substring(5, 21));
		return resp;
	}
}
