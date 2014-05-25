package org.resource.objectclass;

// Generated Sep 26, 2013 2:25:34 PM by Hibernate Tools 4.0.0

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * PdElCnBranchBox generated by hbm2java
 */
public class PdElCnBranchBox implements java.io.Serializable {

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
	private Integer branchBoxType;
	private String branchBoxModel;
	private String manufacturer;
	private String manufactureCode;
	private Date manufactureDate;
	private Integer circuitCountOut;
	private Integer backupCompartmentCount;

	public PdElCnBranchBox() {
	}

	public PdElCnBranchBox(int autoId) {
		this.autoId = autoId;
	}

	public PdElCnBranchBox(int autoId, Long staticId, String name,
			Integer objectType, Integer rfid, Integer locationType,
			Long locationId, Integer serviceStatus, String operatingUnit,
			Date commissioningDate, String stationAddress, String remark,
			String owner, Double longitude, Double latitude, Double elevation,
			Integer wiringDiagramId, Integer attachmentId, String runCode,
			Long belongsCableline, Double groundResistance,
			String assetCode, Integer branchBoxType, String branchBoxModel,
			String manufacturer, String manufactureCode, Date manufactureDate,
			Integer circuitCountOut, Integer backupCompartmentCount) {
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
		this.branchBoxType = branchBoxType;
		this.branchBoxModel = branchBoxModel;
		this.manufacturer = manufacturer;
		this.manufactureCode = manufactureCode;
		this.manufactureDate = manufactureDate;
		this.circuitCountOut = circuitCountOut;
		this.backupCompartmentCount = backupCompartmentCount;
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

	public Integer getLocationType() {
		return this.locationType;
	}

	public void setLocationType(Integer locationType) {
		this.locationType = locationType;
	}

	public Long getLocationId() {
		return this.locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Integer getServiceStatus() {
		return this.serviceStatus;
	}

	public void setServiceStatus(Integer serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public String getOperatingUnit() {
		return this.operatingUnit;
	}

	public void setOperatingUnit(String operatingUnit) {
		this.operatingUnit = operatingUnit;
	}

	public Date getCommissioningDate() {
		return this.commissioningDate;
	}

	public void setCommissioningDate(Date commissioningDate) {
		this.commissioningDate = commissioningDate;
	}

	public void setCommissioningDate(String commissioningDate) throws Exception{
//		this.commissioningDate = commissioningDate;
		this.commissioningDate = new SimpleDateFormat("yyyy-MM-dd").parse(commissioningDate);
	}

	public String getStationAddress() {
		return this.stationAddress;
	}

	public void setStationAddress(String stationAddress) {
		this.stationAddress = stationAddress;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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

	public Integer getWiringDiagramId() {
		return this.wiringDiagramId;
	}

	public void setWiringDiagramId(Integer wiringDiagramId) {
		this.wiringDiagramId = wiringDiagramId;
	}

	public Integer getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getRunCode() {
		return this.runCode;
	}

	public void setRunCode(String runCode) {
		this.runCode = runCode;
	}

	public Long getBelongsCableline() {
		return this.belongsCableline;
	}

	public void setBelongsCableline(Long belongsCableline) {
		this.belongsCableline = belongsCableline;
	}

	public Double getGroundResistance() {
		return this.groundResistance;
	}

	public void setGroundResistance(Double groundResistance) {
		this.groundResistance = groundResistance;
	}

	public String getAssetCode() {
		return this.assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public Integer getBranchBoxType() {
		return this.branchBoxType;
	}

	public void setBranchBoxType(Integer branchBoxType) {
		this.branchBoxType = branchBoxType;
	}

	public String getBranchBoxModel() {
		return this.branchBoxModel;
	}

	public void setBranchBoxModel(String branchBoxModel) {
		this.branchBoxModel = branchBoxModel;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getManufactureCode() {
		return this.manufactureCode;
	}

	public void setManufactureCode(String manufactureCode) {
		this.manufactureCode = manufactureCode;
	}

	public Date getManufactureDate() {
		return this.manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public void setManufactureDate(String manufactureDate) throws Exception{
//		this.manufactureDate = manufactureDate;
		this.commissioningDate = new SimpleDateFormat("yyyy-MM-dd").parse(manufactureDate);
	}
	
	public Integer getCircuitCountOut() {
		return this.circuitCountOut;
	}

	public void setCircuitCountOut(Integer circuitCountOut) {
		this.circuitCountOut = circuitCountOut;
	}

	public Integer getBackupCompartmentCount() {
		return this.backupCompartmentCount;
	}

	public void setBackupCompartmentCount(Integer backupCompartmentCount) {
		this.backupCompartmentCount = backupCompartmentCount;
	}

}
