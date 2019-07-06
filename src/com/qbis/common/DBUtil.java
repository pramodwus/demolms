package com.qbis.common;

/**
 * @author Vikram Malik
 * @version 1.0
 * This class provide database connection ,read database details from properties file 
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DBUtil {
	private static String driverClassName;
	private static String databaseurl;
	private static String username;
	private static String password;
	static {
		Properties prop = new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("database.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		driverClassName = prop.getProperty("jdbc.driverClassName");
		databaseurl = prop.getProperty("jdbc.databaseurl");
		username = prop.getProperty("jdbc.username");
		password = prop.getProperty("jdbc.password");

	}

	public static Connection getConnection() {

		Connection con = null;
		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(databaseurl, username, password);
		} catch (Exception ex) {
			System.out.println(ex);

		}

		return con;
	}

	public static void closeResource(Connection con, PreparedStatement pstmt,
			ResultSet rs) {
		try {
			if (con != null) {
				con.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void closeResource(Connection con, PreparedStatement pstmt) {
		try {
			if (con != null) {
				con.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
