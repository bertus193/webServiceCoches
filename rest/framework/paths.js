var bp = require('body-parser')

var framework = require('./framework')

var app = framework.app;
var fs = framework.fs;
var path = framework.path;

//HTML CON PARAMETROS -> RENDER
app.set('view engine', 'ejs');

app.use(bp.urlencoded({ extended: true })) //POST obtener datos
app.use(bp.json())

app.get('*', function(pet, resp){
	resp.status(404);
	resp.send('Hola soy express que tal');
})

module.exports = app;