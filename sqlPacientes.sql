create database java_conection;
use java_conection;

create table pacientes(
	nom_pac varchar(45) not null,
    edad int default null,
    peso decimal(3,2) default null,
    talla decimal(3,2) default null,
    imc decimal(3,2) default null,
    condicion_pac varchar(45) default null
    ) engine=InnoDB DEFAULT CHARSET=utf8mb4;

SELECT * FROM pacientes;
ALTER TABLE pacientes MODIFY peso DECIMAL(6,2) DEFAULT NULL;
ALTER TABLE pacientes MODIFY talla DECIMAL(6,2) DEFAULT NULL;
ALTER TABLE pacientes MODIFY imc DECIMAL(5,2) DEFAULT NULL;

create table estudiante(
	id_est varchar(10) not null,
    nom_est varchar(45) default null,
    nota1 decimal(3,2) default null,
    nota2 decimal(3,2) default null,
    nota3 decimal(3,2) default null,
    promedio decimal(3,2) default null,
    primary key (id_est)
    ) engine=InnoDB DEFAULT CHARSET=utf8mb4;
    
INSERT INTO estudiante (id_est, nom_est, nota1, nota2, nota3, promedio) VALUES
('0000', 'Ana Gómez',    4.5, 4.2, 4.8, ROUND((4.5+4.2+4.8)/3, 2)),
('0002', 'Luis Pérez',   3.8, 4.0, 3.9, ROUND((3.8+4.0+3.9)/3, 2)),
('0003', 'Carlos Ruiz',  4.1, 3.7, 4.0, ROUND((4.1+3.7+4.0)/3, 2)),
('0004', 'Diana Torres', 5.0, 4.9, 5.0, ROUND((5.0+4.9+5.0)/3, 2)),
('0005', 'José Silva',   3.5, 3.6, 3.4, ROUND((3.5+3.6+3.4)/3, 2)),
('0006', 'María Rojas',  4.3, 4.1, 4.4, ROUND((4.3+4.1+4.4)/3, 2)),
('0007', 'Jorge Díaz',   2.8, 3.0, 2.9, ROUND((2.8+3.0+2.9)/3, 2)),
('0008', 'Sandra León',  4.6, 4.7, 4.5, ROUND((4.6+4.7+4.5)/3, 2)),
('0009', 'Raúl Mejía',   3.2, 3.3, 3.1, ROUND((3.2+3.3+3.1)/3, 2)),
('0010', 'Laura Niño',   4.0, 4.2, 4.1, ROUND((4.0+4.2+4.1)/3, 2));

select * from estudiante;
DELETE FROM estudiante;
