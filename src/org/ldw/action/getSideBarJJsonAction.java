package org.ldw.action;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.ldw.service.propertyService;

/*
 * 接线图侧栏数据查询
 */
public class getSideBarJJsonAction {
	private Long SId;
	private propertyService proService;

	public String execute() throws Exception {
		
		JSONArray result = proService.getSideBarJson(SId);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addHeader("Content-Type", "text/html; charset=UTF-8");
		response.getWriter().print(result.toString());
		return null;
		
	}
	
	//setter
	public void setSId(String SId){
		this.SId = Long.parseLong(SId);
	}
	
	public Long getSId(){
		return SId;
	}
	
	public void setProService(propertyService proService){
		this.proService = proService;
	}
	
	public propertyService getProService(){
		return proService;
	}
}
