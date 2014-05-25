package org.lyd.test;

import java.util.ArrayList;
import java.util.List;

import org.lyd.Operate.TreeCatalog;
import org.lyd.Operate.TreeCatalogRecord;

public class TestTreeCatalog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<TreeCatalogRecord> testRecord = new ArrayList<TreeCatalogRecord>();
		testRecord =new TreeCatalog().getTreeCatalog();
		for(TreeCatalogRecord x:testRecord){
			System.out.println(x.getName());
		}
		//System.out.println();
	}

}
