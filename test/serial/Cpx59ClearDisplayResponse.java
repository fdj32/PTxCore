package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.62/184} {59. Clear LCD Display}
 * 
 * @author nfeng
 *
 */
public class Cpx59ClearDisplayResponse extends CpxResponse {
	
	public Cpx59ClearDisplayResponse() {
		super();
		this.setMessageType("59.");
	}

	public static Cpx59ClearDisplayResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1) != str.length() || !str.startsWith("59.")) {
			return null;
		}
		Cpx59ClearDisplayResponse resp = new Cpx59ClearDisplayResponse();
		resp.setStatus(str.substring(3, 4));
		return resp;
	}

}
