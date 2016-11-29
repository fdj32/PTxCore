package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.139/184} {6H . Master/Session
 * PIN Data Entry with Display and Pin Entry Control}
 * 
 * @author nfeng
 *
 */
public class Cpx6HMasterSessionPinDataEntryRequest extends CpxRequest {

	/**
	 * "0" = no time -out "1" to "F" - 5 to 75 seconds, lowercase 'a' -'f' is
	 * treated as the corresponding uppercase letter.
	 */
	private String pinEntryTimeout;
	/**
	 * 1 ASCII character "0" - "4"
	 */
	private String pinEntryLineNumber;
	/**
	 * 1 ASCII character "4" – "C" hexadecimal
	 */
	private String pinEntryMinimum;
	/**
	 * 1 ASCII character Pin entry minimum value up to "C" – must not be less
	 * than Pin Entry minimum, otherwise an invalid command failure will occur.
	 */
	private String pinEntryMaximum;
	/**
	 * 1 ASCII character Application -Based:"0" - "2" – field is ignored (see
	 * note below).
	 */
	private String masterKeyIndicator;
	/**
	 * 16 hex ASCII characters
	 */
	private String primaryAccountNumber;
	/**
	 * 1 ASCII character "S" – single length session key included
	 */
	private String sessionKeyLength;
	/**
	 * 16 hex ASCII characters (If the SK is stored this value will be ignored
	 * although the length is checked)
	 */
	private String encrytedSessionKey;

	/**
	 * Double Length: 8 hex ASCII characters check value to be verified before
	 * the key is used
	 */
	private String checkValue;
	/**
	 * Single Length: 4 ASCII characters Field is ignored .. Double Length:
	 * Field ignored for U32 devices if a predefined pin entry prompt has
	 * already been displayed (i.e. via a "58.0"
	 */
	private String pinDisplayPrompt;
	/**
	 * 1 hex ASCII char # of lines included: "0" - "3" where 0 or 1 = 1 line
	 */
	private String lines;
	/**
	 * 1 hex ASCII char starting line #: "0" - "4" where 0 or 1 = line 1 where
	 * line + # of lines cannot conflict with PIN entry line number. note: at
	 * least one line must be reserved for data entry
	 */
	private String promptLineNumber;
	/**
	 * 2 hex ASCII char "00" - "3F" or use "FF" for default.
	 */
	private String promptIndex;
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

	public String getPinEntryTimeout() {
		return pinEntryTimeout;
	}

	public void setPinEntryTimeout(String pinEntryTimeout) {
		this.pinEntryTimeout = pinEntryTimeout;
	}

	public String getPinEntryLineNumber() {
		return pinEntryLineNumber;
	}

	public void setPinEntryLineNumber(String pinEntryLineNumber) {
		this.pinEntryLineNumber = pinEntryLineNumber;
	}

	public String getPinEntryMinimum() {
		return pinEntryMinimum;
	}

	public void setPinEntryMinimum(String pinEntryMinimum) {
		this.pinEntryMinimum = pinEntryMinimum;
	}

	public String getPinEntryMaximum() {
		return pinEntryMaximum;
	}

	public void setPinEntryMaximum(String pinEntryMaximum) {
		this.pinEntryMaximum = pinEntryMaximum;
	}

	public String getMasterKeyIndicator() {
		return masterKeyIndicator;
	}

	public void setMasterKeyIndicator(String masterKeyIndicator) {
		this.masterKeyIndicator = masterKeyIndicator;
	}

	public String getPrimaryAccountNumber() {
		return primaryAccountNumber;
	}

	public void setPrimaryAccountNumber(String primaryAccountNumber) {
		this.primaryAccountNumber = primaryAccountNumber;
	}

	public String getSessionKeyLength() {
		return sessionKeyLength;
	}

	public void setSessionKeyLength(String sessionKeyLength) {
		this.sessionKeyLength = sessionKeyLength;
	}

	public String getEncrytedSessionKey() {
		return encrytedSessionKey;
	}

	public void setEncrytedSessionKey(String encrytedSessionKey) {
		this.encrytedSessionKey = encrytedSessionKey;
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	public String getPinDisplayPrompt() {
		return pinDisplayPrompt;
	}

	public void setPinDisplayPrompt(String pinDisplayPrompt) {
		this.pinDisplayPrompt = pinDisplayPrompt;
	}

	public String getLines() {
		return lines;
	}

	public void setLines(String lines) {
		this.lines = lines;
	}

	public String getPromptLineNumber() {
		return promptLineNumber;
	}

	public void setPromptLineNumber(String promptLineNumber) {
		this.promptLineNumber = promptLineNumber;
	}

	public String getPromptIndex() {
		return promptIndex;
	}

	public void setPromptIndex(String promptIndex) {
		this.promptIndex = promptIndex;
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

	public Cpx6HMasterSessionPinDataEntryRequest() {
		super();
		this.setMessageType("6H.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		int timeout = Integer.parseInt(this.getPinEntryTimeout());
		if (timeout > 75) {
			timeout = 75;
		}
		msg += String.format("%x", (int) (timeout / 5)).toUpperCase();
		msg += this.getPinEntryLineNumber(); // 3
		msg += this.getPinEntryMinimum(); // 4
		msg += this.getPinEntryMaximum(); // C
		msg += this.getMasterKeyIndicator(); // 0
		String pan = this.getPrimaryAccountNumber();
		if (pan.length() >= 12) {
			pan = StringUtils
					.leftPad(pan.substring(pan.length() - 12), 16, '0');
		} else {
			pan = StringUtils.leftPad(pan, 16, '0');
		}
		msg += pan;
		msg += this.getSessionKeyLength(); // 0
		msg += StringUtils.defaultString(this.getEncrytedSessionKey());
		msg += StringUtils.defaultString(this.getCheckValue());
		msg += this.getPinDisplayPrompt(); // "    "
		msg += this.getLines(); // 1
		msg += this.getPromptLineNumber(); // 2
		msg += this.getPromptIndex(); // FF
		msg += this.getPrompt1() + UTFUtils.FS;
		if (StringUtils.isNotEmpty(this.getPrompt2())) {
			msg += this.getPrompt2() + UTFUtils.FS;
		}
		if (StringUtils.isNotEmpty(this.getPrompt3())) {
			msg += this.getPrompt3() + UTFUtils.FS;
		}
		return msg;
	}
}
