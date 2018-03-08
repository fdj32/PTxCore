package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * TERMINAL TRAILER REQUEST RECORD, TYPE TT
 * 
 * @author nickfeng
 *
 */
public class TerminalTrailerRequest {

	/**
	 * Record Type, 1-2, AN 2, Always TT.
	 */
	private String recordType = "TT";
	/**
	 * Terminal ID, 3-21, AN 19, The assigned Terminal ID set up on Global Payments'
	 * Customer Master File. Left justified, space filled.
	 */
	private String terminalId;

	/* Filler, 22-24, AN 3, Space filled. */

	/**
	 * Number of Purchases, 25-30, N 6, Number of Purchase transactions submitted
	 * for this merchant. Right justified, zero filled.
	 */
	private String numberOfPurchases;

	/**
	 * Amount of Purchases, 31-42, N 12, Amount of Purchase transactions submitted
	 * for this merchant. Right justified, zero filled.
	 */
	private String amountOfPurchases;

	/**
	 * Number of Returns, 43-48, N 6, Number of Return transactions submitted for
	 * this merchant. Right justified, zero filled.
	 */
	private String numberOfReturns;

	/**
	 * Amount of Returns, 49-60, N 12, Amount of Return transactions submitted for
	 * this merchant. Right justified, zero filled.
	 */
	private String amountOfReturns;

	/* Filler, 61-80, AN 20, Space filled. */

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getNumberOfPurchases() {
		return numberOfPurchases;
	}

	public void setNumberOfPurchases(String numberOfPurchases) {
		this.numberOfPurchases = numberOfPurchases;
	}

	public String getAmountOfPurchases() {
		return amountOfPurchases;
	}

	public void setAmountOfPurchases(String amountOfPurchases) {
		this.amountOfPurchases = amountOfPurchases;
	}

	public String getNumberOfReturns() {
		return numberOfReturns;
	}

	public void setNumberOfReturns(String numberOfReturns) {
		this.numberOfReturns = numberOfReturns;
	}

	public String getAmountOfReturns() {
		return amountOfReturns;
	}

	public void setAmountOfReturns(String amountOfReturns) {
		this.amountOfReturns = amountOfReturns;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(StringUtils.rightPad(terminalId, 19, ' '));
		sb.append(StringUtils.repeat(" ", 3));
		sb.append(StringUtils.leftPad(numberOfPurchases, 6, '0'));
		sb.append(StringUtils.leftPad(amountOfPurchases, 12, '0'));
		sb.append(StringUtils.leftPad(numberOfReturns, 6, '0'));
		sb.append(StringUtils.leftPad(amountOfReturns, 12, '0'));
		sb.append(StringUtils.repeat(" ", 20));
		return sb.toString();
	}

	public static TerminalTrailerRequest fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("TT")) {
			return null;
		}
		TerminalTrailerRequest tt = new TerminalTrailerRequest();
		tt.setTerminalId(s.substring(2, 21));
		tt.setNumberOfPurchases(s.substring(24, 30));
		tt.setAmountOfPurchases(s.substring(30, 42));
		tt.setNumberOfReturns(s.substring(42, 48));
		tt.setAmountOfReturns(s.substring(48, 60));
		return tt;
	}

}
