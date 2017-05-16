var framework = require('./framework');

var franquicia = {

    getStockVehiculo(marca, modelo, callback) {
        var con = framework.getMysql().getCon();
        framework.getMysql().getFranquicia();
        con.query('SELECT * FROM vehiculo WHERE marca = "' + marca + '" AND modelo = "' + modelo + '"', function (err, rows) {
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




}

module.exports = franquicia;