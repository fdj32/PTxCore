package serial.enums;

import java.nio.ByteBuffer;

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
}