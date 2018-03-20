package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * PURCHASE CARD LEVEL III ADDITIONAL DATA REQUEST RECORD 2, TYPE P2
 * 
 * @author nickfeng
 *
 */
public class BLevel3P2 {
	/**
	 * Record Type, 1-2, AN 2, Always P2.
	 */
	private String recordType = "P2";

	/**
	 * Reference Number, 3-27, AN 25
	 * <ul>
	 * <li>For Visa: The Unique VAT Invoice Reference Number. The value is limited
	 * to 15 characters.</li>
	 * <li>For MasterCard: The Card Acceptor Reference Number.</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 */
	private String referenceNumber;

	/**
	 * National Tax, 28-39, N 12, For Visa only: The national tax amount. 2 decimal
	 * places assumed, right justified, zero filled. Amount cannot exceed 99,999.99.
	 */
	private String nationalTax;

	/**
	 * Other Tax, 40-51, N 12, For Visa only: The amount of any other tax included
	 * in the transaction amount. 2 decimal places assumed, right justified, zero
	 * filled. Amount cannot exceed 99,999.99.
	 */
	private String otherTax;

	/**
	 * VAT / Tax Amount (Freight / Shipping), 52-63, N 12, For Visa only: The tax
	 * amount applied to freight / shipping costs only. 2 decimal places assumed,
	 * right justified, zero filled. Amount cannot exceed 99,999.99.
	 */
	private String vatAmount;

	/**
	 * VAT / Tax Rate (Freight / Shipping), 64-67, N 4, For Visa only: The tax rate
	 * applied to freight / shipping costs only. 2 decimal places assumed, right
	 * justified, zero filled.
	 */
	private String vatRate;

	/* Filler, 68-94, AN 27, Space Filled. */

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

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getNationalTax() {
		return nationalTax;
	}

	public void setNationalTax(String nationalTax) {
		this.nationalTax = nationalTax;
	}

	public String getOtherTax() {
		return otherTax;
	}

	public void setOtherTax(String otherTax) {
		this.otherTax = otherTax;
	}

	public String getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(String vatAmount) {
		this.vatAmount = vatAmount;
	}

	public String getVatRate() {
		return vatRate;
	}

	public void setVatRate(String vatRate) {
		this.vatRate = vatRate;
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
		sb.append(referenceNumber);
		sb.append(StringUtils.leftPad(nationalTax, 12, '0'));
		sb.append(StringUtils.leftPad(otherTax, 12, '0'));
		sb.append(StringUtils.leftPad(vatAmount, 12, '0'));
		sb.append(StringUtils.leftPad(vatRate, 4, '0'));
		sb.append(StringUtils.repeat(" ", 27));
		sb.append(StringUtils.leftPad(recordSequenceNumber, 6, '0'));
		return sb.toString();
	}

	public static BLevel3P2 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 100 || !s.startsWith("P2")) {
			return null;
		}
		BLevel3P2 p2 = new BLevel3P2();
		p2.setReferenceNumber(s.substring(2, 27));
		p2.setNationalTax(s.substring(27, 39));
		p2.setOtherTax(s.substring(39, 51));
		p2.setVatAmount(s.substring(51, 63));
		p2.setVatRate(s.substring(63, 67));
		p2.setRecordSequenceNumber(s.substring(94, 100));
		return p2;
	}

}
