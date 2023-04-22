package xin.fcxl9876.miniappserver.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *  获取spring上下文
 * @author liugh
 * @since 2018-03-21
 */
public class SpringContextBeanService implements ApplicationContextAware{

	  private static ApplicationContext context = null;
	  
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
		
	}
    public static <T> T getBean(String name)
    {
        return (T)context.getBean(name);
    }

    public static <T> T getBean(Class<T> beanClass){
        return context.getBean(beanClass);
    }

}
