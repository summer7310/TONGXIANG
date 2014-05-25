package org.ldw.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.ldw.service.propertyService;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 要素部分属性随着输入实时匹配所属管沟线记录供前台选择
 */
public class getSearchPLInfoAction extends ActionSupport{
	private String term;
	private propertyService proService;
	
	public String execute() throws Exception {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addHeader("Content-Type", "text/html; charset=UTF-8");
		response.getWriter().print(proService.getFitPipeline(term));
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
