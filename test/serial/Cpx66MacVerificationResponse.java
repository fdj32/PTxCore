package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.112/184} {66. MAC Verification
 * (ASCII character mode) ), One Variable Size Frame Format}
 * 
 * @author nfeng
 *
 */
public class Cpx66MacVerificationResponse extends CpxResponse {
	/**
	 * 1 ASCII character "0" = SUCCESS (match) "1" = FAILURE (no match)
	 */
	private String match;

	public String getMatch() {
		return match;
	}

	public void setMatch(String match) {
		this.match = match;
	}

	public Cpx66MacVerificationResponse() {
		super();
		this.setMessageType("66.");
	}

	public static Cpx66MacVerificationResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1 + 1) != str.length() || !str.startsWith("66.")) {
			return null;
		}
		Cpx66MacVerificationResponse resp = new Cpx66MacVerificationResponse();
		resp.setStatus(str.substring(3, 4));
		resp.setMatch(str.substring(4, 5));
		return resp;
	}
}
