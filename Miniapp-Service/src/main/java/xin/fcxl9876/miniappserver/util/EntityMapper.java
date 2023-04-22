//package xin.fcxl9876.miniappserver.util;
//
//import java.sql.ResultSet;
//
//import org.springframework.jdbc.core.RowMapper;
//
//public class EntityMapper<T> implements RowMapper<T>{
//
//	private final Class<T> type;
//
//	public EntityMapper(Class<T> type){
//		this.type = type;
//	}
//
//	public T  mapRow(ResultSet rs, int rowNum) {
//		try {
//			return EntityProcessor.getInstance().toBean(rs, type);
//		} catch (Exception e) {
//		      throw new RuntimeException(e);
//		}
//	}
//
//}
