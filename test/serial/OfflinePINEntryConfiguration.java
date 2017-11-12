package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Hex;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 0142-07204-0503 Generic EMV API.pdf Page 28/167
 * 
 * @author nickfeng
 *
 */
public class OfflinePINEntryConfiguration {

	/**
	 * length=1 Text font used for PIN Entry.
	 * <ul>
	 * <li>1 = SSA_FONT_1 = Ingenico Font 1, fixed font 8 x 16</li>
	 * <li>2 = SSA_FONT_2 = Ingenico Font 2, fixed bold font 8 x16</li>
	 * <li>3 = SSA_FONT_3 = Ingenico Font 3, variable font 8 x 10</li>
	 * <li>4 =SSA_FONT_4 = Ingenico Font 4, variable font 8 x 16</li>
	 * <li>5 = SSA_FONT_5 = Ingenico Font 5, fixed font 6 x 8</li>
	 * </ul>
	 */
	private byte textFont;
	/**
	 * length=4*250 PIN entry prompt. One for each of the maximum 4 languages to be
	 * supported, in the order as the languages specified in TerminalLanguages
	 * field. Each prompt is 250 characters long.
	 */
	private byte[] prompt;
	/**
	 * length=4*9, Not used in Telium VEGA.
	 */
	private byte[] promptMAC;
	/**
	 * length=4, Prompt box x corner, i.e. which column
	 */
	private byte[] promptX;
	/**
	 * length=4, Prompt box y corner, i.e which line
	 */
	private byte[] promptY;
	/**
	 * length=4, Edit box x corner, i.e. which column for PIN entry
	 */
	private byte[] editX;
	/**
	 * length=4, Edit box y corner, i.e. which line for PIN entry
	 */
	private byte[] editY;
	/**
	 * length=1, Not used in Telium VEGA.
	 */
	private byte formatType;
	/**
	 * length=9, Not used in Telium VEGA.
	 */
	private byte[] formatSpMAC;
	/**
	 * length=50, Not used in Telium VEGA.
	 */
	private byte[] formatSpecifier;
	/**
	 * length=1, Minimum number of digits for the PIN. Minimum is 4.
	 */
	private byte minimumKeys;
	/**
	 * length=1, Maximum number of digits for the PIN. Must be more than the
	 * minimumKeys and <= 12.
	 */
	private byte maximumKeys;
	/**
	 * length=1, Character to be echoed when formatType is either 1, 2 or 3.
	 */
	private byte echoCharacter;
	/**
	 * length=1, Not used in Telium VEGA.
	 */
	private byte cursorType;
	/**
	 * length=1, Direction of display of PIN entry
	 * <ul>
	 * <li>0 = DIR_LEFT_TO_RIGHT = from left to right</li>
	 * <li>1 = DIR_RIGHT_TO_LEFT = from right to left</li>
	 * </ul>
	 */
	private byte direction;
	/**
	 * length=4, Not used in Telium VEGA.
	 */
	private byte[] beepInvalidKey;
	/**
	 * length=4, Timeout of first key entry in 10ms. 0 means wait forever.
	 */
	private byte[] timeOutFirstKey;
	/**
	 * length=4, Timeout of inter key entry in 10 ms. 0 means wait forever.
	 */
	private byte[] timeOutInterKey;
	/**
	 * length=1, Not used in Telium VEGA.
	 */
	private byte keyType;
	/**
	 * length=1, Not used in Telium VEGA.
	 */
	private byte keyIndex;
	/**
	 * length=4, Not used in Telium VEGA.
	 */
	private byte[] noEnterLessMin;
	/**
	 * length=2, Not used in Telium VEGA.
	 */
	private byte[] addReqSettings;

	public byte getTextFont() {
		return textFont;
	}

	public void setTextFont(byte textFont) {
		this.textFont = textFont;
	}

	public byte[] getPrompt() {
		return prompt;
	}

	public void setPrompt(byte[] prompt) {
		this.prompt = prompt;
	}

	public byte[] getPromptMAC() {
		return promptMAC;
	}

	public void setPromptMAC(byte[] promptMAC) {
		this.promptMAC = promptMAC;
	}

	public byte[] getPromptX() {
		return promptX;
	}

	public void setPromptX(byte[] promptX) {
		this.promptX = promptX;
	}

	public byte[] getPromptY() {
		return promptY;
	}

	public void setPromptY(byte[] promptY) {
		this.promptY = promptY;
	}

	public byte[] getEditX() {
		return editX;
	}

	public void setEditX(byte[] editX) {
		this.editX = editX;
	}

	public byte[] getEditY() {
		return editY;
	}

	public void setEditY(byte[] editY) {
		this.editY = editY;
	}

	public byte getFormatType() {
		return formatType;
	}

	public void setFormatType(byte formatType) {
		this.formatType = formatType;
	}

	public byte[] getFormatSpMAC() {
		return formatSpMAC;
	}

	public void setFormatSpMAC(byte[] formatSpMAC) {
		this.formatSpMAC = formatSpMAC;
	}

	public byte[] getFormatSpecifier() {
		return formatSpecifier;
	}

	public void setFormatSpecifier(byte[] formatSpecifier) {
		this.formatSpecifier = formatSpecifier;
	}

	public byte getMinimumKeys() {
		return minimumKeys;
	}

	public void setMinimumKeys(byte minimumKeys) {
		this.minimumKeys = minimumKeys;
	}

	public byte getMaximumKeys() {
		return maximumKeys;
	}

	public void setMaximumKeys(byte maximumKeys) {
		this.maximumKeys = maximumKeys;
	}

	public byte getEchoCharacter() {
		return echoCharacter;
	}

	public void setEchoCharacter(byte echoCharacter) {
		this.echoCharacter = echoCharacter;
	}

	public byte getCursorType() {
		return cursorType;
	}

	public void setCursorType(byte cursorType) {
		this.cursorType = cursorType;
	}

	public byte getDirection() {
		return direction;
	}

	public void setDirection(byte direction) {
		this.direction = direction;
	}

	public byte[] getBeepInvalidKey() {
		return beepInvalidKey;
	}

	public void setBeepInvalidKey(byte[] beepInvalidKey) {
		this.beepInvalidKey = beepInvalidKey;
	}

	public byte[] getTimeOutFirstKey() {
		return timeOutFirstKey;
	}

	public void setTimeOutFirstKey(byte[] timeOutFirstKey) {
		this.timeOutFirstKey = timeOutFirstKey;
	}

	public byte[] getTimeOutInterKey() {
		return timeOutInterKey;
	}

	public void setTimeOutInterKey(byte[] timeOutInterKey) {
		this.timeOutInterKey = timeOutInterKey;
	}

	public byte getKeyType() {
		return keyType;
	}

	public void setKeyType(byte keyType) {
		this.keyType = keyType;
	}

	public byte getKeyIndex() {
		return keyIndex;
	}

	public void setKeyIndex(byte keyIndex) {
		this.keyIndex = keyIndex;
	}

	public byte[] getNoEnterLessMin() {
		return noEnterLessMin;
	}

	public void setNoEnterLessMin(byte[] noEnterLessMin) {
		this.noEnterLessMin = noEnterLessMin;
	}

	public byte[] getAddReqSettings() {
		return addReqSettings;
	}

	public void setAddReqSettings(byte[] addReqSettings) {
		this.addReqSettings = addReqSettings;
	}
	
	public Element element() {
		Element r = DocumentHelper.createElement("offlinePINEntryConfiguration");
		r.addElement("textFont").addText(Hex.encodeHexString(new byte[] {textFont}));
		r.addElement("prompt").addText(Hex.encodeHexString(prompt));
		r.addElement("promptMAC").addText(Hex.encodeHexString(promptMAC));
		r.addElement("promptX").addText(Hex.encodeHexString(promptX));
		r.addElement("promptY").addText(Hex.encodeHexString(promptY));
		r.addElement("editX").addText(Hex.encodeHexString(editX));
		r.addElement("editY").addText(Hex.encodeHexString(editY));
		r.addElement("formatType").addText(Hex.encodeHexString(new byte[] {formatType}));
		r.addElement("formatSpMAC").addText(Hex.encodeHexString(formatSpMAC));
		r.addElement("formatSpecifier").addText(Hex.encodeHexString(formatSpecifier));
		r.addElement("minimumKeys").addText(Hex.encodeHexString(new byte[] {minimumKeys}));
		r.addElement("maximumKeys").addText(Hex.encodeHexString(new byte[] {maximumKeys}));
		r.addElement("echoCharacter").addText(Hex.encodeHexString(new byte[] {echoCharacter}));
		r.addElement("cursorType").addText(Hex.encodeHexString(new byte[] {cursorType}));
		r.addElement("direction").addText(Hex.encodeHexString(new byte[] {direction}));
		r.addElement("beepInvalidKey").addText(Hex.encodeHexString(beepInvalidKey));
		r.addElement("timeOutFirstKey").addText(Hex.encodeHexString(timeOutFirstKey));
		r.addElement("timeOutInterKey").addText(Hex.encodeHexString(timeOutInterKey));
		r.addElement("keyType").addText(Hex.encodeHexString(new byte[] {keyType}));
		r.addElement("keyIndex").addText(Hex.encodeHexString(new byte[] {keyIndex}));
		r.addElement("noEnterLessMin").addText(Hex.encodeHexString(noEnterLessMin));
		r.addElement("addReqSettings").addText(Hex.encodeHexString(addReqSettings));
		return r;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public byte[] toBinary() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(textFont);
		baos.write(prompt);
		baos.write(promptMAC);
		baos.write(promptX);
		baos.write(promptY);
		baos.write(editX);
		baos.write(editY);
		baos.write(formatType);
		baos.write(formatSpMAC);
		baos.write(formatSpecifier);
		baos.write(minimumKeys);
		baos.write(maximumKeys);
		baos.write(echoCharacter);
		baos.write(cursorType);
		baos.write(direction);
		baos.write(beepInvalidKey);
		baos.write(timeOutFirstKey);
		baos.write(timeOutInterKey);
		baos.write(keyType);
		baos.write(keyIndex);
		baos.write(noEnterLessMin);
		baos.write(addReqSettings);
		byte[] data = baos.toByteArray();
		baos.close();
		return data;
	}
	
	public static OfflinePINEntryConfiguration fromBinary(byte[] bin) {
		OfflinePINEntryConfiguration o = new OfflinePINEntryConfiguration();
		o.setTextFont(bin[0]);
		
		byte[] prompt = new byte[1000];
		System.arraycopy(bin, 1, prompt, 0, 1000);
		o.setPrompt(prompt);
		
		byte[] promptMAC = new byte[36];
		System.arraycopy(bin, 1001, promptMAC, 0, 36);
		o.setPromptMAC(promptMAC);
		
		byte[] promptX = new byte[4];
		System.arraycopy(bin, 1037, promptX, 0, 4);
		o.setPromptX(promptX);
		
		byte[] promptY = new byte[4];
		System.arraycopy(bin, 1041, promptY, 0, 4);
		o.setPromptY(promptY);
		
		byte[] editX = new byte[4];
		System.arraycopy(bin, 1045, editX, 0, 4);
		o.setEditX(editX);
		
		byte[] editY = new byte[4];
		System.arraycopy(bin, 1049, editY, 0, 4);
		o.setEditY(editY);
		
		o.setFormatType(bin[1053]);
		
		byte[] formatSpMac = new byte[9];
		System.arraycopy(bin, 1054, formatSpMac, 0, 9);
		o.setFormatSpMAC(formatSpMac);
		
		byte[] formatSpecifier = new byte[50];
		System.arraycopy(bin, 1063, formatSpecifier, 0, 50);
		o.setFormatSpecifier(formatSpecifier);
		
		o.setMinimumKeys(bin[1113]);
		o.setMaximumKeys(bin[1114]);
		o.setEchoCharacter(bin[1115]);
		o.setCursorType(bin[1116]);
		o.setDirection(bin[1117]);
		
		byte[] beepAtInvalidKey = new byte[4];
		System.arraycopy(bin, 1118, beepAtInvalidKey, 0, 4);
		o.setBeepInvalidKey(beepAtInvalidKey);
		
		byte[] timeOutFirstKey = new byte[4];
		System.arraycopy(bin, 1122, timeOutFirstKey, 0, 4);
		o.setTimeOutFirstKey(timeOutFirstKey);
		
		byte[] timeOutInterKey = new byte[4];
		System.arraycopy(bin, 1126, timeOutInterKey, 0, 4);
		o.setTimeOutInterKey(timeOutInterKey);
		
		o.setKeyType(bin[1130]);
		o.setKeyIndex(bin[1131]);
		
		byte[] noEnterLessMin = new byte[4];
		System.arraycopy(bin, 1132, noEnterLessMin, 0, 4);
		o.setNoEnterLessMin(noEnterLessMin);
		
		byte[] addReqSettings = new byte[2];
		System.arraycopy(bin, 1136, addReqSettings, 0, 2);
		o.setAddReqSettings(addReqSettings);
		
		return o;
	}

}
