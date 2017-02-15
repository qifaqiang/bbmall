package com.wxsoft.framework.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrimaryGenerater {

	private static PrimaryGenerater primaryGenerater = null;
	private static String sno = "00000000";

	private PrimaryGenerater() {
	}

	/**
	 * 取得PrimaryGenerater的单例实现
	 * 
	 * @return
	 */
	public static PrimaryGenerater getInstance() {
		if (primaryGenerater == null) {
			synchronized (PrimaryGenerater.class) {
				if (primaryGenerater == null) {
					primaryGenerater = new PrimaryGenerater();
				}
			}
		}
		return primaryGenerater;
	}

	/**
	 * 生成下一个编号
	 */
	public synchronized String generaterNextNumber() {
		if (sno.equals("00000000")) {
			sno = new RedisClient().getOrderCode();
			if(null==sno||sno.equals("")){
				sno="00000000";
			}
		}
		if (sno.equals("99999999")) {
			sno = "00000000";
		}
		String id = null;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		DecimalFormat df = new DecimalFormat("00000000");
		id = df.format(1 + Integer.parseInt(sno));
		sno = id;
		new RedisClient().SetOrderCode(sno);
		return formatter.format(date) + "006" + id;
	}

	public static void main(String[] args) {
		PrimaryGenerater kk = new PrimaryGenerater();
		System.out.println(kk.generaterNextNumber());
		System.out.println(kk.generaterNextNumber());
		System.out.println(kk.generaterNextNumber());
		System.out.println(kk.generaterNextNumber());
		System.out.println(kk.generaterNextNumber());
	}

	private static void fileWrite(String orderCode) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("C:/orderCode.txt");
			fw.write(orderCode);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private static String fileReader() {
		FileReader fw = null;
		BufferedReader br = null;
		String temp = null;
		try {
			fw = new FileReader("C:/orderCode.txt");
			br = new BufferedReader(fw);
			String line = "";
			while ((line = br.readLine()) != null) {
				temp = line;
			}
			br.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return temp;
	}
}