package org.qbang.rxtx;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;

import serial.UTFUtils;

public class PinpadMain {

	public static void main(String[] args) throws Exception {
		Pinpad pp = new Pinpad();
//		pp.write(UTFUtils.cpx58Display(new String[]{"Hello Nick"}));
//		pp.read();
//		pp.write(UTFUtils.cpx58Display(new String[]{"Hello Nick"}), true, 3);
//		pp.write(UTFUtils.cpx5DDeviceInformation("6"), true, 3);
		
		byte[] initData = IOUtils.toByteArray(new FileInputStream(new File("/Users/nickfeng/Hub/fdj32/PTxCore/doc/131540886937708660.init.dat")));
		
		pp.init(initData);
		
//		pp.write(UTFUtils.cpx58Display(new String[]{"Hello Nick"}), false, 3);
		
		//pp.getSerialPort().removeEventListener();
		//pp.getSerialPort().close();
	}

}
