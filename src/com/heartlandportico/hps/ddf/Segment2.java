package com.heartlandportico.hps.ddf;

import org.apache.commons.lang.StringUtils;

public class Segment2 {

	/**
	 * 1-2
	 */
	private String recordFormat = "MH";

	/**
	 * 3-4
	 */
	private String recordType = "CB";

	/**
	 * 5-10, YYMMDD. Date item received for processing.
	 */
	private String reportDate;

	/**
	 * 11-12
	 * <ul>
	 * <li>40 = Draft Retrieval</li>
	 * <li>41 = Purchase Chargeback</li>
	 * <li>42 = Chargeback of Refund</li>
	 * </ul>
	 */
	private String transactionType;

	/**
	 * 13-24, Case Number
	 */
	private String recordNumber;

	/**
	 * 25, 2 = Segment 2
	 */
	private char segmentNumber;

	/**
	 * 26, Total number of segments for this transaction.
	 */
	private char totalSegments;

	/**
	 * 27-76
	 */
	private String commentsFromIssuer;

	/**
	 * 77-126
	 */
	private String commentsToIssuer;

	/**
	 * 127-188
	 */
	private String commentsToMerchant;

	/**
	 * 189-250
	 */
	private String commentsFromMerchant;

	/* Filler, 251-260, AN 10, Reserved Spaces. */

	public String getRecordFormat() {
		return recordFormat;
	}

	public void setRecordFormat(String recordFormat) {
		this.recordFormat = recordFormat;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public char getSegmentNumber() {
		return segmentNumber;
	}

	public void setSegmentNumber(char segmentNumber) {
		this.segmentNumber = segmentNumber;
	}

	public char getTotalSegments() {
		return totalSegments;
	}

	public void setTotalSegments(char totalSegments) {
		this.totalSegments = totalSegments;
	}

	public String getCommentsFromIssuer() {
		return commentsFromIssuer;
	}

	public void setCommentsFromIssuer(String commentsFromIssuer) {
		this.commentsFromIssuer = commentsFromIssuer;
	}

	public String getCommentsToIssuer() {
		return commentsToIssuer;
	}

	public void setCommentsToIssuer(String commentsToIssuer) {
		this.commentsToIssuer = commentsToIssuer;
	}

	public String getCommentsToMerchant() {
		return commentsToMerchant;
	}

	public void setCommentsToMerchant(String commentsToMerchant) {
		this.commentsToMerchant = commentsToMerchant;
	}

	public String getCommentsFromMerchant() {
		return commentsFromMerchant;
	}

	public void setCommentsFromMerchant(String commentsFromMerchant) {
		this.commentsFromMerchant = commentsFromMerchant;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordFormat);
		sb.append(recordType);
		sb.append(reportDate);
		sb.append(transactionType);
		sb.append(recordNumber);
		sb.append(segmentNumber);
		sb.append(totalSegments);
		sb.append(commentsFromIssuer);
		sb.append(commentsToIssuer);
		sb.append(commentsToMerchant);
		sb.append(commentsFromMerchant);
		sb.append(StringUtils.repeat(" ", 10));
		return sb.toString();
	}

	public static Segment2 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 260) {
			return null;
		}
		Segment2 o = new Segment2();
		o.setRecordFormat(s.substring(0, 2));
		o.setRecordType(s.substring(2, 4));
		o.setReportDate(s.substring(4, 10));
		o.setTransactionType(s.substring(10, 12));
		o.setRecordNumber(s.substring(12, 24));
		o.setSegmentNumber(s.charAt(24));
		o.setTotalSegments(s.charAt(25));
		o.setCommentsFromIssuer(s.substring(26, 76));
		o.setCommentsToIssuer(s.substring(76, 126));
		o.setCommentsToMerchant(s.substring(126, 188));
		o.setCommentsToMerchant(s.substring(188, 250));
		return o;
	}

}
