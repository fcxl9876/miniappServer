package xin.fcxl9876.common.util;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtils {
	
	// 字符串转集合
	public static List<String> stringToList(String string) {
		List<String> list = new ArrayList<>();
		if (string.contains(",")) {
			list = Arrays.asList(string.split(","));
		} else {
			list.add(string);
		}
		return list;
	}
	
	/**
     * 将List<String>集合 转化为String
     * 如{"aaa","bbb"} To 'aaa','bbb'
     */
    public static String convertListToString(List<String> strlist, String separator){
        StringBuffer sb = new StringBuffer();
        if(!CollectionUtils.isEmpty(strlist)){
            for (int i=0;i<strlist.size();i++) {
                if(i==0){
                    sb.append(strlist.get(i));
                }else{
                    sb.append(" " + separator + " ").append(strlist.get(i));
                }
            }
        }
        return sb.toString();
    }

	/**
     * 将List<String>集合 转化为String
     * 如{"aaa","bbb"} To 'aaa','bbb'
     */
    public static String convertListToString(List<String> strlist){
        StringBuffer sb = new StringBuffer();
        if(!CollectionUtils.isEmpty(strlist)){
            for (int i=0;i<strlist.size();i++) {
                if(i==0){
                    sb.append("'").append(strlist.get(i)).append("'");
                }else{
                    sb.append(",").append("'").append(strlist.get(i)).append("'");
                }
            }
        }
        return sb.toString();
    }
}
