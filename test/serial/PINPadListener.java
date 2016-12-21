package serial;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PINPadListener implements SerialPortEventListener {
	
	private static final int READ_BUFFER_LENGTH = 1024;
	
	private ConcurrentLinkedQueue<byte[]> q = new ConcurrentLinkedQueue<byte[]>();

	public ConcurrentLinkedQueue<byte[]> getQ() {
		return q;
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			byte[] buffer = null;
			int available = 0, totalLength = 0;
			ByteBuffer bb = ByteBuffer.allocateDirect(1024);
			SerialPort port = (SerialPort) (event.getSource());
			InputStream is = null;
			OutputStream os = null;
			
			try {
				is = port.getInputStream();
				os = port.getOutputStream();
				while((available = is.available()) > 0) {
					buffer = new byte[available];
					is.read(buffer, 0, available);
					bb.put(buffer);
					totalLength += available;
				}
				bb.flip();
				byte[] totalData = new byte[totalLength];
				bb.get(totalData, 0, totalLength);
				this.getQ().add(totalData);
				if(1 != totalData.length) {
					// Ignore ACK, NAK
					os.write(UTFUtils.ACK);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}

}
