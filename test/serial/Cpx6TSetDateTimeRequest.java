package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.144/184} {6T . Read/Set Date and
 * Time}
 * 
 * @author nfeng
 *
 */
public class Cpx6TSetDateTimeRequest extends CpxRequest {

	private String mode;
	private String year;
	private String month;
	private String day;
	private String hour;
	private String minute;
	private String second;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public Cpx6TSetDateTimeRequest() {
		super();
		this.setMessageType("6T.");
		this.setMode("1");
	}

	@Override
	public String toString() {
		String msg = this.getMessageType();
		msg += this.getMode();
		msg += this.getYear();
		msg += this.getMonth();
		msg += this.getDay();
		msg += this.getHour();
		msg += this.getMinute();
		msg += this.getSecond();
		return msg;
	}
}
