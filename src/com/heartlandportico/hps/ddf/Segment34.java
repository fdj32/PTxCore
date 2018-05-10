package com.heartlandportico.hps.ddf;

import org.apache.commons.lang.StringUtils;

public class Segment34 {

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
	 * 25, 3 = Segment 3, 4 = Segment 4
	 * 
	 */
	private char segmentNumber;

	/**
	 * 26, Total number of segments for this transaction.
	 */
	private char totalSegments;

	/**
	 * 26-106
	 */
	private String merchantResearch;

	/* Filler, 107-260, AN 154, Reserved Spaces. */

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

	public String getMerchantResearch() {
		return merchantResearch;
	}

	public void setMerchantResearch(String merchantResearch) {
		this.merchantResearch = merchantResearch;
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
		sb.append(merchantResearch);
		sb.append(StringUtils.repeat(" ", 154));
		return sb.toString();
	}

	public static Segment34 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 260) {
			return null;
		}
		Segment34 o = new Segment34();
		o.setRecordFormat(s.substring(0, 2));
		o.setRecordType(s.substring(2, 4));
		o.setReportDate(s.substring(4, 10));
		o.setTransactionType(s.substring(10, 12));
		o.setRecordNumber(s.substring(12, 24));
		o.setSegmentNumber(s.charAt(24));
		o.setTotalSegments(s.charAt(25));
		o.setMerchantResearch(s.substring(26, 106));
		return o;
	}

}
