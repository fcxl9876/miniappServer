package xin.fcxl9876.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import xin.fcxl9876.common.enums.ResultStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 对象转换工具,处理远程调用结果
 *
 * @author jht
 */
@Component
public class ObjectMapperUtil {
    private final ObjectMapper objectMapper;

    public ObjectMapperUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 将数据转化为需求的对象
     *
     * @param src
     * @param clazz
     * @return List<T>
     * @author 蒋恒涛
     * @date 2018年7月26日下午2:10:33
     */
    public <T> List<T> convertList(List<Object> src, Class<T> clazz) {
        if (src == null || src.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>();
        for (Object o : src) {
            T ele = objectMapper.convertValue(o, clazz);
            result.add(ele);
        }
        return result;
    }

    /**
     * 将数据转化为单个对象
     *
     * @param src
     * @param clazz
     * @return T
     * @author 蒋恒涛
     * @date 2018年7月26日下午2:12:04
     */
    public <T> T convertObject(Object src, Class<T> clazz) {

        return objectMapper.convertValue(src, clazz);
    }

    /**
     * 解析返回单个对象数据
     *
     * @param src
     * @param clazz
     * @return T
     * @author 蒋恒涛
     * @date 2018年7月26日下午2:15:19
     */
    public <T> T convertObject(ResultEntity src, Class<T> clazz) {
        if (src == null || ResultStatus.SUCCESS.getStatusCode() != src.getCode()) {
            return null;
        }
        @SuppressWarnings("unchecked") Object o = ((Map<String, Object>) src.getContent()).get("info");
        return objectMapper.convertValue(o, clazz);
    }

    /**
     * 将返回数据转化为list，当返回内容为null时返回list size为0
     *
     * @param src
     * @param clazz
     * @return List<T>
     * @author 蒋恒涛
     * @date 2018年7月26日下午2:24:57
     */
    public <T> List<T> convertList(ResultEntity src, Class<T> clazz) {
        if (src == null || ResultStatus.SUCCESS.getStatusCode() != src.getCode()) {
            return Collections.emptyList();
        }
        @SuppressWarnings("unchecked") List<Object> list = (List<Object>) ((Map<String, Object>) src.getContent()).get("dataList");
        return convertList(list, clazz);
    }

    public List<Map<String, Object>> convertListMap(ResultEntity src) {
        if (src == null || ResultStatus.SUCCESS.getStatusCode() != src.getCode()) {
            return Collections.emptyList();
        }
        @SuppressWarnings("unchecked") List<Map<String, Object>> list = (List<Map<String, Object>>) ((Map<String, Object>) src.getContent()).get("dataList");
        return list;
    }
}
