use cruceroBD;
go


---Cargado de datos---

---CONTINENTE---
insert [dbo].[continente] ([idContinente], [nombre]) values ('EU', 'Europa')
insert [dbo].[continente] ([idContinente], [nombre]) values ('AF', 'África')
insert [dbo].[continente] ([idContinente], [nombre]) values ('OC', 'Oceanía')
insert [dbo].[continente] ([idContinente], [nombre]) values ('NA', 'América del Norte')
insert [dbo].[continente] ([idContinente], [nombre]) values ('SA', 'América del Sur')
insert [dbo].[continente] ([idContinente], [nombre]) values ('AS', 'Asia')

---PAIS---
insert [dbo].[pais] ([idPais], [nombre], [idContinente]) values ('ESP', 'España', 'EU')
insert [dbo].[pais] ([idPais], [nombre], [idContinente]) values ('ITA', 'Italia', 'EU')
insert [dbo].[pais] ([idPais], [nombre], [idContinente]) values ('FRA', 'Francia', 'EU')
insert [dbo].[pais] ([idPais], [nombre], [idContinente]) values ('MCO', 'Mónaco', 'EU')
insert [dbo].[pais] ([idPais], [nombre], [idContinente]) values ('MLT', 'Malta', 'EU')
insert [dbo].[pais] ([idPais], [nombre], [idContinente]) values ('HRV', 'Croacia', 'EU')
insert [dbo].[pais] ([idPais], [nombre], [idContinente]) values ('MNE', 'Montenegro', 'EU')
insert [dbo].[pais] ([idPais], [nombre], [idContinente]) values ('GRC', 'Grecia', 'EU')
insert [dbo].[pais] ([idPais], [nombre], [idContinente]) values ('DEU', 'Alemania', 'EU')
insert [dbo].[pais] ([idPais], [nombre], [idContinente]) values ('SWE', 'Suecia', 'EU')
insert [dbo].[pais] ([idPais], [nombre], [idContinente]) values ('DNK', 'Dinamarca', 'EU')
insert [dbo].[pais] ([idPais], [nombre], [idContinente]) values ('NLD', 'Países Bajos', 'EU')

---CIUDADES---
set identity_insert [dbo].[ciudad] on
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (1, 'Barcelona', 'ESP')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (2, 'Valencia', 'ESP')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (3, 'Roma', 'ITA')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (4, 'Nápoles', 'ITA')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (5, 'Venecia', 'ITA')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (6, 'Palermo', 'ITA')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (7, 'Marsella', 'FRA')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (8, 'Niza', 'FRA')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (9, 'Mónaco', 'MCO')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (10, 'La Valetta', 'MLT')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (11, 'Dubrovnik', 'HRV')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (12, 'Kotor', 'MNE')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (13, 'Atenas', 'GRC')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (14, 'Mykonos', 'GRC')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (15, 'Santorini', 'GRC')

insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (16, 'Hamburgo', 'DEU')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (17, 'Gotemburgo', 'SWE')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (18, 'Malmo', 'SWE')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (19, 'Estocolmo', 'SWE')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (20, 'Copenague', 'DNK')
insert [dbo].[ciudad] ([idCiudad], [nombre], [idPais]) values (21, 'Ámsterdam', 'NLD')
set identity_insert [dbo].[ciudad] off

----RUTAS---
set identity_insert [dbo].[ruta] on
insert [dbo].[ruta] ([idRuta], [nombre], [duracion]) values (1, 'Mediteráneo 1', 10)
insert [dbo].[ruta] ([idRuta], [nombre], [duracion]) values (2, 'Mediteráneo 2', 7)
insert [dbo].[ruta] ([idRuta], [nombre], [duracion]) values (3, 'Mediteráneo 3', 5)

insert [dbo].[ruta] ([idRuta], [nombre], [duracion]) values (4, 'Báltico 1', 4)
insert [dbo].[ruta] ([idRuta], [nombre], [duracion]) values (5, 'Báltico 2', 4)
set identity_insert [dbo].[ruta] off

---CIUDADES EN RUTAS---
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (1, 1, 1)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (1, 8, 2)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (1, 4, 3)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (1, 12, 4)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (1, 14, 5)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (1, 15, 6)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (1, 10, 7)

insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (2, 2, 1)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (2, 7, 2)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (2, 3, 3)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (2, 5, 4)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (2, 13, 5)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (2, 15, 6)

insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (3, 5, 1)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (3, 9, 2)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (3, 3, 3)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (3, 8, 4)

insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (4, 20, 1)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (4, 16, 2)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (4, 18, 3)

insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (4, 21, 1)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (4, 19, 2)
insert [dbo].[ciudadRuta] ([idRuta], [idCiudad], [numParada]) values (4, 17, 3)

---PUESTO TRABAJO---
set identity_insert [dbo].[puestoTrabajo] on
insert [dbo].[puestoTrabajo] ([idPuesto], [nombre]) values (1, 'Personal de limpieza')
insert [dbo].[puestoTrabajo] ([idPuesto], [nombre]) values (2, 'Camarero/a')
insert [dbo].[puestoTrabajo] ([idPuesto], [nombre]) values (3, 'Mantenimiento')
insert [dbo].[puestoTrabajo] ([idPuesto], [nombre]) values (4, 'Socorrista')
insert [dbo].[puestoTrabajo] ([idPuesto], [nombre]) values (5, 'Animación')
insert [dbo].[puestoTrabajo] ([idPuesto], [nombre]) values (6, 'Asistente de tienda')
insert [dbo].[puestoTrabajo] ([idPuesto], [nombre]) values (7, 'Personal de spa')
insert [dbo].[puestoTrabajo] ([idPuesto], [nombre]) values (8, 'Fotógrafo')
insert [dbo].[puestoTrabajo] ([idPuesto], [nombre]) values (9, 'Cocinero/a')
set identity_insert [dbo].[puestoTrabajo] off

---EMPLEADO---
set identity_insert [dbo].[empleado] on
insert [dbo].[empleado] ([idEmpleado], [nombre], [apellido1], [apellido2], [fechaNacimiento], [NIF], [email], [telefono], [idPuesto], [fechaContratacion])
	values (1, 'Juan', 'Pérez', 'Domínguez', '13/12/1989', '11111111A', 'abc@gmail.com', '123456789', 1, '23/07/2020')
insert [dbo].[empleado] ([idEmpleado], [nombre], [apellido1], [apellido2], [fechaNacimiento], [NIF], [email], [telefono], [idPuesto], [fechaContratacion])
	values (2, 'Rosa', 'Gutiérrez', 'garcía', '02/08/1970', '22222222A', '123@gmail.com', '123456789', 2, '23/07/2012')
insert [dbo].[empleado] ([idEmpleado], [nombre], [apellido1], [fechaNacimiento], [NIF], [email], [telefono], [idPuesto], [fechaContratacion])
	values (3, 'Sophia', 'Harris', '17/01/1999', '33333333A', 'sph@gmail.com', '123456789', 3, '01/01/2022')
insert [dbo].[empleado] ([idEmpleado], [nombre], [apellido1], [fechaNacimiento], [NIF], [email], [telefono], [idPuesto], [fechaContratacion], [fechaDespido])
	values (4, 'Rodrigo', 'Domínguez', '31/05/1987', '44444444A', 'rdg@gmail.com', '123456789', 4, '23/07/2018', '13/03/2020')
insert [dbo].[empleado] ([idEmpleado], [nombre], [apellido1], [fechaNacimiento], [NIF], [email], [telefono], [idPuesto], [fechaContratacion], [fechaDespido])
	values (5, 'Marc', 'Anthony', '16/09/1968', '55555555A', 'mrc@gmail.com', '123456789', 5, '12/05/2004', '31/08/2006')
set identity_insert [dbo].[empleado] off

---BARCO---
set identity_insert [dbo].[barco] on
insert [dbo].[barco] ([idBarco], [nombre], [numCamarotes], [fechaAdquisicion], [ultimaRenovacion])
	values (1, 'Anchoa of the Seas', 3000, '01/01/2002', '01/01/2020')
insert [dbo].[barco] ([idBarco], [nombre], [numCamarotes], [fechaAdquisicion])
	values (2, 'Quesada of the Seas', 1000, '01/01/1999')
insert [dbo].[barco] ([idBarco], [nombre], [numCamarotes], [fechaAdquisicion])
	values (3, 'Sobao of the Seas', 3500, '01/01/2017')
set identity_insert [dbo].[barco] off

---TRAYECTO---
insert [dbo].[trayecto] ([idBarco], [idRuta], [fechaSalida]) values (1, 2, '01/06/2002')
insert [dbo].[trayecto] ([idBarco], [idRuta], [fechaSalida]) values (1, 3, '25/12/2016')

insert [dbo].[trayecto] ([idBarco], [idRuta], [fechaSalida]) values (2, 4, '04/06/2020')
insert [dbo].[trayecto] ([idBarco], [idRuta], [fechaSalida]) values (2, 5, '01/09/2017')

insert [dbo].[trayecto] ([idBarco], [idRuta], [fechaSalida]) values (3, 1, '15/07/2017')

---PERSONAL BARCO---
insert [dbo].[personalBarco] ([idBarco], [idEmpleado], [fechaEmbarco], [fechaDesembarco]) values (2, 1, '04/06/2020', '01/10/2020')
insert [dbo].[personalBarco] ([idBarco], [idEmpleado], [fechaEmbarco], [fechaDesembarco]) values (1, 2, '01/06/2002', '24/08/2012')
insert [dbo].[personalBarco] ([idBarco], [idEmpleado], [fechaEmbarco], [fechaDesembarco]) values (2, 2, '04/06/2020', '01/09/2022')
insert [dbo].[personalBarco] ([idBarco], [idEmpleado], [fechaEmbarco]) values (3, 3, '01/03/2022')
insert [dbo].[personalBarco] ([idBarco], [idEmpleado], [fechaEmbarco], [fechaDesembarco]) values (3, 4, '15/07/2017', '13/03/2020')
insert [dbo].[personalBarco] ([idBarco], [idEmpleado], [fechaEmbarco], [fechaDesembarco]) values (2, 5, '04/06/2020', '31/08/2022')
insert [dbo].[personalBarco] ([idBarco], [idEmpleado], [fechaEmbarco], [fechaDesembarco]) values (2, 5, '01/05/2005', '31/08/2006')

---CAMAROTES---
set identity_insert [dbo].[camarote] on
insert [dbo].[camarote] ([idCamarote], [nombre], [ocupacionMaxima], [vistaAlMar]) values (1, 'Personal', 3, 'NO')
insert [dbo].[camarote] ([idCamarote], [nombre], [ocupacionMaxima], [vistaAlMar]) values (2, 'Vista Interior', 4, 'NO')
insert [dbo].[camarote] ([idCamarote], [nombre], [ocupacionMaxima], [vistaAlMar]) values (3, 'Vista Exterior', 4, 'SI')
insert [dbo].[camarote] ([idCamarote], [nombre], [ocupacionMaxima], [vistaAlMar]) values (4, 'Exterior con balcón', 6, 'SI')
insert [dbo].[camarote] ([idCamarote], [nombre], [ocupacionMaxima], [vistaAlMar]) values (5, 'Suite', 6, 'SI')
set identity_insert [dbo].[camarote] off

---PRECIO CAMAROTE BARCO---
insert [dbo].[precioCamaroteBarco] ([idBarco], [idCamarote], [precioPorNoche]) values (1, 1, 0)
insert [dbo].[precioCamaroteBarco] ([idBarco], [idCamarote], [precioPorNoche]) values (1, 2, 100)
insert [dbo].[precioCamaroteBarco] ([idBarco], [idCamarote], [precioPorNoche]) values (1, 3, 150)
insert [dbo].[precioCamaroteBarco] ([idBarco], [idCamarote], [precioPorNoche]) values (1, 4, 200)
insert [dbo].[precioCamaroteBarco] ([idBarco], [idCamarote], [precioPorNoche]) values (1, 5, 250)

insert [dbo].[precioCamaroteBarco] ([idBarco], [idCamarote], [precioPorNoche]) values (2, 1, 0)
insert [dbo].[precioCamaroteBarco] ([idBarco], [idCamarote], [precioPorNoche]) values (2, 2, 75)
insert [dbo].[precioCamaroteBarco] ([idBarco], [idCamarote], [precioPorNoche]) values (2, 3, 125)
insert [dbo].[precioCamaroteBarco] ([idBarco], [idCamarote], [precioPorNoche]) values (2, 4, 175)
insert [dbo].[precioCamaroteBarco] ([idBarco], [idCamarote], [precioPorNoche]) values (2, 5, 225)

insert [dbo].[precioCamaroteBarco] ([idBarco], [idCamarote], [precioPorNoche]) values (3, 1, 0)
insert [dbo].[precioCamaroteBarco] ([idBarco], [idCamarote], [precioPorNoche]) values (3, 2, 150)
insert [dbo].[precioCamaroteBarco] ([idBarco], [idCamarote], [precioPorNoche]) values (3, 3, 200)
insert [dbo].[precioCamaroteBarco] ([idBarco], [idCamarote], [precioPorNoche]) values (3, 4, 250)
insert [dbo].[precioCamaroteBarco] ([idBarco], [idCamarote], [precioPorNoche]) values (3, 5, 300)

---NUM CAMAROTES BARCO---
insert [dbo].[numeroCamarotesBarco] ([idBarco], [idCamarote], [numCamarotes]) values (1, 1, 300)
insert [dbo].[numeroCamarotesBarco] ([idBarco], [idCamarote], [numCamarotes]) values (1, 2, 1000)
insert [dbo].[numeroCamarotesBarco] ([idBarco], [idCamarote], [numCamarotes]) values (1, 3, 100)
insert [dbo].[numeroCamarotesBarco] ([idBarco], [idCamarote], [numCamarotes]) values (1, 4, 500)
insert [dbo].[numeroCamarotesBarco] ([idBarco], [idCamarote], [numCamarotes]) values (1, 5, 200)

insert [dbo].[numeroCamarotesBarco] ([idBarco], [idCamarote], [numCamarotes]) values (2, 1, 50)
insert [dbo].[numeroCamarotesBarco] ([idBarco], [idCamarote], [numCamarotes]) values (2, 2, 400)
insert [dbo].[numeroCamarotesBarco] ([idBarco], [idCamarote], [numCamarotes]) values (2, 3, 445)
insert [dbo].[numeroCamarotesBarco] ([idBarco], [idCamarote], [numCamarotes]) values (2, 4, 100)
insert [dbo].[numeroCamarotesBarco] ([idBarco], [idCamarote], [numCamarotes]) values (2, 5, 5)

insert [dbo].[numeroCamarotesBarco] ([idBarco], [idCamarote], [numCamarotes]) values (3, 1, 350)
insert [dbo].[numeroCamarotesBarco] ([idBarco], [idCamarote], [numCamarotes]) values (3, 2, 1200)
insert [dbo].[numeroCamarotesBarco] ([idBarco], [idCamarote], [numCamarotes]) values (3, 3, 1000)
insert [dbo].[numeroCamarotesBarco] ([idBarco], [idCamarote], [numCamarotes]) values (3, 4, 500)
insert [dbo].[numeroCamarotesBarco] ([idBarco], [idCamarote], [numCamarotes]) values (3, 5, 450)

---CLIENTE---
set identity_insert [dbo].[cliente] on
insert [dbo].[cliente] ([idCliente], [nombre], [apellido1], [apellido2], [fechaNacimiento], [NIF], [email], [telefono])
	values (1, 'Roberto', 'Fernández', 'González', '24/01/1978', '12345678A', 'rob@gmail.com', '111111111')
insert [dbo].[cliente] ([idCliente], [nombre], [apellido1], [apellido2], [fechaNacimiento], [NIF], [email], [telefono])
	values (2, 'Lucía', 'Ruiz', 'Pérez', '15/05/1983', '12345678B', 'luc@gmail.com', '111111112')
insert [dbo].[cliente] ([idCliente], [nombre], [apellido1], [fechaNacimiento], [NIF], [email], [telefono])
	values (3, 'Pablo', 'Smith', '03/03/1999', '12345678C', 'pab@gmail.com', '111111113')
set identity_insert [dbo].[cliente] off

---RÉGIMEN ALOJAMIENTO---
set identity_insert [dbo].[regimenAlojamiento] on
insert [dbo].[regimenAlojamiento] ([idRegimenAlojamiento], [nombre], [precioDia]) values (1, 'Todo Incluido', 30)
insert [dbo].[regimenAlojamiento] ([idRegimenAlojamiento], [nombre], [precioDia]) values (2, 'Pensión Completa', 15)
set identity_insert [dbo].[regimenAlojamiento] off

---RESERVA---
set identity_insert [dbo].[reserva] on
insert [dbo].[reserva] ([idReserva], [idBarco], [idRuta], [fechaSalida], [numPersonas], [idCamarote], [idRegimenAlojamiento])
	values (1, 1, 2, '01/06/2002', 2, 4, 1)
insert [dbo].[reserva] ([idReserva], [idBarco], [idRuta], [fechaSalida], [numPersonas], [idCamarote], [idRegimenAlojamiento])
	values (2, 1, 3, '25/12/2016', 1, 2, 2)
insert [dbo].[reserva] ([idReserva], [idBarco], [idRuta], [fechaSalida], [numPersonas], [idCamarote], [idRegimenAlojamiento])
	values (3, 2, 5, '01/09/2017', 1, 3, 2)
set identity_insert [dbo].[reserva] off

---CLIENTES RESERVA---
insert [dbo].[clientesReserva] ([idCliente], [idReserva]) values (1, 1)
insert [dbo].[clientesReserva] ([idCliente], [idReserva]) values (2, 1)
insert [dbo].[clientesReserva] ([idCliente], [idReserva]) values (1, 2)
insert [dbo].[clientesReserva] ([idCliente], [idReserva]) values (3, 3)