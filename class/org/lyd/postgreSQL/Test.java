package org.lyd.postgreSQL;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		GISSelect test=new GISSelect("localhost:5432/TONGXIANG", "postgres", "1990911");
		//���Ե�һĿ¼	1010201Ϊ���վ���ͺ�
		String temp1=test.getTreeContents(1010201);
		//���Ա��վ�¼�Ŀ¼ ��·���ͺ�1010301�ܹ�����һ�����ص�
		String temp2=test.getTreeContents(1, 1010301);
		//������·��һ��Ŀ¼ 45ΪҪ��ѯ����·ID 1010401Ϊ���¶� ������һ������
		String temp=test.getTreeContents(45,1010401);
		//������֯�ڵ��֧����Ŀ¼
		String temp3=test.getTreeContents(45, 1010205);
		//String temp1=test.getCableLine(1);
		//String temp2=test.getBranchBox(45);
		System.out.println(temp1);
		System.out.println(temp2);
		System.out.println(temp);
		System.out.println(temp3);
	}

}
