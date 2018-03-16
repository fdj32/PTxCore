package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * DETAIL AUTHORIZATION, PURCHASE, RETURN, OR AVS-ONLY REQUEST RECORD, TYPE DR
 * 
 * @author nickfeng
 *
 */
public class DetailRequest {

	/**
	 * Record Type, 1-2, AN 2, Always DR.
	 */
	private String recordType = "DR";

	/**
	 * Processing Code, 3-8, AN 6, Code identifying the action to be taken with the
	 * transaction. Refer to Processing Codes on page A-16 for a list of values.
	 */
	private String processingCode;

	/**
	 * Cardholder Presence Indicator, 9-9, AN 1, Value indicating whether the
	 * cardholder was presentat the time of the transaction. Valid values are:
	 * <ul>
	 * <li>0 - Present</li>
	 * <li>1 - Not present</li>
	 * <li>2 - Not present (mail order)</li>
	 * <li>3 - Not present (phone order)</li>
	 * <li>4 - Not present (recurring billing)</li>
	 * <li>5 - Electronic Order (home PC, Internet)</li>
	 * <li>6 - Not present (standing/credential on file authorization)</li>
	 * <li>7 - Not present (initial credential on file storage)</li>
	 * </ul>
	 */
	private char cardholderPresenceIndicator;

	/**
	 * UCAF Collection Indicator, 10-10, AN 1, Values:
	 * <ul>
	 * <li>Space - Non-internet transaction</li>
	 * <li>0 - UCAF not supported by the merchant website</li>
	 * </ul>
	 * This field should be populated with '0' for all MasterCard electronic
	 * commerce transactions.
	 */
	private char ucafCollectionIndicator;

	/* Filler, 11-28, AN 18, Space Filled. */

	/**
	 * Card Account Number, 29-47, AN 19, Card number for this transaction. Left
	 * justified, space filled.
	 */
	private String cardAccountNumber;

	/* Filler, 48-52, AN 5, Space Filled. */

	/**
	 * Expiration Date, 53-56, AN 4, Expiration Date from the card in YYMM format.
	 */
	private String expirationDate;

	/**
	 * Amount 1, 57-66, N 10, Total amount of the transaction, includes any amount
	 * in the Amount 2 field. Right justified, zero filled. Decimal places based on
	 * the transaction currency.
	 */
	private String amount1;

	/**
	 * Amount 2, 67-76, N 10, Included in the transaction amount contained in the
	 * Amount 1 field. Used for reporting purposes on purchase transactions. For
	 * Commercial Card transactions, represents tax amount. Field is zero filled if
	 * not used. Right justified, zero filled.
	 */
	private String amount2;

	/* Filler, 77-80, AN 4, Space Filled. */

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getProcessingCode() {
		return processingCode;
	}

	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}

	public char getCardholderPresenceIndicator() {
		return cardholderPresenceIndicator;
	}

	public void setCardholderPresenceIndicator(char cardholderPresenceIndicator) {
		this.cardholderPresenceIndicator = cardholderPresenceIndicator;
	}

	public char getUcafCollectionIndicator() {
		return ucafCollectionIndicator;
	}

	public void setUcafCollectionIndicator(char ucafCollectionIndicator) {
		this.ucafCollectionIndicator = ucafCollectionIndicator;
	}

	public String getCardAccountNumber() {
		return cardAccountNumber;
	}

	public void setCardAccountNumber(String cardAccountNumber) {
		this.cardAccountNumber = cardAccountNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getAmount1() {
		return amount1;
	}

	public void setAmount1(String amount1) {
		this.amount1 = amount1;
	}

	public String getAmount2() {
		return amount2;
	}

	public void setAmount2(String amount2) {
		this.amount2 = amount2;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(processingCode);
		sb.append(cardholderPresenceIndicator);
		sb.append(ucafCollectionIndicator);
		sb.append(StringUtils.repeat(" ", 18));
		sb.append(StringUtils.rightPad(cardAccountNumber, 19, ' '));
		sb.append(StringUtils.repeat(" ", 5));
		sb.append(expirationDate);
		sb.append(StringUtils.leftPad(amount1, 10, '0'));
		sb.append(StringUtils.leftPad(amount2, 10, '0'));
		sb.append(StringUtils.repeat(" ", 4));
		return sb.toString();
	}

	public static DetailRequest fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("DR")) {
			return null;
		}
		DetailRequest dr = new DetailRequest();
		dr.setProcessingCode(s.substring(2, 8));
		dr.setCardholderPresenceIndicator(s.charAt(8));
		dr.setUcafCollectionIndicator(s.charAt(9));
		dr.setCardAccountNumber(s.substring(28, 47));
		dr.setExpirationDate(s.substring(52, 56));
		dr.setAmount1(s.substring(56, 66));
		dr.setAmount2(s.substring(66, 76));
		return dr;
	}

}
