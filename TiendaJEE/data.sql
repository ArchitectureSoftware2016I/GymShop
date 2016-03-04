
-- -----------------------------------------------------
-- Data for table `tienda`.`Rol`
-- -----------------------------------------------------
START TRANSACTION;
USE `tienda`;
INSERT INTO `tienda`.`Rol` (`name`) VALUES ('admin');

COMMIT;


-- -----------------------------------------------------
-- Data for table `tienda`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `tienda`;
INSERT INTO `tienda`.`User` (`name`, `password`, `username`, `rol`) VALUES ('Administrador', '123456', 'admin', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tienda`.`Permission`
-- -----------------------------------------------------
START TRANSACTION;
USE `tienda`;
INSERT INTO `tienda`.`Permission` (`idPermission`, `name`) VALUES (1, 'Crear cuenta');
INSERT INTO `tienda`.`Permission` (`idPermission`, `name`) VALUES (2, 'Administrar cuentas');
INSERT INTO `tienda`.`Permission` (`idPermission`, `name`) VALUES (3, 'Administrar roles');
INSERT INTO `tienda`.`Permission` (`idPermission`, `name`) VALUES (4, 'Crear producto');
INSERT INTO `tienda`.`Permission` (`idPermission`, `name`) VALUES (5, 'Administrar productos');
INSERT INTO `tienda`.`Permission` (`idPermission`, `name`) VALUES (6, 'Comprar producto');

COMMIT;


-- -----------------------------------------------------
-- Data for table `tienda`.`RolesPermissions`
-- -----------------------------------------------------
START TRANSACTION;
USE `tienda`;
INSERT INTO `tienda`.`RolesPermissions` (`idRolesPermissions`, `idRole`, `idPermission`) VALUES (1, 1, 1);
INSERT INTO `tienda`.`RolesPermissions` (`idRolesPermissions`, `idRole`, `idPermission`) VALUES (2, 1, 2);
INSERT INTO `tienda`.`RolesPermissions` (`idRolesPermissions`, `idRole`, `idPermission`) VALUES (3, 1, 3);
INSERT INTO `tienda`.`RolesPermissions` (`idRolesPermissions`, `idRole`, `idPermission`) VALUES (4, 1, 4);
INSERT INTO `tienda`.`RolesPermissions` (`idRolesPermissions`, `idRole`, `idPermission`) VALUES (5, 1, 5);
INSERT INTO `tienda`.`RolesPermissions` (`idRolesPermissions`, `idRole`, `idPermission`) VALUES (6, 2, 5);
INSERT INTO `tienda`.`RolesPermissions` (`idRolesPermissions`, `idRole`, `idPermission`) VALUES (7, 3, 6);
INSERT INTO `tienda`.`RolesPermissions` (`idRolesPermissions`, `idRole`, `idPermission`) VALUES (8, 4, 1);

COMMIT;