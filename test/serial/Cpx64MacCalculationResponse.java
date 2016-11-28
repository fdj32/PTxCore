package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.111/184} {64. MAC Calculation
 * (ASCII character mode), One Variable Size Frame Format}
 * 
 * @author nfeng
 *
 */
public class Cpx64MacCalculationResponse extends CpxResponse {

	/**
	 * 8 ASCII hex characters
	 */
	private String encryptedData;

	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}

	public Cpx64MacCalculationResponse() {
		super();
		this.setMessageType("64.");
	}

	public static Cpx64MacCalculationResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1 + 8) != str.length() || !str.startsWith("64.")) {
			return null;
		}
		Cpx64MacCalculationResponse resp = new Cpx64MacCalculationResponse();
		resp.setStatus(str.substring(3, 4));
		resp.setEncryptedData(str.substring(4, 12));
		return resp;
	}
}
