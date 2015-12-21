package serial;

public class DataObject {
	private String recvData = null;
	private boolean waitForData = true;

	public String getRecvData() {
		return recvData;
	}

	public void setRecvData(String recvData) {
		this.recvData = recvData;
	}

	public boolean isWaitForData() {
		return waitForData;
	}

	public void setWaitForData(boolean waitForData) {
		this.waitForData = waitForData;
	}

}
