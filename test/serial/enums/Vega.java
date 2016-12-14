package serial.enums;

import java.nio.ByteBuffer;

import org.apache.commons.lang.StringUtils;

public class Vega {

	public static final String ENGLISH = "en";
	public static final String FRENCH = "fr";
	public static final byte[] EMV_SELECT_LANGUAGE_EN = new byte[] {
			(byte) EmvServiceCode.EMV_SELECT_LANGUAGE,
			(byte) EmvReasonCode.EMV_OK, 0x02, 0, (byte) 'e', (byte) 'n' };
	public static final byte[] EMV_SELECT_LANGUAGE_FR = new byte[] {
			(byte) EmvServiceCode.EMV_SELECT_LANGUAGE,
			(byte) EmvReasonCode.EMV_OK, 0x02, 0, (byte) 'f', (byte) 'r' };

	public static byte[] selectApplicationRequest(AidCandidate[] aidcs) {
		ByteBuffer bb = ByteBuffer.allocate(256);
		bb.put((byte)(EmvServiceCode.EMV_SELECT_APPLICATION));
		bb.put((byte)(EmvReasonCode.EMV_UNDEF));
		byte[] emvData = AidCandidate.array2bytes(aidcs);
		bb.put((byte)(emvData.length % 0x100));
		bb.put((byte)(emvData.length / 0x100));
		bb.put(emvData);
		int position = bb.position();
		bb.flip();
		byte[] dst = new byte[position];
		bb.get(dst, 0, position);
		return dst;
	}
	
	public static byte[] selectApplicationResponse(AidCandidate aid) {
		ByteBuffer bb = ByteBuffer.allocate(256);
		bb.put((byte)(EmvServiceCode.EMV_SELECT_APPLICATION));
		bb.put((byte)(EmvReasonCode.EMV_OK));
		byte[] emvData = AidCandidate.toBinary(aid);
		bb.put((byte)(emvData.length % 0x100));
		bb.put((byte)(emvData.length / 0x100));
		bb.put(emvData);
		int position = bb.position();
		bb.flip();
		byte[] dst = new byte[position];
		bb.get(dst, 0, position);
		return dst;
	}
	
	/**
	 * Page.69/167
	 * @param purchaseAmount
	 * @param cashbackAmount
	 * @return
	 */
	public static byte[] getAmountResponse(int purchaseAmount, int cashbackAmount) {
		ByteBuffer bb = ByteBuffer.allocate(32);
		bb.put((byte)EmvServiceCode.EMV_GET_AMOUNT);
		bb.put((byte)EmvReasonCode.EMV_OK);
		bb.put((byte)20);
		bb.put((byte)0);
		bb.put((byte)8);
		bb.put((byte)0);
		bb.put(StringUtils.leftPad(""+purchaseAmount, 8, '0').getBytes());
		bb.put((byte)8);
		bb.put((byte)0);
		bb.put(StringUtils.leftPad(""+cashbackAmount, 8, '0').getBytes());
		int position = bb.position();
		bb.flip();
		byte[] dst = new byte[position];
		bb.get(dst, 0, position);
		return dst;
	}
	
	/**
	 * Page.70/167
	 * @return
	 */
	public static byte[] getPreviousAmountRequest() {
		byte[] data = new byte[4];
		data[0] = (byte)EmvServiceCode.EMV_GET_PREVIOUS_AMOUNT;
		data[1] = (byte)EmvReasonCode.EMV_UNDEF;
		data[2] = (byte)0;
		data[3] = (byte)0;
		return data;
	}
}
