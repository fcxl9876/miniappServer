package xin.fcxl9876.miniappserver.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * 平台Page对象
 * @param <T>
 * @author zhhui
 * **/
public class EppPage<T> extends Page<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * 针对单表需要反馈的页面分页对象
	 * @param wrapper
	 * @return EppPage  
	 * **/
	public EppPage(IService service, BaseModel baseModel, Wrapper wrapper) {
		super(baseModel.getPage(),baseModel.getRows(),baseModel.getSidx(),baseModel.getIsAsc());
		this.setTotal(service.selectCount(wrapper));
		if(StringUtils.isBlank(baseModel.getSidx())) {//如果没有排序字段，默认为更新时间
			baseModel.setSidx("createDate");
		}
		this.setOrderByField(getValue(baseModel,baseModel.getSidx()));
		
	}
	
	/**
	 * 针对多表关联查询需要反馈的页面分页对象
	 * @param wrapper
	 * @param Total  总数
	 * @return EppPage
	 * **/
	public EppPage(IService service, BaseModel baseModel, Wrapper wrapper, Integer Total) {
		super(baseModel.getPage(),baseModel.getRows(),baseModel.getSidx(),baseModel.getIsAsc());
		this.setTotal(Total);
		if(StringUtils.isBlank(baseModel.getSidx())) {//如果没有排序字段，默认为更新时间
			baseModel.setSidx("createDate");
		}
		this.setOrderByField(getValue(baseModel,baseModel.getSidx()));
	}
	
	/**
	 * 获取某个类的注解字段。
	 * @author Belen
	 * @param o 对象
	 * @param name 属性
	 * @return
	 */
	public static String getValue(Object o, String name) {
		Class<?> c = o.getClass();
		Field [] field = c.getSuperclass().getDeclaredFields();
		String r = "";
		for (Field f : field) {
			if (f.getName().equals(name)) {
				TableField t = (TableField) f.getAnnotation(TableField.class);
				r = t.value();
				break;
			}
		}
		return r;
	}


}
