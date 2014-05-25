package org.resource.objectclass;

// Generated Sep 26, 2013 2:25:34 PM by Hibernate Tools 4.0.0

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * PdElCnTransformer generated by hbm2java
 */
public class PdElCnTransformer implements java.io.Serializable {

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
	private Integer transformerType;
	private Integer boxType;
	private Boolean hasRing;
	private Integer voltageGrade;
	private Integer natureOfUse;
	private String transformerModel;
	private String manufacturer;
	private String manufactureCode;
	private Date manufactureDate;
	private Integer phaseCount;
	private Integer dielectric;
	private Double ratedCapacity;
	private Double impedanceVoltage;
	private Double unLoadCurrent;
	private Double shortCircuitLoss;
	private Double unLoadLoss;
	private String wiringGroup;
	private String voltageRatio;
	private Double highPreCurrent;
	private Double lowPreCurrent;
	private String oilNumber;
	private Double oilWeight;
	private Double totalWeight;
	private String thermostatManufacturers;
	private String thermostatModel;
	private String insulationClass;

	public PdElCnTransformer() {
	}

	public PdElCnTransformer(int autoId) {
		this.autoId = autoId;
	}

	public PdElCnTransformer(int autoId, Long staticId, String name,
			Integer objectType, Integer rfid, Integer locationType,
			Long locationId, Integer serviceStatus, String operatingUnit,
			Date commissioningDate, String stationAddress, String remark,
			String owner, Double longitude, Double latitude, Double elevation,
			Integer wiringDiagramId, Integer attachmentId, String runCode,
			Long belongsCableline, Double groundResistance,
			String assetCode, Integer transformerType, Integer boxType,
			Boolean hasRing, Integer voltageGrade, Integer natureOfUse,
			String transformerModel, String manufacturer,
			String manufactureCode, Date manufactureDate, Integer phaseCount,
			Integer dielectric, Double ratedCapacity, Double impedanceVoltage,
			Double unLoadCurrent, Double shortCircuitLoss, Double unLoadLoss,
			String wiringGroup, String voltageRatio, Double highPreCurrent,
			Double lowPreCurrent, String oilNumber, Double oilWeight,
			Double totalWeight, String thermostatManufacturers,
			String thermostatModel, String insulationClass) {
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
		this.transformerType = transformerType;
		this.boxType = boxType;
		this.hasRing = hasRing;
		this.voltageGrade = voltageGrade;
		this.natureOfUse = natureOfUse;
		this.transformerModel = transformerModel;
		this.manufacturer = manufacturer;
		this.manufactureCode = manufactureCode;
		this.manufactureDate = manufactureDate;
		this.phaseCount = phaseCount;
		this.dielectric = dielectric;
		this.ratedCapacity = ratedCapacity;
		this.impedanceVoltage = impedanceVoltage;
		this.unLoadCurrent = unLoadCurrent;
		this.shortCircuitLoss = shortCircuitLoss;
		this.unLoadLoss = unLoadLoss;
		this.wiringGroup = wiringGroup;
		this.voltageRatio = voltageRatio;
		this.highPreCurrent = highPreCurrent;
		this.lowPreCurrent = lowPreCurrent;
		this.oilNumber = oilNumber;
		this.oilWeight = oilWeight;
		this.totalWeight = totalWeight;
		this.thermostatManufacturers = thermostatManufacturers;
		this.thermostatModel = thermostatModel;
		this.insulationClass = insulationClass;
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

	public Integer getTransformerType() {
		return this.transformerType;
	}

	public void setTransformerType(Integer transformerType) {
		this.transformerType = transformerType;
	}

	public Integer getBoxType() {
		return this.boxType;
	}

	public void setBoxType(Integer boxType) {
		this.boxType = boxType;
	}

	public Boolean getHasRing() {
		return this.hasRing;
	}

	public void setHasRing(Boolean hasRing) {
		this.hasRing = hasRing;
	}

	public Integer getVoltageGrade() {
		return this.voltageGrade;
	}

	public void setVoltageGrade(Integer voltageGrade) {
		this.voltageGrade = voltageGrade;
	}

	public Integer getNatureOfUse() {
		return this.natureOfUse;
	}

	public void setNatureOfUse(Integer natureOfUse) {
		this.natureOfUse = natureOfUse;
	}

	public String getTransformerModel() {
		return this.transformerModel;
	}

	public void setTransformerModel(String transformerModel) {
		this.transformerModel = transformerModel;
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

	public void setManufactureDate(String manufactureDate)throws Exception {
//		this.manufactureDate = manufactureDate;
		this.commissioningDate = new SimpleDateFormat("yyyy-MM-dd").parse(manufactureDate);
	}

	public Integer getPhaseCount() {
		return this.phaseCount;
	}

	public void setPhaseCount(Integer phaseCount) {
		this.phaseCount = phaseCount;
	}

	public Integer getDielectric() {
		return this.dielectric;
	}

	public void setDielectric(Integer dielectric) {
		this.dielectric = dielectric;
	}

	public Double getRatedCapacity() {
		return this.ratedCapacity;
	}

	public void setRatedCapacity(Double ratedCapacity) {
		this.ratedCapacity = ratedCapacity;
	}

	public Double getImpedanceVoltage() {
		return this.impedanceVoltage;
	}

	public void setImpedanceVoltage(Double impedanceVoltage) {
		this.impedanceVoltage = impedanceVoltage;
	}

	public Double getUnLoadCurrent() {
		return this.unLoadCurrent;
	}

	public void setUnLoadCurrent(Double unLoadCurrent) {
		this.unLoadCurrent = unLoadCurrent;
	}

	public Double getShortCircuitLoss() {
		return this.shortCircuitLoss;
	}

	public void setShortCircuitLoss(Double shortCircuitLoss) {
		this.shortCircuitLoss = shortCircuitLoss;
	}

	public Double getUnLoadLoss() {
		return this.unLoadLoss;
	}

	public void setUnLoadLoss(Double unLoadLoss) {
		this.unLoadLoss = unLoadLoss;
	}

	public String getWiringGroup() {
		return this.wiringGroup;
	}

	public void setWiringGroup(String wiringGroup) {
		this.wiringGroup = wiringGroup;
	}

	public String getVoltageRatio() {
		return this.voltageRatio;
	}

	public void setVoltageRatio(String voltageRatio) {
		this.voltageRatio = voltageRatio;
	}

	public Double getHighPreCurrent() {
		return this.highPreCurrent;
	}

	public void setHighPreCurrent(Double highPreCurrent) {
		this.highPreCurrent = highPreCurrent;
	}

	public Double getLowPreCurrent() {
		return this.lowPreCurrent;
	}

	public void setLowPreCurrent(Double lowPreCurrent) {
		this.lowPreCurrent = lowPreCurrent;
	}

	public String getOilNumber() {
		return this.oilNumber;
	}

	public void setOilNumber(String oilNumber) {
		this.oilNumber = oilNumber;
	}

	public Double getOilWeight() {
		return this.oilWeight;
	}

	public void setOilWeight(Double oilWeight) {
		this.oilWeight = oilWeight;
	}

	public Double getTotalWeight() {
		return this.totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public String getThermostatManufacturers() {
		return this.thermostatManufacturers;
	}

	public void setThermostatManufacturers(String thermostatManufacturers) {
		this.thermostatManufacturers = thermostatManufacturers;
	}

	public String getThermostatModel() {
		return this.thermostatModel;
	}

	public void setThermostatModel(String thermostatModel) {
		this.thermostatModel = thermostatModel;
	}

	public String getInsulationClass() {
		return this.insulationClass;
	}

	public void setInsulationClass(String insulationClass) {
		this.insulationClass = insulationClass;
	}

}