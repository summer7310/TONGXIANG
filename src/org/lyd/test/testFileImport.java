package org.lyd.test;

import java.io.File;

import org.lyd.Hibernate.InsertObjectByFile;

public class testFileImport {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		InsertObjectByFile test = new InsertObjectByFile();
		test.insertObjectByFile(new File("D:\\computer\\Java\\eclipse\\OpengisDataBase1.1\\src\\org\\分支接头.xls"), new File("D:\\src\\org\\resource\\excelXML\\PdElCnBranch.xml"));
	}

}
