use cruceroBD
go

-----------------
--- FUNCIONES ---
-----------------

/*
 * Muestra todas la informacion de las ciudades por las que transcurre la ruta pasado como parámetro.
 */
create or alter function infoCiudadesRuta (@idRuta int)
returns table
as
return
	select c.nombre, c.idPais
		from ciudad c inner join ciudadRuta cd
		on c.idCiudad = cd.idCiudad
		where cd.idRuta = @idRuta
go

--Prueba función---
select * from infoCiudadesRuta(3)
go


/*
 * Muestra las ciudades de el código de país pasado como parámetro.
 */
create or alter function ciudadesPais (@idpais char(3))
returns table
as return
	select c.idCiudad, c.nombre
		from ciudad c
		where c.idPais = @idpais
go

---Prueba función---
select * from ciudadesPais('ITA')
go

/*
 * Obtiene el número de personas que se encuentran en un barco con una fecha de salida.
 */
create or alter function numPersonasBarco (@idBarco int, @fecha date)
returns int
as
begin
return (select sum(r.numPersonas)
		from reserva r
		where r.idBarco = @idBarco and r.fechaSalida = @fecha)
end
go

---Prueba función---
select dbo.numPersonasBarco(2, '01/09/2017')
go

/*
 * Obtiene el precio total de una reserva.
 */
create or alter function precioReserva (@idReserva int)
returns decimal (7,2)
as
begin
	declare @numPersonasReserva int
	declare @idCamaroteReserva int
	declare @idRutaReserva int
	declare @idRegimenAlojamiento int
	declare @idBarcoReserva int

	select @numPersonasReserva = r.numPersonas, @idCamaroteReserva = r.idCamarote, @idRutaReserva = r.idRuta, 
		   @idRegimenAlojamiento = r.idRegimenAlojamiento, @idBarcoReserva = r.idBarco
		from reserva r
		where @idReserva = r.idReserva

	declare @precioRegimenAlojamiento decimal(5,2)
	select @precioRegimenAlojamiento = a.precioDia
		from regimenAlojamiento a
		where @idRegimenAlojamiento = a.idRegimenAlojamiento

	declare @precioCamarote int
	select @precioCamarote = p.precioPorNoche
		from precioCamaroteBarco p
		where @idBarcoReserva = p.idBarco and @idCamaroteReserva = p.idCamarote

	declare @duracionRuta int
	select @duracionRuta = r.duracion
		from ruta r
		where @idRutaReserva = r.idRuta

	declare @precioTotal decimal(7,2)
	set @precioTotal = @numPersonasReserva * (@precioCamarote * @duracionRuta + @precioRegimenAlojamiento * @duracionRuta)
	return @precioTotal
end
go

---Prueba función---
select dbo.precioReserva(2)
go


----------------------
--- PROCEDIMIENTOS ---
----------------------

/*
 * Muestra errores
 */
CREATE OR ALTER PROCEDURE usp_showerrorinfo
AS
	SELECT ERROR_NUMBER() AS [Numero de Error],
	ERROR_STATE() AS [Estado del Error],
	ERROR_SEVERITY() AS [Severidad del Error],
	ERROR_LINE() AS [Linea],
	ISNULL(ERROR_PROCEDURE(), 'No esta en un proc') AS [Procedimiento],
	ERROR_MESSAGE() AS [Mensaje]
GO

/*
 * Inserta un nuevo empleado, si este ya ha trabajado en la empresa y ha sido despedido,
 * se actualiza la fecha de contratación y la de despido pasa a ser null
 */
create or alter procedure insertaEmpleado @nombre varchar(20), @apellido1 varchar(20), @apellido2 varchar(20),
							@fechaNacimiento date, @NIF char(9), @email varchar(100), @telefono char(9), @idPuesto int as
begin
	begin try
		begin transaction
			if (select count(*) from empleado e where @NIF = e.NIF and e.fechaDespido is not null) > 0 begin
				update empleado
					set fechaContratacion = getdate(), fechaDespido = null
					where empleado.NIF = @NIF
			end;
			if (select count(*) from empleado e where @NIF = e.NIF) = 0 begin
				insert into empleado (nombre, apellido1, apellido2, fechaNacimiento, NIF, email, telefono, idPuesto, fechaContratacion, fechaDespido)
					values (@nombre, @apellido1, @apellido2, @fechaNacimiento, @NIF, @email, @telefono, @idPuesto, getdate(), null)
			end;
		commit transaction
	end try
	begin catch
		rollback transaction
		exec usp_showerrorinfo
	end catch
end;
go

---Prueba procedimiento---
select * from empleado
exec insertaEmpleado 'Juan', 'de la Cosa', null, '28/02/1510', '44444444A', 'JNDLACOSA@SIGLOXVI.es', '111111111', 1 /*Actualizar fecha de contratacion y despido*/
select * from empleado
exec insertaEmpleado 'Juan', 'de la Cosa', null, '28/02/1510', '44444444B', 'JNDLACOSA@SIGLOXVI.es', '111111111', 1 /*Añadir empleado*/
select * from empleado
go


/*
 * Añade un barco a la base de datos si no hay error, en caso contrario, se notifica
 */
create or alter proc insertaBarco @nombre varchar(20), @anhoConstruccion date, @anhoRenovacion date, @numCamarotes int as begin
	begin try
		begin transaction
			insert into barco (nombre, fechaAdquisicion, ultimaRenovacion, numCamarotes)
			values (@nombre, @anhoConstruccion, @anhoRenovacion, @numCamarotes)
		commit transaction
	end try
	begin catch
		rollback transaction
		exec usp_showerrorinfo
	end catch
end;
go

---Prueba procedimiento---
select * from barco
exec insertaBarco 'Harmony of the seas', '2020', null, 500
select * from barco
go

/*
 * Guarda el precio calculado de una reserva en el campo importe
 */
create or alter proc guardaPrecioReserva @idreserva int as
begin
	begin try
		begin transaction
			if (select count(*) from reserva r where r.idReserva = @idreserva) = 0 begin
				raiserror('No se encuetra ese número de reserva',16,1)
				end;
			declare @precioReserva  int
			select @precioReserva = dbo.precioReserva(@idreserva)
			update reserva
				set importe = @precioReserva
				where reserva.idReserva = @idreserva
		commit transaction
	end try
	begin catch
		rollback transaction
		exec usp_showerrorinfo
	end catch
end;
go

---Prueba procedimiento---
select * from reserva
exec guardaPrecioReserva 1
select * from reserva
go

/*
 * Se crea una reserva. Se debe comprobar que todas las personas entran en el camarote indicado/hay camarotes disponibles del tipo seleccionado
 */
create or alter proc insertaReserva @idBarco int, @idRuta int, @fechaSalida datetime, @numPersonas int, @idCamarote int, @idRegimenAlojamiento int as
begin
	begin try
		begin transaction
			declare @maxCamarotes int
			select @maxCamarotes = count(*)
				from numeroCamarotesBarco n
				where n.idBarco = @idBarco and n.idCamarote = @idCamarote
			declare @camarotesOcupados int
			select @camarotesOcupados = count(*)
				from reserva r
				where r.idBarco = @idBarco and r.fechaSalida = @fechaSalida and r.idCamarote = @idCamarote
			if (@camarotesOcupados) >= @maxCamarotes begin
				raiserror('No hay plazas disponibles para ese tipo de camarote',16,1)
			end;
			declare @maxOcupacionCamarote int
			select @maxOcupacionCamarote = c.ocupacionMaxima
				from camarote c
				where c.idCamarote = @idCamarote
			if (@numPersonas) > @maxOcupacionCamarote begin
				raiserror('El camarote escogido es demasiado pequeño para este número de personas',16,1)
			end;
			insert into reserva (idBarco, idRuta, fechaSalida, numPersonas, idCamarote, idRegimenAlojamiento)
				values (@idBarco, @idRuta, @fechaSalida, @numPersonas, @idCamarote, @idRegimenAlojamiento)
		commit transaction	
	end try
	begin catch
		rollback transaction
		exec usp_showerrorinfo
	end catch
end;
go

---Prueba procedimiento---
select * from reserva
exec insertaReserva 1, 2, '01/06/2002', 800, 1,1
go

/*
 * Asocia un cliente a determinada reserva si no se ha alcanzado el máximo
 */
create or alter proc asociaClienteReserva @idCliente int, @idReserva int as
begin
	begin try
		begin transaction
			declare @numClientesReservaActual int
			select @numClientesReservaActual = count(*)
				from clientesReserva c
				where c.idReserva = @idReserva
			declare @numClientesReservaMax int
			select @numClientesReservaMax = r.numPersonas
				from reserva r
				where r.idReserva = @idReserva
			if (@numClientesReservaActual) = @numClientesReservaMax begin
				raiserror('No se puede añadir a esta persona en la reserva, se ha alcanzado el máximo',16,1)
			end;
			insert into clientesReserva (idCliente, idReserva)
				values (@idCliente, @idReserva)
		commit transaction
	end try
	begin catch
		rollback transaction
		exec usp_showerrorinfo
	end catch
end;
go

---Prueba procedimiento
select * from clientesReserva
select * from reserva
exec asociaClienteReserva 2, 2
go


------------------
--- DISPARADOR ---
------------------

/*
 * Comprueba a la hora de añadir/actualizar los camarotes de un barco si el número de camarotes
 * totales asignados de cada tipo supera el máximo de camarotes del barco.
 */
create or alter trigger tg_max_camarotes on numeroCamarotesBarco after insert, update
as begin
	declare @totalCamarotesBarco as table (idBarco int, numCamarotes int)

	insert into @totalCamarotesBarco (idBarco, numCamarotes)
		select n.idBarco, sum(n.numCamarotes)
			from numeroCamarotesBarco n
			group by idBarco

		if (select count(*)
			from @totalCamarotesBarco t inner join barco b
			on t.idBarco = b.idBarco
			where t.numCamarotes > b.numCamarotes) > 0
	begin
		rollback transaction 
		raiserror('No se puede insertar ese número de camarotes', 16, 1)
	end
end;
go

---Prueba disparador---
select * from barco
update numeroCamarotesBarco
	set numCamarotes = 2000
	where idBarco = 1 and idBarco = 1
go

/*
 * Comprueba que la fecha de embargo de un empleado coincida con la fecha de salida de un barco
 */
create or alter trigger tg_fechaEmbarco_personal on personalBarco after insert, update
as begin
	declare @salidasBarco as table (idBarco int, fechaSalida datetime)
	insert into @salidasBarco (idBarco, fechaSalida)
		select t.idBarco, t.fechaSalida
			from trayecto t
			group by idBarco, t.fechaSalida
		select * from @salidasBarco

		if (select count(*)
				from @salidasBarco s inner join personalBarco p
				on s.idBarco = p.idBarco
				where s.fechaSalida = p.fechaEmbarco) > 0
	begin
		rollback transaction
		raiserror('La fecha de la embarco no se corresponde con el trayecto de ningún barco', 16, 1)
	end
end;
go

---Prueba disparador---
-- debe dar fallo
select * from trayecto
select * from personalBarco
update personalBarco
	set fechaEmbarco = getdate()
	where idBarco = 3 and idEmpleado = 3
go

/*
 * No se permite borrar un empleado de la BD. Delete actualiza su fecha de despido
 * en su lugar.
 */
CREATE OR ALTER TRIGGER tg_despideEmpleado ON empleado INSTEAD OF DELETE
AS
	BEGIN
		DECLARE @idEmpleado_deleted int
		SELECT @idEmpleado_deleted = e.idEmpleado FROM empleado e
			INNER JOIN deleted d 
			ON e.idEmpleado = d.idEmpleado;
		IF (SELECT fechaDespido FROM empleado
			WHERE idEmpleado = @idEmpleado_deleted) IS NOT NULL
			BEGIN
				ROLLBACK TRANSACTION
				RAISERROR('No se permite eliminar empleados. El empleado seleccionado ya ha sido despedido.', 16, 1);
			END
			UPDATE empleado SET fechaDespido = GETDATE()
				WHERE idEmpleado = @idEmpleado_deleted;
END;
GO
---Prueba disparador---
-- debe dar fallo
SELECT * FROM empleado;
DELETE empleado FROM empleado
	WHERE idEmpleado = 6;
GO
SELECT * FROM empleado;