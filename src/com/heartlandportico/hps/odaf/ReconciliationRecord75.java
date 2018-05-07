package com.heartlandportico.hps.odaf;

import org.apache.commons.lang.StringUtils;

public class ReconciliationRecord75 {

	/**
	 * 1-10, Refer to Control Section – Record Type = '75'
	 */
	private ControlSection cs;

	/**
	 * 11-21
	 */
	private String chargebackAmount;

	/**
	 * 22
	 */
	private char chargebackSign;

	/**
	 * 23-33
	 */
	private String adjustmentAmount;

	/**
	 * 34
	 */
	private char adjustmentSign;

	/**
	 * 35-45
	 */
	private String feeAmount;

	/**
	 * 46
	 */
	private char feeSign;

	/**
	 * 47-57
	 */
	private String netDepositAmount;

	/**
	 * 58
	 */
	private char netDepositSign;

	/* Filler, 59-250, AN 192, Space Filled. */

	public ControlSection getCs() {
		return cs;
	}

	public void setCs(ControlSection cs) {
		this.cs = cs;
	}

	public String getChargebackAmount() {
		return chargebackAmount;
	}

	public void setChargebackAmount(String chargebackAmount) {
		this.chargebackAmount = chargebackAmount;
	}

	public char getChargebackSign() {
		return chargebackSign;
	}

	public void setChargebackSign(char chargebackSign) {
		this.chargebackSign = chargebackSign;
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

	public String getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(String feeAmount) {
		this.feeAmount = feeAmount;
	}

	public char getFeeSign() {
		return feeSign;
	}

	public void setFeeSign(char feeSign) {
		this.feeSign = feeSign;
	}

	public String getNetDepositAmount() {
		return netDepositAmount;
	}

	public void setNetDepositAmount(String netDepositAmount) {
		this.netDepositAmount = netDepositAmount;
	}

	public char getNetDepositSign() {
		return netDepositSign;
	}

	public void setNetDepositSign(char netDepositSign) {
		this.netDepositSign = netDepositSign;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(cs.toString());
		sb.append(chargebackAmount);
		sb.append(chargebackSign);
		sb.append(adjustmentAmount);
		sb.append(adjustmentSign);
		sb.append(feeAmount);
		sb.append(feeSign);
		sb.append(netDepositAmount);
		sb.append(netDepositSign);
		sb.append(StringUtils.repeat(" ", 192));
		return sb.toString();
	}

	public static ReconciliationRecord75 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 250) {
			return null;
		}
		ReconciliationRecord75 o = new ReconciliationRecord75();
		o.setCs(ControlSection.fromString(s.substring(0, 10)));
		o.setChargebackAmount(s.substring(10, 21));
		o.setChargebackSign(s.charAt(21));
		o.setAdjustmentAmount(s.substring(22, 33));
		o.setAdjustmentSign(s.charAt(33));
		o.setFeeAmount(s.substring(34, 45));
		o.setFeeSign(s.charAt(45));
		o.setNetDepositAmount(s.substring(46, 57));
		o.setNetDepositSign(s.charAt(57));
		return o;
	}

}
