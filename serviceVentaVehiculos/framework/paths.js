var bp = require('body-parser')

var framework = require('./framework')

var app = framework.app;
var fs = framework.fs;
var path = framework.path;

//HTML CON PARAMETROS -> RENDER
app.set('view engine', 'ejs');

app.use(bp.urlencoded({ extended: true })) //POST obtener datos
app.use(bp.json())

app.get('/stock', function (req, res) {
	var marca = req.query.marca
	var modelo = req.query.modelo
	framework.getFranquicia().getStockVehiculo(marca, modelo, function (err, result) {
		if (err) {
			res.send(err)
		}
		else if (result[0].cantidad == null) {
			res.send("No hay stock");
		}
		else {
			res.setHeader('Content-Type', 'application/json');
			res.send(result)
		}
	});
	//res.render('../views/login.ejs', {user: 'test' })
})

//PRESUPUESTO A PROVEEDOR V
app.get('/presupuesto', function (req, res) {
	var marca = req.query.marca
	var modelo = req.query.modelo
	var proveedor = req.query.proveedor
	framework.getProveedor().getPresupuestoVehiculo(proveedor, marca, modelo, function (err, result) {
		if (err) {
			res.send(err)
		}
		else if (result == 0) {
			res.send("No hay stock");
		}
		else {
			res.setHeader('Content-Type', 'application/json');
			res.send(result)
		}
	});

})

//PEDIDO A FRANQUICIA
app.put('/pedidoF', function (req, res) {
	res.send("to-do")
})

//PEDIDO A PROVEEDOR
app.put('/pedidoP', function (req, res) {
	res.send("to-do")
})

//MODIFICAR (ACTUALIZA) STOCK
app.post('/stock', function (req, res) {
	resp.send("to-do")
})

//GENERA FACTURA
app.put('/factura', function (req, res) {
	res.send("to-do")
})

app.get('*', function (pet, res) {
	res.status(404);
	res.send('Hola soy express que tal');
})

module.exports = app;