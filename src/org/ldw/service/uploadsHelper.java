package org.ldw.service;

import java.io.*;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
//import org.lyd.Hibernate.InsertObjectByFile;
import org.lyd.Operate.SurveyDataImport;

public class uploadsHelper {
	public static boolean uploadsSingleFile(File myFile, String fileName, String savePath){
		
		
		if(myFile != null){
			File saveFile = new File(new File(savePath), fileName);
			if (!saveFile.getParentFile().exists())
                saveFile.getParentFile().mkdirs();
            try{
            	FileUtils.copyFile(myFile, saveFile);	
            } catch(Exception e){
            	System.out.println("copy file wrong");
            }
           
            return true;
		} else {
			return false;
		}
	}
}
