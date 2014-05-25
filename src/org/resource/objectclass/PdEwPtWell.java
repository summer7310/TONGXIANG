package org.resource.objectclass;

// Generated Sep 26, 2013 2:25:34 PM by Hibernate Tools 4.0.0

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * PdEwPtWell generated by hbm2java
 */
public class PdEwPtWell implements java.io.Serializable {

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
	private String operatingUnit;
	private String location;
	private Integer wellType;
	private Integer structure;
	private Double topDepth;
	private Double bottomDepth;
	private Double wellWidth;
	private Double wellLenth;
	private Integer platformLayers;
	private Date commencementDate;
	private Date completionDate;
	private String drawingNumber;
	private Integer channelCount;
	private Boolean isVirtualWell;
	private Double coversSizeRound;
	private Integer coversCountRound;
	private Integer coversMaterialRound;
	private String coversManufactureRound;
	private Date manufactureDateRound;
	private String coversSizeSquare;
	private Integer coversCountSquare;
	private Integer coversMaterialSquare;
	private String coversManufactureSquare;
	private Date manufactureDateSquare;
	private String kickSpecification;
	private String kickStandMaterial;
	private Integer kickStandCount;
	private Integer wellExpansionPlanId;

	public PdEwPtWell() {
	}

	public PdEwPtWell(int autoId) {
		this.autoId = autoId;
	}

	public PdEwPtWell(int autoId, Long staticId, String name,
			Integer objectType, Integer rfid, String builder, String assetCode,
			Double longitude, Double latitude, Double elevation, String owner,
			Long belongsRoad, Integer voltageGrade, Integer attachmentId,
			String remark, String operatingUnit, String location,
			Integer wellType, Integer structure, Double topDepth,
			Double bottomDepth, Double wellWidth, Double wellLenth,
			Integer platformLayers, Date commencementDate, Date completionDate,
			String drawingNumber, Integer channelCount, Boolean isVirtualWell,
			Double coversSizeRound, Integer coversCountRound,
			Integer coversMaterialRound, String coversManufactureRound,
			Date manufactureDateRound, String coversSizeSquare,
			Integer coversCountSquare, Integer coversMaterialSquare,
			String coversManufactureSquare, Date manufactureDateSquare,
			String kickSpecification, String kickStandMaterial,
			Integer kickStandCount, Integer wellExpansionPlanId) {
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
		this.operatingUnit = operatingUnit;
		this.location = location;
		this.wellType = wellType;
		this.structure = structure;
		this.topDepth = topDepth;
		this.bottomDepth = bottomDepth;
		this.wellWidth = wellWidth;
		this.wellLenth = wellLenth;
		this.platformLayers = platformLayers;
		this.commencementDate = commencementDate;
		this.completionDate = completionDate;
		this.drawingNumber = drawingNumber;
		this.channelCount = channelCount;
		this.isVirtualWell = isVirtualWell;
		this.coversSizeRound = coversSizeRound;
		this.coversCountRound = coversCountRound;
		this.coversMaterialRound = coversMaterialRound;
		this.coversManufactureRound = coversManufactureRound;
		this.manufactureDateRound = manufactureDateRound;
		this.coversSizeSquare = coversSizeSquare;
		this.coversCountSquare = coversCountSquare;
		this.coversMaterialSquare = coversMaterialSquare;
		this.coversManufactureSquare = coversManufactureSquare;
		this.manufactureDateSquare = manufactureDateSquare;
		this.kickSpecification = kickSpecification;
		this.kickStandMaterial = kickStandMaterial;
		this.kickStandCount = kickStandCount;
		this.wellExpansionPlanId = wellExpansionPlanId;
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

	public String getOperatingUnit() {
		return this.operatingUnit;
	}

	public void setOperatingUnit(String operatingUnit) {
		this.operatingUnit = operatingUnit;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getWellType() {
		return this.wellType;
	}

	public void setWellType(Integer wellType) {
		this.wellType = wellType;
	}

	public Integer getStructure() {
		return this.structure;
	}

	public void setStructure(Integer structure) {
		this.structure = structure;
	}

	public Double getTopDepth() {
		return this.topDepth;
	}

	public void setTopDepth(Double topDepth) {
		this.topDepth = topDepth;
	}

	public Double getBottomDepth() {
		return this.bottomDepth;
	}

	public void setBottomDepth(Double bottomDepth) {
		this.bottomDepth = bottomDepth;
	}

	public Double getWellWidth() {
		return this.wellWidth;
	}

	public void setWellWidth(Double wellWidth) {
		this.wellWidth = wellWidth;
	}

	public Double getWellLenth() {
		return this.wellLenth;
	}

	public void setWellLenth(Double wellLenth) {
		this.wellLenth = wellLenth;
	}

	public Integer getPlatformLayers() {
		return this.platformLayers;
	}

	public void setPlatformLayers(Integer platformLayers) {
		this.platformLayers = platformLayers;
	}

	public Date getCommencementDate() {
		return this.commencementDate;
	}

	public void setCommencementDate(Date commencementDate) {
		this.commencementDate = commencementDate;
	}
	public void setCommencementDate(String commencementDate) throws Exception{
		this.commencementDate = new SimpleDateFormat("yyyy-MM-dd").parse(commencementDate);
//		this.commencementDate = commencementDate;
	}

	public Date getCompletionDate() {
		return this.completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public void setCompletionDate(String completionDate)throws Exception{
		
		this.completionDate = new SimpleDateFormat("yyyy-MM-dd").parse(completionDate);
		
	}
	
	public String getDrawingNumber() {
		return this.drawingNumber;
	}

	public void setDrawingNumber(String drawingNumber) {
		this.drawingNumber = drawingNumber;
	}

	public Integer getChannelCount() {
		return this.channelCount;
	}

	public void setChannelCount(Integer channelCount) {
		this.channelCount = channelCount;
	}

	public Boolean getIsVirtualWell() {
		return this.isVirtualWell;
	}

	public void setIsVirtualWell(Boolean isVirtualWell) {
		this.isVirtualWell = isVirtualWell;
	}

	public Double getCoversSizeRound() {
		return this.coversSizeRound;
	}

	public void setCoversSizeRound(Double coversSizeRound) {
		this.coversSizeRound = coversSizeRound;
	}

	public Integer getCoversCountRound() {
		return this.coversCountRound;
	}

	public void setCoversCountRound(Integer coversCountRound) {
		this.coversCountRound = coversCountRound;
	}

	public Integer getCoversMaterialRound() {
		return this.coversMaterialRound;
	}

	public void setCoversMaterialRound(Integer coversMaterialRound) {
		this.coversMaterialRound = coversMaterialRound;
	}

	public String getCoversManufactureRound() {
		return this.coversManufactureRound;
	}

	public void setCoversManufactureRound(String coversManufactureRound) {
		this.coversManufactureRound = coversManufactureRound;
	}

	public Date getManufactureDateRound() {
		return this.manufactureDateRound;
	}

	public void setManufactureDateRound(Date manufactureDateRound) {
		this.manufactureDateRound = manufactureDateRound;
	}

	public void setManufactureDateRound(String manufactureDateRound) throws Exception {
		this.manufactureDateRound = new SimpleDateFormat("yyyy-MM-dd").parse(manufactureDateRound);
	}
	public String getCoversSizeSquare() {
		return this.coversSizeSquare;
	}

	public void setCoversSizeSquare(String coversSizeSquare) {
		this.coversSizeSquare = coversSizeSquare;
	}

	public Integer getCoversCountSquare() {
		return this.coversCountSquare;
	}

	public void setCoversCountSquare(Integer coversCountSquare) {
		this.coversCountSquare = coversCountSquare;
	}

	public Integer getCoversMaterialSquare() {
		return this.coversMaterialSquare;
	}

	public void setCoversMaterialSquare(Integer coversMaterialSquare) {
		this.coversMaterialSquare = coversMaterialSquare;
	}

	public String getCoversManufactureSquare() {
		return this.coversManufactureSquare;
	}

	public void setCoversManufactureSquare(String coversManufactureSquare) {
		this.coversManufactureSquare = coversManufactureSquare;
	}

	public Date getManufactureDateSquare() {
		return this.manufactureDateSquare;
	}

	public void setManufactureDateSquare(Date manufactureDateSquare) {
		this.manufactureDateSquare = manufactureDateSquare;
	}

	public void setManufactureDateSquare(String manufactureDateSquare) throws Exception {
		this.manufactureDateSquare = new SimpleDateFormat("yyyy-MM-dd").parse(manufactureDateSquare);
	}
	
	public String getKickSpecification() {
		return this.kickSpecification;
	}

	public void setKickSpecification(String kickSpecification) {
		this.kickSpecification = kickSpecification;
	}

	public String getKickStandMaterial() {
		return this.kickStandMaterial;
	}

	public void setKickStandMaterial(String kickStandMaterial) {
		this.kickStandMaterial = kickStandMaterial;
	}

	public Integer getKickStandCount() {
		return this.kickStandCount;
	}

	public void setKickStandCount(Integer kickStandCount) {
		this.kickStandCount = kickStandCount;
	}

	public Integer getWellExpansionPlanId() {
		return this.wellExpansionPlanId;
	}

	public void setWellExpansionPlanId(Integer wellExpansionPlanId) {
		this.wellExpansionPlanId = wellExpansionPlanId;
	}

}