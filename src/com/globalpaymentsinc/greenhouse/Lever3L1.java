package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * PURCHASE CARD LEVEL III LINE ITEM DETAIL REQUEST RECORD 1, TYPE L1
 * 
 * @author nickfeng
 *
 */
public class Lever3L1 {

	/**
	 * Record Type, 1-2, AN 2, Always L1.
	 */
	private String recordType = "L1";

	/**
	 * Item Description, 3-37, AN 35, Individual item description. Left justified,
	 * space filled. For Visa: The value is limited to 26 characters. Space - Not
	 * applicable
	 */
	private String itemDescription;

	/**
	 * Item Quantity, 38-49, N 12, Quantity purchased of line item. For American
	 * Express: The quantity cannot exceed 999. Right justified, zero filled.
	 */
	private String itemQuantity;

	/**
	 * Item Quantity Exponent, 50-50, N 1, The exponent defining the number of
	 * decimal places represented in the Item Quantity.
	 * <ul>
	 * <li>For Visa: Valid value is 4.</li>
	 * <li>For MasterCard: Valid values are 0 – 5.</li>
	 * <li>For American Express and Discover: Valid value is 0.</li>
	 * </ul>
	 */
	private char itemQuantityExponent;

	/**
	 * Unit of Measure, 51-62, AN 12, Unit of measure of the line item. Space - Not
	 * applicable
	 */
	private String unitOfMeasure;

	/**
	 * Extended Item Amount, 63-74, N 12
	 * <ul>
	 * <li>For Visa: Unit price multiplied by item quantity minus item
	 * discount.</li>
	 * <li>For MasterCard: Unit price multiplied by item quantity. Amount cannot
	 * exceed 99,999.99.</li>
	 * <li>For American Express and Discover: Zero fill. 2 decimal places assumed,
	 * right justified, zero filled.</li>
	 * </ul>
	 */
	private String extendedItemAmount;

	/* Reserved, 75-80, AN 6, Space Filled. */

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public char getItemQuantityExponent() {
		return itemQuantityExponent;
	}

	public void setItemQuantityExponent(char itemQuantityExponent) {
		this.itemQuantityExponent = itemQuantityExponent;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getExtendedItemAmount() {
		return extendedItemAmount;
	}

	public void setExtendedItemAmount(String extendedItemAmount) {
		this.extendedItemAmount = extendedItemAmount;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(itemDescription);
		sb.append(StringUtils.leftPad(itemQuantity, 12, '0'));
		sb.append(itemQuantityExponent);
		sb.append(unitOfMeasure);
		sb.append(StringUtils.leftPad(extendedItemAmount, 12, '0'));
		sb.append(StringUtils.repeat(" ", 6));
		return sb.toString();
	}

	public static Lever3L1 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("L1")) {
			return null;
		}
		Lever3L1 l1 = new Lever3L1();
		l1.setItemDescription(s.substring(2, 37));
		l1.setItemQuantity(s.substring(37, 49));
		l1.setItemQuantityExponent(s.charAt(49));
		l1.setUnitOfMeasure(s.substring(50, 62));
		l1.setExtendedItemAmount(s.substring(62, 74));
		return l1;
	}

}
