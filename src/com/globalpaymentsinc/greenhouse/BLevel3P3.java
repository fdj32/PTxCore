package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * PURCHASE CARD LEVEL III ADDITIONAL DATA REQUEST RECORD 3, TYPE P3
 * 
 * @author nickfeng
 *
 */
public class BLevel3P3 {
	/**
	 * Record Type, 1-2, AN 2, Always P3.
	 */
	private String recordType = "P3";

	/**
	 * Merchant VAT Number, 3-22, AN 20, The merchant’s VAT registration number.
	 * Space - Not applicable
	 */
	private String merchantVatNumber;

	/**
	 * Customer VAT Number, 23-42, AN 20, The customer’s VAT registration number.
	 * For Visa: The value is limited to 13 characters. Space - Not applicable
	 */
	private String customerVatNumber;

	/**
	 * Commodity Code, 43-57, AN 15, The identifier that best categorizes the items
	 * being purchased in a standard commodity group. Space - Not applicable
	 */
	private String commodityCode;

	/* Filler, 58-71, AN 14, Space Filled. */

	/**
	 * Total Addenda, 72-74, N 3, Number of line items:
	 * <ul>
	 * <li>Visa: 000 – 999</li>
	 * <li>MasterCard: 000 – 998</li>
	 * <li>Discover: 000 – 099</li>
	 * <li>American Express: 000 – 004</li>
	 * </ul>
	 * The Total Addenda reflects the number of either the Line Item Detail Request
	 * Record 1 (L1) or Temporary Services Detail Request Record 1 (T1) addenda.
	 */
	private String totalAddenda;

	/* Filler, 75-94, AN 20, Space Filled. */

	/**
	 * Record Sequence Number, 95-100, N 6, Previous Record Sequence Number plus 1.
	 */
	private String recordSequenceNumber;

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getMerchantVatNumber() {
		return merchantVatNumber;
	}

	public void setMerchantVatNumber(String merchantVatNumber) {
		this.merchantVatNumber = merchantVatNumber;
	}

	public String getCustomerVatNumber() {
		return customerVatNumber;
	}

	public void setCustomerVatNumber(String customerVatNumber) {
		this.customerVatNumber = customerVatNumber;
	}

	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getTotalAddenda() {
		return totalAddenda;
	}

	public void setTotalAddenda(String totalAddenda) {
		this.totalAddenda = totalAddenda;
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
		sb.append(merchantVatNumber);
		sb.append(customerVatNumber);
		sb.append(commodityCode);
		sb.append(StringUtils.repeat(" ", 14));
		sb.append(totalAddenda);
		sb.append(StringUtils.repeat(" ", 20));
		sb.append(StringUtils.leftPad(recordSequenceNumber, 6, '0'));
		return sb.toString();
	}

	public static BLevel3P3 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 100 || !s.startsWith("P3")) {
			return null;
		}
		BLevel3P3 p3 = new BLevel3P3();
		p3.setMerchantVatNumber(s.substring(2, 22));
		p3.setCustomerVatNumber(s.substring(22, 42));
		p3.setCommodityCode(s.substring(42, 57));
		p3.setTotalAddenda(s.substring(71, 74));
		p3.setRecordSequenceNumber(s.substring(94, 100));
		return p3;
	}

}
