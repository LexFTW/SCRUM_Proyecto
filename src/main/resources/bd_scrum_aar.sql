-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-01-2019 a las 15:35:40
-- Versión del servidor: 10.1.33-MariaDB
-- Versión de PHP: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_scrum_aar`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `projects`
--

CREATE TABLE `projects` (
  `ProjectID` int(11) NOT NULL,
  `ProjectTitle` varchar(75) NOT NULL,
  `ProjectDescription` varchar(200) DEFAULT NULL,
  `ScrumMasterID` int(11) NOT NULL,
  `ProductOwnerID` int(11) NOT NULL,
  `CreatedAt` timestamp DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `projects`
--

INSERT INTO `projects` (`ProjectID`, `ProjectTitle`, `ProjectDescription`, `ScrumMasterID`, `ProductOwnerID`, `CreatedAt`, `UpdatedAt`) VALUES
(1, 'Proyecto 1', 'Este proyecto es una prueba', 2, 3, '2019-01-22 15:30:36', '2019-01-22 15:30:36');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `specifications`
--

CREATE TABLE `specifications` (
  `SpecificationID` int(11) NOT NULL,
  `SpecificationTitle` varchar(50) NOT NULL,
  `SpecificationDescription` varchar(150) DEFAULT NULL,
  `SpecificationStatus` tinyint(1) NOT NULL DEFAULT '0',
  `SpecificationTime` time NOT NULL,
  `SprintID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sprints`
--

CREATE TABLE `sprints` (
  `SprintID` int(11) NOT NULL,
  `SprintTime` time NOT NULL,
  `ProjectID` int(11) NOT NULL,
  `CreatedAt` timestamp DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `UserID` int(11) NOT NULL,
  `UserName` varchar(100) NOT NULL,
  `UserLastname` varchar(175) NOT NULL,
  `UserNickname` varchar(50) NOT NULL,
  `UserPassword` varchar(50) NOT NULL,
  `UserEmail` varchar(175) DEFAULT NULL,
  `PermissionID` int(11) NOT NULL,
  `CreatedAt` timestamp DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`UserID`, `UserName`, `UserLastname`, `UserNickname`, `UserPassword`, `UserEmail`, `PermissionID`, `CreatedAt`, `UpdatedAt`) VALUES
(1, 'Alexis', 'Mengual Vázquez', 'Amengual', '5a6d1c612954979ea99ee33dbb2d231b00f6ac0a', 'amengual@correo.com', 1, '2019-01-22 15:07:48', '2019-01-22 15:07:48'),
(2, 'Roger', 'Carballo', 'Rcarballo', '5a6d1c612954979ea99ee33dbb2d231b00f6ac0a', 'rcarballo@correo.com', 2, '2019-01-22 15:08:32', '2019-01-22 15:08:32'),
(3, 'Adrián', 'Salas', 'Asalas', '5a6d1c612954979ea99ee33dbb2d231b00f6ac0a', 'asalas@correo.com', 3, '2019-01-22 15:09:02', '2019-01-22 15:09:02'),
(4, 'Leandro', 'Zabala', 'Lzabala', '5a6d1c612954979ea99ee33dbb2d231b00f6ac0a', 'lzabala@correo.com', 4, '2019-01-22 15:09:36', '2019-01-22 15:09:36');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users_group`
--

CREATE TABLE `users_group` (
  `GroupID` int(11) NOT NULL,
  `GroupName` varchar(50) NOT NULL,
  `CreatedAt` timestamp DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `users_group`
--

INSERT INTO `users_group` (`GroupID`, `GroupName`, `CreatedAt`, `UpdatedAt`) VALUES
(1, 'Grupo 1', '2019-01-22 15:31:59', '2019-01-22 15:31:59');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users_group_integrants`
--

CREATE TABLE `users_group_integrants` (
  `IntegrantID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `GroupID` int(11) NOT NULL,
  `ProjectID` int(11) NOT NULL,
  `CreatedAt` timestamp DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `users_group_integrants`
--

INSERT INTO `users_group_integrants` (`IntegrantID`, `UserID`, `GroupID`, `ProjectID`, `CreatedAt`, `UpdatedAt`) VALUES
(1, 4, 1, 1, '2019-01-22 15:33:17', '2019-01-22 15:33:17'),
(2, 3, 1, 1, '2019-01-22 15:33:32', '2019-01-22 15:33:32');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users_permission`
--

CREATE TABLE `users_permission` (
  `PermissionID` int(11) NOT NULL,
  `PermissionName` varchar(100) NOT NULL,
  `CreatedAt` timestamp DEFAULT CURRENT_TIMESTAMP,
  `UpdatedAt` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `users_permission`
--

INSERT INTO `users_permission` (`PermissionID`, `PermissionName`, `CreatedAt`, `UpdatedAt`) VALUES
(1, 'Administrador', '2019-01-22 14:56:47', '2019-01-22 14:56:47'),
(2, 'Scrum Master', '2019-01-22 14:57:05', '2019-01-22 14:57:05'),
(3, 'Product Owner', '2019-01-22 14:57:29', '2019-01-22 14:57:29'),
(4, 'Developer', '2019-01-22 14:57:44', '2019-01-22 14:57:44');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`ProjectID`),
  ADD KEY `ScrumMasterID` (`ScrumMasterID`),
  ADD KEY `ProductOwnerID` (`ProductOwnerID`);

--
-- Indices de la tabla `specifications`
--
ALTER TABLE `specifications`
  ADD PRIMARY KEY (`SpecificationID`);

--
-- Indices de la tabla `sprints`
--
ALTER TABLE `sprints`
  ADD PRIMARY KEY (`SprintID`),
  ADD KEY `ProjectID` (`ProjectID`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`),
  ADD KEY `PermissionID` (`PermissionID`);

--
-- Indices de la tabla `users_group`
--
ALTER TABLE `users_group`
  ADD PRIMARY KEY (`GroupID`);

--
-- Indices de la tabla `users_group_integrants`
--
ALTER TABLE `users_group_integrants`
  ADD PRIMARY KEY (`IntegrantID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `GroupID` (`GroupID`),
  ADD KEY `ProjectID` (`ProjectID`);

--
-- Indices de la tabla `users_permission`
--
ALTER TABLE `users_permission`
  ADD PRIMARY KEY (`PermissionID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `projects`
--
ALTER TABLE `projects`
  MODIFY `ProjectID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `specifications`
--
ALTER TABLE `specifications`
  MODIFY `SpecificationID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sprints`
--
ALTER TABLE `sprints`
  MODIFY `SprintID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `users_group`
--
ALTER TABLE `users_group`
  MODIFY `GroupID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `users_group_integrants`
--
ALTER TABLE `users_group_integrants`
  MODIFY `IntegrantID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `users_permission`
--
ALTER TABLE `users_permission`
  MODIFY `PermissionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `projects`
--
ALTER TABLE `projects`
  ADD CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`ScrumMasterID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `projects_ibfk_2` FOREIGN KEY (`ProductOwnerID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `sprints`
--
ALTER TABLE `sprints`
  ADD CONSTRAINT `sprints_ibfk_1` FOREIGN KEY (`ProjectID`) REFERENCES `projects` (`ProjectID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`PermissionID`) REFERENCES `users_permission` (`PermissionID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `users_group_integrants`
--
ALTER TABLE `users_group_integrants`
  ADD CONSTRAINT `users_group_integrants_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`),
  ADD CONSTRAINT `users_group_integrants_ibfk_2` FOREIGN KEY (`GroupID`) REFERENCES `users_group` (`GroupID`),
  ADD CONSTRAINT `users_group_integrants_ibfk_3` FOREIGN KEY (`ProjectID`) REFERENCES `projects` (`ProjectID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
