package com.thg.robot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模拟Http请求调用远程接口
 * 
 */
public class HttpClientUtil {

	/**
	 * 发送get请求
	 * 
	 */
	public static String sendGet(String url, Map<String, String> paraMap) {
		String result = "";
		URL connUrl = null;
		BufferedReader reader = null;
		
		String paramStr = "";
		StringBuilder sb = new StringBuilder();
		try {
			if(paraMap != null) {
				if(paraMap.size() == 1) {
					for(String e : paraMap.keySet()) {
						sb.append(e).append("=").append(URLEncoder.encode(paraMap.get(e), "UTF-8"));
					}
					paramStr = sb.toString();
				}else {
					for(String e : paraMap.keySet()) {
						sb.append(e).append("=").append(URLEncoder.encode(paraMap.get(e), "UTF-8")).append("&");
					}
					String tmpStr = sb.toString();
					paramStr = tmpStr.substring(0, tmpStr.length()-1);
				}
			}
			
			String fullUrl = url + "?" + paramStr;
			//创建统一资源定位符
			connUrl = new URL(fullUrl);
			//创建http连接
			HttpURLConnection httpConn = (HttpURLConnection) connUrl.openConnection();
			
			//设置通用属性
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Connection", "keep-alive");
			httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
			
			//建立连接
			httpConn.connect();
			
			//获取响应的头部信息
			Map<String, List<String>> headers = httpConn.getHeaderFields();
			
			reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			String line;
			while((line=reader.readLine()) != null) {
				result += line;
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("不支持指定的字符编码格式");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("未知的资源协议类型");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("到网络资源引用的远程对象连接异常");
			e.printStackTrace();
		} finally {
			try {
				if(reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 发送post请求
	 * @param url
	 * @param paraMap
	 * @return
	 */
	public static String sendPost(String url, Map<String, String> paraMap) {
		String result = "";
		URL connUrl = null;
		HttpURLConnection httpConn = null;
		BufferedReader reader = null;
		BufferedWriter writer = null;
		
		String paramStr = "";
		StringBuilder sb = new StringBuilder();
		try {
			if(paraMap != null) {
				if(paraMap.size() == 1) {
					for(String e : paraMap.keySet()) {
						sb.append(e).append("=").append(URLEncoder.encode(paraMap.get(e), "UTF-8"));
					}
					paramStr = sb.toString();
				}else {
					for(String e : paraMap.keySet()) {
						sb.append(e).append("=").append(URLEncoder.encode(paraMap.get(e), "UTF-8")).append("&");
					}
					String tmpStr = sb.toString();
					paramStr = tmpStr.substring(0, tmpStr.length()-1);
				}
			}
			
			connUrl = new URL(url);
			httpConn = (HttpURLConnection) connUrl.openConnection();
			
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			httpConn.setRequestProperty("Connection", "keep-alive");
			httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
			
			//建立连接
//			httpConn.connect();
			
			//设置post方式
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			//获取HttpURLConnection对象的输出流，来发送请求参数
			writer = new BufferedWriter(new OutputStreamWriter(httpConn.getOutputStream()));
			writer.write(paramStr);
			writer.flush();
			
			reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			String line = null;
			while((line=reader.readLine()) != null) {
				result += line;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(writer != null) {
					writer.close();
				}
				if(reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		String getUrl = "http://221.181.129.90:10110/net/api/product/common/productDetail";
		Map param = new HashMap();
		param.put("prodId", "100");
		String getResult = HttpClientUtil.sendGet(getUrl, param);
		System.out.println(getResult);
		
		param.clear();
		param.put("prodId", "100");
		String postUrl = "http://221.181.129.90:10110/net/api/product/common/reProdList";
		String postResult = HttpClientUtil.sendPost(postUrl, param);
		System.out.println(postResult);
	}

}
