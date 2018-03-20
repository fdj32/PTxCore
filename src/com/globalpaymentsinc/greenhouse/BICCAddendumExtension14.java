package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * INTEGRATED CIRCUIT CARD (ICC) ADDENDUM EXTENSION RECORD, TYPE 14
 * 
 * @author nickfeng
 *
 */
public class BICCAddendumExtension14 {

	/**
	 * Record Type, 1-2, AN 2, Always 14.
	 */
	private String recordType = "14";

	/**
	 * Interface Device (IFD) Serial Number Tag 9F1E, 3-10, AN 8, Unique and
	 * permanent serial number assigned to the IFD by the manufacturer.
	 */
	private String ifdSerialNumber;

	/**
	 * Transaction Sequence Counter Tag 9F41, 11-18, AN 8, Counter maintained by the
	 * terminal that is incremented by one for each transaction. Variable length,
	 * left justified, space filled.
	 */
	private String transactionSequenceCounter;

	/**
	 * Form Factor Indicator Tag 9F6E, 19-26, AN 8, Contains identification of the
	 * cardholder device, its security features, and the communication technology
	 * used to acquire the transaction. Variable length, left justified, space
	 * filled.
	 */
	private String formFactorIndicator;

	/**
	 * Issuer Application Data Tag 9F10, 27-90, AN 64, Contains proprietary
	 * application data for transmission to the Issuer in an online transaction.
	 * Variable length, left justified, space filled.
	 */
	private String issuerApplicationData;

	/* Filler, 91-94, AN 4, Space Filled. */

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

	public String getIfdSerialNumber() {
		return ifdSerialNumber;
	}

	public void setIfdSerialNumber(String ifdSerialNumber) {
		this.ifdSerialNumber = ifdSerialNumber;
	}

	public String getTransactionSequenceCounter() {
		return transactionSequenceCounter;
	}

	public void setTransactionSequenceCounter(String transactionSequenceCounter) {
		this.transactionSequenceCounter = transactionSequenceCounter;
	}

	public String getFormFactorIndicator() {
		return formFactorIndicator;
	}

	public void setFormFactorIndicator(String formFactorIndicator) {
		this.formFactorIndicator = formFactorIndicator;
	}

	public String getIssuerApplicationData() {
		return issuerApplicationData;
	}

	public void setIssuerApplicationData(String issuerApplicationData) {
		this.issuerApplicationData = issuerApplicationData;
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
		sb.append(ifdSerialNumber);
		sb.append(StringUtils.rightPad(transactionSequenceCounter, 8, ' '));
		sb.append(StringUtils.rightPad(formFactorIndicator, 8, ' '));
		sb.append(StringUtils.rightPad(issuerApplicationData, 64, ' '));
		sb.append(StringUtils.repeat(" ", 4));
		sb.append(StringUtils.leftPad(recordSequenceNumber, 6, '0'));
		return sb.toString();
	}

	public static BICCAddendumExtension14 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 100 || !s.startsWith("14")) {
			return null;
		}
		BICCAddendumExtension14 o = new BICCAddendumExtension14();
		o.setIfdSerialNumber(s.substring(2, 10));
		o.setTransactionSequenceCounter(s.substring(10, 18));
		o.setFormFactorIndicator(s.substring(18, 26));
		o.setIssuerApplicationData(s.substring(26, 90));
		o.setRecordSequenceNumber(s.substring(94, 100));
		return o;
	}
}
