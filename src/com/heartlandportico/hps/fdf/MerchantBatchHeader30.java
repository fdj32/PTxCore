package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class MerchantBatchHeader30 {

	/**
	 * 1-2, '30'
	 */
	private String recordType;

	/**
	 * 3-8, Sequential number of the record within file. Incremented by 1 for each
	 * record.
	 */
	private String recordSequenceNumber;

	/**
	 * 9-24
	 */
	private String uniqueBatchId;

	/**
	 * 25-32
	 */
	private String businessDateId;

	/**
	 * 33-38
	 */
	private String closeDate;

	/**
	 * 39-54
	 */
	private String merchantNumber;

	/**
	 * 55-66
	 */
	private String batchId;

	/**
	 * 67-18
	 */
	private String terminalId;

	/**
	 * 79-82
	 */
	private String terminalNumber;

	/**
	 * 83-90
	 */
	private String terminalApp;

	/**
	 * 91-102
	 */
	private String sourceId;

	/**
	 * 103-106
	 */
	private String currencyCode;

	/**
	 * 107-111
	 */
	private String debitCount;

	/**
	 * 112-122
	 */
	private String debitAmount;

	/**
	 * 123-127
	 */
	private String creditCount;

	/**
	 * 128-138
	 */
	private String creditAmount;

	/**
	 * 139-142
	 */
	private String sicCode;

	/**
	 * 143-167
	 */
	private String submitterName;

	/* Filler, 168-550, AN 383, Space Filled. */

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

	public String getBusinessDateId() {
		return businessDateId;
	}

	public void setBusinessDateId(String businessDateId) {
		this.businessDateId = businessDateId;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public String getMerchantNumber() {
		return merchantNumber;
	}

	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
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

	public String getTerminalApp() {
		return terminalApp;
	}

	public void setTerminalApp(String terminalApp) {
		this.terminalApp = terminalApp;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getDebitCount() {
		return debitCount;
	}

	public void setDebitCount(String debitCount) {
		this.debitCount = debitCount;
	}

	public String getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
	}

	public String getCreditCount() {
		return creditCount;
	}

	public void setCreditCount(String creditCount) {
		this.creditCount = creditCount;
	}

	public String getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getSicCode() {
		return sicCode;
	}

	public void setSicCode(String sicCode) {
		this.sicCode = sicCode;
	}

	public String getSubmitterName() {
		return submitterName;
	}

	public void setSubmitterName(String submitterName) {
		this.submitterName = submitterName;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(recordSequenceNumber);
		sb.append(uniqueBatchId);
		sb.append(businessDateId);
		sb.append(closeDate);
		sb.append(merchantNumber);
		sb.append(batchId);
		sb.append(terminalId);
		sb.append(terminalNumber);
		sb.append(terminalApp);
		sb.append(sourceId);
		sb.append(currencyCode);
		sb.append(debitCount);
		sb.append(debitAmount);
		sb.append(creditCount);
		sb.append(creditAmount);
		sb.append(sicCode);
		sb.append(submitterName);
		sb.append(StringUtils.repeat(" ", 383));
		return sb.toString();
	}

	public static MerchantBatchHeader30 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		MerchantBatchHeader30 o = new MerchantBatchHeader30();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setUniqueBatchId(s.substring(8, 24));
		o.setBusinessDateId(s.substring(24, 32));
		o.setCloseDate(s.substring(32, 38));
		o.setMerchantNumber(s.substring(38, 54));
		o.setBatchId(s.substring(54, 66));
		o.setTerminalId(s.substring(66, 78));
		o.setTerminalNumber(s.substring(78, 82));
		o.setTerminalApp(s.substring(82, 90));
		o.setSourceId(s.substring(90, 102));
		o.setCurrencyCode(s.substring(102, 106));
		o.setDebitCount(s.substring(106, 111));
		o.setDebitAmount(s.substring(111, 122));
		o.setCreditCount(s.substring(122, 127));
		o.setCreditAmount(s.substring(127, 138));
		o.setSicCode(s.substring(138, 142));
		o.setSubmitterName(s.substring(142, 165));
		return o;
	}

}
