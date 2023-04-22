package xin.fcxl9876.miniappserver.base;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

public abstract class BaseModel<T extends BaseModel<?>> extends Model<T> {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@TableField(exist=false)
	public int rows = 5;

	@JsonIgnore
	@TableField(exist=false)
   	public int page = 0;

	@JsonIgnore
   	@TableField(exist=false)
   	public String sidx;

	@JsonIgnore
	@TableField(exist=false)
   	public String sord;

	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}

	//获取升序
	@JsonIgnore
	public boolean getIsAsc(){
		boolean flag = false;
		if(StringUtils.isNotBlank(sord)) {
			if("asc".equals(sord)) {
				flag=true;
			}
		}
		return flag;
	}


}
