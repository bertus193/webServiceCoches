# webServiceCoches
Proyecto de integración en Mule. La integración se compone de: 

- Servicios web.
    - Enfoque top-down.
    - Java (Eclipse).
    - Acceso a base de datos.
- Orquestación de servicios mediante BPEL.
- Servicios web API RESTful.
    - Node.js.
    - Lógica de negocio MVC.
    - Acceso a base de datos.
    - Express.
    - POST, GET, PUT.
    - JSON.
- Integración de todo mediante MuleESB.
    - Integración en flujo BPEL + Peticiones API Rest + Servicios Web.

### Servicios web

- serviceAlquilerVehiculos
- serviceCompraVehiculosProveedor
- serviceCompraPiezasProveedor

### BPEL

- manageCompraVehiculosProveedor

### REST

- serviceVentaVehiculos
- serviceGenerarFactura:enviarFactura

## Puesta en marcha del proyecto

- ServiceCompraVehiculos (1 y 2)

    Para poner en marcha ambos  services será necesario configurar la base de datos desde: serviceCompraVehiculos(1 o 2) `/src/org/example/.../mysqlConnection.java`

- REST

    Para poner en marcha REST será necesaria la configuración del servidor Node desde `serviceVentaVehiculos/framework/config.js`
    
    Para arrancar el servidor desde serviceVentaVehiculos se ejecutará `node index.js`

- Alquiler

    Configuración de la base de datos: `/src/org/example/.../Consultas.java`




## Ejemplo generación de facturas:

 ![facturaEjemplo](https://user-images.githubusercontent.com/22213393/27098980-a48b7408-5079-11e7-8104-65177247db2e.png)

## Utilidades

Listar productos para factura
```
select l.*, CONCAT(v.marca, ' ', v.modelo) as nombre from linea_factura l, vehiculo v where l.idproducto = v.id and l.idFactura = 1 and l.tipo = 'vehiculo'
union select l.*, p.nombre from linea_factura l, pieza p where p.id = l.idproducto and l.idFactura = 1 and l.tipo = 'pieza' 

```