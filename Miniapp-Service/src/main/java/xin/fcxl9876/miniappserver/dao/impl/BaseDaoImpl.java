//package xin.fcxl9876.miniappserver.dao.impl;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.BatchPreparedStatementSetter;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.JdbcUtils;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Service;
//
//import xin.fcxl9876.miniappserver.circle.dialect.IDialectService;
//import xin.fcxl9876.miniappserver.circle.sql.Limits;
//import xin.fcxl9876.miniappserver.circle.sql.ParamPack;
//import xin.fcxl9876.miniappserver.circle.sql.SQL;
//import xin.fcxl9876.miniappserver.circle.sql.SQLUtils;
//import xin.fcxl9876.miniappserver.dao.IBaseDao;
//import xin.fcxl9876.miniappserver.util.EntityMapper;
//import xin.fcxl9876.miniappserver.util.FreedomUtil;
//import xin.fcxl9876.miniappserver.util.Globals;
//import xin.fcxl9876.miniappserver.util.Page;
//import xin.fcxl9876.miniappserver.util.Pager;
//import xin.fcxl9876.miniappserver.util.SQLParse;
//
//@Service
//public class BaseDaoImpl implements IBaseDao {
//
//	protected static Log log = LogFactory.getLog(BaseDaoImpl.class);
//
//    @Autowired @Qualifier("dialect_oraclesql")
//	private IDialectService dialect;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//	/**
//	 * 自定义map生成对应的实体
//	 *
//	 * @param sql
//	 * @param parameters
//	 * @return
//	 */
//	public List getListEntityMapper(String sql,RowMapper mapper,Object... args){
//		return  jdbcTemplate.query(sql,mapper ,args);
//	}
//
//
//
//	/**
//	 * 查询获取实体列表
//	 *
//	 * @param sql
//	 * @param parameters
//	 * @return
//	 */
//	public List getListEntity(String sql,Class type,Object... args){
//		return  jdbcTemplate.query(sql, args, new EntityMapper(type));
//	}
//
//
//
//	/**
//	 * 删除语句
//	 *
//	 * @param sql
//	 * @param parameters
//	 * @return
//	 */
//	public int delete(String sql,Object... args){
//	     return	jdbcTemplate.update(sql, args);
//	}
//
//
//	/**
//	 * 更新语句
//	 *
//	 * @param sql
//	 * @param parameters
//	 * @return
//	 */
//	public int update(String sql,Object... args){
//	     return	jdbcTemplate.update(sql, args);
//	}
//
//
//
//	/**
//	 * 插入语句
//	 *
//	 * @param sql
//	 * @param parameters
//	 * @return
//	 */
//	public void insert(final String sql,final Object... args){
//		    KeyHolder keyHolder = new GeneratedKeyHolder();//
//	     	jdbcTemplate.update(new PreparedStatementCreator() {
//				public PreparedStatement createPreparedStatement(Connection con)throws SQLException {
//					    PreparedStatement ps=con.prepareStatement(sql,new int[]{1});
//					    if (args!=null&&args.length>0) {
//						    for (int i = 0; i < args.length; i++) {
//								// 设置参数
//								ps.setObject(i + 1, args[i]);
//							}
//						}
//		              return ps;
//				}
//	     	}, keyHolder);
////	     return keyHolder.getKey().toString();
//	}
//
//	/**
//	 * 批处理插入语句
//	 */
//	public void insertBatch(final String sql,final List<Object[]> list){
//     	jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
//             //执行次数
//             public int getBatchSize() {
//                 return list.size();
//             }
//             //执行参数
//             public void setValues(PreparedStatement ps, int i)throws SQLException {
//            	 Object[] objs = list.get(i);
//            	 for(int j = 0; j < objs.length; j++){
//            		 ps.setObject(j+1, objs[j]);
//            	 }
//             }
//         });
//    }
//
//
//
//	/**
//	 * 执行语句
//	 *
//	 * @param sql
//	 * @return
//	 */
//	public void execute(String sql){
//	    jdbcTemplate.execute(sql);
//	}
//
//	/**
//	 * 查询sql语句
//	 *
//	 * @param sql
//	 * @param parameters
//	 * @return
//	 */
//	public List<Map<String, Object>> getListMap(String sql,Object... args){
//		if (Globals.devMode) {
//	 	  int index=sql.indexOf("*");
//	 	  if (index!=-1) {
//			// throw new RuntimeException("你的sql含有*号");
//		  }
//		}
//		return  jdbcTemplate.query(sql, args, new RowMapper<Map<String, Object>>(){
//			public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Map<String, Object> map = new HashMap<String, Object>();
//				ResultSetMetaData rsmd = rs.getMetaData();
//				int columnCount = rsmd.getColumnCount();
//				for (int i = 1; i <= columnCount; i++) {
//					String key = JdbcUtils.lookupColumnName(rsmd, i);
//					Object obj = rs.getObject(i);
//
//					if ("ID".equalsIgnoreCase(key)) {
//						map.put("id", obj);
//					}
//					boolean flag = "SSDQBH".equalsIgnoreCase(key);
//					if (obj!=null&&flag) {
//						//map.put("SSDQBHTEXT", Cache.getDistrict().getSNameByBh(obj.toString()));
//					}
//					if (obj != null && "java.sql.Timestamp".equals(obj.getClass().getName())) {
//						Calendar cal = Calendar.getInstance();
//						cal.setTimeInMillis(((Timestamp)obj).getTime());
//						map.put(key.toUpperCase()+"TEXT",FreedomUtil.getDateStrFm(cal) );
//					}
//					map.put(key.toUpperCase(), obj);
//				}
//				return map;
//			}
//		});
//	}
//
//
//	/**
//	 * 查询sql语句
//	 *
//	 * @param sql
//	 * @param parameters
//	 * @return
//	 */
//	public List<Map<String, Object>> getSQLListMap(SQL sql,ParamPack params){
//			String sqlStr = sql.toSqlStr();
//			StringBuilder buf = new StringBuilder(sqlStr.length());
//			String[] p_names = SQLUtils.parseParamsPattern(sqlStr, buf);
//	    	Object[] p_values = SQLUtils.buildNamedParamValues(p_names, params);
//	    	Map<String,Boolean> parametersFirst = new HashMap<String, Boolean>();
//		    sqlStr = SQLUtils.buildDialectLimitQuery(sqlStr, sql.getLimits(), dialect,parametersFirst);
//			Object[] values = SQLUtils.buildDialectParamBindValues(p_values, sql.getLimits(), dialect,parametersFirst);
//		return getListMap(sqlStr,values);
//	}
//
//
//
//
//	public void getPagerListMap(SQL sql,Pager pager,ParamPack params,String idAlias,boolean autoCount){
//		        sql.setLimits(Limits.of(pager.getFirstRow(), pager.getPageSize()));
//				String sqlStr = sql.toSqlStr();
//				StringBuilder buf = new StringBuilder(sqlStr.length());
//				String[] p_names = SQLUtils.parseParamsPattern(sqlStr, buf);
//		    	Object[] p_values = SQLUtils.buildNamedParamValues(p_names, params);
//		    	if (autoCount) {
//			        String countSql = SQLParse.replaceCountColumn(sqlStr,idAlias);
//			        countSql = countSql.replaceAll(" AS ", " ");
//			        long  records = getCount(countSql, p_values);
//			        pager.setRecords(records);
//				}
//		    	Map<String,Boolean> parametersFirst = new HashMap<String, Boolean>();
//			    sqlStr = SQLUtils.buildDialectLimitQuery(sqlStr, sql.getLimits(), dialect,parametersFirst);
//				Object[] values = SQLUtils.buildDialectParamBindValues(p_values, sql.getLimits(), dialect, parametersFirst);
//				sqlStr = sqlStr.replaceAll(" AS ", sqlStr);
//		        List<Map<String, Object>> rows = getListMap(sqlStr, values);
//		        pager.setRows(rows);
//
//	}
//
//
//	/**
//	 * 查询sql语句
//	 *
//	 * @param sql
//	 * @param parameters
//	 * @return
//	 */
//	public Map<String, Object> getSingleMap(String sql,Object... args){
//		  if (Globals.devMode) {
//			  int index = sql.indexOf("*");
//			  if (index!=-1) {
//				// throw new RuntimeException("你的sql含有*号");
//			  }
////			  System.out.println(sql);
//		   }
//		    List<Map<String, Object>>  mapList = jdbcTemplate.query(sql, args, new RowMapper<Map<String, Object>>(){
//			public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Map<String, Object> map = new HashMap<String, Object>();
//				ResultSetMetaData rsmd = rs.getMetaData();
//				int columnCount = rsmd.getColumnCount();
//				for (int i = 1; i <= columnCount; i++) {
//					String key =JdbcUtils.lookupColumnName(rsmd, i);
//					Object obj = rs.getObject(i);
//					map.put(key.toUpperCase(), obj);
//				}
//				return map;
//			}
//		  });
//		    if (mapList!=null&&mapList.size()>0) {
//				return mapList.get(0);
//			}else {
//				return null;
//			}
//	}
//
//
//	/**
//	 * 查询汇总记录数
//	 *
//	 * @param sql
//	 * @param parameters
//	 * @return
//	 */
//	public long getCount(String sql,Object... args){
//		System.out.println(sql);
//		if (Globals.devMode) {
//			int index = sql.indexOf("*");
//			 if (index!=-1) {
//				// throw new RuntimeException("你的sql含有*号");
//			 }
//			 System.out.println(sql);
//		}
//		 return jdbcTemplate.queryForObject(sql, args,Long.class);
//	}
//
//
//
//	@Override
//	public void getPagerListMap(SQL sql, Page pager, ParamPack params, String idAlias, boolean autoCount) {
//		// TODO Auto-generated method stub
//
//	}
//
//
//
//
//
//
//}
