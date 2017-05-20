
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


/**
*  CompraVehiculosProveedor1Skeleton java skeleton for the axisService
*/
public class CompraVehiculosProveedor1Skeleton{


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
    	
        for(int i = 0; i< actualizarStock.localVehiculos.length; i++){       
	       	try {
	 			PreparedStatement prepStmt = con.prepareStatement("UPDATE vehiculo SET cantidad + ? where id = ?");
	 			prepStmt.setString(1, actualizarStock.localVehiculos[i].getIdVehiculo());
	 			ResultSet rs = prepStmt.executeQuery();
	 			if(!rs.rowUpdated()){
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
        Connection con = mysqlConnection.getConnection("proveedorv1");
        
        int total;
        String id;
        
        for(int i = 0; i< comprobarStock.localVehiculos.length; i++){       
	       	try {
	 			PreparedStatement prepStmt = con.prepareStatement("SELECT id, cantidad FROM vehiculo where id = ? LIMIT 1");
	 			prepStmt.setString(1, comprobarStock.localVehiculos[i].getIdVehiculo());
	 			ResultSet rs = prepStmt.executeQuery();
	 			if(rs.next()){
	 				id  = rs.getString("id");
	 				total = rs.getInt("cantidad");
	 				salida.localVehiculos[i].localIdVehiculo = id;
	 				salida.localVehiculos[i].localLinea_total = total;
	 			}	 			
	 		} catch (SQLException e) {
	 			System.out.println(e.getMessage());
	 		}
        }
        
    	return salida;
    }

}
