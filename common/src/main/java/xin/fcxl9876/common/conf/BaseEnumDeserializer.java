package xin.fcxl9876.common.conf;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import xin.fcxl9876.common.enums.BaseEnum;

import java.io.IOException;

/**
 * 接口定义Json的反序化类
 *
 * @author shaolay
 * @date 2023/3/8 11:02
 */
public class BaseEnumDeserializer extends JsonDeserializer<BaseEnum> implements ContextualDeserializer {
    private BaseEnum[] enums;

    @Override
    public BaseEnum deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        String val = p.getText();
        for (BaseEnum e : enums) {
            if (e.getCode().toString().equals(val)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctx, BeanProperty property) {
        this.enums = (BaseEnum[]) property.getType().getRawClass().getEnumConstants();
        return this;
    }
}
