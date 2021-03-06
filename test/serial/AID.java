package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 0142-07204-0503 Generic EMV API.pdf Page 35/167
 * 
 * @author nickfeng
 *
 */
public class AID {

	/**
	 * length=1
	 * <ul>
	 * <li>1 = partial selection is allowed.</li>
	 * <li>0 = partial selection is not allowed.</li>
	 * </ul>
	 */
	private byte applicationSelectionIndicator;
	/**
	 * length=1
	 */
	private byte lengthTLVData;
	private byte[] tlvData;
	/**
	 * length=1, Length of actual data in the fixed length AID field.
	 */
	private byte aidLength;
	/**
	 * length=16, 9F06
	 */
	private byte[] aid;
	/**
	 * length=5, RID the AID belongs to.
	 */
	private byte[] rid;
	/**
	 * length=2, 9F09
	 */
	private byte[] applicaitonVersionNumber;
	/**
	 * length=5, DF03
	 */
	private byte[] tacDefault;
	/**
	 * length=5, DF04
	 */
	private byte[] tacDenial;
	/**
	 * length=5, DF05
	 */
	private byte[] tacOnline;
	/**
	 * length=1, DF09
	 */
	private byte maximumTargetPercentage;
	/**
	 * length=1, DF08
	 */
	private byte targetPercentage;
	/**
	 * length=4, DF07
	 */
	private byte[] thresholdValue;
	/**
	 * length=4, 9F1B
	 */
	private byte[] terminalFloorLimit;
	/**
	 * length=2
	 */
	private byte[] defaultTDOLLength;
	/**
	 * DF18
	 */
	private byte[] defaultTDOL;
	/**
	 * length=2
	 */
	private byte[] defaultDDOLLength;
	/**
	 * DF15, IMPORTANT: the value of DefaultDDOL must comply with the BER-TLV
	 * formatting, and not with the Ingenico proprietary tlv formatting
	 */
	private byte[] defaultDDOL;

	public byte getApplicationSelectionIndicator() {
		return applicationSelectionIndicator;
	}

	public void setApplicationSelectionIndicator(byte applicationSelectionIndicator) {
		this.applicationSelectionIndicator = applicationSelectionIndicator;
	}

	public byte getLengthTLVData() {
		return lengthTLVData;
	}

	public void setLengthTLVData(byte lengthTLVData) {
		this.lengthTLVData = lengthTLVData;
	}

	public byte[] getTlvData() {
		return tlvData;
	}

	public void setTlvData(byte[] tlvData) {
		this.tlvData = tlvData;
	}

	public byte getAidLength() {
		return aidLength;
	}

	public void setAidLength(byte aidLength) {
		this.aidLength = aidLength;
	}

	public byte[] getAid() {
		return aid;
	}

	public void setAid(byte[] aid) {
		this.aid = aid;
	}

	public byte[] getRid() {
		return rid;
	}

	public void setRid(byte[] rid) {
		this.rid = rid;
	}

	public byte[] getApplicaitonVersionNumber() {
		return applicaitonVersionNumber;
	}

	public void setApplicaitonVersionNumber(byte[] applicaitonVersionNumber) {
		this.applicaitonVersionNumber = applicaitonVersionNumber;
	}

	public byte[] getTacDefault() {
		return tacDefault;
	}

	public void setTacDefault(byte[] tacDefault) {
		this.tacDefault = tacDefault;
	}

	public byte[] getTacDenial() {
		return tacDenial;
	}

	public void setTacDenial(byte[] tacDenial) {
		this.tacDenial = tacDenial;
	}

	public byte[] getTacOnline() {
		return tacOnline;
	}

	public void setTacOnline(byte[] tacOnline) {
		this.tacOnline = tacOnline;
	}

	public byte getMaximumTargetPercentage() {
		return maximumTargetPercentage;
	}

	public void setMaximumTargetPercentage(byte maximumTargetPercentage) {
		this.maximumTargetPercentage = maximumTargetPercentage;
	}

	public byte getTargetPercentage() {
		return targetPercentage;
	}

	public void setTargetPercentage(byte targetPercentage) {
		this.targetPercentage = targetPercentage;
	}

	public byte[] getThresholdValue() {
		return thresholdValue;
	}

	public void setThresholdValue(byte[] thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	public byte[] getTerminalFloorLimit() {
		return terminalFloorLimit;
	}

	public void setTerminalFloorLimit(byte[] terminalFloorLimit) {
		this.terminalFloorLimit = terminalFloorLimit;
	}

	public byte[] getDefaultTDOLLength() {
		return defaultTDOLLength;
	}

	public void setDefaultTDOLLength(byte[] defaultTDOLLength) {
		this.defaultTDOLLength = defaultTDOLLength;
	}

	public byte[] getDefaultTDOL() {
		return defaultTDOL;
	}

	public void setDefaultTDOL(byte[] defaultTDOL) {
		this.defaultTDOL = defaultTDOL;
	}

	public byte[] getDefaultDDOLLength() {
		return defaultDDOLLength;
	}

	public void setDefaultDDOLLength(byte[] defaultDDOLLength) {
		this.defaultDDOLLength = defaultDDOLLength;
	}

	public byte[] getDefaultDDOL() {
		return defaultDDOL;
	}

	public void setDefaultDDOL(byte[] defaultDDOL) {
		this.defaultDDOL = defaultDDOL;
	}

	public byte[] toBinary() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		baos.write(applicationSelectionIndicator);
		baos.write(lengthTLVData);
		baos.write(tlvData);
		baos.write(aidLength);
		baos.write(aid);
		baos.write(rid);
		baos.write(applicaitonVersionNumber);
		baos.write(tacDefault);
		baos.write(tacDenial);
		baos.write(tacOnline);
		baos.write(maximumTargetPercentage);
		baos.write(targetPercentage);
		baos.write(thresholdValue);
		baos.write(terminalFloorLimit);
		baos.write(defaultTDOLLength);
		if(null != defaultTDOL)
			baos.write(defaultTDOL);
		baos.write(defaultDDOLLength);
		if(null != defaultDDOL)
			baos.write(defaultDDOL);

		byte[] data = baos.toByteArray();
		baos.close();
		return data;
	}
	
	public static AID fromBinary(byte[] bin) {
		AID a = new AID();
		a.setApplicationSelectionIndicator(bin[0]);
		a.setLengthTLVData(bin[1]);
		int tlv = a.getLengthTLVDataInt();
		if(0 != tlv) {
			byte[] tlvData = new byte[tlv];
			System.arraycopy(bin, 2, tlvData, 0, tlv);
			a.setTlvData(tlvData);
		}
		a.setAidLength(bin[2+tlv]);
		
		byte[] aid = new byte[16];
		System.arraycopy(bin, 3+tlv, aid, 0, 16);
		a.setAid(aid);
		
		byte[] rid = new byte[5];
		System.arraycopy(bin, 19+tlv, rid, 0, 5);
		a.setRid(rid);
		
		byte[] applicationVersionNumber = new byte[2];
		System.arraycopy(bin, 24+tlv, applicationVersionNumber, 0, 2);
		a.setApplicaitonVersionNumber(applicationVersionNumber);
		
		byte[] tacDefault = new byte[5];
		System.arraycopy(bin, 26+tlv, tacDefault, 0, 5);
		a.setTacDefault(tacDefault);
		
		byte[] tacDenial = new byte[5];
		System.arraycopy(bin, 31+tlv, tacDenial, 0, 5);
		a.setTacDenial(tacDenial);
		
		byte[] tacOnline = new byte[5];
		System.arraycopy(bin, 36+tlv, tacOnline, 0, 5);
		a.setTacOnline(tacOnline);
		
		a.setMaximumTargetPercentage(bin[41+tlv]);
		a.setTargetPercentage(bin[42+tlv]);
		
		byte[] thresholdValue = new byte[4];
		System.arraycopy(bin, 43+tlv, thresholdValue, 0, 4);
		a.setThresholdValue(thresholdValue);
		
		byte[] terminalFloorLimit = new byte[4];
		System.arraycopy(bin, 47+tlv, terminalFloorLimit, 0, 4);
		a.setTerminalFloorLimit(terminalFloorLimit);
		
		byte[] defaultTDOLLength = new byte[2];
		System.arraycopy(bin, 51+tlv, defaultTDOLLength, 0, 2);
		a.setDefaultTDOLLength(defaultTDOLLength);
		int tdol = a.getDefaultTDOLLengthInt();
		if(0 != tdol) {
			byte[] defaultTDOL = new byte[tdol];
			System.arraycopy(bin, 53+tlv, defaultTDOL, 0, tdol);
			a.setDefaultTDOL(defaultTDOL);
		}
		
		byte[] defaultDDOLLength = new byte[2];
		System.arraycopy(bin, 53+tlv+tdol, defaultDDOLLength, 0, 2);
		a.setDefaultDDOLLength(defaultDDOLLength);
		int ddol = a.getDefaultDDOLLengthInt();
		if(0 != ddol) {
			byte[] defaultDDOL = new byte[ddol];
			System.arraycopy(bin, 55+tlv+tdol, defaultDDOL, 0, ddol);
			a.setDefaultDDOL(defaultDDOL);
		}
		return a;
	}
	
	public static List<AID> fromBinaryToList(byte[] bin) {
		List<AID> list = new ArrayList<AID>();
		int index = 0;
		while(index < bin.length) {
			byte[] temp = new byte[bin.length - index];
			System.arraycopy(bin, index, temp, 0, temp.length);
			AID aid = fromBinary(temp);
			list.add(aid);
			index += aid.totalLength();
		}
		return list;
	}
	
	public int getLengthTLVDataInt() {
		return lengthTLVData >=0 ? lengthTLVData : lengthTLVData + 0x100;
	}
	
	public int getDefaultTDOLLengthInt() {
		return UTFUtils.littleEndian(defaultTDOLLength);
	}
	
	public int getDefaultDDOLLengthInt() {
		return UTFUtils.littleEndian(defaultDDOLLength);
	}
	
	public int totalLength() {
		return 55+getLengthTLVDataInt()+getDefaultTDOLLengthInt()+getDefaultDDOLLengthInt();
	}
	
	public Element element() {
		Element r = DocumentHelper.createElement("AID");
		r.addElement("applicationSelectionIndicator").addText(Hex.encodeHexString(new byte[] {applicationSelectionIndicator}));
		r.addElement("lengthTLVData").addText(Hex.encodeHexString(new byte[] {lengthTLVData}));
		r.addElement("TLVData").addText(Hex.encodeHexString(tlvData));
		r.addElement("aidLength").addText(Hex.encodeHexString(new byte[] {aidLength}));
		r.addElement("aid").addText(Hex.encodeHexString(aid));
		r.addElement("rid").addText(Hex.encodeHexString(rid));
		r.addElement("applicaitonVersionNumber").addText(Hex.encodeHexString(applicaitonVersionNumber));
		r.addElement("tacDefault").addText(Hex.encodeHexString(tacDefault));
		r.addElement("tacDenial").addText(Hex.encodeHexString(tacDenial));
		r.addElement("tacOnline").addText(Hex.encodeHexString(tacOnline));
		r.addElement("maximumTargetPercentage").addText(Hex.encodeHexString(new byte[] {maximumTargetPercentage}));
		r.addElement("targetPercentage").addText(Hex.encodeHexString(new byte[] {targetPercentage}));
		r.addElement("thresholdValue").addText(Hex.encodeHexString(thresholdValue));
		r.addElement("terminalFloorLimit").addText(Hex.encodeHexString(terminalFloorLimit));
		r.addElement("defaultTDOLLength").addText(Hex.encodeHexString(defaultTDOLLength));
		if(null != defaultTDOL)
			r.addElement("defaultTDOL").addText(Hex.encodeHexString(defaultTDOL));
		r.addElement("defaultDDOLLength").addText(Hex.encodeHexString(defaultDDOLLength));
		if(null != defaultDDOL) 
			r.addElement("defaultDDOL").addText(Hex.encodeHexString(defaultDDOL));
		return r;
	}

}
