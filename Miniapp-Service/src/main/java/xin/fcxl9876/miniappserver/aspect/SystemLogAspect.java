package xin.fcxl9876.miniappserver.aspect;

import com.alibaba.fastjson.JSON;
import xin.fcxl9876.miniappserver.annotation.RecordLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Discription TODO
 * @Autor lijianhua
 * @Date 2021/11/5 9:46
 */
@Aspect
@Component
@Slf4j
public class SystemLogAspect {

    @Pointcut("@annotation(xin.fcxl9876.miniappserver.annotation.RecordLog)")
    public  void controllerMethodAspect() {

    }

    @Before("controllerMethodAspect()")
    public void doBefore(JoinPoint joinPoint) {
    }

    @Around("controllerMethodAspect()")
    public Object around(ProceedingJoinPoint joinPoint){
        Object obj = null;
        try {
            String methodName = joinPoint.getSignature().getName();
            String targetName = joinPoint.getTarget().getClass().getName();

            //获取方法参数
            Object[] arguments = joinPoint.getArgs();
            String jsonString = JSON.toJSONString(arguments);
            log.info("argments : [{}]",jsonString);

            //记录方法执行时长
            Long begin = System.currentTimeMillis();
            obj = joinPoint.proceed();
            Long end = System.currentTimeMillis();
            Long d = end - begin;
            log.info("=====方法调用持续时长====={}",d);

            Class targetClass = null;
            targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String descript = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        descript = method.getAnnotation(RecordLog.class).descript();
                        //记录日志
                        recordLog(descript,d,jsonString,method.getName());
                    }
                }
            }

            //SysUserInfo sysUserInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }


    @After(value = "controllerMethodAspect()")
    public  void after(JoinPoint joinPoint) {
    }

    @AfterThrowing(pointcut = "controllerMethodAspect()", throwing="e")
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
    }

    @AfterReturning(value = "controllerMethodAspect()", returning="retVal")
    public  void doAfterThrowing(JoinPoint joinPoint, Object retVal) {
    }


    private void recordLog(String descript,Long duration,String args,String methodName){
        //TODO 日志记录业务逻辑
        log.info("=====recordLog===params :descript={} ; duration={} ; args={} ; methodName={};"
        ,descript,duration,args,methodName);
    }



}
