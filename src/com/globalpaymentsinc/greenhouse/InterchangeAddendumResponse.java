package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * ELECTRONIC INTERCHANGE ADDENDUM RESPONSE RECORD, TYPE IA
 * 
 * @author nickfeng
 *
 */
public class InterchangeAddendumResponse {

	/**
	 * Record Type, 1-2, AN 2, Always IA.
	 */
	private String recordType = "IA";

	/* Filler, 3-32, AN 30, Space Filled. */

	/**
	 * Authorization Characteristics Indicator (ACI), 33-33, AN 1, Authorization
	 * Characteristic Indicator (ACI). Valid values for the response messages are
	 * listed below. For Visa:
	 * <ul>
	 * <li>A - Card present</li>
	 * <li>B - Tokenized e-commerce with mobile device</li>
	 * <li>C - Card present with merchant name and location data
	 * (cardholder-activated, self-service terminal)</li>
	 * <li>E - Card present with merchant name and location data</li>
	 * <li>F - Card not present—Account Funding</li>
	 * <li>J - Card not present—Recurring Bill Payment</li>
	 * <li>K - Card present with key-entry</li>
	 * <li>M - Card not present (Address Verification Service not requested)</li>
	 * <li>N - Not a payment service transaction</li>
	 * <li>P - Card not present (Preferred Customer participation requested)</li>
	 * <li>R - Card not present (Address Verification Service not required)</li>
	 * <li>S - Electronic Commerce 3-D Secure Attempts</li>
	 * <li>T - A CPS Program was not available when authorized</li>
	 * <li>U - Card not present—3-D Secure Electronic Commerce</li>
	 * <li>V - Card not present (Address Verification Service requested)</li>
	 * <li>W - Card not present—Non-3-D Secure Electronic Commerce</li>
	 * </ul>
	 * For other card types:Not used.
	 */
	private char aci;

	/**
	 * AVS Result Code, 34-34, AN 1, Card association AVS result code. Refer to AVS
	 * Result Codes on page A-5 for a list of valid values.
	 */
	private char avsResultCode;

	/* Filler, 35-36, AN 2, Space Filled. */

	/**
	 * Transaction ID/Banknet Data, 37-51, AN 15, Assigned by Visa, MasterCard,
	 * American Express and Discover in the original authorization response.
	 * Required for interchange qualification and compliance mandates. The field is
	 * not applicable to settlement transactions for credits (returns).
	 * <ul>
	 * <li>For Visa: The 15-digit Transaction Identifier.</li>
	 * <li>For MasterCard: 13-character Trace ID consists of the Banknet Reference
	 * Number (positions 1-9) and the Banknet Date (positions 10- 13) followed by
	 * two spaces.</li>
	 * <li>For American Express: The 15-digit Transaction Identifier.</li>
	 * <li>For Discover: The 15-digit Network Reference ID.</li>
	 * <li>For other card types: Not used unless a value was returned in the
	 * original authorization response from the issuer.</li>
	 * </ul>
	 */
	private String transactionId;

	/* Filler, 52-52, AN 1, Space Filled. */

	/**
	 * Validation Code/Magnetic Stripe Compliance Indicators, 53-56, AN 4,
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
	 * <li>space - not applicable.</li>
	 * </ul>
	 * 
	 * </li>
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
	 * 
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
	 * 
	 * 3rd and 4th Characters: The first two positions of the Processing Code value
	 * sent to Discover in the original authorization request.
	 * 
	 * </li>
	 * <li>For other card types: Not used.</li>
	 * </ul>
	 */
	private String validationCode;

	/**
	 * Authorization Source Code, 57-57, AN 1, Assigned by Visa in the original
	 * authorization. Indicates the source of the authorization. Must be present in
	 * the settlement message. Valid values are:
	 * <ul>
	 * <li>space - Default value</li>
	 * <li>0 - Advice of Exception file change</li>
	 * <li>B - Response provided by STIP: Transaction met Visa Transaction Advisor
	 * Service criteria.</li>
	 * </ul>
	 */
	private char authorizationSourceCode;

	/**
	 * Issuer Response Code, 58-59, AN 2, A field assigned by issuer at the time of
	 * authorization to indicate the issuer's response to the authorization request.
	 */
	private String issuerResponseCode;

	/* Filler, 60-61, AN 2, Space Filled. */

	/**
	 * CVV2/CVC2/CID Result Code, 62-62, AN 1, A code that reflects the outcome of
	 * the CVV2/CVC2/CID check. Valid values are:
	 * <ul>
	 * <li>M - CVV2/CVC2/CID Match</li>
	 * <li>N - CVV2/CVC2/CID No Match</li>
	 * <li>P - Not processed</li>
	 * <li>S - CVV2/CID should be on the card, but the merchant has indicated that
	 * CVV2/CID is not present (Visa and Discover only)</li>
	 * <li>U - Issuer is not certified for CVV2/CVC2/CID processing Space - Not
	 * applicable</li>
	 * </ul>
	 */
	private char cvv2ResultCode;

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

	public char getAvsResultCode() {
		return avsResultCode;
	}

	public void setAvsResultCode(char avsResultCode) {
		this.avsResultCode = avsResultCode;
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

	public String getIssuerResponseCode() {
		return issuerResponseCode;
	}

	public void setIssuerResponseCode(String issuerResponseCode) {
		this.issuerResponseCode = issuerResponseCode;
	}

	public char getCvv2ResultCode() {
		return cvv2ResultCode;
	}

	public void setCvv2ResultCode(char cvv2ResultCode) {
		this.cvv2ResultCode = cvv2ResultCode;
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
		sb.append(avsResultCode);
		sb.append(StringUtils.repeat(" ", 2));
		sb.append(transactionId);
		sb.append(' ');
		sb.append(validationCode);
		sb.append(authorizationSourceCode);
		sb.append(issuerResponseCode);
		sb.append(StringUtils.repeat(" ", 2));
		sb.append(cvv2ResultCode);
		sb.append(merchantAdviceCode);
		sb.append(StringUtils.repeat(" ", 13));
		sb.append(marketSpecificDataIdentifier);
		sb.append(productIdentification);
		return sb.toString();
	}

	public static InterchangeAddendumResponse fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("IA")) {
			return null;
		}
		InterchangeAddendumResponse ia = new InterchangeAddendumResponse();
		ia.setAci(s.charAt(32));
		ia.setAvsResultCode(s.charAt(33));
		ia.setTransactionId(s.substring(36, 51));
		ia.setValidationCode(s.substring(52, 56));
		ia.setAuthorizationSourceCode(s.charAt(56));
		ia.setIssuerResponseCode(s.substring(57, 59));
		ia.setCvv2ResultCode(s.charAt(61));
		ia.setMerchantAdviceCode(s.substring(62, 64));
		ia.setMarketSpecificDataIdentifier(s.charAt(77));
		ia.setProductIdentification(s.substring(78, 80));
		return ia;
	}

}
