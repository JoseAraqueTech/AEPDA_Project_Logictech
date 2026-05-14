CREATE TABLE usuarios (
    dni VARCHAR(20) PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    soci BOOLEAN NOT NULL DEFAULT FALSE,
    meses_membresia INT DEFAULT 0,
    participaciones int DEFAULT 0,
    id_activitat INT,
    ADD CONSTRAINT fk_usuario_activitat
    FOREIGN KEY (id_activitat)
    REFERENCES activitats(id_activitat)
    ON DELETE SET NULL
);

CREATE TABLE socios (
    dni VARCHAR(20) PRIMARY KEY,
    saldo DOUBLE,

    FOREIGN KEY (dni)
    REFERENCES usuarios(dni)
    ON DELETE CASCADE
);

CREATE TABLE activitats (
    id_activitat INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    data DATE NOT NULL,
    tipus VARCHAR(50) NOT NULL
);


CREATE TABLE curso_pintura (
    id_activitat INT PRIMARY KEY,
    profesor VARCHAR(100) NOT NULL,
    nivell varchar(20),

    FOREIGN KEY (id_activitat)
        REFERENCES activitats(id_activitat)
        ON DELETE CASCADE
);

CREATE TABLE torneo (
    id_activitat INT PRIMARY KEY,

    FOREIGN KEY (id_activitat)
        REFERENCES activitats(id_activitat)
        ON DELETE CASCADE
);

CREATE TABLE balda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ubicacion VARCHAR(100) NOT NULL,
    ocupada BOOLEAN NOT NULL DEFAULT FALSE,

    asignacion_actual VARCHAR(20),

    FOREIGN KEY (asignacion_actual)
        REFERENCES usuarios(dni)
        ON DELETE SET NULL
);
