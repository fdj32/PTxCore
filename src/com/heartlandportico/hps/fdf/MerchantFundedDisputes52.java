package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class MerchantFundedDisputes52 {

	/**
	 * 1-2, '52'
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
	 * 15-20, Total Nbr of Chargebacks of Sales
	 */
	private String saleChargebackCount;

	/**
	 * 21-31, Total Amount of Chargebacks of Sales
	 */
	private String saleChargebackAmount;

	/**
	 * 32-37, Total Nbr of Chargebacks of Sales Reversals
	 */
	private String saleReversalChargebackCount;

	/**
	 * 38-48, Total Amount of Chargebacks of Sales Reversals
	 */
	private String saleReversalChargebackAmount;

	/**
	 * 49-54, Total Nbr of Chargebacks of Returns
	 */
	private String returnChargebackCount;

	/**
	 * 55-65, Total Amount Chargebacks of Returns
	 */
	private String returnChargebackAmount;

	/**
	 * 66-71, Total Nbr of Chargebacks of Returns Reversals
	 */
	private String returnReversalChargebackCount;

	/**
	 * 72-82, Total Amount Chargebacks of Returns Reversals
	 */
	private String returnReversalChargebackAmount;

	/**
	 * 83-88, Total Nbr of Representments of Sales
	 */
	private String saleRepresentmentCount;

	/**
	 * 89-99, Total Amount Representments of Sales
	 */
	private String saleRepresentmentAmount;

	/**
	 * 100-105, Total Nbr of Representments of Returns
	 */
	private String returnRepresentmentCount;

	/**
	 * 106-116, Total Amount Representments of Returns
	 */
	private String returnRepresentmentAmount;

	/**
	 * 117-122, Total Nbr of Representments of Sales Reversals
	 */
	private String saleReversalRepresentmentCount;

	/**
	 * 123-133, Total Amount Representments of Sales Reversals
	 */
	private String saleReversalRepresentmentAmount;

	/**
	 * 134-139, Total Nbr of Representments of Returns Reversals
	 */
	private String returnReversalRepresentmentCount;

	/**
	 * 140-150, Total Amount of Representments of Returns Reversals
	 */
	private String returnReversalRepresentmentAmount;

	/* Filler, 151-550, AN 400, Space Filled. */

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

	public String getSaleChargebackCount() {
		return saleChargebackCount;
	}

	public void setSaleChargebackCount(String saleChargebackCount) {
		this.saleChargebackCount = saleChargebackCount;
	}

	public String getSaleChargebackAmount() {
		return saleChargebackAmount;
	}

	public void setSaleChargebackAmount(String saleChargebackAmount) {
		this.saleChargebackAmount = saleChargebackAmount;
	}

	public String getSaleReversalChargebackCount() {
		return saleReversalChargebackCount;
	}

	public void setSaleReversalChargebackCount(String saleReversalChargebackCount) {
		this.saleReversalChargebackCount = saleReversalChargebackCount;
	}

	public String getSaleReversalChargebackAmount() {
		return saleReversalChargebackAmount;
	}

	public void setSaleReversalChargebackAmount(String saleReversalChargebackAmount) {
		this.saleReversalChargebackAmount = saleReversalChargebackAmount;
	}

	public String getReturnChargebackCount() {
		return returnChargebackCount;
	}

	public void setReturnChargebackCount(String returnChargebackCount) {
		this.returnChargebackCount = returnChargebackCount;
	}

	public String getReturnChargebackAmount() {
		return returnChargebackAmount;
	}

	public void setReturnChargebackAmount(String returnChargebackAmount) {
		this.returnChargebackAmount = returnChargebackAmount;
	}

	public String getReturnReversalChargebackCount() {
		return returnReversalChargebackCount;
	}

	public void setReturnReversalChargebackCount(String returnReversalChargebackCount) {
		this.returnReversalChargebackCount = returnReversalChargebackCount;
	}

	public String getReturnReversalChargebackAmount() {
		return returnReversalChargebackAmount;
	}

	public void setReturnReversalChargebackAmount(String returnReversalChargebackAmount) {
		this.returnReversalChargebackAmount = returnReversalChargebackAmount;
	}

	public String getSaleRepresentmentCount() {
		return saleRepresentmentCount;
	}

	public void setSaleRepresentmentCount(String saleRepresentmentCount) {
		this.saleRepresentmentCount = saleRepresentmentCount;
	}

	public String getSaleRepresentmentAmount() {
		return saleRepresentmentAmount;
	}

	public void setSaleRepresentmentAmount(String saleRepresentmentAmount) {
		this.saleRepresentmentAmount = saleRepresentmentAmount;
	}

	public String getReturnRepresentmentCount() {
		return returnRepresentmentCount;
	}

	public void setReturnRepresentmentCount(String returnRepresentmentCount) {
		this.returnRepresentmentCount = returnRepresentmentCount;
	}

	public String getReturnRepresentmentAmount() {
		return returnRepresentmentAmount;
	}

	public void setReturnRepresentmentAmount(String returnRepresentmentAmount) {
		this.returnRepresentmentAmount = returnRepresentmentAmount;
	}

	public String getSaleReversalRepresentmentCount() {
		return saleReversalRepresentmentCount;
	}

	public void setSaleReversalRepresentmentCount(String saleReversalRepresentmentCount) {
		this.saleReversalRepresentmentCount = saleReversalRepresentmentCount;
	}

	public String getSaleReversalRepresentmentAmount() {
		return saleReversalRepresentmentAmount;
	}

	public void setSaleReversalRepresentmentAmount(String saleReversalRepresentmentAmount) {
		this.saleReversalRepresentmentAmount = saleReversalRepresentmentAmount;
	}

	public String getReturnReversalRepresentmentCount() {
		return returnReversalRepresentmentCount;
	}

	public void setReturnReversalRepresentmentCount(String returnReversalRepresentmentCount) {
		this.returnReversalRepresentmentCount = returnReversalRepresentmentCount;
	}

	public String getReturnReversalRepresentmentAmount() {
		return returnReversalRepresentmentAmount;
	}

	public void setReturnReversalRepresentmentAmount(String returnReversalRepresentmentAmount) {
		this.returnReversalRepresentmentAmount = returnReversalRepresentmentAmount;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(recordSequenceNumber);
		sb.append(fundingDate);
		sb.append(saleChargebackCount);
		sb.append(saleChargebackAmount);
		sb.append(saleReversalChargebackCount);
		sb.append(saleReversalChargebackAmount);
		sb.append(returnChargebackCount);
		sb.append(returnChargebackAmount);
		sb.append(returnReversalChargebackCount);
		sb.append(returnReversalChargebackAmount);
		sb.append(saleRepresentmentCount);
		sb.append(saleRepresentmentAmount);
		sb.append(returnRepresentmentCount);
		sb.append(returnRepresentmentAmount);
		sb.append(saleReversalRepresentmentCount);
		sb.append(saleReversalRepresentmentAmount);
		sb.append(returnReversalRepresentmentCount);
		sb.append(returnReversalRepresentmentAmount);
		sb.append(StringUtils.repeat(" ", 400));
		return sb.toString();
	}

	public static MerchantFundedDisputes52 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		MerchantFundedDisputes52 o = new MerchantFundedDisputes52();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setFundingDate(s.substring(8, 14));
		o.setSaleChargebackCount(s.substring(14, 20));
		o.setSaleChargebackAmount(s.substring(20, 31));
		o.setSaleReversalChargebackCount(s.substring(31, 37));
		o.setSaleReversalChargebackAmount(s.substring(37, 48));
		o.setReturnChargebackCount(s.substring(48, 54));
		o.setReturnChargebackAmount(s.substring(54, 65));
		o.setReturnReversalChargebackCount(s.substring(65, 71));
		o.setReturnReversalChargebackAmount(s.substring(71, 82));
		o.setSaleRepresentmentCount(s.substring(82, 88));
		o.setSaleRepresentmentAmount(s.substring(88, 99));
		o.setReturnRepresentmentCount(s.substring(99, 105));
		o.setReturnRepresentmentAmount(s.substring(105, 116));
		o.setSaleReversalRepresentmentCount(s.substring(116, 122));
		o.setSaleReversalRepresentmentAmount(s.substring(122, 133));
		o.setReturnReversalRepresentmentCount(s.substring(133, 139));
		o.setReturnReversalRepresentmentAmount(s.substring(139, 150));
		return o;
	}

}
