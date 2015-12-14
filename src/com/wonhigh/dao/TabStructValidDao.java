package com.wonhigh.dao;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import com.wonhigh.bean.TabColumnInfo;
import com.wonhigh.busi.TabStructValidBusi;
import com.wonhigh.util.Constants;
import com.wonhigh.util.DateUtils;
import com.wonhigh.util.FileUtils;
import com.wonhigh.util.GlobalInfo;
import com.wonhigh.util.ValidUtils;

public class TabStructValidDao {
	
	/**
	 * 表结构校验启动程序
	 */
	public void startValidTableStruct(){
		FileUtils fileUtils = new FileUtils();
		List<String> DbPropFileList = fileUtils.loadAllFilePathListByDir(Constants.DB_PROP_FILE_DIR);
		for(int i = 0 ; i < DbPropFileList.size() ; i++){
			new TabStructValidBusi().detectSrcTabAndDestTabDiff(DbPropFileList.get(i));
		}
		
	}
	
	/**
	 * 源表在目标库表集中的索引
	 * @param srcTabName
	 * 		源表名称
	 * @param destTabNameList
	 * 		目标表名称集
	 * @return
	 * 		返回源表在目标表集中的索引值
	 */
	public int srcTabIndexOfDestTab(String srcTabName,List<String> destTabNameList,Properties props){
		int location = -1;
		String info = null;
		if(srcTabName == null){
			return location;
		}
		
		if(destTabNameList != null && destTabNameList.size() > 0){
			for (int i = 0; i < destTabNameList.size(); i++){
				if(srcTabName.equalsIgnoreCase(destTabNameList.get(i))){
					return i;
				}
			}
		}
		
		//如果源表在目标库中未检索到，拼接需要记录的日志信息
		info = "警告:[" + GlobalInfo.getSrcDbHost(props) + "]主机上端口为[" 
				+ GlobalInfo.getSrcDbPort(props) + "]的数据库[" + GlobalInfo.getSrcDbName(props) + "]"
				+ "模式[" + GlobalInfo.getSrcSchemaName(props) + "]下["
				+ srcTabName + "]表在主机[" + GlobalInfo.getDestDbHost(props) + "]上端口为[" 
				+ GlobalInfo.getDestDbPort(props) + "]的数据库[" + GlobalInfo.getDestDbName(props) + "]"
				+ "模式[" + GlobalInfo.getDestSchemaName(props) + "]下不存在";
		
		//将比对信息记录到校验日志文件中
		this.writeTabStructCompLog(info,props);
		
		return location;
	}
	
	/**
	 * 源表中的列与目标表中的列进行匹配验证
	 * @param srcTabColInfo
	 * 		源表列信息对象
	 * @param destTabColInfoList
	 * 		目标表列信息对象集
	 * @return
	 * 		返回源表列在目标表列中的索引值
	 */
	public int srcTabColCompWithDestTabCols(TabColumnInfo srcTabColInfo,List<TabColumnInfo> destTabColInfoList,Properties props){
		int location = -1;
		String info = null;
		TabColumnInfo destTabColInfo = null;
		int count = (destTabColInfoList == null ? 0 : destTabColInfoList.size());
		if(srcTabColInfo == null){
			return location;
		}
		for(int i = 0; i < count; i++){
			destTabColInfo = destTabColInfoList.get(i);
			if(location == -1 && srcTabColInfo.getColumnName().equalsIgnoreCase(destTabColInfo.getColumnName())){
				location = i;
				//类型比对
				if(srcTabColInfo.getColumnType() != destTabColInfo.getColumnType() 
						&& !ValidUtils.dataTypeIsConsist(srcTabColInfo.getColumnTypeName(), destTabColInfo.getColumnTypeName())){
					//源表列与目标表中列类型不一致，拼接需要记录的日志信息
					info = "警告:[" + GlobalInfo.getSrcDbHost(props) + "]主机上端口为[" 
							+ GlobalInfo.getSrcDbPort(props) + "]的数据库[" + GlobalInfo.getSrcDbName(props) + "]"
							+ "模式[" + GlobalInfo.getSrcSchemaName(props) + "]下["
							+ srcTabColInfo.getTableName() + "]表中的"
							+ srcTabColInfo.getColumnName() + "列与"
							+ "主机[" + GlobalInfo.getDestDbHost(props) + "]上端口为[" 
							+ GlobalInfo.getDestDbPort(props) + "]的数据库[" + GlobalInfo.getDestDbName(props) + "]"
							+ "模式[" + GlobalInfo.getDestSchemaName(props) + "]下"
							+ destTabColInfo.getTableName() + "表的"
							+ destTabColInfo.getColumnName() + "列类型不一致"
							+ "\n";
					System.out.println(srcTabColInfo.getTableName() + "表：" + srcTabColInfo.getColumnName() + "列" + srcTabColInfo.getColumnTypeName() + "-->" + destTabColInfo.getColumnTypeName());
				}
				//字段长度比对
				if(srcTabColInfo.getColumnSize() != destTabColInfo.getColumnSize() && ValidUtils.isRequireMatchFieldLength(srcTabColInfo.getColumnTypeName())){
					//源表列与目标表中列长度不一致，拼接需要记录的日志信息
					info = "警告:[" + GlobalInfo.getSrcDbHost(props) + "]主机上端口为[" 
							+ GlobalInfo.getSrcDbPort(props) + "]的数据库[" + GlobalInfo.getSrcDbName(props) + "]"
							+ "模式[" + GlobalInfo.getSrcSchemaName(props) + "]下["
							+ srcTabColInfo.getTableName() + "]表中的"
							+ srcTabColInfo.getColumnName() + "列与"
							+ "主机[" + GlobalInfo.getDestDbHost(props) + "]上端口为[" 
							+ GlobalInfo.getDestDbPort(props) + "]的数据库[" + GlobalInfo.getDestDbName(props) + "]"
							+ "模式[" + GlobalInfo.getDestSchemaName(props) + "]下"
							+ destTabColInfo.getTableName() + "表的"
							+ destTabColInfo.getColumnName() + "列长度不一致"
							+ "\n";
				}
				
			}
		}
		
		//源表列在目标表中未找到对应的列，拼接需要记录的日志信息
		if(location == -1){
			info = "警告:[" + GlobalInfo.getSrcDbHost(props) + "]主机上端口为[" 
					+ GlobalInfo.getSrcDbPort(props) + "]的数据库[" + GlobalInfo.getSrcDbName(props) + "]"
					+ "模式[" + GlobalInfo.getSrcSchemaName(props) + "]下["
					+ srcTabColInfo.getTableName() + "]表中的"
					+ srcTabColInfo.getColumnName() + "列不存在于"
					+ "主机[" + GlobalInfo.getDestDbHost(props) + "]上端口为[" 
					+ GlobalInfo.getDestDbPort(props) + "]的数据库[" + GlobalInfo.getDestDbName(props) + "]"
					+ "模式[" + GlobalInfo.getDestSchemaName(props) + "]下"
					+ srcTabColInfo.getTableName() + "表中"
					+ "\n";
		}
		
		//将比对信息记录到校验日志文件中
		this.writeTabStructCompLog(info,props);
		
		return location;
	}
	
	/**
	 * 记录目标表在源表中不存在的额外列信息到校验日志文件中
	 * @param destTabColInfoList
	 * 		目标表字段信息列表
	 */
	public void recordDestTabExtraCols(List<TabColumnInfo> destTabColInfoList,Properties props){
		StringBuffer sbf = null;
		TabColumnInfo tci = null;
		if(destTabColInfoList == null || destTabColInfoList.size() < 1){
			return ;
		}
		sbf = new StringBuffer();
		for(int i = 0 ; i < destTabColInfoList.size() ; i++){
			tci = destTabColInfoList.get(i);
			sbf.append("警告:主机[" + GlobalInfo.getDestDbHost(props) + "]上端口为["); 
			sbf.append(GlobalInfo.getDestDbPort(props) + "]的数据库[" + GlobalInfo.getDestDbName(props) + "]");
			sbf.append("模式[" + GlobalInfo.getDestSchemaName(props) + "]下[");
			sbf.append(tci.getTableName());
			sbf.append("]表中的");
			sbf.append(tci.getColumnName());
			sbf.append("列不存在于");
			sbf.append("主机[" + GlobalInfo.getSrcDbHost(props) + "]上端口为["); 
			sbf.append(GlobalInfo.getSrcDbPort(props) + "]的数据库[" + GlobalInfo.getSrcDbName(props) + "]");
			sbf.append("模式[" + GlobalInfo.getSrcSchemaName(props) + "]下[");
			sbf.append(tci.getTableName());
			sbf.append("]表中");
			sbf.append("\n");
		}
		
		//将比对信息记录到校验日志文件中
		this.writeTabStructCompLog(sbf.toString(),props);
	}
	
	/**
	 * 比较表名是否相等
	 * @param srcTabName
	 * @param destTabName
	 * @return
	 */
	public boolean equalsTabName(String srcTabName,String destTabName){
		if(srcTabName == null || destTabName == null){
			return false;
		}
		//不区分大小写比较大小写比较
		return srcTabName.equalsIgnoreCase(destTabName);
	}
	
	/**
	 * 比较列名是否相等
	 * @param srcColumnName
	 * @param destColumnName
	 * @return
	 */
	public boolean equalsColumnName(String srcColumnName,String destColumnName){
		if(srcColumnName == null || destColumnName == null){
			return false;
		}
		//不区分大小写比较大小写比较
		return srcColumnName.equalsIgnoreCase(destColumnName);
	}
	
	/**
	 * 记录表结构比对信息到校验日志文件中
	 * @param info
	 * 		表结构比对信息
	 */
	public void writeTabStructCompLog(String info,Properties props){
		//如果需要记录的信息为空，什么都不做
		if(info == null || "".equals(info.trim())){
			return ;
		}
		StringBuffer sbf = null;
		try {
			sbf = new StringBuffer("[" + DateUtils.getFormatDate(props.getProperty(Constants.LOG_FILE_DATE_FORMAT)) + "]");
			sbf.append("\n" + info);
			new FileUtils().appendFile(props.getProperty(Constants.TAB_VALID_LOGFILE_NAME),sbf.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}