package com.heartlandportico.hps.fdf;

import org.apache.commons.lang.StringUtils;

public class ChainTotalFunding70 {

	/**
	 * 1-2, '70'
	 */
	private String recordType;

	/**
	 * 3-8, Sequential number of the record within file. Incremented by 1 for each
	 * record.
	 */
	private String recordSequenceNumber;

	/**
	 * 9-14, YYMMDD
	 */
	private String fundingDate;

	/**
	 * 15-25, Total Chain Net Sales Amount
	 */
	private String salesAmount;

	/**
	 * 26, Total Chain Net Sales Amount Sign
	 */
	private char salesSign;

	/**
	 * 27-37, Total Chain Net Fees Amount
	 */
	private String feesAmount;

	/**
	 * 38, Total Chain Net Fees Amount Sign
	 */
	private char feesSign;

	/**
	 * 39-49, Total Chain Net Returned Items Amount
	 */
	private String returnsAmount;

	/**
	 * 50, Total Chain Net Returned Items Amount Sign
	 */
	private char returnsSign;

	/**
	 * 51-61, Total Chain Net Chargebacks Amount
	 */
	private String chargebacksAmount;

	/**
	 * 62, Total Chain Net Chargebacks Amount Sign
	 */
	private char chargebacksSign;

	/**
	 * 63-73, Total Chain Net Adjustment Amount
	 */
	private String adjustmentsAmount;

	/**
	 * 74, Total Chain Net Adjustment Amount Sign
	 */
	private char adjustmentsSign;

	/**
	 * 75-85, Total Chain Net Deposit Amount
	 */
	private String depositAmount;

	/**
	 * 86, Total Chain Net Deposit Amount Sign
	 */
	private char depositSign;

	/* Filler, 87-550, AN 464, Space Filled. */

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

	public String getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(String salesAmount) {
		this.salesAmount = salesAmount;
	}

	public char getSalesSign() {
		return salesSign;
	}

	public void setSalesSign(char salesSign) {
		this.salesSign = salesSign;
	}

	public String getChargebacksAmount() {
		return chargebacksAmount;
	}

	public void setChargebacksAmount(String chargebacksAmount) {
		this.chargebacksAmount = chargebacksAmount;
	}

	public char getChargebacksSign() {
		return chargebacksSign;
	}

	public void setChargebacksSign(char chargebacksSign) {
		this.chargebacksSign = chargebacksSign;
	}

	public String getAdjustmentsAmount() {
		return adjustmentsAmount;
	}

	public void setAdjustmentsAmount(String adjustmentsAmount) {
		this.adjustmentsAmount = adjustmentsAmount;
	}

	public char getAdjustmentsSign() {
		return adjustmentsSign;
	}

	public void setAdjustmentsSign(char adjustmentsSign) {
		this.adjustmentsSign = adjustmentsSign;
	}

	public String getFeesAmount() {
		return feesAmount;
	}

	public void setFeesAmount(String feesAmount) {
		this.feesAmount = feesAmount;
	}

	public char getFeesSign() {
		return feesSign;
	}

	public void setFeesSign(char feesSign) {
		this.feesSign = feesSign;
	}

	public String getReturnsAmount() {
		return returnsAmount;
	}

	public void setReturnsAmount(String returnsAmount) {
		this.returnsAmount = returnsAmount;
	}

	public char getReturnsSign() {
		return returnsSign;
	}

	public void setReturnsSign(char returnsSign) {
		this.returnsSign = returnsSign;
	}

	public String getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}

	public char getDepositSign() {
		return depositSign;
	}

	public void setDepositSign(char depositSign) {
		this.depositSign = depositSign;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(recordSequenceNumber);
		sb.append(fundingDate);
		sb.append(salesAmount);
		sb.append(salesSign);
		sb.append(feesAmount);
		sb.append(feesSign);
		sb.append(returnsAmount);
		sb.append(returnsSign);
		sb.append(chargebacksAmount);
		sb.append(chargebacksSign);
		sb.append(adjustmentsAmount);
		sb.append(adjustmentsSign);
		sb.append(depositAmount);
		sb.append(depositSign);
		sb.append(StringUtils.repeat(" ", 464));
		return sb.toString();
	}

	public static ChainTotalFunding70 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 550) {
			return null;
		}
		ChainTotalFunding70 o = new ChainTotalFunding70();
		o.setRecordType(s.substring(0, 2));
		o.setRecordSequenceNumber(s.substring(2, 8));
		o.setFundingDate(s.substring(8, 14));
		o.setSalesAmount(s.substring(14, 25));
		o.setSalesSign(s.charAt(25));
		o.setFeesAmount(s.substring(26, 37));
		o.setFeesSign(s.charAt(37));
		o.setReturnsAmount(s.substring(38, 49));
		o.setReturnsSign(s.charAt(49));
		o.setChargebacksAmount(s.substring(50, 61));
		o.setChargebacksSign(s.charAt(61));
		o.setAdjustmentsAmount(s.substring(62, 73));
		o.setAdjustmentsSign(s.charAt(73));
		o.setDepositAmount(s.substring(74, 85));
		o.setDepositSign(s.charAt(85));
		return o;
	}

}
