package xin.fcxl9876.miniappserver.circle.sql;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xin.fcxl9876.miniappserver.circle.dialect.IDialectService;
import xin.fcxl9876.miniappserver.util.StringUtils;





public final class SQLUtils {
	
	private static Log log = LogFactory.getLog(SQLUtils.class); 
	
    private static final Pattern VariableFormatPattern = Pattern.compile("(\\$\\{\\s*[a-zA-Z0-9\\_]+(\\.[a-zA-Z\\_]{1}[a-zA-Z0-9\\_]*)?\\s*\\})");
    private static final Pattern ParamsFormatPattern = Pattern.compile("(\\:[a-zA-Z0-9\\_]+(\\.[a-zA-Z\\_]{1}[a-zA-Z0-9\\_]*)?)");
    //"\\s*ORDER\\s+BY\\s+([A-Z]+[.]*[A-Z]+\\s*[,]*\\s*)*\\s*[)]";
    private static final Pattern OrderByPattern = Pattern.compile("order\\s+by", Pattern.CASE_INSENSITIVE);
    

	/**
	 * 
	 * @param paramSql
	 * @return
	 */
	public static String parsePreparedSQL(String paramSql) {
		//预先编译提升性能
		return ParamsFormatPattern.matcher(paramSql).replaceAll("?");
	}
	/**
	 * 从带参数的sql中获取到参数名字
	 * @param sql
	 * @return
	 */
	public static String[] parseParamsPattern(CharSequence sql, StringBuilder buffer) {
        List<String> list = new LinkedList<String>();
        Matcher matcher = ParamsFormatPattern.matcher(sql);
        int from = 0;
        while (matcher.find()) {
            int idx = matcher.start ();
            buffer.append(sql.subSequence(from, idx)).append('?');
            String v = matcher.group();
            list.add(v.substring(1));
            from = idx + v.length();
        }
        if (from > 0)
            buffer.append(sql.subSequence(from, sql.length()));
        else
            buffer.append(sql);
        return list.toArray(new String[list.size()]);
	}

    /**
     * 解析给定sql中的变量
     * @param sql
     * @param vars
     * @return
     */
    public static CharSequence parseVariablePattern(CharSequence sql, Map<String, Object> vars) {
        StringBuilder sb = new StringBuilder(sql.length());
        Matcher matcher = VariableFormatPattern.matcher(sql);
        int from = 0;
        //StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            int idx = matcher.start ();            
            String v = matcher.group();
            int v_len = v.length();            
            sb.append(sql.subSequence(from, idx));
            v = v.substring(2, v_len-1).trim();
            
            Object o = vars.get(v);
            CharSequence s;
            if (o == null) s = "#"; //暂时用#表示空变量
            else {
                String ss = o.toString();
                if (ss.indexOf("${") >= 0)
                //如果是条件就解开其中可能存在的变量                
//                if (o instanceof Criteria) {
                	s = parseVariablePattern(ss, vars);
//                }
                else 
                	s = ss;
            }
            sb.append(s);
            from = idx + v_len;
            
        }

        if (from > 0)
            sb.append(sql.subSequence(from, sql.length()));
        else
            sb.append(sql);

        return sb;
    }
    /**
     * 
     * @param sql
     * @return
     */
    public static CharSequence filterOrderBy(CharSequence sql) {
		char FQuotationMark = '\'';
		char FLeftBracketMark = '(';
		char FRightBracketMark = ')';

		
		Matcher FMatcher = OrderByPattern.matcher ( sql );
		StringBuilder buf = new StringBuilder(sql.length());
		int from=0;
		while ( FMatcher.find () ) {
			int FBracketMarkNumber = 0;
			boolean FIsQuotationMark = false;
			buf.append(sql.subSequence(from, FMatcher.start()));
			int FIndex = FMatcher.end ();
			while ( FIndex < sql.length () ) {
				// ---下一个字符为引号，则设置引号标志，推进下标，开始下一个字符处理。
				if ( sql.charAt ( FIndex ) == FQuotationMark ) {
					FIndex++;
					FIsQuotationMark = !FIsQuotationMark;
					continue;
				}
				// ---不在引号标志内的左括号，意味着可能存在子语句的开始，无需特别处理，仅将括号标志数量加一，表示语法结束的层多了一层，并推进下标。
				if ( !FIsQuotationMark && ( sql.charAt ( FIndex ) == FLeftBracketMark ) ) {
					FBracketMarkNumber++;
					FIndex++;
					continue;
				}
				// ---不在引号标志内的右括号，有两种情况，一是在当前处理范围内不成对的右括号，即当前“order
				// by”子语句遇到了上一层语句的边界，此情况下结束处理保持FIndex指向语句的结束位置。二是当前处理范围内成对的右括号，即当前“order
				// by”的子语句，此情况下减少尚未处理的左括号基数，并推进下标。
				if ( !FIsQuotationMark && ( sql.charAt ( FIndex ) == FRightBracketMark ) ) {

					if ( FBracketMarkNumber == 0 ) {
						// ---遇到语句边界则退出，此时FIndex的值指向当前语句边界外的首个字符。
						break;
					} else {
						// ---order by的子语句。
						FBracketMarkNumber--;
						FIndex++;
						continue;
					}
				}
				// ---到这一步，意味着要么是处于一个引号标志之后的括号字符，要么是无需关注的其他字符，总之简单的推进下标。
				FIndex++;
			}
			// ---此时FBeginIndex指向“order
			// by”子语句开始的位置，即“o”的下标。FIndex指向位置有两个可能，如果sql.length
			// ()，即当前语句最大下标加一；其次是上层语句的“)”位置。
			//sql.delete ( FBeginIndex, FIndex );
			from = FIndex;
			// ---重新计算匹配，如果还存在匹配，意味着还存在其他层的语句未处理。
			//FMatcher = FVariablePattern.matcher ( sql );
		}

		if (from > 0) {
			buf.append(sql.subSequence(from, sql.length()));
		} else {
			buf.append(sql);
		}
		
		return buf;
    }
	/**
	 * 
	 * @param paramTypeExpr
     *          format : name1:alias1, name2:alias2 或着 name1:11 , name2:23
	 */
	public static int[] parseJDBCTypes(String[] names, String paramTypeExpr) {
		
		assert names != null && paramTypeExpr != null : "Cannot be null";
		
		if (paramTypeExpr.charAt(0) == '[' 
			&& paramTypeExpr.charAt(paramTypeExpr.length()-1) == ']') {
			paramTypeExpr = paramTypeExpr.substring(1, paramTypeExpr.length()-1);
		}				
		
		String[] types = paramTypeExpr.split("\\s*[,;]\\s*");
		
		if (types.length < names.length) {
			throw new SQLNestedException("Illigal type count. types count must bigger than  ["+names.length+"]");
		}
		
		Map<String, Integer> tempTypes = new HashMap<String, Integer>();
		
		for (String t : types) {
			String[] nt = t.split("\\s*:\\s*");
			if (nt.length != 2) {
				throw new SQLNestedException("Illigal type expresstion ["+paramTypeExpr+"].check it please.");
			}
			if (StringUtils.isNumeric(nt[1])) {
				tempTypes.put(nt[0], new Integer(nt[1]));
			} else {
				int iT = SQLTypeMapping.getSQLTypeByAlias(nt[1]);
				if (iT == SQLTypeMapping.UNKNOWN_TYPE) {
					throw new SQLNestedException("Illigal type alias["+nt[1]+"] of params["+nt[0]+"]. can not build jdbc type array");
				}
				tempTypes.put(nt[0], new Integer(iT));
			}			
		}
		
		int[] result = new int[names.length];
		
		for (int i = 0; i<names.length; ++i) {
			Integer it = tempTypes.get(names[i]); 
			if (it == null) {
				throw new SQLNestedException("Param["+names[i]+"] has no type value defined. can not build type array");
			}
			result[i] = it.intValue();
		}
		// clear temp map
		tempTypes.clear();
		// return type array
		return result;
	}

	/**
	 * Convert a Map of parameter types to a corresponding int array.
	 * This is necessary in order to reuse existing methods on JdbcTemplate.
	 * Any named parameter types are placed in the correct position in the
	 * Object array based on the parsed SQL statement info.
	 * @param names
     *          解析出来的名字参数
	 * @param types
     *          参数类型别名的Map
	 */
	public static int[] parseJDBCTypes(String[] names, Map<String, String> types) {
		
		if(names==null){
			return new int[5];
		}
		
		assert names != null && types.size() >= names.length : "Types's count["+types.size()+"] must greater than named param count["+names.length+"]"; 
		
		int[] result = new int[names.length];
		
		for (int i = 0; i < names.length; i++) {
			String t = types.get(names[i]);
			if (t == null) {
				//sqlTypes[i] = 
				throw new SQLNestedException("Illigal type value on ["+names[i]+"]");
			}			
			if (StringUtils.isNumeric(t))
				result[i] = Integer.parseInt(t);
			else {
				int iT = SQLTypeMapping.getSQLTypeByAlias(t);
				if (iT == SQLTypeMapping.UNKNOWN_TYPE) {
					throw new SQLNestedException("Illigal type alias["+t+"] of param["+names[i]+"]. can not build jdbc type array");
				}
				result[i] = iT;					
			}
		}
		// Do not clear any thing here
		return result;
	}	
	
	public static Object[] buildNamedParamValues(String[] names, ParamPack pack) {		

		if ((names == null || names.length == 0) 
				&& (pack == null || pack.size() == 0)) return new Object[0];
		
		if (names.length > pack.size()) {
			throw new SQLNestedException("Parameter names length can not bigger than param values");
		}
		//如果是自动类型的Object数组组装就 直接返回
		if ((names.length == 0) || pack.isAutoArray()) {
			return pack.values();
		}
		//按照名字给定的顺序组装
		Object[] result = new Object[names.length];
		for (int i = 0; i < names.length; i++) {
			result[i] = pack.get(names[i]);
		}
		return result;
	}
	
	public static Object[] buildDialectParamBindValues(Object[] values, Limits limits, IDialectService dialect,Map<String,Boolean> parametersFirst) {
		
        Integer[] limit_values = buildDialectLimitValues(limits, dialect);        
        
        if (limit_values == null) {
        	return values;
        }
        // for copy logic
        if (values == null) {
        	values = new Object[0];
        }
        Boolean topAdded = parametersFirst.get("topAdded");
        Boolean hasOffset = parametersFirst.get("hasOffset");
        Object[] result = null;
        
		if (  (topAdded!=null&&topAdded)&&(hasOffset==null||!hasOffset)) {
				result = new Object[values.length+limit_values.length];
				System.arraycopy(limit_values, 0, result, 0, limit_values.length);
				System.arraycopy(values, 0, result, limit_values.length, values.length);
		} else {
			if ((hasOffset!=null&&hasOffset)&&(topAdded!=null&&topAdded)) {
				result = new Object[values.length+limit_values.length+1];
				result[0] = limits.getOffset()+limits.getLimit() ;
				System.arraycopy(values, 0, result, 1, values.length);
				System.arraycopy(limit_values, 0, result, values.length+1, limit_values.length);		
			}else {
				result = new Object[values.length+limit_values.length];
				System.arraycopy(values, 0, result, 0, values.length);
				System.arraycopy(limit_values, 0, result, values.length, limit_values.length);		
			}
				
		}
		return result;		
	}
	/**
	 * 创建limits查询语句
	 * @param sql
	 * @param limits
	 * @param dialect
	 * @return
	 */
	public static String buildDialectLimitQuery(String sql, Limits limits, IDialectService dialect, Map<String,Boolean> parametersFirst) {
        if (limits.getOffset() <=0 && limits.getLimit() <= 0) return sql;
        //limits.getOffset()>=0加上等于号会出现下一页记录可能与上一页的记录有重复，但性能相差很大
        return dialect.getLimitSQL(sql, limits.getOffset()>0, limits.getLimit()>0,parametersFirst);
	}		
	/**
	 * 创建经过顺序转换的limit值数组
	 * @param limits
	 * @param dialect
	 * @return
	 */
	public static Integer[] buildDialectLimitValues(Limits limits, IDialectService dialect) {
		Integer[] limit_values = null;
        if (limits.getOffset()>0 && limits.getLimit()>0) {
            boolean switchLimitParams = dialect.bindLimitParametersInReverseOrder();
            limit_values = new Integer[2];
            limit_values[0] = switchLimitParams ? (limits.getLimit()+limits.getOffset()) : limits.getOffset();
            limit_values[1] = switchLimitParams ? limits.getOffset() : (limits.getOffset() + limits.getLimit());
        }
        else if (limits.getOffset()>0) {
            limit_values = new Integer[1];
            limit_values[0] = limits.getOffset();
        }
        else if (limits.getLimit()>0) {
            limit_values = new Integer[1];
            limit_values[0] = Integer.valueOf(limits.getLimit());
        }
        return limit_values;
	}

    public static SQLType evalSQLType(String sqlSeg) {
        sqlSeg = sqlSeg.toUpperCase();
        if (sqlSeg.equals("SELE") || sqlSeg.equals("WITH"))
            return SQLType.SELECT;
        if (sqlSeg.equals("UPDA"))
            return SQLType.UPDATE;
        if (sqlSeg.equals("INSE"))
            return SQLType.UPDATE;
        if (sqlSeg.equals("DELE"))
            return SQLType.DELETE;
        if (sqlSeg.equals("CREA"))
            return SQLType.CREATE;
        if (sqlSeg.startsWith("DROP"))
            return SQLType.DROP;
        if (sqlSeg.startsWith("TRUN"))
            return SQLType.TRUNCATE;
        if (sqlSeg.startsWith("ALTE"))
            return SQLType.ALTER;
        return SQLType.OTHER;

    }
	
	
	
	public static void main(String[] args ) {

		String sql="";
		String FSQL_1 = "";
		String FSQL_2 = "";
		String sql2 = "";

		String reg1 = "";
		String reg2 = "";
		String reg3 = "";
		String reg4 = "";

		//sw.start("a");
		for (int i=0; i<10000; i++) {
		 sql = "SELECT T.* FROM ( SELECT * FROM ( SELECT M.* FROM EIS_ORGAN M ORDER BY M.CODE,M.NAME ) order by org.showsort,dept.showsort,emp.topDeptText,executivegrade.show_index,emp.employeeText  nulls last) T";
		 FSQL_1 = "select * from employee where name=::name and (gender=::female or gender=::male) and department in ($$department) order by name, to_number(gender) asc";
		 FSQL_2 = "select * from emp_employee order by (select id from emp_employee  where id='00106A1EE9F36B3D0F63A609D23D95D2' )";
		 sql2 = "SELECT T.* FROM ( SELECT * FROM ( SELECT M.* FROM EIS_ORGAN M ORDER BY M.CODE,M.NAME ) ORDER BY NAME) T";

		 reg1 = SQLUtils.filterOrderBy(sql).toString();
		 reg2 = SQLUtils.filterOrderBy(FSQL_1).toString();
		 reg3 = SQLUtils.filterOrderBy(sql2).toString();	
		 reg4 = SQLUtils.filterOrderBy(FSQL_2).toString();
		}
		System.out.println(sql);
		System.out.println(reg2);
		System.out.println(reg1);
		System.out.println(reg3);
		System.out.println(reg4);
	}
}


