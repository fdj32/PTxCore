package org.qbang.rxtx;

import java.io.IOException;
import java.io.InputStream;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class PinpadListener implements SerialPortEventListener {
	
	private Pinpad pinpad;
	
	public Pinpad getPinpad() {
		return pinpad;
	}

	public void setPinpad(Pinpad pinpad) {
		this.pinpad = pinpad;
	}

	public PinpadListener(Pinpad pinpad) {
		setPinpad(pinpad);
	}

	@Override
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
			InputStream is = null;
			int available = 0;
			byte[] data = null;
			try {
				is = ((SerialPort)(event.getSource())).getInputStream();
				available = is.available();
				if(available > 0) {
					data = new byte[available];
					is.read(data);
				}
				getPinpad().getDataQ().add(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}		
	}

}
