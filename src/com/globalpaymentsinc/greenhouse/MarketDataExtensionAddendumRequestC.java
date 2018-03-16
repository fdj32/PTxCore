package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * MARKET DATA EXTENSION ADDENDUM REQUEST, TYPE MC
 * 
 * @author nickfeng
 *
 */
public class MarketDataExtensionAddendumRequestC {

	/**
	 * Record Type, 1-2, AN 2, Always MC.
	 */
	private String recordType = "MC";

	/* Filler, 3-4, AN 2, Space Filled. */

	/**
	 * Additional Market Data, 5-54, AN 50, Contains the remaining Market Data. Left
	 * justified, space filled. Refer to Market Data on page A-8 for a list of valid
	 * values and Appendix B: Acquirer Reference Data & Market Data for
	 * explanations.
	 */
	private String additionalMarketData;

	/* Filler, 55-80, AN 26, Space Filled. */

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getAdditionalMarketData() {
		return additionalMarketData;
	}

	public void setAdditionalMarketData(String additionalMarketData) {
		this.additionalMarketData = additionalMarketData;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(StringUtils.repeat(" ", 2));
		sb.append(StringUtils.rightPad(additionalMarketData, 50, ' '));
		sb.append(StringUtils.repeat(" ", 26));
		return sb.toString();
	}

	public static MarketDataExtensionAddendumRequestC fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("MC")) {
			return null;
		}
		MarketDataExtensionAddendumRequestC mc = new MarketDataExtensionAddendumRequestC();
		mc.setAdditionalMarketData(s.substring(4, 54));
		return mc;
	}

}
