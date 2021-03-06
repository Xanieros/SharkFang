package com.revature.battleship.pojos;

/*
 * Author: Shawn Barnes
 * Date: 7/7/17
 * File: OracleConnection.java
 * Purpose: Open and maintain "Connection" to the Oracle DB
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import java.io.File;
import java.io.FileReader;

public class OracleConnection {

	// static connection variable for singleton design pattern
	private static Connection conn = null;
    private String url = "jdbc:oracle:thin:@battleship.cxpxhpeerkg0.us-east-1.rds.amazonaws.com:1521:xe";
    private String username = "BATTLESHIP";
    private String password = "P4SSW0RD";
	private String oracleClass = "oracle.jdbc.driver.OracleDriver";
	
	// private constructor for singleton design pattern
	private OracleConnection()
	{
		try{
			Class.forName(oracleClass);
      conn = DriverManager.getConnection(url, username, password);
			//create an instance of java.util.Properties class
			//Properties prop = new Properties();
			
			/*//load the prop instance with the file
			prop.load(new FileReader(new File("connection.properties")));

			url = prop.getProperty("Url");
			username = prop.getProperty("Username");
			password = prop.getProperty("Password");
			oracleClass = prop.getProperty("OracleClass");
			// 2 - Load Driver
			Class.forName(oracleClass);
			
			//3 - Get connection object;
			conn = DriverManager.getConnection(url, username, password);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// static method to create new connection using singleton design pattern
	public static Connection getOracleConnection() {

		if (conn == null) {
			new OracleConnection();
		}

		return conn;
	}
	
	public static Connection close()
	{
		try
		{
			conn.close();
			conn = null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
