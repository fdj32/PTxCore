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
	private String lgt;
	/**
	 * 2 bytes TYPE . driver number in INGENICO UNICAPT structure
	 */
	private String type;
	/**
	 * 1 bytes STATUS . execution status (same as particular report in DRIVER
	 * structure in SIMELITE)
	 */
	private String status;
	/**
	 * (LGT - 2) bytes DATA_R . data information response field for the driver
	 * command selected
	 */
	private String dataR;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDataR() {
		return dataR;
	}

	public void setDataR(String dataR) {
		this.dataR = dataR;
	}

	public static CpxF0Result parse(String str) {
		CpxF0Result rst = new CpxF0Result();
		rst.setLgt(str.substring(0, 2));
		rst.setType(str.substring(2, 3));
		rst.setStatus(str.substring(3, 4));
		rst.setDataR(str.substring(4));
		return rst;
	}

}
