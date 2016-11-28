package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.60/184} {58. Display Handling Command}
 * 
 * @author nfeng
 *
 */
public class Cpx58DisplayMode27Response extends CpxResponse {
	/**
	 * 2 hex ASCII char Optional "01" – "20" length of inputted data Exists only
	 * when request command used the Maximum Input Length. The value is the
	 * length of inputed data.
	 */
	private String inputLength;
	/**
	 * 17 ASCII character Or Input Length + 1, User input, right justify with CR
	 * at the end
	 */
	private String buffer;

	public String getInputLength() {
		return inputLength;
	}

	public void setInputLength(String inputLength) {
		this.inputLength = inputLength;
	}

	public String getBuffer() {
		return buffer;
	}

	public void setBuffer(String buffer) {
		this.buffer = buffer;
	}

	public Cpx58DisplayMode27Response() {
		super();
		this.setMessageType("58.");
	}

	public static Cpx58DisplayMode27Response parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1 + 2 + 1) > str.length() || !str.startsWith("58.")) {
			return null;
		}
		Cpx58DisplayMode27Response resp = new Cpx58DisplayMode27Response();
		resp.setStatus(str.substring(3, 4));
		resp.setInputLength(str.substring(4, 6));
		// TODO: verify input length
		resp.setBuffer(str.substring(6));
		return resp;
	}
}
