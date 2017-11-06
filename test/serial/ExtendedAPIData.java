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
public class ExtendedAPIData {

	private byte[] lengthStepTags;
	private Map<EmvTransactionType, Map<EmvTransactionStep, List<Tag>>> tagListToAskFor;
	private Map<EmvTransactionType, Map<EmvTransactionStep, List<Tag>>> tagListInCallBack;

	public byte[] toBinary() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ByteArrayOutputStream baosTemp = new ByteArrayOutputStream();
		for (int i = EmvTransactionType.EMV_PURCHASE; i <= EmvTransactionType.EMV_PREAUTH_COMPLETION; i++) {
			for (int j = EmvTransactionStep.EMV_LANGUAGE_SELECTION; j <= EmvTransactionStep.EMV_TRANSACTION_COMPLETION; j++) {
				for (Tag item : tagListToAskFor.get(i).get(j)) {
					baosTemp.write(item.getIdBin());
				}
				byte[] tagListToAskForBin = baosTemp.toByteArray();
				baosTemp.reset();
				byte[] lenTagListToAskFor = UTFUtils.lgt(tagListToAskForBin.length, 2);

				for (Tag item : tagListInCallBack.get(i).get(j)) {
					baosTemp.write(item.getIdBin());
				}
				byte[] tagListInCallBackBin = baosTemp.toByteArray();
				baosTemp.reset();
				byte[] lenTagListInCallBack = UTFUtils.lgt(tagListInCallBackBin.length, 2);

				baos.write(lenTagListToAskFor);
				baos.write(tagListToAskForBin);
				baos.write(lenTagListInCallBack);
				baos.write(tagListInCallBackBin);
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

}
