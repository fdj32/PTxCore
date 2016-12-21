package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.71/184} {CHAPTER 6 – SPECIFIC
 * ELITE COMMANDS}
 * 
 * @author nfeng
 * @see CpxF0Response
 */
public class CpxF0Result {

	/**
	 * 2 bytes LGT . length of application field information for response
	 */
	private byte[] lgt;
	/**
	 * 1 bytes TYPE . driver number in INGENICO UNICAPT structure
	 */
	private byte type;
	/**
	 * 1 bytes STATUS . execution status (same as particular report in DRIVER
	 * structure in SIMELITE)
	 */
	private byte status;
	/**
	 * (LGT - 2) bytes DATA_R . data information response field for the driver
	 * command selected
	 */
	private byte[] dataR;

	public byte[] getLgt() {
		return lgt;
	}

	public void setLgt(byte[] lgt) {
		this.lgt = lgt;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public byte[] getDataR() {
		return dataR;
	}

	public void setDataR(byte[] dataR) {
		this.dataR = dataR;
	}

	public static CpxF0Result parse(byte[] src) {
		CpxF0Result rst = new CpxF0Result();
		byte[] dest = new byte[2];
		System.arraycopy(src, 0, dest, 0, 2);
		rst.setLgt(dest);
		rst.setType(src[2]);
		rst.setStatus(src[3]);
		if(src.length > 4) {
			dest = new byte[src.length - 4];
			System.arraycopy(src, 4, dest, 0, src.length - 4);
			rst.setDataR(dest);
		}
		return rst;
	}

}
