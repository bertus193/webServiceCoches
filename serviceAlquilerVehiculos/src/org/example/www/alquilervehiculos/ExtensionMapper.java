
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:18:31 BST)
 */

package org.example.www.alquilervehiculos;

/**
 * ExtensionMapper class
 */
@SuppressWarnings({ "unchecked", "unused" })

public class ExtensionMapper {

	public static java.lang.Object getTypeObject(java.lang.String namespaceURI, java.lang.String typeName,
			javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {

		if ("http://www.example.org/AlquilerVehiculos/".equals(namespaceURI) && "Asignacion".equals(typeName)) {

			return org.example.www.alquilervehiculos.Asignacion.Factory.parse(reader);

		}

		if ("http://www.example.org/AlquilerVehiculos/".equals(namespaceURI) && "Vehiculo".equals(typeName)) {

			return org.example.www.alquilervehiculos.Vehiculo.Factory.parse(reader);

		}

		if ("http://www.example.org/AlquilerVehiculos/".equals(namespaceURI) && "VehiculosAsignados".equals(typeName)) {

			return org.example.www.alquilervehiculos.VehiculosAsignados.Factory.parse(reader);

		}

		throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
	}

}
