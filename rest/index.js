var framework = require('./framework/framework')

framework.getMysql().connect();

framework.getMysql().getFranquicia();

var app = framework.getPaths();

app.listen(3000,function(){
	console.log('Marchando el servidor...')

})

module.exports = app;