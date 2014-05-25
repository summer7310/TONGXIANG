package org.ldw.action;

import org.ldw.service.systemUserService;
import org.resource.objectclass.systemGisUser;

import com.opensymphony.xwork2.ActionSupport;


public class getUserInfoAction extends ActionSupport {
	private int AId;
	private systemGisUser user;
	private systemUserService userService;
	
	public String execute() throws Exception {
		user = userService.getUserByAId(AId);
		
		if(user == null){
			return ERROR;
		}
		return SUCCESS;
	}

	//setter&getter
	public void setAId(int AId){
		this.AId = AId;
	}
	
	public int getAId(){
		return AId;
	}
	
	public void setUser(systemGisUser user){
		this.user = user;
	}
	
	public systemGisUser getUser(){
		return user;
	}
	
	public void setUserService(systemUserService userService){
		this.userService = userService;
	} 
	
	public systemUserService getUserService(){
		return userService;
	}
}
