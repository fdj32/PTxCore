package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import serial.enums.EmvTransactionStep;
import serial.enums.EmvTransactionType;
import serial.enums.Tag;

/**
 * 0142-07204-0503 Generic EMV API.pdf Page 33/167
 * 
 * @author nickfeng
 *
 */
public class ExtendedAPIData implements Constant {

	private byte[] lengthStepTags;
	private Map<Integer, Map<Integer, List<Tag>>> tagListToAskFor;
	private Map<Integer, Map<Integer, List<Tag>>> tagListInCallBack;

	public byte[] getLengthStepTags() {
		return lengthStepTags;
	}

	public void setLengthStepTags(byte[] lengthStepTags) {
		this.lengthStepTags = lengthStepTags;
	}

	public Map<Integer, Map<Integer, List<Tag>>> getTagListToAskFor() {
		return tagListToAskFor;
	}

	public void setTagListToAskFor(Map<Integer, Map<Integer, List<Tag>>> tagListToAskFor) {
		this.tagListToAskFor = tagListToAskFor;
	}

	public Map<Integer, Map<Integer, List<Tag>>> getTagListInCallBack() {
		return tagListInCallBack;
	}

	public void setTagListInCallBack(Map<Integer, Map<Integer, List<Tag>>> tagListInCallBack) {
		this.tagListInCallBack = tagListInCallBack;
	}

	public byte[] toBinary() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ByteArrayOutputStream baosTemp = new ByteArrayOutputStream();
		for (int i = EmvTransactionType.EMV_PURCHASE; i <= EmvTransactionType.EMV_PREAUTH_COMPLETION; i++) {
			for (int j = EmvTransactionStep.EMV_LANGUAGE_SELECTION; j <= EmvTransactionStep.EMV_TRANSACTION_COMPLETION; j++) {
				baos.write(RFU);
				baos.write(RFU);
				if(null == tagListToAskFor || null == tagListToAskFor.get(i)
						|| 0 == tagListToAskFor.get(i).size()
						|| null == tagListToAskFor.get(i).get(j)
						|| 0 == tagListToAskFor.get(i).get(j).size()) {
					baos.write(0x00);
					baos.write(0x00);
				} else {
					for (Tag item : tagListToAskFor.get(i).get(j)) {
						baosTemp.write(item.getIdBin());
					}
					byte[] tagListToAskForBin = baosTemp.toByteArray();
					baosTemp.reset();
					byte[] lenTagListToAskFor = UTFUtils.lgt(tagListToAskForBin.length, 1);
					baos.write(lenTagListToAskFor);
					baos.write(tagListToAskForBin);
				}
				
				if(null == tagListInCallBack || null == tagListInCallBack.get(i)
						|| 0 == tagListInCallBack.get(i).size()
						|| null == tagListInCallBack.get(i).get(j)
						|| 0 == tagListInCallBack.get(i).get(j).size()) {
					baos.write(0x00);
					baos.write(0x00);
				} else {
					for (Tag item : tagListInCallBack.get(i).get(j)) {
						baosTemp.write(item.getIdBin());
					}
					byte[] tagListInCallBackBin = baosTemp.toByteArray();
					baosTemp.reset();
					byte[] lenTagListInCallBack = UTFUtils.lgt(tagListInCallBackBin.length, 1);
					baos.write(lenTagListInCallBack);
					baos.write(tagListInCallBackBin);
				}
			}
		}
		byte[] data = baos.toByteArray();
		baos.reset();
		lengthStepTags = UTFUtils.lgt(data.length, 2);

		baos.write(lengthStepTags);
		baos.write(data);

		baosTemp.close();
		baos.close();
		return data;
	}

	public static ExtendedAPIData fromBinary(byte[] bin) {
		ExtendedAPIData e = new ExtendedAPIData();
		byte[] lengthStepTags = new byte[2];
		System.arraycopy(bin, 0, lengthStepTags, 0, 2);
		e.setLengthStepTags(lengthStepTags);
		int index = 2;
		Map<Integer, Map<Integer, List<Tag>>> tagListToAskFor = new HashMap<Integer, Map<Integer, List<Tag>>>();
		Map<Integer, Map<Integer, List<Tag>>> tagListInCallBack = new HashMap<Integer, Map<Integer, List<Tag>>>();
		for (int i = EmvTransactionType.EMV_PURCHASE; i <= EmvTransactionType.EMV_PREAUTH_COMPLETION; i++) {
			Map<Integer, List<Tag>> mapAskFor = new HashMap<Integer, List<Tag>>();
			Map<Integer, List<Tag>> mapCallBack = new HashMap<Integer, List<Tag>>();
			for (int j = EmvTransactionStep.EMV_LANGUAGE_SELECTION; j <= EmvTransactionStep.EMV_TRANSACTION_COMPLETION; j++) {
				byte[] temp = new byte[bin.length - index];
				System.arraycopy(bin, index, temp, 0, temp.length);
				// RFU*2
				index+=2;
				int lenTagListToAskFor = temp[2];
				index++;
				if(0 != lenTagListToAskFor) {
					byte[] tagListToAskForBin = new byte[lenTagListToAskFor];
					System.arraycopy(temp, 3, tagListToAskForBin, 0, lenTagListToAskFor);
					List<Tag> listAskFor = Tag.fromBinaryToIdList(tagListToAskForBin);
					mapAskFor.put(j, listAskFor);
					index+=lenTagListToAskFor;
				}
				int lenTagListInCallBack = temp[3+lenTagListToAskFor];
				if(0 != lenTagListInCallBack) {
					byte[] tagListInCallBackBin = new byte[lenTagListInCallBack];
					System.arraycopy(temp, 4+lenTagListToAskFor, tagListInCallBackBin, 0, lenTagListInCallBack);
					List<Tag> listCallBack = Tag.fromBinaryToIdList(tagListInCallBackBin);
					mapCallBack.put(j, listCallBack);
					index+=lenTagListInCallBack;
				}
			}
			tagListToAskFor.put(i, mapAskFor);
			tagListInCallBack.put(i, mapCallBack);
		}
		e.setTagListToAskFor(tagListToAskFor);
		e.setTagListInCallBack(tagListInCallBack);
		return e;
	}
	
	public Element element() {
		Element r = DocumentHelper.createElement("ExtendedAPIData");
		r.addElement("lengthStepTags").addText(Hex.encodeHexString(lengthStepTags));
		Element stepTags = r.addElement("EMVStepTags");
		for (int i = EmvTransactionType.EMV_PURCHASE; i <= EmvTransactionType.EMV_PREAUTH_COMPLETION; i++) {
			Map<Integer, List<Tag>> mapAskFor = null;
			if(null != tagListToAskFor && !tagListToAskFor.isEmpty())
				mapAskFor = tagListToAskFor.get(i);
			Map<Integer, List<Tag>> mapCallBack = null;
			if(null != tagListInCallBack && !tagListInCallBack.isEmpty())
				mapCallBack = tagListInCallBack.get(i);
			Element type = stepTags.addElement("EMVTransactionType").addAttribute("type", "" + i);
			for (int j = EmvTransactionStep.EMV_LANGUAGE_SELECTION; j <= EmvTransactionStep.EMV_TRANSACTION_COMPLETION; j++) {
				Element step = type.addElement("EMVStep").addAttribute("step", "" + j);
				List<Tag> listAskFor = null;
				if(null != tagListToAskFor && null != mapAskFor && null != mapAskFor.get(j))
					listAskFor = mapAskFor.get(j);
				if(null != listAskFor) {
					Element askFor = step.addElement("tagListToAskFor");
					for(Tag t : listAskFor) {
						askFor.addElement("Tag").addAttribute("ID", Hex.encodeHexString(t.getIdBin()));
					}
				}
				
				List<Tag> listCallBack = null;
				if(null != tagListInCallBack && null != mapCallBack && null != mapCallBack.get(j))
					listCallBack = mapCallBack.get(j);
				if(null != listCallBack) {
					Element callBack = step.addElement("tagListInCallBack");
					for(Tag t : listCallBack) {
						callBack.addElement("Tag").addAttribute("ID", Hex.encodeHexString(t.getIdBin()));
					}
				}
			}
		}
		
		return r;
	}

}
