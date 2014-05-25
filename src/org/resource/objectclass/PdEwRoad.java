package org.resource.objectclass;

// Generated Sep 26, 2013 2:25:34 PM by Hibernate Tools 4.0.0

/**
 * PdEwRoad generated by hbm2java
 */
public class PdEwRoad implements java.io.Serializable {

	private int autoId;
	private Long staticId;
	private String name;
	private Integer objectType;
	private Integer rfid;

	public PdEwRoad() {
	}

	public PdEwRoad(int autoId) {
		this.autoId = autoId;
	}

	public PdEwRoad(int autoId, Long staticId, String name,
			Integer objectType, Integer rfid) {
		this.autoId = autoId;
		this.staticId = staticId;
		this.name = name;
		this.objectType = objectType;
		this.rfid = rfid;
	}

	public int getAutoId() {
		return this.autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public Long getStaticId() {
		return this.staticId;
	}

	public void setStaticId(Long staticId) {
		this.staticId = staticId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getObjectType() {
		return this.objectType;
	}

	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}

	public Integer getRfid() {
		return this.rfid;
	}

	public void setRfid(Integer rfid) {
		this.rfid = rfid;
	}

}