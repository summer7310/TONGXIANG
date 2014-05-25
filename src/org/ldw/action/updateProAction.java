package org.ldw.action;

import org.ldw.service.propertyService;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 根据静态ID更新对象某条属性
 */
public class updateProAction extends ActionSupport{
	private long SId;
	private long wcSeg;
	private propertyService proService;
	
	public String execute() throws Exception {
		
		if(proService.updateWCSegOfHole(SId, wcSeg) == 1){
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	//setter&getter
	public void setSId(String SId){
		this.SId = Long.parseLong(SId);
	}
	
	public void setWcSeg(String wcSeg){
		this.wcSeg = Long.parseLong(wcSeg);
	}
	
	public long getSId(){
		return SId;
	}
	
	public long getWcSeg(){
		return wcSeg;
	}
	
	public void setProService(propertyService proService){
		this.proService = proService;
	}
	
	public propertyService getProService(){
		return proService;
	}

}
