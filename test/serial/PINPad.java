package serial;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import org.apache.commons.codec.binary.Hex;

public class PINPad {

	private static final int RESEND_TIMES = 3;
	private static final String SERIAL_PORT_OWNER = "Ingenico";
	private static final int SERIAL_PORT_OPEN_TIMEOUT = 2000;
	private static final int SERIAL_PORT_READ_TIMEOUT = 300;
	private static final int SERIAL_PORT_READ_POLL = 50;
	private static final String SERIAL_PORT_NAME = "COM9"; // windows=COM9,
															// ubuntu=/dev/ttyACM0
	private static String[] display = new String[] { "Hello World!",
			"nfeng@Active", "What time is it?", "" };
	private static SerialPort serialPort;
	private static PINPadListener listener;
	private static PINPad instance = null;

	private PINPad() {
	}

	public static PINPad getInstance() throws PortInUseException,
			TooManyListenersException, UnsupportedCommOperationException {
		// System.setProperty("gnu.io.rxtx.SerialPorts",
		// "/dev/ttyS0:/dev/ttyACM0");
		if (null != instance) {
			return instance;
		}
		instance = new PINPad();
		serialPort = getSerialPort(SERIAL_PORT_NAME);
		serialPort.notifyOnDataAvailable(true);
		serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		listener = new PINPadListener();
		serialPort.addEventListener(listener);
		instance.setListener(listener);
		return instance;
	}

	public SerialPort getSerialPort() {
		return serialPort;
	}

	public void setSerialPort(SerialPort serialPort) {
		this.serialPort = serialPort;
	}

	public PINPadListener getListener() {
		return listener;
	}

	public void setListener(PINPadListener listener) {
		this.listener = listener;
	}

	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm:ss");
		display[3] = sdf.format(new Date());

		// PINPad.getInstance().write(
		// new byte[] { 0x02, 0x46, 0x30, 0x2E, 0x40, 0x40, 0x50, 0x44,
		// 0x40, 0x40, 0x40, 0x41, 0x40, 0x03, 0x0E });

		// PINPad.getInstance().write(
		// new byte[] { 0x02, 0x46, 0x30, 0x2E, 0x40, 0x40, 0x50, 0x44,
		// 0x40, 0x40, 0x40, 0x42, 0x40, 0x03, 0x0D });

		// PINPad.getInstance().write(
		// new byte[] { 0x02, 0x46, 0x30, 0x2E, 0x40, 0x40, 0x50, 0x44,
		// 0x42, 0x7B, 0x60, 0x43, 0x40, 0x03, 0x15 });

		// PINPad.getInstance().write(UTFUtils.cpx58Display(display));
		// PINPad.getInstance().write(UTFUtils.cpx58Display(display));
		// PINPad.getInstance().write(UTFUtils.cpx59Clear(1));
		// PINPad.getInstance().write(UTFUtils.cpx5DDeviceInformation(null));

		PINPad.getInstance().write(
				UTFUtils.cmd((new Cpx50CancelRequest()).toString()));

		Cpx6KE2EEManualCardEntryRequest cpx6k = new Cpx6KE2EEManualCardEntryRequest();
		cpx6k.setLineNumber("1");
		// cpx6k.setPrompt1(String.format("%-32s", "KEY ACCOUNT #-OK"));
		cpx6k.setPrompt1("KEY ACCOUNT #-OK");
		cpx6k.setPrompt1index("10");
		// cpx6k.setPrompt2(String.format("%-32s", "KEY EXP DATE -OK"));
		cpx6k.setPrompt2("KEY EXP DATE -OK");
		cpx6k.setPrompt2index("12");
		PINPad.getInstance().write(UTFUtils.cmd(cpx6k.toString()));

		PINPad.getInstance().getSerialPort().close();
	}

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

	public static void write(byte[] data) throws IOException,
			InterruptedException {
		write(data, 3);
	}

	public static void write(byte[] data, int resend) throws IOException,
			InterruptedException {
		PrintUtil.print(data, true);
		serialPort.getOutputStream().write(data);
		long start = System.currentTimeMillis();
		boolean receivedACK = false;
		while (System.currentTimeMillis() - start < SERIAL_PORT_READ_TIMEOUT) {
			if (listener.getQ().isEmpty()) {
				Thread.sleep(SERIAL_PORT_READ_POLL);
				continue;
			} else {
				byte[] responseData = listener.getQ().poll();
				// System.out.println("<" + Hex.encodeHexString(responseData));
				if ((byte) 0x06 == responseData[0]) {
					PrintUtil.print(new byte[] { (byte) 0x06 }, false);
					if (responseData.length > 1) {
						byte[] dest = new byte[responseData.length - 1];
						System.arraycopy(responseData, 1, dest, 0,
								responseData.length - 1);
						PrintUtil.print(dest, false);
						asyncACK(dest);
					}
					receivedACK = true;
				} else {
					if((byte)0x46 == data[1]) {
						byte[] decoded = UTFUtils.decodeCmd(data);
						if(null != decoded
								&& decoded.length > 9
								&& (byte)0x04 == decoded[5]) {
							// async command
							byte[] respDecoded = UTFUtils.decodeCmd(responseData);
							if(null != respDecoded
									&& respDecoded.length >= 7
									&& respDecoded[5] == (byte)0x05
									&& respDecoded[6] == decoded[6]) {
								receivedACK = true;
							}
						}
					}
					PrintUtil.print(responseData, false);
					asyncACK(responseData);
				}
			}
		}
		if(!receivedACK && resend > 1) {
			write(data, resend - 1);
		}
	}
	
	public static void asyncACK(byte[] data) throws IOException {
		byte[] decoded = UTFUtils.decodeCmd(data);
		if(decoded.length > 7
				&& (byte)0x04 == decoded[5]) {
			serialPort.getOutputStream().write(UTFUtils.cmd((new CpxF1Request(CpxF1Command.asynEmvCmdAck(decoded[6])).toBinary())));
		}
	}

}
