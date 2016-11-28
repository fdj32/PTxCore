package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.63/184} {5B. Beep}
 * 
 * @author nfeng
 *
 */
public class Cpx5BBeepRequest extends CpxRequest {
	/**
	 * 1 ASCII character, "0" - "F"
	 */
	private String beepLength;
	/**
	 * 1 Hex ASCII character, Optional field - "0"-"F", higher number has higher
	 * pitch than lower number.
	 */
	private String beepFrequency;

	public String getBeepLength() {
		return beepLength;
	}

	public void setBeepLength(String beepLength) {
		this.beepLength = beepLength;
	}

	public String getBeepFrequency() {
		return beepFrequency;
	}

	public void setBeepFrequency(String beepFrequency) {
		this.beepFrequency = beepFrequency;
	}

	public Cpx5BBeepRequest() {
		super();
		this.setMessageType("5B.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getBeepLength();
		msg += this.getBeepFrequency();
		return msg;
	}
}
