package org.qbang.rxtx;

import serial.UTFUtils;

public class PinpadMain {

	public static void main(String[] args) throws Exception {
		Pinpad pp = new Pinpad();
		pp.write(UTFUtils.cpx58Display(new String[]{"Hello Nick"}));
		pp.read();
		pp.getSerialPort().close();
	}

}
