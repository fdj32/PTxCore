package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * ELECTRONIC INTERCHANGE ADDENDUM RECORD, TYPE 07
 * 
 * @author nickfeng
 *
 */
public class BInterchangeAddendum {

	/**
	 * Record Type, 1-2, AN 2, Always 07.
	 */
	private String recordType = "07";

	/**
	 * Authorization Source Code, 3-3, AN 1,
	 * <ul>
	 * <li>For Visa: Assigned by Visa in the original authorization. Indicates the
	 * source of the authorization. Must be present in the settlement message. Valid
	 * values are:
	 * 
	 * <ul>
	 * <li>space - Default value</li>
	 * <li>0 - Advice of Exception file change</li>
	 * <li>B - Response provided by STIP: Transaction met Visa Transaction Advisor
	 * Service criteria.</li>
	 * </ul>
	 * 
	 * </li>
	 * <li>For American Express and Discover: Returned by Global Payments in the
	 * response to indicate an in-application tokenized original transaction. Must
	 * be present in the settlement message. Valid value is: Y - Transaction
	 * initiated with a token.</li>
	 * <li>For other card types: Not used.</li>
	 * </ul>
	 */
	private char authorizationSourceCode;

	/* Reserved, 4-4, AN 1, Space filled. */

	/**
	 * Cardholder ID Method, 5-5, N 1, Code indicating the method of identification
	 * used for the transaction. Valid entries are:
	 * <ul>
	 * <li>0 or space - Not applicable</li>
	 * <li>1 - Signature</li>
	 * <li>2 - PIN</li>
	 * <li>3 - Unattended terminal, no PIN pad</li>
	 * <li>4 - Card not present</li>
	 * </ul>
	 */
	private char cardholderIdMethod;

	/**
	 * Authorization Characteristics Indicator, 6-6, AN 1, Authorization
	 * Characteristic Indicator (ACI). Valid values for the response messages are
	 * listed below.
	 * <ul>
	 * <li>For Visa:
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
	 * 
	 * </li>
	 * <li>For MasterCard: P - Preferred Customer Program Participation</li>
	 * <li>For other card types: Not used.</li>
	 * </ul>
	 */
	private char authorizationCharacteristicsIndicator;

	/**
	 * Transaction ID/Banknet Data, 7-21, AN 15, Assigned by Visa, MasterCard,
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

	/**
	 * Validation Code/Magnetic Stripe Compliance Indicators/ Magnetic Stripe
	 * Condition Code, 22-25, AN 4,
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
	 * <li>space - not applicable.</li>
	 * </ul>
	 * </li>
	 * <li>For Discover: This field is used to indicate the magnetic stripe
	 * condition of the authorization response and the Processing Code in the
	 * request. Must be present in the settlement request. 1st Character: Track 1
	 * Data Indicator This position indicates the condition of track 1 Data in the
	 * request. Valid values are:
	 * 
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
	 * 3rd and 4th Characters: The first two positions of the Processing Code value
	 * sent to Discover in the original authorization request.
	 * 
	 * </li>
	 * <li>For other card types: Not used.</li>
	 * </ul>
	 */
	private String validationCode;

	/**
	 * Original Authorized Amount, 26-35, N 10
	 */
	private String originalAuthorizedAmount;

	/**
	 * Amount 2, 36-45, N 10, Included in the transaction amount contained in the
	 * Amount 1 field. Used for reporting purposes on Purchase transactions. For
	 * Commercial Card transactions, represents the tax amount. Field is zero filled
	 * if not used. 2 decimal places assumed, right justified, zero filled.
	 */
	private String amount2;

	/**
	 * Response Code, 46-47, AN 2, A field assigned by issuer at the time of
	 * authorization to indicate the issuer's response to the authorization request.
	 */
	private String responseCode;

	/**
	 * Merchant Category Code, 48-51, N 4, Code indicating type of business,
	 * product, or service.
	 */
	private String merchantCategoryCode;

	/**
	 * POS Entry Mode, 52-53, AN 2, Code indicating how account number and
	 * expiration date were entered into POS device. The values in parentheses are
	 * the equivalent values for mapping from position 7 of the POS Entry Mode in
	 * the Greenhouse South Authorization Platform Online Message Specification.
	 * Valid codes are:
	 * <ul>
	 * <li>01 - Key entered (6, 7)</li>
	 * <li>03 - Bar Code/Payment Code (D) (Visa and Discover Only)</li>
	 * <li>05 - Integrated Circuit Card (ICC) read (5)</li>
	 * <li>07 - Proximity payment via ICC rules (A)</li>
	 * <li>10 - Key entered, credential on file (F) (Visa Only)</li>
	 * <li>79 - Key entered, chip and magnetic stripe read failed (C)</li>
	 * <li>80 - Chip read failed and terminal defaulted to magnetic stripe (B)</li>
	 * <li>81 - MasterCard electronic commerce (6, 8)</li>
	 * <li>86 - Contactless Interface Change (E) (Discover Only)</li>
	 * <li>90 - Complete magnetic stripe read & transmitted (2)</li>
	 * <li>91 - Proximity payment via magnetic stripe rules (9)</li>
	 * <li>Space - Not specified (0)</li>
	 * </ul>
	 */
	private String posEntryMode;

	/**
	 * POS Capability, 54-54, AN 1, Code indicating capability of the POS terminal
	 * to electronically read cards. Valid codes are:
	 * <ul>
	 * <li>0 - Unknown</li>
	 * <li>1 - No terminal</li>
	 * <li>2 - Magnetic stripe read</li>
	 * <li>4 - Contactless</li>
	 * <li>5 - Contact chip</li>
	 * <li>6 - Key entry only (no electronic reading)</li>
	 * <li>7 - Contact chip and contactless</li>
	 * <li>Space - Defaults to 0</li>
	 * </ul>
	 * 
	 */
	private char posCapability;

	/**
	 * Zip Code, 55-63, AN 9, Merchant zip code. Left justified, space filled.
	 */
	private String zipCode;

	/**
	 * Market Specific Data Identifier, 64-64, AN 1
	 * <ul>
	 * <li>For Visa: Valid values are:
	 * <ul>
	 * <li>A - Auto Rental</li>
	 * <li>B - Bill Payment</li>
	 * <li>E - Electronic Commerce Transaction Aggregation</li>
	 * <li>H - Hotel</li>
	 * <li>J - B2B Invoice Payments</li>
	 * <li>M - Healthcare/Medical (auto-substantiation)</li>
	 * <li>N - Failed Market Specific Data edits</li>
	 * <li>T - Transit (auto-substantiation)</li>
	 * </ul>
	 * 
	 * </li>
	 * 
	 * <li>For MasterCard: Valid value is:
	 * 
	 * <ul>
	 * <li>M - Healthcare/Medical</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 * 
	 * </li>
	 * 
	 * </ul>
	 */
	private char marketSpecificDataIdentifier;

	/**
	 * Systems Trace Audit Number (STAN), 65-70, AN 6, For Discover: The STAN from
	 * the original authorization request. The first six bytes of Acquirer Reference
	 * Data subfield j in the original authorization response.
	 */
	private String systemsTraceAuditNumber;

	/**
	 * AVS Result Code, 71-71, AN 1, Card association AVS result code. Refer to AVS
	 * Result Codes on page A-5 for a list of values.
	 */
	private char avsResultCode;

	/**
	 * CVV2/CVC2/CID Result Code, 72-72, AN 1, Contains the CVV2/CVC2/CID result
	 * code.Valid values are:
	 * <ul>
	 * <li>M - CVV2/CVC2/CID Match</li>
	 * <li>N - CVV2/CVC2/CID No Match</li>
	 * <li>P - Not processed</li>
	 * <li>S - CVV2/CID should be on the card, but the merchant has indicated that
	 * CVV2/CID is not present (Visa and Discover only)</li>
	 * <li>U - Issuer is not certified for CVV2/CVC2/CID processing</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 * 
	 */
	private char cvv2ResultCode;

	/**
	 * MOTO/EC Indicator, 73-74, AN 2
	 * <ul>
	 * <li>Contains the Mail/Telephone or Electronic Commerce Indicator. Valid codes
	 * for Mail/Telephone transactions are:
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
	 * </ul>
	 * </li>
	 * <li>For American Express:
	 * <ul>
	 * <li>05 - Authenticated with AEVV</li>
	 * <li>06 - Attempted with AEVV</li>
	 * <li>07 - Not Authenticated</li>
	 * <li>20 - Payment token data present</li>
	 * </ul>
	 * </li>
	 * <li>For Discover:
	 * <ul>
	 * <li>00 - Unknown/Unspecified</li>
	 * <li>01 - Transaction is not an e-commerce transaction</li>
	 * <li>04 - In-App Authentication</li>
	 * <li>05 - Card transaction is a secure transaction (Cardholder authenticated
	 * using Protect Buy)</li>
	 * <li>06 - Merchant attempted to authenticate the cardholder information using
	 * Protect Buy, but was not able to complete authentication because the
	 * cardholder or the issuer does not participate in Protect Buy.</li>
	 * <li>07 - E-commerce Card Transaction with data protection but not using
	 * ProtectBuy for cardholder authentication</li>
	 * <li>08 - E-commerce Card Transaction without data protection</li>
	 * </ul>
	 * </li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 */
	private String motoECIndicator;

	/* Reserved, 75-87, AN 13, Space filled. */

	/**
	 * Cardholder Presence Indicator, 88-88, AN 1, Value indicating whether the
	 * cardholder was present at the time of the transaction. This value should
	 * match what was sent in the authorization request.
	 * <ul>
	 * <li>0 - Present</li>
	 * <li>1 - Not present (unspecified)</li>
	 * <li>2 - Not present (mail order)</li>
	 * <li>3 - Not present (phone order)</li>
	 * <li>4 - Not present (recurring billing)</li>
	 * <li>5 - Electronic Order (home PC, Internet)</li>
	 * <li>6 - Not present (standing/credential on file authorization)</li>
	 * <li>7 - Not present (initial credential on file storage)</li>
	 * </ul>
	 */
	private char cardholderPresenceIndicator;

	/* Filler, 89-91, AN 3, Space filled. */

	/**
	 * Prestigious Property Indicator, 92-92, AN 1, For merchants participating in
	 * Visa’s Prestigious Lodging program. Valid values are:
	 * <ul>
	 * <li>D - Visa defined limit</li>
	 * <li>B - Visa defined limit</li>
	 * <li>S - Visa defined limit</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 */
	private char prestigiousPropertyIndicator;

	/**
	 * Product Identification, 93-94, AN 2, Value assigned by Visa in the original
	 * authorization response. Refer to Product Identification Values on page A-17
	 * for valid values.
	 */
	private String productIdentification;

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

	public char getAuthorizationSourceCode() {
		return authorizationSourceCode;
	}

	public void setAuthorizationSourceCode(char authorizationSourceCode) {
		this.authorizationSourceCode = authorizationSourceCode;
	}

	public char getCardholderIdMethod() {
		return cardholderIdMethod;
	}

	public void setCardholderIdMethod(char cardholderIdMethod) {
		this.cardholderIdMethod = cardholderIdMethod;
	}

	public char getAuthorizationCharacteristicsIndicator() {
		return authorizationCharacteristicsIndicator;
	}

	public void setAuthorizationCharacteristicsIndicator(char authorizationCharacteristicsIndicator) {
		this.authorizationCharacteristicsIndicator = authorizationCharacteristicsIndicator;
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

	public String getOriginalAuthorizedAmount() {
		return originalAuthorizedAmount;
	}

	public void setOriginalAuthorizedAmount(String originalAuthorizedAmount) {
		this.originalAuthorizedAmount = originalAuthorizedAmount;
	}

	public String getAmount2() {
		return amount2;
	}

	public void setAmount2(String amount2) {
		this.amount2 = amount2;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getMerchantCategoryCode() {
		return merchantCategoryCode;
	}

	public void setMerchantCategoryCode(String merchantCategoryCode) {
		this.merchantCategoryCode = merchantCategoryCode;
	}

	public String getPosEntryMode() {
		return posEntryMode;
	}

	public void setPosEntryMode(String posEntryMode) {
		this.posEntryMode = posEntryMode;
	}

	public char getPosCapability() {
		return posCapability;
	}

	public void setPosCapability(char posCapability) {
		this.posCapability = posCapability;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public char getMarketSpecificDataIdentifier() {
		return marketSpecificDataIdentifier;
	}

	public void setMarketSpecificDataIdentifier(char marketSpecificDataIdentifier) {
		this.marketSpecificDataIdentifier = marketSpecificDataIdentifier;
	}

	public String getSystemsTraceAuditNumber() {
		return systemsTraceAuditNumber;
	}

	public void setSystemsTraceAuditNumber(String systemsTraceAuditNumber) {
		this.systemsTraceAuditNumber = systemsTraceAuditNumber;
	}

	public char getAvsResultCode() {
		return avsResultCode;
	}

	public void setAvsResultCode(char avsResultCode) {
		this.avsResultCode = avsResultCode;
	}

	public char getCvv2ResultCode() {
		return cvv2ResultCode;
	}

	public void setCvv2ResultCode(char cvv2ResultCode) {
		this.cvv2ResultCode = cvv2ResultCode;
	}

	public String getMotoECIndicator() {
		return motoECIndicator;
	}

	public void setMotoECIndicator(String motoECIndicator) {
		this.motoECIndicator = motoECIndicator;
	}

	public char getCardholderPresenceIndicator() {
		return cardholderPresenceIndicator;
	}

	public void setCardholderPresenceIndicator(char cardholderPresenceIndicator) {
		this.cardholderPresenceIndicator = cardholderPresenceIndicator;
	}

	public char getPrestigiousPropertyIndicator() {
		return prestigiousPropertyIndicator;
	}

	public void setPrestigiousPropertyIndicator(char prestigiousPropertyIndicator) {
		this.prestigiousPropertyIndicator = prestigiousPropertyIndicator;
	}

	public String getProductIdentification() {
		return productIdentification;
	}

	public void setProductIdentification(String productIdentification) {
		this.productIdentification = productIdentification;
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
		sb.append(authorizationSourceCode);
		sb.append(' ');
		sb.append(cardholderIdMethod);
		sb.append(authorizationCharacteristicsIndicator);
		sb.append(transactionId);
		sb.append(validationCode);
		sb.append(originalAuthorizedAmount);
		sb.append(StringUtils.leftPad(amount2, 10, '0'));
		sb.append(responseCode);
		sb.append(merchantCategoryCode);
		sb.append(posEntryMode);
		sb.append(posCapability);
		sb.append(StringUtils.rightPad(zipCode, 9, ' '));
		sb.append(marketSpecificDataIdentifier);
		sb.append(systemsTraceAuditNumber);
		sb.append(avsResultCode);
		sb.append(cvv2ResultCode);
		sb.append(motoECIndicator);
		sb.append(StringUtils.repeat(" ", 13));
		sb.append(cardholderPresenceIndicator);
		sb.append(StringUtils.repeat(" ", 3));
		sb.append(prestigiousPropertyIndicator);
		sb.append(productIdentification);
		sb.append(StringUtils.leftPad(recordSequenceNumber, 6, '0'));
		return sb.toString();
	}

	public static BInterchangeAddendum fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 100 || !s.startsWith("07")) {
			return null;
		}
		BInterchangeAddendum bia = new BInterchangeAddendum();
		bia.setAuthorizationSourceCode(s.charAt(2));
		bia.setCardholderIdMethod(s.charAt(4));
		bia.setAuthorizationCharacteristicsIndicator(s.charAt(5));
		bia.setTransactionId(s.substring(6, 21));
		bia.setValidationCode(s.substring(21, 25));
		bia.setOriginalAuthorizedAmount(s.substring(25, 35));
		bia.setAmount2(s.substring(35, 45));
		bia.setResponseCode(s.substring(45, 47));
		bia.setMerchantCategoryCode(s.substring(47, 51));
		bia.setPosEntryMode(s.substring(51, 53));
		bia.setPosCapability(s.charAt(53));
		bia.setZipCode(s.substring(54, 63));
		bia.setMarketSpecificDataIdentifier(s.charAt(63));
		bia.setSystemsTraceAuditNumber(s.substring(64, 70));
		bia.setAvsResultCode(s.charAt(70));
		bia.setCvv2ResultCode(s.charAt(71));
		bia.setMotoECIndicator(s.substring(72, 74));
		bia.setCardholderPresenceIndicator(s.charAt(87));
		bia.setPrestigiousPropertyIndicator(s.charAt(91));
		bia.setProductIdentification(s.substring(92, 94));
		bia.setRecordSequenceNumber(s.substring(94, 100));
		return bia;
	}

}
