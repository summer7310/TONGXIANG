package org.lyd.Hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.lyd.object.GeoObjectBase;
//import org.lyd.object.PdObjectBase;
import org.lyd.util.HibernateSessionFactory;
import org.resource.objectclass.GeoObjectBase;
import org.resource.objectclass.PdObjectBase;
import org.resource.objectclass.PdPipeProfile;

public class DeleteObject {
	private static Session s=null;
	private static Transaction tx=null;
	public int deleteObjectG(Long staticId){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			int deleteResult = s.createQuery("delete from GeoObjectBase where staticId=:staticId").setParameter("staticId", staticId).executeUpdate();
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
	public int deleteObjectP(Long staticId){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			int deleteResult = s.createQuery("delete from PdObjectBase where staticId=:staticId").setParameter("staticId", staticId).executeUpdate();
			
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
	public int deleteAuxi(int autoId){
		try{
			s = HibernateSessionFactory.currentSession();
			tx =s.beginTransaction();
			int deleteResult = s.createQuery("delete from PdObjectBase where autoId=:autoId").setParameter("autoId", autoId).executeUpdate();
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
	public int deleteHolePoleInfo(Long staticId){
		try{
			s = HibernateSessionFactory.currentSession();
			tx = s.beginTransaction();
			s.createQuery("update PdPipeHole set wirecableSegmentId=0 where staticId=:staticId").setParameter("staticId", staticId).executeUpdate();
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
	public int deleteProfile(Long staticId){
		try{
			s = HibernateSessionFactory.currentSession();
			tx = s.beginTransaction();
			List<PdPipeProfile> profile = s.createQuery("from PdPipeProfile where ppSegmentId=:staticId").setParameter("staticId", staticId).list();
			for(PdPipeProfile x:profile){
				s.delete(x);
			}
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
}
