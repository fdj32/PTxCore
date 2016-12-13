package serial;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;

import serial.enums.Tag;

public class Test1 {

	public static void main(String[] args) throws DecoderException, UnsupportedEncodingException {
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
		
		System.out.println("--------------------------------------------------------------------------------");
		
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
		
		System.out.println("--------------------------------------------------------------------------------");
		
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
		
		System.out.println("--------------------------------------------------------------------------------");
		
		log = "02 46 31 2E 40 41 70 42  40 50 40 41 50 74 44 70"
				+ "50 73 40 70 57 74 45 70  55 66 65 73 50 63 49 5F"
				+ "54 45 51 78 51 56 79 67  5A 56 79 65 03 5C";
		
		System.out.println(Hex.encodeHexString(UTFUtils.hexLog2bytes(log)));
		System.out.println(Hex.encodeHexString(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		System.out.println(new String(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		CpxF1Response cpxF1Resp = CpxF1Response.parse(new String(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		System.out.println(Hex.encodeHexString(cpxF1Resp.getRst().getStatus().getBytes()));
		System.out.println(cpxF1Resp.getRst().geteAppName());
		System.out.println(cpxF1Resp.getRst().getpAppName());
		
		log = "02 46 31 2E 40 43 68 44  40 40 40 41 50 63 49 5F"
				+ "54 45 51 78 51 56 79 67  5A 56 79 65 50 74 44 70"
				+ "50 73 40 70 57 74 45 70  55 66 65 73 46 5F 7C 5A"
				+ "40 4F 7C 44 40 40 44 40  40 48 44 40 41 40 40 40"
				+ "40 7E 62 5F 41 40 40 44  40 40 40 40 40 49 7D 5A"
				+ "40 40 44 40 03 7D";
		
		System.out.println(Hex.encodeHexString(UTFUtils.hexLog2bytes(log)));
		System.out.println(Hex.encodeHexString(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		System.out.println(new String(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		// 19ff1a00ff0400010000810004000003e89f040004000000009f5a000100
		// 19 ff 1a00 ff04 0001 00 0081 0004 000003e8 9f04 0004 00000000 9f5a 0001 00
		// 0142-07204-0503%20Generic%20EMV%20API.pdf Page.106/167
		// IngenicoCPXVega.BuildEmvStartContactless
		System.out.println(Hex.encodeHexString(new byte[]{(byte)0x9f, (byte)0x5a}));
		System.out.println(Hex.encodeHexString(Hex.decodeHex("9f5a".toCharArray())));
		
		Tag t = new Tag(0xff04, "00");
		System.out.println(Hex.encodeHexString(Tag.buildTLV(t)));
		t = new Tag(0x81, "000003e8");
		System.out.println(Hex.encodeHexString(Tag.buildTLV(t)));
		t = new Tag(0x9f04, "00000000");
		System.out.println(Hex.encodeHexString(Tag.buildTLV(t)));
		t = new Tag(0x9f5a, "00");
		System.out.println(Hex.encodeHexString(Tag.buildTLV(t)));
		
		t = Tag.parseTLV("ff04000100");
		System.out.println(t.getLength());
		
		List<Tag> tagList = Tag.parseTLVList("ff0400010000810004000003e89f040004000000009f5a000100");
		for(Tag tag : tagList) {
			System.out.println(Hex.encodeHexString(Tag.buildTLV(tag)));
		}
		
		log = "02 46 31 2E 40 41 6C 43  40 50 45 42 4C 65 7D 50"
				+ "55 47 61 45 5B 66 5D 69  5B 66 55 43 50 53 41 43"
				+ "4C 43 41 5F 50 57 41 56  5A 57 4C 03 32";
		System.out.println(Hex.encodeHexString(UTFUtils.hexLog2bytes(log)));
		System.out.println(Hex.encodeHexString(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		System.out.println(new String(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		System.out.println(Hex.encodeHex(UTFUtils.cmd((new CpxF1Request(CpxF1Command.cpxF1CloseSession("\u0001"))).toString())));
		
		log = "02 35 38 2E 30 30 34 31  20 20 20 20 20 20 20 20"
				+ "20 20 20 20 20 20 20 20  50 6C 65 61 73 65 20 57"
				+ "61 69 74 20 20 20 20 20  20 20 20 20 20 20 20 20"
				+ "20 20 20 20 20 20 20 20  20 20 20 20 20 20 20 20"
				+ "20 20 20 20 20 20 20 20  03 20";
		System.out.println(Hex.encodeHexString(UTFUtils.hexLog2bytes(log)));
		Cpx58DisplayMode01Request cpx58 = new Cpx58DisplayMode01Request();
		cpx58.setMode("0");
		cpx58.setToggle("0");
		cpx58.setLines("4");
		cpx58.setLineStartIndex("1");
		cpx58.setPrompt2("Please Wait");
		System.out.println(Hex.encodeHex(UTFUtils.cmd(cpx58.toString())));
		
		log = "02 46 31 2E 40 43 64 44  40 40 40 41 50 63 49 5F"
				+ "54 45 51 78 51 56 79 67  5A 56 79 65 50 74 44 70"
				+ "50 73 40 70 57 74 45 70  55 66 65 73 40 6F 7C 59"
				+ "40 4F 7E 5F 4E 50 40 41  41 5F 7C 44 40 40 44 40"
				+ "67 74 44 40 41 40 40 40  40 40 45 5F 4A 60 40 42"
				+ "42 44 40 03 27";
		System.out.println(Hex.encodeHexString(UTFUtils.hexLog2bytes(log)));
		System.out.println(Hex.encodeHexString(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		System.out.println(new String(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		byte[] emvData = UTFUtils.hexLog2bytes("02ff1900ff9f39000105ff040001009f410004000000015f2a00020840");
		byte[] cmdData = CpxF1Command.cpxF1AsyncEmvData("\u0000", emvData).toBinary();
		System.out.println(Hex.encodeHexString(cmdData));
		System.out.println(Hex.encodeHexString(UTFUtils.cpxP16Encode(cmdData)));
		System.out.println(Hex.encodeHexString(UTFUtils.cpxP16Decode(UTFUtils.cpxP16Encode(cmdData))));
		
		byte[] out = new byte[cmdData.length*2];
		int outlen = 0;
		outlen = UTFUtils.cpxP16Encode(cmdData, cmdData.length, out, 0);
		byte[] encoded = new byte[outlen];
		System.arraycopy(out, 0, encoded, 0, outlen);
		System.out.println(Hex.encodeHexString(encoded));
		System.out.println(outlen);
		
		
		
		String s = new String(new byte[]{(byte)0xff});
		System.out.println(Hex.encodeHexString(s.getBytes()));
		System.out.println(Hex.encodeHexString("\u00ff".getBytes()));
		System.out.println(String.format("%x", (byte)0xff));
		
		
		
		
		System.out.println(Hex.encodeHexString(UTFUtils.cpxP16Encode(new byte[]{0, 0x39, 0x04})));
		
		//System.out.println(Hex.encodeHexString(new CpxF1Request(CpxF1Command.cpxF1AsyncEmvData("\u0000", emvData)).toBinary()));
		
	}

}
