package mavenTest;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class NetIntfUtil {

    /**
     * post����
     * @param url         url��ַ
     * @param jsonParam     ����
     * @param noNeedResponse    ����Ҫ���ؽ��
     * @return
     */

    public static JSONObject httpPost(String url,String Authorization, JSONObject jsonParam){
        //post���󷵻ؽ��
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);

        try {
            if (null != jsonParam) {
                //���������������
//            	StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
//            	entity.setContentEncoding("UTF-8");
//            	entity.setContentType("application/json");
//            	method.addHeader("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIzIiwiaXNzIjoiRVRXX05FVDpUT0tFTl80YmI1MzZhM2JlNDY0YzU5YTk5YjFmMWMwNmM3MjNlNyIsInN1YiI6IiIsImF1ZCI6IiIsImlhdCI6MTUyNjM0OTM3MiwiZXhwIjoxNTI2MzkyNTcyfQ.xYCaDXOQNTu88MkTJDDfu93IqjR13buxWzjSeRX492ZPHLEbPbiIIy-MYG-OTtgsHyNs9JDmD-eo4r7DOZmEKw");
//            	method.setEntity(entity);
            	
            	//KEY-VALUE��ʽ
            	method.addHeader("Authorization", Authorization);
            	List nvps = new ArrayList();
            	for(Object o : jsonParam.entrySet()) {
            		Map.Entry<String, String> item = (Map.Entry<String, String>)o;
            		nvps.add(new BasicNameValuePair(item.getKey(), item.getValue()));
            	}
                method.setEntity(new UrlEncodedFormEntity(nvps));
                
            }

            HttpResponse result = httpClient.execute(method);
            /**�����ͳɹ������õ���Ӧ**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    //��ָ������ת�����ʵ��ΪString����
                    str = EntityUtils.toString(result.getEntity(), "utf-8"); //�ӿڷ������������ַ���������������ʾasc��
                    /**��json�ַ���ת����json����**/
                    jsonResult = JSONObject.parseObject(str);
                } catch (Exception e) {
                	e.printStackTrace();
                    System.out.println("post�����ύʧ��:" + url);
                }
            }
        } catch (IOException e) {
            System.out.println("post�����ύʧ��:" + url);
        }

        return jsonResult;
    }


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please Enter Authorization...");
		String Authorization = scan.nextLine();
		System.out.println("Entered Authorization=" + Authorization);
		
		System.out.println("Enter URL:http://172.18.230.120:20280/net/api/tool/intf/checkcsf2");
		String url = scan.nextLine();
		System.out.println("Entered URL=" + url);
		

		JSONObject param = new JSONObject();
		String inStr = null, value = null;
		while(true) {
			System.out.println("Enter Param key:");
			inStr = scan.nextLine();
			if("quit".equals(inStr)) {
				System.out.println("�������ý���!!");
				break;
			}
			System.out.println("������"+inStr+"��ֵ:");
			value = scan.nextLine();
			System.out.println("key=" + inStr);
			System.out.println("value=" + value);
			
			param.put(inStr, value);
		}
//		param.put("serviceCode", "YKBINTERFACE_VOICECALLBACKINGMESSAGE_POST");
//		param.put("csfJsonParam", "{\"params\":{\"accountId\":\"C160831WECI\",\"mobile\":\"015224991893\",\"destNumber\":\"015224991891\",\"callerNumber\":\"10086\",\"calledNumber\":\"015224991891\",\"callId\":\"20180416000046\"}}");
		JSONObject result = NetIntfUtil.httpPost(url, Authorization, param);
		System.out.println(JSONObject.toJSONString(result));
	}
}
