package org.ldw.service;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.lyd.Operate.TreeCatalog;
import org.lyd.Operate.TreeCatalogRecord;
import org.resource.objectclass.PdElLnCableline;

/*
 * 树目录及侧栏列表服务
 * 1.左侧数目录
 * 2.右侧单线图包含的设备
 */
public class treeService {
	private String fatherId;
	private String fatherLevel;
	private String fatherName;
	private String fatherType;
	//private GISSelect conn;
	private TreeCatalog treeDao;
	private static final String wrongMsg = "ERROR";
	
	public treeService(){
		//conn = new GISSelect("localhost:5432/TONGXIANG", "postgres", "admin");
	}
	
	public void initVar(String id, String n, String lev){
		if(id != null){
			String[] array = id.split("-");
			this.fatherId =  array[1];
			this.fatherType = array[0];
			this.fatherLevel = lev;
			this.fatherName = n;
		} else {
			this.fatherId = null;
			this.fatherType = null;
			this.fatherLevel = null;
			this.fatherName = null;
		}
		
	}
	/* 
	 * 获取父类型
	 */
	private int getType(){
		if(fatherLevel.equals("none")){
			return  0;
		} else {
			return 1;
		}
	}
	//获取最终目录数据
	public String getData(){
		return getJsonF().toString();
	}
	
	//获取返回JSON数据
	//[{idname:'TONGXIANG', isParent:true, iconSkin:'city'}]
	public JSONArray getJsonF(){
		JSONArray jsa = new JSONArray();
		int t = this.getType();
		List<TreeCatalogRecord> aim ;//= treeDao.getTreeCatalog();
		
		switch(t){
		case 0	: aim = treeDao.getTreeCatalog();break;
		case 1	: aim = treeDao.getTreeCatalog(Long.parseLong(fatherId), Integer.parseInt(fatherType));break;
		default	: aim = treeDao.getTreeCatalog(Long.parseLong(fatherId), Integer.parseInt(fatherType));
		}
		for(int i=0; i<aim.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("id", aim.get(i).getTypeId() + "-" + aim.get(i).getStaticId());
			jso.accumulate("name", aim.get(i).getName());
			jso.accumulate("isParent", aim.get(i).getParent());
			jso.accumulate("iconSkin", aim.get(i).getIconSkin());
			jsa.add(jso);
		}
		return jsa;
	}
	
	/*
	 * 获取单线图侧栏数据
	 */
	/*
	public JSONArray getSideBarJson(){
		JSONArray jsa = new JSONArray();
		Long SId = Long.parseLong(fatherId);
		List<TreeCatalogRecord> aimswitch = treeDao.getTreeCatalog(SId, 10102020);
		for(int i=0; i<aimswitch.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", aimswitch.get(i).getStaticId());
			jso.accumulate("type", aimswitch.get(i).getTypeId());
			jso.accumulate("name", aimswitch.get(i).getName());
			//jso.accumulate("isParent", aim.get(i).getParent());
			//jso.accumulate("iconSkin", aim.get(i).getIconSkin());
			jsa.add(jso);
		}
		
		List<TreeCatalogRecord> aimbranch = treeDao.getTreeCatalog(SId, 10102050);
		for(int i=0; i<aimbranch.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", aimbranch.get(i).getStaticId());
			jso.accumulate("type", aimbranch.get(i).getTypeId());
			jso.accumulate("name", aimbranch.get(i).getName());
			//jso.accumulate("isParent", aim.get(i).getParent());
			//jso.accumulate("iconSkin", aim.get(i).getIconSkin());
			jsa.add(jso);
		}
		
		List<TreeCatalogRecord> aimhw = treeDao.getTreeCatalog(SId, 10102040);
		for(int i=0; i<aimhw.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", aimhw.get(i).getStaticId());
			jso.accumulate("type", aimhw.get(i).getTypeId());
			jso.accumulate("name", aimhw.get(i).getName());
			//jso.accumulate("isParent", aim.get(i).getParent());
			//jso.accumulate("iconSkin", aim.get(i).getIconSkin());
			jsa.add(jso);
		}
		
		List<TreeCatalogRecord> aimtrans = treeDao.getTreeCatalog(SId, 10102030);
		for(int i=0; i<aimtrans.size(); ++i){	
			JSONObject jso = new JSONObject();
			jso.accumulate("sid", aimtrans.get(i).getStaticId());
			jso.accumulate("type", aimtrans.get(i).getTypeId());
			jso.accumulate("name", aimtrans.get(i).getName());
			//jso.accumulate("isParent", aim.get(i).getParent());
			//jso.accumulate("iconSkin", aim.get(i).getIconSkin());
			jsa.add(jso);
		}
		
		return jsa;
	}
	*/
	
	//setter&getter
	public void setTreeDao(TreeCatalog treeDao){
		this.treeDao = treeDao;
	}
	
	public TreeCatalog getTreeDao(){
		return treeDao;
	}
	
}
