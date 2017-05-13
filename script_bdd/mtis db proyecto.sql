-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-05-2017 a las 12:00:28
-- Versión del servidor: 10.1.19-MariaDB
-- Versión de PHP: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `franquicia`
--
CREATE DATABASE IF NOT EXISTS `franquicia` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `franquicia`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alquiler`
--

CREATE TABLE `alquiler` (
  `id` int(11) NOT NULL,
  `idcliente` int(11) NOT NULL,
  `idvehiculo` int(11) NOT NULL,
  `fechaini` date NOT NULL,
  `fechafin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` int(11) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id` int(11) NOT NULL,
  `idcliente` int(11) NOT NULL,
  `tipo` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea_factura`
--

CREATE TABLE `linea_factura` (
  `id` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `total_linea` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pieza`
--

CREATE TABLE `pieza` (
  `id` int(11) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `precio` float NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `id` int(11) NOT NULL,
  `marca` varchar(200) NOT NULL,
  `modelo` varchar(200) NOT NULL,
  `color` varchar(50) NOT NULL,
  `precio` float NOT NULL,
  `cantidad` int(11) NOT NULL,
  `disponibilidad` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alquiler`
--
ALTER TABLE `alquiler`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `linea_factura`
--
ALTER TABLE `linea_factura`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pieza`
--
ALTER TABLE `pieza`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alquiler`
--
ALTER TABLE `alquiler`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `pieza`
--
ALTER TABLE `pieza`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;--
-- Base de datos: `proveedorp1`
--
CREATE DATABASE IF NOT EXISTS `proveedorp1` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `proveedorp1`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pieza`
--

CREATE TABLE `pieza` (
  `id` int(11) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `precio` float NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `pieza`
--
ALTER TABLE `pieza`
  ADD PRIMARY KEY (`id`);
--
-- Base de datos: `proveedorp2`
--
CREATE DATABASE IF NOT EXISTS `proveedorp2` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `proveedorp2`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pieza`
--

CREATE TABLE `pieza` (
  `id` int(11) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `precio` float NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `pieza`
--
ALTER TABLE `pieza`
  ADD PRIMARY KEY (`id`);
--
-- Base de datos: `proveedorv1`
--
CREATE DATABASE IF NOT EXISTS `proveedorv1` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `proveedorv1`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `id` int(11) NOT NULL,
  `marca` varchar(200) NOT NULL,
  `modelo` varchar(200) NOT NULL,
  `color` varchar(50) NOT NULL,
  `precio` float NOT NULL,
  `cantidad` int(11) NOT NULL,
  `disponibilidad` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`id`);
--
-- Base de datos: `proveedorv2`
--
CREATE DATABASE IF NOT EXISTS `proveedorv2` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `proveedorv2`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `id` int(11) NOT NULL,
  `marca` varchar(200) NOT NULL,
  `modelo` varchar(200) NOT NULL,
  `color` varchar(50) NOT NULL,
  `precio` float NOT NULL,
  `cantidad` int(11) NOT NULL,
  `disponibilidad` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
