package org.ldw.action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.ldw.service.*;
import java.io.*;

//import org.lyd.Hibernate.InsertObjectByFile;


/*
 * 导入EXCEL文件
 * 1.测绘层
 * 2.
 */
public class uploadsExcelAction extends ActionSupport {
	private String type;
	private File upFile;
	private String upFileFileName;
	private String upFileContentType;
	private importExcelService importService;
	
	public String execute() throws Exception {
		//String savePath = ServletActionContext.getServletContext().getRealPath("/uploads");
		
		if(upFile != null){
			importService.setUpFile(upFile);
		} else {
			return ERROR;
		}
		
		if(importService.importExcel(type) == 1){
			return SUCCESS;
		} else {
			return ERROR;
		}

		/*
		if(uploadsHelper.uploadsSingleFile(upFile, upFileFileName , savePath)){
			importService.setUpFile(new File(savePath + "/" + upFileFileName));
			if(importService.importWells() == 1){
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return ERROR;
		}*/
	} 
	
	//setter
	public void setUpFile(File upFile){
		this.upFile = upFile;
	}

	public void setUpFileFileName(String upFileFileName){
		this.upFileFileName = upFileFileName;
	}
	
	public void setUpFileContentType(String upFileContentType){
		this.upFileContentType = upFileContentType;
	}
	
	public void setImportService(importExcelService importService){
		this.importService = importService;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	//getter
	public File getUpFile(){
		return upFile;
	}
	
	public String getUpFileName(){
		return upFileFileName;
	}
	
	public String getUpFileContentType(){
		return upFileContentType;
	}
	
	public importExcelService getImportService(){
		return importService;
	}
	
	public String getType(){
		return type;
	}
	
}
