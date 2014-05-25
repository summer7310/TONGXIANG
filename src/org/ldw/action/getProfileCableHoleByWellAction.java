package org.ldw.action;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.ldw.service.propertyService;

import com.opensymphony.xwork2.ActionSupport;

public class getProfileCableHoleByWellAction extends ActionSupport {
	private propertyService proService;
	private JSONObject jsonResult;
	private Long wellId;
	
	public String execute() throws Exception{
		String result;
		jsonResult = proService.getProJsonProfileCableHoleByWell(wellId);
		
		if(jsonResult != null){
			result =jsonResult.toString();
		}else{
			result = "[]";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addHeader("Content-Type", "text/html; charset=UTF-8");
		response.getWriter().print(result);
		return null;
	}

	public propertyService getProService() {
		return proService;
	}

	public void setProService(propertyService proService) {
		this.proService = proService;
	}

	public JSONObject getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(JSONObject jsonResult) {
		this.jsonResult = jsonResult;
	}

	public Long getWellId() {
		return wellId;
	}

	public void setWellId(String wellId) {
		this.wellId = Long.parseLong(wellId);
	}
	
}
