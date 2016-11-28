package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.53/184} {50. Cancel}
 * 
 * @author nfeng
 *
 */
public class Cpx50CancelRequest extends CpxRequest {

	public Cpx50CancelRequest() {
		super();
		this.setMessageType("50.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		return msg;
	}
}
