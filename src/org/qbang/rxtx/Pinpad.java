package org.qbang.rxtx;

import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.codec.binary.Hex;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import serial.CpxF1Command;
import serial.CpxF1Request;
import serial.CpxRequest;
import serial.CpxResponse;
import serial.UTFUtils;
import serial.VegaEmvInitReq;

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
	private static final int MAX_TRY_SEND_TIMES = 3;
	private static final String SERIAL_PORT_NAME = "/dev/tty.usbmodem1411";
	private static final int MAX_VEGA_PACKET_SIZE = 498;
	
	private int cpxSeqId = 0;
	private SerialPort serialPort;
	private byte[] tempResp = null;
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
	
	public void init(byte[] initData) throws Exception {
		if(!openSession())
			return;
		int initDataIndex = 0;
		boolean result = true; // CPX F1 Async EMV Data result
		while(initDataIndex < initData.length && result) {
			boolean bMoreDataToCome = false;
			byte[] dataPacket = new byte[MAX_VEGA_PACKET_SIZE - 1];
			int dataPacketSize = 0;
			if(initData.length - initDataIndex > dataPacket.length) {
				dataPacketSize = dataPacket.length;
				bMoreDataToCome = true; // More to Follow
			} else { // Last Packet
				dataPacketSize = initData.length - initDataIndex;
				bMoreDataToCome = false; // No more to Follow
			}
			System.arraycopy(initData, initDataIndex, dataPacket, 0, dataPacketSize);
			VegaEmvInitReq initReq = new VegaEmvInitReq(bMoreDataToCome, dataPacket);
System.out.println(Hex.encodeHexString(dataPacket));
System.out.println(Hex.encodeHexString(initReq.toBinary()));
			result = cpxF1AsyncEmvData(initReq, true);
			initDataIndex += dataPacketSize;
		}
		if(!closeSession())
			return;
	}
	
	public boolean sendCpxRequestForBool(CpxRequest req, boolean waitForResponse) throws Exception {
		byte[] data = req.toString().getBytes();
		System.out.println(Hex.encodeHexString(data));
		data = UTFUtils.cmd(data);
		System.out.println(Hex.encodeHexString(data));
		try {
			write(data, waitForResponse, MAX_TRY_SEND_TIMES);
		} catch (Exception e) {
			return false;
		}
		CpxResponse resp = CpxResponse.parse(new String(tempResp));
		// send ACK
		ack();
		// send Async EMV ACK
		asyncEmvACK((byte) cpxSeqId);
		cpxSeqId++;
		return null != resp && resp.succeed();
	}
	
	public boolean openSession() throws Exception {
		CpxF1Command cmd = CpxF1Command.cpxF1OpenSession((byte) cpxSeqId);
		CpxRequest req = new CpxF1Request(cmd);
		return sendCpxRequestForBool(req, true);
	}
	
	public boolean closeSession() throws Exception {
		CpxF1Command cmd = CpxF1Command.cpxF1CloseSession((byte) cpxSeqId);
		CpxRequest req = new CpxF1Request(cmd);
		return sendCpxRequestForBool(req, true);
	}
	
	public boolean cpxF1AsyncEmvData(VegaEmvInitReq initReq, boolean waitForResponse) throws Exception {
		CpxF1Command cmd = CpxF1Command.cpxF1AsyncEmvData((byte) cpxSeqId, initReq.toBinary());
		CpxF1Request req = new CpxF1Request(cmd);
		return sendCpxRequestForBool(req, true);
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
System.out.print(".");
				} else if(1 == response.length) {
					if(UTFUtils.ACK[0] == response[0]) {
System.out.println("Got ACK");
						// receive ACK
						gotACK = true;
						if(!waitForResponse)
							break;
					}
System.out.println("Got " + Hex.encodeHexString(response));

				} else if(response.length > 1) {
System.out.println("Got Response:" + UTFUtils.printFormat(response));
					// receive response
					gotResponse = true;
					tempResp = response;
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
	
	public void ack() throws Exception {
		write(UTFUtils.ACK);
	}
	
	public void asyncEmvACK(byte inSeqId) throws Exception {
		CpxF1Command cmd = CpxF1Command.asynEmvCmdAck(inSeqId);
		CpxF1Request req = new CpxF1Request(cmd);
		byte[] data = req.toString().getBytes();
		data = UTFUtils.cmd(data);
		write(data);
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
