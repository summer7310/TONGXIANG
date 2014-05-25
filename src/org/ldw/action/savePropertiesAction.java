package org.ldw.action;

import java.util.List;

import org.ldw.service.propertyService;
import org.resource.objectclass.*;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 保存多个（不同种类）要素的属性
 * 根据POST传送的NAME区分要素种类
 */
public class savePropertiesAction extends ActionSupport{
	private propertyService proService;
	private int type;
	private List<PdElCnTransformer> trans;
	private List<PdPipeHole> holes;
	private List<PdPipeProfile> profile;
	private List<PdEwPpVirtualPipe> virtualpipe;
	private List<PdEwPpRackPipe> rackpipe;
	private List<PdEwPpBridge> bridge;
	private List<PdEwPpChannel> channel;
	private List<PdEwPpBuried> buried;
	private List<PdEwPpTunnel> tunnel;
	private List<PdEwPpJacking> jacking;
	private List<PdEwPtWell> wells;
	
	public String execute() throws Exception {	
		/*
		if(proService.saveProperties(trans) == 1){
			return SUCCESS;
		} else {
			return ERROR;
		}*/
		int flag = 0;
		if(holes != null){
			flag = proService.savePropertiesOfHoles(holes);
		}
		
		if(profile != null){
			flag = proService.savePropertiesOfProfile(profile);
		}
		
		if(virtualpipe != null){
			flag = proService.savePropertiesOfVirtualpipe(virtualpipe);
		}
		
		if(rackpipe != null){
			flag = proService.savePropertiesOfRackpipe(rackpipe);
		}
		
		if(bridge != null){
			flag = proService.savePropertiesOfBridge(bridge);
		}
		
		if(channel != null){
			flag = proService.savePropertiesOfChannel(channel);
		}
		
		if(buried != null){
			flag = proService.savePropertiesOfBuried(buried);
		}
		
		if(tunnel != null){
			flag = proService.savePropertiesOfTunnel(tunnel);
		}
		
		if(jacking != null){
			flag = proService.savePropertiesOfJacking(jacking);
		}
		
		if(wells != null){
			flag = proService.savePropertiesOfWells(wells);
		}
		
		if(flag == 1){
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
	
	public void setTrans(List<PdElCnTransformer> trans){
		this.trans = trans;
	}
	
	public void setHoles(List<PdPipeHole> holes){
		this.holes = holes;
	}
	
	public void setProfile(List<PdPipeProfile> profile){
		this.profile = profile;
	}
	
	public void setVirtualpipe(List<PdEwPpVirtualPipe> virtualpipe){
		this.virtualpipe = virtualpipe;
	}
	
	public void setRackpipe(List<PdEwPpRackPipe> rackpipe){
		this.rackpipe = rackpipe;
	}
	
	public void setBridge(List<PdEwPpBridge> bridge){
		this.bridge = bridge;
	}
	
	public void setChannel(List<PdEwPpChannel> channel){
		this.channel = channel;
	}
	
	public void setBuried(List<PdEwPpBuried> buried){
		this.buried = buried;
	}
	
	public void setTunnel(List<PdEwPpTunnel> tunnel){
		this.tunnel = tunnel;
	}
	
	public void setJacking(List<PdEwPpJacking> jacking){
		this.jacking = jacking;
	}
	
	public void setWells(List<PdEwPtWell> wells){
		this.wells = wells;
	}
	//getter
	public propertyService getProService(){
		return proService;
	}
	
	public int getType(){
		return type;
	}
	
	public List<PdElCnTransformer> getTrans(){
		return trans;
	}
	
	public List<PdPipeHole> getHoles(){
		return holes;
	}
	
	public List<PdPipeProfile> getProfile(){
		return profile;
	}
	
	public List<PdEwPpVirtualPipe> getVirtualpipe(){
		return virtualpipe;
	}
	
	public List<PdEwPpRackPipe> getRackpipe(){
		return rackpipe;
	}
	
	public List<PdEwPpBridge> getBridge(){
		return bridge;
	}
	
	public List<PdEwPpChannel> getChannel(){
		return channel;
	}
	
	public List<PdEwPpBuried> getBuried(){
		return buried;
	}
	
	public List<PdEwPpTunnel> getTunnel(){
		return tunnel;
	}
	
	public List<PdEwPpJacking> getJacking(){
		return jacking;
	}
	
	public List<PdEwPtWell> getWells(){
		return wells;
	}
}
