var bp = require('body-parser')

var framework = require('./framework')

var app = framework.app;
var fs = framework.fs;
var path = framework.path;

//HTML CON PARAMETROS -> RENDER
app.set('view engine', 'ejs');

app.use(bp.urlencoded({ extended: true })) //POST obtener datos
app.use(bp.json())

app.get('/stock', function(req, resp) {
	var marca = req.query.marca
	var modelo = req.query.modelo
	framework.getFranquicia().getStockVehiculo(marca, modelo, function(err, result){
	if(err){
		resp.send(err)
	}
	else if(result[0].total == null){
		resp.send("No hay stock");
	}
	else{
		resp.send("Total: " + result[0].total)
	}
	});
   //res.render('../views/login.ejs', {user: 'test' })
})

//PRESUPUESTO A PROVEEDOR V
app.get('/presupuesto', function(req, resp) {
	var marca = req.query.marca
	var modelo = req.query.modelo
	var proveedor = req.query.proveedor
	framework.getProveedor().getPresupuestoVehiculo(proveedor, marca, modelo, function(err, result){
	if(err){
		resp.send(err)
	}
	else if(result == 0){
		resp.send("No hay stock");
	}
	else{
		resp.send("Precio: " + result[0].precio)
	}
	});

})

//PEDIDO A FRANQUICIA
app.put('/pedidoF', function(req, resp) {
	resp.send("to-do")
})

//PEDIDO A PROVEEDOR
app.put('/pedidoP', function(req, resp) {
	resp.send("to-do")
})

//MODIFICAR (ACTUALIZA) STOCK
app.post('/stock', function(req, resp) {
	resp.send("to-do")
})

//GENERA FACTURA
app.put('/factura', function(req, resp) {
	resp.send("to-do")
})

app.get('*', function(pet, resp){
	resp.status(404);
	resp.send('Hola soy express que tal');
})

module.exports = app;