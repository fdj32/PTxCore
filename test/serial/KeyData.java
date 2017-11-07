package serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 0142-07204-0503 Generic EMV API.pdf Page 29/167
 * 
 * @author nickfeng
 *
 */
public class KeyData {

	/**
	 * length=1, CA public key index.
	 */
	private byte keyIndex;
	/**
	 * length=1, CA public key algorithm ID.
	 */
	private byte keyAlgorithmIndicator;
	/**
	 * length=1, CA public key hash indicator.
	 */
	private byte hashAlgorithmIndicator;
	/**
	 * length=1, CA public key modulus length.
	 */
	private byte keyLength;
	/**
	 * length=248, CA public key modulus.
	 */
	private byte[] key;
	/**
	 * length=1, CA public key exponent length.
	 */
	private byte keyExponentLength;
	/**
	 * length=3, CA public key exponent. EMV defines CA key exponent to be either 3
	 * or 65537 (indicated as FF). This should be:
	 * <ul>
	 * <li>03 00 00 when key exponent is 3, with KeyExponentLength set to 1</li>
	 * <li>01 00 01 when key exponent is FF, with KeyExponentLength set to 3</li>
	 * </ul>
	 */
	private byte[] keyExponent;
	/**
	 * length=20, CA public key checksum.
	 */
	private byte[] keyCheckSum;

	public byte getKeyIndex() {
		return keyIndex;
	}

	public void setKeyIndex(byte keyIndex) {
		this.keyIndex = keyIndex;
	}

	public byte getKeyAlgorithmIndicator() {
		return keyAlgorithmIndicator;
	}

	public void setKeyAlgorithmIndicator(byte keyAlgorithmIndicator) {
		this.keyAlgorithmIndicator = keyAlgorithmIndicator;
	}

	public byte getHashAlgorithmIndicator() {
		return hashAlgorithmIndicator;
	}

	public void setHashAlgorithmIndicator(byte hashAlgorithmIndicator) {
		this.hashAlgorithmIndicator = hashAlgorithmIndicator;
	}

	public byte getKeyLength() {
		return keyLength;
	}

	public void setKeyLength(byte keyLength) {
		this.keyLength = keyLength;
	}

	public byte[] getKey() {
		return key;
	}

	public void setKey(byte[] key) {
		this.key = key;
	}

	public byte getKeyExponentLength() {
		return keyExponentLength;
	}

	public void setKeyExponentLength(byte keyExponentLength) {
		this.keyExponentLength = keyExponentLength;
	}

	public byte[] getKeyExponent() {
		return keyExponent;
	}

	public void setKeyExponent(byte[] keyExponent) {
		this.keyExponent = keyExponent;
	}

	public byte[] getKeyCheckSum() {
		return keyCheckSum;
	}

	public void setKeyCheckSum(byte[] keyCheckSum) {
		this.keyCheckSum = keyCheckSum;
	}

	public byte[] toBinary() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(keyIndex);
		baos.write(keyAlgorithmIndicator);
		baos.write(hashAlgorithmIndicator);
		baos.write(keyLength);
		baos.write(key);
		baos.write(keyExponentLength);
		baos.write(keyExponent);
		baos.write(keyCheckSum);
		byte[] data = baos.toByteArray();
		baos.close();
		return data;
	}
	
	public static KeyData fromBinary(byte[] bin) {
		KeyData k = new KeyData();
		k.setKeyIndex(bin[0]);
		k.setKeyAlgorithmIndicator(bin[1]);
		k.setHashAlgorithmIndicator(bin[2]);
		k.setKeyLength(bin[3]);
		byte[] key = new byte[248];
		System.arraycopy(bin, 4, key, 0, 248);
		k.setKey(key);
		k.setKeyExponentLength(bin[252]);
		byte[] keyExponent = new byte[3];
		System.arraycopy(bin, 253, keyExponent, 0, 3);
		k.setKeyExponent(keyExponent);
		byte[] keyCheckSum = new byte[20];
		System.arraycopy(bin, 256, keyCheckSum, 0, 20);
		k.setKeyCheckSum(keyCheckSum);
		return k;
	}
	
	public static List<KeyData> fromBinaryToList(byte[] bin) {
		List<KeyData> list = new ArrayList<KeyData>();
		int size = bin.length / 276;
		for(int i = 0; i < size; i++) {
			byte[] sub = new byte[276];
			System.arraycopy(bin, 0+i*276, sub, 0, 276);
			KeyData k = fromBinary(sub);
			list.add(k);
		}
		return list;
	}

}
