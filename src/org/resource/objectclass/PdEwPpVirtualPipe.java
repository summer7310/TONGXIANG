package org.resource.objectclass;

// Generated Sep 26, 2013 2:25:34 PM by Hibernate Tools 4.0.0

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * PdEwPpVirtualPipe generated by hbm2java
 */
public class PdEwPpVirtualPipe implements java.io.Serializable {

	private int autoId;
	private Long staticId;
	private String name;
	private Integer objectType;
	private Integer rfid;
	private Long startBuilding;
	private Integer startBuildingType;
	private Long endBuilding;
	private Integer endBuildingType;
	private Integer sectionId;
	private Long lineBelongId;
	private String segmentCode;
	private String operatingUnit;
	private String location;
	private String archiveName;
	private String builder;
	private Date completionDate;
	private String drawingNumber;
	private String remark;
	private String owner;
	private Double sectionSizeWidth;
	private Double sectionSizeHeight;
	private Integer attachmentId;

	public PdEwPpVirtualPipe() {
	}

	public PdEwPpVirtualPipe(int autoId) {
		this.autoId = autoId;
	}

	public PdEwPpVirtualPipe(int autoId, Long staticId, String name,
			Integer objectType, Integer rfid, Long startBuilding,
			Integer startBuildingType, Long endBuilding,
			Integer endBuildingType, Integer sectionId, Long lineBelongId,
			String segmentCode, String operatingUnit, String location,
			String archiveName, String builder, Date completionDate,
			String drawingNumber, String remark, String owner,
			Double sectionSizeWidth, Double sectionSizeHeight,
			Integer attachmentId) {
		this.autoId = autoId;
		this.staticId = staticId;
		this.name = name;
		this.objectType = objectType;
		this.rfid = rfid;
		this.startBuilding = startBuilding;
		this.startBuildingType = startBuildingType;
		this.endBuilding = endBuilding;
		this.endBuildingType = endBuildingType;
		this.sectionId = sectionId;
		this.lineBelongId = lineBelongId;
		this.segmentCode = segmentCode;
		this.operatingUnit = operatingUnit;
		this.location = location;
		this.archiveName = archiveName;
		this.builder = builder;
		this.completionDate = completionDate;
		this.drawingNumber = drawingNumber;
		this.remark = remark;
		this.owner = owner;
		this.sectionSizeWidth = sectionSizeWidth;
		this.sectionSizeHeight = sectionSizeHeight;
		this.attachmentId = attachmentId;
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

	public Long getStartBuilding() {
		return this.startBuilding;
	}

	public void setStartBuilding(Long startBuilding) {
		this.startBuilding = startBuilding;
	}

	public Integer getStartBuildingType() {
		return this.startBuildingType;
	}

	public void setStartBuildingType(Integer startBuildingType) {
		this.startBuildingType = startBuildingType;
	}

	public Long getEndBuilding() {
		return this.endBuilding;
	}

	public void setEndBuilding(Long endBuilding) {
		this.endBuilding = endBuilding;
	}

	public Integer getEndBuildingType() {
		return this.endBuildingType;
	}

	public void setEndBuildingType(Integer endBuildingType) {
		this.endBuildingType = endBuildingType;
	}

	public Integer getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public Long getLineBelongId() {
		return this.lineBelongId;
	}

	public void setLineBelongId(Long lineBelongId) {
		this.lineBelongId = lineBelongId;
	}

	public String getSegmentCode() {
		return this.segmentCode;
	}

	public void setSegmentCode(String segmentCode) {
		this.segmentCode = segmentCode;
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

	public String getArchiveName() {
		return this.archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	public String getBuilder() {
		return this.builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public Date getCompletionDate() {
		return this.completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public void setCompletionDate(String completionDate)throws Exception {
//		this.completionDate = completionDate;
		this.completionDate = new SimpleDateFormat("yyyy-MM-dd").parse(completionDate);
	}
	public String getDrawingNumber() {
		return this.drawingNumber;
	}

	public void setDrawingNumber(String drawingNumber) {
		this.drawingNumber = drawingNumber;
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

	public Double getSectionSizeWidth() {
		return this.sectionSizeWidth;
	}

	public void setSectionSizeWidth(Double sectionSizeWidth) {
		this.sectionSizeWidth = sectionSizeWidth;
	}

	public Double getSectionSizeHeight() {
		return this.sectionSizeHeight;
	}

	public void setSectionSizeHeight(Double sectionSizeHeight) {
		this.sectionSizeHeight = sectionSizeHeight;
	}

	public Integer getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

}
