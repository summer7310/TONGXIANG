package org.lyd.postgreSQL;

import java.sql.*;
public class ConnectionSQL {
	//�������ݿ���������
	private String DBDRIVER;
	//�������ݿ����ӵ�ַ
	private String url;
	//�������ݿ������û���
	private String userName;
	//�������ݿ���������
	private String password;
	//�����ѯ���
	private String sql;
	//�������ݿ�������
	private Connection conn;
	//�������ݿ������
	private Statement stmt;
	//�����ѯ������
	private ResultSet rs=null;
	//���캯��
	public ConnectionSQL(String url,String userName,String password){
		//postgreSQL���ݿ���������
		this.DBDRIVER="org.postgresql.Driver";
		this.url="jdbc:postgresql://"+url;
		this.userName=userName;
		this.password=password;
		//�������ݿ���������
		try{
			Class.forName(DBDRIVER);
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	//��ȡSQL���
	public void getSQL(String sql){
		this.sql=sql;
	}
	//ִ��SQL��� ������ResultSet��
	public ResultSet Execute(){
		try{
			conn=DriverManager.getConnection(url, userName, password);
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			rs=stmt.executeQuery(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
		
		
	}
	//�ر�ResultSet��
	public void CloseResultSet(){
		try{	
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	//�ر����ݿ������
	public void CloseStatement(){
		try{
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	//�ر����ݿ�������
	public void CloseConnection(){
		try{
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}	
}
