package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.62/184} {59. Clear LCD Display}
 * 
 * @author nfeng
 *
 */
public class Cpx59ClearDisplayRequest extends CpxRequest {

	/**
	 * 1 ASCII character, "0" - "4"
	 */
	private String lineNumber;

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Cpx59ClearDisplayRequest() {
		super();
		this.setMessageType("59.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getLineNumber();
		return msg;
	}

}
