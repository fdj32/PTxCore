package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * TERMINAL TRAILER RESPONSE RECORD, TYPE TT
 * 
 * @author nickfeng
 *
 */
public class TerminalTrailerResponse {
	/**
	 * Record Type, 1-2, AN 2, Always TT.
	 */
	private String recordType = "TT";
	/**
	 * Terminal ID, 3-21, AN 19, The assigned Terminal ID set up on Global Payments'
	 * Customer Master File. Left justified, space filled.
	 */
	private String terminalId;

	/**
	 * Number Approved, 22-27, N 6, Number of transactions approved for this
	 * merchant. Includes Purchases and Returns. Right justified, zero filled.
	 */
	private String numberApproved;

	/**
	 * Amount Approved, 28-39, N 12, Amount of approved transactions for this
	 * merchant. Calculated by subtracting any Return transactions from all approved
	 * Purchase transactions. Right justified, zero filled.
	 */
	private String amountApproved;

	/**
	 * Number Declined, 40-45, N 6, Number of declined transactions. Right
	 * justified, zero filled.
	 */
	private String numberDeclined;

	/**
	 * Amount Declined, 46-57, N 12, Amount of declined transactions. Right
	 * justified, zero filled.
	 */
	private String amountDeclined;

	/**
	 * Number Referred, 58-63, N 6, Number of referred transactions. Right
	 * justified, zero filled.
	 */
	private String numberReferred;

	/**
	 * Amount Referred, 64-75, N 12, Amount of referred transactions. Right
	 * justified, zero filled.
	 */
	private String amountReferred;

	/* Filler, 76-80, AN 5, Space filled. */

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

	public String getNumberApproved() {
		return numberApproved;
	}

	public void setNumberApproved(String numberApproved) {
		this.numberApproved = numberApproved;
	}

	public String getAmountApproved() {
		return amountApproved;
	}

	public void setAmountApproved(String amountApproved) {
		this.amountApproved = amountApproved;
	}

	public String getNumberDeclined() {
		return numberDeclined;
	}

	public void setNumberDeclined(String numberDeclined) {
		this.numberDeclined = numberDeclined;
	}

	public String getAmountDeclined() {
		return amountDeclined;
	}

	public void setAmountDeclined(String amountDeclined) {
		this.amountDeclined = amountDeclined;
	}

	public String getNumberReferred() {
		return numberReferred;
	}

	public void setNumberReferred(String numberReferred) {
		this.numberReferred = numberReferred;
	}

	public String getAmountReferred() {
		return amountReferred;
	}

	public void setAmountReferred(String amountReferred) {
		this.amountReferred = amountReferred;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(StringUtils.rightPad(terminalId, 19, ' '));
		sb.append(StringUtils.leftPad(numberApproved, 6, '0'));
		sb.append(StringUtils.leftPad(amountApproved, 12, '0'));
		sb.append(StringUtils.leftPad(numberDeclined, 6, '0'));
		sb.append(StringUtils.leftPad(amountDeclined, 12, '0'));
		sb.append(StringUtils.leftPad(numberReferred, 6, '0'));
		sb.append(StringUtils.leftPad(amountReferred, 12, '0'));
		sb.append(StringUtils.repeat(" ", 5));
		return sb.toString();
	}

	public static TerminalTrailerResponse fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("TT")) {
			return null;
		}
		TerminalTrailerResponse tt = new TerminalTrailerResponse();
		tt.setTerminalId(s.substring(2, 21));
		tt.setNumberApproved(s.substring(21, 27));
		tt.setAmountApproved(s.substring(27, 39));
		tt.setNumberDeclined(s.substring(39, 45));
		tt.setAmountDeclined(s.substring(45, 57));
		tt.setNumberReferred(s.substring(57, 63));
		tt.setAmountReferred(s.substring(63, 75));
		return tt;
	}
	
	
}
