package org.resource.objectclass;

import java.util.Date;

public class systemGisUser {
	private int autoId;
	private String userName;
	private String userPassword;
	private Integer userLevel;
	private String userRealName;
	private String userEmail;
	private String userMobile;
	private Date userAddDate;
	
	public void setAutoId(int autoId){
		this.autoId = autoId;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}
	
	public void setUserLevel(Integer userLevel){
		this.userLevel = userLevel;
	}
	
	public void setUserRealName(String userRealName){
		this.userRealName = userRealName;
	}
	
	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}
	
	public void setUserMobile(String userMobile){
		this.userMobile = userMobile;
	}
	
	public void setUserAddDate(Date userAddDate){
		this.userAddDate = userAddDate;
	}
	
	public int getAutoId(){
		return autoId;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public String getUserPassword(){
		return userPassword;
	}
	
	public Integer getUserLevel(){
		return userLevel;
	}
	
	public String getUserRealName(){
		return userRealName;
	}
	
	public String getUserEmail(){
		return userEmail;
	}
	
	public String getUserMobile(){
		return userMobile;
	}
	
	public Date getUserAddDate(){
		return userAddDate;
	}
}
