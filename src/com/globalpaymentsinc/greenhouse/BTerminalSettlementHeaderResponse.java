package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * TERMINAL SETTLEMENT HEADER RESPONSE RECORD, TYPE 03
 * 
 * @author nickfeng
 *
 */
public class BTerminalSettlementHeaderResponse {

	/**
	 * Record Type, 1-2, AN 2, Always 03.
	 */
	private String recordType = "03";

	/* Filler, 3-3, AN 1, Space filled. */

	/**
	 * Accepted Batch Item Count, 4-8, N 5, The number of detail transactions in
	 * this batch which processed successfully. Zero filled when Record Type 96 is
	 * used.
	 */
	private String acceptedBatchItemCount;

	/**
	 * Accepted Batch Item Amount Sign, 9-9, AN 1, Codes are:
	 * <ul>
	 * <li>+ Positive</li>
	 * <li>space Positive</li>
	 * <li>- Negative</li>
	 * </ul>
	 * Space filled when Record Type 96 is used.
	 */
	private char acceptedBatchItemAmountSign;

	/**
	 * Accepted Batch Item Amount, 10-19, N 10, The net amount of the successfully
	 * processed detail transactions in the batch. No sign on dollars and cents,
	 * decimal assumed, right justified, zero filled. Zero filled when Record Type
	 * 96 is used.
	 */
	private String acceptedBatchItemAmount;

	/**
	 * Rejected Batch Item Count, 20-24, N 5, The number of detail transactions in
	 * this batch which were rejected during processing. Zero filled when Record
	 * Type 96 is used.
	 */
	private String rejectedBatchItemCount;

	/**
	 * Rejected Batch Item Amount Sign, 25-25, AN 1, Codes are:
	 * <ul>
	 * <li>+ Positive</li>
	 * <li>space Positive</li>
	 * <li>- Negative</li>
	 * </ul>
	 * Space filled when Record Type 96 is used.
	 */
	private char rejectedBatchItemAmountSign;

	/**
	 * Rejected Batch Item Amount, 26-35, N 10, The net amount of the rejected
	 * detail transactions in the batch. No sign on dollars and cents, decimal
	 * assumed, right justified, zero filled. Zero filled when Record Type 96 is
	 * used.
	 * 
	 */
	private String rejectedBatchItemAmount;

	/**
	 * Terminal ID, 36-54, AN 19, The assigned Terminal ID set up on Global
	 * Payments' Customer Master File. Left justified, space filled.
	 */
	private String terminalId;

	/**
	 * Vendor ID, 55-64, AN 10, Identifies the vendor supplying the Big Batch
	 * software. Left justified, space filled. Space filled when Record Type 96 is
	 * used.
	 */
	private String vendorId;

	/**
	 * Application ID, 65-79, AN 15, Identifies the application and its version.
	 * Left justified, space filled. Space filled when Record Type 96 is used.
	 */
	private String applicationId;

	/* Filler, 80-94, AN 15, Space filled. */

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

	public String getAcceptedBatchItemCount() {
		return acceptedBatchItemCount;
	}

	public void setAcceptedBatchItemCount(String acceptedBatchItemCount) {
		this.acceptedBatchItemCount = acceptedBatchItemCount;
	}

	public char getAcceptedBatchItemAmountSign() {
		return acceptedBatchItemAmountSign;
	}

	public void setAcceptedBatchItemAmountSign(char acceptedBatchItemAmountSign) {
		this.acceptedBatchItemAmountSign = acceptedBatchItemAmountSign;
	}

	public String getAcceptedBatchItemAmount() {
		return acceptedBatchItemAmount;
	}

	public void setAcceptedBatchItemAmount(String acceptedBatchItemAmount) {
		this.acceptedBatchItemAmount = acceptedBatchItemAmount;
	}

	public String getRejectedBatchItemCount() {
		return rejectedBatchItemCount;
	}

	public void setRejectedBatchItemCount(String rejectedBatchItemCount) {
		this.rejectedBatchItemCount = rejectedBatchItemCount;
	}

	public char getRejectedBatchItemAmountSign() {
		return rejectedBatchItemAmountSign;
	}

	public void setRejectedBatchItemAmountSign(char rejectedBatchItemAmountSign) {
		this.rejectedBatchItemAmountSign = rejectedBatchItemAmountSign;
	}

	public String getRejectedBatchItemAmount() {
		return rejectedBatchItemAmount;
	}

	public void setRejectedBatchItemAmount(String rejectedBatchItemAmount) {
		this.rejectedBatchItemAmount = rejectedBatchItemAmount;
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
		sb.append(StringUtils.leftPad(acceptedBatchItemCount, 5, '0'));
		sb.append(acceptedBatchItemAmountSign);
		sb.append(StringUtils.leftPad(acceptedBatchItemAmount, 10, '0'));

		sb.append(StringUtils.leftPad(rejectedBatchItemCount, 5, '0'));
		sb.append(rejectedBatchItemAmountSign);
		sb.append(StringUtils.leftPad(rejectedBatchItemAmount, 10, '0'));

		sb.append(StringUtils.rightPad(terminalId, 19, ' '));
		sb.append(StringUtils.rightPad(vendorId, 10, ' '));
		sb.append(StringUtils.rightPad(applicationId, 15, ' '));

		sb.append(StringUtils.repeat(" ", 15));
		sb.append(StringUtils.leftPad(recordSequenceNumber, 6, '0'));
		return sb.toString();
	}

	public static BTerminalSettlementHeaderResponse fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 100 || !s.startsWith("03")) {
			return null;
		}
		BTerminalSettlementHeaderResponse bth = new BTerminalSettlementHeaderResponse();
		bth.setAcceptedBatchItemCount(s.substring(3, 8));
		bth.setAcceptedBatchItemAmountSign(s.charAt(8));
		bth.setAcceptedBatchItemAmount(s.substring(9, 19));
		bth.setRejectedBatchItemCount(s.substring(19, 24));
		bth.setRejectedBatchItemAmountSign(s.charAt(24));
		bth.setRejectedBatchItemAmount(s.substring(25, 35));
		bth.setTerminalId(s.substring(35, 54));
		bth.setVendorId(s.substring(54, 64));
		bth.setApplicationId(s.substring(64, 79));
		bth.setRecordSequenceNumber(s.substring(94, 100));
		return bth;
	}

}
