package xin.fcxl9876.miniappserver.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;

/**
 * 针对 Wrapper条件查询帮助类
 * @author zhhui
 * **/
public class WrapperHelp {
	
	/**
	 * 构建常规查询条件
	 * 1、除此ID外，字符串用模糊匹配
	 * 2、其他类型字段，使用等于
	 * @param wrapper 构造对象
	 * @param object 传参对象
	 * @return EntityWrapper
	 * @throws Exception 
	 * **/
	public static EntityWrapper<?> addCondition(EntityWrapper<?> wrapper , Object object) throws Exception {
		  
		//获取数据库字段并且有值得字段
		Class<?> c = object.getClass();
		Field [] field = c.getSuperclass().getDeclaredFields();
		//遍历
		for (Field f : field) {
				//判断是否是数据库字段
				TableField t = (TableField) f.getAnnotation(TableField.class);
				if("delState".equals(f.getName())) {
					wrapper.eq("del_state","0");
					continue;
				}
				if(!"id".equals(f.getName())) {
					if((t==null||!t.exist())) {
						continue;
					}
				}
				//判断是否带值
				PropertyDescriptor pd = new PropertyDescriptor(f.getName(),c.getSuperclass());  
		        Method getMethod = pd.getReadMethod();//获得get方法  
		        Object obj = getMethod.invoke(object);//执行get方法返回一个Object  
				if(obj!=null) {
					//首先判断主键
				     if("id".equals(f.getName())) {
				    	 wrapper.eq("ID",obj.toString());
				    	 continue;
				     }
					Class  ftype = f.getType();
					String colName  = t.value();
					if(ftype.isAssignableFrom(String.class)) {
						wrapper.like(colName, obj.toString());
					}else if(ftype.isAssignableFrom(Date.class)) {
						wrapper.eq(colName, obj.toString());
					}else if(ftype.isAssignableFrom(Integer.class)) {
						wrapper.eq(colName, ((Integer)obj).intValue());
					}else{//如果需要话，可以继续补充
						wrapper.eq(colName, obj.toString());
					}
				}
			}
		return wrapper;
	}
	

}
