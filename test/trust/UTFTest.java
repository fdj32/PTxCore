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
		System.out.println(UTFUtils.printFormat(UTFUtils.lgt(1422, 2)));
		System.out.println(UTFUtils.printFormat(UTFUtils.littleEndian(1422)));
		System.out.println(UTFUtils.printFormat(UTFUtils.littleEndian(18620)));
		System.out.println(UTFUtils.printFormat(UTFUtils.littleEndian(809)));
		System.out.println(UTFUtils.printFormat(UTFUtils.lgt(5, 1)));
		
		byte[] bin = IOUtils.toByteArray(new FileInputStream(new File("/Users/nickfeng/Hub/fdj32/PTxCore/doc/131540886937708660.init.dat")));
		
		//System.out.println(Hex.encodeHexString(bin));
		
		VegaInitData v = VegaInitData.fromBinary(bin);
		
		//System.out.println(new Gson().toJson(v));
		
//		System.out.println(v.getTsd().element().asXML());
		
//		System.out.println(v.getRsd().element().asXML());
		
//		System.out.println(v.getAsd().element().asXML());
		
		System.out.println(v.element().asXML());
	}

}
