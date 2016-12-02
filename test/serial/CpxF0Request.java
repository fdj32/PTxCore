package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.70/184} {CHAPTER 6 – SPECIFIC
 * ELITE COMMANDS}
 * 
 * @author nfeng
 * @see CpxF0Command
 */
public class CpxF0Request extends CpxRequest {

	private CpxF0Command cmd;

	public CpxF0Command getCmd() {
		return cmd;
	}

	public void setCmd(CpxF0Command cmd) {
		this.cmd = cmd;
	}

	public CpxF0Request() {
		super();
		this.setMessageType("F0.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getCmd().toString();
		return msg;
	}

}
