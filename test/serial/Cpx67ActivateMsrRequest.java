package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.113/184} {67. Activate MSR with
 * Display Handling }
 * 
 * @author nfeng
 *
 */
public class Cpx67ActivateMsrRequest extends CpxRequest {

	/**
	 * 1 ASCII char "1" ISO1 magnetic card reading "2" ISO2 magnetic card
	 * reading "3" ISO1 + ISO2 magnetic card reading
	 */
	private String trackNumber;
	/**
	 * 2 ASCII chars timeout in seconds – "00" = no timeout
	 */
	private String timeout;
	/**
	 * 1 hex ASCII character "0" = no function keys active "1" = function key
	 * response
	 */
	private String functionKeysActive;
	/**
	 * 1 hex ASCII char # of lines to be displayed: "0" - "4" where 0 or 1 = 1
	 * line
	 */
	private String lines;
	/**
	 * 1 hex ASCII char starting line #: "0" - "4" where 0 or 1 = line 1
	 */
	private String startLineIndex;
	/**
	 * up to 17 characters Information to be display on line # - variable length
	 * string where FS or 0x1C terminates the line and causes " " padding to the
	 * end of line
	 */
	private String prompt1;
	/**
	 * up to 17 characters Optional - variable length string terminated by a FS
	 * (0x1C)
	 */
	private String prompt2;
	/**
	 * up to 17 characters Optional - variable length string terminated by a FS
	 * (0x1C)
	 */
	private String prompt3;
	/**
	 * up to 17 characters Optional - variable length string terminated by a FS
	 * (0x1C)
	 */
	private String prompt4;

	public String getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getFunctionKeysActive() {
		return functionKeysActive;
	}

	public void setFunctionKeysActive(String functionKeysActive) {
		this.functionKeysActive = functionKeysActive;
	}

	public String getLines() {
		return lines;
	}

	public void setLines(String lines) {
		this.lines = lines;
	}

	public String getStartLineIndex() {
		return startLineIndex;
	}

	public void setStartLineIndex(String startLineIndex) {
		this.startLineIndex = startLineIndex;
	}

	public String getPrompt1() {
		return prompt1;
	}

	public void setPrompt1(String prompt1) {
		this.prompt1 = prompt1;
	}

	public String getPrompt2() {
		return prompt2;
	}

	public void setPrompt2(String prompt2) {
		this.prompt2 = prompt2;
	}

	public String getPrompt3() {
		return prompt3;
	}

	public void setPrompt3(String prompt3) {
		this.prompt3 = prompt3;
	}

	public String getPrompt4() {
		return prompt4;
	}

	public void setPrompt4(String prompt4) {
		this.prompt4 = prompt4;
	}

	public Cpx67ActivateMsrRequest() {
		super();
		this.setMessageType("67.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getTrackNumber();
		msg += this.getTimeout();
		msg += this.getFunctionKeysActive();
		msg += this.getLines();
		msg += this.getStartLineIndex();
		msg += this.getPrompt1();
		msg += this.getPrompt2();
		msg += this.getPrompt3();
		msg += this.getPrompt4();
		return msg;
	}
}
