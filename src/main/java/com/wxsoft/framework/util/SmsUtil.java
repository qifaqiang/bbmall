package com.wxsoft.framework.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wxsoft.framework.config.SystemConfig;

public class SmsUtil {

	public static void main(String[] args) {
		// sendSms("13255681878", 3, "cccc","111点点滴滴1");
		sendSms("13255681878", 1, "0000");
		sendSms("13255681878", 2, "0000");
		sendSms("13255681878", 3, "0000","1111","2222","3333");
		sendSms("13255681878", 4, "0000","1111");
		sendSms("13255681878", 5, "0000");
	}

	public static boolean sendSms(String mobile, int type, String... params) {
		String content = "";
		switch (type) {
		case 1:// 注册
			content = "尊敬的用户，您正在进行齐鲁干烘茶城注册，您的验证码是" + params[0] + "，感谢您的支持！";
			break;
		case 2:// 找回密码
			content = "尊敬的用户，您正在进行齐鲁干烘茶城找回密码操作，您的验证码是" + params[0] + "，感谢您的支持！";
			break;
		case 3:// 支付提货码
			content = "尊敬的用户，您的订单" + params[0] + "已被受理，请凭提货码" + params[1]
					+ "，到" + params[2] + "提取您的商品，详询：" + params[3] + "，感谢您的支持！";
			break;
		case 4:// 预订商品补货
			content = "尊敬的用户，您的店铺收到了一条新订单，商品：" + params[0] + "，收货信息："
					+ params[1] + "!";
			break;
		case 5:// 门店：
				// 客户下预订单
			content = "尊敬的用户，您的手机确认码为" + params[0];
			break;
		default:
			break;
		}
		boolean result = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()));
		URL url;
		try {
			url = new URL("http://userinterface.vcomcn.com/Opration.aspx");

			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(), "gbk");
			String temp = "<Group Login_Name=\"" + SystemConfig.SMS_USER
					+ "\" Login_Pwd=\""
					+ Md5Encrypt.md5(SystemConfig.SMS_PASSWD).toUpperCase()
					+ "\" OpKind=\"0\" InterFaceID=\"\" SerType=\"10\">"
					+ "<E_Time></E_Time><Item><Task>" + "<Recive_Phone_Number>"
					+ mobile + "</Recive_Phone_Number>" + "<Content><![CDATA["
					+ content + "]]></Content>"
					+ "<Search_ID>1</Search_ID></Task></Item></Group>";
			System.out.println(temp);
			out.write(temp);
			out.flush();
			out.close();
			DataInputStream in = new DataInputStream(
					connection.getInputStream());
			String inline = in.readLine();
			if (inline.equals("00")) {
				result = true;
			} else {
				result = false;
			}
			System.out.println(inline);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
