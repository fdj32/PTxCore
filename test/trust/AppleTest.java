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
	}

}
