package com.heartlandportico.hps.odaf;

import org.apache.commons.lang.StringUtils;

public class Reconciliation76 {

	/**
	 * 1-10, Refer to Control Section – Record Type = '76'
	 */
	private ControlSection cs;

	private String[] cardholderInstitutionId = new String[9];

	private String[] interchangeFeeAmount = new String[9];

	private char[] interchangeFeeSign = new char[9];

	private String[] processingFeeAmount = new String[9];

	private char[] processingFeeSign = new char[9];

	/* Filler, 225-250, AN 26, Space Filled. */

	public String[] getCardholderInstitutionId() {
		return cardholderInstitutionId;
	}

	public void setCardholderInstitutionId(String[] cardholderInstitutionId) {
		this.cardholderInstitutionId = cardholderInstitutionId;
	}

	public String[] getInterchangeFeeAmount() {
		return interchangeFeeAmount;
	}

	public void setInterchangeFeeAmount(String[] interchangeFeeAmount) {
		this.interchangeFeeAmount = interchangeFeeAmount;
	}

	public char[] getInterchangeFeeSign() {
		return interchangeFeeSign;
	}

	public void setInterchangeFeeSign(char[] interchangeFeeSign) {
		this.interchangeFeeSign = interchangeFeeSign;
	}

	public String[] getProcessingFeeAmount() {
		return processingFeeAmount;
	}

	public void setProcessingFeeAmount(String[] processingFeeAmount) {
		this.processingFeeAmount = processingFeeAmount;
	}

	public char[] getProcessingFeeSign() {
		return processingFeeSign;
	}

	public void setProcessingFeeSign(char[] processingFeeSign) {
		this.processingFeeSign = processingFeeSign;
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
		for (int i = 0; i < 9; i++) {
			sb.append(cardholderInstitutionId[i]);
			sb.append(interchangeFeeAmount[i]);
			sb.append(interchangeFeeSign[i]);
			sb.append(processingFeeAmount[i]);
			sb.append(processingFeeSign[i]);
		}
		sb.append(StringUtils.repeat(" ", 26));
		return sb.toString();
	}

	public static Reconciliation76 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 250) {
			return null;
		}
		Reconciliation76 o = new Reconciliation76();
		o.setCs(ControlSection.fromString(s.substring(0, 10)));
		for (int i = 0; i < 9; i++) {
			o.getCardholderInstitutionId()[i] = s.substring(10 + 24 * i, 14 + 24 * i);
			o.getInterchangeFeeAmount()[i] = s.substring(14 + 24 * i, 23 + 24 * i);
			o.getInterchangeFeeSign()[i] = s.charAt(23 + 24 * i);
			o.getProcessingFeeAmount()[i] = s.substring(24 + 24 * i, 33 + 24 * i);
			o.getProcessingFeeSign()[i] = s.charAt(33 + 24 * i);
		}
		return o;
	}

}
