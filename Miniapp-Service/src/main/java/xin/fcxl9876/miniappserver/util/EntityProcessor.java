//package xin.fcxl9876.miniappserver.util;
//
//import java.beans.BeanInfo;
//import java.beans.IntrospectionException;
//import java.beans.Introspector;
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Method;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import xin.fcxl9876.miniappserver.dao.impl.BaseDaoImpl;
//
//public class EntityProcessor {
//
//   protected static Log log = LogFactory.getLog(BaseDaoImpl.class);
//
//   protected static final int PROPERTY_NOT_FOUND = -1;
//
//   private static final Map<Class<?>, Object> primitiveDefaults = new HashMap<Class<?>, Object>();
//
//   static {
//       primitiveDefaults.put(Integer.TYPE, Integer.valueOf(0));
//       primitiveDefaults.put(Short.TYPE, Short.valueOf((short) 0));
//       primitiveDefaults.put(Byte.TYPE, Byte.valueOf((byte) 0));
//       primitiveDefaults.put(Float.TYPE, Float.valueOf(0f));
//       primitiveDefaults.put(Double.TYPE, Double.valueOf(0d));
//       primitiveDefaults.put(Long.TYPE, Long.valueOf(0L));
//       primitiveDefaults.put(Boolean.TYPE, Boolean.FALSE);
//       primitiveDefaults.put(Character.TYPE, Character.valueOf((char) 0));
//   }
//
//     private static final EntityProcessor single = new EntityProcessor();
//
//     private EntityProcessor() {}
//
//	 public static EntityProcessor getInstance() {
//		 return single;
//	 }
//
//     public <T> T toBean(ResultSet rs, Class<T> type) throws Exception {
//       PropertyDescriptor[] props = this.propertyDescriptors(type);
//       ResultSetMetaData rsmd = rs.getMetaData();
//       int[] columnToProperty = this.mapColumnsToProperties(rsmd, props);
//       return this.createBean(rs, type, props, columnToProperty);
//   }
//
//
//    protected int[] mapColumnsToProperties(ResultSetMetaData rsmd,PropertyDescriptor[] props) throws SQLException {
//        int cols = rsmd.getColumnCount();
//        int[] columnToProperty = new int[cols + 1];
//        Arrays.fill(columnToProperty, PROPERTY_NOT_FOUND);
//
//        for (int col = 1; col <= cols; col++) {
//            String columnName = rsmd.getColumnLabel(col);
//            if (null == columnName || 0 == columnName.length()) {
//              columnName = rsmd.getColumnName(col);
//            }
//            String propertyName = getColumn2Property(columnName);
//            if (propertyName == null) {
//                propertyName = columnName;
//            }
//            for (int i = 0; i < props.length; i++) {
//                if (propertyName.equalsIgnoreCase(props[i].getName())) {
//                    columnToProperty[col] = i;
//                    break;
//                }
//            }
//        }
//        return columnToProperty;
//    }
//
//    private <T> T createBean(ResultSet rs, Class<T> type,PropertyDescriptor[] props, int[] columnToProperty)throws Exception {
//        T bean = this.newInstance(type);
//        for (int i = 1; i < columnToProperty.length; i++) {
//
//            if (columnToProperty[i] == PROPERTY_NOT_FOUND) {
//                continue;
//            }
//
//            PropertyDescriptor prop = props[columnToProperty[i]];
//            Class<?> propType = prop.getPropertyType();
//
//            Object value = this.processColumn(rs, i, propType);
//
//            if (propType != null && value == null && propType.isPrimitive()) {
//                value = primitiveDefaults.get(propType);
//            }
//
//            this.callSetter(bean, prop, value);
//        }
//
//        return bean;
//    }
//
//    private void callSetter(Object target, PropertyDescriptor prop, Object value) throws Exception {
//
//		Method setter = prop.getWriteMethod();
//
//		if (setter == null) {
//		    return;
//		}
//		Class<?>[] params = setter.getParameterTypes();
//	    if (value instanceof java.util.Date) {
//	        final String targetType = params[0].getName();
//	        if ("java.sql.Date".equals(targetType)) {
//	            value = new java.sql.Date(((java.util.Date) value).getTime());
//	          //  value = new Date(Long.valueOf(value.toString()));
//	        } else
//	        if ("java.sql.Time".equals(targetType)) {
//	            value = new java.sql.Time(((java.util.Date) value).getTime());
//	           // value = new Date(Long.valueOf(value.toString()));
//	        } else
//	        if ("java.sql.Timestamp".equals(targetType)) {
//	            value = new java.sql.Timestamp(((java.util.Date) value).getTime());
//	        //    value = new Date(Long.valueOf(value.toString()));
//	        }
//	    }
//
//	    // 类型不匹配不设置
//	    if (this.isCompatibleType(value, params[0])) {
//	        setter.invoke(target, new Object[]{value});
//	    } else {
//	      throw new SQLException(
//	          "Cannot set " + prop.getName() + ": incompatible types, cannot convert "
//	          + value.getClass().getName() + " to " + params[0].getName());
//
//	    }
//
//
//     }
//
//
//    private Object callGetter(Object target, PropertyDescriptor prop) throws Exception{
//		Method getter = prop.getReadMethod();
//		if (getter == null) {
//		    return null;
//		}
//		return getter.invoke(target);
//     }
//
//
//    protected Object processColumn(ResultSet rs, int index, Class<?> propType) throws SQLException {
//	    	if(rs==null||propType==null){
//	    		return null;
//	    	}
//
//		    if ( !propType.isPrimitive() && rs.getObject(index) == null ) {
//		        return null;
//		    }
//
//		    if (propType.equals(String.class)) {
//		        return rs.getString(index);
//
//		    } else if (
//		        propType.equals(Integer.TYPE) || propType.equals(Integer.class)) {
//		        return Integer.valueOf(rs.getInt(index));
//
//		    } else if (
//		        propType.equals(Boolean.TYPE) || propType.equals(Boolean.class)) {
//		        return Boolean.valueOf(rs.getBoolean(index));
//
//		    } else if (propType.equals(Long.TYPE) || propType.equals(Long.class)) {
//		        return Long.valueOf(rs.getLong(index));
//
//		    } else if (
//		        propType.equals(Double.TYPE) || propType.equals(Double.class)) {
//		        return Double.valueOf(rs.getDouble(index));
//
//		    } else if (
//		        propType.equals(Float.TYPE) || propType.equals(Float.class)) {
//		        return Float.valueOf(rs.getFloat(index));
//
//		    } else if (
//		        propType.equals(Short.TYPE) || propType.equals(Short.class)) {
//		        return Short.valueOf(rs.getShort(index));
//
//		    } else if (propType.equals(Byte.TYPE) || propType.equals(Byte.class)) {
//		        return Byte.valueOf(rs.getByte(index));
//
//		    } else if (propType.equals(Timestamp.class)) {
//		        return rs.getTimestamp(index);
//
//		    }  else {
//		        return rs.getObject(index);
//		    }
//
//}
//
//
//    protected <T> T newInstance(Class<T> c) throws Exception {
//            return c.newInstance();
//    }
//
//
//    private PropertyDescriptor[] propertyDescriptors(Class<?> c) throws SQLException {
//       BeanInfo beanInfo = null;
//	    try {
//	        beanInfo = Introspector.getBeanInfo(c);
//
//	    } catch (IntrospectionException e) {
//	        throw new SQLException(
//	            "Bean introspection failed: " + e.getMessage());
//	    }
//    return beanInfo.getPropertyDescriptors();
//  }
//
//    private boolean isCompatibleType(Object value, Class<?> type) {
//        if (value == null || type.isInstance(value)) {
//            return true;
//
//        } else if (type.equals(Integer.TYPE) && Integer.class.isInstance(value)) {
//            return true;
//
//        } else if (type.equals(Long.TYPE) && Long.class.isInstance(value)) {
//            return true;
//
//        } else if (type.equals(Double.TYPE) && Double.class.isInstance(value)) {
//            return true;
//
//        } else if (type.equals(Float.TYPE) && Float.class.isInstance(value)) {
//            return true;
//
//        } else if (type.equals(Short.TYPE) && Short.class.isInstance(value)) {
//            return true;
//
//        } else if (type.equals(Byte.TYPE) && Byte.class.isInstance(value)) {
//            return true;
//
//        } else if (type.equals(Character.TYPE) && Character.class.isInstance(value)) {
//            return true;
//
//        } else if (type.equals(Boolean.TYPE) && Boolean.class.isInstance(value)) {
//            return true;
//
//        }
//        return false;
//
//    }
//
//    public String getColumn2Property(String column){
//    	StringBuffer sb = new StringBuffer();
//    	boolean b = false;
//    	for (int i = 0; i < column.length(); i++) {
//		  	    char str = column.charAt(i);
//		  	    if (str=='_') {
//		  	    	b = true;
//		  	    	continue;
//				}else {
//					if (b) {
//				  	    sb.append(Character.toUpperCase(str));
//					}else {
//				  	    sb.append(Character.toLowerCase(str));
//					}
//					b = false;
//				}
//		}
//    	return sb.toString();
//    }
//
//    public String getProperty2Column(String property){
//    	StringBuffer sb = new StringBuffer();
//    	for (int i = 0; i < property.length(); i++) {
//		  	    char str = property.charAt(i);
//		  	    if (Character.isUpperCase(str) == true) {
//		  	    	sb.append("_");
//				}
//		  	    sb.append(str);
//		}
//    	return sb.toString();
//
//    }
//
//    public String getEntity2Table(String entityName){
//    	StringBuffer sb = new StringBuffer();
//    	boolean b = false;
//    	for (int i = 0; i < entityName.length(); i++) {
//		  	    char str = entityName.charAt(i);
//		  	    if (b&&Character.isUpperCase(str) == true) {
//		  	    	sb.append("_");
//				}
//		  	    sb.append(str);
//		  	    b=true;
//		}
//    	return sb.toString();
//    }
//
//    public String getInsertSql(Object entity,List<Object> param,String... args) throws Exception {
// 	     Class clz = entity.getClass();
// 	     String entityName  = clz.getSimpleName();
//	     String tableName = getEntity2Table(entityName);
//		 StringBuffer sb = new StringBuffer();
//		 PropertyDescriptor[] props = this.propertyDescriptors(clz);
//		 if (args == null||args.length==0) {
//			 args = EntityIgnoreProperty.get(clz.getName());
//			 if (args==null) {
//				 args = new String[]{};
//			}
//		 }
//		 sb.append("insert into ");
//		 sb.append(tableName);
//
//		 StringBuffer params = new StringBuffer();
//		 StringBuffer columns = new StringBuffer();
//		 for (int i = 0; i < props.length; i++) {
//			 boolean b = true;
//			 String property = props[i].getName();
//			 if (property.endsWith(Globals.defaultEntityText)) {
//				 continue;
//			 }
//			 if(property.endsWith("class")){
//				 continue;
//			 }
//
//			 for (int j = 0; j < args.length; j++) {
//				if (property.equals(args[j])) {
//					 b = false;
//					 break;
//				}
//			 }
//			 if (b) {
//				 Object value = callGetter(entity, props[i]);
//				 columns.append(","+getProperty2Column(property));
//				 params.append(",?");
//				 param.add(value);
//			 }
//		 }
//		 sb.append(" ("+columns.substring(1)+")values("+params.substring(1)+") ");
//		 log.info(sb.toString());
//		 return sb.toString().toUpperCase();
//    }
//
//
//
//    public String getUpdateSql(Object entity,List<Object> param,String... args) throws Exception {
//	     Class clz = entity.getClass();
// 	     String entityName  = clz.getSimpleName();
//	     String tableName = getEntity2Table(entityName);
//		 StringBuffer sb = new StringBuffer();
//		 PropertyDescriptor[] props = this.propertyDescriptors(clz);
//		 if (args == null||args.length==0) {
//			 args = EntityIgnoreProperty.get(clz.getName());
//			 if (args==null) {
//				 args = new String[]{};
//			}
//		 }
//		 sb.append("update ");
//		 sb.append(tableName+" set ");
//		 StringBuffer columns = new StringBuffer();
//		 Object id = null;
//		 for (int i = 0; i < props.length; i++) {
//			 String property = props[i].getName();
//			 if (property.endsWith(Globals.defaultEntityText)) {
//				 continue;
//			 }
//			 if(property.endsWith("class")){
//				 continue;
//			 }
//			 if ("id".equals(property)) {
//				 id = callGetter(entity, props[i]);
//				 continue;
//			 }
//			 boolean b = true;
//			 for (int j = 0; j < args.length; j++) {
//				if (property.equals(args[j])) {
//					 b = false;
//					 break;
//				}
//			 }
//			 if (b) {
//				 Object value = callGetter(entity, props[i]);
//				 columns.append(","+getProperty2Column(property)+"=?");
//				 param.add(value);
//			 }
//		 }
//		 sb.append(columns.substring(1)+" where id=? ");
//		 param.add(id);
//		 log.info(sb.toString());
//		 return sb.toString().toUpperCase();
//    }
//
//    public String getSelectSql(Class clz,String... args) throws Exception {
//	     String entityName = clz.getSimpleName();
//	     String tableName = getEntity2Table(entityName);
//		 StringBuffer sb = new StringBuffer();
//		 PropertyDescriptor[] props = this.propertyDescriptors(clz);
//		 if (args == null||args.length==0) {
//			 args = EntityIgnoreProperty.get(clz.getName());
//			 if (args==null) {
//				 args = new String[]{};
//			}
//		 }
//		 sb.append("select ");
//		 StringBuffer columns = new StringBuffer();
//		 for (int i = 0; i < props.length; i++) {
//			 boolean b = true;
//			 String property = props[i].getName();
//			 if (property.endsWith(Globals.defaultEntityText)) {
//				 continue;
//			 }
//			 if(property.endsWith("class")){
//				 continue;
//			 }
//			 for (int j = 0; j < args.length; j++) {
//				if (property.equals(args[j])) {
//					 b = false;
//					 break;
//				}
//			 }
//			 if (b) {
//				 columns.append(","+getProperty2Column(property));
//			 }
//		 }
//		 sb.append(" "+columns.substring(1) );
//		 sb.append(" from ");
//		 sb.append(tableName);
//		 sb.append(" where id=? ");
//		 log.info(sb.toString());
//		 return sb.toString().toUpperCase();
//
//    }
//
//    public static void main(String[] args) throws Exception {
//		//System.out.println(EntityProcessor.getInstance().getUpdateSql(new PpXl(),new ArrayList<Object>()));
//	}
//
//
//
//}
