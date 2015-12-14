package com.wonhigh.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GlobalInfo {
	
	private final static Map<String, String> mapDbTypeToDriveClass = new HashMap<>();
	private final static Map<String, String> mapDbTypeToConnPrefix = new HashMap<>();
	static {
		mapDbTypeToDriveClass.put("pg", "org.postgresql.Driver");
		mapDbTypeToDriveClass.put("mysql", "com.mysql.jdbc.Driver");
		mapDbTypeToConnPrefix.put("pg", "jdbc:postgresql://");
		mapDbTypeToConnPrefix.put("mysql", "jdbc:mysql://");
	}
	
	/**
	 * 通过数据库类型获取对应的驱动类
	 * @param dbType
	 * @return
	 */
	public static String getDriveClassByDbType(String dbType){
		return GlobalInfo.mapDbTypeToDriveClass.get(dbType);
	}
	
	/**
	 * 通过数据库类型获取对应连接信息前缀
	 * @param dbType
	 * @return
	 */
	public static String getConnPrefixByDbType(String dbType){
		return GlobalInfo.mapDbTypeToConnPrefix.get(dbType);
	}
	
	/**
	 * 获取全局属性文件信息
	 * @return
	 */
	public static Properties getGlobalProps(String propsFileName){
		FileUtils fileUtils = new FileUtils();
		Properties props = null;
		try {
			props = fileUtils.loadPropertiesFile(propsFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	/**
	 * 获取源数据库主机名称
	 * @return
	 */
	public static String getSrcDbHost(Properties props){
		return props == null ? null : props.getProperty(Constants.SRC_DB_HOST);
	}
	
	/**
	 * 获取目标数据库主机名称
	 * @return
	 */
	public static String getDestDbHost(Properties props){
		return props == null ? null : props.getProperty(Constants.DEST_DB_HOST);
	}
	
	/**
	 * 获取源数据库端口
	 * @return
	 */
	public static String getSrcDbPort(Properties props){
		return props == null ? null : props.getProperty(Constants.SRC_DB_PORT);
	}
	
	/**
	 * 获取目标数据库端口
	 * @return
	 */
	public static String getDestDbPort(Properties props){
		return props == null ? null : props.getProperty(Constants.DEST_DB_PORT);
	}
	
	/**
	 * 获取源库数据库名称
	 * @return
	 */
	public static String getSrcDbName(Properties props){
		return props == null ? null : props.getProperty(Constants.SRC_DB_NAME);
	}
	
	/**
	 * 获取目标库数据库名称
	 * @return
	 */
	public static String getDestDbName(Properties props){
		return props == null ? null : props.getProperty(Constants.DEST_DB_NAME);
	}
	
	/**
	 * 获取源库数据库模式名称
	 * @return
	 */
	public static String getSrcSchemaName(Properties props){
		return props == null ? null : props.getProperty(Constants.SRC_SCHEMA_NAME);
	}
	
	/**
	 * 获取目标库数据库模式名称
	 * @return
	 */
	public static String getDestSchemaName(Properties props){
		return props == null ? null : props.getProperty(Constants.DEST_SCHEMA_NAME);
	}
	
}
