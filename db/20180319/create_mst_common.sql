-- Table: mst_common

-- DROP TABLE mst_common;

CREATE TABLE mst_common
(
  id bigint NOT NULL,
  commonno character varying(10) NOT NULL,
  classno character varying(10) NOT NULL,
  classname character varying(120) NOT NULL,
  chardata1 character varying(1000),
  chardata2 character varying(1000),
  chardata3 character varying(1000),
  chardata4 character varying(1000),
  chardata5 character varying(1000),
  chardata6 text,
  numdata1 numeric(14,3),
  numdata2 numeric(14,3),
  numdata3 numeric(14,3),
  numdata4 numeric(14,3),
  numdata5 numeric(14,3),
  numdata6 numeric(14,3),
  flgdata1 boolean,
  flgdata2 boolean,
  flgdata3 boolean,
  flgdata4 boolean,
  flgdata5 boolean,
  flgdata6 boolean,
  lastupdateempid bigint NOT NULL,
  lastupdatetime timestamp without time zone NOT NULL,
  CONSTRAINT mst_common_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE mst_common
  OWNER TO postgres;
