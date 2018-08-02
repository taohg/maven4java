package mavenTest;

import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 通过HttpClient调用webservice接口
 * 优点：1.使用httpclient作为客户端调用webservice，不用关注繁琐的webservice框架，只需找到SOAP消息格式，添加httpclient依赖就行。
       2.使用httpclient调用webservice，建议采用soap1.1方式调用，经测试使用soap1.1方式能调用soap1.1和soap1.2的服务端。
        缺点：  唯一的缺点是，你得自己解析返回的XML，找到你关注的信息内容。
 * @author taohg
 *
 */
public class HttpWebServiceTest {
	static int socketTimeout = 30000;// 请求超时时间
	static int connectTimeout = 30000;// 传输超时时间
	
	/**
	 * 使用SOAP1.1发送消息
	 * 
	 * @param postUrl
	 * @param soapXml
	 * @param soapAction
	 * @return
	 */
	public static String doPostSoap1_1(String postUrl, String soapXml,
			String soapAction) {
		String retStr = "";
		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpPost httpPost = new HttpPost(postUrl);
                //  设置请求和传输超时时间
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout).build();
		httpPost.setConfig(requestConfig);
		try {
			httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
			httpPost.setHeader("SOAPAction", soapAction);
			StringEntity data = new StringEntity(soapXml, Charset.forName("UTF-8"));
			httpPost.setEntity(data);
			CloseableHttpResponse response = closeableHttpClient
					.execute(httpPost);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				// 打印响应内容
				
				System.out.println();
				retStr = EntityUtils.toString(httpEntity, "UTF-8");
				System.out.println("response:" + retStr);
			}
			// 释放资源
			closeableHttpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retStr;
	}

	
	/**
	 * 使用SOAP1.2发送消息
	 * 
	 * @param postUrl
	 * @param soapXml
	 * @param soapAction
	 * @return
	 */
	public static String doPostSoap1_2(String postUrl, String soapXml,
			String soapAction) {
		String retStr = "";
		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpPost httpPost = new HttpPost(postUrl);
                // 设置请求和传输超时时间
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout).build();
		httpPost.setConfig(requestConfig);
		try {
			httpPost.setHeader("Content-Type",
					"application/soap+xml;charset=UTF-8");
			httpPost.setHeader("SOAPAction", soapAction);
			StringEntity data = new StringEntity(soapXml,
					Charset.forName("UTF-8"));
			httpPost.setEntity(data);
			CloseableHttpResponse response = closeableHttpClient
					.execute(httpPost);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				// 打印响应内容
				retStr = EntityUtils.toString(httpEntity, "UTF-8");
				System.out.println("response:" + retStr);
			}
			// 释放资源
			closeableHttpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retStr;
	}

	public static String getXML() {
		String strXML = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:pm=\"http://wechat.webservice.aqs.zyzx.com/\">\r\n" + 
				"  <soap:header></soap:header>\r\n" + 
				"    <soap:Body>\r\n" + 
				"      <pm:refreshMessager>\r\n" + 
				"        <measureId>101236001</measureId>\r\n" + 
				"        <dimIdList>200005</dimIdList>\r\n" + 
				"        <userId>100672</userId>\r\n" + 
				"        <dimNameList>prov_code</dimNameList>\r\n" + 
				"        <versionFlag>1</versionFlag>\r\n" + 
				"        <versionCode></versionCode>\r\n" + 
				"      </pm:refreshMessager>\r\n" + 
				"    </soap:Body>\r\n" + 
				"</soap:Envelope>";
		
		return strXML;
	}
	
	public static String parseResXml(String resXml) {
		int beginIndex = resXml.indexOf("<return>");
		int endIndex = resXml.indexOf("</return>");
		String res = resXml.substring(beginIndex+8, endIndex);
		
		return res;
	}
	
	public static void main(String[] args) {
		String postUrl = "http://localhost:8070/aqs/services/wechatServiceService?wsdl";
		String xml = HttpWebServiceTest.getXML();
		//采用SOAP1.1调用服务端，这种方式能调用服务端为soap1.1和soap1.2的服务
//		String resStr = doPostSoap1_1(postUrl, xml, "");
		
		//采用SOAP1.2调用服务端，这种方式只能调用服务端为soap1.2的服务
		String resStr = doPostSoap1_2(postUrl, xml, "order");
//		String resStr = doPostSoap1_2(postUrl, xml, "query");
		System.out.println("---------"+resStr.replaceAll("&quot;", "\""));
		
//		String xml = "><return>{\"prov_code\"</return>";
//		HttpWebServiceTest.parseResXml(xml);
	}

}
