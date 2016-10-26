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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.TooManyListenersException;

public class Clock implements Runnable, SerialPortEventListener {

	static String [] display = new String[]{"Hello World!", "nfeng@Active", "What time is it?", ""};
	
	Thread readThread;

	static CommPortIdentifier portId = null;
	static InputStream inputStream = null;
	static OutputStream outputStream = null;
	static SerialPort serialPort = null;
	static String output = null;

	public static void main(String[] args) {
		System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyS0:/dev/ttyACM0");
		//System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println(System.getProperty("os.name"));
		Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm:ss");

		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			System.out.println(portId.getName());
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals("/dev/ttyACM0")) {
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
					
					Clock reader = new Clock();
					
					while(true) {
						display[3] = sdf.format(new Date());
						try {
							outputStream.write(UTFUtils.cpx58Display(display));
						} catch (IOException e) {
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
						}
					}
				}
			}
		}
	}

	public Clock() {
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
				System.out.println(output);
			} catch (IOException e) {
			}
			break;
		}
	}
}
