package org.ldw.action;

import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.ldw.service.*;

public class TreeAction extends ActionSupport {
	private String id;// = "1-1";
	private String name;// = "none";
	private String level;// = "none";
	private treeService service;
	private String result;
	
	public String execute() throws Exception {
		
		if(id==null || id.equals("")){id = "1-1";}
		if(name==null || name.equals("")){name = "none";}
		if(level==null || level.equals("")){level = "none";}
		
		service.initVar(id, name, level);
		result = service.getData();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addHeader("Content-Type", "text/html; charset=UTF-8");
		response.getWriter().print(result);
		return null;
	}
	
	//@setter
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setLevel(String level){
		this.level = level;
	}
	
	public void setService(treeService service){
		this.service = service;
	}
	
	//@getter
	public String getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getLevel(){
		return this.level;
	}	
	
	public treeService getService(){
		return this.service;
	}
	
	public String getResult(){
		return this.result;
	}
	
}