package org.resource.objectclass;

import com.vividsolutions.jts.geom.Geometry;

public class GeoPipeBracket implements java.io.Serializable{
	private int autoId;
	private Long staticId;
	private Geometry geometryData;
	private String name;
	private Integer objectType;
	private Integer rfid;
	private Integer isbn;
	private Long wellId;
	private Long ppSegmentId;
	private Long mirrorWellId;
	
	public GeoPipeBracket() {

	}
	
	public GeoPipeBracket(int auroId) {
		this.autoId = auroId;
	}
	
	public GeoPipeBracket(int auroId, Long staticId, Geometry geometryData,
			String name, Integer objectType, Integer rfid, Integer isbn,
			Long wellId, Long ppSegmentId, Long mirrorWellId) {
		this.autoId = auroId;
		this.staticId = staticId;
		this.geometryData = geometryData;
		this.name = name;
		this.objectType = objectType;
		this.rfid = rfid;
		this.isbn = isbn;
		this.wellId = wellId;
		this.ppSegmentId = ppSegmentId;
		this.mirrorWellId = mirrorWellId;
	}
	
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int auroId) {
		this.autoId = auroId;
	}
	public Long getStaticId() {
		return staticId;
	}
	public void setStaticId(Long staticId) {
		this.staticId = staticId;
	}
	public Geometry getGeometryData() {
		return geometryData;
	}
	public void setGeometryData(Geometry geometryData) {
		this.geometryData = geometryData;
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
	public Integer getIsbn() {
		return isbn;
	}
	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}
	public Long getWellId() {
		return wellId;
	}
	public void setWellId(Long wellId) {
		this.wellId = wellId;
	}
	public Long getPpSegmentId() {
		return ppSegmentId;
	}
	public void setPpSegmentId(Long ppSegmentId) {
		this.ppSegmentId = ppSegmentId;
	}
	public Long getMirrorWellId() {
		return mirrorWellId;
	}
	public void setMirrorWellId(Long mirrorWellId) {
		this.mirrorWellId = mirrorWellId;
	}
	
	
}
