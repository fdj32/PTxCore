package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class MerchantFundedTransactionLevelFees32 {

	/**
	 * 1-2, '32'
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
	 * 37-86, 99-148, 161-210, 223-272, 285-334, Description of the
	 * ServiceOptionTxnTypeID (SOTT)
	 */
	private String[] feeDescription = new String[5];

	/**
	 * 87, 149, 211, 273, 335, Debit/Credit Ind
	 */
	private char[] debitCreditInd = new char[5];

	/**
	 * 88-98, 150-160, 212-222, 274-284, 336-346, Net total amount of fee
	 */
	private String[] feeAmount = new String[5];

	/* Filler, 347-550, AN 204, Space Filled. */

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

	public String[] getFeeDescription() {
		return feeDescription;
	}

	public void setFeeDescription(String[] feeDescription) {
		this.feeDescription = feeDescription;
	}

	public char[] getDebitCreditInd() {
		return debitCreditInd;
	}

	public void setDebitCreditInd(char[] debitCreditInd) {
		this.debitCreditInd = debitCreditInd;
	}

	public String[] getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(String[] feeAmount) {
		this.feeAmount = feeAmount;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(recordSequenceNumber);
		sb.append(uniqueBatchId);
		sb.append(batchId);
		for (int i = 0; i < 5; i++) {
			sb.append(feeDescription[i]);
			sb.append(debitCreditInd[i]);
			sb.append(feeAmount[i]);
		}
		sb.append(StringUtils.repeat(" ", 204));
		return sb.toString();
	}

	public static MerchantFundedTransactionLevelFees32 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		MerchantFundedTransactionLevelFees32 o = new MerchantFundedTransactionLevelFees32();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setUniqueBatchId(s.substring(8, 24));
		o.setBatchId(s.substring(24, 36));
		for (int i = 0; i < 5; i++) {
			o.getFeeDescription()[i] = s.substring(36 + 62 * i, 86 + 62 * i);
			o.getDebitCreditInd()[i] = s.charAt(86 + 62 * i);
			o.getFeeAmount()[i] = s.substring(87 + 62 * i, 98 + 62 * i);
		}
		return o;
	}

}
