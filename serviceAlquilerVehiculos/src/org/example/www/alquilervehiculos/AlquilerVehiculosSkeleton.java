
/**
 * AlquilerVehiculosSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package org.example.www.alquilervehiculos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * AlquilerVehiculosSkeleton java skeleton for the axisService
 */
public class AlquilerVehiculosSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param asignarVehiculos
	 * @return asignarVehiculosResponse
	 */

	public org.example.www.alquilervehiculos.AsignarVehiculosResponse asignarVehiculos(
			org.example.www.alquilervehiculos.AsignarVehiculos asignarVehiculos) {
		
		//int idCliente = asignarVehiculos.getIdCliente();
		//int idVehiculo = asignarVehiculos.getIdVehiculo();
		//Date fechai = asignarVehiculos.getFechaini();
		//Date fechaf = asignarVehiculos.getFechafin();
		
		Asignacion[] alquilar = asignarVehiculos.getAsignacion();
		
		org.example.www.alquilervehiculos.persistencia.Consultas consulta = new org.example.www.alquilervehiculos.persistencia.Consultas();
		
		boolean asignado = consulta.asignarVehiculo(alquilar);
		
		AsignarVehiculosResponse av = new AsignarVehiculosResponse();
		
		//av.setAsignado(asignado);
		List<VehiculosAsignados> lista = new ArrayList<VehiculosAsignados>();
		
		for(int i=0; i<alquilar.length; i++) {
			VehiculosAsignados v = new VehiculosAsignados();
			v.setIdCliente(alquilar[i].getIdCliente());
			v.setIdVehiculo(alquilar[i].getIdVehiculo());
			lista.add(v);
		}
		VehiculosAsignados[] retorno = new VehiculosAsignados[lista.size()];
		retorno = lista.toArray(retorno);
		
		if(asignado) {
			av.setVehiculos(retorno);
		} else {
			av.setVehiculos(retorno);
		}
		
		return av;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param obtenerVehiculos
	 * @return obtenerVehiculosResponse
	 */

	public org.example.www.alquilervehiculos.ObtenerVehiculosResponse obtenerVehiculos(
			org.example.www.alquilervehiculos.ObtenerVehiculos obtenerVehiculos) {

		String modelo = obtenerVehiculos.getModelo();
		String marca = obtenerVehiculos.getMarca();
		String color = obtenerVehiculos.getColor();
		
		org.example.www.alquilervehiculos.persistencia.Consultas consulta = new org.example.www.alquilervehiculos.persistencia.Consultas();
		
		Vehiculo[] vehiculos = consulta.obtenerVehiculos(modelo, marca, color);
		
		ObtenerVehiculosResponse ov = new ObtenerVehiculosResponse();
		
		ov.setVehiculos(vehiculos);
		
		return ov;
	}
	
	/**
	 * Auto generated method signature
	 * 
	 * @param generarPresupuesto
	 * @return generarPresupuestoResponse
	 */

	public org.example.www.alquilervehiculos.GenerarPresupuestoResponse generarPresupuesto(
			org.example.www.alquilervehiculos.GenerarPresupuesto generarPresupuesto) {
		
		VehiculosAsignados[] vehiculos = generarPresupuesto.getVehiculos();
		
		org.example.www.alquilervehiculos.persistencia.Consultas consulta = new org.example.www.alquilervehiculos.persistencia.Consultas();
		
		float precio = consulta.generarPresupuesto(vehiculos);
		
		GenerarPresupuestoResponse gp = new GenerarPresupuestoResponse();
		
		gp.setPrecio(precio);
		
		return gp;
	}

}
