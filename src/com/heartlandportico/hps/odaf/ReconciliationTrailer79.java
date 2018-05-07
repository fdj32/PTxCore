package com.heartlandportico.hps.odaf;

import org.apache.commons.lang.StringUtils;

public class ReconciliationTrailer79 {

	/**
	 * 1-10, Refer to Control Section – Record Type = '79'
	 */
	private ControlSection cs;

	/**
	 * 11-16,‘RECON’
	 */
	private String batchIdentifier;

	/* Filler, 17-18, AN 2, Space Filled. */

	/**
	 * 19-25, Count includes Batch Headers & Trailers, but not File Header & Trailer
	 * records
	 */
	private String logicalCount;

	/* Filler, 26-250, AN 225, Space Filled. */

	public ControlSection getCs() {
		return cs;
	}

	public void setCs(ControlSection cs) {
		this.cs = cs;
	}

	public String getBatchIdentifier() {
		return batchIdentifier;
	}

	public void setBatchIdentifier(String batchIdentifier) {
		this.batchIdentifier = batchIdentifier;
	}

	public String getLogicalCount() {
		return logicalCount;
	}

	public void setLogicalCount(String logicalCount) {
		this.logicalCount = logicalCount;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(cs.toString());
		sb.append(batchIdentifier);
		sb.append(StringUtils.repeat(" ", 2));
		sb.append(logicalCount);
		sb.append(StringUtils.repeat(" ", 225));
		return sb.toString();
	}

	public static ReconciliationTrailer79 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 250) {
			return null;
		}
		ReconciliationTrailer79 o = new ReconciliationTrailer79();
		o.setCs(ControlSection.fromString(s.substring(0, 10)));
		o.setBatchIdentifier(s.substring(10, 16));
		o.setLogicalCount(s.substring(18, 25));
		return o;
	}

}
