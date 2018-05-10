package com.heartlandportico.hps.ddf;

import org.apache.commons.lang.StringUtils;

public class FileTrailer {

	/**
	 * 1-2
	 */
	private String recordFormat = "MH";

	/**
	 * 3-4
	 */
	private String recordType = "CT";

	/**
	 * 5-14, Count of all detail records
	 */
	private String recordCount;

	/**
	 * 15-26, Sum of actual amounts in the activity records. Implied 2 decimal
	 * places.
	 */
	private String totalAmount;

	/* Filler, 27-260, AN 234, Reserved Spaces. */

	public String getRecordFormat() {
		return recordFormat;
	}

	public void setRecordFormat(String recordFormat) {
		this.recordFormat = recordFormat;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordFormat);
		sb.append(recordType);
		sb.append(recordCount);
		sb.append(totalAmount);
		sb.append(StringUtils.repeat(" ", 234));
		return sb.toString();
	}

	public static FileTrailer fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 260) {
			return null;
		}
		FileTrailer o = new FileTrailer();
		o.setRecordFormat(s.substring(0, 2));
		o.setRecordType(s.substring(2, 4));
		o.setRecordCount(s.substring(4, 14));
		o.setTotalAmount(s.substring(14, 26));
		return o;
	}

}
