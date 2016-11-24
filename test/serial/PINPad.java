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

public class PINPad {

	private static final String SERIAL_PORT_OWNER = "Ingenico";
	private static final int SERIAL_PORT_OPEN_TIMEOUT = 2000;
	private static final int SERIAL_PORT_READ_TIMEOUT = 500;
	private static final int SERIAL_PORT_READ_POLL = 50;
	private static final String SERIAL_PORT_NAME = "/dev/ttyACM0"; // windows=COM9,
																	// ubuntu=/dev/ttyACM0
	private static String[] display = new String[] { "Hello World!",
			"nfeng@Active", "What time is it?", "" };
	private static SerialPort serialPort;
	private static PINPadListener listener;
	private static PINPad instance = null;

	private PINPad() {}

	public static PINPad getInstance() throws PortInUseException,
			TooManyListenersException, UnsupportedCommOperationException {
		System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyS0:/dev/ttyACM0");
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
		PINPad.getInstance().write(UTFUtils.cpx58Display(display));
		//PINPad.getInstance().write(UTFUtils.cpx58Display(display));
		//PINPad.getInstance().write(UTFUtils.cpx59Clear(1));
		//PINPad.getInstance().write(UTFUtils.cpx5DDeviceInformation(null));
		serialPort.close();
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

	public static void write(byte[] data) throws IOException, InterruptedException {
		serialPort.getOutputStream().write(data);
		long start = System.currentTimeMillis();
		boolean timeout = true;
		while (System.currentTimeMillis() - start < SERIAL_PORT_READ_TIMEOUT) {
			if (listener.getQ().isEmpty()) {
				System.out.println("listener queue is empty");
				Thread.sleep(SERIAL_PORT_READ_POLL);
				continue;
			} else {
				byte[] responseData = listener.getQ().poll();
				System.out.println("PINPad poll : "
						+ UTFUtils.printFormat(responseData));
				timeout = false;
			}
		}
		if(timeout) {
			serialPort.close();
		}
	}

}
