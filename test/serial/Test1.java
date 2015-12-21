package serial;

import java.nio.ByteBuffer;

public class Test1 {

	public static void main(String[] args) {
//		System.out.println((char)(0x1B));
//		ByteBuffer bb = ByteBuffer.allocate(0);
//		bb.put((byte)0x01);
//		System.out.println(bb.array().length);
//		System.out.println((int)'1' >  (int)'0');
		byte[] data = UTFUtils.cpx59Clear(0);
		String out = SimpleRead.write(data, "COM9");
		System.out.println(out);
	}

}
