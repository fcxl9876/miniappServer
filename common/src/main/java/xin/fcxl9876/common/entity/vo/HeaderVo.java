package xin.fcxl9876.common.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author shaolay
 * @date 2023/4/4 13:42
 */
@Data
public class HeaderVo {

    private String name;

    private String prop;

    private List<HeaderVo> children;
}
