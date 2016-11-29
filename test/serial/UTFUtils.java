package serial;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.CharUtils;

/**
 * 0140-07748-0111 Telium CPX and EMV Emulation.pdf
 * 
 * @author nfeng
 *
 */
public class UTFUtils {

	public static void main(String[] args) {
		String msg = STX + CPX_5D_DEVICE_INFORMATION + ETX;
		System.out.println(Hex.encodeHexString(appendLRCToString(msg)
				.getBytes())); // 0235442e035c

		msg = STX + BEEP + BEEP_LENGTH + BEEP_FREQUENCY + ETX;
		System.out.println(Hex.encodeHexString(appendLRCToString(msg)
				.getBytes())); // 0235422e3333035a

		System.out.println(printFormat(deviceInfo));

		byte[] init = cpx58Display(new String[] { null, "Initializing" });
		System.out.println(Hex.encodeHexString(init));
	}

	static byte[] deviceInfo = new byte[] { 0x02, 0x35, 0x44, 0x2e, 0x03, 0x5c };
	static byte[] beep = new byte[] { 0x02, 0x35, 0x42, 0x2e, 0x33, 0x33, 0x03, 0x5a };
	
	public static final String STX = "\u0002";
	public static final String FS = "\u001C";
	public static final String RS = "\u001E";
	public static final String ETX = "\u0003";
	public static final byte[] ACK = "\u0006".getBytes();

	public static final String SPACE = "\u0020";
	public static final String BEEP = "5B.";
	public static final String BEEP_LENGTH = "3";
	public static final String BEEP_FREQUENCY = "3";
	public static final String CPX_5D_DEVICE_INFORMATION = "5D.";
	public static final String CPX_58_DISPLAY = "58.0041";
	public static final String CPX_59_CLEAR = "59.";
	public static final String CPX_6D_TIMED_MULTI_DISPLAY = "6D.";
	
	public static byte[] cmd(String cmd) {
		StringBuffer sb = new StringBuffer();
		sb.append(STX);
		sb.append(cmd);
		sb.append(ETX);
		return appendLRCToString(sb.toString()).getBytes();
	}

	public static byte[] cpx5DDeviceInformation(String option) {
		return cmd(CPX_5D_DEVICE_INFORMATION + ((null == option) ? "" : option));
	}

	public static byte[] cpx59Clear(int line) {
		return cmd(CPX_59_CLEAR + line);
	}

	public static byte[] cpx58Display(String[] lines) {
		StringBuffer sb = new StringBuffer();
		sb.append(CPX_58_DISPLAY);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 16; j++) {
				if (i < lines.length && null != lines[i]
						&& j < lines[i].length()) {
					sb.append(lines[i].charAt(j));
				} else {
					sb.append(SPACE);
				}
			}
		}
		return cmd(sb.toString());
	}

	public static byte[] cpx6DTimedMultiDisplay(char cmdMode, char timeDelay,
			char funcKeysActive, char lineType, String[] text) {
		StringBuffer sb = new StringBuffer();
		sb.append(CPX_6D_TIMED_MULTI_DISPLAY);
		sb.append(cmdMode);
		sb.append(timeDelay);
		sb.append(funcKeysActive);
		sb.append(lineType);
		for (String str : text) {
			sb.append(str);
			sb.append(FS);
		}
		return cmd(sb.toString());
	}

	public static byte calculateLRC(byte[] bytes) {
		byte checksum = bytes[0];
		int startIndex = 1;
		if (bytes[0] == (byte) 0x02) {
			checksum = bytes[1];
			startIndex = 2;
		}
		for (int i = startIndex; i < bytes.length; i++) {
			checksum ^= bytes[i];
		}
		return checksum;
	}

	public static String appendLRCToString(String str) {
		try {
			byte[] rawbytes = str.getBytes("ISO-8859-1");
			byte lrc = calculateLRC(rawbytes);

			byte[] r = new byte[rawbytes.length + 1];
			System.arraycopy(rawbytes, 0, r, 0, rawbytes.length);
			r[rawbytes.length] = lrc;
			return new String(r, "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported encoding", e);
		}
	}

	public static String printFormat(byte[] bArr) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bArr) {
			char ch = (char) b;
			if (CharUtils.isAsciiPrintable(ch)) {
				sb.append(ch);
			} else {
				sb.append("[");
				sb.append(Hex.encodeHexString(new byte[] { b }));
				sb.append("]");
			}
		}
		return sb.toString();
	}

	private static int cpxP16Encode(byte[] inputBuffer, int inputBufferLength,
			byte[] outputBuffer, int outputBufferIndex) {
		int outputBitIndex = 0;
		byte newByte = 0x00;
		for (int inputByteIndex = 0; inputByteIndex < inputBufferLength; inputByteIndex++) {
			for (int inputBitIndex = 7; inputBitIndex >= 0; inputBitIndex--) {
				newByte <<= 1;
				newByte |= (byte) ((inputBuffer[inputByteIndex] >> inputBitIndex) & 0x01);
				outputBitIndex++;

				if (outputBitIndex == 6) {
					outputBuffer[outputBufferIndex++] = (byte) (0x40 | newByte);
					outputBitIndex = 0;
					newByte = 0x00;
				}
			}
		}
		// Finish of the last byte
		if ((outputBitIndex > 0) && (outputBitIndex != 6))
		// if (OutputBitIndex != 6)
		{
			while (outputBitIndex++ != 6)
				newByte <<= 1;
			outputBuffer[outputBufferIndex++] = (byte) (0x40 | newByte);
		}
		return (outputBufferIndex);
	}

	private byte[] CpxP16Decode(byte[] inputBuffer, int inputStartIndex,
			int inputBufferLength) {
		int outputBitIndex = 0;
		byte newByte = 0x00;
		ByteBuffer outputBuffer = ByteBuffer.allocate(inputBufferLength);
		for (int inputByteIndex = inputStartIndex; inputByteIndex < inputBufferLength; inputByteIndex++) {
			for (int inputBitIndex = 5; inputBitIndex >= 0; inputBitIndex--) {
				newByte <<= 1;
				newByte |= (byte) ((inputBuffer[inputByteIndex] >> inputBitIndex) & 0x01);
				outputBitIndex++;

				if (outputBitIndex == 8) {
					outputBuffer.put(newByte);
					outputBitIndex = 0;
					newByte = 0x00;
				}
			}
		}

		return (outputBuffer.array());
	}
}
