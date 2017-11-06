package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 0142-07204-0503 Generic EMV API.pdf Page 35/167
 * 
 * @author nickfeng
 *
 */
public class AIDSpecificData {

	private List<AID> list = new ArrayList<AID>();

	public byte[] toBinary() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (AID item : list) {
			baos.write(item.toBinary());
		}
		byte[] data = baos.toByteArray();
		baos.close();
		return data;
	}

}
