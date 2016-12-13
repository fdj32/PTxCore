package serial.enums;

public class Vega {

	public static final String ENGLISH = "en";
	public static final String FRENCH = "fr";
	public static final byte[] EMV_SELECT_LANGUAGE_EN = new byte[] {
			(byte) EmvServiceCode.EMV_SELECT_LANGUAGE,
			(byte) EmvReasonCode.EMV_OK, 0x02, 0, (byte) 'e', (byte) 'n' };
	public static final byte[] EMV_SELECT_LANGUAGE_FR = new byte[] {
			(byte) EmvServiceCode.EMV_SELECT_LANGUAGE,
			(byte) EmvReasonCode.EMV_OK, 0x02, 0, (byte) 'f', (byte) 'r' };

}
