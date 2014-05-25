package org.lyd.postgreSQL;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		GISSelect test=new GISSelect("localhost:5432/TONGXIANG", "postgres", "1990911");
		//测试第一目录	1010201为变电站类型号
		String temp1=test.getTreeContents(1010201);
		//测试变电站下级目录 线路类型号1010301能够在上一级返回的
		String temp2=test.getTreeContents(1, 1010301);
		//测试线路下一级目录 45为要查询的线路ID 1010401为电缆段 会在上一级返回
		String temp=test.getTreeContents(45,1010401);
		//测试组织节点分支箱下目录
		String temp3=test.getTreeContents(45, 1010205);
		//String temp1=test.getCableLine(1);
		//String temp2=test.getBranchBox(45);
		System.out.println(temp1);
		System.out.println(temp2);
		System.out.println(temp);
		System.out.println(temp3);
	}

}
