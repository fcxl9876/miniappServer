package xin.fcxl9876.common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import xin.fcxl9876.common.entity.vo.AreaTreeVo;

import java.util.List;
import java.util.Map;

public class AreaTreeUtil {
    private List<AreaTreeVo> rootList; //根节点对象存放到这里

    private List<AreaTreeVo> bodyList; //其他节点存放到这里，可以包含根节点

    public AreaTreeUtil(List<AreaTreeVo> rootList, List<AreaTreeVo> bodyList) {
        this.rootList = rootList;
        this.bodyList = bodyList;
    }

    public List<AreaTreeVo> getTree(){   //调用的方法入口
        if(bodyList != null && !bodyList.isEmpty()){
        //声明一个map，用来过滤已操作过的数据
            Map<String,String> map = Maps.newHashMapWithExpectedSize(bodyList.size());
            rootList.forEach(beanTree -> getChild(beanTree,map));
            return rootList;
        }
        return null;
    }

    public void getChild(AreaTreeVo beanTree,Map<String,String> map){
        List<AreaTreeVo> children = Lists.newArrayList();
        bodyList.stream()
                .filter(c -> !map.containsKey(c.getNodeId()))
                .filter(c -> (c.getPid() == null ? "" :c.getPid()).equals(beanTree.getNodeId()))
                .forEach(c ->{
                    map.put(c.getNodeId(),c.getPid());
                    getChild(c,map);
                    children.add(c);
                });
        beanTree.setChildren(children);
    }

}

