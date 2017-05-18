var framework = require('./framework');

var proveedor = {

    numeroProveedor: 1,

    setNum(num) {
        numeroProveedor = num
    },

    getVehiculo(marca, modelo, callback) {
        var con = framework.getMysql().getCon();
        framework.getMysql().getProveedorVehiculos(numeroProveedor);
        con.query('SELECT * FROM vehiculo WHERE marca = "' + marca + '" AND modelo = "' + modelo + '"', function (err, rows) {
            if (err)
                callback(err);
            callback(undefined, rows);
        });
    },

    actualizarStockVehiculo(idVehiculo, cantidad, callback) {
        var con = framework.getMysql().getCon();
        framework.getMysql().getProveedorVehiculos(numeroProveedor);
        con.query('UPDATE vehiculo SET cantidad = (cantidad + ' + cantidad + ') WHERE id = ' + idVehiculo, function (err, rows) {
            if (err)
                callback(err);
            callback(undefined, rows);
        });
    }


}

module.exports = proveedor;