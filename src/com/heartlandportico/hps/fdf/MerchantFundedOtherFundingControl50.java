package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class MerchantFundedOtherFundingControl50 {

	/**
	 * 1-2, '50'
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
	 * 15-20, Total Number of Merchant Funded Returned Items records
	 */
	private String returnedItemsCount;

	/**
	 * 21-26, Total Number Merchant Funded Disputes records
	 */
	private String disputesCount;

	/**
	 * 27-32, Total Number Merchant Funded Adjustments records
	 */
	private String adjustmentsCount;

	/* Filler, 33-550, AN 518, Space Filled. */

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

	public String getReturnedItemsCount() {
		return returnedItemsCount;
	}

	public void setReturnedItemsCount(String returnedItemsCount) {
		this.returnedItemsCount = returnedItemsCount;
	}

	public String getDisputesCount() {
		return disputesCount;
	}

	public void setDisputesCount(String disputesCount) {
		this.disputesCount = disputesCount;
	}

	public String getAdjustmentsCount() {
		return adjustmentsCount;
	}

	public void setAdjustmentsCount(String adjustmentsCount) {
		this.adjustmentsCount = adjustmentsCount;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(recordSequenceNumber);
		sb.append(fundingDate);
		sb.append(returnedItemsCount);
		sb.append(disputesCount);
		sb.append(adjustmentsCount);
		sb.append(StringUtils.repeat(" ", 518));
		return sb.toString();
	}

	public static MerchantFundedOtherFundingControl50 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		MerchantFundedOtherFundingControl50 o = new MerchantFundedOtherFundingControl50();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setFundingDate(s.substring(8, 14));
		o.setReturnedItemsCount(s.substring(14, 20));
		o.setDisputesCount(s.substring(20, 26));
		o.setAdjustmentsCount(s.substring(26, 32));
		return o;
	}

}
