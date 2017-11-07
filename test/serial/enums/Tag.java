package serial.enums;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;

/**
 * merchant-service/info/EMV/EMV_v4
 * .3_Book/EMV_v4.3_Book_3_Application_Specification_20120607062110791.pdf
 * 
 * @author nfeng
 *
 */
public class Tag {

	/**
	 * Page.143/230 Authorised amount of the transaction (excluding adjustments)
	 */
	public static final int t81_PRIMARY_AMOUNT_BINARY = 0x81;
	/**
	 * Page.144/230 Secondary amount associated with the transaction
	 * representing a cashback amount
	 */
	public static final int t9F04_SECONDARY_AMOUNT_BINARY = 0x9F04;

	public static final int t4F_AID = 0x4F;
	public static final int t50_APP_LABEL = 0x50;
	public static final int t57_TRACK2_DATA = 0x57;
	public static final int t5A_APP_PAN = 0x5A;
	public static final int t71_ISSUER_SCRIPT = 0x71;
	public static final int t72_ISSUER_SCRIPT = 0x72;
	public static final int t82_AIP = 0x82;
	public static final int t84_DEDICATED_FILENAME = 0x84;
	public static final int t89_APPROVAL_CODE = 0x89;
	public static final int t8A_AUTH_RESPONSE_CODE = 0x8A;
	public static final int t91_ISSUER_AUTH_DATA = 0x91;
	public static final int t95_TVR = 0x95;
	public static final int t9A_TRANSACTION_DATE = 0x9A;
	public static final int t9B_TSI = 0x9B;
	public static final int t9C_EMV_TXN_TYPE = 0x9C;
	public static final int t5F20_CARDHOLDER_NAME = 0x5F20;
	public static final int t5F24_EXPIRY_DATE = 0x5F24;
	public static final int t5F25_APP_EFFECT_DATE = 0x5F25;
	public static final int t5F28_ISSUER_COUNTRY_CODE = 0x5F28;
	public static final int t5F2A_CURRENCY_CODE = 0x5F2A;
	public static final int t5F30_SERVICE_CODE = 0x5F30;
	public static final int t5F34_APP_PAN_SEQ_NUMBER = 0x5F34;
	public static final int t9F02_PRIMARY_AMOUNT = 0x9F02;
	public static final int t9F03_SECONDARY_AMOUNT = 0x9F03;
	public static final int t9F06_AID_TERMINAL = 0x9F06;
	public static final int t9F07_APPLICATION_USAGE_CONTROL = 0x9F07;
	public static final int t9F08_ICC_APP_VERSION_NUMBER = 0x9F08;
	public static final int t9F09_ICC_APP_VERSION_NUMBER = 0x9F09;
	public static final int t9F0D_IAC_DEFAULT = 0x9F0D;
	public static final int t9F0E_IAC_DENIAL = 0x9F0E;
	public static final int t9F0F_IAC_ONLINE = 0x9F0F;
	public static final int t9F10_ISSUER_APP_DATA = 0x9F10;
	public static final int t9F11_ISSUER_CODETABLE = 0x9F11;
	public static final int t9F12_APP_PREFERRED_NAME = 0x9F12;
	public static final int t9F1A_TERMINAL_COUNTRY_CODE = 0x9F1A;
	public static final int t9F1E_IFD_SERIAL_NUMBER = 0x9F1E;
	public static final int t9F21_TRANSACTION_TIME = 0x9F21;
	public static final int t9F26_CRYPTOGRAM = 0x9F26;
	public static final int t9F27_CRYPTO_INFO_DATA = 0x9F27;
	public static final int t9F33_TERMINAL_CAPABILITIES = 0x9F33;
	public static final int t9F34_CVM = 0x9F34;
	public static final int t9F35_TERMINAL_TYPE = 0x9F35;
	public static final int t9F36_ATC = 0x9F36;
	public static final int t9F37_UNPREDICTABLE_NUMBER = 0x9F37;
	public static final int t9F39_POS_ENTRY_MODE = 0x9F39;
	public static final int t9F41_TRANSACTION_SEQUENCE_COUNTER = 0x9F41;
	public static final int t9F5B_ISSUER_SCRIPT_RESULTS = 0x9F5B;
	public static final int t9F66_VISA_TTQ = 0x9F66;
	public static final int t9F6C_VISA_CTQ = 0x9F6C;
	public static final int t9F6E_PROPRIETARY_BRAND_DATA = 0x9F6E;
	public static final int t9F7C_VISA_CUSTOMER_EXCLUSIVE_DATA = 0x9F7C;
	public static final int tDF53_PROPRIETARY_TAG = 0xDF53;
	public static final int tDF54_PROPRIETARY_TAG = 0xDF54;
	public static final int tDF55_PROPRIETARY_TAG = 0xDF55;
	public static final int tDF56_PROPRIETARY_TAG = 0xDF56;
	public static final int tDF57_PROPRIETARY_TAG = 0xDF57;
	public static final int tDF58_PROPRIETARY_TAG = 0xDF58;
	public static final int tDF59_PROPRIETARY_TAG = 0xDF59;
	public static final int t9F26_1ST_CRYPTOGRAM = 0x19F26;
	public static final int t9F27_1ST_CRYPTO_INFO_DATA = 0x19F27;

	/**
	 * 2 bytes
	 */
	private int id;
	/**
	 * 2 bytes
	 */
	private int length;
	/**
	 * Hex String
	 */
	private String value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Tag() {
	}

	public Tag(int id, String value) {
		super();
		this.setId(id);
		this.setValue(value);
		this.setLength(this.getValue().length() / 2);
	}
	
	public Tag(int id) {
		super();
		this.setId(id);
	}
	
	public Tag(byte[] idBin) {
		super();
		int id = idBin[0] * 0x100 + idBin[1];
		this.setId(id);
	}
	
	public byte[] getIdBin() {
		byte[] idBin = new byte[2];
		idBin[0] = (byte) (getId() / 0x100);
		idBin[1] = (byte) (getId() % 0x100);
		return idBin;
	}
	
	public static byte[] buildTLV(Tag t) throws DecoderException {
		int size = 2 + 2 + t.getValue().length() / 2;
		byte[] tlv = new byte[size];
		tlv[0] = (byte) (t.getId() / 0x100);
		tlv[1] = (byte) (t.getId() % 0x100);
		tlv[2] = (byte) (t.getLength() / 0x100);
		tlv[3] = (byte) (t.getLength() % 0x100);
		System.arraycopy(Hex.decodeHex(t.getValue().toCharArray()), 0, tlv, 4,
				t.getValue().length() / 2);
		return tlv;
	}

	public static byte[] buildTLVList(List<Tag> list) throws IOException, DecoderException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (Tag tag : list) {
			baos.write(Tag.buildTLV(tag));
		}
		byte[] data = baos.toByteArray();
		baos.close();
		return data;
	}
	
	public static Tag parseTLV(String hexData) {
		Tag t = new Tag();
		t.setId(Integer.parseInt(hexData.substring(0, 4), 16));
		t.setLength(Integer.parseInt(hexData.substring(4, 8), 16));
		t.setValue(hexData.substring(8, 8 + t.getLength() * 2));
		return t;
	}

	public static List<Tag> parseTLVList(String hexData) {
		String str = hexData;
		List<Tag> list = new ArrayList<Tag>();
		Tag t = null;
		while(StringUtils.isNotEmpty(str)) {
			t = parseTLV(str);
			list.add(t);
			str = str.substring(4 + 4 + t.getLength() * 2);
		}
		return list;
	}
	
	public static List<Tag> fromBinaryToIdList(byte[] bin) {
		List<Tag> list = new ArrayList<Tag>();
		int size = bin.length / 2;
		for(int i = 0; i < size; i++) {
			byte[] idBin = new byte[2];
			System.arraycopy(bin, 0+i*2, idBin, 0, 2);
			Tag t = new Tag(idBin);
			list.add(t);
		}
		return list;
	}

}
