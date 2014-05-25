package org.ldw.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.lyd.Hibernate.DeleteObject;
import org.lyd.Hibernate.SelectRecordAll;
import org.resource.objectclass.PdAuxiliary;
import org.resource.objectclass.PdPhoto;

import com.opensymphony.xwork2.ActionSupport;

public class deleteAction extends ActionSupport {
	private String type;
	private Long sid;
	private List<Long> sids = new ArrayList<Long>();
	private int aid;
	private DeleteObject deleter = new DeleteObject();
	private SelectRecordAll selecter = new SelectRecordAll();

	public String execute() throws Exception {
		int res = 0;
		
		if(type.equals("photo")){
			PdPhoto photo = selecter.selectPhotoBuAutoId(aid);
			int flagDelete = deleter.deleteAuxi(aid);
			if(flagDelete==1){
				
				//String photoPath = photo.getPhotoPath();
				//String photoPreviewPath = photo.getPreviewPhotoPath();
				File saveFile = new File(ServletActionContext.getServletContext().getRealPath("/uploads"+photo.getPhotoPath()+""));
				File previewSaveFile = new File(ServletActionContext.getServletContext().getRealPath("/uploads"+photo.getPreviewPhotoPath()+""));
				boolean flagFileDelete = saveFile.delete();
				boolean flagPreviewFileDelete = previewSaveFile.delete();
				if(flagFileDelete&&flagPreviewFileDelete){
					flagDelete=1;
				}else{
					flagDelete = 0;
				}
			}
			
			res = flagDelete;
		}
		if(type.equals("auxi")){
			PdAuxiliary auxi = selecter.selectAuxiliaryByAutoId(aid);
			int flagDelete = deleter.deleteAuxi(aid);
			if(flagDelete==1){
				
				//String photoPath = photo.getPhotoPath();
				//String photoPreviewPath = photo.getPreviewPhotoPath();
				File saveFile = new File(ServletActionContext.getServletContext().getRealPath("/uploads"+auxi.getAuxiliaryPath()+""));
				//File previewSaveFile = new File(ServletActionContext.getServletContext().getRealPath("/uploads"+photo.getPreviewPhotoPath()+""));
				boolean flagFileDelete = saveFile.delete();
				//boolean flagPreviewFileDelete = previewSaveFile.delete();
				if(flagFileDelete){
					flagDelete=1;
				}else{
					flagDelete = 0;
				}
			}
			
			res = flagDelete;
		}
		
		if(type.equals("property")){
			res = deleter.deleteObjectP(sid);
		}
		if(type.equals("cable")){
			for(Long x:sids){
				res = deleter.deleteHolePoleInfo(x);
			}
		}
		
		
		if(res ==1){
			return SUCCESS;
		} else {
			return ERROR;
		}
		
	}

	public String getType() {
		return type;
	}

	public DeleteObject getDeleter() {
		return deleter;
	}

	public void setDeleter(DeleteObject deleter) {
		this.deleter = deleter;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = Long.parseLong(sid);
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public List<Long> getSids() {
		return sids;
	}

	public void setSids(String sids) {
		String res[] = sids.split(",");
		for(int i=0; i<res.length; ++i){
			this.sids.add(Long.parseLong(res[i]));
		}
	}
}
