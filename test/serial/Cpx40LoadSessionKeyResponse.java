package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.38/184} {40. Load Master
 * Key/Session Key}
 * 
 * @author nfeng
 *
 */
public class Cpx40LoadSessionKeyResponse extends CpxResponse {

	public static Cpx40LoadSessionKeyResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1) != str.length() || !str.startsWith("40.")) {
			return null;
		}
		Cpx40LoadSessionKeyResponse resp = new Cpx40LoadSessionKeyResponse();
		resp.setMessageType(str.substring(0, 3));
		resp.setMessageType(str.substring(3, 4));
		return resp;
	}

}
