var framework = require('./framework');

var franquicia = {

    getAllVehiculos(marca, modelo, callback) {
        var con = framework.getMysql().getCon();
        framework.getMysql().getFranquicia();
        con.query('SELECT * FROM vehiculo', function (err, rows) {
            if (err)
                callback(err);
            callback(undefined, rows);
        });
    },

    getVehiculo(marca, modelo, callback) {
        var con = framework.getMysql().getCon();
        framework.getMysql().getFranquicia();
        con.query('SELECT * FROM vehiculo WHERE marca = "' + marca + '" AND modelo = "' + modelo + '" AND disponibilidad = "Venta"', function (err, rows) {
            if (err)
                callback(err);
            callback(undefined, rows);
        });
    },

    getVehiculoById(idVehiculo, callback) {
        var con = framework.getMysql().getCon();
        framework.getMysql().getFranquicia();
        con.query('SELECT * FROM vehiculo WHERE id = ' + idVehiculo, function (err, rows) {
            if (err)
                callback(err);
            callback(undefined, rows);
        });
    },

    getFactura(idFactura, callback) {
        var con = framework.getMysql().getCon();
        framework.getMysql().getFranquicia();
        con.query('SELECT * FROM factura WHERE id = "' + idFactura + '" ', function (err, rows) {
            if (err)
                callback(err);
            callback(undefined, rows);
        });
    },

    getFacturaProductos(idFactura, callback) {
        var con = framework.getMysql().getCon();
        framework.getMysql().getFranquicia();
        con.query("select l.*, CONCAT(v.marca, ' ', v.modelo) as nombre from linea_factura l, vehiculo v where l.idproducto = v.id and l.idFactura = " + idFactura + " and l.tipo = 'vehiculo' union select l.*, p.nombre from linea_factura l, pieza p where p.id = l.idproducto and l.idFactura = " + idFactura + " and l.tipo = 'pieza' ", function (err, rows) {
            if (err)
                callback(err);
            callback(undefined, rows);
        });
    },

    nuevoPedidoCliente(idCliente, tipo, callback) {
        var con = framework.getMysql().getCon();
        framework.getMysql().getFranquicia();
        con.query('INSERT INTO factura (idCliente, tipo) VALUES (' + idCliente + ', "' + tipo + '") ', function (err, rows) {
            if (err)
                callback(err);
            callback(undefined, rows.insertId);
        });
    },

    addVehiculoPedido(idFactura, idVehiculo, cantidad, precioVehiculo, callback) {
        var total_linea = precioVehiculo * cantidad;
        var con = framework.getMysql().getCon();
        framework.getMysql().getFranquicia();
        con.query('INSERT INTO linea_factura (idFactura, idProducto, cantidad, total_linea, tipo) VALUES (' + idFactura + ',' + idVehiculo + ',' + cantidad + ',' + total_linea + ',"vehiculo") ', function (err, rows) {
            if (err)
                callback(err);
            callback(undefined, rows.insertId);
        });
    },

    actualizarStockVehiculo(idVehiculo, cantidad, callback) {
        var con = framework.getMysql().getCon();
        framework.getMysql().getFranquicia();
        con.query('UPDATE vehiculo SET cantidad = (cantidad + ' + cantidad + ') WHERE id = "' + idVehiculo + '"', function (err, rows) {
            if (err)
                callback(err);
            callback(undefined, rows);
        });
    }



}

module.exports = franquicia;