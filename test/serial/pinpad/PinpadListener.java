package serial.pinpad;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PinpadListener implements Runnable, SerialPortEventListener {
	
	private SerialPort serialPort;
	private DataObject dtObj;
	public PinpadListener(SerialPort serialPort, DataObject dtObj) {
		this.serialPort = serialPort;
		this.dtObj = dtObj;
	}

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
			byte[] readBuffer = new byte[128];
			int templength = 0, totalLength = 0, startIndex = 0;
			ByteBuffer bb = ByteBuffer.allocateDirect(1024);
			try {
				while (serialPort.getInputStream().available() > 0) {
					templength = serialPort.getInputStream().read(readBuffer);
					bb.put(readBuffer);
					readBuffer = new byte[128];
					totalLength += templength;
				}
				bb.flip();
				// ignore ACK=[06]
				if(1 == totalLength && ((byte)0x06) == bb.get(0)) {
					return;
				}
				if(((byte)0x06) == bb.get(0)) {
					totalLength--;
					startIndex++;
				}
				byte[] totalData = new byte[totalLength];
				bb.get(totalData, startIndex, totalLength);
				String recvData = UTFUtils.printFormat(totalData);
				dtObj.setRecvData(recvData);
//				System.out.println(recvData);
				serialPort.getOutputStream().write(UTFUtils.ACK); // write ACK back
			} catch (IOException e) {
			}
			dtObj.setWaitForData(false);
			break;
		}
	}

	@Override
	public void run() {
		while(dtObj.isWaitForData()) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.print(".");
		}
	}

}
