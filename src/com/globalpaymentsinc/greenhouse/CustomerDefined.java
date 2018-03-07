package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * <ul>
 * <li>CUSTOMER-DEFINED REQUEST RECORD, TYPE CD</li>
 * <li>CUSTOMER-DEFINED RESPONSE RECORD, TYPE CD</li>
 * </ul>
 * 
 * @author nickfeng
 *
 */
public class CustomerDefined {

	/**
	 * Record Type, 1-2, AN 2, Always CD.
	 */
	private String recordType = "CD";
	/**
	 * Customer Defined Data, 3-80, AN 78, Customer Defined free-form text data.
	 * Echoed in the response file. Left justified, space filled.
	 */
	private String customerDefinedData;

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getCustomerDefinedData() {
		return customerDefinedData;
	}

	public void setCustomerDefinedData(String customerDefinedData) {
		this.customerDefinedData = customerDefinedData;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(StringUtils.rightPad(customerDefinedData, 78, ' '));
		return sb.toString();
	}

	public static CustomerDefined fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("CD  ")) {
			return null;
		}
		CustomerDefined cd = new CustomerDefined();
		cd.setCustomerDefinedData(s.substring(2));
		return cd;
	}
}
