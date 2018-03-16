package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * ELECTRONIC INTERCHANGE ADDENDUM FORCE REQUEST RECORD, TYPE IA
 * 
 * @author nickfeng
 *
 */
public class InterchangeAddendumForceRequest {

	/**
	 * Record Type, 1-2, AN 2, Always IA.
	 */
	private String recordType = "IA";

	/* Filler, 3-32, AN 30, Space Filled. */

	/**
	 * Authorization Characteristics Indicator (ACI), 33-33, ACI value from original
	 * authorization response. Must be an ACI response value.
	 */
	private char aci;

	/**
	 * AVS Result, 34-34, An 1, Card association AVS result code. Refer to AVS
	 * Result Codes on page A-5 for a list of valid values.
	 */
	private char avsResult;

	/* Filler, 35-36, AN 2, Space Filled. */

	/**
	 * Transaction ID/Banknet Data, 37-51, AN 15, Assigned by Visa, MasterCard,
	 * American Express and Discover in the original authorization response.
	 * Required for interchange qualification and compliance mandates. The field is
	 * not applicable to settlement transactions for credits (returns).
	 * <ul>
	 * <li>For Visa: The 15-digit Transaction Identifier.</li>
	 * <li>For MasterCard: 13-character Trace ID consists of the Banknet Reference
	 * Number (positions 1-9) and the Banknet Date (positions 10-13) followed by two
	 * spaces.</li>
	 * <li>For American Express: The 15-digit Transaction Identifier.</li>
	 * <li>For Discover: The 15-digit Network Reference ID.</li>
	 * <li>For other card types: Not used unless a value was returned in the
	 * original authorization response.</li>
	 * </ul>
	 */
	private String transactionId;

	/* Filler, 52-52, AN 1, Space Filled. */

	/**
	 * Validation Code, 53-56, AN 4,
	 * <ul>
	 * <li>For Visa: Assigned by Visa in the original authorization response. Must
	 * be present in the settlement message. If an authorization request is
	 * declined, Visa will assign the first 2 characters of this code as the
	 * downgrade reason value of NA (not approved).</li>
	 * <li>For MasterCard: Global Payments will use this field to store the
	 * downgrade reason assigned by MasterCard in an authorization response on a
	 * transaction that failed magnetic stripe edits. Must be present in the
	 * settlement message to meet MasterCard Interchange Compliance requirements.
	 * Valid values for downgrade reasons are: 1st Character: Error Indicator
	 * 
	 * <ul>
	 * <li>A - Track 1 or Track 2 not present in the message</li>
	 * <li>B - Track 1 and Track 2 present in the message</li>
	 * <li>C - Primary account number not equal in track data</li>
	 * <li>D - Expiration date not equal in track data</li>
	 * <li>E - Service code invalid in track data</li>
	 * <li>F - Field separators invalid in track data</li>
	 * <li>G - A field within the track data has an invalid length</li>
	 * <li>H - Terminal PAN entry mode indicates swiped and the transaction category
	 * code is T for telephone order</li>
	 * <li>I - POS cardholder presence indicates cardholder is not present</li>
	 * <li>J - POS card presence indicates card is not present 2nd Character: Status
	 * Indicator</li>
	 * <li>Y - Authorization system replaced POS entry mode 90 with a value of
	 * 02.</li>
	 * <li>space- not applicable.</li>
	 * </ul>
	 * 
	 * </li>
	 * 
	 * <li>For Discover: This field is used to indicate the magnetic stripe
	 * condition of the authorization response and the Processing Code in the
	 * request. Must be present in the settlement request. 1st Character: Track 1
	 * Data Indicator This position indicates the condition of track 1 Data in the
	 * request. Valid values are:
	 * <ul>
	 * <li>0 - No Track 1 data present</li>
	 * <li>1 - Track 1 data present with CVV not provided</li>
	 * <li>2 - Track 1 data present with non-zero and non-blank CVV</li>
	 * <li>3 - Track 1 data present with CVV set to all zeroes</li>
	 * <li>4 - Track 1 data present with CVV set to all blanks</li>
	 * <li>5 - Track 1 data present but CVV location not provided by issuer</li>
	 * </ul>
	 * 2nd Character: Track 2 Data Indicator This position indicates the condition
	 * of Track 2 Data in Request. Valid values are:
	 * <ul>
	 * <li>0 - No Track 2 data present</li>
	 * <li>1 - Track 2 data present with CVV not provided</li>
	 * <li>2 - Track 2 data present with non-zero and non-blank CVV</li>
	 * <li>3 - Track 2 data present with CVV set to all zeroes</li>
	 * <li>4 - Track 2 data present with CVV set to all blanks</li>
	 * <li>5 - Track 2 data present but CVV location not provided by issuer</li>
	 * </ul>
	 * 3rd and 4th Characters: The first two positions of the Processing Code value
	 * sent to Discover in the original authorization request.</li>
	 * <li>For other card types: Not used.</li>
	 * </ul>
	 */
	private String validationCode;

	/**
	 * Authorization Source Code, 57-57, AN 1, Assigned by Visa in the original
	 * authorization. Indicates the source of the authorization. Must be present in
	 * the settlement message.Valid values are:
	 * <ul>
	 * <li>space - Default value</li>
	 * <li>0 - Advice of Exception file change</li>
	 * <li>B - Response provided by STIP: Transaction met</li>
	 * </ul>
	 * Visa Transaction Advisor Service criteria.
	 */
	private char authorizationSourceCode;

	/**
	 * Response Code, 58-59, AN 2, A field assigned by issuer at the time of
	 * authorization to indicate the issuer's response to the authorization request.
	 */
	private String responseCode;

	/**
	 * MOTO/EC Indicator, 60-61, AN 2, The Mail/Telephone or Electronic Commerce
	 * Indicator (MOTO/EC) code indicates the type of Mail/Telephone or Electronic
	 * Commerce transaction being processed. Valid values for Mail/Telephone
	 * transactions are:
	 * <ul>
	 * <li>01 - Single transaction</li>
	 * <li>02 - Recurring transaction</li>
	 * <li>03 - Installment billing</li>
	 * <li>04 - Unknown classification</li>
	 * </ul>
	 * Valid values for Electronic Commerce transactions are:
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

	/**
	 * Card Verification Result Code, 62-62, AN 1, A code that reflects the outcome
	 * of the Card Verification check. Valid values are:
	 * <ul>
	 * <li>M - Match</li>
	 * <li>N - No Match</li>
	 * <li>P - Not processed</li>
	 * <li>S - CVV2/CID should be on the card, but the merchant has indicated that
	 * CVV2/CID is not present (Visa and Discover only)</li>
	 * <li>U - Issuer is not certified</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 */
	private char cardVerificationResultCode;

	/**
	 * Merchant Advice Code, 63-64, AN 2, The merchant advice code determines the
	 * recycling of authorizations in the response message.The following codes have
	 * been defined by MasterCard:
	 * <ul>
	 * <li>01 - New Account Information – Obtain new account information, then
	 * process again</li>
	 * <li>02 - Try Again Later – Recycle after three days</li>
	 * <li>03 - Do Not Try Again – Obtain a new form of payment</li>
	 * <li>21 - Recurring payment cancellation service.</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 */
	private String merchantAdviceCode;

	/* Filler, 65-77, AN 13, Space Filled. */

	/**
	 * Market Specific Data Identifier, 78-78, AN 1, For Visa only: Valid values
	 * are:
	 * <ul>
	 * <li>B - Bill Payment</li>
	 * <li>E - Electronic Commerce Transaction Aggregation</li>
	 * <li>J - B2B Invoice Payments</li>
	 * <li>N - Failed Market Specific Data edits</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 */
	private char marketSpecificDataIdentifier;

	/**
	 * Product Identification, 79-80, AN 2, Value assigned by Visa in the original
	 * authorization response. Refer to Product Identification Values on page A-17
	 * for valid values.
	 */
	private String productIdentification;

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public char getAci() {
		return aci;
	}

	public void setAci(char aci) {
		this.aci = aci;
	}

	public char getAvsResult() {
		return avsResult;
	}

	public void setAvsResult(char avsResult) {
		this.avsResult = avsResult;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}

	public char getAuthorizationSourceCode() {
		return authorizationSourceCode;
	}

	public void setAuthorizationSourceCode(char authorizationSourceCode) {
		this.authorizationSourceCode = authorizationSourceCode;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getMotoIndicator() {
		return motoIndicator;
	}

	public void setMotoIndicator(String motoIndicator) {
		this.motoIndicator = motoIndicator;
	}

	public char getCardVerificationResultCode() {
		return cardVerificationResultCode;
	}

	public void setCardVerificationResultCode(char cardVerificationResultCode) {
		this.cardVerificationResultCode = cardVerificationResultCode;
	}

	public String getMerchantAdviceCode() {
		return merchantAdviceCode;
	}

	public void setMerchantAdviceCode(String merchantAdviceCode) {
		this.merchantAdviceCode = merchantAdviceCode;
	}

	public char getMarketSpecificDataIdentifier() {
		return marketSpecificDataIdentifier;
	}

	public void setMarketSpecificDataIdentifier(char marketSpecificDataIdentifier) {
		this.marketSpecificDataIdentifier = marketSpecificDataIdentifier;
	}

	public String getProductIdentification() {
		return productIdentification;
	}

	public void setProductIdentification(String productIdentification) {
		this.productIdentification = productIdentification;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(StringUtils.repeat(" ", 30));
		sb.append(aci);
		sb.append(avsResult);
		sb.append(StringUtils.repeat(" ", 2));
		sb.append(transactionId);
		sb.append(' ');
		sb.append(validationCode);
		sb.append(authorizationSourceCode);
		sb.append(responseCode);
		sb.append(motoIndicator);
		sb.append(cardVerificationResultCode);
		sb.append(merchantAdviceCode);
		sb.append(StringUtils.repeat(" ", 13));
		sb.append(marketSpecificDataIdentifier);
		sb.append(productIdentification);
		return sb.toString();
	}

	public static InterchangeAddendumForceRequest fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("IA")) {
			return null;
		}
		InterchangeAddendumForceRequest ia = new InterchangeAddendumForceRequest();
		ia.setAci(s.charAt(32));
		ia.setAvsResult(s.charAt(33));
		ia.setTransactionId(s.substring(36, 51));
		ia.setValidationCode(s.substring(52, 56));
		ia.setAuthorizationSourceCode(s.charAt(56));
		ia.setResponseCode(s.substring(57, 59));
		ia.setMotoIndicator(s.substring(59, 61));
		ia.setCardVerificationResultCode(s.charAt(61));
		ia.setMerchantAdviceCode(s.substring(62, 64));
		ia.setMarketSpecificDataIdentifier(s.charAt(77));
		ia.setProductIdentification(s.substring(78, 80));
		return ia;
	}

}
