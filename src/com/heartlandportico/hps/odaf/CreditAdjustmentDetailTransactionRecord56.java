package com.heartlandportico.hps.odaf;

import org.apache.commons.lang.StringUtils;

public class CreditAdjustmentDetailTransactionRecord56 {

	/**
	 * 1-10, Refer to Control Section – Record Type = '56'
	 */
	private ControlSection cs;

	/**
	 * 11-25
	 */
	private String merchantIdNumber;

	/**
	 * 26-33, YYYYMMDD
	 */
	private String fundingDate;
	/**
	 * 34-41, YYYYMMDD
	 */
	private String adjustmentDate;
	/**
	 * 42-49, YYYYMMDD
	 */
	private String originalTxnDate;

	/**
	 * 50-69, Type of Adjustment
	 */
	private String txnType;

	/**
	 * 70-88, Cardholder Number masked
	 */
	private String accountNumber;

	/**
	 * 89-111, Default to spaces
	 */
	private String acquirerReferenceNumber;

	/**
	 * 112-115, Default to spaces
	 *
	 */
	private String sicmccCode;

	/**
	 * 116-149, Default to spaces
	 */
	private String adjustmentReason;

	/**
	 * 150-158, Leading zeroes. Implied 2 decimal places
	 */
	private String adjustmentAmount;

	/**
	 * 159, Credit – C, Debit – D
	 */
	private char adjustmentSign;

	/**
	 * 160-175, Default to spaces
	 */
	private String locatorNumber;

	/* Filler, 176-250, AN 75, Space Filled. */

	public String getMerchantIdNumber() {
		return merchantIdNumber;
	}

	public void setMerchantIdNumber(String merchantIdNumber) {
		this.merchantIdNumber = merchantIdNumber;
	}

	public String getFundingDate() {
		return fundingDate;
	}

	public void setFundingDate(String fundingDate) {
		this.fundingDate = fundingDate;
	}

	public String getAdjustmentDate() {
		return adjustmentDate;
	}

	public void setAdjustmentDate(String adjustmentDate) {
		this.adjustmentDate = adjustmentDate;
	}

	public String getOriginalTxnDate() {
		return originalTxnDate;
	}

	public void setOriginalTxnDate(String originalTxnDate) {
		this.originalTxnDate = originalTxnDate;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAcquirerReferenceNumber() {
		return acquirerReferenceNumber;
	}

	public void setAcquirerReferenceNumber(String acquirerReferenceNumber) {
		this.acquirerReferenceNumber = acquirerReferenceNumber;
	}

	public String getSicmccCode() {
		return sicmccCode;
	}

	public void setSicmccCode(String sicmccCode) {
		this.sicmccCode = sicmccCode;
	}

	public String getAdjustmentReason() {
		return adjustmentReason;
	}

	public void setAdjustmentReason(String adjustmentReason) {
		this.adjustmentReason = adjustmentReason;
	}

	public String getAdjustmentAmount() {
		return adjustmentAmount;
	}

	public void setAdjustmentAmount(String adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}

	public char getAdjustmentSign() {
		return adjustmentSign;
	}

	public void setAdjustmentSign(char adjustmentSign) {
		this.adjustmentSign = adjustmentSign;
	}

	public String getLocatorNumber() {
		return locatorNumber;
	}

	public void setLocatorNumber(String locatorNumber) {
		this.locatorNumber = locatorNumber;
	}

	public ControlSection getCs() {
		return cs;
	}

	public void setCs(ControlSection cs) {
		this.cs = cs;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(cs.toString());
		sb.append(merchantIdNumber);
		sb.append(fundingDate);
		sb.append(adjustmentDate);
		sb.append(originalTxnDate);
		sb.append(txnType);
		sb.append(accountNumber);
		sb.append(acquirerReferenceNumber);
		sb.append(sicmccCode);
		sb.append(adjustmentReason);
		sb.append(adjustmentAmount);
		sb.append(adjustmentSign);
		sb.append(locatorNumber);
		sb.append(StringUtils.repeat(" ", 75));
		return sb.toString();
	}

	public static CreditAdjustmentDetailTransactionRecord56 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 250) {
			return null;
		}
		CreditAdjustmentDetailTransactionRecord56 o = new CreditAdjustmentDetailTransactionRecord56();
		o.setCs(ControlSection.fromString(s.substring(0, 10)));
		o.setMerchantIdNumber(s.substring(10, 25));
		o.setFundingDate(s.substring(25, 33));
		o.setAdjustmentDate(s.substring(33, 41));
		o.setOriginalTxnDate(s.substring(41, 49));
		o.setAccountNumber(s.substring(49, 69));
		o.setAcquirerReferenceNumber(s.substring(69, 111));
		o.setSicmccCode(s.substring(111, 115));
		o.setAdjustmentReason(s.substring(115, 149));
		o.setAdjustmentAmount(s.substring(149, 158));
		o.setAdjustmentSign(s.charAt(158));
		o.setLocatorNumber(s.substring(159, 175));
		return o;
	}

}
