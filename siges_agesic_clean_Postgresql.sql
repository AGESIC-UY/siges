--
-- PostgreSQL database dump


SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 11756)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3642 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 177 (class 1259 OID 51787)
-- Name: adquisicion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE adquisicion (
    adq_pk integer NOT NULL,
    adq_pre_fk integer NOT NULL,
    adq_nombre character varying(300) NOT NULL,
    adq_prov_orga_fk integer,
    adq_fuente_fk integer,
    adq_moneda_fk integer,
    adq_proc_compra character varying(20),
    adq_proc_compra_grp character varying(20)
);


ALTER TABLE adquisicion OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 51785)
-- Name: adquisicion_adq_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE adquisicion_adq_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE adquisicion_adq_pk_seq OWNER TO postgres;

--
-- TOC entry 3643 (class 0 OID 0)
-- Dependencies: 176
-- Name: adquisicion_adq_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE adquisicion_adq_pk_seq OWNED BY adquisicion.adq_pk;


--
-- TOC entry 179 (class 1259 OID 51795)
-- Name: ambito; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ambito (
    amb_pk integer NOT NULL,
    amb_nombre character varying(100) NOT NULL,
    amb_org_fk integer NOT NULL
);


ALTER TABLE ambito OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 51793)
-- Name: ambito_amb_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ambito_amb_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ambito_amb_pk_seq OWNER TO postgres;

--
-- TOC entry 3644 (class 0 OID 0)
-- Dependencies: 178
-- Name: ambito_amb_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ambito_amb_pk_seq OWNED BY ambito.amb_pk;


--
-- TOC entry 181 (class 1259 OID 51803)
-- Name: area_conocimiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE area_conocimiento (
    con_pk integer NOT NULL,
    con_nombre character varying(150) NOT NULL,
    con_org_fk integer NOT NULL,
    con_padre_fk integer
);


ALTER TABLE area_conocimiento OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 51801)
-- Name: area_conocimiento_con_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE area_conocimiento_con_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE area_conocimiento_con_pk_seq OWNER TO postgres;

--
-- TOC entry 3645 (class 0 OID 0)
-- Dependencies: 180
-- Name: area_conocimiento_con_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE area_conocimiento_con_pk_seq OWNED BY area_conocimiento.con_pk;


--
-- TOC entry 183 (class 1259 OID 51811)
-- Name: area_organi_int_prove; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE area_organi_int_prove (
    areaorgintprov_pk integer NOT NULL,
    areaorgintprov_orga_fk integer NOT NULL,
    areaorgintprov_org_fk integer NOT NULL,
    areaorgintprov_nombre character varying(40)
);


ALTER TABLE area_organi_int_prove OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 51809)
-- Name: area_organi_int_prove_areaorgintprov_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE area_organi_int_prove_areaorgintprov_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE area_organi_int_prove_areaorgintprov_pk_seq OWNER TO postgres;

--
-- TOC entry 3646 (class 0 OID 0)
-- Dependencies: 182
-- Name: area_organi_int_prove_areaorgintprov_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE area_organi_int_prove_areaorgintprov_pk_seq OWNED BY area_organi_int_prove.areaorgintprov_pk;


--
-- TOC entry 185 (class 1259 OID 51819)
-- Name: areas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE areas (
    area_pk integer NOT NULL,
    area_org_fk integer NOT NULL,
    area_padre integer,
    area_nombre character varying(250),
    area_abreviacion character varying(45),
    area_director integer,
    area_activo boolean DEFAULT true,
    area_habilitada boolean DEFAULT true
);


ALTER TABLE areas OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 51817)
-- Name: areas_area_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE areas_area_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE areas_area_pk_seq OWNER TO postgres;

--
-- TOC entry 3647 (class 0 OID 0)
-- Dependencies: 184
-- Name: areas_area_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE areas_area_pk_seq OWNED BY areas.area_pk;


--
-- TOC entry 187 (class 1259 OID 51829)
-- Name: areas_tags; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE areas_tags (
    arastag_pk integer NOT NULL,
    areatag_org_fk integer NOT NULL,
    areatag_padre_fk integer,
    areatag_tematica character varying(45),
    areatag_excluyente boolean,
    areatag_nombre character varying(45),
    areatag_categoria character varying(45)
);


ALTER TABLE areas_tags OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 51827)
-- Name: areas_tags_arastag_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE areas_tags_arastag_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE areas_tags_arastag_pk_seq OWNER TO postgres;

--
-- TOC entry 3648 (class 0 OID 0)
-- Dependencies: 186
-- Name: areas_tags_arastag_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE areas_tags_arastag_pk_seq OWNED BY areas_tags.arastag_pk;


--
-- TOC entry 394 (class 1259 OID 56631)
-- Name: aud_doc_file; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_doc_file (
    docfile_pk integer NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    docfile_nombre character varying(255),
    docfile_path character varying(255),
    docfile_ult_mod timestamp(6) without time zone,
    docfile_ult_origen character varying(255),
    docfile_ult_usuario character varying(255),
    docfile_version integer,
    docfile_doc_fk integer
);


ALTER TABLE aud_doc_file OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 51835)
-- Name: aud_pge_configuraciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_pge_configuraciones (
    cnf_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    cnf_clave character varying(255),
    cnf_crea_fecha timestamp without time zone,
    cnf_crea_origen integer,
    cnf_crea_usu character varying(255),
    cnf_ultmod_fecha timestamp without time zone,
    cnf_ultmod_origen integer,
    cnf_ultmod_usu character varying(255),
    cnf_valor character varying(255),
    cnf_version integer
);


ALTER TABLE aud_pge_configuraciones OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 51841)
-- Name: aud_programas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_programas (
    prog_pk integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    prog_org_fk integer,
    prog_area_fk integer,
    prog_sol_aceptacion boolean,
    prog_nombre character varying(45),
    prog_objetivo character varying(256),
    prog_obj_publico character varying(256),
    prog_grp integer,
    prog_semaforo_verde integer,
    prog_semaforo_amarillo integer,
    prog_version integer,
    prog_ult_usuario character varying(45),
    prog_ult_mod character varying(45),
    prog_ult_origen date
);


ALTER TABLE aud_programas OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 51847)
-- Name: aud_programas_proyectos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_programas_proyectos (
    id character varying(13) NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    activo boolean,
    "areaNombre" character varying(255),
    "areaPk" integer,
    estado integer,
    "estadoNombre" character varying(255),
    "estadoPendiente" integer,
    "fechaCrea" date,
    "fichaFk" integer,
    gerente integer,
    "gerentePrimerApellido" character varying(255),
    "gerentePrimerNombre" character varying(255),
    nombre character varying(45),
    organismo integer,
    "pmoFederada" integer,
    "tipoFicha" bigint
);


ALTER TABLE aud_programas_proyectos OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 51853)
-- Name: aud_ss_ayuda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_ayuda (
    ayu_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    ayu_codigo character varying(45),
    ayu_texto text,
    ayu_ult_mod_fecha timestamp without time zone,
    ayu_ult_mod_origen character varying(45),
    ayu_ult_mod_usuario character varying(45),
    ayu_version integer
);


ALTER TABLE aud_ss_ayuda OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 51859)
-- Name: aud_ss_categoper; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_categoper (
    cat_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    cat_descripcion text,
    cat_nombre character varying(255),
    cat_origen character varying(255),
    cat_user_code integer,
    cat_version integer,
    cat_vigente boolean
);


ALTER TABLE aud_ss_categoper OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 51865)
-- Name: aud_ss_configuraciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_configuraciones (
    id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    cnf_codigo character varying(255),
    cnf_descripcion character varying(255),
    cnf_html boolean,
    cnf_protegido boolean,
    cnf_ult_mod timestamp without time zone,
    cnf_ult_origen character varying(255),
    cnf_ult_usuario character varying(255),
    cnf_valor text,
    cnf_version integer
);


ALTER TABLE aud_ss_configuraciones OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 51871)
-- Name: aud_ss_departamentos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_departamentos (
    depto_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    depto_codigo character varying(255),
    depto_nombre character varying(255),
    depto_ult_mod timestamp without time zone,
    err_ult_origen character varying(255),
    depto_ult_usuario character varying(255),
    depto_version integer
);


ALTER TABLE aud_ss_departamentos OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 51877)
-- Name: aud_ss_domicilios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_domicilios (
    dom_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    dom_apto character varying(50),
    dom_letra character varying(255),
    dom_calle character varying(150),
    dom_codigo_postal character varying(5),
    dom_depto_alt character varying(255),
    dom_descripcion text,
    dom_inmueble_nombre character varying(100),
    dom_kilometro character varying(9),
    dom_manzana character varying(5),
    dom_numero_puerta character varying(5),
    dom_oficina character varying(255),
    dom_ruta character varying(5),
    dom_solar character varying(5),
    dom_ult_mod timestamp without time zone,
    dom_ult_origen character varying(255),
    dom_ult_usuario character varying(255),
    dom_version integer,
    dom_depto_id integer,
    dom_loc_id integer,
    dom_pai_id integer,
    dom_par_id boolean,
    dom_tec_id integer,
    dom_tvi_id integer
);


ALTER TABLE aud_ss_domicilios OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 51883)
-- Name: aud_ss_errores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_errores (
    id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    err_codigo character varying(255),
    err_descripcion character varying(255),
    err_ult_mod timestamp without time zone,
    err_ult_origen character varying(255),
    err_ult_usuario character varying(255),
    err_version integer
);


ALTER TABLE aud_ss_errores OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 51889)
-- Name: aud_ss_localidades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_localidades (
    loc_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    loc_codigo character varying(255),
    loc_nombre character varying(255),
    loc_ult_mod timestamp without time zone,
    loc_ult_origen character varying(255),
    loc_ult_usuario character varying(255),
    loc_version integer,
    loc_depto_id integer
);


ALTER TABLE aud_ss_localidades OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 51895)
-- Name: aud_ss_noticias; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_noticias (
    not_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    not_ampliar character varying(255),
    not_codigo character varying(45),
    not_contenido text,
    not_desde timestamp without time zone,
    not_hasta timestamp without time zone,
    not_imagen character varying(255),
    not_titulo character varying(255),
    not_ult_mod_fecha timestamp without time zone,
    not_ult_mod_origen character varying(45),
    not_ult_mod_usuario character varying(45),
    not_version integer
);


ALTER TABLE aud_ss_noticias OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 51901)
-- Name: aud_ss_oficina; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_oficina (
    ofi_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    ofi_fecha_creacion timestamp without time zone,
    ofi_nombre character varying(255),
    ofi_origen character varying(255),
    ofi_usuario integer,
    ofi_version integer,
    ofi_activo boolean
);


ALTER TABLE aud_ss_oficina OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 51907)
-- Name: aud_ss_operacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_operacion (
    ope_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    ope_codigo character varying(255),
    ope_descripcion text,
    ope_nombre character varying(255),
    ope_origen character varying(255),
    ope_tipocampo character varying(255),
    ope_user_code integer,
    ope_version integer,
    ope_vigente boolean,
    ope_categoria_id integer
);


ALTER TABLE aud_ss_operacion OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 51913)
-- Name: aud_ss_paises; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_paises (
    pai_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    pai_codigo2 character varying(255),
    pai_codigo3 character varying(255),
    pai_comun boolean,
    pai_habilitado boolean,
    pai_nombre character varying(255),
    pai_ult_mod timestamp without time zone,
    pai_ult_origen character varying(255),
    pai_ult_usuario character varying(255),
    pai_version integer
);


ALTER TABLE aud_ss_paises OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 51919)
-- Name: aud_ss_paridades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_paridades (
    par_id boolean NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    par_codigo character varying(9),
    par_descripcion character varying(45),
    par_ult_mod_fecha timestamp without time zone,
    par_ult_mod_origen character varying(45),
    par_ult_mod_usuario character varying(45),
    par_version integer
);


ALTER TABLE aud_ss_paridades OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 51922)
-- Name: aud_ss_personas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_personas (
    per_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    per_fec_nac timestamp without time zone,
    per_nro_doc character varying(45),
    per_primer_apellido character varying(100),
    per_primer_nombre character varying(100),
    per_segundo_apellido character varying(100),
    per_segundo_nombre character varying(100),
    per_ult_mod_fecha timestamp without time zone,
    per_ult_mod_origen character varying(45),
    per_ult_mod_usuario character varying(45),
    per_version integer,
    per_pais_doc integer,
    per_tipo_doc integer
);


ALTER TABLE aud_ss_personas OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 51928)
-- Name: aud_ss_rel_rol_operacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_rel_rol_operacion (
    rel_rol_operacion_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    rel_rol_operacion_editable boolean,
    rel_rol_operacion_origen character varying(255),
    rel_rol_operacion_user_code integer,
    rel_rol_operacion_visible boolean,
    rel_rol_operacion_operacion_id integer,
    rel_rol_operacion_rol_id integer
);


ALTER TABLE aud_ss_rel_rol_operacion OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 51931)
-- Name: aud_ss_rol; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_rol (
    rol_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    rol_cod character varying(255),
    rol_descripcion text,
    rol_nombre character varying(255),
    rol_label character varying(150) NOT NULL,
    rol_origen character varying(255),
    rol_user_code integer,
    rol_version integer,
    rol_vigente boolean,
    rol_tipo_usuario boolean
);


ALTER TABLE aud_ss_rol OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 51937)
-- Name: aud_ss_telefonos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_telefonos (
    tel_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    tel_numero character varying(25),
    tel_observaciones character varying(255),
    tel_prefijo character varying(10),
    tel_ult_mod timestamp without time zone,
    tel_ult_origen character varying(45),
    tel_ult_usuario character varying(45),
    tel_version integer,
    tel_tiptel_id integer
);


ALTER TABLE aud_ss_telefonos OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 51940)
-- Name: aud_ss_tipos_documento_persona; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_tipos_documento_persona (
    tipdocper_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    tipdocper_codigo character varying(255),
    tipdocper_descripcion character varying(255),
    tipdocper_habilitado boolean,
    tipdocper_ult_mod timestamp without time zone,
    tipdocper_ult_origen character varying(255),
    tipdocper_ult_usuario character varying(255),
    tipdocper_version integer
);


ALTER TABLE aud_ss_tipos_documento_persona OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 51946)
-- Name: aud_ss_tipos_entrada_colectiva; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_tipos_entrada_colectiva (
    tec_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    tec_codigo character varying(255),
    tec_descripcion character varying(255),
    tec_ult_mod timestamp without time zone,
    tec_ult_origen character varying(255),
    tec_ult_usuario character varying(255),
    tec_version integer
);


ALTER TABLE aud_ss_tipos_entrada_colectiva OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 51952)
-- Name: aud_ss_tipos_telefono; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_tipos_telefono (
    "tipTel_id" integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    "tipTel_codigo" character varying(255),
    "tipTel_descripcion" character varying(255),
    "tipTel_habilitado" boolean,
    "tipTel_ult_mod" timestamp without time zone,
    "tipTel_ult_origen" character varying(255),
    "tipTel_ult_usuario" character varying(255),
    "tipTel_version" integer
);


ALTER TABLE aud_ss_tipos_telefono OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 51958)
-- Name: aud_ss_tipos_vialidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_tipos_vialidad (
    tvi_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    tvi_abreviacion character varying(255),
    tvi_codigo character varying(255),
    tvi_descripcion character varying(255),
    tvi_ult_mod timestamp without time zone,
    tvi_ult_origen character varying(255),
    tvi_ult_usuario character varying(255),
    tvi_version integer
);


ALTER TABLE aud_ss_tipos_vialidad OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 51964)
-- Name: aud_ss_usu_ofi_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_usu_ofi_roles (
    usu_ofi_roles_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    usu_ofi_roles_origen character varying(255),
    usu_ofi_roles_user_code integer,
    usu_ofi_roles_oficina integer,
    usu_ofi_roles_rol integer,
    usu_ofi_roles_usuario integer,
    usu_ofi_roles_activo boolean
);


ALTER TABLE aud_ss_usu_ofi_roles OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 51967)
-- Name: aud_ss_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aud_ss_usuario (
    usu_id integer NOT NULL,
    rev integer NOT NULL,
    revtype integer,
    usu_administrador boolean,
    usu_cambio_estado_desc text,
    usu_cod character varying(255),
    usu_correo_electronico character varying(255),
    usu_cuenta_bloqueada boolean,
    usu_descripcion text,
    usu_direccion text,
    usu_fecha_password timestamp without time zone,
    usu_fecha_uuid timestamp without time zone,
    usu_foto bytea,
    usu_intentos_fallidos integer,
    usu_nro_doc character varying(255),
    usu_oficina_por_defecto integer,
    usu_origen text,
    usu_password character varying(255),
    usu_primer_apellido character varying(255),
    usu_primer_nombre character varying(255),
    usu_registrado boolean,
    usu_segundo_apellido character varying(255),
    usu_segundo_nombre character varying(255),
    usu_telefono character varying(255),
    usu_user_code integer,
    usu_uuid_des character varying(255),
    usu_version integer,
    usu_vigente boolean,
    usu_aprob_facturas boolean,
    usu_ult_usuario character varying(255),
    usu_ult_mod timestamp without time zone,
    usu_ult_origen character varying(255),
    usu_area_org integer,
    usu_celular character varying(255),
    usu_token character varying(100) DEFAULT 'NULL::character varying'::character varying,
    usu_token_act timestamp without time zone,
    usu_ldap_user character varying(50)
);


ALTER TABLE aud_ss_usuario OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 51976)
-- Name: busq_filtro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE busq_filtro (
    busq_filtro_pk integer NOT NULL,
    busq_filtro_usu_fk integer NOT NULL,
    busq_filtro_org_fk integer NOT NULL,
    busq_filtro_xml text NOT NULL
);


ALTER TABLE busq_filtro OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 51974)
-- Name: busq_filtro_busq_filtro_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE busq_filtro_busq_filtro_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE busq_filtro_busq_filtro_pk_seq OWNER TO postgres;

--
-- TOC entry 3649 (class 0 OID 0)
-- Dependencies: 213
-- Name: busq_filtro_busq_filtro_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE busq_filtro_busq_filtro_pk_seq OWNED BY busq_filtro.busq_filtro_pk;


--
-- TOC entry 216 (class 1259 OID 51987)
-- Name: calidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE calidad (
    cal_pk integer NOT NULL,
    cal_peso integer NOT NULL,
    cal_vca_fk integer NOT NULL,
    cal_actualizacion date NOT NULL,
    cal_tipo integer NOT NULL,
    cal_ent_fk integer,
    cal_prod_fk integer,
    cal_tca_fk integer,
    cal_proy_fk integer NOT NULL
);


ALTER TABLE calidad OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 51985)
-- Name: calidad_cal_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE calidad_cal_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE calidad_cal_pk_seq OWNER TO postgres;

--
-- TOC entry 3650 (class 0 OID 0)
-- Dependencies: 215
-- Name: calidad_cal_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE calidad_cal_pk_seq OWNED BY calidad.cal_pk;


--
-- TOC entry 218 (class 1259 OID 51995)
-- Name: categoria_proyectos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE categoria_proyectos (
    cat_proy_pk integer NOT NULL,
    cat_proy_codigo character varying(45) NOT NULL,
    cat_proy_nombre character varying(145) NOT NULL,
    cat_proy_activo boolean DEFAULT true NOT NULL,
    cat_tipo integer DEFAULT 0 NOT NULL,
    cat_icono integer,
    cat_icono_marker integer,
    cat_org_fk integer
);


ALTER TABLE categoria_proyectos OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 51993)
-- Name: categoria_proyectos_cat_proy_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE categoria_proyectos_cat_proy_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categoria_proyectos_cat_proy_pk_seq OWNER TO postgres;

--
-- TOC entry 3651 (class 0 OID 0)
-- Dependencies: 217
-- Name: categoria_proyectos_cat_proy_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE categoria_proyectos_cat_proy_pk_seq OWNED BY categoria_proyectos.cat_proy_pk;


--
-- TOC entry 220 (class 1259 OID 52005)
-- Name: cronogramas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cronogramas (
    cro_pk integer NOT NULL,
    cro_ent_seleccionado integer,
    cro_ent_borrados character varying(45),
    cro_resources character varying(45),
    cro_permiso_escritura boolean,
    cro_permiso_escritura_padre boolean
);


ALTER TABLE cronogramas OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 52003)
-- Name: cronogramas_cro_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cronogramas_cro_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cronogramas_cro_pk_seq OWNER TO postgres;

--
-- TOC entry 3652 (class 0 OID 0)
-- Dependencies: 219
-- Name: cronogramas_cro_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cronogramas_cro_pk_seq OWNED BY cronogramas.cro_pk;


--
-- TOC entry 172 (class 1259 OID 51773)
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE databasechangelog OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 51768)
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE databasechangeloglock OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 52011)
-- Name: departamentos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE departamentos (
    dep_pk integer NOT NULL,
    dep_nombre character varying(145),
    dep_lat double precision,
    dep_lng double precision,
    dep_zoom integer
);


ALTER TABLE departamentos OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 52016)
-- Name: devengado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE devengado (
    dev_pk integer NOT NULL,
    dev_adq_fk integer NOT NULL,
    dev_mes smallint NOT NULL,
    dev_anio smallint NOT NULL,
    dev_plan numeric(11,2),
    dev_real numeric(11,2)
);


ALTER TABLE devengado OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 52014)
-- Name: devengado_dev_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE devengado_dev_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE devengado_dev_pk_seq OWNER TO postgres;

--
-- TOC entry 3653 (class 0 OID 0)
-- Dependencies: 222
-- Name: devengado_dev_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE devengado_dev_pk_seq OWNED BY devengado.dev_pk;


--
-- TOC entry 225 (class 1259 OID 52024)
-- Name: doc_file; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE doc_file (
    docfile_pk integer NOT NULL,
    docfile_nombre character varying(256) NOT NULL,
    docfile_doc_fk integer NOT NULL,
    docfile_path character varying(255),
    docfile_ult_mod timestamp without time zone,
    docfile_ult_origen character varying(255),
    docfile_ult_usuario character varying(255),
    docfile_version integer DEFAULT 0
);


ALTER TABLE doc_file OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 52022)
-- Name: doc_file_docfile_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE doc_file_docfile_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE doc_file_docfile_pk_seq OWNER TO postgres;

--
-- TOC entry 3654 (class 0 OID 0)
-- Dependencies: 224
-- Name: doc_file_docfile_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE doc_file_docfile_pk_seq OWNED BY doc_file.docfile_pk;


--
-- TOC entry 227 (class 1259 OID 52035)
-- Name: documentos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE documentos (
    docs_pk integer NOT NULL,
    docs_nombre character varying(100),
    docs_fecha date NOT NULL,
    docs_privado boolean,
    docs_estado double precision,
    docs_entregable_fk integer,
    docs_tipodoc_fk integer NOT NULL,
    docs_docfile_pk integer,
    docs_aprobado boolean,
    docs_pago_fk integer
);


ALTER TABLE documentos OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 52033)
-- Name: documentos_docs_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE documentos_docs_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE documentos_docs_pk_seq OWNER TO postgres;

--
-- TOC entry 3655 (class 0 OID 0)
-- Dependencies: 226
-- Name: documentos_docs_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE documentos_docs_pk_seq OWNED BY documentos.docs_pk;


--
-- TOC entry 229 (class 1259 OID 52043)
-- Name: ent_hist_linea_base; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ent_hist_linea_base (
    enthist_pk integer NOT NULL,
    enthist_ent_fk integer NOT NULL,
    enthist_fecha date NOT NULL,
    enthist_inicio_linea_base bigint NOT NULL,
    enthist_fin_linea_base bigint,
    enthist_duracion integer,
    enthist_replan_fk integer
);


ALTER TABLE ent_hist_linea_base OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 52041)
-- Name: ent_hist_linea_base_enthist_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ent_hist_linea_base_enthist_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ent_hist_linea_base_enthist_pk_seq OWNER TO postgres;

--
-- TOC entry 3656 (class 0 OID 0)
-- Dependencies: 228
-- Name: ent_hist_linea_base_enthist_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ent_hist_linea_base_enthist_pk_seq OWNED BY ent_hist_linea_base.enthist_pk;


--
-- TOC entry 231 (class 1259 OID 52051)
-- Name: entregables; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE entregables (
    ent_pk integer NOT NULL,
    ent_cro_fk integer NOT NULL,
    ent_id integer NOT NULL,
    ent_nombre character varying(1000),
    ent_codigo character varying(256),
    ent_nivel integer,
    ent_status character varying(256),
    ent_inicio bigint,
    ent_duracion integer,
    ent_fin bigint,
    ent_horas_estimadas character varying(15),
    ent_inicio_es_hito boolean,
    ent_fin_es_hito boolean,
    ent_collapsed boolean,
    ent_assigs character varying(256),
    ent_coord_usu_fk integer,
    ent_esfuerzo integer,
    ent_inicio_linea_base bigint,
    ent_duracion_linea_base integer,
    ent_fin_linea_base bigint DEFAULT 0,
    ent_predecesor_fk character varying(2000),
    ent_predecesor_dias integer,
    ent_descripcion character varying(1000),
    ent_progreso integer,
    ent_relevante boolean,
    ent_parent boolean,
    ent_inicio_periodo boolean DEFAULT false NOT NULL,
    ent_fin_periodo boolean DEFAULT false NOT NULL
);


ALTER TABLE entregables OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 52049)
-- Name: entregables_ent_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE entregables_ent_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE entregables_ent_pk_seq OWNER TO postgres;

--
-- TOC entry 3657 (class 0 OID 0)
-- Dependencies: 230
-- Name: entregables_ent_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE entregables_ent_pk_seq OWNED BY entregables.ent_pk;


--
-- TOC entry 233 (class 1259 OID 52063)
-- Name: estados; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE estados (
    est_pk integer NOT NULL,
    est_codigo character varying(150),
    est_nombre character varying(45),
    est_label character varying(150),
    est_orden_proceso integer
);


ALTER TABLE estados OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 52061)
-- Name: estados_est_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE estados_est_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE estados_est_pk_seq OWNER TO postgres;

--
-- TOC entry 3658 (class 0 OID 0)
-- Dependencies: 232
-- Name: estados_est_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE estados_est_pk_seq OWNED BY estados.est_pk;


--
-- TOC entry 235 (class 1259 OID 52071)
-- Name: estados_publicacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE estados_publicacion (
    est_pub_pk integer NOT NULL,
    est_pub_codigo character varying(45),
    est_pub_nombre character varying(145)
);


ALTER TABLE estados_publicacion OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 52069)
-- Name: estados_publicacion_est_pub_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE estados_publicacion_est_pub_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE estados_publicacion_est_pub_pk_seq OWNER TO postgres;

--
-- TOC entry 3659 (class 0 OID 0)
-- Dependencies: 234
-- Name: estados_publicacion_est_pub_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE estados_publicacion_est_pub_pk_seq OWNED BY estados_publicacion.est_pub_pk;


--
-- TOC entry 237 (class 1259 OID 52079)
-- Name: etapa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE etapa (
    eta_pk integer NOT NULL,
    eta_org_fk integer NOT NULL,
    eta_codigo character varying(45) NOT NULL,
    eta_nombre character varying(100) NOT NULL,
    eta_label character varying(100)
);


ALTER TABLE etapa OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 52077)
-- Name: etapa_eta_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE etapa_eta_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE etapa_eta_pk_seq OWNER TO postgres;

--
-- TOC entry 3660 (class 0 OID 0)
-- Dependencies: 236
-- Name: etapa_eta_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE etapa_eta_pk_seq OWNED BY etapa.eta_pk;


--
-- TOC entry 239 (class 1259 OID 52087)
-- Name: fuente_financiamiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE fuente_financiamiento (
    fue_pk integer NOT NULL,
    fue_nombre character varying(300) NOT NULL,
    fue_org_fk integer NOT NULL
);


ALTER TABLE fuente_financiamiento OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 52085)
-- Name: fuente_financiamiento_fue_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE fuente_financiamiento_fue_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fuente_financiamiento_fue_pk_seq OWNER TO postgres;

--
-- TOC entry 3661 (class 0 OID 0)
-- Dependencies: 238
-- Name: fuente_financiamiento_fue_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE fuente_financiamiento_fue_pk_seq OWNED BY fuente_financiamiento.fue_pk;


--
-- TOC entry 241 (class 1259 OID 52095)
-- Name: gastos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE gastos (
    gas_pk integer NOT NULL,
    gas_proy_fk integer NOT NULL,
    gas_tipo_fk integer NOT NULL,
    gas_usu_fk integer NOT NULL,
    gas_mon_fk integer NOT NULL,
    gas_importe numeric(11,2) NOT NULL,
    gas_fecha date NOT NULL,
    gas_obs character varying(4000) NOT NULL,
    gas_aprobado boolean
);


ALTER TABLE gastos OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 52093)
-- Name: gastos_gas_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE gastos_gas_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE gastos_gas_pk_seq OWNER TO postgres;

--
-- TOC entry 3662 (class 0 OID 0)
-- Dependencies: 240
-- Name: gastos_gas_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE gastos_gas_pk_seq OWNED BY gastos.gas_pk;


--
-- TOC entry 173 (class 1259 OID 51779)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 52106)
-- Name: image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE image (
    image_pk integer NOT NULL,
    image_name character varying(45) NOT NULL,
    image_desc character varying(255) DEFAULT 'NULL::character varying'::character varying,
    image_ext character varying(20) NOT NULL,
    image_blob bytea NOT NULL,
    image_tipo integer,
    image_orden integer
);


ALTER TABLE image OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 52104)
-- Name: image_image_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE image_image_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE image_image_pk_seq OWNER TO postgres;

--
-- TOC entry 3663 (class 0 OID 0)
-- Dependencies: 242
-- Name: image_image_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE image_image_pk_seq OWNED BY image.image_pk;


--
-- TOC entry 245 (class 1259 OID 52118)
-- Name: interesados; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE interesados (
    int_pk integer NOT NULL,
    int_rolint_fk integer NOT NULL,
    int_observaciones character varying(4000),
    int_pers_fk integer,
    int_orga_fk integer,
    int_ent_fk integer
);


ALTER TABLE interesados OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 52116)
-- Name: interesados_int_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE interesados_int_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE interesados_int_pk_seq OWNER TO postgres;

--
-- TOC entry 3664 (class 0 OID 0)
-- Dependencies: 244
-- Name: interesados_int_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE interesados_int_pk_seq OWNED BY interesados.int_pk;


--
-- TOC entry 247 (class 1259 OID 52129)
-- Name: latlng_proyectos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE latlng_proyectos (
    latlng_pk integer NOT NULL,
    latlng_lat numeric(19,15),
    latlng_lng numeric(19,15),
    latlng_proy_fk integer,
    latlang_dep_fk integer,
    latlang_loc_fk integer,
    latlang_codigopostal integer,
    latlang_barrio character varying(245),
    latlang_loc character varying(245)
);


ALTER TABLE latlng_proyectos OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 52127)
-- Name: latlng_proyectos_latlng_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE latlng_proyectos_latlng_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE latlng_proyectos_latlng_pk_seq OWNER TO postgres;

--
-- TOC entry 3665 (class 0 OID 0)
-- Dependencies: 246
-- Name: latlng_proyectos_latlng_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE latlng_proyectos_latlng_pk_seq OWNED BY latlng_proyectos.latlng_pk;


--
-- TOC entry 248 (class 1259 OID 52138)
-- Name: lecapr_areacon; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE lecapr_areacon (
    lecaprcon_con_fk integer NOT NULL,
    lecaprcon_lecapr_fk integer NOT NULL
);


ALTER TABLE lecapr_areacon OWNER TO postgres;

--
-- TOC entry 250 (class 1259 OID 52143)
-- Name: lecc_aprendidas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE lecc_aprendidas (
    lecapr_pk integer NOT NULL,
    lecapr_proy_fk integer,
    lecapr_tipo_fk integer NOT NULL,
    lecapr_usr_fk integer NOT NULL,
    lecapr_org_fk integer NOT NULL,
    lecapr_fecha date NOT NULL,
    lecapr_desc character varying(3000) NOT NULL,
    lecapr_activo boolean
);


ALTER TABLE lecc_aprendidas OWNER TO postgres;

--
-- TOC entry 249 (class 1259 OID 52141)
-- Name: lecc_aprendidas_lecapr_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE lecc_aprendidas_lecapr_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE lecc_aprendidas_lecapr_pk_seq OWNER TO postgres;

--
-- TOC entry 3666 (class 0 OID 0)
-- Dependencies: 249
-- Name: lecc_aprendidas_lecapr_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE lecc_aprendidas_lecapr_pk_seq OWNED BY lecc_aprendidas.lecapr_pk;


--
-- TOC entry 252 (class 1259 OID 52154)
-- Name: lineabase_historico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE lineabase_historico (
    lineabase_pk integer NOT NULL,
    "lineabase_entFk" integer NOT NULL,
    lineabase_fecha date NOT NULL,
    lineabase_inicio bigint NOT NULL,
    lineabase_duracion integer,
    lineabase_fin bigint NOT NULL
);


ALTER TABLE lineabase_historico OWNER TO postgres;

--
-- TOC entry 251 (class 1259 OID 52152)
-- Name: lineabase_historico_lineabase_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE lineabase_historico_lineabase_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE lineabase_historico_lineabase_pk_seq OWNER TO postgres;

--
-- TOC entry 3667 (class 0 OID 0)
-- Dependencies: 251
-- Name: lineabase_historico_lineabase_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE lineabase_historico_lineabase_pk_seq OWNED BY lineabase_historico.lineabase_pk;


--
-- TOC entry 254 (class 1259 OID 52162)
-- Name: mails_template; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE mails_template (
    mail_tmp_pk integer NOT NULL,
    mail_tmp_cod character varying(45) NOT NULL,
    mail_tmp_org_fk integer NOT NULL,
    mail_tmp_asunto character varying(200) NOT NULL,
    mail_tmp_mensaje character varying(5000) NOT NULL
);


ALTER TABLE mails_template OWNER TO postgres;

--
-- TOC entry 253 (class 1259 OID 52160)
-- Name: mails_template_mail_tmp_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE mails_template_mail_tmp_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mails_template_mail_tmp_pk_seq OWNER TO postgres;

--
-- TOC entry 3668 (class 0 OID 0)
-- Dependencies: 253
-- Name: mails_template_mail_tmp_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE mails_template_mail_tmp_pk_seq OWNED BY mails_template.mail_tmp_pk;


--
-- TOC entry 256 (class 1259 OID 52173)
-- Name: media_proyectos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE media_proyectos (
    media_pk integer NOT NULL,
    media_tipo_fk integer,
    media_link character varying(545),
    media_estado integer,
    media_proy_fk integer,
    media_principal boolean,
    media_orden integer,
    media_usr_pub_fk integer,
    media_pub_fecha timestamp without time zone,
    media_usr_mod_fk integer,
    media_mod_fecha timestamp without time zone,
    media_usr_rech_fk integer,
    media_rech_fecha timestamp without time zone,
    media_comentario character varying(2000),
    media_contenttype character varying(200),
    media_publicable boolean
);


ALTER TABLE media_proyectos OWNER TO postgres;

--
-- TOC entry 255 (class 1259 OID 52171)
-- Name: media_proyectos_media_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE media_proyectos_media_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE media_proyectos_media_pk_seq OWNER TO postgres;

--
-- TOC entry 3669 (class 0 OID 0)
-- Dependencies: 255
-- Name: media_proyectos_media_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE media_proyectos_media_pk_seq OWNED BY media_proyectos.media_pk;


--
-- TOC entry 258 (class 1259 OID 52184)
-- Name: moneda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE moneda (
    mon_pk integer NOT NULL,
    mon_nombre character varying(100) NOT NULL,
    mon_signo character varying(50),
    mon_cod_pais character varying(10)
);


ALTER TABLE moneda OWNER TO postgres;

--
-- TOC entry 257 (class 1259 OID 52182)
-- Name: moneda_mon_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE moneda_mon_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE moneda_mon_pk_seq OWNER TO postgres;

--
-- TOC entry 3670 (class 0 OID 0)
-- Dependencies: 257
-- Name: moneda_mon_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE moneda_mon_pk_seq OWNED BY moneda.mon_pk;


--
-- TOC entry 260 (class 1259 OID 52192)
-- Name: notificacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE notificacion (
    not_pk integer NOT NULL,
    not_org_fk integer NOT NULL,
    not_cod character varying(45) NOT NULL,
    not_desc character varying(245) NOT NULL,
    not_valor integer,
    not_gerente_adjunto boolean,
    not_pmof boolean,
    not_pmot boolean,
    not_sponsor boolean,
    not_msg character varying(5000) NOT NULL
);


ALTER TABLE notificacion OWNER TO postgres;

--
-- TOC entry 262 (class 1259 OID 52203)
-- Name: notificacion_envio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE notificacion_envio (
    notenv_pk integer NOT NULL,
    notenv_fecha date NOT NULL,
    notenv_proy_fk integer NOT NULL,
    notenv_not_cod character varying(45) NOT NULL
);


ALTER TABLE notificacion_envio OWNER TO postgres;

--
-- TOC entry 261 (class 1259 OID 52201)
-- Name: notificacion_envio_notenv_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE notificacion_envio_notenv_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE notificacion_envio_notenv_pk_seq OWNER TO postgres;

--
-- TOC entry 3671 (class 0 OID 0)
-- Dependencies: 261
-- Name: notificacion_envio_notenv_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE notificacion_envio_notenv_pk_seq OWNED BY notificacion_envio.notenv_pk;


--
-- TOC entry 264 (class 1259 OID 52211)
-- Name: notificacion_instancia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE notificacion_instancia (
    notinst_pk integer NOT NULL,
    notinst_not_fk integer NOT NULL,
    notinst_proy_fk integer NOT NULL,
    notinst_gerente_adjunto boolean,
    notinst_pmof boolean,
    notinst_pmot boolean,
    notinst_sponsor boolean
);


ALTER TABLE notificacion_instancia OWNER TO postgres;

--
-- TOC entry 263 (class 1259 OID 52209)
-- Name: notificacion_instancia_notinst_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE notificacion_instancia_notinst_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE notificacion_instancia_notinst_pk_seq OWNER TO postgres;

--
-- TOC entry 3672 (class 0 OID 0)
-- Dependencies: 263
-- Name: notificacion_instancia_notinst_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE notificacion_instancia_notinst_pk_seq OWNED BY notificacion_instancia.notinst_pk;


--
-- TOC entry 259 (class 1259 OID 52190)
-- Name: notificacion_not_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE notificacion_not_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE notificacion_not_pk_seq OWNER TO postgres;

--
-- TOC entry 3673 (class 0 OID 0)
-- Dependencies: 259
-- Name: notificacion_not_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE notificacion_not_pk_seq OWNED BY notificacion.not_pk;


--
-- TOC entry 174 (class 1259 OID 51781)
-- Name: obj_est_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE obj_est_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE obj_est_pk_seq OWNER TO postgres;

--
-- TOC entry 266 (class 1259 OID 52219)
-- Name: objetivos_estrategicos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE objetivos_estrategicos (
    obj_est_pk integer NOT NULL,
    obj_est_nombre character varying(100) NOT NULL,
    obj_est_descripcion character varying(100) NOT NULL,
    obj_est_org_fk integer NOT NULL
);


ALTER TABLE objetivos_estrategicos OWNER TO postgres;

--
-- TOC entry 265 (class 1259 OID 52217)
-- Name: objetivos_estrategicos_obj_est_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE objetivos_estrategicos_obj_est_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE objetivos_estrategicos_obj_est_pk_seq OWNER TO postgres;

--
-- TOC entry 3674 (class 0 OID 0)
-- Dependencies: 265
-- Name: objetivos_estrategicos_obj_est_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE objetivos_estrategicos_obj_est_pk_seq OWNED BY objetivos_estrategicos.obj_est_pk;


--
-- TOC entry 268 (class 1259 OID 52227)
-- Name: organi_int_prove; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE organi_int_prove (
    orga_pk integer NOT NULL,
    orga_nombre character varying(50),
    orga_proveedor boolean,
    orga_razon_social character varying(50),
    orga_rut character varying(45),
    orga_mail character varying(45),
    orga_telefono character varying(45),
    orga_web character varying(45),
    orga_direccion character varying(100),
    orga_ambito character varying(45),
    orga_org_fk integer NOT NULL,
    orga_amb_fk integer
);


ALTER TABLE organi_int_prove OWNER TO postgres;

--
-- TOC entry 267 (class 1259 OID 52225)
-- Name: organi_int_prove_orga_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE organi_int_prove_orga_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE organi_int_prove_orga_pk_seq OWNER TO postgres;

--
-- TOC entry 3675 (class 0 OID 0)
-- Dependencies: 267
-- Name: organi_int_prove_orga_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE organi_int_prove_orga_pk_seq OWNED BY organi_int_prove.orga_pk;


--
-- TOC entry 270 (class 1259 OID 52235)
-- Name: organismos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE organismos (
    org_pk integer NOT NULL,
    org_nombre character varying(45),
    org_logo_nombre character varying(45),
    org_direccion character varying(45),
    org_logo bytea,
    org_activo boolean DEFAULT true NOT NULL,
    org_token character varying(100)
);


ALTER TABLE organismos OWNER TO postgres;

--
-- TOC entry 269 (class 1259 OID 52233)
-- Name: organismos_org_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE organismos_org_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE organismos_org_pk_seq OWNER TO postgres;

--
-- TOC entry 3676 (class 0 OID 0)
-- Dependencies: 269
-- Name: organismos_org_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE organismos_org_pk_seq OWNED BY organismos.org_pk;


--
-- TOC entry 272 (class 1259 OID 52247)
-- Name: pagos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pagos (
    pag_pk integer NOT NULL,
    pag_adq_fk integer NOT NULL,
    pag_ent_fk integer,
    pag_observacion character varying(3000),
    pag_fecha_planificada date NOT NULL,
    pag_importe_planificado numeric(11,2) NOT NULL,
    pag_fecha_real date,
    pag_importe_real numeric(11,2),
    pag_txt_referencia character varying(20),
    pag_confirmar boolean
);


ALTER TABLE pagos OWNER TO postgres;

--
-- TOC entry 271 (class 1259 OID 52245)
-- Name: pagos_pag_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pagos_pag_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pagos_pag_pk_seq OWNER TO postgres;

--
-- TOC entry 3677 (class 0 OID 0)
-- Dependencies: 271
-- Name: pagos_pag_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pagos_pag_pk_seq OWNED BY pagos.pag_pk;


--
-- TOC entry 274 (class 1259 OID 52258)
-- Name: participantes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE participantes (
    part_pk integer NOT NULL,
    part_usu_fk integer NOT NULL,
    part_proy_fk integer NOT NULL,
    part_ent_fk integer,
    part_horas_plan numeric(11,2),
    part_activo boolean DEFAULT true NOT NULL
);


ALTER TABLE participantes OWNER TO postgres;

--
-- TOC entry 273 (class 1259 OID 52256)
-- Name: participantes_part_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE participantes_part_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE participantes_part_pk_seq OWNER TO postgres;

--
-- TOC entry 3678 (class 0 OID 0)
-- Dependencies: 273
-- Name: participantes_part_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE participantes_part_pk_seq OWNED BY participantes.part_pk;


--
-- TOC entry 276 (class 1259 OID 52267)
-- Name: personas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE personas (
    pers_pk integer NOT NULL,
    pers_rol_fk integer NOT NULL,
    pers_orga_fk integer NOT NULL,
    pers_nombre character varying(45) NOT NULL,
    pers_telefono character varying(45),
    pers_cargo character varying(45),
    pers_mail character varying(45)
);


ALTER TABLE personas OWNER TO postgres;

--
-- TOC entry 275 (class 1259 OID 52265)
-- Name: personas_pers_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE personas_pers_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE personas_pers_pk_seq OWNER TO postgres;

--
-- TOC entry 3679 (class 0 OID 0)
-- Dependencies: 275
-- Name: personas_pers_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE personas_pers_pk_seq OWNED BY personas.pers_pk;


--
-- TOC entry 278 (class 1259 OID 52275)
-- Name: pge_configuraciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pge_configuraciones (
    cnf_id integer NOT NULL,
    cnf_clave character varying(255),
    cnf_crea_fecha timestamp without time zone,
    cnf_crea_origen integer,
    cnf_crea_usu character varying(255),
    cnf_ultmod_fecha timestamp without time zone,
    cnf_ultmod_origen integer,
    cnf_ultmod_usu character varying(255),
    cnf_valor character varying(255),
    cnf_version integer
);


ALTER TABLE pge_configuraciones OWNER TO postgres;

--
-- TOC entry 277 (class 1259 OID 52273)
-- Name: pge_configuraciones_cnf_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pge_configuraciones_cnf_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pge_configuraciones_cnf_id_seq OWNER TO postgres;

--
-- TOC entry 3680 (class 0 OID 0)
-- Dependencies: 277
-- Name: pge_configuraciones_cnf_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pge_configuraciones_cnf_id_seq OWNED BY pge_configuraciones.cnf_id;


--
-- TOC entry 280 (class 1259 OID 52286)
-- Name: pge_invocaciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pge_invocaciones (
    inv_id integer NOT NULL,
    inv_crea_usu character varying(255),
    inv_env_fecha timestamp without time zone,
    inv_env_mensaje character varying(255),
    inv_env_ok boolean,
    inv_operacion character varying(255),
    inv_rec_fecha timestamp without time zone,
    inv_rec_mensaje character varying(255),
    inv_rec_ok boolean,
    inv_servicio character varying(255),
    inv_url character varying(255)
);


ALTER TABLE pge_invocaciones OWNER TO postgres;

--
-- TOC entry 279 (class 1259 OID 52284)
-- Name: pge_invocaciones_inv_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pge_invocaciones_inv_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pge_invocaciones_inv_id_seq OWNER TO postgres;

--
-- TOC entry 3681 (class 0 OID 0)
-- Dependencies: 279
-- Name: pge_invocaciones_inv_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pge_invocaciones_inv_id_seq OWNED BY pge_invocaciones.inv_id;


--
-- TOC entry 282 (class 1259 OID 52297)
-- Name: plantilla_cronograma; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE plantilla_cronograma (
    p_crono_pk integer NOT NULL,
    p_crono_nombre character varying(845),
    p_crono_org_fk integer,
    activo boolean
);


ALTER TABLE plantilla_cronograma OWNER TO postgres;

--
-- TOC entry 281 (class 1259 OID 52295)
-- Name: plantilla_cronograma_p_crono_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE plantilla_cronograma_p_crono_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE plantilla_cronograma_p_crono_pk_seq OWNER TO postgres;

--
-- TOC entry 3682 (class 0 OID 0)
-- Dependencies: 281
-- Name: plantilla_cronograma_p_crono_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE plantilla_cronograma_p_crono_pk_seq OWNED BY plantilla_cronograma.p_crono_pk;


--
-- TOC entry 284 (class 1259 OID 52308)
-- Name: plantilla_entregables; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE plantilla_entregables (
    p_entregables_id integer NOT NULL,
    p_entregables_nombre character varying(1000),
    p_entregable_nivel integer,
    p_entregable_esfuerzo integer,
    p_entregable_duracion integer,
    p_entregable_ant_fk integer,
    p_entregable_p_cro_fk integer,
    p_entregables_numero integer
);


ALTER TABLE plantilla_entregables OWNER TO postgres;

--
-- TOC entry 283 (class 1259 OID 52306)
-- Name: plantilla_entregables_p_entregables_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE plantilla_entregables_p_entregables_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE plantilla_entregables_p_entregables_id_seq OWNER TO postgres;

--
-- TOC entry 3683 (class 0 OID 0)
-- Dependencies: 283
-- Name: plantilla_entregables_p_entregables_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE plantilla_entregables_p_entregables_id_seq OWNED BY plantilla_entregables.p_entregables_id;


--
-- TOC entry 286 (class 1259 OID 52319)
-- Name: presupuesto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE presupuesto (
    pre_pk integer NOT NULL,
    pre_base numeric(15,2),
    pre_moneda integer,
    pre_fuente_organi_fk integer
);


ALTER TABLE presupuesto OWNER TO postgres;

--
-- TOC entry 285 (class 1259 OID 52317)
-- Name: presupuesto_pre_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE presupuesto_pre_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE presupuesto_pre_pk_seq OWNER TO postgres;

--
-- TOC entry 3684 (class 0 OID 0)
-- Dependencies: 285
-- Name: presupuesto_pre_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE presupuesto_pre_pk_seq OWNED BY presupuesto.pre_pk;


--
-- TOC entry 288 (class 1259 OID 52327)
-- Name: prod_mes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prod_mes (
    prodmes_pk integer NOT NULL,
    prodmes_prod_fk integer NOT NULL,
    prodmes_mes smallint NOT NULL,
    prodmes_anio smallint NOT NULL,
    prodmes_plan numeric(11,2) NOT NULL,
    prodmes_real numeric(11,2),
    prodmes_acu_plan numeric(11,2) NOT NULL,
    prodmes_acu_real numeric(11,2)
);


ALTER TABLE prod_mes OWNER TO postgres;

--
-- TOC entry 287 (class 1259 OID 52325)
-- Name: prod_mes_prodmes_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE prod_mes_prodmes_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE prod_mes_prodmes_pk_seq OWNER TO postgres;

--
-- TOC entry 3685 (class 0 OID 0)
-- Dependencies: 287
-- Name: prod_mes_prodmes_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE prod_mes_prodmes_pk_seq OWNED BY prod_mes.prodmes_pk;


--
-- TOC entry 290 (class 1259 OID 52335)
-- Name: productos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE productos (
    prod_pk integer NOT NULL,
    prod_peso integer NOT NULL,
    prod_und_medida character varying(45) NOT NULL,
    prod_ent_fk integer NOT NULL,
    prod_fecha_crea date NOT NULL,
    prod_ult_mod date,
    prod_acumulado boolean,
    prod_desc character varying(2000)
);


ALTER TABLE productos OWNER TO postgres;

--
-- TOC entry 289 (class 1259 OID 52333)
-- Name: productos_prod_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE productos_prod_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE productos_prod_pk_seq OWNER TO postgres;

--
-- TOC entry 3686 (class 0 OID 0)
-- Dependencies: 289
-- Name: productos_prod_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE productos_prod_pk_seq OWNED BY productos.prod_pk;


--
-- TOC entry 291 (class 1259 OID 52344)
-- Name: prog_docs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prog_docs (
    progdocs_prog_pk integer NOT NULL,
    progdocs_doc_pk integer NOT NULL
);


ALTER TABLE prog_docs OWNER TO postgres;

--
-- TOC entry 292 (class 1259 OID 52347)
-- Name: prog_docs_obl; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prog_docs_obl (
    progdocsobl_docs_pk integer NOT NULL,
    progdocsobl_prog_pk integer NOT NULL
);


ALTER TABLE prog_docs_obl OWNER TO postgres;

--
-- TOC entry 294 (class 1259 OID 52352)
-- Name: prog_indices; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prog_indices (
    progind_pk integer NOT NULL,
    progind_riesgo_expo double precision,
    progind_riesgo_ultact date,
    progind_riesgo_alto integer,
    progind_metodo_estado double precision,
    progind_metodo_sin_aprobar boolean,
    progind_periodo_inicio date,
    progind_periodo_fin date,
    progind_proy_actualizacion date,
    progind_cal_ind double precision,
    progind_cal_pend integer,
    progind_avance_par_azul integer,
    progind_avance_par_verde integer,
    progind_anvance_par_rojo integer,
    progind_avance_fin_azul integer,
    progind_avance_fin_verde integer,
    progind_anvance_fin_rojo integer,
    progind_fecha_act timestamp without time zone
);


ALTER TABLE prog_indices OWNER TO postgres;

--
-- TOC entry 296 (class 1259 OID 52360)
-- Name: prog_indices_pre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prog_indices_pre (
    progindpre_pk integer NOT NULL,
    progindpre_progind_fk integer NOT NULL,
    progindpre_mon_fk integer NOT NULL,
    progindpre_total double precision,
    progindpre_est_pre integer,
    progindpre_anio double precision,
    progindpre_ac double precision,
    progindpre_pv double precision
);


ALTER TABLE prog_indices_pre OWNER TO postgres;

--
-- TOC entry 295 (class 1259 OID 52358)
-- Name: prog_indices_pre_progindpre_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE prog_indices_pre_progindpre_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE prog_indices_pre_progindpre_pk_seq OWNER TO postgres;

--
-- TOC entry 3687 (class 0 OID 0)
-- Dependencies: 295
-- Name: prog_indices_pre_progindpre_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE prog_indices_pre_progindpre_pk_seq OWNED BY prog_indices_pre.progindpre_pk;


--
-- TOC entry 293 (class 1259 OID 52350)
-- Name: prog_indices_progind_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE prog_indices_progind_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE prog_indices_progind_pk_seq OWNER TO postgres;

--
-- TOC entry 3688 (class 0 OID 0)
-- Dependencies: 293
-- Name: prog_indices_progind_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE prog_indices_progind_pk_seq OWNED BY prog_indices.progind_pk;


--
-- TOC entry 297 (class 1259 OID 52366)
-- Name: prog_int; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prog_int (
    progint_prog_pk integer NOT NULL,
    progint_int_pk integer NOT NULL
);


ALTER TABLE prog_int OWNER TO postgres;

--
-- TOC entry 298 (class 1259 OID 52369)
-- Name: prog_lectura_area; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prog_lectura_area (
    proglectarea_prog_pk integer NOT NULL,
    proglectarea_area_pk integer NOT NULL
);


ALTER TABLE prog_lectura_area OWNER TO postgres;

--
-- TOC entry 299 (class 1259 OID 52372)
-- Name: prog_pre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prog_pre (
    progpre_prog_fk integer NOT NULL,
    progpre_pre_fk integer NOT NULL
);


ALTER TABLE prog_pre OWNER TO postgres;

--
-- TOC entry 300 (class 1259 OID 52375)
-- Name: prog_tags; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prog_tags (
    progtag_prog_pk integer NOT NULL,
    progtag_area_pk integer NOT NULL
);


ALTER TABLE prog_tags OWNER TO postgres;

--
-- TOC entry 302 (class 1259 OID 52380)
-- Name: programas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE programas (
    prog_pk integer NOT NULL,
    prog_org_fk integer NOT NULL,
    prog_area_fk integer NOT NULL,
    prog_est_fk integer NOT NULL,
    prog_est_pendiente_fk integer,
    prog_sol_aceptacion boolean,
    prog_usr_gerente_fk integer NOT NULL,
    prog_usr_adjunto_fk integer NOT NULL,
    prog_usr_sponsor_fk integer NOT NULL,
    prog_usr_pmofed_fk integer,
    prog_cro_fk integer,
    prog_pre_fk integer,
    prog_progindices_fk integer,
    prog_nombre character varying(100),
    prog_descripcion character varying(4000),
    prog_objetivo character varying(4000),
    prog_obj_publico character varying(4000),
    prog_pub_web boolean,
    prog_grp character varying(45),
    prog_semaforo_amarillo integer,
    prog_semaforo_rojo integer,
    prog_activo boolean,
    prog_fecha_crea date,
    prog_fecha_act date NOT NULL,
    prog_version integer,
    prog_ult_usuario character varying(45),
    prog_id_migrado integer,
    prog_ult_origen character varying,
    prog_ult_mod date,
    prog_obj_est_fk integer,
    prog_factor_impacto text
);


ALTER TABLE programas OWNER TO postgres;

--
-- TOC entry 301 (class 1259 OID 52378)
-- Name: programas_prog_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE programas_prog_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE programas_prog_pk_seq OWNER TO postgres;

--
-- TOC entry 3689 (class 0 OID 0)
-- Dependencies: 301
-- Name: programas_prog_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE programas_prog_pk_seq OWNED BY programas.prog_pk;


--
-- TOC entry 322 (class 1259 OID 52466)
-- Name: proyectos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE proyectos (
    proy_pk integer NOT NULL,
    proy_org_fk integer NOT NULL,
    proy_est_fk integer NOT NULL,
    proy_est_pendiente_fk integer,
    proy_sol_aceptacion boolean,
    proy_area_fk integer NOT NULL,
    proy_usr_adjunto_fk integer NOT NULL,
    proy_usr_sponsor_fk integer NOT NULL,
    proy_usr_gerente_fk integer NOT NULL,
    proy_usr_pmofed_fk integer,
    proy_prog_fk integer,
    proy_risk_fk integer,
    proy_cro_fk integer,
    proy_pre_fk integer,
    proy_proyindices_fk integer,
    proy_peso integer,
    proy_descripcion character varying(4000),
    proy_objetivo character varying(4000),
    proy_obj_publico character varying(4000),
    proy_situacion_actual character varying(4000),
    proy_pub_web boolean,
    proy_leccion_aprendida character varying(256),
    proy_nombre character varying(100),
    proy_grp character varying(45),
    proy_semaforo_amarillo integer,
    proy_semaforo_rojo integer,
    proy_activo boolean,
    proy_fecha_crea date,
    proy_fecha_act date NOT NULL,
    proy_ult_mod date,
    proy_ult_origen character varying(45),
    proy_version integer,
    proy_fecha_est_act date,
    proy_id_migrado integer,
    proy_publicable boolean DEFAULT true NOT NULL,
    proy_otr_dat_fk integer,
    proy_latlng_fk integer,
    proy_ult_usuario character varying,
    proy_obj_est_fk integer,
    proy_factor_impacto text
);


ALTER TABLE proyectos OWNER TO postgres;

--
-- TOC entry 373 (class 1259 OID 52729)
-- Name: ss_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_usuario (
    usu_id integer NOT NULL,
    usu_administrador boolean,
    usu_cambio_estado_desc text,
    usu_cod character varying(255) NOT NULL,
    usu_correo_electronico character varying(255) NOT NULL,
    usu_cuenta_bloqueada boolean,
    usu_descripcion text,
    usu_direccion text,
    usu_fecha_password timestamp without time zone,
    usu_fecha_uuid timestamp without time zone,
    usu_foto bytea,
    usu_intentos_fallidos integer,
    usu_nro_doc character varying(255),
    usu_oficina_por_defecto integer,
    usu_origen text,
    usu_password character varying(255),
    usu_primer_apellido character varying(255) NOT NULL,
    usu_primer_nombre character varying(255) NOT NULL,
    usu_registrado boolean,
    usu_segundo_apellido character varying(255),
    usu_segundo_nombre character varying(255),
    usu_telefono character varying(255),
    usu_celular character varying(255),
    usu_user_code integer,
    usu_uuid_des character varying(255),
    usu_version integer,
    usu_vigente boolean NOT NULL,
    usu_aprob_facturas boolean,
    usu_ult_usuario character varying(255),
    usu_ult_mod timestamp without time zone,
    usu_ult_origen character varying(255),
    usu_area_org integer,
    usu_token character varying(100) DEFAULT 'NULL::character varying'::character varying,
    usu_token_act timestamp without time zone,
    usu_ldap_user character varying(50)
);


ALTER TABLE ss_usuario OWNER TO postgres;

--
-- TOC entry 392 (class 1259 OID 53490)
-- Name: programas_proyectos; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW programas_proyectos AS
 SELECT concat('1-', programas.prog_pk) AS id,
    programas.prog_pk AS fichafk,
    1 AS tipoficha,
    programas.prog_fecha_crea AS fechacrea,
    programas.prog_activo AS activo,
    programas.prog_org_fk AS organismo,
    programas.prog_nombre AS nombre,
    estados.est_pk AS estado,
    estados.est_nombre AS estadonombre,
    programas.prog_est_pendiente_fk AS estadopendiente,
    areas.area_pk AS areapk,
    areas.area_nombre AS areanombre,
    programas.prog_sol_aceptacion AS solaceptacion,
    programas.prog_usr_gerente_fk AS gerente,
    ss_usuario.usu_primer_apellido AS gerenteprimerapellido,
    ss_usuario.usu_primer_nombre AS gerenteprimernombre,
    programas.prog_usr_pmofed_fk AS pmofederada
   FROM (((programas
     JOIN estados ON ((programas.prog_est_fk = estados.est_pk)))
     JOIN areas ON ((programas.prog_area_fk = areas.area_pk)))
     JOIN ss_usuario ON ((programas.prog_usr_gerente_fk = ss_usuario.usu_id)))
UNION
 SELECT concat('2-', proyectos.proy_pk) AS id,
    proyectos.proy_pk AS fichafk,
    2 AS tipoficha,
    proyectos.proy_fecha_crea AS fechacrea,
    proyectos.proy_activo AS activo,
    proyectos.proy_org_fk AS organismo,
    proyectos.proy_nombre AS nombre,
    estados.est_pk AS estado,
    estados.est_nombre AS estadonombre,
    proyectos.proy_est_pendiente_fk AS estadopendiente,
    areas.area_pk AS areapk,
    areas.area_nombre AS areanombre,
    proyectos.proy_sol_aceptacion AS solaceptacion,
    proyectos.proy_usr_gerente_fk AS gerente,
    ss_usuario.usu_primer_apellido AS gerenteprimerapellido,
    ss_usuario.usu_primer_nombre AS gerenteprimernombre,
    proyectos.proy_usr_pmofed_fk AS pmofederada
   FROM (((proyectos
     JOIN estados ON ((proyectos.proy_est_fk = estados.est_pk)))
     JOIN areas ON ((proyectos.proy_area_fk = areas.area_pk)))
     JOIN ss_usuario ON ((proyectos.proy_usr_gerente_fk = ss_usuario.usu_id)));


ALTER TABLE programas_proyectos OWNER TO postgres;

--
-- TOC entry 303 (class 1259 OID 52389)
-- Name: proy_docs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE proy_docs (
    proydoc_proy_pk integer NOT NULL,
    proydoc_doc_pk integer NOT NULL
);


ALTER TABLE proy_docs OWNER TO postgres;

--
-- TOC entry 305 (class 1259 OID 52394)
-- Name: proy_indices; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE proy_indices (
    proyind_pk integer NOT NULL,
    proyind_riesgo_expo double precision,
    proyind_riesgo_ultact date,
    proyind_riesgo_alto integer,
    proyind_metodo_estado double precision,
    proyind_metodo_sin_aprobar boolean,
    proyind_periodo_inicio date,
    proyind_periodo_fin date,
    proyind_porc_peso_total double precision,
    proyind_cal_ind double precision,
    proyind_cal_pend integer,
    proyind_fase_color integer,
    proyind_avance_par_azul integer,
    proyind_avance_par_verde integer,
    proyind_anvance_par_rojo integer,
    proyind_avance_fin_azul integer,
    proyind_avance_fin_verde integer,
    proyind_anvance_fin_rojo integer,
    proyind_fecha_act timestamp without time zone,
    proyind_periodo_inicio_ent_fk integer,
    proyind_periodo_fin_ent_fk integer
);


ALTER TABLE proy_indices OWNER TO postgres;

--
-- TOC entry 307 (class 1259 OID 52402)
-- Name: proy_indices_pre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE proy_indices_pre (
    proyindpre_pk integer NOT NULL,
    proyindpre_proyind_fk integer NOT NULL,
    proyindpre_mon_fk integer NOT NULL,
    proyindpre_total double precision,
    proyindpre_est_pre integer,
    proyindpre_ac double precision,
    proyindpre_pv double precision,
    proyindpre_anio double precision
);


ALTER TABLE proy_indices_pre OWNER TO postgres;

--
-- TOC entry 306 (class 1259 OID 52400)
-- Name: proy_indices_pre_proyindpre_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE proy_indices_pre_proyindpre_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE proy_indices_pre_proyindpre_pk_seq OWNER TO postgres;

--
-- TOC entry 3690 (class 0 OID 0)
-- Dependencies: 306
-- Name: proy_indices_pre_proyindpre_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE proy_indices_pre_proyindpre_pk_seq OWNED BY proy_indices_pre.proyindpre_pk;


--
-- TOC entry 304 (class 1259 OID 52392)
-- Name: proy_indices_proyind_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE proy_indices_proyind_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE proy_indices_proyind_pk_seq OWNER TO postgres;

--
-- TOC entry 3691 (class 0 OID 0)
-- Dependencies: 304
-- Name: proy_indices_proyind_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE proy_indices_proyind_pk_seq OWNED BY proy_indices.proyind_pk;


--
-- TOC entry 308 (class 1259 OID 52408)
-- Name: proy_int; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE proy_int (
    proyint_proy_pk integer NOT NULL,
    proyint_int_pk integer NOT NULL
);


ALTER TABLE proy_int OWNER TO postgres;

--
-- TOC entry 309 (class 1259 OID 52411)
-- Name: proy_lectura_area; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE proy_lectura_area (
    proglectarea_area_pk integer NOT NULL,
    proglectarea_proy_pk integer NOT NULL
);


ALTER TABLE proy_lectura_area OWNER TO postgres;

--
-- TOC entry 310 (class 1259 OID 52414)
-- Name: proy_otros_cat_secundarias; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE proy_otros_cat_secundarias (
    proy_cat_proy_otros_fk integer NOT NULL,
    proy_cat_cat_proy_fk integer NOT NULL
);


ALTER TABLE proy_otros_cat_secundarias OWNER TO postgres;

--
-- TOC entry 312 (class 1259 OID 52419)
-- Name: proy_otros_datos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE proy_otros_datos (
    proy_otr_pk integer NOT NULL,
    proy_otr_eta_fk integer,
    proy_otr_cont_fk integer,
    proy_otr_ins_eje_fk integer,
    proy_otr_ent_fk integer,
    proy_otr_origen character varying(1000),
    proy_otr_plazo integer,
    proy_otr_observaciones character varying(4000),
    proy_otr_cat_fk integer,
    proy_otr_est_pub_fk integer
);


ALTER TABLE proy_otros_datos OWNER TO postgres;

--
-- TOC entry 311 (class 1259 OID 52417)
-- Name: proy_otros_datos_proy_otr_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE proy_otros_datos_proy_otr_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE proy_otros_datos_proy_otr_pk_seq OWNER TO postgres;

--
-- TOC entry 3692 (class 0 OID 0)
-- Dependencies: 311
-- Name: proy_otros_datos_proy_otr_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE proy_otros_datos_proy_otr_pk_seq OWNED BY proy_otros_datos.proy_otr_pk;


--
-- TOC entry 313 (class 1259 OID 52428)
-- Name: proy_pre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE proy_pre (
    proypre_proy_fk integer NOT NULL,
    proypre_pre_fk integer NOT NULL
);


ALTER TABLE proy_pre OWNER TO postgres;

--
-- TOC entry 315 (class 1259 OID 52433)
-- Name: proy_publica_hist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE proy_publica_hist (
    proy_publica_pk integer NOT NULL,
    proy_publica_fecha timestamp without time zone NOT NULL,
    proy_publica_proy_fk integer NOT NULL,
    proy_publica_usu_fk integer NOT NULL
);


ALTER TABLE proy_publica_hist OWNER TO postgres;

--
-- TOC entry 314 (class 1259 OID 52431)
-- Name: proy_publica_hist_proy_publica_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE proy_publica_hist_proy_publica_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE proy_publica_hist_proy_publica_pk_seq OWNER TO postgres;

--
-- TOC entry 3693 (class 0 OID 0)
-- Dependencies: 314
-- Name: proy_publica_hist_proy_publica_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE proy_publica_hist_proy_publica_pk_seq OWNED BY proy_publica_hist.proy_publica_pk;


--
-- TOC entry 317 (class 1259 OID 52441)
-- Name: proy_replanificacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE proy_replanificacion (
    proyreplan_pk integer NOT NULL,
    proyreplan_proy_fk integer NOT NULL,
    proyreplan_fecha date NOT NULL,
    proyreplan_desc character varying(5000) NOT NULL,
    proyreplan_historial boolean NOT NULL,
    proyreplan_activo boolean,
    version integer,
    proyreplan_generar_linea_base boolean
);


ALTER TABLE proy_replanificacion OWNER TO postgres;

--
-- TOC entry 316 (class 1259 OID 52439)
-- Name: proy_replanificacion_proyreplan_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE proy_replanificacion_proyreplan_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE proy_replanificacion_proyreplan_pk_seq OWNER TO postgres;

--
-- TOC entry 3694 (class 0 OID 0)
-- Dependencies: 316
-- Name: proy_replanificacion_proyreplan_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE proy_replanificacion_proyreplan_pk_seq OWNED BY proy_replanificacion.proyreplan_pk;


--
-- TOC entry 319 (class 1259 OID 52452)
-- Name: proy_sitact_historico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE proy_sitact_historico (
    proy_sitact_hist_pk integer NOT NULL,
    proy_sitact_fecha date NOT NULL,
    proy_sitact_desc character varying(4000),
    proy_sitact_proy_fk integer NOT NULL,
    proy_sitact_usu_fk integer,
    version integer
);


ALTER TABLE proy_sitact_historico OWNER TO postgres;

--
-- TOC entry 318 (class 1259 OID 52450)
-- Name: proy_sitact_historico_proy_sitact_hist_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE proy_sitact_historico_proy_sitact_hist_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE proy_sitact_historico_proy_sitact_hist_pk_seq OWNER TO postgres;

--
-- TOC entry 3695 (class 0 OID 0)
-- Dependencies: 318
-- Name: proy_sitact_historico_proy_sitact_hist_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE proy_sitact_historico_proy_sitact_hist_pk_seq OWNED BY proy_sitact_historico.proy_sitact_hist_pk;


--
-- TOC entry 320 (class 1259 OID 52461)
-- Name: proy_tags; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE proy_tags (
    proytag_proy_pk integer NOT NULL,
    proytag_area_pk integer NOT NULL
);


ALTER TABLE proy_tags OWNER TO postgres;

--
-- TOC entry 321 (class 1259 OID 52464)
-- Name: proyectos_proy_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE proyectos_proy_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE proyectos_proy_pk_seq OWNER TO postgres;

--
-- TOC entry 3696 (class 0 OID 0)
-- Dependencies: 321
-- Name: proyectos_proy_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE proyectos_proy_pk_seq OWNED BY proyectos.proy_pk;


--
-- TOC entry 324 (class 1259 OID 52478)
-- Name: registros_horas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE registros_horas (
    rh_pk integer NOT NULL,
    rh_usu_fk integer NOT NULL,
    rh_proy_fk integer NOT NULL,
    rh_ent_fk integer NOT NULL,
    rh_fecha date NOT NULL,
    rh_horas numeric(11,2) NOT NULL,
    rh_comentario character varying(4000),
    rh_aprobado boolean
);


ALTER TABLE registros_horas OWNER TO postgres;

--
-- TOC entry 323 (class 1259 OID 52476)
-- Name: registros_horas_rh_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE registros_horas_rh_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE registros_horas_rh_pk_seq OWNER TO postgres;

--
-- TOC entry 3697 (class 0 OID 0)
-- Dependencies: 323
-- Name: registros_horas_rh_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE registros_horas_rh_pk_seq OWNED BY registros_horas.rh_pk;


--
-- TOC entry 326 (class 1259 OID 52489)
-- Name: revinfo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE revinfo (
    rev integer NOT NULL,
    revtstmp bigint,
    version integer DEFAULT 0
);


ALTER TABLE revinfo OWNER TO postgres;

--
-- TOC entry 325 (class 1259 OID 52487)
-- Name: revinfo_rev_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE revinfo_rev_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE revinfo_rev_seq OWNER TO postgres;

--
-- TOC entry 3698 (class 0 OID 0)
-- Dependencies: 325
-- Name: revinfo_rev_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE revinfo_rev_seq OWNED BY revinfo.rev;


--
-- TOC entry 328 (class 1259 OID 52498)
-- Name: riesgos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE riesgos (
    risk_pk integer NOT NULL,
    risk_proy_fk integer NOT NULL,
    risk_nombre character varying(3000) NOT NULL,
    risk_fecha_actu date,
    risk_probabilidad integer,
    risk_impacto integer,
    risk_ent_fk integer,
    risk_fecha_limite date,
    risk_efecto character varying(3000),
    risk_estategia character varying(3000),
    risk_disparador character varying(3000),
    risk_contingencia character varying(3000),
    risk_superado boolean,
    risk_fecha_superado date,
    risk_usuario_superado_fk integer,
    risk_exposicion double precision,
    version integer
);


ALTER TABLE riesgos OWNER TO postgres;

--
-- TOC entry 327 (class 1259 OID 52496)
-- Name: riesgos_risk_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE riesgos_risk_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE riesgos_risk_pk_seq OWNER TO postgres;

--
-- TOC entry 3699 (class 0 OID 0)
-- Dependencies: 327
-- Name: riesgos_risk_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE riesgos_risk_pk_seq OWNED BY riesgos.risk_pk;


--
-- TOC entry 330 (class 1259 OID 52509)
-- Name: roles_interesados; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE roles_interesados (
    rolint_pk integer NOT NULL,
    rolint_org_fk integer NOT NULL,
    rolint_nombre character varying(45)
);


ALTER TABLE roles_interesados OWNER TO postgres;

--
-- TOC entry 329 (class 1259 OID 52507)
-- Name: roles_interesados_rolint_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE roles_interesados_rolint_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE roles_interesados_rolint_pk_seq OWNER TO postgres;

--
-- TOC entry 3700 (class 0 OID 0)
-- Dependencies: 329
-- Name: roles_interesados_rolint_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE roles_interesados_rolint_pk_seq OWNED BY roles_interesados.rolint_pk;


--
-- TOC entry 331 (class 1259 OID 52515)
-- Name: roles_usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE roles_usuarios (
    rol_pk integer NOT NULL,
    rol_nombre character varying(45)
);


ALTER TABLE roles_usuarios OWNER TO postgres;

--
-- TOC entry 333 (class 1259 OID 52520)
-- Name: sql_executed; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sql_executed (
    sql_pk integer NOT NULL,
    sql_ver_mayor integer NOT NULL,
    sql_ver_menor integer NOT NULL,
    sql_build integer NOT NULL,
    sql_desc text,
    sql_fecha date,
    sql_update integer NOT NULL
);


ALTER TABLE sql_executed OWNER TO postgres;

--
-- TOC entry 332 (class 1259 OID 52518)
-- Name: sql_executed_sql_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE sql_executed_sql_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sql_executed_sql_pk_seq OWNER TO postgres;

--
-- TOC entry 3701 (class 0 OID 0)
-- Dependencies: 332
-- Name: sql_executed_sql_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE sql_executed_sql_pk_seq OWNED BY sql_executed.sql_pk;


--
-- TOC entry 335 (class 1259 OID 52531)
-- Name: ss_ayuda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_ayuda (
    ayu_id integer NOT NULL,
    ayu_codigo character varying(45),
    ayu_texto text,
    ayu_ult_mod_fecha timestamp without time zone,
    ayu_ult_mod_origen character varying(45),
    ayu_ult_mod_usuario character varying(45),
    ayu_version integer
);


ALTER TABLE ss_ayuda OWNER TO postgres;

--
-- TOC entry 334 (class 1259 OID 52529)
-- Name: ss_ayuda_ayu_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_ayuda_ayu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_ayuda_ayu_id_seq OWNER TO postgres;

--
-- TOC entry 3702 (class 0 OID 0)
-- Dependencies: 334
-- Name: ss_ayuda_ayu_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_ayuda_ayu_id_seq OWNED BY ss_ayuda.ayu_id;


--
-- TOC entry 337 (class 1259 OID 52542)
-- Name: ss_categoper; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_categoper (
    cat_id integer NOT NULL,
    cat_descripcion text,
    cat_nombre character varying(255) NOT NULL,
    cat_origen character varying(255) NOT NULL,
    cat_user_code integer NOT NULL,
    cat_version integer,
    cat_vigente boolean
);


ALTER TABLE ss_categoper OWNER TO postgres;

--
-- TOC entry 336 (class 1259 OID 52540)
-- Name: ss_categoper_cat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_categoper_cat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_categoper_cat_id_seq OWNER TO postgres;

--
-- TOC entry 3703 (class 0 OID 0)
-- Dependencies: 336
-- Name: ss_categoper_cat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_categoper_cat_id_seq OWNED BY ss_categoper.cat_id;


--
-- TOC entry 339 (class 1259 OID 52553)
-- Name: ss_configuraciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_configuraciones (
    id integer NOT NULL,
    cnf_org_fk integer,
    cnf_codigo character varying(145),
    cnf_descripcion character varying(245),
    cnf_valor character varying(255),
    cnf_protegido boolean,
    cnf_html boolean,
    cnf_ult_usuario character varying(45),
    cnf_ult_mod character varying(45),
    cnf_version integer,
    cnf_ult_origen character varying(50)
);


ALTER TABLE ss_configuraciones OWNER TO postgres;

--
-- TOC entry 338 (class 1259 OID 52551)
-- Name: ss_configuraciones_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_configuraciones_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_configuraciones_id_seq OWNER TO postgres;

--
-- TOC entry 3704 (class 0 OID 0)
-- Dependencies: 338
-- Name: ss_configuraciones_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_configuraciones_id_seq OWNED BY ss_configuraciones.id;


--
-- TOC entry 341 (class 1259 OID 52564)
-- Name: ss_departamentos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_departamentos (
    depto_id integer NOT NULL,
    depto_codigo character varying(255),
    depto_nombre character varying(255),
    depto_ult_mod timestamp without time zone,
    err_ult_origen character varying(255),
    depto_ult_usuario character varying(255),
    depto_version integer
);


ALTER TABLE ss_departamentos OWNER TO postgres;

--
-- TOC entry 340 (class 1259 OID 52562)
-- Name: ss_departamentos_depto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_departamentos_depto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_departamentos_depto_id_seq OWNER TO postgres;

--
-- TOC entry 3705 (class 0 OID 0)
-- Dependencies: 340
-- Name: ss_departamentos_depto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_departamentos_depto_id_seq OWNED BY ss_departamentos.depto_id;


--
-- TOC entry 343 (class 1259 OID 52575)
-- Name: ss_domicilios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_domicilios (
    dom_id integer NOT NULL,
    dom_apto character varying(50),
    dom_letra character varying(255),
    dom_calle character varying(150),
    dom_codigo_postal character varying(5),
    dom_depto_alt character varying(255),
    dom_descripcion text,
    dom_inmueble_nombre character varying(100),
    dom_kilometro character varying(9),
    dom_manzana character varying(5),
    dom_numero_puerta character varying(5),
    dom_oficina character varying(255),
    dom_ruta character varying(5),
    dom_solar character varying(5),
    dom_ult_mod timestamp without time zone,
    dom_ult_origen character varying(255),
    dom_ult_usuario character varying(255),
    dom_version integer,
    dom_depto_id integer,
    dom_loc_id integer,
    dom_pai_id integer,
    dom_par_id boolean,
    dom_tec_id integer,
    dom_tvi_id integer
);


ALTER TABLE ss_domicilios OWNER TO postgres;

--
-- TOC entry 342 (class 1259 OID 52573)
-- Name: ss_domicilios_dom_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_domicilios_dom_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_domicilios_dom_id_seq OWNER TO postgres;

--
-- TOC entry 3706 (class 0 OID 0)
-- Dependencies: 342
-- Name: ss_domicilios_dom_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_domicilios_dom_id_seq OWNED BY ss_domicilios.dom_id;


--
-- TOC entry 344 (class 1259 OID 52584)
-- Name: ss_errores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_errores (
    id integer NOT NULL,
    err_codigo character varying(255),
    err_descripcion character varying(255),
    err_ult_mod timestamp without time zone,
    err_ult_origen character varying(255),
    err_ult_usuario character varying(255),
    err_version integer
);


ALTER TABLE ss_errores OWNER TO postgres;

--
-- TOC entry 346 (class 1259 OID 52592)
-- Name: ss_localidades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_localidades (
    loc_id integer NOT NULL,
    loc_codigo character varying(255),
    loc_nombre character varying(255),
    loc_ult_mod timestamp without time zone,
    loc_ult_origen character varying(255),
    loc_ult_usuario character varying(255),
    loc_version integer,
    loc_depto_id integer
);


ALTER TABLE ss_localidades OWNER TO postgres;

--
-- TOC entry 345 (class 1259 OID 52590)
-- Name: ss_localidades_loc_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_localidades_loc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_localidades_loc_id_seq OWNER TO postgres;

--
-- TOC entry 3707 (class 0 OID 0)
-- Dependencies: 345
-- Name: ss_localidades_loc_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_localidades_loc_id_seq OWNED BY ss_localidades.loc_id;


--
-- TOC entry 348 (class 1259 OID 52603)
-- Name: ss_noticias; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_noticias (
    not_id integer NOT NULL,
    not_ampliar character varying(255),
    not_codigo character varying(45),
    not_contenido text,
    not_desde timestamp without time zone,
    not_hasta timestamp without time zone,
    not_imagen character varying(255),
    not_titulo character varying(255),
    not_ult_mod_fecha timestamp without time zone,
    not_ult_mod_origen character varying(45),
    not_ult_mod_usuario character varying(45),
    not_version integer
);


ALTER TABLE ss_noticias OWNER TO postgres;

--
-- TOC entry 347 (class 1259 OID 52601)
-- Name: ss_noticias_not_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_noticias_not_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_noticias_not_id_seq OWNER TO postgres;

--
-- TOC entry 3708 (class 0 OID 0)
-- Dependencies: 347
-- Name: ss_noticias_not_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_noticias_not_id_seq OWNED BY ss_noticias.not_id;


--
-- TOC entry 393 (class 1259 OID 53495)
-- Name: ss_oficina; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW ss_oficina AS
 SELECT organismos.org_pk AS ofi_id,
    organismos.org_nombre AS ofi_nombre,
    organismos.org_activo AS ofi_activo,
    now() AS ofi_fecha_creacion,
    '' AS ofi_origen,
    1 AS ofi_usuario,
    1 AS ofi_version
   FROM organismos;


ALTER TABLE ss_oficina OWNER TO postgres;

--
-- TOC entry 350 (class 1259 OID 52614)
-- Name: ss_operacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_operacion (
    ope_id integer NOT NULL,
    ope_codigo character varying(255) NOT NULL,
    ope_descripcion text NOT NULL,
    ope_nombre character varying(255) NOT NULL,
    ope_origen character varying(255) NOT NULL,
    ope_tipocampo character varying(255) NOT NULL,
    ope_user_code integer NOT NULL,
    ope_version integer,
    ope_vigente boolean NOT NULL,
    ope_categoria_id integer
);


ALTER TABLE ss_operacion OWNER TO postgres;

--
-- TOC entry 349 (class 1259 OID 52612)
-- Name: ss_operacion_ope_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_operacion_ope_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_operacion_ope_id_seq OWNER TO postgres;

--
-- TOC entry 3709 (class 0 OID 0)
-- Dependencies: 349
-- Name: ss_operacion_ope_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_operacion_ope_id_seq OWNED BY ss_operacion.ope_id;


--
-- TOC entry 352 (class 1259 OID 52625)
-- Name: ss_paises; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_paises (
    pai_id integer NOT NULL,
    pai_codigo2 character varying(255),
    pai_codigo3 character varying(255),
    pai_comun boolean,
    pai_habilitado boolean,
    pai_nombre character varying(255),
    pai_ult_mod timestamp without time zone,
    pai_ult_origen character varying(255),
    pai_ult_usuario character varying(255),
    pai_version integer
);


ALTER TABLE ss_paises OWNER TO postgres;

--
-- TOC entry 351 (class 1259 OID 52623)
-- Name: ss_paises_pai_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_paises_pai_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_paises_pai_id_seq OWNER TO postgres;

--
-- TOC entry 3710 (class 0 OID 0)
-- Dependencies: 351
-- Name: ss_paises_pai_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_paises_pai_id_seq OWNED BY ss_paises.pai_id;


--
-- TOC entry 353 (class 1259 OID 52634)
-- Name: ss_paridades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_paridades (
    par_id boolean NOT NULL,
    par_codigo character varying(9),
    par_descripcion character varying(45),
    par_ult_mod_fecha timestamp without time zone,
    par_ult_mod_origen character varying(45),
    par_ult_mod_usuario character varying(45),
    par_version integer
);


ALTER TABLE ss_paridades OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 51783)
-- Name: ss_paridades_par_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_paridades_par_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_paridades_par_id_seq OWNER TO postgres;

--
-- TOC entry 355 (class 1259 OID 52639)
-- Name: ss_personas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_personas (
    per_id integer NOT NULL,
    per_fec_nac timestamp without time zone,
    per_nro_doc character varying(45),
    per_primer_apellido character varying(100) NOT NULL,
    per_primer_nombre character varying(100),
    per_segundo_apellido character varying(100) NOT NULL,
    per_segundo_nombre character varying(100),
    per_ult_mod_fecha timestamp without time zone,
    per_ult_mod_origen character varying(45),
    per_ult_mod_usuario character varying(45),
    per_version integer,
    per_pais_doc integer,
    per_tipo_doc integer
);


ALTER TABLE ss_personas OWNER TO postgres;

--
-- TOC entry 354 (class 1259 OID 52637)
-- Name: ss_personas_per_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_personas_per_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_personas_per_id_seq OWNER TO postgres;

--
-- TOC entry 3711 (class 0 OID 0)
-- Dependencies: 354
-- Name: ss_personas_per_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_personas_per_id_seq OWNED BY ss_personas.per_id;


--
-- TOC entry 357 (class 1259 OID 52650)
-- Name: ss_rel_rol_operacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_rel_rol_operacion (
    rel_rol_operacion_id integer NOT NULL,
    rel_rol_operacion_editable boolean NOT NULL,
    rel_rol_operacion_origen character varying(255) NOT NULL,
    rel_rol_operacion_user_code integer NOT NULL,
    rel_rol_operacion_visible boolean NOT NULL,
    rel_rol_operacion_operacion_id integer NOT NULL,
    rel_rol_operacion_rol_id integer NOT NULL
);


ALTER TABLE ss_rel_rol_operacion OWNER TO postgres;

--
-- TOC entry 356 (class 1259 OID 52648)
-- Name: ss_rel_rol_operacion_rel_rol_operacion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_rel_rol_operacion_rel_rol_operacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_rel_rol_operacion_rel_rol_operacion_id_seq OWNER TO postgres;

--
-- TOC entry 3712 (class 0 OID 0)
-- Dependencies: 356
-- Name: ss_rel_rol_operacion_rel_rol_operacion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_rel_rol_operacion_rel_rol_operacion_id_seq OWNED BY ss_rel_rol_operacion.rel_rol_operacion_id;


--
-- TOC entry 359 (class 1259 OID 52658)
-- Name: ss_rol; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_rol (
    rol_id integer NOT NULL,
    rol_cod character varying(255) NOT NULL,
    rol_descripcion text,
    rol_nombre character varying(255) NOT NULL,
    rol_label character varying(150),
    rol_origen character varying(255),
    rol_user_code integer,
    rol_version integer,
    rol_vigente boolean,
    rol_tipo_usuario boolean
);


ALTER TABLE ss_rol OWNER TO postgres;

--
-- TOC entry 358 (class 1259 OID 52656)
-- Name: ss_rol_rol_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_rol_rol_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_rol_rol_id_seq OWNER TO postgres;

--
-- TOC entry 3713 (class 0 OID 0)
-- Dependencies: 358
-- Name: ss_rol_rol_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_rol_rol_id_seq OWNED BY ss_rol.rol_id;


--
-- TOC entry 361 (class 1259 OID 52669)
-- Name: ss_telefonos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_telefonos (
    tel_id integer NOT NULL,
    tel_numero character varying(25),
    tel_observaciones character varying(255),
    tel_prefijo character varying(10),
    tel_ult_mod timestamp without time zone,
    tel_ult_origen character varying(45),
    tel_ult_usuario character varying(45),
    tel_version integer,
    tel_tiptel_id integer
);


ALTER TABLE ss_telefonos OWNER TO postgres;

--
-- TOC entry 360 (class 1259 OID 52667)
-- Name: ss_telefonos_tel_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_telefonos_tel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_telefonos_tel_id_seq OWNER TO postgres;

--
-- TOC entry 3714 (class 0 OID 0)
-- Dependencies: 360
-- Name: ss_telefonos_tel_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_telefonos_tel_id_seq OWNED BY ss_telefonos.tel_id;


--
-- TOC entry 363 (class 1259 OID 52677)
-- Name: ss_tipos_documento_persona; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_tipos_documento_persona (
    tipdocper_id integer NOT NULL,
    tipdocper_codigo character varying(255),
    tipdocper_descripcion character varying(255),
    tipdocper_habilitado boolean,
    tipdocper_ult_mod timestamp without time zone,
    tipdocper_ult_origen character varying(255),
    tipdocper_ult_usuario character varying(255),
    tipdocper_version integer
);


ALTER TABLE ss_tipos_documento_persona OWNER TO postgres;

--
-- TOC entry 362 (class 1259 OID 52675)
-- Name: ss_tipos_documento_persona_tipdocper_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_tipos_documento_persona_tipdocper_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_tipos_documento_persona_tipdocper_id_seq OWNER TO postgres;

--
-- TOC entry 3715 (class 0 OID 0)
-- Dependencies: 362
-- Name: ss_tipos_documento_persona_tipdocper_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_tipos_documento_persona_tipdocper_id_seq OWNED BY ss_tipos_documento_persona.tipdocper_id;


--
-- TOC entry 365 (class 1259 OID 52688)
-- Name: ss_tipos_entrada_colectiva; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_tipos_entrada_colectiva (
    tec_id integer NOT NULL,
    tec_codigo character varying(255),
    tec_descripcion character varying(255),
    tec_ult_mod timestamp without time zone,
    tec_ult_origen character varying(255),
    tec_ult_usuario character varying(255),
    tec_version integer
);


ALTER TABLE ss_tipos_entrada_colectiva OWNER TO postgres;

--
-- TOC entry 364 (class 1259 OID 52686)
-- Name: ss_tipos_entrada_colectiva_tec_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_tipos_entrada_colectiva_tec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_tipos_entrada_colectiva_tec_id_seq OWNER TO postgres;

--
-- TOC entry 3716 (class 0 OID 0)
-- Dependencies: 364
-- Name: ss_tipos_entrada_colectiva_tec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_tipos_entrada_colectiva_tec_id_seq OWNED BY ss_tipos_entrada_colectiva.tec_id;


--
-- TOC entry 367 (class 1259 OID 52699)
-- Name: ss_tipos_telefono; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_tipos_telefono (
    "tipTel_id" integer NOT NULL,
    "tipTel_codigo" character varying(255),
    "tipTel_descripcion" character varying(255),
    "tipTel_habilitado" boolean,
    "tipTel_ult_mod" timestamp without time zone,
    "tipTel_ult_origen" character varying(255),
    "tipTel_ult_usuario" character varying(255),
    "tipTel_version" integer
);


ALTER TABLE ss_tipos_telefono OWNER TO postgres;

--
-- TOC entry 366 (class 1259 OID 52697)
-- Name: ss_tipos_telefono_tipTel_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "ss_tipos_telefono_tipTel_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "ss_tipos_telefono_tipTel_id_seq" OWNER TO postgres;

--
-- TOC entry 3717 (class 0 OID 0)
-- Dependencies: 366
-- Name: ss_tipos_telefono_tipTel_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "ss_tipos_telefono_tipTel_id_seq" OWNED BY ss_tipos_telefono."tipTel_id";


--
-- TOC entry 369 (class 1259 OID 52710)
-- Name: ss_tipos_vialidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_tipos_vialidad (
    tvi_id integer NOT NULL,
    tvi_abreviacion character varying(255),
    tvi_codigo character varying(255),
    tvi_descripcion character varying(255),
    tvi_ult_mod timestamp without time zone,
    tvi_ult_origen character varying(255),
    tvi_ult_usuario character varying(255),
    tvi_version integer
);


ALTER TABLE ss_tipos_vialidad OWNER TO postgres;

--
-- TOC entry 368 (class 1259 OID 52708)
-- Name: ss_tipos_vialidad_tvi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_tipos_vialidad_tvi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_tipos_vialidad_tvi_id_seq OWNER TO postgres;

--
-- TOC entry 3718 (class 0 OID 0)
-- Dependencies: 368
-- Name: ss_tipos_vialidad_tvi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_tipos_vialidad_tvi_id_seq OWNED BY ss_tipos_vialidad.tvi_id;


--
-- TOC entry 371 (class 1259 OID 52721)
-- Name: ss_usu_ofi_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_usu_ofi_roles (
    usu_ofi_roles_id integer NOT NULL,
    usu_ofi_roles_origen character varying(255) NOT NULL,
    usu_ofi_roles_user_code integer NOT NULL,
    usu_ofi_roles_oficina integer,
    usu_ofi_roles_rol integer NOT NULL,
    usu_ofi_roles_usuario integer NOT NULL,
    usu_ofi_roles_usu_area integer,
    usu_ofi_roles_activo boolean
);


ALTER TABLE ss_usu_ofi_roles OWNER TO postgres;

--
-- TOC entry 370 (class 1259 OID 52719)
-- Name: ss_usu_ofi_roles_usu_ofi_roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_usu_ofi_roles_usu_ofi_roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_usu_ofi_roles_usu_ofi_roles_id_seq OWNER TO postgres;

--
-- TOC entry 3719 (class 0 OID 0)
-- Dependencies: 370
-- Name: ss_usu_ofi_roles_usu_ofi_roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_usu_ofi_roles_usu_ofi_roles_id_seq OWNED BY ss_usu_ofi_roles.usu_ofi_roles_id;


--
-- TOC entry 372 (class 1259 OID 52727)
-- Name: ss_usuario_usu_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_usuario_usu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_usuario_usu_id_seq OWNER TO postgres;

--
-- TOC entry 3720 (class 0 OID 0)
-- Dependencies: 372
-- Name: ss_usuario_usu_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_usuario_usu_id_seq OWNED BY ss_usuario.usu_id;


--
-- TOC entry 375 (class 1259 OID 52741)
-- Name: ss_usuarios_datos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ss_usuarios_datos (
    ss_usu_dat_pk integer NOT NULL,
    ss_usu_dat_area_fk integer NOT NULL,
    ss_usu_dat_usu_fk integer NOT NULL
);


ALTER TABLE ss_usuarios_datos OWNER TO postgres;

--
-- TOC entry 374 (class 1259 OID 52739)
-- Name: ss_usuarios_datos_ss_usu_dat_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ss_usuarios_datos_ss_usu_dat_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ss_usuarios_datos_ss_usu_dat_pk_seq OWNER TO postgres;

--
-- TOC entry 3721 (class 0 OID 0)
-- Dependencies: 374
-- Name: ss_usuarios_datos_ss_usu_dat_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ss_usuarios_datos_ss_usu_dat_pk_seq OWNED BY ss_usuarios_datos.ss_usu_dat_pk;


--
-- TOC entry 377 (class 1259 OID 52749)
-- Name: temas_calidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE temas_calidad (
    tca_pk integer NOT NULL,
    tca_nombre character varying(100) NOT NULL,
    tca_org_fk integer NOT NULL
);


ALTER TABLE temas_calidad OWNER TO postgres;

--
-- TOC entry 376 (class 1259 OID 52747)
-- Name: temas_calidad_tca_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE temas_calidad_tca_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE temas_calidad_tca_pk_seq OWNER TO postgres;

--
-- TOC entry 3722 (class 0 OID 0)
-- Dependencies: 376
-- Name: temas_calidad_tca_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE temas_calidad_tca_pk_seq OWNED BY temas_calidad.tca_pk;


--
-- TOC entry 379 (class 1259 OID 52757)
-- Name: tipo_documento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipo_documento (
    tipdoc_pk integer NOT NULL,
    tipodoc_nombre character varying(145) NOT NULL,
    tipodoc_exigido_desde integer,
    tipodoc_peso integer,
    tipodoc_org_fk integer,
    tipodoc_resum_ejecutivo boolean
);


ALTER TABLE tipo_documento OWNER TO postgres;

--
-- TOC entry 381 (class 1259 OID 52765)
-- Name: tipo_documento_instancia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipo_documento_instancia (
    tipodoc_inst_pk integer NOT NULL,
    tipodoc_inst_tipodoc_fk integer NOT NULL,
    tipodoc_inst_exigido_desde integer,
    tipodoc_inst_peso integer,
    tipodoc_inst_proy_fk integer,
    tipodoc_inst_prog_fk integer,
    tipodoc_inst_resum_ejecutivo boolean
);


ALTER TABLE tipo_documento_instancia OWNER TO postgres;

--
-- TOC entry 380 (class 1259 OID 52763)
-- Name: tipo_documento_instancia_tipodoc_inst_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipo_documento_instancia_tipodoc_inst_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipo_documento_instancia_tipodoc_inst_pk_seq OWNER TO postgres;

--
-- TOC entry 3723 (class 0 OID 0)
-- Dependencies: 380
-- Name: tipo_documento_instancia_tipodoc_inst_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipo_documento_instancia_tipodoc_inst_pk_seq OWNED BY tipo_documento_instancia.tipodoc_inst_pk;


--
-- TOC entry 378 (class 1259 OID 52755)
-- Name: tipo_documento_tipdoc_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipo_documento_tipdoc_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipo_documento_tipdoc_pk_seq OWNER TO postgres;

--
-- TOC entry 3724 (class 0 OID 0)
-- Dependencies: 378
-- Name: tipo_documento_tipdoc_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipo_documento_tipdoc_pk_seq OWNED BY tipo_documento.tipdoc_pk;


--
-- TOC entry 383 (class 1259 OID 52773)
-- Name: tipo_gasto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipo_gasto (
    tipogas_pk integer NOT NULL,
    tipogas_org_fk integer,
    tipogas_nombre character varying(150) NOT NULL
);


ALTER TABLE tipo_gasto OWNER TO postgres;

--
-- TOC entry 382 (class 1259 OID 52771)
-- Name: tipo_gasto_tipogas_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipo_gasto_tipogas_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipo_gasto_tipogas_pk_seq OWNER TO postgres;

--
-- TOC entry 3725 (class 0 OID 0)
-- Dependencies: 382
-- Name: tipo_gasto_tipogas_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipo_gasto_tipogas_pk_seq OWNED BY tipo_gasto.tipogas_pk;


--
-- TOC entry 385 (class 1259 OID 52781)
-- Name: tipo_leccion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipo_leccion (
    tipolec_pk integer NOT NULL,
    tipolec_codigo character varying(45),
    tipolec_nombre character varying(150)
);


ALTER TABLE tipo_leccion OWNER TO postgres;

--
-- TOC entry 384 (class 1259 OID 52779)
-- Name: tipo_leccion_tipolec_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipo_leccion_tipolec_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipo_leccion_tipolec_pk_seq OWNER TO postgres;

--
-- TOC entry 3726 (class 0 OID 0)
-- Dependencies: 384
-- Name: tipo_leccion_tipolec_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipo_leccion_tipolec_pk_seq OWNED BY tipo_leccion.tipolec_pk;


--
-- TOC entry 387 (class 1259 OID 52789)
-- Name: tipos_media; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipos_media (
    tip_pk integer NOT NULL,
    tip_cod character varying(45),
    tip_nombre character varying(245)
);


ALTER TABLE tipos_media OWNER TO postgres;

--
-- TOC entry 386 (class 1259 OID 52787)
-- Name: tipos_media_tip_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipos_media_tip_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipos_media_tip_pk_seq OWNER TO postgres;

--
-- TOC entry 3727 (class 0 OID 0)
-- Dependencies: 386
-- Name: tipos_media_tip_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipos_media_tip_pk_seq OWNED BY tipos_media.tip_pk;


--
-- TOC entry 389 (class 1259 OID 52797)
-- Name: valor_calidad_codigos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE valor_calidad_codigos (
    vca_pk integer NOT NULL,
    vca_codigo character varying(45) NOT NULL,
    vca_nombre character varying(100) NOT NULL,
    vca_habilitado boolean NOT NULL
);


ALTER TABLE valor_calidad_codigos OWNER TO postgres;

--
-- TOC entry 388 (class 1259 OID 52795)
-- Name: valor_calidad_codigos_vca_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE valor_calidad_codigos_vca_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE valor_calidad_codigos_vca_pk_seq OWNER TO postgres;

--
-- TOC entry 3728 (class 0 OID 0)
-- Dependencies: 388
-- Name: valor_calidad_codigos_vca_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE valor_calidad_codigos_vca_pk_seq OWNED BY valor_calidad_codigos.vca_pk;


--
-- TOC entry 391 (class 1259 OID 52805)
-- Name: valor_hora; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE valor_hora (
    val_hor_pk integer NOT NULL,
    val_hor_usu_fk integer NOT NULL,
    val_hor_org_fk integer NOT NULL,
    val_hor_mon_fk integer NOT NULL,
    val_hor_valor numeric(11,2) NOT NULL,
    val_hor_anio integer NOT NULL
);


ALTER TABLE valor_hora OWNER TO postgres;

--
-- TOC entry 390 (class 1259 OID 52803)
-- Name: valor_hora_val_hor_pk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE valor_hora_val_hor_pk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE valor_hora_val_hor_pk_seq OWNER TO postgres;

--
-- TOC entry 3729 (class 0 OID 0)
-- Dependencies: 390
-- Name: valor_hora_val_hor_pk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE valor_hora_val_hor_pk_seq OWNED BY valor_hora.val_hor_pk;


--
-- TOC entry 2603 (class 2604 OID 51790)
-- Name: adq_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY adquisicion ALTER COLUMN adq_pk SET DEFAULT nextval('adquisicion_adq_pk_seq'::regclass);


--
-- TOC entry 2604 (class 2604 OID 51798)
-- Name: amb_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ambito ALTER COLUMN amb_pk SET DEFAULT nextval('ambito_amb_pk_seq'::regclass);


--
-- TOC entry 2605 (class 2604 OID 51806)
-- Name: con_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY area_conocimiento ALTER COLUMN con_pk SET DEFAULT nextval('area_conocimiento_con_pk_seq'::regclass);


--
-- TOC entry 2606 (class 2604 OID 51814)
-- Name: areaorgintprov_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY area_organi_int_prove ALTER COLUMN areaorgintprov_pk SET DEFAULT nextval('area_organi_int_prove_areaorgintprov_pk_seq'::regclass);


--
-- TOC entry 2607 (class 2604 OID 51822)
-- Name: area_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY areas ALTER COLUMN area_pk SET DEFAULT nextval('areas_area_pk_seq'::regclass);


--
-- TOC entry 2610 (class 2604 OID 51832)
-- Name: arastag_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY areas_tags ALTER COLUMN arastag_pk SET DEFAULT nextval('areas_tags_arastag_pk_seq'::regclass);


--
-- TOC entry 2612 (class 2604 OID 51979)
-- Name: busq_filtro_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY busq_filtro ALTER COLUMN busq_filtro_pk SET DEFAULT nextval('busq_filtro_busq_filtro_pk_seq'::regclass);


--
-- TOC entry 2613 (class 2604 OID 51990)
-- Name: cal_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY calidad ALTER COLUMN cal_pk SET DEFAULT nextval('calidad_cal_pk_seq'::regclass);


--
-- TOC entry 2614 (class 2604 OID 51998)
-- Name: cat_proy_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria_proyectos ALTER COLUMN cat_proy_pk SET DEFAULT nextval('categoria_proyectos_cat_proy_pk_seq'::regclass);


--
-- TOC entry 2617 (class 2604 OID 52008)
-- Name: cro_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cronogramas ALTER COLUMN cro_pk SET DEFAULT nextval('cronogramas_cro_pk_seq'::regclass);


--
-- TOC entry 2618 (class 2604 OID 52019)
-- Name: dev_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY devengado ALTER COLUMN dev_pk SET DEFAULT nextval('devengado_dev_pk_seq'::regclass);


--
-- TOC entry 2619 (class 2604 OID 52027)
-- Name: docfile_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY doc_file ALTER COLUMN docfile_pk SET DEFAULT nextval('doc_file_docfile_pk_seq'::regclass);


--
-- TOC entry 2621 (class 2604 OID 52038)
-- Name: docs_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY documentos ALTER COLUMN docs_pk SET DEFAULT nextval('documentos_docs_pk_seq'::regclass);


--
-- TOC entry 2622 (class 2604 OID 52046)
-- Name: enthist_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ent_hist_linea_base ALTER COLUMN enthist_pk SET DEFAULT nextval('ent_hist_linea_base_enthist_pk_seq'::regclass);


--
-- TOC entry 2623 (class 2604 OID 52054)
-- Name: ent_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY entregables ALTER COLUMN ent_pk SET DEFAULT nextval('entregables_ent_pk_seq'::regclass);


--
-- TOC entry 2627 (class 2604 OID 52066)
-- Name: est_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estados ALTER COLUMN est_pk SET DEFAULT nextval('estados_est_pk_seq'::regclass);


--
-- TOC entry 2628 (class 2604 OID 52074)
-- Name: est_pub_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estados_publicacion ALTER COLUMN est_pub_pk SET DEFAULT nextval('estados_publicacion_est_pub_pk_seq'::regclass);


--
-- TOC entry 2629 (class 2604 OID 52082)
-- Name: eta_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY etapa ALTER COLUMN eta_pk SET DEFAULT nextval('etapa_eta_pk_seq'::regclass);


--
-- TOC entry 2630 (class 2604 OID 52090)
-- Name: fue_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fuente_financiamiento ALTER COLUMN fue_pk SET DEFAULT nextval('fuente_financiamiento_fue_pk_seq'::regclass);


--
-- TOC entry 2631 (class 2604 OID 52098)
-- Name: gas_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gastos ALTER COLUMN gas_pk SET DEFAULT nextval('gastos_gas_pk_seq'::regclass);


--
-- TOC entry 2632 (class 2604 OID 52109)
-- Name: image_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY image ALTER COLUMN image_pk SET DEFAULT nextval('image_image_pk_seq'::regclass);


--
-- TOC entry 2634 (class 2604 OID 52121)
-- Name: int_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY interesados ALTER COLUMN int_pk SET DEFAULT nextval('interesados_int_pk_seq'::regclass);


--
-- TOC entry 2635 (class 2604 OID 52132)
-- Name: latlng_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY latlng_proyectos ALTER COLUMN latlng_pk SET DEFAULT nextval('latlng_proyectos_latlng_pk_seq'::regclass);


--
-- TOC entry 2636 (class 2604 OID 52146)
-- Name: lecapr_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lecc_aprendidas ALTER COLUMN lecapr_pk SET DEFAULT nextval('lecc_aprendidas_lecapr_pk_seq'::regclass);


--
-- TOC entry 2637 (class 2604 OID 52157)
-- Name: lineabase_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lineabase_historico ALTER COLUMN lineabase_pk SET DEFAULT nextval('lineabase_historico_lineabase_pk_seq'::regclass);


--
-- TOC entry 2638 (class 2604 OID 52165)
-- Name: mail_tmp_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mails_template ALTER COLUMN mail_tmp_pk SET DEFAULT nextval('mails_template_mail_tmp_pk_seq'::regclass);


--
-- TOC entry 2639 (class 2604 OID 52176)
-- Name: media_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY media_proyectos ALTER COLUMN media_pk SET DEFAULT nextval('media_proyectos_media_pk_seq'::regclass);


--
-- TOC entry 2640 (class 2604 OID 52187)
-- Name: mon_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY moneda ALTER COLUMN mon_pk SET DEFAULT nextval('moneda_mon_pk_seq'::regclass);


--
-- TOC entry 2641 (class 2604 OID 52195)
-- Name: not_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacion ALTER COLUMN not_pk SET DEFAULT nextval('notificacion_not_pk_seq'::regclass);


--
-- TOC entry 2642 (class 2604 OID 52206)
-- Name: notenv_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacion_envio ALTER COLUMN notenv_pk SET DEFAULT nextval('notificacion_envio_notenv_pk_seq'::regclass);


--
-- TOC entry 2643 (class 2604 OID 52214)
-- Name: notinst_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacion_instancia ALTER COLUMN notinst_pk SET DEFAULT nextval('notificacion_instancia_notinst_pk_seq'::regclass);


--
-- TOC entry 2644 (class 2604 OID 52222)
-- Name: obj_est_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY objetivos_estrategicos ALTER COLUMN obj_est_pk SET DEFAULT nextval('objetivos_estrategicos_obj_est_pk_seq'::regclass);


--
-- TOC entry 2645 (class 2604 OID 52230)
-- Name: orga_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organi_int_prove ALTER COLUMN orga_pk SET DEFAULT nextval('organi_int_prove_orga_pk_seq'::regclass);


--
-- TOC entry 2646 (class 2604 OID 52238)
-- Name: org_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organismos ALTER COLUMN org_pk SET DEFAULT nextval('organismos_org_pk_seq'::regclass);


--
-- TOC entry 2648 (class 2604 OID 52250)
-- Name: pag_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pagos ALTER COLUMN pag_pk SET DEFAULT nextval('pagos_pag_pk_seq'::regclass);


--
-- TOC entry 2649 (class 2604 OID 52261)
-- Name: part_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY participantes ALTER COLUMN part_pk SET DEFAULT nextval('participantes_part_pk_seq'::regclass);


--
-- TOC entry 2651 (class 2604 OID 52270)
-- Name: pers_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY personas ALTER COLUMN pers_pk SET DEFAULT nextval('personas_pers_pk_seq'::regclass);


--
-- TOC entry 2652 (class 2604 OID 52278)
-- Name: cnf_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pge_configuraciones ALTER COLUMN cnf_id SET DEFAULT nextval('pge_configuraciones_cnf_id_seq'::regclass);


--
-- TOC entry 2653 (class 2604 OID 52289)
-- Name: inv_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pge_invocaciones ALTER COLUMN inv_id SET DEFAULT nextval('pge_invocaciones_inv_id_seq'::regclass);


--
-- TOC entry 2654 (class 2604 OID 52300)
-- Name: p_crono_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY plantilla_cronograma ALTER COLUMN p_crono_pk SET DEFAULT nextval('plantilla_cronograma_p_crono_pk_seq'::regclass);


--
-- TOC entry 2655 (class 2604 OID 52311)
-- Name: p_entregables_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY plantilla_entregables ALTER COLUMN p_entregables_id SET DEFAULT nextval('plantilla_entregables_p_entregables_id_seq'::regclass);


--
-- TOC entry 2656 (class 2604 OID 52322)
-- Name: pre_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY presupuesto ALTER COLUMN pre_pk SET DEFAULT nextval('presupuesto_pre_pk_seq'::regclass);


--
-- TOC entry 2657 (class 2604 OID 52330)
-- Name: prodmes_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prod_mes ALTER COLUMN prodmes_pk SET DEFAULT nextval('prod_mes_prodmes_pk_seq'::regclass);


--
-- TOC entry 2658 (class 2604 OID 52338)
-- Name: prod_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY productos ALTER COLUMN prod_pk SET DEFAULT nextval('productos_prod_pk_seq'::regclass);


--
-- TOC entry 2659 (class 2604 OID 52355)
-- Name: progind_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prog_indices ALTER COLUMN progind_pk SET DEFAULT nextval('prog_indices_progind_pk_seq'::regclass);


--
-- TOC entry 2660 (class 2604 OID 52363)
-- Name: progindpre_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prog_indices_pre ALTER COLUMN progindpre_pk SET DEFAULT nextval('prog_indices_pre_progindpre_pk_seq'::regclass);


--
-- TOC entry 2661 (class 2604 OID 52383)
-- Name: prog_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY programas ALTER COLUMN prog_pk SET DEFAULT nextval('programas_prog_pk_seq'::regclass);


--
-- TOC entry 2662 (class 2604 OID 52397)
-- Name: proyind_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_indices ALTER COLUMN proyind_pk SET DEFAULT nextval('proy_indices_proyind_pk_seq'::regclass);


--
-- TOC entry 2663 (class 2604 OID 52405)
-- Name: proyindpre_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_indices_pre ALTER COLUMN proyindpre_pk SET DEFAULT nextval('proy_indices_pre_proyindpre_pk_seq'::regclass);


--
-- TOC entry 2664 (class 2604 OID 52422)
-- Name: proy_otr_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_otros_datos ALTER COLUMN proy_otr_pk SET DEFAULT nextval('proy_otros_datos_proy_otr_pk_seq'::regclass);


--
-- TOC entry 2665 (class 2604 OID 52436)
-- Name: proy_publica_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_publica_hist ALTER COLUMN proy_publica_pk SET DEFAULT nextval('proy_publica_hist_proy_publica_pk_seq'::regclass);


--
-- TOC entry 2666 (class 2604 OID 52444)
-- Name: proyreplan_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_replanificacion ALTER COLUMN proyreplan_pk SET DEFAULT nextval('proy_replanificacion_proyreplan_pk_seq'::regclass);


--
-- TOC entry 2667 (class 2604 OID 52455)
-- Name: proy_sitact_hist_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_sitact_historico ALTER COLUMN proy_sitact_hist_pk SET DEFAULT nextval('proy_sitact_historico_proy_sitact_hist_pk_seq'::regclass);


--
-- TOC entry 2668 (class 2604 OID 52469)
-- Name: proy_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos ALTER COLUMN proy_pk SET DEFAULT nextval('proyectos_proy_pk_seq'::regclass);


--
-- TOC entry 2670 (class 2604 OID 52481)
-- Name: rh_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY registros_horas ALTER COLUMN rh_pk SET DEFAULT nextval('registros_horas_rh_pk_seq'::regclass);


--
-- TOC entry 2671 (class 2604 OID 52492)
-- Name: rev; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY revinfo ALTER COLUMN rev SET DEFAULT nextval('revinfo_rev_seq'::regclass);


--
-- TOC entry 2673 (class 2604 OID 52501)
-- Name: risk_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY riesgos ALTER COLUMN risk_pk SET DEFAULT nextval('riesgos_risk_pk_seq'::regclass);


--
-- TOC entry 2674 (class 2604 OID 52512)
-- Name: rolint_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles_interesados ALTER COLUMN rolint_pk SET DEFAULT nextval('roles_interesados_rolint_pk_seq'::regclass);


--
-- TOC entry 2675 (class 2604 OID 52523)
-- Name: sql_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sql_executed ALTER COLUMN sql_pk SET DEFAULT nextval('sql_executed_sql_pk_seq'::regclass);


--
-- TOC entry 2676 (class 2604 OID 52534)
-- Name: ayu_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_ayuda ALTER COLUMN ayu_id SET DEFAULT nextval('ss_ayuda_ayu_id_seq'::regclass);


--
-- TOC entry 2677 (class 2604 OID 52545)
-- Name: cat_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_categoper ALTER COLUMN cat_id SET DEFAULT nextval('ss_categoper_cat_id_seq'::regclass);


--
-- TOC entry 2678 (class 2604 OID 52556)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_configuraciones ALTER COLUMN id SET DEFAULT nextval('ss_configuraciones_id_seq'::regclass);


--
-- TOC entry 2679 (class 2604 OID 52567)
-- Name: depto_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_departamentos ALTER COLUMN depto_id SET DEFAULT nextval('ss_departamentos_depto_id_seq'::regclass);


--
-- TOC entry 2680 (class 2604 OID 52578)
-- Name: dom_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_domicilios ALTER COLUMN dom_id SET DEFAULT nextval('ss_domicilios_dom_id_seq'::regclass);


--
-- TOC entry 2681 (class 2604 OID 52595)
-- Name: loc_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_localidades ALTER COLUMN loc_id SET DEFAULT nextval('ss_localidades_loc_id_seq'::regclass);


--
-- TOC entry 2682 (class 2604 OID 52606)
-- Name: not_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_noticias ALTER COLUMN not_id SET DEFAULT nextval('ss_noticias_not_id_seq'::regclass);


--
-- TOC entry 2683 (class 2604 OID 52617)
-- Name: ope_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_operacion ALTER COLUMN ope_id SET DEFAULT nextval('ss_operacion_ope_id_seq'::regclass);


--
-- TOC entry 2684 (class 2604 OID 52628)
-- Name: pai_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_paises ALTER COLUMN pai_id SET DEFAULT nextval('ss_paises_pai_id_seq'::regclass);


--
-- TOC entry 2685 (class 2604 OID 52642)
-- Name: per_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_personas ALTER COLUMN per_id SET DEFAULT nextval('ss_personas_per_id_seq'::regclass);


--
-- TOC entry 2686 (class 2604 OID 52653)
-- Name: rel_rol_operacion_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_rel_rol_operacion ALTER COLUMN rel_rol_operacion_id SET DEFAULT nextval('ss_rel_rol_operacion_rel_rol_operacion_id_seq'::regclass);


--
-- TOC entry 2687 (class 2604 OID 52661)
-- Name: rol_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_rol ALTER COLUMN rol_id SET DEFAULT nextval('ss_rol_rol_id_seq'::regclass);


--
-- TOC entry 2688 (class 2604 OID 52672)
-- Name: tel_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_telefonos ALTER COLUMN tel_id SET DEFAULT nextval('ss_telefonos_tel_id_seq'::regclass);


--
-- TOC entry 2689 (class 2604 OID 52680)
-- Name: tipdocper_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_tipos_documento_persona ALTER COLUMN tipdocper_id SET DEFAULT nextval('ss_tipos_documento_persona_tipdocper_id_seq'::regclass);


--
-- TOC entry 2690 (class 2604 OID 52691)
-- Name: tec_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_tipos_entrada_colectiva ALTER COLUMN tec_id SET DEFAULT nextval('ss_tipos_entrada_colectiva_tec_id_seq'::regclass);


--
-- TOC entry 2691 (class 2604 OID 52702)
-- Name: tipTel_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_tipos_telefono ALTER COLUMN "tipTel_id" SET DEFAULT nextval('"ss_tipos_telefono_tipTel_id_seq"'::regclass);


--
-- TOC entry 2692 (class 2604 OID 52713)
-- Name: tvi_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_tipos_vialidad ALTER COLUMN tvi_id SET DEFAULT nextval('ss_tipos_vialidad_tvi_id_seq'::regclass);


--
-- TOC entry 2693 (class 2604 OID 52724)
-- Name: usu_ofi_roles_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_usu_ofi_roles ALTER COLUMN usu_ofi_roles_id SET DEFAULT nextval('ss_usu_ofi_roles_usu_ofi_roles_id_seq'::regclass);


--
-- TOC entry 2694 (class 2604 OID 52732)
-- Name: usu_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_usuario ALTER COLUMN usu_id SET DEFAULT nextval('ss_usuario_usu_id_seq'::regclass);


--
-- TOC entry 2696 (class 2604 OID 52744)
-- Name: ss_usu_dat_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_usuarios_datos ALTER COLUMN ss_usu_dat_pk SET DEFAULT nextval('ss_usuarios_datos_ss_usu_dat_pk_seq'::regclass);


--
-- TOC entry 2697 (class 2604 OID 52752)
-- Name: tca_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY temas_calidad ALTER COLUMN tca_pk SET DEFAULT nextval('temas_calidad_tca_pk_seq'::regclass);


--
-- TOC entry 2698 (class 2604 OID 52760)
-- Name: tipdoc_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_documento ALTER COLUMN tipdoc_pk SET DEFAULT nextval('tipo_documento_tipdoc_pk_seq'::regclass);


--
-- TOC entry 2699 (class 2604 OID 52768)
-- Name: tipodoc_inst_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_documento_instancia ALTER COLUMN tipodoc_inst_pk SET DEFAULT nextval('tipo_documento_instancia_tipodoc_inst_pk_seq'::regclass);


--
-- TOC entry 2700 (class 2604 OID 52776)
-- Name: tipogas_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_gasto ALTER COLUMN tipogas_pk SET DEFAULT nextval('tipo_gasto_tipogas_pk_seq'::regclass);


--
-- TOC entry 2701 (class 2604 OID 52784)
-- Name: tipolec_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_leccion ALTER COLUMN tipolec_pk SET DEFAULT nextval('tipo_leccion_tipolec_pk_seq'::regclass);


--
-- TOC entry 2702 (class 2604 OID 52792)
-- Name: tip_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipos_media ALTER COLUMN tip_pk SET DEFAULT nextval('tipos_media_tip_pk_seq'::regclass);


--
-- TOC entry 2703 (class 2604 OID 52800)
-- Name: vca_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY valor_calidad_codigos ALTER COLUMN vca_pk SET DEFAULT nextval('valor_calidad_codigos_vca_pk_seq'::regclass);


--
-- TOC entry 2704 (class 2604 OID 52808)
-- Name: val_hor_pk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY valor_hora ALTER COLUMN val_hor_pk SET DEFAULT nextval('valor_hora_val_hor_pk_seq'::regclass);


--
-- TOC entry 3419 (class 0 OID 51787)
-- Dependencies: 177
-- Data for Name: adquisicion; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3730 (class 0 OID 0)
-- Dependencies: 176
-- Name: adquisicion_adq_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('adquisicion_adq_pk_seq', 1, false);


--
-- TOC entry 3421 (class 0 OID 51795)
-- Dependencies: 179
-- Data for Name: ambito; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3731 (class 0 OID 0)
-- Dependencies: 178
-- Name: ambito_amb_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ambito_amb_pk_seq', 1, false);


--
-- TOC entry 3423 (class 0 OID 51803)
-- Dependencies: 181
-- Data for Name: area_conocimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3732 (class 0 OID 0)
-- Dependencies: 180
-- Name: area_conocimiento_con_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('area_conocimiento_con_pk_seq', 1, false);


--
-- TOC entry 3425 (class 0 OID 51811)
-- Dependencies: 183
-- Data for Name: area_organi_int_prove; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3733 (class 0 OID 0)
-- Dependencies: 182
-- Name: area_organi_int_prove_areaorgintprov_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('area_organi_int_prove_areaorgintprov_pk_seq', 1, false);


--
-- TOC entry 3427 (class 0 OID 51819)
-- Dependencies: 185
-- Data for Name: areas; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3734 (class 0 OID 0)
-- Dependencies: 184
-- Name: areas_area_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('areas_area_pk_seq', 1, false);


--
-- TOC entry 3429 (class 0 OID 51829)
-- Dependencies: 187
-- Data for Name: areas_tags; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3735 (class 0 OID 0)
-- Dependencies: 186
-- Name: areas_tags_arastag_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('areas_tags_arastag_pk_seq', 1, false);


--
-- TOC entry 3634 (class 0 OID 56631)
-- Dependencies: 394
-- Data for Name: aud_doc_file; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3430 (class 0 OID 51835)
-- Dependencies: 188
-- Data for Name: aud_pge_configuraciones; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3431 (class 0 OID 51841)
-- Dependencies: 189
-- Data for Name: aud_programas; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3432 (class 0 OID 51847)
-- Dependencies: 190
-- Data for Name: aud_programas_proyectos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3433 (class 0 OID 51853)
-- Dependencies: 191
-- Data for Name: aud_ss_ayuda; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3434 (class 0 OID 51859)
-- Dependencies: 192
-- Data for Name: aud_ss_categoper; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3435 (class 0 OID 51865)
-- Dependencies: 193
-- Data for Name: aud_ss_configuraciones; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3436 (class 0 OID 51871)
-- Dependencies: 194
-- Data for Name: aud_ss_departamentos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3437 (class 0 OID 51877)
-- Dependencies: 195
-- Data for Name: aud_ss_domicilios; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3438 (class 0 OID 51883)
-- Dependencies: 196
-- Data for Name: aud_ss_errores; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3439 (class 0 OID 51889)
-- Dependencies: 197
-- Data for Name: aud_ss_localidades; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3440 (class 0 OID 51895)
-- Dependencies: 198
-- Data for Name: aud_ss_noticias; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3441 (class 0 OID 51901)
-- Dependencies: 199
-- Data for Name: aud_ss_oficina; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3442 (class 0 OID 51907)
-- Dependencies: 200
-- Data for Name: aud_ss_operacion; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3443 (class 0 OID 51913)
-- Dependencies: 201
-- Data for Name: aud_ss_paises; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3444 (class 0 OID 51919)
-- Dependencies: 202
-- Data for Name: aud_ss_paridades; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3445 (class 0 OID 51922)
-- Dependencies: 203
-- Data for Name: aud_ss_personas; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3446 (class 0 OID 51928)
-- Dependencies: 204
-- Data for Name: aud_ss_rel_rol_operacion; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3447 (class 0 OID 51931)
-- Dependencies: 205
-- Data for Name: aud_ss_rol; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3448 (class 0 OID 51937)
-- Dependencies: 206
-- Data for Name: aud_ss_telefonos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3449 (class 0 OID 51940)
-- Dependencies: 207
-- Data for Name: aud_ss_tipos_documento_persona; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3450 (class 0 OID 51946)
-- Dependencies: 208
-- Data for Name: aud_ss_tipos_entrada_colectiva; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3451 (class 0 OID 51952)
-- Dependencies: 209
-- Data for Name: aud_ss_tipos_telefono; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3452 (class 0 OID 51958)
-- Dependencies: 210
-- Data for Name: aud_ss_tipos_vialidad; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3453 (class 0 OID 51964)
-- Dependencies: 211
-- Data for Name: aud_ss_usu_ofi_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3454 (class 0 OID 51967)
-- Dependencies: 212
-- Data for Name: aud_ss_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3456 (class 0 OID 51976)
-- Dependencies: 214
-- Data for Name: busq_filtro; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3736 (class 0 OID 0)
-- Dependencies: 213
-- Name: busq_filtro_busq_filtro_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('busq_filtro_busq_filtro_pk_seq', 1, false);


--
-- TOC entry 3458 (class 0 OID 51987)
-- Dependencies: 216
-- Data for Name: calidad; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3737 (class 0 OID 0)
-- Dependencies: 215
-- Name: calidad_cal_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('calidad_cal_pk_seq', 1, false);


--
-- TOC entry 3460 (class 0 OID 51995)
-- Dependencies: 218
-- Data for Name: categoria_proyectos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3738 (class 0 OID 0)
-- Dependencies: 217
-- Name: categoria_proyectos_cat_proy_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('categoria_proyectos_cat_proy_pk_seq', 1, false);


--
-- TOC entry 3462 (class 0 OID 52005)
-- Dependencies: 220
-- Data for Name: cronogramas; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3739 (class 0 OID 0)
-- Dependencies: 219
-- Name: cronogramas_cro_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cronogramas_cro_pk_seq', 1, false);


--
-- TOC entry 3414 (class 0 OID 51773)
-- Dependencies: 172
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-1', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 1, 'EXECUTED', '7:afe897bea20bc08d1681087c94d0967d', 'createSequence sequenceName=hibernate_sequence', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-2', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 2, 'EXECUTED', '7:212167d2d53c02a46c0ebebbd79ef075', 'createSequence sequenceName=obj_est_pk_seq', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-3', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 3, 'EXECUTED', '7:4d590c2f9452e49634d0c634e7cdb401', 'createSequence sequenceName=ss_paridades_par_id_seq', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-4', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 4, 'EXECUTED', '7:dd68e5fdfbbdfafc34355179cf7ff3c6', 'createTable tableName=adquisicion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-5', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 5, 'EXECUTED', '7:33e87e598cc85a25499cbff53ef3baea', 'createTable tableName=ambito', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-6', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 6, 'EXECUTED', '7:d1d8449dc1f57884655ea0266f307acd', 'createTable tableName=area_conocimiento', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-7', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 7, 'EXECUTED', '7:851a5fd20af6416b31b285134e1264a2', 'createTable tableName=area_organi_int_prove', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-8', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 8, 'EXECUTED', '7:93d484e7b5ee9e593fc6e948ba5f3ae5', 'createTable tableName=areas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-9', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 9, 'EXECUTED', '7:744ef2fe80d25c53fa0fbeea3e02d1b5', 'createTable tableName=areas_tags', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-10', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 10, 'EXECUTED', '7:d422560d069597f2d17a1608c8ba294c', 'createTable tableName=aud_pge_configuraciones', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-11', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 11, 'EXECUTED', '7:ec9b4ac61c5f8df319e91f0b58dc5b4b', 'createTable tableName=aud_programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-12', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 12, 'EXECUTED', '7:93ebe4b9e77cddf87cdbbbbcd6fe635b', 'createTable tableName=aud_programas_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-13', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 13, 'EXECUTED', '7:deefdf45cd827f2ad86a86b9bd108105', 'createTable tableName=aud_ss_ayuda', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-14', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 14, 'EXECUTED', '7:fc3bb13054becf06b460640e96ac8853', 'createTable tableName=aud_ss_categoper', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-15', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 15, 'EXECUTED', '7:9a649e242d661a5a75d5b5f6583c77b6', 'createTable tableName=aud_ss_configuraciones', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-16', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 16, 'EXECUTED', '7:ca19823f92bde90bb3995b08d0df1671', 'createTable tableName=aud_ss_departamentos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-17', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 17, 'EXECUTED', '7:e2e4c26d56779e603684fc58d4675147', 'createTable tableName=aud_ss_domicilios', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-18', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 18, 'EXECUTED', '7:b26c3889747b3ba67afbe20785cde464', 'createTable tableName=aud_ss_errores', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-19', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 19, 'EXECUTED', '7:63fc82aab78526f153aaf24950a87c79', 'createTable tableName=aud_ss_localidades', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-20', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 20, 'EXECUTED', '7:964d6fd059ab51c6acd0337ff3b3208b', 'createTable tableName=aud_ss_noticias', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-21', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 21, 'EXECUTED', '7:6239b451107aa4a762b982a6bd5fa6b1', 'createTable tableName=aud_ss_oficina', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-22', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 22, 'EXECUTED', '7:39021606d7deae098c21bcce7f2b0fe8', 'createTable tableName=aud_ss_operacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-23', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 23, 'EXECUTED', '7:ae7c70edb6970a99a699e589f69a95af', 'createTable tableName=aud_ss_paises', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-24', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 24, 'EXECUTED', '7:2ac14045a0ff7e7fbd9ca8ed6da68567', 'createTable tableName=aud_ss_paridades', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-25', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 25, 'EXECUTED', '7:26ce3467e5da068d36619e1a28bd09d6', 'createTable tableName=aud_ss_personas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-26', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 26, 'EXECUTED', '7:1e5356758ff22440af7808772acc59d1', 'createTable tableName=aud_ss_rel_rol_operacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-27', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 27, 'EXECUTED', '7:964fb8ca39597671b0104b92867f1c33', 'createTable tableName=aud_ss_rol', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-28', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 28, 'EXECUTED', '7:9c869dd450c3d4744d0d21e55f7e8b95', 'createTable tableName=aud_ss_telefonos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-29', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 29, 'EXECUTED', '7:f44163cd03478bd78a68644010eac205', 'createTable tableName=aud_ss_tipos_documento_persona', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-30', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 30, 'EXECUTED', '7:e7edce6ed61333e2ad0cfbcad0b12bb2', 'createTable tableName=aud_ss_tipos_entrada_colectiva', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-31', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 31, 'EXECUTED', '7:6ae26ba6212800e5899f1a31a3b988b8', 'createTable tableName=aud_ss_tipos_telefono', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-32', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 32, 'EXECUTED', '7:d5741468f5a7481e2fbd512477a06951', 'createTable tableName=aud_ss_tipos_vialidad', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-33', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 33, 'EXECUTED', '7:b89b76b058429e7c23b1939c552e9298', 'createTable tableName=aud_ss_usu_ofi_roles', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-34', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 34, 'EXECUTED', '7:93378aec469425364ec7d5909ea71f82', 'createTable tableName=aud_ss_usuario', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-35', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 35, 'EXECUTED', '7:f6a795c555698b24e52eaebd1407a237', 'createTable tableName=busq_filtro', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-36', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 36, 'EXECUTED', '7:341a13795f1fa7370da960b95d7f477a', 'createTable tableName=calidad', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-37', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 37, 'EXECUTED', '7:64ee740bfe189adf4691551d3763db9a', 'createTable tableName=categoria_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-38', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 38, 'EXECUTED', '7:e7460ce57407420d39d121657012d2be', 'createTable tableName=cronogramas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-39', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 39, 'EXECUTED', '7:d95493f70b011f7fba60f96517e36cc2', 'createTable tableName=departamentos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-40', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 40, 'EXECUTED', '7:78356db09f5ce8b8ec14c55f0b5cd924', 'createTable tableName=devengado', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-41', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 41, 'EXECUTED', '7:d661fb38df65d962d9046537cfcaced2', 'createTable tableName=doc_file', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-42', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 42, 'EXECUTED', '7:111600c705c013a5159e0a6e82ae21bd', 'createTable tableName=documentos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-43', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 43, 'EXECUTED', '7:4074747e737d22e7c0edf203038619d4', 'createTable tableName=ent_hist_linea_base', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-44', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 44, 'EXECUTED', '7:daba987ed995ee924a8905bc584ce8ab', 'createTable tableName=entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-45', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 45, 'EXECUTED', '7:3e98dd81dfa45edb2f185aa15ded3714', 'createTable tableName=estados', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-46', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 46, 'EXECUTED', '7:8d7bea99e9c115b6ded18d5c1aaae574', 'createTable tableName=estados_publicacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-47', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 47, 'EXECUTED', '7:dba56b0b5d23e63f4081d899f5d960a7', 'createTable tableName=etapa', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-48', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 48, 'EXECUTED', '7:686f9370c8fa0a6ba0ba25516bca1cdf', 'createTable tableName=fuente_financiamiento', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-49', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 49, 'EXECUTED', '7:238bebd7fb46dc2ff81283f075cc4b41', 'createTable tableName=gastos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-50', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 50, 'EXECUTED', '7:2a0d9723791991b0f7b668563c6dad8b', 'createTable tableName=image', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-51', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 51, 'EXECUTED', '7:7db1c74a360194e815822a290f03bb65', 'createTable tableName=interesados', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-52', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 52, 'EXECUTED', '7:6043b3bfb8e90d8f2bc3987342d2f56b', 'createTable tableName=latlng_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-53', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 53, 'EXECUTED', '7:3cc32c801e6200460a65863da84cfe4a', 'createTable tableName=lecapr_areacon', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-54', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 54, 'EXECUTED', '7:48c7f81eeee7ee4bb03d36ef2d0a9950', 'createTable tableName=lecc_aprendidas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-55', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 55, 'EXECUTED', '7:ac1124f1dbb6b8573c927527116635a4', 'createTable tableName=lineabase_historico', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-56', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 56, 'EXECUTED', '7:fd207f6d87d62eb1883e09c92c8aa418', 'createTable tableName=mails_template', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-57', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 57, 'EXECUTED', '7:4244ece95783fc72f5e003edf79bfd5c', 'createTable tableName=media_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-58', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 58, 'EXECUTED', '7:78a8c16d764904a81d4f1e1a9bf0dff4', 'createTable tableName=moneda', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-59', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 59, 'EXECUTED', '7:4df1c8bee0c9a3b09a89e5da484334ef', 'createTable tableName=notificacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-60', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 60, 'EXECUTED', '7:85de5a5bc07efad0b6294fed6ec0d049', 'createTable tableName=notificacion_envio', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-61', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 61, 'EXECUTED', '7:20c07a9ae9b5e0e628a79d8195787227', 'createTable tableName=notificacion_instancia', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-62', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 62, 'EXECUTED', '7:14353e65c884333323f36b90b22c961f', 'createTable tableName=objetivos_estrategicos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-63', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 63, 'EXECUTED', '7:74cebac4e3b387794a8c4ae2a105ead8', 'createTable tableName=organi_int_prove', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-64', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 64, 'EXECUTED', '7:4e6199166f1a8307a94c3fe793a426c2', 'createTable tableName=organismos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-65', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 65, 'EXECUTED', '7:4127bdf266b5d3233b25bb595ca1a942', 'createTable tableName=pagos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-66', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 66, 'EXECUTED', '7:02927e8d5cea8a9754fa83198aece74d', 'createTable tableName=participantes', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-67', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 67, 'EXECUTED', '7:90632dfacad37c0a5077cca0371eecd5', 'createTable tableName=personas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-68', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 68, 'EXECUTED', '7:1ae3b296c472c3245ba5763353c81fa5', 'createTable tableName=pge_configuraciones', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-69', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 69, 'EXECUTED', '7:896ccc9c4eb12008ddb3adab62251b1d', 'createTable tableName=pge_invocaciones', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-70', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 70, 'EXECUTED', '7:808c10d21018c8feffb2df95657a7eb8', 'createTable tableName=plantilla_cronograma', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-71', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 71, 'EXECUTED', '7:c17266ae0e44dd4aa5fd39c60229cb87', 'createTable tableName=plantilla_entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-72', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 72, 'EXECUTED', '7:1f077956e80d02be6ebfb94b9e710269', 'createTable tableName=presupuesto', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-73', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 73, 'EXECUTED', '7:9353eacea6eb6ad8832fe07528ddaaef', 'createTable tableName=prod_mes', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-74', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 74, 'EXECUTED', '7:18f512681beb80e5ce42c1475d50e107', 'createTable tableName=productos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-75', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 75, 'EXECUTED', '7:3c809042385b28110f1fdab50c1b8498', 'createTable tableName=prog_docs', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-76', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 76, 'EXECUTED', '7:6e76fef0475101577fb3305b63e3b4f6', 'createTable tableName=prog_docs_obl', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-77', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 77, 'EXECUTED', '7:51b79c94d2d968c07f53f3d66bdfb618', 'createTable tableName=prog_indices', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-78', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 78, 'EXECUTED', '7:47426a3d4e9bae4d466e1f65f44e109b', 'createTable tableName=prog_indices_pre', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-79', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 79, 'EXECUTED', '7:38f728a3d47c6844727b9b87aabe792c', 'createTable tableName=prog_int', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-80', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 80, 'EXECUTED', '7:fe611794e06fa3446c2223adae721a74', 'createTable tableName=prog_lectura_area', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-81', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 81, 'EXECUTED', '7:36b45e4bc367c7c9552d69bcf634f25e', 'createTable tableName=prog_pre', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-82', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 82, 'EXECUTED', '7:e4051b443ad835516e7d3a5fbccfc5f2', 'createTable tableName=prog_tags', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-83', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 83, 'EXECUTED', '7:016909ed665c2d66d9e19e494b74f8f9', 'createTable tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-84', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 84, 'EXECUTED', '7:85552b7d7fc7441f4df8b814389c4bdd', 'createTable tableName=proy_docs', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-85', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 85, 'EXECUTED', '7:15dba16f2bac603227972454da2e55a7', 'createTable tableName=proy_indices', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-86', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 86, 'EXECUTED', '7:740170f1308ed89f6a2338da5577ee62', 'createTable tableName=proy_indices_pre', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-87', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 87, 'EXECUTED', '7:447f638e49335ef43d9c13670950a201', 'createTable tableName=proy_int', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-88', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 88, 'EXECUTED', '7:d6639855ad8f8d8c6754ecff78579451', 'createTable tableName=proy_lectura_area', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-89', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 89, 'EXECUTED', '7:73e9f12605fb00613a8bf98473cd6c52', 'createTable tableName=proy_otros_cat_secundarias', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-90', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 90, 'EXECUTED', '7:872ec0e6089fd7195655f39f11744fde', 'createTable tableName=proy_otros_datos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-91', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 91, 'EXECUTED', '7:c8e027fab89bac6c13fbdf7a7bf8cda5', 'createTable tableName=proy_pre', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-92', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 92, 'EXECUTED', '7:403c64ec9af6ee5517def5706b8e967e', 'createTable tableName=proy_publica_hist', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-93', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 93, 'EXECUTED', '7:fc27c8a0220b7e29c83b21bbf54bc7a7', 'createTable tableName=proy_replanificacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-94', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 94, 'EXECUTED', '7:38c4bf3a11cc77ea76b98170bfdd4b62', 'createTable tableName=proy_sitact_historico', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-95', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 95, 'EXECUTED', '7:aa140e7e2e81019e46709a4a16051e14', 'createTable tableName=proy_tags', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-96', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 96, 'EXECUTED', '7:5511c6b2df559c2a796764f2ddc96aec', 'createTable tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-97', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 97, 'EXECUTED', '7:107e5b83e469a324d252840ea786a3e2', 'createTable tableName=registros_horas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-98', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 98, 'EXECUTED', '7:0bd283a85341ffde3184599a93f16a9d', 'createTable tableName=revinfo', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-99', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 99, 'EXECUTED', '7:304d36301d313a92b6f1560efd9f7f29', 'createTable tableName=riesgos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-100', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 100, 'EXECUTED', '7:e4e5e4d1e94438568f700eabab3c737d', 'createTable tableName=roles_interesados', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-101', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 101, 'EXECUTED', '7:84330b9553bd094c18678db8c6534f46', 'createTable tableName=roles_usuarios', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-102', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 102, 'EXECUTED', '7:55d18473762e8e069ccd8a6cffe03203', 'createTable tableName=sql_executed', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-103', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 103, 'EXECUTED', '7:2f5e15c4cab2e86f72b38cd33a6f3d4b', 'createTable tableName=ss_ayuda', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-104', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 104, 'EXECUTED', '7:1caf769ff6b6140634f9051eb4854184', 'createTable tableName=ss_categoper', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-105', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 105, 'EXECUTED', '7:ee2f7d0c1ad19ba25df2e8013a9316f8', 'createTable tableName=ss_configuraciones', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-106', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 106, 'EXECUTED', '7:1e0de694558bafba8ba77e4fcd5d34d1', 'createTable tableName=ss_departamentos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-107', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 107, 'EXECUTED', '7:4360262a92547f889d5ade5a5a4e159e', 'createTable tableName=ss_domicilios', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-108', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 108, 'EXECUTED', '7:de2bac3e38d12f7f678393cfc0c38fab', 'createTable tableName=ss_errores', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-109', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 109, 'EXECUTED', '7:a5dfad56931e4ef8f2ac213585d4b585', 'createTable tableName=ss_localidades', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-110', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 110, 'EXECUTED', '7:fd7725f9f329e05b5f29fbfa76d6348d', 'createTable tableName=ss_noticias', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-111', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 111, 'EXECUTED', '7:cfd445393a74d49c5db0a68769ab9c09', 'createTable tableName=ss_operacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-112', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 112, 'EXECUTED', '7:057879a50d1e8fc519857c24e3e8ecea', 'createTable tableName=ss_paises', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-113', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 113, 'EXECUTED', '7:5ce50193242b2db533eb8862b5e54970', 'createTable tableName=ss_paridades', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-114', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 114, 'EXECUTED', '7:8b20346537b18e08dababd8f17c0cadd', 'createTable tableName=ss_personas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-115', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 115, 'EXECUTED', '7:738f81bec58cda12332924108755df4a', 'createTable tableName=ss_rel_rol_operacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-116', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 116, 'EXECUTED', '7:48282bc6398250f447bd16bad4630b1a', 'createTable tableName=ss_rol', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-117', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 117, 'EXECUTED', '7:9d6c8904d8c967a1bb7a8428c17ba14e', 'createTable tableName=ss_telefonos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-118', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 118, 'EXECUTED', '7:c11bb2fb22d338f65d02ca0383da304c', 'createTable tableName=ss_tipos_documento_persona', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-119', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 119, 'EXECUTED', '7:4c5bff13838f8a1d8d88ab3443dbbc15', 'createTable tableName=ss_tipos_entrada_colectiva', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-120', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 120, 'EXECUTED', '7:624e3ceece27c263c42fdba2906f5aee', 'createTable tableName=ss_tipos_telefono', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-121', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 121, 'EXECUTED', '7:666b835c8bf12c638a31eea89673abea', 'createTable tableName=ss_tipos_vialidad', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-122', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 122, 'EXECUTED', '7:05aa348264a37dd578d000db4c081e9f', 'createTable tableName=ss_usu_ofi_roles', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-123', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 123, 'EXECUTED', '7:b5b3f7ba673495fa4471399ebd6deacf', 'createTable tableName=ss_usuario', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-124', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 124, 'EXECUTED', '7:1b719e47151d14bff5490b167d922d09', 'createTable tableName=ss_usuarios_datos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-125', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 125, 'EXECUTED', '7:de09f75ce4a7574ac46802b6edc7b151', 'createTable tableName=temas_calidad', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-126', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 126, 'EXECUTED', '7:f67446ee795434d2db4c8e7dad8ec9e8', 'createTable tableName=tipo_documento', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-127', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 127, 'EXECUTED', '7:72cf75e560de680df6a4ec85dbb166d9', 'createTable tableName=tipo_documento_instancia', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-128', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 128, 'EXECUTED', '7:1d7043a4b63066ec9564bfc3938d1386', 'createTable tableName=tipo_gasto', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-129', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 129, 'EXECUTED', '7:94fdacf5edd073796845402dda4cb62b', 'createTable tableName=tipo_leccion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-130', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 130, 'EXECUTED', '7:40784640f2695a20f39ca89a6b15013e', 'createTable tableName=tipos_media', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-131', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 131, 'EXECUTED', '7:a7971cd47a50575d4dc5da1663f4c142', 'createTable tableName=valor_calidad_codigos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-132', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 132, 'EXECUTED', '7:4121237986332d5ce77e668558472733', 'createTable tableName=valor_hora', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-133', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 133, 'EXECUTED', '7:50dde42d87af8a31ba11bf65fe76509e', 'addPrimaryKey constraintName=aud_pge_configuraciones_pkey, tableName=aud_pge_configuraciones', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-134', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 134, 'EXECUTED', '7:5824c42700bf297d9bf63e63416f3adb', 'addPrimaryKey constraintName=aud_programas_pkey, tableName=aud_programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-135', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 135, 'EXECUTED', '7:9168558bd62dcfa73e35d0be055b6179', 'addPrimaryKey constraintName=aud_programas_proyectos_pkey, tableName=aud_programas_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-136', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 136, 'EXECUTED', '7:de7045db11ac183a8007e3f75ca7cd28', 'addPrimaryKey constraintName=aud_ss_ayuda_pkey, tableName=aud_ss_ayuda', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-137', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 137, 'EXECUTED', '7:49d32bb09dba2ddeeaff1c8fc4e73d8c', 'addPrimaryKey constraintName=aud_ss_categoper_pkey, tableName=aud_ss_categoper', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-138', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 138, 'EXECUTED', '7:1d746eca55782fdde34c817c3ddf4a8b', 'addPrimaryKey constraintName=aud_ss_configuraciones_pkey, tableName=aud_ss_configuraciones', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-139', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 139, 'EXECUTED', '7:67ee0bd4e9d3d47853241f91e021e3c1', 'addPrimaryKey constraintName=aud_ss_departamentos_pkey, tableName=aud_ss_departamentos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-140', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 140, 'EXECUTED', '7:5362a35a910ff71c2a92d4b2d0b31a41', 'addPrimaryKey constraintName=aud_ss_domicilios_pkey, tableName=aud_ss_domicilios', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-141', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 141, 'EXECUTED', '7:dab85c48466b5614a99b6e866a7dd18f', 'addPrimaryKey constraintName=aud_ss_errores_pkey, tableName=aud_ss_errores', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-142', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 142, 'EXECUTED', '7:84d2286c5884c1dd9e48b5950beb413f', 'addPrimaryKey constraintName=aud_ss_localidades_pkey, tableName=aud_ss_localidades', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-143', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 143, 'EXECUTED', '7:c37e53d14c18dcf3154710ecd4238198', 'addPrimaryKey constraintName=aud_ss_noticias_pkey, tableName=aud_ss_noticias', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-144', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 144, 'EXECUTED', '7:7584621fba585c1ca46275aeb9d63c49', 'addPrimaryKey constraintName=aud_ss_oficina_pkey, tableName=aud_ss_oficina', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-145', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 145, 'EXECUTED', '7:4553e11421479c125d3239df9e734316', 'addPrimaryKey constraintName=aud_ss_operacion_pkey, tableName=aud_ss_operacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-146', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 146, 'EXECUTED', '7:0617b7d0e85a9220b709b595094b4db7', 'addPrimaryKey constraintName=aud_ss_paises_pkey, tableName=aud_ss_paises', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-147', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 147, 'EXECUTED', '7:d4005f9d50daa7c40dfb8911668eceaa', 'addPrimaryKey constraintName=aud_ss_paridades_pkey, tableName=aud_ss_paridades', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-148', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 148, 'EXECUTED', '7:2ba4ca9eaa80d3d0226904c9bba54559', 'addPrimaryKey constraintName=aud_ss_personas_pkey, tableName=aud_ss_personas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-149', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 149, 'EXECUTED', '7:1546e79b0d832ca0daad6579703aa2f0', 'addPrimaryKey constraintName=aud_ss_rel_rol_operacion_pkey, tableName=aud_ss_rel_rol_operacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-150', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 150, 'EXECUTED', '7:3d29c32d4205b72e8b0957c102ee8d23', 'addPrimaryKey constraintName=aud_ss_rol_pkey, tableName=aud_ss_rol', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-151', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 151, 'EXECUTED', '7:2d832d6f036db4403829ec01ab767268', 'addPrimaryKey constraintName=aud_ss_telefonos_pkey, tableName=aud_ss_telefonos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-152', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 152, 'EXECUTED', '7:01214e13f82bb938a6fcbbc27662886c', 'addPrimaryKey constraintName=aud_ss_tipos_documento_persona_pkey, tableName=aud_ss_tipos_documento_persona', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-153', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 153, 'EXECUTED', '7:9babc5c29dd381741bec34a3f10ff3ab', 'addPrimaryKey constraintName=aud_ss_tipos_entrada_colectiva_pkey, tableName=aud_ss_tipos_entrada_colectiva', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-154', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 154, 'EXECUTED', '7:9be73573ce028d776f6aa6356da0381d', 'addPrimaryKey constraintName=aud_ss_tipos_telefono_pkey, tableName=aud_ss_tipos_telefono', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-155', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 155, 'EXECUTED', '7:ad80b60810e9ffc0fc492b071f8b27ee', 'addPrimaryKey constraintName=aud_ss_tipos_vialidad_pkey, tableName=aud_ss_tipos_vialidad', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-156', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 156, 'EXECUTED', '7:e7086b1e551dca2d361089e69376f69f', 'addPrimaryKey constraintName=aud_ss_usu_ofi_roles_pkey, tableName=aud_ss_usu_ofi_roles', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-157', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 157, 'EXECUTED', '7:152628d83a59073229688906a223d89f', 'addPrimaryKey constraintName=aud_ss_usuario_pkey, tableName=aud_ss_usuario', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-158', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 158, 'EXECUTED', '7:9936c98e7bbafe943c11675fa26edc45', 'addPrimaryKey constraintName=departamentos_pkey, tableName=departamentos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-159', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 159, 'EXECUTED', '7:e87af281d60e20b2361cf6ffa13d318c', 'addPrimaryKey constraintName=lecapr_areacon_pkey, tableName=lecapr_areacon', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-160', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 160, 'EXECUTED', '7:580ea6e488d53244450f81b80ce3173f', 'addPrimaryKey constraintName=prog_docs_obl_pkey, tableName=prog_docs_obl', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-161', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 161, 'EXECUTED', '7:616dd8efd7ed8386fffe07558b22ead3', 'addPrimaryKey constraintName=prog_docs_pkey, tableName=prog_docs', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-162', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 162, 'EXECUTED', '7:bccff6a44bf10100271400ad05fb4f74', 'addPrimaryKey constraintName=prog_int_pkey, tableName=prog_int', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-163', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 163, 'EXECUTED', '7:704d0ea6a1cb6fdd5eac046575f938af', 'addPrimaryKey constraintName=prog_lectura_area_pkey, tableName=prog_lectura_area', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-164', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 164, 'EXECUTED', '7:ef4fc3f510443676b6720ab0df368057', 'addPrimaryKey constraintName=prog_pre_pkey, tableName=prog_pre', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-165', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 165, 'EXECUTED', '7:f180511d9a271ab29cd516c4637441ab', 'addPrimaryKey constraintName=prog_tags_pkey, tableName=prog_tags', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-166', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 166, 'EXECUTED', '7:23fcb59fa5c6bbf5bd1a662761f150c6', 'addPrimaryKey constraintName=proy_docs_pkey, tableName=proy_docs', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-167', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 167, 'EXECUTED', '7:c330ed37b4a4f4d9b7c0804c53e08293', 'addPrimaryKey constraintName=proy_int_pkey, tableName=proy_int', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-168', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 168, 'EXECUTED', '7:9a30a5c09570e91ed84bbf12e515fde6', 'addPrimaryKey constraintName=proy_lectura_area_pkey, tableName=proy_lectura_area', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-169', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 169, 'EXECUTED', '7:de9398c42f8436994cd074c4e54a1e0f', 'addPrimaryKey constraintName=proy_otros_cat_secundarias_pkey, tableName=proy_otros_cat_secundarias', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-170', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 170, 'EXECUTED', '7:c59d6ae38bf046e2239e4c17dbf710b1', 'addPrimaryKey constraintName=proy_pre_pkey, tableName=proy_pre', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-171', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 171, 'EXECUTED', '7:0fca7b6444fe28f60af4eba5ddd4bf8a', 'addPrimaryKey constraintName=proy_tags_pkey, tableName=proy_tags', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-172', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 172, 'EXECUTED', '7:2f24132d714bfb17b6413f728f8baf01', 'addPrimaryKey constraintName=roles_usuarios_pkey, tableName=roles_usuarios', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-173', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 173, 'EXECUTED', '7:c1ee53cd8ea83c1746ee97f32cea3cbe', 'addPrimaryKey constraintName=ss_errores_pkey, tableName=ss_errores', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-174', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 174, 'EXECUTED', '7:1bbe0e0c2da19131c013b3567e98528e', 'addPrimaryKey constraintName=ss_paridades_pkey, tableName=ss_paridades', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-175', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 175, 'EXECUTED', '7:2e36f8549e991fd198fd2e972e56d130', 'addUniqueConstraint constraintName=obj_est_org_fk_nombre, tableName=objetivos_estrategicos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-176', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 176, 'EXECUTED', '7:8b54254a619f5520b98e0241849dbde9', 'createIndex indexName=area_activo_idx, tableName=areas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-177', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 177, 'EXECUTED', '7:0b02321c5db5ba3b2fc616701b53c97d', 'createIndex indexName=cat_org_fk_idx, tableName=categoria_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-178', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 178, 'EXECUTED', '7:0970acabd5319d4a81137ee30ba40906', 'createIndex indexName=cat_tipo_idx, tableName=categoria_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-179', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 179, 'EXECUTED', '7:9ee65765210b620628e153a7a7053499', 'createIndex indexName=ent_parent_idx, tableName=entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-180', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 180, 'EXECUTED', '7:4365d4f8efff0c6a871a15d28682ffdf', 'createIndex indexName=obj_est_org_fk1_idx, tableName=objetivos_estrategicos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-181', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 181, 'EXECUTED', '7:c4163004cd4ca33f0bbac278fc5eb522', 'createIndex indexName=org_token_idx, tableName=organismos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-182', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 182, 'EXECUTED', '7:1e330ffb5135ecfd9464d380095165d9', 'createIndex indexName=prog_obj_est_fk_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-183', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 183, 'EXECUTED', '7:6f78deecd8539a7a358c57d8095aa08c', 'createIndex indexName=proy_obj_est_fk_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-184', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 184, 'EXECUTED', '7:2e9ce43e962c53ec498e70a3273cc321', 'createIndex indexName=proy_publica_proy_fk_idx, tableName=proy_publica_hist', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-185', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 185, 'EXECUTED', '7:9396842d056db9184e2d711e815f8684', 'createIndex indexName=proy_publica_usu_fk_idx, tableName=proy_publica_hist', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-186', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 186, 'EXECUTED', '7:7d15194876990ba0927232a5d6db05a2', 'createIndex indexName=siges_clean_adquisicion_adq_fuente_fk2_idx, tableName=adquisicion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-187', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 187, 'EXECUTED', '7:afb6891e4b9ae3f1586f615ee664c182', 'createIndex indexName=siges_clean_adquisicion_adq_moneda_fk3_idx, tableName=adquisicion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-188', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 188, 'EXECUTED', '7:47bcf3a32901f810c86a137fe05759c3', 'createIndex indexName=siges_clean_adquisicion_adq_pre_fk1_idx, tableName=adquisicion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-189', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 189, 'EXECUTED', '7:4e7f446397b21d324956b08e8d5af36d', 'createIndex indexName=siges_clean_adquisicion_adq_prov_orga_fk4_idx, tableName=adquisicion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-190', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 190, 'EXECUTED', '7:2f82fb3343537838f9a46e68231b6f2d', 'createIndex indexName=siges_clean_ambito_amb_org_fk1_idx, tableName=ambito', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-191', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 191, 'EXECUTED', '7:6c4af118f4b27c82f4a919e995a8755a', 'createIndex indexName=siges_clean_area_conocimiento_con_org_fk1_idx, tableName=area_conocimiento', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-192', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 192, 'EXECUTED', '7:551f936d28461ca181c0e9c70fd5cc99', 'createIndex indexName=siges_clean_area_conocimiento_con_padre_fk2_idx, tableName=area_conocimiento', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-193', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 193, 'EXECUTED', '7:fceae59a14ecfbbc3dc9f8317d83e11a', 'createIndex indexName=siges_clean_area_organi_int_prove_areaorgintprov_org_fk1_idx, tableName=area_organi_int_prove', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-194', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 194, 'EXECUTED', '7:c2387cccbfa55880808cb538d2af9336', 'createIndex indexName=siges_clean_area_organi_int_prove_areaorgintprov_orga_fk2_idx, tableName=area_organi_int_prove', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-195', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 195, 'EXECUTED', '7:1d39fd5ae8ddfd1ba475cfa09e1dfd50', 'createIndex indexName=siges_clean_areas_area_director3_idx, tableName=areas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-196', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 196, 'EXECUTED', '7:c83d0710715e78723e4a00bcef1f0dd5', 'createIndex indexName=siges_clean_areas_area_org_fk1_idx, tableName=areas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-197', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 197, 'EXECUTED', '7:66928b3ac1bc6b3df1e56f4d594f15aa', 'createIndex indexName=siges_clean_areas_area_padre2_idx, tableName=areas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-198', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 198, 'EXECUTED', '7:538fb41401d28b9071e6ff2046ab65a5', 'createIndex indexName=siges_clean_areas_tags_areatag_org_fk1_idx, tableName=areas_tags', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-199', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 199, 'EXECUTED', '7:7d9e3ac7e505f99664645084141c46e7', 'createIndex indexName=siges_clean_areas_tags_areatag_padre_fk2_idx, tableName=areas_tags', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-200', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 200, 'EXECUTED', '7:4f2d60b3849d4c8f69f3bd6237db8e8e', 'createIndex indexName=siges_clean_aud_pge_configuraciones_REV1_idx, tableName=aud_pge_configuraciones', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-201', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 201, 'EXECUTED', '7:08b8417d4575f792b5148045ce6cbabd', 'createIndex indexName=siges_clean_aud_programas_proyectos_REV1_idx, tableName=aud_programas_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-202', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 202, 'EXECUTED', '7:09df73b0027a4d53b03c14b127ffd93a', 'createIndex indexName=siges_clean_aud_ss_ayuda_REV1_idx, tableName=aud_ss_ayuda', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-203', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 203, 'EXECUTED', '7:c0231007f744572fe897b7f578e898ca', 'createIndex indexName=siges_clean_aud_ss_categoper_REV1_idx, tableName=aud_ss_categoper', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-204', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 204, 'EXECUTED', '7:fafad795d1b046052a3da60f4c4e0e46', 'createIndex indexName=siges_clean_aud_ss_configuraciones_REV1_idx, tableName=aud_ss_configuraciones', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-205', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 205, 'EXECUTED', '7:e794f0c6396ffe18decbe49d20b136b9', 'createIndex indexName=siges_clean_aud_ss_departamentos_REV1_idx, tableName=aud_ss_departamentos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-206', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 206, 'EXECUTED', '7:27c6c25512f3ec380077f5d30804a1ee', 'createIndex indexName=siges_clean_aud_ss_domicilios_REV1_idx, tableName=aud_ss_domicilios', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-207', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 207, 'EXECUTED', '7:bb989703ffd086e1c08fd2bf35d3f1b7', 'createIndex indexName=siges_clean_aud_ss_errores_REV1_idx, tableName=aud_ss_errores', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-208', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 208, 'EXECUTED', '7:fb93ada354a0d64fa3732f85786c7a66', 'createIndex indexName=siges_clean_aud_ss_localidades_REV1_idx, tableName=aud_ss_localidades', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-209', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 209, 'EXECUTED', '7:75ffdc6670d1ad9a9d1359f314ba6216', 'createIndex indexName=siges_clean_aud_ss_noticias_REV1_idx, tableName=aud_ss_noticias', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-210', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 210, 'EXECUTED', '7:e836dbb9df6edcf568dd327bd0067e2a', 'createIndex indexName=siges_clean_aud_ss_oficina_REV1_idx, tableName=aud_ss_oficina', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-211', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 211, 'EXECUTED', '7:aabca18d21f96dac605fac37b0bb9371', 'createIndex indexName=siges_clean_aud_ss_operacion_REV1_idx, tableName=aud_ss_operacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-212', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 212, 'EXECUTED', '7:78a2c625f792c22ccbbacca8d8612d34', 'createIndex indexName=siges_clean_aud_ss_paises_REV1_idx, tableName=aud_ss_paises', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-213', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 213, 'EXECUTED', '7:f807777792af8cb46a6fef943f1b3b87', 'createIndex indexName=siges_clean_aud_ss_paridades_REV1_idx, tableName=aud_ss_paridades', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-214', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 214, 'EXECUTED', '7:c412ca56c7fae2a0944c8017fa62668f', 'createIndex indexName=siges_clean_aud_ss_personas_REV1_idx, tableName=aud_ss_personas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-215', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 215, 'EXECUTED', '7:784deb323edb4ae2efb863623bbff01d', 'createIndex indexName=siges_clean_aud_ss_rel_rol_operacion_REV1_idx, tableName=aud_ss_rel_rol_operacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-216', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 216, 'EXECUTED', '7:0693935c46ffab61fd23fdeee1fa954e', 'createIndex indexName=siges_clean_aud_ss_rol_REV1_idx, tableName=aud_ss_rol', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-217', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 217, 'EXECUTED', '7:569005a419547cc77387d5710f183ca7', 'createIndex indexName=siges_clean_aud_ss_telefonos_REV1_idx, tableName=aud_ss_telefonos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-218', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 218, 'EXECUTED', '7:24f474fcff05e640362b560e3aa8e9be', 'createIndex indexName=siges_clean_aud_ss_tipos_documento_persona_REV1_idx, tableName=aud_ss_tipos_documento_persona', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-219', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 219, 'EXECUTED', '7:09b7c90e1a762494dc43e1b1070be7af', 'createIndex indexName=siges_clean_aud_ss_tipos_entrada_colectiva_REV1_idx, tableName=aud_ss_tipos_entrada_colectiva', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-220', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 220, 'EXECUTED', '7:ec45f4bb815cbc5dd923fea8ad478d5f', 'createIndex indexName=siges_clean_aud_ss_tipos_telefono_REV1_idx, tableName=aud_ss_tipos_telefono', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-221', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 221, 'EXECUTED', '7:5f23088a9e323abd000509d7eac7d00b', 'createIndex indexName=siges_clean_aud_ss_tipos_vialidad_REV1_idx, tableName=aud_ss_tipos_vialidad', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-222', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 222, 'EXECUTED', '7:05599085f062622d360b4b970e3b7779', 'createIndex indexName=siges_clean_aud_ss_usu_ofi_roles_REV1_idx, tableName=aud_ss_usu_ofi_roles', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-223', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 223, 'EXECUTED', '7:02b7b74e9a979a1b88632026bc3dad87', 'createIndex indexName=siges_clean_aud_ss_usuario_REV1_idx, tableName=aud_ss_usuario', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-224', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 224, 'EXECUTED', '7:7376e06c593431bfab6447a142fb7728', 'createIndex indexName=siges_clean_aud_ss_usuario_usu_area_org2_idx, tableName=aud_ss_usuario', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-225', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 225, 'EXECUTED', '7:8b7d2ae558a3805e02747121a6cc9230', 'createIndex indexName=siges_clean_busq_filtro_busq_filtro_org_fk2_idx, tableName=busq_filtro', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-226', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 226, 'EXECUTED', '7:b943ad4575e88e8a95d299a24ea2fa37', 'createIndex indexName=siges_clean_busq_filtro_busq_filtro_usu_fk1_idx, tableName=busq_filtro', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-227', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 227, 'EXECUTED', '7:343f21323a80747c9c595216c3549a23', 'createIndex indexName=siges_clean_calidad_cal_actualizacion7_idx, tableName=calidad', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-228', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 228, 'EXECUTED', '7:f8c848e9100078c6f9c8402109382ff1', 'createIndex indexName=siges_clean_calidad_cal_ent_fk1_idx, tableName=calidad', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-229', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 229, 'EXECUTED', '7:6399c2d7c1503f2262b032d2afed7d20', 'createIndex indexName=siges_clean_calidad_cal_prod_fk2_idx, tableName=calidad', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-230', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 230, 'EXECUTED', '7:aaf0cc1445b97d8520f13da903493c42', 'createIndex indexName=siges_clean_calidad_cal_proy_fk5_idx, tableName=calidad', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-231', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 231, 'EXECUTED', '7:c64e47549bced7a38c46904e3b366621', 'createIndex indexName=siges_clean_calidad_cal_tca_fk3_idx, tableName=calidad', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-232', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 232, 'EXECUTED', '7:85008d752905eb3ed261285841f9bd96', 'createIndex indexName=siges_clean_calidad_cal_tipo6_idx, tableName=calidad', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-233', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 233, 'EXECUTED', '7:6fd1e1046f9a0421ebd69be83eea14a7', 'createIndex indexName=siges_clean_calidad_cal_vca_fk4_idx, tableName=calidad', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-234', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 234, 'EXECUTED', '7:bab0db85a9cc4b9d9eab8fe38cf1c2a2', 'createIndex indexName=siges_clean_categoria_proyectos_cat_proy_activo1_idx, tableName=categoria_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-235', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 235, 'EXECUTED', '7:bf67892d1d830e79d670cd34b0f83e8f', 'createIndex indexName=siges_clean_devengado_dev_adq_fk1_idx, tableName=devengado', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-236', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 236, 'EXECUTED', '7:3e73e493523704b17245001e70f47dd6', 'createIndex indexName=siges_clean_devengado_dev_anio3_idx, tableName=devengado', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-237', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 237, 'EXECUTED', '7:97c505de79682f008fca9bcf5ea647ea', 'createIndex indexName=siges_clean_devengado_dev_mes2_idx, tableName=devengado', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-238', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 238, 'EXECUTED', '7:4c573a7b3f26288822e4632bf0952c7e', 'createIndex indexName=siges_clean_doc_file_docfile_doc_fk1_idx, tableName=doc_file', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-239', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 239, 'EXECUTED', '7:c2325fd500b79b8ee72cc1498d42eb00', 'createIndex indexName=siges_clean_documentos_docs_entregable_fk1_idx, tableName=documentos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-240', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 240, 'EXECUTED', '7:c82d1fd9affa2a80e3fc5b5a9e25beec', 'createIndex indexName=siges_clean_documentos_docs_pago_fk3_idx, tableName=documentos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-241', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 241, 'EXECUTED', '7:2d1e71e745f118f260bb55183eeffd15', 'createIndex indexName=siges_clean_documentos_docs_tipodoc_fk2_idx, tableName=documentos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-242', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 242, 'EXECUTED', '7:357c117083fa2fd183d33ceb666c9cc0', 'createIndex indexName=siges_clean_ent_hist_linea_base_enthist_ent_fk1_idx, tableName=ent_hist_linea_base', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-243', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 243, 'EXECUTED', '7:8cc7ec5fb31a2e1fd0b8c3b760c76035', 'createIndex indexName=siges_clean_ent_hist_linea_base_enthist_replan_fk2_idx, tableName=ent_hist_linea_base', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-244', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 244, 'EXECUTED', '7:e7e948704208eae490137603b796ee76', 'createIndex indexName=siges_clean_entregables_ent_coord_usu_fk2_idx, tableName=entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-245', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 245, 'EXECUTED', '7:159a4e33b8a9328d32129d059de06c71', 'createIndex indexName=siges_clean_entregables_ent_cro_fk1_idx, tableName=entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-246', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 246, 'EXECUTED', '7:a814635e13744677d466388f8705a9f2', 'createIndex indexName=siges_clean_entregables_ent_fin4_idx, tableName=entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-247', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 247, 'EXECUTED', '7:e244c15c9e27cb5185d7f3c964154bc7', 'createIndex indexName=siges_clean_entregables_ent_inicio3_idx, tableName=entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-248', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 248, 'EXECUTED', '7:05349bc2580d3861ef7b6368c63ca6ee', 'createIndex indexName=siges_clean_entregables_ent_progreso5_idx, tableName=entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-249', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 249, 'EXECUTED', '7:21e25a99b1affb0486cd9d17dd50ae26', 'createIndex indexName=siges_clean_estados_est_codigo1_idx, tableName=estados', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-250', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 250, 'EXECUTED', '7:7dbda22e2640676fe51bfc20d44d9e7d', 'createIndex indexName=siges_clean_etapa_eta_org_fk1_idx, tableName=etapa', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-251', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 251, 'EXECUTED', '7:1346bfea76db8467e9e03d8098c1c9ae', 'createIndex indexName=siges_clean_fuente_financiamiento_fue_org_fk1_idx, tableName=fuente_financiamiento', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-252', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 252, 'EXECUTED', '7:ccddfb7bd32be35b381be792e94ce125', 'createIndex indexName=siges_clean_gastos_gas_aprobado6_idx, tableName=gastos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-253', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 253, 'EXECUTED', '7:c4fca9b3bb03bf5502495a5ec9a1a8c2', 'createIndex indexName=siges_clean_gastos_gas_fecha5_idx, tableName=gastos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-254', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 254, 'EXECUTED', '7:ac76fb8e05c67a55feb6cffe1063bb0f', 'createIndex indexName=siges_clean_gastos_gas_mon_fk4_idx, tableName=gastos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-255', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 255, 'EXECUTED', '7:abce0b0fb38a2519f7582a152b012616', 'createIndex indexName=siges_clean_gastos_gas_proy_fk1_idx, tableName=gastos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-256', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 256, 'EXECUTED', '7:f4269a45b6220588aaf98fd86d4ede39', 'createIndex indexName=siges_clean_gastos_gas_tipo_fk2_idx, tableName=gastos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-257', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 257, 'EXECUTED', '7:8e4de6408809fe0d33b095b4832fcc8e', 'createIndex indexName=siges_clean_gastos_gas_usu_fk3_idx, tableName=gastos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-258', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 258, 'EXECUTED', '7:1f99564070a28592c02dad2bc8062e85', 'createIndex indexName=siges_clean_interesados_int_ent_fk4_idx, tableName=interesados', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-259', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 259, 'EXECUTED', '7:779116807aa7e48da37c15c1e1fe99fc', 'createIndex indexName=siges_clean_interesados_int_orga_fk3_idx, tableName=interesados', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-260', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 260, 'EXECUTED', '7:b0c158023e022668118fb5c62f28f847', 'createIndex indexName=siges_clean_interesados_int_pers_fk2_idx, tableName=interesados', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-261', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 261, 'EXECUTED', '7:f2826d74b2d614355a96986b9f82a2a0', 'createIndex indexName=siges_clean_interesados_int_rolint_fk1_idx, tableName=interesados', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-262', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 262, 'EXECUTED', '7:a3c5647e91fb64e74fdddfc83879930b', 'createIndex indexName=siges_clean_latlng_proyectos_latlang_dep_fk2_idx, tableName=latlng_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-263', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 263, 'EXECUTED', '7:c5f0de397f48cc9d4cb38eab1515543d', 'createIndex indexName=siges_clean_latlng_proyectos_latlng_proy_fk1_idx, tableName=latlng_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-264', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 264, 'EXECUTED', '7:f48f1142d194859fa0ab52c54086d341', 'createIndex indexName=siges_clean_lecapr_areacon_lecaprcon_lecapr_fk1_idx, tableName=lecapr_areacon', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-265', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 265, 'EXECUTED', '7:0678676a9891846892c0d3efde5b5d1a', 'createIndex indexName=siges_clean_lecc_aprendidas_lecapr_activo6_idx, tableName=lecc_aprendidas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-266', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 266, 'EXECUTED', '7:6a24ae10538d4f96fd951de27d3654c1', 'createIndex indexName=siges_clean_lecc_aprendidas_lecapr_fecha5_idx, tableName=lecc_aprendidas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-267', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 267, 'EXECUTED', '7:c536714fdf83e07ff59d6fbe00e5ac0d', 'createIndex indexName=siges_clean_lecc_aprendidas_lecapr_org_fk4_idx, tableName=lecc_aprendidas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-268', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 268, 'EXECUTED', '7:a64b2c39e04ffd4f761c5275e7aeb6ce', 'createIndex indexName=siges_clean_lecc_aprendidas_lecapr_proy_fk1_idx, tableName=lecc_aprendidas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-269', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 269, 'EXECUTED', '7:5f91ee6507ebc53ef111e98d3b694837', 'createIndex indexName=siges_clean_lecc_aprendidas_lecapr_tipo_fk2_idx, tableName=lecc_aprendidas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-270', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 270, 'EXECUTED', '7:3384e5965e5d66527d4dac659e7b5ad6', 'createIndex indexName=siges_clean_lecc_aprendidas_lecapr_usr_fk3_idx, tableName=lecc_aprendidas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-271', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 271, 'EXECUTED', '7:be5dec068593f9085e6aea5edef1fae4', 'createIndex indexName=siges_clean_lineabase_historico_lineabase_entFk1_idx, tableName=lineabase_historico', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-272', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 272, 'EXECUTED', '7:5a05a355e7d3ef9396c07d748f143ecf', 'createIndex indexName=siges_clean_lineabase_historico_lineabase_entFk2_idx, tableName=lineabase_historico', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-273', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 273, 'EXECUTED', '7:0204b783087485b6de15420a406d4570', 'createIndex indexName=siges_clean_mails_template_mail_tmp_org_fk1_idx, tableName=mails_template', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-274', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 274, 'EXECUTED', '7:db26b4ace922fbee5509b7b838745eb0', 'createIndex indexName=siges_clean_media_proyectos_media_proy_fk1_idx, tableName=media_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-275', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 275, 'EXECUTED', '7:cde807b2d231c6ce80d8a33c63ee6d58', 'createIndex indexName=siges_clean_media_proyectos_media_tipo_fk2_idx, tableName=media_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-276', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 276, 'EXECUTED', '7:1569da33ea09b875e454b452373bc5f5', 'createIndex indexName=siges_clean_media_proyectos_media_usr_mod_fk4_idx, tableName=media_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-277', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 277, 'EXECUTED', '7:5bcd0593e7fe13aaafb933f97d8a111c', 'createIndex indexName=siges_clean_media_proyectos_media_usr_pub_fk3_idx, tableName=media_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-278', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 278, 'EXECUTED', '7:ec8a3a0b0899a6535fc8de8b1e92529e', 'createIndex indexName=siges_clean_media_proyectos_media_usr_rech_fk5_idx, tableName=media_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-279', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 279, 'EXECUTED', '7:829dfb9809d321d39a76c41ac08298c8', 'createIndex indexName=siges_clean_notificacion_envio_notenv_fecha1_idx, tableName=notificacion_envio', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-280', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 280, 'EXECUTED', '7:45a8751b7acc306330399689edbd7fd0', 'createIndex indexName=siges_clean_notificacion_instancia_notinst_not_fk1_idx, tableName=notificacion_instancia', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-281', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 281, 'EXECUTED', '7:735955720e1c6a7a6bcc5a7fb121552a', 'createIndex indexName=siges_clean_notificacion_instancia_notinst_proy_fk2_idx, tableName=notificacion_instancia', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-282', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 282, 'EXECUTED', '7:d6b38a31ada8dcee0254d7f75876805f', 'createIndex indexName=siges_clean_organi_int_prove_orga_amb_fk2_idx, tableName=organi_int_prove', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-283', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 283, 'EXECUTED', '7:2b63681197db2e6c71eb11d198f7768d', 'createIndex indexName=siges_clean_organi_int_prove_orga_org_fk1_idx, tableName=organi_int_prove', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-284', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 284, 'EXECUTED', '7:1e909356e713b9acc1bfd02ead74daff', 'createIndex indexName=siges_clean_organi_int_prove_orga_proveedor3_idx, tableName=organi_int_prove', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-285', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 285, 'EXECUTED', '7:7ab66efb0152db9be064d448d3e30d0f', 'createIndex indexName=siges_clean_pagos_pag_adq_fk2_idx, tableName=pagos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-286', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 286, 'EXECUTED', '7:9507da1dd178dfb16c37710dd188fd7a', 'createIndex indexName=siges_clean_pagos_pag_confirmar5_idx, tableName=pagos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-287', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 287, 'EXECUTED', '7:6fee456451721103d21a6ffeaa56a063', 'createIndex indexName=siges_clean_pagos_pag_ent_fk1_idx, tableName=pagos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-288', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 288, 'EXECUTED', '7:fb7dd4dad902cb3aca403740b0183035', 'createIndex indexName=siges_clean_pagos_pag_fecha_planificada3_idx, tableName=pagos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-289', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 289, 'EXECUTED', '7:db03e97fff63f3cda5699b258fbf4d11', 'createIndex indexName=siges_clean_pagos_pag_fecha_real4_idx, tableName=pagos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-290', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 290, 'EXECUTED', '7:fdbc2b1a00c6146f3b067de2effc7e7a', 'createIndex indexName=siges_clean_participantes_part_activo4_idx, tableName=participantes', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-291', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 291, 'EXECUTED', '7:0fdd89a76599920c08980540b946a0e6', 'createIndex indexName=siges_clean_participantes_part_ent_fk3_idx, tableName=participantes', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-292', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 292, 'EXECUTED', '7:7d8eef09d65967a5c1c50558979e65cf', 'createIndex indexName=siges_clean_participantes_part_proy_fk2_idx, tableName=participantes', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-293', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 293, 'EXECUTED', '7:1f912b4822bfdffbd3e9ff68fe75ed4a', 'createIndex indexName=siges_clean_participantes_part_usu_fk1_idx, tableName=participantes', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-294', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 294, 'EXECUTED', '7:40e65fc1763a60b321dd0cd1178cb476', 'createIndex indexName=siges_clean_personas_pers_orga_fk2_idx, tableName=personas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-295', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 295, 'EXECUTED', '7:6980577e2fe2bcc7421b4478070171f2', 'createIndex indexName=siges_clean_personas_pers_rol_fk1_idx, tableName=personas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-296', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 296, 'EXECUTED', '7:c76ee105800ccca813136941af10bff9', 'createIndex indexName=siges_clean_plantilla_cronograma_p_crono_org_fk1_idx, tableName=plantilla_cronograma', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-297', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 297, 'EXECUTED', '7:94c13fb606afa4a83258f6dc58bd8636', 'createIndex indexName=siges_clean_plantilla_entregables_p_entregable_ant_fk2_idx, tableName=plantilla_entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-298', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 298, 'EXECUTED', '7:c70af0e73d3744ace7658332ada8412c', 'createIndex indexName=siges_clean_plantilla_entregables_p_entregable_p_cro_fk1_idx, tableName=plantilla_entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-299', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 299, 'EXECUTED', '7:b525ae3043940956190686ccd06edbde', 'createIndex indexName=siges_clean_presupuesto_pre_fuente_organi_fk1_idx, tableName=presupuesto', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-300', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 300, 'EXECUTED', '7:7f2e9507b6f92dead68f70e9e83ab738', 'createIndex indexName=siges_clean_presupuesto_pre_moneda2_idx, tableName=presupuesto', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-301', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 301, 'EXECUTED', '7:9d6dc697761b45f415ea8c5f0e5b0e7e', 'createIndex indexName=siges_clean_prod_mes_prodmes_anio3_idx, tableName=prod_mes', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-302', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 302, 'EXECUTED', '7:154d4745f7e2db458ec82f415a6e90ca', 'createIndex indexName=siges_clean_prod_mes_prodmes_mes2_idx, tableName=prod_mes', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-303', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 303, 'EXECUTED', '7:162616117fa56b1cefe8ba29acae9157', 'createIndex indexName=siges_clean_prod_mes_prodmes_prod_fk1_idx, tableName=prod_mes', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-304', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 304, 'EXECUTED', '7:5b33d8376f89f413fcbbe4642204f02f', 'createIndex indexName=siges_clean_productos_prod_ent_fk1_idx, tableName=productos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-305', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 305, 'EXECUTED', '7:6e3dced689354f4c374200b34d70691d', 'createIndex indexName=siges_clean_prog_docs_obl_progdocsobl_prog_pk1_idx, tableName=prog_docs_obl', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-306', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 306, 'EXECUTED', '7:7c459ba684eb5f7878d1d273bbfb9b3c', 'createIndex indexName=siges_clean_prog_docs_progdocs_doc_pk1_idx, tableName=prog_docs', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-307', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 307, 'EXECUTED', '7:bfe23a00ac99f9d611ce399b5b650153', 'createIndex indexName=siges_clean_prog_docs_progdocs_prog_pk2_idx, tableName=prog_docs', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-308', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 308, 'EXECUTED', '7:eb2ed15e4f6cf7ef3e7f42d8c4d2bfa3', 'createIndex indexName=siges_clean_prog_indices_pre_progindpre_progind_fk1_idx, tableName=prog_indices_pre', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-309', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 309, 'EXECUTED', '7:cf89272ec7038591580152e80065d116', 'createIndex indexName=siges_clean_prog_int_progint_int_pk1_idx, tableName=prog_int', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-310', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 310, 'EXECUTED', '7:e5cb111fe19f7b5cf8515bb707864558', 'createIndex indexName=siges_clean_prog_lectura_area_proglectarea_area_pk1_idx, tableName=prog_lectura_area', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-311', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 311, 'EXECUTED', '7:0793b43038f6154c9936d0efd39148ad', 'createIndex indexName=siges_clean_prog_pre_progpre_pre_fk1_idx, tableName=prog_pre', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-312', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 312, 'EXECUTED', '7:7eedbc271f867880da7c66bb24d3395b', 'createIndex indexName=siges_clean_prog_tags_progtag_area_pk1_idx, tableName=prog_tags', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-313', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 313, 'EXECUTED', '7:b3a0bcd94303038231e13df67493a6c0', 'createIndex indexName=siges_clean_programas_prog_activo15_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-314', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 314, 'EXECUTED', '7:2e418821ef84766240e48ab25b0922e3', 'createIndex indexName=siges_clean_programas_prog_area_fk1_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-315', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 315, 'EXECUTED', '7:d5675bf12b338642e8dce03147d0b3bd', 'createIndex indexName=siges_clean_programas_prog_cro_fk9_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-316', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 316, 'EXECUTED', '7:05090baf05bd45d86506a1ea5352dc32', 'createIndex indexName=siges_clean_programas_prog_est_fk3_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-317', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 317, 'EXECUTED', '7:730ef32145196f2966f950eca2257609', 'createIndex indexName=siges_clean_programas_prog_est_pendiente_fk12_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-318', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 318, 'EXECUTED', '7:18f95e5bd62f0de934a0b8e7a3734810', 'createIndex indexName=siges_clean_programas_prog_fecha_act17_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-319', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 319, 'EXECUTED', '7:e99de88dd1c747454740c30c0574e477', 'createIndex indexName=siges_clean_programas_prog_fecha_crea16_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-320', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 320, 'EXECUTED', '7:82ef3e16d0b4b4d52bdb94156cfb140c', 'createIndex indexName=siges_clean_programas_prog_nombre13_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-321', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 321, 'EXECUTED', '7:2740a7f41491bf588469a401d467cb94', 'createIndex indexName=siges_clean_programas_prog_org_fk2_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-322', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 322, 'EXECUTED', '7:ad1644f5db80fd9752d9ab3b8a02a329', 'createIndex indexName=siges_clean_programas_prog_pre_fk14_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-323', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 323, 'EXECUTED', '7:964323cf560e437c8b068dc7415a5764', 'createIndex indexName=siges_clean_programas_prog_progindices_fk11_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-324', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 324, 'EXECUTED', '7:9842adda6f6fd37db6d24c09a8886fd0', 'createIndex indexName=siges_clean_programas_prog_usr_adjunto_fk7_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-325', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 325, 'EXECUTED', '7:933d0744d4d89ad99d74a257fcedc7d4', 'createIndex indexName=siges_clean_programas_prog_usr_gerente_fk4_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-326', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 326, 'EXECUTED', '7:01ef05ec3eb9d0bdcf8e8051e610a503', 'createIndex indexName=siges_clean_programas_prog_usr_pmofed_fk6_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-327', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 327, 'EXECUTED', '7:7c18a97b8fc3a6dda2626a5650cbd7a9', 'createIndex indexName=siges_clean_programas_prog_usr_sponsor_fk5_idx, tableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-328', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 328, 'EXECUTED', '7:e5930fb22bd2abdf70b7139b841abd52', 'createIndex indexName=siges_clean_proy_docs_proydoc_doc_pk1_idx, tableName=proy_docs', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-329', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 329, 'EXECUTED', '7:7fa527645d9af2a27de19705016fc6d1', 'createIndex indexName=siges_clean_proy_docs_proydoc_proy_pk2_idx, tableName=proy_docs', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-330', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 330, 'EXECUTED', '7:41554512a92a7513020cabb45525f64a', 'createIndex indexName=siges_clean_proy_indices_proyind_periodo_fin2_idx, tableName=proy_indices', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-331', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 331, 'EXECUTED', '7:0c8ecf78442c6db7285e9abdc74ab59c', 'createIndex indexName=siges_clean_proy_indices_proyind_periodo_inicio1_idx, tableName=proy_indices', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-332', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 332, 'EXECUTED', '7:9fed5a2b29b6b9f754aa10dcfe007fe0', 'createIndex indexName=siges_clean_proy_int_proyint_int_pk1_idx, tableName=proy_int', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-333', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 333, 'EXECUTED', '7:b20f87c2890586442da09861c4854e0d', 'createIndex indexName=siges_clean_proy_lectura_area_proglectarea_area_pk1_idx, tableName=proy_lectura_area', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-334', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 334, 'EXECUTED', '7:e8035efcf2c77fe2b3f269460a4a8f11', 'createIndex indexName=siges_clean_proy_lectura_area_proglectarea_proy_pk2_idx, tableName=proy_lectura_area', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-335', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 335, 'EXECUTED', '7:48a20c4e3819679bb33bf71aecac48a8', 'createIndex indexName=siges_clean_proy_otros_cat_secundarias_proy_cat_cat_proy_fk1_id, tableName=proy_otros_cat_secundarias', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-336', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 336, 'EXECUTED', '7:ba13a4e259edc34a12e1119ec1b31cab', 'createIndex indexName=siges_clean_proy_otros_datos_proy_otr_cat_fk4_idx, tableName=proy_otros_datos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-337', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 337, 'EXECUTED', '7:fce66ea14c1999b27d875072eea2aaa8', 'createIndex indexName=siges_clean_proy_otros_datos_proy_otr_cont_fk1_idx, tableName=proy_otros_datos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-338', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 338, 'EXECUTED', '7:12b14b0f9530ffeef44640b896a4e1cc', 'createIndex indexName=siges_clean_proy_otros_datos_proy_otr_ent_fk3_idx, tableName=proy_otros_datos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-339', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 339, 'EXECUTED', '7:791300b5485a09a408ba14863a767023', 'createIndex indexName=siges_clean_proy_otros_datos_proy_otr_est_pub_fk5_idx, tableName=proy_otros_datos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-340', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 340, 'EXECUTED', '7:60e5af6518a5131a430824531d0cbeca', 'createIndex indexName=siges_clean_proy_otros_datos_proy_otr_eta_fk6_idx, tableName=proy_otros_datos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-341', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 341, 'EXECUTED', '7:7e47afa55036cfe90c7387f538b73929', 'createIndex indexName=siges_clean_proy_otros_datos_proy_otr_ins_eje_fk2_idx, tableName=proy_otros_datos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-342', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 342, 'EXECUTED', '7:05d81ed4a3aad67a535d6524316b342e', 'createIndex indexName=siges_clean_proy_pre_proypre_pre_fk1_idx, tableName=proy_pre', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-343', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 343, 'EXECUTED', '7:91ac57f4932d86a6114c77e7fb8dd480', 'createIndex indexName=siges_clean_proy_replanificacion_proyreplan_activo3_idx, tableName=proy_replanificacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-344', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 344, 'EXECUTED', '7:c4b3272a79b7809e9d08d76616117814', 'createIndex indexName=siges_clean_proy_replanificacion_proyreplan_fecha2_idx, tableName=proy_replanificacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-345', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 345, 'EXECUTED', '7:65f5025827735412c9efec0fbc0b933f', 'createIndex indexName=siges_clean_proy_replanificacion_proyreplan_proy_fk1_idx, tableName=proy_replanificacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-346', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 346, 'EXECUTED', '7:6ae0bc8a263bf70d1f95a0277191ca8f', 'createIndex indexName=siges_clean_proy_sitact_historico_proy_sitact_proy_fk1_idx, tableName=proy_sitact_historico', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-347', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 347, 'EXECUTED', '7:87295643653bd59beeb72550bd75a38e', 'createIndex indexName=siges_clean_proy_sitact_historico_proy_sitact_usu_fk2_idx, tableName=proy_sitact_historico', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-348', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 348, 'EXECUTED', '7:9413666dac8b270b7d91c98e43602563', 'createIndex indexName=siges_clean_proy_tags_proytag_area_pk1_idx, tableName=proy_tags', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-349', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 349, 'EXECUTED', '7:4a30f087467eb92a4dffe3e88fb95ff8', 'createIndex indexName=siges_clean_proyectos_proy_activo17_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-350', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 350, 'EXECUTED', '7:5bc97d8499f18fc92fe9b0f1ac6a358d', 'createIndex indexName=siges_clean_proyectos_proy_area_fk5_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-351', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 351, 'EXECUTED', '7:164da34d1e4d7012aaa6c6b8330fabec', 'createIndex indexName=siges_clean_proyectos_proy_cro_fk1_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-352', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 352, 'EXECUTED', '7:44b2bd783846a503b69def02e28c0790', 'createIndex indexName=siges_clean_proyectos_proy_est_fk3_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-353', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 353, 'EXECUTED', '7:ca3e14f4929be8cd63f2939543cd3169', 'createIndex indexName=siges_clean_proyectos_proy_est_pendiente_fk15_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-354', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 354, 'EXECUTED', '7:e186ecc81d93f2100ba1f46e533284e9', 'createIndex indexName=siges_clean_proyectos_proy_fecha_act19_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-355', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 355, 'EXECUTED', '7:f83d87e234668061d76acf7267a7e558', 'createIndex indexName=siges_clean_proyectos_proy_fecha_crea18_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-356', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 356, 'EXECUTED', '7:37b1a08f76a47eb540dd877cb43d8768', 'createIndex indexName=siges_clean_proyectos_proy_latlng_fk22_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-357', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 357, 'EXECUTED', '7:fc531474568442d4fea8588011174b05', 'createIndex indexName=siges_clean_proyectos_proy_nombre13_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-358', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 358, 'EXECUTED', '7:83adf2d65450c72a521cd300c8c30862', 'createIndex indexName=siges_clean_proyectos_proy_org_fk4_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-359', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 359, 'EXECUTED', '7:29c1a2d566b5d98b954253e14bed3169', 'createIndex indexName=siges_clean_proyectos_proy_otr_dat_fk21_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-360', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 360, 'EXECUTED', '7:49b50db9392a4424692a249b435aa068', 'createIndex indexName=siges_clean_proyectos_proy_pre_fk12_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-361', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 361, 'EXECUTED', '7:b84148d75b36b9a1ce670484858b998f', 'createIndex indexName=siges_clean_proyectos_proy_prog_fk6_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-362', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 362, 'EXECUTED', '7:616bd178974af726aff92f653b0cf58d', 'createIndex indexName=siges_clean_proyectos_proy_proyindices_fk14_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-363', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 363, 'EXECUTED', '7:d7a5b0fb1904f713eeae048f6e5cfdcc', 'createIndex indexName=siges_clean_proyectos_proy_publicable20_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-364', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 364, 'EXECUTED', '7:06d567a70655ffcd4b46c948cdd6e0e4', 'createIndex indexName=siges_clean_proyectos_proy_risk_fk2_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-365', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 365, 'EXECUTED', '7:7252c1b48056092d0d012515d9eb2c2e', 'createIndex indexName=siges_clean_proyectos_proy_usr_adjunto_fk11_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-366', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 366, 'EXECUTED', '7:5660f639a851d7549635ebac8cda4195', 'createIndex indexName=siges_clean_proyectos_proy_usr_gerente_fk16_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-367', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 367, 'EXECUTED', '7:00436c0ab5e9a1f58b7247333e9bb1c6', 'createIndex indexName=siges_clean_proyectos_proy_usr_pmofed_fk7_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-368', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 368, 'EXECUTED', '7:549314bb5a6c59b07fec83b4c116a672', 'createIndex indexName=siges_clean_proyectos_proy_usr_sponsor_fk10_idx, tableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-369', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 369, 'EXECUTED', '7:1b9e8ad484ba5c0451415b717473ac41', 'createIndex indexName=siges_clean_registros_horas_rh_aprobado5_idx, tableName=registros_horas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-370', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 370, 'EXECUTED', '7:94c435292c95057290f0f71c9cdf22b5', 'createIndex indexName=siges_clean_registros_horas_rh_ent_fk3_idx, tableName=registros_horas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-371', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 371, 'EXECUTED', '7:16b3135909a0a3a010f2d6fa82f15387', 'createIndex indexName=siges_clean_registros_horas_rh_fecha4_idx, tableName=registros_horas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-372', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 372, 'EXECUTED', '7:01ba7e6e63470409ebe72c0d3a180858', 'createIndex indexName=siges_clean_registros_horas_rh_proy_fk2_idx, tableName=registros_horas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-373', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 373, 'EXECUTED', '7:641dc05c6b91e1d870e1dd60985a5506', 'createIndex indexName=siges_clean_registros_horas_rh_usu_fk1_idx, tableName=registros_horas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-374', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 374, 'EXECUTED', '7:7f0888b58241f7ae1754a76829a6e7ad', 'createIndex indexName=siges_clean_riesgos_risk_ent_fk5_idx, tableName=riesgos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-375', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 375, 'EXECUTED', '7:9f97773318dca91754f3770d213d07bb', 'createIndex indexName=siges_clean_riesgos_risk_fecha_actu6_idx, tableName=riesgos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-376', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 376, 'EXECUTED', '7:bff2e8d15947459213baadd75bc6b33e', 'createIndex indexName=siges_clean_riesgos_risk_fecha_limite7_idx, tableName=riesgos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-377', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 377, 'EXECUTED', '7:aa5053551e35c147677a7c1a2b7947b5', 'createIndex indexName=siges_clean_riesgos_risk_fecha_superado8_idx, tableName=riesgos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-378', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 378, 'EXECUTED', '7:6d3ddaaf925d85466a09200dd9cc7d94', 'createIndex indexName=siges_clean_riesgos_risk_proy_fk4_idx, tableName=riesgos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-379', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 379, 'EXECUTED', '7:e9e4ed5b7a3a30f6afbfa8e33d09214b', 'createIndex indexName=siges_clean_riesgos_risk_superado3_idx, tableName=riesgos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-380', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 380, 'EXECUTED', '7:fa433b4e87dd3ccca5626a7d77230070', 'createIndex indexName=siges_clean_riesgos_risk_usuario_superado_fk2_idx, tableName=riesgos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-381', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 381, 'EXECUTED', '7:01ffecc9ed9266a4e6539c1f9010c099', 'createIndex indexName=siges_clean_roles_interesados_rolint_org_fk1_idx, tableName=roles_interesados', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-382', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 382, 'EXECUTED', '7:1d49fc386b0597f9454ba273ac685c10', 'createIndex indexName=siges_clean_ss_ayuda_ayu_codigo1_idx, tableName=ss_ayuda', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-383', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 383, 'EXECUTED', '7:df22b5069cd7c91b1b3dc128003164c9', 'createIndex indexName=siges_clean_ss_configuraciones_cnf_codigo2_idx, tableName=ss_configuraciones', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-384', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 384, 'EXECUTED', '7:870a79cb170cf6fe60883b2203ac971d', 'createIndex indexName=siges_clean_ss_configuraciones_cnf_org_fk1_idx, tableName=ss_configuraciones', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-385', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 385, 'EXECUTED', '7:971a25210df32f604973b30764dc5ea4', 'createIndex indexName=siges_clean_ss_domicilios_dom_depto_id6_idx, tableName=ss_domicilios', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-386', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 386, 'EXECUTED', '7:dae1c76dc944ea9ce36d54fe9ec612c7', 'createIndex indexName=siges_clean_ss_domicilios_dom_loc_id5_idx, tableName=ss_domicilios', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-387', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 387, 'EXECUTED', '7:24ffb1642101d656daf89d069544276f', 'createIndex indexName=siges_clean_ss_domicilios_dom_pai_id2_idx, tableName=ss_domicilios', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-388', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 388, 'EXECUTED', '7:d2847cf4ced5107f24ca220d268ab614', 'createIndex indexName=siges_clean_ss_domicilios_dom_par_id3_idx, tableName=ss_domicilios', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-389', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 389, 'EXECUTED', '7:babbc22a11eb6738abb77cb4492f9ca3', 'createIndex indexName=siges_clean_ss_domicilios_dom_tec_id1_idx, tableName=ss_domicilios', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-390', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 390, 'EXECUTED', '7:43a8e80b8a8ad858195e232184b308f0', 'createIndex indexName=siges_clean_ss_domicilios_dom_tvi_id4_idx, tableName=ss_domicilios', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-391', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 391, 'EXECUTED', '7:1754bcc8b8c2ca817fd40d2d4176dbf6', 'createIndex indexName=siges_clean_ss_localidades_loc_depto_id1_idx, tableName=ss_localidades', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-392', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 392, 'EXECUTED', '7:27133505c3ea822ea9fc32a31d89cb34', 'createIndex indexName=siges_clean_ss_noticias_not_codigo1_idx, tableName=ss_noticias', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-393', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 393, 'EXECUTED', '7:830441b71b32394db054b78fb71c7f21', 'createIndex indexName=siges_clean_ss_operacion_ope_categoria_id1_idx, tableName=ss_operacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-394', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 394, 'EXECUTED', '7:f097649aeca17b4850ce56e5ea634028', 'createIndex indexName=siges_clean_ss_personas_per_pais_doc1_idx, tableName=ss_personas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-395', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 395, 'EXECUTED', '7:606c1ed76555c3a08a87543e9a171bb2', 'createIndex indexName=siges_clean_ss_personas_per_tipo_doc2_idx, tableName=ss_personas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-396', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 396, 'EXECUTED', '7:646cf0c2fec5f03978ac201e1bf2c83f', 'createIndex indexName=siges_clean_ss_rel_rol_operacion_rel_rol_operacion_operacion_id, tableName=ss_rel_rol_operacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-397', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 397, 'EXECUTED', '7:67a9ddc2c06838ee1acdc854e1f19279', 'createIndex indexName=siges_clean_ss_rel_rol_operacion_rel_rol_operacion_rol_id2_idx, tableName=ss_rel_rol_operacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-398', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 398, 'EXECUTED', '7:9360d2f50ea1234c09d1ff019e278ecc', 'createIndex indexName=siges_clean_ss_telefonos_tel_tiptel_id1_idx, tableName=ss_telefonos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-399', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 399, 'EXECUTED', '7:235fd5040f5a97c13ed3856f59487a55', 'createIndex indexName=siges_clean_ss_usu_ofi_roles_usu_ofi_roles_rol1_idx, tableName=ss_usu_ofi_roles', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-400', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 400, 'EXECUTED', '7:db8b491789b9dbcd067e9d5ecb1dcee3', 'createIndex indexName=siges_clean_ss_usu_ofi_roles_usu_ofi_roles_usu_area3_idx, tableName=ss_usu_ofi_roles', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-401', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 401, 'EXECUTED', '7:b46aa736441831dbdf460990dadba626', 'createIndex indexName=siges_clean_ss_usu_ofi_roles_usu_ofi_roles_usuario2_idx, tableName=ss_usu_ofi_roles', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-402', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 402, 'EXECUTED', '7:bab5c67f9f7dedfff6bfd54d5c69df37', 'createIndex indexName=siges_clean_ss_usuario_usu_area_org2_idx, tableName=ss_usuario', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-403', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 403, 'EXECUTED', '7:f1cd49ce17a1c57283fefd0a7243bd2d', 'createIndex indexName=siges_clean_ss_usuario_usu_cod1_idx, tableName=ss_usuario', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-404', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 404, 'EXECUTED', '7:62d4803340b529e03d4961c606e7be7f', 'createIndex indexName=siges_clean_temas_calidad_tca_org_fk1_idx, tableName=temas_calidad', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-405', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 405, 'EXECUTED', '7:e74ee2d4fc43775b1c6c33a3abc16e88', 'createIndex indexName=siges_clean_tipo_documento_instancia_tipodoc_inst_tipodoc_fk1_i, tableName=tipo_documento_instancia', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-406', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 406, 'EXECUTED', '7:3e0123b71a904d57a1ee8883875231fd', 'createIndex indexName=siges_clean_tipo_documento_tipodoc_org_fk1_idx, tableName=tipo_documento', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-407', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 407, 'EXECUTED', '7:2161cfc2f242f4e90b77e9da2b8e3bcc', 'createIndex indexName=siges_clean_tipo_gasto_tipogas_org_fk1_idx, tableName=tipo_gasto', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-408', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 408, 'EXECUTED', '7:e9560acad086432067c0f675efe97108', 'createIndex indexName=siges_clean_tipo_leccion_tipolec_codigo1_idx, tableName=tipo_leccion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-409', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 409, 'EXECUTED', '7:2d2bd5932af0f22c306abfab9988db2f', 'createIndex indexName=siges_clean_valor_calidad_codigos_vca_codigo1_idx, tableName=valor_calidad_codigos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-410', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 410, 'EXECUTED', '7:08e6541ba33f68c1053b6d299b29ee4e', 'createIndex indexName=siges_clean_valor_calidad_codigos_vca_habilitado2_idx, tableName=valor_calidad_codigos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-411', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 411, 'EXECUTED', '7:42ea1200698860954fb64d9f973ed572', 'createIndex indexName=siges_clean_valor_hora_val_hor_anio4_idx, tableName=valor_hora', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-412', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 412, 'EXECUTED', '7:ee4426c259c8dd5032f2e610ff233a14', 'createIndex indexName=siges_clean_valor_hora_val_hor_mon_fk3_idx, tableName=valor_hora', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-413', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 413, 'EXECUTED', '7:3b8b957c193fbe0dc2089cf74965087c', 'createIndex indexName=siges_clean_valor_hora_val_hor_org_fk2_idx, tableName=valor_hora', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-414', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 414, 'EXECUTED', '7:a92051996b11fded222339838fd62b29', 'createIndex indexName=siges_clean_valor_hora_val_hor_usu_fk1_idx, tableName=valor_hora', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-415', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 415, 'EXECUTED', '7:11560d8cde752b2bcfb5a1594f995aad', 'createIndex indexName=sql_build_idx, tableName=sql_executed', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-416', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 416, 'EXECUTED', '7:b9b3d95df38e92ddff9fbdf7e8a42d69', 'createIndex indexName=sql_ver_mayor_idx, tableName=sql_executed', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-417', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 417, 'EXECUTED', '7:4a324eeeafccbd47eff44face7bc9bc2', 'createIndex indexName=sql_ver_menor_idx, tableName=sql_executed', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-418', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 418, 'EXECUTED', '7:8ec665e2a2c48e9efe9703752c801a97', 'createIndex indexName=usu_token_idx, tableName=ss_usuario', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-419', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 419, 'EXECUTED', '7:2bed369d7cfdb253b796f547019a39d6', 'addForeignKeyConstraint baseTableName=areas_tags, constraintName=areas_tags_areatag_padre_fk_fkey, referencedTableName=areas_tags', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-420', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 420, 'EXECUTED', '7:0b2cf89d29ceb25a03b5958980a7fd5a', 'addForeignKeyConstraint baseTableName=devengado, constraintName=devengado_dev_adq_fk_fkey, referencedTableName=adquisicion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-421', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 421, 'EXECUTED', '7:824a0d4837260f6d0d4f94baa4ca9281', 'addForeignKeyConstraint baseTableName=latlng_proyectos, constraintName=latlng_proyectos_latlang_dep_fk_fkey, referencedTableName=departamentos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-422', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 422, 'EXECUTED', '7:004f8e2419ead750142d9d424ff48c9b', 'addForeignKeyConstraint baseTableName=lecapr_areacon, constraintName=lecapr_areacon_lecaprcon_con_fk_fkey, referencedTableName=area_conocimiento', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-423', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 423, 'EXECUTED', '7:80dee1e61f7acc5e54007db980063474', 'addForeignKeyConstraint baseTableName=lineabase_historico, constraintName=lineabase_historico_lineabase_entFk_fkey, referencedTableName=entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-424', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 424, 'EXECUTED', '7:365e5b2a7f93cdd6d5f05165f0fdfc47', 'addForeignKeyConstraint baseTableName=notificacion_instancia, constraintName=notificacion_instancia_notinst_not_fk_fkey, referencedTableName=notificacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-425', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 425, 'EXECUTED', '7:51b26d5c840825095acc0c72b6bc003d', 'addForeignKeyConstraint baseTableName=objetivos_estrategicos, constraintName=obj_est_org_fk, referencedTableName=organismos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-426', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 426, 'EXECUTED', '7:239c4272c328f90d5c0a9fef7bfce4a2', 'addForeignKeyConstraint baseTableName=organi_int_prove, constraintName=organi_int_prove_orga_amb_fk_fkey, referencedTableName=ambito', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-427', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 427, 'EXECUTED', '7:4a8071c7620f21a342fe7c2cb99dcfd1', 'addForeignKeyConstraint baseTableName=pagos, constraintName=pagos_pag_adq_fk_fkey, referencedTableName=adquisicion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-428', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 428, 'EXECUTED', '7:6f0b9e0c9fab1694e4b6c859a94ea195', 'addForeignKeyConstraint baseTableName=pagos, constraintName=pagos_pag_ent_fk_fkey, referencedTableName=entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-429', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 429, 'EXECUTED', '7:212a705e99f1da4097cdd6a4803c03f2', 'addForeignKeyConstraint baseTableName=personas, constraintName=personas_pers_orga_fk_fkey, referencedTableName=organi_int_prove', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-430', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 430, 'EXECUTED', '7:b014856c181657d77d83b7b6b953bca9', 'addForeignKeyConstraint baseTableName=plantilla_cronograma, constraintName=plantilla_cronograma_p_crono_org_fk_fkey, referencedTableName=organismos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-431', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 431, 'EXECUTED', '7:45e734dc60a27a6a894054ae9fefb88b', 'addForeignKeyConstraint baseTableName=plantilla_entregables, constraintName=plantilla_entregables_p_entregable_ant_fk_fkey, referencedTableName=plantilla_entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-432', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 432, 'EXECUTED', '7:b56bb8137fe74a337a9efc6fcd575c0f', 'addForeignKeyConstraint baseTableName=plantilla_entregables, constraintName=plantilla_entregables_p_entregable_p_cro_fk_fkey, referencedTableName=plantilla_cronograma', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-433', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 433, 'EXECUTED', '7:199b0c504ce91872af37950c329e4514', 'addForeignKeyConstraint baseTableName=presupuesto, constraintName=presupuesto_pre_fuente_organi_fk_fkey, referencedTableName=fuente_financiamiento', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-434', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 434, 'EXECUTED', '7:d7cc549e146548f3baf10a038afeab8f', 'addForeignKeyConstraint baseTableName=presupuesto, constraintName=presupuesto_pre_moneda_fkey, referencedTableName=moneda', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-435', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 435, 'EXECUTED', '7:9caf57c3c6ccd742c9f49e200bb50943', 'addForeignKeyConstraint baseTableName=productos, constraintName=productos_prod_ent_fk_fkey, referencedTableName=entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-436', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 436, 'EXECUTED', '7:552d7607e2cac2204e17d038112900d7', 'addForeignKeyConstraint baseTableName=prog_docs_obl, constraintName=prog_docs_obl_progdocsobl_docs_pk_fkey, referencedTableName=documentos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-437', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 437, 'EXECUTED', '7:d876fc41d336eb142efdafbd62f9095e', 'addForeignKeyConstraint baseTableName=prog_indices_pre, constraintName=prog_indices_pre_progindpre_progind_fk_fkey, referencedTableName=prog_indices', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-438', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 438, 'EXECUTED', '7:47a274109d8196e0bdb1950725f9b0f9', 'addForeignKeyConstraint baseTableName=prog_int, constraintName=prog_int_progint_int_pk_fkey, referencedTableName=interesados', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-439', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 439, 'EXECUTED', '7:9451e271b14ef27490f6dc0e64202f7b', 'addForeignKeyConstraint baseTableName=prog_lectura_area, constraintName=prog_lectura_area_proglectarea_area_pk_fkey, referencedTableName=areas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-440', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 440, 'EXECUTED', '7:df1310b665d241d11bcd3506520f86ca', 'addForeignKeyConstraint baseTableName=programas, constraintName=prog_obj_est_fk, referencedTableName=objetivos_estrategicos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-441', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 441, 'EXECUTED', '7:21ed4134db03688d09b053057d298a9d', 'addForeignKeyConstraint baseTableName=prog_tags, constraintName=prog_tags_progtag_area_pk_fkey, referencedTableName=areas_tags', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-442', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 442, 'EXECUTED', '7:9de1639563a1205d87b5213fba9af9d6', 'addForeignKeyConstraint baseTableName=programas, constraintName=programas_prog_area_fk_fkey, referencedTableName=areas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-443', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 443, 'EXECUTED', '7:2c9c6d172d628746c02822c9459dac17', 'addForeignKeyConstraint baseTableName=programas, constraintName=programas_prog_est_fk_fkey, referencedTableName=estados', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-444', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 444, 'EXECUTED', '7:b733a1d42733aea29372c00c449dc408', 'addForeignKeyConstraint baseTableName=programas, constraintName=programas_prog_org_fk_fkey, referencedTableName=organismos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-445', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 445, 'EXECUTED', '7:f884ce75b13ecbbdbadb20d679b3fbbb', 'addForeignKeyConstraint baseTableName=programas, constraintName=programas_prog_pre_fk_fkey, referencedTableName=presupuesto', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-446', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 446, 'EXECUTED', '7:619fd6ccab003ea1c5eb0c3d9195d5d9', 'addForeignKeyConstraint baseTableName=proy_int, constraintName=proy_int_proyint_int_pk_fkey, referencedTableName=interesados', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-447', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 447, 'EXECUTED', '7:6e09c9d9a82c9192ab147b03250100fb', 'addForeignKeyConstraint baseTableName=proy_lectura_area, constraintName=proy_lectura_area_proglectarea_area_pk_fkey, referencedTableName=areas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-448', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 448, 'EXECUTED', '7:e09a88c2515c58f2effa5cade36c6ee7', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proy_obj_est_fk, referencedTableName=objetivos_estrategicos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-449', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 449, 'EXECUTED', '7:c4afdf3273e044fc1e17ff4c8bfb7a2b', 'addForeignKeyConstraint baseTableName=proy_otros_cat_secundarias, constraintName=proy_otros_cat_secundarias_proy_cat_cat_proy_fk_fkey, referencedTableName=categoria_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-450', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 450, 'EXECUTED', '7:2307a79e46191939a7df5082bf8c2bde', 'addForeignKeyConstraint baseTableName=proy_otros_datos, constraintName=proy_otros_datos_proy_otr_cat_fk_fkey, referencedTableName=categoria_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-451', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 451, 'EXECUTED', '7:287b02d463db365c97f242887e6c0c22', 'addForeignKeyConstraint baseTableName=proy_otros_datos, constraintName=proy_otros_datos_proy_otr_cont_fk_fkey, referencedTableName=organi_int_prove', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-452', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 452, 'EXECUTED', '7:d6091ea5a04cf0e4fb6d8c46cd070620', 'addForeignKeyConstraint baseTableName=proy_otros_datos, constraintName=proy_otros_datos_proy_otr_ent_fk_fkey, referencedTableName=entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-453', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 453, 'EXECUTED', '7:e370d310e8d76e3a8e1a024ccbbf0886', 'addForeignKeyConstraint baseTableName=proy_otros_datos, constraintName=proy_otros_datos_proy_otr_est_pub_fk_fkey, referencedTableName=estados_publicacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-454', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 454, 'EXECUTED', '7:8a752ecf3576eb9251a1416f5098477c', 'addForeignKeyConstraint baseTableName=proy_otros_datos, constraintName=proy_otros_datos_proy_otr_eta_fk_fkey, referencedTableName=etapa', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-455', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 455, 'EXECUTED', '7:f0bc45ddf9dd0638a40b73f1d99bad0b', 'addForeignKeyConstraint baseTableName=proy_otros_datos, constraintName=proy_otros_datos_proy_otr_ins_eje_fk_fkey, referencedTableName=organi_int_prove', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-456', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 456, 'EXECUTED', '7:bcc1f0b5cf2e8770ebc54db35d5cf7e7', 'addForeignKeyConstraint baseTableName=proy_pre, constraintName=proy_pre_proypre_pre_fk_fkey, referencedTableName=presupuesto', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-457', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 457, 'EXECUTED', '7:2bf7f1cf2fd39f0c139c0b4f8004a98d', 'addForeignKeyConstraint baseTableName=proy_publica_hist, constraintName=proy_publica_proy_fk, referencedTableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-458', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 458, 'EXECUTED', '7:ff58f34a2faf8497780cbc711975a0f7', 'addForeignKeyConstraint baseTableName=proy_publica_hist, constraintName=proy_publica_usu_fk, referencedTableName=ss_usuario', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-459', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 459, 'EXECUTED', '7:f579a7ffedb39867f0e57227766b1ac1', 'addForeignKeyConstraint baseTableName=proy_tags, constraintName=proy_tags_proytag_area_pk_fkey, referencedTableName=areas_tags', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-460', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 460, 'EXECUTED', '7:233efc716cb7283f77ca66fe2bb150b1', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proyectos_proy_area_fk_fkey, referencedTableName=areas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-461', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 461, 'EXECUTED', '7:5f4646ad9eb977337c92545f23cb2cba', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proyectos_proy_est_fk_fkey, referencedTableName=estados', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-462', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 462, 'EXECUTED', '7:837530ba5e3bbf41521f4affdd8487e2', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proyectos_proy_est_pendiente_fk_fkey, referencedTableName=estados', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-463', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 463, 'EXECUTED', '7:49c281a5b16f4ef7060d3e72eed4f571', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proyectos_proy_latlng_fk_fkey, referencedTableName=latlng_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-464', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 464, 'EXECUTED', '7:7ef4d734349df708b17a0d09204c0671', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proyectos_proy_org_fk_fkey, referencedTableName=organismos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-465', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 465, 'EXECUTED', '7:79011cf2c50923cd0714d932a98950dc', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proyectos_proy_otr_dat_fk_fkey, referencedTableName=proy_otros_datos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-466', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 466, 'EXECUTED', '7:e6c4960fb4c9a26cc3e41f5d78cf256c', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proyectos_proy_pre_fk_fkey, referencedTableName=presupuesto', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-467', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 467, 'EXECUTED', '7:bc36eb8081f30d3d712bb2bae8e80a11', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proyectos_proy_prog_fk_fkey, referencedTableName=programas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-468', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 468, 'EXECUTED', '7:5a0a4ff12019fb470e74a75b8e674073', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proyectos_proy_proyindices_fk_fkey, referencedTableName=proy_indices', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-469', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 469, 'EXECUTED', '7:8f89642d57a813e2b5d1feb554235cc5', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proyectos_proy_risk_fk_fkey, referencedTableName=riesgos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-470', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 470, 'EXECUTED', '7:bc0c62f36a56fcc6b032d83a4bf5fa5a', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proyectos_proy_usr_adjunto_fk_fkey, referencedTableName=ss_usuario', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-471', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 471, 'EXECUTED', '7:75654c4c0106e7d9ddef7f42233cf117', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proyectos_proy_usr_pmofed_fk_fkey, referencedTableName=ss_usuario', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-472', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 472, 'EXECUTED', '7:c6c5dc0daee7e75a20620f5b97d63a14', 'addForeignKeyConstraint baseTableName=proyectos, constraintName=proyectos_proy_usr_sponsor_fk_fkey, referencedTableName=ss_usuario', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-473', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 473, 'EXECUTED', '7:94f44fc8b51d6fbfaf2485cb3576b0be', 'addForeignKeyConstraint baseTableName=registros_horas, constraintName=registros_horas_rh_ent_fk_fkey, referencedTableName=entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-474', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 474, 'EXECUTED', '7:ba51b009593d0aec173d23187cc762a7', 'addForeignKeyConstraint baseTableName=registros_horas, constraintName=registros_horas_rh_proy_fk_fkey, referencedTableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-475', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 475, 'EXECUTED', '7:96f320a075144f92b503915bd72cfcc0', 'addForeignKeyConstraint baseTableName=riesgos, constraintName=riesgos_risk_ent_fk_fkey, referencedTableName=entregables', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-476', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 476, 'EXECUTED', '7:3060db705030b1668b799c1d0ada91f6', 'addForeignKeyConstraint baseTableName=riesgos, constraintName=riesgos_risk_proy_fk_fkey, referencedTableName=proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-477', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 477, 'EXECUTED', '7:730ed10e664adb821243bfdaadbbc4ea', 'addForeignKeyConstraint baseTableName=roles_interesados, constraintName=roles_interesados_rolint_org_fk_fkey, referencedTableName=organismos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-478', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 478, 'EXECUTED', '7:03d0b0538e0c18926391fcc8c403970d', 'addForeignKeyConstraint baseTableName=ss_localidades, constraintName=ss_localidades_loc_depto_id_fkey, referencedTableName=ss_departamentos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-479', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 479, 'EXECUTED', '7:0bf2e894f6fb21e843cfce3af8d59521', 'addForeignKeyConstraint baseTableName=ss_operacion, constraintName=ss_operacion_ope_categoria_id_fkey, referencedTableName=ss_categoper', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-480', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 480, 'EXECUTED', '7:0be674d7715bf082d204b8424adcf3e0', 'addForeignKeyConstraint baseTableName=ss_rel_rol_operacion, constraintName=ss_rel_rol_operacion_rel_rol_operacion_operacion_id_fkey, referencedTableName=ss_operacion', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-481', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 481, 'EXECUTED', '7:d0e123b3c9406f13f337420fcb6e89af', 'addForeignKeyConstraint baseTableName=ss_usuario, constraintName=ss_usuario_usu_area_org_fkey, referencedTableName=areas', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-482', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 482, 'EXECUTED', '7:4eb13726252a29ecd0cbba8a91aa6b45', 'addForeignKeyConstraint baseTableName=temas_calidad, constraintName=temas_calidad_tca_org_fk_fkey, referencedTableName=organismos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-483', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 483, 'EXECUTED', '7:083cc2c3447581ffbee9869a6eb2692d', 'addForeignKeyConstraint baseTableName=tipo_documento_instancia, constraintName=tipo_documento_instancia_tipodoc_inst_tipodoc_fk_fkey, referencedTableName=tipo_documento', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-484', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 484, 'EXECUTED', '7:1bb97dae7b30e7f31a40d8fae6f76bd6', 'addForeignKeyConstraint baseTableName=tipo_documento, constraintName=tipo_documento_tipodoc_org_fk_fkey, referencedTableName=organismos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-485', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 485, 'EXECUTED', '7:a4e382fddcbfea9ff6eb278b6b4f2874', 'addForeignKeyConstraint baseTableName=tipo_gasto, constraintName=tipo_gasto_tipogas_org_fk_fkey, referencedTableName=organismos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-486', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 486, 'EXECUTED', '7:3ff9d3645e9ae2519ce3f2109e7176fd', 'addForeignKeyConstraint baseTableName=valor_hora, constraintName=valor_hora_val_hor_mon_fk_fkey, referencedTableName=moneda', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-487', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 487, 'EXECUTED', '7:27523ce088bcd7760c18f7cf63ea9cef', 'addForeignKeyConstraint baseTableName=valor_hora, constraintName=valor_hora_val_hor_org_fk_fkey, referencedTableName=organismos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-488', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 488, 'EXECUTED', '7:53ac71c71f9da48fc90a9535ab7fa148', 'addForeignKeyConstraint baseTableName=valor_hora, constraintName=valor_hora_val_hor_usu_fk_fkey, referencedTableName=ss_usuario', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-489', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 489, 'EXECUTED', '7:1fcdf8ca85ad2238a07adf7329fec8bf', 'createView viewName=programas_proyectos', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1497647833276-490', 'bruno (generated)', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 490, 'EXECUTED', '7:8b6e3a7e24c73353d70f59ee7936c700', 'createView viewName=ss_oficina', '', NULL, '3.5.3', NULL, NULL, '8857248732');
INSERT INTO databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('load_data', 'bruno', 'siges/clean/db.changelog.postgresql.clean.xml', '2017-06-30 21:14:24.650262', 491, 'EXECUTED', '7:2ee23efcf7d840635b46c5cd46e15faf', 'loadData tableName=departamentos; loadData tableName=estados; loadData tableName=estados_publicacion; loadData tableName=moneda; loadData tableName=ss_configuraciones; loadData tableName=ss_rol; loadData tableName=ss_usuario; loadData tableName=ss...', '', NULL, '3.5.3', NULL, NULL, '8857248732');


--
-- TOC entry 3413 (class 0 OID 51768)
-- Dependencies: 171
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO databasechangeloglock (id, locked, lockgranted, lockedby) VALUES (1, false, NULL, NULL);


--
-- TOC entry 3463 (class 0 OID 52011)
-- Dependencies: 221
-- Data for Name: departamentos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (1, 'MONTEVIDEO', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (2, 'ARTIGAS', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (3, 'CANELONES', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (4, 'CERRO LARGO', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (5, 'COLONIA', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (6, 'DURAZNO', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (7, 'FLORES', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (8, 'FLORIDA', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (9, 'LAVALLEJA', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (10, 'MALDONADO', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (11, 'PAYSANDU', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (12, 'RIO NEGRO', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (13, 'RIVERA', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (14, 'ROCHA', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (15, 'SALTO', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (16, 'SAN JOSE', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (17, 'SORIANO', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (18, 'TACUAREMBO', NULL, NULL, NULL);
INSERT INTO departamentos (dep_pk, dep_nombre, dep_lat, dep_lng, dep_zoom) VALUES (19, 'TREINTA Y TRES', NULL, NULL, NULL);


--
-- TOC entry 3465 (class 0 OID 52016)
-- Dependencies: 223
-- Data for Name: devengado; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3740 (class 0 OID 0)
-- Dependencies: 222
-- Name: devengado_dev_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('devengado_dev_pk_seq', 1, false);


--
-- TOC entry 3467 (class 0 OID 52024)
-- Dependencies: 225
-- Data for Name: doc_file; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3741 (class 0 OID 0)
-- Dependencies: 224
-- Name: doc_file_docfile_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('doc_file_docfile_pk_seq', 1, false);


--
-- TOC entry 3469 (class 0 OID 52035)
-- Dependencies: 227
-- Data for Name: documentos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3742 (class 0 OID 0)
-- Dependencies: 226
-- Name: documentos_docs_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('documentos_docs_pk_seq', 1, false);


--
-- TOC entry 3471 (class 0 OID 52043)
-- Dependencies: 229
-- Data for Name: ent_hist_linea_base; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3743 (class 0 OID 0)
-- Dependencies: 228
-- Name: ent_hist_linea_base_enthist_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ent_hist_linea_base_enthist_pk_seq', 1, false);


--
-- TOC entry 3473 (class 0 OID 52051)
-- Dependencies: 231
-- Data for Name: entregables; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3744 (class 0 OID 0)
-- Dependencies: 230
-- Name: entregables_ent_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('entregables_ent_pk_seq', 1, false);


--
-- TOC entry 3475 (class 0 OID 52063)
-- Dependencies: 233
-- Data for Name: estados; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO estados (est_pk, est_codigo, est_nombre, est_label, est_orden_proceso) VALUES (0, 'NO_EXIGIDO', 'No Exigido', '', NULL);
INSERT INTO estados (est_pk, est_codigo, est_nombre, est_label, est_orden_proceso) VALUES (1, 'PENDIENTE', 'Pendiente', '', 1);
INSERT INTO estados (est_pk, est_codigo, est_nombre, est_label, est_orden_proceso) VALUES (2, 'INICIO', 'Inicio', '', 2);
INSERT INTO estados (est_pk, est_codigo, est_nombre, est_label, est_orden_proceso) VALUES (3, 'PLANIFICACION', 'Planificacion', '', 3);
INSERT INTO estados (est_pk, est_codigo, est_nombre, est_label, est_orden_proceso) VALUES (4, 'EJECUCION', 'Ejecucion', '', 4);
INSERT INTO estados (est_pk, est_codigo, est_nombre, est_label, est_orden_proceso) VALUES (5, 'FINALIZADO', 'Finalizado', '', 5);
INSERT INTO estados (est_pk, est_codigo, est_nombre, est_label, est_orden_proceso) VALUES (11, 'PENDIENTE_PMOT', 'Pendiente PMO T.', '', NULL);
INSERT INTO estados (est_pk, est_codigo, est_nombre, est_label, est_orden_proceso) VALUES (12, 'PENDIENTE_PMOF', 'Pendiente PMO F.', '', NULL);
INSERT INTO estados (est_pk, est_codigo, est_nombre, est_label, est_orden_proceso) VALUES (41, 'SOLICITUD_FINALIZADO_PMOF', 'Solicitud Finalizado PMO F.', '', NULL);
INSERT INTO estados (est_pk, est_codigo, est_nombre, est_label, est_orden_proceso) VALUES (42, 'SOLICITUD_FINALIZADO_PMOT', 'Solicitud Finalizado PMO T.', '', NULL);
INSERT INTO estados (est_pk, est_codigo, est_nombre, est_label, est_orden_proceso) VALUES (61, 'SOLICITUD_CANCELAR_PMOT', 'Solicitud Cancelar PMO T.', '', NULL);


--
-- TOC entry 3745 (class 0 OID 0)
-- Dependencies: 232
-- Name: estados_est_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('estados_est_pk_seq', 1, false);


--
-- TOC entry 3477 (class 0 OID 52071)
-- Dependencies: 235
-- Data for Name: estados_publicacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO estados_publicacion (est_pub_pk, est_pub_codigo, est_pub_nombre) VALUES (1, 'NO_ES_PARA_PUBLICAR', 'No es para publicar');
INSERT INTO estados_publicacion (est_pub_pk, est_pub_codigo, est_pub_nombre) VALUES (2, 'PENDIENTE_CARGAR', 'Pendiente de cargar datos');
INSERT INTO estados_publicacion (est_pub_pk, est_pub_codigo, est_pub_nombre) VALUES (3, 'PENDIENTE_PUBLICAR', 'Pendiente de publicar');
INSERT INTO estados_publicacion (est_pub_pk, est_pub_codigo, est_pub_nombre) VALUES (4, 'PUBLICADO', 'Publicado');


--
-- TOC entry 3746 (class 0 OID 0)
-- Dependencies: 234
-- Name: estados_publicacion_est_pub_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('estados_publicacion_est_pub_pk_seq', 1, false);


--
-- TOC entry 3479 (class 0 OID 52079)
-- Dependencies: 237
-- Data for Name: etapa; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3747 (class 0 OID 0)
-- Dependencies: 236
-- Name: etapa_eta_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('etapa_eta_pk_seq', 1, false);


--
-- TOC entry 3481 (class 0 OID 52087)
-- Dependencies: 239
-- Data for Name: fuente_financiamiento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3748 (class 0 OID 0)
-- Dependencies: 238
-- Name: fuente_financiamiento_fue_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fuente_financiamiento_fue_pk_seq', 1, false);


--
-- TOC entry 3483 (class 0 OID 52095)
-- Dependencies: 241
-- Data for Name: gastos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3749 (class 0 OID 0)
-- Dependencies: 240
-- Name: gastos_gas_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('gastos_gas_pk_seq', 1, false);


--
-- TOC entry 3750 (class 0 OID 0)
-- Dependencies: 173
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 1, false);


--
-- TOC entry 3485 (class 0 OID 52106)
-- Dependencies: 243
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3751 (class 0 OID 0)
-- Dependencies: 242
-- Name: image_image_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('image_image_pk_seq', 1, false);


--
-- TOC entry 3487 (class 0 OID 52118)
-- Dependencies: 245
-- Data for Name: interesados; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3752 (class 0 OID 0)
-- Dependencies: 244
-- Name: interesados_int_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('interesados_int_pk_seq', 1, false);


--
-- TOC entry 3489 (class 0 OID 52129)
-- Dependencies: 247
-- Data for Name: latlng_proyectos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3753 (class 0 OID 0)
-- Dependencies: 246
-- Name: latlng_proyectos_latlng_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('latlng_proyectos_latlng_pk_seq', 1, false);


--
-- TOC entry 3490 (class 0 OID 52138)
-- Dependencies: 248
-- Data for Name: lecapr_areacon; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3492 (class 0 OID 52143)
-- Dependencies: 250
-- Data for Name: lecc_aprendidas; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3754 (class 0 OID 0)
-- Dependencies: 249
-- Name: lecc_aprendidas_lecapr_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('lecc_aprendidas_lecapr_pk_seq', 1, false);


--
-- TOC entry 3494 (class 0 OID 52154)
-- Dependencies: 252
-- Data for Name: lineabase_historico; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3755 (class 0 OID 0)
-- Dependencies: 251
-- Name: lineabase_historico_lineabase_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('lineabase_historico_lineabase_pk_seq', 1, false);


--
-- TOC entry 3496 (class 0 OID 52162)
-- Dependencies: 254
-- Data for Name: mails_template; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3756 (class 0 OID 0)
-- Dependencies: 253
-- Name: mails_template_mail_tmp_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('mails_template_mail_tmp_pk_seq', 1, false);


--
-- TOC entry 3498 (class 0 OID 52173)
-- Dependencies: 256
-- Data for Name: media_proyectos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3757 (class 0 OID 0)
-- Dependencies: 255
-- Name: media_proyectos_media_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('media_proyectos_media_pk_seq', 1, false);


--
-- TOC entry 3500 (class 0 OID 52184)
-- Dependencies: 258
-- Data for Name: moneda; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO moneda (mon_pk, mon_nombre, mon_signo, mon_cod_pais) VALUES (1, 'Pesos', '$', 'UY');
INSERT INTO moneda (mon_pk, mon_nombre, mon_signo, mon_cod_pais) VALUES (2, 'Dolares', 'U$S', 'US');
INSERT INTO moneda (mon_pk, mon_nombre, mon_signo, mon_cod_pais) VALUES (3, 'Euros', '€', 'EU');


--
-- TOC entry 3758 (class 0 OID 0)
-- Dependencies: 257
-- Name: moneda_mon_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('moneda_mon_pk_seq', 1, false);


--
-- TOC entry 3502 (class 0 OID 52192)
-- Dependencies: 260
-- Data for Name: notificacion; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3504 (class 0 OID 52203)
-- Dependencies: 262
-- Data for Name: notificacion_envio; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3759 (class 0 OID 0)
-- Dependencies: 261
-- Name: notificacion_envio_notenv_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('notificacion_envio_notenv_pk_seq', 1, false);


--
-- TOC entry 3506 (class 0 OID 52211)
-- Dependencies: 264
-- Data for Name: notificacion_instancia; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3760 (class 0 OID 0)
-- Dependencies: 263
-- Name: notificacion_instancia_notinst_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('notificacion_instancia_notinst_pk_seq', 1, false);


--
-- TOC entry 3761 (class 0 OID 0)
-- Dependencies: 259
-- Name: notificacion_not_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('notificacion_not_pk_seq', 1, false);


--
-- TOC entry 3762 (class 0 OID 0)
-- Dependencies: 174
-- Name: obj_est_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('obj_est_pk_seq', 1, false);


--
-- TOC entry 3508 (class 0 OID 52219)
-- Dependencies: 266
-- Data for Name: objetivos_estrategicos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3763 (class 0 OID 0)
-- Dependencies: 265
-- Name: objetivos_estrategicos_obj_est_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('objetivos_estrategicos_obj_est_pk_seq', 1, false);


--
-- TOC entry 3510 (class 0 OID 52227)
-- Dependencies: 268
-- Data for Name: organi_int_prove; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3764 (class 0 OID 0)
-- Dependencies: 267
-- Name: organi_int_prove_orga_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('organi_int_prove_orga_pk_seq', 1, false);


--
-- TOC entry 3512 (class 0 OID 52235)
-- Dependencies: 270
-- Data for Name: organismos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3765 (class 0 OID 0)
-- Dependencies: 269
-- Name: organismos_org_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('organismos_org_pk_seq', 1, false);


--
-- TOC entry 3514 (class 0 OID 52247)
-- Dependencies: 272
-- Data for Name: pagos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3766 (class 0 OID 0)
-- Dependencies: 271
-- Name: pagos_pag_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pagos_pag_pk_seq', 1, false);


--
-- TOC entry 3516 (class 0 OID 52258)
-- Dependencies: 274
-- Data for Name: participantes; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3767 (class 0 OID 0)
-- Dependencies: 273
-- Name: participantes_part_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('participantes_part_pk_seq', 1, false);


--
-- TOC entry 3518 (class 0 OID 52267)
-- Dependencies: 276
-- Data for Name: personas; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3768 (class 0 OID 0)
-- Dependencies: 275
-- Name: personas_pers_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('personas_pers_pk_seq', 1, false);


--
-- TOC entry 3520 (class 0 OID 52275)
-- Dependencies: 278
-- Data for Name: pge_configuraciones; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3769 (class 0 OID 0)
-- Dependencies: 277
-- Name: pge_configuraciones_cnf_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pge_configuraciones_cnf_id_seq', 1, false);


--
-- TOC entry 3522 (class 0 OID 52286)
-- Dependencies: 280
-- Data for Name: pge_invocaciones; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3770 (class 0 OID 0)
-- Dependencies: 279
-- Name: pge_invocaciones_inv_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pge_invocaciones_inv_id_seq', 1, false);


--
-- TOC entry 3524 (class 0 OID 52297)
-- Dependencies: 282
-- Data for Name: plantilla_cronograma; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3771 (class 0 OID 0)
-- Dependencies: 281
-- Name: plantilla_cronograma_p_crono_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('plantilla_cronograma_p_crono_pk_seq', 1, false);


--
-- TOC entry 3526 (class 0 OID 52308)
-- Dependencies: 284
-- Data for Name: plantilla_entregables; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3772 (class 0 OID 0)
-- Dependencies: 283
-- Name: plantilla_entregables_p_entregables_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('plantilla_entregables_p_entregables_id_seq', 1, false);


--
-- TOC entry 3528 (class 0 OID 52319)
-- Dependencies: 286
-- Data for Name: presupuesto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3773 (class 0 OID 0)
-- Dependencies: 285
-- Name: presupuesto_pre_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('presupuesto_pre_pk_seq', 1, false);


--
-- TOC entry 3530 (class 0 OID 52327)
-- Dependencies: 288
-- Data for Name: prod_mes; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3774 (class 0 OID 0)
-- Dependencies: 287
-- Name: prod_mes_prodmes_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('prod_mes_prodmes_pk_seq', 1, false);


--
-- TOC entry 3532 (class 0 OID 52335)
-- Dependencies: 290
-- Data for Name: productos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3775 (class 0 OID 0)
-- Dependencies: 289
-- Name: productos_prod_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('productos_prod_pk_seq', 1, false);


--
-- TOC entry 3533 (class 0 OID 52344)
-- Dependencies: 291
-- Data for Name: prog_docs; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3534 (class 0 OID 52347)
-- Dependencies: 292
-- Data for Name: prog_docs_obl; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3536 (class 0 OID 52352)
-- Dependencies: 294
-- Data for Name: prog_indices; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3538 (class 0 OID 52360)
-- Dependencies: 296
-- Data for Name: prog_indices_pre; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3776 (class 0 OID 0)
-- Dependencies: 295
-- Name: prog_indices_pre_progindpre_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('prog_indices_pre_progindpre_pk_seq', 1, false);


--
-- TOC entry 3777 (class 0 OID 0)
-- Dependencies: 293
-- Name: prog_indices_progind_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('prog_indices_progind_pk_seq', 1, false);


--
-- TOC entry 3539 (class 0 OID 52366)
-- Dependencies: 297
-- Data for Name: prog_int; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3540 (class 0 OID 52369)
-- Dependencies: 298
-- Data for Name: prog_lectura_area; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3541 (class 0 OID 52372)
-- Dependencies: 299
-- Data for Name: prog_pre; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3542 (class 0 OID 52375)
-- Dependencies: 300
-- Data for Name: prog_tags; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3544 (class 0 OID 52380)
-- Dependencies: 302
-- Data for Name: programas; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3778 (class 0 OID 0)
-- Dependencies: 301
-- Name: programas_prog_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('programas_prog_pk_seq', 1, false);


--
-- TOC entry 3545 (class 0 OID 52389)
-- Dependencies: 303
-- Data for Name: proy_docs; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3547 (class 0 OID 52394)
-- Dependencies: 305
-- Data for Name: proy_indices; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3549 (class 0 OID 52402)
-- Dependencies: 307
-- Data for Name: proy_indices_pre; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3779 (class 0 OID 0)
-- Dependencies: 306
-- Name: proy_indices_pre_proyindpre_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('proy_indices_pre_proyindpre_pk_seq', 1, false);


--
-- TOC entry 3780 (class 0 OID 0)
-- Dependencies: 304
-- Name: proy_indices_proyind_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('proy_indices_proyind_pk_seq', 1, false);


--
-- TOC entry 3550 (class 0 OID 52408)
-- Dependencies: 308
-- Data for Name: proy_int; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3551 (class 0 OID 52411)
-- Dependencies: 309
-- Data for Name: proy_lectura_area; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3552 (class 0 OID 52414)
-- Dependencies: 310
-- Data for Name: proy_otros_cat_secundarias; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3554 (class 0 OID 52419)
-- Dependencies: 312
-- Data for Name: proy_otros_datos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3781 (class 0 OID 0)
-- Dependencies: 311
-- Name: proy_otros_datos_proy_otr_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('proy_otros_datos_proy_otr_pk_seq', 1, false);


--
-- TOC entry 3555 (class 0 OID 52428)
-- Dependencies: 313
-- Data for Name: proy_pre; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3557 (class 0 OID 52433)
-- Dependencies: 315
-- Data for Name: proy_publica_hist; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3782 (class 0 OID 0)
-- Dependencies: 314
-- Name: proy_publica_hist_proy_publica_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('proy_publica_hist_proy_publica_pk_seq', 1, false);


--
-- TOC entry 3559 (class 0 OID 52441)
-- Dependencies: 317
-- Data for Name: proy_replanificacion; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3783 (class 0 OID 0)
-- Dependencies: 316
-- Name: proy_replanificacion_proyreplan_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('proy_replanificacion_proyreplan_pk_seq', 1, false);


--
-- TOC entry 3561 (class 0 OID 52452)
-- Dependencies: 319
-- Data for Name: proy_sitact_historico; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3784 (class 0 OID 0)
-- Dependencies: 318
-- Name: proy_sitact_historico_proy_sitact_hist_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('proy_sitact_historico_proy_sitact_hist_pk_seq', 1, false);


--
-- TOC entry 3562 (class 0 OID 52461)
-- Dependencies: 320
-- Data for Name: proy_tags; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3564 (class 0 OID 52466)
-- Dependencies: 322
-- Data for Name: proyectos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3785 (class 0 OID 0)
-- Dependencies: 321
-- Name: proyectos_proy_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('proyectos_proy_pk_seq', 1, false);


--
-- TOC entry 3566 (class 0 OID 52478)
-- Dependencies: 324
-- Data for Name: registros_horas; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3786 (class 0 OID 0)
-- Dependencies: 323
-- Name: registros_horas_rh_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('registros_horas_rh_pk_seq', 1, false);


--
-- TOC entry 3568 (class 0 OID 52489)
-- Dependencies: 326
-- Data for Name: revinfo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3787 (class 0 OID 0)
-- Dependencies: 325
-- Name: revinfo_rev_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('revinfo_rev_seq', 1, false);


--
-- TOC entry 3570 (class 0 OID 52498)
-- Dependencies: 328
-- Data for Name: riesgos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3788 (class 0 OID 0)
-- Dependencies: 327
-- Name: riesgos_risk_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('riesgos_risk_pk_seq', 1, false);


--
-- TOC entry 3572 (class 0 OID 52509)
-- Dependencies: 330
-- Data for Name: roles_interesados; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3789 (class 0 OID 0)
-- Dependencies: 329
-- Name: roles_interesados_rolint_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('roles_interesados_rolint_pk_seq', 1, false);


--
-- TOC entry 3573 (class 0 OID 52515)
-- Dependencies: 331
-- Data for Name: roles_usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3575 (class 0 OID 52520)
-- Dependencies: 333
-- Data for Name: sql_executed; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3790 (class 0 OID 0)
-- Dependencies: 332
-- Name: sql_executed_sql_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sql_executed_sql_pk_seq', 1, false);


--
-- TOC entry 3577 (class 0 OID 52531)
-- Dependencies: 335
-- Data for Name: ss_ayuda; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3791 (class 0 OID 0)
-- Dependencies: 334
-- Name: ss_ayuda_ayu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_ayuda_ayu_id_seq', 1, false);


--
-- TOC entry 3579 (class 0 OID 52542)
-- Dependencies: 337
-- Data for Name: ss_categoper; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3792 (class 0 OID 0)
-- Dependencies: 336
-- Name: ss_categoper_cat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_categoper_cat_id_seq', 1, false);


--
-- TOC entry 3581 (class 0 OID 52553)
-- Dependencies: 339
-- Data for Name: ss_configuraciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (1, 2, 'FILTRO_INICIO_POR_AREAS', 'Agrupar resultado incio por areas', 'false', NULL, NULL, NULL, '2015-10-21 13:23:35.955000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (2, 2, 'RIESGO_INDICE_LIMITE_AMARILLO', NULL, '1.2', NULL, NULL, NULL, '2015-10-21 13:23:35.971000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (3, 2, 'RIESGO_INDICE_LIMITE_ROJO', NULL, '4', NULL, NULL, NULL, '2015-10-21 13:23:35.971000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (4, 2, 'RIESGO_TIEMPO_LIMITE_AMARILLO', NULL, '10', NULL, NULL, NULL, '2015-10-21 13:23:35.971000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (5, 2, 'RIESGO_TIEMPO_LIMITE_ROJO', NULL, '20', NULL, NULL, NULL, '2015-10-21 13:23:35.986000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (6, 2, 'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO', NULL, '30', NULL, NULL, NULL, '2015-10-21 13:23:36.002000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (7, 2, 'DOCUMENTO_PORCENTAJE_LIMITE_ROJO', NULL, '70', NULL, NULL, NULL, '2015-10-21 13:23:36.002000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (8, 2, 'TAMANIO_MAX_ARCHIVO_DOCUMENTO', NULL, '10485760', NULL, NULL, NULL, '2015-10-21 13:23:36.002000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (9, 2, 'TAMANIO_MAX_ARCHIVO_MULTIMEDIA', NULL, '10485760', NULL, NULL, NULL, '2015-10-21 13:23:36.018000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (10, 2, 'COSTO_ACTUAL_LIMITE_AMARILLO', NULL, '10', NULL, NULL, NULL, '2015-10-21 13:23:36.018000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (11, 2, 'COSTO_ACTUAL_LIMITE_ROJO', NULL, '20', NULL, NULL, NULL, '2015-10-21 13:23:36.018000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (12, 2, 'ESTADO_INICIO_LIMITE_AMARILLO', 'Semaforo estado Inicio amarillo', '10', NULL, NULL, NULL, '2015-10-21 13:23:36.033000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (13, 2, 'ESTADO_INICIO_LIMITE_ROJO', 'Semaforo estado Inicio rojo', '15', NULL, NULL, NULL, '2015-10-21 13:23:36.033000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (14, 2, 'ESTADO_PLANIFICACION_LIMITE_AMARILLO', 'Semaforo estado Planificacion amarillo', '15', NULL, NULL, NULL, '2015-10-21 13:23:36.049000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (15, 2, 'ESTADO_PLANIFICACION_LIMITE_ROJO', 'Semaforo estado Planificacion rojo', '20', NULL, NULL, NULL, '2015-10-21 13:23:36.049000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (16, 2, 'TAMANIO_MAX_LOGO_ORGANISMO', 'Tamaño máximo en bytes del logo del Organismo', '262144', NULL, NULL, NULL, '2015-10-21 13:23:36.049000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (17, 2, 'MAIL_FROM', 'Dirección desde donde se envían los mails', 'mail@agesic.gub.uy', NULL, NULL, NULL, '2015-10-21 13:23:36.064000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (18, 2, 'MAIL_TLS', 'Configuración TLS en envío de mail', 'false', NULL, NULL, NULL, '2015-10-21 13:23:36.064000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (19, 2, 'MAIL_DEBUG', 'Debug del envío de mail', 'false', NULL, NULL, NULL, '2015-10-21 13:23:36.064000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (20, 2, 'MAIL_ENCODING', 'Encoding de los mails a enviar', 'utf8', NULL, NULL, NULL, '2015-10-21 13:23:36.080000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (21, 2, 'CON_CORREO', 'Si se envía correo o no', 'false', NULL, NULL, NULL, '2015-10-21 13:23:36.080000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (22, 2, 'CON_CONTROL_ACCESO', 'Si se usa el control de acceso de Agesic o no', 'false', NULL, NULL, NULL, '2015-10-21 13:23:36.096000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (23, 2, 'PRODUCTO_INDICE_LIMITE_AMARILLO', 'Limite semaforo amarillo para Productos', '10', NULL, NULL, NULL, '2015-10-21 13:23:36.096000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (24, 2, 'PRODUCTO_INDICE_LIMITE_ROJO', 'Limite semaforo rojo para Productos', '20', NULL, NULL, NULL, '2015-10-21 13:23:36.096000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (25, 2, 'ALCANCE_INDICE_LIMITE_AMARILLO', 'Limite semaforo amarillo para Alcance', '90', NULL, NULL, NULL, '2015-10-21 13:23:36.111000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (26, 2, 'ALCANCE_INDICE_LIMITE_ROJO', 'Limite semaforo rojo para Alcance', '70', NULL, NULL, NULL, '2015-10-21 13:23:36.111000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (27, 2, 'ADJUNTO_MODIFICA_PRESUPUESTO', 'Adjunto puede modificar presupuesto', 'false', NULL, NULL, NULL, '2015-10-21 13:23:36.127000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (28, 2, 'ENVIO_NOTIFICACIONES', 'Determina si se envían notificaciones', 'false', NULL, NULL, NULL, '2015-10-21 13:23:36.127000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (29, 2, 'CALIDAD_LIMITE_AMARILLO', 'Semaforo limite amarillo calidad', '70', NULL, NULL, NULL, '2015-10-21 13:23:36.127000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (30, 2, 'CALIDAD_LIMITE_ROJO', 'Semaforo limite rojo calidad', '30', NULL, NULL, NULL, '2015-10-21 13:23:36.142000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (31, 2, 'CALIDAD_GERENTE_MODIFICA', 'Permitir al Gerente modificar items calidad', 'false', NULL, NULL, NULL, '2015-10-21 13:23:36.142000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (32, 2, 'FOLDER_MEDIA', 'Carpeta donde se almacenan los archivos Multimedia', '/siges/media_files/', NULL, NULL, NULL, '2015-10-21 13:23:36.142000 -02:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (33, 3, 'FILTRO_INICIO_POR_AREAS', 'Agrupar resultado incio por areas', 'false', NULL, NULL, NULL, '2016-02-03 12:07:33.231000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (34, 3, 'RIESGO_INDICE_LIMITE_AMARILLO', NULL, '1.2', NULL, NULL, NULL, '2016-02-03 12:07:33.231000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (35, 3, 'RIESGO_INDICE_LIMITE_ROJO', NULL, '4', NULL, NULL, NULL, '2016-02-03 12:07:33.247000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (36, 3, 'RIESGO_TIEMPO_LIMITE_AMARILLO', NULL, '10', NULL, NULL, NULL, '2016-02-03 12:07:33.247000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (37, 3, 'RIESGO_TIEMPO_LIMITE_ROJO', NULL, '20', NULL, NULL, NULL, '2016-02-03 12:07:33.263000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (38, 3, 'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO', NULL, '30', NULL, NULL, NULL, '2016-02-03 12:07:33.263000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (39, 3, 'DOCUMENTO_PORCENTAJE_LIMITE_ROJO', NULL, '70', NULL, NULL, NULL, '2016-02-03 12:07:33.263000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (40, 3, 'TAMANIO_MAX_ARCHIVO_DOCUMENTO', NULL, '10485760', NULL, NULL, NULL, '2016-02-03 12:07:33.278000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (41, 3, 'TAMANIO_MAX_ARCHIVO_MULTIMEDIA', NULL, '512000', NULL, NULL, NULL, '2016-02-03 12:07:33.278000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (42, 3, 'COSTO_ACTUAL_LIMITE_AMARILLO', NULL, '10', NULL, NULL, NULL, '2016-02-03 12:07:33.278000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (43, 3, 'COSTO_ACTUAL_LIMITE_ROJO', NULL, '20', NULL, NULL, NULL, '2016-02-03 12:07:33.294000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (44, 3, 'ESTADO_INICIO_LIMITE_AMARILLO', 'Semaforo estado Inicio amarillo', '10', NULL, NULL, NULL, '2016-02-03 12:07:33.294000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (45, 3, 'ESTADO_INICIO_LIMITE_ROJO', 'Semaforo estado Inicio rojo', '15', NULL, NULL, NULL, '2016-02-03 12:07:33.294000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (46, 3, 'ESTADO_PLANIFICACION_LIMITE_AMARILLO', 'Semaforo estado Planificacion amarillo', '15', NULL, NULL, NULL, '2016-02-03 12:07:33.309000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (47, 3, 'ESTADO_PLANIFICACION_LIMITE_ROJO', 'Semaforo estado Planificacion rojo', '20', NULL, NULL, NULL, '2016-02-03 12:07:33.309000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (48, 3, 'TAMANIO_MAX_LOGO_ORGANISMO', 'Tamaño máximo en bytes del logo del Organismo', '262144', NULL, NULL, NULL, '2016-02-03 12:07:33.309000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (49, 3, 'MAIL_FROM', 'Dirección desde donde se envían los mails', 'mail@agesic.gub.uy', NULL, NULL, NULL, '2016-02-03 12:07:33.325000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (50, 3, 'MAIL_TLS', 'Configuración TLS en envío de mail', 'false', NULL, NULL, NULL, '2016-02-03 12:07:33.325000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (51, 3, 'MAIL_DEBUG', 'Debug del envío de mail', 'false', NULL, NULL, NULL, '2016-02-03 12:07:33.325000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (52, 3, 'MAIL_ENCODING', 'Encoding de los mails a enviar', 'utf8', NULL, NULL, NULL, '2016-02-03 12:07:33.341000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (53, 3, 'CON_CORREO', 'Si se envía correo o no', 'true', NULL, NULL, NULL, '2016-02-03 12:07:33.341000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (54, 3, 'CON_CONTROL_ACCESO', 'Si se usa el control de acceso de Agesic o no', 'false', NULL, NULL, NULL, '2016-02-03 12:07:33.341000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (55, 3, 'PRODUCTO_INDICE_LIMITE_AMARILLO', 'Limite semaforo amarillo para Productos', '10', NULL, NULL, NULL, '2016-02-03 12:07:33.341000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (56, 3, 'PRODUCTO_INDICE_LIMITE_ROJO', 'Limite semaforo rojo para Productos', '20', NULL, NULL, NULL, '2016-02-03 12:07:33.356000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (57, 3, 'ALCANCE_INDICE_LIMITE_AMARILLO', 'Limite semaforo amarillo para Alcance', '90', NULL, NULL, NULL, '2016-02-03 12:07:33.356000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (58, 3, 'ALCANCE_INDICE_LIMITE_ROJO', 'Limite semaforo rojo para Alcance', '70', NULL, NULL, NULL, '2016-02-03 12:07:33.356000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (59, 3, 'ADJUNTO_MODIFICA_PRESUPUESTO', 'Adjunto puede modificar presupuesto', 'false', NULL, NULL, NULL, '2016-02-03 12:07:33.372000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (60, 3, 'ENVIO_NOTIFICACIONES', 'Determina si se envían notificaciones', 'false', NULL, NULL, NULL, '2016-02-03 12:07:33.372000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (61, 3, 'CALIDAD_LIMITE_AMARILLO', 'Semaforo limite amarillo calidad', '70', NULL, NULL, NULL, '2016-02-03 12:07:33.372000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (62, 3, 'CALIDAD_LIMITE_ROJO', 'Semaforo limite rojo calidad', '30', NULL, NULL, NULL, '2016-02-03 12:07:33.387000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (63, 3, 'CALIDAD_GERENTE_MODIFICA', 'Permitir al Gerente modificar items calidad', 'false', NULL, NULL, NULL, '2016-02-03 12:07:33.387000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (64, 3, 'FOLDER_MEDIA', 'Carpeta donde se almacenan los archivos Multimedia', '/srv/siges/media_files/', NULL, NULL, NULL, '2016-02-03 12:07:33.387000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (65, NULL, 'DATABASE_ENGINE', 'Motor de DB', 'postgresql', NULL, NULL, NULL, '2016-05-29 15:55:21.759-03', NULL, '0');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (66, NULL, 'DATABASE_SCHEMA', 'Schema de DB', 'siges', NULL, NULL, NULL, '2016-05-29 15:55:21.759-03', NULL, '0');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (67, NULL, 'VISUALIZADOR_EXPORTACION_POR_PGE', 'Realizar la exportación al Visualizador por la PGE', 'true', NULL, NULL, NULL, '2016-05-29 16:16:48.307000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (68, 4, 'FILTRO_INICIO_POR_AREAS', 'Agrupar resultado incio por areas', 'false', NULL, NULL, NULL, '2016-05-29 16:28:44.802000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (69, 4, 'RIESGO_INDICE_LIMITE_AMARILLO', NULL, '1.2', NULL, NULL, NULL, '2016-05-29 16:28:44.807000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (70, 4, 'RIESGO_INDICE_LIMITE_ROJO', NULL, '4', NULL, NULL, NULL, '2016-05-29 16:28:44.813000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (71, 4, 'RIESGO_TIEMPO_LIMITE_AMARILLO', NULL, '10', NULL, NULL, NULL, '2016-05-29 16:28:44.817000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (72, 4, 'RIESGO_TIEMPO_LIMITE_ROJO', NULL, '20', NULL, NULL, NULL, '2016-05-29 16:28:44.821000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (73, 4, 'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO', NULL, '30', NULL, NULL, NULL, '2016-05-29 16:28:44.827000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (74, 4, 'DOCUMENTO_PORCENTAJE_LIMITE_ROJO', NULL, '70', NULL, NULL, NULL, '2016-05-29 16:28:44.835000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (75, 4, 'TAMANIO_MAX_ARCHIVO_DOCUMENTO', NULL, '10485760', NULL, NULL, NULL, '2016-05-29 16:28:44.843000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (76, 4, 'TAMANIO_MAX_ARCHIVO_MULTIMEDIA', NULL, '512000', NULL, NULL, NULL, '2016-05-29 16:28:44.848000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (77, 4, 'COSTO_ACTUAL_LIMITE_AMARILLO', NULL, '10', NULL, NULL, NULL, '2016-05-29 16:28:44.853000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (78, 4, 'COSTO_ACTUAL_LIMITE_ROJO', NULL, '20', NULL, NULL, NULL, '2016-05-29 16:28:44.858000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (79, 4, 'ESTADO_INICIO_LIMITE_AMARILLO', 'Semaforo estado Inicio amarillo', '10', NULL, NULL, NULL, '2016-05-29 16:28:44.864000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (80, 4, 'ESTADO_INICIO_LIMITE_ROJO', 'Semaforo estado Inicio rojo', '15', NULL, NULL, NULL, '2016-05-29 16:28:44.868000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (81, 4, 'ESTADO_PLANIFICACION_LIMITE_AMARILLO', 'Semaforo estado Planificacion amarillo', '15', NULL, NULL, NULL, '2016-05-29 16:28:44.873000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (82, 4, 'ESTADO_PLANIFICACION_LIMITE_ROJO', 'Semaforo estado Planificacion rojo', '20', NULL, NULL, NULL, '2016-05-29 16:28:44.880000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (83, 4, 'TAMANIO_MAX_LOGO_ORGANISMO', 'Tamaño máximo en bytes del logo del Organismo', '262144', NULL, NULL, NULL, '2016-05-29 16:28:44.889000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (84, 4, 'MAIL_FROM', 'Dirección desde donde se envían los mails', 'mail@agesic.gub.uy', NULL, NULL, NULL, '2016-05-29 16:28:44.896000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (85, 4, 'MAIL_TLS', 'Configuración TLS en envío de mail', 'false', NULL, NULL, NULL, '2016-05-29 16:28:44.901000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (86, 4, 'MAIL_DEBUG', 'Debug del envío de mail', 'false', NULL, NULL, NULL, '2016-05-29 16:28:44.906000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (87, 4, 'MAIL_ENCODING', 'Encoding de los mails a enviar', 'utf8', NULL, NULL, NULL, '2016-05-29 16:28:44.911000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (88, 4, 'CON_CORREO', 'Si se envía correo o no', 'true', NULL, NULL, NULL, '2016-05-29 16:28:44.916000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (89, 4, 'CON_CONTROL_ACCESO', 'Si se usa el control de acceso de Agesic o no', 'false', NULL, NULL, NULL, '2016-05-29 16:28:44.921000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (90, 4, 'PRODUCTO_INDICE_LIMITE_AMARILLO', 'Limite semaforo amarillo para Productos', '10', NULL, NULL, NULL, '2016-05-29 16:28:44.927000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (91, 4, 'PRODUCTO_INDICE_LIMITE_ROJO', 'Limite semaforo rojo para Productos', '20', NULL, NULL, NULL, '2016-05-29 16:28:44.934000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (92, 4, 'ALCANCE_INDICE_LIMITE_AMARILLO', 'Limite semaforo amarillo para Alcance', '90', NULL, NULL, NULL, '2016-05-29 16:28:44.942000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (93, 4, 'ALCANCE_INDICE_LIMITE_ROJO', 'Limite semaforo rojo para Alcance', '70', NULL, NULL, NULL, '2016-05-29 16:28:44.947000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (94, 4, 'ADJUNTO_MODIFICA_PRESUPUESTO', 'Adjunto puede modificar presupuesto', 'false', NULL, NULL, NULL, '2016-05-29 16:28:44.952000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (95, 4, 'ENVIO_NOTIFICACIONES', 'Determina si se envían notificaciones', 'false', NULL, NULL, NULL, '2016-05-29 16:28:44.958000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (96, 4, 'CALIDAD_LIMITE_AMARILLO', 'Semaforo limite amarillo calidad', '70', NULL, NULL, NULL, '2016-05-29 16:28:44.963000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (97, 4, 'CALIDAD_LIMITE_ROJO', 'Semaforo limite rojo calidad', '30', NULL, NULL, NULL, '2016-05-29 16:28:44.968000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (98, 4, 'CALIDAD_GERENTE_MODIFICA', 'Permitir al Gerente modificar items calidad', 'false', NULL, NULL, NULL, '2016-05-29 16:28:44.973000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (99, 4, 'FOLDER_MEDIA', 'Carpeta donde se almacenan los archivos Multimedia', '/srv/siges/media_files/', NULL, NULL, NULL, '2016-05-29 16:28:44.979000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (100, 5, 'FILTRO_INICIO_POR_AREAS', 'Agrupar resultado incio por areas', 'false', NULL, NULL, NULL, '2016-05-29 21:53:53.701000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (101, 5, 'RIESGO_INDICE_LIMITE_AMARILLO', NULL, '1.2', NULL, NULL, NULL, '2016-05-29 21:53:53.708000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (102, 5, 'RIESGO_INDICE_LIMITE_ROJO', NULL, '4', NULL, NULL, NULL, '2016-05-29 21:53:53.714000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (103, 5, 'RIESGO_TIEMPO_LIMITE_AMARILLO', NULL, '10', NULL, NULL, NULL, '2016-05-29 21:53:53.720000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (104, 5, 'RIESGO_TIEMPO_LIMITE_ROJO', NULL, '20', NULL, NULL, NULL, '2016-05-29 21:53:53.728000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (105, 5, 'DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO', NULL, '30', NULL, NULL, NULL, '2016-05-29 21:53:53.734000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (106, 5, 'DOCUMENTO_PORCENTAJE_LIMITE_ROJO', NULL, '70', NULL, NULL, NULL, '2016-05-29 21:53:53.741000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (107, 5, 'TAMANIO_MAX_ARCHIVO_DOCUMENTO', NULL, '10485760', NULL, NULL, NULL, '2016-05-29 21:53:53.747000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (108, 5, 'TAMANIO_MAX_ARCHIVO_MULTIMEDIA', NULL, '512000', NULL, NULL, NULL, '2016-05-29 21:53:53.754000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (109, 5, 'COSTO_ACTUAL_LIMITE_AMARILLO', NULL, '10', NULL, NULL, NULL, '2016-05-29 21:53:53.760000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (110, 5, 'COSTO_ACTUAL_LIMITE_ROJO', NULL, '20', NULL, NULL, NULL, '2016-05-29 21:53:53.765000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (111, 5, 'ESTADO_INICIO_LIMITE_AMARILLO', 'Semaforo estado Inicio amarillo', '10', NULL, NULL, NULL, '2016-05-29 21:53:53.771000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (112, 5, 'ESTADO_INICIO_LIMITE_ROJO', 'Semaforo estado Inicio rojo', '15', NULL, NULL, NULL, '2016-05-29 21:53:53.777000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (113, 5, 'ESTADO_PLANIFICACION_LIMITE_AMARILLO', 'Semaforo estado Planificacion amarillo', '15', NULL, NULL, NULL, '2016-05-29 21:53:53.783000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (114, 5, 'ESTADO_PLANIFICACION_LIMITE_ROJO', 'Semaforo estado Planificacion rojo', '20', NULL, NULL, NULL, '2016-05-29 21:53:53.789000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (115, 5, 'TAMANIO_MAX_LOGO_ORGANISMO', 'Tamaño máximo en bytes del logo del Organismo', '262144', NULL, NULL, NULL, '2016-05-29 21:53:53.795000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (116, 5, 'MAIL_FROM', 'Dirección desde donde se envían los mails', 'mail@agesic.gub.uy', NULL, NULL, NULL, '2016-05-29 21:53:53.800000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (117, 5, 'MAIL_TLS', 'Configuración TLS en envío de mail', 'false', NULL, NULL, NULL, '2016-05-29 21:53:53.806000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (118, 5, 'MAIL_DEBUG', 'Debug del envío de mail', 'false', NULL, NULL, NULL, '2016-05-29 21:53:53.811000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (119, 5, 'MAIL_ENCODING', 'Encoding de los mails a enviar', 'utf8', NULL, NULL, NULL, '2016-05-29 21:53:53.817000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (120, 5, 'CON_CORREO', 'Si se envía correo o no', 'true', NULL, NULL, NULL, '2016-05-29 21:53:53.822000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (121, 5, 'CON_CONTROL_ACCESO', 'Si se usa el control de acceso de Agesic o no', 'false', NULL, NULL, NULL, '2016-05-29 21:53:53.827000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (122, 5, 'PRODUCTO_INDICE_LIMITE_AMARILLO', 'Limite semaforo amarillo para Productos', '10', NULL, NULL, NULL, '2016-05-29 21:53:53.832000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (123, 5, 'PRODUCTO_INDICE_LIMITE_ROJO', 'Limite semaforo rojo para Productos', '20', NULL, NULL, NULL, '2016-05-29 21:53:53.838000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (124, 5, 'ALCANCE_INDICE_LIMITE_AMARILLO', 'Limite semaforo amarillo para Alcance', '90', NULL, NULL, NULL, '2016-05-29 21:53:53.843000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (125, 5, 'ALCANCE_INDICE_LIMITE_ROJO', 'Limite semaforo rojo para Alcance', '70', NULL, NULL, NULL, '2016-05-29 21:53:53.848000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (126, 5, 'ADJUNTO_MODIFICA_PRESUPUESTO', 'Adjunto puede modificar presupuesto', 'false', NULL, NULL, NULL, '2016-05-29 21:53:53.854000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (127, 5, 'ENVIO_NOTIFICACIONES', 'Determina si se envían notificaciones', 'false', NULL, NULL, NULL, '2016-05-29 21:53:53.861000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (128, 5, 'CALIDAD_LIMITE_AMARILLO', 'Semaforo limite amarillo calidad', '70', NULL, NULL, NULL, '2016-05-29 21:53:53.868000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (129, 5, 'CALIDAD_LIMITE_ROJO', 'Semaforo limite rojo calidad', '30', NULL, NULL, NULL, '2016-05-29 21:53:53.874000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (130, 5, 'CALIDAD_GERENTE_MODIFICA', 'Permitir al Gerente modificar items calidad', 'false', NULL, NULL, NULL, '2016-05-29 21:53:53.880000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (131, 5, 'FOLDER_MEDIA', 'Carpeta donde se almacenan los archivos Multimedia', '/srv/siges/media_files/', NULL, NULL, NULL, '2016-05-29 21:53:53.887000 -03:00:00', NULL, '1');
INSERT INTO ss_configuraciones (id, cnf_org_fk, cnf_codigo, cnf_descripcion, cnf_valor, cnf_protegido, cnf_html, cnf_ult_usuario, cnf_ult_mod, cnf_version, cnf_ult_origen) VALUES (132, NULL, 'DOCUMENTOS_DIR', 'Directorio donde se almacenarán los documentos', '/srv/siges/docs', NULL, false, NULL, '2017-07-03 17:24:03.345716+00', 0, NULL);


--
-- TOC entry 3793 (class 0 OID 0)
-- Dependencies: 338
-- Name: ss_configuraciones_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_configuraciones_id_seq', 132, true);


--
-- TOC entry 3583 (class 0 OID 52564)
-- Dependencies: 341
-- Data for Name: ss_departamentos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3794 (class 0 OID 0)
-- Dependencies: 340
-- Name: ss_departamentos_depto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_departamentos_depto_id_seq', 1, false);


--
-- TOC entry 3585 (class 0 OID 52575)
-- Dependencies: 343
-- Data for Name: ss_domicilios; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3795 (class 0 OID 0)
-- Dependencies: 342
-- Name: ss_domicilios_dom_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_domicilios_dom_id_seq', 1, false);


--
-- TOC entry 3586 (class 0 OID 52584)
-- Dependencies: 344
-- Data for Name: ss_errores; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3588 (class 0 OID 52592)
-- Dependencies: 346
-- Data for Name: ss_localidades; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3796 (class 0 OID 0)
-- Dependencies: 345
-- Name: ss_localidades_loc_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_localidades_loc_id_seq', 1, false);


--
-- TOC entry 3590 (class 0 OID 52603)
-- Dependencies: 348
-- Data for Name: ss_noticias; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3797 (class 0 OID 0)
-- Dependencies: 347
-- Name: ss_noticias_not_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_noticias_not_id_seq', 1, false);


--
-- TOC entry 3592 (class 0 OID 52614)
-- Dependencies: 350
-- Data for Name: ss_operacion; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3798 (class 0 OID 0)
-- Dependencies: 349
-- Name: ss_operacion_ope_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_operacion_ope_id_seq', 1, false);


--
-- TOC entry 3594 (class 0 OID 52625)
-- Dependencies: 352
-- Data for Name: ss_paises; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3799 (class 0 OID 0)
-- Dependencies: 351
-- Name: ss_paises_pai_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_paises_pai_id_seq', 1, false);


--
-- TOC entry 3595 (class 0 OID 52634)
-- Dependencies: 353
-- Data for Name: ss_paridades; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3800 (class 0 OID 0)
-- Dependencies: 175
-- Name: ss_paridades_par_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_paridades_par_id_seq', 1, false);


--
-- TOC entry 3597 (class 0 OID 52639)
-- Dependencies: 355
-- Data for Name: ss_personas; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3801 (class 0 OID 0)
-- Dependencies: 354
-- Name: ss_personas_per_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_personas_per_id_seq', 1, false);


--
-- TOC entry 3599 (class 0 OID 52650)
-- Dependencies: 357
-- Data for Name: ss_rel_rol_operacion; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3802 (class 0 OID 0)
-- Dependencies: 356
-- Name: ss_rel_rol_operacion_rel_rol_operacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_rel_rol_operacion_rel_rol_operacion_id_seq', 1, false);


--
-- TOC entry 3601 (class 0 OID 52658)
-- Dependencies: 359
-- Data for Name: ss_rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (24, 'REGISTRO_HORAS', 'Externo (solo carga de horas)', 'Externo (solo carga de horas)', 'rol_registro_horas', 'SIGES_WEB', 0, NULL, true, true);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (0, 'ADMINISTRADOR', 'Usuario Administrador de la aplicacion', 'Usuario Administrador', 'a', 'SIGES_WEB', 0, NULL, true, false);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (1, 'PMOT', 'PMO Transversal', 'PMO Transversal', 'a', 'SIGES_WEB', 0, NULL, true, true);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (2, 'PMOF', 'PMO Federada', 'PMO Federada', 'a', 'SIGES_WEB', 0, NULL, true, true);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (3, 'DIR', 'Director', 'Director', 'a', 'SIGES_WEB', 0, NULL, true, true);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (4, 'USU', 'Usuario', 'Usuario', 'a', 'SIGES_WEB', 0, NULL, true, true);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (5, 'ADMINIS', NULL, 'ADMINIS', 'a', 'SIGES_WEB', 0, NULL, true, false);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (6, 'ADMIN_TDO', NULL, 'ADMIN_TDO', 'a', 'SIGES_WEB', 0, NULL, true, false);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (7, 'ADMIN_ERR', NULL, 'ADMIN_ERR', 'a', 'SIGES_WEB', 0, NULL, true, false);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (8, 'ADM_CONF', NULL, 'ADM_CONF', 'a', 'SIGES_WEB', 0, NULL, true, false);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (9, 'ADMIN_NUEVOFICHA', NULL, 'ADMIN_NUEVOFICHA', 'a', 'SIGES_WEB', 0, NULL, true, false);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (10, 'CONF_ADD', NULL, 'CONF_ADD', 'a', 'SIGES_WEB', 0, NULL, true, false);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (11, 'CONF_EDIT', NULL, 'CONF_EDIT', 'a', 'SIGES_WEB', 0, NULL, true, false);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (12, 'CONF_HIST', NULL, 'CONF_HIST', 'a', 'SIGES_WEB', 0, NULL, true, false);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (13, 'MIGRACION', 'Acceso a la migración', 'MIGRACION', 'a', 'SIGES_WEB', 0, NULL, true, false);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (14, 'MIGRA_CALC_INDICA', 'Acceso a los callculos de la migración', 'MIGRA_CALC_INDICA', 'a', 'SIGES_WEB', 0, NULL, true, false);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (15, 'ADMIN_USU', 'Administración de los Usuarios', 'ADMIN_USU', 'a', 'SIGES_WEB', 0, NULL, true, false);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (16, 'USU_HORAS', 'Externo (solo carga de horas)', 'Externo (solo carga de horas)', 'a', 'SIGES_WEB', 0, NULL, true, false);
INSERT INTO ss_rol (rol_id, rol_cod, rol_descripcion, rol_nombre, rol_label, rol_origen, rol_user_code, rol_version, rol_vigente, rol_tipo_usuario) VALUES (17, 'EDITOR_GRAL', 'Editor General', 'Editor General', 'a', 'SIGES_WEB', 0, NULL, true, true);


--
-- TOC entry 3803 (class 0 OID 0)
-- Dependencies: 358
-- Name: ss_rol_rol_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_rol_rol_id_seq', 1, false);


--
-- TOC entry 3603 (class 0 OID 52669)
-- Dependencies: 361
-- Data for Name: ss_telefonos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3804 (class 0 OID 0)
-- Dependencies: 360
-- Name: ss_telefonos_tel_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_telefonos_tel_id_seq', 1, false);


--
-- TOC entry 3605 (class 0 OID 52677)
-- Dependencies: 363
-- Data for Name: ss_tipos_documento_persona; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3805 (class 0 OID 0)
-- Dependencies: 362
-- Name: ss_tipos_documento_persona_tipdocper_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_tipos_documento_persona_tipdocper_id_seq', 1, false);


--
-- TOC entry 3607 (class 0 OID 52688)
-- Dependencies: 365
-- Data for Name: ss_tipos_entrada_colectiva; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3806 (class 0 OID 0)
-- Dependencies: 364
-- Name: ss_tipos_entrada_colectiva_tec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_tipos_entrada_colectiva_tec_id_seq', 1, false);


--
-- TOC entry 3609 (class 0 OID 52699)
-- Dependencies: 367
-- Data for Name: ss_tipos_telefono; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3807 (class 0 OID 0)
-- Dependencies: 366
-- Name: ss_tipos_telefono_tipTel_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"ss_tipos_telefono_tipTel_id_seq"', 1, false);


--
-- TOC entry 3611 (class 0 OID 52710)
-- Dependencies: 369
-- Data for Name: ss_tipos_vialidad; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3808 (class 0 OID 0)
-- Dependencies: 368
-- Name: ss_tipos_vialidad_tvi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_tipos_vialidad_tvi_id_seq', 1, false);


--
-- TOC entry 3613 (class 0 OID 52721)
-- Dependencies: 371
-- Data for Name: ss_usu_ofi_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO ss_usu_ofi_roles (usu_ofi_roles_id, usu_ofi_roles_origen, usu_ofi_roles_user_code, usu_ofi_roles_oficina, usu_ofi_roles_rol, usu_ofi_roles_usuario, usu_ofi_roles_usu_area, usu_ofi_roles_activo) VALUES (1, 'SIGES_WEB', 1, NULL, 0, 1, NULL, true);


--
-- TOC entry 3809 (class 0 OID 0)
-- Dependencies: 370
-- Name: ss_usu_ofi_roles_usu_ofi_roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_usu_ofi_roles_usu_ofi_roles_id_seq', 1, false);


--
-- TOC entry 3615 (class 0 OID 52729)
-- Dependencies: 373
-- Data for Name: ss_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO ss_usuario (usu_id, usu_administrador, usu_cambio_estado_desc, usu_cod, usu_correo_electronico, usu_cuenta_bloqueada, usu_descripcion, usu_direccion, usu_fecha_password, usu_fecha_uuid, usu_foto, usu_intentos_fallidos, usu_nro_doc, usu_oficina_por_defecto, usu_origen, usu_password, usu_primer_apellido, usu_primer_nombre, usu_registrado, usu_segundo_apellido, usu_segundo_nombre, usu_telefono, usu_celular, usu_user_code, usu_uuid_des, usu_version, usu_vigente, usu_aprob_facturas, usu_ult_usuario, usu_ult_mod, usu_ult_origen, usu_area_org, usu_token, usu_token_act, usu_ldap_user) VALUES (1, true, 'desc', 'admin', 'admin', true, 'desc', 'dir', '2014-09-12 12:33:00', '2014-09-12 12:33:00', NULL, 0, '123131232312', NULL, '213213', '21232f297a57a5a743894a0e4a801fc3', 'Admin', 'Usuario', true, 'ap', 'nom', 'te', '000', 0, '1212', 0, true, false, 'admin', '2014-09-12 12:33:00', 'MIG', NULL, '', NULL, NULL);


--
-- TOC entry 3810 (class 0 OID 0)
-- Dependencies: 372
-- Name: ss_usuario_usu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_usuario_usu_id_seq', 1, false);


--
-- TOC entry 3617 (class 0 OID 52741)
-- Dependencies: 375
-- Data for Name: ss_usuarios_datos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3811 (class 0 OID 0)
-- Dependencies: 374
-- Name: ss_usuarios_datos_ss_usu_dat_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ss_usuarios_datos_ss_usu_dat_pk_seq', 1, false);


--
-- TOC entry 3619 (class 0 OID 52749)
-- Dependencies: 377
-- Data for Name: temas_calidad; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3812 (class 0 OID 0)
-- Dependencies: 376
-- Name: temas_calidad_tca_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('temas_calidad_tca_pk_seq', 1, false);


--
-- TOC entry 3621 (class 0 OID 52757)
-- Dependencies: 379
-- Data for Name: tipo_documento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3623 (class 0 OID 52765)
-- Dependencies: 381
-- Data for Name: tipo_documento_instancia; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3813 (class 0 OID 0)
-- Dependencies: 380
-- Name: tipo_documento_instancia_tipodoc_inst_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipo_documento_instancia_tipodoc_inst_pk_seq', 1, false);


--
-- TOC entry 3814 (class 0 OID 0)
-- Dependencies: 378
-- Name: tipo_documento_tipdoc_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipo_documento_tipdoc_pk_seq', 1, false);


--
-- TOC entry 3625 (class 0 OID 52773)
-- Dependencies: 383
-- Data for Name: tipo_gasto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3815 (class 0 OID 0)
-- Dependencies: 382
-- Name: tipo_gasto_tipogas_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipo_gasto_tipogas_pk_seq', 1, false);


--
-- TOC entry 3627 (class 0 OID 52781)
-- Dependencies: 385
-- Data for Name: tipo_leccion; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3816 (class 0 OID 0)
-- Dependencies: 384
-- Name: tipo_leccion_tipolec_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipo_leccion_tipolec_pk_seq', 1, false);


--
-- TOC entry 3629 (class 0 OID 52789)
-- Dependencies: 387
-- Data for Name: tipos_media; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3817 (class 0 OID 0)
-- Dependencies: 386
-- Name: tipos_media_tip_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipos_media_tip_pk_seq', 1, false);


--
-- TOC entry 3631 (class 0 OID 52797)
-- Dependencies: 389
-- Data for Name: valor_calidad_codigos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO valor_calidad_codigos (vca_pk, vca_codigo, vca_nombre, vca_habilitado) VALUES (1, 'baja', '0', true);
INSERT INTO valor_calidad_codigos (vca_pk, vca_codigo, vca_nombre, vca_habilitado) VALUES (2, 'media', '0.5', true);
INSERT INTO valor_calidad_codigos (vca_pk, vca_codigo, vca_nombre, vca_habilitado) VALUES (3, 'alta', '1', true);
INSERT INTO valor_calidad_codigos (vca_pk, vca_codigo, vca_nombre, vca_habilitado) VALUES (4, 'no_corresponde', 'No Corresponde', true);
INSERT INTO valor_calidad_codigos (vca_pk, vca_codigo, vca_nombre, vca_habilitado) VALUES (5, 'pendiente', 'Pendiente de Cargar', true);


--
-- TOC entry 3818 (class 0 OID 0)
-- Dependencies: 388
-- Name: valor_calidad_codigos_vca_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('valor_calidad_codigos_vca_pk_seq', 1, false);


--
-- TOC entry 3633 (class 0 OID 52805)
-- Dependencies: 391
-- Data for Name: valor_hora; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3819 (class 0 OID 0)
-- Dependencies: 390
-- Name: valor_hora_val_hor_pk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('valor_hora_val_hor_pk_seq', 1, false);


--
-- TOC entry 2708 (class 2606 OID 51792)
-- Name: adquisicion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY adquisicion
    ADD CONSTRAINT adquisicion_pkey PRIMARY KEY (adq_pk);


--
-- TOC entry 2714 (class 2606 OID 51800)
-- Name: ambito_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ambito
    ADD CONSTRAINT ambito_pkey PRIMARY KEY (amb_pk);


--
-- TOC entry 2718 (class 2606 OID 51808)
-- Name: area_conocimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY area_conocimiento
    ADD CONSTRAINT area_conocimiento_pkey PRIMARY KEY (con_pk);


--
-- TOC entry 2722 (class 2606 OID 51816)
-- Name: area_organi_int_prove_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY area_organi_int_prove
    ADD CONSTRAINT area_organi_int_prove_pkey PRIMARY KEY (areaorgintprov_pk);


--
-- TOC entry 2727 (class 2606 OID 51826)
-- Name: areas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY areas
    ADD CONSTRAINT areas_pkey PRIMARY KEY (area_pk);


--
-- TOC entry 2733 (class 2606 OID 51834)
-- Name: areas_tags_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY areas_tags
    ADD CONSTRAINT areas_tags_pkey PRIMARY KEY (arastag_pk);


--
-- TOC entry 3222 (class 2606 OID 56675)
-- Name: aud_doc_file_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_doc_file
    ADD CONSTRAINT aud_doc_file_pkey PRIMARY KEY (docfile_pk, rev);


--
-- TOC entry 2737 (class 2606 OID 52812)
-- Name: aud_pge_configuraciones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_pge_configuraciones
    ADD CONSTRAINT aud_pge_configuraciones_pkey PRIMARY KEY (cnf_id, rev);


--
-- TOC entry 2740 (class 2606 OID 52814)
-- Name: aud_programas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_programas
    ADD CONSTRAINT aud_programas_pkey PRIMARY KEY (prog_pk, rev);


--
-- TOC entry 2742 (class 2606 OID 52816)
-- Name: aud_programas_proyectos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_programas_proyectos
    ADD CONSTRAINT aud_programas_proyectos_pkey PRIMARY KEY (id, rev);


--
-- TOC entry 2745 (class 2606 OID 52818)
-- Name: aud_ss_ayuda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_ayuda
    ADD CONSTRAINT aud_ss_ayuda_pkey PRIMARY KEY (ayu_id, rev);


--
-- TOC entry 2748 (class 2606 OID 52820)
-- Name: aud_ss_categoper_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_categoper
    ADD CONSTRAINT aud_ss_categoper_pkey PRIMARY KEY (cat_id, rev);


--
-- TOC entry 2751 (class 2606 OID 52822)
-- Name: aud_ss_configuraciones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_configuraciones
    ADD CONSTRAINT aud_ss_configuraciones_pkey PRIMARY KEY (id, rev);


--
-- TOC entry 2754 (class 2606 OID 52824)
-- Name: aud_ss_departamentos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_departamentos
    ADD CONSTRAINT aud_ss_departamentos_pkey PRIMARY KEY (depto_id, rev);


--
-- TOC entry 2757 (class 2606 OID 52826)
-- Name: aud_ss_domicilios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_domicilios
    ADD CONSTRAINT aud_ss_domicilios_pkey PRIMARY KEY (dom_id, rev);


--
-- TOC entry 2760 (class 2606 OID 52828)
-- Name: aud_ss_errores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_errores
    ADD CONSTRAINT aud_ss_errores_pkey PRIMARY KEY (id, rev);


--
-- TOC entry 2763 (class 2606 OID 52830)
-- Name: aud_ss_localidades_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_localidades
    ADD CONSTRAINT aud_ss_localidades_pkey PRIMARY KEY (loc_id, rev);


--
-- TOC entry 2766 (class 2606 OID 52832)
-- Name: aud_ss_noticias_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_noticias
    ADD CONSTRAINT aud_ss_noticias_pkey PRIMARY KEY (not_id, rev);


--
-- TOC entry 2769 (class 2606 OID 52834)
-- Name: aud_ss_oficina_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_oficina
    ADD CONSTRAINT aud_ss_oficina_pkey PRIMARY KEY (ofi_id, rev);


--
-- TOC entry 2772 (class 2606 OID 52836)
-- Name: aud_ss_operacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_operacion
    ADD CONSTRAINT aud_ss_operacion_pkey PRIMARY KEY (ope_id, rev);


--
-- TOC entry 2775 (class 2606 OID 52838)
-- Name: aud_ss_paises_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_paises
    ADD CONSTRAINT aud_ss_paises_pkey PRIMARY KEY (pai_id, rev);


--
-- TOC entry 2778 (class 2606 OID 52840)
-- Name: aud_ss_paridades_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_paridades
    ADD CONSTRAINT aud_ss_paridades_pkey PRIMARY KEY (par_id, rev);


--
-- TOC entry 2781 (class 2606 OID 52842)
-- Name: aud_ss_personas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_personas
    ADD CONSTRAINT aud_ss_personas_pkey PRIMARY KEY (per_id, rev);


--
-- TOC entry 2784 (class 2606 OID 52844)
-- Name: aud_ss_rel_rol_operacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_rel_rol_operacion
    ADD CONSTRAINT aud_ss_rel_rol_operacion_pkey PRIMARY KEY (rel_rol_operacion_id, rev);


--
-- TOC entry 2787 (class 2606 OID 52846)
-- Name: aud_ss_rol_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_rol
    ADD CONSTRAINT aud_ss_rol_pkey PRIMARY KEY (rol_id, rev);


--
-- TOC entry 2790 (class 2606 OID 52848)
-- Name: aud_ss_telefonos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_telefonos
    ADD CONSTRAINT aud_ss_telefonos_pkey PRIMARY KEY (tel_id, rev);


--
-- TOC entry 2793 (class 2606 OID 52850)
-- Name: aud_ss_tipos_documento_persona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_tipos_documento_persona
    ADD CONSTRAINT aud_ss_tipos_documento_persona_pkey PRIMARY KEY (tipdocper_id, rev);


--
-- TOC entry 2796 (class 2606 OID 52852)
-- Name: aud_ss_tipos_entrada_colectiva_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_tipos_entrada_colectiva
    ADD CONSTRAINT aud_ss_tipos_entrada_colectiva_pkey PRIMARY KEY (tec_id, rev);


--
-- TOC entry 2799 (class 2606 OID 52854)
-- Name: aud_ss_tipos_telefono_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_tipos_telefono
    ADD CONSTRAINT aud_ss_tipos_telefono_pkey PRIMARY KEY ("tipTel_id", rev);


--
-- TOC entry 2802 (class 2606 OID 52856)
-- Name: aud_ss_tipos_vialidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_tipos_vialidad
    ADD CONSTRAINT aud_ss_tipos_vialidad_pkey PRIMARY KEY (tvi_id, rev);


--
-- TOC entry 2805 (class 2606 OID 52858)
-- Name: aud_ss_usu_ofi_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_usu_ofi_roles
    ADD CONSTRAINT aud_ss_usu_ofi_roles_pkey PRIMARY KEY (usu_ofi_roles_id, rev);


--
-- TOC entry 2808 (class 2606 OID 52860)
-- Name: aud_ss_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_ss_usuario
    ADD CONSTRAINT aud_ss_usuario_pkey PRIMARY KEY (usu_id, rev);


--
-- TOC entry 2812 (class 2606 OID 51984)
-- Name: busq_filtro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY busq_filtro
    ADD CONSTRAINT busq_filtro_pkey PRIMARY KEY (busq_filtro_pk);


--
-- TOC entry 2818 (class 2606 OID 51992)
-- Name: calidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY calidad
    ADD CONSTRAINT calidad_pkey PRIMARY KEY (cal_pk);


--
-- TOC entry 2829 (class 2606 OID 52002)
-- Name: categoria_proyectos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria_proyectos
    ADD CONSTRAINT categoria_proyectos_pkey PRIMARY KEY (cat_proy_pk);


--
-- TOC entry 2834 (class 2606 OID 52010)
-- Name: cronogramas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cronogramas
    ADD CONSTRAINT cronogramas_pkey PRIMARY KEY (cro_pk);


--
-- TOC entry 2836 (class 2606 OID 52862)
-- Name: departamentos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY departamentos
    ADD CONSTRAINT departamentos_pkey PRIMARY KEY (dep_pk);


--
-- TOC entry 2838 (class 2606 OID 52021)
-- Name: devengado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY devengado
    ADD CONSTRAINT devengado_pkey PRIMARY KEY (dev_pk);


--
-- TOC entry 2844 (class 2606 OID 52032)
-- Name: doc_file_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY doc_file
    ADD CONSTRAINT doc_file_pkey PRIMARY KEY (docfile_pk);


--
-- TOC entry 2849 (class 2606 OID 52040)
-- Name: documentos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY documentos
    ADD CONSTRAINT documentos_pkey PRIMARY KEY (docs_pk);


--
-- TOC entry 2854 (class 2606 OID 52048)
-- Name: ent_hist_linea_base_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ent_hist_linea_base
    ADD CONSTRAINT ent_hist_linea_base_pkey PRIMARY KEY (enthist_pk);


--
-- TOC entry 2859 (class 2606 OID 52060)
-- Name: entregables_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY entregables
    ADD CONSTRAINT entregables_pkey PRIMARY KEY (ent_pk);


--
-- TOC entry 2866 (class 2606 OID 52068)
-- Name: estados_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estados
    ADD CONSTRAINT estados_pkey PRIMARY KEY (est_pk);


--
-- TOC entry 2869 (class 2606 OID 52076)
-- Name: estados_publicacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estados_publicacion
    ADD CONSTRAINT estados_publicacion_pkey PRIMARY KEY (est_pub_pk);


--
-- TOC entry 2871 (class 2606 OID 52084)
-- Name: etapa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY etapa
    ADD CONSTRAINT etapa_pkey PRIMARY KEY (eta_pk);


--
-- TOC entry 2874 (class 2606 OID 52092)
-- Name: fuente_financiamiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fuente_financiamiento
    ADD CONSTRAINT fuente_financiamiento_pkey PRIMARY KEY (fue_pk);


--
-- TOC entry 2877 (class 2606 OID 52103)
-- Name: gastos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gastos
    ADD CONSTRAINT gastos_pkey PRIMARY KEY (gas_pk);


--
-- TOC entry 2885 (class 2606 OID 52115)
-- Name: image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY image
    ADD CONSTRAINT image_pkey PRIMARY KEY (image_pk);


--
-- TOC entry 2887 (class 2606 OID 52126)
-- Name: interesados_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY interesados
    ADD CONSTRAINT interesados_pkey PRIMARY KEY (int_pk);


--
-- TOC entry 2893 (class 2606 OID 52137)
-- Name: latlng_proyectos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY latlng_proyectos
    ADD CONSTRAINT latlng_proyectos_pkey PRIMARY KEY (latlng_pk);


--
-- TOC entry 2897 (class 2606 OID 52864)
-- Name: lecapr_areacon_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lecapr_areacon
    ADD CONSTRAINT lecapr_areacon_pkey PRIMARY KEY (lecaprcon_con_fk, lecaprcon_lecapr_fk);


--
-- TOC entry 2900 (class 2606 OID 52151)
-- Name: lecc_aprendidas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lecc_aprendidas
    ADD CONSTRAINT lecc_aprendidas_pkey PRIMARY KEY (lecapr_pk);


--
-- TOC entry 2908 (class 2606 OID 52159)
-- Name: lineabase_historico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lineabase_historico
    ADD CONSTRAINT lineabase_historico_pkey PRIMARY KEY (lineabase_pk);


--
-- TOC entry 2912 (class 2606 OID 52170)
-- Name: mails_template_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mails_template
    ADD CONSTRAINT mails_template_pkey PRIMARY KEY (mail_tmp_pk);


--
-- TOC entry 2915 (class 2606 OID 52181)
-- Name: media_proyectos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY media_proyectos
    ADD CONSTRAINT media_proyectos_pkey PRIMARY KEY (media_pk);


--
-- TOC entry 2922 (class 2606 OID 52189)
-- Name: moneda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY moneda
    ADD CONSTRAINT moneda_pkey PRIMARY KEY (mon_pk);


--
-- TOC entry 2926 (class 2606 OID 52208)
-- Name: notificacion_envio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacion_envio
    ADD CONSTRAINT notificacion_envio_pkey PRIMARY KEY (notenv_pk);


--
-- TOC entry 2929 (class 2606 OID 52216)
-- Name: notificacion_instancia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacion_instancia
    ADD CONSTRAINT notificacion_instancia_pkey PRIMARY KEY (notinst_pk);


--
-- TOC entry 2924 (class 2606 OID 52200)
-- Name: notificacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacion
    ADD CONSTRAINT notificacion_pkey PRIMARY KEY (not_pk);


--
-- TOC entry 2934 (class 2606 OID 52896)
-- Name: obj_est_org_fk_nombre; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY objetivos_estrategicos
    ADD CONSTRAINT obj_est_org_fk_nombre UNIQUE (obj_est_org_fk, obj_est_nombre);


--
-- TOC entry 2936 (class 2606 OID 52224)
-- Name: objetivos_estrategicos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY objetivos_estrategicos
    ADD CONSTRAINT objetivos_estrategicos_pkey PRIMARY KEY (obj_est_pk);


--
-- TOC entry 2938 (class 2606 OID 52232)
-- Name: organi_int_prove_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organi_int_prove
    ADD CONSTRAINT organi_int_prove_pkey PRIMARY KEY (orga_pk);


--
-- TOC entry 2944 (class 2606 OID 52244)
-- Name: organismos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organismos
    ADD CONSTRAINT organismos_pkey PRIMARY KEY (org_pk);


--
-- TOC entry 2946 (class 2606 OID 52255)
-- Name: pagos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pagos
    ADD CONSTRAINT pagos_pkey PRIMARY KEY (pag_pk);


--
-- TOC entry 2953 (class 2606 OID 52264)
-- Name: participantes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY participantes
    ADD CONSTRAINT participantes_pkey PRIMARY KEY (part_pk);


--
-- TOC entry 2959 (class 2606 OID 52272)
-- Name: personas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY personas
    ADD CONSTRAINT personas_pkey PRIMARY KEY (pers_pk);


--
-- TOC entry 2963 (class 2606 OID 52283)
-- Name: pge_configuraciones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pge_configuraciones
    ADD CONSTRAINT pge_configuraciones_pkey PRIMARY KEY (cnf_id);


--
-- TOC entry 2965 (class 2606 OID 52294)
-- Name: pge_invocaciones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pge_invocaciones
    ADD CONSTRAINT pge_invocaciones_pkey PRIMARY KEY (inv_id);


--
-- TOC entry 2706 (class 2606 OID 51772)
-- Name: pk_databasechangeloglock; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY databasechangeloglock
    ADD CONSTRAINT pk_databasechangeloglock PRIMARY KEY (id);


--
-- TOC entry 2967 (class 2606 OID 52305)
-- Name: plantilla_cronograma_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY plantilla_cronograma
    ADD CONSTRAINT plantilla_cronograma_pkey PRIMARY KEY (p_crono_pk);


--
-- TOC entry 2970 (class 2606 OID 52316)
-- Name: plantilla_entregables_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY plantilla_entregables
    ADD CONSTRAINT plantilla_entregables_pkey PRIMARY KEY (p_entregables_id);


--
-- TOC entry 2974 (class 2606 OID 52324)
-- Name: presupuesto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY presupuesto
    ADD CONSTRAINT presupuesto_pkey PRIMARY KEY (pre_pk);


--
-- TOC entry 2978 (class 2606 OID 52332)
-- Name: prod_mes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prod_mes
    ADD CONSTRAINT prod_mes_pkey PRIMARY KEY (prodmes_pk);


--
-- TOC entry 2983 (class 2606 OID 52343)
-- Name: productos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT productos_pkey PRIMARY KEY (prod_pk);


--
-- TOC entry 2990 (class 2606 OID 52866)
-- Name: prog_docs_obl_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prog_docs_obl
    ADD CONSTRAINT prog_docs_obl_pkey PRIMARY KEY (progdocsobl_docs_pk, progdocsobl_prog_pk);


--
-- TOC entry 2986 (class 2606 OID 52868)
-- Name: prog_docs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prog_docs
    ADD CONSTRAINT prog_docs_pkey PRIMARY KEY (progdocs_prog_pk, progdocs_doc_pk);


--
-- TOC entry 2993 (class 2606 OID 52357)
-- Name: prog_indices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prog_indices
    ADD CONSTRAINT prog_indices_pkey PRIMARY KEY (progind_pk);


--
-- TOC entry 2995 (class 2606 OID 52365)
-- Name: prog_indices_pre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prog_indices_pre
    ADD CONSTRAINT prog_indices_pre_pkey PRIMARY KEY (progindpre_pk);


--
-- TOC entry 2998 (class 2606 OID 52870)
-- Name: prog_int_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prog_int
    ADD CONSTRAINT prog_int_pkey PRIMARY KEY (progint_prog_pk, progint_int_pk);


--
-- TOC entry 3001 (class 2606 OID 52872)
-- Name: prog_lectura_area_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prog_lectura_area
    ADD CONSTRAINT prog_lectura_area_pkey PRIMARY KEY (proglectarea_prog_pk, proglectarea_area_pk);


--
-- TOC entry 3004 (class 2606 OID 52874)
-- Name: prog_pre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prog_pre
    ADD CONSTRAINT prog_pre_pkey PRIMARY KEY (progpre_prog_fk);


--
-- TOC entry 3007 (class 2606 OID 52876)
-- Name: prog_tags_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prog_tags
    ADD CONSTRAINT prog_tags_pkey PRIMARY KEY (progtag_prog_pk, progtag_area_pk);


--
-- TOC entry 3011 (class 2606 OID 52388)
-- Name: programas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY programas
    ADD CONSTRAINT programas_pkey PRIMARY KEY (prog_pk);


--
-- TOC entry 3028 (class 2606 OID 52878)
-- Name: proy_docs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_docs
    ADD CONSTRAINT proy_docs_pkey PRIMARY KEY (proydoc_proy_pk, proydoc_doc_pk);


--
-- TOC entry 3032 (class 2606 OID 52399)
-- Name: proy_indices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_indices
    ADD CONSTRAINT proy_indices_pkey PRIMARY KEY (proyind_pk);


--
-- TOC entry 3038 (class 2606 OID 52407)
-- Name: proy_indices_pre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_indices_pre
    ADD CONSTRAINT proy_indices_pre_pkey PRIMARY KEY (proyindpre_pk);


--
-- TOC entry 3040 (class 2606 OID 52880)
-- Name: proy_int_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_int
    ADD CONSTRAINT proy_int_pkey PRIMARY KEY (proyint_proy_pk, proyint_int_pk);


--
-- TOC entry 3043 (class 2606 OID 52882)
-- Name: proy_lectura_area_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_lectura_area
    ADD CONSTRAINT proy_lectura_area_pkey PRIMARY KEY (proglectarea_area_pk, proglectarea_proy_pk);


--
-- TOC entry 3047 (class 2606 OID 52884)
-- Name: proy_otros_cat_secundarias_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_otros_cat_secundarias
    ADD CONSTRAINT proy_otros_cat_secundarias_pkey PRIMARY KEY (proy_cat_proy_otros_fk, proy_cat_cat_proy_fk);


--
-- TOC entry 3050 (class 2606 OID 52427)
-- Name: proy_otros_datos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_otros_datos
    ADD CONSTRAINT proy_otros_datos_pkey PRIMARY KEY (proy_otr_pk);


--
-- TOC entry 3058 (class 2606 OID 52886)
-- Name: proy_pre_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_pre
    ADD CONSTRAINT proy_pre_pkey PRIMARY KEY (proypre_proy_fk);


--
-- TOC entry 3061 (class 2606 OID 52438)
-- Name: proy_publica_hist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_publica_hist
    ADD CONSTRAINT proy_publica_hist_pkey PRIMARY KEY (proy_publica_pk);


--
-- TOC entry 3065 (class 2606 OID 52449)
-- Name: proy_replanificacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_replanificacion
    ADD CONSTRAINT proy_replanificacion_pkey PRIMARY KEY (proyreplan_pk);


--
-- TOC entry 3070 (class 2606 OID 52460)
-- Name: proy_sitact_historico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_sitact_historico
    ADD CONSTRAINT proy_sitact_historico_pkey PRIMARY KEY (proy_sitact_hist_pk);


--
-- TOC entry 3074 (class 2606 OID 52888)
-- Name: proy_tags_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_tags
    ADD CONSTRAINT proy_tags_pkey PRIMARY KEY (proytag_proy_pk, proytag_area_pk);


--
-- TOC entry 3078 (class 2606 OID 52475)
-- Name: proyectos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proyectos_pkey PRIMARY KEY (proy_pk);


--
-- TOC entry 3100 (class 2606 OID 52486)
-- Name: registros_horas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY registros_horas
    ADD CONSTRAINT registros_horas_pkey PRIMARY KEY (rh_pk);


--
-- TOC entry 3107 (class 2606 OID 52495)
-- Name: revinfo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY revinfo
    ADD CONSTRAINT revinfo_pkey PRIMARY KEY (rev);


--
-- TOC entry 3109 (class 2606 OID 52506)
-- Name: riesgos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY riesgos
    ADD CONSTRAINT riesgos_pkey PRIMARY KEY (risk_pk);


--
-- TOC entry 3118 (class 2606 OID 52514)
-- Name: roles_interesados_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles_interesados
    ADD CONSTRAINT roles_interesados_pkey PRIMARY KEY (rolint_pk);


--
-- TOC entry 3121 (class 2606 OID 52890)
-- Name: roles_usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles_usuarios
    ADD CONSTRAINT roles_usuarios_pkey PRIMARY KEY (rol_pk);


--
-- TOC entry 3124 (class 2606 OID 52528)
-- Name: sql_executed_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sql_executed
    ADD CONSTRAINT sql_executed_pkey PRIMARY KEY (sql_pk);


--
-- TOC entry 3129 (class 2606 OID 52539)
-- Name: ss_ayuda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_ayuda
    ADD CONSTRAINT ss_ayuda_pkey PRIMARY KEY (ayu_id);


--
-- TOC entry 3131 (class 2606 OID 52550)
-- Name: ss_categoper_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_categoper
    ADD CONSTRAINT ss_categoper_pkey PRIMARY KEY (cat_id);


--
-- TOC entry 3135 (class 2606 OID 52561)
-- Name: ss_configuraciones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_configuraciones
    ADD CONSTRAINT ss_configuraciones_pkey PRIMARY KEY (id);


--
-- TOC entry 3137 (class 2606 OID 52572)
-- Name: ss_departamentos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_departamentos
    ADD CONSTRAINT ss_departamentos_pkey PRIMARY KEY (depto_id);


--
-- TOC entry 3145 (class 2606 OID 52583)
-- Name: ss_domicilios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_domicilios
    ADD CONSTRAINT ss_domicilios_pkey PRIMARY KEY (dom_id);


--
-- TOC entry 3147 (class 2606 OID 52892)
-- Name: ss_errores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_errores
    ADD CONSTRAINT ss_errores_pkey PRIMARY KEY (id);


--
-- TOC entry 3150 (class 2606 OID 52600)
-- Name: ss_localidades_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_localidades
    ADD CONSTRAINT ss_localidades_pkey PRIMARY KEY (loc_id);


--
-- TOC entry 3153 (class 2606 OID 52611)
-- Name: ss_noticias_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_noticias
    ADD CONSTRAINT ss_noticias_pkey PRIMARY KEY (not_id);


--
-- TOC entry 3156 (class 2606 OID 52622)
-- Name: ss_operacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_operacion
    ADD CONSTRAINT ss_operacion_pkey PRIMARY KEY (ope_id);


--
-- TOC entry 3158 (class 2606 OID 52633)
-- Name: ss_paises_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_paises
    ADD CONSTRAINT ss_paises_pkey PRIMARY KEY (pai_id);


--
-- TOC entry 3160 (class 2606 OID 52894)
-- Name: ss_paridades_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_paridades
    ADD CONSTRAINT ss_paridades_pkey PRIMARY KEY (par_id);


--
-- TOC entry 3164 (class 2606 OID 52647)
-- Name: ss_personas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_personas
    ADD CONSTRAINT ss_personas_pkey PRIMARY KEY (per_id);


--
-- TOC entry 3168 (class 2606 OID 52655)
-- Name: ss_rel_rol_operacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_rel_rol_operacion
    ADD CONSTRAINT ss_rel_rol_operacion_pkey PRIMARY KEY (rel_rol_operacion_id);


--
-- TOC entry 3170 (class 2606 OID 52666)
-- Name: ss_rol_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_rol
    ADD CONSTRAINT ss_rol_pkey PRIMARY KEY (rol_id);


--
-- TOC entry 3173 (class 2606 OID 52674)
-- Name: ss_telefonos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_telefonos
    ADD CONSTRAINT ss_telefonos_pkey PRIMARY KEY (tel_id);


--
-- TOC entry 3175 (class 2606 OID 52685)
-- Name: ss_tipos_documento_persona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_tipos_documento_persona
    ADD CONSTRAINT ss_tipos_documento_persona_pkey PRIMARY KEY (tipdocper_id);


--
-- TOC entry 3177 (class 2606 OID 52696)
-- Name: ss_tipos_entrada_colectiva_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_tipos_entrada_colectiva
    ADD CONSTRAINT ss_tipos_entrada_colectiva_pkey PRIMARY KEY (tec_id);


--
-- TOC entry 3179 (class 2606 OID 52707)
-- Name: ss_tipos_telefono_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_tipos_telefono
    ADD CONSTRAINT ss_tipos_telefono_pkey PRIMARY KEY ("tipTel_id");


--
-- TOC entry 3181 (class 2606 OID 52718)
-- Name: ss_tipos_vialidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_tipos_vialidad
    ADD CONSTRAINT ss_tipos_vialidad_pkey PRIMARY KEY (tvi_id);


--
-- TOC entry 3186 (class 2606 OID 52726)
-- Name: ss_usu_ofi_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_usu_ofi_roles
    ADD CONSTRAINT ss_usu_ofi_roles_pkey PRIMARY KEY (usu_ofi_roles_id);


--
-- TOC entry 3190 (class 2606 OID 52738)
-- Name: ss_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_usuario
    ADD CONSTRAINT ss_usuario_pkey PRIMARY KEY (usu_id);


--
-- TOC entry 3193 (class 2606 OID 52746)
-- Name: ss_usuarios_datos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_usuarios_datos
    ADD CONSTRAINT ss_usuarios_datos_pkey PRIMARY KEY (ss_usu_dat_pk);


--
-- TOC entry 3196 (class 2606 OID 52754)
-- Name: temas_calidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY temas_calidad
    ADD CONSTRAINT temas_calidad_pkey PRIMARY KEY (tca_pk);


--
-- TOC entry 3202 (class 2606 OID 52770)
-- Name: tipo_documento_instancia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_documento_instancia
    ADD CONSTRAINT tipo_documento_instancia_pkey PRIMARY KEY (tipodoc_inst_pk);


--
-- TOC entry 3199 (class 2606 OID 52762)
-- Name: tipo_documento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_documento
    ADD CONSTRAINT tipo_documento_pkey PRIMARY KEY (tipdoc_pk);


--
-- TOC entry 3205 (class 2606 OID 52778)
-- Name: tipo_gasto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_gasto
    ADD CONSTRAINT tipo_gasto_pkey PRIMARY KEY (tipogas_pk);


--
-- TOC entry 3208 (class 2606 OID 52786)
-- Name: tipo_leccion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_leccion
    ADD CONSTRAINT tipo_leccion_pkey PRIMARY KEY (tipolec_pk);


--
-- TOC entry 3210 (class 2606 OID 52794)
-- Name: tipos_media_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipos_media
    ADD CONSTRAINT tipos_media_pkey PRIMARY KEY (tip_pk);


--
-- TOC entry 2847 (class 2606 OID 56677)
-- Name: uk_n76rhuste8gi3p3jq7m91j7iq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY doc_file
    ADD CONSTRAINT uk_n76rhuste8gi3p3jq7m91j7iq UNIQUE (docfile_doc_fk);


--
-- TOC entry 3214 (class 2606 OID 52802)
-- Name: valor_calidad_codigos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY valor_calidad_codigos
    ADD CONSTRAINT valor_calidad_codigos_pkey PRIMARY KEY (vca_pk);


--
-- TOC entry 3220 (class 2606 OID 52810)
-- Name: valor_hora_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY valor_hora
    ADD CONSTRAINT valor_hora_pkey PRIMARY KEY (val_hor_pk);


--
-- TOC entry 2725 (class 1259 OID 52897)
-- Name: area_activo_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX area_activo_idx ON areas USING btree (area_activo);


--
-- TOC entry 2826 (class 1259 OID 52898)
-- Name: cat_org_fk_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX cat_org_fk_idx ON categoria_proyectos USING btree (cat_org_fk);


--
-- TOC entry 2827 (class 1259 OID 52899)
-- Name: cat_tipo_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX cat_tipo_idx ON categoria_proyectos USING btree (cat_tipo);


--
-- TOC entry 2857 (class 1259 OID 52900)
-- Name: ent_parent_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX ent_parent_idx ON entregables USING btree (ent_parent);


--
-- TOC entry 2813 (class 1259 OID 56678)
-- Name: fk_4b0pq8qh2f6u7ei11lh0atbf8; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fk_4b0pq8qh2f6u7ei11lh0atbf8 ON busq_filtro USING btree (busq_filtro_usu_fk);


--
-- TOC entry 2839 (class 1259 OID 56679)
-- Name: fk_9c8og633e1waprs81i6ayorba; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fk_9c8og633e1waprs81i6ayorba ON devengado USING btree (dev_adq_fk);


--
-- TOC entry 2814 (class 1259 OID 56680)
-- Name: fk_a726jrgroi6p90dip38n70ftp; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fk_a726jrgroi6p90dip38n70ftp ON busq_filtro USING btree (busq_filtro_org_fk);


--
-- TOC entry 2830 (class 1259 OID 56681)
-- Name: fk_hb3weyc8xvdrbp62k3u1halnb; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fk_hb3weyc8xvdrbp62k3u1halnb ON categoria_proyectos USING btree (cat_icono_marker);


--
-- TOC entry 2831 (class 1259 OID 56682)
-- Name: fk_hh3lr9l8qt7isgvniyif3w6tw; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fk_hh3lr9l8qt7isgvniyif3w6tw ON categoria_proyectos USING btree (cat_icono);


--
-- TOC entry 2728 (class 1259 OID 56683)
-- Name: fk_j151q3d1wiqgx10w9n92hwx5j; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fk_j151q3d1wiqgx10w9n92hwx5j ON areas USING btree (area_director);


--
-- TOC entry 2715 (class 1259 OID 56684)
-- Name: fk_n5tl58dqv18cs790jbmejxom2; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fk_n5tl58dqv18cs790jbmejxom2 ON ambito USING btree (amb_org_fk);


--
-- TOC entry 2932 (class 1259 OID 52901)
-- Name: obj_est_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX obj_est_org_fk1_idx ON objetivos_estrategicos USING btree (obj_est_org_fk);


--
-- TOC entry 2942 (class 1259 OID 52902)
-- Name: org_token_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX org_token_idx ON organismos USING btree (org_token);


--
-- TOC entry 3009 (class 1259 OID 52903)
-- Name: prog_obj_est_fk_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX prog_obj_est_fk_idx ON programas USING btree (prog_obj_est_fk);


--
-- TOC entry 3076 (class 1259 OID 52904)
-- Name: proy_obj_est_fk_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX proy_obj_est_fk_idx ON proyectos USING btree (proy_obj_est_fk);


--
-- TOC entry 3062 (class 1259 OID 52905)
-- Name: proy_publica_proy_fk_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX proy_publica_proy_fk_idx ON proy_publica_hist USING btree (proy_publica_proy_fk);


--
-- TOC entry 3063 (class 1259 OID 52906)
-- Name: proy_publica_usu_fk_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX proy_publica_usu_fk_idx ON proy_publica_hist USING btree (proy_publica_usu_fk);


--
-- TOC entry 3033 (class 1259 OID 56685)
-- Name: proyind_periodo_fin_ent_fk_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX proyind_periodo_fin_ent_fk_idx ON proy_indices USING btree (proyind_periodo_fin_ent_fk);


--
-- TOC entry 3034 (class 1259 OID 56686)
-- Name: proyind_periodo_inicio_ent_fk_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX proyind_periodo_inicio_ent_fk_idx ON proy_indices USING btree (proyind_periodo_inicio_ent_fk);


--
-- TOC entry 2709 (class 1259 OID 52907)
-- Name: siges_clean_adquisicion_adq_fuente_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_adquisicion_adq_fuente_fk2_idx ON adquisicion USING btree (adq_fuente_fk);


--
-- TOC entry 2710 (class 1259 OID 52908)
-- Name: siges_clean_adquisicion_adq_moneda_fk3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_adquisicion_adq_moneda_fk3_idx ON adquisicion USING btree (adq_moneda_fk);


--
-- TOC entry 2711 (class 1259 OID 52909)
-- Name: siges_clean_adquisicion_adq_pre_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_adquisicion_adq_pre_fk1_idx ON adquisicion USING btree (adq_pre_fk);


--
-- TOC entry 2712 (class 1259 OID 52910)
-- Name: siges_clean_adquisicion_adq_prov_orga_fk4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_adquisicion_adq_prov_orga_fk4_idx ON adquisicion USING btree (adq_prov_orga_fk);


--
-- TOC entry 2716 (class 1259 OID 52911)
-- Name: siges_clean_ambito_amb_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ambito_amb_org_fk1_idx ON ambito USING btree (amb_org_fk);


--
-- TOC entry 2719 (class 1259 OID 52912)
-- Name: siges_clean_area_conocimiento_con_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_area_conocimiento_con_org_fk1_idx ON area_conocimiento USING btree (con_org_fk);


--
-- TOC entry 2720 (class 1259 OID 52913)
-- Name: siges_clean_area_conocimiento_con_padre_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_area_conocimiento_con_padre_fk2_idx ON area_conocimiento USING btree (con_padre_fk);


--
-- TOC entry 2723 (class 1259 OID 52914)
-- Name: siges_clean_area_organi_int_prove_areaorgintprov_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_area_organi_int_prove_areaorgintprov_org_fk1_idx ON area_organi_int_prove USING btree (areaorgintprov_org_fk);


--
-- TOC entry 2724 (class 1259 OID 52915)
-- Name: siges_clean_area_organi_int_prove_areaorgintprov_orga_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_area_organi_int_prove_areaorgintprov_orga_fk2_idx ON area_organi_int_prove USING btree (areaorgintprov_orga_fk);


--
-- TOC entry 2729 (class 1259 OID 52916)
-- Name: siges_clean_areas_area_director3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_areas_area_director3_idx ON areas USING btree (area_director);


--
-- TOC entry 2730 (class 1259 OID 52917)
-- Name: siges_clean_areas_area_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_areas_area_org_fk1_idx ON areas USING btree (area_org_fk);


--
-- TOC entry 2731 (class 1259 OID 52918)
-- Name: siges_clean_areas_area_padre2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_areas_area_padre2_idx ON areas USING btree (area_padre);


--
-- TOC entry 2734 (class 1259 OID 52919)
-- Name: siges_clean_areas_tags_areatag_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_areas_tags_areatag_org_fk1_idx ON areas_tags USING btree (areatag_org_fk);


--
-- TOC entry 2735 (class 1259 OID 52920)
-- Name: siges_clean_areas_tags_areatag_padre_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_areas_tags_areatag_padre_fk2_idx ON areas_tags USING btree (areatag_padre_fk);


--
-- TOC entry 2738 (class 1259 OID 52921)
-- Name: siges_clean_aud_pge_configuraciones_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_pge_configuraciones_REV1_idx" ON aud_pge_configuraciones USING btree (rev);


--
-- TOC entry 2743 (class 1259 OID 52922)
-- Name: siges_clean_aud_programas_proyectos_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_programas_proyectos_REV1_idx" ON aud_programas_proyectos USING btree (rev);


--
-- TOC entry 2746 (class 1259 OID 52923)
-- Name: siges_clean_aud_ss_ayuda_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_ayuda_REV1_idx" ON aud_ss_ayuda USING btree (rev);


--
-- TOC entry 2749 (class 1259 OID 52924)
-- Name: siges_clean_aud_ss_categoper_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_categoper_REV1_idx" ON aud_ss_categoper USING btree (rev);


--
-- TOC entry 2752 (class 1259 OID 52925)
-- Name: siges_clean_aud_ss_configuraciones_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_configuraciones_REV1_idx" ON aud_ss_configuraciones USING btree (rev);


--
-- TOC entry 2755 (class 1259 OID 52926)
-- Name: siges_clean_aud_ss_departamentos_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_departamentos_REV1_idx" ON aud_ss_departamentos USING btree (rev);


--
-- TOC entry 2758 (class 1259 OID 52927)
-- Name: siges_clean_aud_ss_domicilios_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_domicilios_REV1_idx" ON aud_ss_domicilios USING btree (rev);


--
-- TOC entry 2761 (class 1259 OID 52928)
-- Name: siges_clean_aud_ss_errores_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_errores_REV1_idx" ON aud_ss_errores USING btree (rev);


--
-- TOC entry 2764 (class 1259 OID 52929)
-- Name: siges_clean_aud_ss_localidades_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_localidades_REV1_idx" ON aud_ss_localidades USING btree (rev);


--
-- TOC entry 2767 (class 1259 OID 52930)
-- Name: siges_clean_aud_ss_noticias_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_noticias_REV1_idx" ON aud_ss_noticias USING btree (rev);


--
-- TOC entry 2770 (class 1259 OID 52931)
-- Name: siges_clean_aud_ss_oficina_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_oficina_REV1_idx" ON aud_ss_oficina USING btree (rev);


--
-- TOC entry 2773 (class 1259 OID 52932)
-- Name: siges_clean_aud_ss_operacion_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_operacion_REV1_idx" ON aud_ss_operacion USING btree (rev);


--
-- TOC entry 2776 (class 1259 OID 52933)
-- Name: siges_clean_aud_ss_paises_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_paises_REV1_idx" ON aud_ss_paises USING btree (rev);


--
-- TOC entry 2779 (class 1259 OID 52934)
-- Name: siges_clean_aud_ss_paridades_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_paridades_REV1_idx" ON aud_ss_paridades USING btree (rev);


--
-- TOC entry 2782 (class 1259 OID 52935)
-- Name: siges_clean_aud_ss_personas_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_personas_REV1_idx" ON aud_ss_personas USING btree (rev);


--
-- TOC entry 2785 (class 1259 OID 52936)
-- Name: siges_clean_aud_ss_rel_rol_operacion_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_rel_rol_operacion_REV1_idx" ON aud_ss_rel_rol_operacion USING btree (rev);


--
-- TOC entry 2788 (class 1259 OID 52937)
-- Name: siges_clean_aud_ss_rol_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_rol_REV1_idx" ON aud_ss_rol USING btree (rev);


--
-- TOC entry 2791 (class 1259 OID 52938)
-- Name: siges_clean_aud_ss_telefonos_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_telefonos_REV1_idx" ON aud_ss_telefonos USING btree (rev);


--
-- TOC entry 2794 (class 1259 OID 52939)
-- Name: siges_clean_aud_ss_tipos_documento_persona_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_tipos_documento_persona_REV1_idx" ON aud_ss_tipos_documento_persona USING btree (rev);


--
-- TOC entry 2797 (class 1259 OID 52940)
-- Name: siges_clean_aud_ss_tipos_entrada_colectiva_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_tipos_entrada_colectiva_REV1_idx" ON aud_ss_tipos_entrada_colectiva USING btree (rev);


--
-- TOC entry 2800 (class 1259 OID 52941)
-- Name: siges_clean_aud_ss_tipos_telefono_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_tipos_telefono_REV1_idx" ON aud_ss_tipos_telefono USING btree (rev);


--
-- TOC entry 2803 (class 1259 OID 52942)
-- Name: siges_clean_aud_ss_tipos_vialidad_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_tipos_vialidad_REV1_idx" ON aud_ss_tipos_vialidad USING btree (rev);


--
-- TOC entry 2806 (class 1259 OID 52943)
-- Name: siges_clean_aud_ss_usu_ofi_roles_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_usu_ofi_roles_REV1_idx" ON aud_ss_usu_ofi_roles USING btree (rev);


--
-- TOC entry 2809 (class 1259 OID 52944)
-- Name: siges_clean_aud_ss_usuario_REV1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_aud_ss_usuario_REV1_idx" ON aud_ss_usuario USING btree (rev);


--
-- TOC entry 2810 (class 1259 OID 52945)
-- Name: siges_clean_aud_ss_usuario_usu_area_org2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_aud_ss_usuario_usu_area_org2_idx ON aud_ss_usuario USING btree (usu_area_org);


--
-- TOC entry 2815 (class 1259 OID 52946)
-- Name: siges_clean_busq_filtro_busq_filtro_org_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_busq_filtro_busq_filtro_org_fk2_idx ON busq_filtro USING btree (busq_filtro_org_fk);


--
-- TOC entry 2816 (class 1259 OID 52947)
-- Name: siges_clean_busq_filtro_busq_filtro_usu_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_busq_filtro_busq_filtro_usu_fk1_idx ON busq_filtro USING btree (busq_filtro_usu_fk);


--
-- TOC entry 2819 (class 1259 OID 52948)
-- Name: siges_clean_calidad_cal_actualizacion7_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_calidad_cal_actualizacion7_idx ON calidad USING btree (cal_actualizacion);


--
-- TOC entry 2820 (class 1259 OID 52949)
-- Name: siges_clean_calidad_cal_ent_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_calidad_cal_ent_fk1_idx ON calidad USING btree (cal_ent_fk);


--
-- TOC entry 2821 (class 1259 OID 52950)
-- Name: siges_clean_calidad_cal_prod_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_calidad_cal_prod_fk2_idx ON calidad USING btree (cal_prod_fk);


--
-- TOC entry 2822 (class 1259 OID 52951)
-- Name: siges_clean_calidad_cal_proy_fk5_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_calidad_cal_proy_fk5_idx ON calidad USING btree (cal_proy_fk);


--
-- TOC entry 2823 (class 1259 OID 52952)
-- Name: siges_clean_calidad_cal_tca_fk3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_calidad_cal_tca_fk3_idx ON calidad USING btree (cal_tca_fk);


--
-- TOC entry 2824 (class 1259 OID 52953)
-- Name: siges_clean_calidad_cal_tipo6_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_calidad_cal_tipo6_idx ON calidad USING btree (cal_tipo);


--
-- TOC entry 2825 (class 1259 OID 52954)
-- Name: siges_clean_calidad_cal_vca_fk4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_calidad_cal_vca_fk4_idx ON calidad USING btree (cal_vca_fk);


--
-- TOC entry 2832 (class 1259 OID 52955)
-- Name: siges_clean_categoria_proyectos_cat_proy_activo1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_categoria_proyectos_cat_proy_activo1_idx ON categoria_proyectos USING btree (cat_proy_activo);


--
-- TOC entry 2840 (class 1259 OID 52956)
-- Name: siges_clean_devengado_dev_adq_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_devengado_dev_adq_fk1_idx ON devengado USING btree (dev_adq_fk);


--
-- TOC entry 2841 (class 1259 OID 52957)
-- Name: siges_clean_devengado_dev_anio3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_devengado_dev_anio3_idx ON devengado USING btree (dev_anio);


--
-- TOC entry 2842 (class 1259 OID 52958)
-- Name: siges_clean_devengado_dev_mes2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_devengado_dev_mes2_idx ON devengado USING btree (dev_mes);


--
-- TOC entry 2845 (class 1259 OID 52959)
-- Name: siges_clean_doc_file_docfile_doc_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_doc_file_docfile_doc_fk1_idx ON doc_file USING btree (docfile_doc_fk);


--
-- TOC entry 2850 (class 1259 OID 52960)
-- Name: siges_clean_documentos_docs_entregable_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_documentos_docs_entregable_fk1_idx ON documentos USING btree (docs_entregable_fk);


--
-- TOC entry 2851 (class 1259 OID 52961)
-- Name: siges_clean_documentos_docs_pago_fk3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_documentos_docs_pago_fk3_idx ON documentos USING btree (docs_pago_fk);


--
-- TOC entry 2852 (class 1259 OID 52962)
-- Name: siges_clean_documentos_docs_tipodoc_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_documentos_docs_tipodoc_fk2_idx ON documentos USING btree (docs_tipodoc_fk);


--
-- TOC entry 2855 (class 1259 OID 52963)
-- Name: siges_clean_ent_hist_linea_base_enthist_ent_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ent_hist_linea_base_enthist_ent_fk1_idx ON ent_hist_linea_base USING btree (enthist_ent_fk);


--
-- TOC entry 2856 (class 1259 OID 52964)
-- Name: siges_clean_ent_hist_linea_base_enthist_replan_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ent_hist_linea_base_enthist_replan_fk2_idx ON ent_hist_linea_base USING btree (enthist_replan_fk);


--
-- TOC entry 2860 (class 1259 OID 52965)
-- Name: siges_clean_entregables_ent_coord_usu_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_entregables_ent_coord_usu_fk2_idx ON entregables USING btree (ent_coord_usu_fk);


--
-- TOC entry 2861 (class 1259 OID 52966)
-- Name: siges_clean_entregables_ent_cro_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_entregables_ent_cro_fk1_idx ON entregables USING btree (ent_cro_fk);


--
-- TOC entry 2862 (class 1259 OID 52967)
-- Name: siges_clean_entregables_ent_fin4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_entregables_ent_fin4_idx ON entregables USING btree (ent_fin);


--
-- TOC entry 2863 (class 1259 OID 52968)
-- Name: siges_clean_entregables_ent_inicio3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_entregables_ent_inicio3_idx ON entregables USING btree (ent_inicio);


--
-- TOC entry 2864 (class 1259 OID 52969)
-- Name: siges_clean_entregables_ent_progreso5_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_entregables_ent_progreso5_idx ON entregables USING btree (ent_progreso);


--
-- TOC entry 2867 (class 1259 OID 52970)
-- Name: siges_clean_estados_est_codigo1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_estados_est_codigo1_idx ON estados USING btree (est_codigo);


--
-- TOC entry 2872 (class 1259 OID 52971)
-- Name: siges_clean_etapa_eta_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_etapa_eta_org_fk1_idx ON etapa USING btree (eta_org_fk);


--
-- TOC entry 2875 (class 1259 OID 52972)
-- Name: siges_clean_fuente_financiamiento_fue_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_fuente_financiamiento_fue_org_fk1_idx ON fuente_financiamiento USING btree (fue_org_fk);


--
-- TOC entry 2878 (class 1259 OID 52973)
-- Name: siges_clean_gastos_gas_aprobado6_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_gastos_gas_aprobado6_idx ON gastos USING btree (gas_aprobado);


--
-- TOC entry 2879 (class 1259 OID 52974)
-- Name: siges_clean_gastos_gas_fecha5_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_gastos_gas_fecha5_idx ON gastos USING btree (gas_fecha);


--
-- TOC entry 2880 (class 1259 OID 52975)
-- Name: siges_clean_gastos_gas_mon_fk4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_gastos_gas_mon_fk4_idx ON gastos USING btree (gas_mon_fk);


--
-- TOC entry 2881 (class 1259 OID 52976)
-- Name: siges_clean_gastos_gas_proy_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_gastos_gas_proy_fk1_idx ON gastos USING btree (gas_proy_fk);


--
-- TOC entry 2882 (class 1259 OID 52977)
-- Name: siges_clean_gastos_gas_tipo_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_gastos_gas_tipo_fk2_idx ON gastos USING btree (gas_tipo_fk);


--
-- TOC entry 2883 (class 1259 OID 52978)
-- Name: siges_clean_gastos_gas_usu_fk3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_gastos_gas_usu_fk3_idx ON gastos USING btree (gas_usu_fk);


--
-- TOC entry 2888 (class 1259 OID 52979)
-- Name: siges_clean_interesados_int_ent_fk4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_interesados_int_ent_fk4_idx ON interesados USING btree (int_ent_fk);


--
-- TOC entry 2889 (class 1259 OID 52980)
-- Name: siges_clean_interesados_int_orga_fk3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_interesados_int_orga_fk3_idx ON interesados USING btree (int_orga_fk);


--
-- TOC entry 2890 (class 1259 OID 52981)
-- Name: siges_clean_interesados_int_pers_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_interesados_int_pers_fk2_idx ON interesados USING btree (int_pers_fk);


--
-- TOC entry 2891 (class 1259 OID 52982)
-- Name: siges_clean_interesados_int_rolint_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_interesados_int_rolint_fk1_idx ON interesados USING btree (int_rolint_fk);


--
-- TOC entry 2894 (class 1259 OID 52983)
-- Name: siges_clean_latlng_proyectos_latlang_dep_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_latlng_proyectos_latlang_dep_fk2_idx ON latlng_proyectos USING btree (latlang_dep_fk);


--
-- TOC entry 2895 (class 1259 OID 52984)
-- Name: siges_clean_latlng_proyectos_latlng_proy_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_latlng_proyectos_latlng_proy_fk1_idx ON latlng_proyectos USING btree (latlng_proy_fk);


--
-- TOC entry 2898 (class 1259 OID 52985)
-- Name: siges_clean_lecapr_areacon_lecaprcon_lecapr_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_lecapr_areacon_lecaprcon_lecapr_fk1_idx ON lecapr_areacon USING btree (lecaprcon_lecapr_fk);


--
-- TOC entry 2901 (class 1259 OID 52986)
-- Name: siges_clean_lecc_aprendidas_lecapr_activo6_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_lecc_aprendidas_lecapr_activo6_idx ON lecc_aprendidas USING btree (lecapr_activo);


--
-- TOC entry 2902 (class 1259 OID 52987)
-- Name: siges_clean_lecc_aprendidas_lecapr_fecha5_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_lecc_aprendidas_lecapr_fecha5_idx ON lecc_aprendidas USING btree (lecapr_fecha);


--
-- TOC entry 2903 (class 1259 OID 52988)
-- Name: siges_clean_lecc_aprendidas_lecapr_org_fk4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_lecc_aprendidas_lecapr_org_fk4_idx ON lecc_aprendidas USING btree (lecapr_org_fk);


--
-- TOC entry 2904 (class 1259 OID 52989)
-- Name: siges_clean_lecc_aprendidas_lecapr_proy_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_lecc_aprendidas_lecapr_proy_fk1_idx ON lecc_aprendidas USING btree (lecapr_proy_fk);


--
-- TOC entry 2905 (class 1259 OID 52990)
-- Name: siges_clean_lecc_aprendidas_lecapr_tipo_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_lecc_aprendidas_lecapr_tipo_fk2_idx ON lecc_aprendidas USING btree (lecapr_tipo_fk);


--
-- TOC entry 2906 (class 1259 OID 52991)
-- Name: siges_clean_lecc_aprendidas_lecapr_usr_fk3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_lecc_aprendidas_lecapr_usr_fk3_idx ON lecc_aprendidas USING btree (lecapr_usr_fk);


--
-- TOC entry 2909 (class 1259 OID 52992)
-- Name: siges_clean_lineabase_historico_lineabase_entFk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_lineabase_historico_lineabase_entFk1_idx" ON lineabase_historico USING btree ("lineabase_entFk");


--
-- TOC entry 2910 (class 1259 OID 52993)
-- Name: siges_clean_lineabase_historico_lineabase_entFk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "siges_clean_lineabase_historico_lineabase_entFk2_idx" ON lineabase_historico USING btree ("lineabase_entFk");


--
-- TOC entry 2913 (class 1259 OID 52994)
-- Name: siges_clean_mails_template_mail_tmp_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_mails_template_mail_tmp_org_fk1_idx ON mails_template USING btree (mail_tmp_org_fk);


--
-- TOC entry 2916 (class 1259 OID 52995)
-- Name: siges_clean_media_proyectos_media_proy_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_media_proyectos_media_proy_fk1_idx ON media_proyectos USING btree (media_proy_fk);


--
-- TOC entry 2917 (class 1259 OID 52996)
-- Name: siges_clean_media_proyectos_media_tipo_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_media_proyectos_media_tipo_fk2_idx ON media_proyectos USING btree (media_tipo_fk);


--
-- TOC entry 2918 (class 1259 OID 52997)
-- Name: siges_clean_media_proyectos_media_usr_mod_fk4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_media_proyectos_media_usr_mod_fk4_idx ON media_proyectos USING btree (media_usr_mod_fk);


--
-- TOC entry 2919 (class 1259 OID 52998)
-- Name: siges_clean_media_proyectos_media_usr_pub_fk3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_media_proyectos_media_usr_pub_fk3_idx ON media_proyectos USING btree (media_usr_pub_fk);


--
-- TOC entry 2920 (class 1259 OID 52999)
-- Name: siges_clean_media_proyectos_media_usr_rech_fk5_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_media_proyectos_media_usr_rech_fk5_idx ON media_proyectos USING btree (media_usr_rech_fk);


--
-- TOC entry 2927 (class 1259 OID 53000)
-- Name: siges_clean_notificacion_envio_notenv_fecha1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_notificacion_envio_notenv_fecha1_idx ON notificacion_envio USING btree (notenv_fecha);


--
-- TOC entry 2930 (class 1259 OID 53001)
-- Name: siges_clean_notificacion_instancia_notinst_not_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_notificacion_instancia_notinst_not_fk1_idx ON notificacion_instancia USING btree (notinst_not_fk);


--
-- TOC entry 2931 (class 1259 OID 53002)
-- Name: siges_clean_notificacion_instancia_notinst_proy_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_notificacion_instancia_notinst_proy_fk2_idx ON notificacion_instancia USING btree (notinst_proy_fk);


--
-- TOC entry 2939 (class 1259 OID 53003)
-- Name: siges_clean_organi_int_prove_orga_amb_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_organi_int_prove_orga_amb_fk2_idx ON organi_int_prove USING btree (orga_amb_fk);


--
-- TOC entry 2940 (class 1259 OID 53004)
-- Name: siges_clean_organi_int_prove_orga_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_organi_int_prove_orga_org_fk1_idx ON organi_int_prove USING btree (orga_org_fk);


--
-- TOC entry 2941 (class 1259 OID 53005)
-- Name: siges_clean_organi_int_prove_orga_proveedor3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_organi_int_prove_orga_proveedor3_idx ON organi_int_prove USING btree (orga_proveedor);


--
-- TOC entry 2947 (class 1259 OID 53006)
-- Name: siges_clean_pagos_pag_adq_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_pagos_pag_adq_fk2_idx ON pagos USING btree (pag_adq_fk);


--
-- TOC entry 2948 (class 1259 OID 53007)
-- Name: siges_clean_pagos_pag_confirmar5_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_pagos_pag_confirmar5_idx ON pagos USING btree (pag_confirmar);


--
-- TOC entry 2949 (class 1259 OID 53008)
-- Name: siges_clean_pagos_pag_ent_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_pagos_pag_ent_fk1_idx ON pagos USING btree (pag_ent_fk);


--
-- TOC entry 2950 (class 1259 OID 53009)
-- Name: siges_clean_pagos_pag_fecha_planificada3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_pagos_pag_fecha_planificada3_idx ON pagos USING btree (pag_fecha_planificada);


--
-- TOC entry 2951 (class 1259 OID 53010)
-- Name: siges_clean_pagos_pag_fecha_real4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_pagos_pag_fecha_real4_idx ON pagos USING btree (pag_fecha_real);


--
-- TOC entry 2954 (class 1259 OID 53011)
-- Name: siges_clean_participantes_part_activo4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_participantes_part_activo4_idx ON participantes USING btree (part_activo);


--
-- TOC entry 2955 (class 1259 OID 53012)
-- Name: siges_clean_participantes_part_ent_fk3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_participantes_part_ent_fk3_idx ON participantes USING btree (part_ent_fk);


--
-- TOC entry 2956 (class 1259 OID 53013)
-- Name: siges_clean_participantes_part_proy_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_participantes_part_proy_fk2_idx ON participantes USING btree (part_proy_fk);


--
-- TOC entry 2957 (class 1259 OID 53014)
-- Name: siges_clean_participantes_part_usu_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX siges_clean_participantes_part_usu_fk1_idx ON participantes USING btree (part_usu_fk, part_proy_fk);


--
-- TOC entry 2960 (class 1259 OID 53015)
-- Name: siges_clean_personas_pers_orga_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_personas_pers_orga_fk2_idx ON personas USING btree (pers_orga_fk);


--
-- TOC entry 2961 (class 1259 OID 53016)
-- Name: siges_clean_personas_pers_rol_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_personas_pers_rol_fk1_idx ON personas USING btree (pers_rol_fk);


--
-- TOC entry 2968 (class 1259 OID 53017)
-- Name: siges_clean_plantilla_cronograma_p_crono_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_plantilla_cronograma_p_crono_org_fk1_idx ON plantilla_cronograma USING btree (p_crono_org_fk);


--
-- TOC entry 2971 (class 1259 OID 53018)
-- Name: siges_clean_plantilla_entregables_p_entregable_ant_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_plantilla_entregables_p_entregable_ant_fk2_idx ON plantilla_entregables USING btree (p_entregable_ant_fk);


--
-- TOC entry 2972 (class 1259 OID 53019)
-- Name: siges_clean_plantilla_entregables_p_entregable_p_cro_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_plantilla_entregables_p_entregable_p_cro_fk1_idx ON plantilla_entregables USING btree (p_entregable_p_cro_fk);


--
-- TOC entry 2975 (class 1259 OID 53020)
-- Name: siges_clean_presupuesto_pre_fuente_organi_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_presupuesto_pre_fuente_organi_fk1_idx ON presupuesto USING btree (pre_fuente_organi_fk);


--
-- TOC entry 2976 (class 1259 OID 53021)
-- Name: siges_clean_presupuesto_pre_moneda2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_presupuesto_pre_moneda2_idx ON presupuesto USING btree (pre_moneda);


--
-- TOC entry 2979 (class 1259 OID 53022)
-- Name: siges_clean_prod_mes_prodmes_anio3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_prod_mes_prodmes_anio3_idx ON prod_mes USING btree (prodmes_anio);


--
-- TOC entry 2980 (class 1259 OID 53023)
-- Name: siges_clean_prod_mes_prodmes_mes2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_prod_mes_prodmes_mes2_idx ON prod_mes USING btree (prodmes_mes);


--
-- TOC entry 2981 (class 1259 OID 53024)
-- Name: siges_clean_prod_mes_prodmes_prod_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_prod_mes_prodmes_prod_fk1_idx ON prod_mes USING btree (prodmes_prod_fk);


--
-- TOC entry 2984 (class 1259 OID 53025)
-- Name: siges_clean_productos_prod_ent_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_productos_prod_ent_fk1_idx ON productos USING btree (prod_ent_fk);


--
-- TOC entry 2991 (class 1259 OID 53026)
-- Name: siges_clean_prog_docs_obl_progdocsobl_prog_pk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_prog_docs_obl_progdocsobl_prog_pk1_idx ON prog_docs_obl USING btree (progdocsobl_prog_pk);


--
-- TOC entry 2987 (class 1259 OID 53027)
-- Name: siges_clean_prog_docs_progdocs_doc_pk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_prog_docs_progdocs_doc_pk1_idx ON prog_docs USING btree (progdocs_doc_pk);


--
-- TOC entry 2988 (class 1259 OID 53028)
-- Name: siges_clean_prog_docs_progdocs_prog_pk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_prog_docs_progdocs_prog_pk2_idx ON prog_docs USING btree (progdocs_prog_pk);


--
-- TOC entry 2996 (class 1259 OID 53029)
-- Name: siges_clean_prog_indices_pre_progindpre_progind_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_prog_indices_pre_progindpre_progind_fk1_idx ON prog_indices_pre USING btree (progindpre_progind_fk);


--
-- TOC entry 2999 (class 1259 OID 53030)
-- Name: siges_clean_prog_int_progint_int_pk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_prog_int_progint_int_pk1_idx ON prog_int USING btree (progint_int_pk);


--
-- TOC entry 3002 (class 1259 OID 53031)
-- Name: siges_clean_prog_lectura_area_proglectarea_area_pk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_prog_lectura_area_proglectarea_area_pk1_idx ON prog_lectura_area USING btree (proglectarea_area_pk);


--
-- TOC entry 3005 (class 1259 OID 53032)
-- Name: siges_clean_prog_pre_progpre_pre_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_prog_pre_progpre_pre_fk1_idx ON prog_pre USING btree (progpre_pre_fk);


--
-- TOC entry 3008 (class 1259 OID 53033)
-- Name: siges_clean_prog_tags_progtag_area_pk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_prog_tags_progtag_area_pk1_idx ON prog_tags USING btree (progtag_area_pk);


--
-- TOC entry 3012 (class 1259 OID 53034)
-- Name: siges_clean_programas_prog_activo15_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_programas_prog_activo15_idx ON programas USING btree (prog_activo);


--
-- TOC entry 3013 (class 1259 OID 53035)
-- Name: siges_clean_programas_prog_area_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_programas_prog_area_fk1_idx ON programas USING btree (prog_area_fk);


--
-- TOC entry 3014 (class 1259 OID 53036)
-- Name: siges_clean_programas_prog_cro_fk9_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_programas_prog_cro_fk9_idx ON programas USING btree (prog_cro_fk);


--
-- TOC entry 3015 (class 1259 OID 53037)
-- Name: siges_clean_programas_prog_est_fk3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_programas_prog_est_fk3_idx ON programas USING btree (prog_est_fk);


--
-- TOC entry 3016 (class 1259 OID 53038)
-- Name: siges_clean_programas_prog_est_pendiente_fk12_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_programas_prog_est_pendiente_fk12_idx ON programas USING btree (prog_est_pendiente_fk);


--
-- TOC entry 3017 (class 1259 OID 53039)
-- Name: siges_clean_programas_prog_fecha_act17_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_programas_prog_fecha_act17_idx ON programas USING btree (prog_fecha_act);


--
-- TOC entry 3018 (class 1259 OID 53040)
-- Name: siges_clean_programas_prog_fecha_crea16_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_programas_prog_fecha_crea16_idx ON programas USING btree (prog_fecha_crea);


--
-- TOC entry 3019 (class 1259 OID 53041)
-- Name: siges_clean_programas_prog_nombre13_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_programas_prog_nombre13_idx ON programas USING btree (prog_nombre);


--
-- TOC entry 3020 (class 1259 OID 53042)
-- Name: siges_clean_programas_prog_org_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_programas_prog_org_fk2_idx ON programas USING btree (prog_org_fk);


--
-- TOC entry 3021 (class 1259 OID 53043)
-- Name: siges_clean_programas_prog_pre_fk14_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_programas_prog_pre_fk14_idx ON programas USING btree (prog_pre_fk);


--
-- TOC entry 3022 (class 1259 OID 53044)
-- Name: siges_clean_programas_prog_progindices_fk11_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_programas_prog_progindices_fk11_idx ON programas USING btree (prog_progindices_fk);


--
-- TOC entry 3023 (class 1259 OID 53045)
-- Name: siges_clean_programas_prog_usr_adjunto_fk7_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_programas_prog_usr_adjunto_fk7_idx ON programas USING btree (prog_usr_adjunto_fk);


--
-- TOC entry 3024 (class 1259 OID 53046)
-- Name: siges_clean_programas_prog_usr_gerente_fk4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_programas_prog_usr_gerente_fk4_idx ON programas USING btree (prog_usr_gerente_fk);


--
-- TOC entry 3025 (class 1259 OID 53047)
-- Name: siges_clean_programas_prog_usr_pmofed_fk6_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_programas_prog_usr_pmofed_fk6_idx ON programas USING btree (prog_usr_pmofed_fk);


--
-- TOC entry 3026 (class 1259 OID 53048)
-- Name: siges_clean_programas_prog_usr_sponsor_fk5_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_programas_prog_usr_sponsor_fk5_idx ON programas USING btree (prog_usr_sponsor_fk);


--
-- TOC entry 3029 (class 1259 OID 53049)
-- Name: siges_clean_proy_docs_proydoc_doc_pk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_docs_proydoc_doc_pk1_idx ON proy_docs USING btree (proydoc_doc_pk);


--
-- TOC entry 3030 (class 1259 OID 53050)
-- Name: siges_clean_proy_docs_proydoc_proy_pk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_docs_proydoc_proy_pk2_idx ON proy_docs USING btree (proydoc_proy_pk);


--
-- TOC entry 3035 (class 1259 OID 53051)
-- Name: siges_clean_proy_indices_proyind_periodo_fin2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_indices_proyind_periodo_fin2_idx ON proy_indices USING btree (proyind_periodo_fin);


--
-- TOC entry 3036 (class 1259 OID 53052)
-- Name: siges_clean_proy_indices_proyind_periodo_inicio1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_indices_proyind_periodo_inicio1_idx ON proy_indices USING btree (proyind_periodo_inicio);


--
-- TOC entry 3041 (class 1259 OID 53053)
-- Name: siges_clean_proy_int_proyint_int_pk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_int_proyint_int_pk1_idx ON proy_int USING btree (proyint_int_pk);


--
-- TOC entry 3044 (class 1259 OID 53054)
-- Name: siges_clean_proy_lectura_area_proglectarea_area_pk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_lectura_area_proglectarea_area_pk1_idx ON proy_lectura_area USING btree (proglectarea_area_pk);


--
-- TOC entry 3045 (class 1259 OID 53055)
-- Name: siges_clean_proy_lectura_area_proglectarea_proy_pk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_lectura_area_proglectarea_proy_pk2_idx ON proy_lectura_area USING btree (proglectarea_proy_pk);


--
-- TOC entry 3048 (class 1259 OID 53056)
-- Name: siges_clean_proy_otros_cat_secundarias_proy_cat_cat_proy_fk1_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_otros_cat_secundarias_proy_cat_cat_proy_fk1_id ON proy_otros_cat_secundarias USING btree (proy_cat_cat_proy_fk);


--
-- TOC entry 3051 (class 1259 OID 53057)
-- Name: siges_clean_proy_otros_datos_proy_otr_cat_fk4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_otros_datos_proy_otr_cat_fk4_idx ON proy_otros_datos USING btree (proy_otr_cat_fk);


--
-- TOC entry 3052 (class 1259 OID 53058)
-- Name: siges_clean_proy_otros_datos_proy_otr_cont_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_otros_datos_proy_otr_cont_fk1_idx ON proy_otros_datos USING btree (proy_otr_cont_fk);


--
-- TOC entry 3053 (class 1259 OID 53059)
-- Name: siges_clean_proy_otros_datos_proy_otr_ent_fk3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_otros_datos_proy_otr_ent_fk3_idx ON proy_otros_datos USING btree (proy_otr_ent_fk);


--
-- TOC entry 3054 (class 1259 OID 53060)
-- Name: siges_clean_proy_otros_datos_proy_otr_est_pub_fk5_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_otros_datos_proy_otr_est_pub_fk5_idx ON proy_otros_datos USING btree (proy_otr_est_pub_fk);


--
-- TOC entry 3055 (class 1259 OID 53061)
-- Name: siges_clean_proy_otros_datos_proy_otr_eta_fk6_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_otros_datos_proy_otr_eta_fk6_idx ON proy_otros_datos USING btree (proy_otr_eta_fk);


--
-- TOC entry 3056 (class 1259 OID 53062)
-- Name: siges_clean_proy_otros_datos_proy_otr_ins_eje_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_otros_datos_proy_otr_ins_eje_fk2_idx ON proy_otros_datos USING btree (proy_otr_ins_eje_fk);


--
-- TOC entry 3059 (class 1259 OID 53063)
-- Name: siges_clean_proy_pre_proypre_pre_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_pre_proypre_pre_fk1_idx ON proy_pre USING btree (proypre_pre_fk);


--
-- TOC entry 3066 (class 1259 OID 53064)
-- Name: siges_clean_proy_replanificacion_proyreplan_activo3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_replanificacion_proyreplan_activo3_idx ON proy_replanificacion USING btree (proyreplan_activo);


--
-- TOC entry 3067 (class 1259 OID 53065)
-- Name: siges_clean_proy_replanificacion_proyreplan_fecha2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_replanificacion_proyreplan_fecha2_idx ON proy_replanificacion USING btree (proyreplan_fecha);


--
-- TOC entry 3068 (class 1259 OID 53066)
-- Name: siges_clean_proy_replanificacion_proyreplan_proy_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_replanificacion_proyreplan_proy_fk1_idx ON proy_replanificacion USING btree (proyreplan_proy_fk);


--
-- TOC entry 3071 (class 1259 OID 53067)
-- Name: siges_clean_proy_sitact_historico_proy_sitact_proy_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_sitact_historico_proy_sitact_proy_fk1_idx ON proy_sitact_historico USING btree (proy_sitact_proy_fk);


--
-- TOC entry 3072 (class 1259 OID 53068)
-- Name: siges_clean_proy_sitact_historico_proy_sitact_usu_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_sitact_historico_proy_sitact_usu_fk2_idx ON proy_sitact_historico USING btree (proy_sitact_usu_fk);


--
-- TOC entry 3075 (class 1259 OID 53069)
-- Name: siges_clean_proy_tags_proytag_area_pk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proy_tags_proytag_area_pk1_idx ON proy_tags USING btree (proytag_area_pk);


--
-- TOC entry 3079 (class 1259 OID 53070)
-- Name: siges_clean_proyectos_proy_activo17_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_activo17_idx ON proyectos USING btree (proy_activo);


--
-- TOC entry 3080 (class 1259 OID 53071)
-- Name: siges_clean_proyectos_proy_area_fk5_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_area_fk5_idx ON proyectos USING btree (proy_area_fk);


--
-- TOC entry 3081 (class 1259 OID 53072)
-- Name: siges_clean_proyectos_proy_cro_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_cro_fk1_idx ON proyectos USING btree (proy_cro_fk);


--
-- TOC entry 3082 (class 1259 OID 53073)
-- Name: siges_clean_proyectos_proy_est_fk3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_est_fk3_idx ON proyectos USING btree (proy_est_fk);


--
-- TOC entry 3083 (class 1259 OID 53074)
-- Name: siges_clean_proyectos_proy_est_pendiente_fk15_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_est_pendiente_fk15_idx ON proyectos USING btree (proy_est_pendiente_fk);


--
-- TOC entry 3084 (class 1259 OID 53075)
-- Name: siges_clean_proyectos_proy_fecha_act19_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_fecha_act19_idx ON proyectos USING btree (proy_fecha_act);


--
-- TOC entry 3085 (class 1259 OID 53076)
-- Name: siges_clean_proyectos_proy_fecha_crea18_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_fecha_crea18_idx ON proyectos USING btree (proy_fecha_crea);


--
-- TOC entry 3086 (class 1259 OID 53077)
-- Name: siges_clean_proyectos_proy_latlng_fk22_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_latlng_fk22_idx ON proyectos USING btree (proy_latlng_fk);


--
-- TOC entry 3087 (class 1259 OID 53078)
-- Name: siges_clean_proyectos_proy_nombre13_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_nombre13_idx ON proyectos USING btree (proy_nombre);


--
-- TOC entry 3088 (class 1259 OID 53079)
-- Name: siges_clean_proyectos_proy_org_fk4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_org_fk4_idx ON proyectos USING btree (proy_org_fk);


--
-- TOC entry 3089 (class 1259 OID 53080)
-- Name: siges_clean_proyectos_proy_otr_dat_fk21_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_otr_dat_fk21_idx ON proyectos USING btree (proy_otr_dat_fk);


--
-- TOC entry 3090 (class 1259 OID 53081)
-- Name: siges_clean_proyectos_proy_pre_fk12_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_pre_fk12_idx ON proyectos USING btree (proy_pre_fk);


--
-- TOC entry 3091 (class 1259 OID 53082)
-- Name: siges_clean_proyectos_proy_prog_fk6_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_prog_fk6_idx ON proyectos USING btree (proy_prog_fk);


--
-- TOC entry 3092 (class 1259 OID 53083)
-- Name: siges_clean_proyectos_proy_proyindices_fk14_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_proyindices_fk14_idx ON proyectos USING btree (proy_proyindices_fk);


--
-- TOC entry 3093 (class 1259 OID 53084)
-- Name: siges_clean_proyectos_proy_publicable20_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_publicable20_idx ON proyectos USING btree (proy_publicable);


--
-- TOC entry 3094 (class 1259 OID 53085)
-- Name: siges_clean_proyectos_proy_risk_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_risk_fk2_idx ON proyectos USING btree (proy_risk_fk);


--
-- TOC entry 3095 (class 1259 OID 53086)
-- Name: siges_clean_proyectos_proy_usr_adjunto_fk11_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_usr_adjunto_fk11_idx ON proyectos USING btree (proy_usr_adjunto_fk);


--
-- TOC entry 3096 (class 1259 OID 53087)
-- Name: siges_clean_proyectos_proy_usr_gerente_fk16_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_usr_gerente_fk16_idx ON proyectos USING btree (proy_usr_gerente_fk);


--
-- TOC entry 3097 (class 1259 OID 53088)
-- Name: siges_clean_proyectos_proy_usr_pmofed_fk7_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_usr_pmofed_fk7_idx ON proyectos USING btree (proy_usr_pmofed_fk);


--
-- TOC entry 3098 (class 1259 OID 53089)
-- Name: siges_clean_proyectos_proy_usr_sponsor_fk10_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_proyectos_proy_usr_sponsor_fk10_idx ON proyectos USING btree (proy_usr_sponsor_fk);


--
-- TOC entry 3101 (class 1259 OID 53090)
-- Name: siges_clean_registros_horas_rh_aprobado5_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_registros_horas_rh_aprobado5_idx ON registros_horas USING btree (rh_aprobado);


--
-- TOC entry 3102 (class 1259 OID 53091)
-- Name: siges_clean_registros_horas_rh_ent_fk3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_registros_horas_rh_ent_fk3_idx ON registros_horas USING btree (rh_ent_fk);


--
-- TOC entry 3103 (class 1259 OID 53092)
-- Name: siges_clean_registros_horas_rh_fecha4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_registros_horas_rh_fecha4_idx ON registros_horas USING btree (rh_fecha);


--
-- TOC entry 3104 (class 1259 OID 53093)
-- Name: siges_clean_registros_horas_rh_proy_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_registros_horas_rh_proy_fk2_idx ON registros_horas USING btree (rh_proy_fk);


--
-- TOC entry 3105 (class 1259 OID 53094)
-- Name: siges_clean_registros_horas_rh_usu_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_registros_horas_rh_usu_fk1_idx ON registros_horas USING btree (rh_usu_fk);


--
-- TOC entry 3110 (class 1259 OID 53095)
-- Name: siges_clean_riesgos_risk_ent_fk5_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_riesgos_risk_ent_fk5_idx ON riesgos USING btree (risk_ent_fk);


--
-- TOC entry 3111 (class 1259 OID 53096)
-- Name: siges_clean_riesgos_risk_fecha_actu6_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_riesgos_risk_fecha_actu6_idx ON riesgos USING btree (risk_fecha_actu);


--
-- TOC entry 3112 (class 1259 OID 53097)
-- Name: siges_clean_riesgos_risk_fecha_limite7_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_riesgos_risk_fecha_limite7_idx ON riesgos USING btree (risk_fecha_limite);


--
-- TOC entry 3113 (class 1259 OID 53098)
-- Name: siges_clean_riesgos_risk_fecha_superado8_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_riesgos_risk_fecha_superado8_idx ON riesgos USING btree (risk_fecha_superado);


--
-- TOC entry 3114 (class 1259 OID 53099)
-- Name: siges_clean_riesgos_risk_proy_fk4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_riesgos_risk_proy_fk4_idx ON riesgos USING btree (risk_proy_fk);


--
-- TOC entry 3115 (class 1259 OID 53100)
-- Name: siges_clean_riesgos_risk_superado3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_riesgos_risk_superado3_idx ON riesgos USING btree (risk_superado);


--
-- TOC entry 3116 (class 1259 OID 53101)
-- Name: siges_clean_riesgos_risk_usuario_superado_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_riesgos_risk_usuario_superado_fk2_idx ON riesgos USING btree (risk_usuario_superado_fk);


--
-- TOC entry 3119 (class 1259 OID 53102)
-- Name: siges_clean_roles_interesados_rolint_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_roles_interesados_rolint_org_fk1_idx ON roles_interesados USING btree (rolint_org_fk);


--
-- TOC entry 3127 (class 1259 OID 53103)
-- Name: siges_clean_ss_ayuda_ayu_codigo1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX siges_clean_ss_ayuda_ayu_codigo1_idx ON ss_ayuda USING btree (ayu_codigo);


--
-- TOC entry 3132 (class 1259 OID 53104)
-- Name: siges_clean_ss_configuraciones_cnf_codigo2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_configuraciones_cnf_codigo2_idx ON ss_configuraciones USING btree (cnf_codigo);


--
-- TOC entry 3133 (class 1259 OID 53105)
-- Name: siges_clean_ss_configuraciones_cnf_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_configuraciones_cnf_org_fk1_idx ON ss_configuraciones USING btree (cnf_org_fk);


--
-- TOC entry 3138 (class 1259 OID 53106)
-- Name: siges_clean_ss_domicilios_dom_depto_id6_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_domicilios_dom_depto_id6_idx ON ss_domicilios USING btree (dom_depto_id);


--
-- TOC entry 3139 (class 1259 OID 53107)
-- Name: siges_clean_ss_domicilios_dom_loc_id5_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_domicilios_dom_loc_id5_idx ON ss_domicilios USING btree (dom_loc_id);


--
-- TOC entry 3140 (class 1259 OID 53108)
-- Name: siges_clean_ss_domicilios_dom_pai_id2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_domicilios_dom_pai_id2_idx ON ss_domicilios USING btree (dom_pai_id);


--
-- TOC entry 3141 (class 1259 OID 53109)
-- Name: siges_clean_ss_domicilios_dom_par_id3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_domicilios_dom_par_id3_idx ON ss_domicilios USING btree (dom_par_id);


--
-- TOC entry 3142 (class 1259 OID 53110)
-- Name: siges_clean_ss_domicilios_dom_tec_id1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_domicilios_dom_tec_id1_idx ON ss_domicilios USING btree (dom_tec_id);


--
-- TOC entry 3143 (class 1259 OID 53111)
-- Name: siges_clean_ss_domicilios_dom_tvi_id4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_domicilios_dom_tvi_id4_idx ON ss_domicilios USING btree (dom_tvi_id);


--
-- TOC entry 3148 (class 1259 OID 53112)
-- Name: siges_clean_ss_localidades_loc_depto_id1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_localidades_loc_depto_id1_idx ON ss_localidades USING btree (loc_depto_id);


--
-- TOC entry 3151 (class 1259 OID 53113)
-- Name: siges_clean_ss_noticias_not_codigo1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX siges_clean_ss_noticias_not_codigo1_idx ON ss_noticias USING btree (not_codigo);


--
-- TOC entry 3154 (class 1259 OID 53114)
-- Name: siges_clean_ss_operacion_ope_categoria_id1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_operacion_ope_categoria_id1_idx ON ss_operacion USING btree (ope_categoria_id);


--
-- TOC entry 3161 (class 1259 OID 53115)
-- Name: siges_clean_ss_personas_per_pais_doc1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_personas_per_pais_doc1_idx ON ss_personas USING btree (per_pais_doc);


--
-- TOC entry 3162 (class 1259 OID 53116)
-- Name: siges_clean_ss_personas_per_tipo_doc2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_personas_per_tipo_doc2_idx ON ss_personas USING btree (per_tipo_doc);


--
-- TOC entry 3165 (class 1259 OID 53117)
-- Name: siges_clean_ss_rel_rol_operacion_rel_rol_operacion_operacion_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_rel_rol_operacion_rel_rol_operacion_operacion_id ON ss_rel_rol_operacion USING btree (rel_rol_operacion_operacion_id);


--
-- TOC entry 3166 (class 1259 OID 53118)
-- Name: siges_clean_ss_rel_rol_operacion_rel_rol_operacion_rol_id2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_rel_rol_operacion_rel_rol_operacion_rol_id2_idx ON ss_rel_rol_operacion USING btree (rel_rol_operacion_rol_id);


--
-- TOC entry 3171 (class 1259 OID 53119)
-- Name: siges_clean_ss_telefonos_tel_tiptel_id1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_telefonos_tel_tiptel_id1_idx ON ss_telefonos USING btree (tel_tiptel_id);


--
-- TOC entry 3182 (class 1259 OID 53120)
-- Name: siges_clean_ss_usu_ofi_roles_usu_ofi_roles_rol1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_usu_ofi_roles_usu_ofi_roles_rol1_idx ON ss_usu_ofi_roles USING btree (usu_ofi_roles_rol);


--
-- TOC entry 3183 (class 1259 OID 53121)
-- Name: siges_clean_ss_usu_ofi_roles_usu_ofi_roles_usu_area3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_usu_ofi_roles_usu_ofi_roles_usu_area3_idx ON ss_usu_ofi_roles USING btree (usu_ofi_roles_usu_area);


--
-- TOC entry 3184 (class 1259 OID 53122)
-- Name: siges_clean_ss_usu_ofi_roles_usu_ofi_roles_usuario2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_usu_ofi_roles_usu_ofi_roles_usuario2_idx ON ss_usu_ofi_roles USING btree (usu_ofi_roles_usuario);


--
-- TOC entry 3187 (class 1259 OID 53123)
-- Name: siges_clean_ss_usuario_usu_area_org2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_usuario_usu_area_org2_idx ON ss_usuario USING btree (usu_area_org);


--
-- TOC entry 3188 (class 1259 OID 53124)
-- Name: siges_clean_ss_usuario_usu_cod1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_ss_usuario_usu_cod1_idx ON ss_usuario USING btree (usu_cod);


--
-- TOC entry 3194 (class 1259 OID 53125)
-- Name: siges_clean_temas_calidad_tca_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_temas_calidad_tca_org_fk1_idx ON temas_calidad USING btree (tca_org_fk);


--
-- TOC entry 3200 (class 1259 OID 53126)
-- Name: siges_clean_tipo_documento_instancia_tipodoc_inst_tipodoc_fk1_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_tipo_documento_instancia_tipodoc_inst_tipodoc_fk1_i ON tipo_documento_instancia USING btree (tipodoc_inst_tipodoc_fk);


--
-- TOC entry 3197 (class 1259 OID 53127)
-- Name: siges_clean_tipo_documento_tipodoc_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_tipo_documento_tipodoc_org_fk1_idx ON tipo_documento USING btree (tipodoc_org_fk);


--
-- TOC entry 3203 (class 1259 OID 53128)
-- Name: siges_clean_tipo_gasto_tipogas_org_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_tipo_gasto_tipogas_org_fk1_idx ON tipo_gasto USING btree (tipogas_org_fk);


--
-- TOC entry 3206 (class 1259 OID 53129)
-- Name: siges_clean_tipo_leccion_tipolec_codigo1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_tipo_leccion_tipolec_codigo1_idx ON tipo_leccion USING btree (tipolec_codigo);


--
-- TOC entry 3211 (class 1259 OID 53130)
-- Name: siges_clean_valor_calidad_codigos_vca_codigo1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_valor_calidad_codigos_vca_codigo1_idx ON valor_calidad_codigos USING btree (vca_codigo);


--
-- TOC entry 3212 (class 1259 OID 53131)
-- Name: siges_clean_valor_calidad_codigos_vca_habilitado2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_valor_calidad_codigos_vca_habilitado2_idx ON valor_calidad_codigos USING btree (vca_habilitado);


--
-- TOC entry 3215 (class 1259 OID 53132)
-- Name: siges_clean_valor_hora_val_hor_anio4_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_valor_hora_val_hor_anio4_idx ON valor_hora USING btree (val_hor_anio);


--
-- TOC entry 3216 (class 1259 OID 53133)
-- Name: siges_clean_valor_hora_val_hor_mon_fk3_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_valor_hora_val_hor_mon_fk3_idx ON valor_hora USING btree (val_hor_mon_fk);


--
-- TOC entry 3217 (class 1259 OID 53134)
-- Name: siges_clean_valor_hora_val_hor_org_fk2_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_valor_hora_val_hor_org_fk2_idx ON valor_hora USING btree (val_hor_org_fk);


--
-- TOC entry 3218 (class 1259 OID 53135)
-- Name: siges_clean_valor_hora_val_hor_usu_fk1_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX siges_clean_valor_hora_val_hor_usu_fk1_idx ON valor_hora USING btree (val_hor_usu_fk);


--
-- TOC entry 3122 (class 1259 OID 53136)
-- Name: sql_build_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX sql_build_idx ON sql_executed USING btree (sql_build);


--
-- TOC entry 3125 (class 1259 OID 53137)
-- Name: sql_ver_mayor_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX sql_ver_mayor_idx ON sql_executed USING btree (sql_ver_mayor);


--
-- TOC entry 3126 (class 1259 OID 53138)
-- Name: sql_ver_menor_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX sql_ver_menor_idx ON sql_executed USING btree (sql_ver_menor);


--
-- TOC entry 3191 (class 1259 OID 53139)
-- Name: usu_token_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX usu_token_idx ON ss_usuario USING btree (usu_token);


--
-- TOC entry 3225 (class 2606 OID 53140)
-- Name: areas_tags_areatag_padre_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY areas_tags
    ADD CONSTRAINT areas_tags_areatag_padre_fk_fkey FOREIGN KEY (areatag_padre_fk) REFERENCES areas_tags(arastag_pk);


--
-- TOC entry 3231 (class 2606 OID 53145)
-- Name: devengado_dev_adq_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY devengado
    ADD CONSTRAINT devengado_dev_adq_fk_fkey FOREIGN KEY (dev_adq_fk) REFERENCES adquisicion(adq_pk) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3227 (class 2606 OID 56687)
-- Name: fk_4b0pq8qh2f6u7ei11lh0atbf8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY busq_filtro
    ADD CONSTRAINT fk_4b0pq8qh2f6u7ei11lh0atbf8 FOREIGN KEY (busq_filtro_usu_fk) REFERENCES ss_usuario(usu_id);


--
-- TOC entry 3230 (class 2606 OID 56692)
-- Name: fk_4t53ltja1415bq3d23c3kdjl1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria_proyectos
    ADD CONSTRAINT fk_4t53ltja1415bq3d23c3kdjl1 FOREIGN KEY (cat_org_fk) REFERENCES organismos(org_pk);


--
-- TOC entry 3232 (class 2606 OID 56697)
-- Name: fk_9c8og633e1waprs81i6ayorba; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY devengado
    ADD CONSTRAINT fk_9c8og633e1waprs81i6ayorba FOREIGN KEY (dev_adq_fk) REFERENCES adquisicion(adq_pk);


--
-- TOC entry 3226 (class 2606 OID 56702)
-- Name: fk_a726jrgroi6p90dip38n70ftp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY busq_filtro
    ADD CONSTRAINT fk_a726jrgroi6p90dip38n70ftp FOREIGN KEY (busq_filtro_org_fk) REFERENCES organismos(org_pk);


--
-- TOC entry 3229 (class 2606 OID 56707)
-- Name: fk_hb3weyc8xvdrbp62k3u1halnb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria_proyectos
    ADD CONSTRAINT fk_hb3weyc8xvdrbp62k3u1halnb FOREIGN KEY (cat_icono_marker) REFERENCES image(image_pk);


--
-- TOC entry 3228 (class 2606 OID 56712)
-- Name: fk_hh3lr9l8qt7isgvniyif3w6tw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria_proyectos
    ADD CONSTRAINT fk_hh3lr9l8qt7isgvniyif3w6tw FOREIGN KEY (cat_icono) REFERENCES image(image_pk);


--
-- TOC entry 3224 (class 2606 OID 56717)
-- Name: fk_j151q3d1wiqgx10w9n92hwx5j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY areas
    ADD CONSTRAINT fk_j151q3d1wiqgx10w9n92hwx5j FOREIGN KEY (area_director) REFERENCES ss_usuario(usu_id);


--
-- TOC entry 3223 (class 2606 OID 56722)
-- Name: fk_n5tl58dqv18cs790jbmejxom2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ambito
    ADD CONSTRAINT fk_n5tl58dqv18cs790jbmejxom2 FOREIGN KEY (amb_org_fk) REFERENCES organismos(org_pk);


--
-- TOC entry 3233 (class 2606 OID 56727)
-- Name: fk_n76rhuste8gi3p3jq7m91j7iq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY doc_file
    ADD CONSTRAINT fk_n76rhuste8gi3p3jq7m91j7iq FOREIGN KEY (docfile_doc_fk) REFERENCES documentos(docs_pk);


--
-- TOC entry 3234 (class 2606 OID 53150)
-- Name: latlng_proyectos_latlang_dep_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY latlng_proyectos
    ADD CONSTRAINT latlng_proyectos_latlang_dep_fk_fkey FOREIGN KEY (latlang_dep_fk) REFERENCES departamentos(dep_pk);


--
-- TOC entry 3235 (class 2606 OID 53155)
-- Name: lecapr_areacon_lecaprcon_con_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lecapr_areacon
    ADD CONSTRAINT lecapr_areacon_lecaprcon_con_fk_fkey FOREIGN KEY (lecaprcon_con_fk) REFERENCES area_conocimiento(con_pk);


--
-- TOC entry 3236 (class 2606 OID 53160)
-- Name: lineabase_historico_lineabase_entFk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lineabase_historico
    ADD CONSTRAINT "lineabase_historico_lineabase_entFk_fkey" FOREIGN KEY ("lineabase_entFk") REFERENCES entregables(ent_pk) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3237 (class 2606 OID 53165)
-- Name: notificacion_instancia_notinst_not_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificacion_instancia
    ADD CONSTRAINT notificacion_instancia_notinst_not_fk_fkey FOREIGN KEY (notinst_not_fk) REFERENCES notificacion(not_pk);


--
-- TOC entry 3238 (class 2606 OID 53170)
-- Name: obj_est_org_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY objetivos_estrategicos
    ADD CONSTRAINT obj_est_org_fk FOREIGN KEY (obj_est_org_fk) REFERENCES organismos(org_pk);


--
-- TOC entry 3239 (class 2606 OID 53175)
-- Name: organi_int_prove_orga_amb_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organi_int_prove
    ADD CONSTRAINT organi_int_prove_orga_amb_fk_fkey FOREIGN KEY (orga_amb_fk) REFERENCES ambito(amb_pk);


--
-- TOC entry 3240 (class 2606 OID 53180)
-- Name: pagos_pag_adq_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pagos
    ADD CONSTRAINT pagos_pag_adq_fk_fkey FOREIGN KEY (pag_adq_fk) REFERENCES adquisicion(adq_pk);


--
-- TOC entry 3241 (class 2606 OID 53185)
-- Name: pagos_pag_ent_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pagos
    ADD CONSTRAINT pagos_pag_ent_fk_fkey FOREIGN KEY (pag_ent_fk) REFERENCES entregables(ent_pk);


--
-- TOC entry 3242 (class 2606 OID 53190)
-- Name: personas_pers_orga_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY personas
    ADD CONSTRAINT personas_pers_orga_fk_fkey FOREIGN KEY (pers_orga_fk) REFERENCES organi_int_prove(orga_pk) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3243 (class 2606 OID 53195)
-- Name: plantilla_cronograma_p_crono_org_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY plantilla_cronograma
    ADD CONSTRAINT plantilla_cronograma_p_crono_org_fk_fkey FOREIGN KEY (p_crono_org_fk) REFERENCES organismos(org_pk) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3244 (class 2606 OID 53200)
-- Name: plantilla_entregables_p_entregable_ant_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY plantilla_entregables
    ADD CONSTRAINT plantilla_entregables_p_entregable_ant_fk_fkey FOREIGN KEY (p_entregable_ant_fk) REFERENCES plantilla_entregables(p_entregables_id);


--
-- TOC entry 3245 (class 2606 OID 53205)
-- Name: plantilla_entregables_p_entregable_p_cro_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY plantilla_entregables
    ADD CONSTRAINT plantilla_entregables_p_entregable_p_cro_fk_fkey FOREIGN KEY (p_entregable_p_cro_fk) REFERENCES plantilla_cronograma(p_crono_pk);


--
-- TOC entry 3246 (class 2606 OID 53210)
-- Name: presupuesto_pre_fuente_organi_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY presupuesto
    ADD CONSTRAINT presupuesto_pre_fuente_organi_fk_fkey FOREIGN KEY (pre_fuente_organi_fk) REFERENCES fuente_financiamiento(fue_pk);


--
-- TOC entry 3247 (class 2606 OID 53215)
-- Name: presupuesto_pre_moneda_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY presupuesto
    ADD CONSTRAINT presupuesto_pre_moneda_fkey FOREIGN KEY (pre_moneda) REFERENCES moneda(mon_pk);


--
-- TOC entry 3248 (class 2606 OID 53220)
-- Name: productos_prod_ent_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT productos_prod_ent_fk_fkey FOREIGN KEY (prod_ent_fk) REFERENCES entregables(ent_pk);


--
-- TOC entry 3249 (class 2606 OID 53225)
-- Name: prog_docs_obl_progdocsobl_docs_pk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prog_docs_obl
    ADD CONSTRAINT prog_docs_obl_progdocsobl_docs_pk_fkey FOREIGN KEY (progdocsobl_docs_pk) REFERENCES documentos(docs_pk);


--
-- TOC entry 3250 (class 2606 OID 53230)
-- Name: prog_indices_pre_progindpre_progind_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prog_indices_pre
    ADD CONSTRAINT prog_indices_pre_progindpre_progind_fk_fkey FOREIGN KEY (progindpre_progind_fk) REFERENCES prog_indices(progind_pk) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3251 (class 2606 OID 53235)
-- Name: prog_int_progint_int_pk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prog_int
    ADD CONSTRAINT prog_int_progint_int_pk_fkey FOREIGN KEY (progint_int_pk) REFERENCES interesados(int_pk);


--
-- TOC entry 3252 (class 2606 OID 53240)
-- Name: prog_lectura_area_proglectarea_area_pk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prog_lectura_area
    ADD CONSTRAINT prog_lectura_area_proglectarea_area_pk_fkey FOREIGN KEY (proglectarea_area_pk) REFERENCES areas(area_pk);


--
-- TOC entry 3254 (class 2606 OID 53245)
-- Name: prog_obj_est_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY programas
    ADD CONSTRAINT prog_obj_est_fk FOREIGN KEY (prog_obj_est_fk) REFERENCES objetivos_estrategicos(obj_est_pk);


--
-- TOC entry 3253 (class 2606 OID 53250)
-- Name: prog_tags_progtag_area_pk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prog_tags
    ADD CONSTRAINT prog_tags_progtag_area_pk_fkey FOREIGN KEY (progtag_area_pk) REFERENCES areas_tags(arastag_pk);


--
-- TOC entry 3255 (class 2606 OID 53255)
-- Name: programas_prog_area_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY programas
    ADD CONSTRAINT programas_prog_area_fk_fkey FOREIGN KEY (prog_area_fk) REFERENCES areas(area_pk);


--
-- TOC entry 3256 (class 2606 OID 53260)
-- Name: programas_prog_est_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY programas
    ADD CONSTRAINT programas_prog_est_fk_fkey FOREIGN KEY (prog_est_fk) REFERENCES estados(est_pk);


--
-- TOC entry 3257 (class 2606 OID 53265)
-- Name: programas_prog_org_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY programas
    ADD CONSTRAINT programas_prog_org_fk_fkey FOREIGN KEY (prog_org_fk) REFERENCES organismos(org_pk);


--
-- TOC entry 3258 (class 2606 OID 53270)
-- Name: programas_prog_pre_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY programas
    ADD CONSTRAINT programas_prog_pre_fk_fkey FOREIGN KEY (prog_pre_fk) REFERENCES presupuesto(pre_pk) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3261 (class 2606 OID 53275)
-- Name: proy_int_proyint_int_pk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_int
    ADD CONSTRAINT proy_int_proyint_int_pk_fkey FOREIGN KEY (proyint_int_pk) REFERENCES interesados(int_pk);


--
-- TOC entry 3262 (class 2606 OID 53280)
-- Name: proy_lectura_area_proglectarea_area_pk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_lectura_area
    ADD CONSTRAINT proy_lectura_area_proglectarea_area_pk_fkey FOREIGN KEY (proglectarea_area_pk) REFERENCES areas(area_pk);


--
-- TOC entry 3274 (class 2606 OID 53285)
-- Name: proy_obj_est_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proy_obj_est_fk FOREIGN KEY (proy_obj_est_fk) REFERENCES objetivos_estrategicos(obj_est_pk);


--
-- TOC entry 3263 (class 2606 OID 53290)
-- Name: proy_otros_cat_secundarias_proy_cat_cat_proy_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_otros_cat_secundarias
    ADD CONSTRAINT proy_otros_cat_secundarias_proy_cat_cat_proy_fk_fkey FOREIGN KEY (proy_cat_cat_proy_fk) REFERENCES categoria_proyectos(cat_proy_pk);


--
-- TOC entry 3264 (class 2606 OID 53295)
-- Name: proy_otros_datos_proy_otr_cat_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_otros_datos
    ADD CONSTRAINT proy_otros_datos_proy_otr_cat_fk_fkey FOREIGN KEY (proy_otr_cat_fk) REFERENCES categoria_proyectos(cat_proy_pk);


--
-- TOC entry 3265 (class 2606 OID 53300)
-- Name: proy_otros_datos_proy_otr_cont_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_otros_datos
    ADD CONSTRAINT proy_otros_datos_proy_otr_cont_fk_fkey FOREIGN KEY (proy_otr_cont_fk) REFERENCES organi_int_prove(orga_pk);


--
-- TOC entry 3266 (class 2606 OID 53305)
-- Name: proy_otros_datos_proy_otr_ent_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_otros_datos
    ADD CONSTRAINT proy_otros_datos_proy_otr_ent_fk_fkey FOREIGN KEY (proy_otr_ent_fk) REFERENCES entregables(ent_pk);


--
-- TOC entry 3267 (class 2606 OID 53310)
-- Name: proy_otros_datos_proy_otr_est_pub_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_otros_datos
    ADD CONSTRAINT proy_otros_datos_proy_otr_est_pub_fk_fkey FOREIGN KEY (proy_otr_est_pub_fk) REFERENCES estados_publicacion(est_pub_pk);


--
-- TOC entry 3268 (class 2606 OID 53315)
-- Name: proy_otros_datos_proy_otr_eta_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_otros_datos
    ADD CONSTRAINT proy_otros_datos_proy_otr_eta_fk_fkey FOREIGN KEY (proy_otr_eta_fk) REFERENCES etapa(eta_pk);


--
-- TOC entry 3269 (class 2606 OID 53320)
-- Name: proy_otros_datos_proy_otr_ins_eje_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_otros_datos
    ADD CONSTRAINT proy_otros_datos_proy_otr_ins_eje_fk_fkey FOREIGN KEY (proy_otr_ins_eje_fk) REFERENCES organi_int_prove(orga_pk);


--
-- TOC entry 3270 (class 2606 OID 53325)
-- Name: proy_pre_proypre_pre_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_pre
    ADD CONSTRAINT proy_pre_proypre_pre_fk_fkey FOREIGN KEY (proypre_pre_fk) REFERENCES presupuesto(pre_pk);


--
-- TOC entry 3271 (class 2606 OID 53330)
-- Name: proy_publica_proy_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_publica_hist
    ADD CONSTRAINT proy_publica_proy_fk FOREIGN KEY (proy_publica_proy_fk) REFERENCES proyectos(proy_pk);


--
-- TOC entry 3272 (class 2606 OID 53335)
-- Name: proy_publica_usu_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_publica_hist
    ADD CONSTRAINT proy_publica_usu_fk FOREIGN KEY (proy_publica_usu_fk) REFERENCES ss_usuario(usu_id);


--
-- TOC entry 3273 (class 2606 OID 53340)
-- Name: proy_tags_proytag_area_pk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_tags
    ADD CONSTRAINT proy_tags_proytag_area_pk_fkey FOREIGN KEY (proytag_area_pk) REFERENCES areas_tags(arastag_pk);


--
-- TOC entry 3275 (class 2606 OID 53345)
-- Name: proyectos_proy_area_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proyectos_proy_area_fk_fkey FOREIGN KEY (proy_area_fk) REFERENCES areas(area_pk);


--
-- TOC entry 3276 (class 2606 OID 53350)
-- Name: proyectos_proy_est_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proyectos_proy_est_fk_fkey FOREIGN KEY (proy_est_fk) REFERENCES estados(est_pk);


--
-- TOC entry 3277 (class 2606 OID 53355)
-- Name: proyectos_proy_est_pendiente_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proyectos_proy_est_pendiente_fk_fkey FOREIGN KEY (proy_est_pendiente_fk) REFERENCES estados(est_pk);


--
-- TOC entry 3278 (class 2606 OID 53360)
-- Name: proyectos_proy_latlng_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proyectos_proy_latlng_fk_fkey FOREIGN KEY (proy_latlng_fk) REFERENCES latlng_proyectos(latlng_pk);


--
-- TOC entry 3279 (class 2606 OID 53365)
-- Name: proyectos_proy_org_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proyectos_proy_org_fk_fkey FOREIGN KEY (proy_org_fk) REFERENCES organismos(org_pk);


--
-- TOC entry 3280 (class 2606 OID 53370)
-- Name: proyectos_proy_otr_dat_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proyectos_proy_otr_dat_fk_fkey FOREIGN KEY (proy_otr_dat_fk) REFERENCES proy_otros_datos(proy_otr_pk);


--
-- TOC entry 3281 (class 2606 OID 53375)
-- Name: proyectos_proy_pre_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proyectos_proy_pre_fk_fkey FOREIGN KEY (proy_pre_fk) REFERENCES presupuesto(pre_pk);


--
-- TOC entry 3282 (class 2606 OID 53380)
-- Name: proyectos_proy_prog_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proyectos_proy_prog_fk_fkey FOREIGN KEY (proy_prog_fk) REFERENCES programas(prog_pk);


--
-- TOC entry 3283 (class 2606 OID 53385)
-- Name: proyectos_proy_proyindices_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proyectos_proy_proyindices_fk_fkey FOREIGN KEY (proy_proyindices_fk) REFERENCES proy_indices(proyind_pk);


--
-- TOC entry 3284 (class 2606 OID 53390)
-- Name: proyectos_proy_risk_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proyectos_proy_risk_fk_fkey FOREIGN KEY (proy_risk_fk) REFERENCES riesgos(risk_pk);


--
-- TOC entry 3285 (class 2606 OID 53395)
-- Name: proyectos_proy_usr_adjunto_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proyectos_proy_usr_adjunto_fk_fkey FOREIGN KEY (proy_usr_adjunto_fk) REFERENCES ss_usuario(usu_id);


--
-- TOC entry 3286 (class 2606 OID 53400)
-- Name: proyectos_proy_usr_pmofed_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proyectos_proy_usr_pmofed_fk_fkey FOREIGN KEY (proy_usr_pmofed_fk) REFERENCES ss_usuario(usu_id);


--
-- TOC entry 3287 (class 2606 OID 53405)
-- Name: proyectos_proy_usr_sponsor_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proyectos_proy_usr_sponsor_fk_fkey FOREIGN KEY (proy_usr_sponsor_fk) REFERENCES ss_usuario(usu_id);


--
-- TOC entry 3260 (class 2606 OID 56732)
-- Name: proyind_periodo_fin_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_indices
    ADD CONSTRAINT proyind_periodo_fin_ent_fk FOREIGN KEY (proyind_periodo_fin_ent_fk) REFERENCES entregables(ent_pk);


--
-- TOC entry 3259 (class 2606 OID 56737)
-- Name: proyind_periodo_inicio_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proy_indices
    ADD CONSTRAINT proyind_periodo_inicio_ent_fk FOREIGN KEY (proyind_periodo_inicio_ent_fk) REFERENCES entregables(ent_pk);


--
-- TOC entry 3288 (class 2606 OID 53410)
-- Name: registros_horas_rh_ent_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY registros_horas
    ADD CONSTRAINT registros_horas_rh_ent_fk_fkey FOREIGN KEY (rh_ent_fk) REFERENCES entregables(ent_pk);


--
-- TOC entry 3289 (class 2606 OID 53415)
-- Name: registros_horas_rh_proy_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY registros_horas
    ADD CONSTRAINT registros_horas_rh_proy_fk_fkey FOREIGN KEY (rh_proy_fk) REFERENCES proyectos(proy_pk);


--
-- TOC entry 3290 (class 2606 OID 53420)
-- Name: riesgos_risk_ent_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY riesgos
    ADD CONSTRAINT riesgos_risk_ent_fk_fkey FOREIGN KEY (risk_ent_fk) REFERENCES entregables(ent_pk);


--
-- TOC entry 3291 (class 2606 OID 53425)
-- Name: riesgos_risk_proy_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY riesgos
    ADD CONSTRAINT riesgos_risk_proy_fk_fkey FOREIGN KEY (risk_proy_fk) REFERENCES proyectos(proy_pk) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3292 (class 2606 OID 53430)
-- Name: roles_interesados_rolint_org_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles_interesados
    ADD CONSTRAINT roles_interesados_rolint_org_fk_fkey FOREIGN KEY (rolint_org_fk) REFERENCES organismos(org_pk);


--
-- TOC entry 3293 (class 2606 OID 53435)
-- Name: ss_localidades_loc_depto_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_localidades
    ADD CONSTRAINT ss_localidades_loc_depto_id_fkey FOREIGN KEY (loc_depto_id) REFERENCES ss_departamentos(depto_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3294 (class 2606 OID 53440)
-- Name: ss_operacion_ope_categoria_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_operacion
    ADD CONSTRAINT ss_operacion_ope_categoria_id_fkey FOREIGN KEY (ope_categoria_id) REFERENCES ss_categoper(cat_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3295 (class 2606 OID 53445)
-- Name: ss_rel_rol_operacion_rel_rol_operacion_operacion_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_rel_rol_operacion
    ADD CONSTRAINT ss_rel_rol_operacion_rel_rol_operacion_operacion_id_fkey FOREIGN KEY (rel_rol_operacion_operacion_id) REFERENCES ss_operacion(ope_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3296 (class 2606 OID 53450)
-- Name: ss_usuario_usu_area_org_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ss_usuario
    ADD CONSTRAINT ss_usuario_usu_area_org_fkey FOREIGN KEY (usu_area_org) REFERENCES areas(area_pk);


--
-- TOC entry 3297 (class 2606 OID 53455)
-- Name: temas_calidad_tca_org_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY temas_calidad
    ADD CONSTRAINT temas_calidad_tca_org_fk_fkey FOREIGN KEY (tca_org_fk) REFERENCES organismos(org_pk);


--
-- TOC entry 3299 (class 2606 OID 53460)
-- Name: tipo_documento_instancia_tipodoc_inst_tipodoc_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_documento_instancia
    ADD CONSTRAINT tipo_documento_instancia_tipodoc_inst_tipodoc_fk_fkey FOREIGN KEY (tipodoc_inst_tipodoc_fk) REFERENCES tipo_documento(tipdoc_pk);


--
-- TOC entry 3298 (class 2606 OID 53465)
-- Name: tipo_documento_tipodoc_org_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_documento
    ADD CONSTRAINT tipo_documento_tipodoc_org_fk_fkey FOREIGN KEY (tipodoc_org_fk) REFERENCES organismos(org_pk);


--
-- TOC entry 3300 (class 2606 OID 53470)
-- Name: tipo_gasto_tipogas_org_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_gasto
    ADD CONSTRAINT tipo_gasto_tipogas_org_fk_fkey FOREIGN KEY (tipogas_org_fk) REFERENCES organismos(org_pk);


--
-- TOC entry 3301 (class 2606 OID 53475)
-- Name: valor_hora_val_hor_mon_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY valor_hora
    ADD CONSTRAINT valor_hora_val_hor_mon_fk_fkey FOREIGN KEY (val_hor_mon_fk) REFERENCES moneda(mon_pk);


--
-- TOC entry 3302 (class 2606 OID 53480)
-- Name: valor_hora_val_hor_org_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY valor_hora
    ADD CONSTRAINT valor_hora_val_hor_org_fk_fkey FOREIGN KEY (val_hor_org_fk) REFERENCES organismos(org_pk);


--
-- TOC entry 3303 (class 2606 OID 53485)
-- Name: valor_hora_val_hor_usu_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY valor_hora
    ADD CONSTRAINT valor_hora_val_hor_usu_fk_fkey FOREIGN KEY (val_hor_usu_fk) REFERENCES ss_usuario(usu_id);


--
-- TOC entry 3641 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-07-03 15:28:59 UYT

--
-- PostgreSQL database dump complete
--

