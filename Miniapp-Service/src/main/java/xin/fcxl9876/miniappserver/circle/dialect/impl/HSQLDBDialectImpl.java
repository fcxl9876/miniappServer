package xin.fcxl9876.miniappserver.circle.dialect.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import xin.fcxl9876.miniappserver.circle.dialect.IDialectService;
import xin.fcxl9876.miniappserver.circle.sql.SQLNestedException;


@Service("dialect_hsql")
public class HSQLDBDialectImpl implements IDialectService {

    public String getLimitSQL(String sql, boolean hasFirstResult, boolean hasMaxResults, Map<String,Boolean> parametersFirst) {
        StringBuilder sb = new StringBuilder(sql.length() + 10);
        sb.append(sql);
        int pos = sql.toLowerCase().indexOf("select");
        if (pos==(-1))
            throw new SQLNestedException("Bad SQL syntax, missing 'select': " + sql);
        pos += 6;
        if (hasFirstResult && hasMaxResults)
            sb.insert(pos, " limit ? ? ");
        else if (!hasFirstResult && hasMaxResults)
            sb.insert(pos, " limit 0 ? ");
        else if (hasFirstResult && !hasMaxResults)
            sb.insert(pos, " limit ? 2147483647 " );
        return sb.toString();
    }

    public boolean bindLimitParametersFirst() {
        return true;
    }

    public boolean bindLimitParametersInReverseOrder() {
        return false;
    }

}
