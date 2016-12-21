package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.152/184}
 * 
 * @author nfeng
 * @see CpxF1Response
 */
public class CpxF1Result {

	/**
	 * 2 bytes LGT . length of application field information for response
	 */
	private byte[] lgt;
	/**
	 * 1 byte TYPE . general command type
	 */
	private byte cmdType;
	/**
	 * 1 byte, the message identifier of the request message (set by the sender
	 * for both MSG ID and SEQ ID).
	 */
	private byte msgSeqId;
	/**
	 * 1 bytes STATUS . execution status (same as particular report in DRIVER
	 * structure in SIMELITE)
	 */
	private byte status;
	/**
	 * 1 byte, message version to allow for future expansion and legacy message
	 * handling
	 */
	private byte msgVer;
	/**
	 * 12 bytes, response message source -> EMV application name
	 */
	private String eAppName;
	/**
	 * 12 bytes, application name of the payment application where CPX will
	 * direct its response
	 */
	private String pAppName;
	/**
	 * (LGT - 28) bytes DATA_R . data information response field for the driver
	 * command selected
	 */
	private byte[] dataR;

	public byte[] getLgt() {
		return lgt;
	}

	public void setLgt(byte[] lgt) {
		this.lgt = lgt;
	}

	public byte getCmdType() {
		return cmdType;
	}

	public void setCmdType(byte cmdType) {
		this.cmdType = cmdType;
	}

	public byte getMsgSeqId() {
		return msgSeqId;
	}

	public void setMsgSeqId(byte msgSeqId) {
		this.msgSeqId = msgSeqId;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public byte getMsgVer() {
		return msgVer;
	}

	public void setMsgVer(byte msgVer) {
		this.msgVer = msgVer;
	}

	public String geteAppName() {
		return eAppName;
	}

	public void seteAppName(String eAppName) {
		this.eAppName = eAppName;
	}

	public String getpAppName() {
		return pAppName;
	}

	public void setpAppName(String pAppName) {
		this.pAppName = pAppName;
	}

	public byte[] getDataR() {
		return dataR;
	}

	public void setDataR(byte[] dataR) {
		this.dataR = dataR;
	}

	public static CpxF1Result parse(byte[] src) {
		CpxF1Result rst = new CpxF1Result();
		byte[] dest = new byte[2];
		System.arraycopy(src, 0, dest, 0, 2);
		rst.setLgt(dest);
		rst.setCmdType(src[2]);
		rst.setMsgSeqId(src[3]);
		rst.setStatus(src[4]);
		rst.setMsgVer(src[5]);
		dest = new byte[12];
		System.arraycopy(src, 6, dest, 0, 12);
		rst.seteAppName(new String(dest));
		dest = new byte[12];
		System.arraycopy(src, 18, dest, 0, 12);
		rst.setpAppName(new String(dest));
		if (src.length > 30) {
			dest = new byte[src.length - 30];
			System.arraycopy(src, 30, dest, 0, src.length - 30);
			rst.setDataR(dest);
		}
		return rst;
	}

}
