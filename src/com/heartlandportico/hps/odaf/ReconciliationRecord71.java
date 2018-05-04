package com.heartlandportico.hps.odaf;

import org.apache.commons.lang.StringUtils;

public class ReconciliationRecord71 {

	/**
	 * 1-10, Refer to Control Section – Record Type = '70'
	 */
	private ControlSection cs;

	/**
	 * 11-12
	 * <ul>
	 * <li>‘CH’ – Chain Level Reconciliation</li>
	 * <li>‘DV’ – Division Level Reconciliation</li>
	 * <li>‘ST’ – Store Level Reconciliation</li>
	 * </ul>
	 */
	private String reconciliationRecordLevel;

	/**
	 * 13-16, MMDD = Submission Date, the date transactions received for processing.
	 */
	private String submissionDate;

	/**
	 * 17-20, Time transactions received for processing – HHMM
	 */
	private String submissionTime;

	/**
	 * 21-24, Date file was created - MMDD
	 */
	private String fileDate;

	/**
	 * 25-27, ‘TCF’ - Transaction Clearing File
	 */
	private String fileType;

	/**
	 * 28-30, Division Number. Spaces on Chain level records
	 */
	private String divisionNumber;

	/* Filler, 31-34, AN 4, Space Filled. */

	/**
	 * 35-50, External Client or merchant number
	 */
	private String merchantNumber;

	/**
	 * 51-61, Sales minus Returns
	 */
	private String netSubmittedSalesAmount;

	/**
	 * 62
	 * <ul>
	 * <li>‘C’ - Credit (receives money)</li>
	 * <li>‘D’ - Debit (owes money)</li>
	 * </ul>
	 */
	private char netSubmittedSign;

	/**
	 * 63-71
	 */
	private String rejectedAmount;

	/**
	 * 72
	 * <ul>
	 * <li>‘C’ - Credit (receives money)</li>
	 * <li>‘D’ – Debit (owes money)</li>
	 * </ul>
	 */
	private char rejectedAmountSign;

	/* Filler, 73-250, AN 178, Space Filled. */

	public ControlSection getCs() {
		return cs;
	}

	public void setCs(ControlSection cs) {
		this.cs = cs;
	}

	public String getReconciliationRecordLevel() {
		return reconciliationRecordLevel;
	}

	public void setReconciliationRecordLevel(String reconciliationRecordLevel) {
		this.reconciliationRecordLevel = reconciliationRecordLevel;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(String submissionTime) {
		this.submissionTime = submissionTime;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getDivisionNumber() {
		return divisionNumber;
	}

	public void setDivisionNumber(String divisionNumber) {
		this.divisionNumber = divisionNumber;
	}

	public String getMerchantNumber() {
		return merchantNumber;
	}

	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
	}

	public String getNetSubmittedSalesAmount() {
		return netSubmittedSalesAmount;
	}

	public void setNetSubmittedSalesAmount(String netSubmittedSalesAmount) {
		this.netSubmittedSalesAmount = netSubmittedSalesAmount;
	}

	public char getNetSubmittedSign() {
		return netSubmittedSign;
	}

	public void setNetSubmittedSign(char netSubmittedSign) {
		this.netSubmittedSign = netSubmittedSign;
	}

	public String getRejectedAmount() {
		return rejectedAmount;
	}

	public void setRejectedAmount(String rejectedAmount) {
		this.rejectedAmount = rejectedAmount;
	}

	public char getRejectedAmountSign() {
		return rejectedAmountSign;
	}

	public void setRejectedAmountSign(char rejectedAmountSign) {
		this.rejectedAmountSign = rejectedAmountSign;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(cs.toString());
		sb.append(reconciliationRecordLevel);
		sb.append(submissionDate);
		sb.append(submissionTime);
		sb.append(fileDate);
		sb.append(fileType);
		sb.append(divisionNumber);
		sb.append(StringUtils.repeat(" ", 4));
		sb.append(merchantNumber);
		sb.append(netSubmittedSalesAmount);
		sb.append(netSubmittedSign);
		sb.append(rejectedAmount);
		sb.append(rejectedAmountSign);
		sb.append(StringUtils.repeat(" ", 178));
		return sb.toString();
	}

	public static ReconciliationRecord71 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 250) {
			return null;
		}
		ReconciliationRecord71 o = new ReconciliationRecord71();
		o.setCs(ControlSection.fromString(s.substring(0, 10)));
		o.setReconciliationRecordLevel(s.substring(10, 12));
		o.setSubmissionDate(s.substring(12, 16));
		o.setSubmissionTime(s.substring(16, 20));
		o.setFileDate(s.substring(20, 24));
		o.setFileType(s.substring(24, 27));
		o.setDivisionNumber(s.substring(27, 30));
		o.setMerchantNumber(s.substring(34, 50));
		o.setNetSubmittedSalesAmount(s.substring(50, 61));
		o.setNetSubmittedSign(s.charAt(61));
		o.setRejectedAmount(s.substring(62, 71));
		o.setRejectedAmountSign(s.charAt(71));
		return o;
	}

}
