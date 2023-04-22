package xin.fcxl9876.common.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import xin.fcxl9876.common.conf.BaseEnumDeserializer;

/**
 * 通用枚举接口
 *
 * @author shaolay
 * @date 2023/3/8 10:16
 */
@JsonDeserialize(using = BaseEnumDeserializer.class)
public interface BaseEnum {

    String getCode();
}
