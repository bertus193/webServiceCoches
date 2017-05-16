var framework = require('./framework');

var proveedor = {

    getPresupuestoVehiculo(numProveedor, marca, modelo, callback) {
        var con = framework.getMysql().getCon();
        framework.getMysql().getProveedorVehiculos(numProveedor);
        con.query('SELECT * FROM vehiculo WHERE marca = "' + marca + '" AND modelo = "' + modelo + '"', function (err, rows) {
            if (err)
                callback(err);
            callback(undefined, rows);
        });
    }


}

module.exports = proveedor;