package mavenTest;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpRequestUtils {

    /**
     * post����
     * @param url         url��ַ
     * @param jsonParam     ����
     * @param noNeedResponse    ����Ҫ���ؽ��
     * @return
     */

    public static JSONObject httpPost(String url,JSONObject jsonParam){
        //post���󷵻ؽ��
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);

        try {
            if (null != jsonParam) {
                //���������������
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }

            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**�����ͳɹ������õ���Ӧ**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**��ȡ���������ع�����json�ַ�������**/
                    str = EntityUtils.toString(result.getEntity());

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


    /**
     * ����get����
     * @param url    ·��
     * @return
     */
    public static JSONObject httpGet(String url){
        //get���󷵻ؽ��
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            //����get����
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

 

            /**�����ͳɹ������õ���Ӧ**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**��ȡ���������ع�����json�ַ�������**/
                String strResult = EntityUtils.toString(response.getEntity());

                /**��json�ַ���ת����json����**/
                jsonResult = JSONObject.parseObject(strResult);

                url = URLDecoder.decode(url, "UTF-8");
            } else {
                System.out.println("get�����ύʧ��:" + url);
            }

        } catch (IOException e) {
        	e.printStackTrace();
        }
        return jsonResult;
    }
    
	public static void main(String[] args) {
//		String url = "http://localhost:18080/edcadapi/doRequest";
		String url = "http://192.168.20.95:21020/edcadapi/doRequest";
		JSONObject param = new JSONObject();
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("openId", "chatid");
		reqMap.put("telNum", "18392686555");
		reqMap.put("provinceCode", "123");
		reqMap.put("p", "111");
		param.put("busiId", "20180116");
		param.put("reqInfo", reqMap);
		param.put("targetCode", "");
		JSONObject result = HttpRequestUtils.httpPost(url, param);
		System.out.println(JSONObject.toJSONString(result));
	}
}
