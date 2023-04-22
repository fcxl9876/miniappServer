package xin.fcxl9876.miniappserver.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import xin.fcxl9876.miniappserver.circle.sql.ParamPack;
import xin.fcxl9876.miniappserver.circle.sql.SQL;
import xin.fcxl9876.miniappserver.util.Page;


public interface IBaseDao {
	
	/**
	 * 自定义map生成对应的实体
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public List getListEntityMapper(String sql,RowMapper mapper,Object... args);
	
	
	/**
	 * 查询获取实体列表
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public List getListEntity(String sql,Class type,Object... args);

	/**
	 * 删除语句
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public int delete(String sql,Object... args);
	
	
	/**
	 * 更新语句
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public int update(String sql,Object... args);
	
	/**
	 * 插入语句
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public void insert(final String sql,final Object... args);
	
	/**
	 * 批处理插入语句
	 */
	public void insertBatch(final String sql,final List<Object[]> list);
	
	/**
	 * 执行语句
	 * 
	 * @param sql
	 * @return
	 */
	public void execute(String sql);
	/**
	 * 查询sql语句
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public List<Map<String, Object>> getListMap(String sql,Object... args);
	
	
	/**
	 * 查询sql语句
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public Map<String, Object> getSingleMap(String sql,Object... args);
	
	
	
	/**
	 * 查询汇总记录数
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public long getCount(String sql,Object... args);
	
	
	
	public List<Map<String, Object>> getSQLListMap(SQL sql,ParamPack params);
	
	
	/**
	 * 分页查询
	 * 
	 * @param sql
	 * @param pager
	 * @param params
	 * @param idAlias
	 */
	public void getPagerListMap(SQL sql,Page pager,ParamPack params,String idAlias,boolean autoCount);

}
