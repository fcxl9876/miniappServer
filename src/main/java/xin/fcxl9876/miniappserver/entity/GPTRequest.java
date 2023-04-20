package xin.fcxl9876.miniappserver.entity;

import lombok.Data;

/**
 * GPT-3 请求对象
 * @author fcxl9876
 */
@Data
public class GPTRequest {
    /**
     * 问题
     */
    private String askStr;

    /**
     * 回答
     */
    private String replyStr;
}
