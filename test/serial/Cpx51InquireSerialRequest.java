package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.54/184} {51. Inquire Current
 * Serial Number}
 * 
 * @author nfeng
 *
 */
public class Cpx51InquireSerialRequest extends CpxRequest {

	/**
	 * 1 ASCII number, 'S' for serial number
	 */
	private String keyIndicator;

	public String getKeyIndicator() {
		return keyIndicator;
	}

	public void setKeyIndicator(String keyIndicator) {
		this.keyIndicator = keyIndicator;
	}

	public Cpx51InquireSerialRequest() {
		super();
		this.setMessageType("51.");
		this.setKeyIndicator("S");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getKeyIndicator();
		return super.toString();
	}

}
