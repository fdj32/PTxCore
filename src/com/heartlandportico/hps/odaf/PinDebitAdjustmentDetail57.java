package com.heartlandportico.hps.odaf;

import org.apache.commons.lang.StringUtils;

public class PinDebitAdjustmentDetail57 {

	/**
	 * 1-10, Refer to Control Section – Record Type = '57'
	 */
	private ControlSection cs;

	/**
	 * 11-25
	 */
	private String merchantIdNumber;

	/**
	 * 25-44, Masked CardHolder Number
	 */
	private String account;

	/**
	 * 45-53, Leading zeroes. Implied 2 decimals
	 */
	private String adjustmentAmount;

	/**
	 * 54, Credit – C, Debit - D
	 * 
	 */
	private char adjustmentSign;

	/**
	 * 55-60, MMDDYY
	 */
	private String txnDate;

	/**
	 * 61-66, HHMMSS
	 */
	private String txnTime;

	/**
	 * 67-78
	 */
	private String terminalId;

	/**
	 * 79-84
	 */
	private String sequenceNumber;

	/**
	 * 85-88, Default to spaces
	 */
	private String sicmccCode;

	/**
	 * 89-138, Default to spaces
	 */
	private String adjustmentReason;

	/**
	 * 139-144, Default to spaces
	 */
	private String settlementDate;

	/**
	 * 145-150, Default to spaces
	 */
	private String networkId;

	/**
	 * 151-166, Default to spaces
	 */
	private String locatorNumber;

	/**
	 * 167
	 * <ul>
	 * <li>‘D’ - Signature Debit</li>
	 * <li>‘C’ - Signature Credit</li>
	 * <li>‘R’ – Deferred Debit</li>
	 * <li>‘P’ - Prepaid</li>
	 * <li>‘H’ – Charge(treat as Credit)</li>
	 * <li>‘1’ – EBT</li>
	 * <li>‘2’ – PIN Debit</li>
	 * <li>‘3’ – PINless Debit</li>
	 * <li>‘4’ – Fee Adjustment</li>
	 * <li>Default to spaces</li>
	 * </ul>
	 * The definition of values on this columns is subject to change if the card
	 * brands define new card product categories.
	 */
	private char adjustmentCategory;

	/**
	 * 168-170, Default to spaces
	 */
	private String adjustmentType;

	/* Filler, 171-250, AN 80, Space Filled. */

	public String getMerchantIdNumber() {
		return merchantIdNumber;
	}

	public void setMerchantIdNumber(String merchantIdNumber) {
		this.merchantIdNumber = merchantIdNumber;
	}

	public ControlSection getCs() {
		return cs;
	}

	public void setCs(ControlSection cs) {
		this.cs = cs;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAdjustmentAmount() {
		return adjustmentAmount;
	}

	public void setAdjustmentAmount(String adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}

	public char getAdjustmentSign() {
		return adjustmentSign;
	}

	public void setAdjustmentSign(char adjustmentSign) {
		this.adjustmentSign = adjustmentSign;
	}

	public String getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getSicmccCode() {
		return sicmccCode;
	}

	public void setSicmccCode(String sicmccCode) {
		this.sicmccCode = sicmccCode;
	}

	public String getAdjustmentReason() {
		return adjustmentReason;
	}

	public void setAdjustmentReason(String adjustmentReason) {
		this.adjustmentReason = adjustmentReason;
	}

	public String getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getLocatorNumber() {
		return locatorNumber;
	}

	public void setLocatorNumber(String locatorNumber) {
		this.locatorNumber = locatorNumber;
	}

	public char getAdjustmentCategory() {
		return adjustmentCategory;
	}

	public void setAdjustmentCategory(char adjustmentCategory) {
		this.adjustmentCategory = adjustmentCategory;
	}

	public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(cs.toString());
		sb.append(merchantIdNumber);
		sb.append(account);
		sb.append(adjustmentAmount);
		sb.append(adjustmentSign);
		sb.append(txnDate);
		sb.append(txnTime);
		sb.append(terminalId);
		sb.append(sequenceNumber);
		sb.append(sicmccCode);
		sb.append(adjustmentReason);
		sb.append(settlementDate);
		sb.append(networkId);
		sb.append(locatorNumber);
		sb.append(adjustmentCategory);
		sb.append(adjustmentType);
		sb.append(StringUtils.repeat(" ", 80));
		return sb.toString();
	}

	public static PinDebitAdjustmentDetail57 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 250) {
			return null;
		}
		PinDebitAdjustmentDetail57 o = new PinDebitAdjustmentDetail57();
		o.setCs(ControlSection.fromString(s.substring(0, 10)));
		o.setMerchantIdNumber(s.substring(10, 25));
		o.setAccount(s.substring(25, 44));
		o.setAdjustmentAmount(s.substring(44, 53));
		o.setAdjustmentSign(s.charAt(53));
		o.setTxnDate(s.substring(54, 60));
		o.setTxnTime(s.substring(60, 66));
		o.setTerminalId(s.substring(66, 78));
		o.setSequenceNumber(s.substring(78, 84));
		o.setSicmccCode(s.substring(84, 88));
		o.setAdjustmentReason(s.substring(88, 138));
		o.setSettlementDate(s.substring(138, 144));
		o.setNetworkId(s.substring(144, 150));
		o.setLocatorNumber(s.substring(150, 166));
		o.setAdjustmentCategory(s.charAt(166));
		o.setAdjustmentType(s.substring(167, 170));
		return o;
	}

}
