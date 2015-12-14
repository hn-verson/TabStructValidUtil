package com.wonhigh.busi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.wonhigh.bean.TabColumnInfo;
import com.wonhigh.dao.CommonDbDao;
import com.wonhigh.dao.TabStructValidDao;
import com.wonhigh.util.Constants;
import com.wonhigh.util.DbUtils;
import com.wonhigh.util.FileUtils;
import com.wonhigh.util.FormatUtil;
import com.wonhigh.util.GlobalInfo;

public class TabStructValidBusi {

	/**
	 * 比较源库与目标库表结构的差异性
	 */
	public void detectSrcTabAndDestTabDiff(String propsFileName){
		
		Connection srcConn = null;
		Connection destConn = null;
		CommonDbDao commonDbDao = new CommonDbDao();
		TabStructValidDao validDao = new TabStructValidDao();
		List<String> srcTableNameList = new ArrayList<>();
		String srcTableName = null;
		TabColumnInfo srcTabColumnInfo = null;
		List<TabColumnInfo> srcTabColumnInfoList = null;
		List<String> destTableNameList = new ArrayList<>();
		String destTableName = null;
		List<TabColumnInfo> destTabColumnInfoList = null;
		Properties props = null;
		String srcUrl = null;
		String destUrl = null;
		int location = -1;
		FileUtils fileUtils = new FileUtils();
		
		String busiPrefix = null;
		
		try {
			props = fileUtils.loadPropertiesFile(propsFileName);
			
			DbUtils.loadDbDriver(GlobalInfo.getDriveClassByDbType(props.getProperty(Constants.SRC_DB_TYPE)));
			DbUtils.loadDbDriver(GlobalInfo.getDriveClassByDbType(props.getProperty(Constants.DEST_DB_TYPE)));
			
			srcUrl = GlobalInfo.getConnPrefixByDbType(props.getProperty(Constants.SRC_DB_TYPE))
					+ props.getProperty(Constants.SRC_DB_HOST) 
					+ ":" + props.getProperty(Constants.SRC_DB_PORT)
					+ "/" + props.getProperty(Constants.SRC_DB_NAME);
			
			destUrl = GlobalInfo.getConnPrefixByDbType(props.getProperty(Constants.DEST_DB_TYPE))
					+ props.getProperty(Constants.DEST_DB_HOST) 
					+ ":" + props.getProperty(Constants.DEST_DB_PORT)
					+ "/" + props.getProperty(Constants.DEST_DB_NAME);
			
			srcConn = DriverManager.getConnection(srcUrl, props.getProperty(Constants.SRC_DB_USER),props.getProperty(Constants.SRC_DB_PASS));
			srcTableNameList = commonDbDao.getCatalogTables(props.getProperty(Constants.SRC_DB_NAME), props.getProperty(Constants.SRC_SCHEMA_NAME), Constants.DB_OBJ_TYPES, srcConn);
			busiPrefix = props.getProperty(Constants.BUSI_PREFIX);
			srcTableNameList = FormatUtil.formatTableNameList(srcTableNameList, busiPrefix, null);
			
			destConn = DriverManager.getConnection(destUrl, props.getProperty(Constants.DEST_DB_USER),props.getProperty(Constants.DEST_DB_PASS));
			destTableNameList = commonDbDao.getCatalogTables(props.getProperty(Constants.DEST_DB_NAME), props.getProperty(Constants.DEST_SCHEMA_NAME), Constants.DB_OBJ_TYPES, destConn);
			
			if(srcTableNameList == null || srcTableNameList.size() < 1){
				return ;
			}
			
			//源库与目标库表结构对比
			for(int i = 0 ; i < srcTableNameList.size() ; i++){
				srcTableName = srcTableNameList.get(i);
				location = validDao.srcTabIndexOfDestTab(srcTableName, destTableNameList,props);
				if(location > -1){
					destTableName = destTableNameList.get(location);
					//考虑到搜索效率,从集合中移除匹配过的元素
					destTableNameList.remove(location);
					location = -1;
					srcTabColumnInfoList = commonDbDao.getTableColumnsInfo(props.getProperty(Constants.SRC_DB_NAME), props.getProperty(Constants.SRC_SCHEMA_NAME), FormatUtil.addBusiPrefixForTable(srcTableName, busiPrefix), srcConn);
					destTabColumnInfoList = commonDbDao.getTableColumnsInfo(props.getProperty(Constants.DEST_DB_NAME), props.getProperty(Constants.DEST_SCHEMA_NAME), destTableName, destConn);
					if(srcTabColumnInfoList == null || srcTabColumnInfoList.size() < 1){
						continue;
					}
					
					//源表与目标表列对比
					for(int j = 0 ; j < srcTabColumnInfoList.size(); j++){
						srcTabColumnInfo = srcTabColumnInfoList.get(j);
						location = validDao.srcTabColCompWithDestTabCols(srcTabColumnInfo, destTabColumnInfoList,props);
						if(location > -1){
							destTabColumnInfoList.remove(location);
							location = -1;
						}
					}
					if(destTabColumnInfoList != null && destTabColumnInfoList.size() > 0){
						validDao.recordDestTabExtraCols(destTabColumnInfoList,props);
					}
					
				}
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
			
	}
	
	public TabStructValidBusi() {
		super();
	}
	
}