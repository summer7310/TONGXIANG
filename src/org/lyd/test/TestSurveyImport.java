package org.lyd.test;

import java.io.File;

import org.lyd.Operate.SurveyDataImport;

public class TestSurveyImport {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		SurveyDataImport test = new SurveyDataImport();
		test.surveyDataImport(new File("src\\org\\resource\\excelXML\\well.xls"));
	}

}
