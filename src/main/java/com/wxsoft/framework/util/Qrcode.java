package com.wxsoft.framework.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.wxsoft.framework.config.BaseConfig;

public class Qrcode {

	/**
	 * 编码
	 * 
	 * @param contents
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	public void encode(String contents, int width, int height, String imgPath) {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "GBK");
		hints.put(EncodeHintType.MARGIN, 0); // 二维码边框宽度，这里文档说设置0-4，但是设置后没有效果，不知原因，
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
					BarcodeFormat.QR_CODE, width, height, hints);

			MatrixToImageWriter
					.writeToFile(bitMatrix, "png", new File(imgPath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getImageSizeByImageReader(String content) {
		String path = BaseConfig.FX_PIC_PATH+"/attached/";
		try {
			if (!(new File(path).isDirectory())) {
				new File(path).mkdir();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		String pathFile = path + System.nanoTime() + ".jpg";
		encode(content, 200, 200, pathFile);
		File file = new File(pathFile);
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedImage;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String imgPath = "d://37.png";
		String contents = "http://192.168.0.15/xyd/wap/index.html?companyId=37";
		int width = 1000, height = 1000;
		Qrcode handler = new Qrcode();
		handler.encode(contents, width, height, imgPath);

		System.out.println("Michael ,you have finished zxing encode.");
	}
}
