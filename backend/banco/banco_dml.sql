--INSERT STATUS
INSERT INTO TAB_STATUS (DESCRICAO) VALUES ('ABERTO');
INSERT INTO TAB_STATUS (DESCRICAO) VALUES ('DEFERIDO');
INSERT INTO TAB_STATUS (DESCRICAO) VALUES ('INDEFERIDO');

--INSERT PAPEL
INSERT INTO TAB_ROLE (NOME) VALUES ('ADMIN');
INSERT INTO TAB_ROLE (NOME) VALUES ('PROFESSOR');
INSERT INTO TAB_ROLE (NOME) VALUES ('COORDENADOR');
INSERT INTO TAB_ROLE (NOME) VALUES ('SECRETARIA');

-- INSERT BIOTERIO
INSERT INTO TAB_BIOTERIO(NOME, ENDERECO) VALUES ('Biotério A', 'Rua A, n° 62 Centro, Aracaju-SE');

-- INSERT ESPECIES
INSERT INTO TAB_ESPECIE( NOME) VALUES ('Rato');
