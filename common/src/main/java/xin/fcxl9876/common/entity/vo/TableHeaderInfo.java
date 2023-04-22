package xin.fcxl9876.common.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 表格表头信息
 *
 * @author shaolay
 * @date 2023/2/23 15:14
 */
@Data
@Accessors(chain = true)
public class TableHeaderInfo {

    /**
     * 单位
     */
    private String unit;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

}
