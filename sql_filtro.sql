CREATE DATABASE _CentroComercial;

use _CentroComercial;

CREATE table tienda(
	id int primary key auto_increment,
    nombre varchar (255) not null, 
    ubicacion varchar (255)
);
CREATE Table producto (
	id int primary key auto_increment,
    nombre varchar (255) not null,
    precio double not null,
    id_tienda int,
    constraint fk_tienda foreign key (id_tienda) 
		references tienda (id) on delete cascade
);

Create Table cliente (
	id int primary key auto_increment,
    nombre varchar (255) not null, 
    ubicacion varchar (255) not null,
    email varchar(255) not null
);

Create table compra (
	id int primary key auto_increment,
    fecha_compra timestamp default current_timestamp,
    id_cliente int,
    id_producto int,
    constraint fk_cliente foreign key (id_cliente) 
		references cliente (id) on delete cascade,
	constraint fk_producto foreign key (id_producto)
		references producto(id) on delete cascade
);

select * from tienda;

select * from producto;
Alter table cliente 
change column ubicacion apellido varchar(255) not null;

Alter table compra
add column cantidad int not null;

select * from compra;

SELECT * FROM compra INNER JOIN cliente ON cliente.id = compra.id_cliente
INNER JOIN producto ON producto.id = compra.id_producto
INNER JOIN tienda ON tienda.id = producto.id_tienda ORDER BY compra.id ASC;