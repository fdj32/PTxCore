package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class MerchantDeposit31 {

	/**
	 * 1-2, '31'
	 */
	private String recordType;

	/**
	 * 3-8, Sequential number of the record within file. Incremented by 1 for each
	 * record.
	 */
	private String recordSequenceNumber;

	/**
	 * 9-24
	 */
	private String uniqueBatchId;

	/**
	 * 25-28
	 */
	private String uniqueTransactionId;

	/**
	 * 29-40
	 */
	private String batchId;

	/**
	 * 41-48
	 */
	private String transactionDate;

	/**
	 * 49-59
	 */
	private String transactionAmount;

	/**
	 * 60-62
	 */
	private String transactionType;

	/**
	 * 63-78
	 */
	private String cardholderNumberMask;

	/**
	 * 79-94
	 */
	private String cardType;

	/**
	 * 95-144
	 */
	private String cardProductName;

	/**
	 * 145-148
	 */
	private String transactionCode;

	/**
	 * 149-159
	 */
	private String accountRangeId;

	/**
	 * 160-167
	 */
	private String authorizationDate;

	/**
	 * 168-178
	 */
	private String authorizationAmount;

	/**
	 * 179-184
	 */
	private String authorizationCode;

	/**
	 * 185-192
	 */
	private String authorizationMethodId;

	/**
	 * 193
	 */
	private char cardholderIdMethod;

	/**
	 * 194-195
	 */
	private String posEntryMode;

	/**
	 * 196-197
	 */
	private String posConditionCode;

	/**
	 * 198-199
	 */
	private String posTerminalCapability;

	/**
	 * 200-203
	 */
	private String cvv2ResultCode;

	/**
	 * 204-219
	 */
	private String referenceNumber;

	/**
	 * 220-222
	 */
	private String catInd;

	/**
	 * 223-224
	 */
	private String authorizationResponseCode;

	/**
	 * 225-244
	 */
	private String connectivityType;

	/**
	 * 245-259
	 */
	private String debitNetworkName;

	/**
	 * 260-289
	 */
	private String feVendor;

	/**
	 * 290-291
	 */
	private String thirdPartyProviderId;

	/**
	 * 292-300
	 */
	private String mastercardBankNetRefNumber;

	/**
	 * 301-315
	 */
	private String visaTransId;

	/**
	 * 316-330
	 */
	private String onlineAccountNumber;

	/**
	 * 331
	 */
	private char transactionDisposition;

	/**
	 * 332-381
	 */
	private String description;

	/**
	 * 382-451
	 */
	private String invoiceNbr;

	/**
	 * 452-501
	 */
	private String customerId;

	/**
	 * 502-526
	 */
	private String directMktInvoiceNbr;

	/* Filler, 527-550, AN 24, Space Filled. */

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecordSequenceNumber() {
		return recordSequenceNumber;
	}

	public void setRecordSequenceNumber(String recordSequenceNumber) {
		this.recordSequenceNumber = recordSequenceNumber;
	}

	public String getUniqueBatchId() {
		return uniqueBatchId;
	}

	public void setUniqueBatchId(String uniqueBatchId) {
		this.uniqueBatchId = uniqueBatchId;
	}

	public String getUniqueTransactionId() {
		return uniqueTransactionId;
	}

	public void setUniqueTransactionId(String uniqueTransactionId) {
		this.uniqueTransactionId = uniqueTransactionId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getCardholderNumberMask() {
		return cardholderNumberMask;
	}

	public void setCardholderNumberMask(String cardholderNumberMask) {
		this.cardholderNumberMask = cardholderNumberMask;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardProductName() {
		return cardProductName;
	}

	public void setCardProductName(String cardProductName) {
		this.cardProductName = cardProductName;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public String getAccountRangeId() {
		return accountRangeId;
	}

	public void setAccountRangeId(String accountRangeId) {
		this.accountRangeId = accountRangeId;
	}

	public String getAuthorizationDate() {
		return authorizationDate;
	}

	public void setAuthorizationDate(String authorizationDate) {
		this.authorizationDate = authorizationDate;
	}

	public String getAuthorizationAmount() {
		return authorizationAmount;
	}

	public void setAuthorizationAmount(String authorizationAmount) {
		this.authorizationAmount = authorizationAmount;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public String getAuthorizationMethodId() {
		return authorizationMethodId;
	}

	public void setAuthorizationMethodId(String authorizationMethodId) {
		this.authorizationMethodId = authorizationMethodId;
	}

	public char getCardholderIdMethod() {
		return cardholderIdMethod;
	}

	public void setCardholderIdMethod(char cardholderIdMethod) {
		this.cardholderIdMethod = cardholderIdMethod;
	}

	public String getPosEntryMode() {
		return posEntryMode;
	}

	public void setPosEntryMode(String posEntryMode) {
		this.posEntryMode = posEntryMode;
	}

	public String getPosConditionCode() {
		return posConditionCode;
	}

	public void setPosConditionCode(String posConditionCode) {
		this.posConditionCode = posConditionCode;
	}

	public String getPosTerminalCapability() {
		return posTerminalCapability;
	}

	public void setPosTerminalCapability(String posTerminalCapability) {
		this.posTerminalCapability = posTerminalCapability;
	}

	public String getCvv2ResultCode() {
		return cvv2ResultCode;
	}

	public void setCvv2ResultCode(String cvv2ResultCode) {
		this.cvv2ResultCode = cvv2ResultCode;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getCatInd() {
		return catInd;
	}

	public void setCatInd(String catInd) {
		this.catInd = catInd;
	}

	public String getAuthorizationResponseCode() {
		return authorizationResponseCode;
	}

	public void setAuthorizationResponseCode(String authorizationResponseCode) {
		this.authorizationResponseCode = authorizationResponseCode;
	}

	public String getConnectivityType() {
		return connectivityType;
	}

	public void setConnectivityType(String connectivityType) {
		this.connectivityType = connectivityType;
	}

	public String getDebitNetworkName() {
		return debitNetworkName;
	}

	public void setDebitNetworkName(String debitNetworkName) {
		this.debitNetworkName = debitNetworkName;
	}

	public String getFeVendor() {
		return feVendor;
	}

	public void setFeVendor(String feVendor) {
		this.feVendor = feVendor;
	}

	public String getThirdPartyProviderId() {
		return thirdPartyProviderId;
	}

	public void setThirdPartyProviderId(String thirdPartyProviderId) {
		this.thirdPartyProviderId = thirdPartyProviderId;
	}

	public String getMastercardBankNetRefNumber() {
		return mastercardBankNetRefNumber;
	}

	public void setMastercardBankNetRefNumber(String mastercardBankNetRefNumber) {
		this.mastercardBankNetRefNumber = mastercardBankNetRefNumber;
	}

	public String getVisaTransId() {
		return visaTransId;
	}

	public void setVisaTransId(String visaTransId) {
		this.visaTransId = visaTransId;
	}

	public String getOnlineAccountNumber() {
		return onlineAccountNumber;
	}

	public void setOnlineAccountNumber(String onlineAccountNumber) {
		this.onlineAccountNumber = onlineAccountNumber;
	}

	public char getTransactionDisposition() {
		return transactionDisposition;
	}

	public void setTransactionDisposition(char transactionDisposition) {
		this.transactionDisposition = transactionDisposition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInvoiceNbr() {
		return invoiceNbr;
	}

	public void setInvoiceNbr(String invoiceNbr) {
		this.invoiceNbr = invoiceNbr;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDirectMktInvoiceNbr() {
		return directMktInvoiceNbr;
	}

	public void setDirectMktInvoiceNbr(String directMktInvoiceNbr) {
		this.directMktInvoiceNbr = directMktInvoiceNbr;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(recordSequenceNumber);
		sb.append(uniqueBatchId);
		sb.append(uniqueTransactionId);
		sb.append(batchId);
		sb.append(transactionDate);
		sb.append(transactionAmount);
		sb.append(transactionType);
		sb.append(cardholderNumberMask);
		sb.append(cardType);
		sb.append(cardProductName);
		sb.append(transactionCode);
		sb.append(accountRangeId);
		sb.append(authorizationDate);
		sb.append(authorizationAmount);
		sb.append(authorizationCode);
		sb.append(authorizationMethodId);
		sb.append(cardholderIdMethod);
		sb.append(posEntryMode);
		sb.append(posConditionCode);
		sb.append(posTerminalCapability);
		sb.append(cvv2ResultCode);
		sb.append(referenceNumber);
		sb.append(catInd);
		sb.append(authorizationResponseCode);
		sb.append(connectivityType);
		sb.append(debitNetworkName);
		sb.append(feVendor);
		sb.append(thirdPartyProviderId);
		sb.append(mastercardBankNetRefNumber);
		sb.append(visaTransId);
		sb.append(onlineAccountNumber);
		sb.append(transactionDisposition);
		sb.append(description);
		sb.append(invoiceNbr);
		sb.append(customerId);
		sb.append(directMktInvoiceNbr);
		sb.append(StringUtils.repeat(" ", 24));
		return sb.toString();
	}

	public static MerchantDeposit31 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		MerchantDeposit31 o = new MerchantDeposit31();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setUniqueBatchId(s.substring(8, 24));
		o.setUniqueTransactionId(s.substring(24, 28));
		o.setBatchId(s.substring(28, 40));
		o.setTransactionDate(s.substring(40, 48));
		o.setTransactionAmount(s.substring(48, 59));
		o.setTransactionType(s.substring(59, 62));
		o.setCardholderNumberMask(s.substring(62, 78));
		o.setCardType(s.substring(78, 94));
		o.setCardProductName(s.substring(94, 144));
		o.setTransactionCode(s.substring(144, 148));
		o.setAccountRangeId(s.substring(148, 159));
		o.setAuthorizationDate(s.substring(159, 167));
		o.setAuthorizationAmount(s.substring(167, 178));
		o.setAuthorizationCode(s.substring(178, 184));
		o.setAuthorizationMethodId(s.substring(184, 192));
		o.setCardholderIdMethod(s.charAt(192));
		o.setPosEntryMode(s.substring(193, 195));
		o.setPosConditionCode(s.substring(195, 197));
		o.setPosTerminalCapability(s.substring(197, 199));
		o.setCvv2ResultCode(s.substring(199, 203));
		o.setReferenceNumber(s.substring(203, 219));
		o.setCatInd(s.substring(219, 222));
		o.setAuthorizationResponseCode(s.substring(222, 224));
		o.setConnectivityType(s.substring(224, 244));
		o.setDebitNetworkName(s.substring(244, 259));
		o.setFeVendor(s.substring(259, 289));
		o.setThirdPartyProviderId(s.substring(289, 291));
		o.setMastercardBankNetRefNumber(s.substring(291, 300));
		o.setVisaTransId(s.substring(300, 315));
		o.setOnlineAccountNumber(s.substring(315, 330));
		o.setTransactionDisposition(s.charAt(330));
		o.setDescription(s.substring(331, 381));
		o.setInvoiceNbr(s.substring(381, 451));
		o.setCustomerId(s.substring(451, 501));
		o.setDirectMktInvoiceNbr(s.substring(501, 526));
		return o;
	}

}
