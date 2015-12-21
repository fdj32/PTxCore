package serial;

import java.util.Locale;
import java.util.ResourceBundle;

public class Message {
	
	private static final String BASE_NAME = "message";
	private static ResourceBundle RB_EN = ResourceBundle.getBundle(BASE_NAME, Locale.ENGLISH);
	private static ResourceBundle RB_FR = ResourceBundle.getBundle(BASE_NAME, Locale.CANADA_FRENCH);
	private static ResourceBundle RB_JP = ResourceBundle.getBundle(BASE_NAME, Locale.JAPAN);
	private static ResourceBundle RB_CN = ResourceBundle.getBundle(BASE_NAME, Locale.CHINA);
//	private static Locale HEBREW = new Locale("he", "IL");
//	private static ResourceBundle RB_HE = ResourceBundle.getBundle(BASE_NAME, HEBREW);
	
	public static void main(String[] args) {
		System.out.println(RB_EN.getString("APPROVED"));
		System.out.println(RB_FR.getString("APPROVED"));
//		Locale HEBREW = new Locale("he", "IL");
//		ResourceBundle RB_HE = ResourceBundle.getBundle(BASE_NAME, HEBREW);
		Locale HEBREW = new Locale("he");
		ResourceBundle RB_HE = ResourceBundle.getBundle(BASE_NAME, HEBREW);
		System.out.println(RB_HE.getString("APPROVED"));
//		System.out.println(RB_JP.getString("APPROVED"));
		System.out.println(RB_CN.getString("APPROVED"));
//		System.out.println("中国");
	}

}
