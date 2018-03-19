package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * PURCHASE CARD LEVEL III LINE ITEM DETAIL REQUEST RECORD 2, TYPE L2
 * 
 * @author nickfeng
 *
 */
public class Lever3L2 {

	/**
	 * Record Type, 1-2, AN 2, Always L2.
	 */
	private String recordType = "L2";

	/**
	 * Unit Price, 3-14, N 12, The item price per unit. Right justified, zero
	 * filled. Amount cannot exceed 99,999.99.
	 */
	private String unitPrice;

	/**
	 * Unit Price Exponent, 15-15, N 1, The exponent defining the number of decimal
	 * places represented in the Unit Price.
	 * <ul>
	 * <li>For Visa: Valid value is 4.</li>
	 * <li>For MasterCard: Valid values are 0 – 5.</li>
	 * <li>For American Express and Discover: Valid value is 2.</li>
	 * </ul>
	 */
	private char unitPriceExponent;

	/**
	 * Debit / Credit Indicator, 16-16, AN 1, Indicates whether the net of extended
	 * item amount, net of discount, taxes, and price is a debit or credit. For
	 * MasterCard: Valid values are:
	 * <ul>
	 * <li>D - Extended item amount is a cardholder debit.</li>
	 * <li>C - Extended item amount is a cardholder credit.</li>
	 * </ul>
	 * For other card types: Space filled.
	 */
	private char debitCreditIndicator;

	/**
	 * Product Code, 17-31, AN 15, Product code of the individual item purchased.
	 * For Visa: The value is limited to 12 characters.
	 */
	private String productCode;

	/**
	 * Item Discount, 32-43, N 12, The amount of discount applied to the item. 2
	 * decimal places assumed, right justified, zero filled. Amount cannot exceed
	 * 99,999.99.
	 */
	private String itemDiscount;

	/**
	 * Item Discount Rate, 44-48, N 5, For MasterCard and Discover: The discount
	 * rate applied to the item. For other card types: Zero filled.
	 */
	private String itemDiscountRate;

	/**
	 * Item Discount Rate Exponent, 49-49, N 1, For MasterCard: The exponent
	 * defining the number of decimal places represented in the Item Discount Rate.
	 * Valid values are 0 – 5. For Visa and Discover: Valid value is 0.
	 */
	private char itemDiscountRateExponent;

	/**
	 * Item Commodity Code, 50-64, AN 15, The commodity code for the item. For Visa:
	 * The value is limited to 12 characters.
	 */
	private String itemCommodityCode;

	/**
	 * Zero Cost to Customer Indicator, 65-65, AN 1, For MasterCard: Valid values
	 * are:
	 * <ul>
	 * <li>Y - The line item is at zero cost to the customer. The extended item
	 * amount may be equal to zero.</li>
	 * <li>N - The line item is not at zero cost to the customer. The extended item
	 * amount may not be equal to zero.</li>
	 * <li>Space - No indicator.</li>
	 * </ul>
	 * For other card types: Space filled.
	 */
	private char zeroCostToCustomerIndicator;

	/* Filler, 66-74, AN 9, Space Filled. */

	/* Reserved, 75-80, AN 6, Space Filled. */

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public char getUnitPriceExponent() {
		return unitPriceExponent;
	}

	public void setUnitPriceExponent(char unitPriceExponent) {
		this.unitPriceExponent = unitPriceExponent;
	}

	public char getDebitCreditIndicator() {
		return debitCreditIndicator;
	}

	public void setDebitCreditIndicator(char debitCreditIndicator) {
		this.debitCreditIndicator = debitCreditIndicator;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getItemDiscount() {
		return itemDiscount;
	}

	public void setItemDiscount(String itemDiscount) {
		this.itemDiscount = itemDiscount;
	}

	public String getItemDiscountRate() {
		return itemDiscountRate;
	}

	public void setItemDiscountRate(String itemDiscountRate) {
		this.itemDiscountRate = itemDiscountRate;
	}

	public char getItemDiscountRateExponent() {
		return itemDiscountRateExponent;
	}

	public void setItemDiscountRateExponent(char itemDiscountRateExponent) {
		this.itemDiscountRateExponent = itemDiscountRateExponent;
	}

	public String getItemCommodityCode() {
		return itemCommodityCode;
	}

	public void setItemCommodityCode(String itemCommodityCode) {
		this.itemCommodityCode = itemCommodityCode;
	}

	public char getZeroCostToCustomerIndicator() {
		return zeroCostToCustomerIndicator;
	}

	public void setZeroCostToCustomerIndicator(char zeroCostToCustomerIndicator) {
		this.zeroCostToCustomerIndicator = zeroCostToCustomerIndicator;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(StringUtils.leftPad(unitPrice, 12, '0'));
		sb.append(unitPriceExponent);
		sb.append(debitCreditIndicator);
		sb.append(productCode);
		sb.append(StringUtils.leftPad(itemDiscount, 12, '0'));
		sb.append(StringUtils.leftPad(itemDiscountRate, 5, '0'));
		sb.append(itemDiscountRateExponent);
		sb.append(itemCommodityCode);
		sb.append(zeroCostToCustomerIndicator);
		sb.append(StringUtils.repeat(" ", 15));
		return sb.toString();
	}

	public static Lever3L2 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("L2")) {
			return null;
		}
		Lever3L2 l2 = new Lever3L2();
		l2.setUnitPrice(s.substring(2, 14));
		l2.setUnitPriceExponent(s.charAt(14));
		l2.setDebitCreditIndicator(s.charAt(15));
		l2.setProductCode(s.substring(16, 31));
		l2.setItemDiscount(s.substring(31, 43));
		l2.setItemDiscountRate(s.substring(43, 48));
		l2.setItemDiscountRateExponent(s.charAt(48));
		l2.setItemCommodityCode(s.substring(49, 64));
		l2.setZeroCostToCustomerIndicator(s.charAt(64));
		return l2;
	}

}
