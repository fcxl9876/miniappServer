package xin.fcxl9876.common.conf;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @description  自定义注解表示,加在类上表示是一个JpaDto类
 */

@Documented
@Component
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JpaDto {
}
