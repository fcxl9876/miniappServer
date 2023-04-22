package xin.fcxl9876.miniappserver.circle.sql;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Types;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class SQLTypeMapping {

	private static final Map<String, Integer> AliasSQLTypeMap = new HashMap<String, Integer>();
    
	private static final Map<String, Class<?>> AliasClassTypeMap = new HashMap<String, Class<?>>();	

	private static final Map<Class<?>, String> ClassTypeAliasMap = new HashMap<Class<?>, String>();

    /**
     * 未知数据类型<br>
	 * Value for the unknown type
	 */
	public static final int UNKNOWN_TYPE = -99999999;

	/**
	 * Value for a JDBC 3.o datalink type
	 */
	public final static int JDBC_30_DATALINK = 70;

	/**
	 * Value for a JDBC 3.o boolean type
	 */
	public final static int JDBC_30_BOOLEAN = 16;
    
	private SQLTypeMapping() {
		
	}
	
	static {
		registerSQLTypeAlias("char",Types.CHAR);
		registerSQLTypeAlias("str", Types.VARCHAR);
		registerSQLTypeAlias("string", Types.VARCHAR);
		registerSQLTypeAlias("boolean", JDBC_30_BOOLEAN);
		registerSQLTypeAlias("byte", Types.TINYINT);
		registerSQLTypeAlias("short", Types.SMALLINT);
		registerSQLTypeAlias("integer", Types.INTEGER);
		registerSQLTypeAlias("int", Types.INTEGER);
		registerSQLTypeAlias("long", Types.NUMERIC);
		registerSQLTypeAlias("float", Types.FLOAT);
		registerSQLTypeAlias("double", Types.DOUBLE);
		registerSQLTypeAlias("numeric", Types.NUMERIC);
		registerSQLTypeAlias("big_decimal", Types.NUMERIC);
		registerSQLTypeAlias("decimal", Types.DECIMAL);
		registerSQLTypeAlias("bigint", Types.BIGINT);
		registerSQLTypeAlias("big_int", Types.BIGINT);
		registerSQLTypeAlias("date", Types.DATE);
        registerSQLTypeAlias("datetime", Types.DATE);
        registerSQLTypeAlias("time", Types.TIME);
		registerSQLTypeAlias("timestamp", Types.TIMESTAMP);
		registerSQLTypeAlias("url", Types.VARCHAR);
		registerSQLTypeAlias("byte[]", Types.VARBINARY);
		registerSQLTypeAlias("bytes", Types.VARBINARY);
		registerSQLTypeAlias("blob", Types.BLOB);
		registerSQLTypeAlias("clob", Types.CLOB);
		registerSQLTypeAlias("list", Types.ARRAY);
		registerSQLTypeAlias("other", Types.OTHER);
		registerSQLTypeAlias("ref", Types.REF);
		//---------------------------------------------------
		registerJavaTypeAlias("boolean", Boolean.class);
		registerJavaTypeAlias("char", char.class);
		registerJavaTypeAlias("character", Character.class);
		registerJavaTypeAlias("byte", Byte.class);
		registerJavaTypeAlias("short", Short.class);
		registerJavaTypeAlias("int", Integer.class);
		registerJavaTypeAlias("integer", Integer.class);
		registerJavaTypeAlias("long", Long.class);
		registerJavaTypeAlias("float", Float.class);
		registerJavaTypeAlias("double", Double.class);
		registerJavaTypeAlias("bigint", BigInteger.class);
		registerJavaTypeAlias("big_integer", BigInteger.class);
		registerJavaTypeAlias("decimal", Currency.class);
		registerJavaTypeAlias("big_decimal", BigDecimal.class);
		registerJavaTypeAlias("string", String.class);
		registerJavaTypeAlias("text", String.class);
		registerJavaTypeAlias("datetime", java.util.Date.class);
		registerJavaTypeAlias("date", java.sql.Date.class);
		registerJavaTypeAlias("time", java.sql.Time.class);
		registerJavaTypeAlias("timestamp", java.sql.Timestamp.class);
		registerJavaTypeAlias("bytes", byte[].class);
		registerJavaTypeAlias("byte[]", byte[].class);
		registerJavaTypeAlias("data", byte[].class);
		registerJavaTypeAlias("char", Character.class);
		registerJavaTypeAlias("url", URL.class);
		registerJavaTypeAlias("color", Color.class);	
		registerJavaTypeAlias("list", Collection.class);
		registerJavaTypeAlias("blob", Blob.class);
		registerJavaTypeAlias("clob", Clob.class);
		//----------------------------------------------
		ClassTypeAliasMap.put(char.class, "char");
		ClassTypeAliasMap.put(Character.class, "char");
		ClassTypeAliasMap.put(byte.class, "byte");
		ClassTypeAliasMap.put(Byte.class, "byte");
		ClassTypeAliasMap.put(boolean.class, "boolean");
		ClassTypeAliasMap.put(Boolean.class, "boolean");
		ClassTypeAliasMap.put(short.class, "short");
		ClassTypeAliasMap.put(Short.class, "short");
		ClassTypeAliasMap.put(int.class, "int");
		ClassTypeAliasMap.put(Integer.class, "int");
		ClassTypeAliasMap.put(long.class, "long");
		ClassTypeAliasMap.put(Long.class, "long");
		ClassTypeAliasMap.put(float.class, "float");
		ClassTypeAliasMap.put(Float.class, "float");
		ClassTypeAliasMap.put(double.class, "double");
		ClassTypeAliasMap.put(Double.class, "double");
		ClassTypeAliasMap.put(BigInteger.class, "bigint");
		ClassTypeAliasMap.put(Currency.class, "decimal");
		ClassTypeAliasMap.put(String.class, "string");
		ClassTypeAliasMap.put(java.util.Date.class, "datetime");
		ClassTypeAliasMap.put(java.sql.Date.class, "date");
		ClassTypeAliasMap.put(java.sql.Time.class, "time");
		ClassTypeAliasMap.put(java.sql.Timestamp.class, "timestamp");
		ClassTypeAliasMap.put(byte[].class, "bytes");
		ClassTypeAliasMap.put(URL.class, "url");
		ClassTypeAliasMap.put(Color.class, "color");	
		ClassTypeAliasMap.put(Collection.class, "list");
		ClassTypeAliasMap.put(Blob.class, "blob");
		ClassTypeAliasMap.put(Clob.class, "clob");
	}
	/**
	 * 注册别名
	 * @param alias
	 * @param type
	 */
    public static void registerSQLTypeAlias(String alias, int type) {
        AliasSQLTypeMap.put(alias, type);
    }

	public static void registerJavaTypeAlias(String name, Class<?> type) {
		AliasClassTypeMap.put(name, type);
		if (ClassTypeAliasMap.get(type) == null) {
			ClassTypeAliasMap.put(type, name);
		}
	}
	
    public static int getSQLTypeByAlias(String alias) {
        Integer type = AliasSQLTypeMap.get(alias);
		if (type == null) {
			System.err.println("Alias ["+alias+"] SQL type not registered.");
			type = UNKNOWN_TYPE;
		}
		return type;
    }
    
	public static int getSQLTypeByClass(Class<?> clazz) {
		String alias = ClassTypeAliasMap.get(clazz);
		if (alias == null) {
			System.err.println("Class ["+clazz+"] alias not registered.");
			return UNKNOWN_TYPE;
		}
		return getSQLTypeByAlias(alias);
	}
	
	public static String getAliasByClassType(Class<?> clazz) {
		String alias = ClassTypeAliasMap.get(clazz);
		if (alias == null) {
			System.err.println("Class ["+clazz+"] alias not registered.");
		}
		return alias;
	}
	
	public static Class<?> getClassTypeByAlias(String alias) {
		Class<?> clazz = AliasClassTypeMap.get(alias);
		if (clazz == null) {
			System.err.println("Alias ["+alias+"] Class type not registered.");
		}
		return clazz;
	}
}
