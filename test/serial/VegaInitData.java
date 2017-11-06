package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 0142-07204-0503 Generic EMV API.pdf Page 23/167
 * 
 * @author nickfeng
 *
 */
public class VegaInitData {

	private TerminalSpecificData tsd;
	private RIDSpecificData rsd;
	private AIDSpecificData asd;

	public TerminalSpecificData getTsd() {
		return tsd;
	}

	public void setTsd(TerminalSpecificData tsd) {
		this.tsd = tsd;
	}

	public RIDSpecificData getRsd() {
		return rsd;
	}

	public void setRsd(RIDSpecificData rsd) {
		this.rsd = rsd;
	}

	public AIDSpecificData getAsd() {
		return asd;
	}

	public void setAsd(AIDSpecificData asd) {
		this.asd = asd;
	}

	public VegaInitData() {
		super();
	}

	public VegaInitData(TerminalSpecificData tsd, RIDSpecificData rsd, AIDSpecificData asd) {
		super();
		this.tsd = tsd;
		this.rsd = rsd;
		this.asd = asd;
	}

	public byte[] toBinary() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] data = tsd.toBinary();
		baos.write(UTFUtils.lengthSwap(data.length));
		baos.write(data);

		data = rsd.toBinary();
		baos.write(UTFUtils.lengthSwap(data.length));
		baos.write(data);

		data = asd.toBinary();
		baos.write(UTFUtils.lengthSwap(data.length));
		baos.write(data);
		
		baos.write(0x00); // TODO : I didn't find out the specification.

		data = baos.toByteArray();

		baos.close();
		return data;
	}

}
