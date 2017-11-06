package trust;

import java.io.IOException;

import serial.TerminalSpecificData;
import serial.UTFUtils;

public class UTFTest {

	public static void main(String[] args) throws IOException {
		System.out.println(UTFUtils.printFormat(UTFUtils.lgt(1422, 2)));
		System.out.println(UTFUtils.printFormat(UTFUtils.lengthSwap(1422)));
		System.out.println(UTFUtils.printFormat(UTFUtils.lgt(5, 1)));
		
		TerminalSpecificData tsd = new TerminalSpecificData();
		System.out.println(UTFUtils.printFormat(tsd.toBinary()));
	}

}
