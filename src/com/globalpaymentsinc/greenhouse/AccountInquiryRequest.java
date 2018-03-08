package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * ACCOUNT INQUIRY REQUEST RECORD, TYPE DI
 * 
 * @author nickfeng
 *
 */
public class AccountInquiryRequest {

	/**
	 * Record Type, 1-2, AN 2, Always DI.
	 */
	private String recordType = "DI";
	/**
	 * Processing Code, 3-8, AN 6, Code identifying the action to be taken with the
	 * transaction. Valid value is: 393000.
	 */
	private String processingCode;

	/**
	 * Merchant ID, 9-23, AN 15, Discover/MasterCard/Visa-assigned merchant number.
	 * <ul>
	 * <li>For Discover: Right justified, zero filled.</li>
	 * <li>For Visa: Left justified, space filled.</li>
	 * <li>For MasterCard: Right justified, zero filled.</li>
	 * </ul>
	 */
	private String merchantId;
	/**
	 * Routing Indicator, 24-25, AN 2, 2-character code indicating where the file
	 * will be transmitted for processing. Valid values are:
	 * <ul>
	 * <li>DI - Discover</li>
	 * <li>MC - MasterCard</li>
	 * <li>VI - Visa</li>
	 * </ul>
	 */
	private String routingIndicator;
	/**
	 * Card Account Number, 26-44, AN 19, Card number for this transaction. Left
	 * justified, space filled.
	 */
	private String cardAccountNumber;
	/**
	 * Expiration Date, 45-48, AN 4, Expiration Date from the card in YYMM format.
	 */
	private String expirationDate;
	/**
	 * Sub-merchant Name, 49-68, AN 20, Name of the merchant when a Third Party
	 * Agent submits inquiries on behalf of the merchant.
	 */
	private String subMerchantName;

	/* Filler, 69-80, AN 12, Space Filled. */
	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getProcessingCode() {
		return processingCode;
	}

	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getRoutingIndicator() {
		return routingIndicator;
	}

	public void setRoutingIndicator(String routingIndicator) {
		this.routingIndicator = routingIndicator;
	}

	public String getCardAccountNumber() {
		return cardAccountNumber;
	}

	public void setCardAccountNumber(String cardAccountNumber) {
		this.cardAccountNumber = cardAccountNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getSubMerchantName() {
		return subMerchantName;
	}

	public void setSubMerchantName(String subMerchantName) {
		this.subMerchantName = subMerchantName;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(processingCode);
		if ("VI".equals(routingIndicator)) {
			sb.append(StringUtils.rightPad(merchantId, 15, ' '));
		} else {
			sb.append(StringUtils.leftPad(merchantId, 15, '0'));
		}
		sb.append(routingIndicator);
		sb.append(StringUtils.rightPad(cardAccountNumber, 19, ' '));
		sb.append(expirationDate);
		sb.append(StringUtils.rightPad(subMerchantName, 20, ' '));
		sb.append(StringUtils.repeat(" ", 12));
		return sb.toString();
	}

	public static AccountInquiryRequest fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("DI")) {
			return null;
		}
		AccountInquiryRequest di = new AccountInquiryRequest();
		di.setProcessingCode(s.substring(2, 8));
		di.setMerchantId(s.substring(8, 23));
		di.setRoutingIndicator(s.substring(23, 25));
		di.setCardAccountNumber(s.substring(25, 44));
		di.setExpirationDate(s.substring(44, 48));
		di.setSubMerchantName(s.substring(48, 68));
		return di;
	}

}
