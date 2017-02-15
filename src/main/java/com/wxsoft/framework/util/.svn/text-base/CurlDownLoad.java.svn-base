package com.wxsoft.framework.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * curl下载
 * @author kyz
 *
 */
public class CurlDownLoad {
	private static Pattern p = Pattern.compile(".*?state=(\\d+)size=(.*?)end.*?");
	
	public static String DEFAULT_UA = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:11.0) Gecko/20100101 Firefox/11.0";
	
	public static String TEMP_SUFFIX = ".curltemp";
	
	// 连接超时时间
	private static final int CONNECT_TIME_OUT = 1*60;
	
	// 下载超时时间
	private static final int DOWNLOAD_TIME_OUT = 10 * 60;
	
	/**
	 * curl下载
	 * @param url 下载地址
	 * @param extraMap 下载附加信息
	 * @param savePath 下载保存路径，包含文件名称
	 * @param errSb 错误信息Buffer
	 * @return 1表示下载成功， 0表示下载失败, -1表示下载超时, -2下载返回结果中没有找到Http状态和文件大小
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static int curl(String url, Map<String, String> extraMap, 
			String savePath, StringBuffer errSb) throws IOException, InterruptedException{
		int state = 0;
		url = encodeUrl(url);
		String user_agent = DEFAULT_UA;
		
		if(null!=extraMap){
			String ua = extraMap.get("user-agent");
			if(null!=ua && !"".equals(ua)){
				user_agent = ua;
			}	
		}
		
		String tempFilePath = savePath + ".curltemp";
		
		// 拼写下载命令
		String[] cmdString = {"/bin/bash", "-c", "/usr/bin/curl "
				+ " --connect-timeout " + CONNECT_TIME_OUT + " -m " + DOWNLOAD_TIME_OUT
				+ " -L -w state=%{http_code}size=%{size_download}end"
				+ " -A " + "\"" + user_agent + "\""
				+ " -o " + tempFilePath +" "
				+ url}; 
		System.out.println(Arrays.asList(cmdString));
		
		String resultStr = exec(Arrays.asList(cmdString));
		
		if(resultStr.contains("timed out")){
			errSb.append("下载超时");
			return -1;
		}
		
		
		// 匹配下载输出结果中的 Http状态和文件大小
		Matcher match = p.matcher(resultStr);
		if(match.find()){
			String stateStr = match.group(1);
			String sizeStr = match.group(2);
			
			// http状态为200 Ok
			if("200".equals(stateStr)){
				File downFile = new File(tempFilePath);
				if(downFile.exists()){
					try{
						long fileSize = Long.parseLong(sizeStr);
						
						// 比较下载下来的文件和Get请求返回的真实文件大小
						if(fileSize!=downFile.length()){
							errSb.append("文件下载中断，下载未完成！");
						}
						else{
							// 下载的文件大小和真实文件大小相符合，下载成功
							state = 1;
							downFile.renameTo(new File(savePath));
						}
					}
					catch(Exception e){
						e.printStackTrace();
						errSb.append("下载失败" + ", Http_code:" + stateStr + ";size:" + sizeStr 
								+";Excpetion:" + e.getMessage());
					}
				}else{
					errSb.append("下载已完成，但文件丢失！");
				}
			}
			else{
				errSb.append("下载失败" + ", Http_code:" + stateStr);
			}
		}
		else{
			state=-2;
		}
		
		return state;
	}
	
	private static String encodeUrl(String url){
		url = url.replace("&", "\\&");
		url = url.replace("(", "%28");
		url = url.replace(")", "%29");
		
		return url;
	}
	
	
	private static String exec(List<String> commandList) {
		ProcessBuilder builder = new ProcessBuilder();
		builder.command(commandList);
		builder.redirectErrorStream(true);
		Process p;
		StringBuffer sb = new StringBuffer();
		try {
			p = builder.start();
			BufferedReader inputBuf = null;
			String line = null;
			inputBuf = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = inputBuf.readLine()) != null) {
				sb.append(line);
				continue;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// 这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			throw new RuntimeException("Curl下载过程被打断！");
		}
		return sb.toString();
	}
	
	// ------------------  Local Test ------------------------
	public static void main(String[] args) throws Exception{
		String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=PLgXAFBVHWbHA5TMNkCNrjGlEZCs7emq06alC_XShbtcDFu_9RfSxwqf3auTSZWRjx9olESWHZYU19X0xp1YWdhtWn59II3s6nfaqfS_lmw&media_id=ojKmPFyykcOab1bHL12XqMbDAUWA_jqBnUt9--b8cCWmlSEeMuGVYZ4kQ695lUP4";
		StringBuffer errSb = new StringBuffer();
		int result = CurlDownLoad.curl(url, null, "/mnt/"+System.currentTimeMillis()+".jpg", errSb);
		System.out.println("result:" + result + " error: " + errSb.toString());
	}
}
