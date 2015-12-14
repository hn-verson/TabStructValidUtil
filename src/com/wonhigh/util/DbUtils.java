package com.wonhigh.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DbUtils {
	
	public DbUtils() {
		// do nothing
	}
	
	/**
	 * 加载数据库驱动
	 * @param driverClass
	 * @throws ClassNotFoundException
	 */
	public static void loadDbDriver(String driverClass) throws ClassNotFoundException{
		Class.forName(driverClass);
	}
	
	/**
	 * 关闭连接
	 * @param conn
	 * @throws SQLException
	 */
	public static void close(Connection conn) throws SQLException{
		if (conn != null){
			conn.close();
		}
	}
	
	/**
	 * 关闭结果集
	 * @param rs
	 * @throws SQLException
	 */
	public static void close(ResultSet rs) throws SQLException{
		if (rs != null){
			rs.close();
		}
	}
	
	/**
	 * 关闭语句
	 * @param st
	 * @throws SQLException
	 */
	public static void close(Statement stmt) throws SQLException{
		if (stmt != null){
			stmt.close();
		}
	}
	
	/**
	 * 提交并关闭连接
	 * @param conn
	 * @throws SQLException
	 */
	public static void commitAndClose(Connection conn) throws SQLException{
		if (conn != null){
			try{
				conn.commit();
			}finally{
				conn.close();
			}
		}
	}
	
	/**
	 * 回滚所有的改变
	 * @param conn
	 * @throws SQLException
	 */
	public static void rollback(Connection conn) throws SQLException{
		if (null != null){
			conn.rollback();
		}
	}
	
	/**
	 * 回滚并关闭连接
	 * @param conn
	 * @throws SQLException
	 */
	public static void rollbackAndClose(Connection conn) throws SQLException{
		if (conn != null){
			try{
				conn.rollback();
			}finally{
				conn.close();
			}
		}
	}
	
}
