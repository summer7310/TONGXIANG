package org.ldw.action;
import com.opensymphony.xwork2.ActionSupport;
import org.ldw.service.systemUserService;
import org.resource.objectclass.systemGisUser;

/*
 * 用户的添加，信息修改及删除
 */
public class addUserAction extends ActionSupport{
	private systemGisUser user;
	private String mode;
	private systemUserService userService;
	
	
	public String execute() throws Exception {
		if(mode != null && mode.equals("update")){
			if(userService.updateUser(user) == 1){
				return "ok";
			} else {
				return ERROR;
			}
		} else if(mode != null && mode.equals("delete")){
			if(userService.delUserByAId(user.getAutoId()) == 1){
				return "ok";
			} else {
				return ERROR;
			}
		} else {
			if(userService.addUser(user) == 1){
				return SUCCESS;
			} else {
				return ERROR;
			}
		}
	}
	
	//setter
	public void setUser(systemGisUser user){
		this.user = user;
	}
	
	public void setUserService(systemUserService userService){
		this.userService = userService;
	}
	
	public void setMode(String mode){
		this.mode = mode;
	}
	
	//getter
	public systemGisUser getUser(){
		return user;
	}
	
	public systemUserService getUserService(){
		return userService;
	}
	
	public String getMode(){
		return mode;
	}
}
