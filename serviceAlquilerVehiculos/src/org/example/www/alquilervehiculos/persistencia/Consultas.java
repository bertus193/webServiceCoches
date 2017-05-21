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

import org.example.www.alquilervehiculos.Asignacion;
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
			
			String query = "select * from vehiculo where disponibilidad = 'alquiler'";
			if(!marca.equals("")) {
				query += " and marca= '" + marca + "'";
			}
			if(!modelo.equals("")) {
				query += " and modelo= '" + modelo + "'";
			}
			if(!color.equals("")) {
				query += " and color= '" + color + "'";
			}
			System.out.println(query);
			ResultSet rs = s.executeQuery(query);
			while(rs.next()) {
				Vehiculo v = new Vehiculo();
				v.setIdVehiculo(rs.getInt(1));
				v.setMarca(rs.getString(2));
				v.setModelo(rs.getString(3));
				v.setPrecio(rs.getFloat(5));
				v.setColor(rs.getString(4));
				v.setMensaje("Coche");
				if(rs.getString(7).equals("alquiler")) {
					lista.add(v);
				}
				System.out.println(v.getMarca());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vehiculo[] retorno = new Vehiculo[lista.size()];
		return lista.toArray(retorno);
	}
	
	public boolean asignarVehiculo(Asignacion[] alquileres) {
		boolean asignado = true;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost:3306/franquicia",usu_bd, pass_bd);
			Statement s = conexion.createStatement(); 
			
			//Insertar en alquiler los nuevos alquileres
			for(int i = 0; i < alquileres.length; i++) {
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String inicial = sdf.format(alquileres[i].getFechaini());
				String fin = sdf.format(alquileres[i].getFechafin());
				s.executeUpdate ("insert into alquiler (idCliente, idVehiculo, fechaini, fechafin) "+"values ('" + alquileres[i].getIdCliente() + "', '" + alquileres[i].getIdVehiculo() +"', '" + inicial+ "', '" + fin + "')");
				//int res = s.executeUpdate ("insert into alquiler (idCliente, idVehiculo, fechaini, fechafin) "+"values ('" + alquileres[i].getIdCliente() + "', '" + alquileres[i].getIdVehiculo() +"', '" + inicial+ "', '" + fin + "')");
			}
			
			//Comprobar si se han introducido BD
			for(int x = 0; x < alquileres.length; x++) {
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String inicial = sdf.format(alquileres[x].getFechaini());
				String fin = sdf.format(alquileres[x].getFechafin());
				ResultSet rs2 = s.executeQuery ("select * from alquiler where idCliente= '" + alquileres[x].getIdCliente() + "' and idVehiculo= '" + alquileres[x].getIdVehiculo() + "' and fechaini= '" + inicial + "' and fechafin= '" + fin + "'");
				if(!rs2.next()) {
					asignado = false;
				}
			}
			
			//Modificar a alquilado en vehiculos
			if(asignado) {
				for(int j = 0; j < alquileres.length; j++) {
					s.executeUpdate("update vehiculo set disponibilidad= '" + "alquilado" + "' where id= '" + alquileres[j].getIdVehiculo()  + "'");
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
