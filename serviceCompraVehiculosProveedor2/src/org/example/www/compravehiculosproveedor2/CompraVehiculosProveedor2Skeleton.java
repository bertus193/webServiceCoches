
/**
* CompraVehiculosProveedor2Skeleton.java
*
* This file was auto-generated from WSDL
* by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
*/
package org.example.www.compravehiculosproveedor2;

import java.sql.*;

import org.json.JSONArray;
import org.json.JSONObject;

/**
*  CompraVehiculosProveedor2Skeleton java skeleton for the axisService
*/
public class CompraVehiculosProveedor2Skeleton{


    /**
    * Auto generated method signature
    * 
    * @param actualizarStock 
    * @return actualizarStockResponse 
    */

    public org.example.www.compravehiculosproveedor2.ActualizarStockResponse actualizarStock(org.example.www.compravehiculosproveedor2.ActualizarStock actualizarStock)
    {
    	ActualizarStockResponse salida = new ActualizarStockResponse();
        Connection con = mysqlConnection.getConnection("proveedorv2");

        boolean error = false;
        
    	JSONObject obj = new JSONObject(actualizarStock.getVehiculos());
    	JSONArray arr = obj.getJSONArray("vehiculos");
    	
        for(int i = 0; i< arr.length(); i++){       
	       	try {
	       		PreparedStatement prepStmt = con.prepareStatement("UPDATE vehiculo SET cantidad = cantidad - ? where id = ?");
	 			prepStmt.setInt(1, arr.getJSONObject(i).getInt("cantidad"));
	 			prepStmt.setString(2, arr.getJSONObject(i).getString("id"));
	 			
	 			int rs = prepStmt.executeUpdate();
	 			if(rs == 0){
	 				error = true;
	 			}		 			
	 		} catch (SQLException e) {
	 			System.out.println(e.getMessage());
	 		}
        }
        salida.setDevolver(false);
        salida.setMensaje("No actualizado");
        if(error == false){
            salida.setDevolver(true);
            salida.setMensaje("Actualizado");
        }
        
    	return salida;
    }


    /**
    * Auto generated method signature
    * 
    * @param comprobarStock 
    * @return comprobarStockResponse 
    */

    public org.example.www.compravehiculosproveedor2.ComprobarStockResponse comprobarStock(org.example.www.compravehiculosproveedor2.ComprobarStock comprobarStock)
    {
    	ComprobarStockResponse salida = new ComprobarStockResponse();
    	JSONObject jo; //salida json
    	JSONArray ja = new JSONArray();
    	JSONObject salidaJSON = new JSONObject();
    	
        Connection con = mysqlConnection.getConnection("proveedorv2");
        
        int total;
        String id;
        
    	JSONObject obj = new JSONObject(comprobarStock.getVehiculos());
    	JSONArray arr = obj.getJSONArray("vehiculos");
        
        for(int i = 0; i< arr.length(); i++){       
	       	try {
	 			PreparedStatement prepStmt = con.prepareStatement("SELECT id, cantidad FROM vehiculo where id = ? LIMIT 1");
	 			prepStmt.setString(1, arr.getJSONObject(i).getString("id"));
	 			ResultSet rs = prepStmt.executeQuery();
	 			if(rs.next()){
	 				jo = new JSONObject();
	 				jo.put("id", rs.getString("id"));
	 				jo.put("cantidad", rs.getInt("cantidad"));
	 				ja.put(jo);
	 			}	 			
	 		} catch (SQLException e) {
	 			System.out.println(e.getMessage());
	 		}
        }
        
        salidaJSON.put("vehiculos", ja);
        
        salida.setVehiculos(salidaJSON.toString());
        
    	return salida;
    }
    
    /**
    * Auto generated method signature
    * 
    * @param sumarCantidad 
    * @return sumarCantidadResponse 
    */

    public org.example.www.compravehiculosproveedor2.SumarCantidadResponse sumarCantidad(org.example.www.compravehiculosproveedor2.SumarCantidad sumarCantidad)
    {
    	SumarCantidadResponse salida = new SumarCantidadResponse();	
    	int cantidad = 0;
    	
    	JSONObject obj = new JSONObject(sumarCantidad.getVehiculos());
    	JSONArray arr = obj.getJSONArray("vehiculos");
    	
    	for(int i = 0; i< arr.length();i++){
    		cantidad = cantidad + arr.getJSONObject(i).getInt("cantidad");
    	}
    	    	
    	salida.setTotal(cantidad);
    	return salida;
    }

}
