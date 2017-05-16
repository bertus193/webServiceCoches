var framework = require('./framework');

var proveedor = {

    getPresupuestoVehiculo(num, marca, modelo, callback) {
        var con = framework.getMysql().getCon();
        framework.getMysql().getProveedorVehiculos(num);
        con.query('SELECT precio FROM vehiculo WHERE marca = "' + marca + '" AND modelo = "' + modelo + '"', function (err, rows) {
            if (err)
                callback(err);
            callback(undefined, rows);
        });
    }


}

module.exports = proveedor;