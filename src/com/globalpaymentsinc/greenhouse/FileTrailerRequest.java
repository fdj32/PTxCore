package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * FILE TRAILER REQUEST RECORD, TYPE FT
 * 
 * @author nickfeng
 *
 */
public class FileTrailerRequest {

	/**
	 * Record Type, 1-2, AN 2, Always FT.
	 */
	private String recordType = "FT";

	/* Filler, 3-8, AN 6, Space filled. */

	/**
	 * Big Batch ID, 9-12, N 4, Global Payments-assigned number used to identify the
	 * customer.
	 */
	private String bigBatchId;

	/**
	 * Total Number Records, 13-22, N 10, Total number of records on this file
	 * including all header and trailer records.
	 */
	private String totalNumberRecords;

	/* Filler, 23-80, AN 58, Space filled. */

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

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(StringUtils.repeat(" ", 6));
		sb.append(bigBatchId);
		sb.append(StringUtils.leftPad(totalNumberRecords, 10, '0'));
		sb.append(StringUtils.repeat(" ", 58));
		return sb.toString();
	}

	public static FileTrailerRequest fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("FT")) {
			return null;
		}
		FileTrailerRequest ft = new FileTrailerRequest();
		ft.setBigBatchId(s.substring(8, 12));
		ft.setTotalNumberRecords(s.substring(12, 22));
		return ft;
	}

}
