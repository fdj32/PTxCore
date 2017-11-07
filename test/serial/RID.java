package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import serial.enums.EmvTransactionType;
import serial.enums.Tag;

/**
 * 0142-07204-0503 Generic EMV API.pdf Page 29/167
 * 
 * @author nickfeng
 *
 */
public class RID implements Constant {

	private byte[] rid;
	/**
	 * length=2
	 */
	private byte[] keyDataTotalLength;
	private List<KeyData> keyDatas;
	/**
	 * length=2
	 */
	private byte[] lengthGoOnlineTags;
	private List<Tag> goOnlineTags;
	/**
	 * length=2
	 */
	private byte[] lengthEndOfTransactionTags;
	private Map<Integer, List<Tag>> endOfTransactionTags;
	private byte[] EndOfTransactionStep;
	/**
	 * length=2
	 */
	private byte[] lengthGetPreviousAmountTags;
	private List<Tag> getPreviousAmountTags;
	/**
	 * length=2
	 */
	private byte[] lengthExtendedAPIData;
	private List<ExtendedAPIData> extendedAPIDatas;
	/**
	 * length=2
	 */
	private byte[] lengthProprietaryRIDData = new byte[] { 0x00, 0x00 };
	private byte[] proprietaryRIDData;
	/**
	 * length=2
	 */
	private byte[] lengthIgnoreTags;
	private List<Tag> ignoreTags;
	// RFU*8
	/**
	 * length=1
	 */
	private byte miscellaneousOptions;
	/**
	 * length=2
	 */
	private byte[] lengthTLVData;
	/**
	 * FF09
	 */
	private byte[] tlvData;

	public byte[] getRid() {
		return rid;
	}

	public void setRid(byte[] rid) {
		this.rid = rid;
	}

	public byte[] getKeyDataTotalLength() {
		return keyDataTotalLength;
	}

	public void setKeyDataTotalLength(byte[] keyDataTotalLength) {
		this.keyDataTotalLength = keyDataTotalLength;
	}

	public List<KeyData> getKeyDatas() {
		return keyDatas;
	}

	public void setKeyDatas(List<KeyData> keyDatas) {
		this.keyDatas = keyDatas;
	}

	public byte[] getLengthGoOnlineTags() {
		return lengthGoOnlineTags;
	}

	public void setLengthGoOnlineTags(byte[] lengthGoOnlineTags) {
		this.lengthGoOnlineTags = lengthGoOnlineTags;
	}

	public List<Tag> getGoOnlineTags() {
		return goOnlineTags;
	}

	public void setGoOnlineTags(List<Tag> goOnlineTags) {
		this.goOnlineTags = goOnlineTags;
	}

	public byte[] getLengthEndOfTransactionTags() {
		return lengthEndOfTransactionTags;
	}

	public void setLengthEndOfTransactionTags(byte[] lengthEndOfTransactionTags) {
		this.lengthEndOfTransactionTags = lengthEndOfTransactionTags;
	}

	public Map<Integer, List<Tag>> getEndOfTransactionTags() {
		return endOfTransactionTags;
	}

	public void setEndOfTransactionTags(Map<Integer, List<Tag>> endOfTransactionTags) {
		this.endOfTransactionTags = endOfTransactionTags;
	}

	public byte[] getEndOfTransactionStep() {
		return EndOfTransactionStep;
	}

	public void setEndOfTransactionStep(byte[] endOfTransactionStep) {
		EndOfTransactionStep = endOfTransactionStep;
	}

	public byte[] getLengthGetPreviousAmountTags() {
		return lengthGetPreviousAmountTags;
	}

	public void setLengthGetPreviousAmountTags(byte[] lengthGetPreviousAmountTags) {
		this.lengthGetPreviousAmountTags = lengthGetPreviousAmountTags;
	}

	public List<Tag> getGetPreviousAmountTags() {
		return getPreviousAmountTags;
	}

	public void setGetPreviousAmountTags(List<Tag> getPreviousAmountTags) {
		this.getPreviousAmountTags = getPreviousAmountTags;
	}

	public byte[] getLengthExtendedAPIData() {
		return lengthExtendedAPIData;
	}

	public void setLengthExtendedAPIData(byte[] lengthExtendedAPIData) {
		this.lengthExtendedAPIData = lengthExtendedAPIData;
	}

	public List<ExtendedAPIData> getExtendedAPIDatas() {
		return extendedAPIDatas;
	}

	public void setExtendedAPIDatas(List<ExtendedAPIData> extendedAPIDatas) {
		this.extendedAPIDatas = extendedAPIDatas;
	}

	public byte[] getLengthProprietaryRIDData() {
		return lengthProprietaryRIDData;
	}

	public void setLengthProprietaryRIDData(byte[] lengthProprietaryRIDData) {
		this.lengthProprietaryRIDData = lengthProprietaryRIDData;
	}

	public byte[] getProprietaryRIDData() {
		return proprietaryRIDData;
	}

	public void setProprietaryRIDData(byte[] proprietaryRIDData) {
		this.proprietaryRIDData = proprietaryRIDData;
	}

	public byte[] getLengthIgnoreTags() {
		return lengthIgnoreTags;
	}

	public void setLengthIgnoreTags(byte[] lengthIgnoreTags) {
		this.lengthIgnoreTags = lengthIgnoreTags;
	}

	public List<Tag> getIgnoreTags() {
		return ignoreTags;
	}

	public void setIgnoreTags(List<Tag> ignoreTags) {
		this.ignoreTags = ignoreTags;
	}

	public byte getMiscellaneousOptions() {
		return miscellaneousOptions;
	}

	public void setMiscellaneousOptions(byte miscellaneousOptions) {
		this.miscellaneousOptions = miscellaneousOptions;
	}

	public byte[] getLengthTLVData() {
		return lengthTLVData;
	}

	public void setLengthTLVData(byte[] lengthTLVData) {
		this.lengthTLVData = lengthTLVData;
	}

	public byte[] getTlvData() {
		return tlvData;
	}

	public void setTlvData(byte[] tlvData) {
		this.tlvData = tlvData;
	}

	public byte[] toBinary() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		for (KeyData item : keyDatas) {
			baos.write(item.toBinary());
		}
		byte[] keyDatasBin = baos.toByteArray();
		keyDataTotalLength = UTFUtils.lgt(keyDatasBin.length, 2);
		baos.reset();

		for (Tag item : goOnlineTags) {
			baos.write(item.getIdBin());
		}
		byte[] goOnlineTagsBin = baos.toByteArray();
		lengthGoOnlineTags = UTFUtils.lgt(goOnlineTagsBin.length, 2);
		baos.reset();

		for (int i = EmvTransactionType.EMV_PURCHASE; i <= EmvTransactionType.EMV_PREAUTH_COMPLETION; i++) {
			if(null == endOfTransactionTags.get(i) || 0 == endOfTransactionTags.get(i).size()) {
				baos.write(0x00);
			} else {
				baos.write(endOfTransactionTags.get(i).size());
				for (Tag item : endOfTransactionTags.get(i)) {
					baos.write(item.getIdBin());
				}
			}
		}
		byte[] endOfTransactionTagsBin = baos.toByteArray();
		lengthEndOfTransactionTags = UTFUtils.lgt(endOfTransactionTagsBin.length, 2);
		baos.reset();

		for (Tag item : getPreviousAmountTags) {
			baos.write(item.getIdBin());
		}
		byte[] getPreviousAmountTagsBin = baos.toByteArray();
		lengthGetPreviousAmountTags = UTFUtils.lgt(getPreviousAmountTagsBin.length, 2);
		baos.reset();

		for (ExtendedAPIData item : extendedAPIDatas) {
			baos.write(item.toBinary());
		}
		byte[] extendedAPIDatasBin = baos.toByteArray();
		lengthExtendedAPIData = UTFUtils.lgt(extendedAPIDatasBin.length, 2);
		baos.reset();

		for (Tag item : ignoreTags) {
			baos.write(item.getIdBin());
		}
		byte[] ignoreTagsBin = baos.toByteArray();
		lengthIgnoreTags = UTFUtils.lgt(ignoreTagsBin.length, 2);
		baos.reset();

		// start to build RID
		baos.write(rid);
		baos.write(keyDataTotalLength);
		baos.write(keyDatasBin);
		baos.write(lengthGoOnlineTags);
		baos.write(goOnlineTagsBin);
		baos.write(lengthEndOfTransactionTags);
		baos.write(endOfTransactionTagsBin);
		baos.write(EndOfTransactionStep);
		baos.write(lengthGetPreviousAmountTags);
		baos.write(getPreviousAmountTagsBin);
		baos.write(lengthExtendedAPIData);
		baos.write(extendedAPIDatasBin);
		baos.write(lengthProprietaryRIDData);
		// proprietaryRIDData = null
		baos.write(lengthIgnoreTags);
		baos.write(ignoreTagsBin);
		for (int i = 0; i < 8; i++) {
			baos.write(RFU);
		}
		baos.write(miscellaneousOptions);
		baos.write(lengthTLVData);
		baos.write(tlvData);

		byte[] data = baos.toByteArray();
		baos.close();
		return data;
	}

	public static RID fromBinary(byte[] bin) {
		RID r = new RID();
		
		byte[] rid = new byte[5];
		System.arraycopy(bin, 0, rid, 0, 5);
		r.setRid(rid);
		
		byte[] keyDataTotalLength = new byte[2];
		System.arraycopy(bin, 5, keyDataTotalLength, 0, 2);
		r.setKeyDataTotalLength(keyDataTotalLength);
		int key = r.getKeyDataTotalLengthInt();
		byte[] keyDatasBin = new byte[key];
		List<KeyData> keyDatas = KeyData.fromBinaryToList(keyDatasBin);
		r.setKeyDatas(keyDatas);
		
		byte[] lengthGoOnlineTags = new byte[2];
		System.arraycopy(bin, 7+key, lengthGoOnlineTags, 0, 2);
		r.setLengthGoOnlineTags(lengthGoOnlineTags);
		int online = r.getLengthGoOnlineTagsInt();
		byte[] goOnlineTagsBin = new byte[online];
		List<Tag> goOnlineTags = Tag.fromBinaryToIdList(goOnlineTagsBin);
		r.setGoOnlineTags(goOnlineTags);
		
		byte[] lengthEndOfTransactionTags = new byte[2];
		System.arraycopy(bin, 9+key+online, lengthEndOfTransactionTags, 0, 2);
		r.setLengthEndOfTransactionTags(lengthEndOfTransactionTags);
		int endTags = r.getLengthEndOfTransactionTagsInt();
		byte[] endTagsBin = new byte[endTags]; 
		System.arraycopy(bin, 11+key+online, endTagsBin, 0, endTags);
		int index = 0;
		Map<Integer, List<Tag>> endOfTransactionTags = new HashMap<Integer, List<Tag>>();
		for (int i = EmvTransactionType.EMV_PURCHASE; i <= EmvTransactionType.EMV_PREAUTH_COMPLETION; i++) {
			byte[] temp = new byte[endTags - index];
			System.arraycopy(endTagsBin, index, temp, 0, endTags - index);
			int size = temp[0];
			index++;
			if(0 == size) {
				continue;
			} else {
				byte[] sub = new byte[size*2];
				index += size*2;
				System.arraycopy(temp, 1, sub, 0, size*2);
				List<Tag> list = Tag.fromBinaryToIdList(sub);
				endOfTransactionTags.put(i, list);
			}
		}
		
		
		

		return r;
	}
	
	public int getKeyDataTotalLengthInt() {
		return keyDataTotalLength[0] * 0x100 + keyDataTotalLength[1];
	}
	
	public int getLengthGoOnlineTagsInt() {
		return lengthGoOnlineTags[0] * 0x100 + lengthGoOnlineTags[1];
	}
	
	public int getLengthEndOfTransactionTagsInt() {
		return lengthEndOfTransactionTags[0] * 0x100 + lengthEndOfTransactionTags[1];
	}

}
