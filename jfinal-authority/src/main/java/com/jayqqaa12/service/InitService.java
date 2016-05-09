package com.jayqqaa12.service;

import java.io.File;
import java.io.IOException;

import com.jayqqaa12.jbase.util.Fs;
import com.jfinal.plugin.activerecord.Db;

public class InitService
{

	public void initDb(String path) throws IOException{
		
	 
			String sql = Fs.readFile(new File(path));
			
			String[] sqls = sql.split(";");
			
			for(String s :sqls ){
				Db.update(s);
			}
		 
	}
	
	
	
}
