package org.ldw.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.lyd.Hibernate.SelectRecordAll;
import org.resource.objectclass.*;
//import org.lyd.saveObject.SavePropertyObject;
//import org.lyd.Operate.SaveOrUpdateObject;
import org.lyd.Hibernate.SavePropertyObject;

import com.vividsolutions.jts.geom.Geometry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/*
 * 要素属性服务
 * 1.通用设备属性查询功能
 * 2.部分从属关系字段为数字时替换文字显示功能
 * 3.前台添加属性时根据用户输入字符实时后台查询完整匹配数据部分
 * 4.某线路下设备土建查询
 * 5.剖面（所属及包含管孔信息）属性查询功能部分
 * 6.接线图包含的设备（间隔和线缆）查询
 * 7.（批量）属性保存
 */
public class propertyService {
	
	private SelectRecordAll daoSelect;// = new SelectRecordAll();
	private SavePropertyObject daoSave;
	private JsonConfig jsonConfig = new JsonConfig();
	
	
	/*******************************************
	 * 通用设备属性查询功能
	 * 查询字段包括
	 * 1.静态ID
	 * 2.名字
	 ********************************************/
	/*
	 * 根据static_id查询要素属性，并且返回jSON数据
	 */
	public JSONObject getPropertyJson(int type, Long staticId){
		
		JSONObject result;
		jsonConfig.registerJsonValueProcessor(Date.class , new jsonDateHelper());
		if(type == 1010201){
			jsonConfig.registerJsonValueProcessor(PdCity.class , new jsonClassHelper());
		}
		switch(type){
			//变压器
			case 1010203:result = JSONObject.fromObject(daoSelect.selectTransformerRec(staticId), jsonConfig);break;
			//杆塔
			case 1020102:result = JSONObject.fromObject(daoSelect.selectTowerRec(staticId), jsonConfig);break;
			//工井
			case 1020101:result = JSONObject.fromObject(daoSelect.selectWellRec(staticId), jsonConfig);break;
			//虚拟工井
			case 1020103:result = JSONObject.fromObject(daoSelect.selectWellRec(staticId), jsonConfig);break;
			//分支箱
			case 1010205:result = JSONObject.fromObject(daoSelect.selectBranchBoxRec(staticId), jsonConfig);break;
			//环网柜
			case 1010204:result = JSONObject.fromObject(daoSelect.selectRingmainunitRec(staticId), jsonConfig);break;
			//开关站
			case 1010202:result = JSONObject.fromObject(daoSelect.selectSwitchStationRec(staticId), jsonConfig);break;
			//变电站
			case 1010201:result = JSONObject.fromObject(daoSelect.selectTransSubRec(staticId), jsonConfig);break;
			//剖面
			case 1010501:result = JSONObject.fromObject(daoSelect.selectProfile(staticId), jsonConfig);break;
			//管孔
			case 1010502:result = JSONObject.fromObject(daoSelect.selectPipeHoleRec(staticId), jsonConfig);break;
			//电缆段
			case 1010402:result = JSONObject.fromObject(daoSelect.selectCableSegmengVRec(staticId), jsonConfig);break;
			//导线段
			case 1010403:result = JSONObject.fromObject(daoSelect.selectWireSegmetRec(staticId), jsonConfig);break;
			//配电线路
			case 1010301:result = JSONObject.fromObject(daoSelect.selectCablelineRec(staticId), jsonConfig);break;
			case 1020202:result = JSONObject.fromObject(daoSelect.selectCablePipeVRec(staticId), jsonConfig);break;
			//虚拟管沟
			case 1020203:result = JSONObject.fromObject(daoSelect.selectVirtualPipeRec(staticId), jsonConfig);break;
			//排管
			case 1020211:result = JSONObject.fromObject(daoSelect.selectRackPipeRec(staticId), jsonConfig);break;
			//桥架
			case 1020212:result = JSONObject.fromObject(daoSelect.selectBridgeRec(staticId), jsonConfig);break;
			//沟道
			case 1020213:result = JSONObject.fromObject(daoSelect.selectChannelRec(staticId), jsonConfig);break;
			//直埋
			case 1020214:result = JSONObject.fromObject(daoSelect.selectBuriedRec(staticId), jsonConfig);break;
			//隧道
			case 1020215:result = JSONObject.fromObject(daoSelect.selectTunnelRec(staticId), jsonConfig);break;
			//顶管
			case 1020216:result = JSONObject.fromObject(daoSelect.selectJackingRec(staticId), jsonConfig);break;
			//间隔
			case 1010601:result = JSONObject.fromObject(daoSelect.selectIntermissionRec(staticId), jsonConfig);break;
			//道路
			case 1020301:result = JSONObject.fromObject(daoSelect.selectRoadRec(staticId), jsonConfig);break;
			//中间接头
			case 1010208:result = JSONObject.fromObject(daoSelect.selectIntermediateJointRec(staticId), jsonConfig);break;
			//分支接头
			case 1010206:result = JSONObject.fromObject(daoSelect.selectBranchRec(staticId), jsonConfig);break;
			//盘余
			case 1010207:result = JSONObject.fromObject(daoSelect.selectCabledrunRemainder(staticId), jsonConfig);break;
			//管沟线
			case 1020200:result = JSONObject.fromObject(daoSelect.selectPipeLineVRec(staticId), jsonConfig);break;
			//支架
			case 1010503:result = JSONObject.fromObject(daoSelect.selectBracketByStaticId(staticId), jsonConfig);break;
			default:result = new JSONObject();
		}
		return result;
	}
	
	/*
	 * 根据name查询要素属性，并且返回jSON数据
	 */
	public JSONObject getPropertyJson(int type, String name){
		
		JSONObject result;
		jsonConfig.registerJsonValueProcessor(Date.class , new jsonDateHelper());
		switch(type){
			//变压器
			case 1010203:result = JSONObject.fromObject(daoSelect.selectTransformerRec(daoSelect.selectObjectByName(name)), jsonConfig);break;
			//杆塔
			case 1020102:result = JSONObject.fromObject(daoSelect.selectTowerRec(daoSelect.selectObjectByName(name)), jsonConfig);break;
			//工井
			case 1020101:result = JSONObject.fromObject(daoSelect.selectWellRec(daoSelect.selectObjectByName(name)), jsonConfig);break;
			//虚拟工井
			case 1020103:result = JSONObject.fromObject(daoSelect.selectWellRec(daoSelect.selectObjectByName(name)), jsonConfig);break;
			//分支箱
			case 1010205:result = JSONObject.fromObject(daoSelect.selectBranchBoxRec(daoSelect.selectObjectByName(name)), jsonConfig);break;
			//环网柜
			case 1010204:result = JSONObject.fromObject(daoSelect.selectRingmainunitRec(daoSelect.selectObjectByName(name)), jsonConfig);break;
			//开关站
			case 1010202:result = JSONObject.fromObject(daoSelect.selectSwitchStationRec(daoSelect.selectObjectByName(name)), jsonConfig);break;
			//变电站
			case 1010201:result = JSONObject.fromObject(daoSelect.selectTransformerSubstationRec(daoSelect.selectObjectByName(name)), jsonConfig);break;
			//剖面
			case 1010501:result = JSONObject.fromObject(daoSelect.selectProfile(daoSelect.selectObjectByName(name)), jsonConfig);break;
			//管孔
			case 1010502:result = JSONObject.fromObject(daoSelect.selectPipeHole(daoSelect.selectObjectByName(name)), jsonConfig);break;
			default:result = new JSONObject();
		}
		return result;
	}
	
	/********************************************************
	 * 部分从属关系字段为数字时替换文字显示功能
	 * 内容包括：
	 * 1.所属线路（多设备）
	 * 2.起点/终点间隔（标准段）
	 * 3.所属道路（管沟段）
	 * 4.对应工井（剖面）
	 * 5.对应管沟段（剖面）
	 * 6.起点/终点设备（标准段）
	 * 7.标准段名称（中间接头）
	 * 8.所属管沟线名称（排管，桥架等）
	 * 9.所属设备（间隔）
	 * 10.起点电站（配电线路）
	 *******************************************************/
	/*
	 * 根据实体类对象的所属线路ID获取所属线路的名字用于前台显示
	 */
	public String getNameOfCableline(int type, JSONObject js){
		
		if(type == 1010202 || type == 1010201 || type == 1010203 || type == 1010204 || type == 1010205 || type == 1010206 || type == 1010207 || type == 1010402 || type == 1010403 || type == 1010208 || type == 1020102){
			//Integer belong = js.getInt();
			Long belong = js.getLong("belongsCableline");
			if(belong <= 0){
				return "";
			} else {
				return daoSelect.selectCablelineRec(belong).getName();
			}
		} else {
			return "";
		}
	}
	
	/*
	 * 根据实体类对象的间隔ID获取间隔名字用于前台显示
	 */
	public String getNameOfIntermission(int type, JSONObject js, String pram){
		//如果是电缆段和导线段
		if(type == 1010402 || type == 1010403){
			Long sid = js.getLong(pram);
			//Long end = js.getLong("endIntermission");
			if(sid <= 0){
				return "";
			} else {
				return daoSelect.selectIntermissionRec(sid).getName();
			}
		} else {
			return "";
		}
	}
	
	/*
	 * 根据实体类对象的道路ID获取道路名字用于前台显示
	 */
	public String getNameOfRoad(int type, JSONObject js){
		//如果是电缆段和导线段,工井，塔杆，虚拟工井
		if(type == 1020200 || type == 1020101 || type == 1020102 || type == 1020103){
			Long sid = js.getLong("belongsRoad");
			//Long end = js.getLong("endIntermission");
			if(sid <= 0){
				return "";
			} else {
				return daoSelect.selectRoadRec(sid).getName();
			}
		} else {
			return "";
		}
	}
	
	/*
	 * 根据剖面的工井ID获取工井名字用于前台显示
	 */
	public String getNameOfWell(int type, JSONObject js){
		//如果是剖面
		if(type == 1010501){
			Long sid = js.getLong("wellId");
			//Long end = js.getLong("endIntermission");
			
			if(sid <= 0){
				return "";
			} else {
				return daoSelect.selectWellRec(sid).getName();
			}
		} else {
			return "";
		}
	}
	
	//根据剖面的空间属性查找所属的工井名字
	public String getNameOfWell2(int type, Long wellSid){
		//如果是剖面
		if(type == 1010501){
			 
			if(wellSid <= 0 || wellSid == null){
				return "";
			} else {
				//long wellSid = daoSelect.selectGeoProfileBySId(sid).getWellId();
				return daoSelect.selectWellRec(wellSid).getName();
			}
		} else {
			return "";
		}
	}
	
	/*
	 * 根据剖面的管沟段ID获取管沟名字用于前台显示
	 */
	public String getNameOfPP(int type, JSONObject js){
		//如果是电缆段和导线段
		if(type == 1010501){
			Long sid = js.getLong("ppSegmentId");
			//Long end = js.getLong("endIntermission");
			if(sid <= 0){
				return "";
			} else {
				return daoSelect.selectPipeSegmentRec(sid).getName();
			}
		} else {
			return "";
		}
	}
	
	/*
	 * 管沟段显示开始终点土建
	 */
	public String getNameOfBuilding(int type, JSONObject js, String param){
		//如果是管沟线,虚拟管沟，排管，桥架，沟道,直埋,隧道,顶管
		if(type == 1020200 || type == 1020201 || type == 1020203 || type == 1020211 || type == 1020212 || type == 1020213 || type==1020214 || type==1020215 || type==1020216){
			//int objectType = js.getInt(param + "Type");
			Long sid = js.getLong(param);
			if(sid <= 0){
				return "";
			} else {
				return daoSelect.selectObjectBaseRec(sid).getName();
			}
			
			
			/*
			if(objectType == 1020101){
				return daoSelect.selectWellRec(sid).getName();
			} else if(objectType == 1020102){
				return daoSelect.selectTowerRec(sid).getName();
			} else {
				return "";
			}*/
		} else {
			return "";
		}
	}
	
	/*
	 * 管沟段显示所属管沟线
	 */
	public String getNameOfPipeline(int type, JSONObject js){
		//如果是 虚拟管沟，排管，桥架，沟道,直埋,隧道,顶管
		if(type == 1020201 || type == 1020203 || type == 1020211 || type == 1020212 || type == 1020213 || type==1020214 || type==1020215 || type==1020216){
			Long sid = js.getLong("lineBelongId");
			if(sid <= 0) return "";
			return daoSelect.selectObjectBaseRec(sid).getName();
			
			/*
			if(objectType == 1020101){
				return daoSelect.selectWellRec(sid).getName();
			} else if(objectType == 1020102){
				return daoSelect.selectTowerRec(sid).getName();
			} else {
				return "";
			}*/
		} else {
			return "";
		}
	}
	
	/*
	 * 间隔所属设备
	 */
	public String getNameOfElectrical(int type, JSONObject js, String param){
		if(type == 1010601){
			Long sid = js.getLong(param);
			if(sid <= 0) return "";
			return daoSelect.selectObjectBaseRec(sid).getName();
			
		} else {
			return "";
		}
	}
	
	/*
	 * 配电线路起点电站
	 */
	public String getNameOfStartTransStation(int type, JSONObject js, String param){
		if(type == 1010301){
			Long sid = js.getLong(param);
			if(sid <= 0) return "";
			return daoSelect.selectObjectBaseRec(sid).getName();
			
		} else {
			return "";
		}
	}
	
	/*
	 * 显示设备所在位置
	 */
	public String getNameOfLocateId(int type, JSONObject js, String param){
		//变电站、开关站、变压器、环网柜、分支箱、分支接头、中间接头、盘余
		if(type == 1010201 || type == 1010202 || type == 1010203 || type == 1010204 || type == 1010205 || type == 1010206 || type == 1010207 || type == 1010208){
			Long sid = js.getLong(param);	
			if(sid <= 0) return "";
			return daoSelect.selectObjectBaseRec(sid).getName();
			
		} else {
			return "";
		}
	}
	
	/*
	 * 根据实体类对象的设备ID获取间设备字用于前台显示
	 */
	public String getNameOfObj(int type, JSONObject js, String pram){
		//如果是标准段
		if(type == 1010402 || type == 1010403){
			Long sid = js.getLong(pram);
			//Long end = js.getLong("endIntermission");
			if(sid <= 0){
				return "";
			} else {
				return daoSelect.selectObjectBaseRec(sid).getName();
			}
		} else {
			return "";
		}
	}
	
	/*
	 * 查询标准段（电缆段和导线段）名字，用于中间接头属性显示
	 */
	public String getNameOfWirecable(int type, JSONObject js){
		if(type == 1010208){
			Long sid = js.getLong("elHostId");
			//Long end = js.getLong("endIntermission");
			if(sid <= 0){
				return "";
			} else {
				return daoSelect.selectObjectBaseRec(sid).getName();
			}
		} else {
			return "";
		}
	}
	
	/* *****************************************************************
	 * 前台添加属性时根据用户输入字符实时后台查询完整匹配数据部分
	 * 匹配内容：
	 * 1.所属线路（多设备）
	 * 2.间隔（标准段）
	 * 3.管沟线（管沟段）
	 * 4.土建，包括工井和塔杆（管沟线）
	 * 5.设备，添加标准段时
	 * 6.电缆段和导线段
	 * 7.道路(工井和塔杆)
	 * *****************************************************************/
	
	/*
	 * 查询所有线路列表
	 */
	public List<PdElLnCableline> getAllCableline(){
		return daoSelect.selectAllCablelineRec();
	}
	
	/*
	 * 前台填写所属线路的实时查询匹配
	 */
	public JSONArray getFitCableline(String key){
		JSONArray jsa = new JSONArray();
		List<PdElLnCableline> aim = getAllCableline(); 
		for(int i=0; i<aim.size(); ++i){
			if(aim.get(i).getName().startsWith(key)){
				JSONObject jso = new JSONObject();
				jso.accumulate("id", aim.get(i).getStaticId());
				jso.accumulate("label", aim.get(i).getName());
				jso.accumulate("value", aim.get(i).getName());
				//jso.accumulate("label", key);
				//jso.accumulate("value", key);
				jsa.add(jso);
			}
		}
		return jsa;
	}
	
	/*
	 * 查询所有间隔
	 */
	public List<PdElLnIntermission> getAllIntermission(){
		return daoSelect.selectAllIntermissionRec();
	}
	
	/*
	 * 前台填写起点终点间隔的实时查询匹配
	 */
	public JSONArray getFitIntermission(String key){
		JSONArray jsa = new JSONArray();
		List<PdElLnIntermission> aim = getAllIntermission(); 
		for(int i=0; i<aim.size(); ++i){
			if(aim.get(i).getName().startsWith(key)){
				JSONObject jso = new JSONObject();
				jso.accumulate("id", aim.get(i).getStaticId());
				jso.accumulate("label", aim.get(i).getName());
				jso.accumulate("value", aim.get(i).getName());
				jso.accumulate("father", aim.get(i).getContainerId());
				jsa.add(jso);
			}
		}
		return jsa;
	}
	
	/*
	 * 查询所有间隔
	 */
	public List<PdEwPipeLineV> getAllPipeline(){
		return daoSelect.selectAllPipeLineVRec();
	}
	
	/*
	 * 前台添加管沟填写的所属管沟线的实时查询匹配
	 */
	public JSONArray getFitPipeline(String key){
		JSONArray jsa = new JSONArray();
		List<PdEwPipeLineV> aim = getAllPipeline(); 
		for(int i=0; i<aim.size(); ++i){
			if(aim.get(i).getName().startsWith(key)){
				JSONObject jso = new JSONObject();
				jso.accumulate("id", aim.get(i).getStaticId());
				jso.accumulate("label", aim.get(i).getName());
				jso.accumulate("value", aim.get(i).getName());
				//jso.accumulate("father", aim.get(i).getContainerId());
				jsa.add(jso);
			}
		}
		return jsa;
	}

	/*
	 * 添加管沟线时起点终点土建查询匹配
	 */
	public JSONArray getFitBuilding(String key){
		JSONArray jsa = new JSONArray();
		List<PdEwPtWell> aim = daoSelect.selectWellsByNameRec(key); 
		for(int i=0; i<aim.size(); ++i){
			//if(aim.get(i).getName().startsWith(key)){
				JSONObject jso = new JSONObject();
				jso.accumulate("id", aim.get(i).getStaticId());
				jso.accumulate("label", aim.get(i).getName());
				jso.accumulate("value", aim.get(i).getName());
				jso.accumulate("type", aim.get(i).getObjectType());
				jsa.add(jso);
			//}
		}
		
		List<PdEwPtTower> aim2 = daoSelect.selectTowersByNameRec(key); 
		for(int i=0; i<aim2.size(); ++i){
			//if(aim.get(i).getName().startsWith(key)){
				JSONObject jso = new JSONObject();
				jso.accumulate("id", aim2.get(i).getStaticId());
				jso.accumulate("label", aim2.get(i).getName());
				jso.accumulate("value", aim2.get(i).getName());
				jso.accumulate("type", aim2.get(i).getObjectType());
				jsa.add(jso);
			//}
		}
		
		
		return jsa;
	}
	
	/*
	 * 添加中间接头时土建宿主查询匹配
	 */
	public JSONArray getFitBuildingHost(String key){
		JSONArray jsa = new JSONArray();
		List<PdEwPtWell> aim = daoSelect.selectWellsByNameRec(key); 
		for(int i=0; i<aim.size(); ++i){
			//if(aim.get(i).getName().startsWith(key)){
				JSONObject jso = new JSONObject();
				jso.accumulate("id", aim.get(i).getStaticId());
				jso.accumulate("label", aim.get(i).getName());
				jso.accumulate("value", aim.get(i).getName());
				jso.accumulate("type", aim.get(i).getObjectType());
				jsa.add(jso);
			//}
		}
		
		List<PdEwPtTower> aim2 = daoSelect.selectTowersByNameRec(key); 
		for(int i=0; i<aim2.size(); ++i){
			//if(aim.get(i).getName().startsWith(key)){
				JSONObject jso = new JSONObject();
				jso.accumulate("id", aim2.get(i).getStaticId());
				jso.accumulate("label", aim2.get(i).getName());
				jso.accumulate("value", aim2.get(i).getName());
				jso.accumulate("type", aim2.get(i).getObjectType());
				jsa.add(jso);
			//}
		}
		
		List<PdEwPpSegmentV> aim3 = daoSelect.selectPipesByNameRec(key); 
		for(int i=0; i<aim3.size(); ++i){
			//if(aim.get(i).getName().startsWith(key)){
				JSONObject jso = new JSONObject();
				jso.accumulate("id", aim3.get(i).getStaticId());
				jso.accumulate("label", aim3.get(i).getName());
				jso.accumulate("value", aim3.get(i).getName());
				jso.accumulate("type", aim3.get(i).getObjectType());
				jsa.add(jso);
			//}
		}
			
		return jsa;
	}
	
	/*
	 * 添加标准段时起始终点设备查询
	 */
	public JSONArray getFitContainer(String key){
		JSONArray jsa = new JSONArray();
		List<PdObjectBase> aim = daoSelect.selectObjectBaseFuzzyByName(key); 
		for(int i=0; i<aim.size(); ++i){
			JSONObject jso = new JSONObject();
			//包括设备：开关站，分支箱，环网柜，变压器，中间接头，变电站，杆塔
			int type = aim.get(i).getObjectType();
			if(type==1010202 || type==1010205 || type==1010204 || type== 1010203 || type== 1010208 || type==1010201 || type==1020102){
				jso.accumulate("id", aim.get(i).getStaticId());
				jso.accumulate("label", aim.get(i).getName());
				jso.accumulate("value", aim.get(i).getName());
				jso.accumulate("type", type);
				jsa.add(jso);
			}
		}
		return jsa;
	}
	
	/*
	 * 添加接线头时电缆段和导线段查询、所属配电线路查询
	 */
	public JSONArray getFitWirecableSeg(String key){
		JSONArray jsa = new JSONArray();
		List<PdElLnWirecableSegmengV> aim = daoSelect.selectWirecableSegByNameRec(key);
		for(int i=0; i<aim.size(); ++i){
			JSONObject jso = new JSONObject();
			int type = aim.get(i).getObjectType();
			PdElLnCableline aimtemp = daoSelect.selectCablelineRec(aim.get(i).getBelongsCableline()); 
			jso.accumulate("belongId", aimtemp.getStaticId());
			jso.accumulate("belongName", aimtemp.getName());
			jso.accumulate("id", aim.get(i).getStaticId());
			jso.accumulate("label", aim.get(i).getName());
			jso.accumulate("value", aim.get(i).getName());
			jso.accumulate("type", type);
			jsa.add(jso);
		}
		return jsa;
	}
	/*
	 * 添加工井时的所属道路
	 */
	public JSONArray getFitRoad(String key){
		JSONArray jsa = new JSONArray();
		List<PdObjectBase> aim = daoSelect.selectObjectBaseFuzzyByName(key); 
		for(int i=0; i<aim.size(); ++i){
			JSONObject jso = new JSONObject();
			int type = aim.get(i).getObjectType();
			if(type != 1020301) continue;
			jso.accumulate("id", aim.get(i).getStaticId());
			jso.accumulate("label", aim.get(i).getName());
			jso.accumulate("value", aim.get(i).getName());
			jso.accumulate("type", type);
			jsa.add(jso);
		}
		return jsa;
	}
	
	/*********************************************
	 * 用于分网页显示剖面图
	 * 通过工井查询剖面、管孔、电缆、支架、测绘层对象、工井、线缆段
	 * ******************************************
	 * @param wellId 工井的静态Id
	 * @return JSONObject 包含所查结果的JSON对象
	 */
	public JSONObject getProJsonProfileCableHoleByWell(Long wellId){
		
		JSONObject jsoALLElement = new JSONObject();
		JSONArray jsaProfile = new JSONArray();
		JSONArray jsaHole = new JSONArray();
		JSONArray jsaCable = new JSONArray();
		//JSONArray jsaBracket = new JSONArray();
		JSONArray jsaSegment = new JSONArray();
		JSONArray jsaPointSurvery = new JSONArray();
		JSONArray jsaWireSegment = new JSONArray();
		List<GeoPipeProfile> geoProfiles = daoSelect.selectGeoProfileByWellId(wellId);
		List<GeoPipeHole> geoHoles = daoSelect.selectGeoPipeHoleByWellId(wellId);
		//List<GeoPipeBracket> geoBrackets = daoSelect.selectGeoBracketByWellId(wellId);
		List<GeoCable> geoCables = daoSelect.selectGeoCableByWellId(wellId);
		List<GeoCivilEngineering> geosegments = daoSelect.selectGeoSegmentByWellId(wellId);
		List<GeoPointSurvery> geopointsurverys = daoSelect.selectGeoPointSurveryByWellId(wellId);
		GeoObjectBase well = daoSelect.selectGeoObjectBaseByStaticId(wellId);
		PdEwPtWell wellProperty = daoSelect.selectWellRec(wellId);
		JSONObject jsoWell = new JSONObject();
		jsoWell.accumulate("value", well.getStaticId());
		jsoWell.accumulate("geometry", well.getGeometryData().toText());
		jsoWell.accumulate("countRound", wellProperty.getCoversCountRound());
		jsoWell.accumulate("countSquare", wellProperty.getCoversCountSquare());
		for(GeoPipeProfile x:geoProfiles){
			JSONObject jso = new JSONObject();
			jso.accumulate("value", x.getStaticId());
			jso.accumulate("geometry", x.getGeometryData().toText());
			jso.accumulate("belongsSegment", x.getPpSegmentId());
			jsaProfile.add(jso);
		}
		for(GeoPipeHole x:geoHoles){
			JSONObject jso = new JSONObject();
			jso.accumulate("value", x.getStaticId());
			jso.accumulate("geometry", x.getGeometryData().toText());
			if(x.getObjectType()==1010502){
				PdPipeHole propertyHole = daoSelect.selectPipeHoleRec(x.getStaticId());
				jso.accumulate("belongsSegment", propertyHole.getPpSegmentId());
			}else{
				PdPipeBracket propertyBracket = daoSelect.selectBracketByStaticId(x.getStaticId());
				jso.accumulate("belongsSegment", propertyBracket.getBelongsPpSegment());
			}
//			PdPipeHole propertyHole = daoSelect.selectPipeHoleRec(x.getStaticId());
//			jso.accumulate("belongsProfile", propertyHole.getPpSegmentId());
			jsaHole.add(jso);
		}
//		for(GeoPipeBracket x:geoBrackets){
//			JSONObject jso = new JSONObject();
//			jso.accumulate("value", "geo_pipe_bracket"+x.getAutoId());
//			jso.accumulate("geometry", x.getGeometryData().toText());
//			jsaBracket.add(jso);
//		}
		for(GeoPointSurvery x:geopointsurverys){
			JSONObject jso = new JSONObject();
			jso.accumulate("value", x.getAutoId());
			jso.accumulate("geometry", x.getGeometryData().toText());
			jsaPointSurvery.add(jso);
		}
		for(GeoCable x:geoCables){
			JSONObject jso = new JSONObject();
			jso.accumulate("value", x.getAutoId());
			jso.accumulate("geometry", x.getGeometryData().toText());
			jso.accumulate("voltagegrade", x.getVoltageGrade());
			jso.accumulate("belongsSegment", x.getPpSegmentId());
			jsaCable.add(jso);
		}
		for(GeoCivilEngineering x:geosegments){
			JSONObject jso = new JSONObject();
			jso.accumulate("value", x.getStaticId());
			jso.accumulate("geometry", x.getGeometryData().toText());
			jso.accumulate("objecttype",x.getObjectType());
			jsaSegment.add(jso);
			List<PdElLnWirecableSegmengV> PdWireSegment = daoSelect.selectWirecableByPipeSegment(x.getStaticId());
			for(PdElLnWirecableSegmengV y:PdWireSegment){
				JSONObject jsoW = new JSONObject();
				GeoObjectBase objectBase = daoSelect.selectGeoObjectBaseByStaticId(y.getStaticId());
				jsoW.accumulate("value", x.getStaticId());
				jsoW.accumulate("geometry", objectBase.getGeometryData().toText());
				jsoW.accumulate("objecttype", objectBase.getObjectType());
				jsaWireSegment.add(jsoW);
			}
		}
		jsoALLElement.accumulate("well", jsoWell);
		jsoALLElement.accumulate("profile", jsaProfile);
		jsoALLElement.accumulate("hole", jsaHole);
		//jsoALLElement.accumulate("bracket", jsaBracket);
		jsoALLElement.accumulate("cable", jsaCable);
		jsoALLElement.accumulate("segment", jsaSegment);
		jsoALLElement.accumulate("pointsurvery", jsaPointSurvery);
		jsoALLElement.accumulate("wiresegment", jsaWireSegment);
		return jsoALLElement;
	}
	
	/*************************************************
	 * 某线路下设备土建
	 * 用于前台地图凸出显示某线路
	 **************************************************/
	//根据配电线路查询配电线路上的所有设备和中间接头的所有土建
	public JSONObject getProJsonOfContainsBuildingByCablelineId(Long cablelineId){
		JSONObject jsoAllElement = new JSONObject();
		JSONArray jsa = new JSONArray();
		JSONArray jsaCE = new JSONArray();
		List<GeoElectrical> geos = daoSelect.selectGeoElectricalByCablelineId(cablelineId);
		List<GeoCivilEngineering> engineering = daoSelect.selectGeoCivilEngineeringByCablelineId(cablelineId);
		List<GeoCivilEngineering> engineeringPoint = daoSelect.selectGeoCivilEngineeringPointByCablelineId(cablelineId);
		//List<PdEwPointBuilding> pointBuildings = daoSelect.selectPointBuildingBelongsCableline(cablelineId);
		//List<PdElLnWirecableSegmengV> wirecable = daoSelect.selectWirecableByCablelineId(cablelineId);
		for(GeoElectrical x:geos){
			JSONObject jso = new JSONObject();
			//jsonConfig.registerJsonValueProcessor(Geometry.class , new jsonGeometryHelper());
			jso.accumulate("value", "geo_electrical."+x.getAutoId());
			jso.accumulate("geometry", x.getGeometryData().toText());
			jsa.add(jso);
		}
		for(GeoCivilEngineering x:engineering){
			JSONObject jso = new JSONObject();
			jso.accumulate("value", "geo_civil_engineering."+x.getAutoId());
			jso.accumulate("geometry", x.getGeometryData().toText());
			jsaCE.add(jso);
			
		}
		for(GeoCivilEngineering x:engineeringPoint){
			JSONObject jso = new JSONObject();
			jso.accumulate("value", "geo_civil_engineering."+x.getAutoId());
			jso.accumulate("geometry", x.getGeometryData().toText());
			jsaCE.add(jso);			
		}
		jsoAllElement.accumulate("electrical", jsa);
		jsoAllElement.accumulate("civilEngineering", jsaCE);
		return jsoAllElement;
	}
	
	//根据变电站查询配电线路上的所有线缆段
	public JSONArray getProJsonofWirecableByTransStationId(Long staticId){
		JSONArray jsa = new JSONArray();
		List<GeoElectrical> geos = daoSelect.selectGeoWirecableByTransstationId(staticId);
		for(GeoElectrical x:geos){
			JSONObject jso = new JSONObject();
			jso.accumulate("value", "geo_electrical."+x.getAutoId());
			jso.accumulate("geometry", x.getGeometryData().toText());
			jsa.add(jso);
		}
		return jsa;
	}
		

	/*
	 * 查询所有管沟段
	 */
	public JSONArray getFitPipeSeg(String key){
		JSONArray jsa = new JSONArray();
		List<PdEwPpSegmentV> aim = daoSelect.selectAllSegmentVRec(); 
		for(int i=0; i<aim.size(); ++i){
			if(aim.get(i).getName().startsWith(key)){
				JSONObject jso = new JSONObject();
				jso.accumulate("id", aim.get(i).getStaticId());
				jso.accumulate("label", aim.get(i).getName());
				jso.accumulate("value", aim.get(i).getName());
				//jso.accumulate("father", aim.get(i).getContainerId());
				jsa.add(jso);
			}
		}
		return jsa;
	}
	
	
	
	/******************************************************
	 * 剖面属性功能部分
	 ******************************************************/
	/*
	 * 根据单个profileId查询所有管孔信息
	 */
	public JSONArray getProJsonOfHolesByPId(long profileId){
		jsonConfig.registerJsonValueProcessor(Date.class , new jsonDateHelper());
		JSONArray result = JSONArray.fromObject(daoSelect.selectPipeHolesRecByPId(profileId), jsonConfig);
		return result;
	}
	
	/*
	 * 根据单个wellId查询所有管孔信息
	 */
	public JSONArray getProJsonOfHolesByWId(long wellId){
		jsonConfig.registerJsonValueProcessor(Date.class , new jsonDateHelper());
		JSONArray result = JSONArray.fromObject(daoSelect.selectPipeHolesRecByWId(wellId), jsonConfig);
		return result;
	}
	
	/*
	 * 根据单个管孔Id查询所有同属一个剖面的管孔信息
	 */
	public JSONArray getProJsonOfHolesByPpId(long pipeSId){
		jsonConfig.registerJsonValueProcessor(Date.class , new jsonDateHelper());
		Long PSId = daoSelect.selectPipeHoleRec(pipeSId).getProfileId();
		JSONArray result = JSONArray.fromObject(daoSelect.selectPipeHolesRecByPId(PSId), jsonConfig);
		return result;
	}
	
	
	/*
	 * 根据管沟的staticId查询所有所属线缆段信息
	 */
	public JSONArray getProJsonOfWireCablesByPPSId(Long SId){
		jsonConfig.registerJsonValueProcessor(Date.class , new jsonDateHelper());
		JSONArray result = JSONArray.fromObject(daoSelect.selectWirecableByPipeSegment(SId), jsonConfig);
		return result;
	}
	
	public List<PdElLnWirecableSegmengV> getProOfWireCablesByPPSId(Long SId){
		//jsonConfig.registerJsonValueProcessor(Date.class , new jsonDateHelper());
		//JSONArray result = JSONArray.fromObject(daoSelect.selectWirecableByPipeSegment(autoId), jsonConfig);
		return daoSelect.selectWirecableByPipeSegment(SId);
	}
	
	
	/*
	 * 根据多个参数autoId查询线缆信息
	 */
	public JSONArray getProJsonOfCablesByAIds(List<Integer> autoIds){
		jsonConfig.registerJsonValueProcessor(Date.class , new jsonDateHelper());
		List<PdElCnCableSegment> obj = new ArrayList<PdElCnCableSegment>();
		for(int i=0; i<autoIds.size(); ++i){
			obj.add(daoSelect.selectCableSegmengVRec(autoIds.get(i)));
		}
		return JSONArray.fromObject(obj);
	}
	
	/*
	 * 根据多个参数staticId查询线缆信息
	 */
	public JSONArray getProJsonOfCablesBySIds(List<Long> SIds){
		jsonConfig.registerJsonValueProcessor(Date.class , new jsonDateHelper());
		List<PdElCnCableSegment> obj = new ArrayList<PdElCnCableSegment>();
		for(int i=0; i<SIds.size(); ++i){
			obj.add(daoSelect.selectCableSegmengVRec(SIds.get(i)));
		}
		return JSONArray.fromObject(obj);
	}
	
	/*
	 * 剖面：通过剖面查询已经穿管的线缆
	 */
	public JSONArray getWireDoneOfProfile(long SId){
		JSONArray jsa = new JSONArray();
		List<PdPipeHole> aim = daoSelect.selectHoleByProfile(SId);
		for(int i=0; i<aim.size(); ++i){
			JSONObject jso = new JSONObject();
			PdElLnWirecableSegmengV wr = daoSelect.selectWirecableSegmengVRec(aim.get(i).getWirecableSegmentId()); 
			jso.accumulate("code", aim.get(i).getPipeCode());
			jso.accumulate("name", wr.getName());
			jso.accumulate("sid", wr.getStaticId());
			jsa.add(jso);
		}
		return jsa;
	}
	
	/*
	 * 剖面：通过剖面查询未穿管的线缆
	 */
	public List<PdElLnWirecableSegmengV> getWireUndoneOfProfile(long SId){
		return daoSelect.selectWirecableUnfinishByProfileId(SId);
	}
	
	/*
	 * 剖面：前台无法传输auto id时根据static id更新管孔穿管字段的属性
	 */
	public int updateWCSegOfHole(long SId, long WCSeg){
		PdPipeHole hole = daoSelect.selectPipeHoleRec(SId);
		hole.setWirecableSegmentId(WCSeg);
		return daoSave.SaveObjectAchieve(hole);
	}
	
	/***********************************************************
	 * 接线图功能部分
	 * 1.查询某环网柜接线图侧栏包含的间隔和线缆
	 ***********************************************************/
	/*
	 * 接线图侧栏数据查询
	 */
	public JSONArray getSideBarJson(long SId){
		JSONArray jsa = new JSONArray();
		List<PdElLnIntermission> inter = daoSelect.selectIntermissionByContainerId(SId);
		for(int i=0; i<inter.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", inter.get(i).getStaticId());
			jso.accumulate("type", inter.get(i).getObjectType());
			jso.accumulate("name", inter.get(i).getName());
			jsa.add(jso);
		}
		
		List<PdElLnWirecableSegmengV> wcStart = daoSelect.selectWirecableByContainerStartId(SId);
		for(int i=0; i<wcStart.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", wcStart.get(i).getStaticId());
			jso.accumulate("type", wcStart.get(i).getObjectType());
			jso.accumulate("name", wcStart.get(i).getName());
			jso.accumulate("wc", "start");
			jsa.add(jso);
		}
		
		List<PdElLnWirecableSegmengV> wcEnd = daoSelect.selectWirecableByContainerEndId(SId);
		for(int i=0; i<wcEnd.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", wcEnd.get(i).getStaticId());
			jso.accumulate("type", wcEnd.get(i).getObjectType());
			jso.accumulate("name", wcEnd.get(i).getName());
			jso.accumulate("wc", "end");
			jsa.add(jso);
		}
		
		return jsa;
	}
	
	/******************************************************
	 * 单线图功能部分
	 * 1.单线图侧栏数据查询，返回JSON
	 *****************************************************/
	public JSONArray getSingleSideBarJson(long SId){
		JSONArray jsa = new JSONArray();
		
		//开关站
		List<PdElCnSwitchStation> switchSta = daoSelect.selectSwitchStationBelongsCableline(SId);
		for(int i=0; i<switchSta.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", switchSta.get(i).getStaticId());
			jso.accumulate("type", switchSta.get(i).getObjectType());
			jso.accumulate("name", switchSta.get(i).getName());
			jsa.add(jso);
		}
		
		//分支箱
		List<PdElCnBranchBox> branchBox = daoSelect.selectBranchBoxBelongsCableline(SId);
		for(int i=0; i<branchBox.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", branchBox.get(i).getStaticId());
			jso.accumulate("type", branchBox.get(i).getObjectType());
			jso.accumulate("name", branchBox.get(i).getName());
			jsa.add(jso);
		}
		
		//环网柜
		List<PdElCnRingmainunit> hw = daoSelect.selectRingmainunitBelongsCableline(SId);
		for(int i=0; i<hw.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", hw.get(i).getStaticId());
			jso.accumulate("type", hw.get(i).getObjectType());
			jso.accumulate("name", hw.get(i).getName());
			jsa.add(jso);
		}
		
		//变压器
		List<PdElCnTransformer> trans = daoSelect.selectTransformerBelongsCableline(SId);
		for(int i=0; i<trans.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", trans.get(i).getStaticId());
			jso.accumulate("type", trans.get(i).getObjectType());
			jso.accumulate("name", trans.get(i).getName());
			jsa.add(jso);
		}
		
		//中间接头
		List<PdElCnIntermediateJoint> interj = daoSelect.selectIntermediateJointBelongsCableline(SId);
		for(int i=0; i<interj.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", interj.get(i).getStaticId());
			jso.accumulate("type", interj.get(i).getObjectType());
			jso.accumulate("name", interj.get(i).getName());
			jsa.add(jso);
		}

		//电站
		//List<PdElCnTransformersubstation> transta = daoSelect.selectTransformersubstationStartByCablelineId(SId);
		List<PdElCnTransformersubstation> transta = daoSelect.selectTransformersubstationByCablelineId(SId);
		//transta.addAll(transtaS);
		for(int i=0; i<transta.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", transta.get(i).getStaticId());
			jso.accumulate("type", transta.get(i).getObjectType());
			jso.accumulate("name", transta.get(i).getName());
			jsa.add(jso);
		}
		
		//塔杆
		List<PdEwPtTower> tower = daoSelect.selectTowerByCableline(SId);
		for(int i=0; i<tower.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", tower.get(i).getStaticId());
			jso.accumulate("type", tower.get(i).getObjectType());
			jso.accumulate("name", tower.get(i).getName());
			jsa.add(jso);
		}
		
		//工井
		
		List<PdEwPointBuilding> wells = daoSelect.selectPointBuildingBelongsCableline(SId);
		for(int i=0; i<wells.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", wells.get(i).getStaticId());
			jso.accumulate("type", wells.get(i).getObjectType());
			jso.accumulate("name", wells.get(i).getName());
			jsa.add(jso);
		}
		
		//线缆段
		
		List<PdElLnWirecableSegmengV> wirecable = daoSelect.selectWirecableByCablelineId(SId);
		for(PdElLnWirecableSegmengV x:wirecable){
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", x.getStaticId());
			jso.accumulate("type", x.getObjectType());
			jso.accumulate("name", x.getName());
			jsa.add(jso);
		}
		return jsa;
	}
	
	/************************************************************
	 * 插入要素属性
	 ***********************************************************/
	public int saveProperty(PdElCnTransformer trans){
		return daoSave.SaveObjectAchieve(trans);	
	}
	
	public int saveProperty(PdElCnTransformersubstation transSub){
		return daoSave.SaveObjectAchieve(transSub, 1000000000000001l);	
	}
	
	public int saveProperty(PdEwPtTower tower){
		return daoSave.SaveObjectAchieve(tower);	
	}
	
	public int saveProperty(PdElCnSwitchStation switchSta){
		return daoSave.SaveObjectAchieve(switchSta);	
	}
	
	public int saveProperty(PdElCnBranchBox branchBox){
		return daoSave.SaveObjectAchieve(branchBox);	
	}
	
	public int saveProperty(PdElCnRingmainunit ringmainunit){
		return daoSave.SaveObjectAchieve(ringmainunit);	
	}
	
	public int saveProperty(PdEwPtWell well){
		return daoSave.SaveObjectAchieve(well);	
	}
	
	public int saveProperty(PdElCnCableSegment cableSeg){
		return daoSave.SaveObjectAchieve(cableSeg);	
	}
	
	public int saveProperty(PdElLnCableline cableline){
		return daoSave.SaveObjectAchieve(cableline);	
	}
	
	public int saveProperty(PdEwPpCablePipeV cablepipe){
		return daoSave.SaveObjectAchieve(cablepipe);	
	}
	
	public int saveProperty(PdEwPpVirtualPipe virtualpipe){
		return daoSave.SaveObjectAchieve(virtualpipe);	
	}
	
	public int saveProperty(PdEwPpRackPipe rackpipe){
		return daoSave.SaveObjectAchieve(rackpipe);	
	}
	
	public int saveProperty(PdEwPpBridge bridge){
		return daoSave.SaveObjectAchieve(bridge);	
	}
	
	public int saveProperty(PdEwPpChannel channel){
		return daoSave.SaveObjectAchieve(channel);	
	}
	
	public int saveProperty(PdEwPpBuried buried){
		return daoSave.SaveObjectAchieve(buried);	
	}
	
	public int saveProperty(PdEwPpTunnel tunnel){
		return daoSave.SaveObjectAchieve(tunnel);	
	}
	
	public int saveProperty(PdEwPpJacking jacking){
		return daoSave.SaveObjectAchieve(jacking);	
	}
	
	public int saveProperty(PdElLnIntermission intermission){
		return daoSave.SaveObjectAchieve(intermission);
	}
	
	public int saveProperty(PdElCnWireSegmet wireSeg){
		return daoSave.SaveObjectAchieve(wireSeg);
	}
	
	public int saveProperty(PdEwRoad road){
		return daoSave.SaveObjectAchieve(road);
	}
	
	public int saveProperty(PdEwPipeLineV pipeline){
		return daoSave.SaveObjectAchieve(pipeline);
	}
	
	public int saveProperty(PdWirecablToPipeSegmentId topipe){
		return daoSave.SaveObjectAchieve(new PdWirecablToPipeSegment(topipe));
	}
	
	public int saveProperty(PdElCnBranch branch){
		return daoSave.SaveObjectAchieve(branch);
	}
	
	public int saveProperty(PdElCnIntermediateJoint interjoint){
		return daoSave.SaveObjectAchieve(interjoint);
	}
	
	public int saveProperty(PdElCnCabledrumRemainder interjoint){
		return daoSave.SaveObjectAchieve(interjoint);
	}
	
	public int saveProperty(PdPipeBracket bracket){
		return daoSave.SaveObjectAchieve(bracket);
	}
	
	/*******************************************************
	 * 批量插入要素组
	 *******************************************************/
	public int savePropertiesOfTrans(List<PdElCnTransformer> trans){
		int res = 0;
		for(int i=0; i<trans.size(); ++i){
			res = daoSave.SaveObjectAchieve(trans.get(i));	
		} 
		return res;
	}
	
	public int savePropertiesOfHoles(List<PdPipeHole> holes){
		int res = 0;
		for(int i=0; i<holes.size(); ++i){
			res = daoSave.SaveObjectAchieve(holes.get(i));	
		}
		return res;
	}
	
	public int savePropertiesOfProfile(List<PdPipeProfile> profile){
		int res = 0;
		for(int i=0; i<profile.size(); ++i){
			res = daoSave.SaveObjectAchieve(profile.get(i));	
		}
		return res;
	}
	
	public int savePropertiesOfVirtualpipe(List<PdEwPpVirtualPipe> virtualpipe){
		int res = 0;
		for(int i=0; i<virtualpipe.size(); ++i){
			res = daoSave.SaveObjectAchieve(virtualpipe.get(i));	
		}
		return res;
	}
	
	public int savePropertiesOfRackpipe(List<PdEwPpRackPipe> rackpipe){
		int res = 0;
		for(int i=0; i<rackpipe.size(); ++i){
			res = daoSave.SaveObjectAchieve(rackpipe.get(i));	
		}
		return res;
	}
	
	public int savePropertiesOfBridge(List<PdEwPpBridge> bridge){
		int res = 0;
		for(int i=0; i<bridge.size(); ++i){
			res = daoSave.SaveObjectAchieve(bridge.get(i));	
		}
		return res;
	}
	
	public int savePropertiesOfChannel(List<PdEwPpChannel> channel){
		int res = 0;
		for(int i=0; i<channel.size(); ++i){
			res = daoSave.SaveObjectAchieve(channel.get(i));	
		}
		return res;
	}
	
	public int savePropertiesOfBuried(List<PdEwPpBuried> buried){
		int res = 0;
		for(int i=0; i<buried.size(); ++i){
			res = daoSave.SaveObjectAchieve(buried.get(i));	
		}
		return res;
	}
	
	public int savePropertiesOfTunnel(List<PdEwPpTunnel> tunnel){
		int res = 0;
		for(int i=0; i<tunnel.size(); ++i){
			res = daoSave.SaveObjectAchieve(tunnel.get(i));	
		}
		return res;
	}
	
	public int savePropertiesOfJacking(List<PdEwPpJacking> jacking){
		int res = 0;
		for(int i=0; i<jacking.size(); ++i){
			res = daoSave.SaveObjectAchieve(jacking.get(i));	
		}
		return res;
	}
	
	public int savePropertiesOfWells(List<PdEwPtWell> wells){
		int res = 0;
		for(int i=0; i<wells.size(); ++i){
			res = daoSave.SaveObjectAchieve(wells.get(i));	
		}
		return res;
	}
	
	//setter
	public void setDaoSelect(SelectRecordAll daoSelect){
		this.daoSelect = daoSelect;
	}
	
	public void setDaoSave(SavePropertyObject daoSave){
		this.daoSave = daoSave;
	}
	
	//getter
	public SelectRecordAll getDaoSelect(){
		return daoSelect;
	}
	
	public SavePropertyObject getDaoSave(){
		return daoSave;
	}

}
