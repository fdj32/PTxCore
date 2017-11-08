package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
	private ExtendedAPIData extendedAPIData;
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

	public ExtendedAPIData getExtendedAPIData() {
		return extendedAPIData;
	}

	public void setExtendedAPIData(ExtendedAPIData extendedAPIData) {
		this.extendedAPIData = extendedAPIData;
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
		
		byte[] extendedAPIDataBin = extendedAPIData.toBinary();
		lengthExtendedAPIData = UTFUtils.lgt(extendedAPIDataBin.length, 2);

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
		baos.write(extendedAPIDataBin);
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
		System.arraycopy(bin, 7, keyDatasBin, 0, key);
		List<KeyData> keyDatas = KeyData.fromBinaryToList(keyDatasBin);
		r.setKeyDatas(keyDatas);
		
		byte[] lengthGoOnlineTags = new byte[2];
		System.arraycopy(bin, 7+key, lengthGoOnlineTags, 0, 2);
		r.setLengthGoOnlineTags(lengthGoOnlineTags);
		int online = r.getLengthGoOnlineTagsInt();
		byte[] goOnlineTagsBin = new byte[online];
		System.arraycopy(bin, 9+key, goOnlineTagsBin, 0, online);
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
		
		byte[] endOfTransactionStep = new byte[7];
		System.arraycopy(bin, 11+key+online+endTags, endOfTransactionStep, 0, 7);
		r.setEndOfTransactionStep(endOfTransactionStep);
		
		byte[] lengthGetPreviousAmountTags = new byte[2];
		System.arraycopy(bin, 18+key+online+endTags, lengthGetPreviousAmountTags, 0, 2);
		r.setLengthGetPreviousAmountTags(lengthGetPreviousAmountTags);
		int previous = r.getLengthGetPreviousAmountTagsInt();
		byte[] getPreviousAmountTagsBin = new byte[previous];
		System.arraycopy(bin, 20+key+online+endTags, getPreviousAmountTagsBin, 0, previous);
		List<Tag> getPreviousAmountTags = Tag.fromBinaryToIdList(getPreviousAmountTagsBin);
		r.setGetPreviousAmountTags(getPreviousAmountTags);
		
		byte[] lengthExtendedAPIData = new byte[2];
		System.arraycopy(bin, 20+key+online+endTags+previous, lengthExtendedAPIData, 0, 2);
		r.setLengthExtendedAPIData(lengthExtendedAPIData);
		int extend = r.getLengthExtendedAPIDataInt();
		byte[] extendedAPIDataBin = new byte[extend];
		System.arraycopy(bin, 22+key+online+endTags+previous, extendedAPIDataBin, 0, extend);
		ExtendedAPIData extendedAPIData = ExtendedAPIData.fromBinary(extendedAPIDataBin);
		r.setExtendedAPIData(extendedAPIData);
		
		byte[] lengthProprietaryRIDData = new byte[2]; // 0x00 0x00
		System.arraycopy(bin, 22+key+online+endTags+previous+extend, lengthProprietaryRIDData, 0, 2);
		r.setLengthProprietaryRIDData(lengthProprietaryRIDData);
		
		byte[] lengthIgnoreTags = new byte[2];
		System.arraycopy(bin, 24+key+online+endTags+previous+extend, lengthIgnoreTags, 0, 2);
		r.setLengthIgnoreTags(lengthIgnoreTags);
		int ignore = r.getLengthIgnoreTagsInt();
		byte[] ignoreTagsBin = new byte[ignore];
		System.arraycopy(bin, 26+key+online+endTags+previous+extend, ignoreTagsBin, 0, ignore);
		List<Tag> ignoreTags = Tag.fromBinaryToIdList(ignoreTagsBin);
		r.setIgnoreTags(ignoreTags);
		// RFU*1
		r.setMiscellaneousOptions(bin[27+key+online+endTags+previous+extend+ignore]);
		
		byte[] lengthTLVData = new byte[2];
		System.arraycopy(bin, 28+key+online+endTags+previous+extend+ignore, lengthTLVData, 0, 2);
		r.setLengthTLVData(lengthTLVData);
		int tlv = r.getLengthTLVDataInt();
		byte[] tlvData = new byte[tlv];
		System.arraycopy(bin, 30+key+online+endTags+previous+extend+ignore, tlvData, 0, tlv);
		r.setTlvData(tlvData);
		
		return r;
	}
	
	public static List<RID> fromBinaryToList(byte[] bin) {
		List<RID> list = new ArrayList<RID>();
		int index = 0;
		while(index <= bin.length) {
			byte[] temp = new byte[bin.length - index];
			System.arraycopy(bin, index, temp, 0, temp.length);
			RID rid = fromBinary(temp);
			list.add(rid);
			index += rid.totalLength();
		}
		return list;
	}
	
	public int totalLength() {
		return 30 + getKeyDataTotalLengthInt()
		+ getLengthGoOnlineTagsInt()
		+ getLengthEndOfTransactionTagsInt()
		+ getLengthGetPreviousAmountTagsInt()
		+ getLengthExtendedAPIDataInt()
		+ getLengthIgnoreTagsInt()
		+ getLengthTLVDataInt();
	}
	
	public int getKeyDataTotalLengthInt() {
		return UTFUtils.littleEndian(keyDataTotalLength);
	}
	
	public int getLengthGoOnlineTagsInt() {
		return UTFUtils.littleEndian(lengthGoOnlineTags);
	}
	
	public int getLengthEndOfTransactionTagsInt() {
		return UTFUtils.littleEndian(lengthEndOfTransactionTags);
	}
	
	public int getLengthGetPreviousAmountTagsInt() {
		return UTFUtils.littleEndian(lengthGetPreviousAmountTags);
	}
	
	public int getLengthExtendedAPIDataInt() {
		return UTFUtils.littleEndian(lengthExtendedAPIData);
	}
	
	public int getLengthIgnoreTagsInt() {
		return UTFUtils.littleEndian(lengthIgnoreTags);
	}
	
	public int getLengthTLVDataInt() {
		return UTFUtils.littleEndian(lengthTLVData);
	}

}
