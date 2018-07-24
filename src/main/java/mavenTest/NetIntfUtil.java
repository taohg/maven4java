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
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @param noNeedResponse    不需要返回结果
     * @return
     */

    public static JSONObject httpPost(String url,String Authorization, JSONObject jsonParam){
        //post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);

        try {
            if (null != jsonParam) {
                //解决中文乱码问题
//            	StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
//            	entity.setContentEncoding("UTF-8");
//            	entity.setContentType("application/json");
//            	method.addHeader("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIzIiwiaXNzIjoiRVRXX05FVDpUT0tFTl80YmI1MzZhM2JlNDY0YzU5YTk5YjFmMWMwNmM3MjNlNyIsInN1YiI6IiIsImF1ZCI6IiIsImlhdCI6MTUyNjM0OTM3MiwiZXhwIjoxNTI2MzkyNTcyfQ.xYCaDXOQNTu88MkTJDDfu93IqjR13buxWzjSeRX492ZPHLEbPbiIIy-MYG-OTtgsHyNs9JDmD-eo4r7DOZmEKw");
//            	method.setEntity(entity);
            	
            	//KEY-VALUE格式
            	method.addHeader("Authorization", Authorization);
            	List nvps = new ArrayList();
            	for(Object o : jsonParam.entrySet()) {
            		Map.Entry<String, String> item = (Map.Entry<String, String>)o;
            		nvps.add(new BasicNameValuePair(item.getKey(), item.getValue()));
            	}
                method.setEntity(new UrlEncodedFormEntity(nvps));
                
            }

            HttpResponse result = httpClient.execute(method);
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    //按指定编码转换结果实体为String类型
                    str = EntityUtils.toString(result.getEntity(), "utf-8"); //接口返回内容设置字符集，否则中文显示asc码
                    /**把json字符串转换成json对象**/
                    jsonResult = JSONObject.parseObject(str);
                } catch (Exception e) {
                	e.printStackTrace();
                    System.out.println("post请求提交失败:" + url);
                }
            }
        } catch (IOException e) {
            System.out.println("post请求提交失败:" + url);
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
				System.out.println("参数设置结束!!");
				break;
			}
			System.out.println("请设置"+inStr+"的值:");
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
