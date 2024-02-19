DROP TABLE IF EXISTS tipo_cambio;
CREATE TABLE tipo_cambio(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, origen VARCHAR(255), destino VARCHAR(255), tasa FLOAT(1));
CREATE UNIQUE INDEX index1 ON tipo_cambio (origen,destino);
/*INSERT INTO tipo_cambio ("ORIGEN","DESTINO","TASA") VALUES ('USD','PEN',3.85);
INSERT INTO tipo_cambio ("ORIGEN","DESTINO","TASA") VALUES ('PEN','USD',3.85);*/