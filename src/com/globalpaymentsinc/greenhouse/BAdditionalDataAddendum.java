package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * ADDITIONAL DATA ADDENDUM RECORD, TYPE 11
 * 
 * @author nickfeng
 *
 */
public class BAdditionalDataAddendum {

	/**
	 * Record Type, 1-2, AN 2, Always 11.
	 */
	private String recordType = "11";

	/**
	 * Transaction Fee Amount, 3-11, AN 9, The amount of any surcharge on the
	 * transaction. The surcharge prefix followed by the 8-digit surcharge amount.
	 * Valid surcharge prefix values are:
	 * <ul>
	 * <li>C - Credit to cardholder</li>
	 * <li>D - Debit to cardholder</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 */
	private String transactionFeeAmount;

	/**
	 * Device Type Indicator, 12-13, AN 2, For MasterCard: Provides information
	 * about the device type used to initiate a non-card transaction. Valid values
	 * are:
	 * <ul>
	 * <li>00 - Card</li>
	 * <li>01 - Mobile Phone or Smartphone with Mobile Network Operator (MNO)
	 * controlled removable secure element (SIM or UICC) personalized for use with a
	 * Mobile Phone or Smartphone</li>
	 * <li>02 - Key fob</li>
	 * <li>03 - Watch</li>
	 * <li>04 - Mobile tag</li>
	 * <li>05 - Wristband</li>
	 * <li>06 - Mobile Phone case or sleeve</li>
	 * <li>07 - Mobile Phone or Smartphone with a fixed (nonremovable) secure
	 * element controlled by the MNO, for example, code division multiple access
	 * (CDMA)</li>
	 * <li>08 - Mobile Phone or Smartphone with removable secure element not
	 * controlled by the MNO, for example, memory card personalized for use with a
	 * Mobile Phone or Smartphone</li>
	 * <li>09 - Mobile Phone or Smartphone with a fixed (non-removable) secure
	 * element not controlled by the MNO</li>
	 * <li>10 - Tablet or e-Reader with an MNO controlled removable secure element
	 * (SIM or UICC) personalized for use with a tablet or e-Reader</li>
	 * <li>11 - Tablet or e-Reader with a fixed (non-removable) secure element
	 * controlled by the MNO</li>
	 * <li>12 - Removable secure element not controlled by the MNO, for example,
	 * memory card personalized for use with a tablet or e-Reader</li>
	 * <li>13 - Tablet or e-Reader with fixed (non-removable) secure element not
	 * controlled by the MNO</li>
	 * <li>14 - Mobile Phone or Smartphone with a payment application running in a
	 * host processor.</li>
	 * <li>15 - Tablet or e-Reader with a payment application running in a host
	 * processor.</li>
	 * <li>16 - Mobile phone or smartphone with a payment application running in the
	 * TEE of a host processor</li>
	 * <li>17 - Tablet or e-book with a payment application running in the TEE of a
	 * host processor</li>
	 * <li>18 - Watch with a payment application running in the TEE of a host
	 * processor</li>
	 * <li>19 - Watch with a payment application running in a host processor</li>
	 * <li>20 - Card</li>
	 * <li>21 - Mobile phone</li>
	 * <li>22 - Tablet computer or e-reader</li>
	 * <li>23 - Watch or wristband, including a fitness band, smart strap,
	 * disposable band, watch add-on, and security/ID band</li>
	 * <li>24 - Sticker</li>
	 * <li>25 - PC or laptop</li>
	 * <li>26 - Device peripheral, such as a mobile phone case or sleeve</li>
	 * <li>27 - Key fob or mobile tag</li>
	 * <li>28 - Jewelry, such as a ring, bracelet, necklace, or cuff links</li>
	 * <li>29 - Fashion accessory, such as a handbag, bag charm, or glasses</li>
	 * <li>30 - Garment, such as a dress</li>
	 * <li>31 - Domestic appliance, such as a refrigerator or washing machine</li>
	 * <li>32 - Vehicle, including vehicle attached devices</li>
	 * <li>33 - Media or gaming device, including a set top box, media player, and
	 * television</li>
	 * <li>34-99 - Reserved for future form factors. Any value in this range may
	 * occur within form factor and transaction data without prior notice.</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 * For other card types: Space filled.
	 */
	private String deviceTypeIndicator;

	/**
	 * Wallet Program Data, 14-16, AN 3, Identifies transactions submitted using
	 * MasterPass Online or MasterCard Digital Enablement Service. Valid values are:
	 * <ul>
	 * <li>101 - Wallet Remote</li>
	 * <li>102 - Wallet Remote NFC Payment</li>
	 * <li>103 - Apple Pay</li>
	 * <li>216 - Android Pay</li>
	 * <li>217 - Samsung Pay</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 * Other undocumented values may be present in authorization requests. These
	 * values should be retained and passed for settlement.
	 */
	private String walletProgramData;

	/**
	 * Spend Qualified Indicator, 17-17, AN 1, For Visa: Valid values are:
	 * <ul>
	 * <li>B - Base spend assessment threshold defined by Visa has been met</li>
	 * <li>N - Spend assessment threshold defined by Visa has not been met</li>
	 * <li>Q - Qualified spend assessment threshold defined by Visa has been
	 * met</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 */
	private char spendQualifiedIndicator;

	/**
	 * Mobile POS Acceptance Device, 18-19, AN 1, Indicates that a mobile device,
	 * such as a smartphone, tablet or PDS is used as a point-of-sale terminal
	 * typically using a POS card reader accessory that can either be plugged into
	 * the audio jack or USB port or connected via Bluetooth to read magnetic
	 * stripe, chip contact or contactless payment cards. Valid Values are:
	 * <ul>
	 * <li>5 - Mobile Acceptance Device</li>
	 * <li>Space - Not applicable</li>
	 * </ul>
	 */
	private char mobilePosAcceptanceDevice;

	/**
	 * Token Assurance Level, 19-20, AN 2, The value returned in an authorization
	 * response to indicate the confidence level of the token to PAN/ cardholder
	 * binding.
	 */
	private String tokenAssuranceLevel;

	/**
	 * Token Requestor ID, 21-31, AN 11, The value returned in an authorization
	 * response assigned by the Token Service Provider to the Token Requestor. This
	 * value uniquely identifies the pairing of the Token Requestor with the token
	 * domain.
	 */
	private String tokenRequestorId;

	/**
	 * Electronic Commerce Security Level, 32-33, AN 2, For MasterCard. Valid values
	 * are: Position 1: Security Protocol
	 * <ul>
	 * <li>2 - Channel</li>
	 * <li>9 - None (no security protocol)</li>
	 * </ul>
	 * Position 2: Cardholder Authentication
	 * <ul>
	 * <li>1 - Cardholder certificate not used</li>
	 * <li>2 - Processed through MasterPass</li>
	 * <li>4 - Digital Secure Remote Payment transaction</li>
	 * </ul>
	 * Not applicable: Space filled.
	 */
	private String electronicCommerceSecurityLevel;

	/**
	 * Transaction Integrity Class, 34-35, AN 2, For MasterCard. The
	 * MasterCard-provided Transaction Integrity Classification for Point of Sale
	 * (POS) Purchase and Purchase with Cash Back transactions. Valid values are:
	 * <ul>
	 * <li>A1 - Card and Cardholder Present; EMV/Token in a Secure,Trusted
	 * Environment</li>
	 * <li>B1 - Card and Cardholder Present; EMV/Chip Equivalent</li>
	 * <li>C1 - Card and Cardholder Present; Mag Stripe</li>
	 * <li>E1 - Card and Cardholder Present; Key Entered</li>
	 * <li>U0 - Card and Cardholder Present; Unclassified</li>
	 * <li>A2 - Card and/or Cardholder Not Present; Digital Transactions</li>
	 * <li>B2 - Card and/or Cardholder Not Present; Authenticated Checkout</li>
	 * <li>C2 - Card and/or Cardholder Not Present; Transaction Validation</li>
	 * <li>D2 - Card and/or Cardholder Not Present; Enhanced Data</li>
	 * <li>E2 - Card and/or Cardholder Not Present; Generic Messaging</li>
	 * <li>U0 - Card and/or Cardholder Not Present; Unclassified</li>
	 * </ul>
	 */
	private String transactionIntegrityClass;

	/**
	 * Account Status, 36-36, AN 1, For Visa. Applies to U.S.-issued and U.S.
	 * territory issued debit and prepaid cards and identifies the account range as
	 * regulated or non-regulated. Valid values are:
	 * <ul>
	 * <li>R - Regulated</li>
	 * <li>N - Non-regulated</li>
	 * </ul>
	 */
	private char accountStatus;

	/**
	 * Digital Entity Identifier, 37-41, AN 5, For Visa. Indicates that the
	 * transaction was processed through Visa Checkout. Valid values are: VCIND -
	 * Visa Checkout
	 */
	private String digitalEntityIdentifier;

	/**
	 * Transaction Status Indicator, 42-42, AN 1, For Discover. Code indicating the
	 * purpose or status of the transaction request. Valid values are:
	 * <ul>
	 * <li>0 - Normal Request</li>
	 * <li>4 - Preauthorized Request</li>
	 * <li>P - Partial/Split Shipment</li>
	 * <li>R - Recurring Payment</li>
	 * <li>A - Re-authorize for Full Amount</li>
	 * </ul>
	 */
	private char transactionStatusIndicator;

	/**
	 * POS Environment, 43-43, AN 1, For Visa. Valid values are:
	 * <ul>
	 * <li>C - Credential on file</li>
	 * <li>I - Installment payment</li>
	 * <li>R - Recurring billing</li>
	 * </ul>
	 */
	private char posEnvironment;

	/* Filler, 44-94, AN 51, Space Filled. */

	/**
	 * Record Sequence Number, 95-100, N 6, Previous Record Sequence Number plus 1.
	 */
	private String recordSequenceNumber;

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getTransactionFeeAmount() {
		return transactionFeeAmount;
	}

	public void setTransactionFeeAmount(String transactionFeeAmount) {
		this.transactionFeeAmount = transactionFeeAmount;
	}

	public String getDeviceTypeIndicator() {
		return deviceTypeIndicator;
	}

	public void setDeviceTypeIndicator(String deviceTypeIndicator) {
		this.deviceTypeIndicator = deviceTypeIndicator;
	}

	public String getWalletProgramData() {
		return walletProgramData;
	}

	public void setWalletProgramData(String walletProgramData) {
		this.walletProgramData = walletProgramData;
	}

	public char getSpendQualifiedIndicator() {
		return spendQualifiedIndicator;
	}

	public void setSpendQualifiedIndicator(char spendQualifiedIndicator) {
		this.spendQualifiedIndicator = spendQualifiedIndicator;
	}

	public char getMobilePosAcceptanceDevice() {
		return mobilePosAcceptanceDevice;
	}

	public void setMobilePosAcceptanceDevice(char mobilePosAcceptanceDevice) {
		this.mobilePosAcceptanceDevice = mobilePosAcceptanceDevice;
	}

	public String getTokenAssuranceLevel() {
		return tokenAssuranceLevel;
	}

	public void setTokenAssuranceLevel(String tokenAssuranceLevel) {
		this.tokenAssuranceLevel = tokenAssuranceLevel;
	}

	public String getTokenRequestorId() {
		return tokenRequestorId;
	}

	public void setTokenRequestorId(String tokenRequestorId) {
		this.tokenRequestorId = tokenRequestorId;
	}

	public String getElectronicCommerceSecurityLevel() {
		return electronicCommerceSecurityLevel;
	}

	public void setElectronicCommerceSecurityLevel(String electronicCommerceSecurityLevel) {
		this.electronicCommerceSecurityLevel = electronicCommerceSecurityLevel;
	}

	public String getTransactionIntegrityClass() {
		return transactionIntegrityClass;
	}

	public void setTransactionIntegrityClass(String transactionIntegrityClass) {
		this.transactionIntegrityClass = transactionIntegrityClass;
	}

	public char getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(char accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getDigitalEntityIdentifier() {
		return digitalEntityIdentifier;
	}

	public void setDigitalEntityIdentifier(String digitalEntityIdentifier) {
		this.digitalEntityIdentifier = digitalEntityIdentifier;
	}

	public char getTransactionStatusIndicator() {
		return transactionStatusIndicator;
	}

	public void setTransactionStatusIndicator(char transactionStatusIndicator) {
		this.transactionStatusIndicator = transactionStatusIndicator;
	}

	public char getPosEnvironment() {
		return posEnvironment;
	}

	public void setPosEnvironment(char posEnvironment) {
		this.posEnvironment = posEnvironment;
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
		sb.append(transactionFeeAmount);
		sb.append(deviceTypeIndicator);
		sb.append(walletProgramData);
		sb.append(spendQualifiedIndicator);
		sb.append(mobilePosAcceptanceDevice);
		sb.append(tokenAssuranceLevel);
		sb.append(tokenRequestorId);
		sb.append(electronicCommerceSecurityLevel);
		sb.append(transactionIntegrityClass);
		sb.append(accountStatus);
		sb.append(digitalEntityIdentifier);
		sb.append(transactionStatusIndicator);
		sb.append(posEnvironment);
		sb.append(StringUtils.repeat(" ", 51));
		sb.append(StringUtils.leftPad(recordSequenceNumber, 6, '0'));
		return sb.toString();
	}

	public static BAdditionalDataAddendum fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 100 || !s.startsWith("11")) {
			return null;
		}
		BAdditionalDataAddendum bada = new BAdditionalDataAddendum();
		bada.setTransactionFeeAmount(s.substring(2, 11));
		bada.setDeviceTypeIndicator(s.substring(11, 13));
		bada.setWalletProgramData(s.substring(13, 16));
		bada.setSpendQualifiedIndicator(s.charAt(16));
		bada.setMobilePosAcceptanceDevice(s.charAt(17));
		bada.setTokenAssuranceLevel(s.substring(18, 20));
		bada.setTokenRequestorId(s.substring(20, 31));
		bada.setElectronicCommerceSecurityLevel(s.substring(31, 33));
		bada.setTransactionIntegrityClass(s.substring(33, 35));
		bada.setAccountStatus(s.charAt(35));
		bada.setDigitalEntityIdentifier(s.substring(36, 41));
		bada.setTransactionStatusIndicator(s.charAt(41));
		bada.setPosEnvironment(s.charAt(42));
		bada.setRecordSequenceNumber(s.substring(94, 100));
		return bada;
	}

}
