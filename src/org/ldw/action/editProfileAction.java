package org.ldw.action;
import com.opensymphony.xwork2.ActionSupport;
/*
 * 改变剖面形状
 */
public class editProfileAction extends ActionSupport  {
	private int points;
	public String execute() throws Exception {
		return SUCCESS;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	 
}
