package serial;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.TooManyListenersException;

public class Initialize implements Runnable, SerialPortEventListener {

	private static final int POLL_MILLIS = 31;
	private static CommPortIdentifier portId = null;
	private static SerialPort serialPort = null;
	private static InputStream inputStream = null;
	private static OutputStream outputStream = null;
	
	private static boolean waitForData = true;
	private static String output = null;
	
	private Thread readThread;

	public static void main(String[] args) {
		byte[] data = UTFUtils.cpx59Clear(0);
		System.out.println(write(data, "COM9"));
	}
	
	public static String write(byte[] data, String portName) {
		Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals(portName)) {
					try {
						serialPort = (SerialPort) portId.open("SimpleReadApp",
								2000);
					} catch (PortInUseException e) {
					}

					try {
						outputStream = serialPort.getOutputStream();
						inputStream = serialPort.getInputStream();
					} catch (IOException e) {
					}
					try {
						serialPort.setSerialPortParams(9600,
								SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
								SerialPort.PARITY_NONE);
					} catch (UnsupportedCommOperationException e) {
					}
					
					Initialize reader = new Initialize();
					
					try {
						outputStream.write(data);
					} catch (IOException e) {
					}
					break;
				}
			}
		}
		while(waitForData) {
			try {
				Thread.sleep(POLL_MILLIS);
			} catch (InterruptedException e) {
			}
		}
		return output;
	}

	public Initialize() {
		try {
			serialPort.addEventListener(this);
		} catch (TooManyListenersException e) {
		}
		serialPort.notifyOnDataAvailable(true);
		try {
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} catch (UnsupportedCommOperationException e) {
		}
		
		readThread = new Thread(this);
		readThread.start();
	}

	public void run() {
		while(waitForData) {
			try {
				Thread.sleep(POLL_MILLIS);
			} catch (InterruptedException e) {
			}
		}
		serialPort.removeEventListener();
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
			int templength = 0, totalLength = 0;
			ByteBuffer bb = ByteBuffer.allocateDirect(1024);
			try {
				while (inputStream.available() > 0) {
					templength = inputStream.read(readBuffer);
					bb.put(readBuffer);
					readBuffer = new byte[128];
					totalLength += templength;
				}
				bb.flip();
				byte[] totalData = new byte[totalLength];
				bb.get(totalData, 0, totalLength);
				output = UTFUtils.printFormat(totalData);
			} catch (IOException e) {
			}
			waitForData = false;
			break;
		}
	}
}
