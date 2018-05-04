package com.heartlandportico.hps.odaf;

import org.apache.commons.lang.StringUtils;

public class CreditDetailTransactionRecord53 {

	/**
	 * 1-10, Refer to Control Section – Record Type = '53'
	 */
	private ControlSection cs;

	/**
	 * 11-14
	 */
	private String actualInterchangeCode;

	/* Filler, 15-19, AN 5, Space Filled. */

	/**
	 * 20-22
	 */
	private String currencyCode;

	/* Filler, 23, AN 1, Space Filled. */

	/**
	 * 24-25
	 */
	private String posConditionCode;

	/**
	 * 26
	 */
	private char authorizationCharateristicsInd;

	/**
	 * 27-30
	 */
	private String anticipatedInterchangeCode;

	/**
	 * 31-37
	 */
	private String actualInterchangeAmount;

	/**
	 * 38, C – Credit, D - Debit
	 * 
	 */
	private char actualInterchangeSign;

	/**
	 * 39-46
	 */
	private String interchangeAdjustmentReasonCode;

	/**
	 * 47-53
	 */
	private String interchangeAdjustmentAmount;

	/**
	 * 54
	 */
	private char interchangeAdjustmentSign;

	/**
	 * 55
	 */
	private char avsResponseCode;

	/**
	 * 56, ‘0’ – No, ‘1’ - Yes
	 */
	private char networkResponseFields;

	//////////////////////////////////////////////////////////

	/**
	 * 57-71
	 */
	private String visaTransactionId;

	/**
	 * 72-75
	 */
	private String visaValidationCode;

	/**
	 * 76-77
	 */
	private String visaAuthResponseCode;

	//////////////////////////////////////////////////////////

	/**
	 * 57-65
	 */
	private String mcBandnetReferenceNumber;

	/**
	 * 66-69
	 */
	private String mcSettlementDate;

	/**
	 * 70-77
	 */
	private String mcFiller;

	//////////////////////////////////////////////////////////

	/**
	 * 78
	 */
	private char visaRealTimeClearingFlag;

	/* Filler, 79-250, AN 172, Space Filled. */

	public String getActualInterchangeCode() {
		return actualInterchangeCode;
	}

	public void setActualInterchangeCode(String actualInterchangeCode) {
		this.actualInterchangeCode = actualInterchangeCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getPosConditionCode() {
		return posConditionCode;
	}

	public void setPosConditionCode(String posConditionCode) {
		this.posConditionCode = posConditionCode;
	}

	public char getAuthorizationCharateristicsInd() {
		return authorizationCharateristicsInd;
	}

	public void setAuthorizationCharateristicsInd(char authorizationCharateristicsInd) {
		this.authorizationCharateristicsInd = authorizationCharateristicsInd;
	}

	public String getAnticipatedInterchangeCode() {
		return anticipatedInterchangeCode;
	}

	public void setAnticipatedInterchangeCode(String anticipatedInterchangeCode) {
		this.anticipatedInterchangeCode = anticipatedInterchangeCode;
	}

	public String getActualInterchangeAmount() {
		return actualInterchangeAmount;
	}

	public void setActualInterchangeAmount(String actualInterchangeAmount) {
		this.actualInterchangeAmount = actualInterchangeAmount;
	}

	public char getActualInterchangeSign() {
		return actualInterchangeSign;
	}

	public void setActualInterchangeSign(char actualInterchangeSign) {
		this.actualInterchangeSign = actualInterchangeSign;
	}

	public String getInterchangeAdjustmentReasonCode() {
		return interchangeAdjustmentReasonCode;
	}

	public void setInterchangeAdjustmentReasonCode(String interchangeAdjustmentReasonCode) {
		this.interchangeAdjustmentReasonCode = interchangeAdjustmentReasonCode;
	}

	public String getInterchangeAdjustmentAmount() {
		return interchangeAdjustmentAmount;
	}

	public void setInterchangeAdjustmentAmount(String interchangeAdjustmentAmount) {
		this.interchangeAdjustmentAmount = interchangeAdjustmentAmount;
	}

	public char getInterchangeAdjustmentSign() {
		return interchangeAdjustmentSign;
	}

	public void setInterchangeAdjustmentSign(char interchangeAdjustmentSign) {
		this.interchangeAdjustmentSign = interchangeAdjustmentSign;
	}

	public char getAvsResponseCode() {
		return avsResponseCode;
	}

	public void setAvsResponseCode(char avsResponseCode) {
		this.avsResponseCode = avsResponseCode;
	}

	public char getNetworkResponseFields() {
		return networkResponseFields;
	}

	public void setNetworkResponseFields(char networkResponseFields) {
		this.networkResponseFields = networkResponseFields;
	}

	public String getVisaTransactionId() {
		return visaTransactionId;
	}

	public void setVisaTransactionId(String visaTransactionId) {
		this.visaTransactionId = visaTransactionId;
	}

	public String getVisaValidationCode() {
		return visaValidationCode;
	}

	public void setVisaValidationCode(String visaValidationCode) {
		this.visaValidationCode = visaValidationCode;
	}

	public String getVisaAuthResponseCode() {
		return visaAuthResponseCode;
	}

	public void setVisaAuthResponseCode(String visaAuthResponseCode) {
		this.visaAuthResponseCode = visaAuthResponseCode;
	}

	public String getMcBandnetReferenceNumber() {
		return mcBandnetReferenceNumber;
	}

	public void setMcBandnetReferenceNumber(String mcBandnetReferenceNumber) {
		this.mcBandnetReferenceNumber = mcBandnetReferenceNumber;
	}

	public String getMcSettlementDate() {
		return mcSettlementDate;
	}

	public void setMcSettlementDate(String mcSettlementDate) {
		this.mcSettlementDate = mcSettlementDate;
	}

	public String getMcFiller() {
		return mcFiller;
	}

	public void setMcFiller(String mcFiller) {
		this.mcFiller = mcFiller;
	}

	public char getVisaRealTimeClearingFlag() {
		return visaRealTimeClearingFlag;
	}

	public void setVisaRealTimeClearingFlag(char visaRealTimeClearingFlag) {
		this.visaRealTimeClearingFlag = visaRealTimeClearingFlag;
	}

	public ControlSection getCs() {
		return cs;
	}

	public void setCs(ControlSection cs) {
		this.cs = cs;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(cs.toString());
		sb.append(actualInterchangeCode);
		sb.append(StringUtils.repeat(" ", 5));
		sb.append(currencyCode);
		sb.append(' ');
		sb.append(posConditionCode);
		sb.append(authorizationCharateristicsInd);
		sb.append(anticipatedInterchangeCode);
		sb.append(actualInterchangeAmount);
		sb.append(actualInterchangeSign);
		sb.append(interchangeAdjustmentReasonCode);
		sb.append(interchangeAdjustmentAmount);
		sb.append(interchangeAdjustmentSign);
		sb.append(avsResponseCode);
		sb.append(networkResponseFields);
		if (StringUtils.isNotEmpty(visaTransactionId) && StringUtils.isNotEmpty(visaValidationCode)
				&& StringUtils.isNotEmpty(visaAuthResponseCode)) {
			sb.append(visaTransactionId);
			sb.append(visaValidationCode);
			sb.append(visaAuthResponseCode);
		} else {
			sb.append(mcBandnetReferenceNumber);
			sb.append(mcSettlementDate);
			sb.append(mcFiller);
		}
		sb.append(visaRealTimeClearingFlag);
		sb.append(StringUtils.repeat(" ", 172));
		return sb.toString();
	}

	public static CreditDetailTransactionRecord53 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 250) {
			return null;
		}
		CreditDetailTransactionRecord53 o = new CreditDetailTransactionRecord53();
		o.setCs(ControlSection.fromString(s.substring(0, 10)));
		o.setActualInterchangeCode(s.substring(10, 14));
		o.setCurrencyCode(s.substring(19, 22));
		o.setPosConditionCode(s.substring(23, 25));
		o.setAuthorizationCharateristicsInd(s.charAt(25));
		o.setAnticipatedInterchangeCode(s.substring(26, 30));
		o.setActualInterchangeAmount(s.substring(30, 37));
		o.setActualInterchangeSign(s.charAt(37));
		o.setInterchangeAdjustmentReasonCode(s.substring(38, 46));
		o.setInterchangeAdjustmentAmount(s.substring(46, 53));
		o.setInterchangeAdjustmentSign(s.charAt(53));
		o.setAvsResponseCode(s.charAt(54));
		o.setNetworkResponseFields(s.charAt(55));
		boolean isVisa = true; // TODO : how to know it's visa or mc?
		if (isVisa) {
			o.setVisaTransactionId(s.substring(56, 71));
			o.setVisaValidationCode(s.substring(71, 75));
			o.setVisaAuthResponseCode(s.substring(75, 77));
		} else {
			o.setMcBandnetReferenceNumber(s.substring(56, 65));
			o.setMcSettlementDate(s.substring(65, 69));
			o.setMcFiller(s.substring(69, 77));
		}
		o.setVisaRealTimeClearingFlag(s.charAt(78));
		return o;
	}

}
