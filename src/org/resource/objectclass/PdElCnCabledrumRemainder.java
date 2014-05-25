package org.resource.objectclass;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PdElCnCabledrumRemainder {
	private int autoId;
	private Long staticId;
	private String name;
	private Integer objectType;
	private Integer rfid;
	private Integer locationType;
	private Long locationId;
	private Integer serviceStatus;
	private String operatingUnit;
	private Date commissioningDate;
	private String stationAddress;
	private String remark;
	private String owner;
	private Double longitude;
	private Double latitude;
	private Double elevation;
	private Integer wiringDiagramId;
	private Integer attachmentId;
	private String runCode;
	private Long belongsCableline;
	private Double groundResistance;
	private String assetCode;
	private Integer remainderLength;
	private Long belongsWirecableSegmeng;
	
	public Integer getRemainderLength() {
		return remainderLength;
	}
	public void setRemainderLength(Integer remainderLength) {
		this.remainderLength = remainderLength;
	}
	public PdElCnCabledrumRemainder() {
	}
	public PdElCnCabledrumRemainder(int autoId) {
		this.autoId = autoId;
	}

	public PdElCnCabledrumRemainder(int autoId, Long staticId, String name,
			Integer objectType, Integer rfid, Integer locationType,
			Long locationId, Integer serviceStatus, String operatingUnit,
			Date commissioningDate, String stationAddress, String remark,
			String owner, Double longitude, Double latitude, Double elevation,
			Integer wiringDiagramId, Integer attachmentId, String runCode,
			Long belongsCableline, Double groundResistance, String assetCode,
			Integer remainderLength,Long belongsWirecableSegmeng) {
		this.autoId = autoId;
		this.staticId = staticId;
		this.name = name;
		this.objectType = objectType;
		this.rfid = rfid;
		this.locationType = locationType;
		this.locationId = locationId;
		this.serviceStatus = serviceStatus;
		this.operatingUnit = operatingUnit;
		this.commissioningDate = commissioningDate;
		this.stationAddress = stationAddress;
		this.remark = remark;
		this.owner = owner;
		this.longitude = longitude;
		this.latitude = latitude;
		this.elevation = elevation;
		this.wiringDiagramId = wiringDiagramId;
		this.attachmentId = attachmentId;
		this.runCode = runCode;
		this.belongsCableline = belongsCableline;
		this.groundResistance = groundResistance;
		this.assetCode = assetCode;
		this.remainderLength = remainderLength;
		this.belongsWirecableSegmeng = belongsWirecableSegmeng;
	}
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	public Long getStaticId() {
		return staticId;
	}
	public void setStaticId(Long staticId) {
		this.staticId = staticId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getObjectType() {
		return objectType;
	}
	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}
	public Integer getRfid() {
		return rfid;
	}
	public void setRfid(Integer rfid) {
		this.rfid = rfid;
	}
	public Integer getLocationType() {
		return locationType;
	}
	public void setLocationType(Integer locationType) {
		this.locationType = locationType;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public Integer getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(Integer serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public String getOperatingUnit() {
		return operatingUnit;
	}
	public void setOperatingUnit(String operatingUnit) {
		this.operatingUnit = operatingUnit;
	}
	public Date getCommissioningDate() {
		return commissioningDate;
	}
	public void setCommissioningDate(Date commissioningDate) {
		this.commissioningDate = commissioningDate;
	}
	public void setCommissioningDate(String commissioningDate) throws Exception{
		this.commissioningDate = new SimpleDateFormat("yyyy-MM-dd").parse(commissioningDate);
	}
	public String getStationAddress() {
		return stationAddress;
	}
	public void setStationAddress(String stationAddress) {
		this.stationAddress = stationAddress;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getElevation() {
		return elevation;
	}
	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}
	public Integer getWiringDiagramId() {
		return wiringDiagramId;
	}
	public void setWiringDiagramId(Integer wiringDiagramId) {
		this.wiringDiagramId = wiringDiagramId;
	}
	public Integer getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getRunCode() {
		return runCode;
	}
	public void setRunCode(String runCode) {
		this.runCode = runCode;
	}
	public Long getBelongsCableline() {
		return belongsCableline;
	}
	public void setBelongsCableline(Long belongsCableline) {
		this.belongsCableline = belongsCableline;
	}
	public Double getGroundResistance() {
		return groundResistance;
	}
	public void setGroundResistance(Double groundResistance) {
		this.groundResistance = groundResistance;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public Long getBelongsWirecableSegmeng() {
		return belongsWirecableSegmeng;
	}
	public void setBelongsWirecableSegmeng(Long belongsWirecableSegmeng) {
		this.belongsWirecableSegmeng = belongsWirecableSegmeng;
	}
	
}
