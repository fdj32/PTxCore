package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 0142-07204-0503 Generic EMV API.pdf Page 29/167
 * 
 * @author nickfeng
 *
 */
public class RIDSpecificData {

	private List<RID> list = new ArrayList<RID>();

	public List<RID> getList() {
		return list;
	}

	public void setList(List<RID> list) {
		this.list = list;
	}

	public byte[] toBinary() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (RID item : list) {
			baos.write(item.toBinary());
		}
		byte[] data = baos.toByteArray();
		baos.close();
		return data;
	}
	
	public static RIDSpecificData fromBinary(byte[] bin) {
		List<RID> list = RID.fromBinaryToList(bin);
		RIDSpecificData rsd = new RIDSpecificData();
		rsd.setList(list);
		return rsd;
	}

}
