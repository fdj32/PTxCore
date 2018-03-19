package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * BIG BATCH STANDARD SETTLEMENT FILE FORMAT / FILE HEADER RECORD, TYPE 00
 * 
 * @author nickfeng
 *
 */
public class BFileHeader {
	/**
	 * Record Type, 1-2, AN 2, Always 00.
	 */
	private String recordType = "00";

	/* Filler, 3-8, AN 6, Space filled. */

	/**
	 * Big Batch ID, 9-12, N 4, Global Payments-assigned Big Batch ID used to
	 * identify the customer.
	 */
	private String bigBatchId;

	/* Filler, 13-48, AN 36, Space filled. */

	/**
	 * Subscriber ID, 49-94, AN 46, Free-form identification, left justified, space
	 * filled.
	 */
	private String subscriberId;

	/**
	 * Record Sequence Number, 95-100, N 6, The first record is 000000. Subsequent
	 * records are incremented by 1.
	 */
	private String recordSequenceNumber;

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

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
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
		sb.append(StringUtils.repeat(" ", 6));
		sb.append(bigBatchId);
		sb.append(StringUtils.repeat(" ", 36));
		sb.append(StringUtils.rightPad(subscriberId, 46, ' '));
		sb.append(StringUtils.leftPad(recordSequenceNumber, 6, '0'));
		return sb.toString();
	}

	public static BFileHeader fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 100 || !s.startsWith("00")) {
			return null;
		}
		BFileHeader bfh = new BFileHeader();
		bfh.setBigBatchId(s.substring(8, 12));
		bfh.setSubscriberId(s.substring(48, 94));
		bfh.setRecordSequenceNumber(s.substring(94, 100));
		return bfh;
	}

}
