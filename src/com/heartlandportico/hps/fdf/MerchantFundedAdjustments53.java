package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class MerchantFundedAdjustments53 {

	/**
	 * 1-2, '53'
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
	 * 15-20, Total Nbr of Debit Adjustments
	 */
	private String debitAdjustmentCount;

	/**
	 * 21-31, Total Amount of Debit Adjustments
	 */
	private String debitAdjustmentAmount;

	/**
	 * 32-37, Total Nbr of Credit Adjustments
	 */
	private String creditAdjustmentCount;

	/**
	 * 38-48, Total Amount of Credit Adjustments
	 */
	private String creditAdjustmentAmount;

	/* Filler, 49-550, AN 502, Space Filled. */

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

	public String getDebitAdjustmentCount() {
		return debitAdjustmentCount;
	}

	public void setDebitAdjustmentCount(String debitAdjustmentCount) {
		this.debitAdjustmentCount = debitAdjustmentCount;
	}

	public String getDebitAdjustmentAmount() {
		return debitAdjustmentAmount;
	}

	public void setDebitAdjustmentAmount(String debitAdjustmentAmount) {
		this.debitAdjustmentAmount = debitAdjustmentAmount;
	}

	public String getCreditAdjustmentCount() {
		return creditAdjustmentCount;
	}

	public void setCreditAdjustmentCount(String creditAdjustmentCount) {
		this.creditAdjustmentCount = creditAdjustmentCount;
	}

	public String getCreditAdjustmentAmount() {
		return creditAdjustmentAmount;
	}

	public void setCreditAdjustmentAmount(String creditAdjustmentAmount) {
		this.creditAdjustmentAmount = creditAdjustmentAmount;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(recordSequenceNumber);
		sb.append(fundingDate);
		sb.append(debitAdjustmentCount);
		sb.append(debitAdjustmentAmount);
		sb.append(creditAdjustmentCount);
		sb.append(creditAdjustmentAmount);
		sb.append(StringUtils.repeat(" ", 502));
		return sb.toString();
	}

	public static MerchantFundedAdjustments53 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		MerchantFundedAdjustments53 o = new MerchantFundedAdjustments53();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setFundingDate(s.substring(8, 14));
		o.setDebitAdjustmentCount(s.substring(14, 20));
		o.setDebitAdjustmentAmount(s.substring(20, 31));
		o.setCreditAdjustmentCount(s.substring(31, 37));
		o.setCreditAdjustmentAmount(s.substring(37, 48));
		return o;
	}

}
