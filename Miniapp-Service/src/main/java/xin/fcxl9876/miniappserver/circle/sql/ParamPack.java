package xin.fcxl9876.miniappserver.circle.sql;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import xin.fcxl9876.miniappserver.util.RandomStringGenerator;


@SuppressWarnings("serial")
public class ParamPack implements Serializable {
	
	public static final ParamPack NONE = new ParamPack("NONE");
		
	private String name;
	private Map<String, Object> params;
	private boolean autoArray = false;
	
    /**
     * @param name
     *      用来标识这个pack
     */
	public ParamPack(String name) {
	    this.name = name;
	    this.params = new LinkedHashMap<String,Object>(12);
	}
	
	public ParamPack(String name, int capality) {
	    this.name = name;
	    this.params = new LinkedHashMap<String,Object>(capality);
	}
		
    public String getPackName() {
        return name;
    }
    
    public Object get(String key) {
    	return params.get(key);
    }
    
    public ParamPack set(String key, Object value) {
    	if (autoArray) {
    		throw new RuntimeException("ParamPack["+name+"] is a immutable auto-object-array. Can not set value to a immutable ParamPack instance.");
    	}
    	params.put(key, value);    	
    	return this;
    }

	public ParamPack putAll(Map<String, Object> map) {
    	if (autoArray) {
    		throw new RuntimeException("ParamPack["+name+"] is a immutable auto-object-array. Can not set value to a immutable ParamPack instance.");
    	}
        params.putAll(map);
        return this;
	}

    public ParamPack putAll(ParamPack pack) {
    	if (autoArray) {
    		throw new RuntimeException("ParamPack["+name+"] is a immutable auto-object-array. Can not set value to a immutable ParamPack instance.");
    	}
        params.putAll(pack.params);
        return this;
    }

	public ParamPack remove(String key) {
    	if (autoArray) {
    		throw new RuntimeException("ParamPack["+key+"] is a immutable auto-object-array. Can not set value to a immutable ParamPack instance.");
    	}
		params.remove(key);
		return this;
	}

	public Set<String> keys(){
		return params.keySet();
	}
    
    public int size() {
        return params.size();
    }
    
    public boolean isAutoArray() {
    	return autoArray;
    }
    
    public String getAsString(String key) {
    	Object o = get(key);
    	return o == null? null : String.valueOf(o);  
    }
    /**
     * 将pack转换成Map使用
     *
     * @return
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.putAll(params);
        return map;
    }

    /**
     * 返回pack内部的Map引用，修改这个引用的值会引起pack内部值的变化
     * 在不改变这个值的前提下使用这个方法较toMap()速度快
      * @return
     */
    public Map<String, Object> ref() {
        return params;
    }
    
    public String[] names() {
    	return params.keySet().toArray(new String[size()]);
    }
    
    public Object[] values() {
    	return params.values().toArray(new Object[size()]);
    }
    
    /**
     * 复制本一个与本Pack相同的实例
     * @return
     */
    public ParamPack duplicate() {
    	ParamPack pack = new ParamPack(this.name);
    	pack.putAll(params);
    	return pack;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(params.size()*20);
        sb.append("ParamPack[").append(name).append("]\n{ ");
        for (String name: params.keySet()) {
        	sb.append(name).append("=").append(params.get(name)).append("\n");        
        }
        sb.append("}");
        return sb.toString();
    }
    
	public static ParamPack pack() {
		ParamPack pp = new ParamPack(RandomStringGenerator.getRandomStr(6));
		return pp;
	}
	
	public static ParamPack pack(int capality) {
		ParamPack pp = new ParamPack(RandomStringGenerator.getRandomStr(6), capality);
		return pp;
	}
	
    public static ParamPack pack(String name, int capacity) {
        ParamPack pp = new ParamPack(name, capacity);
        return pp;
    }
        
    public static ParamPack of(Object... params) {
    	if (params == null || params.length == 0) return ParamPack.NONE;
    	if (params[0] instanceof ParamPack) 
    		return (ParamPack)params[0];
    	ParamPack pack = ParamPack.pack("OV_"+RandomStringGenerator.getRandomStr(4), params.length); //[__] is a magic name, DO *NOT* use this name apparently. 
		for (int i=0; i<params.length; i++) {
			pack.set(String.valueOf(i), params[i]);
		}
		pack.autoArray = true;
    	return pack;
    }
    
    public static void main(String[] args) {
    	ParamPack pp = ParamPack.pack().set("1", "1");
    	ParamPack p = ParamPack.of(pp);
    	System.out.println(p);
    }
 
}

