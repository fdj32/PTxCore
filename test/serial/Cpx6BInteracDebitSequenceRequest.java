package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.123/184} {6B. Interac Debit
 * Sequence}
 * 
 * @author nfeng
 *
 */
public class Cpx6BInteracDebitSequenceRequest extends CpxRequest {

	/**
	 * "0" – English, "1" – French
	 */
	private String language;
	/**
	 * 2 ASCII chars timeout in seconds – "00" = no timeout
	 */
	private String swipeCardTimeout;
	/**
	 * 1 ASCII char "0" Card Swipe disabled "1" ISO2 magnetic card reading "2"
	 * ISO1 + ISO2 magnetic card reading
	 */
	private String trackNumber;
	/**
	 * 1 ASCII character "0" – don't perform validation "1" – validate service
	 * code.
	 */
	private String serviceCodeFlag;
	/**
	 * 2 ASCII chars timeout in seconds – "00" = no timeout
	 */
	private String dataEntryTimeout;
	/**
	 * 1 ASCII character "0" = n o time -out "1" to "F" - 5 to 75 seconds,
	 * lowercase 'a' -'f' is treated as the corresponding uppercase letter.
	 */
	private String pinEntryTimeout;
	/**
	 * 4 ASCII characters Field ignored for U32 devices
	 */
	private String pinDisplayprompt;
	/**
	 * 9 characters no decimal point (fixed length with leading zeros)
	 */
	private String amount;
	/**
	 * 1 ASCII character "0" – Disabled, "1" – Enabled; Tip will be prompted
	 */
	private String tipEntryEnabled;
	/**
	 * 1 ASCII character "0" – Disabled, "1" – Enabled; Cashback will be
	 * prompted
	 */
	private String cashbackEnabled;
	/**
	 * 1 ASCII character Application -Based:"0" - "2" – field is ignored (see
	 * note below). Terminal -Based: "0" -"o" in ASCII ( 64 Keys)
	 */
	private String masterKeyIndicator;
	/**
	 * 1 ASCII character "S" – single length session key included "D" – double
	 * length session key included, "0" – no session key included
	 */
	private String sessionKeyLength;
	/**
	 * 16 hex ASCII characters (If the SK is stored this value will be ignored
	 * although the length is checked) 32 hex ASCII characters (If the SK is
	 * stored this value will be ignored although the length is checked)
	 */
	private String encryptedSessionKey;
	/**
	 * Double:8 hex ASCII characters check value to be verified before the key
	 * is used
	 */
	private String checkValue;
	/**
	 * 16 hex ASCII characters Field only used if Track # setting is set to
	 * "Card Swipe Disabled" otherwise this field must not be included in the
	 * request.
	 */
	private String primaryAccountNumber;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSwipeCardTimeout() {
		return swipeCardTimeout;
	}

	public void setSwipeCardTimeout(String swipeCardTimeout) {
		this.swipeCardTimeout = swipeCardTimeout;
	}

	public String getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	public String getServiceCodeFlag() {
		return serviceCodeFlag;
	}

	public void setServiceCodeFlag(String serviceCodeFlag) {
		this.serviceCodeFlag = serviceCodeFlag;
	}

	public String getDataEntryTimeout() {
		return dataEntryTimeout;
	}

	public void setDataEntryTimeout(String dataEntryTimeout) {
		this.dataEntryTimeout = dataEntryTimeout;
	}

	public String getPinEntryTimeout() {
		return pinEntryTimeout;
	}

	public void setPinEntryTimeout(String pinEntryTimeout) {
		this.pinEntryTimeout = pinEntryTimeout;
	}

	public String getPinDisplayprompt() {
		return pinDisplayprompt;
	}

	public void setPinDisplayprompt(String pinDisplayprompt) {
		this.pinDisplayprompt = pinDisplayprompt;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTipEntryEnabled() {
		return tipEntryEnabled;
	}

	public void setTipEntryEnabled(String tipEntryEnabled) {
		this.tipEntryEnabled = tipEntryEnabled;
	}

	public String getCashbackEnabled() {
		return cashbackEnabled;
	}

	public void setCashbackEnabled(String cashbackEnabled) {
		this.cashbackEnabled = cashbackEnabled;
	}

	public String getMasterKeyIndicator() {
		return masterKeyIndicator;
	}

	public void setMasterKeyIndicator(String masterKeyIndicator) {
		this.masterKeyIndicator = masterKeyIndicator;
	}

	public String getSessionKeyLength() {
		return sessionKeyLength;
	}

	public void setSessionKeyLength(String sessionKeyLength) {
		this.sessionKeyLength = sessionKeyLength;
	}

	public String getEncryptedSessionKey() {
		return encryptedSessionKey;
	}

	public void setEncryptedSessionKey(String encryptedSessionKey) {
		this.encryptedSessionKey = encryptedSessionKey;
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	public String getPrimaryAccountNumber() {
		return primaryAccountNumber;
	}

	public void setPrimaryAccountNumber(String primaryAccountNumber) {
		this.primaryAccountNumber = primaryAccountNumber;
	}

	public Cpx6BInteracDebitSequenceRequest() {
		super();
		this.setMessageType("6B.");
	}
	
	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getLanguage();
		msg += this.getSwipeCardTimeout();
		msg += this.getTrackNumber();
		msg += this.getServiceCodeFlag();
		msg += this.getDataEntryTimeout();
		msg += this.getPinDisplayprompt();
		msg += this.getAmount();
		msg += this.getTipEntryEnabled();
		msg += this.getCashbackEnabled();
		msg += this.getMasterKeyIndicator();
		msg += this.getSessionKeyLength();
		msg += StringUtils.defaultString(this.getEncryptedSessionKey());
		msg += StringUtils.defaultString(this.getCheckValue());
		msg += this.getPrimaryAccountNumber();
		return msg;
	}
}
