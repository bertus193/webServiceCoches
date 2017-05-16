var bp = require('body-parser')

var framework = require('./framework')

var app = framework.app;
var fs = require('fs');
var path = framework.path;
var pdf = require('express-pdf');
var dateFormat = require('dateformat');

//HTML CON PARAMETROS -> RENDER
app.set('view engine', 'ejs');

app.use(bp.urlencoded({ extended: true })) //POST obtener datos
app.use(bp.json())
app.use(pdf)

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

//GENERA FACTURA (PUT)
app.get('/factura', function (req, res) {
	var idFactura = req.query.idFactura

	framework.getFranquicia().getFactura(idFactura, function (err, factura) {
		if (err) {
			res.send(err)
		}
		else if (factura == 0) {
			res.send("No existe ningún pedido con dicha información");
		}
		else {

			framework.getFranquicia().getFacturaProductos(idFactura, function (err, productos) {
				if (err) {
					res.send(err)
				}
				else if (productos == 0) {
					res.send("No existe ningún pedido con dicha información");
				}
				else {
					res.render('../views/factura.ejs', { idFactura: factura[0].id, productos: productos, fecha: dateFormat(Date.parse(factura[0].fecha), 'dd-mm-yyyy') }, function (err, output) {
						res.pdfFromHTML({
							filename: 'generated.pdf',
							htmlContent: output
						})
					});

				}
			});
		}
	})
})

app.get('*', function (pet, res) {
	res.status(404);
	res.send('Hola soy express que tal');
})

module.exports = app