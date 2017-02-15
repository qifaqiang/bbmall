package com.wxsoft.framework.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSONObject;
import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.json.JSONUtil;

public class HTTPSClient {
	private static class TrustAnyTrustManager implements X509TrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getAccessToken() throws Exception {
		long a = new Date().getTime();
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Map<String, String>> accessCompany = BaseConfig.ACCESS_TOKEN_COMPANY;
		Map<String, Long> accessTime = BaseConfig.ACCESS_TOKEN_TIME;

		if (accessTime.get("access_token_time") != null) {
			long time = accessTime.get("access_token_time");
			int c = (int) ((a - time) / 1000);
			if (c > 6000) {
			} else {
				return accessCompany.get("access_token");
			}
		}
		String str_return = "";
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(
					"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
							+ SystemConfig.APPID + "&secret="
							+ SystemConfig.APPSECRET);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.connect();
			InputStream is = conn.getInputStream();
			DataInputStream indata = new DataInputStream(is);
			String ret = "";

			while (ret != null) {
				ret = indata.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return = ret;
				}
			}
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;

		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;

		} finally {
		}
		System.out.println(str_return);
		JSONObject obj = JSONObject.parseObject(str_return);
		String access_token = obj.getString("access_token");
		String expires_in = obj.getString("expires_in");
		map.put("access_token", access_token);
		map.put("expires_in", expires_in);
		BaseConfig.ACCESS_TOKEN_COMPANY.put("access_token", map);
		accessTime.put("access_token_time", a);
		BaseConfig.ACCESS_TOKEN_TIME.put("access_token_time", a);
		return map;
	}

	/**
	 * 获取二维码
	 * 
	 * @param ACCESS_TOKEN
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> qrcode(String accessToken, String data) {
		Map<String, Object> maps = SendHttpsPOST(
				"https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="
						+ accessToken, data);
		return maps;
	}

	/**
	 * 根据openId获取用户信息
	 * 
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> getUserInfo(String openId)
			throws Exception {
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
				+ getAccessToken().get("access_token")
				+ "&openid="
				+ openId
				+ "&lang=zh_CN";
		Map<String, Object> map = SendHttpsPOST(url, "");
		return map;
	}

	/**
	 * 获取JS_token
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getJsToken() throws Exception {
		long a = new Date().getTime();
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Map<String, String>> accessCompany = BaseConfig.JS_TOKEN_COMPANY;
		Map<String, Long> accessTime = BaseConfig.JS_TOKEN_TIME;

		if (accessTime.get("access_token_time") != null) {
			long time = accessTime.get("access_token_time");

			int c = (int) ((a - time) / 1000);
			if (c > 6000) {

			} else {
				return accessCompany.get("access_token");
			}
		}
		String str_return = "";
		String access_token_temp = getAccessToken().get("access_token");
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(
					"https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
							+ access_token_temp + "&type=jsapi");
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.connect();
			InputStream is = conn.getInputStream();
			DataInputStream indata = new DataInputStream(is);
			String ret = "";

			while (ret != null) {
				ret = indata.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return = ret;
				}
			}
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;

		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;

		} finally {
		}
		System.out.println(str_return);
		JSONObject obj = JSONObject.parseObject(str_return);
		String access_token = obj.getString("ticket");
		String expires_in = obj.getString("expires_in");
		map.put("ticket", access_token);
		map.put("expires_in", expires_in);
		BaseConfig.JS_TOKEN_COMPANY.put("access_token", map);
		accessTime.put("access_token_time", a);
		BaseConfig.JS_TOKEN_TIME.put("access_token_time", a);
		return map;
	}

	/**
	 * 获取menu
	 * 
	 * @param ACCESS_TOKEN
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static Map<String, String> getMenu(String ACCESS_TOKEN)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String str_return = "";
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(
					"https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN"
							+ ACCESS_TOKEN);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.connect();
			InputStream is = conn.getInputStream();
			DataInputStream indata = new DataInputStream(is);
			String ret = "";

			while (ret != null) {
				ret = indata.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return = ret;
				}
			}
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;

		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;

		}
		System.out.println(str_return);
		map.put("result", str_return);
		return map;
	}

	/**
	 * 客服接口
	 * 
	 * @param ACCESS_TOKEN
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static Map<String, String> setKeFu(String ACCESS_TOKEN, String json)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String str_return = "";
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(
					"https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
							+ ACCESS_TOKEN);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.connect();
			InputStream is = conn.getInputStream();
			DataInputStream indata = new DataInputStream(is);
			String ret = "";

			while (ret != null) {
				ret = indata.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return = ret;
				}
			}
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;

		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;

		}
		System.out.println(str_return);
		return map;
	}

	/**
	 * 刪除菜單
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static Map<String, String> del(String access_token) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String str_return = "";
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(
					"https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="
							+ access_token);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.connect();
			InputStream is = conn.getInputStream();
			DataInputStream indata = new DataInputStream(is);
			String ret = "";

			while (ret != null) {
				ret = indata.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return = ret;
				}
			}
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;

		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;

		}
		System.out.println(str_return);
		JSONObject obj = JSONObject.parseObject(str_return);
		String errcode = obj.getString("errcode");
		String errmsg = obj.getString("errmsg");
		map.put("errcode", errcode);
		map.put("errmsg", errmsg);
		return map;
	}

	/**
	 * 根据openId返回结果 string
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static String getUserInfoByOpenID(String access_token, String openId)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String str_return = "";
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(
					"https://api.weixin.qq.com/cgi-bin/user/info?access_token="
							+ access_token + "&openid=" + openId
							+ "&lang=zh_CN");
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.connect();
			InputStream is = conn.getInputStream();
			DataInputStream indata = new DataInputStream(is);
			String ret = "";

			while (ret != null) {
				ret = indata.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return = ret;
				}
			}
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;

		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;

		}
		String s = new String(str_return.getBytes("iso-8859-1"), "UTF-8");
		return s;
	}

	public static void main(String[] args) throws Exception {

		// System.out.println(getAccessToken("wxbfbc3b5f28f37872",
		// "b348798581c951968ed412498aca8f09").get("access_token"));
		// System.out.println(getUserInfoByOpenID(
		// getAccessToken("wxafaeee9a77b01617",
		// "9667c4e1b50759b51da71cbbaaf49b60", 2).get(
		// "access_token"), "o6syQjpvDQ9Kn3UWPDBUs4T2KChY"));
		String OpenId = "oMCn5t8_V2BXXKnQkf9RWUF8T9n4";
		String AccessToken = "AYwVNFe6Di5eXxzQU17bH6Y8CtYYRqa8TnActkMtvMhibQsGKBc8pCEqgH8u4aj3_NGqrpz9QbnLRKX6xs7jnkdDwo720k1KE3gDHqTWuJQ";
		// getAccessToken("wxafaeee9a77b01617",
		// "9667c4e1b50759b51da71cbbaaf49b60", 1);

		// getUserInfo(
		// "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
		// + getAccessToken("wxafaeee9a77b01617",
		// "9667c4e1b50759b51da71cbbaaf49b60", 2).get(
		// "access_token")
		// + "&openid=oMCn5t8_V2BXXKnQkf9RWUF8T9n4&lang=zh_CN", "");
		sentkefu(
				"https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=AfRxcBs5Lg3zyNVaaqU5WU0DkdfcqDjSBuo3zBmT5htsJwi5k79mbKejiDpFzeWyNT6lGEqw7xTiMVUUPV9_2nT9p56PaiHncpqG81i1ZRs",
				"{\"touser\":\"oMCn5t8_V2BXXKnQkf9RWUF8T9n4\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"8RRXd4_ptJ9PQPPOJdRWbfMgCBTYiBylPqGouuKjmOmDIpC3h8hrZkpf_RZCpGg_\"}}");

		getUserInfo("oofRSs9a2nt3LH6YXrrIxuInjkO0");
		// PaySuccess(OpenId, "",
		// "我们已收到您的货款，开始为您打包商品，请耐心等待: )","100元","iphone6","如有问题请微信留言，我们将在第一时间为您服务！",AccessToken);
		// upMoneyNotice(OpenId, "",
		// "提现申请已提交，我们正在审核，请耐心等待: )","1000.00元","2014-04-02 11:45:08","如有问题请微信留言，我们将在第一时间为您服务！",AccessToken);
		// upRedBagNotice(OpenId, "",
		// "恭喜您领取到红包","红包（1个）","1元","如有问题请微信留言，我们将在第一时间为您服务！",AccessToken);
		// shenHeNotice(OpenId, "",
		// "您好，审核结果如下","30140603320008","备案内容通过审核","如有问题请微信留言，我们将在第一时间为您服务！",AccessToken);
		// faHuoNotice(OpenId, "",
		// "亲，宝贝已经启程了，好想快点来到你身边","201408189987","圆通快递","6522238281","如有问题请微信留言，我们将在第一时间为您服务！",AccessToken);
		// returnMeonyNotice(OpenId, "",
		// "您已申请退款，等待商家确认退款信息。","¥145.25","七匹狼正品 牛皮男士钱包 真皮钱…","546787944-55446467-544749","如有问题请微信留言，我们将在第一时间为您服务！",AccessToken);
		// MeonyChangeNotice(OpenId, "",
		// "您的账户资金发生以下变动！","下级订单审核成功","100.00元","2014-09-02 12:11:25","如有问题请微信留言，我们将在第一时间为您服务！",AccessToken);
		// MeonyGetNotice(OpenId, "",
		// "很遗憾，提款申请审核未通过T_T","XXXX7462","XXXX","50元","2014-09-02 12:11:25","不符合提款要求，请核对提款要求条款。","如有问题请微信留言，我们将在第一时间为您服务！",AccessToken);
		// System.out
		// .println(del("DJ0Ny3avRyvb3LaLqGCT0rcV1U8YYvPhU8P_TcJgIV26Svd3NqjRxF6ZgiWGh3Y4SGuuUIjB0mzU4oLlS4YMsX7on6aq-5P_2GEOvC35dYqq_KHtM7G6KKxdquSiEpPWnZLOMlfXW79mFJp0j-_6Og"));
		// SendHttpsPOST("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=q4lc09eYnRt52oVsED1lZj6KedrzZcE5AN1NaN2wDbAA6uLUKntCfbYHhPsDeYIMKnkp3nsN6-6o3K_MSg0X54EqTXH6nK49uMhm1xrQfOpS9WQZYsCEefeFaYMHRYHKvfhFvPUZrroq1E1UOm9HhQ",
		// " {'button':[{'type':'click','name':'今日歌曲','key':'V1001_TODAY_MUSIC'},{'type':'click','name':'歌手简介','key':'V1001_TODAY_SINGER'},{ 'name':'菜单','sub_button':[{   'type':'click','name':'hello word','key':'V1001_HELLO_WORLD'},{'type':'click','name':'赞一下我们','key':'V1001_GOOD'}]}]}");
	}

	private static X509TrustManager myX509TrustManager = new X509TrustManager() {

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException { // TODO Auto-generated method stub

		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException { // TODO Auto-generated method stub

		}

		@Override
		public X509Certificate[] getAcceptedIssuers() { // TODO Auto-generated
			return null;
		}
	};

	/**
	 * 发送请求.Http Post
	 * 
	 * @param httpsUrl
	 *            请求的地址
	 * @param xmlStr
	 *            请求的数据
	 */

	public static Map<String, Object> SendHttpsPOST(String url1, String data) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());

			URL url = new URL(url1);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(sc.getSocketFactory());
			httpUrlConn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			httpUrlConn.setDoOutput(true);
			httpUrlConn.connect();
			System.out.println(url);
			// 当有数据需要提交时
			if (null != data && !data.equals("")) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(data.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		map = JSONUtil.parseJSON2Map(buffer.toString());
		System.out.println(buffer);
		return map;

	}

	/**
	 * 发送信息（模板）
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> SentMuBan(String url1, String data) {
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, new TrustManager[] { myX509TrustManager },
					null);
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(url1);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod("POST");
			System.out.println(url);
			// 当有数据需要提交时
			if (null != data) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(data.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(buffer);
		JSONObject obj = JSONObject.parseObject(buffer.toString());
		String errcode = obj.getString("errcode");
		map.put("errcode", errcode);
		return map;

	}

	/**
	 * 发送Http POST信息
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> SentPost(String url1, String data) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, new TrustManager[] { myX509TrustManager },
					null);
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(url1);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod("POST");
			System.out.println(url);
			// 当有数据需要提交时
			if (null != data && !data.equals("")) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(data.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("buffer:" + buffer);
		map = JSONUtil.parseJSON2Map(buffer.toString());
		return map;

	}

	/**
	 * 获取模板id
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 * @throws Exception
	 */
	public static String getTemplate_id(String url1, String data) {
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, new TrustManager[] { myX509TrustManager },
					null);
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(url1);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod("POST");
			System.out.println(url);
			// 当有数据需要提交时
			if (null != data) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(data.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("buffer:" + buffer);
		JSONObject obj = JSONObject.parseObject(buffer.toString());
		String template_id = obj.getString("template_id");
		map.put("template_id", template_id);
		return template_id;
	}

	/**
	 * 发送POST客服信息
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 * @throws Exception
	 */
	public static String sentkefu(String url1, String data) {
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, new TrustManager[] { myX509TrustManager },
					null);
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(url1);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod("POST");
			System.out.println(url);
			// 当有数据需要提交时
			if (null != data) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(data.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("buffer:" + buffer);
		return null;
	}

}