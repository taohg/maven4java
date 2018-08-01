package com.thg.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/** ���ڸ�ʽ **/
	public interface DATE_PATTERN {
		String HHMMSS = "HHmmss";
		String HH_MM_SS = "HH:mm:ss";
		String YYYYMM = "yyyyMM";
		String YYYYMMDD = "yyyyMMdd";
		String YYYY_MM_DD = "yyyy-MM-dd";
		String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
		String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	}
	

	/**
	 * <Desc>��ȡϵͳ����</Dsec>
	 * @return
	 */
	public static Date getSysDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	
	/**
	 * <Desc>��ָ�����ڽ���ƫ��</Desc>
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date add(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}
	
	/**
	 * �����ڸ�ʽ�������ַ���ת��Ϊ����
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date string2Date(String date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * ������ڵ��µ�һ��
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //��ȡĳ����С����
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //�����������·ݵ���С����
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return cal.getTime();
    }
	
	/**
	 * ������ڵ��µ�һ��
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static Date getFirstDayOfMonth(String dateString, String pattern) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date tmpDate = format.parse(dateString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(tmpDate);
        //��ȡĳ����С����
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //�����������·ݵ���С����
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return cal.getTime();
    }

	
	/**
	 * ������ڵ������һ��
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //�����������·ݵ��������
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return cal.getTime();
    }
	
	/**
	 * ������ڵ������һ��
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(String dateString, String pattern) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date tmpDate = format.parse(dateString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(tmpDate);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //�����������·ݵ��������
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return cal.getTime();
    }
	
	
	public static void main(String[] args) throws ParseException {
		System.out.println(DateUtil.getFirstDayOfMonth(new Date()));
		System.out.println(DateUtil.getLastDayOfMonth(new Date()));
		System.out.println(DateUtil.getFirstDayOfMonth("201806", DATE_PATTERN.YYYYMM));
		System.out.println(DateUtil.getLastDayOfMonth("201806", DATE_PATTERN.YYYYMM));
	}
}
