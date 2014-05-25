package org.lyd.postgreSQL;

public class ObjectBase {
	public int id;
	public String name;
	public int type;
	public boolean isParent;
	public ObjectBase(){
		
	}
	public ObjectBase(ObjectBase temp){
		this.id=temp.id;
		this.name=temp.name;
		this.type=temp.type;
		this.isParent=temp.isParent;
	}
}
