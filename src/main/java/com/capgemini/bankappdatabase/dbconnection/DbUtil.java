package com.capgemini.bankappdatabase.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@PropertySource("classpath:application.properties")
public class DbUtil {
	@Autowired
	Environment env;

	
	public  Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(env.getProperty("db.url"), env.getProperty("db.user"),env.getProperty("db.password"));
			if (connection != null)
				System.out.println("connection established");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
