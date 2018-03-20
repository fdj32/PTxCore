package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * TERMINAL SETTLEMENT TRAILER REQUEST RECORD, TYPE 96
 * 
 * @author nickfeng
 *
 */
public class BTerminalSettlementTrailerRequest {

	/**
	 * Record Type, 1-2, AN 2, Always 96.
	 */
	private String recordType = "96";

	/* Filler, 3-3, AN 1, Space Filled. */

	/**
	 * Batch Item Count, 4-8, N 5, The number of detail transactions in this
	 * Settlement Batch including detail and addendum records.
	 */
	private String batchItemCount;

	/**
	 * Batch Creation Date, 9-13, N 5, The Julian Date this Settlement Batch was
	 * created. YYDDD (YYJJJ) format.
	 */
	private String batchCreationDate;

	/**
	 * Batch Creation Time, 14-19, N 6, The time this Settlement Batch was created.
	 * HHMMSS format.
	 */
	private String batchCreationTime;

	/**
	 * Batch Amount Sign, 20-20, AN 1, Codes are:
	 * <ul>
	 * <li>+ Positive</li>
	 * <li>space Positive</li>
	 * <li>- Negative</li>
	 * </ul>
	 */
	private char batchAmountSign;

	/**
	 * Batch Amount, 21-30, N 10, The net amount of the batch. Right justified, zero
	 * filled. Decimal places based on the transaction currency
	 */
	private String batchAmount;

	/**
	 * Terminal ID, 31-49, AN 19, The assigned Terminal ID set up on Global
	 * Payments' Customer Master File. Left justified, space filled.
	 */
	private String terminalId;

	/**
	 * Vendor ID, 50-59, AN 10, Identifies the vendor supplying the Big Batch
	 * software. Left justified, space filled.
	 */
	private String vendorId;

	/**
	 * Application ID, 60-74, AN 15, Identifies the application and its version.
	 * Left justified, space filled.
	 */
	private String applicationId;

	/* Filler, 75-94, AN 20, Space Filled. */

	/**
	 * Record Sequence Number, 95-100, N 6, Sequence number of the previous record
	 * plus 1.
	 */
	private String recordSequenceNumber;

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getBatchItemCount() {
		return batchItemCount;
	}

	public void setBatchItemCount(String batchItemCount) {
		this.batchItemCount = batchItemCount;
	}

	public String getBatchCreationDate() {
		return batchCreationDate;
	}

	public void setBatchCreationDate(String batchCreationDate) {
		this.batchCreationDate = batchCreationDate;
	}

	public String getBatchCreationTime() {
		return batchCreationTime;
	}

	public void setBatchCreationTime(String batchCreationTime) {
		this.batchCreationTime = batchCreationTime;
	}

	public char getBatchAmountSign() {
		return batchAmountSign;
	}

	public void setBatchAmountSign(char batchAmountSign) {
		this.batchAmountSign = batchAmountSign;
	}

	public String getBatchAmount() {
		return batchAmount;
	}

	public void setBatchAmount(String batchAmount) {
		this.batchAmount = batchAmount;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getRecordSequenceNumber() {
		return recordSequenceNumber;
	}

	public void setRecordSequenceNumber(String recordSequenceNumber) {
		this.recordSequenceNumber = recordSequenceNumber;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(' ');
		sb.append(StringUtils.leftPad(batchItemCount, 6, '0'));
		sb.append(batchCreationDate);
		sb.append(batchCreationTime);
		sb.append(batchAmountSign);
		sb.append(StringUtils.leftPad(batchAmount, 6, '0'));
		sb.append(StringUtils.rightPad(terminalId, 19, ' '));
		sb.append(StringUtils.rightPad(vendorId, 10, ' '));
		sb.append(StringUtils.rightPad(applicationId, 15, ' '));
		sb.append(StringUtils.repeat(" ", 20));
		sb.append(StringUtils.leftPad(recordSequenceNumber, 6, '0'));
		return sb.toString();
	}

	public static BTerminalSettlementTrailerRequest fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 100 || !s.startsWith("96")) {
			return null;
		}
		BTerminalSettlementTrailerRequest o = new BTerminalSettlementTrailerRequest();
		o.setBatchItemCount(s.substring(3, 8));
		o.setBatchCreationDate(s.substring(8, 13));
		o.setBatchCreationTime(s.substring(13, 19));
		o.setBatchAmountSign(s.charAt(19));
		o.setBatchAmount(s.substring(20, 30));
		o.setTerminalId(s.substring(30, 49));
		o.setVendorId(s.substring(49, 59));
		o.setApplicationId(s.substring(59, 74));
		o.setRecordSequenceNumber(s.substring(94, 100));
		return o;
	}

}
