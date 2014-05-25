package org.ldw.action;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.ldw.service.propertyService;

import com.opensymphony.xwork2.ActionSupport;

public class getProJsonOfWireCableByTransStationAction extends ActionSupport{
	private propertyService proService;
	private JSONArray jsonResult;
	private Long staticId;
	
	public String execute() throws Exception {
		String result;
		jsonResult = proService.getProJsonofWirecableByTransStationId(staticId);
		
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
	
	public void setStaticId(String staticId){
		this.staticId = Long.parseLong(staticId);
	}
	
	public long getStaticId(){
		return staticId;
	}
	
	public void setProService(propertyService proService){
		this.proService = proService;
	}
	
	public propertyService getProService(){
		return proService;
	}
}
