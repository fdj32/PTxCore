package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * MARKET DATA EXTENSION ADDENDUM REQUEST, TYPE MB
 * 
 * @author nickfeng
 *
 */
public class MarketDataExtensionAddendumRequestB {

	/**
	 * Record Type, 1-2, AN 2, Always MB.
	 */
	private String recordType = "MB";

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
	 * Additional Market Data, 5-79, AN 75, Contains Market Data. Left justified, space filled.
	 * If more than 75 characters of market data are required, the remainder of the
	 * market data must be placed in the Market Data Extension Record. Refer to
	 * Market Data on page A-8 for a list of valid values and Appendix B: Acquirer
	 * Reference Data & Market Data for explanations.
	 */
	private String additionalMarketData;

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
		sb.append(extensionRecordIndicator);
		sb.append(' ');
		sb.append(StringUtils.rightPad(additionalMarketData, 75, ' '));
		sb.append(' ');
		return sb.toString();
	}

	public static MarketDataExtensionAddendumRequestB fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 80 || !s.startsWith("MB")) {
			return null;
		}
		MarketDataExtensionAddendumRequestB mb = new MarketDataExtensionAddendumRequestB();
		mb.setExtensionRecordIndicator(s.charAt(2));
		mb.setAdditionalMarketData(s.substring(4, 79));
		return mb;
	}

}
