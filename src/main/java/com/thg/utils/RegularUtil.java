package com.thg.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 * @author taohg
 *
 */
public class RegularUtil {
	public static boolean judgeMatch(String srcString, String regexStr) {
		boolean result = false;
		Pattern pattern = Pattern.compile(regexStr);
		Matcher matcher = pattern.matcher(srcString);
		result = matcher.matches();
		return result;
	}
	
	/**
	 * 从正则表达式匹配处开始截断字符串
	 * @param srcString
	 * @param regexStr
	 * @return
	 */
	public static String subString(String srcString, String regexStr) {
		String reStr = null;
		String[] tmpArr = srcString.split(regexStr);
		if(tmpArr.length > 1) {
			reStr = tmpArr[1];
		}else {
			reStr = tmpArr[0];
		}
		return reStr;
	}
	
	public static void main(String[] args) {
		System.out.println("---1---"+RegularUtil.judgeMatch("义马市金城贸易有限公司", "义马[省|市|区|县]?.*"));
		System.out.println("---2---"+RegularUtil.subString("义马市金城贸易有限公司", "义马[省|市|区|县]?"));
	}
}
