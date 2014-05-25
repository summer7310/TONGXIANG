package org.ldw.service;

import org.ldw.dao.systemUserDao;
import org.resource.objectclass.systemGisUser;
import org.ldw.service.MD5Helper;

/*
 * 用户登录服务
 */
public class loginService {
	private systemUserDao userDao;
	private systemGisUser userInfo;
	
	/*
	 * 校验账号
	 * 账号不存在返回0，
	 * 账号密码错误返回-1
	 * 账号正确返回1
	 */
	public int checkAccount(String name, String psd){
		userInfo = userDao.getUserByName(name);
		if(userInfo == null){
			return 0;
		}
		if(userInfo.getUserPassword().equals(MD5Helper.MD5(psd))){
			return 1;
		} 
		return -1;
	}
	
	
	
	//setter
	public void setUserDao(systemUserDao userDao){
		this.userDao = userDao;
	}
	
	public void setUserInfo(systemGisUser userInfo){
		this.userInfo = userInfo;
	}
	//getter
	public systemUserDao getUserDao(){
		return userDao;
	}
	
	public systemGisUser getUserInfo(){
		return userInfo;
	}
}
