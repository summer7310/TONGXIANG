package org.lyd.Operate;

import java.util.HashMap;
import java.util.Map;

public class ObjectTypeMap {
	private static Map type = new HashMap();
	public ObjectTypeMap(){
		initMapElement();
	}
	private  void initMapElement(){
		this.type.put("变电站", 1010201);
		this.type.put("环网柜", 1010204);
		this.type.put("杆塔",1020102);
		this.type.put("开闭所", 1010202);
		this.type.put("分支箱", 1010205);
		this.type.put("配电室",1010201);
		this.type.put("工井", 1020101);
	}
	public Map getType(){
		return type;
	}
}
