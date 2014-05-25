package org.lyd.Hibernate;
//import hibernate.ch1.UserInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.lyd.object.*;
import org.resource.objectclass.*;
import org.lyd.util.HibernateSessionFactory;

public class SelectRecordAll {
	private static Session session;
	private static Transaction tx;
	//private Integer recordId;
//	public SelectRecordAll(){
//	}
//	public Integer getRecordId() {
//		return recordId;
//	}
//
//	public void setRecordId(Integer recordId) {
//		this.recordId = recordId;
//	}
//	public SelectRecordAll(Integer recordId){
//		setRecordId(recordId);
//	}
	//查询所有城区
	public List<PdCity> selectCity(){
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("from PdCity");
		List<PdCity> list = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return list;
	}
	//查询支架通过Static_id
	public PdPipeBracket selectBracketByStaticId(Long staticId){
		PdPipeBracket ui = new PdPipeBracket();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("from PdPipeBracket where staticId=:staticId");
		query.setParameter("staticId", staticId);
		List<PdPipeBracket> list = query.list();
		Iterator it = list.iterator();
		if(it.hasNext()){
			ui=(PdPipeBracket)it.next();
		}
		tx.commit();
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//查询城区下所有变电站
	public List<PdElCnTransformersubstation> selectTranssubstationBelongsCity(Long staticId){
		List<PdElCnTransformersubstation> transformerSubstationL = new ArrayList<PdElCnTransformersubstation>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("select transSub from PdCity city inner join city.pdElCnTransformersubstations transSub where city.staticId=:staticId");
		query.setParameter("staticId", staticId);
		//PdCity city = (PdCity)query.uniqueResult();
		//Set<PdElCnTransformersubstation> transformerSubstationS = city.getPdElCnTransformersubstations();
		//Iterator iterator = transformerSubstationS.iterator();
		//while(iterator.hasNext()){
		//	transformerSubstationL.add((PdElCnTransformersubstation)iterator.next());
		//}
		transformerSubstationL = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return transformerSubstationL;
	}
	
	//通过配电线路查询开关站
	public List<PdElCnSwitchStation> selectSwitchStationBelongsCableline(Long staticId){
		List<PdElCnSwitchStation> switchStation = new ArrayList<PdElCnSwitchStation>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("select distinct switchStation from PdElLnWirecableSegmengV wirecable,PdElCnSwitchStation switchStation where ((wirecable.startContainer=switchStation.staticId)or(wirecable.endContainer=switchStation.staticId))and wirecable.belongsCableline=:staticId").setParameter("staticId", staticId);
		//query.setParameter("staticId", staticId);
		switchStation = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return switchStation;
	}
	//通过配电线路查询电缆段
	public List<PdElLnWirecableSegmengV> selectWirecableByCablelineId(Long staticId){
		List<PdElLnWirecableSegmengV> wirecable = new ArrayList<PdElLnWirecableSegmengV>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("from PdElLnWirecableSegmengV where belongs_cableline=:staticId").setParameter("staticId", staticId);
		wirecable = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return wirecable;
	}
	
	//通过配电线路查询分支箱
	public List<PdElCnBranchBox> selectBranchBoxBelongsCableline(Long staticId){
		List<PdElCnBranchBox> branchBox = new ArrayList<PdElCnBranchBox>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("select distinct branchBox from PdElLnWirecableSegmengV wirecable,PdElCnBranchBox branchBox where ((wirecable.startContainer=branchBox.staticId)or(wirecable.endContainer=branchBox.staticId))and wirecable.belongsCableline=:staticId").setParameter("staticId", staticId);
		query.setParameter("staticId", staticId);
		branchBox = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return branchBox;
	}
	
	//通过配电线路查询环网柜
	public List<PdElCnRingmainunit> selectRingmainunitBelongsCableline(Long staticId){
		List<PdElCnRingmainunit> branchBox = new ArrayList<PdElCnRingmainunit>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		//Query query = session.createQuery("from PdElCnRingmainunit where belongs_cableline=:staticId");
		//query.setParameter("staticId", staticId);
		Query query = session.createQuery("select distinct ringmainunit from PdElLnWirecableSegmengV wirecable,PdElCnRingmainunit ringmainunit where ((wirecable.startContainer=ringmainunit.staticId)or(wirecable.endContainer=ringmainunit.staticId))and wirecable.belongsCableline=:staticId").setParameter("staticId", staticId);
		branchBox = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return branchBox;
	}
	
	//通过配电线路查询变压器
	public List<PdElCnTransformer> selectTransformerBelongsCableline(Long staticId){
		List<PdElCnTransformer> branchBox = new ArrayList<PdElCnTransformer>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		//Query query = session.createQuery("from PdElCnTransformer where belongs_cableline=:staticId");
		//query.setParameter("staticId", staticId);
		Query query = session.createQuery("select distinct transformer from PdElLnWirecableSegmengV wirecable,PdElCnTransformer transformer where ((wirecable.startContainer=transformer.staticId)or(wirecable.endContainer=transformer.staticId))and wirecable.belongsCableline=:staticId").setParameter("staticId", staticId);
		branchBox = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return branchBox;
	}
	
	//通过配电线路查询中间接头
	public List<PdElCnIntermediateJoint> selectIntermediateJointBelongsCableline(Long staticId){
		List<PdElCnIntermediateJoint> branchBox = new ArrayList<PdElCnIntermediateJoint>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("from PdElCnIntermediateJoint where belongs_cableline=:staticId");
		query.setParameter("staticId", staticId);
		branchBox = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return branchBox;
	}
	/*
	 * 通过配电线路查询起点电站
	 */
	public List<PdElCnTransformersubstation> selectTransformersubstationStartByCablelineId(Long staticId){
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("select substation from PdElLnCableline cableline,PdElCnTransformersubstation substation where cableline.startTransStation=substation.staticId and cableline.staticId=:staticId").setParameter("staticId", staticId);
		List<PdElCnTransformersubstation> substation = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return substation;
	}
	/*
	 * 通过配电线路查询杆塔
	 */
	public List<PdEwPtTower> selectTowerByCableline(Long staticId){
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		//Query query = session.createQuery("from PdEwPtTower where belongsCableline=:staticId").setParameter("staticId", staticId);
		Query query = session.createQuery("select distinct tower from PdElLnWirecableSegmengV wirecable,PdEwPtTower tower where ((wirecable.startContainer=tower.staticId)or(wirecable.endContainer=tower.staticId))and wirecable.belongsCableline=:staticId").setParameter("staticId", staticId);
		List<PdEwPtTower> tower = query.list();
		
		tx.commit();
		HibernateSessionFactory.closeSession();
		return tower;
	}
	
	/*
	 * 通过配电线路查询变电站
	 */
	public List<PdElCnTransformersubstation> selectTransformersubstationByCablelineId(Long staticId){
		session =HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("select distinct tower from PdElLnWirecableSegmengV wirecable,PdElCnTransformersubstation tower where ((wirecable.startContainer=tower.staticId)or(wirecable.endContainer=tower.staticId))and wirecable.belongsCableline=:staticId").setParameter("staticId", staticId);
		List<PdElCnTransformersubstation> tower = query.list();
		
		tx.commit();
		HibernateSessionFactory.closeSession();
		return tower;
	}
	//通过配电线路查询盘余
	public List<PdElCnCabledrumRemainder> selectCabledrunRemainderBelongsCableline(Long staticId){
		List<PdElCnCabledrumRemainder> branchBox = new ArrayList<PdElCnCabledrumRemainder>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("from PdElCnCabledrumRemainder where belongs_cableline=:staticId");
		query.setParameter("staticId", staticId);
		branchBox = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return branchBox;
	}
	//通过static_id查询盘余
	public PdElCnCabledrumRemainder selectCabledrunRemainder(Long staticId){
		PdElCnCabledrumRemainder ui = null;
		session = HibernateSessionFactory.currentSession();
		Query query = session.createQuery("from PdElCnCabledrumRemainder where static_id=:staticId");
		query.setParameter("staticId", staticId);
		List list = query.list();
		Iterator it = list.iterator();
		if(it.hasNext()){
			ui=(PdElCnCabledrumRemainder)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	
	//通过配电线路查询中间接头所在的工井
	public List<PdEwPointBuilding> selectPointBuildingBelongsCableline(Long staticId){
		List<PdEwPointBuilding> pointBuilding = new ArrayList<PdEwPointBuilding>();
		List<PdElCnIntermediateJoint> joint = new ArrayList<PdElCnIntermediateJoint>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		//Query queryType = session.createQuery("select joint.locationType from PdElCnIntermediateJoint joint where joint.belongsCableline=:staticId").setParameter("staticId", staticId);
		/*
		 * 工井查询完善
		 */
		
		Query queryJoint = session.createQuery("from PdElCnIntermediateJoint where belongsCableline=:staticId").setParameter("staticId", staticId);
		joint = queryJoint.list();
		if(!joint.isEmpty()){
			for(PdElCnIntermediateJoint x:joint){
				if((x.getLocationType()/100)==10201){
					Query queryBuilding = session.createQuery("select building from PdElCnIntermediateJoint joint,PdEwPointBuilding  building where joint.locationId=building.staticId and joint.staticId=:staticId").setParameter("staticId", x.getStaticId());
					pointBuilding.addAll(queryBuilding.list());
				}else{
					pointBuilding.addAll(session.createSQLQuery("SELECT BUILDING.* FROM (PROPERTY.PD_EL_CN_INTERMEDIATE_JOINT AS JOINT JOIN  PROPERTY.PD_EW_PP_SEGMENT_V AS SEGMENT ON JOINT.LOCATION_ID=SEGMENT.STATIC_ID)JOIN PROPERTY.PD_EW_POINT_BUILDING AS BUILDING ON (SEGMENT.START_BUILDING=BUILDING.STATIC_ID) OR (SEGMENT.END_BUILDING=BUILDING.STATIC_ID) WHERE JOINT.STATIC_ID=:staticId")
							.addEntity(PdEwPointBuilding.class)
							.setParameter("staticId", x.getStaticId())
							.list());
					
				}
			}
		}
		
		//Query queryWell = session.createQuery("select ")
//		SQLQuery querySQL =session.createSQLQuery("SELECT PROPERTY.PD_EW_POINT_BUILDING.* FROM PROPERTY.PD_EL_CN_INTERMEDIATE_JOINT JOIN PROPERTY.PD_EW_POINT_BUILDING ON PROPERTY.PD_EL_CN_INTERMEDIATE_JOINT.EW_HOST_ID=PROPERTY.PD_EW_POINT_BUILDING.STATIC_ID WHERE PROPERTY.PD_EL_CN_INTERMEDIATE_JOINT.BELONGS_CABLELINE=:staticId");
//		querySQL.addEntity(PdEwPointBuilding.class);
//		querySQL.setParameter("staticId", staticId);
		//Query query = session.createQuery("select building from PdElCnIntermediateJoint joint,PdEwPointBuilding  building where joint.locationId=building.staticId and joint.belongsCableline=:staticId");
		//query.setParameter("staticId", staticId);
		//pointBuilding = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return pointBuilding;
	}
	
//	//通过配电线路查询空间表中的点状建筑
//	public List<GeoElectrical> selectGeoBuildingBelongsCableline(Long staticId){
//		List<GeoElectrical> pointBuilding = new ArrayList<GeoElectrical>();
//		session = HibernateSessionFactory.currentSession();
//		tx = session.beginTransaction();
////		SQLQuery querySQL =session.createSQLQuery("SELECT PROPERTY.PD_EW_POINT_BUILDING.* FROM PROPERTY.PD_EL_CN_INTERMEDIATE_JOINT JOIN PROPERTY.PD_EW_POINT_BUILDING ON PROPERTY.PD_EL_CN_INTERMEDIATE_JOINT.EW_HOST_ID=PROPERTY.PD_EW_POINT_BUILDING.STATIC_ID WHERE PROPERTY.PD_EL_CN_INTERMEDIATE_JOINT.BELONGS_CABLELINE=:staticId");
////		querySQL.addEntity(PdEwPointBuilding.class);
////		querySQL.setParameter("staticId", staticId);
//		Query query = session.createQuery("select geo from GeoElectrical geo where geo.staticId=(select building.staticId from PdElCnIntermediateJoint joint,PdEwPointBuilding  building where joint.ewHostId=building.staticId and joint.belongsCableline=:staticId)");
//		query.setParameter("staticId", staticId);
//		pointBuilding = query.list();
//		tx.commit();
//		HibernateSessionFactory.closeSession();
//		return pointBuilding;
//	}	
	
	//通过配电线路查询配电线路上的所有设备
//	public List<PdElPtContainerV> selectContainerBelongsCableline(Long staticId){
//		List<PdElPtContainerV> container = new ArrayList<PdElPtContainerV>();
//		session = HibernateSessionFactory.currentSession();
//		tx =session.beginTransaction();
//		Query query = session.createQuery("");
//		query.setParameter("staticId", staticId);
//		container = query.list();
//		tx.commit();
//		HibernateSessionFactory.closeSession();
//		return container;
//	}
	//变电站
	public PdElCnTransformersubstation selectTransformerSubstationRec(Integer recordId){
		PdElCnTransformersubstation ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElCnTransformersubstation where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElCnTransformersubstation)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	
	//通过static_id查询变电站
	public PdElCnTransformersubstation selectTransSubRec(Long staticId){
		PdElCnTransformersubstation ui = null;
		session = HibernateSessionFactory.currentSession();
		Query query = session.createQuery("from PdElCnTransformersubstation where static_id=:staticId");
		query.setParameter("staticId", staticId);
		List list = query.list();
		Iterator it = list.iterator();
		if(it.hasNext()){
			ui=(PdElCnTransformersubstation)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//分支接头
	public PdElCnBranch selectBranchRec(Integer recordId){
		PdElCnBranch ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElCnBranch where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElCnBranch)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询分支接头
	public PdElCnBranch selectBranchRec(Long staticId){
		PdElCnBranch ui = null;
		session = HibernateSessionFactory.currentSession();
		Query query = session.createQuery("from PdElCnBranch where static_id=:staticId");
		query.setParameter("staticId", staticId);
		List list = query.list();
		Iterator it = list.iterator();
		if(it.hasNext()){
			ui=(PdElCnBranch)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询中间接头
	public PdElCnIntermediateJoint selectIntermediateJointRec(Long staticId){
		PdElCnIntermediateJoint ui = null;
		session = HibernateSessionFactory.currentSession();
		Query query = session.createQuery("from PdElCnIntermediateJoint where static_id=:staticId");
		query.setParameter("staticId", staticId);
		List list = query.list();
		Iterator it = list.iterator();
		if(it.hasNext()){
			ui=(PdElCnIntermediateJoint)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//分支箱
	public PdElCnBranchBox selectBranchBoxRec(Integer recordId){
		PdElCnBranchBox ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElCnBranchBox where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElCnBranchBox)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询分支箱
	public PdElCnBranchBox selectBranchBoxRec(Long staticId){
		PdElCnBranchBox ui = null;
		session = HibernateSessionFactory.currentSession();
		Query query = session.createQuery("from PdElCnBranchBox where static_id=:staticId");
		query.setParameter("staticId", staticId);
		List list = query.list();
		Iterator it = list.iterator();
		if(it.hasNext()){
			ui=(PdElCnBranchBox)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//环网柜
	public PdElCnRingmainunit selectRingmainunitRec(Integer recordId){
		PdElCnRingmainunit ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElCnRingmainunit where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElCnRingmainunit)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询环网柜
	public PdElCnRingmainunit selectRingmainunitRec(Long staticId){
		PdElCnRingmainunit ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElCnRingmainunit where static_id=:staticId");
		query.setParameter("staticId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElCnRingmainunit)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//开关
	public PdElCnSwitch selectSwitchRec(Integer recordId){
		PdElCnSwitch ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElCnSwitch where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElCnSwitch)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询开关
	public PdElCnSwitch selectSwitchRec(Long staticId){
		PdElCnSwitch ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElCnSwitch where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElCnSwitch)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//开关站
	public PdElCnSwitchStation selectSwitchStationRec(Integer recordId){
		PdElCnSwitchStation ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElCnSwitchStation where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElCnSwitchStation)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询开关站
	public PdElCnSwitchStation selectSwitchStationRec(Long staticId){
		PdElCnSwitchStation ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElCnSwitchStation where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElCnSwitchStation)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//电压器
	public PdElCnTransformer selectTransformerRec(Integer recordId){
		PdElCnTransformer ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElCnTransformer where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElCnTransformer)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询电压器
	public PdElCnTransformer selectTransformerRec(Long staticId){
		PdElCnTransformer ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElCnTransformer where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElCnTransformer)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//配电线路
	public PdElLnCableline selectCablelineRec(Integer recordId){
		PdElLnCableline ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElLnCableline where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElLnCableline)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	
	public List<PdElLnCableline> selectAllCablelineRec(){
		//PdElLnCableline ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElLnCableline");
		//query.setParameter("recordId", recordId);
		List<PdElLnCableline> list=query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}
	
	
	//通过static_id查询配电线路
	public PdElLnCableline selectCablelineRec(Long staticId){
		PdElLnCableline ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElLnCableline where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElLnCableline)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过名字查询配电线路
	public List<PdElLnCableline> selectCablelineListByName(String name){
		session  = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("from PdElLnCableline where name=:name").setParameter("name", name);
		List<PdElLnCableline> list = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return list;
	}
	//电缆段
	public PdElCnCableSegment selectCableSegmengVRec(Integer recordId){
		PdElCnCableSegment ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElCnCableSegment where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElCnCableSegment)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询电缆段
	public PdElCnCableSegment selectCableSegmengVRec(Long staticId){
		PdElCnCableSegment ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElCnCableSegment where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElCnCableSegment)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//间隔
	public PdElLnIntermission selectIntermissionRec(Integer recordId){
		PdElLnIntermission ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElLnIntermission where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElLnIntermission)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询间隔
	public PdElLnIntermission selectIntermissionRec(Long staticId){
		PdElLnIntermission ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElLnIntermission where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElLnIntermission)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	/*
	 * 查询间隔列表
	 */
	public List<PdElLnIntermission> selectAllIntermissionRec(){
		PdElLnIntermission ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElLnIntermission");
		List<PdElLnIntermission> list=query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}
	
	//通过static_id查询线缆段_V
	public PdElLnWirecableSegmengV selectWirecableSegmengVRec(Long staticId){
		PdElLnWirecableSegmengV ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElLnWirecableSegmengV where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElLnWirecableSegmengV)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//线缆段_V
	public PdElLnWirecableSegmengV selectWirecableSegmengVRec(Integer recordId){
		PdElLnWirecableSegmengV ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElLnWirecableSegmengV where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElLnWirecableSegmengV)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//导线段
	public PdElCnWireSegmet selectWireSegmetRec(Integer recordId){
		PdElCnWireSegmet ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElCnWireSegmet where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElCnWireSegmet)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询导线段
	public PdElCnWireSegmet selectWireSegmetRec(Long staticId){
		PdElCnWireSegmet ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElCnWireSegmet where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElCnWireSegmet)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//设备
	public PdElPtContainerV selectContainerVRec(Integer recordId){
		PdElPtContainerV ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElPtContainerV where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElPtContainerV)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询设备
	public PdElPtContainerV selectContainerVRec(Long staticId){
		PdElPtContainerV ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElPtContainerV where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdElPtContainerV)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//管沟线_V
	public PdEwPipeLineV selectPipeLineVRec(Integer recordId){
		PdEwPipeLineV ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPipeLineV where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPipeLineV)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	/*
	 * 查询所有管沟线
	 */
	public List<PdEwPipeLineV> selectAllPipeLineVRec(){
		//PdEwPipeLineV ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPipeLineV");
		List<PdEwPipeLineV> list=query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}
	
	
	//通过static_id查询管沟线_V
	public PdEwPipeLineV selectPipeLineVRec(Long staticId){
		PdEwPipeLineV ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPipeLineV where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPipeLineV)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//点状建筑
	public PdEwPointBuilding selectPointBuildingRec(Integer recordId){
		PdEwPointBuilding ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPointBuilding where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPointBuilding)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询点状建筑
	public PdEwPointBuilding selectPointBuildingRec(Long staticId){
		PdEwPointBuilding ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPointBuilding where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPointBuilding)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//桥架
	public PdEwPpBridge selectBridgeRec(Integer recordId){
		PdEwPpBridge ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpBridge where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpBridge)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询桥架
	public PdEwPpBridge selectBridgeRec(Long staticId){
		PdEwPpBridge ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpBridge where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpBridge)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//直埋
	public PdEwPpBuried selectBuriedRec(Integer recordId){
		PdEwPpBuried ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpBuried where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpBuried)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询直埋
	public PdEwPpBuried selectBuriedRec(Long staticId){
		PdEwPpBuried ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpBuried where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpBuried)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//电缆管沟_V
	public PdEwPpCablePipeV selectCablePipeVRec(Integer recordId){
		PdEwPpCablePipeV ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpCablePipeV where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpCablePipeV)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询电缆管沟_V
	public PdEwPpCablePipeV selectCablePipeVRec(Long staticId){
		PdEwPpCablePipeV ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpCablePipeV where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpCablePipeV)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//沟道
	public PdEwPpChannel selectChannelRec(Integer recordId){
		PdEwPpChannel ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpChannel where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpChannel)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询沟道
	public PdEwPpChannel selectChannelRec(Long staticId){
		PdEwPpChannel ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpChannel where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpChannel)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//顶管
	public PdEwPpJacking selectJackingRec(Integer recordId){
		PdEwPpJacking ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpJacking where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpJacking)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询顶管
	public PdEwPpJacking selectJackingRec(Long staticId){
		PdEwPpJacking ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpJacking where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpJacking)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//排管
	public PdEwPpRackPipe selectRackPipeRec(Integer recordId){
		PdEwPpRackPipe ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpRackPipe where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpRackPipe)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询排管
	public PdEwPpRackPipe selectRackPipeRec(Long staticId){
		PdEwPpRackPipe ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpRackPipe where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpRackPipe)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//虚拟管沟
	public PdEwPpVirtualPipe selectVirtualPipeRec(Integer recordId){
		PdEwPpVirtualPipe ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpVirtualPipe where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpVirtualPipe)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询虚拟管沟
	public PdEwPpVirtualPipe selectVirtualPipeRec(Long staticId){
		PdEwPpVirtualPipe ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpVirtualPipe where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpVirtualPipe)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//管沟段_V
	public PdEwPpSegmentV selectSegmentVRec(Integer recordId){
		PdEwPpSegmentV ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpSegmentV where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpSegmentV)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	/*
	 * 查询所有管沟段
	 */
	public List<PdEwPpSegmentV> selectAllSegmentVRec(){
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpSegmentV");
		List<PdEwPpSegmentV> list=query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}
	//通过static_id查询管沟段_V
	public PdEwPpSegmentV selectSegmentVRec(Long staticId){
		PdEwPpSegmentV ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpSegmentV where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpSegmentV)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//隧道
	public PdEwPpTunnel selectTunnelRec(Integer recordId){
		PdEwPpTunnel ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpTunnel where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpTunnel)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询隧道
	public PdEwPpTunnel selectTunnelRec(Long staticId){
		PdEwPpTunnel ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpTunnel where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpTunnel)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//剖面
	public PdPipeProfile selectProfile(Integer recordId){
		PdPipeProfile ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdPipeProfile where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdPipeProfile)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询剖面
	public PdPipeProfile selectProfile(Long staticId){
		PdPipeProfile ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdPipeProfile where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdPipeProfile)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//管孔
	public PdPipeHole selectPipeHole(Integer recordId){
		PdPipeHole ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdPipeHole where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdPipeHole)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id查询管孔
	public PdPipeHole selectPipeHoleRec(Long staticId){
		PdPipeHole ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdPipeHole where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdPipeHole)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	
	/*
	 * 差管沟段
	 */
	public PdEwPpSegmentV selectPipeSegmentRec(Long staticId){
		PdEwPpSegmentV ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpSegmentV where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPpSegmentV)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	/*
	 * 根据profileId查找管孔列表
	 */
	public List<PdPipeHole> selectPipeHolesRecByPId(Long profileId){
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdPipeHole where profile_id=:profileId order by pipe_code asc");
		query.setParameter("profileId", profileId);
		List<PdPipeHole> list=query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}
	
	/*
	 * 根据wellId查找管孔列表
	 */
	public List<PdPipeHole> selectPipeHolesRecByWId(Long wellId){
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdPipeHole where well_id=:wellId order by pipe_code asc");
		query.setParameter("wellId", wellId);
		List<PdPipeHole> list=query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}
	
	//杆塔
	public PdEwPtTower selectTowerRec(Integer recordId){
		PdEwPtTower ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPtTower where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPtTower)it.next();
		}
		HibernateSessionFactory.closeSession();			
		return ui;
	}
	


	//通过static_id查询杆塔
	public PdEwPtTower selectTowerRec(Long staticId){
		PdEwPtTower ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPtTower where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPtTower)it.next();
		}
		HibernateSessionFactory.closeSession();			
		return ui;
	}
	//工井
	public PdEwPtWell selectWellRec(Integer recordId){
		PdEwPtWell ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPtWell where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPtWell)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	/*
	 * 通过名字模糊查询部分
	 */
	
	/*
	 * 根据工井的名字模糊查询
	 */
	public List<PdEwPtWell> selectWellsByNameRec(String wellname){
		//List<PdEwPtWell> ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPtWell where name like:wellname");
		query.setParameter("wellname", "%"+wellname+"%");
		List<PdEwPtWell> list=query.list();
		
		HibernateSessionFactory.closeSession();
		return list;
	}
	
	/*
	 * 通过塔杆名字模糊查询
	 */
	public List<PdEwPtTower> selectTowersByNameRec(String towername){
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPtTower where name like:towername");
		query.setParameter("towername", "%"+towername+"%");
		List<PdEwPtTower> list=query.list();
		HibernateSessionFactory.closeSession();			
		return list;
	}
	
	/*
	 * 通过管沟段名字模糊查询
	 */
	public List<PdEwPpSegmentV> selectPipesByNameRec(String pipename){
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPpSegmentV where name like:pipename");
		query.setParameter("pipename", "%"+pipename+"%");
		List<PdEwPpSegmentV> list=query.list();
		HibernateSessionFactory.closeSession();			
		return list;
	}
	
	/*
	 * 通过名字模糊查询配电线路
	 */
	public List<PdElLnCableline> selectCablelineByNameRec(String cablielineName){
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElLnCableline where name like:cablelinename");
		query.setParameter("cableline", "%"+cablielineName+"%");
		List<PdElLnCableline> list=query.list();
		HibernateSessionFactory.closeSession();			
		return list;
	}
	
	/*
	 * 通过名字模糊查询线缆段
	 */
	public List<PdElLnWirecableSegmengV> selectWirecableSegByNameRec(String key){
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdElLnWirecableSegmengV where name like:key");
		query.setParameter("key", "%" + key + "%");
		List<PdElLnWirecableSegmengV> list=query.list();
		HibernateSessionFactory.closeSession();			
		return list;
	}
	
	/*
	 * 通过名字查询所有属性表
	 */
	public List<PdObjectBase> selectObjectBaseFuzzyByName(String objectBaseName){
		session = HibernateSessionFactory.currentSession();
		Query query = session.createQuery("from PdObjectBase where name like:objectBaseName");
		query.setParameter("objectBaseName", "%" + objectBaseName + "%");
		List<PdObjectBase> list = query.list();
		HibernateSessionFactory.closeSession();
		return list;
	}
	//通过static_id工井
	public PdEwPtWell selectWellRec(Long staticId){
		PdEwPtWell ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwPtWell where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwPtWell)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//道路
	public PdEwRoad selectRoadRec(Integer recordId){
		PdEwRoad ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwRoad where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwRoad)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id道路
	public PdEwRoad selectRoadRec(Long staticId){
		PdEwRoad ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdEwRoad where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdEwRoad)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//对象基础
	public PdObjectBase selectObjectBaseRec(Integer recordId){
		PdObjectBase ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdObjectBase where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdObjectBase)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过static_id对象基础
	public PdObjectBase selectObjectBaseRec(Long staticId){
		PdObjectBase ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdObjectBase where static_id=:recordId");
		query.setParameter("recordId", staticId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdObjectBase)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//对象类型
	public PdObjectType selectObjectTypeRec(Integer recordId){
		PdObjectType ui=null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdObjectType where auto_id=:recordId");
		query.setParameter("recordId", recordId);
		List list=query.list();
		Iterator it=list.iterator();
		if(it.hasNext()){
			ui=(PdObjectType)it.next();
		}
		HibernateSessionFactory.closeSession();
		return ui;
	}
	//通过名字查询属性数据库对象基础表
	public Integer selectObjectByName(String recordName){
		PdObjectBase ui = null;
		session=HibernateSessionFactory.currentSession();
		Query query=session.createQuery("from PdObjectBase where name=:recordName");
		query.setParameter("recordName" , recordName);
		List list =query.list();
		Iterator it = list.iterator();
		if(it.hasNext()){
			ui=(PdObjectBase)it.next();
		}
		return ui.getAutoId();
	}
	//通过名字查询属性表的对象基础表
	public List<PdObjectBase> selectObjectListByName(String name){
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("from PdObjectBase where name=:name").setParameter("name", name);
		List<PdObjectBase> list = query.list();
		
		tx.commit();
		HibernateSessionFactory.closeSession();
		return list;
		
	}
	
//	//通过城市查询变电站
//	public Set selectTransformersubsationByCity(){
//		
//	}
	//通过名字查询空间数据库对象基础表
	public GeoObjectBase SelectGeoObjectByNameF(String receiveName){
		GeoObjectBase temp=null;
		try{
			session=HibernateSessionFactory.currentSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("from GeoObjectBase where name=:nameReceive");
			query.setParameter("nameReceive", receiveName);
			List list=query.list();
			Iterator it=list.iterator();
			if(it.hasNext()){
				temp=(GeoObjectBase)it.next();
			}
			tx.commit();
			return temp;
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			return temp;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
	//通过管沟段查询线缆段
	public List<PdElLnWirecableSegmengV> selectWirecableByPipeSegment(Long staticId){
		//Integer autoId = this.selectSegmentVRec(staticId).getAutoId();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("from PdWirecablToPipeSegment where wirecable_belongs_segment=:autoId");
		query.setParameter("autoId", staticId);
		List<PdWirecablToPipeSegment> listWireToPipe = query.list();
		List<PdElLnWirecableSegmengV> listwire = new ArrayList<PdElLnWirecableSegmengV>();
		PdElLnWirecableSegmengV e = new PdElLnWirecableSegmengV();
		for(PdWirecablToPipeSegment x:listWireToPipe){
			Query queryWire = session.createQuery("from PdElLnWirecableSegmengV where static_id=:static_id");
			queryWire.setParameter("static_id", x.getId().getSegmentHaveWirecable());
			//List<PdElLnWirecableSegmengV> listWireTemp = queryWire.list();
			e = (PdElLnWirecableSegmengV)queryWire.uniqueResult();
			listwire.add(e);
			//listwire.add((PdElLnWirecableSegmengV)session.get(PdElLnWirecableSegmengV.class, x.getId().getSegmentHaveWirecable()));
		}
		//List<PdElLnWirecableSegmengV> list = query.list();
		tx.commit();
		return listwire;
	}
	//通过auto_id查询geo_electrical
	public GeoElectrical selectGeoElectricalByAutoId(Integer autoId){
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		GeoElectrical ui = (GeoElectrical)session.get(GeoElectrical.class, autoId);
		return ui;
	}
	//通过名字查询配电线路
	public PdElLnCableline selectCablelineByName(String name){
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query  query = session.createQuery("from PdElLnCableline where name=:name");
		query.setParameter("name", name);
		PdElLnCableline ui = (PdElLnCableline)query.uniqueResult();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return ui;
	}
	
//	//测试用
//	public List<GeoElectrical> selectTestByCablelineId(Long staticId){
//		List<GeoElectrical> geo = new ArrayList<GeoElectrical>();
//		session = HibernateSessionFactory.currentSession();
//		tx =session.beginTransaction();
////		Query query = session.createQuery("select building from PdElCnIntermediateJoint joint,PdEwPointBuilding  building where joint.ewHostId=building.staticId and joint.belongsCableline=:staticId");
//		
//		Query queryWirecables = session.createQuery("select geo from PdElLnWirecableSegmengV wirecable,GeoElectrical geo where wirecable.staticId=geo.staticId and wirecable.belongsCableline=:staticId");
//		Query queryContainters = session.createQuery("select geo from PdElPtContainerV container,GeoElectrical geo where container.staticId=geo.staticId and container.belongsCableline=:staticId");
//		//Query queryBuilding = session.createQuery("select geo from PdElCnIntermediateJoint joint,PdEwPointBuilding  building,GeoElectricable geo where joint.ewHostId=building.staticId and building.staticId=geo.staticId and joint.belongsCableline=:staticId");
//		queryWirecables.setParameter("staticId", staticId);
//		queryContainters.setParameter("staticId", staticId);
//		geo = queryWirecables.list();
//		geo.addAll(queryContainters.list());
//		tx.commit();
//		HibernateSessionFactory.closeSession();
//		return geo;
//	}
	/*
	 * 单线路显示的查询内容，其中包括查询该配电线路的空间表中的线缆段、电气设备、土建层管沟段、土建层点状建筑
	 * */
	//通过配电线路查询电气层空间表
	public List<GeoElectrical> selectGeoElectricalByCablelineId(Long staticId){
		List<GeoElectrical> geo = new ArrayList<GeoElectrical>();
		session = HibernateSessionFactory.currentSession();
		tx =session.beginTransaction();
//		Query query = session.createQuery("select building from PdElCnIntermediateJoint joint,PdEwPointBuilding  building where joint.ewHostId=building.staticId and joint.belongsCableline=:staticId");
		
		Query queryWirecables = session.createQuery("select geo from PdElLnWirecableSegmengV wirecable,GeoElectrical geo where wirecable.staticId=geo.staticId and wirecable.belongsCableline=:staticId");
		Query queryContainters = session.createQuery("select geo from PdElPtContainerV container,GeoElectrical geo, PdElLnWirecableSegmengV wirecable where container.staticId=geo.staticId and (container.staticId=wirecable.startContainer or container.staticId=wirecable.endContainer)and wirecable.belongsCableline=:staticId");
		Query queryTransstation = session.createQuery("select geo from GeoElectrical geo,PdElLnCableline cableline where cableline.startTransStation=geo.staticId and cableline.staticId=:staticId").setParameter("staticId", staticId);
		//Query queryBuilding = session.createQuery("select geo from GeoElectrical geo where geo.staticId=(select building.staticId from PdElCnIntermediateJoint joint,PdEwPointBuilding  building where joint.ewHostId=building.staticId and joint.belongsCableline=:staticId)");
		queryWirecables.setParameter("staticId", staticId);
		queryContainters.setParameter("staticId", staticId);
		//queryBuilding.setParameter("staticId", staticId);
		geo = queryWirecables.list();
		geo.addAll(queryContainters.list());
		geo.addAll(queryTransstation.list());
		//geo.addAll(queryBuilding.list());
		tx.commit();
		HibernateSessionFactory.closeSession();
		return geo;
	}
	//通过配电线路查询土建层管沟段空间表
	public List<GeoCivilEngineering> selectGeoCivilEngineeringByCablelineId(Long staticId){
		List<GeoCivilEngineering> geo = new ArrayList<GeoCivilEngineering>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		//Query querySegment = session.createQuery("select geo from GeoCivilEngineering geo where geo.staticId=(select segment.staticId from PdEwPpSegmentV segment,PdWirecablToPipeSegment wts where segment.staticId=wts.id.wirecableBelongsSegment and wts.id.segmentHaveWirecable=:staticId)");
		//querySegment.setParameter("staticId", staticId);
		//geo = querySegment.list();
		SQLQuery querySQL = session.createSQLQuery("SELECT ENGINEERING.* FROM (((GDBO.GEO_CIVIL_ENGINEERING AS ENGINEERING JOIN PROPERTY.PD_EW_PP_SEGMENT_V AS SEGMENT ON ENGINEERING.STATIC_ID=SEGMENT.STATIC_ID)JOIN PROPERTY.PD_WIRECABL_TO_PIPE_SEGMENT AS WTS ON SEGMENT.STATIC_ID=WTS.WIRECABLE_BELONGS_SEGMENT)JOIN PROPERTY.PD_EL_LN_WIRECABLE_SEGMENG_V AS WIRECABLE ON WIRECABLE.STATIC_ID=WTS.SEGMENT_HAVE_WIRECABLE) WHERE WIRECABLE.BELONGS_CABLELINE=:staticId");
		querySQL.addEntity(GeoCivilEngineering.class);
		querySQL.setParameter("staticId", staticId);
		geo = querySQL.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return geo;
	}
	
	//通过配电线路查询土建层点状建筑空间表
	public List<GeoCivilEngineering> selectGeoCivilEngineeringPointByCablelineId(Long staticId){
		List<GeoCivilEngineering> geo = new ArrayList<GeoCivilEngineering>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		SQLQuery querySQL = session.createSQLQuery("SELECT DISTINCT ENGINEERING.* FROM (((GDBO.GEO_CIVIL_ENGINEERING AS ENGINEERING JOIN PROPERTY.PD_EW_POINT_BUILDING AS BUILDING ON ENGINEERING.STATIC_ID=BUILDING.STATIC_ID)JOIN PROPERTY.PD_EW_PP_SEGMENT_V AS SEGMENT ON ((BUILDING.STATIC_ID=SEGMENT.START_BUILDING) OR (BUILDING.STATIC_ID=SEGMENT.END_BUILDING)))JOIN PROPERTY.PD_WIRECABL_TO_PIPE_SEGMENT AS WTS ON SEGMENT.STATIC_ID=WTS.WIRECABLE_BELONGS_SEGMENT)JOIN PROPERTY.PD_EL_LN_WIRECABLE_SEGMENG_V AS WIRECABLE ON WIRECABLE.STATIC_ID=WTS.SEGMENT_HAVE_WIRECABLE WHERE WIRECABLE.BELONGS_CABLELINE=:staticId");
		querySQL.addEntity(GeoCivilEngineering.class);
		querySQL.setParameter("staticId", staticId);
		geo = querySQL.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return geo;
	}
	
	//通过变电站查询线缆段
	public List<GeoElectrical> selectGeoWirecableByTransstationId(Long staticId){
		List<GeoElectrical> geo = new ArrayList<GeoElectrical>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		SQLQuery querySQL =session.createSQLQuery("SELECT GEOM.* FROM (PROPERTY.PD_EL_LN_WIRECABLE_SEGMENG_V AS WIRECABLE JOIN PROPERTY.PD_EL_LN_CABLELINE AS CABLELINE ON WIRECABLE.BELONGS_CABLELINE=CABLELINE.STATIC_ID) JOIN GDBO.GEO_ELECTRICAL GEOM ON WIRECABLE.STATIC_ID=GEOM.STATIC_ID WHERE CABLELINE.START_TRANS_STATION=:staticId");
		querySQL.addEntity(GeoElectrical.class);
		querySQL.setParameter("staticId", staticId);
		geo = querySQL.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return geo;
		
	}
	//通过剖面查询已穿管的线缆段
	public List<PdElLnWirecableSegmengV> selectWirecableFinishByProfileId(Long staticId){
		List<PdElLnWirecableSegmengV> wirecableFinish = new ArrayList<PdElLnWirecableSegmengV>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		SQLQuery querySQL = session.createSQLQuery("SELECT WIRECABLE.* FROM PROPERTY.PD_PIPE_HOLE AS HOLE JOIN PROPERTY.PD_EL_LN_WIRECABLE_SEGMENG_V AS WIRECABLE ON HOLE.WIRECABLE_SEGMENT_ID=WIRECABLE.STATIC_ID WHERE HOLE.PROFILE_ID=:staticId");
		querySQL.addEntity(PdElLnWirecableSegmengV.class);
		querySQL.setParameter("staticId", staticId);
		wirecableFinish = querySQL.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return wirecableFinish;
	}
	//通过剖面查询未穿管线缆段
	public List<PdElLnWirecableSegmengV> selectWirecableUnfinishByProfileId(Long staticId){
		//List<PdElLnWirecableSegmengV> wirecableFinish = selectWirecableFinishByProfileId(staticId);
		List<PdElLnWirecableSegmengV> wirecableUnfinish = new ArrayList<PdElLnWirecableSegmengV>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		//String strSQL = "";
		//SQLQuery querySQL = session.createSQLQuery("(SELECT WIRECABLE.* FROM ((PROPERTY.PD_EL_LN_WIRECABLE_SEGMENG_V AS WIRECABLE JOIN PROPERTY.PD_WIRECABL_TO_PIPE_SEGMENT AS WTP ON WIRECABLE.STATIC_ID=WTP.SEGMENT_HAVE_WIRECABLE)JOIN PROPERTY.PD_EW_PP_SEGMENT_V AS PIPE_SEGMENT ON PIPE_SEGMENT.STATIC_ID=WTP.WIRECABLE_BELONGS_SEGMENT)JOIN PROPERTY.PD_PIPE_PROFILE AS PROFILE ON PROFILE.PP_SEGMENT_ID=PIPE_SEGMENT.STATIC_ID WHERE PROFILE.STATIC_ID=:staticId1) EXCEPT (SELECT WIRECABLE.* FROM PROPERTY.PD_PIPE_HOLE AS HOLE JOIN PROPERTY.PD_EL_LN_WIRECABLE_SEGMENG_V AS WIRECABLE ON HOLE.WIRECABLE_SEGMENT_ID=WIRECABLE.STATIC_ID WHERE HOLE.PROFILE_ID=:staticId2)").addEntity("WIRECABLE", PdElLnWirecableSegmengV.class).setParameter("staticId1", staticId).setParameter("staticId2", staticId);
		
		//querySQL.addEntity(PdElLnWirecableSegmengV.class);
		//querySQL.setParameter("staticId1", staticId);
		//querySQL.setParameter("staticId2", staticId);
		wirecableUnfinish = session.createSQLQuery("SELECT WIRECABLE.* FROM ((PROPERTY.PD_EL_LN_WIRECABLE_SEGMENG_V AS WIRECABLE JOIN PROPERTY.PD_WIRECABL_TO_PIPE_SEGMENT AS WTP ON WIRECABLE.STATIC_ID=WTP.SEGMENT_HAVE_WIRECABLE)JOIN PROPERTY.PD_EW_PP_SEGMENT_V AS PIPE_SEGMENT ON PIPE_SEGMENT.STATIC_ID=WTP.WIRECABLE_BELONGS_SEGMENT)JOIN PROPERTY.PD_PIPE_PROFILE AS PROFILE ON PROFILE.PP_SEGMENT_ID=PIPE_SEGMENT.STATIC_ID WHERE PROFILE.STATIC_ID=:staticId1 AND WIRECABLE.STATIC_ID NOT IN (SELECT WIRECABLE.STATIC_ID FROM PROPERTY.PD_PIPE_HOLE AS HOLE JOIN PROPERTY.PD_EL_LN_WIRECABLE_SEGMENG_V AS WIRECABLE ON HOLE.WIRECABLE_SEGMENT_ID=WIRECABLE.STATIC_ID WHERE HOLE.PROFILE_ID=:staticId2)")
				.addEntity("WIRECABLE", PdElLnWirecableSegmengV.class)
				.setParameter("staticId1", staticId)
				.setParameter("staticId2", staticId)
				.list();
		//wirecableUnfinish.removeAll(wirecableFinish);
		tx.commit();
		HibernateSessionFactory.closeSession();
		return wirecableUnfinish;
	}
	//通过剖面查询已穿管的管孔
	public List<PdPipeHole> selectHoleByProfile(Long staticId){
		List<PdPipeHole> hole = new ArrayList<PdPipeHole>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		SQLQuery querySQL = session.createSQLQuery("SELECT Hole.* FROM PROPERTY.PD_PIPE_HOLE AS HOLE JOIN PROPERTY.PD_EL_LN_WIRECABLE_SEGMENG_V AS WIRECABLE ON HOLE.WIRECABLE_SEGMENT_ID=WIRECABLE.STATIC_ID WHERE HOLE.PROFILE_ID=:staticId");
		querySQL.addEntity(PdPipeHole.class);
		querySQL.setParameter("staticId", staticId);
		hole = querySQL.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return hole;
	}
	//查询管孔的穿管状态
	public List<PdElLnWirecableSegmengV> selectHoleStatus(Long staticId){
		List<PdElLnWirecableSegmengV> wirecable = new ArrayList<PdElLnWirecableSegmengV>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query  query = session.createQuery("select wirecable from PdElLnWirecableSegmengV wirecable,PdPipeHole hole where hole.wirecableSegmentId=wirecable.staticId and hole.staticId=:staticId").setParameter("staticId", staticId);
		wirecable = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return wirecable;
	}
	//查询剖面状态
	public List<PdPipeHole> selectProfileStatus(Long staticId){
		List<PdPipeHole> hole = new ArrayList<PdPipeHole>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("select hole from PdPipeProfile profile,PdPipeHole hole where profile.staticId=hole.profileId and profile.ppSegmentId=:staticId").setParameter("staticId", staticId);
		hole = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return hole;
	}
	//查询剖面状态
	public List<PdPipeBracket> selectProfileBracketStatus(Long staticId){
		List<PdPipeBracket> bracket = new ArrayList<PdPipeBracket>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("select bracket from PdPipeProfile profile,PdPipeBracket bracket where profile.wellId=bracket.wellId and profile.ppSegmentId=:staticId").setParameter("staticId", staticId);
		bracket = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return bracket;
		
	}
	//查询剖面状态
	public List<GeoCable> selectProfileWirecableStatus(Long staticId){
		List<GeoCable> wirecable = new ArrayList<GeoCable>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		//SQLQuery query = session.createSQLQuery("S");
		Query query = session.createQuery("from GeoCable cable where cable.ppSegmentId=:staticId").setParameter("staticId", staticId);
		wirecable = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return wirecable;
		
	}
	//通过设备查询间隔
	public List<PdElLnIntermission> selectIntermissionByContainerId(Long staticId){
		List<PdElLnIntermission> intermission = new ArrayList<PdElLnIntermission>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("from PdElLnIntermission where container_id=:staticId");
		query.setParameter("staticId", staticId);
		intermission = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return intermission;
	}
	//通过设备查询线缆段起点
	public List<PdElLnWirecableSegmengV> selectWirecableByContainerStartId(Long staticId){
		List<PdElLnWirecableSegmengV> wirecable = new ArrayList<PdElLnWirecableSegmengV>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("from PdElLnWirecableSegmengV wirecable where wirecable.startContainer=:staticId");
		query.setParameter("staticId", staticId);		
		//SQLQuery querySQL = session.createSQLQuery("SELECT WIRECABLE.* FROM PROPERTY.PD_EL_LN_INTERMISSION AS INTERMISSION JOIN PROPERTY.PD_EL_LN_WIRECABLE_SEGMENG_V AS WIRECABLE ON INTERMISSION.STATIC_ID=WIRECABLE.START_INTERMISSION WHERE INTERMISSION.CONTAINER_ID=:staticId");
		//querySQL.addEntity(PdElLnWirecableSegmengV.class);
		//querySQL.setParameter("staticId", staticId);
		wirecable = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return wirecable;
	}
	//通过设备查询线缆段终点
	public List<PdElLnWirecableSegmengV> selectWirecableByContainerEndId(Long staticId){
		List<PdElLnWirecableSegmengV> wirecable = new ArrayList<PdElLnWirecableSegmengV>();
		session = HibernateSessionFactory.currentSession();
		tx = session.beginTransaction();
		Query query = session.createQuery("from PdElLnWirecableSegmengV wirecable where wirecable.endContainer=:staticId");
		query.setParameter("staticId", staticId);
		//SQLQuery querySQL = session.createSQLQuery("SELECT WIRECABLE.* FROM PROPERTY.PD_EL_LN_INTERMISSION AS INTERMISSION JOIN PROPERTY.PD_EL_LN_WIRECABLE_SEGMENG_V AS WIRECABLE ON INTERMISSION.STATIC_ID=WIRECABLE.END_INTERMISSION WHERE INTERMISSION.CONTAINER_ID=:staticId");
		//querySQL.addEntity(PdElLnWirecableSegmengV.class);
		//querySQL.setParameter("staticId", staticId);
		wirecable = query.list();
		tx.commit();
		HibernateSessionFactory.closeSession();
		return wirecable;
	}
	//通过static_id查询所有的图片
	public List<PdPhoto> selectPhotoByHostId(Long staticId){
		List<PdPhoto> photos = new ArrayList<PdPhoto>();
		try{
			session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from PdPhoto where hostId=:staticId").setParameter("staticId", staticId);
			photos = query.list();
			tx.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			
		}finally{
			HibernateSessionFactory.closeSession();
			
		}
		return photos;
		
	}
	//通过auto_id查询图片
	public PdPhoto selectPhotoBuAutoId(int autoId){
		PdPhoto photo = new PdPhoto();
		try{
			session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			photo = (PdPhoto)session.get(PdPhoto.class, autoId);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return photo;
	}
	//通过static_id查询设备的附件
	public List<PdAuxiliary> selectAuxiliaryByHostId(Long staticId){
		List<PdAuxiliary> auxiliary = new ArrayList<PdAuxiliary>();
		try{
			session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from PdAuxiliary where hostId=:staticId").setParameter("staticId", staticId);
			auxiliary = query.list();
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return auxiliary;
	}
	//通过auto_id查询附件
	public PdAuxiliary selectAuxiliaryByAutoId(int autoId){
		PdAuxiliary auxiliary = new PdAuxiliary();
		try{
			session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			auxiliary = (PdAuxiliary)session.get(PdAuxiliary.class, autoId);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return auxiliary;
	}
	/*
	 * 通过工井查询空间表剖面
	 */
	public List<GeoPipeProfile> selectGeoProfileByWellId(Long staticId){
		List<GeoPipeProfile> geoPipeProfile = new ArrayList<GeoPipeProfile>();
		try{
			session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from GeoPipeProfile where wellId=:staticId").setParameter("staticId", staticId);
			geoPipeProfile = query.list();
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
			return geoPipeProfile;
		}
	}
	/*
	 * 通过STATICID查询空间表剖面
	 */
	public GeoPipeProfile selectGeoProfileBySId(Long staticId){
		GeoPipeProfile geoPipeProfile = new GeoPipeProfile();
		try{
			session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from GeoPipeProfile where staticId=:staticId").setParameter("staticId", staticId);
			List<GeoPipeProfile> list = query.list();
			Iterator it = list.iterator();
			if(it.hasNext()){
				geoPipeProfile=(GeoPipeProfile)it.next();
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
			return geoPipeProfile;
		}
	}
	
	/*
	 * 通过工井查询空间表管孔
	 */
	public List<GeoPipeHole> selectGeoPipeHoleByWellId(Long staticId){
		List<GeoPipeHole> geoPipeProfile = new ArrayList<GeoPipeHole>();
		try{
			session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from GeoPipeHole where wellId=:staticId").setParameter("staticId", staticId);
			geoPipeProfile = query.list();
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
			return geoPipeProfile;
		}
	}
	/*
	 * 通过工井查询空间表支架
	 */
	public List<GeoPipeBracket> selectGeoBracketByWellId(Long staticId){
		List<GeoPipeBracket> geoPipeProfile = new ArrayList<GeoPipeBracket>();
		try{
			session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from GeoPipeBracket where wellId=:staticId").setParameter("staticId", staticId);
			geoPipeProfile = query.list();
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
			return geoPipeProfile;
		}
	}
	/*
	 * 通过工井查询空间表电缆
	 */
	public List<GeoCable> selectGeoCableByWellId(Long staticId){
		List<GeoCable> geoPipeProfile = new ArrayList<GeoCable>();
		try{
			session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from GeoCable where wellId=:staticId").setParameter("staticId", staticId);
			geoPipeProfile = query.list();
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
			return geoPipeProfile;
		}
	}
	/*
	 * 查询空间表管沟线
	 */
	public List<GeoCivilEngineering> selectGeoSegmentByWellId(Long staticId){
		List<GeoCivilEngineering> geoPipeProfile = new ArrayList<GeoCivilEngineering>();
		try{
			session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("select geo from GeoCivilEngineering geo,PdEwPpSegmentV segment where geo.staticId=segment.staticId and (segment.startBuilding=:staticId or segment.endBuilding=:staticId)").setParameter("staticId", staticId);
			geoPipeProfile = query.list();
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
			return geoPipeProfile;
		}
	}
	/*
	 * 通过工井查询测绘层空间表
	 * @param staticId 工井的静态Id
	 * @exception 
	 * @return the <code>List</code> of the <code>GeoPointSurvey</code> selecting from db  or null when there is no result from the db. 
	 */
	public List<GeoPointSurvery> selectGeoPointSurveryByWellId(Long staticId){
		List<GeoPointSurvery> geoPipeProfile = new ArrayList<GeoPointSurvery>();
		try{
			session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from GeoPointSurvery where wellId=:staticId").setParameter("staticId", staticId);
			geoPipeProfile = query.list();
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
			return geoPipeProfile;
		}
	}
	/*
	 * 查询空间表对象
	 */
	public GeoObjectBase selectGeoObjectBaseByStaticId(Long staticId){
		GeoObjectBase geoObjectBase = new GeoObjectBase();
		try{
			session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from GeoObjectBase where staticId=:staticId").setParameter("staticId", staticId);
			geoObjectBase = (GeoObjectBase)query.uniqueResult();
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
			return geoObjectBase;
		}
	}
}