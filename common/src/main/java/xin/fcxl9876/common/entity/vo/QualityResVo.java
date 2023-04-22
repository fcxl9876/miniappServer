package xin.fcxl9876.common.entity.vo;

import lombok.Data;

/**
 * @author shaolay
 * @date 2023/3/21 14:51
 */
@Data
public class QualityResVo {

    /**
     * 质控结果
     */
    private String result;

    /**
     * 质控措施技术要求
     */
    private String tr;

    /**
     * 误差
     */
    private String error;

    /**
     * 误差类型
     */
    private String errorType;

}
