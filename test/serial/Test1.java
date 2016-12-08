package serial;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;

public class Test1 {

	public static void main(String[] args) throws DecoderException {
//		System.out.println((char)(0x1B));
//		ByteBuffer bb = ByteBuffer.allocate(0);
//		bb.put((byte)0x01);
//		System.out.println(bb.array().length);
//		System.out.println((int)'1' >  (int)'0');
//		byte[] data = UTFUtils.cpx59Clear(0);
//		String out = SimpleRead.write(data, "COM9");
//		System.out.println(out);
		System.out.println(StringUtils.rightPad("12345678901234567890123456789012345678901234567890".substring(32), 8, '0'));
		System.out.println(StringUtils.defaultString(null));
		System.out.println(UTFUtils.FS.getBytes().length);
		System.out.println((new String(UTFUtils.FS.getBytes())).getBytes().length);
		byte b = (byte)0;
		String bs = new String(new byte[]{UTFUtils.FS.getBytes()[0], b, UTFUtils.FS.getBytes()[0]});
		System.out.println(bs);
		System.out.println(bs.getBytes().length);
		
		byte[] inputBuffer = "helloworld".getBytes();
		int inputBufferLength = inputBuffer.length;
		byte[] outputBuffer = new byte[1024];
		int outputBufferIndex = 0;
		outputBufferIndex = UTFUtils.cpxP16Encode(inputBuffer, inputBufferLength, outputBuffer, outputBufferIndex);
		System.out.println(outputBufferIndex);
		byte[] dest = new byte[outputBufferIndex];
		System.arraycopy(outputBuffer, 0, dest, 0, outputBufferIndex);
		System.out.println(new String(dest));
		
		byte[] decode = UTFUtils.cpxP16Decode(dest, 0, outputBufferIndex);
		System.out.println(new String(decode));
		
		System.out.println(UTFUtils.cpxP16Decode(UTFUtils.cpxP16Encode("activenetwork")));
		
		System.out.println(UTFUtils.cpxP16Encode("hello").getBytes().length);
		System.out.println(Hex.encodeHex(UTFUtils.cpxP16Encode("hello").getBytes()));
		System.out.println(UTFUtils.cpxP16Encode("hello".getBytes()).length);
		System.out.println(Hex.encodeHex(UTFUtils.cpxP16Encode("hello".getBytes())));
		
		System.out.println(Hex.encodeHex(new byte[]{(byte)(0xFF >> 2)}));
		System.out.println(Hex.encodeHex(new byte[]{(byte)(0xFF << 2)}));
		
		System.out.println(Hex.encodeHex(new byte[]{(byte)(0xFF >> 2 | 0x40)}));
		System.out.println(Hex.encodeHex(new byte[]{(byte)((0xFF << 6) >> 2)}));
		System.out.println(Hex.encodeHex(new byte[]{(byte)(((0xFF << 6) >> 2) | 0x40)}));
		
		System.out.println(new String(UTFUtils.cpxP16Decode(UTFUtils.cpxP16Encode("activenetwork".getBytes()))));
		System.out.println(CpxF1Command.OPEN_SESSION.length());
		
		String log = "02 36 49 2E 33 34 35 31  31 35 50 55 52 43 48 41";
		log = log.replace("  ", " ");
		String[] arr = log.split(" ");
		byte[] data = new byte[arr.length];
		for(int i = 0; i < arr.length; i++) {
			data[i] = (byte)(Integer.parseInt(arr[i], 16));
		}
		System.out.println(Hex.encodeHexString(data));
		
		log = "02 36 49 2E 33 34 35 31  31 35 50 55 52 43 48 41"
				+ "53 45 20 20 20 20 20 20  20 20 1C 24 31 30 2E 30"
				+ "30 20 20 20 20 20 20 20  20 20 20 1C 49 6E 73 65"
				+ "72 74 2C 20 54 61 70 20  6F 72 20 20 1C 53 77 69"
				+ "70 65 20 43 61 72 64 20  20 20 20 20 20 1C 03 5A";
		System.out.println(Hex.encodeHexString(UTFUtils.hexLog2bytes(log)));
		System.out.println(new String(UTFUtils.trimCmd(UTFUtils.hexLog2bytes(log))));
		// "6I.345115PURCHASE        $10.00          Insert, Tap or  Swipe Card      "
		Cpx6IE2EEActivateMSRRequest cpx6IReq = new Cpx6IE2EEActivateMSRRequest();
		cpx6IReq.setTrackNumber("3");
		cpx6IReq.setTimeout("45");
		cpx6IReq.setFunctionKeysActive("1");
		cpx6IReq.setLinesOrTimeDelay("1");
		cpx6IReq.setLineNumber("5");
		cpx6IReq.setPrompt1("PURCHASE");
		cpx6IReq.setPrompt2("$10.00");
		cpx6IReq.setPrompt3("Insert, Tap or");
		cpx6IReq.setPrompt4("Swipe Card");
		System.out.println(cpx6IReq);
		
		System.out.println(Hex.encodeHexString(UTFUtils.cmd(cpx6IReq.toString())));
		
		System.out.println(Hex.encodeHexString(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes("02 46 30 2E 40 40 50 47  40 40 40 44 03 48"))));
		
		System.out.println(Hex.encodeHexString(UTFUtils.cmd((new CpxF0Request(CpxF0Command.cpxF0WaitForSmartCardInsertion(0))).toString())));
		
		log = "02 46 31 2E 40 41 6C 42  40 50 45 42 4C 65 7D 50"
				+ "55 47 61 45 5B 66 5D 69  5B 66 55 43 50 53 41 43"
				+ "4C 43 41 5F 50 57 41 56  5A 57 4C 03 33";
		System.out.println(Hex.encodeHexString(UTFUtils.hexLog2bytes(log)));
		System.out.println(Hex.encodeHexString(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		System.out.println(new String(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		System.out.println(Hex.encodeHexString(UTFUtils.lgt(27, 2)));
		System.out.println(Hex.encodeHexString(CpxF1Command.cpxF1OpenSession("\u0001").toString().getBytes()));
		System.out.println((new CpxF1Request(CpxF1Command.cpxF1OpenSession("\u0001"))).toString());
		System.out.println(Hex.encodeHexString((new CpxF1Request(CpxF1Command.cpxF1OpenSession("\u0001"))).toString().getBytes()));
		System.out.println(Hex.encodeHexString(UTFUtils.cmd((new CpxF1Request(CpxF1Command.cpxF1OpenSession("\u0001"))).toString())));
		
	}

}
