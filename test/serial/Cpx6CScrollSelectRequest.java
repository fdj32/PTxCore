package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.129/184} {6C. Scrolling
 * Selection List}
 * 
 * @author nfeng
 *
 */
public class Cpx6CScrollSelectRequest extends CpxRequest {

	/**
	 * 1 hex ASCII char, "0" for Single Item per screen Mode
	 */
	private String commandMode;
	/**
	 * 1 hex ASCII char "1" = F1 key "2" = F2 key "3" = F3 key "4" = F4 key "5"
	 * = F5 key "6" = F6 key "7" = F7 key "8" = F8 key "9" = F9 key
	 */
	private String nextFunctionKey;
	/**
	 * 1 hex ASCII char "1" = F1 key "2" = F2 key "3" = F3 key "4" = F4 key "5"
	 * = F5 key "6" = F6 key "7" = F7 key "8" = F8 key "9" = F9 key MUST be
	 * different from Next Function Key or the command will fail. Function Key
	 * must exist on the target Pinpad (i.e. on i3500 & i3070, asking for "4" to
	 * "9" will result in Invalid Co mmand failure). Although for the i3070, the
	 * softkeys "F5" and "F6" exist, they are not available for use with this
	 * command.
	 */
	private String previousFunctionKey;
	/**
	 * 1 hex ASCII char "0" use Next/Prev Strings "1" - show images (makes sense
	 * for 3 key PinPad only)
	 */
	private String showImages;
	/**
	 * 1 hex ASCI I char "0" to "F" - as per all others 0 is none, 1 - F *5
	 * seconds.
	 */
	private String timeout;
	/**
	 * 1 ASCII char "0"= no invalid key beep for scroll keys only. "1" = enable
	 * beep
	 */
	private String invalidBeep;
	/**
	 * 2 hex ASCII char The index of the initial item to be displayed. "01" for
	 * the first item, "02" for the second item, ..., "20" for the last item (if
	 * maximum stings used)
	 */
	private String defaultSelection;
	/**
	 * 16 ASCII char + RS Variable length up to 16 Characters Max (will truncate
	 * if larger, but not cause error) followed by RS character (0x1E)
	 */
	private String titleString;
	/**
	 * 16 ASCII char + RS Variable length up to 16 Characters Max (will truncate
	 * if larger, but not cause error) followed by RS character (0x1E). Will be
	 * placed on bottom row. The words may or may not appear above the
	 * respective Function Key. Placement is determined by the spacing in the
	 * string, if Function Keys are located below the last row of the display.
	 * This is also useful when func keys are NOT at bottom of screen – for
	 * future pinpads. (e.g. F1=NEXT F3=PREV). (Will not appear if non e
	 * available).
	 */
	private String NextOrPreviousString;
	/**
	 * 16 ASCII char + RS Variable length up to 16 Characters Max (will truncate
	 * if larger, but not cause error) followed by RS character (0x1E). Will be
	 * placed on bottom row. The "Prev" word may or may not appear above the
	 * respe ctive Function Key. Placement is determined by the spacing in the
	 * string, if Function Keys are located below the last row of the display.
	 * This is also useful when func keys are NOT at bottom of screen – for
	 * future pinpads. (e.g. F3=PREV). (Will not appear if none available).
	 */
	private String prevOnlyString;
	/**
	 * 16 ASCII char + RS Variable length up to 16 Characters Max (will truncate
	 * if larger, but not cause error) followed by RS character (0x1E). Will be
	 * placed on bottom row. The "Next" word may or may not appear above the
	 * respective Function Key. Placement is determined by the spacing in the
	 * string, if Function Keys are located below the last row of the display.
	 * This is also useful when func keys are NOT at bottom of screen – for
	 * future pinpads. (e.g. F1=NEXT).(Will not appear if none available).
	 */
	private String nextOnlyString;
	/**
	 * 32(16) ASCII char, Up to 20 Variable strings each of 32 characters each
	 * (truncated if longer) terminated by an RS character (0x1E) for every
	 * string, including the last string. If the FS character (0x1C) is present,
	 * there will follow a 4 -character ASCII hex value (truncated if longer)
	 * that represents selection data to be returned in the response, if the
	 * associated selection list string is selected by the user.
	 */
	private String[] selectListStrings;

	public String getCommandMode() {
		return commandMode;
	}

	public void setCommandMode(String commandMode) {
		this.commandMode = commandMode;
	}

	public String getNextFunctionKey() {
		return nextFunctionKey;
	}

	public void setNextFunctionKey(String nextFunctionKey) {
		this.nextFunctionKey = nextFunctionKey;
	}

	public String getPreviousFunctionKey() {
		return previousFunctionKey;
	}

	public void setPreviousFunctionKey(String previousFunctionKey) {
		this.previousFunctionKey = previousFunctionKey;
	}

	public String getShowImages() {
		return showImages;
	}

	public void setShowImages(String showImages) {
		this.showImages = showImages;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getInvalidBeep() {
		return invalidBeep;
	}

	public void setInvalidBeep(String invalidBeep) {
		this.invalidBeep = invalidBeep;
	}

	public String getDefaultSelection() {
		return defaultSelection;
	}

	public void setDefaultSelection(String defaultSelection) {
		this.defaultSelection = defaultSelection;
	}

	public String getTitleString() {
		return titleString;
	}

	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}

	public String getNextOrPreviousString() {
		return NextOrPreviousString;
	}

	public void setNextOrPreviousString(String nextOrPreviousString) {
		NextOrPreviousString = nextOrPreviousString;
	}

	public String getPrevOnlyString() {
		return prevOnlyString;
	}

	public void setPrevOnlyString(String prevOnlyString) {
		this.prevOnlyString = prevOnlyString;
	}

	public String getNextOnlyString() {
		return nextOnlyString;
	}

	public void setNextOnlyString(String nextOnlyString) {
		this.nextOnlyString = nextOnlyString;
	}

	public String[] getSelectListStrings() {
		return selectListStrings;
	}

	public void setSelectListStrings(String[] selectListStrings) {
		this.selectListStrings = selectListStrings;
	}

	public Cpx6CScrollSelectRequest() {
		super();
		this.setMessageType("6C.");
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getMessageType()); // 6C.
		sb.append(this.getCommandMode()); // 0 Single Item
		sb.append(this.getNextFunctionKey()); // 1 Next Key - F2
		sb.append(this.getPreviousFunctionKey()); // 3 Previous Key - F1
		sb.append(this.getShowImages()); // 1 Use Next/Previous Strings
		sb.append(this.getTimeout()); // this._configObj.Terminal.StandardEntryTimeout
										// / 5
		sb.append(this.getInvalidBeep()); // 0 Invalid Beep
		sb.append(this.getDefaultSelection()); // 01 Default Selection
		sb.append(StringUtils.rightPad(this.getTitleString(), 16, ' ')).append(
				UTFUtils.RS);
		sb.append(StringUtils.rightPad(this.getNextOrPreviousString(), 16, ' '))
				.append(UTFUtils.RS);
		sb.append(StringUtils.rightPad(this.getPrevOnlyString(), 16, ' '))
				.append(UTFUtils.RS);
		sb.append(StringUtils.rightPad(this.getNextOnlyString(), 16, ' '))
				.append(UTFUtils.RS);
		for (String selectionListString : this.getSelectListStrings()) {
			// TODO: B2's code is 16, while in api doc is 32
			sb.append(StringUtils.rightPad(selectionListString, 16, ' '))
					.append(UTFUtils.RS);
		}
		return sb.toString();
	}
}
