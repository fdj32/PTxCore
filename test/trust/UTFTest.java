package trust;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import serial.UTFUtils;
import serial.VegaInitData;

public class UTFTest {

	public static void main(String[] args) throws IOException {
//		System.out.println(UTFUtils.printFormat(UTFUtils.lgt(1422, 2)));
//		System.out.println(UTFUtils.printFormat(UTFUtils.littleEndian(1422)));
//		System.out.println(UTFUtils.printFormat(UTFUtils.littleEndian(18620)));
//		System.out.println(UTFUtils.printFormat(UTFUtils.littleEndian(809)));
//		System.out.println(UTFUtils.printFormat(UTFUtils.lgt(5, 1)));
		
		byte[] bin = IOUtils.toByteArray(new FileInputStream(new File("/Users/nickfeng/Git/active-emv/ConsoleTest/bin/Debug/131546874042950770.init.dat")));
		
		String hex = Hex.encodeHexString(bin);
		
		bin = IOUtils.toByteArray(new FileInputStream(new File("/Users/nickfeng/Git/active-emv/ConsoleTest/bin/Debug/131546863971950160.init.dat")));
		
		String hex3 = Hex.encodeHexString(bin);
		
		System.out.println(hex.equals(hex3));
		
		System.out.println(indent(hex));
		
		VegaInitData v = VegaInitData.fromBinary(bin);
		
		String tsd = Hex.encodeHexString(v.getTsd().toBinary());
		
//		System.out.println("tsd:" + hex.indexOf(tsd));
		
//		System.out.println(tsd);
		
		String offline = Hex.encodeHexString(v.getTsd().getOfflinePINEntryConfiguration().toBinary());
		
//		System.out.println("offline:" + hex.indexOf(offline));
		
		String rsd = Hex.encodeHexString(v.getRsd().toBinary());
		
//		System.out.println("rsd:" + hex.indexOf(rsd));
		
		String asd = Hex.encodeHexString(v.getAsd().toBinary());
		
//		System.out.println("asd:" + hex.indexOf(asd));
		
		byte[] bin2 = v.toBinary();
		
		String hex2 = Hex.encodeHexString(bin2);
		
//		System.out.println(indent(hex2));
		
		System.out.println(hex.equals(hex2));
		
//		System.out.println(new Gson().toJson(v));
		
//		System.out.println(v.getTsd().element().asXML());
		
//		System.out.println(v.getRsd().element().asXML());
		
//		System.out.println(v.getAsd().element().asXML());
		
//		System.out.println(v.element().asXML());
	}
	
	public static String indent(String s) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < s.length(); i++) {
			if(i % 64 == 0)
				sb.append(System.lineSeparator());
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}
	

}
