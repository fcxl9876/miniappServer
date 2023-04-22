package xin.fcxl9876.miniappserver.circle.sql;


public class SQL {

    private String source;
    private SQLType type;
    private ParamPack var_values;
    private ParamPack param_values;
    private String[] p_names;
    private Limits limits = Limits.NONE;
    private OrderBy order;

    SQL(String paramSql) {
        this.source = paramSql;
        this.type = SQLUtils.evalSQLType(source.substring(0,4));
        this.var_values = ParamPack.pack();
        this.param_values = ParamPack.pack();
    }

    public static SQL of(String paramSql) {
        return new SQL(paramSql);
    }

    public static SQL of(String paramSql, Limits limits) {
        SQL sql = new SQL(paramSql);
        sql.setLimits(limits);
        return sql;
    }

    public SQLType getType() {
        return type;
    }
    
    public String getSource() {
        return source;
    }
    
    public String[] getParamNames() {
        return p_names;
    }

    public Limits getLimits() {
        return limits;
    }

    public SQL setLimits(Limits limits) {
        this.limits = limits;
        return this;
    }

    public OrderBy getOrderBy() {
        if (order == null) {
        	return OrderBy.L();
        }
        return order;
    }
    
    public SQL setOrderBy(OrderBy order) {
        this.order = order;
        return this;
    }
    
    public ParamPack vars(){
    	return var_values;
    }
    
    public ParamPack params(){
    	return param_values;
    }
    /**
     * 使用内部值进行变量设置
     * @return
     */
    public String toSqlStr() {
    	return toSqlStr(var_values);
    }

    /**
     * 不使用内部值而是用外部的变量值进行变量设置
     * @param vars
     * @return
     */
    public String toSqlStr(ParamPack vars) {
        CharSequence statement = SQLUtils.parseVariablePattern(source, vars.ref());
        StringBuilder buffer = new StringBuilder(statement.length()+200);
        this.p_names = SQLUtils.parseParamsPattern(statement, buffer);
        if (isSELECT() && order != null) {
            order.join(buffer);
        }
        return buffer.toString();
    }


    @Override
    public String toString() {
    	//FIXME 考虑直接引入参数值打印？？dzb
    	return toSqlStr(var_values);
    }

    boolean isSELECT() {
        return SQLType.SELECT == type;
    }

    boolean isUPDATE() {
        return SQLType.UPDATE == type;
    }

    boolean isINSERT() {
        return SQLType.INSERT == type;
    }

    boolean isDELETE() {
        return SQLType.DELETE == type;
    }

    boolean isCREATE() {
        return SQLType.CREATE == type;
    }

    boolean isDROP() {
        return SQLType.DROP == type;
    }

    boolean isTRUNCATE() {
        return SQLType.TRUNCATE == type;
    }

}
