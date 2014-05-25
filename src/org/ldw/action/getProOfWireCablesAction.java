package org.ldw.action;



import net.sf.json.JSONArray;

import org.ldw.service.propertyService;
import com.opensymphony.xwork2.ActionSupport;

/*
 * 根据管沟的staticId查询所有所属线缆段信息,return form
 */
public class getProOfWireCablesAction extends ActionSupport {
	private propertyService proService;
	private JSONArray jsonResult;
	private Long PPSId;
	
	public String execute() throws Exception {
		//String result;
		jsonResult = proService.getProJsonOfWireCablesByPPSId(PPSId);
		
		return SUCCESS;

	}
	
	public void setPPSId(String PPSId){
		this.PPSId = Long.parseLong(PPSId);
	}
	
	public long getPPSId(){
		return PPSId;
	}
	
	public void setProService(propertyService proService){
		this.proService = proService;
	}
	
	public propertyService getProService(){
		return proService;
	}
	
	public void setJsonResult(JSONArray jsonResult){
		this.jsonResult = jsonResult;
	}
	
	public JSONArray getJsonResult(){
		return jsonResult;
	}
}
