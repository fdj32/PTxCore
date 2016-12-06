package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.76/184}
 * 
 * @author nfeng
 *
 */
public class CpxF0MsrReadDataR {

	/**
	 * ISO 2 length field
	 */
	private String track2length;
	/**
	 * IS02 track if reading OK
	 */
	private String track2data;
	/**
	 * (1 byte) report from track ISO
	 */
	private String statusMSRTrack1;
	/**
	 * ISO 1 length field
	 */
	private String track1length;
	/**
	 * IS01 track if reading OK
	 */
	private String track1data;

	public String getTrack2length() {
		return track2length;
	}

	public void setTrack2length(String track2length) {
		this.track2length = track2length;
	}

	public String getTrack2data() {
		return track2data;
	}

	public void setTrack2data(String track2data) {
		this.track2data = track2data;
	}

	public String getStatusMSRTrack1() {
		return statusMSRTrack1;
	}

	public void setStatusMSRTrack1(String statusMSRTrack1) {
		this.statusMSRTrack1 = statusMSRTrack1;
	}

	public String getTrack1length() {
		return track1length;
	}

	public void setTrack1length(String track1length) {
		this.track1length = track1length;
	}

	public String getTrack1data() {
		return track1data;
	}

	public void setTrack1data(String track1data) {
		this.track1data = track1data;
	}

	/**
	 * 
	 * @param s
	 * @param cmd
	 *            1=track1, 2=track2, 3=track1+track2
	 * @return
	 */
	public static CpxF0MsrReadDataR parse(String s, int cmd) {
		CpxF0MsrReadDataR r = new CpxF0MsrReadDataR();
		if(1 == cmd) {
			
		}
		return r;
	}
}
