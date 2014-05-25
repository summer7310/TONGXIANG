package org.lyd.Operate;

import org.lyd.Hibernate.SavePropertyObject;
import org.lyd.Hibernate.SelectRecordAll;
import org.resource.objectclass.GeoObjectBase;
import org.resource.objectclass.PdElCnBranch;
import org.resource.objectclass.PdElCnBranchBox;
import org.resource.objectclass.PdElCnRingmainunit;
import org.resource.objectclass.PdElCnSwitch;
import org.resource.objectclass.PdElCnSwitchStation;
import org.resource.objectclass.PdElCnTransformer;
import org.resource.objectclass.PdElCnTransformersubstation;
import org.resource.objectclass.PdEwPtTower;
import org.resource.objectclass.PdEwPtWell;
import org.resource.objectclass.PdPipeHole;
import org.resource.objectclass.PdPipeProfile;

//import org.lyd.object.GeoObjectBase;
//import org.lyd.object.PdCity;
//import org.lyd.object.PdElCnBranch;
//import org.lyd.object.PdElCnBranchBox;
//import org.lyd.object.PdElCnRingmainunit;
//import org.lyd.object.PdElCnSwitch;
//import org.lyd.object.PdElCnSwitchStation;
//import org.lyd.object.PdElCnTransformer;
//import org.lyd.object.PdElCnTransformersubstation;
//import org.lyd.object.PdEwPtTower;
//import org.lyd.object.PdEwPtWell;
//import org.lyd.object.PdPipeHole;
//import org.lyd.object.PdTunnel;
//import org.lyd.saveObject.SavePropertyObject;
//import org.lyd.selectObject.SelectGeoObjectByName;
//import org.lyd.util.HibernateSessionFactory;

public class SaveOrUpdateObject {
	private SavePropertyObject spo=new SavePropertyObject();
	private SelectRecordAll sgo=new SelectRecordAll();
	private GeoObjectBase gob;
	//变压器
	public int SaveOrUpdateObjectF(PdElCnTransformer receive){
		try{
			spo.SaveObjectAchieve(receive);
			gob=sgo.SelectGeoObjectByNameF(receive.getName());
			spo.UpdateGeoObjectIsbn(gob,receive.getAutoId());
			//s.save(receive);
			return receive.getAutoId();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}  
	//开关站
	public int SaveOrUpdateObjectF(PdElCnSwitchStation receive){
		try{
			spo.SaveObjectAchieve(receive);
			gob=sgo.SelectGeoObjectByNameF(receive.getName());
			spo.UpdateGeoObjectIsbn(gob,receive.getAutoId());
			//s.save(receive);
			return receive.getAutoId();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}  
	//分支接头
	public int SaveOrUpdateObjectF(PdElCnBranch receive){
		try{
			spo.SaveObjectAchieve(receive);
			gob=sgo.SelectGeoObjectByNameF(receive.getName());
			spo.UpdateGeoObjectIsbn(gob,receive.getAutoId());
			//s.save(receive);
			return receive.getAutoId();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}  
	//分支箱
	public int SaveOrUpdateObjectF(PdElCnBranchBox receive){
		try{
			spo.SaveObjectAchieve(receive);
			gob=sgo.SelectGeoObjectByNameF(receive.getName());
			spo.UpdateGeoObjectIsbn(gob,receive.getAutoId());
			//s.save(receive);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	//开关
	public int SaveOrUpdateObjectF(PdElCnSwitch receive){
		try{
			spo.SaveObjectAchieve(receive);
			gob=sgo.SelectGeoObjectByNameF(receive.getName());
			spo.UpdateGeoObjectIsbn(gob,receive.getAutoId());
			//s.save(receive);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	//变电站
	public int SaveOrUpdateObjectF(PdElCnTransformersubstation receive){
		try{
			spo.SaveObjectAchieve(receive);
			gob=sgo.SelectGeoObjectByNameF(receive.getName());
			spo.UpdateGeoObjectIsbn(gob,receive.getAutoId());
			//s.save(receive);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	//变电站
	public int SaveOrUpdateObjectF(PdElCnTransformersubstation receive,Long belongsCity){
		try{
			spo.SaveObjectAchieve(receive,belongsCity);
			gob=sgo.SelectGeoObjectByNameF(receive.getName());
			spo.UpdateGeoObjectIsbn(gob,receive.getAutoId());
			//s.save(receive);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	//环网柜
	public int SaveOrUpdateObjectF(PdElCnRingmainunit receive){
		try{
			spo.SaveObjectAchieve(receive);
			gob=sgo.SelectGeoObjectByNameF(receive.getName());
			spo.UpdateGeoObjectIsbn(gob,receive.getAutoId());
			//s.save(receive);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	//剖面
	public int SaveOrUpdateObjectF(PdPipeProfile receive){
		try{
			spo.SaveObjectAchieve(receive);
			gob=sgo.SelectGeoObjectByNameF(receive.getName());
			spo.UpdateGeoObjectIsbn(gob,receive.getAutoId());
			//s.save(receive);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	//剖面属性表
	public int SaveOrUpdateObjectF(PdPipeHole receive){
		try{
			spo.SaveObjectAchieve(receive);
			gob=sgo.SelectGeoObjectByNameF(receive.getName());
			spo.UpdateGeoObjectIsbn(gob,receive.getAutoId());
			//s.save(receive);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	//工井
	public int SaveOrUpdateObjectF(PdEwPtWell receive){
		try{
			spo.SaveObjectAchieve(receive);
			gob=sgo.SelectGeoObjectByNameF(receive.getName());
			spo.UpdateGeoObjectIsbn(gob,receive.getAutoId());
			//s.save(receive);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	//杆塔
	public int SaveOrUpdateObjectF(PdEwPtTower receive){
		try{
			spo.SaveObjectAchieve(receive);
			gob=sgo.SelectGeoObjectByNameF(receive.getName());
			spo.UpdateGeoObjectIsbn(gob,receive.getAutoId());
			//s.save(receive);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
		
}
