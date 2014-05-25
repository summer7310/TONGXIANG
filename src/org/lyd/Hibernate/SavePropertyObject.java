package org.lyd.Hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.lyd.util.HibernateSessionFactory;
//import org.lyd.object.*;
import org.resource.objectclass.*;
public class SavePropertyObject {
	private static Session s=null;
	private static Transaction tx=null;
	//变压器
	public int SaveObjectAchieve(PdElCnTransformer receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			//s.save(receive);
			s.saveOrUpdate(receive);
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
	//开关站
	public int SaveObjectAchieve(PdElCnSwitchStation receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			//s.save(receive);
			s.saveOrUpdate(receive);
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
	
	//中间接头
	public int SaveObjectAchieve(PdElCnIntermediateJoint receive){
		try{
			s = HibernateSessionFactory.currentSession();
			tx = s.beginTransaction();
			s.saveOrUpdate(receive);
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
	
	//支架
	public int SaveObjectAchieve(PdPipeBracket receive){
		try{
			s = HibernateSessionFactory.currentSession();
			tx = s.beginTransaction();
			s.saveOrUpdate(receive);
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
	
	//盘余
	public int SaveObjectAchieve(PdElCnCabledrumRemainder receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			//s.save(receive);
			s.saveOrUpdate(receive);
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
	//分支接头
	public int SaveObjectAchieve(PdElCnBranch receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			//s.save(receive);
			s.saveOrUpdate(receive);
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
	//分支箱
	public int SaveObjectAchieve(PdElCnBranchBox receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			//s.save(receive);
			s.saveOrUpdate(receive);
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
	//开关
	public int SaveObjectAchieve(PdElCnSwitch receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			//s.save(receive);
			s.saveOrUpdate(receive);
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
	//变电站
	public int SaveObjectAchieve(PdElCnTransformersubstation receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			//s.save(receive);
			s.saveOrUpdate(receive);
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
	//变电站
	public int SaveObjectAchieve(PdElCnTransformersubstation receive,Long belongsCity){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			Query query = s.createQuery("from PdCity where static_id=:staticId");
			query.setParameter("staticId", belongsCity);
			PdCity receiveCity=(PdCity)query.uniqueResult();
			receive.setPdCity(receiveCity);
			//s.save(receive);
			s.saveOrUpdate(receive);
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
	//环网柜
	public int SaveObjectAchieve(PdElCnRingmainunit receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	//电缆段
	public int SaveObjectAchieve(PdElCnCableSegment receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	//导线段
	public int SaveObjectAchieve(PdElCnWireSegmet receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	//剖面
	public int SaveObjectAchieve(PdPipeProfile receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	//剖面属性表
	public int SaveObjectAchieve(PdPipeHole receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	//空间数据库 对象基础
	public int UpdateGeoObjectIsbn(GeoObjectBase rcGeoObject,Integer isbn){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			rcGeoObject.setIsbn(isbn);
			//rcGeoObject.setPdObjectBase(rcPdObject);
			//rcPdObject.setGeoObjectBase(rcGeoObject);
			//s.update(rcPdObject);
			s.saveOrUpdate(rcGeoObject);
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
	//工井
	public int SaveObjectAchieve(PdEwPtWell receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	//杆塔
	public int SaveObjectAchieve(PdEwPtTower receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	
	//线缆段
	public int SaveObjectAchieve(PdElLnCableline receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	 * 电缆沟
	 */
	public int SaveObjectAchieve(PdEwPpCablePipeV receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	 * 虚拟管沟
	 */
	public int SaveObjectAchieve(PdEwPpVirtualPipe receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	 * 排管
	 */
	public int SaveObjectAchieve(PdEwPpRackPipe receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	 * 桥架 
	 */
	public int SaveObjectAchieve(PdEwPpBridge receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	 * 沟道 
	 */
	public int SaveObjectAchieve(PdEwPpChannel receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	 * 直埋 
	 */
	public int SaveObjectAchieve(PdEwPpBuried receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	 * 隧道 
	 */
	public int SaveObjectAchieve(PdEwPpTunnel receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	 * 顶管 
	 */
	public int SaveObjectAchieve(PdEwPpJacking receive){
		try{
			s=HibernateSessionFactory.currentSession();
			tx=s.beginTransaction();
			s.saveOrUpdate(receive);
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
	
	//保存间隔
	public int SaveObjectAchieve(PdElLnIntermission receive){
		try{
			s = HibernateSessionFactory.currentSession();
			tx = s.beginTransaction();
			s.saveOrUpdate(receive);
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
	 * 保存道路
	 */
	public int SaveObjectAchieve(PdEwRoad receive){
		try{
			s = HibernateSessionFactory.currentSession();
			tx = s.beginTransaction();
			s.saveOrUpdate(receive);
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
	 * 保存入沟表
	 */
	public int SaveObjectAchieve(PdWirecablToPipeSegment receive){
		try{
			s = HibernateSessionFactory.currentSession();
			tx = s.beginTransaction();
			s.saveOrUpdate(receive);
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
	 * 保存线缆段
	 */
	public int SaveObjectAchieve(PdEwPipeLineV receive){
		try{
			s = HibernateSessionFactory.currentSession();
			tx = s.beginTransaction();
			s.saveOrUpdate(receive);
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
	//更新管孔
	public int updatePipeHole(Long staticId,Long wireCableSegmentId){
		try{
			PdPipeHole pipeHole = new SelectRecordAll().selectPipeHoleRec(staticId);
			pipeHole.setWirecableSegmentId(wireCableSegmentId);
			s = HibernateSessionFactory.currentSession();
			tx = s.beginTransaction();
			s.update(pipeHole);
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
	//保存图片
	public int SaveObjectAchieve(PdPhoto receive){
		try{
			s = HibernateSessionFactory.currentSession();
			tx = s.beginTransaction();
			s.save(receive);
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
	//保存附件
	public int SaveObjectAchieve(PdAuxiliary receive){
		try{
			s = HibernateSessionFactory.currentSession();
			tx = s.beginTransaction();
			s.save(receive);
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
