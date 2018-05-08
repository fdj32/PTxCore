package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class MerchantTotalFunding15 {

	/**
	 * 1-2, '15'
	 */
	private String recordType;

	/**
	 * 3-8, Sequential number of the record within file. Incremented by 1 for each
	 * record.
	 */
	private String recordSequenceNumber;

	/**
	 * 9-14, YYMMDD
	 */
	private String fundingDate;

	/**
	 * 15-30
	 */
	private String merchantNumber;

	/**
	 * 31-60
	 */
	private String merchantName;

	/**
	 * 61-71
	 */
	private String salesSubmittedAmount;

	/**
	 * 72
	 */
	private char salesSubmittedSign;

	/**
	 * 73-83
	 */
	private String salesRejectedAmount;

	/**
	 * 84
	 */
	private char salesRejectedSign;

	/**
	 * 85-95
	 */
	private String salesFundedAmount;

	/**
	 * 96
	 */
	private char salesFundedSign;

	/**
	 * 97-107
	 */
	private String disputeAmount;

	/**
	 * 108
	 */
	private char disputeSign;

	/**
	 * 109-119
	 */
	private String adjustmentsAmount;

	/**
	 * 120
	 */
	private char adjustmentsSign;

	/**
	 * 121-131
	 */
	private String feesAmount;

	/**
	 * 132
	 */
	private char feesSign;

	/**
	 * 133-143
	 */
	private String returnsAmount;

	/**
	 * 144
	 */
	private char returnsSign;

	/**
	 * 145-155
	 */
	private String depositAmount;

	/**
	 * 156
	 */
	private char depositSign;

	/* Filler, 157-550, AN 394, Space Filled. */

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecordSequenceNumber() {
		return recordSequenceNumber;
	}

	public void setRecordSequenceNumber(String recordSequenceNumber) {
		this.recordSequenceNumber = recordSequenceNumber;
	}

	public String getFundingDate() {
		return fundingDate;
	}

	public void setFundingDate(String fundingDate) {
		this.fundingDate = fundingDate;
	}

	public String getMerchantNumber() {
		return merchantNumber;
	}

	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getSalesSubmittedAmount() {
		return salesSubmittedAmount;
	}

	public void setSalesSubmittedAmount(String salesSubmittedAmount) {
		this.salesSubmittedAmount = salesSubmittedAmount;
	}

	public char getSalesSubmittedSign() {
		return salesSubmittedSign;
	}

	public void setSalesSubmittedSign(char salesSubmittedSign) {
		this.salesSubmittedSign = salesSubmittedSign;
	}

	public String getSalesRejectedAmount() {
		return salesRejectedAmount;
	}

	public void setSalesRejectedAmount(String salesRejectedAmount) {
		this.salesRejectedAmount = salesRejectedAmount;
	}

	public char getSalesRejectedSign() {
		return salesRejectedSign;
	}

	public void setSalesRejectedSign(char salesRejectedSign) {
		this.salesRejectedSign = salesRejectedSign;
	}

	public String getSalesFundedAmount() {
		return salesFundedAmount;
	}

	public void setSalesFundedAmount(String salesFundedAmount) {
		this.salesFundedAmount = salesFundedAmount;
	}

	public char getSalesFundedSign() {
		return salesFundedSign;
	}

	public void setSalesFundedSign(char salesFundedSign) {
		this.salesFundedSign = salesFundedSign;
	}

	public String getDisputeAmount() {
		return disputeAmount;
	}

	public void setDisputeAmount(String disputeAmount) {
		this.disputeAmount = disputeAmount;
	}

	public char getDisputeSign() {
		return disputeSign;
	}

	public void setDisputeSign(char disputeSign) {
		this.disputeSign = disputeSign;
	}

	public String getAdjustmentsAmount() {
		return adjustmentsAmount;
	}

	public void setAdjustmentsAmount(String adjustmentsAmount) {
		this.adjustmentsAmount = adjustmentsAmount;
	}

	public char getAdjustmentsSign() {
		return adjustmentsSign;
	}

	public void setAdjustmentsSign(char adjustmentsSign) {
		this.adjustmentsSign = adjustmentsSign;
	}

	public String getFeesAmount() {
		return feesAmount;
	}

	public void setFeesAmount(String feesAmount) {
		this.feesAmount = feesAmount;
	}

	public char getFeesSign() {
		return feesSign;
	}

	public void setFeesSign(char feesSign) {
		this.feesSign = feesSign;
	}

	public String getReturnsAmount() {
		return returnsAmount;
	}

	public void setReturnsAmount(String returnsAmount) {
		this.returnsAmount = returnsAmount;
	}

	public char getReturnsSign() {
		return returnsSign;
	}

	public void setReturnsSign(char returnsSign) {
		this.returnsSign = returnsSign;
	}

	public String getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}

	public char getDepositSign() {
		return depositSign;
	}

	public void setDepositSign(char depositSign) {
		this.depositSign = depositSign;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(recordSequenceNumber);
		sb.append(fundingDate);
		sb.append(merchantNumber);
		sb.append(merchantName);
		sb.append(salesSubmittedAmount);
		sb.append(salesSubmittedSign);
		sb.append(salesRejectedAmount);
		sb.append(salesRejectedSign);
		sb.append(salesFundedAmount);
		sb.append(salesFundedSign);
		sb.append(disputeAmount);
		sb.append(disputeSign);
		sb.append(adjustmentsAmount);
		sb.append(adjustmentsSign);
		sb.append(feesAmount);
		sb.append(feesSign);
		sb.append(returnsAmount);
		sb.append(returnsSign);
		sb.append(depositAmount);
		sb.append(depositSign);
		sb.append(StringUtils.repeat(" ", 394));
		return sb.toString();
	}

	public static MerchantTotalFunding15 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		MerchantTotalFunding15 o = new MerchantTotalFunding15();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setFundingDate(s.substring(8, 14));
		o.setMerchantNumber(s.substring(14, 30));
		o.setMerchantName(s.substring(30, 60));
		o.setSalesSubmittedAmount(s.substring(60, 71));
		o.setSalesSubmittedSign(s.charAt(71));
		o.setSalesRejectedAmount(s.substring(72, 83));
		o.setSalesRejectedSign(s.charAt(83));
		o.setSalesFundedAmount(s.substring(84, 95));
		o.setSalesFundedSign(s.charAt(95));
		o.setDisputeAmount(s.substring(96, 107));
		o.setDisputeSign(s.charAt(107));
		o.setAdjustmentsAmount(s.substring(108, 119));
		o.setAdjustmentsSign(s.charAt(119));
		o.setFeesAmount(s.substring(120, 131));
		o.setFeesSign(s.charAt(131));
		o.setReturnsAmount(s.substring(132, 143));
		o.setReturnsSign(s.charAt(143));
		o.setDepositAmount(s.substring(144, 155));
		o.setDepositSign(s.charAt(155));
		return o;
	}

}
