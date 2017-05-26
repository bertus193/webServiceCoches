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
		else if (result[0] == null) {
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
app.put('/pedido', function (req, res) {
	var body = req.body
	var stringified = JSON.stringify(body)
	stringified = stringified.replace('"[', '[')
	stringified = stringified.replace(']"', ']')
	stringified = stringified.split('\\"').join('"');
	stringified = stringified.replace('"{', '{')
	stringified = stringified.replace('}"', '}')
	body = JSON.parse(stringified)
	//var result = []

	framework.getFranquicia().nuevoPedidoCliente(body["data"]["idCliente"], "venta", function (err, idInsert) {
		if (err) {
			res.send(err)
		}
		else {
			(function next(i) {
				if (i == body["data"]["vehiculos"].length) {
					console.log((idInsert).toString());
					res.send((idInsert).toString())
					//res.contentType('application/json');
					//result.push({ idFactura: idInsert })
					//res.send(JSON.stringify(result));
				}
				else {
					framework.getFranquicia().getVehiculoById(body["data"]["vehiculos"][i]["idVehiculo"], function (err, vehiculo) {
						if (err) {
							res.send(err)
						}
						else if (vehiculo[0].cantidad == null || vehiculo[0].cantidad <= 0) {
							res.send("[Error]No hay stock para el vehiculo " + body["data"]["vehiculos"][i]["idVehiculo"])
							//next(i + 1)
						}
						else if (vehiculo[0].cantidad - body["data"]["vehiculos"][i]["cantidad"] < 0) {
							res.send("[Error]No hay suficiente stock para el vehiculo " + body["data"]["vehiculos"][i]["idVehiculo"])
							//next(i + 1)
						}
						else {
							framework.getFranquicia().addVehiculoPedido(idInsert, vehiculo[0].id, body["data"]["vehiculos"][i]["cantidad"], vehiculo[0].precio, function (err, idInsert) {
								if (err) {
									res.send(err)
								}
								//result.push({ idVehiculo: body["data"]["vehiculos"][i]["idVehiculo"], cantidad: body["data"]["vehiculos"][i]["cantidad"] });
								next(i + 1)
							})
						}
					});
				}
			})(0)
		}
	})
})

//PEDIDO A PROVEEDOR VEHICULO(PUT)
app.put('/pedidoP', function (req, res) {
	var body = req.body

	var proveedorNum = req.body['proveedor']
	var proveedor = framework.getProveedor()
	proveedor.setNum(proveedorNum)

	var result = []

	framework.getFranquicia().nuevoPedidoCliente(body["idCliente"], "pedido", function (err, idInsert) {
		if (err) {
			res.send(err)
		}
		else {
			(function next(i) {
				if (i == body["data"]["vehiculos"].length) {
					res.contentType('application/json');
					res.send(JSON.stringify(result));
				}
				else {
					proveedor.getVehiculo(body["data"]["vehiculos"][i]["marca"], body["data"]["vehiculos"][i]["modelo"], function (err, vehiculo) {
						if (err) {
							res.send(err)
						}
						else if (vehiculo[0].cantidad == null || vehiculo[0].cantidad <= 0) {
							console.log("[Ped][Error]No hay stock para el vehiculo " + body["data"]["vehiculos"][i]["marca"] + " " + body["data"]["vehiculos"][i]["modelo"])
							next(i + 1)
						}
						else if (vehiculo[0].cantidad + body["data"]["vehiculos"][i]["cantidad"] < 0) {
							console.log("[Ped][Error]No hay suficiente stock para el vehiculo " + body["data"]["vehiculos"][i]["marca"] + " " + body["data"]["vehiculos"][i]["modelo"])
							next(i + 1)
						}
						else {
							framework.getFranquicia().addVehiculoPedido(idInsert, vehiculo[0].id, body["data"]["vehiculos"][i]["cantidad"], vehiculo[0].precio, function (err, idInsert) {
								if (err) {
									res.send(err)
								}
								else {
									result.push({ marca: body["data"]["vehiculos"][i]["marca"], modelo: body["data"]["vehiculos"][i]["modelo"], cantidad: body["data"]["vehiculos"][i]["cantidad"] });
									next(i + 1)
								}
							})
						}
					});
				}
			})(0)
		}
	})
})


//MODIFICAR (ACTUALIZA) STOCK FRANQUICIA (POST)
app.post('/stock', function (req, res) {
	var body = req.body;
	var stringified = JSON.stringify(body)
	stringified = stringified.replace('"[', '[')
	stringified = stringified.replace(']"', ']')
	stringified = stringified.split('\\"').join('"');
	stringified = stringified.replace('"{', '{')
	stringified = stringified.replace('}"', '}')
	body = JSON.parse(stringified)
	var result = [];

	(function next(i) {
		if (i == body["data"]["vehiculos"].length) {
			res.contentType('application/json');
			res.send(JSON.stringify(result));
		}
		else {
			framework.getFranquicia().getVehiculoById(body["data"]["vehiculos"][i]["idVehiculo"], function (err, vehiculo) {
				if (err) {
					res.send(err)
				}
				else if (vehiculo[0] == null) {
					res.send("[Stock][Error]No existe el vehiculo " + body["data"]["vehiculos"][i]["idVehiculo"])
					//next(i + 1)
				}
				else if (vehiculo[0].cantidad + body["data"]["vehiculos"][i]["cantidad"] < 0) { //Puede ser negativo
					res.send("[Stock][Error]No hay suficiente stock para el vehiculo " + body["data"]["vehiculos"][i]["idVehiculo"])
					//next(i + 1)
				}
				else {
					framework.getFranquicia().actualizarStockVehiculo(vehiculo[0].id, body["data"]["vehiculos"][i]["cantidad"], function (err, rows) {
						if (err)
							res.send(err)
						result.push({ idVehiculo: vehiculo[0].id, cantidad: -body["data"]["vehiculos"][i]["cantidad"] });
						next(i + 1)
					})
				}
			})
		}
	})(0)
})

//MODIFICAR (ACTUALIZA) STOCK PROVEEDOR (POST)
app.post('/stockP', function (req, res) {
	var body = req.body

	var proveedorNum = req.body["proveedor"]
	var proveedor = framework.getProveedor()
	proveedor.setNum(proveedorNum);

	(function next(i) {
		if (i == body["data"]["vehiculos"].length) {
			res.contentType('application/json');
			res.send(JSON.stringify(result));
		}
		else {
			proveedor.getVehiculo(body["data"]["vehiculos"][i]["marca"], body["data"]["vehiculos"][i]["modelo"], function (err, vehiculo) {
				if (err) {
					res.send(err)
				}
				else if (vehiculo[0].cantidad == null) {
					console.log("[Stock][Error]No existe el vehiculo " + body["data"]["vehiculos"][i]["marca"] + " " + body["data"]["vehiculos"][i]["modelo"])
					next(i + 1)
				}
				else if (vehiculo[0].cantidad + body["data"]["vehiculos"][i]["cantidad"] < 0) { //Puede ser negativo
					console.log("[Stock][Error]No hay suficiente stock para el vehiculo" + body["data"]["vehiculos"][i]["marca"] + " " + body["data"]["vehiculos"][i]["modelo"])
					next(i + 1)
				}
				else {
					proveedor.actualizarStockVehiculo(vehiculo[0].id, body["data"]["vehiculos"][i]["cantidad"], function (err, rows) {
						if (err)
							res.send(err)
						result.push({ idVehiculo: vehiculo[0].id, cantidad: body["data"]["vehiculos"][i]["cantidad"] });
						next(i + 1)
					})
				}
			})
		}
	})(0)
})

//GENERA FACTURA (PUT)
app.put('/factura/', function (req, res) {
	var idFactura = req.body.idFactura
	console.log(req.body)

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