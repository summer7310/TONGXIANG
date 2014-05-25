package org.ldw.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.resource.objectclass.systemGisUser;
import org.ldw.dao.systemUserDao;
import org.ldw.service.MD5Helper;

import com.opensymphony.xwork2.ActionContext;

/*
 * 用户的添加
 * 修改
 * 删除
 */
public class systemUserService {
	private systemUserDao userDao;
	
	public int addUser(systemGisUser user){
		//密码加密
		user.setUserPassword(MD5Helper.MD5(user.getUserPassword()));
		//获取注册时间
		user.setUserAddDate(new Date());
		userDao.addUser(user);
		return 1;
	}
	
	/*
	 * 删除用户根据AID
	 */
	public int delUserByAId(int AId){
		systemGisUser u = userDao.getUserByAId(AId);
		return userDao.delUser(u);
	}
	
	/*
	 * 更新用户信息
	 */
	public int updateUser(systemGisUser user){
		return userDao.addUser(user);
	}
	
	/*
	 * 密码校验
	 */
	public boolean checkPsd(String psd){
		String psdmd5 = MD5Helper.MD5(psd);
		Map<String, Object> session = ActionContext.getContext().getSession();
		systemGisUser user = (systemGisUser)session.get("userInfo");
		if(psdmd5.equals(user.getUserPassword())){
			return true;
		} else {
			return false;
		}
		
	}
	
	/*
	 * 修改用户密码
	 * 返回-1	：原始密码错误
	 * 返回0		：修改错误
	 * 返回1		：修改成功
	 */
	public int editPsd(String oldPsd, String newPsd){
		Map<String, Object> session = ActionContext.getContext().getSession();
		systemGisUser user = (systemGisUser)session.get("userInfo");
		if(!checkPsd(oldPsd)){
			return -1;
		} else {
			user.setUserPassword(MD5Helper.MD5(newPsd));
			if(userDao.addUser(user) == 1){
				return 1;
			} else {
				return 0;
			}
		}
			
	}
	
	/*
	 * 获取用户列表
	 */
	public List<systemGisUser> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	/*
	 * 通过ID获取账户信息
	 */
	public systemGisUser getUserByAId(int AId){
		return userDao.getUserByAId(AId);
	}
	
	
	//setter
	public void setUserDao(systemUserDao userDao){
		this.userDao = userDao;
	}
	
	//getter
	public systemUserDao getUserDao(){
		return userDao;
	}
}
