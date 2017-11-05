package serial;

/**
 * git/merchant-service/info/EMV/paymentech_dev_center/pin_pad_documentation/Ingenico
 * CPX, E2EE, VEGA, TSA iPP320 5C Documentation/API - VEGA/0142-07204-0503
 * Generic EMV API.pdf
 * 
 * @author nfeng
 *
 */
public class Vega {

	/**
	 * @see <code>EmvServiceCode</code>
	 */
	private int emvServiceCode;
	/**
	 * @see <code>EmvReasonCode</code>
	 */
	private int emvReasonCode;
	private int emvDataLength;

	public int getEmvServiceCode() {
		return emvServiceCode;
	}

	public void setEmvServiceCode(int emvServiceCode) {
		this.emvServiceCode = emvServiceCode;
	}

	public int getEmvReasonCode() {
		return emvReasonCode;
	}

	public void setEmvReasonCode(int emvReasonCode) {
		this.emvReasonCode = emvReasonCode;
	}

	public int getEmvDataLength() {
		return emvDataLength;
	}

	public void setEmvDataLength(int emvDataLength) {
		this.emvDataLength = emvDataLength;
	}

}
