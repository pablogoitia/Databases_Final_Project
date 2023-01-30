use master;
go

if exists(select * from sys.databases where name='cruceroBD')
  drop database cruceroBD
go

create database cruceroBD;
go

use cruceroBD;
go

create table continente(
	idContinente char(2) not null,
	nombre varchar(20) unique not null,

	constraint UNQ_nombre_continente unique(nombre),
	constraint PK_continente primary key(idContinente)
);
go

create table pais(
	idPais char(3) not null,
	nombre varchar(20) unique not null,
	idContinente char(2) not null

	constraint UNQ_nombre_pais unique(nombre),
	constraint PK_pais primary key(idPais),
	constraint FK_pais_idContinente foreign key (idContinente) references continente(idContinente)
);
go

create table ciudad(
	idCiudad int not null identity(1,1),
	nombre varchar(20) unique not null,
	idPais char(3) not null,

	constraint CHK_nombre_ciudad unique(nombre),
	constraint PK_ciudad primary key (idCiudad),
	constraint FK_ciudad_idPais foreign key (idPais) references pais(idPais)
);
go

create table ruta(
	idRuta int not null identity(1,1),
	nombre varchar(20) unique not null,
	descripcion varchar(80) null,
	duracion int not null,

	constraint UNQ_nombre_ruta unique(nombre),
	constraint PK_ruta primary key (idRuta)
);
go

create table ciudadRuta(
	idRuta int not null,
	idCiudad int not null,
	numParada int not null,

	constraint PK_ciudadRuta primary key (idRuta, idCiudad, numParada),
	constraint FK_ciudadRuta_idRuta foreign key (idRuta) references ruta (idRuta),
	constraint FK_ciudadRuta_idCiudad foreign key (idCiudad) references ciudad (idCiudad)
);
go

create table puestoTrabajo(
	idPuesto int not null identity(1,1),
	nombre varchar(20) unique not null,
	descripcion varchar(80) null,

	constraint UNQ_nombre_puestoTrabajo unique(nombre),
	constraint PK_puestoTrabajo primary key (idPuesto)
);
go

create table empleado(
	idEmpleado int not null identity(1,1),
	nombre varchar(20) not null,
	apellido1 varchar(20) not null,
	apellido2 varchar(20) null,
	fechaNacimiento date not null,
	NIF char(9) not null,
	email varchar(100) not null,
	telefono char(9) not null,
	idPuesto int not null,
	fechaContratacion date not null,
	fechaDespido date null,

	constraint PK_empleado primary key (idEmpleado),
	constraint CHK_edad check (datediff(year, getdate(), fechaNacimiento) < 16),
	constraint CHK_email_empleado check (email like ('%@%')),
	constraint CHK_fechas_empleado check (fechaContratacion <= fechaDespido),
	constraint CHK_NIF_empleado check (NIF like ('[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][A-Z]') or NIF like ('[A-Z][0-9][0-9][0-9][0-9][0-9][0-9][0-9]_')),
	constraint CHK_telefono_empleado check (telefono like ('[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')),
	constraint UNQ_NIF_empleado unique (NIF),
	constraint FK_empleado_idPuesto foreign key (idPuesto) references puestoTrabajo (idPuesto)

);
go

create table barco(
	idBarco int not null identity(1,1),
	nombre varchar(80) unique not null,
	numCamarotes int not null,
	fechaAdquisicion date not null,
	ultimaRenovacion date null,
	fechaBaja date null,
	
	constraint CHK_renueva_barco check (fechaAdquisicion < ultimaRenovacion),
	constraint CHK_baja_adquisicion_barco check (fechaAdquisicion < fechaBaja),
	constraint CHK_baja_renovacion_barco check (ultimaRenovacion < fechaBaja),
	constraint CHK_numCamarotes check (numCamarotes >= 0),
	constraint PK_barco primary key (idBarco),
);
go

create table trayecto (
	idBarco int not null,
	idRuta int not null,
	fechaSalida datetime not null,

	constraint PK_viajeBarcoRuta primary key (idBarco, idRuta, fechaSalida),
	constraint FK_barco_idBarco foreign key (idBarco) references barco (idBarco),
	constraint FK_ruta_idRuta foreign key (idRuta) references ruta (idRuta)
);
go


create table personalBarco (
	idBarco int not null,
	idEmpleado int not null,
	fechaEmbarco date not null,
	fechaDesembarco date null,

	constraint CHK_fechas_personalBarco check (fechaEmbarco < fechaDesembarco), 
	constraint FK_personalBarco_idBarco foreign key (idBarco) references barco (idBarco),
	constraint FK_personalBarco_idEmpleado foreign key (idEmpleado) references empleado (idEmpleado),
	constraint PK_personalBarco primary key (idBarco, idEmpleado, fechaEmbarco)
);
go

create table camarote (
	idCamarote int not null identity(1,1),
	nombre varchar(20) unique not null,
	descripcion varchar(80) null,
	ocupacionMaxima int not null,
	vistaAlMar char(2) not null,

	constraint CHK_ocupacionMaxima_camarote check (ocupacionMaxima >= 0),
	constraint CHK_vistaAlMar_camarote check (vistaAlMar like ('SI') or vistaAlMar like ('NO')),
	constraint PK_camarote primary key (idCamarote)
);
go

create table precioCamaroteBarco (
	idBarco int not null,
	idCamarote int not null,
	precioPorNoche money not null,

	constraint CHK_precioPorNoche_precioCamaroteBarco check (precioPorNoche >= 0),
	constraint PK_precioCamaroteBarco primary key (idBarco, idCamarote),
	constraint FK_precioCamaroteBarco_idBarco foreign key (idBarco) references barco (idBarco),
	constraint FK_precioCamaroteBarco_idCamarote foreign key (idCamarote) references camarote (idCamarote)
);
go

create table numeroCamarotesBarco (
	idBarco int not null,
	idCamarote int not null,
	numCamarotes int not null,

	constraint CHK_numCamarotes_numeroCamaroteBarco check (numCamarotes > 0),
	constraint PK_numeroCamarotesBarco primary key (idBarco, idCamarote),
	constraint FK_numeroCamarotesBarco_idBarco foreign key (idBarco) references barco (idBarco),
	constraint FK_numeroCamarotesBarco_idCamarote foreign key (idCamarote) references camarote (idCamarote),
);
go


create table cliente (
	idCliente int not null identity(1,1),
	nombre varchar(20) not null,
	apellido1 varchar(20) not null,
	apellido2 varchar(20) null,
	fechaNacimiento date not null,
	NIF char(9) not null,
	email varchar(100) not null,
	telefono char(9) not null,

	constraint CHK_NIF_cliente check (NIF like ('[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][A-Z]') or NIF like ('[A-Z][0-9][0-9][0-9][0-9][0-9][0-9][0-9]_')),
	constraint CHK_email_cliene check (email like ('%@%')),
	constraint CHK_telefono_cliente check (telefono like ('[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')),
	constraint UNQ_NIF_cliente unique (NIF),
	constraint PK_cliente primary key (idCliente),
);
go

create table regimenAlojamiento (
	idRegimenAlojamiento int not null identity(1,1),
	nombre varchar(20) unique not null,
	descripcion varchar(80) null,
	precioDia decimal(4,2) not null,

	constraint UNQ_nombre_regimenAlojamiento unique (nombre),
	constraint CHK_precioDia_regimenAlojamiento check(precioDia > 0),
	constraint PK_regimenAlojamiento primary key (idRegimenAlojamiento),
);
go

create table reserva (
	idReserva int not null identity(1,1),
	idBarco int not null,
	idRuta int not null,
	fechaSalida datetime not null,
	numPersonas int not null,
	idCamarote int not null,
	idRegimenAlojamiento int not null,
	importe decimal (7, 2) null,

	constraint CHK_numPersonas_reserva check (numPersonas > 0),
	constraint PK_reserva primary key (idReserva),
	constraint FK_reserva_idBarco foreign key (idBarco) references barco (idBarco),
	constraint FK_reserva_idRuta foreign key (idRuta) references ruta (idRuta),
	constraint FK_reserva_idCamarote foreign key (idCamarote) references camarote (idCamarote),
	constraint FK_reserva_idRegimenAlojamiento foreign key (idRegimenAlojamiento) references regimenAlojamiento (idRegimenAlojamiento),
	constraint FK_reserva_trayecto foreign key (idBarco, idRuta, fechaSalida) references trayecto (idBarco, idRuta, fechaSalida),
);
go

create table clientesReserva (
	idCliente int not null,
	idReserva int not null,

	constraint PK_clientesReserva primary key (idCliente, idReserva),
	constraint FK_clientesReserva_idCliente foreign key (idCliente) references cliente (idCliente),
	constraint FK_clientesReserva_idReserva foreign key (idReserva) references reserva (idReserva)
);
go