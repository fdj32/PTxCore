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
	private String lgt;
	/**
	 * 1 byte TYPE . general command type
	 */
	private String cmdType;
	/**
	 * 1 byte, the message identifier of the request message (set by the sender
	 * for both MSG ID and SEQ ID).
	 */
	private String msgSeqId;
	/**
	 * 1 bytes STATUS . execution status (same as particular report in DRIVER
	 * structure in SIMELITE)
	 */
	private String status;
	/**
	 * 1 byte, message version to allow for future expansion and legacy message
	 * handling
	 */
	private String msgVer;
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
	private String dataR;

	public String getLgt() {
		return lgt;
	}

	public void setLgt(String lgt) {
		this.lgt = lgt;
	}

	public String getCmdType() {
		return cmdType;
	}

	public void setCmdType(String cmdType) {
		this.cmdType = cmdType;
	}

	public String getMsgSeqId() {
		return msgSeqId;
	}

	public void setMsgSeqId(String msgSeqId) {
		this.msgSeqId = msgSeqId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsgVer() {
		return msgVer;
	}

	public void setMsgVer(String msgVer) {
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

	public String getDataR() {
		return dataR;
	}

	public void setDataR(String dataR) {
		this.dataR = dataR;
	}

	public static CpxF1Result parse(String str) {
		CpxF1Result rst = new CpxF1Result();
		rst.setLgt(str.substring(0, 2));
		rst.setCmdType(str.substring(2, 3));
		rst.setMsgSeqId(str.substring(3, 4));
		rst.setStatus(str.substring(4, 5));
		rst.setMsgVer(str.substring(5, 6));
		rst.seteAppName(str.substring(6, 18));
		rst.setpAppName(str.substring(18, 30));
		rst.setDataR(str.substring(30));
		return rst;
	}

}
