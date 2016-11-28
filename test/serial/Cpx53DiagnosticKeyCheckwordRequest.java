package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.55/184} {53. Diagnostic Key
 * Checkword Request}
 * 
 * @author nfeng
 *
 */
public class Cpx53DiagnosticKeyCheckwordRequest extends CpxRequest {

	/**
	 * 1 ASCII character, "0" - (for PIN encryption master key), "1" - (for
	 * message encryption master key), "2" - (for MAC master key), "3" - (for
	 * PIN encryption session key), "4" - (for message encryption session key),
	 * "5" - (for MAC session key), "K" - (for DTK or CEFMK for U32 devices),
	 * "M" - (for KCMAC or CDMK for U32 devices), "P" - (for PEFMK for U32
	 * devices), "T" - (for KTK). 2 ASCII hex characters "30" - "6F"
	 * (master/session)
	 */
	private String keyIndicator;

	public String getKeyIndicator() {
		return keyIndicator;
	}

	public void setKeyIndicator(String keyIndicator) {
		this.keyIndicator = keyIndicator;
	}

	public Cpx53DiagnosticKeyCheckwordRequest() {
		super();
		this.setMessageType("53.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getKeyIndicator();
		return msg;
	}
	
}
