package xin.fcxl9876.common.util;


import org.apache.commons.lang.StringUtils;

/**
 * @Author lijianhua
 * @Description //SQL注入过滤
 * @Date 2021/9/4 11:02
 * @return
*/
public class SQLFilterUtils {

    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        String lowStr = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(lowStr.indexOf(keyword) != -1){
                return "";
            }
        }
        return str;
    }
}
