package serial.pinpad;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.util.Enumeration;
import java.util.Random;

public class PinpadWriter {

	private static final String SERIAL_PORT_OWNER = "Ingenico";
	private static final int SERIAL_PORT_OPEN_TIMEOUT = 2000;
	private static final String SERIAL_PORT_NAME = "COM9";

	public static SerialPort getSerialPort(String portName)
			throws PortInUseException {
		Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			CommPortIdentifier portId = (CommPortIdentifier) portList
					.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals(portName)) {
					return (SerialPort) portId.open(SERIAL_PORT_OWNER,
							SERIAL_PORT_OPEN_TIMEOUT);
				}
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		byte [] data = null;
		String recvData = null;
		
		data = UTFUtils.cpx58Display(new String[] { "ActiveNetwork",
				"" + new Random().nextInt() });
		recvData = write(data, false);
		System.out.println(recvData);
		
		data = UTFUtils.cpx5DDeviceInformation("4");
		recvData = write(data, true);
		System.out.println(recvData);
		
//		DataObject dtObj = new DataObject();
//		SerialPort serialPort = getSerialPort(SERIAL_PORT_NAME);
//		serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
//				SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
//		PinpadListener listener = new PinpadListener(serialPort, dtObj);
//		serialPort.addEventListener(listener);
//		serialPort.notifyOnDataAvailable(true);
//		serialPort.getOutputStream().write(data);
//		Thread thread = new Thread(listener);
//		thread.start();
//		thread.join();
//		System.out.println(dtObj.getRecvData());
//		serialPort.removeEventListener();
		
	}

	public static String write(byte[] data, boolean waitForData) throws Exception {
		DataObject dtObj = new DataObject();
		SerialPort serialPort = getSerialPort(SERIAL_PORT_NAME);
		serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		PinpadListener listener = null;
		if(waitForData) {
			listener = new PinpadListener(serialPort, dtObj);
			serialPort.addEventListener(listener);
			serialPort.notifyOnDataAvailable(true);
		}
		serialPort.getOutputStream().write(data);
		if(waitForData) {
			Thread thread = new Thread(listener);
			thread.start();
			thread.join();
			serialPort.removeEventListener();
		}
//		System.out.println(dtObj.getRecvData());
		serialPort.close();
		return dtObj.getRecvData();
	}

}
