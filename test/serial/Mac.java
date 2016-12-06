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
	 * 4 bytes, The MAC Value that is being Verified against all the supplied
	 * data
	 */
	private String macValue;
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

	public String getMacValue() {
		return macValue;
	}

	public void setMacValue(String macValue) {
		this.macValue = macValue;
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
		msg += StringUtils.defaultString(this.getKeyLenOrIndex());
		msg += StringUtils.defaultString(this.getSessionKey());
		msg += StringUtils.defaultString(this.getSessionKcv());
		// CALCULATION is empty, VERIFICATION make sense
		msg += StringUtils.defaultString(this.getMacValue());
		msg += StringUtils.defaultString(this.getTotalLength());
		msg += StringUtils.defaultString(this.getMacData());
		return msg;
	}

}
