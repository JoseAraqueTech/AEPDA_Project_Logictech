CREATE DATABASE bbdd_aepda;

USE bbdd_aepda;

CREATE TABLE activitats (
    id_activitat INT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    data DATE NOT NULL,
    tipus VARCHAR(30) NOT NULL,
    professor VARCHAR(100),
    nivell VARCHAR(30)
);

CREATE TABLE usuarios (
    dni VARCHAR(20) PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    soci BOOLEAN DEFAULT FALSE,
    mesos_membresia INT DEFAULT 0,
    participaciones INT DEFAULT 0,
    saldo DOUBLE DEFAULT 0,
    id_activitat INT,
    CONSTRAINT fk_usuario_activitat
        FOREIGN KEY (id_activitat)
        REFERENCES activitats(id_activitat)
        ON DELETE SET NULL
);

CREATE TABLE balda (
    id INT PRIMARY KEY,
    ubicacion VARCHAR(100) NOT NULL,
    asignacion_actual VARCHAR(20),
    FOREIGN KEY (asignacion_actual)
        REFERENCES usuarios(dni)
        ON DELETE SET NULL
);