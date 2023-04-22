package xin.fcxl9876.miniappserver.circle.sql;


public class SQLNestedException extends RuntimeException {
	
	private static final long serialVersionUID = 8529292638737423181L;

	public SQLNestedException(String error) {
		super(error);
	}
	
	public SQLNestedException(Throwable cause) {
		super(cause);		
	}
	
	public SQLNestedException(Throwable cause, String error) {
		super(error, cause);
	}
}
