package org.lyd.postgreSQL;

import java.sql.*;
public class ConnectionSQL {
	//定义数据库驱动程序
	private String DBDRIVER;
	//定义数据库连接地址
	private String url;
	//定义数据库连接用户名
	private String userName;
	//定义数据库连接密码
	private String password;
	//定义查询语句
	private String sql;
	//定义数据库连接类
	private Connection conn;
	//定义数据库操作类
	private Statement stmt;
	//定义查询接受类
	private ResultSet rs=null;
	//构造函数
	public ConnectionSQL(String url,String userName,String password){
		//postgreSQL数据库驱动程序
		this.DBDRIVER="org.postgresql.Driver";
		this.url="jdbc:postgresql://"+url;
		this.userName=userName;
		this.password=password;
		//加载数据库驱动程序
		try{
			Class.forName(DBDRIVER);
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	//获取SQL语句
	public void getSQL(String sql){
		this.sql=sql;
	}
	//执行SQL语句 并返回ResultSet类
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
	//关闭ResultSet类
	public void CloseResultSet(){
		try{	
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	//关闭数据库操作类
	public void CloseStatement(){
		try{
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	//关闭数据库连接类
	public void CloseConnection(){
		try{
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}	
}
