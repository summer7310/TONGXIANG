package org.ldw.action;

import java.util.List;

import net.sf.json.JSONArray;

import org.ldw.service.propertyService;
import org.resource.objectclass.PdElLnWirecableSegmengV;

import com.opensymphony.xwork2.ActionSupport;

public class getWiresOfProfileAction extends ActionSupport {
	private JSONArray wireDone;
	private List<PdElLnWirecableSegmengV> wireUndone;
	private Long SId;
	private propertyService proService;
	
	public String execute() throws Exception {
		wireDone = proService.getWireDoneOfProfile(SId);
		wireUndone = proService.getWireUndoneOfProfile(SId);
		return SUCCESS;
	}
	
	//setter&getter
	public void setSId(String SId){
		this.SId = Long.parseLong(SId);
	}
	
	public Long getSId(){
		return SId;
	}
	
	public JSONArray getWireDone(){
		return wireDone;
	}
	
	public List<PdElLnWirecableSegmengV> getWireUndone(){
		return wireUndone;
	}
	
	public void setProService(propertyService proService){
		this.proService = proService;
	}
	
	public propertyService getProService(){
		return proService;
	}
}
