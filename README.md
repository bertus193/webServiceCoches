# webServiceCoches
Proyecto de Integración Mule

- serviceAlquilerVehiculos 
- serviceReparacionVehiculos (BPEL)
- serviceVentaVehiculos (REST)
- serviceCompraVehiculosProveedor
- serviceCompraPiezasProveedor
- serviceGenerarFactura
    - enviarFactura (REST)
- serviceCliente
- manageCompraVehiculosProveedor (BPEL)
- cliente
- franquiciamtis (Mule)

## Utilidades

Listar productos para factura
```
select l.*, CONCAT(v.marca, ' ', v.modelo) as nombre from linea_factura l, vehiculo v where l.idproducto = v.id and l.idFactura = 1 and l.tipo = 'vehiculo'
union select l.*, p.nombre from linea_factura l, pieza p where p.id = l.idproducto and l.idFactura = 1 and l.tipo = 'pieza' 

```
