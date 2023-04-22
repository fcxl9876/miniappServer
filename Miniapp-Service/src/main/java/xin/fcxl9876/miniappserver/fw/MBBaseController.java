package xin.fcxl9876.miniappserver.fw;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import xin.fcxl9876.common.page.ApiResponse;
import xin.fcxl9876.miniappserver.common.BaseError;
import xin.fcxl9876.miniappserver.util.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestControllerAdvice()
public class MBBaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 分页参数提取
     *
     * @param request
     */
    protected Page<Object> page;

    public static final String AJAX_SUCCESS = "success";
    public static final String AJAX_FAIL = "fail";
    public static final String DELETE_FAIL = "delete_fail";

    protected List<String> replaceDictCodeToName(List<String> codeList, String dictPath) {
        List<String> newList = new ArrayList<String>();
        Assert.notNull(dictPath, "dictionary Path can not be null!");

        return newList;
    }

    /**
     * 判断数据的唯一性
     *
     * @param service 服务类
     * @param obj     实体对象
     * @param column  唯一性字段属性
     * @return boolean true 不是唯一的
     **/
    public boolean noUnique(IService service, Object obj, String column) {
        Wrapper wrapper = new EntityWrapper();
        int mub = 1;
        try {
            // 获取属性值
            PropertyDescriptor pd = new PropertyDescriptor(column, obj.getClass());
            Method getMethod = pd.getReadMethod();// 获得get方法
            Object value = getMethod.invoke(obj);// 执行get方法返回一个Object
            // 获取属性对于对于的数据库字段
            String dbColumn = getValue(obj, column);
            wrapper.eq(dbColumn, value.toString());
            if (exist(obj, "delState")) {// 排除已删除数据
                wrapper.eq("del_state", "0");
            }
            // 查询数据库
            mub = service.selectList(wrapper).size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mub > 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取某个类的注解字段。
     *
     * @author Belen
     * @param o    对象
     * @param name 属性
     * @return
     */
    public static String getValue(Object o, String name) {
        Field[] field = o.getClass().getDeclaredFields();
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

    /**
     * @param obj
     * @param colum
     * @return boolean 存在返回true 判断类是否存在给出属性
     **/
    public static boolean exist(Object obj, String colum) {
        boolean flag = false;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(colum)) {
                flag = true;
                break;
            }
        }
        return flag;
    }



    protected void startPage(CriterionRequest request) {
         page = PageHelper.startPage(request.getPage(), request.getLimit(), request.orderBy());
    }

    protected void startPage(CriterionRequest request, Class<?> entityClass) {
        page = PageHelper.startPage(request.getPage(), request.getLimit(), PageUtils.buildOrderTo(request, entityClass));
    }

    protected PageRequest buildPageRequest(CriterionRequest request) {
        return PageUtils.buildPageRequest(request);
    }


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
    }


    @ExceptionHandler(RuntimeException.class)
    public ApiResponse exceptionHandle(RuntimeException e){
        log.error(BaseError.INNER_ERROR.getMsg() ,e);
        return ApiResponse.error(BaseError.INNER_ERROR.getCode(),e.getMessage());
    }
}
