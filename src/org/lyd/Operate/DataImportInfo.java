package org.lyd.Operate;

import java.util.List;

public class DataImportInfo {
	private String fileName;
	private int numSum;
	private int numImported;
	private List<String> errInfo;
	public DataImportInfo(){
		
	}
	
	public DataImportInfo(String fileName, int numSum, int numImported,
			List<String> errInfo) {
		this.fileName = fileName;
		this.numSum = numSum;
		this.numImported = numImported;
		this.errInfo = errInfo;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getNumSum() {
		return numSum;
	}
	public void setNumSum(int numSum) {
		this.numSum = numSum;
	}
	public int getNumImported() {
		return numImported;
	}
	public void setNumImported(int numImported) {
		this.numImported = numImported;
	}
	public List<String> getErrInfo() {
		return errInfo;
	}
	public void setErrInfo(List<String> errInfo) {
		this.errInfo = errInfo;
	}
	       
}
