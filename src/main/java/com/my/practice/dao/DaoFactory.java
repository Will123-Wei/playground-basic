package com.my.practice.dao;

public class DaoFactory {
	
	public static enum DataSouceType {
		FILE, RELATIONAL
	}

	public static INameDao getDao(DataSouceType dataSouceType) {
		switch(dataSouceType) {
			case RELATIONAL: //relational database dao if defined
			case FILE: return new FileDao(); 
			default: return new FileDao(); 
		}
	}
}
