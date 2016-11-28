package serial;

import org.apache.commons.lang.StringUtils;

public class Cpx5DDeviceInformationResponse extends CpxResponse {

	/**
	 * n ASCII Characters
	 */
	private String informationString;

	public String getInformationString() {
		return informationString;
	}

	public void setInformationString(String informationString) {
		this.informationString = informationString;
	}
	
	public Cpx5DDeviceInformationResponse() {
		super();
		this.setMessageType("5D.");
	}
	
	public static Cpx5DDeviceInformationResponse parse(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		if ((3 + 1 + 1) > str.length() || !str.startsWith("5D.")) {
			return null;
		}
		Cpx5DDeviceInformationResponse resp = new Cpx5DDeviceInformationResponse();
		resp.setStatus(str.substring(3, 4));
		resp.setInformationString(str.substring(4));
		return resp;
	} 
}
