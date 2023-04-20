package xin.fcxl9876.miniappserver.entity;

import lombok.Data;

/**
 * GPT-3 返回choice对象
 * @author fcxl9876
 */
@Data
public class GPTChoice {

    private String text;

    private Integer index;
}