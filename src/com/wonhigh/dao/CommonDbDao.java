package com.wonhigh.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.wonhigh.bean.TabColumnInfo;
import com.wonhigh.util.DbUtils;

public class CommonDbDao {
	
	/**
	 * 获取数据库特定模式中所有表名称列表
	 * @param dbName
	 * @param schemaName
	 * @param types
	 * @param meta
	 * @return
	 */
	public List<String> getCatalogTables(String dbName,String schemaName,String[] types,Connection conn) {
		DatabaseMetaData meta = null;
		ResultSet rs = null;
		List<String> tableNameList = null;
		if(conn != null){
			try {
				meta = conn.getMetaData();
				rs = meta.getTables(dbName, schemaName, "%", types);
				tableNameList = new ArrayList<>();
				while(rs.next()){
					tableNameList.add(rs.getString("TABLE_NAME"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				//记得此处要关闭结果集
				try {
					DbUtils.close(rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return tableNameList;
	}
	
	/**
	 * 获取表的字段信息
	 * @param dbName
	 * @param schemaName
	 * @param tableName
	 * @param meta
	 * @return
	 */
	public List<TabColumnInfo> getTableColumnsInfo(String dbName,String schemaName,String tableName,Connection conn){
		List<TabColumnInfo> tabColumnInfoList = null;
		TabColumnInfo tabColumnInfo = null;
		DatabaseMetaData meta = null;
		ResultSet rs = null;
		try {
			meta = conn.getMetaData();
			rs = meta.getColumns(dbName, schemaName, tableName, null);
			tabColumnInfoList = new ArrayList<>();
			while(rs.next()){
				tabColumnInfo = new TabColumnInfo();
				tabColumnInfo.setDbName(rs.getString("TABLE_CAT"));
				tabColumnInfo.setSchemaName(rs.getString("TABLE_SCHEM"));
				tabColumnInfo.setTableName(rs.getString("TABLE_NAME"));
				tabColumnInfo.setColumnName(rs.getString("COLUMN_NAME"));
				tabColumnInfo.setColumnType(rs.getInt("DATA_TYPE"));
				tabColumnInfo.setColumnTypeName(rs.getString("TYPE_NAME"));
				tabColumnInfo.setColumnSize(rs.getInt("COLUMN_SIZE"));
				tabColumnInfoList.add(tabColumnInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//记得此处要关闭结果集
			try {
				DbUtils.close(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return tabColumnInfoList;
	}
	
}
