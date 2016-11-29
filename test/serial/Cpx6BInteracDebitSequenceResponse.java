package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.124/184} {6B. Interac Debit
 * Sequence}
 * 
 * @author nfeng
 *
 */
public class Cpx6BInteracDebitSequenceResponse extends CpxResponse {

	// Fields only returned if no step failures

	/**
	 * 10 characters no decimal point ( fixed length with leading zeros )
	 */
	private String totalAmount;
	/**
	 * 9 characters no decimal point (fixed length with leading zeros )
	 * (included only if Tip Entry enabled)
	 */
	private String tipAmount;
	/**
	 * 9 characters no decimal point (fixed length with leading zeros )
	 * (included only if Cashback Ent ry enabled)
	 */
	private String cashbackAmount;
	/**
	 * 1 ASCII character "C" – Chequing, "S" - Saving
	 */
	private String accountSelected;
	/**
	 * 2 characters "01"=ANSI
	 */
	private String pinBlockFormat;
	/**
	 * 16 ASCII hex characters
	 */
	private String encryptedPinBlock;

	// Track data fields only returned if Card Swipe enabled:
	/**
	 * 2 ASCII chars decimal length of ISO 2 field
	 */
	private String track2length;
	/**
	 * n ASCII chars ISO2 track if reading OK
	 */
	private String iso2field;
	/**
	 * 2 ASCII chars decimal length of ISO 1 field
	 */
	private String track1length;
	/**
	 * n ASCII chars ISO1 track if reading OK
	 */
	private String iso1field;

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTipAmount() {
		return tipAmount;
	}

	public void setTipAmount(String tipAmount) {
		this.tipAmount = tipAmount;
	}

	public String getCashbackAmount() {
		return cashbackAmount;
	}

	public void setCashbackAmount(String cashbackAmount) {
		this.cashbackAmount = cashbackAmount;
	}

	public String getAccountSelected() {
		return accountSelected;
	}

	public void setAccountSelected(String accountSelected) {
		this.accountSelected = accountSelected;
	}

	public String getPinBlockFormat() {
		return pinBlockFormat;
	}

	public void setPinBlockFormat(String pinBlockFormat) {
		this.pinBlockFormat = pinBlockFormat;
	}

	public String getEncryptedPinBlock() {
		return encryptedPinBlock;
	}

	public void setEncryptedPinBlock(String encryptedPinBlock) {
		this.encryptedPinBlock = encryptedPinBlock;
	}

	public String getTrack2length() {
		return track2length;
	}

	public void setTrack2length(String track2length) {
		this.track2length = track2length;
	}

	public String getIso2field() {
		return iso2field;
	}

	public void setIso2field(String iso2field) {
		this.iso2field = iso2field;
	}

	public String getTrack1length() {
		return track1length;
	}

	public void setTrack1length(String track1length) {
		this.track1length = track1length;
	}

	public String getIso1field() {
		return iso1field;
	}

	public void setIso1field(String iso1field) {
		this.iso1field = iso1field;
	}

	public Cpx6BInteracDebitSequenceResponse() {
		super();
		this.setMessageType("6B.");
	}

	public static Cpx6BInteracDebitSequenceResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1) != str.length() || !str.startsWith("6B.")) {
			return null;
		}
		Cpx6BInteracDebitSequenceResponse resp = new Cpx6BInteracDebitSequenceResponse();
		resp.setStatus(str.substring(3, 4));
		resp.setTotalAmount(str.substring(4, 14));
		resp.setTipAmount(str.substring(14, 23));
		resp.setCashbackAmount(str.substring(23, 32));
		resp.setAccountSelected(str.substring(32, 33));
		resp.setPinBlockFormat(str.substring(33, 35));
		resp.setEncryptedPinBlock(str.substring(35, 51));
		if (str.length() >= 55) {
			resp.setTrack2length(str.substring(51, 53));
			int t2len = Integer.parseInt(str.substring(51, 53));
			resp.setIso2field(str.substring(53, 53 + t2len));

			resp.setTrack2length(str.substring(53 + t2len, 55 + t2len));
			int t1len = Integer.parseInt(str.substring(53 + t2len, 55 + t2len));
			resp.setIso1field(str.substring(55 + t2len, 55 + t2len + t1len));
		}
		return resp;
	}

}
