
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:18:31 BST)
 */

        
            package org.example.www.compravehiculosproveedor;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://www.example.org/CompraVehiculosProveedor/".equals(namespaceURI) &&
                  "Vehiculo2".equals(typeName)){
                   
                            return  org.example.www.compravehiculosproveedor.Vehiculo2.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/CompraVehiculosProveedor/".equals(namespaceURI) &&
                  "Vehiculo".equals(typeName)){
                   
                            return  org.example.www.compravehiculosproveedor.Vehiculo.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    