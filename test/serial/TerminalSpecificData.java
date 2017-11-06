package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
		baos.write(diagnosticsTags);
		baos.write(lengthAppSelectionTags);
		baos.write(appSelectionTags);
		baos.write(lengthRIDApps);
		baos.write(ridApps);
		byte[] data = baos.toByteArray();
		baos.close();
		return data;
	}

}
