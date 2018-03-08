package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * TERMINAL HEADER RECORD, TYPE TH
 * 
 * @author nickfeng
 *
 */
public class TerminalHeader {

	/**
	 * Record Type, 1-2, AN 2, Always TH.
	 */
	private String recordType = "TH";

	/**
	 * Terminal ID, 3-21, AN 19, The assigned Terminal ID set up on Global Payments'
	 * Customer Master File. Left justified, space filled.
	 */
	private String terminalId;

	/**
	 * Vendor ID , 22-31, AN 10, Identifies the vendor supplying the Big Batch
	 * software. Left justified, space filled.
	 */
	private String vendorId;

	/**
	 * Application ID, 32-46, AN 15, Identifies the application and its version.
	 * Left justified, space filled.
	 */
	private String applicationId;

	/**
	 * Customer Defined, 47-73, AN 27, Customer defined data echoed in the response.
	 * Field is space filled if not used. Not used for Account Inquiry files.
	 */
	private String customerDefined;
	/* Filler, 74-80, AN 7, Space filled. */

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

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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
		sb.append(StringUtils.rightPad(terminalId, 19, ' '));
		sb.append(StringUtils.rightPad(vendorId, 10, ' '));
		sb.append(StringUtils.rightPad(applicationId, 15, ' '));
		sb.append(StringUtils.rightPad(customerDefined, 27, ' '));
		sb.append(StringUtils.repeat(" ", 7));
		return sb.toString();
	}

	public static TerminalHeader fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("TH")) {
			return null;
		}
		TerminalHeader th = new TerminalHeader();
		th.setTerminalId(s.substring(2, 21));
		th.setVendorId(s.substring(21, 31));
		th.setApplicationId(s.substring(31, 46));
		th.setCustomerDefined(s.substring(46, 73));
		return th;
	}

}
