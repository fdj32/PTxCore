package com.heartlandportico.hps.ddf;

import org.apache.commons.lang.StringUtils;

/**
 * DETAIL DRAFT RETRIEVAL / CHARGEBACK SEGMENT 1
 * 
 * @author nickfeng
 *
 */
public class Segment1 {

	/**
	 * 1-2
	 */
	private String recordFormat = "MH";

	/**
	 * 3-4
	 */
	private String recordType = "CB";

	/**
	 * 5-10, YYMMDD. Date item received for processing.
	 */
	private String reportDate;

	/**
	 * 11-12
	 * <ul>
	 * <li>40 = Draft Retrieval</li>
	 * <li>41 = Purchase Chargeback</li>
	 * <li>42 = Chargeback of Refund</li>
	 * </ul>
	 */
	private String transactionType;

	/**
	 * 13-24, Case Number
	 */
	private String recordNumber;

	/**
	 * 25, 1 = Segment 1
	 */
	private char segmentNumber;

	/**
	 * 26, Total number of segments for this transaction.
	 */
	private char totalSegments;

	/**
	 * 27-36, Implied 2 decimal places
	 */
	private String disputedAmount;

	/**
	 * 37-46, Implied 2 decimal places
	 */
	private String originalTransactionAmount;

	/**
	 * 47-61, Locations Terminal ID
	 */
	private String terminalId;

	/**
	 * 62-72, Locator Number for merchant to locate the original draft.
	 */
	private String merchantDraftRetrieval;

	////////////////////////////////////////////////////////////////////////
	// REDEFINE of Merchant Draft Retrieval
	/**
	 * 62, Indicates where transaction originated P = POS, N = Non-POS
	 */
	private char tranPOSFlag;

	/**
	 * 63-64, Terminal Batch Number
	 */
	private String terminalBatchNbr;

	/**
	 * 65-67, Terminal Sequence Number
	 */
	private String terminalSeqNbr;

	/* Filler, 68-72, AN 5, Reserved Spaces. */
	// END of REDEFINE
	////////////////////////////////////////////////////////////////////////
	/**
	 * 73, Chargeback presentation code, 1st, 2nd
	 */
	private char chargebackUsageCode;

	/**
	 * 74-77
	 * <ul>
	 * <li>DRFT = Draft Retrieval</li>
	 * <li>PNOT = Chargeback Prenotification</li>
	 * <li>CHBK = Chargeback Action Item</li>
	 * </ul>
	 */
	private String adjustmentType;

	/**
	 * 78-83, YYMMDD
	 */
	private String originalTransDate;

	/**
	 * 84
	 * <ul>
	 * <li>F = Facsimile draft</li>
	 * <li>S = Draft sent by merchant</li>
	 * <li>X = Draft Received by Financial Institution</li>
	 * <li>N = Draft not in - still looking for draft</li>
	 * <li>O = Draft previously provided by merchant and on file</li>
	 * </ul>
	 */
	private char draftActionCode;

	/**
	 * 85-90, YYMMDD. Date retrieval action taken
	 */
	private String draftActionCodeDate;

	/**
	 * 91
	 * <ul>
	 * <li>O = Original draft requested</li>
	 * <li>C = Copy of draft requested</li>
	 * <li>N = No draft requested</li>
	 * </ul>
	 */
	private char documentIndicator;

	/**
	 * 92-97, HHMMSS. Time of original transaction
	 */
	private String originalTransTime;

	/**
	 * 98-116, Masked – 1st 6 & last 4 characters clear. Other characters denoted as
	 * ‘*’
	 */
	private String accountNumber;

	/**
	 * 117-120, Code list will be provided
	 */
	private String chargebackActionCode;

	/**
	 * 121-126, YYMMDD. Date a chargeback action was taken. If the Adjustment Type =
	 * Fee, this will be the date the fee was sent or received.
	 */
	private String chargebackActionDate;

	/**
	 * 127-129, 840 (US) unless issuer was international
	 */
	private String issuerCurrencyCode;

	/**
	 * 130-133, Merchant Category Code
	 */
	private String mccsic;

	/**
	 * 134-135, Visa values:
	 * <ul>
	 * <li>Spaces – Not specified</li>
	 * <li>1 - Terminal not used</li>
	 * <li>2 - Mag Stripe read capability</li>
	 * <li>3 - Bar code read capability</li>
	 * <li>4 - OCR read capability</li>
	 * <li>5 - Chip-capable terminal</li>
	 * <li>6 - MICR read</li>
	 * <li>7 - MICR read and Image-capable</li>
	 * <li>8 - Proximity read capability terminal</li>
	 * <li>9 - Terminal does not have the capability to read card data</li>
	 * </ul>
	 * MasterCard values:
	 * <ul>
	 * <li>0 - Unknown</li>
	 * <li>1 - Manual; no terminal</li>
	 * <li>2 - Mag Stripe reader capability</li>
	 * <li>4 - OCR capability</li>
	 * <li>5 - ICC capability</li>
	 * <li>6 - Key entry-only capability</li>
	 * <li>A - PAN auto entry via contactless Mag stripe</li>
	 * <li>B - Mag stripe reader and key entry capability</li>
	 * <li>C - Mag stripe reader, ICC, and key entry capability</li>
	 * <li>D - Mag stripe reader and ICC capability</li>
	 * <li>E - ICC and key entry capability</li>
	 * <li>M - PAN auto entry via contactless M/Chip</li>
	 * <li>V - Other capability</li>
	 * </ul>
	 */
	private String entryMethod;

	/**
	 * 136-140, Reason causing the adjustment
	 */
	private String adjustmentReasonCode;

	/**
	 * 141-152, Issuer’s Control Number or Reference Number received from the
	 * network.
	 */
	private String chargebackReferenceNumber;

	/**
	 * 153-155, Indicates why the activity record was created:
	 * <ul>
	 * <li>100 = New Item</li>
	 * <li>102 = Update</li>
	 * </ul>
	 */
	private String transactionDispositionQualifier;

	/**
	 * 156-178, Reference Number created to help identify each transaction uniquely
	 */
	private String acquirerReferenceNumber;

	/**
	 * 179-181, Division assigned to the location
	 */
	private String divisionNumber;

	/**
	 * 182-197, Left Justified, Space filled Format = OOOOMMMMMMMMMMMb;
	 * <ul>
	 * <li>O = Client Organization Number</li>
	 * <li>M = Location Number</li>
	 * <li>b = space</li>
	 * </ul>
	 * For NWS clients:
	 * <ul>
	 * <li>O = 4 character Company</li>
	 * <li>M = Store Number (alphanumeric)</li>
	 * <li>b = spaces</li>
	 * </ul>
	 */
	private String merchantNumber;

	/**
	 * 198-200, No implied decimals. Amount of fee
	 */
	private String feeAmount;

	/**
	 * 201-202, Indicates if mag stripe read or manually keyed. Values:
	 * <ul>
	 * <li>00 = Terminal Not Used</li>
	 * <li>01 = Manually Keyed</li>
	 * <li>02 = Magnetic Stripe Read</li>
	 * <li>03 = Bar Code Read</li>
	 * <li>04 = OCR Read</li>
	 * <li>06 = Track 1 Read</li>
	 * <li>90 = Magnetic Stripe Read - PS2000 Code</li>
	 * <li>Blanks = Entry Mode Not Known</li>
	 * </ul>
	 */
	private String entryMode;

	/**
	 * 203, Auth source of the transaction
	 * <ul>
	 * <li>1 = STIP time out response</li>
	 * <li>2 = LCS/RCL response, amount below Issuer amount</li>
	 * <li>3 = STIP response, issuer in Suppress-Inquiry mode</li>
	 * <li>4 = STIP response, issuer unavailable</li>
	 * <li>5 = Issuer approval</li>
	 * <li>6 = Force STIP</li>
	 * <li>7 = Acquirer approval, BASE1/INAS unavailable</li>
	 * <li>8 = Acquirer approval of a referral</li>
	 * <li>D = Referral, authorization code manually keyed</li>
	 * <li>E = Offline approval, authorization code manually keyed</li>
	 * <li>Blanks = Authorization Source Not Known</li>
	 * </ul>
	 */
	private char authorizationSource;

	/**
	 * 204-207, Identifies network:
	 * <ul>
	 * <li>VISA - Visa</li>
	 * <li>M/C - MasterCard</li>
	 * <li>AMEX - American Express</li>
	 * <li>DISC - Discover</li>
	 * </ul>
	 */
	private String networkId;

	/**
	 * 208, Financial indicator of the Chargeback Action Code:
	 * <ul>
	 * <li>‘Y’ - Financial</li>
	 * <li>‘N’ - Non-financial</li>
	 * </ul>
	 */
	private char financialInd;

	/**
	 * 209, Denotes the impact of the Chargeback Action Code to the merchant:
	 * <ul>
	 * <li>‘D’ - Debit</li>
	 * <li>‘C’ - Credit</li>
	 * <li>Space - N/A</li>
	 * </ul>
	 */
	private char debitCreditInd;

	/* Filler, 210-260, AN 51, Reserved Spaces. */

	public String getRecordFormat() {
		return recordFormat;
	}

	public void setRecordFormat(String recordFormat) {
		this.recordFormat = recordFormat;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public char getSegmentNumber() {
		return segmentNumber;
	}

	public void setSegmentNumber(char segmentNumber) {
		this.segmentNumber = segmentNumber;
	}

	public char getTotalSegments() {
		return totalSegments;
	}

	public void setTotalSegments(char totalSegments) {
		this.totalSegments = totalSegments;
	}

	public String getDisputedAmount() {
		return disputedAmount;
	}

	public void setDisputedAmount(String disputedAmount) {
		this.disputedAmount = disputedAmount;
	}

	public String getOriginalTransactionAmount() {
		return originalTransactionAmount;
	}

	public void setOriginalTransactionAmount(String originalTransactionAmount) {
		this.originalTransactionAmount = originalTransactionAmount;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getMerchantDraftRetrieval() {
		return merchantDraftRetrieval;
	}

	public void setMerchantDraftRetrieval(String merchantDraftRetrieval) {
		this.merchantDraftRetrieval = merchantDraftRetrieval;
	}

	public char getTranPOSFlag() {
		return tranPOSFlag;
	}

	public void setTranPOSFlag(char tranPOSFlag) {
		this.tranPOSFlag = tranPOSFlag;
	}

	public String getTerminalBatchNbr() {
		return terminalBatchNbr;
	}

	public void setTerminalBatchNbr(String terminalBatchNbr) {
		this.terminalBatchNbr = terminalBatchNbr;
	}

	public String getTerminalSeqNbr() {
		return terminalSeqNbr;
	}

	public void setTerminalSeqNbr(String terminalSeqNbr) {
		this.terminalSeqNbr = terminalSeqNbr;
	}

	public char getChargebackUsageCode() {
		return chargebackUsageCode;
	}

	public void setChargebackUsageCode(char chargebackUsageCode) {
		this.chargebackUsageCode = chargebackUsageCode;
	}

	public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public String getOriginalTransDate() {
		return originalTransDate;
	}

	public void setOriginalTransDate(String originalTransDate) {
		this.originalTransDate = originalTransDate;
	}

	public char getDraftActionCode() {
		return draftActionCode;
	}

	public void setDraftActionCode(char draftActionCode) {
		this.draftActionCode = draftActionCode;
	}

	public String getDraftActionCodeDate() {
		return draftActionCodeDate;
	}

	public void setDraftActionCodeDate(String draftActionCodeDate) {
		this.draftActionCodeDate = draftActionCodeDate;
	}

	public char getDocumentIndicator() {
		return documentIndicator;
	}

	public void setDocumentIndicator(char documentIndicator) {
		this.documentIndicator = documentIndicator;
	}

	public String getOriginalTransTime() {
		return originalTransTime;
	}

	public void setOriginalTransTime(String originalTransTime) {
		this.originalTransTime = originalTransTime;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getChargebackActionCode() {
		return chargebackActionCode;
	}

	public void setChargebackActionCode(String chargebackActionCode) {
		this.chargebackActionCode = chargebackActionCode;
	}

	public String getChargebackActionDate() {
		return chargebackActionDate;
	}

	public void setChargebackActionDate(String chargebackActionDate) {
		this.chargebackActionDate = chargebackActionDate;
	}

	public String getIssuerCurrencyCode() {
		return issuerCurrencyCode;
	}

	public void setIssuerCurrencyCode(String issuerCurrencyCode) {
		this.issuerCurrencyCode = issuerCurrencyCode;
	}

	public String getMccsic() {
		return mccsic;
	}

	public void setMccsic(String mccsic) {
		this.mccsic = mccsic;
	}

	public String getEntryMethod() {
		return entryMethod;
	}

	public void setEntryMethod(String entryMethod) {
		this.entryMethod = entryMethod;
	}

	public String getAdjustmentReasonCode() {
		return adjustmentReasonCode;
	}

	public void setAdjustmentReasonCode(String adjustmentReasonCode) {
		this.adjustmentReasonCode = adjustmentReasonCode;
	}

	public String getChargebackReferenceNumber() {
		return chargebackReferenceNumber;
	}

	public void setChargebackReferenceNumber(String chargebackReferenceNumber) {
		this.chargebackReferenceNumber = chargebackReferenceNumber;
	}

	public String getTransactionDispositionQualifier() {
		return transactionDispositionQualifier;
	}

	public void setTransactionDispositionQualifier(String transactionDispositionQualifier) {
		this.transactionDispositionQualifier = transactionDispositionQualifier;
	}

	public String getAcquirerReferenceNumber() {
		return acquirerReferenceNumber;
	}

	public void setAcquirerReferenceNumber(String acquirerReferenceNumber) {
		this.acquirerReferenceNumber = acquirerReferenceNumber;
	}

	public String getDivisionNumber() {
		return divisionNumber;
	}

	public void setDivisionNumber(String divisionNumber) {
		this.divisionNumber = divisionNumber;
	}

	public String getMerchantNumber() {
		return merchantNumber;
	}

	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
	}

	public String getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(String feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getEntryMode() {
		return entryMode;
	}

	public void setEntryMode(String entryMode) {
		this.entryMode = entryMode;
	}

	public char getAuthorizationSource() {
		return authorizationSource;
	}

	public void setAuthorizationSource(char authorizationSource) {
		this.authorizationSource = authorizationSource;
	}

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public char getFinancialInd() {
		return financialInd;
	}

	public void setFinancialInd(char financialInd) {
		this.financialInd = financialInd;
	}

	public char getDebitCreditInd() {
		return debitCreditInd;
	}

	public void setDebitCreditInd(char debitCreditInd) {
		this.debitCreditInd = debitCreditInd;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordFormat);
		sb.append(recordType);
		sb.append(reportDate);
		sb.append(transactionType);
		sb.append(recordNumber);
		sb.append(segmentNumber);
		sb.append(totalSegments);
		sb.append(disputedAmount);
		sb.append(originalTransactionAmount);
		sb.append(terminalId);
		sb.append(merchantDraftRetrieval); // transPOSFlag + terminalBatchNbr + terminalSeqNbr + 5 spaces
		sb.append(chargebackUsageCode);
		sb.append(adjustmentType);
		sb.append(originalTransDate);
		sb.append(draftActionCode);
		sb.append(draftActionCodeDate);
		sb.append(documentIndicator);
		sb.append(originalTransTime);
		sb.append(accountNumber);
		sb.append(chargebackActionCode);
		sb.append(chargebackActionDate);
		sb.append(issuerCurrencyCode);
		sb.append(mccsic);
		sb.append(entryMethod);
		sb.append(adjustmentReasonCode);
		sb.append(chargebackReferenceNumber);
		sb.append(transactionDispositionQualifier);
		sb.append(acquirerReferenceNumber);
		sb.append(divisionNumber);
		sb.append(merchantNumber);
		sb.append(feeAmount);
		sb.append(entryMode);
		sb.append(authorizationSource);
		sb.append(networkId);
		sb.append(financialInd);
		sb.append(debitCreditInd);
		sb.append(StringUtils.repeat(" ", 51));
		return sb.toString();
	}

	public static Segment1 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 260) {
			return null;
		}
		Segment1 o = new Segment1();
		o.setRecordFormat(s.substring(0, 2));
		o.setRecordType(s.substring(2, 4));
		o.setReportDate(s.substring(4, 10));
		o.setTransactionType(s.substring(10, 12));
		o.setRecordNumber(s.substring(12, 24));
		o.setSegmentNumber(s.charAt(24));
		o.setTotalSegments(s.charAt(25));
		o.setDisputedAmount(s.substring(26, 36));
		o.setOriginalTransactionAmount(s.substring(36, 46));
		o.setTerminalId(s.substring(46, 61));
		o.setMerchantDraftRetrieval(s.substring(61, 72));
		o.setTranPOSFlag(s.charAt(61));
		o.setTerminalBatchNbr(s.substring(62, 64));
		o.setTerminalSeqNbr(s.substring(64, 67));
		o.setChargebackUsageCode(s.charAt(72));
		o.setAdjustmentType(s.substring(73, 77));
		o.setOriginalTransDate(s.substring(77, 83));
		o.setDraftActionCode(s.charAt(83));
		o.setDraftActionCodeDate(s.substring(84, 90));
		o.setDocumentIndicator(s.charAt(90));
		o.setOriginalTransTime(s.substring(91, 97));
		o.setAccountNumber(s.substring(97, 116));
		o.setChargebackActionCode(s.substring(116, 120));
		o.setChargebackActionDate(s.substring(120, 126));
		o.setIssuerCurrencyCode(s.substring(126, 129));
		o.setMccsic(s.substring(129, 133));
		o.setEntryMethod(s.substring(133, 135));
		o.setAdjustmentReasonCode(s.substring(135, 140));
		o.setChargebackReferenceNumber(s.substring(140, 152));
		o.setTransactionDispositionQualifier(s.substring(152, 155));
		o.setAcquirerReferenceNumber(s.substring(155, 178));
		o.setDivisionNumber(s.substring(178, 181));
		o.setMerchantNumber(s.substring(181, 197));
		o.setFeeAmount(s.substring(197, 200));
		o.setEntryMode(s.substring(200, 202));
		o.setAuthorizationSource(s.charAt(202));
		o.setNetworkId(s.substring(203, 207));
		o.setFinancialInd(s.charAt(207));
		o.setDebitCreditInd(s.charAt(208));
		return o;
	}

}
