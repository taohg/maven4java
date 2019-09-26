package com.thg.robot;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Encoder;

public class RobotClient {
	private List<String> urlList = new ArrayList<String>();
	
	public List<String> getUrlList() {
		return urlList;
	}
	
	/**
	 * ��ȡͼƬ�ļ���base64�ַ�����
	 * @param filePath
	 * @return
	 */
	public String getBase64Img(String filePath) {
		String imgStr = "";
		File file = new File(filePath);
		byte[] buffer = new byte[(int)file.length()];
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(filePath);
			int offset = 0;
			int numRead = 0;
			while(offset<buffer.length && (numRead = fis.read(buffer, offset, buffer.length-offset)) > 0) {
				offset += numRead;
			}
			fis.close();
			BASE64Encoder encoder = new BASE64Encoder();
			imgStr = encoder.encode(buffer);
			
			int pos = filePath.indexOf(".");
			String suffix = filePath.substring(pos+1);
			imgStr = "data:image/"+suffix+";base64,"+imgStr;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgStr;
	}
	
	/**
	 * ��ȡurl�ļ�
	 * @param file
	 * @throws IOException
	 */
	public void readUrl(File file) throws IOException {
		if(!file.exists()) return;
		//��ȡ���ķ�����
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String content = null;
		while((content=br.readLine()) != null) {
			urlList.add(content);
		}
		
		isr.close();
		br.close();
	}
	
	/**
	 * ��ȡҳ��չʾԴ��
	 * @param httpUrl
	 * @return
	 * @throws IOException
	 */
	public String getHtmlCode(String httpUrl) throws IOException {  
        String content = "";  
        URL url = new URL(httpUrl);
        //ͨ��streamת��Ϊ�ַ���ʱҪ�����ַ������Է�����
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));  
        String input;  
        // ���������  
        while ((input = reader.readLine()) != null) {  
            // ����ȡ���ݸ���content  
            content += input;  
        }  
        // �رջ�����  
        reader.close();  
        // ����content  
        return content;  
    }
	
	public String getHtmlCode2(String httpUrl) {
		StringBuilder sb = new StringBuilder();
		URL url;
		HttpURLConnection httpConn;
		try {
			//����ͳһ��Դ��λ����ָ��������Դ
			url = new URL(httpUrl);
			//������URL��http����
			httpConn = (HttpURLConnection) url.openConnection();
			
			//����ģ�������
			httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
			httpConn.connect();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			String input;
			while((input=reader.readLine()) != null) {
				sb.append(input);
			}
			reader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * ����ͼƬurl�����ļ���ָ��λ��
	 * @param imgUrl
	 * @param savePath
	 * @throws MalformedURLException 
	 */
	public boolean downloadPicture(String imgUrl, String savePath) {
		boolean result = false;
		URL url;
		DataInputStream fis;
		FileOutputStream fos;
		long startTime = System.currentTimeMillis();
		long endTime;
		try {
			url = new URL(imgUrl);
			fis = new DataInputStream(url.openStream());
			fos = new FileOutputStream(savePath + File.separator + System.currentTimeMillis() + ".jpg");
			int i;
			while((i=fis.read()) != -1) {
				fos.write(i);
			}
			
			fos.flush();
			fos.close();
			fis.close();
			endTime = System.currentTimeMillis();
			result = true;
			System.out.println("-------��ʱ:" + (endTime-startTime) + "ms");
			System.out.println("-------����ͼƬ:" + imgUrl);
		} catch (MalformedURLException e) {
			System.out.println("-----ͼƬurlδָ��Э������");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * ����ͼƬurl�����ļ���ָ��λ��
	 * @param imgUrl
	 * @param savePath
	 * @return
	 */
	public boolean downloadPicture2(String imgUrl, String savePath) {
		boolean result = false;
		URL url;
		HttpURLConnection httpConn; 
		long startTime = System.currentTimeMillis();
		long endTime;
		try {
			//����URL
			url = new URL(imgUrl);
			
			//����http����
			httpConn = (HttpURLConnection) url.openConnection();
			//����ģ�������
			httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
			//������
			httpConn.connect();
			InputStream instream = httpConn.getInputStream();
			
			FileOutputStream fos = new FileOutputStream(savePath + File.separator + System.currentTimeMillis() + ".jpg");
			byte[] buf = new byte[1024*1024];
			int len = -1;
			while((len=instream.read(buf)) != -1) {
				//��ע�⡿�˴�һ��Ҫ��ָ��len�ķ�������Ϊ�������ûд�������ļ�д������ȷ��ͼƬ�л���
				fos.write(buf, 0, len);
			}
			fos.flush();
			fos.close();
			instream.close();
			endTime = System.currentTimeMillis();
			System.out.println("-------��ʱ:" + (endTime-startTime) + "ms");
			result = true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ���ݲ�Ʒurl�����ҳ���е�ͼƬ
	 * @param url
	 * @param savePath
	 */
	public void processUrlImg(String url, String domain, String savePath) {
		System.out.println("-----processUrlImg----url·��:" + url);
		String content;
		String imgPattern1 = "<(img|IMG)([^<]+)(\\s)(src|SRC|background|BACKGROUND)=('|\")([^'\"\\\\]+)('|\")";
		String imgPattern2 = "<(img|IMG)([^<]+)(\\s)(src|SRC|background|BACKGROUND)=('|\")(http[^'\"\\\\]+)('|\")";
		Pattern p1 = Pattern.compile(imgPattern1);
		try {
			content = getHtmlCode(url);
			Matcher m1 = p1.matcher(content);
			String imgUrl;
			while(m1.find()) {
				imgUrl = m1.group(3);
				System.out.println("-----processUrlImg----ͼƬ·��:" + imgUrl);
				if(imgUrl.indexOf("http") < 0) {
					imgUrl = domain + imgUrl; 
				}
				downloadPicture(imgUrl, savePath);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		RobotClient client = new RobotClient();

		//���url�б���ı��ļ�
		File urlFile = new File("E:\\tmp\\url.txt");
		client.readUrl(urlFile);
		List<String> list = client.getUrlList();
		System.out.println("------urls:" + list.toString());
		
		
		//����ÿ���б��Ʒurl�ж�Ӧ��ͼƬ
		String savePath = "E:\\tmp\\list";
		String domain = "https://wltxmall.cmcc-cs.cn";
//		for(String e : list) {
//		for(int i=0; i<1; i++) {
//			String e = list.get(i);
//			client.processUrlImg(e, domain, savePath);
//		}
		
//		String content;
//		content = client.getHtmlCode("https://wltxmall.cmcc-cs.cn/share/appProduct/common?offeringId=10037&SF=ʡ�ݱ���&QD=01&mobile=15890191273");
//		System.out.println("-------->>>>>>>>" + content);
//
//		content = client.getHtmlCode2("http://221.181.129.90:10110/net/api/product/common/reProdList");
//		System.out.println("-------->>>>>>>>" + content);
		
//		String imgUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569322909829&di=a31d2fb8e77701120441cdb8e2f529a8&imgtype=jpg&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D1414537971%2C968812437%26fm%3D214%26gp%3D0.jpg";
//		client.downloadPicture2(imgUrl, savePath);
//		client.downloadPicture(imgUrl, savePath);
		
		String base64Img = null;
		base64Img = client.getBase64Img("E:\\tmp\\˦��.gif");
		System.out.println("------1>>>>>>:" + base64Img);
		base64Img = client.getBase64Img("E:\\tmp\\΢��ͼƬ_20190821090214.jpg");
		System.out.println("------2>>>>>>:" + base64Img);
		
	}

}
