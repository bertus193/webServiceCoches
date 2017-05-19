var express = require('express')

var framework = {
  app: express(),

  getMysql() {
    return require('./mysql');
  },

  getPaths() {
    return require('./paths');
  },

  getFranquicia() {
    return require('./franquicia');
  },

  getProveedor() {
    return require('./proveedor')
  }

}

module.exports = framework;