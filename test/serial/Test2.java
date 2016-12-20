package serial;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class Test2 {

	public static void main(String[] args) throws DecoderException {
		String log = "0246302e40404c474040400310";
		
		log = "0246312e4041704640404c41507444705073407057744570556665735063495f54455178515679675a5679650344";
		
		log = "0246312e4041704240505c41507444705073407057744570556665735063495f54455178515679675a5679650340";
		System.out.println(Hex.encodeHexString(UTFUtils.hexLog2bytes(log)));
		System.out.println(Hex.encodeHexString(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		System.out.println(new String(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		
		
		
	}

}
