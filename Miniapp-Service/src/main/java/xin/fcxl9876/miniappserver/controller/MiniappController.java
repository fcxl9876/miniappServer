package xin.fcxl9876.miniappserver.controller;

import cn.hutool.json.JSONUtil;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.fcxl9876.miniappserver.entity.GPTRequest;
import xin.fcxl9876.miniappserver.service.MiniappService;

/**
 * GPT-3接口
 *
 * @author fcxl9876
 */
@Slf4j
@RestController
@RequestMapping("/miniapp")
public class MiniappController {

    @Resource
    private MiniappService miniappService;

    /**
     * openAI GPT-3
     *
     * @param gptRequest 条件对象
     * @return 出参对象
     */
    @PostMapping("/chatGPT")
    public String askAi(@RequestBody GPTRequest gptRequest) {

        String replyStr = miniappService.send(gptRequest.getAskStr());

        gptRequest.setReplyStr(replyStr);

        return JSONUtil.toJsonStr(gptRequest);
    }
}