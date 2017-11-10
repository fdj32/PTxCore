package serial;

/**
 * 0140-07748-0103 Telium CPX and EMV Emulation.pdf Page 15/195
 * 
 * @author nickfeng
 *
 */
public class CpxMessage {

	private String messageType;

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

}
