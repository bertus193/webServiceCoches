
/**
* CompraVehiculosProveedor1Skeleton.java
*
* This file was auto-generated from WSDL
* by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
*/
package org.example.www.compravehiculosproveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.*;



/**
*  CompraVehiculosProveedor1Skeleton java skeleton for the axisService
*/
public class CompraVehiculosProveedor1Skeleton{
	
    /**
    * Auto generated method signature
    * 
    * @param sumarCantidad 
    * @return sumarCantidadResponse 
    */

    public org.example.www.compravehiculosproveedor.SumarCantidadResponse sumarCantidad(org.example.www.compravehiculosproveedor.SumarCantidad sumarCantidad)
    {
    	SumarCantidadResponse salida = new SumarCantidadResponse();	
    	float cantidad = 0;
    	JSONObject obj = new JSONObject(sumarCantidad.getVehiculos());
    	JSONArray arr = obj.getJSONArray("vehiculos");
    	
    	for(int i = 0; i< arr.length();i++){
    		cantidad = cantidad + (arr.getJSONObject(i).getFloat("precio") * arr.getJSONObject(i).getFloat("cantidad"));
    	}	
    	salida.setTotal((int)(Math.round(cantidad)));
    	return salida;
    }


    /**
    * Auto generated method signature
    * 
    * @param actualizarStock 
    * @return actualizarStockResponse 
    */

    public org.example.www.compravehiculosproveedor.ActualizarStockResponse actualizarStock(org.example.www.compravehiculosproveedor.ActualizarStock actualizarStock)
    {
    	ActualizarStockResponse salida = new ActualizarStockResponse();
        Connection con = mysqlConnection.getConnection("proveedorv1");

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

    public org.example.www.compravehiculosproveedor.ComprobarStockResponse comprobarStock(org.example.www.compravehiculosproveedor.ComprobarStock comprobarStock)
    {
    	ComprobarStockResponse salida = new ComprobarStockResponse();
    	JSONObject jo; //salida json
    	JSONArray ja = new JSONArray();
    	JSONObject salidaJSON = new JSONObject();
    
    	
    	
        Connection con = mysqlConnection.getConnection("proveedorv1");
        
        int total;
        String id;
        
        
        
    	JSONObject obj = new JSONObject(comprobarStock.getVehiculos());
    	JSONArray arr = obj.getJSONArray("vehiculos");
    	
        for(int i = 0; i< arr.length(); i++){       
	       	try {
	 			PreparedStatement prepStmt = con.prepareStatement("SELECT id, cantidad, precio FROM vehiculo where id = ? LIMIT 1");
	 			prepStmt.setString(1, arr.getJSONObject(i).getString("id"));
	 			ResultSet rs = prepStmt.executeQuery();
	 			if(rs.next()){
	 				jo = new JSONObject();
	 				jo.put("id", rs.getString("id"));
	 				jo.put("precio", rs.getFloat("precio"));
	 				jo.put("cantidad", arr.getJSONObject(i).getInt("cantidad"));
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

}
