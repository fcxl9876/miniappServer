package xin.fcxl9876.miniappserver.service;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.Header;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xin.fcxl9876.miniappserver.api.MiniappApi;
import xin.fcxl9876.miniappserver.entity.GPTResponse;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MiniappService implements MiniappApi {

    @Value("${ChatGPT.variables.apiKey}")
    private String apiKey;

    @Value("${ChatGPT.variables.maxTokens}")
    private String maxTokens;

    @Value("${ChatGPT.variables.model}")
    private String model;

    @Value("${ChatGPT.variables.temperature}")
    private String temperature;

    @Override
    public String send(String msg) {
        JSONObject bodyJson = new JSONObject();
        bodyJson.put("model", model);
        JSONObject msgJson = new JSONObject();
        msgJson.put("role", "user");
        msgJson.put("content", msg);
        JSONArray msgArray = new JSONArray();
        msgArray.add(msgJson);
        bodyJson.put("messages", msgArray);
        bodyJson.put("max_tokens", Integer.parseInt(maxTokens));
        bodyJson.put("temperature", Double.parseDouble(temperature));
        Map<String,Object> headMap = new HashMap<>();
        headMap.put("Authorization", "Bearer " + apiKey);

        log.info(JSONUtil.toJsonStr(bodyJson));

        HttpResponse httpResponse = HttpUtil.createPost("https://api.openai-proxy.com/v1/chat/completions")
                .header(Header.AUTHORIZATION, "Bearer " + apiKey)
                .body(JSONUtil.toJsonStr(bodyJson))
                .execute();
        String resStr = httpResponse.body();
        log.info("resStr: {}", resStr);

        GPTResponse gptResponse = JSONUtil.toBean(resStr, GPTResponse.class);

        return gptResponse.getChoices().get(0).getMessage().getContent().replaceAll("\\n","");
    }
}
