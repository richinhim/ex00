package org.josua.web;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

public class MariaDbConnectionTest {

	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	
	private static final String URL = "jdbc:mariadb://localhost:3306/book_ex";
	
	private static final String USER = "myadmin";
	
	private static final String PW = "1234";
	
	@Test
	public void testConnection() throws Exception{
		
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			
			System.out.println(con);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
