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
		case SerialPortEvent.BI: /* Break Interrupt, 通讯中断 */
		case SerialPortEvent.OE: /* Overrun Error,溢位错误 */
		case SerialPortEvent.FE: /* Framing Error,传帧错误 */
		case SerialPortEvent.PE: /* Parity Error,校验错误 */
		case SerialPortEvent.CD: /* Carrier detect,载波检测 */
		case SerialPortEvent.CTS: /* Clear To Send,清除发送 */
		case SerialPortEvent.DSR: /* Data Set Ready,数据设备就绪 */
		case SerialPortEvent.RI: /* Ring Indicator,响铃指示 */
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY: /* Output Buffer is Empty,输出缓冲区清空 */
			break;
		case SerialPortEvent.DATA_AVAILABLE: /* Data Available at the serial port,端口有可用数据。读到缓冲数组，输出到终端。 */
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
