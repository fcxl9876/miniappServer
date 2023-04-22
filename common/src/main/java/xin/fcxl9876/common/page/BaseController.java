package xin.fcxl9876.common.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import xin.fcxl9876.common.constant.BaseError;
import xin.fcxl9876.common.util.PageUtil;
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

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class BaseController {

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
         page = PageHelper.startPage(request.getPage(), request.getLimit());
    }

//    protected void startPage(CriterionRequest request, Class<?> entityClass) {
//        page = PageHelper.startPage(request.getPage(), request.getLimit(), PageUtil.buildOrderTo(request, entityClass));
//    }

    protected PageRequest buildPageRequest(CriterionRequest request) {
        return PageUtil.buildPageRequest(request);
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
