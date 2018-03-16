package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * ELECTRONIC INTERCHANGE ADDENDUM AUTHORIZATION, PURCHASE, OR AVS-ONLY REQUEST
 * RECORD, TYPE IA
 * 
 * @author nickfeng
 *
 */
public class InterchangeAddendumRequest {

	/**
	 * Record Type, 1-2, AN 2, Always IA.
	 */
	private String recordType = "IA";

	/**
	 * AVS (Address Verification Service) Zip Code, 3-11, AN 9, Cardholder zip code
	 * used for AVS. Can contain a 5 or 9 digit zip code or non-US postal code. Left
	 * justified, space filled.
	 */
	private String avsZipCode;

	/* Filler, 12-12, AN 1, Space Filled. */

	/**
	 * AVS Address, 13-32, AN 20, Cardholder address used for AVS. Can contain up to
	 * 20 characters of the street address. Based on current Card Association rules,
	 * first 5 numeric values before the space or a character are required. Left
	 * justified, space filled.
	 */
	private String avsAddress;

	/**
	 * Authorization Characteristics Indicator (ACI), 33-33, AN 1, For Visa: Valid
	 * value is:
	 * <ul>
	 * <li>Y - Card not present with Address Verification Service (AVS) request,
	 * keyed retail transaction or electronic commerce transaction</li>
	 * <li>R - Card not present recurring payment qualification without Address
	 * Verification Service (AVS) request</li>
	 * </ul>
	 */
	private char aci;

	/* Filler, 34-36, AN 3, Space Filled. */

	/**
	 * Transaction ID, 37-51, AN 15, Assigned by Visa in the original authorization
	 * response. Required for subsequent credential on file processing.
	 */
	private String transactionId;

	/* Filler, 52-59, AN 8, Space Filled. */

	/**
	 * CVV2/CID Presence Indicator, 60-60, AN 1, For Visa and Discover Only: A code
	 * the merchant uses to indicate the CVV2/CID Value is on the card. A value of
	 * other than 1 provides information about why the CVV2/CID Value was not
	 * included in the authorization request. Valid values are:
	 * <ul>
	 * <li>0 - CVV2/CID Value is deliberately bypassed or is not provided by the
	 * merchant.</li>
	 * <li>1 - CVV2/CID Value is present</li>
	 * <li>2 - CVV2/CID Value is on the card but is illegible</li>
	 * <li>9 - Cardholder states that the card has no CVV2/CID imprint</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 */
	private char cvv2PresenceIndicator;

	/**
	 * CVV2 Response Type, 61-61, AN 1, For Visa Only: A code the merchant puts on
	 * the authorization request to indicate the type of response to be returned.
	 * Valid values are:
	 * <ul>
	 * <li>0 - Only the authorization response code should be returned</li>
	 * <li>1 - The authorization response code and the CVV2 Result field should be
	 * returned</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 */
	private char cvv2ResponseType;

	/**
	 * Card Verification Value, 62-65, AN 4, Used to assist in fraud detection on
	 * non-PIN based transactions. Visa, MasterCard and Discover use 3 digits while
	 * American Express uses 4 digits. The field is right justified and space
	 * filled. This value cannot be stored by the merchant for future use.
	 */
	private String cardVerificationValue;

	/**
	 * MOTO/EC Indicator, 66-67, AN 2, The Mail/Telephone or Electronic Commerce
	 * Indicator (MOTO/EC) code indicates the type of Mail/Telephone or Electronic
	 * Commerce transaction being processed. Valid codes for Mail/Telephone
	 * transactions are:
	 * <ul>
	 * <li>01 - Single transaction</li>
	 * <li>02 - Recurring transaction</li>
	 * <li>03 - Installment billing</li>
	 * <li>04 - Unknown classification</li>
	 * </ul>
	 * Valid codes for Electronic Commerce transactions are:
	 * <ul>
	 * <li>05 - Secure electronic transaction</li>
	 * <li>06 - Non-Authenticated transaction - authentication attempt</li>
	 * <li>07 - Non-authenticated security transactions, such as channel encrypted
	 * or SSL transactions</li>
	 * <li>08 - Non-secure transactions</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 */
	private String motoIndicator;

	/* Filler, 68-77, AN 10, Space Filled. */

	/**
	 * Market Specific Data Identifier, 78-78, AN 1, For Visa only: Valid values
	 * are:
	 * <ul>
	 * <li>B - Bill Payment</li>
	 * <li>E - Electronic Commerce Transaction Aggregation</li>
	 * <li>J - B2B Invoice Payments</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 */
	private char marketSpecificDataIdentifier;

	/* Filler, 79-80, AN 2, Space Filled. */

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getAvsZipCode() {
		return avsZipCode;
	}

	public void setAvsZipCode(String avsZipCode) {
		this.avsZipCode = avsZipCode;
	}

	public String getAvsAddress() {
		return avsAddress;
	}

	public void setAvsAddress(String avsAddress) {
		this.avsAddress = avsAddress;
	}

	public char getAci() {
		return aci;
	}

	public void setAci(char aci) {
		this.aci = aci;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public char getCvv2PresenceIndicator() {
		return cvv2PresenceIndicator;
	}

	public void setCvv2PresenceIndicator(char cvv2PresenceIndicator) {
		this.cvv2PresenceIndicator = cvv2PresenceIndicator;
	}

	public char getCvv2ResponseType() {
		return cvv2ResponseType;
	}

	public void setCvv2ResponseType(char cvv2ResponseType) {
		this.cvv2ResponseType = cvv2ResponseType;
	}

	public String getCardVerificationValue() {
		return cardVerificationValue;
	}

	public void setCardVerificationValue(String cardVerificationValue) {
		this.cardVerificationValue = cardVerificationValue;
	}

	public String getMotoIndicator() {
		return motoIndicator;
	}

	public void setMotoIndicator(String motoIndicator) {
		this.motoIndicator = motoIndicator;
	}

	public char getMarketSpecificDataIdentifier() {
		return marketSpecificDataIdentifier;
	}

	public void setMarketSpecificDataIdentifier(char marketSpecificDataIdentifier) {
		this.marketSpecificDataIdentifier = marketSpecificDataIdentifier;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(StringUtils.rightPad(avsZipCode, 9, ' '));
		sb.append(' ');
		sb.append(StringUtils.rightPad(avsAddress, 20, ' '));
		sb.append(aci);
		sb.append(StringUtils.repeat(" ", 3));
		sb.append(transactionId);
		sb.append(StringUtils.repeat(" ", 8));
		sb.append(cvv2PresenceIndicator);
		sb.append(cvv2ResponseType);
		sb.append(StringUtils.leftPad(cardVerificationValue, 4, ' '));
		sb.append(motoIndicator);
		sb.append(StringUtils.repeat(" ", 10));
		sb.append(marketSpecificDataIdentifier);
		sb.append(StringUtils.repeat(" ", 2));
		return sb.toString();
	}

	public static InterchangeAddendumRequest fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("IA")) {
			return null;
		}
		InterchangeAddendumRequest ia = new InterchangeAddendumRequest();
		ia.setAvsZipCode(s.substring(2, 11));
		ia.setAvsAddress(s.substring(12, 32));
		ia.setAci(s.charAt(32));
		ia.setTransactionId(s.substring(36, 51));
		ia.setCvv2PresenceIndicator(s.charAt(59));
		ia.setCvv2ResponseType(s.charAt(60));
		ia.setCardVerificationValue(s.substring(61, 65));
		ia.setMotoIndicator(s.substring(65, 67));
		ia.setMarketSpecificDataIdentifier(s.charAt(77));
		return ia;
	}

}
