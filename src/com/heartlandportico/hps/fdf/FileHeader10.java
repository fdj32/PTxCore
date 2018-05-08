package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class FileHeader10 {

	/**
	 * 1-2, '10'
	 */
	private String recordType;

	/**
	 * 3-8, Sequential number of the record within file. Always 1
	 */
	private String recordSequenceNumber;

	/**
	 * 9-12, ‘ FDF’
	 */
	private String fileTitle;

	/**
	 * 13-22, Merchant Center Chain Number
	 */
	private String chainNumber;

	/**
	 * 23-30, Business date of the processed transactions - YYYYMMDD
	 */
	private String hpsProcessingDate;

	/**
	 * 31-38, Date the file was created - YYYYMMDD
	 */
	private String fileCreationDate;

	/**
	 * 39-44, Time the file was created - HHMMSS
	 */
	private String fileCreationTime;

	/* Filler, 45, AN 1, Space Filled. */

	/**
	 * 46-75, Merchant Center Chain Number
	 */
	private String clientName;

	/**
	 * 76-83, Total Number of physical records in the file
	 */
	private String totalNumberOfRecords;

	/* Filler, 84-550, AN 467, Space Filled. */

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

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getChainNumber() {
		return chainNumber;
	}

	public void setChainNumber(String chainNumber) {
		this.chainNumber = chainNumber;
	}

	public String getHpsProcessingDate() {
		return hpsProcessingDate;
	}

	public void setHpsProcessingDate(String hpsProcessingDate) {
		this.hpsProcessingDate = hpsProcessingDate;
	}

	public String getFileCreationDate() {
		return fileCreationDate;
	}

	public void setFileCreationDate(String fileCreationDate) {
		this.fileCreationDate = fileCreationDate;
	}

	public String getFileCreationTime() {
		return fileCreationTime;
	}

	public void setFileCreationTime(String fileCreationTime) {
		this.fileCreationTime = fileCreationTime;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}

	public void setTotalNumberOfRecords(String totalNumberOfRecords) {
		this.totalNumberOfRecords = totalNumberOfRecords;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(recordSequenceNumber);
		sb.append(fileTitle);
		sb.append(chainNumber);
		sb.append(hpsProcessingDate);
		sb.append(fileCreationDate);
		sb.append(fileCreationTime);
		sb.append(' ');
		sb.append(clientName);
		sb.append(totalNumberOfRecords);
		sb.append(StringUtils.repeat(" ", 467));
		return sb.toString();
	}

	public static FileHeader10 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		FileHeader10 o = new FileHeader10();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setFileTitle(s.substring(8, 12));
		o.setChainNumber(s.substring(12, 22));
		o.setHpsProcessingDate(s.substring(22, 30));
		o.setFileCreationDate(s.substring(30, 38));
		o.setFileCreationTime(s.substring(38, 44));
		o.setClientName(s.substring(45, 75));
		o.setTotalNumberOfRecords(s.substring(75, 83));
		return o;
	}

}
