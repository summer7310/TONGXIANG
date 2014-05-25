package org.resource.objectclass;

// Generated Sep 26, 2013 2:25:34 PM by Hibernate Tools 4.0.0

import java.io.Serializable;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

/**
 * GeoObjectBase generated by hbm2java
 */
public class GeoObjectBase implements java.io.Serializable {

	private int autoId;
	private Geometry geometryData;
	private Long staticId;
	private String name;
	private Integer objectType;
	private Integer rfid;
	private Integer isbn;

	public GeoObjectBase() {
	}

	public GeoObjectBase(int autoId) {
		this.autoId = autoId;
	}

	public GeoObjectBase(int autoId, Geometry geometryData,
			Long staticId, String name, Integer objectType, Integer rfid,
			Integer isbn) {
		this.autoId = autoId;
		this.geometryData = geometryData;
		this.staticId = staticId;
		this.name = name;
		this.objectType = objectType;
		this.rfid = rfid;
		this.isbn = isbn;
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

}
