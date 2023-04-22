package xin.fcxl9876.miniappserver.circle.sql;

import java.io.Serializable;

@SuppressWarnings("serial")
public final class Limits implements Serializable {
	/**
	 * 无限制的Limits对象 
	 */
	public static final Limits NONE = new Limits(0, 0);
	//获取起始位置
	private int offset;
	//获取限制数量
	private int limit;	
	/** 
	 * @param offset 开始位置
	 * @param limit 每次获取的数量
	 */
	public Limits(long offset, int limit) {
		setOffset(offset);
		setLimit(limit);
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = (int)offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isEmpty() {
		return (limit <= 0);
	}
	/**
	 * Convenience method to getValue limits object
	 * @param offset 开始位置
	 * @param limit 每次获取的数量
	 * @return
	 */
	public static final Limits of(long offset, int limit) {
		return new Limits(offset, limit);
	}
}
