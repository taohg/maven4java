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
 * ͨ��HttpClient����webservice�ӿ�
 * �ŵ㣺1.ʹ��httpclient��Ϊ�ͻ��˵���webservice�����ù�ע������webservice��ܣ�ֻ���ҵ�SOAP��Ϣ��ʽ�����httpclient�������С�
       2.ʹ��httpclient����webservice���������soap1.1��ʽ���ã�������ʹ��soap1.1��ʽ�ܵ���soap1.1��soap1.2�ķ���ˡ�
        ȱ�㣺  Ψһ��ȱ���ǣ�����Լ��������ص�XML���ҵ����ע����Ϣ���ݡ�
 * @author taohg
 *
 */
public class HttpWebServiceTest {
	static int socketTimeout = 30000;// ����ʱʱ��
	static int connectTimeout = 30000;// ���䳬ʱʱ��
	
	/**
	 * ʹ��SOAP1.1������Ϣ
	 * 
	 * @param postUrl
	 * @param soapXml
	 * @param soapAction
	 * @return
	 */
	public static String doPostSoap1_1(String postUrl, String soapXml,
			String soapAction) {
		String retStr = "";
		// ����HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpPost httpPost = new HttpPost(postUrl);
                //  ��������ʹ��䳬ʱʱ��
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
				// ��ӡ��Ӧ����
				
				System.out.println();
				retStr = EntityUtils.toString(httpEntity, "UTF-8");
				System.out.println("response:" + retStr);
			}
			// �ͷ���Դ
			closeableHttpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retStr;
	}

	
	/**
	 * ʹ��SOAP1.2������Ϣ
	 * 
	 * @param postUrl
	 * @param soapXml
	 * @param soapAction
	 * @return
	 */
	public static String doPostSoap1_2(String postUrl, String soapXml,
			String soapAction) {
		String retStr = "";
		// ����HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpPost httpPost = new HttpPost(postUrl);
                // ��������ʹ��䳬ʱʱ��
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
				// ��ӡ��Ӧ����
				retStr = EntityUtils.toString(httpEntity, "UTF-8");
				System.out.println("response:" + retStr);
			}
			// �ͷ���Դ
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
		//����SOAP1.1���÷���ˣ����ַ�ʽ�ܵ��÷����Ϊsoap1.1��soap1.2�ķ���
//		String resStr = doPostSoap1_1(postUrl, xml, "");
		
		//����SOAP1.2���÷���ˣ����ַ�ʽֻ�ܵ��÷����Ϊsoap1.2�ķ���
		String resStr = doPostSoap1_2(postUrl, xml, "order");
//		String resStr = doPostSoap1_2(postUrl, xml, "query");
		System.out.println("---------"+resStr.replaceAll("&quot;", "\""));
		
//		String xml = "><return>{\"prov_code\"</return>";
//		HttpWebServiceTest.parseResXml(xml);
	}

}
