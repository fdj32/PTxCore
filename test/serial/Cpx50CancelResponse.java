package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.53/184} {50. Cancel}
 * 
 * @author nfeng
 *
 */
public class Cpx50CancelResponse extends CpxResponse {

	public Cpx50CancelResponse() {
		super();
		this.setMessageType("50.");
	}

	public static Cpx50CancelResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1) != str.length() || !str.startsWith("50.")) {
			return null;
		}
		Cpx50CancelResponse resp = new Cpx50CancelResponse();
		resp.setStatus(str.substring(3, 4));
		return resp;
	}

}
