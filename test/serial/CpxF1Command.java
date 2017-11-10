package serial;

import java.nio.ByteBuffer;

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
	private byte[] lgt;
	/**
	 * 1 byte, general command type
	 */
	private String cmdType;
	/**
	 * 1 byte, reference number for this message (if MSG ID, may be user set, if
	 * SEQ ID for Asynchronous messages, it is incremented after each successful
	 * send).
	 */
	private byte msgSeqId;
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
	private byte[] dataE;

	public byte[] getLgt() {
		return lgt;
	}

	public void setLgt(byte[] lgt) {
		this.lgt = lgt;
	}

	public String getCmdType() {
		return cmdType;
	}

	public void setCmdType(String cmdType) {
		this.cmdType = cmdType;
	}

	public byte getMsgSeqId() {
		return msgSeqId;
	}

	public void setMsgSeqId(byte msgSeqId) {
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

	public byte[] getDataE() {
		return dataE;
	}

	public void setDataE(byte[] dataE) {
		this.dataE = dataE;
	}
	
	@Override
	public String toString() {
		return new String(toBinary());
	}

	public byte[] toBinary() {
		ByteBuffer bb = ByteBuffer.allocate(1024);
		bb.put(this.getLgt());
		bb.put(this.getCmdType().getBytes());
		bb.put(this.getMsgSeqId());
		bb.put(StringUtils.defaultString(this.getStatus()).getBytes());
		bb.put(StringUtils.defaultString(this.getMsgVer()).getBytes());
		bb.put(StringUtils.defaultString(this.getpAppName()).getBytes());
		bb.put(StringUtils.defaultString(this.geteAppName()).getBytes());
		if(null != this.getDataE() && 0 != this.getDataE().length) {
			bb.put(this.getDataE());
		}
		int position = bb.position();
		bb.flip();
		byte[] dst = new byte[position];
		bb.get(dst, 0, position);
		return dst;
	}

	/**
	 * ASYNCHRONOUS EMV COMMAND ACKNOWLEDMENT (ACK) (Type 0x05) [Asynchronous]
	 * Page.158/184
	 * 
	 * @param inSeqId
	 * @return
	 */
	public static CpxF1Command asynEmvCmdAck(byte inSeqId) {
		CpxF1Command c = new CpxF1Command();
		c.setLgt(new byte[] { 0, 0x02 });
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
	public static CpxF1Command cpxF1OpenSession(byte inSeqId) {
		CpxF1Command c = new CpxF1Command();
		c.setLgt(UTFUtils.lgt(1 + 1 + 1 + 12 + 12, 2));
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
	public static CpxF1Command cpxF1AsyncEmvData(byte inSeqId, byte[] emvData) {
		CpxF1Command c = new CpxF1Command();
		c.setLgt(UTFUtils.lgt(1 + 1 + 1 + 1 + 12 + 12 + emvData.length, 2));
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
	public static CpxF1Command cpxF1CloseSession(byte inSeqId) {
		CpxF1Command c = new CpxF1Command();
		c.setLgt(UTFUtils.lgt(1 + 1 + 1 + 12 + 12, 2));
		c.setCmdType(CLOSE_SESSION);
		c.setMsgSeqId(inSeqId);
		c.setMsgVer(EMV_VERSION);
		c.setpAppName(P_APP_NAME);
		c.seteAppName(E_APP_NAME);
		return c;
	}

}
