package com.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass{
									
			//Loading a class driver
			String driverclass="oracle.jdbc.driver.OracleDriver";
			// create the connection object
			String con_url="jdbc:oracle:thin:@10.0.0.9:1521:IFSDEV";
			String uname="bnssop",upassword="bnssop";
			private static ConnectionClass conclass = null;
		public ConnectionClass() {
				try {
					
					Class.forName(driverclass);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}

			public Connection getConnection() throws SQLException {
				Connection conn = null;
				conn = DriverManager.getConnection(con_url, "bnssop", "bnssop");
				return conn;
			}

			public static ConnectionClass getInstance() {
				if (conclass == null) {
					conclass = new ConnectionClass();
				}
				return conclass;
			}
		
	}


