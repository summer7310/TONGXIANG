package org.resource.objectclass;

import java.io.Serializable;

public class PdPhoto implements Serializable {
	private int autoId;
	private Long staticId;
	private String name;
	private Integer objectType;
	private Integer rfid;
	private String photoPath;
	private String previewPhotoPath;
	private Long hostId;
	
	
	public PdPhoto() {
		
	}

	public PdPhoto(int autoId) {
		this.autoId = autoId;
	}

	public PdPhoto(int autoId, Long staticId, String name, Integer objectType,
			Integer rfid, String photoPath, String previewPhotoPath,
			Long hostId) {
		this.autoId = autoId;
		this.staticId = staticId;
		this.name = name;
		this.objectType = objectType;
		this.rfid = rfid;
		this.photoPath = photoPath;
		this.previewPhotoPath = previewPhotoPath;
		this.hostId = hostId;
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
	
	public String getPhotoPath() {
		return photoPath;
	}
	
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	public String getPreviewPhotoPath() {
		return previewPhotoPath;
	}
	
	public void setPreviewPhotoPath(String previewPhotoPath) {
		this.previewPhotoPath = previewPhotoPath;
	}
	
	public Long getHostId() {
		return hostId;
	}
	
	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}
	
	
}
