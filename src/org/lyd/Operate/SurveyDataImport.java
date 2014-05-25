package org.lyd.Operate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.lyd.Hibernate.SelectRecordAll;
import org.lyd.util.HibernateSessionFactory;
import org.resource.objectclass.GeoCivilEngineering;
import org.resource.objectclass.GeoPointSurvery;
import org.resource.objectclass.PdEwPtWell;
import org.resource.objectclass.PdObjectBase;

import com.opensymphony.xwork2.ActionContext;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
public class SurveyDataImport {
	/*
	 * /静态变量 Hibernate的会话
	 */
	private static Session s;
	/*
	 * 静态变量 Hibernate的事务
	 */
	private static Transaction tx;
	/*
	 * 静态变量 要保存对象的静态ID的固定常量
	 */
	private static final long STATICID = 1000000000000000l;
	//将会话层
	//private Map session = ActionContext.getContext().getSession();
	//导入信息
	//private DataImportInfo info = new DataImportInfo();
	/*
	 * 错误信息
	 */
	private List<String> errInfo = new ArrayList<String>(); 
	
	private int countAll;
	private int countFinish;
	private SelectRecordAll select = new SelectRecordAll();
	//执行导入方法
	public int surveyDataImport(File surveyExcel){
		try{
			//获取文件名字
			//info.setFileName(surveyExcel.getName());
			//session.put("ImportInfo", info);
			//将Excel文件获取book
			Workbook book = Workbook.getWorkbook(surveyExcel);
			//获取工作簿
			Sheet sheet = book.getSheet(0);
			//取得工作簿的总行数
			int rowSize = sheet.getRows();
			countAll = rowSize-2;
			//System.out.println(rowSize);
			countFinish = 0;
			
			//info.setNumSum(rowSize);
			//session.put("ImportInfo", info);
			String flagName = null;
			//int flag = 0;
			//计数标志
			int countFlag = 2;
			while(countFlag<rowSize){
				//Thread.sleep(2000);//测试使用
				//错误标志
				boolean errFlag = true;
				//导入记录标志
				int importFlag = countFlag;
				GeoCivilEngineering civilEngineering = new GeoCivilEngineering();
				PdEwPtWell well = new PdEwPtWell();
				GeoPointSurvery survey = new GeoPointSurvery();
				List<Coordinate> multPoints = new ArrayList<Coordinate>();
				Double longitude = 0.0 ;
				Double latitude = 0.0 ;

				
				Cell cellName = sheet.getCell(0,countFlag);
				//System.out.println("正在读取第"+(countFlag+1)+"行");
				//System.out.println(flagName);
				/*
				 * 判断名字是否行是否为空，为空则认为已经到文件底
				 */
				if(cellName.getType()==CellType.EMPTY){
					errInfo.add("文件导入停止在第"+(countFlag)+"行，下一行name字段为空，系统识别为文件底。如果与事实不符，请检查文件");
					//System.out.println("文件导入停止在第"+(countFlag)+"行，下一行name字段为空，系统识别为文件底。如果与事实不符，请检查文件");
					countAll = countFlag-2;
					break;
				}
				String name = cellName.getContents().trim().replaceAll("\n", "");
				/*
				 * 判断是否为同组的其他记录
				 */
				if(name.equals(flagName)){
					errInfo.add("第"+(countFlag+1)+"行,因为"+name+"的其他记录错误导致导入错误");
					//System.out.println("第"+(countFlag+1)+"行,因为"+name+"的其他记录错误导致导入错误");
					countFlag++;
					continue;
				}else{
					flagName = name;
				}
//				if(flag==0){
//					flagName = name;
//					flag = 1;
//				}
//				if(flagName==null){
//					flagName = name;
//				}
//				
//				if(flag==0&&name.equals(flagName)){
//					
//					errInfo.add("第"+(countFlag+1)+"行,因为"+name+"的其他记录错误导致导入错误");
//					//info.setErrInfo(errInfo);
//					//session.put("ImportInfo", info);
//					countFlag++;
//					continue;
//				}else{
//					flagName = name;
//					
//				}
//				
				Cell cellLongitude = sheet.getCell(1, countFlag);
				Cell cellLatitude = sheet.getCell(2, countFlag);
				
				if(cellLongitude.getType() == CellType.NUMBER){
					longitude = ((NumberCell)cellLongitude).getValue();
				}else{
					countFlag++;
					errInfo.add("第"+(cellLongitude.getRow()+1)+"行的经度为空，此行导入错误。");
					//info.setErrInfo(errInfo);
					//session.put("ImportInfo", info);
					continue;
				}
				if(cellLatitude.getType() == CellType.NUMBER){
					latitude = ((NumberCell)cellLatitude).getValue();
				}else{
					countFlag++;
					errInfo.add("第"+(cellLatitude.getRow()+1)+"行的纬度为空，此行导入错误。");
					//info.setErrInfo(errInfo);
					//session.put("ImportInfo", info);
					continue;
				}
				//NumberCell cellLongitude = (NumberCell)sheet.getCell(1, countFlag);
				//NumberCell cellLatitude = (NumberCell)sheet.getCell(2, countFlag);

				//well.setName(name);
				//civilEngineering.setName(name);
				//longitude =cellLongitude.getValue();
				//latitude = cellLatitude.getValue();
/*				
				Cell cellWellLenth = sheet.getCell(3, countFlag);
				Cell cellWellWidth = sheet.getCell(4, countFlag);
				Cell cellBottomDepth = sheet.getCell(5, countFlag);
				Cell cellTopDepth = sheet.getCell(7, countFlag);
				Cell cellChannelCount = sheet.getCell(12, countFlag);
				if(cellWellLenth.getType() == CellType.NUMBER){
					well.setWellLenth(((NumberCell)cellWellLenth).getValue());
				}
				if(cellWellWidth.getType() == CellType.NUMBER){
					well.setWellWidth(((NumberCell)cellWellWidth).getValue());
				}
				if(cellBottomDepth.getType() == CellType.NUMBER){
					well.setBottomDepth(((NumberCell)cellBottomDepth).getValue());
				}
				if(cellTopDepth.getType() == CellType.NUMBER){
					well.setTopDepth(((NumberCell)cellTopDepth).getValue());
				}
				if(cellChannelCount.getType() == CellType.NUMBER){
					well.setChannelCount(((Double)((NumberCell)cellTopDepth).getValue()).intValue());
				}*/
				multPoints.add(new Coordinate(longitude,latitude));
				countFlag++;
				//countFinish++;
				/*
				 * 读取此组的其他记录
				 */
				while(countFlag<rowSize){
					Cell cellNameNext = sheet.getCell(0,countFlag);
					String nameNext = cellNameNext.getContents().trim().replaceAll("\n", "");
					//System.out.println("正在读取第"+(countFlag+1)+"行");
					if(!(nameNext.equals(flagName))){
						break;
					}
					Cell cellLongitudeNext = sheet.getCell(1, countFlag);
					Cell cellLatitudeNext = sheet.getCell(2, countFlag);
					if(cellLongitudeNext.getType() == CellType.NUMBER){
						longitude = ((NumberCell)cellLongitudeNext).getValue();
					}else{
						errFlag = false;
						errInfo.add("第"+(cellLatitudeNext.getRow()+1)+"行的经度为空，此行导入错误。");
						//System.out.println("第"+(cellLatitudeNext.getRow()+1)+"行的经度为空，此行导入错误。");
						//info.setErrInfo(errInfo);
						//session.put("ImportInfo", info);
						countFlag++;
						break;
					}
					if(cellLatitudeNext.getType() == CellType.NUMBER){
						latitude = ((NumberCell)cellLatitudeNext).getValue();
					}else{
						errFlag = false;
						errInfo.add("第"+(cellLatitudeNext.getRow()+1)+"行的纬度为空，此行导入错误。");
						//System.out.println("第"+(cellLatitudeNext.getRow()+1)+"行的纬度为空，此行导入错误。");
						//info.setErrInfo(errInfo);
						//session.put("ImportInfo", info);
						countFlag++;
						break;
					}
					//NumberCell cellLongitudeNext = (NumberCell)sheet.getCell(1, countFlag);
					//NumberCell cellLatitudeNext = (NumberCell)sheet.getCell(2, countFlag);
					//Double longitudeNext = cellLongitudeNext.getValue();
					//Double latitudeNext = cellLatitudeNext.getValue();
					multPoints.add(new Coordinate(longitude,latitude));
					countFlag++;
					
				}
				/*
				 * 如果此组记录没有错误执行导入工作
				 */
				if(errFlag){
						System.out.println(flagName);
						List<PdObjectBase> wellList = select.selectObjectListByName(flagName);
						System.out.println(wellList.size());
						Iterator wellIterator = wellList.iterator();

						/*
						 * 判断对应工井是否存在，如果不存在，将生成工井。
						 * 如果对应名字的工井不仅存在一条记录，将此条记录将不导入
						 */
						if(wellList.size()<=1){
							try{
							s = HibernateSessionFactory.currentSession();
							tx = s.beginTransaction();
							Geometry surveyData = ListConvertToGeometry(multPoints);
							if(wellList.size()==1){
								well = (PdEwPtWell)s.get(PdEwPtWell.class, ((PdObjectBase)wellIterator.next()).getAutoId());
							}else{
								well.setName(flagName);
								civilEngineering.setName(flagName);
								Geometry surveyCenter = surveyData.getCentroid();
								civilEngineering.setGeometryData(surveyCenter);
								Double [] num = GeometryConvertToDouble(surveyCenter);
								well.setLongitude(num[0]);
								well.setLatitude(num[1]);
								well.setObjectType(1020101);
								civilEngineering.setObjectType(1020101);
								civilEngineering.setZIndex(30);
								s.save(well);
								long staticId = STATICID+well.getAutoId();
								well.setStaticId(staticId);
								s.update(well);
								civilEngineering.setStaticId(staticId);

								s.save(civilEngineering);
							}
							survey.setGeometryData(surveyData);
							survey.setWellId(well.getStaticId());
							survey.setObjectType(1030110);
							s.save(survey);
							tx.commit();
							//记录成功导入数据的记录数
							countFinish +=(countFlag-importFlag);
							}catch(Exception e){
								tx.rollback();
								e.printStackTrace();
							}finally{
								HibernateSessionFactory.closeSession();
							}
						}else{
							errInfo.add("此条记录对应的工井有两条记录，请检查数据一致性");
						}


						
						//
						//
						//


						
					//countFinish = countFlag-2;
					
//			if(flag == 1){
//				flagName = name;
//				flag  = 0;
//			}
//			if(name.equals(flagName)){
//				
//			}else{
//				flag = 1;
//				flagName = name;
//			}
			//int initSign = i;
			//for(int j = 0;)
					//info.setNumImported(countFlag);
					//session.put("ImportInfo", info);

					
				}

			}
			book.close();
			System.out.println(countFinish);
			return 1;//"SUCCESS";
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
	//将经纬度数组转成Pology的空间数据
	private Geometry ListConvertToGeometry(List<Coordinate> coordinates){
		int size = coordinates.size();
		Coordinate[] result = (Coordinate[])coordinates.toArray(new Coordinate[size]);
		Geometry surveyMultData = new GeometryFactory().createMultiPoint(result);
		surveyMultData.setSRID(4326);
		Geometry surveyData = surveyMultData.convexHull();
		return surveyData;
	}
	//将Geometry转换为经纬度
	private Double[] GeometryConvertToDouble(Geometry surveyData){
		String str = surveyData.toText();
		String subStr[] = str.split(" \\(| |\\)");
		Double[] num = new Double[]{Double.parseDouble(subStr[1]),Double.parseDouble(subStr[2])};
		return num;
	}
	
	//setter&getter
	public int getCountAll(){
		return countAll;
	}
	
	public int getCountFinish(){
		return countFinish;
	}
	
	public List<String> getErrInfo(){
		return errInfo;
	}
	//public int get
}
