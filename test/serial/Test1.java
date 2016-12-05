package serial;

import java.nio.ByteBuffer;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;

public class Test1 {

	public static void main(String[] args) {
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
	}

}
