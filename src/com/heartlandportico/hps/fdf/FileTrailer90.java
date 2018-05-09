package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class FileTrailer90 {

	/**
	 * 1-2, '90'
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
	 * 15-20
	 */
	private String physicalRecordCount;

	/**
	 * 21-26, Total Nbr of Merchant Total Funding records
	 */
	private String merchantTotalFundingCount;

	/**
	 * 27-32, Total Nbr of Merchant Batch Header Control records
	 */
	private String merchantBatchHeaderControlCount;

	/**
	 * 33-38, Total Nbr of Merchant Funded Batch Header records
	 */
	private String merchantFundedBatchHeaderCount;

	/**
	 * 39-44, Total Nbr of Merchant Funded Batch Header records
	 */
	private String merchantDepositCount;

	/**
	 * 45-50, Total Nbr of Merchant Deposits records
	 */
	private String merchantFundedTransactionLevelFeesCount;

	/**
	 * 51-56, Total Nbr of Merchant Funded Transaction Level Fees records
	 */
	private String merchantFutureFundedTransactionLevelFeesCount;

	/**
	 * 57-62, Total Nbr of Merchant Future Funded Transaction Level Fees records
	 */
	private String merchantFundedBatchHMSActivityCount;

	/**
	 * 63-68, Total Nbr of Merchant Funded Batch HMS Activity records
	 */
	private String merchantFundedBatchByCardTypeCount;

	/**
	 * 69-74, Total Nbr of Merchant Funded HMS Activity records
	 */
	private String merchantFundedHMSActivityCount;

	/**
	 * 75-80, Total Nbr of Merchant Funded By Card Type records
	 */
	private String merchantFundedByCardTypeCount;

	/**
	 * 81-86, Total Nbr of Merchant Funded Other Funding Control records
	 */
	private String merchantFundedOtherFundingControlCount;

	/**
	 * 87-92, Total Nbr of Merchant Funded Returned Items records
	 */
	private String merchantFundedReturnedItemsCount;

	/**
	 * 93-98, Total Nbr of Merchant Funded Disputes records
	 */
	private String merchantFundedDisputeCount;

	/**
	 * 99-104, Total Nbr of Merchant Funded Adjustments records
	 */
	private String merchantFundedAdjustmentsCount;

	/**
	 * 105-110, Total Nbr of Chain Total Funding records
	 */
	private String chainTotalFundingCount;

	/**
	 * 111-116, Total Nbr of Chain Total Funding by Card Type records
	 */
	private String chainTotalFundingByCardTypeCount;

	/**
	 * 117-122, Total Nbr of Chain Funded Batch HMS records
	 */
	private String chainFundedBatchHMSCount;

	/* Filler, 123-550, AN 428, Space Filled. */

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

	public String getPhysicalRecordCount() {
		return physicalRecordCount;
	}

	public void setPhysicalRecordCount(String physicalRecordCount) {
		this.physicalRecordCount = physicalRecordCount;
	}

	public String getMerchantTotalFundingCount() {
		return merchantTotalFundingCount;
	}

	public void setMerchantTotalFundingCount(String merchantTotalFundingCount) {
		this.merchantTotalFundingCount = merchantTotalFundingCount;
	}

	public String getMerchantBatchHeaderControlCount() {
		return merchantBatchHeaderControlCount;
	}

	public void setMerchantBatchHeaderControlCount(String merchantBatchHeaderControlCount) {
		this.merchantBatchHeaderControlCount = merchantBatchHeaderControlCount;
	}

	public String getMerchantDepositCount() {
		return merchantDepositCount;
	}

	public void setMerchantDepositCount(String merchantDepositCount) {
		this.merchantDepositCount = merchantDepositCount;
	}

	public String getMerchantFundedTransactionLevelFeesCount() {
		return merchantFundedTransactionLevelFeesCount;
	}

	public void setMerchantFundedTransactionLevelFeesCount(String merchantFundedTransactionLevelFeesCount) {
		this.merchantFundedTransactionLevelFeesCount = merchantFundedTransactionLevelFeesCount;
	}

	public String getMerchantFutureFundedTransactionLevelFeesCount() {
		return merchantFutureFundedTransactionLevelFeesCount;
	}

	public void setMerchantFutureFundedTransactionLevelFeesCount(String merchantFutureFundedTransactionLevelFeesCount) {
		this.merchantFutureFundedTransactionLevelFeesCount = merchantFutureFundedTransactionLevelFeesCount;
	}

	public String getMerchantFundedBatchHMSActivityCount() {
		return merchantFundedBatchHMSActivityCount;
	}

	public void setMerchantFundedBatchHMSActivityCount(String merchantFundedBatchHMSActivityCount) {
		this.merchantFundedBatchHMSActivityCount = merchantFundedBatchHMSActivityCount;
	}

	public String getMerchantFundedBatchByCardTypeCount() {
		return merchantFundedBatchByCardTypeCount;
	}

	public void setMerchantFundedBatchByCardTypeCount(String merchantFundedBatchByCardTypeCount) {
		this.merchantFundedBatchByCardTypeCount = merchantFundedBatchByCardTypeCount;
	}

	public String getMerchantFundedHMSActivityCount() {
		return merchantFundedHMSActivityCount;
	}

	public void setMerchantFundedHMSActivityCount(String merchantFundedHMSActivityCount) {
		this.merchantFundedHMSActivityCount = merchantFundedHMSActivityCount;
	}

	public String getMerchantFundedByCardTypeCount() {
		return merchantFundedByCardTypeCount;
	}

	public void setMerchantFundedByCardTypeCount(String merchantFundedByCardTypeCount) {
		this.merchantFundedByCardTypeCount = merchantFundedByCardTypeCount;
	}

	public String getMerchantFundedOtherFundingControlCount() {
		return merchantFundedOtherFundingControlCount;
	}

	public void setMerchantFundedOtherFundingControlCount(String merchantFundedOtherFundingControlCount) {
		this.merchantFundedOtherFundingControlCount = merchantFundedOtherFundingControlCount;
	}

	public String getMerchantFundedReturnedItemsCount() {
		return merchantFundedReturnedItemsCount;
	}

	public void setMerchantFundedReturnedItemsCount(String merchantFundedReturnedItemsCount) {
		this.merchantFundedReturnedItemsCount = merchantFundedReturnedItemsCount;
	}

	public String getMerchantFundedDisputeCount() {
		return merchantFundedDisputeCount;
	}

	public void setMerchantFundedDisputeCount(String merchantFundedDisputeCount) {
		this.merchantFundedDisputeCount = merchantFundedDisputeCount;
	}

	public String getMerchantFundedAdjustmentsCount() {
		return merchantFundedAdjustmentsCount;
	}

	public void setMerchantFundedAdjustmentsCount(String merchantFundedAdjustmentsCount) {
		this.merchantFundedAdjustmentsCount = merchantFundedAdjustmentsCount;
	}

	public String getChainTotalFundingByCardTypeCount() {
		return chainTotalFundingByCardTypeCount;
	}

	public void setChainTotalFundingByCardTypeCount(String chainTotalFundingByCardTypeCount) {
		this.chainTotalFundingByCardTypeCount = chainTotalFundingByCardTypeCount;
	}

	public String getChainFundedBatchHMSCount() {
		return chainFundedBatchHMSCount;
	}

	public void setChainFundedBatchHMSCount(String chainFundedBatchHMSCount) {
		this.chainFundedBatchHMSCount = chainFundedBatchHMSCount;
	}

	public String getMerchantFundedBatchHeaderCount() {
		return merchantFundedBatchHeaderCount;
	}

	public void setMerchantFundedBatchHeaderCount(String merchantFundedBatchHeaderCount) {
		this.merchantFundedBatchHeaderCount = merchantFundedBatchHeaderCount;
	}

	public String getChainTotalFundingCount() {
		return chainTotalFundingCount;
	}

	public void setChainTotalFundingCount(String chainTotalFundingCount) {
		this.chainTotalFundingCount = chainTotalFundingCount;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(recordSequenceNumber);
		sb.append(fundingDate);
		sb.append(physicalRecordCount);
		sb.append(merchantTotalFundingCount);
		sb.append(merchantBatchHeaderControlCount);
		sb.append(merchantFundedBatchHeaderCount);
		sb.append(merchantDepositCount);
		sb.append(merchantFundedTransactionLevelFeesCount);
		sb.append(merchantFutureFundedTransactionLevelFeesCount);
		sb.append(merchantFundedBatchHMSActivityCount);
		sb.append(merchantFundedBatchByCardTypeCount);
		sb.append(merchantFundedHMSActivityCount);
		sb.append(merchantFundedByCardTypeCount);
		sb.append(merchantFundedOtherFundingControlCount);
		sb.append(merchantFundedReturnedItemsCount);
		sb.append(merchantFundedDisputeCount);
		sb.append(merchantFundedAdjustmentsCount);
		sb.append(chainTotalFundingCount);
		sb.append(chainTotalFundingByCardTypeCount);
		sb.append(chainFundedBatchHMSCount);
		sb.append(StringUtils.repeat(" ", 428));
		return sb.toString();
	}

	public static FileTrailer90 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		FileTrailer90 o = new FileTrailer90();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setFundingDate(s.substring(8, 14));
		o.setPhysicalRecordCount(s.substring(14, 20));
		o.setMerchantTotalFundingCount(s.substring(20, 26));
		o.setMerchantBatchHeaderControlCount(s.substring(26, 32));
		o.setMerchantFundedBatchHeaderCount(s.substring(32, 38));
		o.setMerchantDepositCount(s.substring(38, 44));
		o.setMerchantFundedTransactionLevelFeesCount(s.substring(44, 50));
		o.setMerchantFutureFundedTransactionLevelFeesCount(s.substring(50, 56));
		o.setMerchantFundedBatchHMSActivityCount(s.substring(56, 62));
		o.setMerchantFundedBatchByCardTypeCount(s.substring(62, 68));
		o.setMerchantFundedHMSActivityCount(s.substring(68, 74));
		o.setMerchantFundedByCardTypeCount(s.substring(74, 80));
		o.setMerchantFundedOtherFundingControlCount(s.substring(80, 86));
		o.setMerchantFundedReturnedItemsCount(s.substring(86, 92));
		o.setMerchantFundedDisputeCount(s.substring(92, 98));
		o.setMerchantFundedAdjustmentsCount(s.substring(98, 104));
		o.setChainTotalFundingCount(s.substring(104, 110));
		o.setChainTotalFundingByCardTypeCount(s.substring(110, 116));
		o.setChainFundedBatchHMSCount(s.substring(116, 122));
		return o;
	}

}
