package xin.fcxl9876.miniappserver.fw;

import java.util.List;
import java.util.Map;

public abstract class BaseDao<T,PK> {
    public abstract BaseMapper<T, PK> getMapper();

    public int save(T t){
        return getMapper().insert(t);
    }

    public int saveList(List<T> list){
        return getMapper().insertList(list);
    }

    public int update(T t){
        return getMapper().updateByPrimaryKeySelective(t);
    }

    public T selectById(PK id){
        return getMapper().selectByPrimaryKey(id);
    }

    public List<T> selectByIds(List<PK> ids){
        return getMapper().selectByIdList(ids);
    }

    public List<T> selectAll(){
        return getMapper().selectAll();
    }

    public int delete(T t){
        return getMapper().delete(t);
    }

    public int deleteById(PK id){
        return getMapper().deleteByPrimaryKey(id);
    }

    public int deleteByIds(List<PK> ids){
        return getMapper().deleteByIdList(ids);
    }

    public List<Map<String,Object>> queryBySql(String sql){
        return getMapper().queryBySql(sql);
    }
}
