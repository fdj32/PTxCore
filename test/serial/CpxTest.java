package serial;

import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.util.TooManyListenersException;

public class CpxTest {

	public static void main(String[] args) throws IOException, InterruptedException, PortInUseException, TooManyListenersException, UnsupportedCommOperationException {
		//Cpx51InquireSerialRequest req = new Cpx51InquireSerialRequest();

		/*
		Cpx58DisplayMode01Request req = new Cpx58DisplayMode01Request();
		req.setMode("0");
		req.setToggle("0");
		req.setLines("4");
		req.setLineStartIndex("1");
		req.setPrompt1("prompt1");
		req.setPrompt2("prompt2");
		req.setPrompt3("prompt3");
		req.setPrompt4("prompt4");
		*/
		
		/*
		Cpx59ClearDisplayRequest req = new Cpx59ClearDisplayRequest();
		req.setLineNumber("1");
		*/
		
		Cpx5BBeepRequest req = new Cpx5BBeepRequest();
		req.setBeepFrequency("3");
		req.setBeepLength("3");
		
		//Cpx5DDeviceInformationRequest req = new Cpx5DDeviceInformationRequest();
		
		PINPad.getInstance().write(UTFUtils.cmd(req.toString()));
		PINPad.getInstance().getSerialPort().close();
	}

}
