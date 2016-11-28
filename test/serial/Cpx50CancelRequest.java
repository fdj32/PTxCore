package serial;

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
