package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
	private Map<EmvTransactionType, List<Tag>> endOfTransactionTags;
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
	private byte[] lengthProprietaryRIDData = new byte[] {0x00, 0x00};
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
			baos.write(endOfTransactionTags.get(i).size());
			for (Tag item : endOfTransactionTags.get(i)) {
				baos.write(item.getIdBin());
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

}
