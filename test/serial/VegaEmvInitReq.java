package serial;

import java.nio.ByteBuffer;

import serial.enums.EmvReasonCode;
import serial.enums.EmvServiceCode;

/**
 * git/merchant-service/info/EMV/paymentech_dev_center/pin_pad_documentation/Ingenico
 * CPX, E2EE, VEGA, TSA iPP320 5C Documentation/API - VEGA/0142-07204-0503
 * Generic EMV API.pdf Page 47 / 167 3.4.4. EMV_INIT Request
 * 
 * @author nfeng
 *
 */
public class VegaEmvInitReq extends Vega {

	public VegaEmvInitReq(boolean bMoreDataToCome, byte[] thisDataChunk) {
		super();
		this.setEmvServiceCode(EmvServiceCode.EMV_INIT);
		this.setEmvReasonCode(EmvReasonCode.EMV_UNDEF);
		this.bMoreDataToCome = bMoreDataToCome;
		this.thisDataChunk = thisDataChunk;
	}

	private boolean bMoreDataToCome;
	private byte[] thisDataChunk;

	public boolean isbMoreDataToCome() {
		return bMoreDataToCome;
	}

	public void setbMoreDataToCome(boolean bMoreDataToCome) {
		this.bMoreDataToCome = bMoreDataToCome;
	}

	public byte[] getThisDataChunk() {
		return thisDataChunk;
	}

	public void setThisDataChunk(byte[] thisDataChunk) {
		this.thisDataChunk = thisDataChunk;
	}

	public byte[] toBinary() {
		ByteBuffer bb = ByteBuffer.allocate(1024);
		bb.put((byte) getEmvServiceCode());
		bb.put((byte) getEmvReasonCode());
		bb.put(UTFUtils.littleEndian(getThisDataChunk().length));
		bb.put(isbMoreDataToCome() ? (byte) 0x01 : (byte) 0x00);
		bb.put(getThisDataChunk());
		int position = bb.position();
		bb.flip();
		byte[] dst = new byte[position];
		bb.get(dst, 0, position);
		return dst;
	}

}
