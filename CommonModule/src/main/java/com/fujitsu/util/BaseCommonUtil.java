package com.fujitsu.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import com.fujitsu.common.BaseDefine;

public class BaseCommonUtil {
      
	private  static String FTP_IP_MAPPING = System.getProperty("user.dir")+"\\FTSP_IP_MAPPING.properties";

	/**
	 * 获取上周一的日期
	 * 
	 * @param
	 * 
	 * @return 上周一的日期
	 */
	public static Date getFirstDayOfLastWeek(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		cld.setFirstDayOfWeek(Calendar.MONDAY);
		if (cld.get(Calendar.DAY_OF_YEAR) < 7) {
			cld.set(cld.get(Calendar.YEAR) - 1, Calendar.DECEMBER, 31);
		}
		cld.roll(Calendar.WEEK_OF_YEAR, false);
		cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cld.getTime();
	}

	/**
	 * 获取上周日的日期
	 * 
	 * @param
	 * 
	 * @return 上周日的日期
	 */
	public static Date getLastDayOfLastWeek(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		cld.setFirstDayOfWeek(Calendar.MONDAY);
		if (cld.get(Calendar.DAY_OF_YEAR) < 7) {
			cld.set(cld.get(Calendar.YEAR) - 1, Calendar.DECEMBER, 31);
		}
		cld.roll(Calendar.WEEK_OF_YEAR, false);
		cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return cld.getTime();
	}

	/**
	 * 获取昨天的日期
	 * 
	 * @param
	 * 
	 * @return 昨天的日期
	 */
	public static Date Yesterday(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		if (cld.get(Calendar.DAY_OF_YEAR) == 1) {
			cld.set(cld.get(Calendar.YEAR) - 1, Calendar.DECEMBER, 31);
		} else {
			cld.roll(Calendar.DAY_OF_YEAR, false);
		}
		return cld.getTime();
	}

	/**
	 * 获取上月1号的日期
	 * 
	 * @param
	 * 
	 * @return 上月1号的日期
	 */
	public static Date getFirstDayOfLastMonth(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		if (cld.get(Calendar.MONTH) == Calendar.JANUARY) {
			cld.set(cld.get(Calendar.YEAR) - 1, Calendar.DECEMBER, 1);
		} else {
			cld.set(cld.get(Calendar.YEAR), cld.get(Calendar.MONTH) - 1, 1);
		}
		return cld.getTime();
	}

	/**
	 * 获取上月最后一天的日期
	 * 
	 * @param
	 * 
	 * @return 上月最后一天的日期
	 */
	public static Date getLastDayOfLastMonth(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		if (cld.get(Calendar.MONTH) == Calendar.JANUARY) {
			cld.set(cld.get(Calendar.YEAR) - 1, Calendar.DECEMBER, 31);
		} else {
			cld.set(cld.get(Calendar.YEAR), cld.get(Calendar.MONTH) - 1, 1);
			cld.set(Calendar.DAY_OF_MONTH,
					cld.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
		return cld.getTime();
	}
	
	/**
	 * 获得与指定日期相差指定时间差的日期
	 * @param specifiedDay
	 * @param dayGap
	 * @param minuteGap
	 * @return
	 */
	public static Date getSpecifiedDay(Date specifiedDay,int dayGap,int minuteGap) {
		Calendar c = Calendar.getInstance();
		c.setTime(specifiedDay);
		int day = c.get(Calendar.DATE);
		int minute = c.get(Calendar.MINUTE);
		c.set(Calendar.DATE, day + dayGap);
		c.set(Calendar.MINUTE, minute + minuteGap);
		Date target = c.getTime();
		return target;
	}
	
	/**
	 * 在系统控制台输出两个时间直接的差值
	 * @param title
	 * @param first
	 * @param second
	 */
	public static void timeDif(String title, Date first, Date second){
		long l=second.getTime()-first.getTime();
		long day=l/(24*60*60*1000);
		long hour=(l/(60*60*1000)-day*24);
		long min=((l/(60*1000))-day*24*60-hour*60);
		long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		long ms = l- day*24*60*60*1000 - hour*60*60*1000 - min*60*1000 - s*1000;
//		System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
		System.out.println(title + ":花费了" + (day==0?"":day+"天") + (hour==0?"":hour+"小时") + 
				(min==0?"":min+"分") + (s==0?"0.":s+".") + (ms>=100?ms:ms>=10?"0"+ms:"00"+ms) + "秒");
	}
	
	/**
	 * 获取日期格式对象
	 * @param format
	 * @return
	 */
	public static SimpleDateFormat getDateFormatter(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter;
	}
	
	
	
	/**
	 * 获取系统配置属性
	 * @param propertyName 属性名
	 * @return
	 */
	public static String getSystemConfigProperty(String propertyName) {
		String propertyValue = null;
		String configLocation="resourceConfig/systemConfig/"+BaseDefine.SYSTEM_CONFIG_FILE;
		if(new File("../"+configLocation+".properties").exists()){//存在生产环境配置
			try {
				InputStream in = new BufferedInputStream(new FileInputStream(
							"../"+configLocation+".properties"));
				Properties p = new Properties();
				p.load(in);
				in.close();
				propertyValue = (String)p.get(propertyName);
			} catch (Exception e) {
			}
		}
		if(propertyValue==null){
			ResourceBundle bundle = ResourceBundle.getBundle(configLocation);
		try {
			propertyValue = bundle.getString(propertyName);
		} catch (Exception e) {

		}
		}
		// 未配置ftpIp时使用本机计算机名
		if((propertyValue==null||propertyValue.isEmpty())){
			if(BaseDefine.FTP_IP.equals(propertyName)){
			propertyValue = getLocalHostName();
			} else if(BaseDefine.FTP_PORT.equals(propertyName)){
				propertyValue = "21";
			}
		}
		return propertyValue;
	}
	
	
	/**
	 * 写入临时配置属性
	 * @param propertyName 属性名
	 * @return
	 */
	public static void writeFtpIpMappingConfigProperty(String propertyName,String value){
		Properties prop = new Properties();   
        try {   
        	File file = new File(FTP_IP_MAPPING);
        	if(!file.exists()){
        		file.createNewFile();
        	}
        	InputStream fis = new FileInputStream(FTP_IP_MAPPING);
    		prop.load(fis);
    		fis.close();//一定要在修改值之前关闭fis   
            OutputStream fos = new FileOutputStream(FTP_IP_MAPPING); 
            prop.setProperty(propertyName, value);   
            prop.store(fos, "Update '" + propertyName + "' value");
            fos.close();
        } catch (IOException e) {   
        	e.printStackTrace();
        }
	}
	
	
	/**
	 * 写入临时配置属性
	 * @param propertyName 属性名
	 * @return
	 */
	public static String getFtpIpMappingConfigProperty(String propertyName){
		Properties prop = new Properties();   
		String value = null;
        try {   
        	File file = new File(FTP_IP_MAPPING);
        	if(file.exists()){
        		InputStream fis = new FileInputStream(FTP_IP_MAPPING);
        		prop.load(fis);
        		value = (String) prop.get(propertyName);
        		fis.close();//一定要在修改值之前关闭fis   
        	}
        } catch (IOException e) {   
        	e.printStackTrace();
        }
		return value;
	}
	
	/**
	 * 获取消息配置属性
	 * @param propertyName 属性名
	 * @return
	 */
	public static String getMessageConfigProperty(String propertyName){
		ResourceBundle bundle = ResourceBundle.getBundle("resourceConfig/i18n/"+BaseDefine.MESSAGE_CONFIG_FILE);
		return bundle.getString(propertyName);
	}
	
	/** 获取本机计算机名*/
	public static String getLocalHostName(){
		try {
			InetAddress address=InetAddress.getLocalHost();
			return address.getHostName();
		} catch (Exception e) {
			
		}
		return null;
		
	}
	
	/** 获取本机与指定目标通信所使用的ip
	 *  1.应对中兴不能使用计算机名又多网卡情况
	 *  2.遍历所有网卡下的所有ip
	 *  */
	public static InetAddress getLocalHost(String targetIp) {
		InetAddress local=null;
		try{
			local = InetAddress.getLocalHost();
			
			InetAddress target=InetAddress.getByName(targetIp);
			Enumeration<NetworkInterface> eInfs=NetworkInterface.getNetworkInterfaces();
			while(eInfs!=null&&eInfs.hasMoreElements()){
				NetworkInterface inf=eInfs.nextElement();
				if(inf.isUp()&&!inf.isVirtual()&&!inf.isLoopback()){
					inf=NetworkInterface.getByInetAddress(inf.getInetAddresses().nextElement());
					List<InetAddress> adresses = new ArrayList<InetAddress>();
					Enumeration<InetAddress> eAddrs=inf.getInetAddresses();
					while(eAddrs.hasMoreElements()){
						adresses.add(eAddrs.nextElement());
					}
					Field addrsField=inf.getClass().getDeclaredField("addrs");
					Field bindsField=inf.getClass().getDeclaredField("bindings");
					addrsField.setAccessible(true);
					bindsField.setAccessible(true);
					List<InterfaceAddress> iAddrs= inf.getInterfaceAddresses();
					for(InetAddress addr:adresses){
						addrsField.set(inf, new InetAddress[]{addr});
						for(InterfaceAddress iAddr:iAddrs){
							if(iAddr.getAddress().equals(addr)){
								bindsField.set(inf, new InterfaceAddress[]{iAddr});
								break;
							}
						}
						if(target.isReachable(inf, 0, 5000)){
							return addr;
						}else{
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}catch(UnknownHostException e){
			System.out.println(e+"at CommonUtil.getLocalHost(targetIp);");
		}catch(IOException e){
			System.out.println(e+"at CommonUtil.getLocalHost(targetIp);");
		}catch (IllegalArgumentException e) {
			System.out.println(e+"at CommonUtil.getLocalHost(targetIp);");
		} catch (IllegalAccessException e) {
			System.out.println(e+"at CommonUtil.getLocalHost(targetIp);");
		} catch (SecurityException e) {
			System.out.println(e+"at CommonUtil.getLocalHost(targetIp);");
		} catch (NoSuchFieldException e) {
			System.out.println(e+"at CommonUtil.getLocalHost(targetIp);");
		}
		return local;
	}
	
	/** 检查到指定ip的网络状态*/
	public static boolean isReachable(String targetIp){
		boolean isReachable = true;
		//测试能否ping通
		InetAddress inetAddress;
		try {
			inetAddress = InetAddress.getByName(targetIp);
			isReachable = inetAddress.isReachable(5000);
		} catch (UnknownHostException e1) {
			isReachable = false;
		} catch (IOException e) {
			isReachable = false;
		} catch (Exception e) {
			isReachable = false;
		}
		//如果ping不通，使用cmd模式再次ping，南昌发现服务器cmd ping可以ping通
		//但是使用InetAddress.isReachable返回false,原因是服务器tcp 7端口未开通
		if(!isReachable){
			isReachable = isReachableUseCmd(targetIp);
		}
		return isReachable;
	}
	
	//使用命令行检测是否可以ping通
	public static boolean isReachableUseCmd(String targetIp) {
		
		boolean isReachable = false;
		Runtime runtime = Runtime.getRuntime(); // 获取当前程序的运行进对象
		Process process = null; // 声明处理类对象
		String line = null; // 返回行信息
		InputStream is = null; // 输入流
		InputStreamReader isr = null; // 字节流
		BufferedReader br = null;
		try {
			process = runtime.exec("ping " + targetIp); // PING
			is = process.getInputStream(); // 实例化输入流
			isr = new InputStreamReader(is);// 把输入流转换成字节流
			br = new BufferedReader(isr);// 从字节中读取文本
			while ((line = br.readLine()) != null) {
				if (line.contains("TTL")) {
					isReachable = true;
					break;
				}
			}
			is.close();
			isr.close();
			br.close();
		} catch (Exception e) {
			runtime.exit(1);
		}
		return isReachable;
	}

	public static void main(String args[]){
		

		System.out.println(isReachable("134.244.5.36"));
		
//		System.out.println(getLocalHost("192.168.51.2").getHostAddress());
//		System.out.println(getSystemConfigProperty("ftpIp"));
	}
}
