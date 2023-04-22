package xin.fcxl9876.miniappserver.base;

public class Msg<T> {
	 /*错误码*/
    private Integer code;
 
    /*提示信息 */
    private String msg;
 
    /*具体内容*/
    private  T data;
    /*具体内容*/
    private  T list;
    
    
    /* 分页的信息*/
    private T pageInfo;
    
    /*执行是否成功*/
    private Boolean success;
    
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	/**
	 * @return the list
	 */
	public T getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(T list) {
		this.list = list;
	}

	public T getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(T pageInfo) {
		this.pageInfo = pageInfo;
	}
    
}
