package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * FILE TRAILER RECORD, TYPE 99
 * 
 * @author nickfeng
 *
 */
public class BFileTrailer {

	/**
	 * Record Type, 1-2, AN 2, Always 99.
	 */
	private String recordType = "99";

	/* Filler, 3-8, AN 6, Space Filled. */

	/**
	 * Big Batch ID, 8-12, N 4, Global Payments-assigned Big Batch ID used to
	 * identify the customer.
	 */
	private String bigBatchId;

	/* Filler, 13-47, AN 35, Space Filled. */

	/**
	 * Total Amount Sign, 48-48, AN 1, Codes are:
	 * <ul>
	 * <li>+ Positive</li>
	 * <li>space Positive</li>
	 * <li>- Negative</li>
	 * </ul>
	 */
	private char totalAmountSign;

	/**
	 * Total Amount, 49-58, N 10, The net sum of amount fields from all Detail
	 * Records. Right justified, zero filled. Decimal places based on the
	 * transaction currency.
	 */
	private String totalAmount;

	/**
	 * Record Count, 59-64, N 6, Number of records in the file including header,
	 * addendum, and trailer records. Right justified, zero filled.
	 */
	private String recordCount;

	/* Filler, 65-94, AN 30, Space Filled. */

	/**
	 * Record Sequence Number, 95-100, N 6, Sequence number of the previous record
	 * plus 1.
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

	public char getTotalAmountSign() {
		return totalAmountSign;
	}

	public void setTotalAmountSign(char totalAmountSign) {
		this.totalAmountSign = totalAmountSign;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
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
		sb.append(StringUtils.repeat(" ", 35));
		sb.append(totalAmountSign);
		sb.append(StringUtils.leftPad(totalAmount, 10, '0'));
		sb.append(StringUtils.leftPad(recordCount, 6, '0'));
		sb.append(StringUtils.repeat(" ", 30));
		sb.append(StringUtils.leftPad(recordSequenceNumber, 6, '0'));
		return sb.toString();
	}

	public static BFileTrailer fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 100 || !s.startsWith("99")) {
			return null;
		}
		BFileTrailer o = new BFileTrailer();
		o.setBigBatchId(s.substring(8, 12));
		o.setTotalAmountSign(s.charAt(47));
		o.setTotalAmount(s.substring(48, 58));
		o.setRecordCount(s.substring(58, 64));
		o.setRecordSequenceNumber(s.substring(94, 100));
		return o;
	}
}
