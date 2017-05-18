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
	framework.getFranquicia().getVehiculo(marca, modelo, function (err, result) {
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

//PRESUPUESTO A PROVEEDOR
app.get('/presupuesto', function (req, res) {
	var marca = req.query.marca
	var modelo = req.query.modelo

	var proveedorNum = req.query.proveedor
	var proveedor = framework.getProveedor()
	proveedor.setNum(proveedorNum)

	proveedor.getVehiculo(marca, modelo, function (err, result) {
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

//PEDIDO A FRANQUICIA VEHICULO(PUT)
app.put('/pedidoF', function (req, res) {
	var body = req.body
	framework.getFranquicia().nuevoPedidoCliente(body["idCliente"], "venta", function (err, idInsert) {
		if (err) {
			res.send(err)
		}
		else {
			for (var i in body["vehiculos"]) {
				framework.getFranquicia().getVehiculo(body["vehiculos"][i]["marca"], body["vehiculos"][i]["modelo"], function (err, vehiculo) {
					if (err) {
						res.send(err)
					}
					else if (vehiculo[0].cantidad == null || vehiculo[0].cantidad <= 0) {
						console.log("[Error]No hay stock para el vehiculo" + body["vehiculos"][i]["marca"] + " " + body["vehiculos"][i]["modelo"])
						//res.send("No hay stock para el vehiculo" + body["vehiculos"][i]["marca"] + " " + body["vehiculos"][i]["modelo"])
					}
					else if (vehiculo[0].cantidad - body["vehiculos"][i]["cantidad"] < 0) {
						console.log("[Error]No hay suficiente stock para el vehiculo" + body["vehiculos"][i]["marca"] + " " + body["vehiculos"][i]["modelo"])
					}
					else {
						framework.getFranquicia().addVehiculoPedido(idInsert, vehiculo[0].id, body["vehiculos"][i]["cantidad"], vehiculo[0].precio, function (err, idInsert) {
							if (err) {
								res.send(err)
							}
						})
					}
				});
			}
			res.send("ok")
		}
	})
})

//PEDIDO A PROVEEDOR VEHICULO(PUT)
app.put('/pedidoP', function (req, res) {
	var body = req.body

	var proveedorNum = req.query.proveedor
	var proveedor = framework.getProveedor()
	proveedor.setNum(proveedorNum)

	framework.getFranquicia().nuevoPedidoCliente(body["idCliente"], "pedido", function (err, idInsert) {
		if (err) {
			res.send(err)
		}
		else {
			for (var i in body["vehiculos"]) {
				proveedor.getVehiculo(body["vehiculos"][i]["marca"], body["vehiculos"][i]["modelo"], function (err, vehiculo) {
					if (err) {
						res.send(err)
					}
					else if (vehiculo[0].cantidad == null || vehiculo[0].cantidad <= 0) {
						console.log("[Error]No hay stock para el vehiculo" + body["vehiculos"][i]["marca"] + " " + body["vehiculos"][i]["modelo"])
						//res.send("No hay stock para el vehiculo" + body["vehiculos"][i]["marca"] + " " + body["vehiculos"][i]["modelo"])
					}
					else if (vehiculo[0].cantidad - body["vehiculos"][i]["cantidad"] < 0) {
						console.log("[Error]No hay suficiente stock para el vehiculo" + body["vehiculos"][i]["marca"] + " " + body["vehiculos"][i]["modelo"])
					}
					else {
						framework.getFranquicia().addVehiculoPedido(idInsert, vehiculo[0].id, body["vehiculos"][i]["cantidad"], vehiculo[0].precio, function (err, idInsert) {
							if (err) {
								res.send(err)
							}
						})
					}
				});
			}
			res.send("ok")
		}
	})
})

//MODIFICAR (ACTUALIZA) STOCK FRANQUICIA (POST)
app.post('/stockF', function (req, res) {
	var body = req.body

	for (var i in body["vehiculos"]) {
		framework.getFranquicia().getVehiculo(body["vehiculos"][i]["marca"], body["vehiculos"][i]["modelo"], function (err, vehiculo) {
			if (err) {
				res.send(err)
			}
			else if (vehiculo[0].cantidad == null) {
				console.log("No existe el vehiculo " + body["vehiculos"][i]["marca"] + " " + body["vehiculos"][i]["modelo"])
			}
			else if (vehiculo[0].cantidad + body["vehiculos"][i]["cantidad"] < 0) { //Puede ser negativo
				console.log("[Error]No hay suficiente stock para el vehiculo" + body["vehiculos"][i]["marca"] + " " + body["vehiculos"][i]["modelo"])
			}
			else {
				framework.getFranquicia().actualizarStockVehiculo(vehiculo[0].id, body["vehiculos"][i]["cantidad"], function (err, rows) {
					if (err)
						res.send(err)
				})
			}
		})
	}
	res.send("Stock Actualizado")
})

//MODIFICAR (ACTUALIZA) STOCK PROVEEDOR (POST)
app.post('/stockP', function (req, res) {
	var body = req.body

	var proveedorNum = req.body["proveedor"]
	var proveedor = framework.getProveedor()
	proveedor.setNum(proveedorNum)

	for (var i in body["vehiculos"]) {
		proveedor.getVehiculo(body["vehiculos"][i]["marca"], body["vehiculos"][i]["modelo"], function (err, vehiculo) {
			if (err) {
				res.send(err)
			}
			else if (vehiculo[0].cantidad == null) {
				console.log("No existe el vehiculo " + body["vehiculos"][i]["marca"] + " " + body["vehiculos"][i]["modelo"])
			}
			else if (vehiculo[0].cantidad + body["vehiculos"][i]["cantidad"] < 0) { //Puede ser negativo
				console.log("[Error]No hay suficiente stock para el vehiculo" + body["vehiculos"][i]["marca"] + " " + body["vehiculos"][i]["modelo"])
			}
			else {
				proveedor.actualizarStockVehiculo(vehiculo[0].id, body["vehiculos"][i]["cantidad"], function (err, rows) {
					if (err)
						res.send(err)
				})
			}
		})
	}
	res.send("Stock Actualizado")
})

//GENERA FACTURA (PUT)
app.put('/factura/', function (req, res) {
	var idFactura = req.body.idFactura
	//console.log(req.body)

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