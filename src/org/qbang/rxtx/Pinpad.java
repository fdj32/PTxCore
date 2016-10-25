package org.qbang.rxtx;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentLinkedQueue;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import serial.UTFUtils;

public class Pinpad {

	public Pinpad() {
		try {
			serialPort = getSerialPort(SERIAL_PORT_NAME);
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			PinpadListener listener = new PinpadListener(this);
			serialPort.addEventListener(listener);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static final String SERIAL_PORT_OWNER = "Ingenico";
	private static final int SERIAL_PORT_OPEN_TIMEOUT = 2000;
	private static final int SERIAL_PORT_READ_TIMEOUT = 8000;
	private static final int SERIAL_PORT_POLL_TIME = 100;
	private static final String SERIAL_PORT_NAME = "COM9";

	private SerialPort serialPort;
	private ConcurrentLinkedQueue<byte[]> dataQ = new ConcurrentLinkedQueue<byte[]>();

	public ConcurrentLinkedQueue<byte[]> getDataQ() {
		return dataQ;
	}

	public void setDataQ(ConcurrentLinkedQueue<byte[]> dataQ) {
		this.dataQ = dataQ;
	}

	public SerialPort getSerialPort() {
		return serialPort;
	}

	public void setSerialPort(SerialPort serialPort) {
		this.serialPort = serialPort;
	}

	public SerialPort getSerialPort(String portName) throws PortInUseException {
		Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals(portName)) {
					return (SerialPort) portId.open(SERIAL_PORT_OWNER, SERIAL_PORT_OPEN_TIMEOUT);
				}
			}
		}
		return null;
	}

	public void write(byte[] data) throws Exception {
		serialPort.getOutputStream().write(data);
	}

	public void read() throws Exception {
		long start = System.currentTimeMillis();
		byte[] data = null;
		while ((System.currentTimeMillis() - start) < SERIAL_PORT_READ_TIMEOUT) {
			if(null == (data = dataQ.poll())) {
				Thread.sleep(SERIAL_PORT_POLL_TIME);
				System.out.print(".");
			} else {
				System.out.println("\n"+UTFUtils.printFormat(data));
				start = System.currentTimeMillis();
			}
		}
		System.out.println("\nread end");
	}

}
