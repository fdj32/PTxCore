package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0142-07942-0225 Telium CPX E2EE solution v2.25.pdf {Page.19/35} {3.6. E2EE
 * Manual Card Data Entry}
 * 
 * @author nfeng
 * @see Cpx6IE2EEActivateMSRResponse E2EEData TODO : the document doesn't
 *      contains keyPressed
 */
public class Cpx6KE2EEManualCardEntryResponse extends CpxResponse {

	private String keyPressed;
	private E2EEData e;

	public String getKeyPressed() {
		return keyPressed;
	}

	public void setKeyPressed(String keyPressed) {
		this.keyPressed = keyPressed;
	}

	public E2EEData getE() {
		return e;
	}

	public void setE(E2EEData e) {
		this.e = e;
	}

	public Cpx6KE2EEManualCardEntryResponse() {
		super();
		this.setMessageType("6K.");
	}

	public static Cpx6KE2EEManualCardEntryResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1 + 1) > str.length() || !str.startsWith("6K.")) {
			return null;
		}
		Cpx6KE2EEManualCardEntryResponse resp = new Cpx6KE2EEManualCardEntryResponse();
		resp.setStatus(str.substring(3, 4));
		resp.setKeyPressed(str.substring(4, 5));
		resp.setE(E2EEData.parse(str.substring(5)));
		return resp;
	}

}
