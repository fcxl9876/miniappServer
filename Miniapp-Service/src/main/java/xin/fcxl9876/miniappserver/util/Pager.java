package xin.fcxl9876.miniappserver.util;

import java.util.List;

@SuppressWarnings("unchecked")
public class Pager {
	
	public static final Integer MAX_PAGE_SIZE = 500;// 每页最大记录数限制

	private Long page = 1L;// 当前页码
	
	private Integer pageSize = 50;// 每页记录数
	
	private Long records = 0L;// 总记录数
	
	private Long total = 0L;// 总页数
	
	private List rows;
	
	public Long getFirstRow() {
		return  (page - 1) * pageSize;
	}
	
	
	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		if (page < 1) {
			page = 1L;
		}
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		} else if(pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}
	
	public Long getRecords() {
		return records;
	}

	public void setRecords(Long records) {
		this.records = records;
	}

	public Long getTotal() {
		total = records / pageSize;
		if (records % pageSize > 0) {
			total ++;
		}
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}


	public List getRows() {
		return rows;
	}


	public void setRows(List rows) {
		this.rows = rows;
	}






}