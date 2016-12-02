package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author nfeng
 * @see Cpx6IE2EEActivateMSRResponse
 */
public class E2EEData {

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

	public static E2EEData parse(String s) {
		if (StringUtils.isEmpty(s)) {
			return null;
		}
		E2EEData e = new E2EEData();
		e.setPanFirst6Digits(s.substring(0, 6));
		e.setPanlast4Digits(s.substring(6, 10));
		e.setPanLength(s.substring(10, 12));
		e.setPanMod10CheckFlag(s.substring(12, 13));
		e.setExpiryDate(s.substring(13, 17));
		e.setServiceCode(s.substring(17, 20));
		e.setLanguageCode(s.substring(20, 21));
		e.setCardholderNameLength(s.substring(21, 23));
		int cnlen = Integer.parseInt(e.getCardholderNameLength());
		e.setCardholderName(s.substring(23, 23 + cnlen));
		e.setCardDataEncryptedFlag(s.substring(23 + cnlen, 24 + cnlen));
		if ("0".equals(e.getCardDataEncryptedFlag())) {
			// ASCII data
			e.setTrack1length(s.substring(24 + cnlen, 26 + cnlen));
			int t1len = Integer.parseInt(e.getTrack1length());
			e.setIso1field(s.substring(26 + cnlen, 26 + cnlen + t1len));
			e.setTrack2length(s.substring(26 + cnlen + t1len, 28 + cnlen
					+ t1len));
			int t2len = Integer.parseInt(e.getTrack2length());
			e.setIso2field(s.substring(28 + cnlen + t1len, 28 + cnlen + t1len
					+ t2len));
			e.setExtendedLangCode(s.substring(28 + cnlen + t1len + t2len, 29
					+ cnlen + t1len + t2len));
		} else if ("1".equals(e.getCardDataEncryptedFlag())) {
			// Encrypted data
			int aeslen, lslen;
			e.setEncryptedFormatType(s.substring(24 + cnlen, 25 + cnlen));
			if ("A".equals(e.getEncryptedFormatType())) {
				// KME
				e.setKmeCardDataLength(s.substring(25 + cnlen, 27 + cnlen));
				int kmelen = Integer.parseInt(e.getKmeCardDataLength());
				e.setKmeCardDataField(s.substring(27 + cnlen, 27 + cnlen
						+ kmelen));
				e.setAesPanLength(s.substring(27 + cnlen + kmelen, 29 + cnlen
						+ kmelen));
				aeslen = Integer.parseInt(e.getAesPanLength());
				e.setAesPanField(s.substring(29 + cnlen + kmelen, 29 + cnlen
						+ kmelen + aeslen));
				e.setLsCardDataLength(s.substring(29 + cnlen + kmelen + aeslen,
						31 + cnlen + kmelen + aeslen));
				lslen = Integer.parseInt(e.getLsCardDataLength());
				e.setLsCardDataField(s.substring(31 + cnlen + kmelen + aeslen,
						31 + cnlen + kmelen + aeslen + lslen));
				e.setExtendedLangCode(s.substring(31 + cnlen + kmelen + aeslen
						+ lslen, 32 + cnlen + kmelen + aeslen + lslen));
			} else if ("B".equals(e.getEncryptedFormatType())) {
				// IngeCrypt
				e.setIcKsn(s.substring(25 + cnlen, 49 + cnlen));
				e.setIcCardDataLength(s.substring(49 + cnlen, 51 + cnlen));
				int iclen = Integer.parseInt(e.getIcCardDataLength());
				e.setIcCardDataField(s
						.substring(51 + cnlen, 51 + cnlen + iclen));
				e.setAesPanLength(s.substring(51 + cnlen + iclen, 53 + cnlen
						+ iclen));
				aeslen = Integer.parseInt(e.getAesPanLength());
				e.setAesPanField(s.substring(53 + cnlen + iclen, 53 + cnlen
						+ iclen + aeslen));
				e.setLsCardDataLength(s.substring(53 + cnlen + iclen + aeslen,
						55 + cnlen + iclen + aeslen));
				lslen = Integer.parseInt(e.getLsCardDataLength());
				e.setLsCardDataField(s.substring(55 + cnlen + iclen + aeslen,
						55 + cnlen + iclen + aeslen + lslen));
				e.setExtendedLangCode(s.substring(55 + cnlen + iclen + aeslen
						+ lslen, 56 + cnlen + iclen + aeslen + lslen));
			}
		}
		return e;
	}

}
