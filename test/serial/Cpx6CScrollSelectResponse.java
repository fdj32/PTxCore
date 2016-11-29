package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.130/184} {6C. Scrolling
 * Selection List}
 * 
 * @author nfeng
 *
 */
public class Cpx6CScrollSelectResponse extends CpxResponse {

	// If status is SUCCESS ("0"), then the following will also be returned:
	/**
	 * 2 hex ASCII characters "01" if the first string in the list was selected
	 * "02" if the second string in the list was selected ... "20 " if the last
	 * string in the list was selected
	 */
	private String selectionIndex;
	/**
	 * 4 ASCII hex characters The optional data that was sent for the section
	 * string that was selected. If the data was not included (i.e. the no 4
	 * char data between the FS and RS separator characters, then no data is
	 * returned after the selection index above.
	 */
	private String selectionData;

	public String getSelectionIndex() {
		return selectionIndex;
	}

	public void setSelectionIndex(String selectionIndex) {
		this.selectionIndex = selectionIndex;
	}

	public String getSelectionData() {
		return selectionData;
	}

	public void setSelectionData(String selectionData) {
		this.selectionData = selectionData;
	}

	public Cpx6CScrollSelectResponse() {
		super();
		this.setMessageType("6C.");
	}

	public static Cpx6CScrollSelectResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1 + 2 + 4) != str.length() || !str.startsWith("6C.")) {
			return null;
		}
		Cpx6CScrollSelectResponse resp = new Cpx6CScrollSelectResponse();
		resp.setStatus(str.substring(3, 4));
		resp.setSelectionIndex(str.substring(4, 6));
		resp.setSelectionData(str.substring(6, 10));
		return resp;
	}
}
