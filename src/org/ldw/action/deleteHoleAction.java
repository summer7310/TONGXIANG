package org.ldw.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.lyd.Hibernate.DeleteObject;
import org.lyd.Hibernate.SelectRecordAll;

import com.opensymphony.xwork2.ActionSupport;

public class deleteHoleAction extends ActionSupport {
	private List<Long> holesId = new ArrayList<Long>();
	private List<Long> bracketsId = new ArrayList<Long>();
	private String type;
	public List<Long> getBracketsId() {
		return bracketsId;
	}
	public void setBracketsId(String bracketsId) {
		String res[] = bracketsId.split(",");
		for(int i=0; i<res.length; ++i){
			this.bracketsId.add(Long.parseLong(res[i]));
		}
	}

	private DeleteObject deleter = new DeleteObject();
	private SelectRecordAll select = new SelectRecordAll();
	public String execute() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addHeader("Content-Type", "text/html;charset=UTF-8");

		if(type.equals("hole")){
			for(Long x:holesId){
			
				if(!select.selectHoleStatus(x).isEmpty()){
					response.getWriter().print("pole");
					return null;
				}
			}
			for(Long x:holesId){
				try{
					deleter.deleteObjectP(x);
				}catch(Exception e){
					response.getWriter().print("error");
					return null;
				}
			
			}
			for(Long x:bracketsId){
				try{
					deleter.deleteObjectP(x);
				}catch(Exception e){
					response.getWriter().print("error");
					return null;
				}
			}
		}
		if(type.equals("profile")){
			for(Long x:holesId){
				
				if((!select.selectProfileStatus(x).isEmpty())||(!select.selectProfileBracketStatus(x).isEmpty())||(!select.selectProfileWirecableStatus(x).isEmpty())){
					response.getWriter().print("pole");
					return null;
				}
			}
			for(Long x:holesId){
				try{
					deleter.deleteProfile(x);
				}catch(Exception e){
					response.getWriter().print("error");
					return null;
				}
			
			}
		}
		response.getWriter().print("success");
		return null;
	}
	public List<Long> getHolesId() {
		return holesId;
	}
	public void setHolesId(String holesId) {
		String res[] = holesId.split(",");
		for(int i=0; i<res.length; ++i){
			this.holesId.add(Long.parseLong(res[i]));
		}
	}
	public DeleteObject getDeleter() {
		return deleter;
	}
	public void setDeleter(DeleteObject deleter) {
		this.deleter = deleter;
	}
	public SelectRecordAll getSelect() {
		return select;
	}
	public void setSelect(SelectRecordAll select) {
		this.select = select;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
