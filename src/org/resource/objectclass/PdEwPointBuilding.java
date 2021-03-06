package org.resource.objectclass;

// Generated Sep 26, 2013 2:25:34 PM by Hibernate Tools 4.0.0

/**
 * PdEwPointBuilding generated by hbm2java
 */
public class PdEwPointBuilding implements java.io.Serializable {

	private int autoId;
	private Long staticId;
	private String name;
	private Integer objectType;
	private Integer rfid;
	private String builder;
	private String assetCode;
	private Double longitude;
	private Double latitude;
	private Double elevation;
	private String owner;
	private Long belongsRoad;
	private Integer voltageGrade;
	private Integer attachmentId;
	private String remark;

	public PdEwPointBuilding() {
	}

	public PdEwPointBuilding(int autoId) {
		this.autoId = autoId;
	}

	public PdEwPointBuilding(int autoId, Long staticId, String name,
			Integer objectType, Integer rfid, String builder, String assetCode,
			Double longitude, Double latitude, Double elevation, String owner,
			Long belongsRoad, Integer voltageGrade, Integer attachmentId,
			String remark) {
		this.autoId = autoId;
		this.staticId = staticId;
		this.name = name;
		this.objectType = objectType;
		this.rfid = rfid;
		this.builder = builder;
		this.assetCode = assetCode;
		this.longitude = longitude;
		this.latitude = latitude;
		this.elevation = elevation;
		this.owner = owner;
		this.belongsRoad = belongsRoad;
		this.voltageGrade = voltageGrade;
		this.attachmentId = attachmentId;
		this.remark = remark;
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

	public String getBuilder() {
		return this.builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public String getAssetCode() {
		return this.assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getElevation() {
		return this.elevation;
	}

	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Long getBelongsRoad() {
		return this.belongsRoad;
	}

	public void setBelongsRoad(Long belongsRoad) {
		this.belongsRoad = belongsRoad;
	}

	public Integer getVoltageGrade() {
		return this.voltageGrade;
	}

	public void setVoltageGrade(Integer voltageGrade) {
		this.voltageGrade = voltageGrade;
	}

	public Integer getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
