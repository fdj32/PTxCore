package trust;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.binary.Hex;

import serial.UTFUtils;

public class AppleTest {

	public static void main(String[] args) throws Exception {
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		String [] lines = new String[] {"Goodjob,", "    Nick!"};
		byte[] bin = UTFUtils.cpx58Display(lines);
		System.out.println(new String(bin));
		String hex = Hex.encodeHexString(bin);
		System.out.println(hex);
		
		String a = "Hello";
		a = a.replace('e', (char)0x00);
		System.out.println(a);
		
		System.out.println(a.getBytes().length);
		
		String b = "@ApB@@\\APtDpPs@pWtEpUfesPcI_TEQxQVygZVye";
		
		String c = UTFUtils.cpxP16Encode(b);
		
		System.out.println(c);
		System.out.println(Hex.encodeHexString(c.getBytes()));
		
		System.out.println(Hex.encodeHex("Hello world".getBytes()));
		
		System.out.println(UTFUtils.calculateLRC(("Hello world".getBytes())));
		
	}

}
