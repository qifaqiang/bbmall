/** 
 * @文件名称: WaterMarkHelper.java
 * @类路径: com.wxsoft.framework.util
 * @描述: 为图片添加图片或者文字水印
 * @作者：kasiaits
 * @时间：2013-4-3 上午09:32:42
 */
package com.wxsoft.framework.util;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * @类功能说明：图片添加图片或者文字水印
 * @类修改者：kasiait
 * @修改日期：2013-4-3
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-4-3 上午09:32:42
 */
public class WaterMarkHelper {
	/**
	 * 
	 * @描述: 给图片添加图片水印
	 * @作者: kasiaits
	 * @日期:2013-4-3
	 * @修改内容
	 * @参数： @param iconPath   水印图片路径
	 * @参数： @param srcImgPath 图片原路径
	 * @参数： @param targerPath 目标图片路径
	 * @return void   
	 * @throws
	 */
	public static void markImageByIcon(String iconPath, String srcImgPath, String targerPath) {
		markImageByIcon(iconPath, srcImgPath, targerPath, null);
	}

	/**
	 * 
	 * @描述: 添加文字水印
	 * @作者: kasiaits
	 * @日期:2013-4-3
	 * @修改内容
	 * @参数： @param logoText   水印文字
	 * @参数： @param srcImgPath 原路径
	 * @参数： @param targerPath 目标路径
	 * @return void   
	 * @throws
	 */
    public static void markByText(String logoText, String srcImgPath, String targerPath) {
        markByText(logoText, srcImgPath, targerPath, null);
    }

    /**
     * 
     * @描述: 给图片添加图片水印
     * @作者: kasiaits
     * @日期:2013-4-3
     * @修改内容
	 	 * @参数： @param iconPath   水印图片路径
	 	 * @参数： @param srcImgPath 图片原路径
	 	 * @参数： @param targerPath 目标图片路径
     * @参数： @param degree     水印选择角度 
     * @return void   
     * @throws
     */
	public static boolean markImageByIcon(String iconPath, String srcImgPath, String targerPath, Integer degree) {
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象
			Graphics2D g = buffImg.createGraphics();
			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
			if (null != degree)
			{
				// 设置水印旋转
				g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
			}
			// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
			ImageIcon imgIcon = new ImageIcon(iconPath);
			// 得到Image对象。
			Image img = imgIcon.getImage();
			float alpha = 0.5f; // 透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 表示水印图片的位置
			g.drawImage(img, 150, 300, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
			System.out.println("图片完成添加Icon印章。。。。。。");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @描述: 给图片添加文字水印
	 * @作者: kasiaits
	 * @日期:2013-4-3
	 * @修改内容
	 * @参数： @param logoText    水印文字
	 * @参数： @param srcImgPath  图片原路径
	 * @参数： @param targerPath  目标图片路径
	 * @参数： @param degree      水印文字旋转角度
	 * @return void
	 * @throws
	 */
	public static boolean markByText(String logoText, String srcImgPath, String targerPath, Integer degree) {
        // 主图片的路径
        InputStream is = null;
        OutputStream os = null;
        try {
            Image srcImg = ImageIO.read(new File(srcImgPath));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 得到画笔对象
            // Graphics g= buffImg.getGraphics();
            Graphics2D g = buffImg.createGraphics();
            // 设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
            if (null != degree) 
            {
                // 设置水印旋转
                g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
            }
            // 设置颜色
            g.setColor(Color.red);
            // 设置 Font
            g.setFont(new Font("宋体", Font.BOLD, 30));
            float alpha = 0.5f;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y) .
            g.drawString(logoText, 150, 300);
            g.dispose();
            os = new FileOutputStream(targerPath);
            // 生成图片
            ImageIO.write(buffImg, "JPG", os);
            System.out.println("图片完成添加文字印章。。。。。。");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String argv[])
    {
    	markByText("kasiaits.cn", "E:\\picture\\qing\\02.jpg", "D:\\temp\\fxshop\\b1.jpg", -45);
    }
}
