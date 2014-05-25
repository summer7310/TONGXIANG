package org.ldw.dao;

import java.util.Iterator;
import java.util.List;

import org.resource.objectclass.systemGisUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.lyd.util.HibernateSessionFactory;

/*
 * 用户相关数据库操作
 */
public class systemUserDao {
	private static Session s=null;
	private static Transaction tx=null;
	
	/*
	 * 添加新用户
	 */
	public int addUser(systemGisUser user){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(user);
			tx.commit();
			return 1;
		} catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			return 0;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	/*
	 * 根据auto id删除用户
	 */
	public int delUser(systemGisUser user){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.delete(user);
			tx.commit();
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			return 0;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
	
	/*
	 * 根据账户名获取用户实体类
	 */
	public systemGisUser getUserByName(String name){
		systemGisUser user=null;
		s = HibernateSessionFactory.currentSession();
		Query query = s.createQuery("from systemGisUser where user_name=:name");
		query.setParameter("name", name);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			user=(systemGisUser)it.next();
		}
		HibernateSessionFactory.closeSession();
		return user;
	}
	
	/*
	 * 根据账户ID获取用户实体类
	 */
	public systemGisUser getUserByAId(int AId){
		systemGisUser user=null;
		s = HibernateSessionFactory.currentSession();
		Query query = s.createQuery("from systemGisUser where auto_id=:AId");
		query.setParameter("AId", AId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			user=(systemGisUser)it.next();
		}
		HibernateSessionFactory.closeSession();
		return user;
	}
	
	/*
	 * 查询用户信息列表
	 */
	public List<systemGisUser> getAllUsers(){
		List<systemGisUser> users=null;
		s = HibernateSessionFactory.currentSession();
		Query query = s.createQuery("from systemGisUser");
		//query.setParameter("name", name);
		users = query.list();
		/*
		Iterator it=list.iterator();
		if(it.hasNext()){
			user=(systemGisUser)it.next();
		}*/
		HibernateSessionFactory.closeSession();
		return users;
	}
}
