package mavenTest;

import com.alibaba.fastjson.JSONObject;

public class TestClass {

	public static void main(String[] args) {
		String str = "{\"start\":0,\"limit\":10,\"provCode\":\"00030009\",\"regDateInt\":\"90\"}";
		JSONObject o = JSONObject.parseObject(null);
		System.out.println(">>>>>>>>>" + o);
	}

}
