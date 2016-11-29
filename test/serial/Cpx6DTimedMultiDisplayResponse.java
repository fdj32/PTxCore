package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.133/184} {6D . Timed Multi
 * -Display with Masked Function Keys}
 * 
 * @author nfeng
 *
 */
public class Cpx6DTimedMultiDisplayResponse extends CpxResponse {

	/**
	 * 1 ASCII hex character where key = "A" for Top left Screen key (4 -key
	 * keypad) F1 key (3 -key or 6 -key keypad) F4 key (6 -key keypad i6280) F5
	 * key (5 -key keypad) F8 key (5 -key keypad i3050 labeled "CHQ"). "B" for
	 * Bottom left Screen key (4 -key keypad) F2 key (3 -key or 6 -key keypad)
	 * F5 key (6 -key keypad i6280) F6 key (5 -key keypad) F9 key (5 -key keypad
	 * i3050 labeled "SAV/EP" if power -up prompt setting SAV=F2). "C" for Top
	 * right Screen key (4 -key keypad) F3 key (3 -key or 6 -key keypad) F6 key
	 * (6 -key keypad i6280) Alpha key F9 key (5 -key keypad i3050 labeled
	 * "SAV/EP" if power -up prompt setting SAV=F3) "G" for Bottom right Screen
	 * key (4 -key keypad) not available (3 -key or 6 -key keypad) "D" for
	 * CANCEL KEY "E" for CORR. KEY "F" for ENTER/OK KEY "H" for Bottom left key
	 * (i6510) "I" for third row bottom key (i6510)
	 */
	private String keyPressed;

	public String getKeyPressed() {
		return keyPressed;
	}

	public void setKeyPressed(String keyPressed) {
		this.keyPressed = keyPressed;
	}

	public Cpx6DTimedMultiDisplayResponse() {
		super();
		this.setMessageType("6D.");
	}

	public static Cpx6DTimedMultiDisplayResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1 + 1) != str.length() || !str.startsWith("6D.")) {
			return null;
		}
		Cpx6DTimedMultiDisplayResponse resp = new Cpx6DTimedMultiDisplayResponse();
		resp.setStatus(str.substring(3, 4));
		resp.setKeyPressed(str.substring(4, 5));
		return resp;

	}

}
