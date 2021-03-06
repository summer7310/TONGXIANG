package org.resource.objectclass;

import com.vividsolutions.jts.geom.Geometry;

public class GeoCivilEngineering implements java.io.Serializable{
	private int autoId;
	private Geometry geometryData;
	private Long staticId;
	private String name;
	private Integer objectType;
	private Integer rfid;
	private Integer isbn;
	private Integer ZIndex;
	private Integer XOffset;
	private Integer YOffset;
	public GeoCivilEngineering() {
		
	}

	public GeoCivilEngineering(int autoId) {
		this.autoId = autoId;
	}

	public GeoCivilEngineering(int autoId, Geometry geometryData,
			Long staticId, String name, Integer objectType, Integer rfid,
			Integer isbn,Integer ZIndex,Integer XOffset,Integer YOffset) {
		this.autoId = autoId;
		this.geometryData = geometryData;
		this.staticId = staticId;
		this.name = name;
		this.objectType = objectType;
		this.rfid = rfid;
		this.isbn = isbn;
		//this.belongsCableline = belongsCableline;
		//this.startTransStation = startTransStation;
		this.ZIndex = ZIndex;
		this.XOffset = XOffset;
		this.YOffset = YOffset;
	}

	public int getAutoId() {
		return this.autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public Geometry getGeometryData() {
		return this.geometryData;
	}

	public void setGeometryData(Geometry geometryData) {
		this.geometryData = geometryData;
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

	public Integer getIsbn() {
		return this.isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

//	public Long getBelongsCableline() {
//		return belongsCableline;
//	}

//	public void setBelongsCableline(Long belongsCableline) {
//		this.belongsCableline = belongsCableline;
//	}

//	public Long getStartTransStation() {
//		return startTransStation;
//	}

//	public void setStartTransStation(Long startTransStation) {
//		this.startTransStation = startTransStation;
//	}

	public Integer getZIndex() {
		return ZIndex;
	}

	public void setZIndex(Integer zIndex) {
		this.ZIndex = zIndex;
	}

	public Integer getXOffset() {
		return XOffset;
	}

	public void setXOffset(Integer xOffset) {
		XOffset = xOffset;
	}

	public Integer getYOffset() {
		return YOffset;
	}

	public void setYOffset(Integer yOffset) {
		YOffset = yOffset;
	}


}
