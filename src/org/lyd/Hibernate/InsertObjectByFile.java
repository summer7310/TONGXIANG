package org.lyd.Hibernate;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jxl.Cell;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.lyd.util.HibernateSessionFactory;
import org.resource.objectclass.GeoElectrical;
import org.resource.objectclass.PdElCnBranch;
import org.resource.objectclass.PdElLnIntermission;
import org.resource.objectclass.PdElPtContainerV;
import org.resource.objectclass.PdEwPointBuilding;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
public class InsertObjectByFile {
	private static Session s=null;
	private static Transaction tx=null;
	private static final long STATICID = 1000000000000000l;
//	public void insertBranchByFile(File branchFile) throws Exception{
//		PdElCnBranch branch=new PdElCnBranch();
//		s=HibernateSessionFactory.currentSession();
//		tx=s.beginTransaction();
//		Workbook book=Workbook.getWorkbook(branchFile);
//		Sheet sheet=book.getSheet(0);
//		int rowSize=sheet.getRows();
//		for(int i=1;i<rowSize;i++){
//			branch.setName(sheet.getCell(0, i).getContents());
//			branch.setRfid(Integer.parseInt(sheet.getCell(1, i).getContents()));
//			
//		}
//	}
	public void insertObjectByFile(File objectFile,File xmlObjectFile) throws Exception{
		Workbook book = Workbook.getWorkbook(objectFile);
		Sheet sheet = book.getSheet(0);
		int rowSize = sheet.getRows();
		SAXReader reader = new  SAXReader();
		Document documentXML = reader.read(xmlObjectFile);
		Element elementRoot = documentXML.getRootElement();
		Attribute attributeRoot = elementRoot.attribute("name");
		Attribute attributeTypeRoot = elementRoot.attribute("type");
		Attribute attributeGeoType = elementRoot.attribute("geotype");
		int geoType = Integer.parseInt(attributeGeoType.getValue());
		int objectType = Integer.parseInt(attributeTypeRoot.getValue());
		int zIndex = Integer.parseInt(elementRoot.attribute("zIndex").getValue());
		Class<?> classObject = Class.forName("org.resource.objectclass."+attributeRoot.getValue());
		
		//Object obj = classObject.newInstance();
		for(int i=1;i<rowSize;i++){
			s = HibernateSessionFactory.currentSession();
			tx = s.beginTransaction();
			Object obj = classObject.newInstance();
			GeoElectrical objGeoElectrical = new GeoElectrical();
			setter(objGeoElectrical,"zIndex",zIndex,Integer.class);
			List listColumn = documentXML.selectNodes("//classes/property/list/@location");
			List listProperty = documentXML.selectNodes("//classes/property/@name");
			List listType = documentXML.selectNodes("//classes/property/@type");
			List listIsGeoData = documentXML.selectNodes("//classes/property/@isgeodata");
			Iterator iteratorProperty = listProperty.iterator();
			Iterator iteratorColumn = listColumn.iterator();
			Iterator iteratorType = listType.iterator();
			Iterator iteratorIsGeoData = listIsGeoData.iterator();
			setter(obj,"objectType",objectType,Integer.class);
			setter(objGeoElectrical,"objectType",objectType,Integer.class);
			while(iteratorProperty.hasNext()){
				Attribute attributeColumn = (Attribute)iteratorColumn.next();
				int cellsColumn = Integer.parseInt(attributeColumn.getValue());
				Cell cell = sheet.getCell(cellsColumn, i);	
				Attribute attributeIsGeoData = (Attribute)iteratorIsGeoData.next();
				Boolean isGeoData = Boolean.parseBoolean(attributeIsGeoData.getValue());
				//String cellValue = sheet.getCell(cellsColumn, i).getContents();
				Attribute attributeProperty = (Attribute)iteratorProperty.next();
				String propertyName = attributeProperty.getValue();
				Attribute attributeType = (Attribute)iteratorType.next();
				Class<?> classType = Class.forName(attributeType.getValue());
				if(classType == java.lang.String.class){
					//LabelCell cellWithType = (LabelCell)cell;
					String stringValue = cell.getContents();	
					setter(obj,propertyName,stringValue,classType);
					if(isGeoData){
						setter(objGeoElectrical,propertyName,stringValue,classType);
					}
				}
				if(classType == java.lang.Integer.class){
					NumberCell cellWithType = (NumberCell)cell;
					Integer integerValue = ((Double)cellWithType.getValue()).intValue();
					setter(obj, propertyName, integerValue, classType);
					if(isGeoData){
						setter(objGeoElectrical,propertyName,integerValue,classType);
					}
				}
				if(classType == java.lang.Double.class){ 
					NumberCell cellWithType = (NumberCell)cell;
					Double doubleValue = cellWithType.getValue();
					setter(obj, propertyName, doubleValue, classType);
					if(isGeoData){
						setter(objGeoElectrical,propertyName,doubleValue,classType);
					}
				}
				if(classType == java.util.Date.class){
					DateCell cellWithType = (DateCell)cell;
					Date dateValue = cellWithType.getDate();
					setter(obj, propertyName, dateValue, classType);
					if(isGeoData){
						setter(objGeoElectrical,propertyName,dateValue,classType);
					}
				}
				if(classType == java.lang.Float.class){
					NumberCell cellWithType = (NumberCell)cell;
					Float floatValue = ((Double)cellWithType.getValue()).floatValue();
					setter(obj,propertyName,floatValue,classType);
				}
				if(classType == java.lang.Boolean.class){
					LabelCell cellWithType = (LabelCell)cell;
					String stringValue = cellWithType.getString();
					Boolean booleanValue = false;
					if(stringValue.equals("是")){
						 booleanValue = true;
					}else if(stringValue.equals("否")){
						booleanValue = false;
					}
					setter(obj,propertyName,booleanValue,classType);
				}
				
			}
			s.save(obj);
			Integer objectAutoId = (Integer)getter(obj,"autoId");
			
			Long objectStaticId = STATICID+objectAutoId;
			setter(obj,"staticId",objectStaticId,Long.class);
			setter(objGeoElectrical,"staticId",objectStaticId,Long.class);
			s.update(obj);
			if(geoType == 1){
				Double longitude = (Double)getter(obj,"longitude");
				Double latitude = (Double)getter(obj,"latitude");
				Geometry geom = getGeometry(longitude, latitude);
				setter(objGeoElectrical,"geometryData",geom,Geometry.class);
			}
			if(geoType == 2){
				//Integer startBuildingType = (Integer)getter(obj,"startBuildingType");
				Integer startBuilding = (Integer)getter(obj,"startBuilding");
				//Integer endBuildingType = (Integer)getter(obj,"endBuildingType");
				Integer endBuilding = (Integer)getter(obj,"endBuilding");
				//Class<?> classStartBuilding = Class.forName("org.resource.objectclass."+typeIdToCode(startBuildingType));
				//Object objStartBuilding = classStartBuilding.newInstance();
				//objStartBuilding = s.get(classStartBuilding, startBuilding);
				PdEwPointBuilding startPointBuilding = (PdEwPointBuilding)s.get(PdEwPointBuilding.class, startBuilding);
				PdEwPointBuilding endPointBuilding = (PdEwPointBuilding)s.get(PdEwPointBuilding.class, endBuilding);
				Double x1 = (Double)getter(startPointBuilding,"longitude");
				Double y1 = (Double)getter(startPointBuilding,"latitude");
				Double x2 = (Double)getter(endPointBuilding,"longitude");
				Double y2 = (Double)getter(endPointBuilding,"latitude");
				Geometry geom = getGeometry(x1, y1, x2, y2);
				setter (objGeoElectrical,"geometryData",geom,Geometry.class);
			}
			if(geoType == 3){
				Integer startIntermissionId = (Integer)getter(obj,"startIntermission");
				Integer endIntermissionId = (Integer)getter(obj,"endIntermission");
				PdElLnIntermission startIntermission = (PdElLnIntermission)s.get(PdElLnIntermission.class, startIntermissionId);
				PdElLnIntermission endIntermission = (PdElLnIntermission)s.get(PdElLnIntermission.class, endIntermissionId);
				Integer startContainerId = (Integer)getter(startIntermission,"containerId");
				Integer endContainerId = (Integer)getter(endIntermission,"containerId");
				PdElPtContainerV startContainer = (PdElPtContainerV)s.get(PdElPtContainerV.class, startContainerId);
				PdElPtContainerV endContainer = (PdElPtContainerV)s.get(PdElPtContainerV.class, endContainerId);
				Double x1 = (Double)getter(startContainer,"longitude");
				Double y1 = (Double)getter(startContainer,"latitude");
				Double x2 = (Double)getter(endContainer,"longitude");
				Double y2 = (Double)getter(endContainer,"latitude");
				Geometry geom = getGeometry(x1, y1, x2, y2);
				setter(objGeoElectrical,"geometryData",geom,Geometry.class);
			}
			s.save(objGeoElectrical);
			tx.commit();
			HibernateSessionFactory.closeSession();
			
		}
		
		
	}
	private void  setter(Object obj,String att,Object value,Class<?> type){
		try{
			Method met = obj.getClass().getMethod("set"+initStr(att),type);
			met.invoke(obj, value);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private Object getter(Object obj,String att){
		try{
			Method met = obj.getClass().getMethod("get"+initStr(att));
			return met.invoke(obj);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	private String initStr(String old){
		String str = old.substring(0,1).toUpperCase()+old.substring(1);
		return str;
	}
	private Geometry getGeometry(double x,double y){
		Coordinate objectPoint = new Coordinate(x,y);
		Geometry geom = new GeometryFactory().createPoint(objectPoint);
		geom.setSRID(4326);
		return geom;
	}
	private Geometry getGeometry(double x1,double y1,double x2,double y2){
		Geometry geom = new GeometryFactory().createLineString(new Coordinate[]{new Coordinate(x1,y1),new Coordinate(x2,y2)});
		geom.setSRID(4326);
		return geom;
	}
	private String typeIdToCode(Integer typeId) throws Exception{
		SAXReader reader = new  SAXReader();
		Document documentXML = reader.read(new File("src\\org\\resource\\excelXML\\ObjectType.xml"));
		List listType = documentXML.selectNodes("//objecttype/type");
		Iterator iteratorType = listType.iterator();
		String typeCode = new String();
		while(iteratorType.hasNext()){
			Element elementType = (Element)iteratorType.next();
			//Iterator iteratorTypeId = elementType.elementIterator("typeid");
			Element elementTypeId = elementType.element("typeid");
			//Element elementTypeId = (Element)iteratorTypeId.next();
			if(Integer.parseInt(elementTypeId.getText()) == typeId){
				Element elementCode = elementType.element("code");
				typeCode = elementCode.getText();
				break;
			}
		}
		return typeCode;
	}
	
}
