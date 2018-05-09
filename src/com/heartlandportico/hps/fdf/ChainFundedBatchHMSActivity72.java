package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class ChainFundedBatchHMSActivity72 {

	/**
	 * 1-2, '72'
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
	private String activateTxnCount;

	/**
	 * 21-26
	 */
	private String loadTxnCount;

	/**
	 * 27-32
	 */
	private String redeemReversalTxnCount;

	/**
	 * 33-38
	 */
	private String promotionTxnCount;

	/**
	 * 39-44
	 */
	private String positiveAdjustmentTxnCount;

	/**
	 * 45-50
	 */
	private String redeemTxnCount;

	/**
	 * 51-56
	 */
	private String loadReversalTxnCount;

	/**
	 * 57-62
	 */
	private String negativeAdjustmentTxnCount;

	/**
	 * 63-68
	 */
	private String expiredTxnCount;

	/**
	 * 69-74
	 */
	private String feeTxnCount;

	/**
	 * 75-80
	 */
	private String escheatmentTxnCount;

	/**
	 * 81-86
	 */
	private String closeTxnCount;

	/**
	 * 87-92
	 */
	private String transferTxnCount;

	/**
	 * 93-98
	 */
	private String balanceInquiryTxnCount;

	/**
	 * 99-104
	 */
	private String registerTxnCount;

	/**
	 * 105-110
	 */
	private String freezeTxnCount;

	/**
	 * 111-116
	 */
	private String unfreezeTxnCount;

	/**
	 * 117-122
	 */
	private String promoReversalTxnCount;

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

	public String getActivateTxnCount() {
		return activateTxnCount;
	}

	public void setActivateTxnCount(String activateTxnCount) {
		this.activateTxnCount = activateTxnCount;
	}

	public String getLoadTxnCount() {
		return loadTxnCount;
	}

	public void setLoadTxnCount(String loadTxnCount) {
		this.loadTxnCount = loadTxnCount;
	}

	public String getRedeemReversalTxnCount() {
		return redeemReversalTxnCount;
	}

	public void setRedeemReversalTxnCount(String redeemReversalTxnCount) {
		this.redeemReversalTxnCount = redeemReversalTxnCount;
	}

	public String getPromotionTxnCount() {
		return promotionTxnCount;
	}

	public void setPromotionTxnCount(String promotionTxnCount) {
		this.promotionTxnCount = promotionTxnCount;
	}

	public String getPositiveAdjustmentTxnCount() {
		return positiveAdjustmentTxnCount;
	}

	public void setPositiveAdjustmentTxnCount(String positiveAdjustmentTxnCount) {
		this.positiveAdjustmentTxnCount = positiveAdjustmentTxnCount;
	}

	public String getRedeemTxnCount() {
		return redeemTxnCount;
	}

	public void setRedeemTxnCount(String redeemTxnCount) {
		this.redeemTxnCount = redeemTxnCount;
	}

	public String getLoadReversalTxnCount() {
		return loadReversalTxnCount;
	}

	public void setLoadReversalTxnCount(String loadReversalTxnCount) {
		this.loadReversalTxnCount = loadReversalTxnCount;
	}

	public String getNegativeAdjustmentTxnCount() {
		return negativeAdjustmentTxnCount;
	}

	public void setNegativeAdjustmentTxnCount(String negativeAdjustmentTxnCount) {
		this.negativeAdjustmentTxnCount = negativeAdjustmentTxnCount;
	}

	public String getExpiredTxnCount() {
		return expiredTxnCount;
	}

	public void setExpiredTxnCount(String expiredTxnCount) {
		this.expiredTxnCount = expiredTxnCount;
	}

	public String getFeeTxnCount() {
		return feeTxnCount;
	}

	public void setFeeTxnCount(String feeTxnCount) {
		this.feeTxnCount = feeTxnCount;
	}

	public String getEscheatmentTxnCount() {
		return escheatmentTxnCount;
	}

	public void setEscheatmentTxnCount(String escheatmentTxnCount) {
		this.escheatmentTxnCount = escheatmentTxnCount;
	}

	public String getCloseTxnCount() {
		return closeTxnCount;
	}

	public void setCloseTxnCount(String closeTxnCount) {
		this.closeTxnCount = closeTxnCount;
	}

	public String getTransferTxnCount() {
		return transferTxnCount;
	}

	public void setTransferTxnCount(String transferTxnCount) {
		this.transferTxnCount = transferTxnCount;
	}

	public String getBalanceInquiryTxnCount() {
		return balanceInquiryTxnCount;
	}

	public void setBalanceInquiryTxnCount(String balanceInquiryTxnCount) {
		this.balanceInquiryTxnCount = balanceInquiryTxnCount;
	}

	public String getRegisterTxnCount() {
		return registerTxnCount;
	}

	public void setRegisterTxnCount(String registerTxnCount) {
		this.registerTxnCount = registerTxnCount;
	}

	public String getFreezeTxnCount() {
		return freezeTxnCount;
	}

	public void setFreezeTxnCount(String freezeTxnCount) {
		this.freezeTxnCount = freezeTxnCount;
	}

	public String getUnfreezeTxnCount() {
		return unfreezeTxnCount;
	}

	public void setUnfreezeTxnCount(String unfreezeTxnCount) {
		this.unfreezeTxnCount = unfreezeTxnCount;
	}

	public String getPromoReversalTxnCount() {
		return promoReversalTxnCount;
	}

	public void setPromoReversalTxnCount(String promoReversalTxnCount) {
		this.promoReversalTxnCount = promoReversalTxnCount;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(recordSequenceNumber);
		sb.append(fundingDate);
		sb.append(activateTxnCount);
		sb.append(loadTxnCount);
		sb.append(redeemReversalTxnCount);
		sb.append(promotionTxnCount);
		sb.append(positiveAdjustmentTxnCount);
		sb.append(redeemTxnCount);
		sb.append(loadReversalTxnCount);
		sb.append(negativeAdjustmentTxnCount);
		sb.append(expiredTxnCount);
		sb.append(feeTxnCount);
		sb.append(escheatmentTxnCount);
		sb.append(closeTxnCount);
		sb.append(transferTxnCount);
		sb.append(balanceInquiryTxnCount);
		sb.append(registerTxnCount);
		sb.append(freezeTxnCount);
		sb.append(unfreezeTxnCount);
		sb.append(promoReversalTxnCount);
		sb.append(StringUtils.repeat(" ", 428));
		return sb.toString();
	}

	public static ChainFundedBatchHMSActivity72 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		ChainFundedBatchHMSActivity72 o = new ChainFundedBatchHMSActivity72();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setFundingDate(s.substring(8, 14));
		o.setActivateTxnCount(s.substring(14, 20));
		o.setLoadTxnCount(s.substring(20, 26));
		o.setRedeemReversalTxnCount(s.substring(26, 32));
		o.setPromotionTxnCount(s.substring(32, 38));
		o.setPositiveAdjustmentTxnCount(s.substring(38, 44));
		o.setRedeemTxnCount(s.substring(44, 50));
		o.setLoadReversalTxnCount(s.substring(50, 56));
		o.setNegativeAdjustmentTxnCount(s.substring(56, 62));
		o.setExpiredTxnCount(s.substring(62, 68));
		o.setFeeTxnCount(s.substring(68, 74));
		o.setEscheatmentTxnCount(s.substring(74, 80));
		o.setCloseTxnCount(s.substring(80, 86));
		o.setTransferTxnCount(s.substring(86, 92));
		o.setBalanceInquiryTxnCount(s.substring(92, 98));
		o.setRegisterTxnCount(s.substring(98, 104));
		o.setFreezeTxnCount(s.substring(104, 110));
		o.setUnfreezeTxnCount(s.substring(110, 116));
		o.setPromoReversalTxnCount(s.substring(116, 122));
		return o;
	}

}
