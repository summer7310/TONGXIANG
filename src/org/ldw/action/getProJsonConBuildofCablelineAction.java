package org.ldw.action;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.ldw.service.propertyService;

import com.opensymphony.xwork2.ActionSupport;
//单线路显示时返回的配电线路上的所有设备和土建
public class getProJsonConBuildofCablelineAction extends ActionSupport{
	private propertyService proService;
	private JSONObject jsonResult;
	private Long CablelineId;
	
	public String execute() throws Exception {
		String result;
		jsonResult = proService.getProJsonOfContainsBuildingByCablelineId(CablelineId);
		
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
	
	public void setCablelineId(String CablelineId){
		this.CablelineId = Long.parseLong(CablelineId);
	}
	
	public long getCablelineId(){
		return CablelineId;
	}
	
	public void setProService(propertyService proService){
		this.proService = proService;
	}
	
	public propertyService getProService(){
		return proService;
	}
}
