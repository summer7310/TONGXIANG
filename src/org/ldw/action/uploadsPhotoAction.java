package org.ldw.action;

import java.io.File;

import org.ldw.service.uploadsPhotoService;

import com.opensymphony.xwork2.ActionSupport;
/*
 * 导入照片
 */
public class uploadsPhotoAction extends ActionSupport {
	private String upFileContentType;
	private File upFile;
	private String upFileFileName;
	private Long staticId;
	private uploadsPhotoService uploadsService = new uploadsPhotoService();
	private String type;
	public String execute() throws Exception{
		
		if(upFile != null){
			uploadsService.setUpFile(upFile);
		}else{
			return ERROR;
		}
		if(uploadsService.uploadsFile(upFileFileName, staticId,type) == 1){
			return SUCCESS;
		}else{
			return ERROR;
		}
		
		//System.out.print(upFileFileName + staticId.toString());
		//return SUCCESS;
	}
	
	//setter getter
	public String getUpFileContentType() {
		return upFileContentType;
	}
	
	public void setUpFileContentType(String upFileContentType) {
		this.upFileContentType = upFileContentType;
	}
	
	public File getUpFile() {
		return upFile;
	}
	
	public void setUpFile(File upFile) {
		this.upFile = upFile;
	}
	
	public String getUpFileFileName() {
		return upFileFileName;
	}
	
	public void setUpFileFileName(String upFileFileName) {
		this.upFileFileName = upFileFileName;
	}
	
	public Long getStaticId() {
		return staticId;
	}
	
	public void setStaticId(Long staticId) {
		this.staticId = staticId;
	}
	public uploadsPhotoService getUploadsService() {
		return uploadsService;
	}
	public void setUploadsService(uploadsPhotoService uploadsService) {
		this.uploadsService = uploadsService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
