package xin.fcxl9876.miniappserver.util;

import java.util.HashMap;
import java.util.Map;

public class EntityIgnoreProperty {
	
	 private final static Map<String,String[]> ignoreMap = new HashMap<String, String[]>();
	 public static String[] get(String key){
		 return ignoreMap.get(key);
	 }

}
