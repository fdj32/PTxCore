package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0142-07942-0225 Telium CPX E2EE solution v2.25.pdf {Page.14/35} {3.4. E2EE
 * Activate MSR with Display handling}
 * 
 * @author nfeng
 * @see Cpx67ActivateMsrRequest
 */
public class Cpx6IE2EEActivateMSRRequest extends CpxRequest {

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
	 * 1 hex ASCII char "0" = no function keys active "1" = function key
	 * response
	 */
	private String functionKeysActive;
	/**
	 * 1 hex ASCII char Lines = # of lines to be displayed: "0" - "4" where 0 or
	 * 1 = 1 line when Line # field is not set to 5 Or Time Delay ="0" -"F" (1
	 * second increment, "0" =500ms) when line # field is set to 5
	 */
	private String LinesOrTimeDelay;
	/**
	 * 1 hex ASCII char starting line #: "0" - "4" where 0 or 1 = line 1 "5" -
	 * toggles specified prompts on line 1 with the rate of change specified in
	 * Time Delay field
	 */
	private String lineNumber;
	/**
	 * up to 17 char s Information to be display on line # - variable length
	 * string where FS or 0x1C terminates the line and causes " " padding to the
	 * end of line
	 */
	private String prompt1;
	/**
	 * up to 17 chars Optional - variable length string terminated by a FS
	 * (0x1C)
	 */
	private String prompt2;
	/**
	 * up to 17 chars Optional - variable length string terminated by a FS
	 * (0x1C)
	 */
	private String prompt3;
	/**
	 * up to 17 chars Optional - variable length string terminated by a FS
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

	public String getLinesOrTimeDelay() {
		return LinesOrTimeDelay;
	}

	public void setLinesOrTimeDelay(String linesOrTimeDelay) {
		LinesOrTimeDelay = linesOrTimeDelay;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
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

	public Cpx6IE2EEActivateMSRRequest() {
		super();
		this.setMessageType("6I.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getTrackNumber();
		msg += this.getTimeout();
		msg += this.getFunctionKeysActive();
		msg += this.getLinesOrTimeDelay();
		msg += this.getLineNumber();
		msg += this.getPrompt1() + UTFUtils.FS;
		msg += StringUtils.defaultString(this.getPrompt2()) + UTFUtils.FS;
		msg += StringUtils.defaultString(this.getPrompt3()) + UTFUtils.FS;
		msg += StringUtils.defaultString(this.getPrompt4()) + UTFUtils.FS;
		return msg;
	}
}
