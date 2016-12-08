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
		c.setCmdType(new String(new byte[] { (byte) 0x05 }));
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
		c.setLgt(new String(UTFUtils.lgt(1+1+1+12+12, 2)));
		c.setCmdType(new String(new byte[] { (byte) 0x02 }));
		c.setMsgSeqId(inSeqId);
		c.setMsgVer("\u0001");
		c.setpAppName("B2_PTxEngine");
		c.seteAppName("CA0C00_ApVis");
		return c;
	}

}
