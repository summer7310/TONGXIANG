package org.lyd.Operate;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.lyd.Hibernate.SelectRecordAll;
import org.lyd.util.HibernateSessionFactory;
import org.resource.objectclass.GeoCivilEngineering;
import org.resource.objectclass.GeoElectrical;
import org.resource.objectclass.PdElCnBranch;
import org.resource.objectclass.PdElLnIntermission;
import org.resource.objectclass.PdElPtContainerV;
import org.resource.objectclass.PdEwPointBuilding;
import org.resource.objectclass.PdObjectBase;

import com.opensymphony.xwork2.ActionContext;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
public class InsertObjectByFile {
	private static Session s=null;
	private static Transaction tx=null;
	private static final long STATICID = 1000000000000000l;
	private SelectRecordAll select = new SelectRecordAll();
	//会话层
	//private Map session = ActionContext.getContext().getSession();
	//导入信息
	//private DataImportInfo info = new DataImportInfo();
	//错误信息
	private List<String> errInfo = new ArrayList<String>();
	//总记录数
	private int countAll;
	//已经完成记录数
	private int countFinish;
	//完成记录数
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
	//读取Excel文件的记录
	public void insertObjectByFile(File objectFile,File xmlObjectFile){
		try{
			Workbook book = Workbook.getWorkbook(objectFile);
			Sheet sheet = book.getSheet(0);
			int rowSize = sheet.getRows();
			countAll = rowSize;
//			System.out.println(countAll);
			//获取工作簿
			SAXReader reader = new SAXReader();
			Document documentXML = reader.read(xmlObjectFile);
			//获取根节点
			Element elementRoot = documentXML.getRootElement();
			//获取所要插入对象的名字
			Attribute attributeRootName = elementRoot.attribute("name");
			//获取其空间数据库的类名
			Attribute attributeRootGeoName = elementRoot.attribute("geoName");
			//获取对象的类型ID
			int objectType = Integer.parseInt(elementRoot.attribute("type").getValue());
			//geoType
			int geoType = Integer.parseInt(elementRoot.attribute("geotype").getValue());
			//Z_index在空间上的层次
			int zIndex = Integer.parseInt(elementRoot.attribute("zIndex").getValue());
			//X_offset标签在X方向上的偏移
			int xOffset = Integer.parseInt(elementRoot.attribute("xOffset").getValue());
			//Y_Offset标签在Y方向上的偏移
			int yOffset = Integer.parseInt(elementRoot.attribute("yOffset").getValue());
			//根据名字获取属性类和空间类
			Class<?> classObject = Class.forName("org.resource.objectclass."+attributeRootName.getValue());
			Class<?> classGeoObject = Class.forName("org.resource.objectclass."+attributeRootGeoName.getValue());
			//元组中的名字属性
			Attribute attributeRecordName = (Attribute)documentXML.selectNodes("//classes/name/@location").iterator().next();
			int propertyNameColumn = Integer.parseInt(attributeRecordName.getValue());
//			System.out.println(propertyNameColumn);
			for(int countFlag = 2;countFlag<rowSize;countFlag++){
				Boolean errFlag = true;
//				System.out.println(countFlag);
				Cell cellName = sheet.getCell(propertyNameColumn,countFlag);
//				System.out.println(cellName.getContents());
//				System.out.println(cellName.getType());
				if(cellName.getType()==CellType.EMPTY){
					errInfo.add("导入停止在第"+countFlag+"行，下一行名字为空。如果与事实不符，请检查文件");
					countAll = countFlag-2;
					break;
				}
				//实例化属性类和空间类
				Object obj = classObject.newInstance();
				Object objGeo = classGeoObject.newInstance();
				//获取本记录的名字
				String name = cellName.getContents().trim().replaceAll("\n", "");
				//将name存入到对象中
				setter(obj, "name", name, String.class);
				setter(objGeo, "name", name, String.class);
				//将objectType存入到对象中
				setter(obj, "objectType", objectType,Integer.class);
				setter(objGeo, "objectType", objectType,Integer.class);
				//将zIndex存入到空间对象中
				setter(objGeo, "zIndex", zIndex,Integer.class);
				setter(objGeo,"xOffset",xOffset,Integer.class);
				setter(objGeo,"yOffset",yOffset,Integer.class);
				//读取Excel表的其他列
				List<Element> propertyElementList = documentXML.selectNodes("//classes/property");
				for(Element propertyElement:propertyElementList){
					//获取属性的关联类型
					String relateType = propertyElement.attribute("relateType").getValue();
					//获取可以为空
					Boolean notNull = Boolean.parseBoolean(propertyElement.attribute("notNull").getValue());
					//获取属性的名字
					String propertyName = propertyElement.attribute("name").getValue();
					//获取属性的类型
					String propertyType = propertyElement.attribute("type").getValue();
					Class<?> classType = Class.forName(propertyType);
					//获取Excel表中列号
					int propertyColumn = Integer.parseInt(propertyElement.element("list").attribute("location").getValue());
					//获取Excel表中的列名
					String propertyColumnName = propertyElement.element("list").getText();
					Cell cellProperty = sheet.getCell(propertyColumn, countFlag);
					if(notNull==true&&cellProperty.getType()==CellType.EMPTY){
						errInfo.add("第"+(countFlag+1)+"行的\""+propertyColumnName+"\"列不能为空，此行导入终止");
						errFlag=false;
						break;
					}
					if(relateType.equals("none")){
						if(classType == java.lang.String.class){
							//LabelCell cellWithType = (LabelCell)cell;
							if(cellProperty.getType() != CellType.EMPTY){
								String stringValue = cellProperty.getContents().trim().replaceAll("\n", "");	
								setter(obj,propertyName,stringValue,classType);
							}
						}
						if(classType == java.lang.Integer.class){
							if(cellProperty.getType() == CellType.NUMBER){
								NumberCell cellWithType = (NumberCell)cellProperty;
								Integer integerValue = ((Double)cellWithType.getValue()).intValue();
								setter(obj, propertyName, integerValue, classType);
//								if(isGeoData){
//									setter(objGeoElectrical,propertyName,integerValue,classType);
//								}
							}
						}
						if(classType == java.lang.Double.class){
							if(cellProperty.getType() == CellType.NUMBER){
								NumberCell cellWithType = (NumberCell)cellProperty;
								Double doubleValue = cellWithType.getValue();
								setter(obj, propertyName, doubleValue, classType);
//								if(isGeoData){
//									setter(objGeoElectrical,propertyName,doubleValue,classType);
//								}
							}
						}
						if(classType == java.util.Date.class){
							if(cellProperty.getType() == CellType.DATE){
								DateCell cellWithType = (DateCell)cellProperty;
								Date dateValue = cellWithType.getDate();
								setter(obj, propertyName, dateValue, classType);
//								if(isGeoData){
//									setter(objGeoElectrical,propertyName,dateValue,classType);
//								}
							}
						}
						if(classType == java.lang.Float.class){
							if(cellProperty.getType() == CellType.NUMBER){
								
							
								NumberCell cellWithType = (NumberCell)cellProperty;
								Float floatValue = ((Double)cellWithType.getValue()).floatValue();
								setter(obj,propertyName,floatValue,classType);
							}
						}
						if(classType == java.lang.Boolean.class){
							if(cellProperty.getType() == CellType.BOOLEAN){
								
							
								LabelCell cellWithType = (LabelCell)cellProperty;
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
					}else if(relateType.equals("class")){
						String stringValue = cellProperty.getContents().trim().replaceAll("\n", "");
//						System.out.println(stringValue);
						//查询关联记录
						List<PdObjectBase> objectBaseList = select.selectObjectListByName(stringValue);
//						System.out.println(objectBaseList.size());
						if(objectBaseList.size()!=1){
							errInfo.add("第"+(countFlag+1)+"行的第"+(char)(propertyColumn+65)+"列中关联数据没有在数据库中查询到结果，请检查数据约束的正确性");
							continue;
						}
						PdObjectBase objectBase = objectBaseList.iterator().next();
						setter(obj,propertyName,objectBase.getStaticId(),Long.class);
					}else if(relateType.equals("map")){
						String stringValue = cellProperty.getContents().trim().replaceAll("\n", "");
						String mapClassName = propertyElement.element("map").attribute("name").getValue();
						Class<?> classMapName = Class.forName("org.lyd.Operate."+mapClassName);
						Object objMap = classMapName.newInstance();
						Map typeMap = (Map)getter(objMap, "type");
						if(typeMap.containsKey(stringValue)){
							Integer propertyValue = (Integer)typeMap.get(stringValue);
//							System.out.println(propertyValue);
							setter(obj, propertyName, propertyValue, Integer.class);
						}else{
							errInfo.add("第"+(countFlag+1)+"行的第"+(char)(propertyColumn+65)+"列中的类型无法找到对应的值，请检查填写的数据是否正确");
							continue;
						}
					}
				}
				if(errFlag){
					System.out.println("正在导入第"+(countFlag+1)+"行");
					runCode(obj, objGeo, geoType);
					countFinish++;
				}
			}
//			System.out.println(countAll);
//			System.out.println(countFinish);
//			
//			for(String x:errInfo){
//				System.out.println(x);
//			}
		}catch(IOException e){
			e.printStackTrace();
		}catch(DocumentException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void runCode(Object obj , Object objGeo,Integer geoType){
		try{
			s = HibernateSessionFactory.currentSession();
			tx = s.beginTransaction();
			s.save(obj);
			Integer objectAutoId = (Integer)getter(obj,"autoId");
			Long objectStaticId = STATICID+objectAutoId;
			setter(obj,"staticId",objectStaticId,Long.class);
			s.update(obj);
			setter(objGeo,"staticId",objectStaticId,Long.class);
			switch(geoType){
				case 1:{
					Double longitude = (Double)getter(obj,"longitude");
					Double latitude = (Double)getter(obj,"latitude");
					Geometry geom = getGeometry(longitude, latitude);
					setter(objGeo,"geometryData",geom,Geometry.class);
					break;
				}
				case 2:{
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
					setter (objGeo,"geometryData",geom,Geometry.class);
					break;
				}
				case 3:{
					
					break;
				}
			}
			
			s.save(objGeo);
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
	
//	//将Excel文件和对应的XML文件传入方法中
//	public void insertObjectByFile(File objectFile,File xmlObjectFile) throws Exception{
//		//info.setFileName(objectFile.getName());
//		//session.put("ImportInfo", info);
//		Workbook book = Workbook.getWorkbook(objectFile);
//		Sheet sheet = book.getSheet(0);
//		int rowSize = sheet.getRows();
//		countAll = rowSize;
//		//info.setNumSum(rowSize);
//		//session.put("ImportInfo", info);
//		SAXReader reader = new  SAXReader();
//		Document documentXML = reader.read(xmlObjectFile);
//		Element elementRoot = documentXML.getRootElement();
//		//获取去所要插入类的名字
//		Attribute attributeRoot = elementRoot.attribute("name");
//		//获取其空间数据库的类名
//		Attribute attributeGeoName = elementRoot.attribute("geoName");
//		//获取其对象类型的ID号
//		Attribute attributeTypeRoot = elementRoot.attribute("type");
//		Attribute attributeGeoType = elementRoot.attribute("geotype");
//		int geoType = Integer.parseInt(attributeGeoType.getValue());
//		int objectType = Integer.parseInt(attributeTypeRoot.getValue());
//		int zIndex = Integer.parseInt(elementRoot.attribute("zIndex").getValue());
//		Class<?> classObject = Class.forName("org.resource.objectclass."+attributeRoot.getValue());
//		Class<?> classGeoObject = Class.forName("org.resource.objectclass."+attributeGeoName.getValue());
//		//Object obj = classObject.newInstance();
//		for(int i=2;i<rowSize;i++){
//			Attribute attributeName =(Attribute)documentXML.selectNodes("//classes/name/@location").iterator().next();
//			int columnName = Integer.parseInt(attributeName.getValue());
//			Cell cellName = sheet.getCell(columnName, i);
//			//如果所导入的记录名字为空，表明Excel文件已经读完
//			if(cellName.getType()==CellType.EMPTY){
//				errInfo.add("第"+(i+1)+"行记录的第"+(char)(65+columnName)+"列为空，程序认为此文件已到文件底。如果与事实不符，请检查文件是否正确");
//				break;
//			}
//			String name = cellName.getContents();
//			s = HibernateSessionFactory.currentSession();
//			tx = s.beginTransaction();
//			Object obj = classObject.newInstance();
//			Object objGeoElectrical = classGeoObject.newInstance();
//			//GeoCivilEngineering objGeoElectrical = new GeoCivilEngineering();
//			setter(obj, "name", name, String.class);
//			setter(objGeoElectrical,"name",name,String.class);
//			setter(objGeoElectrical,"zIndex",zIndex,Integer.class);
//			List listRelateType = documentXML.selectNodes("//classes/property/@relateType");
//			List listColumn = documentXML.selectNodes("//classes/property/list/@location");
//			List listProperty = documentXML.selectNodes("//classes/property/@name");
//			List listType = documentXML.selectNodes("//classes/property/@type");
//			List listIsGeoData = documentXML.selectNodes("//classes/property/@isgeodata");
//			Iterator iteratorRelateType = listRelateType.iterator();
//			Iterator iteratorProperty = listProperty.iterator();
//			Iterator iteratorColumn = listColumn.iterator();
//			Iterator iteratorType = listType.iterator();
//			Iterator iteratorIsGeoData = listIsGeoData.iterator();
//			setter(obj,"objectType",objectType,Integer.class);
//			setter(objGeoElectrical,"objectType",objectType,Integer.class);
//			while(iteratorProperty.hasNext()){
//				Attribute attributeRelateType = (Attribute)iteratorRelateType.next();
//				String relateType = attributeRelateType.getValue();
//				if(relateType.equals("none")){
//				Attribute attributeColumn = (Attribute)iteratorColumn.next();
//				int cellsColumn = Integer.parseInt(attributeColumn.getValue());
//				Cell cell = sheet.getCell(cellsColumn, i);	
//				Attribute attributeIsGeoData = (Attribute)iteratorIsGeoData.next();
//				Boolean isGeoData = Boolean.parseBoolean(attributeIsGeoData.getValue());
//				//String cellValue = sheet.getCell(cellsColumn, i).getContents();
//				Attribute attributeProperty = (Attribute)iteratorProperty.next();
//				String propertyName = attributeProperty.getValue();
//				Attribute attributeType = (Attribute)iteratorType.next();
//				Class<?> classType = Class.forName(attributeType.getValue());
//				if(classType == java.lang.String.class){
//					//LabelCell cellWithType = (LabelCell)cell;
//					if(cell.getType() != CellType.EMPTY){
//						String stringValue = cell.getContents();	
//						setter(obj,propertyName,stringValue,classType);
//						if(isGeoData){
//							setter(objGeoElectrical,propertyName,stringValue,classType);
//						}
//					}
//				}
//				if(classType == java.lang.Integer.class){
//					if(cell.getType() != CellType.EMPTY){
//						NumberCell cellWithType = (NumberCell)cell;
//						Integer integerValue = ((Double)cellWithType.getValue()).intValue();
//						setter(obj, propertyName, integerValue, classType);
//						if(isGeoData){
//							setter(objGeoElectrical,propertyName,integerValue,classType);
//						}
//					}
//				}
//				if(classType == java.lang.Double.class){
//					if(cell.getType() != CellType.EMPTY){
//						NumberCell cellWithType = (NumberCell)cell;
//						Double doubleValue = cellWithType.getValue();
//						setter(obj, propertyName, doubleValue, classType);
//						if(isGeoData){
//							setter(objGeoElectrical,propertyName,doubleValue,classType);
//						}
//					}
//				}
//				if(classType == java.util.Date.class){
//					if(cell.getType() != CellType.EMPTY){
//						DateCell cellWithType = (DateCell)cell;
//						Date dateValue = cellWithType.getDate();
//						setter(obj, propertyName, dateValue, classType);
//						if(isGeoData){
//							setter(objGeoElectrical,propertyName,dateValue,classType);
//						}
//					}
//				}
//				if(classType == java.lang.Float.class){
//					if(cell.getType() != CellType.EMPTY){
//						
//					
//						NumberCell cellWithType = (NumberCell)cell;
//						Float floatValue = ((Double)cellWithType.getValue()).floatValue();
//						setter(obj,propertyName,floatValue,classType);
//					}
//				}
//				if(classType == java.lang.Boolean.class){
//					if(cell.getType() != CellType.EMPTY){
//						
//					
//						LabelCell cellWithType = (LabelCell)cell;
//						String stringValue = cellWithType.getString();
//						Boolean booleanValue = false;
//						if(stringValue.equals("是")){
//							booleanValue = true;
//						}else if(stringValue.equals("否")){
//							booleanValue = false;
//						}
//						setter(obj,propertyName,booleanValue,classType);
//				
//					}
//				}
//			}else if(relateType.equals("class")){
//				//Attribute attributeRelateType = (Attribute)iteratorRelateType.next();
//				//String relateType = attributeRelateType.getValue();
//				//if(relateType.equals("none")){
//				Attribute attributeColumn = (Attribute)iteratorColumn.next();
//				int cellsColumn = Integer.parseInt(attributeColumn.getValue());
//				Cell cell = sheet.getCell(cellsColumn, i);
//				String valueString = cell.getContents();
//				List<PdObjectBase> objectBaseList = select.selectObjectListByName(valueString);
//				if(objectBaseList.isEmpty()||objectBaseList.size()!=1){
//					break;
//				}
//				Long relateLong = objectBaseList.iterator().next().getStaticId();
//				Attribute attributeIsGeoData = (Attribute)iteratorIsGeoData.next();
//				Boolean isGeoData = Boolean.parseBoolean(attributeIsGeoData.getValue());
//				//String cellValue = sheet.getCell(cellsColumn, i).getContents();
//				Attribute attributeProperty = (Attribute)iteratorProperty.next();
//				String propertyName = attributeProperty.getValue();
//				setter(obj, propertyName, relateLong, java.lang.Long.class);
//				//Attribute attributeType = (Attribute)iteratorType.next();
//				//Class<?> classType = Class.forName(attributeType.getValue());
//			}else if(relateType.equals("map")){
//				
//			}
//			
//				
//			}
//			s.save(obj);
//			Integer objectAutoId = (Integer)getter(obj,"autoId");
//			
//			Long objectStaticId = STATICID+objectAutoId;
//			setter(obj,"staticId",objectStaticId,Long.class);
//			setter(objGeoElectrical,"staticId",objectStaticId,Long.class);
//			s.update(obj);
//			if(geoType == 1){
//				Double longitude = (Double)getter(obj,"longitude");
//				Double latitude = (Double)getter(obj,"latitude");
//				Geometry geom = getGeometry(longitude, latitude);
//				setter(objGeoElectrical,"geometryData",geom,Geometry.class);
//			}
//			if(geoType == 2){
//				//Integer startBuildingType = (Integer)getter(obj,"startBuildingType");
//				Integer startBuilding = (Integer)getter(obj,"startBuilding");
//				//Integer endBuildingType = (Integer)getter(obj,"endBuildingType");
//				Integer endBuilding = (Integer)getter(obj,"endBuilding");
//				//Class<?> classStartBuilding = Class.forName("org.resource.objectclass."+typeIdToCode(startBuildingType));
//				//Object objStartBuilding = classStartBuilding.newInstance();
//				//objStartBuilding = s.get(classStartBuilding, startBuilding);
//				PdEwPointBuilding startPointBuilding = (PdEwPointBuilding)s.get(PdEwPointBuilding.class, startBuilding);
//				PdEwPointBuilding endPointBuilding = (PdEwPointBuilding)s.get(PdEwPointBuilding.class, endBuilding);
//				Double x1 = (Double)getter(startPointBuilding,"longitude");
//				Double y1 = (Double)getter(startPointBuilding,"latitude");
//				Double x2 = (Double)getter(endPointBuilding,"longitude");
//				Double y2 = (Double)getter(endPointBuilding,"latitude");
//				Geometry geom = getGeometry(x1, y1, x2, y2);
//				setter (objGeoElectrical,"geometryData",geom,Geometry.class);
//			}
//			if(geoType == 3){
//				Integer startIntermissionId = (Integer)getter(obj,"startIntermission");
//				Integer endIntermissionId = (Integer)getter(obj,"endIntermission");
//				PdElLnIntermission startIntermission = (PdElLnIntermission)s.get(PdElLnIntermission.class, startIntermissionId);
//				PdElLnIntermission endIntermission = (PdElLnIntermission)s.get(PdElLnIntermission.class, endIntermissionId);
//				Integer startContainerId = (Integer)getter(startIntermission,"containerId");
//				Integer endContainerId = (Integer)getter(endIntermission,"containerId");
//				PdElPtContainerV startContainer = (PdElPtContainerV)s.get(PdElPtContainerV.class, startContainerId);
//				PdElPtContainerV endContainer = (PdElPtContainerV)s.get(PdElPtContainerV.class, endContainerId);
//				Double x1 = (Double)getter(startContainer,"longitude");
//				Double y1 = (Double)getter(startContainer,"latitude");
//				Double x2 = (Double)getter(endContainer,"longitude");
//				Double y2 = (Double)getter(endContainer,"latitude");
//				Geometry geom = getGeometry(x1, y1, x2, y2);
//				setter(objGeoElectrical,"geometryData",geom,Geometry.class);
//			}
//			s.save(objGeoElectrical);
//			//info.setNumImported(i);
//			//session.put("ImportInfo", info);
//			tx.commit();
//			HibernateSessionFactory.closeSession();
//			
//		}
//		
//		
//	}
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
	
}
