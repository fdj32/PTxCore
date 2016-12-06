package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.151/184} {CHAPTER 8 – CPX EMV
 * COMMANDS}
 * 
 * @author nfeng
 * @see CpxF1Command
 */
public class CpxF1Request extends CpxRequest {

	private CpxF1Command cmd;

	private boolean encode = true;

	public CpxF1Command getCmd() {
		return cmd;
	}

	public void setCmd(CpxF1Command cmd) {
		this.cmd = cmd;
	}

	public boolean isEncode() {
		return encode;
	}

	public void setEncode(boolean encode) {
		this.encode = encode;
	}

	public CpxF1Request() {
		super();
		this.setMessageType("F1.");
		this.setEncode(true);
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