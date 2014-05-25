package org.ldw.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.ldw.service.propertyService;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 入沟查询管沟段匹配
 */
public class getSearchWCSAction extends ActionSupport{
	private String term;
	private propertyService proService;
	
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addHeader("Content-Type", "text/html; charset=UTF-8");
		response.getWriter().print(proService.getFitWirecableSeg(term));
		return null;
	}
	
	//setter&getter
	public void setTerm(String term){
		this.term = term;
	}
	
	public String getTerm(){
		return term;
	}
	
	public void setProService(propertyService proService){
		this.proService = proService;
	}
	
	public propertyService getProService(){
		return proService;
	}
}
