package org.ldw.moudles;
import org.lyd.postgreSQL.*;

public class handleTree {
	private String fatherId;
	private String fatherLevel;
	private String fatherName;
	private String fatherType;
	private GISSelect conn;
	private static final String wrongMsg = "��ѯ����";
	
	public handleTree(){
		conn = new GISSelect("localhost:5432/TONGXIANG", "postgres", "1990911");
	}
	
	public void initVar(String id, String n, String lev){
		if(id != null){
			String[] array = id.split("-");
			this.fatherId = array[1];
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
	/* ��ȡ������
	 * ����ֵ��
	 * -1:��Ŀ¼
	 * 0:���վ
	 */
	private int getType(){
		if(fatherLevel==null){
			return  -1;
		}else if(fatherType.equals("city")){
			return 0;
		}else{
			return 1;
		}
	}
	//��ȡ����Ŀ¼����
	public String getData(){
		
		int t = this.getType();
		switch(t){
			case -1: return "[{id:\"city-1\", name:\"TONGXIANG\", isParent:true, iconSkin:\"city\"}]";
			case 0: return getTrans();
			default: return getContent(Integer.parseInt(fatherId), Integer.parseInt(fatherType));//return "[{id:\"city\", name:\""+t+"\", isParent:true}]";
		}
	}
	
	//��ѯ��·
	private String getContent(int id, int type){
		try{
			return conn.getTreeContents(id, type);
		} catch(Exception e) {
			return wrongMsg;//e.message.toString();
		}
	}
	
	//��ѯ���վ
	private String getTrans(){
		try{
			return conn.getTreeContents(1010201);
		} catch(Exception e) {
			return wrongMsg;//e.message.toString();
		}
	}
}
