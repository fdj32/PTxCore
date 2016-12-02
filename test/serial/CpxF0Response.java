package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.71/184} {CHAPTER 6 – SPECIFIC
 * ELITE COMMANDS}
 * 
 * @author nfeng
 * @see CpxF0Result
 */
public class CpxF0Response extends CpxResponse {
	
	private CpxF0Result rst;

	public CpxF0Result getRst() {
		return rst;
	}

	public void setRst(CpxF0Result rst) {
		this.rst = rst;
	}
	
	public CpxF0Response() {
		super();
		this.setMessageType("F0.");
	}
	
	public static CpxF0Response parse(String str) {
		CpxF0Response resp = new CpxF0Response();
		resp.setRst(CpxF0Result.parse(str.substring(3)));
		return resp;
	}

}
