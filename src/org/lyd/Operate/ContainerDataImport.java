package org.lyd.Operate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.lyd.util.HibernateSessionFactory;
import org.resource.objectclass.GeoCivilEngineering;
import org.resource.objectclass.GeoElectrical;
import org.resource.objectclass.PdCity;
import org.resource.objectclass.PdElCnBranchBox;
import org.resource.objectclass.PdElCnRingmainunit;
import org.resource.objectclass.PdElCnSwitchStation;
import org.resource.objectclass.PdElCnTransformersubstation;
import org.resource.objectclass.PdEwPtTower;

import com.opensymphony.xwork2.ActionContext;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

public class ContainerDataImport {
	//Hibernate会话层
	private static Session s;
	//Hibernate事务
	private static Transaction tx;
	//静态变量 静态ID的固定常量
	private static final long STATICID = 1000000000000000l;
	//会话层
	//private Map session = ActionContext.getContext().getSession();
	//总导入记录条数
	private int numSum;
	//已经导入数记录条数
	private int numImported;
	//错误信息
	private List<String> errInfo = new ArrayList<String>();
	//类型匹配
	private ObjectTypeMap map = new ObjectTypeMap();
	//执行导入方法
	public void containerDataImport(File containerExcel){
		try{
			Workbook book = Workbook.getWorkbook(containerExcel);
			//获取工作簿
			Sheet sheet = book.getSheet(0);
			//取得工作簿的总行数
			int rowSize = sheet.getRows();
			numSum = rowSize-2;
			for(int numFlag = 2;numFlag<rowSize;numFlag++){
				Cell cellName = sheet.getCell(1, numFlag);
				Cell cellLongitude = sheet.getCell(2, numFlag);
				Cell cellLatitude = sheet.getCell(3, numFlag);
				Cell cellType = sheet.getCell(4, numFlag);
				if(cellName.getType() == CellType.EMPTY){
					numSum = numFlag-2;
					errInfo.add("导入停止于Excel表的第"+(numFlag+1)+"行，设备名称为空，如果与事实不符，请检查文件");
					break;
				}
				String name = cellName.getContents().trim().replaceAll("\n", "");                  
				String typeString = cellType.getContents();
				Double longitude = 0.0;
				Double latitude = 0.0;
				if(cellLongitude.getType() == CellType.NUMBER){
					longitude = ((NumberCell)cellLongitude).getValue();
				}else{
					errInfo.add("第"+(numFlag+1)+"行经度为空，此行没有导入");
					continue;
				}
				if(cellLatitude.getType() == CellType.NUMBER){
					latitude = ((NumberCell)cellLatitude).getValue();
				}else{
					errInfo.add("第"+(numFlag+1)+"行纬度为空，此行没有导入");
					continue;
				}
				//Map typeId= new ObjectTypeMap(); 
				runCode(name,longitude,latitude,(Integer)map.getType().get(typeString));
				numImported++;
			}
//			System.out.println("总数"+numSum+" 已经导入的记录数"+numImported);
//			for(String x:errInfo){
//				System.out.println(x);
//			}
		}catch(BiffException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//将EXCEl数据存入数据库
	private void runCode(String name,Double longitude,Double latitude,Integer type){
		Long staticId;
		s = HibernateSessionFactory.currentSession();
		tx = s.beginTransaction();
		//变电站
		if(type == 1010201){
			PdElCnTransformersubstation ui = new PdElCnTransformersubstation();
			GeoElectrical geo = new GeoElectrical();
			PdCity city = (PdCity)s.get(PdCity.class, 1);
			ui.setName(name);
			geo.setName(name);
			ui.setLongitude(longitude);
			ui.setLatitude(latitude);
			ui.setPdCity(city);
			geo.setGeometryData(getGeometry(longitude,latitude));
			ui.setObjectType(type);
			geo.setObjectType(type);
			s.save(ui);
			staticId = ui.getAutoId()+STATICID;
			ui.setStaticId(staticId);
			geo.setStaticId(staticId);
			geo.setZIndex(30);
			geo.setXOffset(0);
			geo.setYOffset(-15);
			s.update(ui);
			s.save(geo);
				
		}
		//环网柜
		if(type == 1010204){
			PdElCnRingmainunit ui = new PdElCnRingmainunit();
			GeoElectrical geo = new GeoElectrical(); 
			ui.setName(name);
			geo.setName(name);
			ui.setLongitude(longitude);
			ui.setLatitude(latitude);
			geo.setGeometryData(getGeometry(longitude,latitude));
			ui.setObjectType(type);
			geo.setObjectType(type);
			s.save(ui);
			staticId = ui.getAutoId()+STATICID;
			ui.setStaticId(staticId);
			geo.setStaticId(staticId);
			geo.setZIndex(30);
			geo.setXOffset(0);
			geo.setYOffset(-15);
			s.update(ui);
			s.save(geo);
		}
		//开闭所
		if(type == 1010202){
			PdElCnSwitchStation ui = new PdElCnSwitchStation();
			GeoElectrical geo = new GeoElectrical(); 
			ui.setName(name);
			geo.setName(name);
			ui.setLongitude(longitude);
			ui.setLatitude(latitude);
			geo.setGeometryData(getGeometry(longitude,latitude));
			ui.setObjectType(type);
			geo.setObjectType(type);
			s.save(ui);
			staticId = ui.getAutoId()+STATICID;
			ui.setStaticId(staticId);
			geo.setStaticId(staticId);
			geo.setZIndex(30);
			geo.setXOffset(0);
			geo.setYOffset(-15);
			s.update(ui);
			s.save(geo);
		}
		//分支箱
		if(type == 1010205){
			PdElCnBranchBox ui = new PdElCnBranchBox();
			GeoElectrical geo = new GeoElectrical(); 
			ui.setName(name);
			geo.setName(name);
			ui.setLongitude(longitude);
			ui.setLatitude(latitude);
			geo.setGeometryData(getGeometry(longitude,latitude));
			ui.setObjectType(type);
			geo.setObjectType(type);
			s.save(ui);
			staticId = ui.getAutoId()+STATICID;
			ui.setStaticId(staticId);
			geo.setStaticId(staticId);
			geo.setZIndex(30);
			geo.setXOffset(0);
			geo.setYOffset(-15);
			s.update(ui);
			s.save(geo);
		}
		//杆塔
		if(type == 1020102){
			PdEwPtTower ui = new PdEwPtTower();
			GeoCivilEngineering geo = new GeoCivilEngineering(); 
			ui.setName(name);
			geo.setName(name);
			ui.setLongitude(longitude);
			ui.setLatitude(latitude);
			geo.setGeometryData(getGeometry(longitude,latitude));
			ui.setObjectType(type);
			geo.setObjectType(type);
			s.save(ui);
			staticId = ui.getAutoId()+STATICID;
			ui.setStaticId(staticId);
			geo.setStaticId(staticId);
			geo.setZIndex(30);
			geo.setXOffset(0);
			geo.setYOffset(15);
			s.update(ui);
			s.save(geo);
		}
		tx.commit();
		HibernateSessionFactory.closeSession();
	}
	//经纬度转换成空间数据
	private Geometry getGeometry(double x,double y){
		Coordinate objectPoint = new Coordinate(x,y);
		Geometry geom = new GeometryFactory().createPoint(objectPoint);
		geom.setSRID(4326);
		return geom;
	}
	public int getNumSum() {
		return numSum;
	}
	public void setNumSum(int numSum) {
		this.numSum = numSum;
	}
	public int getNumImported() {
		return numImported;
	}
	public void setNumImported(int numImported) {
		this.numImported = numImported;
	}
	public List<String> getErrInfo() {
		return errInfo;
	}
	public void setErrInfo(List<String> errInfo) {
		this.errInfo = errInfo;
	}
	
	
}
