package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Hex;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 0142-07204-0503 Generic EMV API.pdf Page 24/167
 * 
 * @author nickfeng
 *
 */
public class TerminalSpecificData implements Constant {

	// RFU*1
	/**
	 * length=3, 9F33
	 */
	private byte[] terminalCapabilities;
	/**
	 * length=5, 9F40
	 */
	private byte[] additionalTerminalCapabilities;
	/**
	 * length=2, 9F1A
	 */
	private byte[] terminalCountryCode;
	/**
	 * length=1, 9F35
	 */
	private byte terminalType;
	/**
	 * length=2, 5F2A
	 */
	private byte[] transactionCurrencyCode;
	/**
	 * length=1, 5F36
	 */
	private byte transactionCurrencyExponent;
	/**
	 * length=2, 9F3C
	 */
	private byte[] transactionReferenceCurrencyCode;
	/**
	 * length=1, 9F3D
	 */
	private byte transactionReferenceCurrencyExponent;
	/**
	 * length=4
	 */
	private byte[] transactionReferenceCurrencyConversion;
	/**
	 * length=6, 9F01
	 */
	private byte[] acquirerIdentifier;
	/**
	 * length=2, 9F15
	 */
	private byte[] merchantCategoryCode;
	/**
	 * length=15, 9F16
	 */
	private byte[] merchantIdentifier;
	/**
	 * length=8, 9F1C
	 */
	private byte[] terminalIdentification;
	/**
	 * length=8, 9F1D
	 */
	private byte[] terminalRiskManagementData;
	/**
	 * length=8, 9F1E
	 */
	private byte[] ifdSerialNumber;
	/**
	 * length=20, DF16
	 */
	private byte[] authorizationResponseCodeList;
	/**
	 * length=1
	 */
	private byte miscellaneousOptions;
	/**
	 * length=1
	 */
	private byte miscellaneousOptions1;
	// RFU*1
	private byte[] lengthTLVData;
	/**
	 * FF09
	 */
	private byte[] tlvData;
	// RFU*20
	/**
	 * length=2
	 */
	private byte[] lengthOfflinePINEntryConfiguration;
	/**
	 * 
	 */
	private OfflinePINEntryConfiguration offlinePINEntryConfiguration;
	/**
	 * length=8
	 */
	private byte[] terminalLanguages;
	// RFU*2*2
	/**
	 * length=2
	 */
	private byte[] lengthDiagnosticsTags;
	private byte[] diagnosticsTags;
	/**
	 * length=2
	 */
	private byte[] lengthAppSelectionTags;
	private byte[] appSelectionTags;
	/**
	 * length=2
	 */
	private byte[] lengthRIDApps;
	private byte[] ridApps;

	public byte[] getTerminalCapabilities() {
		return terminalCapabilities;
	}

	public void setTerminalCapabilities(byte[] terminalCapabilities) {
		this.terminalCapabilities = terminalCapabilities;
	}

	public byte[] getAdditionalTerminalCapabilities() {
		return additionalTerminalCapabilities;
	}

	public void setAdditionalTerminalCapabilities(byte[] additionalTerminalCapabilities) {
		this.additionalTerminalCapabilities = additionalTerminalCapabilities;
	}

	public byte[] getTerminalCountryCode() {
		return terminalCountryCode;
	}

	public void setTerminalCountryCode(byte[] terminalCountryCode) {
		this.terminalCountryCode = terminalCountryCode;
	}

	public byte getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(byte terminalType) {
		this.terminalType = terminalType;
	}

	public byte[] getTransactionCurrencyCode() {
		return transactionCurrencyCode;
	}

	public void setTransactionCurrencyCode(byte[] transactionCurrencyCode) {
		this.transactionCurrencyCode = transactionCurrencyCode;
	}

	public byte getTransactionCurrencyExponent() {
		return transactionCurrencyExponent;
	}

	public void setTransactionCurrencyExponent(byte transactionCurrencyExponent) {
		this.transactionCurrencyExponent = transactionCurrencyExponent;
	}

	public byte[] getTransactionReferenceCurrencyCode() {
		return transactionReferenceCurrencyCode;
	}

	public void setTransactionReferenceCurrencyCode(byte[] transactionReferenceCurrencyCode) {
		this.transactionReferenceCurrencyCode = transactionReferenceCurrencyCode;
	}

	public byte getTransactionReferenceCurrencyExponent() {
		return transactionReferenceCurrencyExponent;
	}

	public void setTransactionReferenceCurrencyExponent(byte transactionReferenceCurrencyExponent) {
		this.transactionReferenceCurrencyExponent = transactionReferenceCurrencyExponent;
	}

	public byte[] getTransactionReferenceCurrencyConversion() {
		return transactionReferenceCurrencyConversion;
	}

	public void setTransactionReferenceCurrencyConversion(byte[] transactionReferenceCurrencyConversion) {
		this.transactionReferenceCurrencyConversion = transactionReferenceCurrencyConversion;
	}

	public byte[] getAcquirerIdentifier() {
		return acquirerIdentifier;
	}

	public void setAcquirerIdentifier(byte[] acquirerIdentifier) {
		this.acquirerIdentifier = acquirerIdentifier;
	}

	public byte[] getMerchantCategoryCode() {
		return merchantCategoryCode;
	}

	public void setMerchantCategoryCode(byte[] merchantCategoryCode) {
		this.merchantCategoryCode = merchantCategoryCode;
	}

	public byte[] getMerchantIdentifier() {
		return merchantIdentifier;
	}

	public void setMerchantIdentifier(byte[] merchantIdentifier) {
		this.merchantIdentifier = merchantIdentifier;
	}

	public byte[] getTerminalIdentification() {
		return terminalIdentification;
	}

	public void setTerminalIdentification(byte[] terminalIdentification) {
		this.terminalIdentification = terminalIdentification;
	}

	public byte[] getTerminalRiskManagementData() {
		return terminalRiskManagementData;
	}

	public void setTerminalRiskManagementData(byte[] terminalRiskManagementData) {
		this.terminalRiskManagementData = terminalRiskManagementData;
	}

	public byte[] getIfdSerialNumber() {
		return ifdSerialNumber;
	}

	public void setIfdSerialNumber(byte[] ifdSerialNumber) {
		this.ifdSerialNumber = ifdSerialNumber;
	}

	public byte[] getAuthorizationResponseCodeList() {
		return authorizationResponseCodeList;
	}

	public void setAuthorizationResponseCodeList(byte[] authorizationResponseCodeList) {
		this.authorizationResponseCodeList = authorizationResponseCodeList;
	}

	public byte getMiscellaneousOptions() {
		return miscellaneousOptions;
	}

	public void setMiscellaneousOptions(byte miscellaneousOptions) {
		this.miscellaneousOptions = miscellaneousOptions;
	}

	public byte getMiscellaneousOptions1() {
		return miscellaneousOptions1;
	}

	public void setMiscellaneousOptions1(byte miscellaneousOptions1) {
		this.miscellaneousOptions1 = miscellaneousOptions1;
	}

	public byte[] getLengthTLVData() {
		return lengthTLVData;
	}

	public void setLengthTLVData(byte[] lengthTLVData) {
		this.lengthTLVData = lengthTLVData;
	}

	public byte[] getTlvData() {
		return tlvData;
	}

	public void setTlvData(byte[] tlvData) {
		this.tlvData = tlvData;
	}

	public byte[] getLengthOfflinePINEntryConfiguration() {
		return lengthOfflinePINEntryConfiguration;
	}

	public void setLengthOfflinePINEntryConfiguration(byte[] lengthOfflinePINEntryConfiguration) {
		this.lengthOfflinePINEntryConfiguration = lengthOfflinePINEntryConfiguration;
	}

	public OfflinePINEntryConfiguration getOfflinePINEntryConfiguration() {
		return offlinePINEntryConfiguration;
	}

	public void setOfflinePINEntryConfiguration(OfflinePINEntryConfiguration offlinePINEntryConfiguration) {
		this.offlinePINEntryConfiguration = offlinePINEntryConfiguration;
	}

	public byte[] getTerminalLanguages() {
		return terminalLanguages;
	}

	public void setTerminalLanguages(byte[] terminalLanguages) {
		this.terminalLanguages = terminalLanguages;
	}

	public byte[] getLengthDiagnosticsTags() {
		return lengthDiagnosticsTags;
	}

	public void setLengthDiagnosticsTags(byte[] lengthDiagnosticsTags) {
		this.lengthDiagnosticsTags = lengthDiagnosticsTags;
	}

	public byte[] getDiagnosticsTags() {
		return diagnosticsTags;
	}

	public void setDiagnosticsTags(byte[] diagnosticsTags) {
		this.diagnosticsTags = diagnosticsTags;
	}

	public byte[] getLengthAppSelectionTags() {
		return lengthAppSelectionTags;
	}

	public void setLengthAppSelectionTags(byte[] lengthAppSelectionTags) {
		this.lengthAppSelectionTags = lengthAppSelectionTags;
	}

	public byte[] getAppSelectionTags() {
		return appSelectionTags;
	}

	public void setAppSelectionTags(byte[] appSelectionTags) {
		this.appSelectionTags = appSelectionTags;
	}

	public byte[] getLengthRIDApps() {
		return lengthRIDApps;
	}

	public void setLengthRIDApps(byte[] lengthRIDApps) {
		this.lengthRIDApps = lengthRIDApps;
	}

	public byte[] getRidApps() {
		return ridApps;
	}

	public void setRidApps(byte[] ridApps) {
		this.ridApps = ridApps;
	}
	
	public Element element() {
		Element r = DocumentHelper.createElement("terminalSpecificData");
		r.addElement("terminalCapabilities").addText(Hex.encodeHexString(terminalCapabilities));
		r.addElement("additionalTerminalCapabilities").addText(Hex.encodeHexString(additionalTerminalCapabilities));
		r.addElement("terminalCountryCode").addText(Hex.encodeHexString(terminalCountryCode));
		r.addElement("terminalType").addText(Hex.encodeHexString(new byte[] {terminalType}));
		r.addElement("transactionCurrencyCode").addText(Hex.encodeHexString(transactionCurrencyCode));
		r.addElement("transactionCurrencyExponent").addText(Hex.encodeHexString(new byte[] {transactionCurrencyExponent}));
		r.addElement("transactionReferenceCurrencyCode").addText(Hex.encodeHexString(transactionReferenceCurrencyCode));
		r.addElement("transactionReferenceCurrencyExponent").addText(Hex.encodeHexString(new byte[] {transactionReferenceCurrencyExponent}));
		r.addElement("transactionReferenceCurrencyConversion").addText(Hex.encodeHexString(transactionReferenceCurrencyConversion));
		r.addElement("acquirerIdentifier").addText(Hex.encodeHexString(acquirerIdentifier));
		r.addElement("merchantCategoryCode").addText(Hex.encodeHexString(merchantCategoryCode));
		r.addElement("merchantIdentifier").addText(Hex.encodeHexString(merchantIdentifier));
		r.addElement("terminalIdentification").addText(Hex.encodeHexString(terminalIdentification));
		r.addElement("terminalRiskManagementData").addText(Hex.encodeHexString(terminalRiskManagementData));
		r.addElement("ifdSerialNumber").addText(Hex.encodeHexString(ifdSerialNumber));
		r.addElement("authorizationResponseCodeList").addText(Hex.encodeHexString(authorizationResponseCodeList));
		r.addElement("miscellaneousOptions").addText(Hex.encodeHexString(new byte[] {miscellaneousOptions}));
		r.addElement("miscellaneousOptions1").addText(Hex.encodeHexString(new byte[] {miscellaneousOptions1}));
		r.addElement("lengthTLVData").addText(Hex.encodeHexString(lengthTLVData));
		r.addElement("tlvData").addText(Hex.encodeHexString(tlvData));
		r.addElement("lengthOfflinePINEntryConfiguration").addText(Hex.encodeHexString(lengthOfflinePINEntryConfiguration));
		r.add(offlinePINEntryConfiguration.element());
		r.addElement("terminalLanguages").addText(Hex.encodeHexString(terminalLanguages));
		r.addElement("lengthDiagnosticsTags").addText(Hex.encodeHexString(lengthDiagnosticsTags));
		if(null != diagnosticsTags)
			r.addElement("diagnosticsTags").addText(Hex.encodeHexString(diagnosticsTags));
		r.addElement("lengthAppSelectionTags").addText(Hex.encodeHexString(lengthAppSelectionTags));
		if(null != appSelectionTags)
			r.addElement("appSelectionTags").addText(Hex.encodeHexString(appSelectionTags));
		r.addElement("lengthRIDApps").addText(Hex.encodeHexString(lengthRIDApps));
		if(null != ridApps)
			r.addElement("ridApps").addText(Hex.encodeHexString(ridApps));
		return r;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public byte[] toBinary() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(RFU);
		baos.write(terminalCapabilities);
		baos.write(additionalTerminalCapabilities);
		baos.write(terminalCountryCode);
		baos.write(terminalType);
		baos.write(transactionCurrencyCode);
		baos.write(transactionCurrencyExponent);
		baos.write(transactionReferenceCurrencyCode);
		baos.write(transactionReferenceCurrencyExponent);
		baos.write(transactionReferenceCurrencyConversion);
		baos.write(acquirerIdentifier);
		baos.write(merchantCategoryCode);
		baos.write(merchantIdentifier);
		baos.write(terminalIdentification);
		baos.write(terminalRiskManagementData);
		baos.write(ifdSerialNumber);
		baos.write(authorizationResponseCodeList);
		baos.write(miscellaneousOptions);
		baos.write(miscellaneousOptions1);
		baos.write(RFU);
		baos.write(lengthTLVData);
		baos.write(tlvData);
		for (int i = 0; i < 20; i++) {
			baos.write(RFU);
		}
		baos.write(lengthOfflinePINEntryConfiguration);
		if (null != offlinePINEntryConfiguration) {
			baos.write(offlinePINEntryConfiguration.toBinary());
		}
		baos.write(terminalLanguages);
		for (int i = 0; i < 4; i++) {
			baos.write(RFU);
		}
		baos.write(lengthDiagnosticsTags);
		if(null != diagnosticsTags)
			baos.write(diagnosticsTags);
		baos.write(lengthAppSelectionTags);
		if(null != appSelectionTags)
			baos.write(appSelectionTags);
		baos.write(lengthRIDApps);
		if(null != ridApps)
			baos.write(ridApps);
		byte[] data = baos.toByteArray();
		baos.close();
		return data;
	}

	public static TerminalSpecificData fromBinary(byte[] bin) {
		TerminalSpecificData t = new TerminalSpecificData();
		byte[] terminalCapabilities = new byte[3];
		// RFU*1
		System.arraycopy(bin, 1, terminalCapabilities, 0, 3);
		t.setTerminalCapabilities(terminalCapabilities);

		byte[] additionalTerminalCapabilities = new byte[5];
		System.arraycopy(bin, 4, additionalTerminalCapabilities, 0, 5);
		t.setAdditionalTerminalCapabilities(additionalTerminalCapabilities);

		byte[] terminalCountryCode = new byte[2];
		System.arraycopy(bin, 9, terminalCountryCode, 0, 2);
		t.setTerminalCountryCode(terminalCountryCode);

		t.setTerminalType(bin[11]);

		byte[] transactionCurrencyCode = new byte[2];
		System.arraycopy(bin, 12, transactionCurrencyCode, 0, 2);
		t.setTransactionCurrencyCode(transactionCurrencyCode);

		t.setTransactionCurrencyExponent(bin[14]);

		byte[] transactionReferenceCurrencyCode = new byte[2];
		System.arraycopy(bin, 15, transactionReferenceCurrencyCode, 0, 2);
		t.setTransactionReferenceCurrencyCode(transactionReferenceCurrencyCode);

		t.setTransactionReferenceCurrencyExponent(bin[17]);

		byte[] transactionReferenceCurrencyConversion = new byte[4];
		System.arraycopy(bin, 18, transactionReferenceCurrencyConversion, 0, 4);
		t.setTransactionReferenceCurrencyConversion(transactionReferenceCurrencyConversion);

		byte[] acquirerIdentifier = new byte[6];
		System.arraycopy(bin, 22, acquirerIdentifier, 0, 6);
		t.setAcquirerIdentifier(acquirerIdentifier);

		byte[] merchantCategoryCode = new byte[2];
		System.arraycopy(bin, 28, merchantCategoryCode, 0, 2);
		t.setMerchantCategoryCode(merchantCategoryCode);

		byte[] merchantIdentifier = new byte[15];
		System.arraycopy(bin, 30, merchantIdentifier, 0, 15);
		t.setMerchantIdentifier(merchantIdentifier);
		
		byte[] terminalIdentification = new byte[8];
		System.arraycopy(bin, 45, terminalIdentification, 0, 8);
		t.setTerminalIdentification(terminalIdentification);

		byte[] terminalRiskManagementData = new byte[8];
		System.arraycopy(bin, 53, terminalRiskManagementData, 0, 8);
		t.setTerminalRiskManagementData(terminalRiskManagementData);

		byte[] ifdSerialNumber = new byte[8];
		System.arraycopy(bin, 61, ifdSerialNumber, 0, 8);
		t.setIfdSerialNumber(ifdSerialNumber);

		byte[] authorizationResponseCodeList = new byte[20];
		System.arraycopy(bin, 69, authorizationResponseCodeList, 0, 20);
		t.setAuthorizationResponseCodeList(authorizationResponseCodeList);

		t.setMiscellaneousOptions(bin[89]);
		t.setMiscellaneousOptions1(bin[90]);
		// RFU*1
		byte[] lengthTLVData = new byte[2];
		System.arraycopy(bin, 92, lengthTLVData, 0, 2);
		t.setLengthTLVData(lengthTLVData);
		int tlv = t.getLengthTLVDataInt();

		if (0 != tlv) {
			byte[] tlvData = new byte[tlv];
			System.arraycopy(bin, 94, tlvData, 0, tlv);
			t.setTlvData(tlvData);
		}
		// RFU*20
		byte[] lengthOfflinePINEntryConfiguration = new byte[2];
		System.arraycopy(bin, 114 + tlv, lengthOfflinePINEntryConfiguration, 0, 2);
		t.setLengthOfflinePINEntryConfiguration(lengthOfflinePINEntryConfiguration);
		int off = t.getLengthOfflinePINEntryConfigurationInt();

		if (0 != off) {
			byte[] offlinePINEntryConfiguration = new byte[off];
			System.arraycopy(bin, 116 + tlv, offlinePINEntryConfiguration, 0, off);
			OfflinePINEntryConfiguration o = OfflinePINEntryConfiguration.fromBinary(offlinePINEntryConfiguration);
			t.setOfflinePINEntryConfiguration(o);
		}

		byte[] terminalLanguages = new byte[8];
		System.arraycopy(bin, 116 + tlv + off, terminalLanguages, 0, 8);
		t.setTerminalLanguages(terminalLanguages);
		// RFU*2*2

		byte[] lengthDiagnosticsTags = new byte[2];
		System.arraycopy(bin, 128 + tlv + off, lengthDiagnosticsTags, 0, 2);
		t.setLengthDiagnosticsTags(lengthDiagnosticsTags);
		int diag = t.getLengthDiagnosticsTagsInt();

		if (0 != diag) {
			byte[] diagnosticsTags = new byte[diag];
			System.arraycopy(bin, 130 + tlv + off, diagnosticsTags, 0, diag);
			t.setDiagnosticsTags(diagnosticsTags);
		}

		byte[] lengthAppSelectionTags = new byte[2];
		System.arraycopy(bin, 130 + tlv + off + diag, lengthAppSelectionTags, 0, 2);
		t.setLengthAppSelectionTags(lengthAppSelectionTags);
		int app = t.getLengthAppSelectionTagsInt();

		if (0 != app) {
			byte[] appSelectionTags = new byte[app];
			System.arraycopy(bin, 132 + tlv + off + diag, appSelectionTags, 0, app);
			t.setAppSelectionTags(appSelectionTags);
		}

		byte[] lengthRIDApps = new byte[2];
		System.arraycopy(bin, 132 + tlv + off + diag + app, lengthRIDApps, 0, 2);
		t.setLengthRIDApps(lengthRIDApps);
		int rid = t.getLengthRIDAppsInt();

		if (0 != rid) {
			byte[] ridApps = new byte[rid];
			System.arraycopy(bin, 134 + tlv + off + diag + app, ridApps, 0, rid);
			t.setRidApps(ridApps);
		}

		return t;
	}

	public int getLengthTLVDataInt() {
		return UTFUtils.littleEndian(lengthTLVData);
	}

	public int getLengthOfflinePINEntryConfigurationInt() {
		return UTFUtils.littleEndian(lengthOfflinePINEntryConfiguration);
	}

	public int getLengthDiagnosticsTagsInt() {
		return UTFUtils.littleEndian(lengthDiagnosticsTags);
	}

	public int getLengthAppSelectionTagsInt() {
		return UTFUtils.littleEndian(lengthAppSelectionTags);
	}

	public int getLengthRIDAppsInt() {
		return UTFUtils.littleEndian(lengthRIDApps);
	}

	public int totalLength() {
		return 134 + getLengthTLVDataInt() + getLengthOfflinePINEntryConfigurationInt() + getLengthDiagnosticsTagsInt()
				+ getLengthAppSelectionTagsInt() + getLengthRIDAppsInt();
	}
}
