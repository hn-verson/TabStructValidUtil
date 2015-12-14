package com.wonhigh.bean;

public class TabColumnInfo {
	
	/*数据库名称*/
	private String dbName;
	/*模式名称*/
	private String schemaName;
	/*表名称*/
	private String tableName;
	/*列名称*/
	private String columnName;
	/*列类型*/
	private int columnType;
	/*列类型名称*/
	private String columnTypeName;
	/*列大小*/
	private int columnSize;
	
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public int getColumnType() {
		return columnType;
	}
	public void setColumnType(int columnType) {
		this.columnType = columnType;
	}
	public String getColumnTypeName() {
		return columnTypeName;
	}
	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}
	public int getColumnSize() {
		return columnSize;
	}
	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}
	
	public TabColumnInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TabColumnInfo(String dbName, String schemaName, String tableName,
			String columnName, int columnType, String columnTypeName,
			int columnSize) {
		super();
		this.dbName = dbName;
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.columnName = columnName;
		this.columnType = columnType;
		this.columnTypeName = columnTypeName;
		this.columnSize = columnSize;
	}
	
}
