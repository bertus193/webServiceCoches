var mysql = require("mysql");

var config = require("./config")
var framework = require('./framework')

var mysql = {
  con: mysql.createConnection({
    host: config.host,
    user: config.user,
    password: config.password
  }),

  connect() {
    this.con.connect(function (err) {
      if (err) {
        console.log('Error connecting to Db');
        return;
      }

      console.log('Connection established');
    });
  },

  getFranquicia() {
    this.con.query("use " + config.bd_franquicia + ";");
  },

  getProveedorVehiculos(num) {
    switch (num) {
      case 2:
        this.con.query("use " + config.bd_proveedorv2 + ";")
        break
      default:
        this.con.query("use " + config.bd_proveedorv1 + ";")
        break
    }
  },

  getProveedorPiezas(num) {
    switch (num) {
      case 2:
        this.con.query("use " + config.bd_proveedorp2 + ";")
        break
      default:
        this.con.query("use " + config.bd_proveedorp1 + ";")
        break
    }
  },

  end() {
    this.con.end(function (err) {

    });
  },

  getCon() {
    return this.con;
  }
}

module.exports = mysql;