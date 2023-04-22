package xin.fcxl9876.common.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author shaolay
 * @date 2023/3/27 15:28
 */
@Data
public class DataAuditVo {

    private String comment;

    //登陆用户id
    private String userId;

    private List<DataAuditBo> list;
}
