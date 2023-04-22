package xin.fcxl9876.miniappserver.circle.dialect.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import xin.fcxl9876.miniappserver.circle.dialect.IDialectService;
import xin.fcxl9876.miniappserver.util.StringUtils;

@Service("dialect_sqlserver")
public class SqlServerDialectServiceImpl implements IDialectService {
	
	private static final String SELECT = "select";
	private static final String SELECT_WITH_SPACE = SELECT + ' ';
	private static final String FROM = "from";
	private static final String DISTINCT = "distinct";
	private static final String ORDER_BY = "order by";
	
	private static final Pattern ALIAS_PATTERN = Pattern.compile( "(?i)\\sas\\s(.)+$" );


    public String getLimitSQL(String sql, boolean hasFirstResult, boolean hasMaxResults, Map<String,Boolean> parametersFirst) {
           return getProcessedSql(sql, hasFirstResult, hasMaxResults, parametersFirst);
    }

    public boolean bindLimitParametersFirst() {
        return false;
    }

    public boolean bindLimitParametersInReverseOrder() {
        return false;
    }
    

	public String getProcessedSql(String sql, boolean hasFirstResult, boolean hasMaxResults, Map<String,Boolean> parametersFirst) {
		final StringBuilder sb = new StringBuilder( sql );
		if ( sb.charAt( sb.length() - 1 ) == ';' ) {
			sb.setLength( sb.length() - 1 );
		}

		if (hasFirstResult) {
			//final String selectClause = fillAliasInSelectClause( sb );
//			final int startPos = shallowIndexOf( sb, SELECT_WITH_SPACE, 0 );
//			int endPos = shallowIndexOfWord( sb, FROM, startPos );
//		    String selectClause = 	sb.substring(6, 60);

			final int orderByIndex = shallowIndexOfWord( sb, ORDER_BY, 0 );
			if ( orderByIndex > 0 ) {
				// ORDER BY requires using TOP.
				addTopExpression( sb );
				parametersFirst.put("topAdded", true);
			}

			encloseWithOuterQuery( sb );

			// Wrap the query within a with statement:
			sb.insert( 0, "WITH query AS (" ).append( ") SELECT " ).append( "query.*" ).append( " FROM query " );
			sb.append( "WHERE __freedom_row_nr__ >= ? AND __freedom_row_nr__ <= ?" );
			parametersFirst.put("hasOffset", true);
		}
		else {
		//	hasOffset = false;
			addTopExpression( sb );
			parametersFirst.put("topAdded", true);
			parametersFirst.put("hasOffset", false);
		}

		return sb.toString();
	}
	
	protected void encloseWithOuterQuery(StringBuilder sql) {
		sql.insert( 0, "SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as __freedom_row_nr__ FROM ( " );
		sql.append( " ) inner_query " );
	}
	
	private static int shallowIndexOfWord(final StringBuilder sb, final String search, int fromIndex) {
		final int index = shallowIndexOf( sb, ' ' + search + ' ', fromIndex );
		// In case of match adding one because of space placed in front of search term.
		return index != -1 ? ( index + 1 ) : -1;
	}
	
	private static int shallowIndexOf(StringBuilder sb, String search, int fromIndex) {
		// case-insensitive match
		final String lowercase = sb.toString().toLowerCase();
		final int len = lowercase.length();
		final int searchlen = search.length();
		int pos = -1;
		int depth = 0;
		int cur = fromIndex;
		do {
			pos = lowercase.indexOf( search, cur );
			if ( pos != -1 ) {
				for ( int iter = cur; iter < pos; iter++ ) {
					final char c = sb.charAt( iter );
					if ( c == '(' ) {
						depth = depth + 1;
					}
					else if ( c == ')' ) {
						depth = depth - 1;
					}
				}
				cur = pos + searchlen;
			}
		} while ( cur < len && depth != 0 && pos != -1 );
		return depth == 0 ? pos : -1;
	}
	
	protected String fillAliasInSelectClause(StringBuilder sb) {
		final List<String> aliases = new LinkedList<String>();
		final int startPos = shallowIndexOf( sb, SELECT_WITH_SPACE, 0 );
		int endPos = shallowIndexOfWord( sb, FROM, startPos );
		
	String str = 	sb.substring(6, 60);
		int nextComa = startPos;
		int prevComa = startPos;
		int unique = 0;
		boolean selectsMultipleColumns = false;

		while ( nextComa != -1 ) {
			prevComa = nextComa;
			nextComa = shallowIndexOf( sb, ",", nextComa );
			if ( nextComa > endPos ) {
				break;
			}
			if ( nextComa != -1 ) {
				final String expression = sb.substring( prevComa, nextComa );
				if ( selectsMultipleColumns( expression ) ) {
					selectsMultipleColumns = true;
				}
				else {
					String alias = getAlias( expression );
					if ( alias == null ) {
						// Inserting alias. It is unlikely that we would have to add alias, but just in case.
						//alias = StringUtils.generateAlias( "page", unique );
						//sb.insert( nextComa, " as " + alias );
						//int aliasExprLength = ( " as " + alias ).length();
						//++unique;
					//	nextComa += aliasExprLength;
						//endPos += aliasExprLength;
					}
					//aliases.add( alias );
				}
				++nextComa;
			}
		}
		// Processing last column.
		// Refreshing end position, because we might have inserted new alias.
		endPos = shallowIndexOfWord( sb, FROM, startPos );
		final String expression = sb.substring( prevComa, endPos );
		if ( selectsMultipleColumns( expression ) ) {
			selectsMultipleColumns = true;
		}
		else {
			String alias = getAlias( expression );
			if ( alias == null ) {
				// Inserting alias. It is unlikely that we would have to add alias, but just in case.
				//alias = StringUtils.generateAlias( "page", unique );
				//sb.insert( endPos - 1, " as " + alias );
			}
			//aliases.add( alias );
		}

		// In case of '*' or '{table}.*' expressions adding an alias breaks SQL syntax, returning '*'.
		return selectsMultipleColumns ? "*" : StringUtils.joinLt( ", ", aliases.iterator() );
	}
	
	protected void addTopExpression(StringBuilder sql) {
		final int distinctStartPos = shallowIndexOfWord( sql, DISTINCT, 0 );
		if ( distinctStartPos > 0 ) {
			// Place TOP after DISTINCT.
			sql.insert( distinctStartPos + DISTINCT.length(), " TOP(?)" );
		}
		else {
			final int selectStartPos = shallowIndexOf( sql, SELECT_WITH_SPACE, 0 );
			// Place TOP after SELECT.
			sql.insert( selectStartPos + SELECT.length(), " TOP(?)" );
		}
		//topAdded = true;
	}

	
	private String getAlias(String expression) {
		final Matcher matcher = ALIAS_PATTERN.matcher( expression );
		if ( matcher.find() ) {
			// Taking advantage of Java regular expressions greedy behavior while extracting the last AS keyword.
			// Note that AS keyword can appear in CAST operator, e.g. 'cast(tab1.col1 as varchar(255)) as col1'.
			return matcher.group( 0 ).replaceFirst( "(?i)(.)*\\sas\\s", "" ).trim();
		}
		return null;
	}
	
	private boolean selectsMultipleColumns(String expression) {
		final String lastExpr = expression.trim().replaceFirst( "(?i)(.)*\\s", "" );
		return "*".equals( lastExpr ) || lastExpr.endsWith( ".*" );
	}
	


}
