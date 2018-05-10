package com.heartlandportico.hps.ddf;

import org.apache.commons.lang.StringUtils;

public class FileHeader {

	/**
	 * 1-2
	 */
	private String recordFormat = "MH";

	/**
	 * 3-4
	 */
	private String recordType = "CH";

	/**
	 * 5-10, YYMMDD
	 */
	private String fileCreationDate;

	/**
	 * 11-16, HHMMSS
	 */
	private String fileCreationTime;

	/**
	 * 17-22, YYMMDD. Date of Activity
	 */
	private String effectiveDate;

	/* Filler, 23-30, AN 8, Reserved Spaces. */

	/**
	 * 31-34, ‘HPS’
	 */
	private String creatingCompany;

	/* Filler, 35-44, AN 10, Reserved Spaces. */

	/**
	 * 45-49, Corporate Merchant Chain Number
	 */
	private String clientMerchantChain;

	/**
	 * 50-79, Corporate Name
	 */
	private String clientMerchantChainName;

	/* Filler, 80-260, AN 181, Reserved Spaces. */

	public String getRecordFormat() {
		return recordFormat;
	}

	public void setRecordFormat(String recordFormat) {
		this.recordFormat = recordFormat;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getFileCreationDate() {
		return fileCreationDate;
	}

	public void setFileCreationDate(String fileCreationDate) {
		this.fileCreationDate = fileCreationDate;
	}

	public String getFileCreationTime() {
		return fileCreationTime;
	}

	public void setFileCreationTime(String fileCreationTime) {
		this.fileCreationTime = fileCreationTime;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getCreatingCompany() {
		return creatingCompany;
	}

	public void setCreatingCompany(String creatingCompany) {
		this.creatingCompany = creatingCompany;
	}

	public String getClientMerchantChain() {
		return clientMerchantChain;
	}

	public void setClientMerchantChain(String clientMerchantChain) {
		this.clientMerchantChain = clientMerchantChain;
	}

	public String getClientMerchantChainName() {
		return clientMerchantChainName;
	}

	public void setClientMerchantChainName(String clientMerchantChainName) {
		this.clientMerchantChainName = clientMerchantChainName;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(recordFormat);
		sb.append(recordType);
		sb.append(fileCreationDate);
		sb.append(fileCreationTime);
		sb.append(effectiveDate);
		sb.append(StringUtils.repeat(" ", 8));
		sb.append(creatingCompany);
		sb.append(StringUtils.repeat(" ", 10));
		sb.append(clientMerchantChain);
		sb.append(clientMerchantChainName);
		sb.append(StringUtils.repeat(" ", 181));
		return sb.toString();
	}

	public static FileHeader fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 260) {
			return null;
		}
		FileHeader o = new FileHeader();
		o.setRecordFormat(s.substring(0, 2));
		o.setRecordType(s.substring(2, 4));
		o.setFileCreationDate(s.substring(4, 10));
		o.setFileCreationTime(s.substring(10, 16));
		o.setEffectiveDate(s.substring(16, 22));
		o.setCreatingCompany(s.substring(30, 34));
		o.setClientMerchantChain(s.substring(44, 49));
		o.setClientMerchantChainName(s.substring(49, 79));
		return o;
	}

}
