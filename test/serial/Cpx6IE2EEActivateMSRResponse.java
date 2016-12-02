package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0142-07942-0225 Telium CPX E2EE solution v2.25.pdf {Page.14/35} {3.4. E2EE
 * Activate MSR with Display handling}
 * 
 * @author nfeng
 * @see E2EEData
 */
public class Cpx6IE2EEActivateMSRResponse extends CpxResponse {

	/**
	 * 3 ASCII chars decimal length of optional data fields i.e., all fields
	 * following this length. Not included when there is a track or other error.
	 */
	private String optionalDataLength;
	/**
	 * 1 ASCII hex char "0" – no key pressed – Not included when there is a
	 * track or other error; where key = A: F1 (Top left key) B: F2 B: F3 C: F4
	 * (Top right key) D: X (RED CANCEL key) E: < (YELLOW CORR. key) F: O (GREEN
	 * OK key)
	 */
	private String keyPressed;

	private E2EEData e;

	public E2EEData getE() {
		return e;
	}

	public void setE(E2EEData e) {
		this.e = e;
	}

	public String getOptionalDataLength() {
		return optionalDataLength;
	}

	public void setOptionalDataLength(String optionalDataLength) {
		this.optionalDataLength = optionalDataLength;
	}

	public String getKeyPressed() {
		return keyPressed;
	}

	public void setKeyPressed(String keyPressed) {
		this.keyPressed = keyPressed;
	}

	public Cpx6IE2EEActivateMSRResponse() {
		super();
		this.setMessageType("6I.");
	}

	public static Cpx6IE2EEActivateMSRResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1 + 3 + 1) > str.length() || !str.startsWith("6I.")) {
			return null;
		}
		Cpx6IE2EEActivateMSRResponse resp = new Cpx6IE2EEActivateMSRResponse();
		resp.setStatus(str.substring(3, 4));
		resp.setOptionalDataLength(str.substring(4, 7));
		resp.setKeyPressed(str.substring(7, 8));
		resp.setE(E2EEData.parse(str.substring(8)));
		return resp;
	}
}
