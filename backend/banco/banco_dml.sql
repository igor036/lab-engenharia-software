--INSERT STATUS
INSERT INTO TAB_STATUS (DESCRICAO) VALUES ('ABERTO');
INSERT INTO TAB_STATUS (DESCRICAO) VALUES ('DEFERIDO');
INSERT INTO TAB_STATUS (DESCRICAO) VALUES ('INDEFERIDO');
INSERT INTO TAB_STATUS (DESCRICAO) VALUES ('ENCAMINHADO');

--INSERT PAPEL
INSERT INTO TAB_ROLE (NOME) VALUES ('ADMIN');
INSERT INTO TAB_ROLE (NOME) VALUES ('PROFESSOR');
INSERT INTO TAB_ROLE (NOME) VALUES ('COORDENADOR');
INSERT INTO TAB_ROLE (NOME) VALUES ('SECRETARIA');

-- INSERT BIOTERIO
INSERT INTO TAB_BIOTERIO(NOME, ENDERECO) VALUES ('Biotério A', 'Rua A, n° 62 Centro, Aracaju-SE');

-- INSERT ESPECIES
INSERT INTO TAB_ESPECIE( NOME) VALUES ('Rato');

--INSERT USUARIOS

--Professor
INSERT INTO TAB_DOCENTE(
 nome, email, senha, fk_id_role)
 VALUES ('Professor', 'professor@gmail.com','1234' , 2);

--Coordenador
INSERT INTO TAB_DOCENTE(
 nome, email, senha, fk_id_role)
 VALUES ('Coordenador', 'coordenador@gmail.com','1234' , 3);

--Secretária
INSERT INTO TAB_DOCENTE(
 nome, email, senha, fk_id_role)
 VALUES ('Secretaria', 'secretaria@gmail.com','1234' , 4);

--Administrador
INSERT INTO TAB_DOCENTE(
 nome, email, senha, fk_id_role)
 VALUES ('Administrador', 'administrador@gmail.com','1234' , 1);

