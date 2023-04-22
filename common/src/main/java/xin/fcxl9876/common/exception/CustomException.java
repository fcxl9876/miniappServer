package xin.fcxl9876.common.exception;

import xin.fcxl9876.common.enums.ResultStatus;
import xin.fcxl9876.common.util.ResultEntity;
import lombok.Data;

/**
 * @author shaolay
 * @date 2023/2/18 10:49
 */
@Data
public class CustomException extends RuntimeException {

    private ResultEntity resultEntity;
    private String message;

    public CustomException(ResultStatus code, String message) {
        this.resultEntity = new ResultEntity(code, message);
        this.message = message;
    }
}
