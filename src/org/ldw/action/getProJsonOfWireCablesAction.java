package org.ldw.action;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import org.ldw.service.propertyService;
import com.opensymphony.xwork2.ActionSupport;

/*
 * 根据管沟的staticId查询所有所属线缆段信息
 */
public class getProJsonOfWireCablesAction extends ActionSupport {
	private propertyService proService;
	private JSONArray jsonResult;
	private Long PPSId;
	
	public String execute() throws Exception {
		String result;
		jsonResult = proService.getProJsonOfWireCablesByPPSId(PPSId);
		
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
}
