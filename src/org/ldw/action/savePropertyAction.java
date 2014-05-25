package org.ldw.action;

import java.util.Date;

import org.ldw.service.propertyService;
import org.resource.objectclass.*;

import com.opensymphony.xwork2.ActionSupport;

public class savePropertyAction extends ActionSupport{
	private propertyService proService;
	private int type;
	private PdElCnTransformer trans;
	private PdElCnTransformersubstation transSub;
	private PdEwPtTower tower;
	private PdElCnSwitchStation switchSta;
	private PdElCnBranchBox branchBox;
	private PdElCnRingmainunit ringmainunit;
	private PdEwPtWell well;
	private PdElCnCableSegment cableSeg;
	private PdElCnWireSegmet wireSeg;
	private PdElLnCableline cableline;
	private PdEwPpCablePipeV cablepipe;
	private PdEwPpVirtualPipe virtualpipe;
	private PdEwPpRackPipe rackpipe;
	private PdEwPpBridge bridge;
	private PdEwPpChannel channel;
	private PdEwPpBuried buried;
	private PdEwPpTunnel tunnel;
	private PdEwPpJacking jacking;
	private PdElLnIntermission intermission;
	private PdEwRoad road;
	private PdEwPipeLineV pipeline;
	private PdWirecablToPipeSegmentId topipe;
	private PdElCnBranch branch;
	private PdElCnIntermediateJoint interjoint;
	private PdElCnCabledrumRemainder remainder;
	private PdPipeBracket bracket;
	private int msg;
	
	public String execute() throws Exception {	
		//trans.setCommissioningDate(commissioningDate);
		switch(type){
			case 1010203:msg = proService.saveProperty(trans);break;
			case 1010201:msg = proService.saveProperty(transSub);break;
			case 1020102:msg = proService.saveProperty(tower);break;
			case 1010202:msg = proService.saveProperty(switchSta);break;
			case 1010205:msg = proService.saveProperty(branchBox);break;
			case 1010204:msg = proService.saveProperty(ringmainunit);break;
			case 1020101:msg = proService.saveProperty(well);break;
			case 1020103:msg = proService.saveProperty(well);break;//虚拟工井
			case 1010402:msg = proService.saveProperty(cableSeg);break;
			case 1010301:msg = proService.saveProperty(cableline);break;
			case 1020202:msg = proService.saveProperty(cablepipe);break;
			case 1020203:msg = proService.saveProperty(virtualpipe);break;
			case 1020211:msg = proService.saveProperty(rackpipe);break;
			case 1020212:msg = proService.saveProperty(bridge);break;
			case 1020213:msg = proService.saveProperty(channel);break;
			case 1020214:msg = proService.saveProperty(buried);break;
			case 1020215:msg = proService.saveProperty(tunnel);break;
			case 1020216:msg = proService.saveProperty(jacking);break;
			//case 1010503:msg = proService.saveProperty(bracket);break;
			//case 1010601:msg = proService.saveProperty(intermission);break;
			default:msg=0;
		}
		if(intermission != null){
			msg = proService.saveProperty(intermission);
		}
		
		if(cableSeg != null){
			msg = proService.saveProperty(cableSeg);
		}
		
		if(wireSeg != null){
			msg = proService.saveProperty(wireSeg);
		}
		
		if(road != null){
			msg = proService.saveProperty(road);
		}
		
		if(pipeline != null){
			msg = proService.saveProperty(pipeline);
		}
		
		if(topipe != null){
			msg = proService.saveProperty(topipe);
		}
		
		if(branch != null){
			msg = proService.saveProperty(branch);
		}
		
		if(interjoint != null){
			msg = proService.saveProperty(interjoint);
		}
		
		if(remainder != null){
			msg = proService.saveProperty(remainder);
		}
		
		if(bracket != null){
			msg = proService.saveProperty(bracket);
		}
		
		if(msg != 0){
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	//@setter
	public void setProService(propertyService proService){
		this.proService = proService;
	}
	
	public void setType(int type){
		this.type = type;
	}
	public void setMsg(int msg){
		this.msg = msg;
	}
	public void setTrans(PdElCnTransformer trans){
		type = 1010203;
		this.trans = trans;
	}
	
	public void setTransSub(PdElCnTransformersubstation transSub){
		type = 1010201;
		this.transSub = transSub;
	}
	
	public void setTower(PdEwPtTower tower){
		type = 1020102;
		this.tower = tower;
	}
	
	public void setSwitchSta(PdElCnSwitchStation switchSta){
		type = 1010202;
		this.switchSta = switchSta;
	}
	
	public void setBranchBox(PdElCnBranchBox branchBox){
		type = 1010205;
		this.branchBox = branchBox;
	}
	
	public void setRingmainunit(PdElCnRingmainunit ringmainunit){
		type = 1010204;
		this.ringmainunit = ringmainunit;
	}
	
	public void setWell(PdEwPtWell well){
		type = 1020101;
		this.well = well;
	}
	
	public void setCableline(PdElLnCableline cableline){
		type = 1010301;
		this.cableline = cableline;
	}
	
	public void setCablepipe(PdEwPpCablePipeV cablepipe){
		type = 1020202;
		this.cablepipe = cablepipe;
	}
	
	public void setVirtualpipe(PdEwPpVirtualPipe virtualpipe){
		type = 1020203;
		this.virtualpipe = virtualpipe;
	}
	
	public void setRackpipe(PdEwPpRackPipe rackpipe){
		type = 1020211;
		this.rackpipe = rackpipe;
	}
	
	public void setBridge(PdEwPpBridge bridge){
		type = 1020212;
		this.bridge = bridge;
	}
	
	public void setChannel(PdEwPpChannel channel){
		type = 1020213;
		this.channel = channel;
	}
	
	public void setBuried(PdEwPpBuried buried){
		type = 1020214;
		this.buried = buried;
	}
	
	public void setTunnel(PdEwPpTunnel tunnel){
		type = 1020215;
		this.tunnel = tunnel;
	}
	
	public void setJacking(PdEwPpJacking jacking){
		type = 1020216;
		this.jacking = jacking;
	}
	
	public void setIntermission(PdElLnIntermission intermission){
		this.intermission = intermission;
	}
	
	public void setWireSeg(PdElCnWireSegmet wireSeg){
		this.wireSeg = wireSeg;
	}
	
	public void setCableSeg(PdElCnCableSegment cableSeg){
		this.cableSeg = cableSeg;
	}
	
	public void setRoad(PdEwRoad road){
		this.road = road;
	}
	
	public void setPipeline(PdEwPipeLineV pipeline){
		this.pipeline = pipeline;
	}
	
	public void setTopipe(PdWirecablToPipeSegmentId topipe){
		this.topipe = topipe;
	}
	
	public void setBranch(PdElCnBranch branch){
		this.branch = branch;
	}
	
	public void setInterjoint(PdElCnIntermediateJoint interjoint){
		this.interjoint = interjoint;
	}
	
	public void setRemainder(PdElCnCabledrumRemainder remainder){
		this.remainder = remainder;
	}
	
	public void setBracket(PdPipeBracket bracket){
		this.bracket = bracket;
	}
	
	//getter
	public propertyService getProService(){
		return proService;
	}
	
	public int getType(){
		return type;
	}
	public int getMsg(){
		return msg;
	}
	
	public PdElCnTransformer getTrans(){
		return trans;
	}
	
	public PdElCnTransformersubstation getTransSub(){
		return transSub;
	}
	
	public PdEwPtTower getTower(){
		return tower;
	}
	
	public PdElCnSwitchStation getSwitchSta(){
		return switchSta;
	}
	
	public PdElCnBranchBox getBranchBox(){
		return branchBox;
	}
	
	public PdElCnRingmainunit getRingmainunit(){
		return ringmainunit;
	}
	
	public PdEwPtWell getWell(){
		return well;
	}
	
	public PdElLnCableline getCableline(){
		return cableline;
	}
	
	public PdEwPpCablePipeV getCablepipe(){
		return cablepipe;
	}
	
	public PdEwPpVirtualPipe getVirtualpipe(){
		return virtualpipe;
	}
	
	public PdEwPpRackPipe getRackpipe(){
		return rackpipe;
	}
	
	public PdEwPpBridge getBridge(){
		return bridge;
	}
	
	public PdEwPpChannel getChannel(){
		return channel;
	}
	
	public PdEwPpBuried getBuried(){
		return buried;
	}
	
	public PdEwPpTunnel getTunnel(){
		return tunnel;
	}
	
	public PdEwPpJacking getJacking(){
		return jacking;
	}
	
	public PdElLnIntermission getIntermission(){
		return intermission;
	}
	
	public PdElCnWireSegmet getWireSeg(){
		return wireSeg;
	}
	
	public PdElCnCableSegment getCableSeg(){
		return cableSeg;
	}
	
	public PdEwRoad getRoad(){
		return road;
	}
	
	public PdEwPipeLineV getPipeline(){
		return pipeline;
	}
	
	public PdWirecablToPipeSegmentId getTopipe(){
		return topipe;
	}
	
	public PdElCnBranch getBranch(){
		return branch;
	}
	
	public PdElCnIntermediateJoint getInterjoint(){
		return interjoint;
	}
	
	public PdElCnCabledrumRemainder getRemainder(){
		return remainder;
	}
	
	public PdPipeBracket getBracket(){
		return bracket;
	}
}
