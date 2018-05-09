package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class MerchantFundedReturnedItems51 {

	/**
	 * 1-2, '51'
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
	 * 15-20, Total Number of Returned Sales
	 */
	private String returnedSalesCount;

	/**
	 * 21-31, Total Amount of Returned Sales
	 */
	private String returnedSalesAmount;

	/**
	 * 32-37, Total Number of Returned Returns
	 */
	private String returnedReturnsCount;

	/**
	 * 38-48, Total Amount of Returned Returns
	 */
	private String returnedReturnsAmount;

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

	public String getReturnedSalesCount() {
		return returnedSalesCount;
	}

	public void setReturnedSalesCount(String returnedSalesCount) {
		this.returnedSalesCount = returnedSalesCount;
	}

	public String getReturnedSalesAmount() {
		return returnedSalesAmount;
	}

	public void setReturnedSalesAmount(String returnedSalesAmount) {
		this.returnedSalesAmount = returnedSalesAmount;
	}

	public String getReturnedReturnsCount() {
		return returnedReturnsCount;
	}

	public void setReturnedReturnsCount(String returnedReturnsCount) {
		this.returnedReturnsCount = returnedReturnsCount;
	}

	public String getReturnedReturnsAmount() {
		return returnedReturnsAmount;
	}

	public void setReturnedReturnsAmount(String returnedReturnsAmount) {
		this.returnedReturnsAmount = returnedReturnsAmount;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(recordSequenceNumber);
		sb.append(fundingDate);
		sb.append(returnedSalesCount);
		sb.append(returnedSalesAmount);
		sb.append(returnedReturnsCount);
		sb.append(returnedReturnsAmount);
		sb.append(StringUtils.repeat(" ", 502));
		return sb.toString();
	}

	public static MerchantFundedReturnedItems51 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		MerchantFundedReturnedItems51 o = new MerchantFundedReturnedItems51();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setFundingDate(s.substring(8, 14));
		o.setReturnedSalesCount(s.substring(14, 20));
		o.setReturnedSalesAmount(s.substring(20, 31));
		o.setReturnedReturnsCount(s.substring(31, 37));
		o.setReturnedReturnsAmount(s.substring(37, 48));
		return o;
	}

}
