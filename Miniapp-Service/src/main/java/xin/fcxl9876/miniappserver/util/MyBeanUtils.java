package xin.fcxl9876.miniappserver.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * 实体类工具类
 * 
 * @author qiye
 * @date 2018年6月22日 下午1:36:39
 */
public class MyBeanUtils {

	/**
	 * 将空值的属性从目标实体类中复制到源实体类中
	 * 
	 * @param src    : 要将属性中的空值覆盖的对象(源实体类)
	 * @param target :从数据库根据id查询出来的目标对象
	 */
	public static void copyNonNullProperties(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullProperties(src));
	}

	public static void copyNotNullProperties(Object source, Object target) {
		BeanUtils.copyProperties(source, target, getNotNullProperties(target));
	}

	/**
	 * 将非空的properties给找出来,然后返回出来
	 * 
	 * @param src
	 * @return
	 */
	public static String[] getNotNullProperties(Object src) {
		BeanWrapper srcBean = new BeanWrapperImpl(src);
		PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
		Set<String> emptyName = new HashSet<>();
		for (PropertyDescriptor p : pds) {
			Object srcValue = srcBean.getPropertyValue(p.getName());
			if (p.getPropertyType().equals(Set.class)) {
				if (((Set) srcValue).size() > 0) {
					emptyName.add(p.getName());
				}
			} else if (!(srcValue == null || "".equals(srcValue))) {
				emptyName.add(p.getName());
			}
		}
		String[] result = new String[emptyName.size()];
		System.out.println("非空字段：" + emptyName);
		return emptyName.toArray(result);
	}

	/**
	 * 将为空的properties给找出来,然后返回出来
	 * 
	 * @param src
	 * @return
	 */
	public static String[] getNullProperties(Object src) {
		BeanWrapper srcBean = new BeanWrapperImpl(src);
		PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
		Set<String> emptyName = new HashSet<>();
		for (PropertyDescriptor p : pds) {
			Object srcValue = srcBean.getPropertyValue(p.getName());
			if (p.getPropertyType().equals(Set.class)) {
				if (((Set) srcValue).size() == 0) {
					emptyName.add(p.getName());
				}
			} else if (srcValue == null || "".equals(srcValue)) {
				emptyName.add(p.getName());
			}
		}
		String[] result = new String[emptyName.size()];
		System.out.println("空字段：" + emptyName);
		return emptyName.toArray(result);
	}
}
