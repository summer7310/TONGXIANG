package org.lyd.Operate;

public class TreeCatalogRecord {
	private long staticId;
	private String name;
	private int typeId;
	private boolean isParent;
	private String iconSkin;
	public TreeCatalogRecord(){
		
	}
	public TreeCatalogRecord(long staticId, String name, int typeId,
			boolean isParent, String iconSkin) {
		this.staticId = staticId;
		this.name = name;
		this.typeId = typeId;
		this.isParent = isParent;
		this.iconSkin = iconSkin;
	}
	public long getStaticId() {
		return staticId;
	}
	public void setStaticId(long staticId) {
		this.staticId = staticId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public boolean getParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public String getIconSkin() {
		return iconSkin;
	}
	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
	
}
