package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class MerchantFundedBatchByCardType39 {

	/**
	 * 1-2, '39'
	 */
	private String recordType;

	/**
	 * 3-8, Sequential number of the record within file. Incremented by 1 for each
	 * record.
	 */
	private String recordSequenceNumber;

	/**
	 * 9-24, Unique ID internally assigned to each Batch
	 */
	private String uniqueBatchId;

	/**
	 * 25-36
	 */
	private String batchId;

	/**
	 * 37-48
	 */
	private String terminalId;

	/**
	 * 49-52
	 */
	private String terminalNumber;

	/**
	 * 53-63, Total Visa Net Sales Amount
	 */
	private String visaAmount;

	/**
	 * 64, Total Visa Net Sales Amount Sign
	 */
	private char visaSign;

	/**
	 * 65-75, Total MasterCard Net Sales Amount
	 */
	private String mastercardAmount;

	/**
	 * 76, Total MasterCard Net Sales Amount Sign
	 */
	private char mastercardSign;

	/**
	 * 77-87, Total AMEX Net Sales Amount
	 */
	private String amexAmount;

	/**
	 * 88, Total AMEX Net Sales Amount Sign
	 */
	private char amexSign;

	/**
	 * 89-99, Total Diners Net Sales Amount
	 */
	private String dinersAmount;

	/**
	 * 100, Total Diners Net Sales Amount Sign
	 */
	private char dinersSign;

	/**
	 * 101-111, Total JCB Net Sales Amount
	 */
	private String jcbAmount;

	/**
	 * 112, Total JCB Net Sales Amount Sign
	 */
	private char jcbSign;

	/**
	 * 113-123, Total Discover Net Sales Amount
	 */
	private String discoverAmount;

	/**
	 * 124, Total Discover Net Sales Amount Sign
	 */
	private char discoverSign;

	/**
	 * 125-135, Total Debit Net Sales Amount
	 */
	private String debitAmount;

	/**
	 * 136, Total Debit Net Sales Amount Sign
	 */
	private char debitSign;

	/**
	 * 137-147, Total EBT Net Sales Amount
	 */
	private String ebtAmount;

	/**
	 * 148, Total EBT Net Sales Amount Sign
	 */
	private char ebtSign;

	/**
	 * 149-159, Total GSBN Net Sales Amount
	 */
	private String gsbnAmount;

	/**
	 * 160, Total GSBN Net Sales Amount Sign
	 */
	private char gsbnSign;

	/* Filler, 161-550, AN 390, Space Filled. */

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

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getTerminalNumber() {
		return terminalNumber;
	}

	public void setTerminalNumber(String terminalNumber) {
		this.terminalNumber = terminalNumber;
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
		sb.append(uniqueBatchId);
		sb.append(batchId);
		sb.append(terminalId);
		sb.append(terminalNumber);
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
		sb.append(StringUtils.repeat(" ", 390));
		return sb.toString();
	}

	public static MerchantFundedBatchByCardType39 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		MerchantFundedBatchByCardType39 o = new MerchantFundedBatchByCardType39();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setUniqueBatchId(s.substring(8, 24));
		o.setBatchId(s.substring(24, 36));
		o.setTerminalId(s.substring(36, 48));
		o.setTerminalNumber(s.substring(48, 52));
		o.setVisaAmount(s.substring(52, 63));
		o.setVisaSign(s.charAt(63));
		o.setMastercardAmount(s.substring(64, 75));
		o.setMastercardSign(s.charAt(75));
		o.setAmexAmount(s.substring(76, 87));
		o.setAmexSign(s.charAt(87));
		o.setDinersAmount(s.substring(88, 99));
		o.setDinersSign(s.charAt(99));
		o.setJcbAmount(s.substring(100, 111));
		o.setJcbSign(s.charAt(111));
		o.setDiscoverAmount(s.substring(112, 123));
		o.setDiscoverSign(s.charAt(123));
		o.setDebitAmount(s.substring(124, 135));
		o.setEbtAmount(s.substring(136, 147));
		o.setEbtSign(s.charAt(147));
		o.setGsbnAmount(s.substring(148, 159));
		o.setGsbnSign(s.charAt(159));
		return o;
	}

}
