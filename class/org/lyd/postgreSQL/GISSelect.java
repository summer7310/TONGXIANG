package org.lyd.postgreSQL;
import java.sql.*;
import org.lyd.postgreSQL.ConnectionSQL;
public class GISSelect {
	private ConnectionSQL powerDataBase;
	public GISSelect(String url,String userName,String password){
		this.powerDataBase=new ConnectionSQL(url, userName, password);
	}
	public String getTreeContents(int id,int type) throws Exception{
		powerDataBase.getSQL("SELECT * FROM PROPERTY.\"PD_OBJECT_TYPE\" WHERE \"TYPE_ID\"="+type+"");
		ResultSet rsType=powerDataBase.Execute();
		rsType.next();
		//int typeId=rsType.getInt(1);
		String typeCode=rsType.getString(3);
		int upBelongType=rsType.getInt(4);
		int downBelongType=rsType.getInt(5);
		int i=0;
		int rowCount;
		StringBuffer treeDirtoryString=new StringBuffer("[");
		if(type==1030101){
			treeDirtoryString.append("{'id':\"1010401-"+id+"\",'name':\"±ê×¼¶Î\",'isParent':true,'iconSkin':\"tree_1010401\"}");
			powerDataBase.getSQL("SELECT \"TYPE_ID\",\"TYPE_NAME\" FROM PROPERTY.\"PD_OBJECT_TYPE\" WHERE \"UPBELONG_TYPE\"=1010301 AND \"TYPE_ID\"!=1010401");
			ResultSet rsTypeAnother=powerDataBase.Execute();
			rsTypeAnother.last();
			rowCount=rsTypeAnother.getRow();
			rsTypeAnother.beforeFirst();
			ObjectBase[] treeDirtoryType=new ObjectBase[rowCount];
			while(rsTypeAnother.next()){
				treeDirtoryType[i]=new ObjectBase();
				treeDirtoryType[i].id=id;
				treeDirtoryType[i].name=rsTypeAnother.getString("TYPE_NAME");
				treeDirtoryType[i].isParent=true;
				treeDirtoryType[i].type=rsTypeAnother.getInt("TYPE_ID");
				treeDirtoryString.append("{'id':\""+treeDirtoryType[i].type+"-"+treeDirtoryType[i].id+"\",'name':\""+treeDirtoryType[i].name+"\",'isParent':"+treeDirtoryType[i].isParent+",'iconSkin':\"tree_"+treeDirtoryType[i].type+"\"}");
				if(i<rowCount-1){	
					treeDirtoryString.append(",");
				}
				i++;
			}
			
		}else{
			StringBuffer treeBodySql=new StringBuffer("SELECT \"AUTO_ID\",\"NAME\" FROM PROPERTY.\""+typeCode+"\" WHERE ");
			switch(type){
			case 1010301:{
				treeBodySql.append("\"START_TRANS_STATION\"="+id+"");
				break;
			}
			default:{
				treeBodySql.append("\"BELONGS_CABLELINE\"="+id+"");
				break;
			}
			}
			powerDataBase.getSQL(treeBodySql.toString());
			ResultSet rs=powerDataBase.Execute();
			rs.last();
			rowCount=rs.getRow();
			rs.beforeFirst();
			ObjectBase[] treeDirtory=new ObjectBase[rowCount];
			while(rs.next()){
				treeDirtory[i]=new ObjectBase();
				treeDirtory[i].name=rs.getString("NAME");
				treeDirtory[i].id=rs.getInt("AUTO_ID");
				treeDirtory[i].isParent=true;
				treeDirtory[i].type=downBelongType;
				treeDirtoryString.append("{'id':\""+treeDirtory[i].type+"-"+treeDirtory[i].id+"\",'name':\""+treeDirtory[i].name+"\",'isParent':"+treeDirtory[i].isParent+",'iconSkin':\"tree_"+type+"\"}");
				if(i<rowCount-1){
					treeDirtoryString.append(",");
				}
				
				
				i++;
			}
			rs.close();
		}
		treeDirtoryString.append("]");
		
		powerDataBase.CloseResultSet();
		powerDataBase.CloseStatement();
		powerDataBase.CloseConnection();
		return treeDirtoryString.toString();
		
//		StringBuffer treeBodySql=new StringBuffer("SELECT \"AUTO_ID\",\"NAME\" FROM PROPERTY.\""+typeCode+"\" WHERE ");
//		switch(type){
//		case 1010301:{
//			treeBodySql.append("\"START_TRANS_STATION\"="+id+"");
//			break;
//		}
//		default:{
//			treeBodySql.append("\"BELONGS_CABLELINE\"="+id+"");
//			break;
//		}
//		}
//		powerDataBase.getSQL(treeBodySql.toString());
//		ResultSet rs=powerDataBase.Execute();
//		
//		rs.last();
//		int rowCount=rs.getRow();
//		rs.beforeFirst();
//		ObjectBase[] treeDirtory=new ObjectBase[rowCount];
//		int i=0;
//		StringBuffer treeDirtoryString=new StringBuffer("[");
//		if(upBelongType==1010301){
//			while(rs.next()){
//				treeDirtory[i]=new ObjectBase();
//				treeDirtory[i].name=rs.getString("NAME");
//				treeDirtory[i].id=rs.getInt("AUTO_ID");
//				treeDirtory[i].isParent=true;
//				treeDirtory[i].type=downBelongType;
//				treeDirtoryString.append("{'id':\""+treeDirtory[i].type+"-"+treeDirtory[i].id+"\",'name':\""+treeDirtory[i].name+"\",'isParent':"+treeDirtory[i].isParent+",'iconSkin':\"tree_"+type+"\"},");
//				
//				
//				
//				i++;
//			}
//			powerDataBase.getSQL("SELECT \"TYPE_ID\",\"TYPE_NAME\" FROM PROPERTY.\"PD_OBJECT_TYPE\" WHERE \"UPBELONG_TYPE\"="+upBelongType+" AND \"TYPE_ID\"!=1010401");
//			ResultSet rsTypeAnother=powerDataBase.Execute();
//			rsTypeAnother.last();
//			rowCount=rsTypeAnother.getRow();
//			rsTypeAnother.beforeFirst();
//			ObjectBase[] treeDirtoryType=new ObjectBase[rowCount];
//			i=0;
//			while(rsTypeAnother.next()){
//				treeDirtoryType[i]=new ObjectBase();
//				treeDirtoryType[i].id=id;
//				treeDirtoryType[i].name=rsTypeAnother.getString("TYPE_NAME");
//				treeDirtoryType[i].isParent=true;
//				treeDirtoryType[i].type=rsTypeAnother.getInt("TYPE_ID");
//				treeDirtoryString.append("{'id':\""+treeDirtoryType[i].type+"-"+treeDirtoryType[i].id+"\",'name':\""+treeDirtoryType[i].name+"\",'isParent':"+treeDirtoryType[i].isParent+",'iconSkin':\"tree_"+treeDirtoryType[i].type+"\"}");
//				if(i<rowCount-1){	
//					treeDirtoryString.append(",");
//				}
//			}
//		}else{
//			while(rs.next()){
//				treeDirtory[i]=new ObjectBase();
//				treeDirtory[i].name=rs.getString("NAME");
//				treeDirtory[i].id=rs.getInt("AUTO_ID");
//				treeDirtory[i].isParent=true;
//				treeDirtory[i].type=downBelongType;
//				treeDirtoryString.append("{'id':\""+downBelongType+"-"+treeDirtory[i].id+"\",'name':\""+treeDirtory[i].name+"\",'isParent':"+treeDirtory[i].isParent+",'iconSkin':\"tree_"+type+"\"}");
//				if(i<rowCount-1){
//					treeDirtoryString.append(",");
//				}
//				i++;
//			}
//		}
//		treeDirtoryString.append("]");
//		rs.close();
//		powerDataBase.CloseResultSet();
//		powerDataBase.CloseStatement();
//		powerDataBase.CloseConnection();
//		
//		return treeDirtoryString.toString(); 
	}
	public String getTreeContents(int type) throws Exception{
		powerDataBase.getSQL("SELECT * FROM PROPERTY.\"PD_OBJECT_TYPE\" WHERE \"TYPE_ID\"="+type+"");
		ResultSet rsType=powerDataBase.Execute();
		rsType.next();
		//int typeId=rsType.getInt(1);
		String typeCode=rsType.getString(3);
		int downBelongType=rsType.getInt(5);
		powerDataBase.getSQL("SELECT \"AUTO_ID\",\"NAME\" FROM PROPERTY.\""+typeCode+"\"");
		ResultSet rs=powerDataBase.Execute();
		rs.last();
		int rowCount=rs.getRow();
		rs.beforeFirst();
		ObjectBase[] treeDirtory=new ObjectBase[rowCount];
		int i=0;
		StringBuffer treeDirtoryString=new StringBuffer("[");
		while(rs.next()){
			treeDirtory[i]=new ObjectBase();
			treeDirtory[i].name=rs.getString("NAME");
			treeDirtory[i].id=rs.getInt("AUTO_ID");
			treeDirtory[i].isParent=true;
			treeDirtory[i].type=downBelongType;
			treeDirtoryString.append("{'id':\""+downBelongType+"-"+treeDirtory[i].id+"\",'name':\""+treeDirtory[i].name+"\",'isParent':"+treeDirtory[i].isParent+",'iconSkin':\"tree_"+type+"\"}");
			if(i<rowCount-1){
				treeDirtoryString.append(",");
			}
			i++;
		}
		treeDirtoryString.append("]");
		rs.close();
		powerDataBase.CloseResultSet();
		powerDataBase.CloseStatement();
		powerDataBase.CloseConnection();
		
		return treeDirtoryString.toString(); 

	}
//	public String getCableLine(int id) throws Exception{
//		powerDataBase.getSQL("SELECT \"AUTO_ID\",\"NAME\" FROM PROPERTY.\"PD_EL_LN_CABLELINE\" WHERE \"START_TRANS_STATION\"="+id+"");
//		ResultSet rs=powerDataBase.Execute();
//		rs.last();
//		int rowCount=rs.getRow();
//		rs.beforeFirst();
//		ObjectBase[] cableLine=new ObjectBase[rowCount];
//		int i=0;
//		StringBuffer cableLineString=new StringBuffer("[");
//		while(rs.next()){
//			cableLine[i]=new ObjectBase();
//			cableLine[i].name=rs.getString("NAME");
//			cableLine[i].id=rs.getInt("AUTO_ID");
//			cableLine[i].status=true;
//			if(i<rowCount-1){
//				cableLineString.append("{'id':"+cableLine[i].id+",'name':\""+cableLine[i].name+"\",'status':"+cableLine[i].status+",'iconSkin':\"pIcon01\"},");
//			}else{
//				cableLineString.append("{'id':"+cableLine[i].id+",'name':\""+cableLine[i].name+"\",'status':"+cableLine[i].status+",'iconSkin':\"pIcon01\"}");
//			}
//			i++;
//		}
//		cableLineString.append("]");
//		rs.close();
//		powerDataBase.CloseResultSet();
//		powerDataBase.CloseStatement();
//		powerDataBase.CloseConnection();
//		return cableLineString.toString();
//	}
//	public String getBranchBox(int id) throws Exception{
//		powerDataBase.getSQL("SELECT \"AUTO_ID\",\"NAME\" FROM PROPERTY.\"PD_EL_CN_BRANCH_BOX\" WHERE \"BELONGS_CABLELINE\"="+id+"");
//		ResultSet rs=powerDataBase.Execute();
//		rs.last();
//		int rowCount=rs.getRow();
//		rs.beforeFirst();
//		ObjectBase[] branchBox=new ObjectBase[rowCount];
//		int i=0;
//		StringBuffer branchBoxString=new StringBuffer("[");
//		while(rs.next()){
//			branchBox[i]=new ObjectBase();
//			branchBox[i].name=rs.getString("NAME");
//			branchBox[i].id=rs.getInt("AUTO_ID");
//			branchBox[i].status=true;
//			if(i<rowCount-1){
//				branchBoxString.append("{'id':"+branchBox[i].id+",'name':\""+branchBox[i].name+"\",'status':"+branchBox[i].status+",'iconSkin':\"pIcon01\"},");
//			}else{
//				branchBoxString.append("{'id':"+branchBox[i].id+",'name':\""+branchBox[i].name+"\",'status':"+branchBox[i].status+",'iconSkin':\"pIcon01\"}");
//			}
//			i++;
//		}
//		branchBoxString.append("]");
//		rs.close();
//		powerDataBase.CloseResultSet();
//		powerDataBase.CloseStatement();
//		powerDataBase.CloseConnection();
//		return branchBoxString.toString();
//	}
}
