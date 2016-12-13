package serial.enums;

import java.nio.ByteBuffer;

import serial.UTFUtils;

/**
 * 0142-07204-0503%20Generic%20EMV%20API.pdf Page.61/167
 * 
 * @author nfeng
 *
 */
public class AidCandidate {

	/**
	 * Application Name in ASCII for display. Note that this can either be
	 * Application Label (tag 50) or Application Preferred Name (tag 9F12),
	 * which has been decided by VEGA according to the EMV rules.
	 */
	private String applicationName;
	/**
	 * AID in ASCII, e.g. "A0000000031010" This field exists only if
	 * EMV_PASS_AID_ON_APPLICATION_SELECTI ON is set in MiscellaneousOptions in
	 * terminal specific data.
	 */
	private String aidAscii;

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getAidAscii() {
		return aidAscii;
	}

	public void setAidAscii(String aidAscii) {
		this.aidAscii = aidAscii;
	}

	public AidCandidate(String applicationName, String aidAscii) {
		super();
		this.applicationName = applicationName;
		this.aidAscii = aidAscii;
	}

	public AidCandidate() {
		super();
	}
	
	public static byte[] array2bytes(AidCandidate[] aidcs) {
		ByteBuffer bb = ByteBuffer.allocate(128);
		for(AidCandidate aid : aidcs) {
			bb.put(toBinary(aid));
		}
		int position = bb.position();
		bb.flip();
		byte[] dst = new byte[position];
		bb.get(dst, 0, position);
		return dst;
	}
	
	public static byte[] toBinary(AidCandidate aid) {
		ByteBuffer bb = ByteBuffer.allocate(128);
		byte[] applicationNameBin = aid.getApplicationName().getBytes();
		byte[] aidAsciiBin = aid.getAidAscii().getBytes();
		bb.put((byte)(applicationNameBin.length + 1 + aidAsciiBin.length));
		bb.put(applicationNameBin);
		bb.put(UTFUtils.SEPARATOR);
		bb.put(aidAsciiBin);
		int position = bb.position();
		bb.flip();
		byte[] dst = new byte[position];
		bb.get(dst, 0, position);
		return dst;
	}
}
