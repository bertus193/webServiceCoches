var mysql = require("mysql");

var config = require("./config")
var framework = require('./framework')

var mysql = {
  con : mysql.createConnection({
          host:     config.host,
          user:     config.user,
          password: config.password
        }),
  
  connect(){
    this.con.connect(function(err){
      if(err){
        console.log('Error connecting to Db');
        return;
      }
      
      console.log('Connection established');
    });
  },
  
  getFranquicia(){
    this.con.query("use " +  config.bd_franquicia + ";");
  },
  
  end(){
      this.con.end(function(err) {
        
      });
  },
  
  getCon(){
    return this.con;
  }
}
 
module.exports = mysql;