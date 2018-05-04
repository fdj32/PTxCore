package com.heartlandportico.hps.odaf;

import org.apache.commons.lang.StringUtils;

public class BatchHeader20 {

	/**
	 * 1-10, Refer to Control Section – Record Type = '20'
	 */
	private ControlSection cs;

	/**
	 * 11-13, 'DTL'
	 */
	private String batchType;

	/**
	 * 14-16, Division
	 */
	private String divisionNumber;

	/* Filler, 17-20, AN 4, Space Filled. */

	/**
	 * 21-36, External Client or merchant number
	 */
	private String merchantNumber;

	/**
	 * 37-51, Terminal number
	 */
	private String merchantTerminalNumber;

	/* Filler, 52-55, AN 4, Space Filled. */

	/**
	 * 56-60, Batch number
	 */
	private String batchNumber;

	/**
	 * 61-65, Sequence number of the batch within this file
	 */
	private String batchSequenceNumber;

	/* Filler, 66-250, AN 185, Space Filled. */

	public ControlSection getCs() {
		return cs;
	}

	public void setCs(ControlSection cs) {
		this.cs = cs;
	}

	public String getBatchType() {
		return batchType;
	}

	public void setBatchType(String batchType) {
		this.batchType = batchType;
	}

	public String getDivisionNumber() {
		return divisionNumber;
	}

	public void setDivisionNumber(String divisionNumber) {
		this.divisionNumber = divisionNumber;
	}

	public String getMerchantNumber() {
		return merchantNumber;
	}

	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
	}

	public String getMerchantTerminalNumber() {
		return merchantTerminalNumber;
	}

	public void setMerchantTerminalNumber(String merchantTerminalNumber) {
		this.merchantTerminalNumber = merchantTerminalNumber;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getBatchSequenceNumber() {
		return batchSequenceNumber;
	}

	public void setBatchSequenceNumber(String batchSequenceNumber) {
		this.batchSequenceNumber = batchSequenceNumber;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(cs.toString());
		sb.append(batchType);
		sb.append(divisionNumber);
		sb.append(StringUtils.repeat(" ", 4));
		sb.append(merchantNumber);
		sb.append(merchantTerminalNumber);
		sb.append(StringUtils.repeat(" ", 4));
		sb.append(batchNumber);
		sb.append(batchSequenceNumber);
		sb.append(StringUtils.repeat(" ", 185));
		return sb.toString();
	}

	public static BatchHeader20 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 250) {
			return null;
		}
		BatchHeader20 o = new BatchHeader20();
		o.setCs(ControlSection.fromString(s.substring(0, 10)));
		o.setBatchType(s.substring(10, 13));
		o.setDivisionNumber(s.substring(13, 16));
		o.setMerchantNumber(s.substring(20, 36));
		o.setBatchNumber(s.substring(55, 60));
		o.setBatchSequenceNumber(s.substring(60, 65));
		return o;
	}

}
