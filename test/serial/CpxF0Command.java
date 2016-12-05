package serial;

import org.apache.commons.lang.StringUtils;

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

	public void setTo(int to) {
		setTo(new String(new byte[] { (byte) (to * 100 / 0x100),
				(byte) (to * 100 % 0x100) }));
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
		msg += StringUtils.defaultString(this.getDataE());
		return msg;
	}

	/**
	 * 
	 * @param to
	 *            timeout in seconds
	 * @param cmd
	 *            1=track1, 2=track2, 3=track1+track2
	 * @return
	 */
	public static CpxF0Command cpxF0MsrRead(int to, int cmd) {
		CpxF0Command msrRead = new CpxF0Command();
		msrRead.setLgt(new String(new byte[] { 0, 0x04 }));
		msrRead.setType(new String(new byte[] { 0x04 }));
		msrRead.setTo(to);
		msrRead.setCmd(new String(new byte[] { (byte) (cmd) }));
		return msrRead;
	}

}
