package xin.fcxl9876.miniappserver.stench.main.utils;


import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Description 获取配置文件值
 * @author hhzhao
 * @version 1.0 2013-5-13
 * @since 1.0 2013-5-13
 * @see 
 */
public class ConfigFile {
	
	public static Log logger = LogFactory.getLog(ConfigFile.class.getName());
	
	private Properties propertie;
	private static ConfigFile comfigValue;
	private static final String FILE_PATH = "generatecode.properties";
	//构造函数
	private ConfigFile() {
		inint();
	}
	//获取实例
	public static ConfigFile getInstance(){
		if(comfigValue==null){
			comfigValue = new ConfigFile();
		}
		return comfigValue;
	}
	/**
	 * 初始化配置
	 * **/
	private void inint(){
		if(propertie==null){
			propertie = new Properties();  
			try {
				propertie.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_PATH));
			} catch (Exception e) {
				logger.info("读取属性文件--->失败！- 原因：文件路径错误或者文件不存在");  
				e.printStackTrace();
			}
		}
	}
    	
	/**
	 * @Description: 获取配制文件的值
	 * @param key
	 * @return      
	 * @throws
	 * @data:2013-9-23下午3:05:20
	 * @author hhzhao
	 * @version 1.0
	 * 
	 */
	public static String getValue(String key){
		return ConfigFile.getInstance().propertie.getProperty(key);
	}
}
