package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Hex;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 0142-07204-0503 Generic EMV API.pdf Page 23/167
 * 
 * @author nickfeng
 *
 */
public class VegaInitData {

	/**
	 * length=2
	 */
	private byte[] terminalDataTotalLength;
	private TerminalSpecificData tsd;
	/**
	 * length=2
	 */
	private byte[] ridDataTotalLength;
	private RIDSpecificData rsd;
	/**
	 * length=2
	 */
	private byte[] aidDataTotalLength;
	private AIDSpecificData asd;

	public byte[] getTerminalDataTotalLength() {
		return terminalDataTotalLength;
	}

	public void setTerminalDataTotalLength(byte[] terminalDataTotalLength) {
		this.terminalDataTotalLength = terminalDataTotalLength;
	}

	public byte[] getRidDataTotalLength() {
		return ridDataTotalLength;
	}

	public void setRidDataTotalLength(byte[] ridDataTotalLength) {
		this.ridDataTotalLength = ridDataTotalLength;
	}

	public byte[] getAidDataTotalLength() {
		return aidDataTotalLength;
	}

	public void setAidDataTotalLength(byte[] aidDataTotalLength) {
		this.aidDataTotalLength = aidDataTotalLength;
	}

	public TerminalSpecificData getTsd() {
		return tsd;
	}

	public void setTsd(TerminalSpecificData tsd) {
		this.tsd = tsd;
	}

	public RIDSpecificData getRsd() {
		return rsd;
	}

	public void setRsd(RIDSpecificData rsd) {
		this.rsd = rsd;
	}

	public AIDSpecificData getAsd() {
		return asd;
	}

	public void setAsd(AIDSpecificData asd) {
		this.asd = asd;
	}

	public VegaInitData() {
		super();
	}

	public VegaInitData(TerminalSpecificData tsd, RIDSpecificData rsd, AIDSpecificData asd) {
		super();
		this.tsd = tsd;
		this.rsd = rsd;
		this.asd = asd;
	}
	
	public Element element() {
		Element r = DocumentHelper.createElement("VegaInitData");
		r.addElement("terminalDataTotalLength").addText(Hex.encodeHexString(terminalDataTotalLength));
		r.add(tsd.element());
		r.addElement("ridDataTotalLength").addText(Hex.encodeHexString(ridDataTotalLength));
		r.add(rsd.element());
		r.addElement("aidDataTotalLength").addText(Hex.encodeHexString(aidDataTotalLength));
		r.add(asd.element());
		return r;
	}

	public byte[] toBinary() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] data = tsd.toBinary();
		terminalDataTotalLength = UTFUtils.littleEndian(data.length);
		baos.write(terminalDataTotalLength);
		baos.write(data);

		data = rsd.toBinary();
		ridDataTotalLength = UTFUtils.littleEndian(data.length);
		baos.write(ridDataTotalLength);
		baos.write(data);

		data = asd.toBinary();
		aidDataTotalLength = UTFUtils.littleEndian(data.length);
		baos.write(aidDataTotalLength);
		baos.write(data);
		
		baos.write(0x00); // TODO : I didn't find out the specification.

		data = baos.toByteArray();

		baos.close();
		return data;
	}
	
	public static VegaInitData fromBinary(byte[] bin) {
		VegaInitData v = new VegaInitData();
		
		byte[] terminalDataTotalLength = new byte[2];
		System.arraycopy(bin, 0, terminalDataTotalLength, 0, 2);
		v.setTerminalDataTotalLength(terminalDataTotalLength);
		int terminal = v.getTerminalDataTotalLengthInt();
		byte[] tsdBin = new byte[terminal];
		System.arraycopy(bin, 2, tsdBin, 0, terminal);
		TerminalSpecificData tsd = TerminalSpecificData.fromBinary(tsdBin);
		v.setTsd(tsd);
		
		byte[] ridDataTotalLength = new byte[2];
		System.arraycopy(bin, 2+terminal, ridDataTotalLength, 0, 2);
		v.setRidDataTotalLength(ridDataTotalLength);
		int rid = v.getRIDDataTotalLengthInt();
		byte[] rsdBin = new byte[rid];
		System.arraycopy(bin, 4+terminal, rsdBin, 0, rid);
		RIDSpecificData rsd = RIDSpecificData.fromBinary(rsdBin);
		v.setRsd(rsd);
		
		byte[] aidDataTotalLength = new byte[2];
		System.arraycopy(bin, 4+terminal+rid, aidDataTotalLength, 0, 2);
		v.setAidDataTotalLength(aidDataTotalLength);
		int aid = v.getAIDDataTotalLengthInt();
		byte[] asdBin = new byte[aid];
		System.arraycopy(bin, 6+terminal+rid, asdBin, 0, aid);
		AIDSpecificData asd = AIDSpecificData.fromBinary(asdBin);
		v.setAsd(asd);
		
		return v;
	}
	
	public int getTerminalDataTotalLengthInt() {
		return UTFUtils.littleEndian(terminalDataTotalLength);
	}
	
	public int getRIDDataTotalLengthInt() {
		return UTFUtils.littleEndian(ridDataTotalLength);
	}
	
	public int getAIDDataTotalLengthInt() {
		return UTFUtils.littleEndian(aidDataTotalLength);
	}

}
