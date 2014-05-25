package org.ldw.service;

import java.io.File;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.lyd.Operate.ContainerDataImport;
import org.lyd.Operate.InsertObjectByFile;
import org.lyd.Operate.SurveyDataImport;
import org.lyd.Operate.WirecableImport;

/******************************
 * 导入excel数据服务
 * 1.导入
 ******************************/
public class importExcelService {
	//上传路径
	private String upPath;// = ServletActionContext.getServletContext().getRealPath("/uploads");
	//模板excel路径
	private String tmpPath;// = ServletActionContext.getServletContext().getRealPath("/WEB-INF/classes/org/resource/excelXML");
	//目标文件
	private File upFile;
	//
	private SurveyDataImport importDao;
	private ContainerDataImport importDaoEle;
	private WirecableImport importDaoWirecable;
	private InsertObjectByFile importDaoObj;
	private int countFinish;
	private int countAll;
	private List<String> errInfo;
	
	public importExcelService(){
		
	}
	
	public int importExcel(String type){
		if(type.equals("survey")){
			return importSurvey();
		} else if(type.equals("well")){
			importWell();
			return 1;
		} else if(type.equals("joint")){
			importJoint();
			return 1;
		} else if(type.equals("wire")){
			importWirecable();
			return 1;
		} else if(type.equals("remainder")){
			importRemainder();
			return 1;
		} else if(type.equals("ele")){
			importEle();
			return 1;
		}
		return 1;
	}
	
	
	/*
	 * 导入测绘层
	 */
	public int importSurvey(){
		//upPath = ServletActionContext.getServletContext().getRealPath("/uploads");
		///tmpPath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/classes/org/resource/excelXML");
		try{
			int res = importDao.surveyDataImport(upFile);
			if(res != -1){
				countFinish = importDao.getCountFinish();
				countAll 	= importDao.getCountAll();
				errInfo 	= importDao.getErrInfo();
			}
			return res;
        }catch(Exception e){
        	return -1;
        }
	}
	
	/*
	 * 导入电气设备
	 */
	public void importEle(){
		//upPath = ServletActionContext.getServletContext().getRealPath("/uploads");
		///tmpPath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/classes/org/resource/excelXML");
		try{
			importDaoEle.containerDataImport(upFile);
			//if(res != -1){
			countFinish = importDaoEle.getNumImported();
			countAll 	= importDaoEle.getNumSum();
			errInfo 	= importDaoEle.getErrInfo();
			//}
			//return res;
        }catch(Exception e){
        	//return -1;
        }
	}
	
	/*
	 * 导入电缆段
	 */
	
	public void importWirecable(){
		//upPath = ServletActionContext.getServletContext().getRealPath("/uploads");
		///tmpPath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/classes/org/resource/excelXML");
		try{
			importDaoWirecable.wirecableImport(upFile);
			//if(res != -1){
			countFinish = importDaoWirecable.getCountFinish();
			countAll 	= importDaoWirecable.getCountAll();
			errInfo 	= importDaoWirecable.getErrInfo();
			//}
			//return res;
        }catch(Exception e){
        	//return -1;
        }
	}
	/*
	 * 导入工井
	 */
	public void importWell(){
		try{
			importDaoObj.insertObjectByFile(upFile, new File(ServletActionContext.getServletContext().getRealPath("")+"\\WEB-INF\\classes\\org\\resource\\excelXML\\PdEwPtWell.xml"));
			countFinish = importDaoObj.getCountFinish();
			countAll    = importDaoObj.getCountAll();
			errInfo		= importDaoObj.getErrInfo();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*
	 * 导入中间接头
	 */
	public void importJoint(){
		try{
			importDaoObj.insertObjectByFile(upFile, new File(ServletActionContext.getServletContext().getRealPath("")+"\\WEB-INF\\classes\\org\\resource\\excelXML\\PdElCnIntermediateJoint.xml"));
			countFinish = importDaoObj.getCountFinish();
			countAll    = importDaoObj.getCountAll();
			errInfo		= importDaoObj.getErrInfo();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*
	 * 导入盘余
	 */
	public void importRemainder(){
		try{
			importDaoObj.insertObjectByFile(upFile, new File(ServletActionContext.getServletContext().getRealPath("")+"\\WEB-INF\\classes\\org\\resource\\excelXML\\PdElCnCabledrumRemainder.xml"));
			countFinish = importDaoObj.getCountFinish();
			countAll    = importDaoObj.getCountAll();
			errInfo		= importDaoObj.getErrInfo();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	//setter&getter
	public void setUpFile(File upFile){
		this.upFile = upFile;
	}
	
	public File getUpFile(){
		return upFile;
	}
	
	public void setImportDao(SurveyDataImport importDao){
		this.importDao = importDao;
	}
	
	public SurveyDataImport getImportDao(){
		return importDao;
	}
	
	public void setImportDaoEle(ContainerDataImport importDaoEle){
		this.importDaoEle = importDaoEle;
	}
	
	public void setImportDaoWirecable(WirecableImport importDaoWirecable){
		this.importDaoWirecable = importDaoWirecable;
	}
	
	public void setImportDaoObj(InsertObjectByFile importDaoObj){
		this.importDaoObj = importDaoObj;
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
}
