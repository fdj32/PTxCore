package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class ChainTotalFundingByCardType71 {

	/**
	 * 1-2, '71'
	 */
	private String recordType;

	/**
	 * 3-8, Sequential number of the record within file. Incremented by 1 for each
	 * record.
	 */
	private String recordSequenceNumber;

	/**
	 * 8-14, YYMMDD
	 */
	private String fundingDate;

	/**
	 * 15-25, Total Visa Net Sales Amount
	 */
	private String visaAmount;

	/**
	 * 26, Total Visa Net Sales Amount Sign
	 */
	private char visaSign;

	/**
	 * 27-37, Total MasterCard Net Sales Amount
	 */
	private String mastercardAmount;

	/**
	 * 38, Total MasterCard Net Sales Amount Sign
	 */
	private char mastercardSign;

	/**
	 * 39-49, Total AMEX Net Sales Amount
	 */
	private String amexAmount;

	/**
	 * 50, Total AMEX Net Sales Amount Sign
	 */
	private char amexSign;

	/**
	 * 51-61, Total Diners Net Sales Amount
	 */
	private String dinersAmount;

	/**
	 * 62, Total Diners Net Sales Amount Sign
	 */
	private char dinersSign;

	/**
	 * 63-73, Total JCB Net Sales Amount
	 */
	private String jcbAmount;

	/**
	 * 74, Total JCB Net Sales Amount Sign
	 */
	private char jcbSign;

	/**
	 * 75-85, Total Discover Net Sales Amount
	 */
	private String discoverAmount;

	/**
	 * 86, Total Discover Net Sales Amount Sign
	 */
	private char discoverSign;

	/**
	 * 87-97, Total Debit Net Sales Amount
	 */
	private String debitAmount;

	/**
	 * 98, Total Debit Net Sales Amount Sign
	 */
	private char debitSign;

	/**
	 * 99-109, Total EBT Net Sales Amount
	 */
	private String ebtAmount;

	/**
	 * 110, Total EBT Net Sales Amount Sign
	 */
	private char ebtSign;

	/**
	 * 111-121, Total GSBN Net Sales Amount
	 */
	private String gsbnAmount;

	/**
	 * 122, Total GSBN Net Sales Amount Sign
	 */
	private char gsbnSign;

	/* Filler, 123-550, AN 428, Space Filled. */

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

	public String getFundingDate() {
		return fundingDate;
	}

	public void setFundingDate(String fundingDate) {
		this.fundingDate = fundingDate;
	}

	public String getVisaAmount() {
		return visaAmount;
	}

	public void setVisaAmount(String visaAmount) {
		this.visaAmount = visaAmount;
	}

	public char getVisaSign() {
		return visaSign;
	}

	public void setVisaSign(char visaSign) {
		this.visaSign = visaSign;
	}

	public String getMastercardAmount() {
		return mastercardAmount;
	}

	public void setMastercardAmount(String mastercardAmount) {
		this.mastercardAmount = mastercardAmount;
	}

	public char getMastercardSign() {
		return mastercardSign;
	}

	public void setMastercardSign(char mastercardSign) {
		this.mastercardSign = mastercardSign;
	}

	public String getAmexAmount() {
		return amexAmount;
	}

	public void setAmexAmount(String amexAmount) {
		this.amexAmount = amexAmount;
	}

	public char getAmexSign() {
		return amexSign;
	}

	public void setAmexSign(char amexSign) {
		this.amexSign = amexSign;
	}

	public String getDinersAmount() {
		return dinersAmount;
	}

	public void setDinersAmount(String dinersAmount) {
		this.dinersAmount = dinersAmount;
	}

	public char getDinersSign() {
		return dinersSign;
	}

	public void setDinersSign(char dinersSign) {
		this.dinersSign = dinersSign;
	}

	public String getJcbAmount() {
		return jcbAmount;
	}

	public void setJcbAmount(String jcbAmount) {
		this.jcbAmount = jcbAmount;
	}

	public char getJcbSign() {
		return jcbSign;
	}

	public void setJcbSign(char jcbSign) {
		this.jcbSign = jcbSign;
	}

	public String getDiscoverAmount() {
		return discoverAmount;
	}

	public void setDiscoverAmount(String discoverAmount) {
		this.discoverAmount = discoverAmount;
	}

	public char getDiscoverSign() {
		return discoverSign;
	}

	public void setDiscoverSign(char discoverSign) {
		this.discoverSign = discoverSign;
	}

	public String getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
	}

	public char getDebitSign() {
		return debitSign;
	}

	public void setDebitSign(char debitSign) {
		this.debitSign = debitSign;
	}

	public String getEbtAmount() {
		return ebtAmount;
	}

	public void setEbtAmount(String ebtAmount) {
		this.ebtAmount = ebtAmount;
	}

	public char getEbtSign() {
		return ebtSign;
	}

	public void setEbtSign(char ebtSign) {
		this.ebtSign = ebtSign;
	}

	public String getGsbnAmount() {
		return gsbnAmount;
	}

	public void setGsbnAmount(String gsbnAmount) {
		this.gsbnAmount = gsbnAmount;
	}

	public char getGsbnSign() {
		return gsbnSign;
	}

	public void setGsbnSign(char gsbnSign) {
		this.gsbnSign = gsbnSign;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(recordSequenceNumber);
		sb.append(fundingDate);
		sb.append(visaAmount);
		sb.append(visaSign);
		sb.append(mastercardAmount);
		sb.append(mastercardSign);
		sb.append(amexAmount);
		sb.append(amexSign);
		sb.append(dinersAmount);
		sb.append(dinersSign);
		sb.append(jcbAmount);
		sb.append(jcbSign);
		sb.append(discoverAmount);
		sb.append(discoverSign);
		sb.append(debitAmount);
		sb.append(debitSign);
		sb.append(ebtAmount);
		sb.append(ebtSign);
		sb.append(gsbnAmount);
		sb.append(gsbnSign);
		sb.append(StringUtils.repeat(" ", 428));
		return sb.toString();
	}

	public static ChainTotalFundingByCardType71 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		ChainTotalFundingByCardType71 o = new ChainTotalFundingByCardType71();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setFundingDate(s.substring(8, 14));
		o.setVisaAmount(s.substring(14, 25));
		o.setVisaSign(s.charAt(25));
		o.setMastercardAmount(s.substring(26, 37));
		o.setMastercardSign(s.charAt(37));
		o.setAmexAmount(s.substring(38, 49));
		o.setAmexSign(s.charAt(49));
		o.setDinersAmount(s.substring(50, 61));
		o.setDinersSign(s.charAt(61));
		o.setJcbAmount(s.substring(62, 73));
		o.setJcbSign(s.charAt(73));
		o.setDiscoverAmount(s.substring(74, 85));
		o.setDiscoverSign(s.charAt(85));
		o.setDebitAmount(s.substring(86, 97));
		o.setDebitSign(s.charAt(97));
		o.setEbtAmount(s.substring(98, 109));
		o.setEbtSign(s.charAt(109));
		o.setGsbnAmount(s.substring(110, 121));
		o.setGsbnSign(s.charAt(121));
		return o;
	}

}
