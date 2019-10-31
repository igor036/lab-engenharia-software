-- Table: public.tab_bioterio

-- DROP TABLE public.tab_bioterio;

CREATE TABLE tab_bioterio
(
  id_bioterio serial,
  nome character varying(100) NOT NULL,
  endereco character varying(250) NOT NULL,

  CONSTRAINT tab_bioterio_pkey PRIMARY KEY (id_bioterio)
);

-- Table: public.tab_especie

-- DROP TABLE public.tab_especie;

CREATE TABLE tab_especie
(
  id_especie serial,
  nome character varying(100) NOT NULL,

  CONSTRAINT tab_especie_pkey PRIMARY KEY (id_especie)
);


-- Table: public.tab_role

-- DROP TABLE public.tab_role;

CREATE TABLE tab_role
(
  id_role serial,
  nome character varying(100) NOT NULL,

  CONSTRAINT tab_role_pkey PRIMARY KEY (id_role)
);

-- Table: public.tab_docente

-- DROP TABLE public.tab_docente;

CREATE TABLE tab_docente
(
  matricula serial,
  nome character varying(100) NOT NULL,
  email character varying(250) NOT NULL,
  senha character varying(50),
  fk_id_role integer NOT NULL,

  CONSTRAINT tab_docente_pkey PRIMARY KEY (matricula),

  CONSTRAINT tab_docente_fk_id_role_fkey FOREIGN KEY (fk_id_role)
      REFERENCES public.tab_role (id_role) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: public.tab_status

-- DROP TABLE public.tab_status;

CREATE TABLE tab_status
(
  id_status serial,
  descricao character varying(50),

  CONSTRAINT tab_status_pkey PRIMARY KEY (id_status)
);

-- Table: public.tab_protocolo

-- DROP TABLE public.tab_protocolo;
CREATE TABLE tab_protocolo
(
  id_protocolo serial,
  justificativa character varying(500) NOT NULL,
  resumo_pt character varying(500) NOT NULL,
  resumo_en character varying(500) NOT NULL,
  data_inicio date NOT NULL,
  data_fim date,
  fk_matricula integer NOT NULL,
  fk_id_status integer NOT NULL,

  CONSTRAINT tab_protocolo_pkey PRIMARY KEY (id_protocolo),

  CONSTRAINT tab_protocolo_fk_id_status_fkey FOREIGN KEY (fk_id_status)
      REFERENCES public.tab_status (id_status) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,

  CONSTRAINT tab_protocolo_fk_matricula_fkey FOREIGN KEY (fk_matricula)
      REFERENCES public.tab_docente (matricula) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE tab_animal_requerido (
	fk_protocolo integer NOT NULL,
	fk_especie integer NOT NULL,
	fk_bioterio integer NOT NULL,
	quantidade integer NOT NULL,
	CONSTRAINT pk_animal_requerido 
		PRIMARY KEY (fk_protocolo, fk_especie, fk_bioterio),
  CONSTRAINT tab_animal_requerido_fk_protocolo FOREIGN KEY (fk_protocolo)
      REFERENCES public.tab_protocolo (id_protocolo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


-- Table: public.tab_pedido

-- DROP TABLE public.tab_pedido;

CREATE TABLE tab_pedido
(
  id_pedido serial,
  quantidade integer NOT NULL,
  fk_id_especie integer NOT NULL,
  fk_id_bioterio integer NOT NULL,
  fk_id_protocolo integer NOT NULL,

  CONSTRAINT tab_pedido_pkey PRIMARY KEY (id_pedido),

  CONSTRAINT tab_pedido_fk_id_bioterio_fkey FOREIGN KEY (fk_id_bioterio)
      REFERENCES public.tab_bioterio (id_bioterio) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,

  CONSTRAINT tab_pedido_fk_id_especie_fkey FOREIGN KEY (fk_id_especie)
      REFERENCES public.tab_especie (id_especie) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,

  CONSTRAINT tab_pedido_fk_id_protocolo_fkey FOREIGN KEY (fk_id_protocolo)
      REFERENCES public.tab_protocolo (id_protocolo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Table: public.tab_parecer

-- DROP TABLE public.tab_parecer;

CREATE TABLE tab_parecer
(
  id_parecer serial,
  obs_parecer character varying(500),
  permitir boolean,
  fk_matricula integer,
  fk_id_protocolo integer NOT NULL,

  CONSTRAINT tab_parecer_pkey PRIMARY KEY (id_parecer),

  CONSTRAINT tab_parecer_fk_id_protocolo_fkey FOREIGN KEY (fk_id_protocolo)
      REFERENCES public.tab_protocolo (id_protocolo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,

  CONSTRAINT tab_parecer_fk_matricula_fkey FOREIGN KEY (fk_matricula)
      REFERENCES public.tab_docente (matricula) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);