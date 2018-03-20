package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * MARKET DATA ADDENDUM RECORD, TYPE 08
 * 
 * @author nickfeng
 *
 */
public class BMarketDataAddendum {

	/**
	 * Record Type, 1-2, AN 2, Always 08.
	 */
	private String recordType = "08";

	/**
	 * Market Data, 3-94, AN 92, Contains Market Data. Left justified, space filled.
	 * If more than 92 characters of market data are required, the remainder of the
	 * market data must be placed in the Market Data Extension Record. Refer to
	 * Market Data on page A-8 for a list of valid values and Appendix B: Acquirer
	 * Reference Data & Market Data for explanations.
	 */
	private String marketData;

	/**
	 * Record Sequence Number, 95-100, N 6, The first record is 000000. Subsequent
	 * records are incremented by 1.
	 */
	private String recordSequenceNumber;

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getMarketData() {
		return marketData;
	}

	public void setMarketData(String marketData) {
		this.marketData = marketData;
	}

	public String getRecordSequenceNumber() {
		return recordSequenceNumber;
	}

	public void setRecordSequenceNumber(String recordSequenceNumber) {
		this.recordSequenceNumber = recordSequenceNumber;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordType);
		sb.append(StringUtils.rightPad(marketData, 92, ' '));
		sb.append(StringUtils.leftPad(recordSequenceNumber, 6, '0'));
		return "BMarketDataAddendum []";
	}

	public static BMarketDataAddendum fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 100 || !s.startsWith("08")) {
			return null;
		}
		BMarketDataAddendum bma = new BMarketDataAddendum();
		bma.setMarketData(s.substring(2, 94));
		bma.setRecordSequenceNumber(s.substring(94, 100));
		return bma;
	}

}
