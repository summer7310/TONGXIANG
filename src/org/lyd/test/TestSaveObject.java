package org.lyd.test;

import org.ldw.service.jsonDateHelper;
import org.ldw.service.propertyService;
import org.lyd.Hibernate.SelectRecordAll;
import org.lyd.Operate.SaveOrUpdateObject;
import org.resource.objectclass.PdElCnTransformersubstation;


//import org.lyd.saveObject.SavePropertyObject;
//import org.lyd.handle.SaveOrUpdateObject;
//import org.lyd.handle.SelectRecordAll;
////import org.lyd.handle.SelectRecordAll;
//import org.lyd.object.PdElCnTransformer;
//import org.lyd.object.PdElCnTransformersubstation;
//import org.lyd.selectObject.*;
public class TestSaveObject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		//PdElCnTransformersubstation test=new PdElCnTransformersubstation();
		//PdElCnTransformersubstation test=new SelectRecordAll().selectTransformerSubstationRec(40);
		//PdElCnTransformer test=new PdElCnTransformer();
		//PdElCnTransformersubstation testStation=new PdElCnTransformersubstation();
		//test.setAutoId(40);
		//test.setName("hhhr");
		//test.setCommissioningDate("2013-10-11");
		//testStation.setName("testStationConnection");
		//SaveOrUpdateObject test2=new SaveOrUpdateObject();
		//test2.SaveObjectAchieve(test);
		//test2.SaveOrUpdateObjectF(test);
		//System.out.println(test.getAutoId());
		//test2.UpdateGeoObjectIsbn(new SelectGeoObjectByName().SelectGeoObjectByNameF(test.getName()), test.getAutoId());
		propertyService ser = new propertyService();
		long s = 1381477294011851l;
		System.out.print("test");
		System.out.print(ser.getProJsonOfHolesByPId(s).toString());
		//System.out.print(ser.selectPropertyJson(1010502, 1381477294011857l).toString());
		//System.out.print();

}}
