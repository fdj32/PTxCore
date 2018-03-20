package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * INTEGRATED CIRCUIT CARD (ICC) ADDENDUM EXTENSION RECORD, TYPE 13
 * 
 * @author nickfeng
 *
 */
public class BICCAddendumExtension13 {

	/**
	 * Record Type, 1-2, AN 2, Always 13.
	 */
	private String recordType = "13";

	/**
	 * Terminal Verification Results Tag 95, 3-12, AN 10, Indicates the status of
	 * the different functions as seen from the terminal.
	 */
	private String terminalVerificationResults;

	/**
	 * Transaction Date Tag 9A, 13-18, N 6, Indicates the local date that an
	 * authorization response is received by a chip card terminal for a chip card
	 * transaction.
	 */
	private String transactionDate;

	/**
	 * Transaction Type Tag 9C, 19-20, N 2, Indicates the process code used in the
	 * authorization request for the chip card transaction.
	 */
	private String transactionType;

	/**
	 * Cryptogram Currency Code Tag 5F2A, 21-23, N 3, Indicates the currency code
	 * assigned to the originating currency in which the merchant conducted a chip
	 * card transaction.
	 */
	private String cryptogramCurrencyCode;

	/**
	 * Other Amount Tag 9F03, 24-35, N 12, Indicates the amount of cash over
	 * dispensed in association with a card sale. Decimal implied based on the
	 * Cryptogram Currency Code.
	 */
	private String otherAmount;

	/**
	 * Unpredictable Number Tag 9F37, 36-43, AN 8, Value to provide variability and
	 * uniqueness to the generation of a cryptogram.
	 */
	private String unpredictableNumber;

	/**
	 * Application Usage Control Tag 9F07, 44-47, AN 4, Indicates the Issuer's
	 * specified restrictions on the geographic usage and services allowed for the
	 * application. For future use.
	 */
	private String applicationUsageControl;

	/**
	 * Cardholder Verification Method (CVM) Results Tag 9F34, 48-53, AN 6, Indicates
	 * the results of the last CVM performed.
	 */
	private String cvmResults;

	/**
	 * Application Identifier (AID) Tag 9F06, 54-85, AN 32, Identifies the
	 * application.
	 */
	private String aid;

	/**
	 * Application Version Number Tag 9F09, 86-89, AN 4, Version number assigned by
	 * the payment system for the application.
	 */
	private String applicationVersionNumber;

	/* Filler, 90-94, AN 5, Space Filled. */

	/**
	 * Record Sequence Number, 95-100, N 6, Previous Record Sequence Number plus 1.
	 */
	private String recordSequenceNumber;

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getTerminalVerificationResults() {
		return terminalVerificationResults;
	}

	public void setTerminalVerificationResults(String terminalVerificationResults) {
		this.terminalVerificationResults = terminalVerificationResults;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getCryptogramCurrencyCode() {
		return cryptogramCurrencyCode;
	}

	public void setCryptogramCurrencyCode(String cryptogramCurrencyCode) {
		this.cryptogramCurrencyCode = cryptogramCurrencyCode;
	}

	public String getOtherAmount() {
		return otherAmount;
	}

	public void setOtherAmount(String otherAmount) {
		this.otherAmount = otherAmount;
	}

	public String getUnpredictableNumber() {
		return unpredictableNumber;
	}

	public void setUnpredictableNumber(String unpredictableNumber) {
		this.unpredictableNumber = unpredictableNumber;
	}

	public String getApplicationUsageControl() {
		return applicationUsageControl;
	}

	public void setApplicationUsageControl(String applicationUsageControl) {
		this.applicationUsageControl = applicationUsageControl;
	}

	public String getCvmResults() {
		return cvmResults;
	}

	public void setCvmResults(String cvmResults) {
		this.cvmResults = cvmResults;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getApplicationVersionNumber() {
		return applicationVersionNumber;
	}

	public void setApplicationVersionNumber(String applicationVersionNumber) {
		this.applicationVersionNumber = applicationVersionNumber;
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
		sb.append(terminalVerificationResults);
		sb.append(transactionDate);
		sb.append(transactionType);
		sb.append(cryptogramCurrencyCode);
		sb.append(otherAmount);
		sb.append(unpredictableNumber);
		sb.append(applicationUsageControl);
		sb.append(cvmResults);
		sb.append(aid);
		sb.append(applicationVersionNumber);
		sb.append(StringUtils.repeat(" ", 5));
		sb.append(StringUtils.leftPad(recordSequenceNumber, 6, '0'));
		return sb.toString();
	}

	public static BICCAddendumExtension13 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 100 || !s.startsWith("13")) {
			return null;
		}
		BICCAddendumExtension13 o = new BICCAddendumExtension13();
		o.setTerminalVerificationResults(s.substring(2, 12));
		o.setTransactionDate(s.substring(12, 18));
		o.setTransactionType(s.substring(18, 20));
		o.setCryptogramCurrencyCode(s.substring(20, 23));
		o.setOtherAmount(s.substring(23, 35));
		o.setUnpredictableNumber(s.substring(35, 43));
		o.setApplicationUsageControl(s.substring(43, 47));
		o.setCvmResults(s.substring(47, 53));
		o.setAid(s.substring(53, 85));
		o.setApplicationVersionNumber(s.substring(85, 89));
		o.setRecordSequenceNumber(s.substring(94, 100));
		return o;
	}

}
