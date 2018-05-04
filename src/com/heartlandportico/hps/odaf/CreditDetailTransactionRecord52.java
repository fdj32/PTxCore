package com.heartlandportico.hps.odaf;

import org.apache.commons.lang.StringUtils;

public class CreditDetailTransactionRecord52 {

	/**
	 * 1-10, Refer to Control Section – Record Type = '52'
	 */
	private ControlSection cs;

	/**
	 * 11-14
	 * <ul>
	 * <li>‘VISN’ - Visa</li>
	 * <li>‘MCRD’ - Mastercard</li>
	 * <li>‘DISC’ - Discover</li>
	 * <li>‘AMEX’ - American Express</li>
	 * <li>‘WEX ‘ - Wright Express</li>
	 * <li>‘VOYG’ - Voyager</li>
	 * <li>‘PIND’ - Pin Debit</li>
	 * <li>‘DINR’ – Diners</li>
	 * <li>‘EBT ‘ - EBT</li>
	 * </ul>
	 */
	private String cardholderInstitutionId;

	/* Filler, 15-18, AN 4, Space Filled. */

	/**
	 * 19-34, Locator Number
	 */
	private String locatorNumber;

	/**
	 * 35-38, SIC/MCC Code
	 */
	private String sicmccCode;

	/**
	 * 39-47, Authorized Amount
	 */
	private String authorizedAmount;

	/* Filler, 48-53, AN 6, Space Filled. */

	/**
	 * 54, Cardholder ID Method
	 */
	private char cardholderIdMethod;

	/**
	 * 55, Authorization Source
	 */
	private char authorizationSource;

	/**
	 * 56, Mail/Phone Indicator
	 */
	private char mailPhoneIndicator;

	/**
	 * 57, CAT Indicator
	 */
	private char catIndicator;

	/**
	 * 58, Card Program Type
	 * <ul>
	 * <li>‘D’ - Signature Debit</li>
	 * <li>‘C’ - Signature Credit</li>
	 * <li>‘R’ – Deferred Debit</li>
	 * <li>‘P’- Prepaid</li>
	 * <li>‘H’ – Charge(treat as Credit)</li>
	 * <li>‘1’ – EBT</li>
	 * <li>‘2’ – PIN Debit</li>
	 * <li>‘3’ – PINless Debit</li>
	 * </ul>
	 * The definition of values on this columns is subject to change if the card
	 * brands define new card product categories.
	 */
	private char cardProgramType;

	/**
	 * 59-61, Debit Network ID
	 */
	private String debitNetworkId;

	/**
	 * 62-64, Front End Vendor ID
	 */
	private String frontEndVendorId;

	/**
	 * 65-67, Premier FPI
	 */
	private String premierFPI;

	/**
	 * 68-76, Cash Back Amount
	 */
	private String cashBackAmount;

	/**
	 * 77, Is it an EMV Transaction?
	 * <ul>
	 * <li>Y – Yes</li>
	 * <li>N – No</li>
	 * </ul>
	 * This field indicates if the transaction is processed as EMV or not.IT does
	 * not indicate if the card or terminal or both were EMV or not
	 */
	private char emvTransaction;

	/* Filler, 78-250, AN 173, Space Filled. */

	public ControlSection getCs() {
		return cs;
	}

	public void setCs(ControlSection cs) {
		this.cs = cs;
	}

	public String getCardholderInstitutionId() {
		return cardholderInstitutionId;
	}

	public void setCardholderInstitutionId(String cardholderInstitutionId) {
		this.cardholderInstitutionId = cardholderInstitutionId;
	}

	public String getLocatorNumber() {
		return locatorNumber;
	}

	public void setLocatorNumber(String locatorNumber) {
		this.locatorNumber = locatorNumber;
	}

	public String getSicmccCode() {
		return sicmccCode;
	}

	public void setSicmccCode(String sicmccCode) {
		this.sicmccCode = sicmccCode;
	}

	public String getAuthorizedAmount() {
		return authorizedAmount;
	}

	public void setAuthorizedAmount(String authorizedAmount) {
		this.authorizedAmount = authorizedAmount;
	}

	public char getCardholderIdMethod() {
		return cardholderIdMethod;
	}

	public void setCardholderIdMethod(char cardholderIdMethod) {
		this.cardholderIdMethod = cardholderIdMethod;
	}

	public char getAuthorizationSource() {
		return authorizationSource;
	}

	public void setAuthorizationSource(char authorizationSource) {
		this.authorizationSource = authorizationSource;
	}

	public char getMailPhoneIndicator() {
		return mailPhoneIndicator;
	}

	public void setMailPhoneIndicator(char mailPhoneIndicator) {
		this.mailPhoneIndicator = mailPhoneIndicator;
	}

	public char getCatIndicator() {
		return catIndicator;
	}

	public void setCatIndicator(char catIndicator) {
		this.catIndicator = catIndicator;
	}

	public char getCardProgramType() {
		return cardProgramType;
	}

	public void setCardProgramType(char cardProgramType) {
		this.cardProgramType = cardProgramType;
	}

	public String getDebitNetworkId() {
		return debitNetworkId;
	}

	public void setDebitNetworkId(String debitNetworkId) {
		this.debitNetworkId = debitNetworkId;
	}

	public String getFrontEndVendorId() {
		return frontEndVendorId;
	}

	public void setFrontEndVendorId(String frontEndVendorId) {
		this.frontEndVendorId = frontEndVendorId;
	}

	public String getPremierFPI() {
		return premierFPI;
	}

	public void setPremierFPI(String premierFPI) {
		this.premierFPI = premierFPI;
	}

	public String getCashBackAmount() {
		return cashBackAmount;
	}

	public void setCashBackAmount(String cashBackAmount) {
		this.cashBackAmount = cashBackAmount;
	}

	public char getEmvTransaction() {
		return emvTransaction;
	}

	public void setEmvTransaction(char emvTransaction) {
		this.emvTransaction = emvTransaction;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(cs.toString());
		sb.append(cardholderInstitutionId);
		sb.append(StringUtils.repeat(" ", 4));
		sb.append(locatorNumber);
		sb.append(sicmccCode);
		sb.append(authorizedAmount);
		sb.append(StringUtils.repeat(" ", 6));
		sb.append(cardholderIdMethod);
		sb.append(authorizationSource);
		sb.append(mailPhoneIndicator);
		sb.append(catIndicator);
		sb.append(cardProgramType);
		sb.append(debitNetworkId);
		sb.append(frontEndVendorId);
		sb.append(premierFPI);
		sb.append(cashBackAmount);
		sb.append(emvTransaction);
		sb.append(StringUtils.repeat(" ", 171));
		return sb.toString();
	}

	public static CreditDetailTransactionRecord52 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 250) {
			return null;
		}
		CreditDetailTransactionRecord52 o = new CreditDetailTransactionRecord52();
		o.setCs(ControlSection.fromString(s.substring(0, 10)));
		o.setCardholderInstitutionId(s.substring(10, 14));
		o.setLocatorNumber(s.substring(18, 34));
		o.setSicmccCode(s.substring(34, 38));
		o.setAuthorizedAmount(s.substring(38, 47));
		o.setCardholderIdMethod(s.charAt(53));
		o.setAuthorizationSource(s.charAt(54));
		o.setMailPhoneIndicator(s.charAt(55));
		o.setCatIndicator(s.charAt(56));
		o.setCardProgramType(s.charAt(57));
		o.setDebitNetworkId(s.substring(58, 61));
		o.setFrontEndVendorId(s.substring(61, 64));
		o.setPremierFPI(s.substring(64, 67));
		o.setCashBackAmount(s.substring(67, 76));
		o.setEmvTransaction(s.charAt(77));
		return o;
	}

}
