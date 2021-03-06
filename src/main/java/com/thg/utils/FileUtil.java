package com.thg.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;

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
			try {
				System.out.println(geneString(fillCode, classCnt) +"____"+ filePath.getName() + "\t" + getDirectorySize(filePath.getAbsolutePath(),"g"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //由于大目录会导致内存溢出报错，这里直接输出单行结果
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
	
	/**
	 * <DESC>获取文件目录大小
	 * @param dirPath 文件目录
	 * @param unit 单位
	 * @return
	 * @throws Exception
	 */
	public static String getDirectorySize(String dirPath, String unit) throws Exception {
		String result = "";
		File tmpFile = new File(dirPath);
		if(!tmpFile.exists()) {
			throw new Exception("file ["+dirPath+"] not found");
		}
		if(!tmpFile.isDirectory()){
			throw new Exception("file ["+dirPath+"] is not a directory");
		}
		long size = FileUtils.sizeOfDirectory(new File(dirPath));
		if("k".equalsIgnoreCase(unit)) {
			result = (size/1024) + "";
		}else if("m".equalsIgnoreCase(unit)) {
			result = (size/1024/1024) + "";
		}else if("g".equalsIgnoreCase(unit)) {
			result = (size/1024/1024/1024) + "";
		}
		return result;
	}
	
	public static String getDirectorySize(String dirPath) throws Exception{
		return getDirectorySize(dirPath, "m");
	}
	
	/**
	 * <DESC>打印目录下的文件目录大小
	 * @param dirPath
	 */
	public static void listDirectorySize(String dirPath) {
		listDirectorySize(dirPath, 0);
	}
	
	/**
	 * <DESC>打印目录下的文件目录大小
	 * @param dirPath
	 */
	public static void listDirectorySize(String dirPath, int minSize) {
		File tmpRoot = new File(dirPath);
		try {
			if(tmpRoot.isDirectory()) {
				int fileSize = Integer.parseInt(getDirectorySize(dirPath,"m"));
				if(fileSize > minSize) {
					System.out.println(dirPath + "\t"+ getDirectorySize(dirPath,"m")+"M");
				}
				
				File[] files = tmpRoot.listFiles();
				if(files!=null && files.length>0) {
					for(File e : files) {
						listDirectorySize(e.getAbsolutePath(), minSize);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * <DESC>打印目录下的文件目录大小
	 * @param dirPath
	 * @param minSize
	 * @param dirClass 要扫描到第几级子目录，如1-表示一级子目录
	 */
	public static void listDirectorySize(String dirPath, int minSize, int childDirClass) {
		File tmpRoot = new File(dirPath);
		if(childDirClass < 0)return ;
		try {
			if(tmpRoot.isDirectory()) {
				int fileSize = Integer.parseInt(getDirectorySize(dirPath,"m"));
				if(fileSize >= minSize) {
					System.out.println(dirPath + "\t"+ getDirectorySize(dirPath,"m")+"M");
				}
				
				File[] files = tmpRoot.listFiles();
				if(files!=null && files.length>0) {
					for(File e : files) {
//						for(int i=childDirClass; i>=0; i--) {
							listDirectorySize(e.getAbsolutePath(), minSize, childDirClass-1);
//						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			FileUtil.getFileList("C:\\Users");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
