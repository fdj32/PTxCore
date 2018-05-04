package com.heartlandportico.hps.odaf;

import org.apache.commons.lang.StringUtils;

public class ControlSection {

	/**
	 * 1-6, Logical Record Number Incremented for each File Record Type or
	 * Transaction ie: File Header is first logical record, Detail Batch Header is
	 * the next logical record. Trans 51,52,53 would be 1 Logical record number.
	 */
	private String logicalRecordNumber;
	/**
	 * 7, Sequence number within a logical record. For example, 3 records within 1
	 * logical record sequence = 1 (of 3), 2(of 3), 3(of 3)
	 */
	private char recordSequenceNumber;
	/**
	 * 8, Number of records within 1 logical record. For example, trans 51, 52, 53
	 * is 1 logical record with a total number of records = 3
	 */
	private char totalNumberOfRecords;
	/**
	 * 9-10, Values are:
	 * <ul>
	 * <li>10 - File Header Record</li>
	 * <li>20 - Detail Batch Header Record</li>
	 * <li>51 - Credit Detail Record 1</li>
	 * <li>52 - Credit Detail Record 2</li>
	 * <li>53 - Credit Detail Record 3</li>
	 * <li>70 - Reconciliation Batch Header Record</li>
	 * <li>71 - Reconciliation Record 1</li>
	 * <li>72 - Reconciliation Record 2</li>
	 * <li>73 - Reconciliation Record 3</li>
	 * <li>75 - Reconciliation Record 5</li>
	 * <li>79 - Reconciliation Batch Trailer Record</li>
	 * <li>80 - Detail Batch Trailer Record</li>
	 * <li>90 - File Trailer Record</li>
	 * </ul>
	 */
	private String recordType;

	public String getLogicalRecordNumber() {
		return logicalRecordNumber;
	}

	public void setLogicalRecordNumber(String logicalRecordNumber) {
		this.logicalRecordNumber = logicalRecordNumber;
	}

	public char getRecordSequenceNumber() {
		return recordSequenceNumber;
	}

	public void setRecordSequenceNumber(char recordSequenceNumber) {
		this.recordSequenceNumber = recordSequenceNumber;
	}

	public char getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}

	public void setTotalNumberOfRecords(char totalNumberOfRecords) {
		this.totalNumberOfRecords = totalNumberOfRecords;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(logicalRecordNumber);
		sb.append(recordSequenceNumber);
		sb.append(totalNumberOfRecords);
		sb.append(recordType);
		return sb.toString();
	}

	public static ControlSection fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 10) {
			return null;
		}
		ControlSection o = new ControlSection();
		o.setLogicalRecordNumber(s.substring(0, 6));
		o.setRecordSequenceNumber(s.charAt(6));
		o.setTotalNumberOfRecords(s.charAt(7));
		o.setRecordType(s.substring(8, 10));
		return o;
	}

}
