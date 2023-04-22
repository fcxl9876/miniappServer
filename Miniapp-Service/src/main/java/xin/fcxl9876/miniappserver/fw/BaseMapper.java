package xin.fcxl9876.miniappserver.fw;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertUseGeneratedKeysMapper;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T,PK> extends Mapper<T>, InsertUseGeneratedKeysMapper<T>, InsertListMapper<T>, IdListMapper<T,PK> {

    List<Map<String,Object>> queryBySql(@Param("sql") String sql);


}
