package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.59/184} {58. Display Handling Comman}
 * 
 * @author nfeng
 *
 */
public class Cpx58DisplayMode01Request extends CpxRequest {

	/**
	 * Response Request flag, 1 hex ASCII character, "0" = no response, "1" = function key response
	 */
	private String mode;
	/**
	 * 1 ASCII character, "0" = no toggle supported(see note below on toggling logic). 
	 */
	private String toggle;
	/**
	 * 1 hex ASCII char # of lines to be displayed: "0" - "4" where 0 or 1 = 1 line	 */
	private String lines;
	/**
	 * 1 hex ASCII char starting line #: "0" - "4" where 0 or 1 = line */
	private String lineStartIndex;
	/**
	 * 16 characters, Information to be display on line #
	 */
	private String prompt1;
	/**
	 * 16 characters Optional 
	 */
	private String prompt2;
	/**
	 * 16 characters Optional 
	 */
	private String prompt3;
	/**
	 * 16 characters Optional 
	 */
	private String prompt4;
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getToggle() {
		return toggle;
	}
	public void setToggle(String toggle) {
		this.toggle = toggle;
	}
	public String getLines() {
		return lines;
	}
	public void setLines(String lines) {
		this.lines = lines;
	}
	public String getLineStartIndex() {
		return lineStartIndex;
	}
	public void setLineStartIndex(String lineStartIndex) {
		this.lineStartIndex = lineStartIndex;
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
	public Cpx58DisplayMode01Request() {
		super();
		this.setMessageType("58.");
	}
	
}
