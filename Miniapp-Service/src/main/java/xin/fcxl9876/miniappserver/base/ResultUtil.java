package xin.fcxl9876.miniappserver.base;

import com.baomidou.mybatisplus.plugins.Page;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResultUtil {

	/**
	 * 请求成功返回
	 * @param object
	 * @return
	 */
	public static Msg<Object> success(Object object) {
		Msg<Object> msg = new Msg<Object>();
		msg.setList(object);
		msg.setCode(200);
		msg.setMsg("查询成功");
		msg.setSuccess(true);
		return msg;
	}
	
	/**
	 * 请求成功返回
	 * @param object
	 * @return
	 */
	public static Msg<Object> successData(Object object) {
		Msg<Object> msg = new Msg<Object>();
		msg.setData(object);
		msg.setCode(200);
		msg.setMsg("查询成功");
		msg.setSuccess(true);
		return msg;
	}
	
	/**
	 * 请求成功返回
	 * @param <T>
	 * @param object
	 * @return
	 */
	public static <T> Msg<Object> successArray(List<T> entityList) {
		Msg<Object> msg = new Msg<Object>();
		msg.setList(formToArray(entityList));
		msg.setCode(200);
		msg.setMsg("查询成功");
		msg.setSuccess(true);
		return msg;
	}
	
	/**
	 * 请求成功返回
	 * @param <T>
	 * @param object
	 * @return
	 */
	public static <T> Msg<Object> successList(List<T> entityList) {
		Msg<Object> msg = new Msg<Object>();
		msg.setList(entityList);
		msg.setCode(200);
		msg.setMsg("查询成功");
		msg.setSuccess(true);
		return msg;
	}
	
	
	/**
	 * @deprecated 专门针对微信小程序，避免前端遍历，后台做数据格式转换
	 * @param entityList
	 * @return Object
	 * @throws Exception 
	 * @date 2020-05-22
	 * **/
	private static <T> Object formToArray(List<T> entityList) {
		
		Map<String, ArrayList> fieldMap = new HashMap<String, ArrayList>();
		int total = entityList.size();
		if(!entityList.isEmpty()) {
			try {
	            Class tempClass = entityList.get(0).getClass();
				while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
					Field[] fileds = 	tempClass.getDeclaredFields();
					//将值放入map的属性对于的数组中
					for(Object object :entityList) {
						for (Field f : fileds) {
							if("serialVersionUID".equals(f.getName())){
								continue;
							}
							if(fieldMap.get(f.getName())==null ) {
								fieldMap.put(f.getName(), new ArrayList());
							}
							//获取对象值
							PropertyDescriptor pd = new PropertyDescriptor(f.getName(),tempClass);  
							Method getMethod = pd.getReadMethod();//获得get方法  
							Object value = getMethod.invoke(object);//执行get方法返回一个Object 
							fieldMap.get(f.getName()).add(value);
						}
					}
				    tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
				    if(tempClass == BaseModel.class) {
		    			break;
				    } 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return fieldMap;
	}
	
	
	/**
	 * 返回带有分页信息
	 * @param Page 对象
	 * @return Msg<Object>
	 * **/
	public static Msg<Object> successPage(Page Page){
		Msg<Object> msg = new Msg<Object>();
		msg.setList(Page.getRecords());
		msg.setCode(200);
		msg.setMsg("查询成功");
		msg.setSuccess(true);
		//分页信息
		PageInfo pageInfo = new PageInfo();
		int curPage = Page.getCurrent();
		int pageRecord = Page.getSize();
		int total = Integer.parseInt(Page.getTotal()+"");
		pageInfo.setPageRecord(pageRecord);
		pageInfo.setCurPge(curPage);
		pageInfo.setTotalRecord(total);
		pageInfo.setNextPageNumber(curPage+1);
		pageInfo.setPreviousPageNumber(curPage-1);
		pageInfo.setStartRecordIndex(1);
		pageInfo.setPaginate(true);
		pageInfo.setEndPageNumber(total%pageRecord>0?(total/pageRecord+1):(total/pageRecord));
		pageInfo.setMaxPageNumber(100);
		pageInfo.setOrderBy(Page.getOrderByField());
		msg.setPageInfo(pageInfo);
		return msg;
	}
	
	
	
	
	
	
	
	/**
	 * 新增、更新返回
	 * @return
	 */
	public static Msg<Object> success(Boolean flag) {
		Msg<Object> msg = new Msg<Object>();
		msg.setCode(200);
		msg.setMsg("操作成功");
		msg.setSuccess(flag);
		return msg;
	}
	
	public static Msg<Object> error(Boolean flag) {
		Msg<Object> msg = new Msg<Object>();
		msg.setCode(500);
		msg.setMsg("操作失败");
		msg.setSuccess(flag);
		return msg;
	}
	public static Msg<Object> error(String message) {
		Msg<Object> msg = new Msg<Object>();
		msg.setCode(500);
		msg.setMsg(message);
		msg.setSuccess(false);
		return msg;
	}
	
	//设值给返回结果
	public static Msg<Object> setResult(boolean result) {
		Msg<Object> msg = new Msg<Object>();
		if(result) {//新增成功
			msg.setCode(200);
			msg.setMsg("操作成功");
			msg.setSuccess(true);
		}else {//失败
			msg.setCode(500);
			msg.setMsg("操作失败");
			msg.setSuccess(false);
		}
		return msg;
	}
	
	//设值给返回结果
	public static Msg<Object> setResult(boolean result,String entityId) {
		Msg<Object> msg = new Msg<Object>();
		if(result) {//新增成功
			msg.setCode(200);
			msg.setMsg("操作成功");
			msg.setSuccess(true);
			msg.setData(entityId);
		}else {//失败
			msg.setCode(500);
			msg.setMsg("操作失败");
			msg.setSuccess(false);
		}
		return msg;
	}
		

	public static Msg<Object> error(Integer code, String resultmsg) {
		Msg<Object> msg = new Msg<Object>();
		msg.setCode(code);
		msg.setMsg(resultmsg);
		return msg;

	}
	
	/*
	 * 返回分页信息
	 */
	public static Msg<Object> success(Object object,Object object1){
		Msg<Object> msg = new Msg<Object>();
		msg.setCode(200);
		msg.setSuccess(true);
		msg.setList(object);
		msg.setPageInfo(object1);
		return msg;
	}
	
	

}
