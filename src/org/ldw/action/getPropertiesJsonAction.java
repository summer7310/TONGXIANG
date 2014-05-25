package org.ldw.action;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import org.ldw.service.propertyService;
import com.opensymphony.xwork2.ActionSupport;

/*
 * 单参数查询具有相同字段的管孔列表
 */
public class getPropertiesJsonAction extends ActionSupport {
	private propertyService proService;
	private JSONArray jsonResult;
	private long profileId;//剖面ID
	private long wellId;//工井ID
	private long pipeSId;//管沟ID
	private String flag;
	
	public String execute() throws Exception {
		String result;
		if(flag.equals("PId")){
			jsonResult = proService.getProJsonOfHolesByPId(profileId);
		} else if(flag.equals("WId")) {
			jsonResult = proService.getProJsonOfHolesByWId(wellId);
		} else if(flag.equals("ppSId")){
			jsonResult = proService.getProJsonOfHolesByPpId(pipeSId);
		}
		
		if(jsonResult != null){
			result = jsonResult.toString();
		} else {
			result = "[]";
		}
	
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addHeader("Content-Type", "text/html; charset=UTF-8");
		response.getWriter().print(result);
		return null;

	}
	
	public void setProfileId(String profileId){
		flag = "PId";
		this.profileId = Long.parseLong(profileId);
	}
	
	public long getProfileId(){
		return profileId;
	}
	
	public void setWellId(String wellId){
		flag = "WId";
		this.wellId = Long.parseLong(wellId);
	}
	
	public long getWellId(){
		return wellId;
	}
	
	public void setPipeSId(String pipeSId){
		flag = "ppSId";
		this.pipeSId = Long.parseLong(pipeSId);
	}
	
	public long getPipeSId(){
		return pipeSId;
	}
	
	public void setProService(propertyService proService){
		this.proService = proService;
	}
	
	public propertyService getProService(){
		return proService;
	}
}
