package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.71/184} {CHAPTER 6 – SPECIFIC
 * ELITE COMMANDS}
 * 
 * @author nfeng
 * @see CpxF0Request
 */
public class CpxF0Command {

	/**
	 * 2 bytes LGT . length of application field information
	 */
	private String lgt;
	/**
	 * 1 byte TYPE . driver number in INGENICO UNICAPT structure
	 */
	private String type;
	/**
	 * 2 bytes TO . maximum time out of execution supported by the terminal (in
	 * multiples of 10 ms)
	 */
	private String to;
	/**
	 * 1 bytes CMD . command number for the driver selected
	 */
	private String cmd;
	/**
	 * (LGT - 4) bytes DATA_E . data information field for the driver command
	 * selected
	 */
	private String dataE;

	public String getLgt() {
		return lgt;
	}

	public void setLgt(String lgt) {
		this.lgt = lgt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getDataE() {
		return dataE;
	}

	public void setDataE(String dataE) {
		this.dataE = dataE;
	}

	@Override
	public String toString() {
		String msg = this.getLgt();
		msg += this.getType();
		msg += this.getTo();
		msg += this.getCmd();
		msg += this.getDataE();
		return msg;
	}

}
