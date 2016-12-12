package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.152/184}
 * 
 * @author nfeng
 * @see CpxF1Request
 */
public class CpxF1Command {

	public static final String EMV_VERSION = "\u0001";
	public static final String OPEN_SESSION = "\u0002";
	public static final String CLOSE_SESSION = "\u0003";
	public static final String ASYN_EMV = "\u0004";
	public static final String ASYN_EMV_ACK = "\u0005";
	public static final String ASYN_EMV_LONG_ACK = "\u0006";
	public static final String EMV_REG_VEGA_FOR_AUTO_INIT = "\u0010";
	public static final String BINARY_MAC_CALC = "\u0043";
	public static final String BINARY_MAC_VERIFY = "\u0045";
	
	public static final String P_APP_NAME = "B2_PTxEngine";
	
	public static final String E_APP_NAME = "CA0C00_ApVis";

	/**
	 * Page.156/184 0x00=Normal
	 */
	public static final String STATUS_NORMAL = "\u0000";
	/**
	 * Page.156/184 0x04=EMV has closed session (DATA is TLV information sent
	 * during close)
	 */
	public static final String STATUS_CLOSED = "\u0004";

	/**
	 * 2 bytes, length of application field information
	 */
	private String lgt;
	/**
	 * 1 byte, general command type
	 */
	private String cmdType;
	/**
	 * 1 byte, reference number for this message (if MSG ID, may be user set, if
	 * SEQ ID for Asynchronous messages, it is incremented after each successful
	 * send).
	 */
	private String msgSeqId;
	/**
	 * 0 or 1 byte, Page.156/184 ASYNCHRONOUS EMV COMMAND (Type 0x04)
	 */
	private String status;
	/**
	 * 1 byte, message version to allow for future expansion and legacy message
	 * handling
	 */
	private String msgVer;
	/**
	 * 12 bytes, request message source -> payment application name
	 */
	private String pAppName;
	/**
	 * 12 bytes, application name of the EMV application where CPX will direct
	 * its request
	 */
	private String eAppName;
	/**
	 * (LGT – 27) bytes, EMV data information field for the CPX EMV command
	 * selected
	 */
	private String dataE;

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

	public String getpAppName() {
		return pAppName;
	}

	public void setpAppName(String pAppName) {
		this.pAppName = pAppName;
	}

	public String geteAppName() {
		return eAppName;
	}

	public void seteAppName(String eAppName) {
		this.eAppName = eAppName;
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
		msg += this.getCmdType();
		msg += StringUtils.defaultString(this.getMsgSeqId());
		msg += StringUtils.defaultString(this.getStatus());
		msg += StringUtils.defaultString(this.getMsgVer());
		msg += StringUtils.defaultString(this.getpAppName());
		msg += StringUtils.defaultString(this.geteAppName());
		msg += StringUtils.defaultString(this.getDataE());
		return msg;
	}

	/**
	 * ASYNCHRONOUS EMV COMMAND ACKNOWLEDMENT (ACK) (Type 0x05) [Asynchronous]
	 * Page.158/184
	 * 
	 * @param inSeqId
	 * @return
	 */
	public static CpxF1Command asynEmvCmdAck(String inSeqId) {
		CpxF1Command c = new CpxF1Command();
		c.setLgt(new String(new byte[] { 0, 0x02 }));
		c.setCmdType(ASYN_EMV_ACK);
		c.setMsgSeqId(inSeqId);
		return c;
	}

	/**
	 * EMV OPEN SESSION REQUEST (Type 0x02) [Synchronous] Page.154/184
	 * 
	 * @param inSeqId
	 * @return
	 */
	public static CpxF1Command cpxF1OpenSession(String inSeqId) {
		CpxF1Command c = new CpxF1Command();
		c.setLgt(new String(UTFUtils.lgt(1 + 1 + 1 + 12 + 12, 2)));
		c.setCmdType(OPEN_SESSION);
		c.setMsgSeqId(inSeqId);
		c.setMsgVer(EMV_VERSION);
		c.setpAppName(P_APP_NAME);
		c.seteAppName(E_APP_NAME);
		return c;
	}

	/**
	 * ASYNCHRONOUS EMV COMMAND (Type 0x04) [Asynchronous] Page.156/184
	 * 
	 * @param inSeqId
	 * @return
	 */
	public static CpxF1Command cpxF1AsyncEmvData(String inSeqId, String emvData) {
		CpxF1Command c = new CpxF1Command();
		c.setLgt(new String(UTFUtils.lgt(1 + 1 + 1 + 1 + 12 + 12 + emvData.length(), 2)));
		c.setCmdType(ASYN_EMV);
		c.setMsgSeqId(inSeqId);
		c.setStatus(STATUS_NORMAL); // Normal
		c.setMsgVer(EMV_VERSION);
		c.setpAppName(P_APP_NAME);
		c.seteAppName(E_APP_NAME);
		c.setDataE(emvData); // VEGA Command
		return c;
	}
	
	/**
	 * EMV CLOSE SESSION REQUEST (Type 0x03) [Synchronous] Page.155/184
	 * 
	 * @param inSeqId
	 * @return
	 */
	public static CpxF1Command cpxF1CloseSession(String inSeqId) {
		CpxF1Command c = new CpxF1Command();
		c.setLgt(new String(UTFUtils.lgt(1 + 1 + 1 + 12 + 12, 2)));
		c.setCmdType(CLOSE_SESSION);
		c.setMsgSeqId(inSeqId);
		c.setMsgVer(EMV_VERSION);
		c.setpAppName(P_APP_NAME);
		c.seteAppName(E_APP_NAME);
		return c;
	}

}
