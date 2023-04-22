package xin.fcxl9876.common.util;

import java.util.List;

/**
 * @ClassName: Page
 * @Description: 分页工具类
 * @author 谭红波
 * @date 2017年9月5日
 * 
 * @param <T>
 */
public class Page<T> {
    // 结果集
    private List<T> list;
    // 查询记录总数
    private int totalRecords;
    // 每页多少条记录
    private int pageSize;
    // 第几页
    private int pageNo;
    //总页数
    private int totalPages;

    /**
     * @return 总页数
     */
    public int getTotalPages() {
    	totalPages = (totalRecords + pageSize - 1) / pageSize;
        return totalPages;
    }
    
    public void setTotalPages(int totalPages) {
    	this.totalPages = totalPages;
    }

    /**
     * 计算当前页开始记录
     * 
     * @param pageSize
     *            每页记录数
     * @param currentPage
     *            当前第几页
     * @return 当前页开始记录号
     */
    public int countOffset(int currentPage, int pageSize) {
        int offset = pageSize * (currentPage - 1);
        return offset;
    }

    /**
     * 第一页
     * 
     * @return
     */
    public int getFirstPageNo() {
        return 1;
    }

    /**
     * 上一页
     * 
     * @return
     */
    public int getPreviousPageNo() {
        if (pageNo <= 1) {
            return 1;
        }
        return pageNo - 1;
    }

    /**
     * 下一页
     * 
     * @return
     */
    public int getNextPageNo() {
        if (pageNo >= getLastPageNo()) {
            return getLastPageNo();
        }
        return pageNo + 1;
    }

    /**
     * 最后一页
     * 
     * @return
     */
    public int getLastPageNo() {
        return getTotalPages();
    }

    /**
     * @Title: getStartOfPage @Description: 获取跳转页第一条数据在数据集的位置 @param @return
     * 设定文件 @return int 返回类型 @throws
     */
    public int getStartOfPage() {
        return ((pageNo - 1) < 0 ? 0 : pageNo) * pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

}
