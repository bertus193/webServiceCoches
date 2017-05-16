package org.example.www.alquilervehiculos.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.example.www.alquilervehiculos.Vehiculo;

public class Consultas {
	private final String usu_bd = "root";
	private final String pass_bd = "root";
	
	public Vehiculo[] obtenerVehiculos(String modelo, String marca, String color) {
		List<Vehiculo> lista = new ArrayList<Vehiculo>();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/franquicia", usu_bd,
					pass_bd);
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("select * from vehiculo where modelo= '" + modelo + "' and marca= '" + marca
					+ "' and disponibilidad= '" + "alquiler" + "'");
			while(rs.next()) {
				Vehiculo v = new Vehiculo();
				v.setIdVehiculo(rs.getString(1));
				v.setMarca(rs.getString(2));
				v.setModelo(rs.getString(3));
				v.setPrecio(rs.getFloat(5));
				v.setColor(rs.getString(4));
				v.setMensaje("Coche");
				if(rs.getString(7).equals("alquiler")) {
					lista.add(v);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vehiculo[] retorno = new Vehiculo[lista.size()];
		return lista.toArray(retorno);
	}
	
	public boolean asignarVehiculo(int idCliente, int idVehiculo, java.util.Date fechai, java.util.Date fechaf) {
		boolean asignado = false;
		
		//java.util.Date dt = new java.util.Date();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String inicial = sdf.format(fechai);
		String fin = sdf.format(fechaf);
		System.out.println(inicial);
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost:3306/franquicia",usu_bd, pass_bd);
			Statement s = conexion.createStatement(); 
			int res = s.executeUpdate ("insert into alquiler (idCliente, idVehiculo, fechaini, fechafin) "+"values ('" + idCliente + "', '" + idVehiculo +"', '" + inicial+ "', '" + fin + "')");
			//xecuteUpdate("INSERT INTO users (first_name, last_name, is_admin, num_points) "
			ResultSet rs2 = s.executeQuery ("select * from alquiler where idCliente= '" + idCliente + "' and idVehiculo= '" + idVehiculo + "' and fechaini= '" + inicial + "' and fechafin= '" + fin + "'");
			if(rs2.next()) {
				if(rs2.getInt(2) == idCliente && rs2.getInt(3) == idVehiculo) {
					//Cambiar a alquilado
					s.executeUpdate("update vehiculo set disponibilidad= '" + "alquilado" + "' where id= '" + idVehiculo  + "'");
					asignado = true;
				}
			}
			//cerrar la conexión
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return asignado;
	}

}
