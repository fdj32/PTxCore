package com.globalpaymentsinc.greenhouse;

import org.apache.commons.lang.StringUtils;

/**
 * MARKET DATA ADDENDUM EXTENSION RECORD, TYPE 09
 * 
 * @author nickfeng
 *
 */
public class BMarketDataAddendumExtension09 {

	/**
	 * Record Type, 1-2, AN 2, Always 09.
	 */
	private String recordType = "09";

	/**
	 * Additional Market Data, 3-94, AN 92, Contains the remaining Market Data. Left
	 * justified, space filled. Refer to Market Data on page A-8 for a list of valid
	 * values and Appendix B: Acquirer Reference Data & Market Data for
	 * explanations.
	 */
	private String additionalMarketData;

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

	public String getAdditionalMarketData() {
		return additionalMarketData;
	}

	public void setAdditionalMarketData(String additionalMarketData) {
		this.additionalMarketData = additionalMarketData;
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
		sb.append(StringUtils.rightPad(additionalMarketData, 92, ' '));
		sb.append(StringUtils.leftPad(recordSequenceNumber, 6, '0'));
		return sb.toString();
	}

	public static BMarketDataAddendumExtension09 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 100 || !s.startsWith("08")) {
			return null;
		}
		BMarketDataAddendumExtension09 bmb = new BMarketDataAddendumExtension09();
		bmb.setAdditionalMarketData(s.substring(2, 94));
		bmb.setRecordSequenceNumber(s.substring(94, 100));
		return bmb;
	}
}
