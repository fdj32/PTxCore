package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-07748-0103 Telium CPX and EMV Emulation.pdf Page 164/195
 * 
 * @author nickfeng
 *
 */
public class CpxResponse extends CpxMessage {

	/**
	 * <ul>
	 * <li>0x00=Success</li>
	 * <li>0x01= Failed(General Failure – i.e., Incorrect header (more than LGT
	 * bytes))</li>
	 * <li>0x02=Failed(Specified EMV Application not found))</li>
	 * <li>0x04=Failed(Unknown MSGVER)</li>
	 * </ul>
	 */
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean succeed() {
		return null != status && 0x00 == status.charAt(0);
	}

	/**
	 * STX+cpx16Encode+ETX+LRC
	 * @param str
	 * @return
	 */
	public static CpxResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		int len = str.length();
		
		str = str.substring(1, 4) + UTFUtils.cpxP16Decode(str.substring(4, len-2));
		if ((3 + 1) > str.length() || '.' != str.charAt(2)) {
			return null;
		}
		CpxResponse resp = null;
		if (str.startsWith("31.")) {
			return Cpx31DukptpinEncryptionResponse.parse(str);
		} else if (str.startsWith("51.")) {
			return Cpx51InquireSerialResponse.parse(str);
		} else if (str.startsWith("53.")) {
			return Cpx53DiagnosticKeyCheckwordResponse.parse(str);
		} else if (str.startsWith("58.")) {
			if ((3 + 1 + 1) == str.length()) {
				return Cpx58DisplayMode01Response.parse(str);
			} else {
				return Cpx58DisplayMode27Response.parse(str);
			}
		} else if (str.startsWith("5D.")) {
			return Cpx5DDeviceInformationResponse.parse(str);
		} else if (str.startsWith("64.")) {
			return Cpx64MacCalculationResponse.parse(str);
		} else if (str.startsWith("66.")) {
			return Cpx66MacVerificationResponse.parse(str);
		} else if (str.startsWith("67.")) {
			return Cpx67ActivateMsrResponse.parse(str);
		} else if (str.startsWith("6B.0")) {
			// only success response
			return Cpx6BInteracDebitSequenceResponse.parse(str);
		} else if (str.startsWith("6C.0")) {
			// only success response
			return Cpx6CScrollSelectResponse.parse(str);
		} else if (str.startsWith("6D.")) {
			return Cpx6DTimedMultiDisplayResponse.parse(str);
		} else if (str.startsWith("6H.")) {
			return Cpx6HMasterSessionPinDataEntryResponse.parse(str);
		} else if (str.startsWith("6K.")) {
			return Cpx6KE2EEManualCardEntryResponse.parse(str);
		} else if (str.startsWith("6L.")) {
			return Cpx6LE2EEPinEntryResponse.parse(str);
		} else if (str.startsWith("F0.")) {
			return CpxF0Response.parse(str);
		} else if (str.startsWith("F1.")) {
			return CpxF1Response.parse(str);
		}
		// 40 50 59 5B 6A 6B.ERROR 6C.ERROR 6N 6T.1
		resp = new CpxResponse();
		resp.setStatus(str.substring(3, 4));
		return resp;
	}

}
