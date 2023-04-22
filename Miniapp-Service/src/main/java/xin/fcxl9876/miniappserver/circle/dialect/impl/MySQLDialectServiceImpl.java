package xin.fcxl9876.miniappserver.circle.dialect.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import xin.fcxl9876.miniappserver.circle.dialect.IDialectService;


@Service("dialect_mysql")
public class MySQLDialectServiceImpl implements IDialectService {

    public String getLimitSQL(String sql, boolean hasFirstResult, boolean hasMaxResults, Map<String,Boolean> parametersFirst) {
        StringBuilder sb = new StringBuilder(sql.length() + 20);
        boolean forUpdate = sql.toLowerCase().endsWith(" for update");
        if (forUpdate)
            sql = sql.substring(0, sql.length() - 11);
        sb.append(sql);
        if (hasFirstResult && hasMaxResults)
            sb.append(" limit ?, ?");
        else if (!hasFirstResult && hasMaxResults)
            sb.append(" limit 0, ?");
        else if (hasFirstResult && !hasMaxResults)
            sb.append(" limit ?, 2147483647");
        if (forUpdate)
            sb.append(" for update");
        return sb.toString();
    }

    public boolean bindLimitParametersFirst() {
        return false;
    }

    public boolean bindLimitParametersInReverseOrder() {
        return false;
    }

}
