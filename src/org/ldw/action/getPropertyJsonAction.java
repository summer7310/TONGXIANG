package org.ldw.action;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;
import org.ldw.service.propertyService;
import org.apache.struts2.ServletActionContext;
import net.sf.json.JSONObject;

public class getPropertyJsonAction extends ActionSupport {
	private Long staticId;
	private int type;
	private String name;
	private boolean ifByName = false;
	//private SelectRecordAll conn;
	private propertyService proService;
	private String result;
	private JSONObject jsonResult;
	
	public String execute() throws Exception {
		
		if(ifByName){
			jsonResult = proService.getPropertyJson(type, name);
		} else {
			jsonResult = proService.getPropertyJson(type, staticId);
		}
		
		if(jsonResult != null){
			result = jsonResult.toString();
		} else {
			result = "{}";
		}
	
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addHeader("Content-Type", "text/html; charset=UTF-8");
		response.getWriter().print(result);
		return null;
		
		//return SUCCESS;

	}
	
	//setter
	public void setStaticId(String staticId){
		ifByName = false;
		this.staticId = Long.parseLong(staticId);
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public void setResult(String result){
		this.result = result;
	}
	
	public void setProService(propertyService proService){
		this.proService = proService;
	}
	
	public void setName(String name){
		ifByName = true;
		this.name = name;
	}
	
	public void setJsonResult(JSONObject jsonResult){
		this.jsonResult = jsonResult;
	}
	//getter
	public Long getStaticId(){
		return staticId;
	}
	
	public int getType(){
		return type;
	}
	
	public String getName(){
		return name;
	}
	
	public String getResult(){
		return result;
	}
	
	public JSONObject getJsonResult(){
		return jsonResult;
	}
	
	public propertyService getProService(){
		return proService;
	}
}
