package org.qbang.rxtx;

import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentLinkedQueue;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import serial.UTFUtils;
import serial.enums.EmvReasonCode;
import serial.enums.EmvServiceCode;

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
	private static final String SERIAL_PORT_NAME = "/dev/tty.usbmodem1411";
	private static final int MAX_VEGA_PACKET_SIZE = 498;

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
	
	public void init(byte[] initData) {
		int initDataIndex = 0;
		boolean result = true; // CPX F1 Async EMV Data result
		while(initDataIndex < initData.length && result) {
			byte[] dataPacket = new byte[MAX_VEGA_PACKET_SIZE];
			int initPacketSize = MAX_VEGA_PACKET_SIZE - 1;
			int dataPacketSize = 0;
			if(initData.length - initDataIndex > initPacketSize) {
				dataPacketSize = initPacketSize;
				dataPacket[0] = 0x01; // More to Follow
			} else { // Last Packet
				dataPacketSize = initData.length - initDataIndex;
				dataPacket[0] = 0x00; // No more to Follow
			}
			System.arraycopy(initData, initDataIndex, dataPacket, 1, dataPacketSize);
			result = cpxF1AsyncEmvData(EmvServiceCode.EMV_INIT, EmvReasonCode.EMV_UNDEF, dataPacket, true);
			initDataIndex += dataPacketSize;
		}
	}
	
	public boolean cpxF1AsyncEmvData(int emvServiceCode, int emvReasonCode, byte[] data, boolean waitForResponse) {
		// TODO
		return true;
	}
	
	public byte[] write(byte[] data, boolean waitForResponse, int tryTimes) throws IOException, InterruptedException {
		long start = System.currentTimeMillis();
		byte[] response = null;
		for(int i = 0; i < tryTimes; i ++) {
			serialPort.getOutputStream().write(data);
			boolean gotACK = false;
			boolean gotResponse = false;
			while((System.currentTimeMillis() - start) < SERIAL_PORT_READ_TIMEOUT) {
				if(null == (response = dataQ.poll())) {
					Thread.sleep(SERIAL_PORT_POLL_TIME);
System.out.println(".");
				} else if(1 == response.length && UTFUtils.ACK[0] == response[0]) {
System.out.println("Got ACK");
					// receive ACK
					gotACK = true;
					if(!waitForResponse)
						break;
				} else if(response.length > 1) {
System.out.println("Got Response:" + UTFUtils.printFormat(response));
					// receive response
					gotResponse = true;
					break;
				}
				start = System.currentTimeMillis();
			}
			if(waitForResponse) {
				if(gotACK && gotResponse) break;
			} else {
				if(gotACK) break;
			}
		}
		return response;
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
