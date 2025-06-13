CREATE TYPE enum_tipo_entidad_participe AS ENUM ('persona', 'empresa');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_tipo_entidad AS ENUM ('persona', 'empresa');
CREATE TYPE enum_tipo_identificacion AS ENUM ('cedula', 'pasaporte', 'ruc');
CREATE TYPE enum_tipo_cliente AS ENUM ('PERSONA_NATURAL', 'PERSONA_JURIDICA');
CREATE TYPE enum_segmento AS ENUM ('masivo', 'preferencial', 'corporativo', 'empresarial', 'pymes', 'microfinanzas');
CREATE TYPE enum_canal_afiliacion AS ENUM ('PAGINA_WEB', 'agencia', 'externo', 'CALL_CENTER');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo', 'suspendido', 'bloqueado');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_tipo_comision AS ENUM ('transaccion', 'servicio', 'periodico');
CREATE TYPE enum_base_calculo AS ENUM ('fijo', 'porcentaje');
CREATE TYPE enum_frecuencia AS ENUM ('mensual', 'trimestral', 'anual');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_tipo_comision AS ENUM ('desembolso', 'PAGO_ATRASADO', 'prepago', 'MODIFICACION_CONTRACTUAL', 'SERVICIOS_ADICIONALES');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo', 'suspendido', 'bloqueado');
CREATE TYPE enum_tipo AS ENUM ('domicilio', 'laboral');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_tipo_identificacion AS ENUM ('ruc');
CREATE TYPE enum_tipo AS ENUM ('privada', 'publica', 'mixta');
CREATE TYPE enum_sector_economico AS ENUM ('primario', 'secundario', 'terciario');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_nombre AS ENUM ('frances', 'americano', 'aleman');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_tipo_feriado AS ENUM ('reg', 'nat');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_tipo_garantia AS ENUM ('hipoteca', 'prendaria', 'personal');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_tipo_identificacion AS ENUM ('cedula', 'pasaporte');
CREATE TYPE enum_genero AS ENUM ('masculino', 'femenino', 'otros');
CREATE TYPE enum_estado_civil AS ENUM ('soltero', 'casado', 'divoriciado', 'viudo', 'unionlibre');
CREATE TYPE enum_nivel_estudio AS ENUM ('primaria', 'secundaria', 'universitaria', 'posgrado', 'doctorado');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo', 'suspendido', 'bloqueado', 'prospecto');
CREATE TYPE enum_base_calculo AS ENUM ('30/360', '31/365');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('solicitado', 'aprobado', 'desembolsado', 'vigente', 'EN_MORA', 'refinanciado', 'pagado', 'castigado');
CREATE TYPE enum_rol AS ENUM ('REPRESENTANTE_LEGAL', 'FIRMA_AUTORIZADA', 'administrador', 'operador');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_tipo_seguro AS ENUM ('vida', 'desempleo', 'PROTECCION_PAGOS', 'desgravamen', 'incendios');
CREATE TYPE enum_estado AS ENUM ('activo', 'vencido', 'cancelado', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_base_calculo AS ENUM ('30/360', '31/365');
CREATE TYPE enum_metodo_calculo AS ENUM ('SALDO_DIARIO', 'SALDO_PROMEDIO');
CREATE TYPE enum_frecuencia_capitalizacion AS ENUM ('diaria', 'mensual', 'semestral', 'anual');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_tipo AS ENUM ('celular', 'residencia', 'laboral');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_nombre AS ENUM ('ahorro', 'corriente', 'deposito A plazo fijo');
CREATE TYPE enum_tipo_cliente AS ENUM ('persona', 'empresa', 'ambos');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');
CREATE TYPE enum_tipo_cliente AS ENUM ('persona', 'empresa', 'ambos');
CREATE TYPE enum_estado AS ENUM ('activo', 'inactivo');


drop table accionistas;

drop table clientes;

drop table clientes_sucursales;

drop table comisiones_cargos;

drop table comisiones_cargos_cuentas;

drop table comisiones_cargos_prestamos;

drop table comisiones_prestamos;

drop table condicion_comisiones;

drop table contacto_transaccion_cliente;

drop table cronogramas_pagos;

drop table cuentas;

drop table cuentas_clientes;

drop table direccion_cliente;

drop table empresas;

drop table entidades_bancarias;

drop table entidades_bancarias_monedas;

drop table esquemas_amortizacion;

drop table estructura_geografica;

drop table exenciones_cuentas;

drop table exenciones_prestamos;

drop table feriados;

drop table garantias;

drop table locacion_geografica;

drop index INDEX_1;

drop table monedas;

drop table pagos_prestamos;

drop table paises;

drop table personas;

drop table prestamos;

drop table prestamos_clientes;

drop table representantes;

drop table seguros;

drop table seguros_prestamos;

drop table servicios_asociados;

drop table servicios_asociados_cuentas;

drop table sucursales;

drop table tasa_plazos;

drop table tasa_saldos;

drop table tasas_interes;

drop table telefono_cliente;

drop table tipo_cuentas;

drop table tipos_prestamos;

-- tabla: clientes
comment on table clientes is 'contiene la información de los clientes, tanto personas como empresas.';

comment on column clientes.id_cliente is 'identificador único del cliente.';
comment on column clientes.tipo_entidad is 'indica si el cliente es una persona o empresa.';
comment on column clientes.id_entidad is 'referencia al id de la entidad correspondiente.';
comment on column clientes.nombre is 'nombre completo del cliente.';
comment on column clientes.nacionalidad is 'Código del país de nacionalidad.';
comment on column clientes.id_sucursal is 'sucursal en la que fue registrado el cliente.';
comment on column clientes.tipo_identificacion is 'tipo de documento de identidad (cedula, ruc, etc.).';
comment on column clientes.numero_identificacion is 'Número de identificación del cliente.';
comment on column clientes.tipo_cliente is 'tipo de cliente (PERSONA_NATURAL o PERSONA_JURIDICA).';
comment on column clientes.segmento is 'segmento al que pertenece el cliente.';
comment on column clientes.canal_afiliacion is 'canal por el cual se afilió el cliente.';
comment on column clientes.fecha_creacion is 'fecha en que se creó el cliente.';
comment on column clientes.fecha_actualizacion is 'Última fecha de modificación del cliente.';
comment on column clientes.comentarios is 'comentarios generales sobre el cliente.';
comment on column clientes.estado is 'estado del cliente (activo, inactivo, etc.).';
comment on column clientes.version is 'campo de versión para control de cambios.';

-- tabla: clientes_sucursales
comment on table clientes_sucursales is 'Relación entre clientes y sucursales a las que están asociados.';

comment on column clientes_sucursales.id_cliente_sucursal is 'identificador único del registro cliente-sucursal.';
comment on column clientes_sucursales.id_sucursal is 'identificador de la sucursal asociada.';
comment on column clientes_sucursales.id_cliente is 'identificador del cliente asociado.';
comment on column clientes_sucursales.estado is 'estado de la relación: activo o inactivo.';
comment on column clientes_sucursales.version is 'Versión del registro para control de cambios.';

-- tabla: direccion_cliente
comment on table direccion_cliente is 'direcciones registradas para los clientes, ya sean de domicilio o laborales.';

comment on column direccion_cliente.id_direccion is 'identificador único de la dirección.';
comment on column direccion_cliente.id_cliente is 'identificador del cliente propietario de la dirección.';
comment on column direccion_cliente.tipo is 'tipo de dirección: domicilio o laboral.';
comment on column direccion_cliente.linea1 is 'primera línea de la dirección.';
comment on column direccion_cliente.linea2 is 'segunda línea de la dirección (opcional).';
comment on column direccion_cliente.codigo_postal is 'Código postal asociado.';
comment on column direccion_cliente.codigo_geografico is 'Código geográfico de ubicación.';
comment on column direccion_cliente.fecha_creacion is 'fecha de creación del registro.';
comment on column direccion_cliente.fecha_actualizacion is 'fecha de última modificación del registro.';
comment on column direccion_cliente.estado is 'estado del registro: activo o inactivo.';
comment on column direccion_cliente.version is 'Versión del registro para control de cambios.';

-- tabla: accionistas
comment on table accionistas is 'tabla que almacena los datos de los accionistas de una empresa.';

comment on column accionistas.id_accionista is 'identificador único del accionista.';
comment on column accionistas.id_empresa is 'identificador de la empresa a la que pertenece el accionista.';
comment on column accionistas.id_participe is 'identificador del participante, que puede ser persona o empresa.';
comment on column accionistas.participacion is 'porcentaje de participación del accionista en la empresa.';
comment on column accionistas.tipo_entidad_participe is 'tipo de entidad que participa: persona o empresa.';
comment on column accionistas.estado is 'estado del registro: activo o inactivo.';
comment on column accionistas.version is 'Versión del registro para control de cambios.';

-- tabla: empresas
comment on table empresas is 'tabla que almacena información de las empresas registradas.';

comment on column empresas.id_empresa is 'identificador único de la empresa.';
comment on column empresas.tipo_identificacion is 'tipo de identificación (por ejemplo: ruc).';
comment on column empresas.numero_identificacion is 'Número de identificación de la empresa.';
comment on column empresas.nombre_comercial is 'nombre comercial de la empresa.';
comment on column empresas.razon_social is 'Razón social registrada legalmente.';
comment on column empresas.tipo is 'tipo de empresa: privada, publica o mixta.';
comment on column empresas.fecha_constitucion is 'fecha de constitución legal de la empresa.';
comment on column empresas.correo_electronico is 'correo electrónico de contacto de la empresa.';
comment on column empresas.sector_economico is 'sector económico al que pertenece la empresa.';
comment on column empresas.fecha_registro is 'fecha de creación del registro en el sistema.';
comment on column empresas.fecha_actualizacion is 'Última fecha de actualización del registro.';
comment on column empresas.estado is 'estado del registro: activo o inactivo.';
comment on column empresas.version is 'Versión del registro para control de cambios.';

-- tabla: telefono_cliente
comment on table telefono_cliente is 'tabla que almacena los números telefónicos registrados por cada cliente.';

comment on column telefono_cliente.id_telefono is 'identificador único del número de teléfono.';
comment on column telefono_cliente.id_cliente is 'identificador del cliente asociado al teléfono.';
comment on column telefono_cliente.tipo is 'tipo de teléfono registrado: celular, residencia o laboral.';
comment on column telefono_cliente.numero_telefono is 'Número telefónico del cliente.';
comment on column telefono_cliente.fecha_creacion is 'fecha de creación del registro telefónico.';
comment on column telefono_cliente.fecha_actualizacion is 'fecha de última modificación del registro.';
comment on column telefono_cliente.estado is 'estado del registro: activo o inactivo.';
comment on column telefono_cliente.version is 'Versión del registro para control de cambios.';

-- tabla: representantes
comment on table representantes is 'tabla que relaciona empresas con sus representantes asignados.';

comment on column representantes.id_representante is 'identificador único del representante.';
comment on column representantes.id_empresa is 'identificador de la empresa representada.';
comment on column representantes.id_cliente is 'identificador del cliente que actúa como representante.';
comment on column representantes.rol is 'rol desempeñado por el representante (ej. REPRESENTANTE_LEGAL, administrador, etc.).';
comment on column representantes.fecha_asignacion is 'fecha en que se asignó el representante.';
comment on column representantes.estado is 'estado del registro: activo o inactivo.';
comment on column representantes.version is 'Versión del registro para control de cambios.';


-- tabla: personas
comment on table personas is 'tabla que almacena información detallada sobre personas naturales registradas.';

comment on column personas.id_persona is 'identificador único de la persona.';
comment on column personas.tipo_identificacion is 'tipo de documento de identificación: cedula o pasaporte.';
comment on column personas.numero_identificacion is 'Número de identificación de la persona.';
comment on column personas.nombre is 'nombre completo de la persona.';
comment on column personas.genero is 'Género de la persona: masculino, femenino u otros.';
comment on column personas.fecha_nacimiento is 'fecha de nacimiento de la persona.';
comment on column personas.estado_civil is 'estado civil de la persona.';
comment on column personas.nivel_estudio is 'nivel educativo alcanzado por la persona.';
comment on column personas.correo_electronico is 'correo electrónico de contacto.';
comment on column personas.fecha_registro is 'fecha de registro inicial en el sistema.';
comment on column personas.fecha_actualizacion is 'fecha de última modificación del registro.';
comment on column personas.estado is 'estado actual de la persona: activo, inactivo, suspendido, etc.';
comment on column personas.version is 'Versión del registro para control de cambios.';


-- tabla: contacto_transaccion_cliente
comment on table contacto_transaccion_cliente is 'tabla que almacena información de contacto transaccional para clientes.';

comment on column contacto_transaccion_cliente.id_contacto_transaccion is 'identificador único del contacto transaccional.';
comment on column contacto_transaccion_cliente.id_cliente is 'identificador del cliente asociado.';
comment on column contacto_transaccion_cliente.telefono is 'Número de teléfono registrado para transacciones.';
comment on column contacto_transaccion_cliente.correo_electronico is 'correo electrónico usado para comunicaciones transaccionales.';
comment on column contacto_transaccion_cliente.fecha_creacion is 'fecha de creación del registro de contacto.';
comment on column contacto_transaccion_cliente.fecha_actualizacion is 'fecha de última modificación del registro.';
comment on column contacto_transaccion_cliente.estado is 'estado del registro: activo o inactivo.';
comment on column contacto_transaccion_cliente.version is 'Versión del registro para control de cambios.';


/*==============================================================*/
/* table: accionistas                                           */
/*==============================================================*/
create table accionistas (
   id_accionista         serial               not null,
   id_empresa            int                  not null,
   id_participe          int                  not null,
   participacion        numeric(5,2)         not null,
   tipo_entidad_participe varchar(10)          not null
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_ACCIONISTAS primary key (id_accionista)
);

/*==============================================================*/
/* table: clientes                                              */
/*==============================================================*/
create table clientes (
   id_cliente            serial not null,
   tipo_entidad          varchar(10)          not null
   id_entidad            int                  not null,
   nombre               varchar(50)          not null,
   nacionalidad         varchar(2)           not null,
   id_sucursal           int                  not null,
   tipo_identificacion   varchar(10)          not null
   numero_identificacion varchar(13)          not null,
   tipo_cliente          varchar(20)          not null
   segmento             varchar(20)          not null
   canal_afiliacion      varchar(15)          not null
   fecha_creacion        timestamp            not null default CURRENT_TIMESTAMP,
   fecha_actualizacion   timestamp            not null,
   comentarios          varchar(100)         not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_CLIENTES primary key (id_cliente)
);

/*==============================================================*/
/* table: clientes_sucursales                                    */
/*==============================================================*/
create table clientes_sucursales (
   id_cliente_sucursal    serial               not null,
   id_sucursal           int4                 not null,
   id_cliente            int4                 not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_CLIENTESSUCURSALES primary key (id_cliente_sucursal)
);

/*==============================================================*/
/* table: comisiones_cargos                                      */
/*==============================================================*/
create table comisiones_cargos (
   id_comision_cargo      serial not null,
   tipo_comision         varchar(30)          not null
   id_servicio           int                  not null,
   nombre               varchar(30)          not null,
   base_calculo          varchar(20)          not null
   monto                numeric(15,2)        not null,
   frecuencia           varchar(30)          not null
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_COMISIONESCARGOS primary key (id_comision_cargo)
);

/*==============================================================*/
/* table: comisiones_cargos_cuentas                               */
/*==============================================================*/
create table comisiones_cargos_cuentas (
   id_comision_cargo_cuenta serial               not null,
   id_cuenta             int                  not null,
   id_comision_cargo      int                  not null,
   fecha_asignacion      timestamp            not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_COMISIONESCARGOSCUENTAS primary key (id_comision_cargo_cuenta)
);

/*==============================================================*/
/* table: comisiones_cargos_prestamos                             */
/*==============================================================*/
create table comisiones_cargos_prestamos (
   id_comision_cargo_prestamo serial               not null,
   id_prestamo           int                  not null,
   id_comision_prestamo   int                  not null,
   fecha_asignacion      timestamp            not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_COMISIONESCARGOSPRESTAMOS primary key (id_comision_cargo_prestamo)
);

/*==============================================================*/
/* table: comisiones_prestamos                                   */
/*==============================================================*/
create table comisiones_prestamos (
   id_comision_prestamo   serial not null,
   nombre               varchar(100)         not null,
   tipo_comision         varchar(30)          not null
   tipo_calculo          varchar(15)          not null,
   valor                numeric(15,2)        not null,
   fecha_creacion        timestamp            not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_COMISIONESPRESTAMOS primary key (id_comision_prestamo)
);

/*==============================================================*/
/* table: condicion_comisiones                                   */
/*==============================================================*/
create table condicion_comisiones (
   id_condicion          serial not null,
   id_comision_prestamo   int                  not null,
   tipo_condicion        varchar(30)          not null,
   valor                numeric(15,2)        not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_CONDICIONCOMISIONES primary key (id_condicion)
);

/*==============================================================*/
/* table: contacto_transaccion_cliente                            */
/*==============================================================*/
create table contacto_transaccion_cliente (
   id_contacto_transaccion serial               not null,
   id_cliente            int4                 not null,
   telefono             varchar(10)          not null,
   correo_electronico    varchar(40)          not null,
   fecha_creacion        timestamp            not null,
   fecha_actualizacion   timestamp            not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_CONTACTOTRANSACCIONCLIENTE primary key (id_contacto_transaccion)
);

/*==============================================================*/
/* table: cronogramas_pagos                                      */
/*==============================================================*/
create table cronogramas_pagos (
   id_cuota              serial not null,
   id_prestamo_cliente    int                  not null,
   id_prestamo           int                  not null,
   numero_cuota          int                  not null,
   vencimiento          date                 not null,
   capital              numeric              not null,
   interes              numeric              not null,
   comisiones           numeric              not null default 0,
   seguros              numeric              not null default 0,
   total                numeric              not null,
   saldo                numeric              not null,
   fecha_pago            date                 not null,
   estado               varchar(15)          not null default 'pendiente'
   version              numeric(9)           not null,
   constraint PK_CRONOGRAMASPAGOS primary key (id_cuota)
);

/*==============================================================*/
/* table: cuentas                                               */
/*==============================================================*/
create table cuentas (
   id_cuenta             serial not null,
   id_tipo_cuenta         int                  not null,
   id_tasa_interes        int                  not null,
   codigo_cuenta         varchar(20)          not null,
   nombre               varchar(50)          not null,
   descripcion          varchar(50)          not null,
   fecha_creacion        timestamp            not null,
   fecha_modificacion    timestamp            not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_CUENTAS primary key (id_cuenta)
);

/*==============================================================*/
/* table: cuentas_clientes                                       */
/*==============================================================*/
create table cuentas_clientes (
   id_cuenta_cliente      serial not null,
   id_cuenta             int                  not null,
   id_cliente            int4                 not null,
   numero_cuenta         varchar(10)          not null,
   saldo_disponible      numeric(100,2)       not null,
   saldo_contable        numeric(100,2)       not null,
   fecha_apertura        timestamp            not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_CUENTASCLIENTES primary key (id_cuenta_cliente)
);

/*==============================================================*/
/* table: direccion_cliente                                      */
/*==============================================================*/
create table direccion_cliente (
   id_direccion          serial not null,
   id_cliente            int4                 not null,
   tipo                 varchar(15)          not null
   linea1               varchar(150)         not null,
   linea2               varchar(150)         null,
   codigo_postal         varchar(6)           not null,
   codigo_geografico     varchar(20)          not null,
   fecha_creacion        timestamp            not null,
   fecha_actualizacion   timestamp            not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_DIRECCIONCLIENTE primary key (id_direccion)
);

/*==============================================================*/
/* table: empresas                                              */
/*==============================================================*/
create table empresas (
   id_empresa            serial not null,
   tipo_identificacion   varchar(10)          not null
   numero_identificacion varchar(13)          not null,
   nombre_comercial      varchar(30)          not null,
   razon_social          varchar(50)          not null,
   tipo                 varchar(10)          not null
   fecha_constitucion    date                 not null,
   correo_electronico    varchar(40)          not null,
   sector_economico      varchar(15)          not null
   fecha_registro        timestamp            not null,
   fecha_actualizacion   timestamp            not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_EMPRESAS primary key (id_empresa)
);

/*==============================================================*/
/* table: entidades_bancarias                                    */
/*==============================================================*/
create table entidades_bancarias (
   id_entidad_bancaria    int                  not null,
   codigo_local          varchar(6)           not null,
   nombre               varchar(100)         not null,
   codigo_internacional  varchar(20)          not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_ENTIDADESBANCARIAS primary key (id_entidad_bancaria)
);

/*==============================================================*/
/* table: entidades_bancarias_monedas                             */
/*==============================================================*/
create table entidades_bancarias_monedas (
   id_entidad_bancaria_moneda serial               not null,
   id_entidad_bancaria    int                  not null,
   id_moneda             varchar(3)           not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_ENTIDADESBANCARIASMONEDAS primary key (id_entidad_bancaria_moneda)
);

/*==============================================================*/
/* table: esquemas_amortizacion                                  */
/*==============================================================*/
create table esquemas_amortizacion (
   id_esquema            serial not null,
   id_tipo_prestamo       int                  not null,
   nombre               varchar(20)          not null
   descripcion          varchar(200)         not null,
   permite_gracia        boolean              not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_ESQUEMASAMORTIZACION primary key (id_esquema),
   constraint AK_KEY_2_ESQUEMAS unique (nombre)
);

/*==============================================================*/
/* table: estructura_geografica                                  */
/*==============================================================*/
create table estructura_geografica (
   id_pais               varchar(2)           not null,
   codigo_nivel          numeric(1)           not null,
   nombre               varchar(25)          not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_ESTRUCTURAGEOGRAFICA primary key (id_pais, codigo_nivel)
);

/*==============================================================*/
/* table: exenciones_cuentas                                     */
/*==============================================================*/
create table exenciones_cuentas (
   id_exencion           serial not null,
   id_comision           int                  not null,
   nombre               varchar(100)         not null,
   descripcion          varchar(100)         not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_EXENCIONESCUENTAS primary key (id_exencion)
);

/*==============================================================*/
/* table: exenciones_prestamos                                   */
/*==============================================================*/
create table exenciones_prestamos (
   id_exencion           serial not null,
   id_comision_prestamo   int                  not null,
   tipo_exencion         varchar(30)          not null,
   nombre               varchar(50)          not null,
   descripcion          varchar(100)         not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_EXENCIONESPRESTAMOS primary key (id_exencion)
);

/*==============================================================*/
/* table: feriados                                              */
/*==============================================================*/
create table feriados (
   id_feriado            int                  not null,
   fecha_feriado         date                 not null,
   id_pais               varchar(2)           not null,
   id_locacion           int                  not null,
   nombre               varchar(25)          not null,
   tipo_feriado          varchar(3)           not null
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_FERIADOS primary key (id_feriado)
);

/*==============================================================*/
/* table: garantias                                             */
/*==============================================================*/
create table garantias (
   id_garantia           serial not null,
   id_tipo_prestamo       int                  not null,
   tipo_garantia         varchar(20)          not null
   descripcion          varchar(200)         not null,
   valor                numeric              not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_GARANTIAS primary key (id_garantia)
);

/*==============================================================*/
/* table: locacion_geografica                                    */
/*==============================================================*/
create table locacion_geografica (
   id_locacion           serial not null,
   id_locacion_padre_id    int                  not null,
   id_pais               varchar(2)           not null,
   codigo_nivel          numeric(1)           not null,
   nombre               varchar(25)          not null,
   codigo_telefono_area   varchar(3)           not null,
   codigo_geografico     varchar(20)          not null,
   codigo_postal         varchar(6)           not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_LOCACIONGEOGRAFICA primary key (id_locacion)
);

/*==============================================================*/
/* table: monedas                                               */
/*==============================================================*/
create table monedas (
   id_moneda             varchar(3)           not null,
   id_pais               varchar(2)           not null,
   nombre               varchar(50)          not null,
   simbolo              varchar(5)           not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_MONEDAS primary key (id_moneda)
);

/*==============================================================*/
/* index: INDEX_1                                               */
/*==============================================================*/
create unique index INDEX_1 on monedas (id_moneda);

/*==============================================================*/
/* table: pagos_prestamos                                        */
/*==============================================================*/
create table pagos_prestamos (
   id_pago               serial not null,
   id_prestamo           int                  not null,
   id_cuota              int                  not null,
   id_prestamo_cliente    int                  not null,
   fecha                date                 not null,
   capital              numeric              not null,
   interes              numeric              not null,
   mora                 numeric              not null default 0,
   comisiones           numeric              not null default 0,
   seguros              numeric              not null default 0,
   total                numeric              not null,
   tipo_pago             varchar(20)          not null,
   cuenta_origen         int                  not null,
   referencia           varchar(100)         not null,
   usuario_registro      varchar(50)          not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_PAGOSPRESTAMOS primary key (id_pago)
);

/*==============================================================*/
/* table: paises                                                */
/*==============================================================*/
create table paises (
   id_pais               varchar(2)           not null,
   nombre               varchar(50)          not null,
   codigo_telefono       varchar(4)           not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_PAISES primary key (id_pais)
);

/*==============================================================*/
/* table: personas                                              */
/*==============================================================*/
create table personas (
   id_persona            serial not null,
   tipo_identificacion   varchar(10)          not null
   numero_identificacion varchar(13)          not null,
   nombre               varchar(50)          not null,
   genero               varchar(10)          not null
   fecha_nacimiento      date                 not null,
   estado_civil          varchar(15)          not null
   nivel_estudio         varchar(15)          not null
   correo_electronico    varchar(40)          not null,
   fecha_registro        timestamp            not null,
   fecha_actualizacion   timestamp            not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_PERSONAS primary key (id_persona)
);

/*==============================================================*/
/* table: prestamos                                             */
/*==============================================================*/
create table prestamos (
   id_prestamo           serial not null,
   id_tipo_prestamo       int                  not null,
   id_moneda             varchar(3)           not null,
   nombre               varchar(100)         not null,
   descripcion          varchar(200)         not null,
   fecha_modificacion    timestamp            not null,
   base_calculo          varchar(10)          not null
   tasa_moratoria        numeric(5,2)         not null,
   estado               varchar(15)          not null default 'solicitado'
   version              numeric(9)           not null,
   constraint PK_PRESTAMOS primary key (id_prestamo)
);

/*==============================================================*/
/* table: prestamos_clientes                                     */
/*==============================================================*/
create table prestamos_clientes (
   id_prestamo_cliente    serial not null,
   id_prestamo           int                  not null,
   id_cliente            int4                 not null,
   fecha_aprobacion      date                 not null,
   fecha_desembolso      date                 not null,
   fecha_vencimiento     date                 not null,
   estado               varchar(20)          not null
   version              numeric(9)           not null,
   constraint PK_PRESTAMOSCLIENTES primary key (id_prestamo_cliente)
);

/*==============================================================*/
/* table: representantes                                        */
/*==============================================================*/
create table representantes (
   id_representante      serial               not null,
   id_empresa            int                  not null,
   id_cliente            int4                 not null,
   rol                  varchar(25)          not null
   fecha_asignacion      timestamp            not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_REPRESENTANTES primary key (id_representante)
);

/*==============================================================*/
/* table: seguros                                               */
/*==============================================================*/
create table seguros (
   id_seguro             serial not null,
   tipo_seguro           varchar(30)          not null
   compania             varchar(100)         not null,
   monto_asegurado       numeric(15,2)        not null,
   fecha_inicio          date                 not null,
   fecha_fin             date                 not null,
   estado               varchar(15)          not null default 'activo'
   version              numeric(9)           not null,
   constraint PK_SEGUROS primary key (id_seguro)
);

/*==============================================================*/
/* table: seguros_prestamos                                      */
/*==============================================================*/
create table seguros_prestamos (
   id_seguro_prestamo     serial               not null,
   id_seguro             int                  not null,
   id_prestamo           int                  not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_SEGUROSPRESTAMOS primary key (id_seguro_prestamo)
);

/*==============================================================*/
/* table: servicios_asociados                                    */
/*==============================================================*/
create table servicios_asociados (
   id_servicio           serial not null,
   nombre               varchar(30)          not null,
   descripcion          varchar(150)         not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_SERVICIOSASOCIADOS primary key (id_servicio)
);

/*==============================================================*/
/* table: servicios_asociados_cuentas                             */
/*==============================================================*/
create table servicios_asociados_cuentas (
   id_servicio_asociado_cuenta serial               not null,
   id_servicio           int                  not null,
   id_cuenta             int                  not null,
   fecha_asignacion      timestamp            not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_SERVICIOSASOCIADOSCUENTAS primary key (id_servicio_asociado_cuenta)
);

/*==============================================================*/
/* table: sucursales                                            */
/*==============================================================*/
create table sucursales (
   id_sucursal           serial not null,
   id_entidad_bancaria    int                  not null,
   id_locacion           int                  not null,
   codigo               varchar(10)          not null,
   clave_unica           varchar(36)          not null,
   nombre               varchar(30)          not null,
   fecha_creacion        date                 not null,
   correo_electronico    varchar(100)         not null,
   telefono             varchar(10)          not null,
   linea1               varchar(150)         not null,
   linea2               varchar(150)         not null,
   latitud              float                not null,
   longitud             float                not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_SUCURSALES primary key (id_sucursal),
   constraint AK_KEY_2_SUCURSAL unique (clave_unica)
);

/*==============================================================*/
/* table: tasa_plazos                                            */
/*==============================================================*/
create table tasa_plazos (
   id_plazo              serial not null,
   id_tasa_interes        int                  not null,
   plazo_minimo          int                  not null,
   plazo_maximo          int                  not null,
   tasa                 numeric(5,2)         not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_TASAPLAZOS primary key (id_plazo)
);

/*==============================================================*/
/* table: tasa_saldos                                            */
/*==============================================================*/
create table tasa_saldos (
   id_saldo              serial not null,
   id_tasa_interes        int                  not null,
   saldo_minimo          numeric(15,2)        not null,
   saldo_maximo          numeric(15,2)        not null,
   tasa                 numeric(5,2)         not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_TASASALDOS primary key (id_saldo)
);

/*==============================================================*/
/* table: tasas_interes                                          */
/*==============================================================*/
create table tasas_interes (
   id_tasa_interes        serial not null,
   base_calculo          varchar(10)          not null
   metodo_calculo        varchar(20)          not null
   frecuencia_capitalizacion varchar(15)          not null
   fecha_inicio_vigencia  date                 not null,
   fecha_fin_vigencia     date                 not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_TASASINTERES primary key (id_tasa_interes)
);

/*==============================================================*/
/* table: telefono_cliente                                       */
/*==============================================================*/
create table telefono_cliente (
   id_telefono           serial not null,
   id_cliente            int4                 not null,
   tipo                 varchar(10)          not null
   numero_telefono       varchar(10)          not null,
   fecha_creacion        timestamp            not null,
   fecha_actualizacion   timestamp            not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_TELEFONOCLIENTE primary key (id_telefono)
);

/*==============================================================*/
/* table: tipo_cuentas                                           */
/*==============================================================*/
create table tipo_cuentas (
   id_tipo_cuenta         serial not null,
   id_moneda             varchar(3)           not null,
   id_tasa_interes_por_defecto int                  not null,
   nombre               varchar(20)          not null
   descripcion          varchar(50)          not null,
   requisitos_apertura   varchar(300)         not null,
   tipo_cliente          varchar(15)          not null
   cuentas_contables_asociadas varchar(50)          not null,
   fecha_creacion        timestamp            not null,
   fecha_modificacion    timestamp            not null,
   estado               varchar(15)          not null
   version              numeric(9)           not null,
   constraint PK_TIPOCUENTAS primary key (id_tipo_cuenta)
);

/*==============================================================*/
/* table: tipos_prestamos                                        */
/*==============================================================*/
create table tipos_prestamos (
   id_tipo_prestamo       serial not null,
   id_moneda             varchar(3)           not null,
   nombre               varchar(50)          not null,
   descripcion          varchar(100)         not null,
   monto_minimo          numeric(15,2)        not null,
   monto_maximo          numeric(15,2)        not null,
   plazo_minimo          int                  not null,
   plazo_maximo          int                  not null,
   requisitos           varchar(300)         not null,
   tipo_cliente          varchar(15)          not null
   fecha_creacion        timestamp            not null default CURRENT_DATE,
   fecha_modificacion    timestamp            not null,
   estado               varchar(15)          not null default 'activo'
   version              numeric(9)           not null,
   constraint PK_TIPOSPRESTAMOS primary key (id_tipo_prestamo)
);

alter table accionistas
   add constraint FK_ACCIONISTAS_EMPRESAS foreign key (id_empresa)
      references empresas (id_empresa)
      on delete restrict on update restrict;

alter table accionistas
   add constraint FK_ACCIONISTAS_EMPRESASPARTICIPE foreign key (id_participe)
      references empresas (id_empresa)
      on delete restrict on update restrict;

alter table accionistas
   add constraint FK_ACCIONISTAS_PERSONAS foreign key (id_participe)
      references personas (id_persona)
      on delete restrict on update restrict;

alter table clientes
   add constraint FK_CLIENTES_EMPRESAS foreign key (id_entidad)
      references empresas (id_empresa)
      on delete restrict on update restrict;

alter table clientes
   add constraint FK_CLIENTES_PAISES foreign key (nacionalidad)
      references paises (id_pais)
      on delete restrict on update restrict;

alter table clientes
   add constraint FK_CLIENTES_PERSONAS foreign key (id_entidad)
      references personas (id_persona)
      on delete restrict on update restrict;

alter table clientes_sucursales
   add constraint FK_CLIENTESSUCURSALES_CLIENTES foreign key (id_cliente)
      references clientes (id_cliente)
      on delete restrict on update restrict;

alter table clientes_sucursales
   add constraint FK_CLIENTESSUCURSALES_SUCURSALES foreign key (id_sucursal)
      references sucursales (id_sucursal)
      on delete restrict on update restrict;

alter table comisiones_cargos
   add constraint FK_COMISIONESCARGOS_SERVICIOSASOCIADOS foreign key (id_servicio)
      references servicios_asociados (id_servicio)
      on delete restrict on update restrict;

alter table comisiones_cargos_cuentas
   add constraint FK_COMISIONESCARGOSCUENTAS_COMISIONESCARGOS foreign key (id_comision_cargo)
      references comisiones_cargos (id_comision_cargo)
      on delete restrict on update restrict;

alter table comisiones_cargos_cuentas
   add constraint FK_COMISIONESCARGOSCUENTAS_CUENTAS foreign key (id_cuenta)
      references cuentas (id_cuenta)
      on delete restrict on update restrict;

alter table comisiones_cargos_prestamos
   add constraint FK_COMISIONESCARGOSPRESTAMOS_COMISIONESPRESTAMOS foreign key (id_comision_prestamo)
      references comisiones_prestamos (id_comision_prestamo)
      on delete restrict on update restrict;

alter table comisiones_cargos_prestamos
   add constraint FK_COMISIONESCARGOSPRESTAMOS_PRESTAMOS foreign key (id_prestamo)
      references prestamos (id_prestamo)
      on delete restrict on update restrict;

alter table condicion_comisiones
   add constraint FK_CONDICIONCOMISIONES_COMISIONESPRESTAMOS foreign key (id_comision_prestamo)
      references comisiones_prestamos (id_comision_prestamo)
      on delete restrict on update restrict;

alter table contacto_transaccion_cliente
   add constraint FK_CONTACTOTRANSACCIONCLIENTE_CLIENTES foreign key (id_cliente)
      references clientes (id_cliente)
      on delete restrict on update restrict;

alter table cronogramas_pagos
   add constraint FK_CRONOGRAMASPAGOS_PRESTAMOSCLIENTES foreign key (id_prestamo_cliente)
      references prestamos_clientes (id_prestamo_cliente)
      on delete restrict on update restrict;

alter table cuentas
   add constraint FK_CUENTAS_TASASINTERES foreign key (id_tasa_interes)
      references tasas_interes (id_tasa_interes)
      on delete restrict on update restrict;

alter table cuentas
   add constraint FK_CUENTAS_TIPOCUENTAS foreign key (id_tipo_cuenta)
      references tipo_cuentas (id_tipo_cuenta)
      on delete restrict on update restrict;

alter table cuentas_clientes
   add constraint FK_CUENTASCLIENTES_CLIENTES foreign key (id_cliente)
      references clientes (id_cliente)
      on delete restrict on update restrict;

alter table cuentas_clientes
   add constraint FK_CUENTASCLIENTES_CUENTAS foreign key (id_cuenta)
      references cuentas (id_cuenta)
      on delete restrict on update restrict;

alter table direccion_cliente
   add constraint FK_DIRECCIONCLIENTE_CLIENTES foreign key (id_cliente)
      references clientes (id_cliente)
      on delete restrict on update restrict;

alter table entidades_bancarias_monedas
   add constraint FK_ENTIDADESBANCARIASMONEDAS_ENTIDADESBANCARIAS foreign key (id_entidad_bancaria)
      references entidades_bancarias (id_entidad_bancaria)
      on delete restrict on update restrict;

alter table entidades_bancarias_monedas
   add constraint FK_ENTIDADESBANCARIASMONEDAS_MONEDAS foreign key (id_moneda)
      references monedas (id_moneda)
      on delete restrict on update restrict;

alter table esquemas_amortizacion
   add constraint FK_ESQUEMASAMORTIZACION_TIPOSPRESTAMOS foreign key (id_tipo_prestamo)
      references tipos_prestamos (id_tipo_prestamo)
      on delete restrict on update restrict;

alter table estructura_geografica
   add constraint FK_ESTRUCTURAGEOGRAFICA_PAISES foreign key (id_pais)
      references paises (id_pais)
      on delete restrict on update restrict;

alter table exenciones_cuentas
   add constraint FK_EXENCIONESCUENTAS_COMISIONESCARGOS foreign key (id_comision)
      references comisiones_cargos (id_comision_cargo)
      on delete restrict on update restrict;

alter table exenciones_prestamos
   add constraint FK_EXENCIONESPRESTAMOS_COMISIONESPRESTAMOS foreign key (id_comision_prestamo)
      references comisiones_prestamos (id_comision_prestamo)
      on delete restrict on update restrict;

alter table feriados
   add constraint FK_FERIADOS_PAISES foreign key (id_pais)
      references paises (id_pais)
      on delete restrict on update restrict;

alter table feriados
   add constraint FK_FERIADOS_LOCACIONGEOGRAFICA foreign key (id_locacion)
      references locacion_geografica (id_locacion)
      on delete restrict on update restrict;

alter table garantias
   add constraint FK_GARANTIAS_TIPOSPRESTAMOS foreign key (id_tipo_prestamo)
      references tipos_prestamos (id_tipo_prestamo)
      on delete restrict on update restrict;

alter table locacion_geografica
   add constraint FK_LOCACIONGEOGRAFICA_ESTRUCTURAGEOGRAFICA foreign key (id_pais, codigo_nivel)
      references estructura_geografica (id_pais, codigo_nivel)
      on delete restrict on update restrict;

alter table locacion_geografica
   add constraint FK_LOCACIONGEOGRAFICA_LOCACIONGEOGRAFICA foreign key (id_locacion_padre_id)
      references locacion_geografica (id_locacion)
      on delete restrict on update restrict;

alter table monedas
   add constraint FK_MONEDAS_PAISES foreign key (id_pais)
      references paises (id_pais)
      on delete restrict on update restrict;

alter table pagos_prestamos
   add constraint FK_PAGOSPRESTAMOS_CRONOGRAMASPAGOS foreign key (id_cuota)
      references cronogramas_pagos (id_cuota)
      on delete restrict on update restrict;

alter table pagos_prestamos
   add constraint FK_PAGOSPRESTAMOS_PRESTAMOSCLIENTES foreign key (id_prestamo_cliente)
      references prestamos_clientes (id_prestamo_cliente)
      on delete restrict on update restrict;

alter table prestamos
   add constraint FK_PRESTAMOS_MONEDAS foreign key (id_moneda)
      references monedas (id_moneda)
      on delete restrict on update restrict;

alter table prestamos
   add constraint FK_PRESTAMOS_TIPOSPRESTAMOS foreign key (id_tipo_prestamo)
      references tipos_prestamos (id_tipo_prestamo)
      on delete restrict on update restrict;

alter table prestamos_clientes
   add constraint FK_PRESTAMOSCLIENTES_CLIENTES foreign key (id_cliente)
      references clientes (id_cliente)
      on delete restrict on update restrict;

alter table prestamos_clientes
   add constraint FK_PRESTAMOSCLIENTES_PRESTAMOS foreign key (id_prestamo)
      references prestamos (id_prestamo)
      on delete restrict on update restrict;

alter table representantes
   add constraint FK_REPRESENTANTES_CLIENTES foreign key (id_cliente)
      references clientes (id_cliente)
      on delete restrict on update restrict;

alter table representantes
   add constraint FK_REPRESENTANTES_EMPRESAS foreign key (id_empresa)
      references empresas (id_empresa)
      on delete restrict on update restrict;

alter table seguros_prestamos
   add constraint FK_SEGUROSPRESTAMOS_PRESTAMOS foreign key (id_prestamo)
      references prestamos (id_prestamo)
      on delete restrict on update restrict;

alter table seguros_prestamos
   add constraint FK_SEGUROSPRESTAMOS_SEGUROS foreign key (id_seguro)
      references seguros (id_seguro)
      on delete restrict on update restrict;

alter table servicios_asociados_cuentas
   add constraint FK_SERVICIOSASOCIADOSCUENTAS_CUENTAS foreign key (id_cuenta)
      references cuentas (id_cuenta)
      on delete restrict on update restrict;

alter table servicios_asociados_cuentas
   add constraint FK_SERVICIOSASOCIADOSCUENTAS_SERVICIOSASOCIADOS foreign key (id_servicio)
      references servicios_asociados (id_servicio)
      on delete restrict on update restrict;

alter table sucursales
   add constraint FK_SUCURSALES_ENTIDADESBANCARIAS foreign key (id_entidad_bancaria)
      references entidades_bancarias (id_entidad_bancaria)
      on delete restrict on update restrict;

alter table sucursales
   add constraint FK_SUCURSALES_LOCACIONGEOGRAFICA foreign key (id_locacion)
      references locacion_geografica (id_locacion)
      on delete restrict on update restrict;

alter table tasa_plazos
   add constraint FK_TASAPLAZOS_TASASINTERES foreign key (id_tasa_interes)
      references tasas_interes (id_tasa_interes)
      on delete restrict on update restrict;

alter table tasa_saldos
   add constraint FK_TASASALDOS_TASASINTERES foreign key (id_tasa_interes)
      references tasas_interes (id_tasa_interes)
      on delete restrict on update restrict;

alter table telefono_cliente
   add constraint FK_TELEFONOCLIENTE_CLIENTES foreign key (id_cliente)
      references clientes (id_cliente)
      on delete restrict on update restrict;

alter table tipo_cuentas
   add constraint FK_TIPOCUENTAS_MONEDAS foreign key (id_moneda)
      references monedas (id_moneda)
      on delete restrict on update restrict;

alter table tipo_cuentas
   add constraint FK_TIPOCUENTAS_TASASINTERES foreign key (id_tasa_interes_por_defecto)
      references tasas_interes (id_tasa_interes)
      on delete restrict on update restrict;

alter table tipos_prestamos
   add constraint FK_TIPOSPRESTAMOS_MONEDAS foreign key (id_moneda)
      references monedas (id_moneda)
      on delete restrict on update restrict;