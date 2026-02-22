INSERT INTO `clientes` (id, nombre, apellido, forma_pago, creado_en, editado_en)VALUES (1,'Rene','Palacios','Debito',NULL,NULL),(2,'John','Doe','Credito',NULL,NULL),(3,'Pepe','Roe','PayPal',NULL,NULL),(4,'Pepaa','Doee','Credito',NULL,NULL);
INSERT INTO direccion(calle, numero) VALUES ('Calle Falsa', 123), ('Avenida Siempre Viva', 742), ('Boulevard de los Sueños Rotos', 456), ('Callejón del Beso', 789);
INSERT INTO tbl_cliente_direcciones (id_cliente, id_direccion) VALUES (1,1);
INSERT INTO tbl_cliente_direcciones (id_cliente, id_direccion) VALUES (1,2);
