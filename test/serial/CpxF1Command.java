package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.152/184}
 * 
 * @author nfeng
 * @see CpxF1Request
 */
public class CpxF1Command {

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
		msg += this.getMsgSeqId();
		msg += this.getMsgVer();
		msg += this.getpAppName();
		msg += this.geteAppName();
		msg += StringUtils.defaultString(this.getDataE());
		return msg;
	}

}
