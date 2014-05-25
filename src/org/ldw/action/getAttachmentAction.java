package org.ldw.action;

import java.util.List;

import org.lyd.Hibernate.SelectRecordAll;
import org.resource.objectclass.PdAuxiliary;
import org.resource.objectclass.PdPhoto;

import com.opensymphony.xwork2.ActionSupport;

public class getAttachmentAction extends ActionSupport{
	private Long sid;
	private String type;
	private SelectRecordAll selecter = new SelectRecordAll();
	private List<PdPhoto> photos;
	private List<PdAuxiliary> auxi;
	
	public String execute() throws Exception {
		if(sid != null){
			photos = selecter.selectPhotoByHostId(sid);
			auxi = selecter.selectAuxiliaryByHostId(sid);
		} else {
			photos = null;
			auxi = null;
		}
		return SUCCESS;
	}
	
	public List<PdAuxiliary> getAuxi() {
		return auxi;
	}

	public void setAuxi(List<PdAuxiliary> auxi) {
		this.auxi = auxi;
	}

	public void setSid(String sid){
		this.sid = Long.parseLong(sid);
	}
	
	public Long getSid(){
		return sid;
	}
	
	public List<PdPhoto> getPhotos(){
		return  photos;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
