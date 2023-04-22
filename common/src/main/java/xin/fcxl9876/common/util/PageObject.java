package xin.fcxl9876.common.util;

import java.io.Serializable;

/**
 * 分页参数封装传递对象
 * @author qiye
 * @date 2018年6月22日 上午10:54:15
 */
public class PageObject implements Serializable {
	private static final long serialVersionUID = -8753809986545361268L;
	/** 排序字段分隔符 */
	public static final String SORD_COLUMNS_SEPARATOR = ",";
	/** 当前页 */
	private Integer pageNumber;
	/** 每页最多能显示的记录数 */
	private Integer pageSize;
	/** 总记录数 */
	private Integer rowCount;
	/** 排序列 */
	String sordColumn;
	/** 排序顺序 */
	String order;
	/**
	 * 上一页的最后一条记录位置 对应:limit startIndex,pageSize;
	 */
	private int startIndex;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer getPageCount() {
		int pages = rowCount / pageSize;
		if (0 != rowCount % pageSize) {
			pages += 1;
		}
		return pages;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public String getSordColumn() {
		return sordColumn;
	}

	public void setSordColumn(String sordColumn) {
		this.sordColumn = sordColumn;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
}
