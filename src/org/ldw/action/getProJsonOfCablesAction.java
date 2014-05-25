package org.ldw.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import org.ldw.service.propertyService;
import com.opensymphony.xwork2.ActionSupport;

/*
 * 多staticId参数查询多个不同的电缆段的属性
 */
public class getProJsonOfCablesAction extends ActionSupport {
	private List<Long> SIds = new ArrayList<Long>();
	private JSONArray result;
	private propertyService proService;
	
	public String execute() throws Exception {
	
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addHeader("Content-Type", "text/html;charset=UTF-8");
		response.getWriter().print(proService.getProJsonOfCablesBySIds(SIds));
		return null;
	}
	
	/*
	 * setter&getter
	 */
	public void setSIds(String SIds){
		
		String res[] = SIds.split(",");
		for(int i=0; i<res.length; ++i){
			this.SIds.add(Long.parseLong(res[i]));
		}
		//this.staticIds.add(Long.parseLong(this.staticIds));
	}
	
	public List<Long> getSIds(){
		return SIds;
	}
	
	public void setProService(propertyService proService){
		this.proService = proService;
	}
	
	public propertyService getProService(){
		return proService;
	}
}
