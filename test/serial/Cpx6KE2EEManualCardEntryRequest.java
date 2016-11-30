package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0142-07942-0225 Telium CPX E2EE solution v2.25.pdf {Page.19/35} {3.6. E2EE
 * Manual Card Data Entry}
 * 
 * @author nfeng
 * 
 */
public class Cpx6KE2EEManualCardEntryRequest extends CpxRequest {

	/**
	 * 1 hex ASCII char "0" - "3" 0 or 1=line 1; this value plus 1 is the input
	 * line. "5" display at line 1, input line is 3. "6" display at line 2,
	 * input line is 4
	 */
	private String lineNumber;
	/**
	 * 32 characters PAN prompt to be displayed on line #.
	 */
	private String prompt1;
	/**
	 * 2 hex ASCII char "00" - "3F"
	 */
	private String prompt1index;
	/**
	 * 32 characters Expiry Date prompt to be displayed on line #.
	 */
	private String prompt2;
	/**
	 * 2 hex ASCII char "00" - "3F"
	 */
	private String prompt2index;

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

	public String getPrompt1index() {
		return prompt1index;
	}

	public void setPrompt1index(String prompt1index) {
		this.prompt1index = prompt1index;
	}

	public String getPrompt2() {
		return prompt2;
	}

	public void setPrompt2(String prompt2) {
		this.prompt2 = prompt2;
	}

	public String getPrompt2index() {
		return prompt2index;
	}

	public void setPrompt2index(String prompt2index) {
		this.prompt2index = prompt2index;
	}

	public Cpx6KE2EEManualCardEntryRequest() {
		super();
		this.setMessageType("6K.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getLineNumber();
		msg += StringUtils.rightPad(this.getPrompt1(), 32, ' ');
		msg += this.getPrompt1index();
		msg += StringUtils.rightPad(this.getPrompt2(), 32, ' ');
		msg += this.getPrompt2index();
		return msg;
	}

}
