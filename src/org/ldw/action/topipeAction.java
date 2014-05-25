package org.ldw.action;


import java.util.List;


import com.opensymphony.xwork2.ActionSupport;
import org.ldw.service.propertyService;
import org.resource.objectclass.*;
/*
 * 根据参数返回系统功能界面
 */
public class topipeAction extends ActionSupport {
	private long wire;
	private List<Long> topipe;
	private propertyService proService;
	
	public String execute() throws Exception {
		for(int i=0; i<topipe.size(); ++i){
			PdWirecablToPipeSegmentId pt = new PdWirecablToPipeSegmentId();
			pt.setSegmentHaveWirecable(wire);
			pt.setWirecableBelongsSegment(topipe.get(i));
			proService.saveProperty(pt);
		}
		
		return SUCCESS;
	}
	
	//setter&getter
	public void setWire(String wire){
		this.wire = Long.parseLong(wire);
	}
	
	public long getWire(){
		return wire;
	};
	
	public void setTopipe(List<Long> topipe){
		this.topipe = topipe;
	}
	
	public List<Long> getTopipe(){
		return topipe;
	}
	
	public void setProService(propertyService proService){
		this.proService = proService;
	}
	
	public propertyService getProService(){
		return proService;
	}
}
