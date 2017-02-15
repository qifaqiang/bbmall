package com.wxsoft.framework.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BinaryTest {
	public static void main(String[] args) throws IOException {

	}

	public static void Binarys(File file) throws IOException {
		pngtojpg(file);
		BufferedImage bi = ImageIO.read(file);// 通过imageio将图像载入
		int h = bi.getHeight();// 获取图像的高
		int w = bi.getWidth();// 获取图像的宽
		int rgb = bi.getRGB(0, 0);// 获取指定坐标的ARGB的像素值
		int[][] gray = new int[w][h];
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				gray[x][y] = getGray(bi.getRGB(x, y));
			}
		}

		BufferedImage nbi = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		int SW = 160;
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				if (getAverageColor(gray, x, y, w, h) > SW) {
					int max = new Color(255, 255, 255).getRGB();
					nbi.setRGB(x, y, max);
				} else {
					int min = new Color(193, 193, 193).getRGB();
					nbi.setRGB(x, y, min);
				}
			}
		}

		ImageIO.write(nbi, "jpg", file);
	}

	public static int getGray(int rgb) {
		String str = Integer.toHexString(rgb);
		int r = Integer.parseInt(str.substring(2, 4), 16);
		int g = Integer.parseInt(str.substring(4, 6), 16);
		int b = Integer.parseInt(str.substring(6, 8), 16);
		// or 直接new个color对象
		Color c = new Color(rgb);
		r = c.getRed();
		g = c.getGreen();
		b = c.getBlue();
		int top = (r + g + b) / 3;
		return (top);
	}

	/**
	 * 自己加周围8个灰度值再除以9，算出其相对灰度值
	 * 
	 * @param gray
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @return
	 */
	public static int getAverageColor(int[][] gray, int x, int y, int w, int h) {
		int rs = gray[x][y] + (x == 0 ? 255 : gray[x - 1][y]) + (x == 0 || y == 0 ? 255 : gray[x - 1][y - 1])
				+ (x == 0 || y == h - 1 ? 255 : gray[x - 1][y + 1]) + (y == 0 ? 255 : gray[x][y - 1])
				+ (y == h - 1 ? 255 : gray[x][y + 1]) + (x == w - 1 ? 255 : gray[x + 1][y])
				+ (x == w - 1 || y == 0 ? 255 : gray[x + 1][y - 1])
				+ (x == w - 1 || y == h - 1 ? 255 : gray[x + 1][y + 1]);
		return rs / 12;
	}

	public static void pngtojpg(File file) {

		BufferedImage bufferedImage;

		try {

			// read image file
			bufferedImage = ImageIO.read(file);

			// create a blank, RGB, same width and height, and a white
			// background
			BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

			// write to jpeg file
			ImageIO.write(newBufferedImage, "jpg", file);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		}
	}
}
