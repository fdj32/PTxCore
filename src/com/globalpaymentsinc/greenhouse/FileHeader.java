package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * FILE HEADER RECORD, TYPE FH
 * 
 * @author nickfeng
 *
 */
public class FileHeader {
	/**
	 * Record Type, 1-2, AN 2, Always FH.
	 */
	private String recordType = "FH";
	/* Filler, 3-4, AN 2, Space filled. */
	/**
	 * Date/Time, 5-16, N 12, File creation date and time in YYMMDDHHMMSS format.
	 */
	private String dateTime;
	/**
	 * Sequence Number, 17-20, N 4, Unique number to identify this file from others
	 * sent on the same day.
	 */
	private String sequenceNumber;
	/* Reserved, 21-21, AN 1, Space filled. Reserved. */
	/**
	 * Account Update Flag, 22-22, AN 1, Valid value for files containing Account
	 * Inquiry Request records are:
	 * <ul>
	 * <li>V - File inquiry is sent to Visa</li>
	 * <li>M - File inquiry is sent to MasterCard</li>
	 * <li>D - File inquiry is sent to Discover</li>
	 * <li>Otherwise, space filled.</li>
	 * </ul>
	 */
	private char accountUpdateFlag = ' ';

	/**
	 * Visa Segment ID/MasterCard Member ID/Discover Acquirer ID 23-28, N 6
	 * <ul>
	 * <li>For Visa: The Visa-assigned ID used for VAU. Right justified, zero
	 * filled.</li>
	 * <li>For MasterCard: The MasterCard assigned member ID. Right justified, zero
	 * filled.</li>
	 * <li>For Discover: The Discover assigned Acquirer ID. Right justified, zero
	 * filled.</li>
	 * </ul>
	 * Only used for files containing Account Inquiry Request Records.
	 */
	private String cardGroupId;
	/**
	 * Big Batch ID, 29-32, N 4, Global Payments-assigned number used to identify
	 * the customer.
	 */
	private String bigBatchId;
	/* Filler, 33-40, AN 8, Space filled. */
	/**
	 * Customer Defined, 41-72, AN 32, Customer Defined Data to be echoed in the
	 * Response File. Field is space filled if not used. Not used for Account
	 * Inquiry files.
	 */
	private String customerDefined;

	/* Reserved, 73-80, AN 8, Space filled. Reserved. */
	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public char getAccountUpdateFlag() {
		return accountUpdateFlag;
	}

	public void setAccountUpdateFlag(char accountUpdateFlag) {
		this.accountUpdateFlag = accountUpdateFlag;
	}

	public String getCardGroupId() {
		return cardGroupId;
	}

	public void setCardGroupId(String cardGroupId) {
		this.cardGroupId = cardGroupId;
	}

	public String getBigBatchId() {
		return bigBatchId;
	}

	public void setBigBatchId(String bigBatchId) {
		this.bigBatchId = bigBatchId;
	}

	public String getCustomerDefined() {
		return customerDefined;
	}

	public void setCustomerDefined(String customerDefined) {
		this.customerDefined = customerDefined;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(StringUtils.repeat(" ", 2));
		sb.append(dateTime);
		sb.append(sequenceNumber);
		sb.append(" ");
		sb.append(accountUpdateFlag);
		sb.append(StringUtils.leftPad(cardGroupId, 6, '0'));
		sb.append(bigBatchId);
		sb.append(StringUtils.repeat(" ", 8));
		sb.append(StringUtils.rightPad(customerDefined, 32, ' '));
		sb.append(StringUtils.repeat(" ", 8));
		return sb.toString();
	}

	public FileHeader fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("FH  ")) {
			return null;
		}
		FileHeader fh = new FileHeader();
		fh.setDateTime(s.substring(4, 16));
		fh.setSequenceNumber(s.substring(16, 20));
		fh.setAccountUpdateFlag(s.charAt(21));
		fh.setCardGroupId(s.substring(22, 28));
		fh.setBigBatchId(s.substring(28, 32));
		fh.setCustomerDefined(s.substring(40, 72));
		return fh;
	}

}
