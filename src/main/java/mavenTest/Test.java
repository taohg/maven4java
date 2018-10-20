package mavenTest;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONObject;
import com.thg.utils.FileUtil;

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
	
	public static void main(String[] args) throws Exception {
		File file = new File("D:/12333");
//		long size = FileUtils.sizeOfDirectory(new File("D:\\Implication"));
//		System.out.println("Size: " + size + " bytes");
//        System.out.println("----------" + FileUtil.getDirectorySize("D:\\Implication", "m"));
		System.out.println("begin Time:"+new Date());
        FileUtil.listDirectorySize("C:", 50, 2);
        System.out.println("end Time:"+new Date());
//        FileUtil.listDirectorySize("E:\\tmp", 0, 2);
        
        
		Map tmpMap = new HashMap();
		List list = new ArrayList();
		list.add(null);
//		System.out.println(list.isEmpty() + "---------" + list.size());
//		tmpMap.putAll((HashMap)list.get(0));
//		ArrayList alist = new ArrayList();
//		alist.add("111");
//		System.out.println(Integer.MAX_VALUE);
//		String[] ars = new String[] {"1", "3"};
//		System.out.println(ars.length);
//		
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(format.parse("2018-07-31"));
	}
}
