package org.lyd.test;

import org.lyd.Hibernate.SelectRecordAll;
import org.resource.objectclass.PdElCnTransformersubstation;

//import org.lyd.object.PdElCnBranchBox;
//import org.lyd.object.PdElCnTransformer;
//import org.lyd.object.PdElCnTransformersubstation;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//SelectRecordAll test=new SelectRecordAll(38);
		PdElCnTransformersubstation testObject=new SelectRecordAll().selectTransformerSubstationRec(new SelectRecordAll().selectObjectByName("luytg"));
		//PdElCnBranchBox testObject=test.selectBranchBoxRec();
		System.out.println(testObject.getName());
	}

}
