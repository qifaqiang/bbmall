package com.wxsoft.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 16进制转字符串
 * @author kasiait
 *
 */
public class Base16 {

	// 16进制转换表
	public static final char[] ENC_TAB = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	// ascii转换16进制表
	public static final byte[] DEC_TAB = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x00, 0x00, 0x00, 0x00, // 16
			0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // 32
			0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // 48
			0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // 64
			0x00, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // 80
			0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // 96
			0x00, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // 112
			0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 // 128
	};

	/**
	 * 16进制数据转换成可见的ascci字符串
	 * encode byte array data to base 16 hex string.
	 * @param data byte array data.
	 * @return base 16 hex string.
	 */
	public static String encode(byte[] data) {
		return encode(data, 0, data.length);
	}

	/**
	 * encode byte array data from offset to offset+length to base 16 hex string.
	 * @param data byte array data.
	 * @param offset start index, included.
	 * @param length total encode length.
	 * @return the base 16 hex string.
	 */
	public static String encode(byte[] data, int offset, int length) {
		StringBuffer buff = new StringBuffer(length * 2);
		int i = offset, total = offset + length;
		while (i < total) {
			buff.append(ENC_TAB[(data[i] & 0xF0) >> 4]);
			buff.append(ENC_TAB[data[i] & 0x0F]);
			i++;
		}

		return buff.toString();
	}

	/**
	 * 16进制转换成byte数组
	 * decode base 16 hex string to byte array.
	 * @param hex base 16 hex string.
	 * @return byte array data.
	 */
	public static byte[] decode(String hex) {
		byte[] data = new byte[hex.length() / 2];
		decode(hex, data, 0);
		return data;
	}

	/**
	 * decode base 16 hex string to byte array.
	 * @param hex base 16 hex string.
	 * @param data byte array data.
	 * @param offset byte array data start index, included.
	 */
	public static void decode(String hex, byte[] data, int offset) {
		int i = 0, total = (hex.length() / 2) * 2, idx = offset;
		while (i < total) {
			data[idx++] = (byte) ((DEC_TAB[hex.charAt(i++)] << 4) | DEC_TAB[hex.charAt(i++)]);
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(URLDecoder.decode("%7B%22doctorId%22%3A%227%22%2C%20%22date%22%3A%221%22%7D", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

	


