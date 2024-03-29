
/**
 * CompraVehiculosProveedor1MessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
        package org.example.www.compravehiculosproveedor;

        /**
        *  CompraVehiculosProveedor1MessageReceiverInOut message receiver
        */

        public class CompraVehiculosProveedor1MessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        CompraVehiculosProveedor1Skeleton skel = (CompraVehiculosProveedor1Skeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){


        

            if("sumarCantidad".equals(methodName)){
                
                org.example.www.compravehiculosproveedor.SumarCantidadResponse sumarCantidadResponse13 = null;
	                        org.example.www.compravehiculosproveedor.SumarCantidad wrappedParam =
                                                             (org.example.www.compravehiculosproveedor.SumarCantidad)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.compravehiculosproveedor.SumarCantidad.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               sumarCantidadResponse13 =
                                                   
                                                   
                                                         skel.sumarCantidad(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), sumarCantidadResponse13, false, new javax.xml.namespace.QName("http://www.example.org/CompraVehiculosProveedor/",
                                                    "sumarCantidad"));
                                    } else 

            if("actualizarStock".equals(methodName)){
                
                org.example.www.compravehiculosproveedor.ActualizarStockResponse actualizarStockResponse15 = null;
	                        org.example.www.compravehiculosproveedor.ActualizarStock wrappedParam =
                                                             (org.example.www.compravehiculosproveedor.ActualizarStock)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.compravehiculosproveedor.ActualizarStock.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               actualizarStockResponse15 =
                                                   
                                                   
                                                         skel.actualizarStock(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), actualizarStockResponse15, false, new javax.xml.namespace.QName("http://www.example.org/CompraVehiculosProveedor/",
                                                    "actualizarStock"));
                                    } else 

            if("comprobarStock".equals(methodName)){
                
                org.example.www.compravehiculosproveedor.ComprobarStockResponse comprobarStockResponse17 = null;
	                        org.example.www.compravehiculosproveedor.ComprobarStock wrappedParam =
                                                             (org.example.www.compravehiculosproveedor.ComprobarStock)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.compravehiculosproveedor.ComprobarStock.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               comprobarStockResponse17 =
                                                   
                                                   
                                                         skel.comprobarStock(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), comprobarStockResponse17, false, new javax.xml.namespace.QName("http://www.example.org/CompraVehiculosProveedor/",
                                                    "comprobarStock"));
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.compravehiculosproveedor.SumarCantidad param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.compravehiculosproveedor.SumarCantidad.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.compravehiculosproveedor.SumarCantidadResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.compravehiculosproveedor.SumarCantidadResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.compravehiculosproveedor.ActualizarStock param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.compravehiculosproveedor.ActualizarStock.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.compravehiculosproveedor.ActualizarStockResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.compravehiculosproveedor.ActualizarStockResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.compravehiculosproveedor.ComprobarStock param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.compravehiculosproveedor.ComprobarStock.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.compravehiculosproveedor.ComprobarStockResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.compravehiculosproveedor.ComprobarStockResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.compravehiculosproveedor.SumarCantidadResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.compravehiculosproveedor.SumarCantidadResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.compravehiculosproveedor.SumarCantidadResponse wrapsumarCantidad(){
                                org.example.www.compravehiculosproveedor.SumarCantidadResponse wrappedElement = new org.example.www.compravehiculosproveedor.SumarCantidadResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.compravehiculosproveedor.ActualizarStockResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.compravehiculosproveedor.ActualizarStockResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.compravehiculosproveedor.ActualizarStockResponse wrapactualizarStock(){
                                org.example.www.compravehiculosproveedor.ActualizarStockResponse wrappedElement = new org.example.www.compravehiculosproveedor.ActualizarStockResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.compravehiculosproveedor.ComprobarStockResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.compravehiculosproveedor.ComprobarStockResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.compravehiculosproveedor.ComprobarStockResponse wrapcomprobarStock(){
                                org.example.www.compravehiculosproveedor.ComprobarStockResponse wrappedElement = new org.example.www.compravehiculosproveedor.ComprobarStockResponse();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (org.example.www.compravehiculosproveedor.ActualizarStock.class.equals(type)){
                
                        return org.example.www.compravehiculosproveedor.ActualizarStock.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.compravehiculosproveedor.ActualizarStockResponse.class.equals(type)){
                
                        return org.example.www.compravehiculosproveedor.ActualizarStockResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.compravehiculosproveedor.ComprobarStock.class.equals(type)){
                
                        return org.example.www.compravehiculosproveedor.ComprobarStock.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.compravehiculosproveedor.ComprobarStockResponse.class.equals(type)){
                
                        return org.example.www.compravehiculosproveedor.ComprobarStockResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.compravehiculosproveedor.SumarCantidad.class.equals(type)){
                
                        return org.example.www.compravehiculosproveedor.SumarCantidad.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.compravehiculosproveedor.SumarCantidadResponse.class.equals(type)){
                
                        return org.example.www.compravehiculosproveedor.SumarCantidadResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    