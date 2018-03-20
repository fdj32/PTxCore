package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * PURCHASE CARD LEVEL III LINE ITEM DETAIL REQUEST RECORD 3, TYPE L3
 * 
 * @author nickfeng
 *
 */
public class BLevel3L3 {

	/**
	 * Record Type, 1-2, AN 2, Always L3.
	 */
	private String recordType = "L3";

	/**
	 * Tax Rate 1, 3-7, N 5, Tax rate applied to the item. Right justified, zero
	 * filled. For example, for Visa: VAT Rate.
	 */
	private String taxRate1;

	/**
	 * Tax Amount 1, 8-19, N 12, The amount of tax on the item. 2 decimal places
	 * assumed, right justified, zero filled. Amount cannot exceed 99,999.99. For
	 * example, for Visa: VAT Amount.
	 */
	private String taxAmount1;

	/**
	 * Tax Rate Exponent 1, 20-20, N 1, The exponent defining the number of decimal
	 * places represented in the Tax Rate field.
	 * <ul>
	 * <li>For Visa and Discover: Valid value is 2.</li>
	 * <li>For MasterCard: Valid values are 0 - 5.</li>
	 * </ul>
	 */
	private char taxRateExponent1;

	/**
	 * Tax Type Identifier 1, 21-22, AN 2
	 * <ul>
	 * <li>For MasterCard: Refer to Tax Type Identifier on page A-18 for valid
	 * values.</li>
	 * <li>For Visa and Discover: Valid value is 10.</li>
	 * </ul>
	 */
	private String taxTypeIdentifier1;

	/**
	 * Tax Rate 2, 23-27, N 5, For MasterCard only. Tax rate applied to the item.
	 * Right justified, zero filled.
	 */
	private String taxRate2;

	/**
	 * Tax Amount 2, 28-39, N 12, For MasterCard only. The amount of tax on the
	 * item. 2 decimal places assumed, right justified, zero filled. Amount cannot
	 * exceed 99,999.99.
	 */
	private String taxAmount2;

	/**
	 * Tax Rate Exponent 2, 40-40, N 1, For MasterCard only. The exponent defining
	 * the number of decimal places represented in the Tax Rate field. Valid values
	 * are 0 - 5.
	 */
	private char taxRateExponent2;

	/**
	 * Tax Type Identifier 2, 41-42, AN 2, For MasterCard only. Refer to Tax Type
	 * Identifier on page A-18 for valid values.
	 */
	private String taxTypeIdentifier2;

	/**
	 * Tax Rate 3, 43-47, N 5, For MasterCard only. Tax rate applied to the item.
	 * Right justified, zero filled.
	 */
	private String taxRate3;

	/**
	 * Tax Amount 3, 48-59, N 12, For MasterCard only. The amount of tax on the
	 * item. 2 decimal places assumed, right justified, zero filled. Amount cannot
	 * exceed 99,999.99.
	 */
	private String taxAmount3;

	/**
	 * Tax Rate Exponent 3, 60-60, N 1, For MasterCard only. The exponent defining
	 * the number of decimal places represented in the Tax Rate field. Valid values
	 * are 0 - 5.
	 */
	private char taxRateExponent3;

	/**
	 * Tax Type Identifier 3, 61-62, AN 2, For MasterCard only. Refer to Tax Type
	 * Identifier on page A-18 for valid values.
	 */
	private String taxTypeIdentifier3;

	/* Filler, 63-94, AN 32, Space Filled. */

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

	public String getTaxRate1() {
		return taxRate1;
	}

	public void setTaxRate1(String taxRate1) {
		this.taxRate1 = taxRate1;
	}

	public String getTaxAmount1() {
		return taxAmount1;
	}

	public void setTaxAmount1(String taxAmount1) {
		this.taxAmount1 = taxAmount1;
	}

	public char getTaxRateExponent1() {
		return taxRateExponent1;
	}

	public void setTaxRateExponent1(char taxRateExponent1) {
		this.taxRateExponent1 = taxRateExponent1;
	}

	public String getTaxTypeIdentifier1() {
		return taxTypeIdentifier1;
	}

	public void setTaxTypeIdentifier1(String taxTypeIdentifier1) {
		this.taxTypeIdentifier1 = taxTypeIdentifier1;
	}

	public String getTaxRate2() {
		return taxRate2;
	}

	public void setTaxRate2(String taxRate2) {
		this.taxRate2 = taxRate2;
	}

	public String getTaxAmount2() {
		return taxAmount2;
	}

	public void setTaxAmount2(String taxAmount2) {
		this.taxAmount2 = taxAmount2;
	}

	public char getTaxRateExponent2() {
		return taxRateExponent2;
	}

	public void setTaxRateExponent2(char taxRateExponent2) {
		this.taxRateExponent2 = taxRateExponent2;
	}

	public String getTaxTypeIdentifier2() {
		return taxTypeIdentifier2;
	}

	public void setTaxTypeIdentifier2(String taxTypeIdentifier2) {
		this.taxTypeIdentifier2 = taxTypeIdentifier2;
	}

	public String getTaxRate3() {
		return taxRate3;
	}

	public void setTaxRate3(String taxRate3) {
		this.taxRate3 = taxRate3;
	}

	public String getTaxAmount3() {
		return taxAmount3;
	}

	public void setTaxAmount3(String taxAmount3) {
		this.taxAmount3 = taxAmount3;
	}

	public char getTaxRateExponent3() {
		return taxRateExponent3;
	}

	public void setTaxRateExponent3(char taxRateExponent3) {
		this.taxRateExponent3 = taxRateExponent3;
	}

	public String getTaxTypeIdentifier3() {
		return taxTypeIdentifier3;
	}

	public void setTaxTypeIdentifier3(String taxTypeIdentifier3) {
		this.taxTypeIdentifier3 = taxTypeIdentifier3;
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
		sb.append(StringUtils.leftPad(taxRate1, 5, '0'));
		sb.append(StringUtils.leftPad(taxAmount1, 12, '0'));
		sb.append(taxRateExponent1);
		sb.append(taxTypeIdentifier1);
		sb.append(StringUtils.leftPad(taxRate2, 5, '0'));
		sb.append(StringUtils.leftPad(taxAmount2, 12, '0'));
		sb.append(taxRateExponent2);
		sb.append(taxTypeIdentifier2);
		sb.append(StringUtils.leftPad(taxRate3, 5, '0'));
		sb.append(StringUtils.leftPad(taxAmount3, 12, '0'));
		sb.append(taxRateExponent3);
		sb.append(taxTypeIdentifier3);
		sb.append(StringUtils.repeat(" ", 32));
		sb.append(StringUtils.leftPad(recordSequenceNumber, 6, '0'));
		return sb.toString();
	}

	public static BLevel3L3 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("L3")) {
			return null;
		}
		BLevel3L3 l3 = new BLevel3L3();
		l3.setTaxRate1(s.substring(2, 7));
		l3.setTaxAmount1(s.substring(7, 19));
		l3.setTaxRateExponent1(s.charAt(19));
		l3.setTaxTypeIdentifier1(s.substring(20, 22));

		l3.setTaxRate2(s.substring(22, 27));
		l3.setTaxAmount2(s.substring(27, 39));
		l3.setTaxRateExponent1(s.charAt(39));
		l3.setTaxTypeIdentifier1(s.substring(40, 42));

		l3.setTaxRate1(s.substring(42, 47));
		l3.setTaxAmount1(s.substring(47, 59));
		l3.setTaxRateExponent1(s.charAt(59));
		l3.setTaxTypeIdentifier1(s.substring(60, 62));

		l2.setRecordSequenceNumber(s.substring(94, 100));
		return l3;
	}

}
