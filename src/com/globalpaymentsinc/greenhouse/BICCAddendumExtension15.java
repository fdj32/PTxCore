package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * INTEGRATED CIRCUIT CARD (ICC) ADDENDUM EXTENSION RECORD, TYPE 15
 * 
 * @author nickfeng
 *
 */
public class BICCAddendumExtension15 {

	/**
	 * Record Type, 1-2, AN 2, Always 15.
	 */
	private String recordType = "15";

	/**
	 * ssuer Authentication Data Tag 91, 3-34, AN 32, Data sent to the chip for
	 * online Issuer authentication.
	 */
	private String issuerAuthenticationData;

	/**
	 * Issuer Script Results Tag 9F5B, 35-84, AN 50, Indicates the result of the
	 * terminal script processing. Variable length, left justified, space filled.
	 */
	private String issuerScriptResults;

	/* Filler, 85-94, AN 10, Space Filled. */

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

	public String getIssuerAuthenticationData() {
		return issuerAuthenticationData;
	}

	public void setIssuerAuthenticationData(String issuerAuthenticationData) {
		this.issuerAuthenticationData = issuerAuthenticationData;
	}

	public String getIssuerScriptResults() {
		return issuerScriptResults;
	}

	public void setIssuerScriptResults(String issuerScriptResults) {
		this.issuerScriptResults = issuerScriptResults;
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
		sb.append(issuerAuthenticationData);
		sb.append(StringUtils.rightPad(issuerScriptResults, 50, ' '));
		sb.append(StringUtils.repeat(" ", 10));
		sb.append(StringUtils.leftPad(recordSequenceNumber, 6, '0'));
		return sb.toString();
	}

	public static BICCAddendumExtension15 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 100 || !s.startsWith("15")) {
			return null;
		}
		BICCAddendumExtension15 o = new BICCAddendumExtension15();
		o.setIssuerAuthenticationData(s.substring(2, 34));
		o.setIssuerScriptResults(s.substring(34, 84));
		o.setRecordSequenceNumber(s.substring(94, 100));
		return o;
	}
}
