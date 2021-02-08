package com.my.practice.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileDao implements INameDao {

	private String filePath; 
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public FileDao(String filePath) { this.filePath = filePath; }
	
	public FileDao() {	}
	
	@Override
	public List<String> getNames() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();		
		BufferedReader bufReader = new BufferedReader(new FileReader(new File(classLoader.getResource(filePath).getFile()))); 
		ArrayList<String> listOfNames = new ArrayList<>(); 
		String name="";
		try {
			name = bufReader.readLine();
		
			while (name != null) { 
				listOfNames.add(name);
				name = bufReader.readLine();
				} 
		}finally {
			bufReader.close();
		}
		return listOfNames;
	}
}
