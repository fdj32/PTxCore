package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class MerchantFundedBatchHMSActivity34 {

	/**
	 * 1-2, '34'
	 */
	private String recordType;

	/**
	 * 3-8, Sequential number of the record within file. Incremented by 1 for each
	 * record.
	 */
	private String recordSequenceNumber;

	/**
	 * 9-24, Unique ID internally assigned to each Batch
	 */
	private String uniqueBatchId;

	/**
	 * 25-36
	 */
	private String batchId;

	/**
	 * 37-48
	 */
	private String terminalId;

	/**
	 * 49-52
	 */
	private String terminalNumber;

	/**
	 * 53-58
	 */
	private String activateTxnCount;

	/**
	 * 59-64
	 */
	private String loadTxnCount;

	/**
	 * 65-70
	 */
	private String redeemReversalTxnCount;

	/**
	 * 71-76
	 */
	private String promotionTxnCount;

	/**
	 * 77-82
	 */
	private String positiveAdjustmentTxnCount;

	/**
	 * 83-88
	 */
	private String redeemTxnCount;

	/**
	 * 89-94
	 */
	private String loadReversalTxnCount;

	/**
	 * 95-100
	 */
	private String negativeAdjustmentTxnCount;

	/**
	 * 101-106
	 */
	private String expiredTxnCount;

	/**
	 * 107-112
	 */
	private String feeTxnCount;

	/**
	 * 113-118
	 */
	private String escheatmentTxnCount;

	/**
	 * 119-124
	 */
	private String closeTxnCount;

	/**
	 * 125-130
	 */
	private String transferTxnCount;

	/**
	 * 131-136
	 */
	private String balanceInquiryTxnCount;

	/**
	 * 137-142
	 */
	private String registerTxnCount;

	/**
	 * 143-148
	 */
	private String freezeTxnCount;

	/**
	 * 149-154
	 */
	private String unfreezeTxnCount;

	/**
	 * 155-160
	 */
	private String promoReversalTxnCount;

	/* Filler, 161-550, AN 390, Space Filled. */

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

	public String getUniqueBatchId() {
		return uniqueBatchId;
	}

	public void setUniqueBatchId(String uniqueBatchId) {
		this.uniqueBatchId = uniqueBatchId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getTerminalNumber() {
		return terminalNumber;
	}

	public void setTerminalNumber(String terminalNumber) {
		this.terminalNumber = terminalNumber;
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
		sb.append(uniqueBatchId);
		sb.append(batchId);
		sb.append(terminalId);
		sb.append(terminalNumber);
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
		sb.append(StringUtils.repeat(" ", 390));
		return sb.toString();
	}

	public static MerchantFundedBatchHMSActivity34 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		MerchantFundedBatchHMSActivity34 o = new MerchantFundedBatchHMSActivity34();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setUniqueBatchId(s.substring(8, 24));
		o.setBatchId(s.substring(24, 36));
		o.setTerminalId(s.substring(36, 48));
		o.setTerminalNumber(s.substring(48, 52));
		o.setActivateTxnCount(s.substring(52, 58));
		o.setLoadTxnCount(s.substring(58, 64));
		o.setRedeemReversalTxnCount(s.substring(64, 70));
		o.setPromotionTxnCount(s.substring(70, 76));
		o.setPositiveAdjustmentTxnCount(s.substring(76, 82));
		o.setRedeemTxnCount(s.substring(82, 88));
		o.setLoadReversalTxnCount(s.substring(88, 94));
		o.setNegativeAdjustmentTxnCount(s.substring(94, 100));
		o.setExpiredTxnCount(s.substring(100, 106));
		o.setFeeTxnCount(s.substring(106, 112));
		o.setEscheatmentTxnCount(s.substring(112, 118));
		o.setCloseTxnCount(s.substring(118, 124));
		o.setTransferTxnCount(s.substring(124, 130));
		o.setBalanceInquiryTxnCount(s.substring(130, 136));
		o.setRegisterTxnCount(s.substring(136, 142));
		o.setFreezeTxnCount(s.substring(142, 148));
		o.setUnfreezeTxnCount(s.substring(148, 154));
		o.setPromoReversalTxnCount(s.substring(154, 160));
		return o;
	}

}
