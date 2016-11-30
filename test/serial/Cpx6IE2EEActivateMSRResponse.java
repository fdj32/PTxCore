package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0142-07942-0225 Telium CPX E2EE solution v2.25.pdf {Page.14/35} {3.4. E2EE
 * Activate MSR with Display handling}
 * 
 * @author nfeng
 * 
 */
public class Cpx6IE2EEActivateMSRResponse extends CpxResponse {

	/**
	 * 3 ASCII chars decimal length of optional data fields i.e., all fields
	 * following this length. Not included when there is a track or other error.
	 */
	private String optionalDataLength;
	/**
	 * 1 ASCII hex char "0" – no key pressed – Not included when there is a
	 * track or other error; where key = A: F1 (Top left key) B: F2 B: F3 C: F4
	 * (Top right key) D: X (RED CANCEL key) E: < (YELLOW CORR. key) F: O (GREEN
	 * OK key)
	 */
	private String keyPressed;
	// Optional fields – not included if a key was pressed
	/**
	 * 6 ASCII chars clear value of the first 6 digits of the PAN
	 */
	private String panFirst6Digits;
	/**
	 * 4 ASCII chars clear value of the last 4 digits of the PAN
	 */
	private String panlast4Digits;
	/**
	 * 2 ASCII chars decimal length of the PAN
	 */
	private String panLength;
	/**
	 * ASCII chars "0" = PAN Failed mod 10 check "1" = PAN Passed mod 10 check
	 */
	private String panMod10CheckFlag;
	/**
	 * 4 ASCII chars clear value of the track 2 expiry date (YYMM)
	 */
	private String expiryDate;
	/**
	 * 3 ASCII chars clear value of the track 2 service code
	 */
	private String serviceCode;
	/**
	 * 1 ASCII chars track 2 card language indicator
	 */
	private String languageCode;
	/**
	 * 2 ASCII chars decimal length of the cardholder name
	 */
	private String cardholderNameLength;
	/**
	 * n ASCII chars clear value of the cardholder name
	 */
	private String cardholderName;
	/**
	 * ASCII chars "0" = clear ASCII data "1" = encrypted data
	 */
	private String cardDataEncryptedFlag;

	// If clear ASCII data:
	/**
	 * 2 ASCII chars decimal length of ISO 1 field
	 */
	private String track1length;
	/**
	 * n ASCII chars IS01 track if reading OK
	 */
	private String iso1field;
	/**
	 * 2 ASCII chars decimal length of ISO 2 field
	 */
	private String track2length;
	/**
	 * n ASCII chars IS02 track if reading OK
	 */
	private String iso2field;
	/**
	 * ASCII/KME/IngeCrypt all have this field, 1 ASCII chars track 2 card
	 * language indicator first digit
	 */
	private String extendedLangCode;

	// If KME/IngeCrypt encrypted data:
	/**
	 * 1 ASCII chars "A" = KME Base 24 data format, "B" = IngeCrypt (IC) data
	 * format
	 */
	private String encryptedFormatType;
	// If encrypted data:
	/**
	 * 2 ASCII chars decimal length of KME encrypted card data
	 */
	private String kmeCardDataLength;
	/**
	 * n ASCII chars KME encrypted card data if reading OK
	 */
	private String kmeCardDataField;
	/**
	 * 2 ASCII chars decimal length of AES encrypted PAN field
	 */
	private String aesPanLength;
	/**
	 * n ASCII chars AES encrypted PAN if reading OK
	 */
	private String aesPanField;
	/**
	 * 2 ASCII chars decimal length of AES/TDES encrypted card data field
	 */
	private String lsCardDataLength;
	/**
	 * n ASCII chars AES/TDES encrypted card data
	 */
	private String lsCardDataField;
	// private String extendedLangCode;

	// If IngeCrypt encrypted data:
	// private String encryptedFormatType;
	/**
	 * 24 ASCII chars DUKPT Key Serial Number (KSN) + custom field
	 */
	private String icKsn;
	/**
	 * 2 ASCII chars decimal length of IC encrypted card data
	 */
	private String icCardDataLength;
	/**
	 * n ASCII chars IC encrypted card data if reading OK
	 */
	private String icCardDataField;

	// private String aesPanLength;
	// private String aesPanField;
	// private String lsCardDataLength;
	// private String lsCardDataField;
	// private String extendedLangCode;
	public String getOptionalDataLength() {
		return optionalDataLength;
	}

	public void setOptionalDataLength(String optionalDataLength) {
		this.optionalDataLength = optionalDataLength;
	}

	public String getKeyPressed() {
		return keyPressed;
	}

	public void setKeyPressed(String keyPressed) {
		this.keyPressed = keyPressed;
	}

	public String getPanFirst6Digits() {
		return panFirst6Digits;
	}

	public void setPanFirst6Digits(String panFirst6Digits) {
		this.panFirst6Digits = panFirst6Digits;
	}

	public String getPanlast4Digits() {
		return panlast4Digits;
	}

	public void setPanlast4Digits(String panlast4Digits) {
		this.panlast4Digits = panlast4Digits;
	}

	public String getPanLength() {
		return panLength;
	}

	public void setPanLength(String panLength) {
		this.panLength = panLength;
	}

	public String getPanMod10CheckFlag() {
		return panMod10CheckFlag;
	}

	public void setPanMod10CheckFlag(String panMod10CheckFlag) {
		this.panMod10CheckFlag = panMod10CheckFlag;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getCardholderNameLength() {
		return cardholderNameLength;
	}

	public void setCardholderNameLength(String cardholderNameLength) {
		this.cardholderNameLength = cardholderNameLength;
	}

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

	public String getCardDataEncryptedFlag() {
		return cardDataEncryptedFlag;
	}

	public void setCardDataEncryptedFlag(String cardDataEncryptedFlag) {
		this.cardDataEncryptedFlag = cardDataEncryptedFlag;
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

	public String getExtendedLangCode() {
		return extendedLangCode;
	}

	public void setExtendedLangCode(String extendedLangCode) {
		this.extendedLangCode = extendedLangCode;
	}

	public String getEncryptedFormatType() {
		return encryptedFormatType;
	}

	public void setEncryptedFormatType(String encryptedFormatType) {
		this.encryptedFormatType = encryptedFormatType;
	}

	public String getKmeCardDataLength() {
		return kmeCardDataLength;
	}

	public void setKmeCardDataLength(String kmeCardDataLength) {
		this.kmeCardDataLength = kmeCardDataLength;
	}

	public String getKmeCardDataField() {
		return kmeCardDataField;
	}

	public void setKmeCardDataField(String kmeCardDataField) {
		this.kmeCardDataField = kmeCardDataField;
	}

	public String getAesPanLength() {
		return aesPanLength;
	}

	public void setAesPanLength(String aesPanLength) {
		this.aesPanLength = aesPanLength;
	}

	public String getAesPanField() {
		return aesPanField;
	}

	public void setAesPanField(String aesPanField) {
		this.aesPanField = aesPanField;
	}

	public String getLsCardDataLength() {
		return lsCardDataLength;
	}

	public void setLsCardDataLength(String lsCardDataLength) {
		this.lsCardDataLength = lsCardDataLength;
	}

	public String getLsCardDataField() {
		return lsCardDataField;
	}

	public void setLsCardDataField(String lsCardDataField) {
		this.lsCardDataField = lsCardDataField;
	}

	public String getIcKsn() {
		return icKsn;
	}

	public void setIcKsn(String icKsn) {
		this.icKsn = icKsn;
	}

	public String getIcCardDataLength() {
		return icCardDataLength;
	}

	public void setIcCardDataLength(String icCardDataLength) {
		this.icCardDataLength = icCardDataLength;
	}

	public String getIcCardDataField() {
		return icCardDataField;
	}

	public void setIcCardDataField(String icCardDataField) {
		this.icCardDataField = icCardDataField;
	}

	public Cpx6IE2EEActivateMSRResponse() {
		super();
		this.setMessageType("6I.");
	}

	public static Cpx6IE2EEActivateMSRResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1 + 3 + 1) > str.length() || !str.startsWith("6I.")) {
			return null;
		}
		Cpx6IE2EEActivateMSRResponse resp = new Cpx6IE2EEActivateMSRResponse();
		resp.setStatus(str.substring(3, 4));
		resp.setOptionalDataLength(str.substring(4, 7));
		resp.setKeyPressed(str.substring(7, 8));
		if (str.length() > 8 && "0".equals(resp.getKeyPressed())) {
			resp.setPanFirst6Digits(str.substring(8, 14));
			resp.setPanlast4Digits(str.substring(14, 18));
			resp.setPanLength(str.substring(18, 20));
			resp.setPanMod10CheckFlag(str.substring(20, 21));
			resp.setExpiryDate(str.substring(21, 25));
			resp.setServiceCode(str.substring(25, 28));
			resp.setLanguageCode(str.substring(28, 29));
			resp.setCardholderNameLength(str.substring(29, 31));
			int cnlen = Integer.parseInt(resp.getCardholderNameLength());
			resp.setCardholderName(str.substring(31, 31 + cnlen));
			resp.setCardDataEncryptedFlag(str.substring(31 + cnlen, 32 + cnlen));
			if ("0".equals(resp.getCardDataEncryptedFlag())) {
				// ASCII data
				resp.setTrack1length(str.substring(32 + cnlen, 34 + cnlen));
				int t1len = Integer.parseInt(resp.getTrack1length());
				resp.setIso1field(str.substring(34 + cnlen, 34 + cnlen + t1len));
				resp.setTrack2length(str.substring(34 + cnlen + t1len, 36
						+ cnlen + t1len));
				int t2len = Integer.parseInt(resp.getTrack2length());
				resp.setIso2field(str.substring(36 + cnlen + t1len, 36 + cnlen
						+ t1len + t2len));
				resp.setExtendedLangCode(str.substring(36 + cnlen + t1len
						+ t2len, 37 + cnlen + t1len + t2len));
			} else if ("1".equals(resp.getCardDataEncryptedFlag())) {
				// Encrypted data
				int aeslen, lslen;
				resp.setEncryptedFormatType(str.substring(32 + cnlen,
						33 + cnlen));
				if ("A".equals(resp.getEncryptedFormatType())) {
					// KME
					resp.setKmeCardDataLength(str.substring(33 + cnlen,
							35 + cnlen));
					int kmelen = Integer.parseInt(resp.getKmeCardDataLength());
					resp.setKmeCardDataField(str.substring(35 + cnlen, 35
							+ cnlen + kmelen));
					resp.setAesPanLength(str.substring(35 + cnlen + kmelen, 37
							+ cnlen + kmelen));
					aeslen = Integer.parseInt(resp.getAesPanLength());
					resp.setAesPanField(str.substring(37 + cnlen + kmelen, 37
							+ cnlen + kmelen + aeslen));
					resp.setLsCardDataLength(str.substring(37 + cnlen + kmelen
							+ aeslen, 39 + cnlen + kmelen + aeslen));
					lslen = Integer.parseInt(resp.getLsCardDataLength());
					resp.setLsCardDataField(str.substring(39 + cnlen + kmelen
							+ aeslen, 39 + cnlen + kmelen + aeslen + lslen));
					resp.setExtendedLangCode(str.substring(39 + cnlen + kmelen
							+ aeslen + lslen, 40 + cnlen + kmelen + aeslen
							+ lslen));
				} else if ("B".equals(resp.getEncryptedFormatType())) {
					// IngeCrypt
					resp.setIcKsn(str.substring(33 + cnlen, 57 + cnlen));
					resp.setIcCardDataLength(str.substring(57 + cnlen,
							59 + cnlen));
					int iclen = Integer.parseInt(resp.getIcCardDataLength());
					resp.setIcCardDataField(str.substring(59 + cnlen, 59
							+ cnlen + iclen));
					resp.setAesPanLength(str.substring(59 + cnlen + iclen, 61
							+ cnlen + iclen));
					aeslen = Integer.parseInt(resp.getAesPanLength());
					resp.setAesPanField(str.substring(61 + cnlen + iclen, 61
							+ cnlen + iclen + aeslen));
					resp.setLsCardDataLength(str.substring(61 + cnlen + iclen
							+ aeslen, 63 + cnlen + iclen + aeslen));
					lslen = Integer.parseInt(resp.getLsCardDataLength());
					resp.setLsCardDataField(str.substring(63 + cnlen + iclen
							+ aeslen, 63 + cnlen + iclen + aeslen + lslen));
					resp.setExtendedLangCode(str.substring(63 + cnlen + iclen
							+ aeslen + lslen, 64 + cnlen + iclen + aeslen
							+ lslen));
				}
			}
		}
		return resp;
	}
}
