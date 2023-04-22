package xin.fcxl9876.common.entity.echart;

import lombok.Data;

import java.util.*;

/**
 * 分割数据到数个饼图：多饼图
 *
 * @author shaolay
 * @date 2023/3/16 13:39
 */
@Data
public class PartitionPies {

    List<List<Object>> source;

    public PartitionPies() {
        this.source = new ArrayList<>();
    }

    public void setTitle(String titleType, List<Object> titleData){
        titleData.add(0, titleType);
        source.add(titleData);
    }

    public void setDataList(Map<String, List<Object>> dataMap){
        for (String s : dataMap.keySet()) {
            setTitle(s, dataMap.get(s));
        }
    }

    public void setDataList(List<String> keys, List<Object> title, List<Object>... lists){
        source.add(title);
        for (int i = 0; i < keys.size(); i++) {
            List<Object> data = new ArrayList<>();
            for (List<Object> list : lists) {
                data.add(list.get(i));
            }
            setTitle(keys.get(i), data);
        }
    }
}
