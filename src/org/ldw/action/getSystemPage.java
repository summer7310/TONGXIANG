package org.ldw.action;

import java.util.List;

import org.ldw.service.systemUserService;
import org.resource.objectclass.systemGisUser;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 根据参数返回系统功能界面
 */
public class getSystemPage extends ActionSupport {
	private String pageType = "error";
	private List<systemGisUser> users;
	private systemUserService userService;
	
	public String execute() throws Exception {
		
		if(pageType.equals("userManager")){
			users = userService.getAllUsers();
		}
		return pageType;
	}
	
	//setter&getter
	public void setPageType(String pageType){
		this.pageType = pageType;
	}

	public String getPageType(){
		return pageType;
	}
	
	public void setUsers(List<systemGisUser> users){
		this.users = users;
	}
	
	public List<systemGisUser> getUsers(){
		return users;
	}
	
	public void setUserService(systemUserService userService){
		this.userService = userService;
	} 
	
	public systemUserService getUserService(){
		return userService;
	}
}
