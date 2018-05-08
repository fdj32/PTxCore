package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class MerchantFundedTransactionLevelFees35 {

	/**
	 * 1-2, '35'
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
	 * 25-28, Unique ID internally assigned to each Transaction
	 */
	private String uniqueTransactionId;

	/**
	 * 29-78, 91-140, 153-202, 215-264, 277-326, Description of the
	 * ServiceOptionTxnTypeID (SOTT)
	 */
	private String[] feeDescription = new String[5];

	/**
	 * 79, 141, 203, 265, 327, Debit/Credit Ind
	 */
	private char[] debitCreditInd = new char[5];

	/**
	 * 80-90, 142-152, 204-214, 266-276, 328-338, Net total amount of fee
	 */
	private String[] feeAmount = new String[5];

	/* Filler, 339-550, AN 212, Space Filled. */

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

	public String getUniqueTransactionId() {
		return uniqueTransactionId;
	}

	public void setUniqueTransactionId(String uniqueTransactionId) {
		this.uniqueTransactionId = uniqueTransactionId;
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
		sb.append(uniqueTransactionId);
		for (int i = 0; i < 5; i++) {
			sb.append(feeDescription[i]);
			sb.append(debitCreditInd[i]);
			sb.append(feeAmount[i]);
		}
		sb.append(StringUtils.repeat(" ", 212));
		return sb.toString();
	}

	public static MerchantFundedTransactionLevelFees35 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		MerchantFundedTransactionLevelFees35 o = new MerchantFundedTransactionLevelFees35();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setUniqueBatchId(s.substring(8, 24));
		o.setUniqueTransactionId(s.substring(24, 28));
		for (int i = 0; i < 5; i++) {
			o.getFeeDescription()[i] = s.substring(28 + 62 * i, 78 + 62 * i);
			o.getDebitCreditInd()[i] = s.charAt(78 + 62 * i);
			o.getFeeAmount()[i] = s.substring(79 + 62 * i, 90 + 62 * i);
		}
		return o;
	}

}
