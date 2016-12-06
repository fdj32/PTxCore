package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.162/184}
 * 
 * @author nfeng
 * @see CpxF1Command
 * @see CpxF1Result
 */
public class Mac {

	/**
	 * 2 bytes, For multiple blocks of data (range 0x0000 to 0xFFFF)
	 */
	private String blockNumber;
	/**
	 * 2 bytes, Total number of blocks to be expected (range 0x0000 to 0xFFFF)
	 */
	private String maxBlockNumber;
	/**
	 * 1 byte, Application-Based Financial Keys 0x01 = Single Length Key
	 * Terminal-Based (64) Financial Keys 0x30 – 0x6F = Key Index ("0" – "o" in
	 * ASCII) and Single Length Key given; 0x02 = Double Length Key
	 * Terminal-Based (64) Financial Keys 0xB0 – 0xEF = Key Index (<7th Bit Set>
	 * + "0" – "o" in ASCII) and Double Length Key given
	 */
	private String keyLenOrIndex;
	/**
	 * Encrypted Session Key (If the SK is stored this field will be ignored
	 * although the 8 bytes are still required); Encrypted (Double Length)
	 * Session Key (If the SK is stored this field will be ignored although the
	 * 16 bytes are still required)
	 */
	private String sessionKey;
	/**
	 * 4 bytes, Double Length: Key Check Value for Double Length Session Key
	 * injection
	 */
	private String sessionKcv;
	/**
	 * 4 bytes, Total of all data bytes in all blocks to be sent (MAC engine
	 * needs this to know when it is done calculating)
	 */
	private String totalLength;
	/**
	 * Single:(LGT – 18) bytes; Double: (LGT – 30) bytes; First block of data to
	 * be in the initial part of the MAC calculation. Data Length must be a
	 * multiple of 8 bytes, except the last block sent (last block is null
	 * padded to a multiple of 8).
	 */
	private String macData;

	public String getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(String blockNumber) {
		this.blockNumber = blockNumber;
	}

	public String getMaxBlockNumber() {
		return maxBlockNumber;
	}

	public void setMaxBlockNumber(String maxBlockNumber) {
		this.maxBlockNumber = maxBlockNumber;
	}

	public String getKeyLenOrIndex() {
		return keyLenOrIndex;
	}

	public void setKeyLenOrIndex(String keyLenOrIndex) {
		this.keyLenOrIndex = keyLenOrIndex;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getSessionKcv() {
		return sessionKcv;
	}

	public void setSessionKcv(String sessionKcv) {
		this.sessionKcv = sessionKcv;
	}

	public String getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(String totalLength) {
		this.totalLength = totalLength;
	}

	public String getMacData() {
		return macData;
	}

	public void setMacData(String macData) {
		this.macData = macData;
	}

	@Override
	public String toString() {
		String msg = this.getBlockNumber();
		msg += this.getMaxBlockNumber();
		msg += this.getKeyLenOrIndex();
		msg += this.getSessionKey();
		msg += StringUtils.defaultString(this.getSessionKcv());
		msg += this.getTotalLength();
		msg += this.getMacData();
		return msg;
	}

	public static Mac parse(String str) {
		Mac md = new Mac();
		md.setBlockNumber(str.substring(0, 2));
		md.setMaxBlockNumber(str.substring(2, 4));
		md.setKeyLenOrIndex(str.substring(4, 5));
		if("\u0001".equals(md.getKeyLenOrIndex())) {
			// Single Length Key
			md.setSessionKey(str.substring(5, 13));
			md.setTotalLength(str.substring(13, 17));
			md.setMacData(str.substring(17));
		} else if("\u0002".equals(md.getKeyLenOrIndex())) {
			// Double Length Key
			md.setSessionKey(str.substring(5, 21));
			md.setSessionKcv(str.substring(21, 25));
			md.setTotalLength(str.substring(25, 29));
			md.setMacData(str.substring(29));
		} else {
			return null;
		}
		return md;
	}

}
