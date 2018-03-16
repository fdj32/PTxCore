package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * DETAIL RESPONSE RECORD, TYPE DR
 * 
 * @author nickfeng
 *
 */
public class DetailResponse {

	/**
	 * Record Type, 1-2, AN 2, Always DR.
	 */
	private String recordType = "DR";

	/**
	 * Processing Code, 3-8, AN 6, Code identifying the action to be taken with the
	 * transaction. Refer to Processing Codes on page A-16 for a list of values.
	 */
	private String processingCode;

	/* Filler, 9-9, AN 1, Space Filled. */

	/**
	 * Action Code, 10-12, AN 3, Code identifying the issuer's response to the
	 * transaction request. Refer to Action Codes on page A-2 for a list of valid
	 * values.
	 */
	private String actionCode;

	/**
	 * Card Type, 13-14, AN 2, 2-character code indicating card type for this
	 * transaction. Refer to Card Type Codes on page A-7 for a list of valid values.
	 */
	private String cardType;

	/**
	 * Approval Code, 15-20, AN 6, Approval code returned by the issuer on approved
	 * transactions. Space filled on non-approved transactions.
	 */
	private String approvalCode;

	/**
	 * Verbiage, 21-28, AN 8, Verbiage describing the action code. May be generated
	 * by Global Payments or passed through from the issuer.
	 */
	private String verbiage;

	/**
	 * Card Account Number, 29-47, AN 19, Card number for this transaction. Left
	 * justified, space filled.
	 */
	private String cardAccountNumber;

	/* Filler, 48-52, AN 5, Space Filled. */

	/**
	 * Expiration Date, 53-56, AN 4, Expiration Date from the card in YYMM format.
	 * If not available, space filled.
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
	 * Amount 1 field. Used for reporting purposes on Purchase transactions. For
	 * Commercial Card transactions, represents the tax amount. Field is zero filled
	 * if not used. Right justified, zero filled.
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

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getVerbiage() {
		return verbiage;
	}

	public void setVerbiage(String verbiage) {
		this.verbiage = verbiage;
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
		sb.append(' ');
		sb.append(actionCode);
		sb.append(cardType);
		sb.append(approvalCode);
		sb.append(verbiage);
		sb.append(StringUtils.rightPad(cardAccountNumber, 19, ' '));
		sb.append(StringUtils.repeat(" ", 5));
		sb.append(expirationDate);
		sb.append(StringUtils.leftPad(amount1, 10, '0'));
		sb.append(StringUtils.leftPad(amount2, 10, '0'));
		sb.append(StringUtils.repeat(" ", 4));
		return sb.toString();
	}

	public static DetailResponse fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("DR")) {
			return null;
		}
		DetailResponse dr = new DetailResponse();
		dr.setProcessingCode(s.substring(2, 8));
		dr.setActionCode(s.substring(9, 12));
		dr.setCardType(s.substring(12, 14));
		dr.setApprovalCode(s.substring(14, 20));
		dr.setVerbiage(s.substring(20, 28));
		dr.setCardAccountNumber(s.substring(28, 47));
		dr.setExpirationDate(s.substring(52, 56));
		dr.setAmount1(s.substring(56, 66));
		dr.setAmount2(s.substring(66, 76));
		return dr;
	}

}
