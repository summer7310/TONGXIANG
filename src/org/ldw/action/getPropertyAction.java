package org.ldw.action;
import com.opensymphony.xwork2.ActionSupport;
import org.ldw.service.propertyService;
import net.sf.json.JSONObject;

/*
 * 获取设备属性表单
 */
public class getPropertyAction extends ActionSupport {
	private int type;
	private Long isbn;
	private Long wellSid;//工井的静态ID，查询剖面属性时候使用
	private String name;
	private propertyService proService;
	private JSONObject result;
	private boolean ifByName = false;
	private String clName;//所属线路
	private String startInt;//起点间隔:标准段
	private String endInt;//终点间隔:标准段
	private String startObj;//起点设备：标准段
	private String endObj;//终点设备：标准段
	private String road;//所属道路
	private String well;//所属工井：剖面
	private String well2;
	private String ppSeg;//所属管沟段：剖面
	private String startBuilding;//起始土建：管沟线
	private String endBuilding;//终点土建：管沟线
	private String building;//土建：中间接头
	private String wirecable;//中间接头
	private String pipeline;//管沟线：管沟段
	private String containerId;//间隔：所属设备
	private String startTransStation;//配电线路：起点电站
	
	public String execute() throws Exception {
	
		if(ifByName){
			result = proService.getPropertyJson(type, name);
		} else if(isbn != null) {
			result = proService.getPropertyJson(type, isbn);
		} else {
			result = null;
		}
		
		if(result != null){
			clName 			= proService.getNameOfCableline(type, result);
			//startInt 		= proService.getNameOfIntermission(type, result, "startIntermission");
			//endInt 			= proService.getNameOfIntermission(type, result, "endIntermission");
			startObj		= proService.getNameOfObj(type, result, "startContainer");
			endObj			= proService.getNameOfObj(type, result, "endContainer");
			road 			= proService.getNameOfRoad(type, result);
			well 			= proService.getNameOfWell(type, result);
			well2 			= proService.getNameOfWell2(type, wellSid);
			ppSeg 			= proService.getNameOfPP(type, result);
			startBuilding 	= proService.getNameOfBuilding(type, result, "startBuilding");
			endBuilding 	= proService.getNameOfBuilding(type, result, "endBuilding");
			building		= proService.getNameOfLocateId(type, result, "locationId");
			wirecable		= proService.getNameOfWirecable(type, result);
			pipeline		= proService.getNameOfPipeline(type, result);
			containerId   	= proService.getNameOfElectrical(type, result, "containerId");
			startTransStation= proService.getNameOfStartTransStation(type, result, "startTransStation");			
		} else {
			clName 			= "";
			startInt 		= "";
			endInt 			= "";
			road 			= "";
			well 			= "";
			ppSeg	 		= "";
			startBuilding 	= "";
			endBuilding 	= "";
			building		= "";
			wirecable		= "";
			pipeline		= "";
			containerId   	= "";
			startTransStation="";
		}
		return Integer.toString(type);
	}
	
	//setter
	public void setType(int type){
		this.type = type;
	}
	
	public void setIsbn(String isbn){
		ifByName = false;
		this.isbn = Long.parseLong(isbn);
	}
	
	public void setName(String name){
		ifByName = true;
		this.name = name;
	}
	
	public void setResult(JSONObject result){
		this.result = result;
	}
	
	public void setProService(propertyService proService){
		this.proService = proService;
	}
	
	public void setWellSid(String wellSid){
		this.wellSid = Long.parseLong(wellSid);
	}
	
	
	//getter
	public int getType(){
		return type;
	}
	
	public Long getIsbn(){
		return isbn;
	}
	
	public String getName(){
		return name;
	}
	
	public JSONObject getResult(){
		return result;
	}
	
	public propertyService getProService(){
		return proService;
	}
	
	public String getClName(){
		return clName;
	}
	
	public String getStartInt(){
		return startInt;
	}
	
	public String getEndInt(){
		return endInt;
	}
	
	public String getRoad(){
		return road;
	}
	
	public String getWell(){
		return well;
	}
	
	public String getWell2(){
		return well2;
	} 
	
	public String getPpSeg(){
		return ppSeg;
	}
	
	public String getStartBuilding(){
		return startBuilding;
	}
	
	public String getEndBuilding(){
		return endBuilding;
	}
	
	public String getStartObj(){
		return startObj;
	}
	
	public String getEndObj(){
		return endObj;
	}
	
	public String getBuilding(){
		return building;
	}
	
	public String getWirecable(){
		return wirecable;
	}
	
	public String getPipeline(){
		return pipeline;
	}
	
	public String getContainerId(){
		return containerId;
	}
	
	public String getStartTransStation(){
		return startTransStation;
	}
	
	public Long getWellSid(){
		return wellSid;
	}
}
