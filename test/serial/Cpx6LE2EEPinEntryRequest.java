package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0142-07942-0225 Telium CPX E2EE solution v2.25.pdf {Page.21/35} {3.7. E2EE
 * PIN Entry}
 * 
 * @author nfeng
 * 
 */
public class Cpx6LE2EEPinEntryRequest extends CpxRequest {
	/**
	 * 1 ASCII char "0" - no time -out "1" to "F" - 5 to 75 seconds, lowercase
	 * 'a' -'f' is treated as the corresponding uppercase letter. Note: Some
	 * Telium SDKs only allow a maximum interdigit timeout of 60 seconds.
	 */
	private String pinEntryTimeout;
	/**
	 * 1 ASCII char "0" - "4"
	 */
	private String pinEntryLineNumber;
	/**
	 * 1 ASCII char "S" – Session key "D " – DUKPT key
	 */
	private String keyType;
	/**
	 * 1 ASCII char "0" -"9" for Telium key pattern 1 "0" – "o" for Telium key
	 * pattern 2 "0" -"5" for Telium key pattern 4
	 */
	private String pinKeySlotIndicator;
	/**
	 * 1 ASCII chars "0" = clear ASCII data "1" = encrypted data
	 */
	private String panEncryptedFlag;
	/**
	 * 2 hex ASCII chars decimal value of the clear text PAN
	 */
	private String clearPanLength;
	/**
	 * 32 hex ASCII chars If the PAN encrypt ed flag indicates encrypted then
	 * this field is the AES encrypted PAN
	 */
	private String pan;
	/**
	 * 1 hex ASCII char # of lines included: "0" - "3" where 0 or 1 = 1 line
	 */
	private String lines;
	/**
	 * 1 hex ASCII char starting line #: "0" - "4" where 0 or 1 = line 1 where
	 * line + # of lines canno t conflict with PIN entry line number. note: at
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
	 * (0x1C )
	 */
	private String prompt3;
	/**
	 * 1 ASCII char Optional field indicating PBM is enabled "Null = PBM
	 * disabled " , "0" = PBM disabled , "1" = PBM enabled
	 */
	private String pinBypassMode;

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

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getPinKeySlotIndicator() {
		return pinKeySlotIndicator;
	}

	public void setPinKeySlotIndicator(String pinKeySlotIndicator) {
		this.pinKeySlotIndicator = pinKeySlotIndicator;
	}

	public String getPanEncryptedFlag() {
		return panEncryptedFlag;
	}

	public void setPanEncryptedFlag(String panEncryptedFlag) {
		this.panEncryptedFlag = panEncryptedFlag;
	}

	public String getClearPanLength() {
		return clearPanLength;
	}

	public void setClearPanLength(String clearPanLength) {
		this.clearPanLength = clearPanLength;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
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

	public String getPinBypassMode() {
		return pinBypassMode;
	}

	public void setPinBypassMode(String pinBypassMode) {
		this.pinBypassMode = pinBypassMode;
	}

	public Cpx6LE2EEPinEntryRequest() {
		super();
		this.setMessageType("6L.");
	}

	public String toString() {
		String msg = this.getMessageType();
		msg += this.getPinEntryTimeout();
		msg += this.getPinEntryLineNumber();
		msg += this.getKeyType();
		msg += this.getPinKeySlotIndicator();
		msg += this.getPanEncryptedFlag();
		msg += this.getClearPanLength();
		msg += this.getPan();
		msg += this.getLines();
		msg += this.getPromptLineNumber();
		msg += this.getPromptIndex();
		msg += StringUtils.defaultString(this.getPrompt1()) + UTFUtils.FS;
		msg += StringUtils.defaultString(this.getPrompt2()) + UTFUtils.FS;
		msg += StringUtils.defaultString(this.getPrompt3()) + UTFUtils.FS;
		msg += this.getPinBypassMode();
		return msg;
	}

}
