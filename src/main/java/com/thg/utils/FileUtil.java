package com.thg.utils;

import java.io.File;

public class FileUtil {

	/**
	 * <Desc>理论上 String 变量长度限制为 int 类型的最大值，即 Integer.MAX_VALUE = 2^31 - 1 = 2147483647，约等于 2G，
	 * 但在 Java 中，由于字符串常量池的存在，String 常量长度限制取决于 String 常量在常量池中的存储大小，所以在目录中的子目录太多会导致内存溢出报错</Desc>
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String getFileList(String filePath) throws Exception{
		File tmpRoot = new File(filePath);
		String fillCode = "|";  //一个该符号表示一个层级
		int classCnt = 0; //目录层级
		
		String tmpStr = null;
		if(tmpRoot.exists()) {
			tmpStr = getDirectory(tmpRoot, fillCode, classCnt);
		}else {
			throw new Exception("file path ["+filePath+"] doesn't exists");
		}
		return tmpStr;
	}
	
	public static String getDirectory(File filePath, String fillCode, int classCnt) {
		String tmpStr = "";
		if(filePath.isDirectory()) {
//			tmpStr = tmpStr + geneString(fillCode, classCnt) +"____"+ filePath.getName() + "\r\n";
			System.out.println(geneString(fillCode, classCnt) +"____"+ filePath.getName()); //由于大目录会导致内存溢出报错，这里直接输出单行结果
			File[] files = filePath.listFiles();
			if(files!=null && files.length>0) {
				for(int i=0; i<files.length; i++) {
					tmpStr = tmpStr + getDirectory(files[i], fillCode, classCnt+1);
				}
			}
		}else {
//			tmpStr = tmpStr + geneString(fillCode, classCnt) +"____" + filePath.getName() + "\r\n";
			System.out.println(geneString(fillCode, classCnt) +"____" + filePath.getName()); //由于大目录会导致内存溢出报错，这里直接输出单行结果
		}
		
		return tmpStr;
	}
	
	public static String geneString(String fillCode, int count) {
		StringBuffer sf = new StringBuffer();
		int i=0;
		while(i < count) {
			sf.append(fillCode);
			i++;
		}
		return sf.toString();
	}
	
	public static void main(String[] args) {
		try {
			FileUtil.getFileList("E:\\tmp\\maven4java");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
