package com.wonhigh.util;

public final class Constants {
	
	private Constants(){
		// restrict instantiation
	}
	
	/*数据库属性文件的所在的目录*/
	public static final String DB_PROP_FILE_DIR 		= "/config/";
	
	/*比对的对象类别*/
	public static final String[] DB_OBJ_TYPES 			= {"TABLE"};
	/*比对业务前缀*/
	public static final String BUSI_PREFIX				= "BUSI_PREFIX";
	
	/*源库类别*/
	public static final String SRC_DB_TYPE	 			= "SRC_DB_TYPE";
	/*表格校验源库IP*/                                  
	public static final String SRC_DB_HOST		        = "SRC_DB_HOST";
	/*表格校验源库端口*/                                
	public static final String SRC_DB_PORT		        = "SRC_DB_PORT";
	/*表格校验源库用户名*/                              
	public static final String SRC_DB_USER              = "SRC_DB_USER";
	/*表格校验源库密码*/                                
	public static final String SRC_DB_PASS              = "SRC_DB_PASS";
	/*表格校验源库名称*/                                
	public static final String SRC_DB_NAME              = "SRC_DB_NAME";
	/*表格校验源库模式名*/                              
	public static final String SRC_SCHEMA_NAME          = "SRC_SCHEMA_NAME";
	
	/*目标库类别*/
	public static final String DEST_DB_TYPE	 			= "DEST_DB_TYPE";
	/*表格校验源库IP*/                                  
	public static final String DEST_DB_HOST		        = "DEST_DB_HOST";
	/*表格校验源库端口*/                                
	public static final String DEST_DB_PORT		        = "DEST_DB_PORT";
	/*表格校验目标库用户名*/                            
	public static final String DEST_DB_USER             = "DEST_DB_USER";
	/*表格校验目标库密码*/                              
	public static final String DEST_DB_PASS             = "DEST_DB_PASS";
	/*表格校验目标库名称*/                              
	public static final String DEST_DB_NAME             = "DEST_DB_NAME";
	/*表格校验目标库模式名*/                            
	public static final String DEST_SCHEMA_NAME         = "DEST_SCHEMA_NAME";
	
	/*源库与目标库表结构校验日志文件的路径*/
	public static final String TAB_VALID_LOGFILE_NAME   = "TAB_VALID_LOGFILE_NAME";
	/*记录日志的时间格式*/                              
	public static final String LOG_FILE_DATE_FORMAT	    = "LOG_FILE_DATE_FORMAT";

}
