package org.ldw.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.ldw.service.loginService;

/*
 * mode=login:跳转到登录界面
 * mode=check:用户登录检查
 */
public class loginAction extends ActionSupport {
	private String mode;
	private String name;
	private String password;
	private loginService loginService;
	//private int flagLogin;
	
	public String execute() throws Exception {
		if(mode.equals("login")){
			return mode;
		}
		
		//注销登录
		if(mode.equals("loginout")){
			Map session = ActionContext.getContext().getSession();
			session.remove("userInfo");
			return "loginout";
		}
		
		//登录检查
		if(mode.equals("check")){
			if(name.equals("")){
				addFieldError("name", "请填写用户名");
				return INPUT;
			}
			if(password.equals("")){
				addFieldError("password", "请填写密码");
				return INPUT;
			}
			
			int flagLogin = -1;
			flagLogin = loginService.checkAccount(name, password);
			
			switch(flagLogin){
			case 0:addFieldError("name", "用户名不存在");
			case -1:addFieldError("password", "密码错误");
			}
			if(hasErrors()){
				return INPUT;
			} else {
				Map session = ActionContext.getContext().getSession();
				session.put("userInfo", loginService.getUserInfo());
				return SUCCESS;
			}
		}
		return SUCCESS;
	}
	
	//setter
	public void setMode(String mode){
		this.mode = mode;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setLoginService(loginService loginService){
		this.loginService = loginService;
	}
	
	//getter
	public String getMode(){
		return mode;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPassword(){
		return password;
	}
	
	public loginService getLoginService(){
		return loginService;
	}
}
