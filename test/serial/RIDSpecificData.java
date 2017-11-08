package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

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
	
	public Element element() {
		Element r = DocumentHelper.createElement("RIDSpecificData");
		for(RID rid : list) {
			r.add(rid.element());
		}
		return r;
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
