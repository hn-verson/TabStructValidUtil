package com.wonhigh.util;

import java.util.ArrayList;
import java.util.List;

public class FormatUtil {
	
	/**
	 * 格式化表名称
	 * @return
	 */
	public static List<String> formatTableNameList(List<String> tableNameList,String prefix,String suffix){
		List<String> fmtTableNameList = null;
		String fmtTableName = null;
		int prefixSize = ValidUtils.isEmpty(prefix) ? 0 : prefix.length();
		int suffixSize = ValidUtils.isEmpty(suffix) ? 0 : suffix.length();
		int tableNameSize = 0;
		int fmtTableNameSize = 0;
		boolean isExistRule = false;
		if(tableNameList != null && tableNameList.size() > 0){
			fmtTableNameList = new ArrayList<String>();
		}
		for(String tableName : tableNameList){
			fmtTableName = null;
			isExistRule = false;
			if(!ValidUtils.isEmpty(tableName)){
				tableNameSize = tableName.length();
				fmtTableName = tableName;
				if(prefixSize > 0 && fmtTableName.indexOf(prefix) == 0 && tableNameSize > prefixSize){
					isExistRule = true;
					fmtTableName = tableName.substring(prefixSize, tableNameSize);
					fmtTableNameSize = fmtTableName.length();
				}
				if(suffixSize > 0 && (fmtTableName.indexOf(suffix) == (fmtTableNameSize - suffixSize - 1)) && fmtTableNameSize > suffixSize){
					isExistRule = true;
					fmtTableName = fmtTableName.substring(fmtTableNameSize-prefixSize, fmtTableNameSize);
				}
				if(!ValidUtils.isEmpty(fmtTableName) && isExistRule){
					fmtTableNameList.add(fmtTableName);
				}
			}
		}
		return fmtTableNameList;
	}
	
	/**
	 * 给表名添加业务前缀
	 * @param tableName
	 * @param prefix
	 * @return
	 */
	public static String addBusiPrefixForTable(String tableName,String prefix){
		if(!ValidUtils.isEmpty(tableName) && !ValidUtils.isEmpty(prefix)){
			tableName = prefix+tableName;
		}
		return ValidUtils.isEmpty(tableName) ? null : tableName;
	}
	
}
