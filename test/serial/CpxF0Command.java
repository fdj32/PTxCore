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
		if (to > 75) {
			to = 75;
		}
		if (to < 0) {
			to = 0;
		}
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
		CpxF0Command c = new CpxF0Command();
		c.setLgt(new String(new byte[] { 0, 0x04 }));
		c.setType(new String(new byte[] { 0x04 }));
		c.setTo(to);
		c.setCmd(new String(new byte[] { (byte) (cmd) }));
		return c;
	}

	/**
	 * CPX+EMV+Emulation.pdf Page.79/184
	 * 
	 * @param to
	 *            timeout in seconds
	 * @return
	 */
	public static CpxF0Command cpxF0CancelMsrRead(int to) {
		CpxF0Command c = new CpxF0Command();
		c.setLgt(new String(new byte[] { 0, 0x04 }));
		c.setType(new String(new byte[] { 0x04 }));
		c.setTo(to);
		c.setCmd(new String(new byte[] { (byte) (0x12) }));
		return c;
	}

	/**
	 * CPX+EMV+Emulation.pdf Page.84/184
	 * 
	 * @param to
	 *            timeout in seconds
	 * @return
	 */
	public static CpxF0Command cpxF0DefineRemoveCardPrompt(int to) {
		CpxF0Command c = new CpxF0Command();
		c.setLgt(new String(new byte[] { 0, 0x04 }));
		c.setType(new String(new byte[] { 0x05 }));
		c.setTo(to);
		// 0x10 = Remove card prompt
		c.setCmd(new String(new byte[] { (byte) (0x10) }));
		return c;
	}

	/**
	 * CPX+EMV+Emulation.pdf Page.84/184
	 * 
	 * @param to
	 *            timeout in seconds
	 * @return
	 */
	public static CpxF0Command cpxF0MSRwithSCDetectCancel(int to) {
		CpxF0Command c = new CpxF0Command();
		c.setLgt(new String(new byte[] { 0, 0x04 }));
		c.setType(new String(new byte[] { 0x05 }));
		c.setTo(to);
		// 0x12 = cancel
		c.setCmd(new String(new byte[] { (byte) (0x12) }));
		return c;
	}

	/**
	 * CPX+EMV+Emulation.pdf Page.86/184
	 * 
	 * @param to
	 *            timeout in seconds
	 * @return
	 */
	public static CpxF0Command cpxF0WaitForSmartCardInsertion(int to) {
		CpxF0Command c = new CpxF0Command();
		c.setLgt(new String(new byte[] { 0, 0x04 }));
		c.setType(new String(new byte[] { 0x07 }));
		c.setTo(to);
		// 0x04: Waiting for a smart card present in the connector
		c.setCmd(new String(new byte[] { (byte) (0x04) }));
		return c;
	}

	/**
	 * CPX+EMV+Emulation.pdf Page.87/184
	 * 
	 * @param to
	 *            timeout in seconds
	 * @return
	 */
	public static CpxF0Command cpxF0PowerUpCard(int to) {
		CpxF0Command c = new CpxF0Command();
		c.setLgt(new String(new byte[] { 0, 0x04 }));
		c.setType(new String(new byte[] { 0x07 }));
		c.setTo(to);
		// 0x05 : Smart card ATR sequence for asynchronous cards or recognition
		// of synchronous card
		c.setCmd(new String(new byte[] { (byte) (0x05) }));
		return c;
	}

	/**
	 * CPX+EMV+Emulation.pdf Page.88/184
	 * 
	 * @param to
	 *            timeout in seconds
	 * @return
	 */
	public static CpxF0Command cpxF0WaitInsertAndPowerUp(int to) {
		CpxF0Command c = new CpxF0Command();
		c.setLgt(new String(new byte[] { 0, 0x04 }));
		c.setType(new String(new byte[] { 0x07 }));
		c.setTo(to);
		// 0x07 : Smart card ATR sequence for asynchronous cards or recognition
		// of synchronous card = command 0x04 followed by command 0x05
		c.setCmd(new String(new byte[] { (byte) (0x07) }));
		return c;
	}

	/**
	 * CPX+EMV+Emulation.pdf Page.89/184
	 * 
	 * @param to
	 *            timeout in seconds
	 * @return
	 */
	public static CpxF0Command cpxF0PowerUpCardAndControlsForATR(int to) {
		CpxF0Command c = new CpxF0Command();
		c.setLgt(new String(new byte[] { 0, 0x04 }));
		c.setType(new String(new byte[] { 0x07 }));
		c.setTo(to);
		// 0x08 : command 0x04 and 0x05 with comparison of the ATR received in
		// commands 0x05 and this command
		c.setCmd(new String(new byte[] { (byte) (0x08) }));
		return c;
	}

	/**
	 * CPX+EMV+Emulation.pdf Page.90/184
	 * 
	 * @param to
	 *            timeout in seconds
	 * @return
	 */
	public static CpxF0Command cpxF0PowerOffCard(int to) {
		CpxF0Command c = new CpxF0Command();
		c.setLgt(new String(new byte[] { 0, 0x04 }));
		c.setType(new String(new byte[] { 0x07 }));
		c.setTo(to);
		// 0x0A smart card power off
		c.setCmd(new String(new byte[] { (byte) (0x0A) }));
		return c;
	}

	/**
	 * CPX+EMV+Emulation.pdf Page.91/184
	 * 
	 * @param to
	 *            timeout in seconds
	 * @return
	 */
	public static CpxF0Command cpxF0WaitForRemovalOfCard(int to) {
		CpxF0Command c = new CpxF0Command();
		c.setLgt(new String(new byte[] { 0, 0x04 }));
		c.setType(new String(new byte[] { 0x07 }));
		c.setTo(to);
		// 0x0B waiting for removal of smart card
		c.setCmd(new String(new byte[] { (byte) (0x0B) }));
		return c;
	}

	/**
	 * CPX+EMV+Emulation.pdf Page.92/184
	 * 
	 * @param to
	 *            timeout in seconds
	 * @return
	 */
	public static CpxF0Command cpxF0PowerOffCardAndWaitForSmartCardRemoval(
			int to) {
		CpxF0Command c = new CpxF0Command();
		c.setLgt(new String(new byte[] { 0, 0x04 }));
		c.setType(new String(new byte[] { 0x07 }));
		c.setTo(to);
		// 0x0C smart card power off and waiting for card removal command 0x0A
		// followed by command 0x0B
		c.setCmd(new String(new byte[] { (byte) (0x0C) }));
		return c;
	}

	/**
	 * CPX+EMV+Emulation.pdf Page.97/184
	 * 
	 * @param to
	 *            timeout in seconds
	 * @return
	 */
	public static CpxF0Command cpxF0SmartCardCommandCancel(int to) {
		CpxF0Command c = new CpxF0Command();
		c.setLgt(new String(new byte[] { 0, 0x04 }));
		c.setType(new String(new byte[] { 0x07 }));
		c.setTo(to);
		// 0x12 = cancel
		c.setCmd(new String(new byte[] { (byte) (0x12) }));
		return c;
	}

	/**
	 * CPX+EMV+Emulation.pdf Page.97/184
	 * 
	 * @param to
	 *            timeout in seconds
	 * @return
	 */
	public static CpxF0Command cpxF0SmartCardStatus(int to) {
		CpxF0Command c = new CpxF0Command();
		c.setLgt(new String(new byte[] { 0, 0x04 }));
		c.setType(new String(new byte[] { 0x07 }));
		c.setTo(to);
		// 0x12 = cancel
		c.setCmd(new String(new byte[] { (byte) (0x18) }));
		return c;
	}

}
