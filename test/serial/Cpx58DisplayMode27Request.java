package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.59/184} {58. Display Handling
 * Command}
 * 
 * @author nfeng
 *
 */
public class Cpx58DisplayMode27Request extends CpxRequest {

	/**
	 * Response Request flag, 1 hex ASCII character, "2" = Alphanumeric buffer
	 * (Use "F3" key to get letters)(Use "F6" key for i6280) "3" = numeric
	 * buffer "5" = password mode (Use "F3" key to get letters for devices using
	 * SSA 2.61 or higher). "6" = formatted amount entry "7" = password mode
	 * (numeric entry only)
	 */
	private String mode;
	/**
	 * "0" - "3" 0 or 1=line 1; this value plus 1 is the input line. "5" display
	 * at line 1, input line is 3. "6" display at line 2, input line is 4
	 */
	private String lineStartIndex;
	/**
	 * 1 hex ASCII char "0" - "F", this also controls the maximum digits allowed
	 * When the Maximum Input Length is used the Start Position of input is
	 * always at the right end of the input line (i.e. this field is ignored for
	 * this case but data is still checked for validity).
	 */
	private String startPosition;
	/**
	 * 32 characters Information to be displayed on line #.
	 */
	private String prompt;
	/**
	 * 2 hex ASCII char "00" - "3F" For default use F
	 */
	private String promptIndex;
	/**
	 * 2 hex ACSII char Optional "01" – "20", (for mode "6" the range is "01" -
	 * "1E"). Maximum length of data input. If Input Length is bigger than 16,
	 * input Line # = input line # +1 (U16 only). The maximum input line # is 4.
	 */
	private String maxInputLength;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getLineStartIndex() {
		return lineStartIndex;
	}

	public void setLineStartIndex(String lineStartIndex) {
		this.lineStartIndex = lineStartIndex;
	}

	public String getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(String startPosition) {
		this.startPosition = startPosition;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getPromptIndex() {
		return promptIndex;
	}

	public void setPromptIndex(String promptIndex) {
		this.promptIndex = promptIndex;
	}

	public String getMaxInputLength() {
		return maxInputLength;
	}

	public void setMaxInputLength(String maxInputLength) {
		this.maxInputLength = maxInputLength;
	}

	public Cpx58DisplayMode27Request() {
		super();
		this.setMessageType("58.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getMode();
		msg += this.getLineStartIndex();
		msg += this.getStartPosition();
		msg += StringUtils.defaultString(this.getPrompt());
		msg += this.getPromptIndex();
		msg += this.getMaxInputLength();
		return msg;
	}

}
