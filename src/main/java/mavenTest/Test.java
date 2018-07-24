package mavenTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class Test {
	public static final String STR_1 = "";
	public static final Map map = new HashMap();
	
	public void method1() {
		System.out.println("-----" + STR_1);;
	}
	
	public void addItem(String key, Object value) {
		map.put(key, value);
	}
	
	public void printMap() {
		Set set = map.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext()) {
			System.out.println("--" + it.next());
		}
	}
	
	public static void main(String[] args) {
		ArrayList alist = new ArrayList();
		alist.add("111");
		System.out.println(Integer.MAX_VALUE);
//		System.out.println(String.valueOf(null));
		System.out.println((String)null);
		
//		Test t = new Test();
//		Set s = new HashSet();
//		int i = 1;
//		double dv = 2.05;
//		System.out.println("*********:" + dv*10000);
//		
//		double dd = Double.parseDouble("0");
//		System.out.println("*********:" + dd*10000);
//		
//		Integer ir = new Integer(1);
////		Long lg = new Long("123456789012");
//		Long lg = new Long("99999999999");
//		map.put("v1", ir);
//		System.out.println("----s1:" + lg.intValue());
//		s.add("1111");
//		s.add("222");
//		System.out.println("----s:" + s);
//		String s1 = "v"+null;
//		System.out.println("xxxxxxx:" + s1 + "zzzzzz" + s1.length());
//
//		String str = "[\"script\", \"mid, 123,<\"]";
//		Set tmpList = JSONObject.parseObject(str, HashSet.class);
		Map m = new HashMap();
//		String str = "[{\"script\", \"mid\"}]";
//		List tmpList = JSONObject.parseObject(str, ArrayList.class);
//		System.out.println("------aaa" + "a".equals(null));
//		System.out.println("------tmpList" + tmpList.size());
//		System.out.println("----------begin..." + JSONObject.parseObject(String.valueOf(m.get("1")), Map.class));
//		System.out.println("----------begin..." + 14%7);
//		t.printMap();
//		t.addItem("k1", "v1");
//		t.addItem("k2", "v2");
//		t.addItem("k3", "v3");
//		Map tMap = new HashMap();
//		System.out.println("--1111---begin..." + tMap.isEmpty());
//		tMap.put("11", "11-v");
//		System.out.println("--2222---begin..." + tMap.isEmpty());
//		t.printMap();
//		System.out.println("----------end...");
//		
//		String str = null;
//		System.out.println("********:" + String.valueOf(str) + "----");
//		
//		
//		Random rand = new Random(10);
//		for(int i=0; i<20; i++) {
//		System.out.println(rand.nextInt(5));
//		}
	}
}
