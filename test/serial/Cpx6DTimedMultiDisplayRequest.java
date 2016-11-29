package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.133/184} {6D . Timed Multi
 * -Display with Masked Function Keys}
 * 
 * @author nfeng
 *
 */
public class Cpx6DTimedMultiDisplayRequest extends CpxRequest {

	/**
	 * 1 ASCII character "0" (currently hard coded)
	 */
	private String mode;
	/**
	 * 1 ASCII character "0" – "F" (1 second increments, "0" = 500ms
	 */
	private String timeDisplay;
	/**
	 * 1 ASCII character "0" – "F" – where: 0 = no function keys enabled. 1 = OK
	 * only 2 = CORR only 3 = OK & CORR only 4 = CANCEL o nly. 5 = OK & Cancel
	 * only. 6 = CORR & CANCEL only. 7 = OK & CORR & CANCEL only 8 = Fx Keys (F1
	 * to F9) only 9 = Fx Keys & OK only A = Fx Keys & CORR only B = Fx Keys &
	 * OK & CORR only C = Fx Keys & CANCEL only D = Fx Keys & OK & CANCEL only E
	 * = Fx Keys & CORR & CANCEL only F = All Fx Keys, OK, CORR, CANCEL.
	 */
	private String funcsKeysActive;
	/**
	 * 1 ASCII character "1" – "4"
	 */
	private String lineNumber;
	/**
	 * 16 characters Information to be displayed on line # - 64 character max.
	 */
	private String[] prompts;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getTimeDisplay() {
		return timeDisplay;
	}

	public void setTimeDisplay(String timeDisplay) {
		this.timeDisplay = timeDisplay;
	}

	public String getFuncsKeysActive() {
		return funcsKeysActive;
	}

	public void setFuncsKeysActive(String funcsKeysActive) {
		this.funcsKeysActive = funcsKeysActive;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String[] getPrompts() {
		return prompts;
	}

	public void setPrompts(String[] prompts) {
		this.prompts = prompts;
	}

	public Cpx6DTimedMultiDisplayRequest() {
		super();
		this.setMessageType("6D.");
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getMessageType());
		sb.append(this.getTimeDisplay());
		sb.append(this.getFuncsKeysActive());
		sb.append(this.getLineNumber());
		for (String prompt : prompts) {
			sb.append(prompt).append(UTFUtils.FS);
		}
		return sb.toString();
	}

}
