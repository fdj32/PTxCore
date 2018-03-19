package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * DETAIL REQUEST RECORD, TYPE 05
 * 
 * @author nickfeng
 *
 */
public class BDetailRequest {

	/**
	 * Record Type, 1-2, AN 2, Always 05.
	 */
	private String recordType = "05";

	/* Filler, 3-3, AN 1, Space filled. */

	/**
	 * Sales Type, 4-4, N 1, Valid codes are:
	 * <ul>
	 * <li>0 - Sale/Purchase</li>
	 * <li>1 - Cash Advance</li>
	 * <li>2 - Mail/Telephone Order</li>
	 * <li>7 - Credit/Return</li>
	 * </ul>
	 */
	private char salesType;

	/**
	 * Account Number, 5-23, AN 19, Cardholder's Account Number. Left justified,
	 * space filled.
	 */
	private String accountNumber;

	/* Filler, 24-28, AN 5, Space filled. */

	/**
	 * Transaction Amount, 29-38, N 10, Total amount of the transaction, includes
	 * any amount in the Amount 2 field. Right justified, zero filled. Decimal
	 * places based on transaction currency.
	 */
	private String transactionAmount;

	/**
	 * Authorization Code, 39-44, AN 6, Optional. However, the Authorization Code is
	 * required to qualify for incentive programs. Left justified, space filled.
	 */
	private String authorizationCode;

	/**
	 * Swiped Indicator, 45-45, N 1, Valid codes are:
	 * <ul>
	 * <li>0 - No terminal used</li>
	 * <li>1 - Key Entered</li>
	 * <li>2 - Magnetic Stripe Read (Track 2)</li>
	 * <li>6 - Magnetic Stripe Read (Track 1)</li>
	 * </ul>
	 */
	private char swipedIndicator;

	/**
	 * Commercial Card, 46-46, AN 1, Valid codes are:
	 * <ul>
	 * <li>Y - Yes</li>
	 * <li>N - No</li>
	 * <li>0 or Space - Not applicable</li>
	 * </ul>
	 * If this field is not set to Y, no associated Purchase Card Level III Data
	 * addenda will be processed.
	 */
	private char commercialCard;

	/* Filler, 47-47, AN 1, Space filled. */

	/**
	 * Card Type, 48-49, AN 2, 2-character code indicating card type for this
	 * transaction. Refer to Card Type Codes on page A-7 for a list of valid values.
	 */
	private String cardType;

	/**
	 * UCAF Collection Indicator, 50-50, AN 1, Values for MasterCard:
	 * <ul>
	 * <li>Space - Non-internet transaction</li>
	 * <li>0 - UCAF data collection is not supported by the merchant or a SecureCode
	 * merchant has chosen not to undertake SecureCode on this transaction.</li>
	 * <li>1 - UCAF data collection is supported by the merchant, and UCAF data must
	 * be present and contain an attempt AAV for MasterCard SecureCode.</li>
	 * <li>2 - UCAF data collection is supported by the merchant, and UCAF data must
	 * be present and contain a fully authenticated AAV.</li>
	 * <li>3 - UCAF data collection is supported by the merchant, and UCAF
	 * (MasterCard-assigned Static Accountholder Authentication Value) data must be
	 * present.</li>
	 * <li>5 - Issuer Risk-Based Decisioning</li>
	 * <li>6 - Merchant Risk-Based Decisioning</li>
	 * <li>7 - Partial shipment or recurring payment (UCAF data required). Liability
	 * will depend on the original UCAF values provided and matching with the
	 * initial transaction.</li>
	 * </ul>
	 */
	private char ucafCollectionIndicator;

	/**
	 * Transaction Date, 51-56, N 6, MMDDYY format. Local date.
	 */
	private String transactionDate;

	/**
	 * Transaction Time, 57-62, N 6, HHMMSS format. Local time.
	 */
	private String transactionTime;

	/**
	 * Authorization Date, 63-68, N 6, Original date the transaction was authorized
	 * in MMDDYY format. For Discover transactions, from Acquirer Reference Data
	 * subfield j in the original authorization response. The value in subfield j is
	 * in YYMMDD format.
	 */
	private String authorizationDate;

	/**
	 * Reference Number, 69-79, N 11, Optional unique reference number used for
	 * research. If not supplied in the file, the Reference Number will be generated
	 * by Global Payments.
	 */
	private String referenceNumber;

	/* Filler, 80-84, AN 5, Space filled. */

	/**
	 * Expiration Date, 85-88, N 4, MMYY format. 0000 if unavailable.
	 */
	private String expirationDate;

	/**
	 * Authorization Time, 89-94, AN 6, Original time the transaction was authorized
	 * in HHMMSS format. For Discover transactions, from Acquirer Reference Data
	 * subfield j in positions 13-18 in the original authorization response.
	 */
	private String authorizationTime;

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

	public char getSalesType() {
		return salesType;
	}

	public void setSalesType(char salesType) {
		this.salesType = salesType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public char getSwipedIndicator() {
		return swipedIndicator;
	}

	public void setSwipedIndicator(char swipedIndicator) {
		this.swipedIndicator = swipedIndicator;
	}

	public char getCommercialCard() {
		return commercialCard;
	}

	public void setCommercialCard(char commercialCard) {
		this.commercialCard = commercialCard;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public char getUcafCollectionIndicator() {
		return ucafCollectionIndicator;
	}

	public void setUcafCollectionIndicator(char ucafCollectionIndicator) {
		this.ucafCollectionIndicator = ucafCollectionIndicator;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getAuthorizationDate() {
		return authorizationDate;
	}

	public void setAuthorizationDate(String authorizationDate) {
		this.authorizationDate = authorizationDate;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getAuthorizationTime() {
		return authorizationTime;
	}

	public void setAuthorizationTime(String authorizationTime) {
		this.authorizationTime = authorizationTime;
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
		sb.append(' ');
		sb.append(salesType);
		sb.append(StringUtils.rightPad(accountNumber, 19, ' '));
		sb.append(StringUtils.repeat(" ", 5));
		sb.append(StringUtils.leftPad(transactionAmount, 10, '0'));
		sb.append(StringUtils.rightPad(authorizationCode, 6, ' '));
		sb.append(swipedIndicator);
		sb.append(commercialCard);
		sb.append(' ');
		sb.append(cardType);
		sb.append(ucafCollectionIndicator);
		sb.append(transactionDate);
		sb.append(transactionTime);
		sb.append(authorizationDate);
		sb.append(referenceNumber);
		sb.append(StringUtils.repeat(" ", 5));
		sb.append(expirationDate);
		sb.append(authorizationTime);
		sb.append(StringUtils.leftPad(recordSequenceNumber, 6, '0'));
		return sb.toString();
	}

	public static BDetailRequest fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 100 || !s.startsWith("05")) {
			return null;
		}
		BDetailRequest bdr = new BDetailRequest();
		bdr.setSalesType(s.charAt(3));
		bdr.setAccountNumber(s.substring(4, 23));
		bdr.setTransactionAmount(s.substring(28, 38));
		bdr.setAuthorizationCode(s.substring(38, 44));
		bdr.setSwipedIndicator(s.charAt(44));
		bdr.setCommercialCard(s.charAt(45));
		bdr.setCardType(s.substring(47, 49));
		bdr.setUcafCollectionIndicator(s.charAt(49));
		bdr.setTransactionDate(s.substring(50, 56));
		bdr.setTransactionTime(s.substring(56, 62));
		bdr.setAuthorizationDate(s.substring(62, 68));
		bdr.setReferenceNumber(s.substring(68, 79));
		bdr.setExpirationDate(s.substring(84, 88));
		bdr.setAuthorizationTime(s.substring(88, 94));
		bdr.setRecordSequenceNumber(s.substring(94, 100));
		return bdr;
	}

}
