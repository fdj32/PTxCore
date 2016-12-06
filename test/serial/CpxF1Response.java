package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.151/184}
 * 
 * @author nfeng
 * @see CpxF1Result
 */
public class CpxF1Response extends CpxResponse {

	private CpxF1Result rst;

	public CpxF1Result getRst() {
		return rst;
	}

	public void setRst(CpxF1Result rst) {
		this.rst = rst;
	}

	public CpxF1Response() {
		super();
		this.setMessageType("F1.");
	}

	public static CpxF1Response parse(String str) {
		CpxF1Response resp = new CpxF1Response();
		resp.setRst(CpxF1Result.parse(str.substring(3)));
		return resp;
	}

}
