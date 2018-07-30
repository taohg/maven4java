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
	private static int WIDTH_MARKIMAGE = 400; // 水印图片的宽度 
	private static int HEIGHT_MARKIMAGE = 1020; // 水印图片的高度因为设置其他的高度会有黑线，所以 拉高高度 
	

	
	public static Map createWaterMark(Map watermarkMessage) throws IOException { 
		Map map = new HashMap(); 
		File file = new File(Thread.currentThread().getName()+"watermark.bmp"); 
		// 获取 bufferedImage 对象 
		BufferedImage bi = new BufferedImage(WIDTH_MARKIMAGE, HEIGHT_MARKIMAGE, BufferedImage.TYPE_INT_RGB); 
		// 处理背景色，设置为白色 
		int minx = bi.getMinX(); 
		int miny = bi.getMinY(); 
		for (int i = minx; i< WIDTH_MARKIMAGE; i++) { 
			for (int j = miny; j < HEIGHT_MARKIMAGE; j++) { 
				bi.setRGB(i, j, 0xffffff); 
			} 
		} 
		// 获取 Graphics2d 对象
		Graphics2D g2d = bi.createGraphics(); 
		// 设置字体颜色为灰色 
		g2d.setColor(Color.LIGHT_GRAY); 
		// 设置图片的属性 
		g2d.setStroke(new BasicStroke(1)); 
		// 设置字体
		g2d.setFont(new Font("Serif", Font.ITALIC, 20)); 
		// 设置字体倾斜度
		g2d.rotate(Math.toRadians(-8)); 
		// 写入水印文字原定高度过小，所以累计写水印，增加高度 
		for (int i = 1; i< 7; i++) { 
			Object[] key = watermarkMessage.keySet().toArray(); 
			for (int j = 0; j <key.length; j++) { 
				if (key[j].equals("date")) { 
					g2d.drawString("日期 : " + watermarkMessage.get(key[j]), 0, 180 * i + 40 * (j + 1)); 
				} else if (key[j].equals("user")) { 
					g2d.drawString("用户 : " + watermarkMessage.get(key[j]), 0, 180 * i + 40 * (j + 1)); 
				} else if (key[j].equals("ip")) {
					g2d.drawString("IP 地址 : " + watermarkMessage.get(key[j]), 0, 180 * i + 40 * (j + 1)); 
				} else { 
					g2d.drawString((String) key[j] + " : " + watermarkMessage.get(key[j]), 0, 180 * i + 40 * (j + 1)); 
				}
			}
		} 
		//设置透明度 
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); 
		// 释放对象
		g2d.dispose();
		// 通过 bmp 写入文件 
		// BMPEncoder.write(bi, file); 
		ImageIO.write(bi, "bmp", file); 
		map.put("markImage", file); 
		map.put("width", WIDTH_MARKIMAGE); 
		map.put("height", HEIGHT_MARKIMAGE); 
		return map; 
	}
	
	public static void addWatermark(String xls_filePath_in, String xls_filePath_out, Map watermarkText) throws Exception{
		InputStream is = new FileInputStream(new File(xls_filePath_in)); 
		jxl.Workbook wb = jxl.Workbook.getWorkbook(is);// 获得原始文档 
		WritableWorkbook wwb = jxl.Workbook.createWorkbook(new File(xls_filePath_out),wb); 
		WritableSheet ws1 = wwb.getSheet(0);// 得到工作薄中的第一个工作表
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
		System.out.println("已添加水印");
	} 
	
	
	
	public void run() {
		Map cMap = new HashMap();
		cMap.put("ip", "12.2.3.1");
		long ll = System.currentTimeMillis();
		cMap.put("user", "我是水印"+ ll);
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
//		cMap.put("user", "中华为啊啊"+ ll);
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
