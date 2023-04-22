package xin.fcxl9876.miniappserver.aspect;



import xin.fcxl9876.miniappserver.datasource.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import xin.fcxl9876.miniappserver.annotation.TargetDataSource;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {


    @Before("@within(xin.fcxl9876.miniappserver.annotation.TargetDataSource) || @annotation(xin.fcxl9876.miniappserver.annotation.TargetDataSource)")
    public void beforeSwitchDS(JoinPoint point){

        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();

        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
       // String dataSource = DynamicDataSource.DEFAULT_DATASOURCE;
        try {


            //方法和类上同时存在TargetDataSource 注解时，方法上的注解的优先级更高。方法上没有TargetDataSource注解，则采用类上的注解属性。
            TargetDataSource annotation = null;
            //判断类上是否存在注解
            if(className.isAnnotationPresent(TargetDataSource.class)){
                annotation = className.getAnnotation(TargetDataSource.class);
            }
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);

            // 判断是否存在@DS注解
            if (method.isAnnotationPresent(TargetDataSource.class)) {
                annotation = method.getAnnotation(TargetDataSource.class);
                // 取出注解中的数据源名
            }
            if(annotation != null){
                String dataSource = annotation.value();
                DynamicDataSource.setDataSource(dataSource);

            }
            log.debug("!~ -------switch DynamicDataSource name=[{}]",DynamicDataSource.getDataSource());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    @After("@within(xin.fcxl9876.miniappserver.annotation.TargetDataSource) || @annotation(xin.fcxl9876.miniappserver.annotation.TargetDataSource)")
    public void afterSwitchDS(JoinPoint point){
        //log.debug("------还原清空数据源ThreadLocal-----");
        DynamicDataSource.clearDataSource();
    }
}