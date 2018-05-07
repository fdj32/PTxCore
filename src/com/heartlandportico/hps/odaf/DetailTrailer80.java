package com.heartlandportico.hps.odaf;

import org.apache.commons.lang.StringUtils;

public class DetailTrailer80 {

	/**
	 * 1-10, Refer to Control Section – Record Type = '80'
	 */
	private ControlSection cs;

	/**
	 * 11-15
	 */
	private String batchSequenceNumber;

	/**
	 * 16-21
	 */
	private String numberOfCompletedDebits;

	/**
	 * 22-30
	 */
	private String completedDebitsAmount;

	/**
	 * 31-36
	 */
	private String numberOfCompletedCredits;

	/**
	 * 37-45
	 */
	private String completedCreditsAmount;

	/* Filler, 46-54, AN 9, Space Filled. */

	/**
	 * 55-63
	 */
	private String netAmount;

	/**
	 * 64
	 */
	private char netAmountSign;

	/**
	 * 65-71
	 */
	private String logicalCount;

	/* Filler, 72-250, AN 179, Space Filled. */

	public ControlSection getCs() {
		return cs;
	}

	public void setCs(ControlSection cs) {
		this.cs = cs;
	}

	public String getBatchSequenceNumber() {
		return batchSequenceNumber;
	}

	public void setBatchSequenceNumber(String batchSequenceNumber) {
		this.batchSequenceNumber = batchSequenceNumber;
	}

	public String getNumberOfCompletedDebits() {
		return numberOfCompletedDebits;
	}

	public void setNumberOfCompletedDebits(String numberOfCompletedDebits) {
		this.numberOfCompletedDebits = numberOfCompletedDebits;
	}

	public String getCompletedDebitsAmount() {
		return completedDebitsAmount;
	}

	public void setCompletedDebitsAmount(String completedDebitsAmount) {
		this.completedDebitsAmount = completedDebitsAmount;
	}

	public String getNumberOfCompletedCredits() {
		return numberOfCompletedCredits;
	}

	public void setNumberOfCompletedCredits(String numberOfCompletedCredits) {
		this.numberOfCompletedCredits = numberOfCompletedCredits;
	}

	public String getCompletedCreditsAmount() {
		return completedCreditsAmount;
	}

	public void setCompletedCreditsAmount(String completedCreditsAmount) {
		this.completedCreditsAmount = completedCreditsAmount;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public char getNetAmountSign() {
		return netAmountSign;
	}

	public void setNetAmountSign(char netAmountSign) {
		this.netAmountSign = netAmountSign;
	}

	public String getLogicalCount() {
		return logicalCount;
	}

	public void setLogicalCount(String logicalCount) {
		this.logicalCount = logicalCount;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(cs.toString());
		sb.append(batchSequenceNumber);
		sb.append(numberOfCompletedDebits);
		sb.append(completedDebitsAmount);
		sb.append(numberOfCompletedCredits);
		sb.append(completedCreditsAmount);
		sb.append(StringUtils.repeat(" ", 9));
		sb.append(netAmount);
		sb.append(netAmountSign);
		sb.append(logicalCount);
		sb.append(StringUtils.repeat(" ", 179));
		return sb.toString();
	}

	public static DetailTrailer80 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 250) {
			return null;
		}
		DetailTrailer80 o = new DetailTrailer80();
		o.setCs(ControlSection.fromString(s.substring(0, 10)));
		o.setBatchSequenceNumber(s.substring(10, 15));
		o.setNumberOfCompletedDebits(s.substring(15, 21));
		o.setCompletedDebitsAmount(s.substring(21, 30));
		o.setNumberOfCompletedCredits(s.substring(30, 36));
		o.setCompletedCreditsAmount(s.substring(36, 45));
		o.setNetAmount(s.substring(54, 63));
		o.setNetAmountSign(s.charAt(63));
		o.setLogicalCount(s.substring(64, 71));
		return o;
	}

}
