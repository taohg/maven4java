package com.thg.utils;

import org.apache.commons.codec.binary.StringUtils;

public class IPUtil {
	/*验证IP是否属于某个IP段
    * ipSection    IP段（以'-'分隔）
    * ip           所验证的IP号码
    */

  public static boolean ipExistsInRange(String ip, String ipSection) {
       ipSection = ipSection.trim();
       ip = ip.trim();
      int idx = ipSection.indexOf('-');
       String beginIP = ipSection.substring(0, idx);
       String endIP = ipSection.substring(idx + 1);
      return getIp2long(beginIP)<=getIp2long(ip) &&getIp2long(ip)<=getIp2long(endIP);

   }

  public static long getIp2long(String ip) {

       ip = ip.trim();

       String[] ips = ip.split("\\.");

      long ip2long = 0L;

      for (int i = 0; i < 4; ++i) {

           ip2long = ip2long << 8 | Integer.parseInt(ips[i]);

       }

      return ip2long;

   }

   public static long getIp2long2(String ip) {

       ip = ip.trim();

       String[] ips = ip.split("\\.");

      long ip1 = Integer.parseInt(ips[0]);

      long ip2 = Integer.parseInt(ips[1]);

      long ip3 = Integer.parseInt(ips[2]);

      long ip4 = Integer.parseInt(ips[3]);



      long ip2long =1L* ip1 * 256 * 256 * 256 + ip2 * 256 * 256 + ip3 * 256 + ip4;

      return ip2long;

   } 
   
   /**
    * 校验IP是否合法
    * @param ip
    * @return
    */
   public static boolean checkValid(String ip) {
	   boolean result = true;
	   if(ip!=null && !"".equals(ip)){
		   ip = ip.trim();
	       String[] ips = ip.split("\\.");
	       if(ips==null || ips.length!=4) {
	    	   result = false;
	       }
	       long ip1 = Integer.parseInt(ips[0]);
	       long ip2 = Integer.parseInt(ips[1]);
	       long ip3 = Integer.parseInt(ips[2]);
	       long ip4 = Integer.parseInt(ips[3]);
	       if(ip1<0 || ip2<0 || ip3<0 || ip4<0 || ip1>255 || ip2>255 || ip3>255 || ip4>255) {
	    	   result = false;
	       }
	   }else {
		   result = false;
	   }
       return result;
   }

   public static void main(String[] args){

      //10.10.10.116 是否属于固定格式的IP段10.10.1.00-10.10.255.255

       String ip="10.10.10.256";

       String ipSection="10.10.1.00-10.10.255.255";
       boolean isValid = checkValid(ip);
       System.out.println(isValid);
      boolean exists=ipExistsInRange(ip,ipSection);

       System.out.println(exists);

       System.out.println(getIp2long(ip));

       System.out.println(getIp2long2(ip));

   }
}
