package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AIDSpecificData {
	
	public byte[] toBinary() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] data = baos.toByteArray();
		baos.close();
		return data;
	}

}
