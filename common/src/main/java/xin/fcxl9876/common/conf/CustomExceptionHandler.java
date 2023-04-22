package xin.fcxl9876.common.conf;

import xin.fcxl9876.common.constant.ResultConstants;
import xin.fcxl9876.common.enums.ResultStatus;
import xin.fcxl9876.common.exception.CustomException;
import xin.fcxl9876.common.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * @author shaolay
 * @date 2023/2/22 14:25
 */
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = CustomException.class)
    public ResultEntity customExceptionHandler(CustomException e){
        log.error(e.getResultEntity().getMessage(), e);
        return e.getResultEntity();
    }

    /**
     * 处理其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResultEntity exceptionHandler(Exception e){
        log.error(e.getMessage(), e);
        return new ResultEntity(ResultStatus.ERROR, ResultConstants.INTERNAL_SERVER_ERROR);
    }
}
