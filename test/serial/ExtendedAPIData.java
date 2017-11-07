package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
					byte[] lenTagListToAskFor = UTFUtils.lgt(tagListToAskForBin.length, 2);
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
					byte[] lenTagListInCallBack = UTFUtils.lgt(tagListInCallBackBin.length, 2);
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

		return e;
	}

}
