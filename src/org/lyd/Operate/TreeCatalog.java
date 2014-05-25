package org.lyd.Operate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.lyd.util.HibernateSessionFactory;
import org.resource.objectclass.PdCity;
import org.resource.objectclass.PdElCnBranchBox;
import org.resource.objectclass.PdElCnRingmainunit;
import org.resource.objectclass.PdElCnSwitchStation;
import org.resource.objectclass.PdElCnTransformer;
import org.resource.objectclass.PdElCnTransformersubstation;
import org.resource.objectclass.PdElLnCableline;
import org.resource.objectclass.PdElLnIntermission;
import org.resource.objectclass.PdElLnWirecableSegmengV;
import org.resource.objectclass.PdEwPipeLineV;
import org.resource.objectclass.PdEwPpSegmentV;
import org.resource.objectclass.PdEwPtTower;
import org.resource.objectclass.PdEwPtWell;
import org.resource.objectclass.PdEwRoad;

public class TreeCatalog {
	private static Session s;
	private static Transaction tx;
	public List<TreeCatalogRecord> getTreeCatalog(){
		List<TreeCatalogRecord> listTreeRecord = new ArrayList<TreeCatalogRecord>();
		try{
			s = HibernateSessionFactory.currentSession();
			tx = s.beginTransaction();
			Query query = s.createQuery("from PdCity");
			List<PdCity> list = query.list();
			for(PdCity x:list){
				TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
				treeCatalogRecord.setStaticId(x.getStaticId());
				treeCatalogRecord.setTypeId(x.getObjectType());
				treeCatalogRecord.setName(x.getName());
				treeCatalogRecord.setParent(true);
				treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
				listTreeRecord.add(treeCatalogRecord);
				//treeCatalogRecord = null;
			}
		//listTreeRecord.add(new TreeCatalogRecord(0l,"土建"));
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
			
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return listTreeRecord;
		
	}
	public List<TreeCatalogRecord> getTreeCatalog(long staticId,int type){
		List<TreeCatalogRecord> listTreeRecord = new ArrayList<TreeCatalogRecord>();
		try{
			s = HibernateSessionFactory.currentSession();
			tx = s.beginTransaction();
			switch(type){
			//城区
			case 1030101:{
				Query query = s.createQuery("from PdElCnTransformersubstation where belongs_city=:staticId");
				query.setParameter("staticId", staticId);
				List<PdElCnTransformersubstation> list = query.list();
				for(PdElCnTransformersubstation x:list){
					TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					treeCatalogRecord.setStaticId(x.getStaticId());
					treeCatalogRecord.setTypeId(x.getObjectType());
					treeCatalogRecord.setName(x.getName());
					treeCatalogRecord.setParent(true);
					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord);
				}
				listTreeRecord.add(new TreeCatalogRecord(0l,"土建",10200000,true,"tree_"+10200000));
				break;
			}
			//变电站
			case 1010201:{
				Query query = s.createQuery("from PdElLnCableline where start_trans_station=:staticId");
				query.setParameter("staticId", staticId);
				List<PdElLnCableline> list = query.list();
				for(PdElLnCableline x:list){
					TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					treeCatalogRecord.setStaticId(x.getStaticId());
					treeCatalogRecord.setTypeId(x.getObjectType());
					treeCatalogRecord.setName(x.getName());
					treeCatalogRecord.setParent(true);
					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord);
				}
				break;
			}
			//配电线路
			case 1010301:{
				//Query query = s.createQuery("select distinct base.objectType from PdElLnWirecableSegmengV wirecable,PdObjectBase base where (base.staticId=wirecable.startContainer or base.staticId=wirecable.endContainer)and wirecable.belongsCableline=:staticId order by base.objectType asc").setParameter("staticId", staticId);
				//List<Integer> containerType = query.list();
				
				listTreeRecord.add(new TreeCatalogRecord(staticId, "标准段", 10104010, true, "tree_"+10104010));
				//for(Integer x:containerType){
					//switch(x){
					//case 1020102:{
				listTreeRecord.add(new TreeCatalogRecord(staticId,"杆塔",10201020,true,"tree_"+10201020));
						//break;
					//}
					//case 1010202:{
				listTreeRecord.add(new TreeCatalogRecord(staticId, "开关站", 10102020, true, "tree_"+10102020));
						//break;
					//}
					//case 1010203:{
				listTreeRecord.add(new TreeCatalogRecord(staticId, "变压器", 10102030, true, "tree_"+10102030));
						//break;
						//listTreeRecord.add(new TreeCatalogRecord(staticId, "开关站", 10102020, true, "tree_"+10102020));
					//}
					//case 1010204:{
				listTreeRecord.add(new TreeCatalogRecord(staticId, "环网柜", 10102040, true, "tree_"+10102040));
						//break;
					//}
					//case 1010205:{
				listTreeRecord.add(new TreeCatalogRecord(staticId, "分支箱", 10102050, true, "tree_"+10102050));
						//break;
					//}
					//}
				//}
				
				
				
				
				
				break;
			}
			//线缆段组织节点
			case 10104010:{
				Query query = s.createQuery("from PdElLnWirecableSegmengV where belongs_cableline=:staticId");
				query.setParameter("staticId", staticId);
				List<PdElLnWirecableSegmengV> list = query.list();
				for(PdElLnWirecableSegmengV x:list){
					TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					treeCatalogRecord.setStaticId(x.getStaticId());
					treeCatalogRecord.setTypeId(x.getObjectType());
					treeCatalogRecord.setName(x.getName());
					treeCatalogRecord.setParent(false);
					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord);
				}
				break;
			}
//			case 1010401:{
//				Query query = s.createQuery("from PdElLnWirecableSegmengV where belongs_cableline=:staticId");
//				query.setParameter("staticId", staticId);
//				List<PdElLnWirecableSegmengV> list = query.list();
//				for(PdElLnWirecableSegmengV x:list){
//					treeCatalogRecord.setStaticId(x.getStaticId());
//					treeCatalogRecord.setTypeId(x.getObjectType());
//					treeCatalogRecord.setName(x.getName());
//					treeCatalogRecord.setParent(true);
//					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
//					listTreeRecord.add(treeCatalogRecord);
//				}
//			}
			//开关站组织节点
			case 10102020:{
				Query query = s.createQuery("select distinct switchStation from PdElLnWirecableSegmengV wirecable,PdElCnSwitchStation switchStation where ((wirecable.startContainer=switchStation.staticId)or(wirecable.endContainer=switchStation.staticId))and wirecable.belongsCableline=:staticId").setParameter("staticId", staticId);
				//query.setParameter("staticId", staticId);
				List<PdElCnSwitchStation> list = query.list();
				for(PdElCnSwitchStation x:list){
					TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					treeCatalogRecord.setStaticId(x.getStaticId());
					treeCatalogRecord.setTypeId(x.getObjectType());
					treeCatalogRecord.setName(x.getName());
					treeCatalogRecord.setParent(true);
					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord);
				}
				break;
			}
			//开关站实例节点 下级为间隔
			case 1010202:{
				Query query = s.createQuery("from PdElLnIntermission where container_id=:staticId");
				query.setParameter("staticId", staticId);
				List<PdElLnIntermission> list = query.list();
				for(PdElLnIntermission x:list){
					TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					treeCatalogRecord.setStaticId(x.getStaticId());
					treeCatalogRecord.setTypeId(x.getObjectType());
					treeCatalogRecord.setName(x.getName());
					treeCatalogRecord.setParent(false);
					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord);
				}
				break;
			}
			//分支箱组织节点 下级为分支箱实例节点
			case 10102050:{
				//Query query = s.createQuery("from PdElCnBranchBox where belongs_cableline=:staticId");
				Query query = s.createQuery("select distinct branchBox from PdElLnWirecableSegmengV wirecable,PdElCnBranchBox branchBox where ((wirecable.startContainer=branchBox.staticId)or(wirecable.endContainer=branchBox.staticId))and wirecable.belongsCableline=:staticId").setParameter("staticId", staticId);
				//query.setParameter("staticId", staticId);
				List<PdElCnBranchBox> list = query.list();
				for(PdElCnBranchBox x:list){
					TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					treeCatalogRecord.setStaticId(x.getStaticId());
					treeCatalogRecord.setTypeId(x.getObjectType());
					treeCatalogRecord.setName(x.getName());
					treeCatalogRecord.setParent(true);
					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord);
				}
				break;
			}
			//分支箱实例节点 下级为分支箱下的间隔
			case 1010205:{
				Query query = s.createQuery("from PdElLnIntermission where container_id=:staticId");
				query.setParameter("staticId", staticId);
				List<PdElLnIntermission> list = query.list();
				for(PdElLnIntermission x:list){
					TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					treeCatalogRecord.setStaticId(x.getStaticId());
					treeCatalogRecord.setTypeId(x.getObjectType());
					treeCatalogRecord.setName(x.getName());
					treeCatalogRecord.setParent(false);
					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord);
				}
				break;
			}
			//环网柜组织节点 下级为环网柜实例节点
			case 10102040:{
				//Query query = s.createQuery("from PdElCnRingmainunit where belongs_cableline=:staticId");
				Query query = s.createQuery("select distinct ringmainunit from PdElLnWirecableSegmengV wirecable,PdElCnRingmainunit ringmainunit where ((wirecable.startContainer=ringmainunit.staticId)or(wirecable.endContainer=ringmainunit.staticId))and wirecable.belongsCableline=:staticId").setParameter("staticId", staticId);
				//query.setParameter("staticId", staticId);
				List<PdElCnRingmainunit> list = query.list();
				for(PdElCnRingmainunit x:list){
					TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					treeCatalogRecord.setStaticId(x.getStaticId());
					treeCatalogRecord.setTypeId(x.getObjectType());
					treeCatalogRecord.setName(x.getName());
					treeCatalogRecord.setParent(true);
					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord);
				}
				break;
			}
			//环网柜实例节点 下级为间隔
			case 1010204:{
				Query query = s.createQuery("from PdElLnIntermission where container_id=:staticId");
				query.setParameter("staticId", staticId);
				List<PdElLnIntermission> list = query.list();
				for(PdElLnIntermission x:list){
					TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					treeCatalogRecord.setStaticId(x.getStaticId());
					treeCatalogRecord.setTypeId(x.getObjectType());
					treeCatalogRecord.setName(x.getName());
					treeCatalogRecord.setParent(false);
					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord);
				}
				break;
			}
			//变压器组织节点
			case 10102030:{
				//Query query = s.createQuery("from PdElCnTransformer where belongs_cableline=:staticId");
				Query query = s.createQuery("select distinct transformer from PdElLnWirecableSegmengV wirecable,PdElCnTransformer transformer where ((wirecable.startContainer=transformer.staticId)or(wirecable.endContainer=transformer.staticId))and wirecable.belongsCableline=:staticId").setParameter("staticId", staticId);
				//query.setParameter("staticId", staticId);
				List<PdElCnTransformer> list = query.list();
				for(PdElCnTransformer x:list){
					TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					treeCatalogRecord.setStaticId(x.getStaticId());
					treeCatalogRecord.setTypeId(x.getObjectType());
					treeCatalogRecord.setName(x.getName());
					treeCatalogRecord.setParent(true);
					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord);
				}
				break;
			}
			//变压器实例节点
			case 1010203:{
				Query query = s.createQuery("from PdElLnIntermission where container_id=:staticId");
				query.setParameter("staticId", staticId);
				List<PdElLnIntermission> list = query.list();
				for(PdElLnIntermission x:list){
					TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					treeCatalogRecord.setStaticId(x.getStaticId());
					treeCatalogRecord.setTypeId(x.getObjectType());
					treeCatalogRecord.setName(x.getName());
					treeCatalogRecord.setParent(false);
					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord);
				}
				break;
			}
			//杆塔
			case 10201020:{
				//Query query = s.createQuery("from PdElCnTransformer where belongs_cableline=:staticId");
				Query query = s.createQuery("select distinct tower from PdElLnWirecableSegmengV wirecable,PdEwPtTower tower where ((wirecable.startContainer=tower.staticId)or(wirecable.endContainer=tower.staticId))and wirecable.belongsCableline=:staticId").setParameter("staticId", staticId);
				//query.setParameter("staticId", staticId);
				List<PdEwPtTower> list = query.list();
				for(PdEwPtTower x:list){
					TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					treeCatalogRecord.setStaticId(x.getStaticId());
					treeCatalogRecord.setTypeId(x.getObjectType());
					treeCatalogRecord.setName(x.getName());
					treeCatalogRecord.setParent(true);
					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord);
				}
				break;
			}
			//杆塔实例节点
			case 1020102:{
				Query query = s.createQuery("from PdElLnIntermission where container_id=:staticId");
				query.setParameter("staticId", staticId);
				List<PdElLnIntermission> list = query.list();
				for(PdElLnIntermission x:list){
					TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					treeCatalogRecord.setStaticId(x.getStaticId());
					treeCatalogRecord.setTypeId(x.getObjectType());
					treeCatalogRecord.setName(x.getName());
					treeCatalogRecord.setParent(false);
					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord);
				}
				break;
			}
			//土建节点
			case 10200000:{
				Query query = s.createQuery("from PdEwRoad");
				//query.setParameter("staticId", staticId);
				List<PdEwRoad> list = query.list();
			for(PdEwRoad x:list){
				TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
				treeCatalogRecord.setStaticId(x.getStaticId());
				treeCatalogRecord.setTypeId(x.getObjectType());
				treeCatalogRecord.setName(x.getName());
				treeCatalogRecord.setParent(true);
				treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
				listTreeRecord.add(treeCatalogRecord);
			}
			break;
			}
			//道路
			case 1020301:{
				Query query = s.createQuery("from PdEwPipeLineV where belongs_road=:staticId");
				query.setParameter("staticId", staticId);
				List<PdEwPipeLineV> list = query.list();
				for(PdEwPipeLineV x:list){
					TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					treeCatalogRecord.setStaticId(x.getStaticId());
					treeCatalogRecord.setTypeId(x.getObjectType());
					treeCatalogRecord.setName(x.getName());
					treeCatalogRecord.setParent(true);
					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord);
				}
				break;
			}
			//管沟线
			case 1020200:{

				Query queryPipeLine = s.createQuery("select distinct well from PdEwPtWell well,PdEwPpSegmentV seg where (well.staticId=seg.startBuilding or well.staticId=seg.endBuilding) and seg.lineBelongId=:staticId").setParameter("staticId", staticId);
				List<PdEwPtWell> wells = queryPipeLine.list();
				for(PdEwPtWell x:wells){
					//TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
					treeCatalogRecord.setStaticId(x.getStaticId());
					treeCatalogRecord.setTypeId(x.getObjectType());
					treeCatalogRecord.setName(x.getName());
					treeCatalogRecord.setParent(false);
					treeCatalogRecord.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord);
				}
//				TreeCatalogRecord treeCatalogRecord = new TreeCatalogRecord();
//				Query queryPipeLine = s.createQuery("from PdEwPipeLineV where static_id=:staticId");
//				queryPipeLine.setParameter("staticId", staticId);
//				PdEwPipeLineV pointBuildingLine = (PdEwPipeLineV)queryPipeLine.uniqueResult(); 
//				//PdEwPipeLineV pointBuildingLine = (PdEwPipeLineV)s.get(PdEwPipeLineV.class, staticId);
//				if(pointBuildingLine.getStartBuilding()!=null){
//					if(pointBuildingLine.getStartBuildingType() == 1020101||pointBuildingLine.getStartBuildingType() == 1020103){
//						Query queryBuilding = s.createQuery("from PdEwPtWell where static_id=:staticId");
//						queryBuilding.setParameter("staticId", pointBuildingLine.getStartBuilding());
//						PdEwPtWell e = (PdEwPtWell)queryBuilding.uniqueResult();
//						treeCatalogRecord.setStaticId(e.getStaticId());
//						treeCatalogRecord.setTypeId(e.getObjectType());
//						treeCatalogRecord.setName(e.getName());
//						treeCatalogRecord.setParent(false);
//						treeCatalogRecord.setIconSkin("tree_"+e.getObjectType());
//						listTreeRecord.add(treeCatalogRecord);
//					}else{
//						Query queryBuilding = s.createQuery("from PdEwPtTower where static_id=:staticId");
//						queryBuilding.setParameter("staticId", pointBuildingLine.getStartBuilding());
//						PdEwPtTower e = (PdEwPtTower)queryBuilding.uniqueResult();
//						treeCatalogRecord.setStaticId(e.getStaticId());
//						treeCatalogRecord.setTypeId(e.getObjectType());
//						treeCatalogRecord.setName(e.getName());
//						treeCatalogRecord.setParent(false);
//						treeCatalogRecord.setIconSkin("tree_"+e.getObjectType());
//						listTreeRecord.add(treeCatalogRecord);
//					}
//				}
//				if(pointBuildingLine.getEndBuilding()!=null){
//					
//			
//					if(pointBuildingLine.getEndBuildingType() == 1020101||pointBuildingLine.getEndBuildingType() == 1020103){
//						Query queryBuilding = s.createQuery("from PdEwPtWell where static_id=:staticId");
//						queryBuilding.setParameter("staticId", pointBuildingLine.getEndBuilding());
//						PdEwPtWell e = (PdEwPtWell)queryBuilding.uniqueResult();
//						treeCatalogRecord.setStaticId(e.getStaticId());
//						treeCatalogRecord.setTypeId(e.getObjectType());
//						treeCatalogRecord.setName(e.getName());
//						treeCatalogRecord.setParent(false);
//						treeCatalogRecord.setIconSkin("tree_"+e.getObjectType());
//						listTreeRecord.add(treeCatalogRecord);
//					}else{
//						Query queryBuilding = s.createQuery("from PdEwPtTower where static_id=:staticId");
//						queryBuilding.setParameter("staticId", pointBuildingLine.getEndBuilding());
//						PdEwPtTower e = (PdEwPtTower)queryBuilding.uniqueResult();
//						treeCatalogRecord.setStaticId(e.getStaticId());
//						treeCatalogRecord.setTypeId(e.getObjectType());
//						treeCatalogRecord.setName(e.getName());
//						treeCatalogRecord.setParent(false);
//						treeCatalogRecord.setIconSkin("tree_"+e.getObjectType());
//						listTreeRecord.add(treeCatalogRecord);
//					}
//				}
				Query query = s.createQuery("from PdEwPpSegmentV where line_belong_id=:staticId");
				query.setParameter("staticId", staticId);
				List<PdEwPpSegmentV> list = query.list();
				for(PdEwPpSegmentV x:list){
					TreeCatalogRecord treeCatalogRecord1 = new TreeCatalogRecord();
					treeCatalogRecord1.setStaticId(x.getStaticId());
					treeCatalogRecord1.setTypeId(x.getObjectType());
					treeCatalogRecord1.setName(x.getName());
					treeCatalogRecord1.setParent(false);
					treeCatalogRecord1.setIconSkin("tree_"+x.getObjectType());
					listTreeRecord.add(treeCatalogRecord1);
				}
				break;
				
			}
			}
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}

		return listTreeRecord;
	}
}
