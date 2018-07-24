package mavenTest;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClassParseUtil {

	public static Map copyMap(Class clz, Map srcMap) {
    	Map resMap = new HashMap();
    	if(clz != null) {
    		Field[] fields = clz.getDeclaredFields();
    		String fName = null;
    		for(int i=0; i<fields.length; i++) {
    			fName = fields[i].getName();
    			resMap.put(fName, srcMap.get(fName));
    		}
    	}
    	return resMap;
    }
	
	public static void parseBean(Object obj) {
		if(obj != null) {
			Class cla = obj.getClass();
			Field[] field = cla.getDeclaredFields();
			if(field!=null && field.length>0) {
				for(int i=0; i<field.length; i++) {
					Class fieldClass = field[i].getType();
					System.out.println(field[i]);
					System.out.println(field[i].getName());
					System.out.println(fieldClass.getSimpleName());
					
				}
			}
			
		}
	}
	
	public static void createBeanProp(Object obj) {
		if(obj != null) {
			Class cla = obj.getClass();
			Field[] field = cla.getDeclaredFields();
			if(field!=null && field.length>0) {
				for(int i=0; i<field.length; i++) {
					System.out.println(field[i].getName()+" "+transUpperToCamel(field[i].getName()));
				}
			}
			
		}
	}
	
	
	
	public static String transUpperToCamel(String upperStr){
        StringBuilder result = new StringBuilder();
        if (upperStr != null && upperStr.length() > 0) {
            // 循环处理其余字符
        	boolean _flag = false;
            for (int i = 0; i < upperStr.length();i++) {
                String s = upperStr.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals("_")) {
                	_flag = true;
                    continue;
                }else if(_flag) {
                	_flag = false;
                	result.append(s);
                	
                }else {
                	_flag = false;
                	result.append(s.toLowerCase());
                	
                }
            }
        }
        return result.toString();
    }
	
	public static void main(String[] args) {
//		System.out.println(ClassParseUtil.transUpperToCamel("RECOMM_PRODUCT_ID"));
		ClassParseUtil.createBeanProp(new TmpBean());
		
//		BiddInfo b = new BiddInfo();
//		TmpBean b = new TmpBean();
//		ClassParseUtil.parseBean(b);
//		
//		Map src = new HashMap();
//		src.put("enrollEndTime", "enrollEndTime-value");
//		src.put("enrollEndTime1", "enrollEndTime1-value");
//		src.put("enrollEndTime2", "enrollEndTime2-value");
//		src.put("enrollEndTime3", "enrollEndTime3-value");
//		Map res = ClassParseUtil.copyMap(b.getClass(), src);
//		System.out.println(res);
	}

}
