package org.ldw.service;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.lyd.Hibernate.SavePropertyObject;
import org.lyd.util.JpegTool;
import org.lyd.util.JpegToolException;
import org.resource.objectclass.PdAuxiliary;
import org.resource.objectclass.PdPhoto;

public class uploadsPhotoService {
	//图片上传路径
	private String upPath;//=ServletActioinContext.getServletContext().getRealPath("/uploads/image");
	//缩略图路径
	private String previewUpPath;//=ServletActionContext.getServletContext().getRealPath("/uploads/image");
	//目标文件
	private File upFile;
	//生成缩略图类
	private JpegTool jpegTool = new JpegTool();
	//上传类型
	//private String type;
	//Hibernate保存类
	private SavePropertyObject daoSave = new SavePropertyObject();
	private PdPhoto photo = new PdPhoto();
	
	public uploadsPhotoService(){
		
	}
	public int uploadsFile(String upFileFileName,Long hostId,String type){
		if(type.equals("photo")){
			 
			return uploadsPhoto(upFileFileName,hostId);
		}
		if(type.equals("auxiliary")){
			
			return uploadsAuxiliary(upFileFileName,hostId);
		}
		return 1;
	}
	public int uploadsPhoto(String upFileFileName,Long hostId){
		//获取运行的当前时间
		DateFormat df = DateFormat.getDateInstance();
		//获取时间戳
		long timestamp = System.currentTimeMillis();
		String fileType = upFileFileName.substring((upFileFileName.indexOf(".")));
		String upPathRelative = File.separator+"image"+File.separator+df.format(new Date())+File.separator+timestamp+fileType;
		//缩略图文件目录
		String previewUpPathRelative = File.separator+"image"+File.separator+df.format(new Date())+File.separator+"thumb_"+timestamp+fileType;
		//创建要保存的目录和文件名
		File saveFile = new File(ServletActionContext.getServletContext().getRealPath("/uploads"+upPathRelative+""));
		File previewSaveFile = new File(ServletActionContext.getServletContext().getRealPath("/uploads"+previewUpPathRelative+""));
		if(!saveFile.getParentFile().exists())
			saveFile.getParentFile().mkdirs();
		try{
			FileUtils.copyFile(upFile, saveFile);
			jpegTool.SetSmallHeight(100);
			jpegTool.doFinal(saveFile, previewSaveFile);
			photo.setName(upFileFileName.substring(0, upFileFileName.indexOf(".")));
			photo.setHostId(hostId);
			photo.setPhotoPath(upPathRelative);
			photo.setPreviewPhotoPath(previewUpPathRelative);
			daoSave.SaveObjectAchieve(photo);
			
			
		}catch(JpegToolException e){
			e.printStackTrace();
			System.out.println("生成缩略图错误");
			//return 0;
		}catch(HibernateException e){
			e.printStackTrace();
			System.out.println("hibernate出现错误");
			//return 0;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("copy file wrong");
			//return 0;
		}
		
		return 1;
	}
	//导入附件
	public int uploadsAuxiliary(String uploadFileName,Long hostId){
		String fileType = uploadFileName.substring((uploadFileName.indexOf(".")+1));
		//获取运行的当前时间
		DateFormat df = DateFormat.getDateInstance();
		//获取时间戳
		long timestamp = System.currentTimeMillis();
		String upPathRelative = File.separator+"auxiliary"+File.separator+df.format(new Date())+File.separator+fileType+File.separator+timestamp+"."+fileType;
		//缩略图文件目录
		//String previewUpPathRelative = File.separator+df.format(new Date())+File.separator+"thumb_"+upFileFileName;
		//创建要保存的目录和文件名
		File saveFile = new File(ServletActionContext.getServletContext().getRealPath("/uploads"+upPathRelative+""));
		//File previewSaveFile = new File(ServletActionContext.getServletContext().getRealPath("/uploads/image"+previewUpPathRelative+""));
		if(!saveFile.getParentFile().exists())
			saveFile.getParentFile().mkdirs();
		try{
			FileUtils.copyFile(upFile, saveFile);
			PdAuxiliary auxiliary = new PdAuxiliary();
			auxiliary.setAuxiliaryType(fileType);
			auxiliary.setAuxiliaryPath(upPathRelative);
			auxiliary.setName(uploadFileName.substring(0, uploadFileName.indexOf(".")));
			auxiliary.setHostId(hostId);
			daoSave.SaveObjectAchieve(auxiliary);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("copy File wrong");
		}

		return 1;
	}

	public String getUpPath() {
		return upPath;
	}

	public void setUpPath(String upPath) {
		this.upPath = upPath;
	}

	public String getPreviewUpPath() {
		return previewUpPath;
	}

	public void setPreviewUpPath(String previewUpPath) {
		this.previewUpPath = previewUpPath;
	}

	public File getUpFile() {
		return upFile;
	}

	public void setUpFile(File upFile) {
		this.upFile = upFile;
	}

	public JpegTool getJpegTool() {
		return jpegTool;
	}

	public void setJpegTool(JpegTool jpegTool) {
		this.jpegTool = jpegTool;
	}

	public SavePropertyObject getDaoSave() {
		return daoSave;
	}

	public void setDaoSave(SavePropertyObject daoSave) {
		this.daoSave = daoSave;
	}

	public PdPhoto getPhoto() {
		return photo;
	}

	public void setPhoto(PdPhoto photo) {
		this.photo = photo;
	}
}
