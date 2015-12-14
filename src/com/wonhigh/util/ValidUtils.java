package com.wonhigh.util;

import java.util.Iterator;
import java.util.Map;
import com.wonhigh.map.PgDataTypeMapToMysql;

public class ValidUtils {
	
	/**
	 * 判断字符串是否为null或空字符串
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s){
		return s == null || "".equals(s.trim());
	}
	
	/**
	 * 验证类型是否一致
	 * @param scrColumnType
	 * @param destColumnType
	 * @return
	 */
	public static boolean dataTypeIsConsist(String scrColumnType,String destColumnType){
		if(isEmpty(scrColumnType) || isEmpty(destColumnType)){
			return false;
		}
		Map<String, String[]> map = PgDataTypeMapToMysql.getPgToMysqlTypesMap();
		String[] destColumnTypeArry = null; 
		Iterator<String> it = null;
		if(map != null){
			it = map.keySet().iterator();
		}
		if(it != null){
			while(it.hasNext()){
				destColumnTypeArry = map.get(it.next());
				for(String columnName : destColumnTypeArry){
					if(destColumnType.equals(columnName)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 是否需要匹配字段长度
	 * @return
	 */
	public static boolean isRequireMatchFieldLength(String srcColumnTypeName){
		if(isEmpty(srcColumnTypeName)){
			return false;
		}
		String[] matchColArr = PgDataTypeMapToMysql.getRequireMatchColLenTypeArr();
		for(String typeName : matchColArr){
			if(srcColumnTypeName.equals(typeName)){
				return true;
			}
		}
		return false;
	}
	

}
