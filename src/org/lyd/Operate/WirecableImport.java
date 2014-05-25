package org.lyd.Operate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.lyd.Hibernate.SelectRecordAll;
import org.lyd.util.HibernateSessionFactory;
import org.resource.objectclass.GeoElectrical;
import org.resource.objectclass.PdElCnCableSegment;
import org.resource.objectclass.PdElLnCableline;
import org.resource.objectclass.PdElPtContainerV;
import org.resource.objectclass.PdEwPtTower;
import org.resource.objectclass.PdObjectBase;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

public class WirecableImport {
	//Hibernate的会话
	private static Session s;
	//Hibernate的事务
	private static Transaction tx;
	//静态ID的固定常量
	private static final long STATICID = 1000000000000000l;
	//错误信息
	private List<String> errInfo = new ArrayList<String>();
	//总记录数
	private int countAll;
	//已经完成导入的记录数
	private int countFinish;
	//数据库查询类
	private SelectRecordAll select = new SelectRecordAll();
	
	
	//执行导入的方法
	public int wirecableImport(File wirecableExcel){
		try{
			//读取Excel文件获取工作簿
			Workbook book = Workbook.getWorkbook(wirecableExcel);
			Sheet sheet = book.getSheet(0);
			//获取工作簿的总行数
			int rowSize = sheet.getRows();
			countAll = rowSize;
			countFinish = 0;
			String transStationName = sheet.getCell(1,2).getContents().trim().replaceAll("\n", "");
			List<PdObjectBase> startTransStation = select.selectObjectListByName(transStationName);
			for(int countFlag = 2;countFlag<rowSize;countFlag++){
				//GeoElectrical geo = new GeoElectrical();
				//PdElCnCableSegment wirecable = new PdElCnCableSegment();
				PdElLnCableline cableline = new PdElLnCableline();
				//查询出的起点和终点设备
				List<PdObjectBase> startContainer = new ArrayList<PdObjectBase>();
				List<PdObjectBase> endContainer = new ArrayList<PdObjectBase>();
				String cablelineName = "";
				String wirecableSegmengModel = "";
				//String transStationName = "";
				Integer length=0;
				//电缆段名字获取
				Cell cellName = sheet.getCell(3, countFlag);
				if(cellName.getType()==CellType.EMPTY){
					errInfo.add("导入停止在第"+(countFlag+1)+"行，如果与事实不符，请检查文件");
					countAll=countFlag-2;
					break;
				}
				String name = cellName.getContents().trim().replaceAll("\n", "");
				//起点电站名字获取
				Cell cellTransStationName = sheet.getCell(1,countFlag);
				if(cellTransStationName.getType()!=CellType.EMPTY){
					transStationName = cellTransStationName.getContents().trim().replaceAll("\n", "");
					startTransStation = select.selectObjectListByName(transStationName);
				}
				Cell cellCableline = sheet.getCell(2, countFlag);
				if(cellCableline.getType()!=CellType.EMPTY){
					cablelineName = cellCableline.getContents().trim().replaceAll("\n", "");
					List<PdElLnCableline> cablelineList = select.selectCablelineListByName(cablelineName);
					if(cablelineList.isEmpty()){
						cableline.setName(cablelineName);
						cableline.setObjectType(1010301);
						cableline.setStartTransStation(startTransStation.iterator().next().getStaticId());
						s = HibernateSessionFactory.currentSession();
						tx =s.beginTransaction();
						s.save(cableline);
						Long staticIdCableline = STATICID+cableline.getAutoId();
						cableline.setStaticId(staticIdCableline);
						s.update(cableline);
						tx.commit();
						HibernateSessionFactory.closeSession();
					}else{
						cableline = cablelineList.iterator().next();
					}
				}
				//起点终点获取
				Cell cellStartContainer = sheet.getCell(5, countFlag);
				Cell cellEndContainer = sheet.getCell(6, countFlag);
				//判断表格中起点终点是否填写
				if((cellStartContainer.getType()!=CellType.EMPTY) && (cellEndContainer.getType()!=CellType.EMPTY)){
					startContainer = select.selectObjectListByName(cellStartContainer.getContents().trim().replaceAll("\n", ""));
					endContainer = select.selectObjectListByName(cellEndContainer.getContents().trim().replaceAll("\n", ""));
					//判断所填写的起点和终点是否存在
					if(startContainer.isEmpty()){
						errInfo.add("第"+(countFlag+1)+"行所填的起点不存在，请检查填写是否正确");
						continue;
					}
					if(endContainer.isEmpty()){
						errInfo.add("第"+(countFlag+1)+"行所填的终点不存在，请检查填写是否正确");
						continue;
					}
				}else{
					errInfo.add("第"+(countFlag+1)+"行的起点或者终点为空，此行记录导入发生错误");
					continue;
				}
				
				Cell cellWirecableSegmengModel = sheet.getCell(7, countFlag);
				//Cell cellCommissioningDate = sheet.getCell(8, countFlag);
				Cell cellLength = sheet.getCell(9, countFlag);
				
				//获取型号和长度
				if(cellWirecableSegmengModel.getType()!=CellType.EMPTY){
					wirecableSegmengModel = cellWirecableSegmengModel.getContents();
				}
				if(cellLength.getType()==CellType.NUMBER){
					length = ((Double)((NumberCell)cellLength).getValue()).intValue();
				}
				//s = HibernateSessionFactory.currentSession();
				//tx = s.beginTransaction();
				runCode(name,cableline,wirecableSegmengModel,length,startContainer,endContainer);
				countFinish++;
			}
			
			return 1;
		}catch(BiffException e){
			e.printStackTrace();
			errInfo.add("Excel解析器无法读取Excel。\n"+"------------------------\n"+e.getMessage());
			//info.setErrInfo(errInfo);
			//session.put("ImportInfo", info);
			//return "Excel解析器无法读取Excel。\n"+"------------------------\n"+e.getMessage();
				
			return -1;
		}catch(IOException e){
			e.printStackTrace();
			errInfo.add("程序无法获取文件。\n"+"---------------------------\n"+e.getMessage());
			//info.setErrInfo(errInfo);
			//session.put("ImportInfo", info);
			//return "程序无法获取文件。\n"+"---------------------------\n"+e.getMessage();
			return -1;
		}catch(Exception e){
			e.printStackTrace();
			errInfo.add("不明错误。\n"+"-----------------------------------\n"+e.getMessage());
			//info.setErrInfo(errInfo);
			return -1;
			//session.put("ImportInfo", info);
		}
	}
	
	/*
	 * getter setter方法
	 */
	public List<String> getErrInfo() {
		return errInfo;
	}
	public void setErrInfo(List<String> errInfo) {
		this.errInfo = errInfo;
	}
	public int getCountAll() {
		return countAll;
	}
	public void setCountAll(int countAll) {
		this.countAll = countAll;
	}
	public int getCountFinish() {
		return countFinish;
	}
	public void setCountFinish(int countFinish) {
		this.countFinish = countFinish;
	}
	
	/*
	 * 数据库保存部分
	 */
	public void runCode(String name,PdElLnCableline cableline,String wirecableSegmengModel,Integer length,List<PdObjectBase> startContainer,List<PdObjectBase> endContainer){
		GeoElectrical geo = new GeoElectrical();
		PdElCnCableSegment wirecable = new PdElCnCableSegment();
		//PdElLnCableline cableline = new PdElLnCableline();
		PdObjectBase startBase = startContainer.iterator().next();
		PdObjectBase endBase = endContainer.iterator().next();
		PdElPtContainerV containerV = new PdElPtContainerV();
		PdEwPtTower tower = new PdEwPtTower();
		//线缆段起点终点经纬度
		double x1,y1,x2,y2;
	
		//电缆段数据保存
		wirecable.setName(name);
		wirecable.setObjectType(1010402);
		if(length!=0){
			wirecable.setLength(length);
		}
		if(!wirecableSegmengModel.equals("")){
			wirecable.setWirecableSegmengModel(wirecableSegmengModel);
		}

		wirecable.setStartContainer(startBase.getStaticId());
		wirecable.setStartContainerType(startBase.getObjectType());
		wirecable.setEndContainer(endBase.getStaticId());
		wirecable.setEndContainerType(endBase.getObjectType());
		
		//电缆段空间数据保存
		geo.setName(name);
		geo.setObjectType(1010402);
		geo.setZIndex(20);
		geo.setXOffset(0);
		geo.setYOffset(-15);
		//配电线路保存
		//cableline.setName(cablelineName);
		//cableline.setObjectType(1010301);
		
		
		s = HibernateSessionFactory.currentSession();
		tx = s.beginTransaction();
		//s.save(cableline);
		Long staticIdCableline = cableline.getStaticId();
		//cableline.setStaticId(staticIdCableline);
		//s.update(cableline);
		wirecable.setBelongsCableline(staticIdCableline);
		s.save(wirecable);
		Long staticIdWirecable = wirecable.getAutoId()+STATICID;
		wirecable.setStaticId(staticIdWirecable);
		s.update(wirecable);
		geo.setStaticId(staticIdWirecable);
		//获得电缆段的起点经纬度
		if(startBase.getObjectType()==1020102){
			tower = (PdEwPtTower)s.get(PdEwPtTower.class, startBase.getAutoId());
			x1 = tower.getLongitude();
			y1 = tower.getLatitude();
			//tower.setBelongsCableline(staticIdCableline);
			//s.update(tower);
		}else{
			containerV = (PdElPtContainerV)s.get(PdElPtContainerV.class, startBase.getAutoId());
			x1 = containerV.getLongitude();
			y1 = containerV.getLatitude();
			//containerV.setBelongsCableline(staticIdCableline);
			//s.update(containerV);
		}
		//获得电缆段终点的经纬度
		if(endBase.getObjectType()==1020102){
			tower = (PdEwPtTower)s.get(PdEwPtTower.class, endBase.getAutoId());
			x2 = tower.getLongitude();
			y2 = tower.getLatitude();
			//tower.setBelongsCableline(staticIdCableline);
			//s.update(tower);
		}else{
			containerV = (PdElPtContainerV)s.get(PdElPtContainerV.class, endBase.getAutoId());
			x2 = containerV.getLongitude();
			y2 = containerV.getLatitude();
			//containerV.setBelongsCableline(staticIdCableline);
			//s.update(containerV);
		}
		geo.setGeometryData(getGeometry(x1, y1, x2, y2));
		s.save(geo);
		tx.commit();
		HibernateSessionFactory.closeSession();
		
	}
	//线段的经纬度转换成空间数据
	private Geometry getGeometry(double x1,double y1,double x2,double y2){
		Geometry geom = new GeometryFactory().createLineString(new Coordinate[]{new Coordinate(x1,y1),new Coordinate(x2,y2)});
		geom.setSRID(4326);
		return geom;
	}
}
