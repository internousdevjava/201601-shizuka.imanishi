/**
 *
 */
package com.internousdev.loginTest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * DBconnector DBから情報を取得するメソッド
 * @author internous
 * @since 2015/06/25
 * @param driverName  ドライバー名
 * @param url         url名
 * @param user        ユーザー名
 * @param pass        パスワード
 * @throws ClassNotFoundException
 * @throws SQLException
 * @return con
 */
public class DBConnector{

	private static String driverName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/test"; //struts2 DB名
	private static String user = "root"; //DBのユーザ名
	private static String pass = "mysql";  //DBに接続するときのパスワード
	public static Connection getConnection(){

		Connection con = null;
		try{
			Class.forName(driverName);
			con = DriverManager.getConnection(url,user,pass);
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch (SQLException e){
			e.printStackTrace();
		}

	return con;
	}
}