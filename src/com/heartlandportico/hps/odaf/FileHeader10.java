package com.heartlandportico.hps.odaf;

import org.apache.commons.lang.StringUtils;

public class FileHeader10 {

	/**
	 * 1-10, Refer to Control Section – Record Type = '10'
	 */
	private ControlSection cs;

	/**
	 * 11-14, ‘DAF’
	 */
	private String fileTitle;

	/* Filler, 15-16, AN 2, Space Filled. */

	/**
	 * 17-22, External Client or Merchant assigned number
	 */
	private String chainNumber;

	/**
	 * 23-30, Business date of the processed transactions - YYYYMMDD
	 */
	private String hpsProcessingDate;

	/**
	 * 31-38, Date the file was created - YYYYMMDD
	 */
	private String fileCreationDate;

	/**
	 * 39-44, Time the file was created - HHMMSS
	 */
	private String fileCreationTime;

	/* Filler, 45, AN 1, Space Filled. */

	/**
	 * 46-75, Client or merchant name
	 */
	private String clientName;

	/* Filler, 76-250, AN 175, Space Filled. */

	public ControlSection getCs() {
		return cs;
	}

	public void setCs(ControlSection cs) {
		this.cs = cs;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getChainNumber() {
		return chainNumber;
	}

	public void setChainNumber(String chainNumber) {
		this.chainNumber = chainNumber;
	}

	public String getHpsProcessingDate() {
		return hpsProcessingDate;
	}

	public void setHpsProcessingDate(String hpsProcessingDate) {
		this.hpsProcessingDate = hpsProcessingDate;
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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(cs.toString());
		sb.append(fileTitle);
		sb.append(StringUtils.repeat(" ", 2));
		sb.append(chainNumber);
		sb.append(hpsProcessingDate);
		sb.append(fileCreationDate);
		sb.append(fileCreationTime);
		sb.append(' ');
		sb.append(clientName);
		sb.append(StringUtils.repeat(" ", 175));
		return sb.toString();
	}

	public static FileHeader10 fromString(String s) {
		if (StringUtils.isEmpty(s) || s.length() != 250) {
			return null;
		}
		FileHeader10 o = new FileHeader10();
		o.setCs(ControlSection.fromString(s.substring(0, 10)));
		o.setFileTitle(s.substring(10, 14));
		o.setChainNumber(s.substring(16, 22));
		o.setHpsProcessingDate(s.substring(22, 30));
		o.setFileCreationDate(s.substring(30, 38));
		o.setFileCreationTime(s.substring(38, 44));
		o.setClientName(s.substring(45, 75));
		return o;
	}

}
