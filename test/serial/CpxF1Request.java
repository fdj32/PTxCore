package serial;

import org.apache.commons.codec.binary.Hex;

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
	
	public CpxF1Request(CpxF1Command cmd) {
		super();
		this.setMessageType("F1.");
		this.setEncode(true);
		this.setCmd(cmd);
	}
	
	@Override
	public String toString() {
		String msg = this.getMessageType();
		
		return msg;
	}

	public byte[] toBinary() {
		byte[] cmdData = this.getCmd().toBinary();
		byte[] cmdEncoded = UTFUtils.cpxP16Encode(cmdData);
		System.out.println(Hex.encodeHexString(cmdData));
		System.out.println(Hex.encodeHexString(cmdEncoded));
		byte[] data = null;
		if(this.isEncode()) {
			data = new byte[3 + cmdEncoded.length];
			System.arraycopy(this.getMessageType().getBytes(), 0, data, 0, 3);
			System.arraycopy(cmdEncoded, 0, data, 3, cmdEncoded.length);
		} else {
			data = new byte[3 + cmdData.length];
			System.arraycopy(this.getMessageType().getBytes(), 0, data, 0, 3);
			System.arraycopy(cmdData, 0, data, 3, cmdData.length);
		}
		return data;
	}

}
