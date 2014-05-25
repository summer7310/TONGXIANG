package org.lyd.test;

import java.util.List;

import org.lyd.Hibernate.SelectRecordAll;
import org.resource.objectclass.GeoElectrical;
import org.resource.objectclass.PdElLnWirecableSegmengV;

public class TestSelectWirecable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<PdElLnWirecableSegmengV> testTransResult = new SelectRecordAll().selectWirecableFinishByProfileId(staticId);
		for(PdElLnWirecableSegmengV x:testTransResult){
			System.out.println(x.getName());
		}
	}

}
