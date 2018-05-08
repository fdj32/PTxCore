package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class MerchantFutureFundedTransactionLevelFees33 {

	/**
	 * 1-2, '33'
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
	 * 37-44, YYYYMMDD
	 */
	private String anticipatedFundingDate;

	/**
	 * 45-94, 107-156, 169-218, 231-280, Description of the
	 * ServiceOptionTxnTypeID (SOTT)
	 */
	private String[] feeDescription = new String[4];

	/**
	 * 95, 157, 219, 281, Debit/Credit Ind
	 */
	private char[] debitCreditInd = new char[4];

	/**
	 * 96-106, 158-168, 220-230, 282-292, Net total amount of fee
	 */
	private String[] feeAmount = new String[4];

	/* Filler, 293-550, AN 258, Space Filled. */

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

	public String getAnticipatedFundingDate() {
		return anticipatedFundingDate;
	}

	public void setAnticipatedFundingDate(String anticipatedFundingDate) {
		this.anticipatedFundingDate = anticipatedFundingDate;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(recordSequenceNumber);
		sb.append(uniqueBatchId);
		sb.append(batchId);
		sb.append(anticipatedFundingDate);
		for (int i = 0; i < 4; i++) {
			sb.append(feeDescription[i]);
			sb.append(debitCreditInd[i]);
			sb.append(feeAmount[i]);
		}
		sb.append(StringUtils.repeat(" ", 258));
		return sb.toString();
	}

	public static MerchantFutureFundedTransactionLevelFees33 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		MerchantFutureFundedTransactionLevelFees33 o = new MerchantFutureFundedTransactionLevelFees33();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setUniqueBatchId(s.substring(8, 24));
		o.setBatchId(s.substring(24, 36));
		o.setAnticipatedFundingDate(s.substring(36, 44));
		for (int i = 0; i < 4; i++) {
			o.getFeeDescription()[i] = s.substring(44 + 62 * i, 94 + 62 * i);
			o.getDebitCreditInd()[i] = s.charAt(94 + 62 * i);
			o.getFeeAmount()[i] = s.substring(95 + 62 * i, 106 + 62 * i);
		}
		return o;
	}

}
