package serial;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;

import serial.enums.AidCandidate;
import serial.enums.Tag;
import serial.enums.Vega;

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
		System.out.println(Hex.encodeHexString(CpxF1Command.cpxF1OpenSession((byte)0x01).toString().getBytes()));
		System.out.println((new CpxF1Request(CpxF1Command.cpxF1OpenSession((byte)0x01))).toString());
		System.out.println(Hex.encodeHexString((new CpxF1Request(CpxF1Command.cpxF1OpenSession((byte)0x01))).toString().getBytes()));
		System.out.println(Hex.encodeHexString(UTFUtils.cmd((new CpxF1Request(CpxF1Command.cpxF1OpenSession((byte)0x01))).toString())));
		
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
		System.out.println(Hex.encodeHex(UTFUtils.cmd((new CpxF1Request(CpxF1Command.cpxF1CloseSession((byte)0x01))).toString())));
		
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
		byte[] cmdData = CpxF1Command.cpxF1AsyncEmvData((byte)0, emvData).toBinary();
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
		
		System.out.println(Hex.encodeHexString(UTFUtils.cmd(new CpxF1Request(CpxF1Command.cpxF1AsyncEmvData((byte)0, emvData)).toBinary())));
		
		log = "02 36 43 2E 30 31 33 31  39 30 30 31 53 65 6C 65"
				+ "63 74 20 4C 61 6E 67 75  61 67 65 20 1E 46 31 3D"
				+ "4E 65 78 74 20 46 32 3D  50 72 65 76 20 1E 46 31"
				+ "3D 4E 65 78 74 20 20 20  20 20 20 20 20 20 1E 46"
				+ "32 3D 50 72 65 76 20 20  20 20 20 20 20 20 20 1E"
				+ "45 6E 67 6C 69 73 68 20  20 20 20 20 20 20 20 20"
				+ "1E 46 72 61 6E 63 61 69  73 20 20 20 20 20 20 20"
				+ "20 1E 03 18";
		System.out.println(Hex.encodeHexString(UTFUtils.hexLog2bytes(log)));
		System.out.println(Hex.encodeHexString(UTFUtils.trimCmd(UTFUtils.hexLog2bytes(log))));
		System.out.println(new String(UTFUtils.trimCmd(UTFUtils.hexLog2bytes(log))));
		// "6C.01319001Select Language F1=Next F2=Prev F1=Next         F2=Prev         English         Francais        "
		
		Cpx6CScrollSelectRequest cpx6c = new Cpx6CScrollSelectRequest();
		cpx6c.setCommandMode("0");
		cpx6c.setNextFunctionKey("1");
		cpx6c.setPreviousFunctionKey("3");
		cpx6c.setShowImages("1");
		cpx6c.setTimeout("9");// 45/5
		cpx6c.setInvalidBeep("0");
		cpx6c.setDefaultSelection("01");
		cpx6c.setTitleString("Select Language");
		cpx6c.setNextOrPreviousString("F1=Next F2=Prev");
		cpx6c.setPrevOnlyString("F1=Next");
		cpx6c.setNextOnlyString("F2=Prev");
		cpx6c.setSelectListStrings(new String[]{"English", "Francais"});
		System.out.println(cpx6c.toString());
		
		log = "02 46 31 2E 40 42 48 44  40 50 40 41 50 63 49 5F"
				+ "54 45 51 78 51 56 79 67  5A 56 79 65 50 74 44 70"
				+ "50 73 40 70 57 74 45 70  55 66 65 73 41 50 44 42"
				+ "40 46 55 6E 03 4B";
		System.out.println(Hex.encodeHexString(UTFUtils.hexLog2bytes(log)));
		System.out.println(Hex.encodeHexString(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		System.out.println(new String(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		System.out.println(Hex.encodeHexString(UTFUtils.cmd(new CpxF1Request(CpxF1Command.cpxF1AsyncEmvData((byte)0x01, Vega.EMV_SELECT_LANGUAGE_EN)).toBinary())));
		
		log = "02 46 31 2E 40 44 48 44  60 60 40 41 50 74 44 70"
				+ "50 73 40 70 57 74 45 70  55 66 65 73 50 63 49 5F"
				+ "54 45 51 78 51 56 79 67  5A 56 79 65 40 7F 7C 62"
				+ "40 42 45 50 59 57 49 73  5B 76 79 61 5B 42 41 41"
				+ "58 76 4D 6F 5D 56 79 74  42 64 44 70 4C 43 40 70"
				+ "4C 43 40 70 4C 63 54 70  4C 53 40 78 4C 43 44 03"
				+ "26";
		System.out.println(Hex.encodeHexString(UTFUtils.hexLog2bytes(log)));
		System.out.println(Hex.encodeHexString(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		System.out.println(new String(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		emvData = Vega.selectApplicationRequest(new AidCandidate[]{new AidCandidate("Personal Account", "A000000025010801")});
		System.out.println(Hex.encodeHexString(emvData));
		cmdData = CpxF1Command.cpxF1AsyncEmvData((byte)0x82, emvData).toBinary();
		System.out.println(Hex.encodeHexString(cmdData));
		System.out.println(Hex.encodeHexString(UTFUtils.cmd(new CpxF1Request(CpxF1Command.cpxF1AsyncEmvData((byte)0x82, emvData)).toBinary())));
		
		log = "02 46 31 2E 40 43 60 44  60 70 40 41 50 74 44 70"
				+ "50 73 40 70 57 74 45 70  55 66 65 73 50 63 49 5F"
				+ "54 45 51 78 51 56 79 67  5A 56 79 65 45 4F 7C 58"
				+ "40 40 40 40 40 40 40 40  40 40 40 40 50 60 45 41"
				+ "40 75 78 43 47 70 48 40  40 40 40 40 40 40 40 40"
				+ "40 40 03 63";
		System.out.println(Hex.encodeHexString(UTFUtils.hexLog2bytes(log)));
		System.out.println(Hex.encodeHexString(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		System.out.println(new String(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		// 0142-07204-0503%20Generic%20EMV%20API.pdf Page.84/167
		
		log = "02 46 31 2E 40 60 48 44  61 40 40 41 50 74 44 70"
				+ "50 73 40 70 57 74 45 70  55 66 65 73 50 63 49 5F"
				+ "54 45 51 78 51 56 79 67  5A 56 79 65 44 60 4F 62"
				+ "40 50 41 4F 40 40 62 60  40 40 40 40 49 50 44 48"
				+ "40 50 41 50 40 41 41 50  59 57 49 73 5B 76 79 61"
				+ "5B 42 41 41 58 76 4D 6F  5D 56 79 74 40 45 5C 40"
				+ "44 70 40 40 40 40 40 40  40 40 40 40 40 40 40 40"
				+ "40 40 40 40 40 40 40 40  40 40 40 40 56 60 40 48"
				+ "4D 74 49 45 40 40 40 40  40 41 7C 40 60 60 40 42"
				+ "57 40 40 40 61 40 40 48  68 40 40 40 40 42 54 41"
				+ "42 40 44 40 66 60 40 43  45 61 48 46 40 49 70 40"
				+ "40 50 41 5F 48 40 40 5A  50 54 78 60 50 54 55 49"
				+ "54 45 4C 60 4C 53 40 60  48 42 40 60 48 42 40 60"
				+ "48 42 40 60 48 42 40 60  48 42 41 5F 49 40 40 43"
				+ "45 51 48 71 57 72 60 40  40 60 60 66 57 72 68 40"
				+ "40 60 61 40 57 73 50 40  40 50 42 5F 41 60 40 48"
				+ "68 40 40 40 40 42 54 41  42 40 46 5F 41 70 40 42"
				+ "7F 70 42 5F 43 50 40 45  7C 45 43 6C 68 44 42 5F"
				+ "43 60 40 45 40 40 40 40  40 40 42 5F 43 70 40 45"
				+ "7C 4F 63 7C 7E 40 42 5F  46 60 40 42 40 52 52 5F"
				+ "47 60 40 48 54 45 40 78  4C 53 50 73 4D 63 56 5F"
				+ "4C 70 40 43 78 4B 63 48  67 73 54 40 40 52 4A 5F"
				+ "4E 50 40 41 41 5F 7C 5D  40 4F 5C 73 4D 73 50 72"
				+ "4D 43 54 70 4C 43 40 71  4C 53 54 71 4C 53 54 71"
				+ "4C 63 48 70 4C 53 40 72  4D 64 45 4E 48 44 45 45"
				+ "52 55 41 53 48 43 44 70  48 42 40 60 48 42 40 60"
				+ "48 42 40 60 48 42 40 60  48 42 40 60 4C 54 49 46"
				+ "51 64 59 46 4C 63 40 70  51 53 49 45 4C 73 48 71"
				+ "4C 44 54 70 4C 43 44 76  51 43 40 71 4C 53 50 73"
				+ "4D 73 48 70 4D 73 58 70  4E 53 50 73 4C 63 5C 77"
				+ "4D 63 54 72 4C 73 74 70  4C 63 40 73 4C 53 54 76"
				+ "4D 43 48 74 4E 43 5C 71  4C 63 48 79 4C 43 54 70"
				+ "4C 53 60 73 4C 64 58 73  4C 54 58 72 4C 44 4C 77"
				+ "50 73 64 78 4D 63 40 72  4C 63 41 42 4E 44 50 79"
				+ "51 54 44 70 4D 53 58 77  51 54 58 74 4E 43 55 45"
				+ "4E 53 59 42 4E 53 5D 44  4C 53 49 46 4C 73 49 43"
				+ "4D 43 4D 43 50 73 5D 44  50 74 48 71 4D 54 58 77"
				+ "4D 73 48 73 4C 64 4D 46  4C 44 51 41 4D 74 51 44"
				+ "51 43 55 41 4D 44 54 72  4E 44 59 44 4C 43 50 77"
				+ "4E 43 40 74 4E 44 50 78  4D 63 41 44 4E 44 45 42"
				+ "50 63 55 41 4D 74 4C 70  51 53 50 78 4C 64 58 73"
				+ "51 43 44 74 4E 53 61 44  51 54 48 71 51 44 44 74"
				+ "50 54 51 44 4D 43 55 45  51 63 59 43 4C 54 58 71"
				+ "51 54 58 70 03 52";
		System.out.println(Hex.encodeHexString(UTFUtils.hexLog2bytes(log)));
		System.out.println(Hex.encodeHexString(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		System.out.println(new String(UTFUtils.decodeCmd(UTFUtils.hexLog2bytes(log))));
		
		List<Tag> list = Tag.parseTLVList("004f0008a00000002501080100500010506572736f6e616c204163636f756e740057001300000000000000000000000000000000000000005a0008374245000000001f008200025c0000840008a000000025010801009a0003161206009c0001005f20001a414e2041454950532031302020202020202020202020202020205f2400031512315f28000208265f2a000208405f340001009f060008a0000000250108019f070002ff009f0d0005f050eca0409f0e000500000000009f0f0005f0f8fcf8009f1a000201249f1e000850503831343336359f330003e0b8c89f350001229f39000105ff1d00f73337343234353030303131353131353132323031303236414e204145495053203130202020202020202020202020202020314246464646323030453245333231304530303136443031313433373230373630393433323737363532333d30323033313536343234383731323239303530313833324633314632304337433938363032323042384439454130353637454634383545393642393744313246333243343343433744434231354637373233324346304441374444443541344532384644303437383034384438363044384142423541374330453438324633443134393844454231444134414444343545463643314631454630");
		
		for(Tag tag : list) {
			System.out.println(String.format("%x", tag.getId()) + ":" + tag.getLength() + ":" + tag.getValue());
		}
	}

}
