var framework = require('./framework');

var franquicia = {

    getStockVehiculo(marca, modelo, callback) {
        var con = framework.getMysql().getCon();
        framework.getMysql().getFranquicia();
        con.query('SELECT sum(cantidad) AS total FROM vehiculo WHERE marca = "' + marca + '" AND modelo = "' + modelo + '"', function (err, rows) {
            if (err)
                callback(err);
            callback(undefined, rows);
        });
    }
}

module.exports = franquicia;