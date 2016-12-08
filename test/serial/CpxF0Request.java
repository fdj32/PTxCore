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

	private boolean encode = true;

	public CpxF0Command getCmd() {
		return cmd;
	}

	public void setCmd(CpxF0Command cmd) {
		this.cmd = cmd;
	}

	public boolean isEncode() {
		return encode;
	}

	public void setEncode(boolean encode) {
		this.encode = encode;
	}

	public CpxF0Request() {
		super();
		this.setMessageType("F0.");
		this.setEncode(true);
	}
	
	public CpxF0Request(CpxF0Command cmd) {
		super();
		this.setMessageType("F0.");
		this.setEncode(true);
		this.setCmd(cmd);
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		if (this.isEncode()) {
			msg += UTFUtils.cpxP16Encode(this.getCmd().toString());
		} else {
			msg += this.getCmd().toString();
		}
		return msg;
	}

}
