CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pm3f4m4fqv89oeeeac4tbe2f4` (`login`)
) ENGINE = InnoDB AUTO_INCREMENT = 9 DEFAULT CHARSET = latin1