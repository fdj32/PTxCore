package serial;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;


public class SimpleWrite {
	static byte[] deviceInfo = new byte[]{0x02, 0x35, 0x44, 0x2e, 0x03, 0x5c};
	
	static byte[] beep = new byte[] { 0x02, 0x35, 0x42, 0x2e, 0x33, 0x33, 0x03, 0x5a };

    public static void main(String[] args) {
    	Enumeration portList = CommPortIdentifier.getPortIdentifiers();
    	CommPortIdentifier portId = null;
    	SerialPort serialPort = null;
    	OutputStream outputStream = null;
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                 if (portId.getName().equals("COM9")) {
//                if (portId.getName().equals("/dev/term/a")) {
                    try {
                        serialPort = (SerialPort)
                            portId.open("SimpleWriteApp", 2000);
                    } catch (PortInUseException e) {}
                    try {
                        outputStream = serialPort.getOutputStream();
                    } catch (IOException e) {}
                    try {
                        serialPort.setSerialPortParams(9600,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);
                    } catch (UnsupportedCommOperationException e) {}
                    try {
                        outputStream.write(deviceInfo);
                    } catch (IOException e) {}
                    break;
                }
            }
        }
    }
}
