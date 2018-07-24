package com.thg.utils;

import java.io.File;

public class FileUtil {

	/**
	 * <Desc>������ String ������������Ϊ int ���͵����ֵ���� Integer.MAX_VALUE = 2^31 - 1 = 2147483647��Լ���� 2G��
	 * ���� Java �У������ַ��������صĴ��ڣ�String ������������ȡ���� String �����ڳ������еĴ洢��С��������Ŀ¼�е���Ŀ¼̫��ᵼ���ڴ��������</Desc>
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String getFileList(String filePath) throws Exception{
		File tmpRoot = new File(filePath);
		String fillCode = "|";  //һ���÷��ű�ʾһ���㼶
		int classCnt = 0; //Ŀ¼�㼶
		
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
			System.out.println(geneString(fillCode, classCnt) +"____"+ filePath.getName()); //���ڴ�Ŀ¼�ᵼ���ڴ������������ֱ��������н��
			File[] files = filePath.listFiles();
			if(files!=null && files.length>0) {
				for(int i=0; i<files.length; i++) {
					tmpStr = tmpStr + getDirectory(files[i], fillCode, classCnt+1);
				}
			}
		}else {
//			tmpStr = tmpStr + geneString(fillCode, classCnt) +"____" + filePath.getName() + "\r\n";
			System.out.println(geneString(fillCode, classCnt) +"____" + filePath.getName()); //���ڴ�Ŀ¼�ᵼ���ڴ������������ֱ��������н��
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
