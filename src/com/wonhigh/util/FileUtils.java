package com.wonhigh.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class FileUtils {
	
	/**
	 * 文件信息追加
	 * @param filePath
	 * @param appendInfo
	 * @throws IOException
	 */
	public void appendFile(String filePath,String appendInfo) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath,true)));
		out.println(appendInfo);
		out.close();
	}

	/**
	 * 加载properties文件
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public Properties loadPropertiesFile(String filePath) throws IOException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream(filePath);
		prop.load(in);
		return prop;
	}
	
	/**
	 * 加载特定目录下的所有文件列表
	 * @param dir
	 * @return
	 */
	public List<String> loadAllFilePathListByDir(String dir){
		URL url = this.getClass().getResource(dir);
		File f = null;
		File[] listOfFiles = null;
		List<String> fileList = null;
		try {
			f = new File(url.toURI());
			if(f.exists() && f.isDirectory()){
				listOfFiles = f.listFiles();
				fileList = new ArrayList<String>();
				for(int i = 0 ; i < listOfFiles.length ; i++){
					fileList.add(listOfFiles[i].getAbsolutePath());
				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return fileList;
	}

}
