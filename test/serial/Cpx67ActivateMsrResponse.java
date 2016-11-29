package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.113/184} {67. Activate MSR with
 * Display Handling }
 * 
 * @author nfeng
 *
 */
public class Cpx67ActivateMsrResponse extends CpxResponse {

	/**
	 * 1 ASCII hex character "0" – key not pressed - Optional where key = "A"
	 * for Top left Screen key (4 -key keypad) F1 key (3 -key or 6 -key keypad)
	 * F4 key (6 -key keypad i6280) F5 key (5 -key keypad) "B" for Botto m left
	 * Screen key (4 -key keypad) F2 key (3 -key or 6 -key keypad) F5 key (6
	 * -key keypad i6280) F6 key (5 -key keypad) "C" for Top right Screen key (4
	 * -key keypad) F3 key (3 -key or 6 -key keypad) F6 key (6 -key keypad
	 * i6280) "G" for Bottom right Screen key ( 4-key keypad ) not available (
	 * 3-key or 6 -key keypad "D" for CANCEL KEY "E" for CORR. KEY "F" for
	 * ENTER/OK KEY "H" for Bottom left key (i6510) "I" for third row bottom key
	 * (i6510)
	 */
	private String keyPressed;
	/**
	 * 2 ASCII chars decimal length of ISO 2 field
	 */
	private String track2length;
	/**
	 * n ASCII chars ISO2 track if reading OK
	 */
	private String iso2field;
	/**
	 * 2 ASCII chars decimal length of ISO 1 field
	 */
	private String track1length;
	/**
	 * n ASCII chars ISO1 track if reading OK
	 */
	private String iso1field;

	public String getKeyPressed() {
		return keyPressed;
	}

	public void setKeyPressed(String keyPressed) {
		this.keyPressed = keyPressed;
	}

	public String getTrack2length() {
		return track2length;
	}

	public void setTrack2length(String track2length) {
		this.track2length = track2length;
	}

	public String getIso2field() {
		return iso2field;
	}

	public void setIso2field(String iso2field) {
		this.iso2field = iso2field;
	}

	public String getTrack1length() {
		return track1length;
	}

	public void setTrack1length(String track1length) {
		this.track1length = track1length;
	}

	public String getIso1field() {
		return iso1field;
	}

	public void setIso1field(String iso1field) {
		this.iso1field = iso1field;
	}
	
	public Cpx67ActivateMsrResponse() {
		super();
		this.setMessageType("67.");
	}
	
	public static Cpx67ActivateMsrResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1 + 1) > str.length() || !str.startsWith("67.")) {
			return null;
		}
		Cpx67ActivateMsrResponse resp = new Cpx67ActivateMsrResponse();
		resp.setStatus(str.substring(3, 4));
		if((3+1+1) == str.length()) {
			resp.setKeyPressed(str.substring(4, 5));
		} else {
			resp.setTrack2length(str.substring(4, 6));
			int t2len = Integer.parseInt(str.substring(4,  6));
			resp.setIso2field(str.substring(6,  6+t2len));
			resp.setTrack2length(str.substring(6+t2len, 6+t2len+2));
			int t1len = Integer.parseInt(str.substring(6+t2len, 6+t2len+2));
			resp.setIso1field(str.substring(6+t2len+2, 6+t2len+2+t1len));
		}
		return resp;
	}
}
