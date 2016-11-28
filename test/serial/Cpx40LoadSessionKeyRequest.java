package serial;

import org.apache.commons.lang.StringUtils;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.37/184} {40. Load Master
 * Key/Session Key}
 * 
 * @author nfeng
 *
 */
public class Cpx40LoadSessionKeyRequest extends CpxRequest {

	/**
	 * 1 ASCII number, Name changed from "master key indicator" because key type
	 * reflects the physical master key slot used. This field is ignored when
	 * loading master keys (except when master key type is Atalla). When session
	 * keys are loaded, this field identifies the type of session key to load.
	 * "0" - encryption, "1" - MACing, "2" - decryption
	 */
	private String sessionKeyType;
	/**
	 * 1 ASCII, "0" – encryption, "1" – decryption, "2" – MACing, "3" - Atalla
	 * master key (U32 only). Not allowed in 2-level mode. "4" = Session key
	 */
	private String masterkeyType;
	/**
	 * Application/Terminal Based Single length: master key (KEK) or session
	 * key, 16 hex ASCII characters key e(KTK,KEK) or e(KEK,SK), without
	 * checkValue; Double length: master key (KEK_left) or session key + master
	 * key (KEK_right) or session key, with checkValue, check value to be
	 * verified after the key is loaded
	 */
	private String masterKeyOrSessionKey;
	/**
	 * 8 hex ASCII characters, check value to be verified after the key is
	 * loaded
	 */
	private String checkValue;
	/**
	 * KSN, 16 hex ASCII characters, serial number
	 */
	private String keySerialNumber;

	public String getSessionKeyType() {
		return sessionKeyType;
	}

	public void setSessionKeyType(String sessionKeyType) {
		this.sessionKeyType = sessionKeyType;
	}

	public String getMasterkeyType() {
		return masterkeyType;
	}

	public void setMasterkeyType(String masterkeyType) {
		this.masterkeyType = masterkeyType;
	}

	public String getMasterKeyOrSessionKey() {
		return masterKeyOrSessionKey;
	}

	public void setMasterKeyOrSessionKey(String masterKeyOrSessionKey) {
		if(StringUtils.isNotEmpty(masterKeyOrSessionKey)
				&& masterKeyOrSessionKey.length() > 32
				&& masterKeyOrSessionKey.length() <= 40) {
			this.masterKeyOrSessionKey = masterKeyOrSessionKey.substring(0, 32);
			this.setCheckValue(StringUtils.rightPad(masterKeyOrSessionKey.substring(32), 8, '0'));
		} else if(16 == masterKeyOrSessionKey.length()){
			this.masterKeyOrSessionKey = masterKeyOrSessionKey;
		} else {
			// wrong value
		}
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	public String getKeySerialNumber() {
		return keySerialNumber;
	}

	public void setKeySerialNumber(String keySerialNumber) {
		this.keySerialNumber = keySerialNumber;
	}

	public Cpx40LoadSessionKeyRequest() {
		super();
		this.setMessageType("40.");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getSessionKeyType();
		msg += this.getMasterkeyType();
		msg += this.getMasterKeyOrSessionKey();
		msg += StringUtils.defaultString(this.getCheckValue());
		msg += StringUtils.defaultString(this.getKeySerialNumber(), "0000000000000000");
		return msg;
	}
	
}
