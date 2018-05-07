package com.heartlandportico.hps.odaf;

import org.apache.commons.lang.StringUtils;

public class CreditDetail51 {

	/**
	 * 1-10, Refer to Control Section – Record Type = '51'
	 */
	private ControlSection cs;

	/**
	 * 11-14, Transaction date - MMDD
	 */
	private String transactionDate;

	/**
	 * 15-20, Transaction time - HHMMSS
	 */
	private String transactionTime;

	/**
	 * 21-26, Terminal Sequence Number
	 */
	private String transactionSequenceNumber;

	/**
	 * 27-28, ‘05’ – Sale, ‘06’ - Return
	 */
	private String transactionType;

	/**
	 * 29, ‘3’ - Completed, ‘5’ - Suspended
	 */
	private char transactionDisposition;

	/**
	 * 30-32, Auth response code
	 */
	private String responseCode;

	/**
	 * 33-41, Transaction amount – leading zeroes
	 */
	private String transactionAmount;

	/**
	 * 42-51, Auth code – left justified
	 */
	private String authorizationCode;

	/**
	 * 52-53, POS Entry Mode
	 */
	private String posEntryCode;

	/**
	 * 54-72, Masked – 1st 6 & last 4
	 */
	private String accountNumber;

	/* Filler, 73-75, AN 3, Space Filled. */

	/**
	 * 76-79, Set to zeroes
	 */
	private String expirationDate;

	/* Filler, 80-250, AN 171, Space Filled. */

	public ControlSection getCs() {
		return cs;
	}

	public void setCs(ControlSection cs) {
		this.cs = cs;
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

	public String getTransactionSequenceNumber() {
		return transactionSequenceNumber;
	}

	public void setTransactionSequenceNumber(String transactionSequenceNumber) {
		this.transactionSequenceNumber = transactionSequenceNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public char getTransactionDisposition() {
		return transactionDisposition;
	}

	public void setTransactionDisposition(char transactionDisposition) {
		this.transactionDisposition = transactionDisposition;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
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

	public String getPosEntryCode() {
		return posEntryCode;
	}

	public void setPosEntryCode(String posEntryCode) {
		this.posEntryCode = posEntryCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(cs.toString());
		sb.append(transactionDate);
		sb.append(transactionTime);
		sb.append(transactionSequenceNumber);
		sb.append(transactionType);
		sb.append(transactionDisposition);
		sb.append(responseCode);
		sb.append(transactionAmount);
		sb.append(authorizationCode);
		sb.append(posEntryCode);
		sb.append(accountNumber);
		sb.append(StringUtils.repeat(" ", 3));
		sb.append(expirationDate);
		sb.append(StringUtils.repeat(" ", 171));
		return sb.toString();
	}

	public static CreditDetail51 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 250) {
			return null;
		}
		CreditDetail51 o = new CreditDetail51();
		o.setCs(ControlSection.fromString(s.substring(0, 10)));
		o.setTransactionDate(s.substring(10, 14));
		o.setTransactionTime(s.substring(14, 20));
		o.setTransactionSequenceNumber(s.substring(20, 26));
		o.setTransactionType(s.substring(26, 28));
		o.setTransactionDisposition(s.charAt(28));
		o.setResponseCode(s.substring(29, 32));
		o.setTransactionAmount(s.substring(32, 41));
		o.setAuthorizationCode(s.substring(41, 51));
		o.setPosEntryCode(s.substring(51, 53));
		o.setAccountNumber(s.substring(53, 72));
		o.setExpirationDate(s.substring(75, 79));
		return o;
	}

}
