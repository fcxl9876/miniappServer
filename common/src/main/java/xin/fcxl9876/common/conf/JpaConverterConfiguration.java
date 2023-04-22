package xin.fcxl9876.common.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import javax.annotation.PostConstruct;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

@Slf4j
@Configuration
public class JpaConverterConfiguration {

    private final ApplicationContext applicationContext;

    public JpaConverterConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 初始化注入@JpaDto对应的Converter
     */
    @PostConstruct
    public void init() {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(JpaDto.class);
        for (Object o : map.values()) {
            Class c = o.getClass();
            GenericConversionService genericConversionService = ((GenericConversionService) DefaultConversionService.getSharedInstance());
        genericConversionService.addConverter(Map.class, c, m -> {
            try {
                Object obj = c.newInstance();
                // 这里可以扩展,注入的converter,实现sql查寻出的结果为数据库中带下划线的字段,通过程序转为驼峰命名再设置到实体中
                // 也可以做类型转换判断,这里未做类型判断,直接copy到dto中,类型不匹配的时候可能会出错
                return copyMapToObj(m, obj);
            } catch (Exception e) {
                throw new FatalBeanException("Jpa结果转换出错,class=" + c.getName(), e);
            }
        });
    }
}

    /**
     * 将map中的值copy到bean中对应的字段上
     */
    private Object copyMapToObj(Map<String, Object> map, Object target) {
        if(map == null || target == null || map.isEmpty()){
            return target;
        }
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);

        for (String key : map.keySet()) {
            String keyCame = "";
            if (key.contains("_")){
                keyCame = UnderlineToHump(key);
            }else {
                keyCame = key;
            }

            for (PropertyDescriptor targetPd : targetPds) {
                if (keyCame.equals(targetPd.getName()) ||
                        keyCame.toUpperCase().equals(targetPd.getName().toUpperCase()) ||
                        keyCame.toUpperCase().equals(UnderlineToHump(targetPd.getName()).toUpperCase()) ){
                    try {
                        Object value = map.get(key);
                        if (value == null) {
                            continue;
                        }
                        Method writeMethod = targetPd.getWriteMethod();
                        if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                            writeMethod.setAccessible(true);
                        }
                        writeMethod.invoke(target, value);
                        continue;
                    } catch (Exception ex) {
                        throw new FatalBeanException("Could not copy properties from source to target", ex);
                    }
                }
            }

        }
        return target;
    }


        public static String UnderlineToHump(String para){
            StringBuilder result = new StringBuilder();
            String a[] = para.split("_");
            for (String s : a) {
                if (!para.contains("_")) {
                    result.append(s);
                    continue;
                }
                if (result.length() == 0) {
                    result.append(s.toLowerCase());
                } else {
                    result.append(s.substring(0, 1).toUpperCase());
                    result.append(s.substring(1).toLowerCase());
                }
            }
            return result.toString();
        }
}
