package serial;

public class PrintUtil {

	public static void print(byte[] data, boolean isOutput) {
		char prefix = isOutput ? '>' : '<';
		StringBuffer sbEncoded = new StringBuffer();
		StringBuffer sbDecoded = new StringBuffer();
		byte[] dataDecoded = null;
		if (null != data && data.length > 6 && data[0] == (byte) 0x02
				&& data[1] == (byte) 0x46 && data[3] == (byte) 0x2E) {
			sbEncoded.append("[Encoded]");
			sbEncoded.append(System.lineSeparator());
			sbDecoded.append("[Decoded]");
			sbDecoded.append(System.lineSeparator());
			
			dataDecoded = UTFUtils.decodeCmd(data);
			for (int i = 0; i < dataDecoded.length; i++) {
				if (0 == i % 16) {
					sbDecoded.append(prefix);
					sbDecoded.append(String.format(" %04d-%04d   ",
							(i / 16) * 16 + 1, (i / 16 + 1) * 16));
				}
				sbDecoded.append(String.format("%02x", (byte) dataDecoded[i]));
				sbDecoded.append(' ');
				if (7 == i % 8 && 15 != i % 16) {
					sbDecoded.append(' ');
				}
				if (15 == i % 16) {
					sbDecoded.append(System.lineSeparator());
				}
			}
		}
		sbDecoded.append(System.lineSeparator());

		for (int i = 0; i < data.length; i++) {
			if (0 == i % 16) {
				sbEncoded.append(prefix);
				sbEncoded.append(String.format(" %04d-%04d   ",
						(i / 16) * 16 + 1, (i / 16 + 1) * 16));
			}
			sbEncoded.append(String.format("%02x", (int) data[i]));
			sbEncoded.append(' ');
			if (7 == i % 8 && 15 != i % 16) {
				sbEncoded.append(' ');
			}
			if (15 == i % 16) {
				sbEncoded.append(System.lineSeparator());
			}
		}
		sbEncoded.append(System.lineSeparator());

		if (isOutput) {
			sbDecoded.append(System.lineSeparator());
			sbDecoded.append(sbEncoded);
			System.out.println(sbDecoded.toString());
		} else {
			sbEncoded.append(System.lineSeparator());
			sbEncoded.append(sbDecoded);
			System.out.println(sbEncoded.toString());
		}
	}

	public static void main(String[] args) {
		System.out.println(String.format("%04d", 3));
		System.out.println(String.format("%02x", (int) 'a'));
	}

}
