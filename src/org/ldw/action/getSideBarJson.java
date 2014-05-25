package org.ldw.action;

import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.ldw.service.*;

public class getSideBarJson extends ActionSupport {
	//private String id;// = "1-1";
	//private String name;// = "none";
	//private String level;// = "none";
	private propertyService proService;
	private Long SId;
	private String result;
	
	public String execute() throws Exception {
		/*
		if(id==null || id.equals("")){id = "1-1";}
		if(name==null || name.equals("")){name = "none";}
		if(level==null || level.equals("")){level = "none";}
		*/
	//	service.initVar(id, name, level);
		//result = service.getSideBarJson().toString();
		//
		result = proService.getSingleSideBarJson(SId).toString();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addHeader("Content-Type", "text/html; charset=UTF-8");
		response.getWriter().print(result);
		return null;
	}
	
	

	public void setSId(String SId){
		this.SId = Long.parseLong(SId);
	}
	
	public Long getSId(){
		return SId;
	}

	public String getResult(){
		return this.result;
	}
	
	public void setProService(propertyService proService){
		this.proService = proService;
	}
	
	public propertyService getProService(){
		return proService;
	}
	
}