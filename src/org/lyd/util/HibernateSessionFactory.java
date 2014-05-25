package org.lyd.util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
public class HibernateSessionFactory {
	//设置配置文件常量
	private static String CONFIG_FILE_LOCATION="/org/resource/hibernate.cfg.xml";
	//实例化一个Session
	private static final ThreadLocal threadLocal=new ThreadLocal();
	//线程安全的Session配置
	private static final Configuration cfg=new Configuration();
	private static org.hibernate.SessionFactory sessionFactory;//线程安全的SessionFactory
	
	//返回线程安全的Session实例，如果需要，lazy初始化
	//SessionFactory
	//@返回Session
	//@throws HibernateException异常
	public static Session currentSession() throws HibernateException{
		Session session=(Session)threadLocal.get();
		if(session==null||!session.isOpen()){
			if(sessionFactory==null){
				try{
					cfg.configure(CONFIG_FILE_LOCATION);
					sessionFactory=cfg.buildSessionFactory();
				}catch(Exception e){
					System.err.println("%%%Error Creating SessionFactory%%%");
					e.printStackTrace();
				}
			}
			session=(sessionFactory!=null)?sessionFactory.openSession():null;
		 	threadLocal.set(session);
		}
		return session;
	}
	//关闭单个Hibernate session实例
	//@throws HibernateException异常
	public static void closeSession() throws HibernateException{
		Session session=(Session)threadLocal.get();
		threadLocal.set(null);
		if(session!=null){
			session.close();
		}
	}
	private HibernateSessionFactory(){
		
	}
}
