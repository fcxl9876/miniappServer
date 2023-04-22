package xin.fcxl9876.miniappserver.handler;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import xin.fcxl9876.miniappserver.util.CookiesUtil;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;


/**
 * 元数据对象处理器，实现 MetaObjectHandler接口
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

    /**
     * 测试 user 表 name 字段为空自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        // 获取请求体 request
        HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) requestAttributes).getRequest();
        String userId = CookiesUtil.getCookieValue(request, "userId");
        if (null == userId || userId.isEmpty()){
            userId = "0";
        }
        Object createdDate = getFieldValByName("createdDate", metaObject);
        Object created_by = getFieldValByName("createdBy", metaObject);
        if (createdDate == null) {
            setFieldValByName("createdDate", new Date(), metaObject);
        }
        if (created_by == null) {
            setFieldValByName("createdBy", userId, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        // 获取请求体 request
        HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) requestAttributes).getRequest();
        String userId = CookiesUtil.getCookieValue(request, "userId");
        if (null == userId || userId.isEmpty()){
            userId = "0";
        }
        setFieldValByName("updatedDate", new Date(), metaObject);
        setFieldValByName("updatedBy", userId, metaObject);
    }
}