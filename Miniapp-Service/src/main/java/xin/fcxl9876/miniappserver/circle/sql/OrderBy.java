package xin.fcxl9876.miniappserver.circle.sql;

import java.util.LinkedList;
import java.util.List;


public class OrderBy {
    
    private List<KV<String,String>> items;
    
    private OrderBy() {
        items = new LinkedList<KV<String,String>>();
    }

    public OrderBy iasc(String name) {
        KV<String,String> asc = KV.of(name, "ASC");
        items.add(0, asc);
        return this;
    }
    
    public OrderBy idesc(String name) {
        KV<String,String> desc = KV.of(name, "DESC");
        items.add(0,desc);
        return this;
    }
    
    public OrderBy add(String name,boolean isAsc) {
    	if (isAsc)
    		return asc(name);
    	else
    		return desc(name);    	
    }
    
    public OrderBy asc(String name) {
        KV<String,String> asc = KV.of(name, "ASC");
        items.add(asc);
        return this;
    }
    
    public OrderBy nl(String name) {
        KV<String,String> nl = KV.of(name, "NULLS LAST");
        items.add(nl);
        return this;
    }
    
    public OrderBy dnl(String name) {
        KV<String,String> nl = KV.of(name, "DESC NULLS LAST");
        items.add(nl);
        return this;
    }

    public OrderBy desc(String name) {
        KV<String,String> desc = KV.of(name, "DESC");
        items.add(desc);
        return this;
    }

    public StringBuilder join(StringBuilder buffer) {
        if (items.size() == 0) return buffer;
        buffer.append(" ORDER BY ");
        for (KV<String,String> kv : items) {
            buffer.append(kv.k()).append(' ')
            	.append("ASC".equals(kv.v()) ? "" : kv.v()).append(", ");
        }
     //   System.out.println(buffer);
        buffer = buffer.deleteCharAt(buffer.length() - 2);
      //  System.out.println(buffer);
        return buffer;
    }
    
    public boolean isEmpty(){
    	return items.isEmpty();
    }

    public boolean isTopAsc(){
    	if (items.isEmpty()) return false;
    	return "ASC".equals(items.get(0).v());
    }
    
    public String topK(){
    	if (items.isEmpty()) return "";
    	return items.get(0).k();
    }
    
    public OrderBy replaceTopK(String k){
    	if (!items.isEmpty()){
    		items.get(0).k(k);
    	}
    	return this;
    }
    
    public OrderBy reset(){
    	items.clear();
    	return this;
    }
    
    public OrderBy append(OrderBy o){
    	if (!o.isEmpty()){
    		items.addAll(o.items);
    	}
    	return this;
    }
    
    @Override
    public String toString() {
        if (items.size() == 0) return "";
        StringBuilder sb = new StringBuilder(20);
        join(sb);
        return sb.toString();
    }
    
    public static OrderBy L() {
        return new OrderBy();
    }

    public static OrderBy ascOf(String name){
    	return L().asc(name);
    }

    public static OrderBy descOf(String name){
    	return L().desc(name);
    }

}
