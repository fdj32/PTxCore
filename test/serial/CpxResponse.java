package serial;

import org.apache.commons.lang.StringUtils;

public class CpxResponse extends CpxMessage {

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static CpxResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
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
		}
		// 40 50 59 5B 6A 6B.ERROR 6C.ERROR 6N 6T.1
		resp = new CpxResponse();
		resp.setStatus(str.substring(3, 4));
		return resp;
	}

}
