package com.thg.excel.watermarker1;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtil implements Runnable {
	private static int WIDTH_MARKIMAGE = 400; // ˮӡͼƬ�Ŀ�� 
	private static int HEIGHT_MARKIMAGE = 1020; // ˮӡͼƬ�ĸ߶���Ϊ���������ĸ߶Ȼ��к��ߣ����� ���߸߶� 
	

	
	public static Map createWaterMark(Map watermarkMessage) throws IOException { 
		Map map = new HashMap(); 
		File file = new File(Thread.currentThread().getName()+"watermark.bmp"); 
		// ��ȡ bufferedImage ���� 
		BufferedImage bi = new BufferedImage(WIDTH_MARKIMAGE, HEIGHT_MARKIMAGE, BufferedImage.TYPE_INT_RGB); 
		// ������ɫ������Ϊ��ɫ 
		int minx = bi.getMinX(); 
		int miny = bi.getMinY(); 
		for (int i = minx; i< WIDTH_MARKIMAGE; i++) { 
			for (int j = miny; j < HEIGHT_MARKIMAGE; j++) { 
				bi.setRGB(i, j, 0xffffff); 
			} 
		} 
		// ��ȡ Graphics2d ����
		Graphics2D g2d = bi.createGraphics(); 
		// ����������ɫΪ��ɫ 
		g2d.setColor(Color.LIGHT_GRAY); 
		// ����ͼƬ������ 
		g2d.setStroke(new BasicStroke(1)); 
		// ��������
		g2d.setFont(new Font("Serif", Font.ITALIC, 20)); 
		// ����������б��
		g2d.rotate(Math.toRadians(-8)); 
		// д��ˮӡ����ԭ���߶ȹ�С�������ۼ�дˮӡ�����Ӹ߶� 
		for (int i = 1; i< 7; i++) { 
			Object[] key = watermarkMessage.keySet().toArray(); 
			for (int j = 0; j <key.length; j++) { 
				if (key[j].equals("date")) { 
					g2d.drawString("���� : " + watermarkMessage.get(key[j]), 0, 180 * i + 40 * (j + 1)); 
				} else if (key[j].equals("user")) { 
					g2d.drawString("�û� : " + watermarkMessage.get(key[j]), 0, 180 * i + 40 * (j + 1)); 
				} else if (key[j].equals("ip")) {
					g2d.drawString("IP ��ַ : " + watermarkMessage.get(key[j]), 0, 180 * i + 40 * (j + 1)); 
				} else { 
					g2d.drawString((String) key[j] + " : " + watermarkMessage.get(key[j]), 0, 180 * i + 40 * (j + 1)); 
				}
			}
		} 
		//����͸���� 
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); 
		// �ͷŶ���
		g2d.dispose();
		// ͨ�� bmp д���ļ� 
		// BMPEncoder.write(bi, file); 
		ImageIO.write(bi, "bmp", file); 
		map.put("markImage", file); 
		map.put("width", WIDTH_MARKIMAGE); 
		map.put("height", HEIGHT_MARKIMAGE); 
		return map; 
	}
	
	public static void addWatermark(String xls_filePath_in, String xls_filePath_out, Map watermarkText) throws Exception{
		InputStream is = new FileInputStream(new File(xls_filePath_in)); 
		jxl.Workbook wb = jxl.Workbook.getWorkbook(is);// ���ԭʼ�ĵ� 
		WritableWorkbook wwb = jxl.Workbook.createWorkbook(new File(xls_filePath_out),wb); 
		WritableSheet ws1 = wwb.getSheet(0);// �õ��������еĵ�һ��������
		Map map = ExcelUtil.createWaterMark(watermarkText); 
		File fileImg = (File) map.get("markImage"); 
		byte imageData[] = new byte[(int) fileImg.length()]; 
		FileInputStream fis = new FileInputStream(fileImg); 
		fis.read(imageData); 
		ws1.setWaterMarkImage(imageData, Integer.parseInt(map.get("width").toString()), Integer.parseInt(map.get("height").toString())); 
		wwb.write(); 
		wwb.close(); 
		fis.close();
		fileImg.delete();
		is.close();
		System.out.println("�����ˮӡ");
	} 
	
	
	
	public void run() {
		Map cMap = new HashMap();
		cMap.put("ip", "12.2.3.1");
		long ll = System.currentTimeMillis();
		cMap.put("user", "����ˮӡ"+ ll);
		System.out.println("start--------"+ ll +"----"+ Thread.currentThread().getName());
		cMap.put("date", "2018-7-27 12:12:12");
		
		try {
			ExcelUtil.addWatermark("E:\\tmp001.xls", ll+".xls", cMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("end--------"+ ll);
	}

	public static void main(String[] args) throws Exception {
//		Map cMap = new HashMap();
//		cMap.put("ip", "12.2.3.1");
//		long ll = System.currentTimeMillis();
//		cMap.put("user", "�л�Ϊ����"+ ll);
//		System.out.println("start--------"+ ll);
//		cMap.put("date", "2018-7-27 12:12:12");
//		
//		ExcelUtil.addWatermark("E:\\tmp001.xls", ll+"out.xls", cMap);
//		System.out.println("end--------"+ ll);
		
		
		int i=0;
		ExcelUtil t1 = new ExcelUtil();
		while(i < 10) {
			new Thread(t1, "name-----" + i++).start();
			Thread.sleep(1);
		}
		
	}
}
