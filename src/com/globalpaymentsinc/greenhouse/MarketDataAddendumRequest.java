package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * MARKET DATA ADDENDUM REQUEST, TYPE MA
 * 
 * @author nickfeng
 *
 */
public class MarketDataAddendumRequest {

	/**
	 * Record Type, 1-2, AN 2, Always MA.
	 */
	private String recordType = "MA";

	/**
	 * Extension Record Indicator, 3-3, AN 1, Indicates if an extension record
	 * follows. Valid values are:
	 * <ul>
	 * <li>0 or space - No extension record follows</li>
	 * <li>1 - Extension record follows</li>
	 * </ul>
	 */
	private char extensionRecordIndicator;

	/* Filler, 4-4, AN 1, Space Filled. */

	/**
	 * Market Data, 5-79, AN 75, Contains Market Data. Left justified, space filled.
	 * If more than 75 characters of market data are required, the remainder of the
	 * market data must be placed in the Market Data Extension Record. Refer to
	 * Market Data on page A-8 for a list of valid values and Appendix B: Acquirer
	 * Reference Data & Market Data for explanations.
	 */
	private String marketData;

	/* Filler, 80-80, AN 1, Space Filled. */

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public char getExtensionRecordIndicator() {
		return extensionRecordIndicator;
	}

	public void setExtensionRecordIndicator(char extensionRecordIndicator) {
		this.extensionRecordIndicator = extensionRecordIndicator;
	}

	public String getMarketData() {
		return marketData;
	}

	public void setMarketData(String marketData) {
		this.marketData = marketData;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(extensionRecordIndicator);
		sb.append(' ');
		sb.append(StringUtils.rightPad(marketData, 75, ' '));
		sb.append(' ');
		return sb.toString();
	}

	public static MarketDataAddendumRequest fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("MA")) {
			return null;
		}
		MarketDataAddendumRequest ma = new MarketDataAddendumRequest();
		ma.setExtensionRecordIndicator(s.charAt(2));
		ma.setMarketData(s.substring(4, 79));
		return ma;
	}

}
