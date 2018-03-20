package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * PURCHASE CARD LEVEL III ADDITIONAL DATA REQUEST RECORD 1, TYPE P1
 * 
 * @author nickfeng
 *
 */
public class Level3P1 {

	/**
	 * Record Type, 1-2, AN 2, Always P1.
	 */
	private String recordType = "P1";

	/**
	 * Freight Amount, 3-14, N 12, Freight Amount on this transaction. 2 decimal
	 * places assumed, right justified, zero filled. Amount cannot exceed 99,999.99.
	 */
	private String freightAmount;

	/**
	 * Duty Amount, 15-26, N 12, Duty Amount on this transaction. 2 decimal places
	 * assumed, right justified, zero filled. Amount cannot exceed 99,999.99.
	 */
	private String dutyAmount;

	/**
	 * Discount Amount, 27-38, N 12, Discount Amount on this transaction. 2 decimal
	 * places assumed, right justified, zero filled. Amount cannot exceed 99,999.99.
	 */
	private String discountAmount;

	/**
	 * Destination Postal / ZIP Code, 39-48, AN 10, The postal / ZIP code for the
	 * location to which goods are being shipped. Space - Not applicable
	 */
	private String destinationZipCode;

	/**
	 * Destination State / Province Code, 49-51, AN 3, The state / province code for
	 * the location to which goods are being shipped. Space - Not applicable
	 */
	private String destinationStateCode;

	/**
	 * Destination Country Code, 52-54, AN 3, The Country Code for the location to
	 * which goods are being shipped. Space - Not applicable
	 */
	private String destinationCountryCode;

	/**
	 * Ship From Postal / ZIP Code, 55-64, AN 10, The postal / ZIP code from which
	 * goods are being shipped. Space - Not applicable
	 */
	private String shipFromZipCode;

	/**
	 * Order Date, 65-70, N 6, The order date in YYMMDD format.
	 */
	private String orderDate;

	/**
	 * Summary Commodity Code, 71-74, AN 4, For Visa: The national coding standard
	 * for the description of goods. Space - Not applicable
	 */
	private String summaryCommodityCode;

	/* Reserved, 75-80, AN 6, Space Filled. */

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getFreightAmount() {
		return freightAmount;
	}

	public void setFreightAmount(String freightAmount) {
		this.freightAmount = freightAmount;
	}

	public String getDutyAmount() {
		return dutyAmount;
	}

	public void setDutyAmount(String dutyAmount) {
		this.dutyAmount = dutyAmount;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getDestinationZipCode() {
		return destinationZipCode;
	}

	public void setDestinationZipCode(String destinationZipCode) {
		this.destinationZipCode = destinationZipCode;
	}

	public String getDestinationStateCode() {
		return destinationStateCode;
	}

	public void setDestinationStateCode(String destinationStateCode) {
		this.destinationStateCode = destinationStateCode;
	}

	public String getDestinationCountryCode() {
		return destinationCountryCode;
	}

	public void setDestinationCountryCode(String destinationCountryCode) {
		this.destinationCountryCode = destinationCountryCode;
	}

	public String getShipFromZipCode() {
		return shipFromZipCode;
	}

	public void setShipFromZipCode(String shipFromZipCode) {
		this.shipFromZipCode = shipFromZipCode;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getSummaryCommodityCode() {
		return summaryCommodityCode;
	}

	public void setSummaryCommodityCode(String summaryCommodityCode) {
		this.summaryCommodityCode = summaryCommodityCode;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(StringUtils.leftPad(freightAmount, 12, '0'));
		sb.append(StringUtils.leftPad(dutyAmount, 12, '0'));
		sb.append(StringUtils.leftPad(discountAmount, 12, '0'));
		sb.append(destinationZipCode);
		sb.append(destinationStateCode);
		sb.append(destinationCountryCode);
		sb.append(shipFromZipCode);
		sb.append(orderDate);
		sb.append(summaryCommodityCode);
		sb.append(StringUtils.repeat(" ", 6));
		return sb.toString();
	}

	public static Level3P1 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("P1")) {
			return null;
		}
		Level3P1 p1 = new Level3P1();
		p1.setFreightAmount(s.substring(2, 14));
		p1.setDutyAmount(s.substring(14, 26));
		p1.setDiscountAmount(s.substring(26, 38));
		p1.setDestinationZipCode(s.substring(38, 48));
		p1.setDestinationStateCode(s.substring(48, 51));
		p1.setDestinationCountryCode(s.substring(51, 54));
		p1.setShipFromZipCode(s.substring(54, 64));
		p1.setOrderDate(s.substring(64, 70));
		p1.setSummaryCommodityCode(s.substring(70, 74));
		return p1;
	}

}
