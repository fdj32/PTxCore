package serial;

import org.junit.Test;

public class CommandTestCase {
	
	private static final String PORT_NAME = "COM9";
	
	private void sleep() {
//		try {
//			Thread.sleep(30);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

	@Test
	public void cpx5DDeviceInformation() {
		System.out.println("null:" + SimpleRead.write(UTFUtils.cpx5DDeviceInformation(null), PORT_NAME));
		sleep();
		System.out.println("null:" + SimpleRead.write(UTFUtils.cpx5DDeviceInformation("1"), PORT_NAME));
		sleep();
		System.out.println("null:" + SimpleRead.write(UTFUtils.cpx5DDeviceInformation("2"), PORT_NAME));
		sleep();
		System.out.println("null:" + SimpleRead.write(UTFUtils.cpx5DDeviceInformation("3"), PORT_NAME));
		sleep();
		System.out.println("null:" + SimpleRead.write(UTFUtils.cpx5DDeviceInformation("4"), PORT_NAME));
		sleep();
		System.out.println("null:" + SimpleRead.write(UTFUtils.cpx5DDeviceInformation("5"), PORT_NAME));
		sleep();
		System.out.println("null:" + SimpleRead.write(UTFUtils.cpx5DDeviceInformation("6"), PORT_NAME));
		sleep();
		System.out.println("null:" + SimpleRead.write(UTFUtils.cpx5DDeviceInformation("7"), PORT_NAME));
		sleep();
		System.out.println("null:" + SimpleRead.write(UTFUtils.cpx5DDeviceInformation("8"), PORT_NAME));
	}
	
}
