package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.65/184} {5D. Device Information}
 * 
 * @author nfeng
 *
 */
public class Cpx5DDeviceInformationRequest extends CpxRequest {

	/**
	 * 1 character - optional "1" - serial numbers and options info. "3" –
	 * terminal device type "4" – terminal application versions "5" - Check if
	 * device has Contactless Reader.
	 */
	private String option;

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Cpx5DDeviceInformationRequest() {
		super();
		this.setMessageType("5D.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getOption();
		return msg;
	}
}
