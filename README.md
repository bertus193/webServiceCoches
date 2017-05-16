# webServiceCoches
Proyecto de Integración Back End y Front End

- serviceAlquilerVehiculos (Sergi)
- serviceReparacionVehiculos (BPEL) (Mario)
- serviceVentaVehiculos (REST) (Alberto)
- serviceCompraVehiculosProveedor
- serviceCompraPiezasProveedor
- serviceGenerarFactura
- serviceCliente (Jorge)
  - listar()
  - crear()
  - buscar()


- cliente (Jorge -)


## Utilidades

Listar productos para factura
```
select l.*, CONCAT(v.marca, ' ', v.modelo) as nombre from linea_factura l, vehiculo v where l.idproducto = v.id and l.idFactura = 1 and l.tipo = 'vehiculo'
union select l.*, p.nombre from linea_factura l, pieza p where p.id = l.idproducto and l.idFactura = 1 and l.tipo = 'pieza' 

```