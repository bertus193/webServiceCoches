SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `franquicia`
--
CREATE DATABASE IF NOT EXISTS `franquicia` DEFAULT CHARACTER SET UTF8;
USE `franquicia`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alquiler`
--

CREATE TABLE `alquiler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcliente` varchar(30) NOT NULL,
  `idvehiculo` int(11) NOT NULL,
  `fechaini` date NOT NULL,
  `fechafin` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` varchar(30) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcliente` int(11) NOT NULL,
  `tipo` varchar(25) NOT NULL,
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea_factura`
--

CREATE TABLE `linea_factura` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idFactura` int(11) NOT NULL,
  `idproducto` varchar(16) NOT NULL DEFAULT '',
  `cantidad` int(11) NOT NULL,
  `total_linea` decimal(16,2) DEFAULT NULL,
  `tipo` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pieza`
--

CREATE TABLE `pieza` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL DEFAULT '',
  `precio` decimal(10,2) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `marca` varchar(200) NOT NULL,
  `modelo` varchar(200) NOT NULL,
  `color` varchar(50) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `disponibilidad` varchar(15) NOT NULL DEFAULT 00 COMMENT 'venta, alquiler, alquilado',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE DATABASE IF NOT EXISTS `proveedorp1` DEFAULT CHARACTER SET UTF8;
USE `proveedorp1`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pieza`
--

CREATE TABLE `pieza` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE DATABASE IF NOT EXISTS `proveedorp2` DEFAULT CHARACTER SET UTF8;
USE `proveedorp2`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pieza`
--

CREATE TABLE `pieza` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE DATABASE IF NOT EXISTS `proveedorv1` DEFAULT CHARACTER SET UTF8;
USE `proveedorv1`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `marca` varchar(200) NOT NULL,
  `modelo` varchar(200) NOT NULL,
  `color` varchar(50) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `disponibilidad` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Base de datos: `proveedorv2`
--
CREATE DATABASE IF NOT EXISTS `proveedorv2` DEFAULT CHARACTER SET UTF8;
USE `proveedorv2`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `marca` varchar(200) NOT NULL,
  `modelo` varchar(200) NOT NULL,
  `color` varchar(50) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `disponibilidad` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


/*  DATOS POR DEFECTO */
USE franquicia;
INSERT INTO vehiculo VALUES(1,'Toyota','Minolta','plata',250689.52,5,'Venta');
INSERT INTO vehiculo VALUES(2,'Subaru','Impreza','azul',17652.52,7,'Venta');
INSERT INTO vehiculo VALUES(3,'Peugeot','208 3 Puertas','plata',10458.58,2,'Venta');
INSERT INTO vehiculo VALUES(4,'Peugeot','208 5 Puertas','plata',10589.12,6,'Alquiler');
INSERT INTO vehiculo VALUES(5,'Seat','Ibiza','plata',8054.36,8,'Alquiler');

INSERT INTO cliente VALUES('74009198M','Paco','Garcia Perez','Calle Colon Nº12','689563214','paco@gmail.com');

INSERT INTO pieza VALUES(1,'Bujias',50.50, 200);
INSERT INTO pieza VALUES(2,'Tornillos',0.75, 2000);
INSERT INTO pieza VALUES(3,'Ruedas',250, 120);
INSERT INTO pieza VALUES(4,'Pastillas de freno',12.76, 100);
INSERT INTO pieza VALUES(5,'Tubo de escape',90.50, 50);

INSERT INTO `factura` (`id`, `idcliente`, `tipo`, `fecha`) VALUES (1, 1, 'factura', '2017-05-16 14:01:36');
INSERT INTO `linea_factura` (`id`, `idFactura`, `idproducto`, `cantidad`, `total_linea`, `tipo`)
VALUES (1, 1, '1', 2, 101.00, 'pieza'), (2, 1, '2', 1, 17652.52, 'vehiculo');



USE proveedorp1;
INSERT INTO pieza VALUES(1,'Bujias',50.50, 200);
INSERT INTO pieza VALUES(2,'Tornillos',0.75, 2000);
INSERT INTO pieza VALUES(3,'Ruedas',250, 120);
INSERT INTO pieza VALUES(4,'Pastillas de freno',12.76, 100);
INSERT INTO pieza VALUES(5,'Tubo de escape',90.50, 50);

USE proveedorp2;
INSERT INTO pieza VALUES(1,'Bujias',50.50, 200);
INSERT INTO pieza VALUES(2,'Tornillos',0.75, 2000);
INSERT INTO pieza VALUES(3,'Ruedas',250, 120);
INSERT INTO pieza VALUES(4,'Pastillas de freno',12.76, 100);
INSERT INTO pieza VALUES(5,'Tubo de escape',90.50, 50);


USE proveedorv1;
INSERT INTO vehiculo VALUES(1,'Toyota','Minolta','plata',250689.52,50,'Venta');
INSERT INTO vehiculo VALUES(2,'Subaru','Impreza','azul',17652.52,70,'Venta');
INSERT INTO vehiculo VALUES(3,'Peugeot','208 3 Puertas','plata',10458.58,20,'Venta');
INSERT INTO vehiculo VALUES(4,'Peugeot','208 5 Puertas','plata',10589.12,60,'Alquiler');
INSERT INTO vehiculo VALUES(5,'Seat','Ibiza','plata',8054.36,80,'Alquiler');

USE proveedorv2;
INSERT INTO vehiculo VALUES(1,'Toyota','Minolta','plata',305689.52,50,'Venta');
INSERT INTO vehiculo VALUES(2,'Subaru','Impreza','azul',16859.52,70,'Venta');
INSERT INTO vehiculo VALUES(3,'Peugeot','208 3 Puertas','plata',9685.58,20,'Venta');
INSERT INTO vehiculo VALUES(4,'Peugeot','208 5 Puertas','plata',11689.12,60,'Alquiler');
INSERT INTO vehiculo VALUES(5,'Seat','Ibiza','plata',9586.36,80,'Alquiler');
