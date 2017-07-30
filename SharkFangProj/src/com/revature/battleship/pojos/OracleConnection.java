package com.revature.battleship.pojos;

import java.io.File;
import java.io.FileReader;

/*
 * Author: Shawn Barnes
 * Date: 7/7/17
 * File: OracleConnection.java
 * Purpose: Open and maintain "Connection" to the Oracle DB
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class OracleConnection {

	// static connection variable for singleton design pattern
	private static Connection conn;
	private String url;
	private String username;
	private String password;
	private String oracleClass;
	
	// private constructor for singleton design pattern
	private OracleConnection()
	{
		try{
			/*Hashtable<String, String> h = new Hashtable<String, String>(7);
            h.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            h.put(Context.PROVIDER_URL, "t3://localhost:7001");
            h.put(Context.SECURITY_PRINCIPAL, "weblogic");
            h.put(Context.SECURITY_CREDENTIALS, "welcome1");        
            Context initContext = new InitialContext(h);
            DataSource ds = (DataSource)initContext.lookup("oracleBATTLESHIP");
            conn = ds.getConnection();*/
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "BATTLESHIP";
            String password = "P4SSW0RD";
            conn = DriverManager.getConnection(url, username, password);
			//create an instance of java.util.Properties class
			Properties prop = new Properties();
			
			//load the prop instance with the file
			/*prop.load(new FileReader(new File("connection.properties")));
			
			url = prop.getProperty("Url");
			username = prop.getProperty("Username");
			password = prop.getProperty("Password");
			oracleClass = prop.getProperty("OracleClass");*/
			//2 - Load Driver
			/*Class.forName(oracleClass);*/
			
			//3 - Get connection object;
			conn = DriverManager.getConnection(url, username, password);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// static method to create new connection using singleton design pattern
	public static Connection getOracleConnection()
	{
			// check to see if static variable conn has been initialized
			if(conn==null)
			{
				new OracleConnection();
			}
			return conn;
	}
}
