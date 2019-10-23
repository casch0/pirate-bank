package com.blued.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static boolean testMode = false;

	public static Connection getConnection() {
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=pirate_bank";
		
		if(testMode) { url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=testing"; }
		
		try {
			Connection conn = DriverManager.getConnection(url, 
								System.getenv("Pirate_Login"), 
								System.getenv("Pirate_PW"));
			
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database connnection failed.");
			return null;
		}
	}
	
}
