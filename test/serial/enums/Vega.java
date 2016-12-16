package serial.enums;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import org.apache.commons.codec.DecoderException;
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
		bb.put((byte) (EmvServiceCode.EMV_SELECT_APPLICATION));
		bb.put((byte) (EmvReasonCode.EMV_UNDEF));
		byte[] emvData = AidCandidate.array2bytes(aidcs);
		bb.put((byte) (emvData.length % 0x100));
		bb.put((byte) (emvData.length / 0x100));
		bb.put(emvData);
		int position = bb.position();
		bb.flip();
		byte[] dst = new byte[position];
		bb.get(dst, 0, position);
		return dst;
	}

	public static byte[] selectApplicationResponse(AidCandidate aid) {
		ByteBuffer bb = ByteBuffer.allocate(256);
		bb.put((byte) (EmvServiceCode.EMV_SELECT_APPLICATION));
		bb.put((byte) (EmvReasonCode.EMV_OK));
		byte[] emvData = AidCandidate.toBinary(aid);
		bb.put((byte) (emvData.length % 0x100));
		bb.put((byte) (emvData.length / 0x100));
		bb.put(emvData);
		int position = bb.position();
		bb.flip();
		byte[] dst = new byte[position];
		bb.get(dst, 0, position);
		return dst;
	}

	/**
	 * Page.69/167
	 * 
	 * @param purchaseAmount
	 * @param cashbackAmount
	 * @return
	 */
	public static byte[] getAmountResponse(int purchaseAmount,
			int cashbackAmount) {
		ByteBuffer bb = ByteBuffer.allocate(32);
		bb.put((byte) EmvServiceCode.EMV_GET_AMOUNT);
		bb.put((byte) EmvReasonCode.EMV_OK);
		bb.put((byte) 20);
		bb.put((byte) 0);
		bb.put((byte) 8);
		bb.put((byte) 0);
		bb.put(StringUtils.leftPad("" + purchaseAmount, 8, '0').getBytes());
		bb.put((byte) 8);
		bb.put((byte) 0);
		bb.put(StringUtils.leftPad("" + cashbackAmount, 8, '0').getBytes());
		int position = bb.position();
		bb.flip();
		byte[] dst = new byte[position];
		bb.get(dst, 0, position);
		return dst;
	}

	/**
	 * Page.69/167
	 * 
	 * @return
	 */
	public static byte[] getAmountRequest() {
		byte[] data = new byte[4];
		data[0] = (byte) EmvServiceCode.EMV_GET_AMOUNT;
		data[1] = (byte) EmvReasonCode.EMV_UNDEF;
		data[2] = (byte) 0;
		data[2] = (byte) 0;
		return data;
	}

	/**
	 * Page.70/167
	 * 
	 * @return
	 */
	public static byte[] getPreviousAmountRequest() {
		byte[] data = new byte[4];
		data[0] = (byte) EmvServiceCode.EMV_GET_PREVIOUS_AMOUNT;
		data[1] = (byte) EmvReasonCode.EMV_UNDEF;
		data[2] = (byte) 0;
		data[3] = (byte) 0;
		return data;
	}

	/**
	 * Page.70/167 0a0102000000 = 0a01010030
	 * 
	 * @return
	 */
	public static byte[] getPreviousAmountResponse(int amount) {
		ByteBuffer bb = ByteBuffer.allocate(32);
		bb.put((byte) EmvServiceCode.EMV_GET_PREVIOUS_AMOUNT);
		bb.put((byte) EmvReasonCode.EMV_OK);
		int length = ("" + amount).length();
		bb.put((byte) (length % 0x100));
		bb.put((byte) (length / 0x100));
		bb.put(("" + amount).getBytes());
		int position = bb.position();
		bb.flip();
		byte[] dst = new byte[position];
		bb.get(dst, 0, position);
		return dst;
	}

	/**
	 * Page.72/167
	 * 
	 * @return
	 * @throws IOException
	 */
	public static byte[] emvReleaseSSARequest() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write((byte) EmvServiceCode.EMV_RELEASE_SSA);
		baos.write((byte) EmvReasonCode.EMV_UNDEF);
		baos.write((byte) 0);
		baos.write((byte) 0);
		byte[] data = baos.toByteArray();
		baos.close();
		return data;
	}

	/**
	 * Page.72/167
	 * 
	 * @return
	 * @throws IOException
	 */
	public static byte[] emvReleaseSSAResponse() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write((byte) EmvServiceCode.EMV_RELEASE_SSA);
		baos.write((byte) EmvReasonCode.EMV_OK);
		baos.write((byte) 0);
		baos.write((byte) 0);
		byte[] data = baos.toByteArray();
		baos.close();
		return data;
	}

	/**
	 * Page.74/167
	 * 
	 * @return
	 * @throws DecoderException
	 * @throws IOException
	 */
	public static byte[] emvGoOnlineRequest(List<Tag> list) throws IOException,
			DecoderException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write((byte) EmvServiceCode.EMV_GO_ONLINE);
		baos.write((byte) EmvReasonCode.EMV_UNDEF);
		byte[] data = Tag.buildTLVList(list);
		baos.write((byte) (data.length % 0x100));
		baos.write((byte) (data.length / 0x100));
		baos.write(data);
		data = baos.toByteArray();
		baos.close();
		return data;
	}

	/**
	 * Page.75/167
	 * 
	 * @return
	 * @throws DecoderException
	 * @throws IOException
	 */
	public static byte[] emvGoOnlineResponse(List<Tag> list)
			throws IOException, DecoderException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write((byte) EmvServiceCode.EMV_GO_ONLINE);
		baos.write((byte) EmvReasonCode.EMV_OK);
		byte[] data = Tag.buildTLVList(list);
		baos.write((byte) (data.length % 0x100));
		baos.write((byte) (data.length / 0x100));
		baos.write(data);
		data = baos.toByteArray();
		baos.close();
		return data;
	}

	/**
	 * Page.86/167
	 * 
	 * @param list
	 * @return
	 * @throws IOException
	 * @throws DecoderException
	 */
	public static byte[] emvStopTransactionRequest(List<Tag> list)
			throws IOException, DecoderException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write((byte) EmvServiceCode.EMV_STOP_TRANSACTION);
		baos.write((byte) EmvReasonCode.EMV_APPROVED);
		byte[] data = Tag.buildTLVList(list);
		baos.write((byte) (data.length % 0x100));
		baos.write((byte) (data.length / 0x100));
		baos.write(data);
		data = baos.toByteArray();
		baos.close();
		return data;
	}
	
	/**
	 * Page.87/167
	 * 
	 * @param list
	 * @return
	 * @throws IOException
	 * @throws DecoderException
	 */
	public static byte[] emvStopTransactionResponse()
			throws IOException, DecoderException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write((byte) EmvServiceCode.EMV_STOP_TRANSACTION);
		baos.write((byte) EmvReasonCode.EMV_APPROVED);
		baos.write((byte) 0);
		baos.write((byte) 0);
		byte[] data = baos.toByteArray();
		baos.close();
		return data;
	}
}
