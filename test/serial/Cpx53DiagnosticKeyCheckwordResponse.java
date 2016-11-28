package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.55/184} {53. Diagnostic Key
 * Checkword Request}
 * 
 * @author nfeng
 *
 */
public class Cpx53DiagnosticKeyCheckwordResponse extends CpxResponse {

	/**
	 * 8 hex ASCII characters
	 */
	private String checkValue;

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	public Cpx53DiagnosticKeyCheckwordResponse() {
		super();
		this.setMessageType("53.");
	}
	
	public static Cpx53DiagnosticKeyCheckwordResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1 + 8) != str.length() || !str.startsWith("53.")) {
			return null;
		}
		Cpx53DiagnosticKeyCheckwordResponse resp = new Cpx53DiagnosticKeyCheckwordResponse();
		resp.setStatus(str.substring(3, 4));
		resp.setCheckValue(str.substring(4, 12));
		return resp;
	}

}
