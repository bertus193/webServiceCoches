package org.example.www.compravehiculosproveedor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mysqlConnection {
	
	public static Connection getConnection(String bd){
		ResultSet rs = null;
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl = "jdbc:mysql://localhost:3306/"+bd;
			String connectionUser = "root";
			String connectionPassword = "";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static boolean validarConexion(Connection con, String SoapKey){
		boolean salida = false;
		
		try {
			Statement stmt = con.createStatement();
			PreparedStatement prepStmt = con.prepareStatement("SELECT * FROM SoapKey where SoapKey = ? LIMIT 1");
			prepStmt.setString(1, SoapKey);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				salida = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return salida;
	}

}
