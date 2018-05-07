package com.heartlandportico.hps.odaf;

import org.apache.commons.lang.StringUtils;

public class FileTrailer90 {

	/**
	 * 1-10, Refer to Control Section – Record Type = '90'
	 */
	private ControlSection cs;

	/**
	 * 11-15
	 */
	private String numberOfBatches;

	/**
	 * 16-21
	 */
	private String physicalRecordCount;

	/**
	 * 22-27
	 */
	private String numberOfSales;

	/**
	 * 28-38
	 */
	private String amountOfSales;

	/**
	 * 39-44
	 */
	private String numberOfRefunds;

	/**
	 * 45-55
	 */
	private String amountOfRefunds;

	/* Filler, 56-60, AN 5, Space Filled. */

	/**
	 * 61-71
	 */
	private String netAmount;

	/**
	 * 72
	 */
	private char netAmountSign;

	/* Filler, 73-250, AN 178, Space Filled. */

	public ControlSection getCs() {
		return cs;
	}

	public void setCs(ControlSection cs) {
		this.cs = cs;
	}

	public String getNumberOfBatches() {
		return numberOfBatches;
	}

	public void setNumberOfBatches(String numberOfBatches) {
		this.numberOfBatches = numberOfBatches;
	}

	public String getPhysicalRecordCount() {
		return physicalRecordCount;
	}

	public void setPhysicalRecordCount(String physicalRecordCount) {
		this.physicalRecordCount = physicalRecordCount;
	}

	public String getNumberOfSales() {
		return numberOfSales;
	}

	public void setNumberOfSales(String numberOfSales) {
		this.numberOfSales = numberOfSales;
	}

	public String getAmountOfSales() {
		return amountOfSales;
	}

	public void setAmountOfSales(String amountOfSales) {
		this.amountOfSales = amountOfSales;
	}

	public String getNumberOfRefunds() {
		return numberOfRefunds;
	}

	public void setNumberOfRefunds(String numberOfRefunds) {
		this.numberOfRefunds = numberOfRefunds;
	}

	public String getAmountOfRefunds() {
		return amountOfRefunds;
	}

	public void setAmountOfRefunds(String amountOfRefunds) {
		this.amountOfRefunds = amountOfRefunds;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public char getNetAmountSign() {
		return netAmountSign;
	}

	public void setNetAmountSign(char netAmountSign) {
		this.netAmountSign = netAmountSign;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(cs.toString());
		sb.append(numberOfBatches);
		sb.append(physicalRecordCount);
		sb.append(numberOfSales);
		sb.append(amountOfSales);
		sb.append(numberOfRefunds);
		sb.append(amountOfRefunds);
		sb.append(StringUtils.repeat(" ", 5));
		sb.append(netAmount);
		sb.append(netAmountSign);
		sb.append(StringUtils.repeat(" ", 178));
		return sb.toString();
	}

	public static FileTrailer90 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 250) {
			return null;
		}
		FileTrailer90 o = new FileTrailer90();
		o.setCs(ControlSection.fromString(s.substring(0, 10)));
		o.setNumberOfBatches(s.substring(10, 15));
		o.setPhysicalRecordCount(s.substring(15, 21));
		o.setNumberOfSales(s.substring(21, 27));
		o.setAmountOfSales(s.substring(27, 38));
		o.setNumberOfRefunds(s.substring(38, 44));
		o.setAmountOfRefunds(s.substring(44, 55));
		o.setNetAmount(s.substring(60, 71));
		o.setNetAmountSign(s.charAt(71));
		return o;
	}

}
