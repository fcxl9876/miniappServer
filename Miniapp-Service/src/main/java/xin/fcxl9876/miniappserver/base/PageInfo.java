package xin.fcxl9876.miniappserver.base;
/**
 *分页信息对象
 *@author zhhui
 * **/
public class PageInfo {

	public int curPge;// 当前页
	public int maxPageNumber;//页面显示数量最大值
	public int totalRecord;//数据总数	
	public int startRecordIndex;//首页	
	public int previousPageNumber;//上一页
	public int nextPageNumber;//下一页
	public int endPageNumber;//末页
	public int pageRecord;//每页数量	
	public boolean paginate;//是否编页码
	public String orderBy;//排序列
	
	public int getCurPge() {
		return curPge;
	}
	public void setCurPge(int curPge) {
		this.curPge = curPge;
	}
	public int getMaxPageNumber() {
		return maxPageNumber;
	}
	public void setMaxPageNumber(int maxPageNumber) {
		this.maxPageNumber = maxPageNumber;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getStartRecordIndex() {
		return startRecordIndex;
	}
	public void setStartRecordIndex(int startRecordIndex) {
		this.startRecordIndex = startRecordIndex;
	}
	public int getPreviousPageNumber() {
		return previousPageNumber;
	}
	public void setPreviousPageNumber(int previousPageNumber) {
		this.previousPageNumber = previousPageNumber;
	}
	public int getNextPageNumber() {
		return nextPageNumber;
	}
	public void setNextPageNumber(int nextPageNumber) {
		this.nextPageNumber = nextPageNumber;
	}
	public int getEndPageNumber() {
		return endPageNumber;
	}
	public void setEndPageNumber(int endPageNumber) {
		this.endPageNumber = endPageNumber;
	}
	public int getPageRecord() {
		return pageRecord;
	}
	public void setPageRecord(int pageRecord) {
		this.pageRecord = pageRecord;
	}
	public boolean isPaginate() {
		return paginate;
	}
	public void setPaginate(boolean paginate) {
		this.paginate = paginate;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
		
}
