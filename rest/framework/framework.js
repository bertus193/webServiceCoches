var express = require('express')

var framework = {
  app : express(),
  
  getMysql(){
    return require('./mysql');
  },
  
  getPaths(){
    return require('./paths');
  },
  
  getVentas(){
    return require('./ventas');
  }
  
}
 
module.exports = framework;