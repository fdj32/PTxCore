package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * FILE TRAILER RESPONSE RECORD, TYPE FT
 * 
 * @author nickfeng
 *
 */
public class FileTrailerResponse {

	/**
	 * Record Type, 1-2, AN 2, Always FT.
	 */
	private String recordType = "FT";

	/**
	 * Big Batch ID, 3-6, N 4, Global Payments-assigned number used to identify the
	 * customer.
	 */
	private String bigBatchId;

	/**
	 * Total Number Records, 7-16, N 10, Total number of records on this file
	 * including all header and trailer records.
	 */
	private String totalNumberRecords;

	/**
	 * Number Approved, 17-22, N 6, Number of transactions approved for this
	 * merchant. Includes Purchases and Returns. Right justified, zero filled.
	 */
	private String numberApproved;

	/**
	 * Amount Approved, 23-34, N 12, Amount of approved transactions for this
	 * merchant. Calculated by subtracting any Return transactions from all approved
	 * Purchase transactions. Right justified, zero filled.
	 */
	private String amountApproved;

	/**
	 * Number Declined, 35-40, N 6, Number of declined transactions. Right
	 * justified, zero filled.
	 */
	private String numberDeclined;

	/**
	 * Amount Declined, 41-52, N 12, Amount of declined transactions. Right
	 * justified, zero filled.
	 */
	private String amountDeclined;

	/**
	 * Number Referred, 53-58, N 6, Number of referred transactions. Right
	 * justified, zero filled.
	 */
	private String numberReferred;

	/**
	 * Amount Referred, 59-70, N 12, Amount of referred transactions. Right
	 * justified, zero filled.
	 */
	private String amountReferred;

	/* Filler, 71-80, AN 10, Space filled. */

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getBigBatchId() {
		return bigBatchId;
	}

	public void setBigBatchId(String bigBatchId) {
		this.bigBatchId = bigBatchId;
	}

	public String getTotalNumberRecords() {
		return totalNumberRecords;
	}

	public void setTotalNumberRecords(String totalNumberRecords) {
		this.totalNumberRecords = totalNumberRecords;
	}

	public String getNumberApproved() {
		return numberApproved;
	}

	public void setNumberApproved(String numberApproved) {
		this.numberApproved = numberApproved;
	}

	public String getAmountApproved() {
		return amountApproved;
	}

	public void setAmountApproved(String amountApproved) {
		this.amountApproved = amountApproved;
	}

	public String getNumberDeclined() {
		return numberDeclined;
	}

	public void setNumberDeclined(String numberDeclined) {
		this.numberDeclined = numberDeclined;
	}

	public String getAmountDeclined() {
		return amountDeclined;
	}

	public void setAmountDeclined(String amountDeclined) {
		this.amountDeclined = amountDeclined;
	}

	public String getNumberReferred() {
		return numberReferred;
	}

	public void setNumberReferred(String numberReferred) {
		this.numberReferred = numberReferred;
	}

	public String getAmountReferred() {
		return amountReferred;
	}

	public void setAmountReferred(String amountReferred) {
		this.amountReferred = amountReferred;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(bigBatchId);
		sb.append(StringUtils.leftPad(totalNumberRecords, 10, '0'));
		sb.append(StringUtils.leftPad(numberApproved, 6, '0'));
		sb.append(StringUtils.leftPad(amountApproved, 12, '0'));
		sb.append(StringUtils.leftPad(numberDeclined, 6, '0'));
		sb.append(StringUtils.leftPad(amountDeclined, 12, '0'));
		sb.append(StringUtils.leftPad(numberReferred, 6, '0'));
		sb.append(StringUtils.leftPad(amountReferred, 12, '0'));
		sb.append(StringUtils.repeat(" ", 10));
		return sb.toString();
	}

	public static FileTrailerResponse fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("FT")) {
			return null;
		}
		FileTrailerResponse ft = new FileTrailerResponse();
		ft.setBigBatchId(s.substring(2, 6));
		ft.setTotalNumberRecords(s.substring(6, 16));
		ft.setNumberApproved(s.substring(16, 22));
		ft.setAmountApproved(s.substring(22, 34));
		ft.setNumberDeclined(s.substring(34, 40));
		ft.setAmountDeclined(s.substring(40, 52));
		ft.setNumberReferred(s.substring(52, 58));
		ft.setAmountReferred(s.substring(58, 70));
		return ft;
	}

}
