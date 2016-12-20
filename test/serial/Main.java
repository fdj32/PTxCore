package serial;

import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import serial.enums.Tag;
import serial.enums.Vega;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException, PortInUseException, TooManyListenersException, UnsupportedCommOperationException, DecoderException {
		
		PINPad.getInstance().write(UTFUtils.cmd(new Cpx50CancelRequest().toString()));
		
		Cpx6IE2EEActivateMSRRequest cpx6IReq = new Cpx6IE2EEActivateMSRRequest();
		cpx6IReq.setTrackNumber("3");
		cpx6IReq.setTimeout("45");
		cpx6IReq.setFunctionKeysActive("1");
		cpx6IReq.setLinesOrTimeDelay("1");
		cpx6IReq.setLineNumber("5");
		cpx6IReq.setPrompt1("PURCHASE");
		cpx6IReq.setPrompt2("$10.00");
		cpx6IReq.setPrompt3("Insert, Tap or");
		cpx6IReq.setPrompt4("Swipe Card");
		PINPad.getInstance().write(UTFUtils.cmd(cpx6IReq.toString()));
		
		PINPad.getInstance().write(UTFUtils.cmd((new CpxF0Request(CpxF0Command.cpxF0WaitForSmartCardInsertion(0))).toString()));
		PINPad.getInstance().write(UTFUtils.cmd((new CpxF1Request(CpxF1Command.cpxF1OpenSession((byte)0x01)).toBinary())));
		
		List<Tag> tags = new ArrayList<Tag>();
		tags.add(new Tag(0xff04, "00"));
		tags.add(new Tag(0x81, "000003e8"));
		tags.add(new Tag(0x9f04, "00000000"));
		tags.add(new Tag(0x9f5a, "00"));
		PINPad.getInstance().write(UTFUtils.cmd(new CpxF1Request(CpxF1Command.cpxF1AsyncEmvData((byte)0, Vega.emvstartContactlessRequest(tags))).toBinary()));
		
//		System.out.println(Hex.encodeHex(Tag.buildTLVList(tags)));
//		System.out.println(Hex.encodeHex(Vega.emvstartContactlessRequest(tags)));
//		System.out.println(Hex.encodeHex(CpxF1Command.cpxF1AsyncEmvData((byte)0, Vega.emvstartContactlessRequest(tags)).toBinary()));
//		System.out.println(Hex.encodeHex(UTFUtils.cmd(new CpxF1Request(CpxF1Command.cpxF1AsyncEmvData((byte)0, Vega.emvstartContactlessRequest(tags))).toBinary())));
		
//		PINPad.getInstance().getSerialPort().close();
	}

}
