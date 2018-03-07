package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * ACCOUNT INQUIRY RESPONSE RECORD, TYPE DI
 * 
 * @author nickfeng
 *
 */
public class AccountInquiryResponse {

	/**
	 * Record Type, 1-2, AN 2, Always DI.
	 */
	private String recordType = "DI";
	/**
	 * Processing Code, 3-8, AN 6, Code identifying the action to be taken with the
	 * transaction. Echoed from the request record.
	 */
	private String processingCode;

	/**
	 * Merchant ID, 9-23, AN 15, MasterCard/Visa/Discover-assigned merchant number.
	 * Echoed from the request record.
	 */
	private String merchantId;
	/**
	 * Routing Indicator, 24-25, AN 2, Echoed from the request record.
	 */
	private String routingIndicator;
	/**
	 * Old Account Number, 26-44, AN 19, Card number for this transaction. Left
	 * justified, space filled.
	 */
	private String oldAccountNumber;
	/**
	 * Old Expiration Date, 45-48, AN 4, Expiration Date from the card in YYMM
	 * format.
	 */
	private String oldExpirationDate;
	/**
	 * New Account Number, 49-67, AN 19, The cardholder’s new account number.
	 */
	private String newAccountNumber;
	/**
	 * New Expiration Date, 68-71, AN 4, The expiration date of the new card in YYMM
	 * format.
	 */
	private String newExpirationDate;
	/**
	 * Visa Service Identifier/Discover Reason Code, 72-72, AN 1, For Visa:
	 * Required. Code that identifies the Visa service provided. Valid values are:
	 * <ul>
	 * <li>A - Account number change message</li>
	 * <li>C - Closed account advice</li>
	 * <li>E - Expiration date change message</li>
	 * <li>N - Non-participating BIN</li>
	 * <li>P - Participating BIN, no match</li>
	 * <li>Q - Contact cardholder advice</li>
	 * <li>V - Match made, account number and expiration date unchanged</li>
	 * </ul>
	 * For Discover: Contains the reason for the change. Valid values are:
	 * <ul>
	 * <li>A - Account Number Change</li>
	 * <li>C - Closed Account</li>
	 * <li>E - Expiration Date Update</li>
	 * <li>N - No Update</li>
	 * <li>O - Override</li>
	 * <li>Q - Contact Cardholder Advice</li>
	 * </ul>
	 */
	private char reasonCode;
	/**
	 * Previously Sent Flag, 73-73, AN 1, For Discover and Visa: Indicates whether
	 * the sameaccount information was previously requested. Valid values are:
	 * <ul>
	 * <li>Y - Yes</li>
	 * <li>N - No</li>
	 * </ul>
	 */
	private char previouslySentFlag;

	/**
	 * MasterCard Reason Identifier/Discover Error Code, 74-79, AN 6, For
	 * MasterCard: Indicates the result of the update request. Valid values are:
	 * <ul>
	 * <li>UPDATE - Match made, update data provided</li>
	 * <li>CONTAC - Match made, account closed</li>
	 * <li>EXPIRY - Match made, expiration date changed</li>
	 * <li>VALID - No updates were found, but the account is valid</li>
	 * <li>UNKNWN - The account number could not be found in the ABU database</li>
	 * </ul>
	 * If MasterCard detects an error in the input, this field contains the error
	 * code value. Valid values are:
	 * <ul>
	 * <li>000101 - Non-Numeric Account Number</li>
	 * <li>000103 - Invalid Expiration Date</li>
	 * <li>000104 - Merchant Not Registered</li>
	 * <li>000122 - Unregistered Sub-merchant</li>
	 * </ul>
	 * For Discover: Indicates the two-position error code.Left justified, space
	 * filled. Valid values are:
	 * <ul>
	 * <li>09 - Batch Acquirer ID not recognized or enrolled</li>
	 * <li>10 - Batch Merchant not recognized or enrolled</li>
	 * <li>11 - Batch Header missing</li>
	 * <li>12 - Batch Trailer count mismatch</li>
	 * <li>13 - Undefined error</li>
	 * <li>14 - File Trailer count mismatch</li>
	 * <li>15 - Card Account Number not valid</li>
	 * <li>16 - Expiration Date not numeric</li>
	 * <li>17 - Reason Code is not recognized</li>
	 * <li>18 - Reason Code is not valid for the record</li>
	 * <li>19 - Account Change Date is not numeric</li>
	 * <li>20 - Duplicate Record within submitted file</li>
	 * <li>21 - Record conflict within submitted file</li>
	 * <li>22 - Data in records is insufficient</li>
	 * <li>23 - Account Change Date is future dated</li>
	 * <li>24 - Record conflicts with database record</li>
	 * <li>25 - Duplicate Record within database</li>
	 * <li>26 - No original Record exists in database</li>
	 * <li>27 - Undefined error</li>
	 * </ul>
	 */
	private String resultCode;

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

	public String getOldAccountNumber() {
		return oldAccountNumber;
	}

	public void setOldAccountNumber(String oldAccountNumber) {
		this.oldAccountNumber = oldAccountNumber;
	}

	public String getOldExpirationDate() {
		return oldExpirationDate;
	}

	public void setOldExpirationDate(String oldExpirationDate) {
		this.oldExpirationDate = oldExpirationDate;
	}

	public String getNewAccountNumber() {
		return newAccountNumber;
	}

	public void setNewAccountNumber(String newAccountNumber) {
		this.newAccountNumber = newAccountNumber;
	}

	public String getNewExpirationDate() {
		return newExpirationDate;
	}

	public void setNewExpirationDate(String newExpirationDate) {
		this.newExpirationDate = newExpirationDate;
	}

	public char getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(char reasonCode) {
		this.reasonCode = reasonCode;
	}

	public char getPreviouslySentFlag() {
		return previouslySentFlag;
	}

	public void setPreviouslySentFlag(char previouslySentFlag) {
		this.previouslySentFlag = previouslySentFlag;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(processingCode);
		sb.append(merchantId);
		sb.append(routingIndicator);
		sb.append(StringUtils.rightPad(oldAccountNumber, 19, ' '));
		sb.append(oldExpirationDate);
		sb.append(StringUtils.rightPad(newAccountNumber, 19, ' '));
		sb.append(newExpirationDate);
		sb.append(reasonCode);
		sb.append(previouslySentFlag);
		sb.append(StringUtils.rightPad(resultCode, 6, ' '));
		sb.append(' ');
		return sb.toString();
	}

	public static AccountInquiryResponse fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("DI  ")) {
			return null;
		}
		AccountInquiryResponse di = new AccountInquiryResponse();
		di.setProcessingCode(s.substring(2, 8));
		di.setMerchantId(s.substring(8, 23));
		di.setRoutingIndicator(s.substring(23, 25));
		di.setOldAccountNumber(s.substring(25, 44));
		di.setOldExpirationDate(s.substring(44, 48));
		di.setNewAccountNumber(s.substring(48, 67));
		di.setNewExpirationDate(s.substring(67, 71));
		di.setReasonCode(s.charAt(71));
		di.setPreviouslySentFlag(s.charAt(72));
		di.setResultCode(s.substring(73, 79));
		return di;
	}

}
