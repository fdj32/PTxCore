package com.heartlandportico.hps.odaf;

import org.apache.commons.lang.StringUtils;

public class Reconciliation73 {

	/**
	 * 1-10, Refer to Control Section – Record Type = '73'
	 */
	private ControlSection cs;

	/**
	 * 11-21
	 */
	private String visaSalesAmount;

	/**
	 * 22
	 */
	private char visaAmountSign;

	/**
	 * 23-33
	 */
	private String mastercardSalesAmount;

	/**
	 * 34
	 */
	private char mastercardAmountSign;

	/**
	 * 35-45
	 */
	private String discoverSalesAmount;

	/**
	 * 46
	 */
	private char discoverAmountSign;

	/**
	 * 47
	 */
	private char discoverSettlementSupportInd;

	/**
	 * 48-58
	 */
	private String amexSalesAmount;

	/**
	 * 59
	 */
	private char amexAmountSign;

	/**
	 * 60
	 */
	private char amexSettlementSupportInd;

	/**
	 * 61-71
	 */
	private String wexSalesAmount;

	/**
	 * 72
	 */
	private char wexAmountSign;

	/**
	 * 73-83
	 */
	private String voyagerSalesAmount;

	/**
	 * 84
	 */
	private char voyagerAmountSign;

	/**
	 * 85-95
	 */
	private String pinDebitSalesAmount;

	/**
	 * 96
	 */
	private char pinDebitAmountSign;

	/**
	 * 97-107
	 */
	private String dinersSalesAmount;

	/**
	 * 108
	 */
	private char dinersAmountSign;

	/**
	 * 109
	 */
	private char dinersSettlementSupportInd;

	/**
	 * 110-120
	 */
	private String reserveSalesAmount1;

	/**
	 * 121
	 */
	private char reserveSalesAmountSign1;

	/**
	 * 122-132
	 */
	private String reserveSalesAmount2;

	/**
	 * 133
	 */
	private char reserveSalesAmountSign2;

	/**
	 * 134-144
	 */
	private String reserveSalesAmount3;

	/**
	 * 145
	 */
	private char reserveSalesAmountSign3;

	/* Filler, 146-250, AN 105, Space Filled. */

	public String getVisaSalesAmount() {
		return visaSalesAmount;
	}

	public void setVisaSalesAmount(String visaSalesAmount) {
		this.visaSalesAmount = visaSalesAmount;
	}

	public char getVisaAmountSign() {
		return visaAmountSign;
	}

	public void setVisaAmountSign(char visaAmountSign) {
		this.visaAmountSign = visaAmountSign;
	}

	public String getMastercardSalesAmount() {
		return mastercardSalesAmount;
	}

	public void setMastercardSalesAmount(String mastercardSalesAmount) {
		this.mastercardSalesAmount = mastercardSalesAmount;
	}

	public char getMastercardAmountSign() {
		return mastercardAmountSign;
	}

	public void setMastercardAmountSign(char mastercardAmountSign) {
		this.mastercardAmountSign = mastercardAmountSign;
	}

	public String getDiscoverSalesAmount() {
		return discoverSalesAmount;
	}

	public void setDiscoverSalesAmount(String discoverSalesAmount) {
		this.discoverSalesAmount = discoverSalesAmount;
	}

	public char getDiscoverAmountSign() {
		return discoverAmountSign;
	}

	public void setDiscoverAmountSign(char discoverAmountSign) {
		this.discoverAmountSign = discoverAmountSign;
	}

	public char getDiscoverSettlementSupportInd() {
		return discoverSettlementSupportInd;
	}

	public void setDiscoverSettlementSupportInd(char discoverSettlementSupportInd) {
		this.discoverSettlementSupportInd = discoverSettlementSupportInd;
	}

	public String getAmexSalesAmount() {
		return amexSalesAmount;
	}

	public void setAmexSalesAmount(String amexSalesAmount) {
		this.amexSalesAmount = amexSalesAmount;
	}

	public char getAmexAmountSign() {
		return amexAmountSign;
	}

	public void setAmexAmountSign(char amexAmountSign) {
		this.amexAmountSign = amexAmountSign;
	}

	public char getAmexSettlementSupportInd() {
		return amexSettlementSupportInd;
	}

	public void setAmexSettlementSupportInd(char amexSettlementSupportInd) {
		this.amexSettlementSupportInd = amexSettlementSupportInd;
	}

	public String getWexSalesAmount() {
		return wexSalesAmount;
	}

	public void setWexSalesAmount(String wexSalesAmount) {
		this.wexSalesAmount = wexSalesAmount;
	}

	public char getWexAmountSign() {
		return wexAmountSign;
	}

	public void setWexAmountSign(char wexAmountSign) {
		this.wexAmountSign = wexAmountSign;
	}

	public String getVoyagerSalesAmount() {
		return voyagerSalesAmount;
	}

	public void setVoyagerSalesAmount(String voyagerSalesAmount) {
		this.voyagerSalesAmount = voyagerSalesAmount;
	}

	public char getVoyagerAmountSign() {
		return voyagerAmountSign;
	}

	public void setVoyagerAmountSign(char voyagerAmountSign) {
		this.voyagerAmountSign = voyagerAmountSign;
	}

	public String getPinDebitSalesAmount() {
		return pinDebitSalesAmount;
	}

	public void setPinDebitSalesAmount(String pinDebitSalesAmount) {
		this.pinDebitSalesAmount = pinDebitSalesAmount;
	}

	public char getPinDebitAmountSign() {
		return pinDebitAmountSign;
	}

	public void setPinDebitAmountSign(char pinDebitAmountSign) {
		this.pinDebitAmountSign = pinDebitAmountSign;
	}

	public String getDinersSalesAmount() {
		return dinersSalesAmount;
	}

	public void setDinersSalesAmount(String dinersSalesAmount) {
		this.dinersSalesAmount = dinersSalesAmount;
	}

	public char getDinersAmountSign() {
		return dinersAmountSign;
	}

	public void setDinersAmountSign(char dinersAmountSign) {
		this.dinersAmountSign = dinersAmountSign;
	}

	public char getDinersSettlementSupportInd() {
		return dinersSettlementSupportInd;
	}

	public void setDinersSettlementSupportInd(char dinersSettlementSupportInd) {
		this.dinersSettlementSupportInd = dinersSettlementSupportInd;
	}

	public String getReserveSalesAmount1() {
		return reserveSalesAmount1;
	}

	public void setReserveSalesAmount1(String reserveSalesAmount1) {
		this.reserveSalesAmount1 = reserveSalesAmount1;
	}

	public char getReserveSalesAmountSign1() {
		return reserveSalesAmountSign1;
	}

	public void setReserveSalesAmountSign1(char reserveSalesAmountSign1) {
		this.reserveSalesAmountSign1 = reserveSalesAmountSign1;
	}

	public String getReserveSalesAmount2() {
		return reserveSalesAmount2;
	}

	public void setReserveSalesAmount2(String reserveSalesAmount2) {
		this.reserveSalesAmount2 = reserveSalesAmount2;
	}

	public char getReserveSalesAmountSign2() {
		return reserveSalesAmountSign2;
	}

	public void setReserveSalesAmountSign2(char reserveSalesAmountSign2) {
		this.reserveSalesAmountSign2 = reserveSalesAmountSign2;
	}

	public String getReserveSalesAmount3() {
		return reserveSalesAmount3;
	}

	public void setReserveSalesAmount3(String reserveSalesAmount3) {
		this.reserveSalesAmount3 = reserveSalesAmount3;
	}

	public char getReserveSalesAmountSign3() {
		return reserveSalesAmountSign3;
	}

	public void setReserveSalesAmountSign3(char reserveSalesAmountSign3) {
		this.reserveSalesAmountSign3 = reserveSalesAmountSign3;
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
		sb.append(visaSalesAmount);
		sb.append(visaAmountSign);
		sb.append(mastercardSalesAmount);
		sb.append(mastercardAmountSign);
		sb.append(discoverSalesAmount);
		sb.append(discoverAmountSign);
		sb.append(discoverSettlementSupportInd);
		sb.append(amexSalesAmount);
		sb.append(amexAmountSign);
		sb.append(amexSettlementSupportInd);
		sb.append(wexSalesAmount);
		sb.append(wexAmountSign);
		sb.append(voyagerSalesAmount);
		sb.append(voyagerAmountSign);
		sb.append(pinDebitSalesAmount);
		sb.append(pinDebitAmountSign);
		sb.append(dinersSalesAmount);
		sb.append(dinersAmountSign);
		sb.append(dinersSettlementSupportInd);
		sb.append(reserveSalesAmount1);
		sb.append(reserveSalesAmountSign1);
		sb.append(reserveSalesAmount2);
		sb.append(reserveSalesAmountSign2);
		sb.append(reserveSalesAmount3);
		sb.append(reserveSalesAmountSign3);
		sb.append(StringUtils.repeat(" ", 105));
		return sb.toString();
	}

	public static Reconciliation73 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 250) {
			return null;
		}
		Reconciliation73 o = new Reconciliation73();
		o.setCs(ControlSection.fromString(s.substring(0, 10)));
		o.setVisaSalesAmount(s.substring(10, 21));
		o.setVisaAmountSign(s.charAt(21));
		o.setMastercardSalesAmount(s.substring(22, 33));
		o.setMastercardAmountSign(s.charAt(33));
		o.setDiscoverSalesAmount(s.substring(34, 45));
		o.setDiscoverAmountSign(s.charAt(45));
		o.setDiscoverSettlementSupportInd(s.charAt(46));
		o.setAmexSalesAmount(s.substring(47, 58));
		o.setAmexAmountSign(s.charAt(58));
		o.setAmexSettlementSupportInd(s.charAt(59));
		o.setWexSalesAmount(s.substring(60, 71));
		o.setWexAmountSign(s.charAt(71));
		o.setVoyagerSalesAmount(s.substring(72, 83));
		o.setVoyagerAmountSign(s.charAt(83));
		o.setPinDebitSalesAmount(s.substring(84, 95));
		o.setPinDebitAmountSign(s.charAt(95));
		o.setDinersSalesAmount(s.substring(96, 107));
		o.setDinersAmountSign(s.charAt(107));
		o.setDiscoverSettlementSupportInd(s.charAt(108));
		o.setReserveSalesAmount1(s.substring(109, 120));
		o.setReserveSalesAmountSign1(s.charAt(120));
		o.setReserveSalesAmount2(s.substring(121, 132));
		o.setReserveSalesAmountSign2(s.charAt(132));
		o.setReserveSalesAmount2(s.substring(133, 144));
		o.setReserveSalesAmountSign2(s.charAt(144));
		return o;
	}

}
