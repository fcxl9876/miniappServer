//package xin.fcxl9876.miniappserver.util;
//
//import java.io.StringReader;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import net.sf.jsqlparser.expression.Expression;
//import net.sf.jsqlparser.parser.CCJSqlParser;
//import net.sf.jsqlparser.statement.select.PlainSelect;
//import net.sf.jsqlparser.statement.select.SelectExpressionItem;
//import net.sf.jsqlparser.statement.select.SelectItem;
//import net.sf.jsqlparser.statement.select.SelectItemVisitor;
//
//public class SQLParse {
//
//	private static Map<String, String> mSQL = new ConcurrentHashMap<String, String>();
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public static String replaceCountColumn(final String sql,final String idAlias) {
//		final String key = sql + ";count";
//		String nsql = mSQL.get(key);
//		if (nsql != null) {
//			return nsql;
//		}
//		try {
//			final PlainSelect select = new CCJSqlParser(new StringReader(sql)).PlainSelect();
//			if (select.getGroupByColumnReferences() != null || select.getDistinct() != null
//					|| isColumnAlias(select)) {
//				nsql = wrapCountColumn(sql,idAlias);
//			} else {
//				final List items = select.getSelectItems();
//				items.clear();
//				items.add(new SelectItem() {
//
//					public void accept(final SelectItemVisitor selectItemVisitor) {
//					}
//
//					@Override
//					public String toString() {
//						return "count("+idAlias+")";
//					}
//				});
//				final List<?> orders = select.getOrderByElements();
//				if (orders != null) {
//					orders.clear();
//				}
//				nsql = select.toString();
//			}
//		} catch (final Throwable e) {
//
//			nsql = wrapCountColumn(sql,idAlias);
//		}
//		mSQL.put(key, nsql);
//		return nsql;
//	}
//
//	private static String wrapCountColumn(final String sql,String idAlias) {
//		final StringBuilder sb = new StringBuilder();
//		sb.append("select count(*) from (").append(sql).append(") t_count");
//		return sb.toString();
//	}
//
//
//	private static boolean isColumnAlias(final PlainSelect select) {
//		final Expression where = select.getWhere();
//		for (final Object column : select.getSelectItems()) {
//			if (column instanceof SelectExpressionItem) {
//				final String alias = ((SelectExpressionItem) column).getAlias();
//				if (StringUtils.isNotBlank(alias) && where != null
//						&& where.toString().toLowerCase().contains(alias.toLowerCase())) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//}
