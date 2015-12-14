package com.wonhigh.map;

import java.util.HashMap;
import java.util.Map;

public class PgDataTypeMapToMysql {
	
	private static Map<String, String[]> pgToMysqlTypesMap = new HashMap<String, String[]>();
	private final static String[] requireMatchColLenTypeArr = new String[]{
			"decimal",
			"numeric",
			"real",
			"double precision",
			"character",
			"char",
			"varchar",
			"character varying"
	};
	
	static {
		pgToMysqlTypesMap.put("int2", new String[]{"TINYINT","SMALLINT"});
		pgToMysqlTypesMap.put("smallint", new String[]{"TINYINT","SMALLINT"});
		pgToMysqlTypesMap.put("int4", new String[]{"TINYINT","SMALLINT","MEDIUMINT","INT"});
		pgToMysqlTypesMap.put("integer", new String[]{"TINYINT","SMALLINT","MEDIUMINT","INT"});
		pgToMysqlTypesMap.put("int8", new String[]{"TINYINT","SMALLINT","MEDIUMINT","INT","BIGINT"});
		pgToMysqlTypesMap.put("integer", new String[]{"TINYINT","SMALLINT","MEDIUMINT","INT","BIGINT"});
		pgToMysqlTypesMap.put("decimal", new String[]{"DECIMAL","NUMERIC"});
		pgToMysqlTypesMap.put("numeric", new String[]{"DECIMAL","NUMERIC"});
		pgToMysqlTypesMap.put("character varying", new String[]{"CHAR","VARCHAR"});
		pgToMysqlTypesMap.put("VARCHAR", new String[]{"CHAR","VARCHAR"});
	}
	
	/**
	 * 获取映射表
	 * @return
	 */
	public static Map<String, String[]> getPgToMysqlTypesMap(){
		return pgToMysqlTypesMap;
	}

	/**
	 * 获取需要匹配列长度的数据类型
	 * @return
	 */
	public static String[] getRequireMatchColLenTypeArr() {
		return requireMatchColLenTypeArr;
	}
	
}
