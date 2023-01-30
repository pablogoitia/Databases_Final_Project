package crucero.presentation;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import crucero.business.PersonalBusiness;
import crucero.domain.Empleado;
import fundamentos.Lectura;
import fundamentos.Mensaje;

/**
 * Clase que contiene las operaciones a realizar cuando se selecciona una opcion
 * en el menu de personal.
 * @author Royal Cantabrian
 * @lastmodified 12/05/2022
 *
 */
public class PersonalMenuOperations {
	
	/**
	 * Implementa la operacion para retornar a todos los empleados
	 */
	public void getEmpleadosActivos() {
		
		// Crea una lista de empleados y llama al metodo de la capa de negocio
		List<Empleado> empleados = new PersonalBusiness().getEmpleadosActivos();
		String txt = new String();
		
		// Guarda la info de cada empleado activo en txt
		for(Empleado e: empleados) {
			txt = txt + e.toString() + "\n";
		}
		
		// Imprime la info de los empleados
		Mensaje msg = new Mensaje();
		msg.escribe(txt);
	}
	
	/**
	 * Implementa la operacion para retornar a todos los empleados
	 */
	public void getAllEmpleados() {
		
		// Crea una lista de empleados y llama al metodo de la capa de negocio
		List<Empleado> empleados = new PersonalBusiness().getAllEmpleados();
		String txt = new String();
		
		// Guarda la info de cada empleado en txt
		for(Empleado e: empleados) {
			txt = txt + e.toString() + "\n";
		}
		
		// Imprime la info de los empleados
		Mensaje msg = new Mensaje();
		msg.escribe(txt);
	}

	/**
	 * Implementa la operacion para retornar a todos los empleados
	 */
	public void newEmpleado() {
		
		// Lectura de datos a insertar
		Lectura lec = new Lectura ("Proporciona los datos del empleado contratado.");
		Mensaje msg = new Mensaje();
		String txt;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();
		lec.creaEntrada("Nombre", "Nombre");
		lec.creaEntrada("Primer apellido", "Primer");
		lec.creaEntrada("Segundo apellido", "Segundo");
		lec.creaEntrada("Fecha de nacimiento", "1990-12-01");
		lec.creaEntrada("NIF", "01234567J");
		lec.creaEntrada("Email", "example@rcantabrian.com");
		lec.creaEntrada("Teléfono", "012345678");
		lec.creaEntrada("ID del Puesto", "1");
		lec.creaEntrada("Fecha de contratación", dtf.format(now));
		lec.esperaYCierra();

		String nombre = lec.leeString("Nombre");
		String apellido1 = lec.leeString("Primer apellido");
		String apellido2 = lec.leeString("Segundo apellido");
		if(apellido2.isEmpty()) apellido2 = null;
		
		try {
			if (nombre.equals("") || apellido1.equals("")) throw new IllegalArgumentException();
			// Llamada al metodo de la capa de negocio para insertar el empleado
			boolean success = new PersonalBusiness().newEmpleado(nombre, apellido1, apellido2,
					Date.valueOf(lec.leeString("Fecha de nacimiento")), lec.leeString("NIF"),
					lec.leeString("Email"), lec.leeString("Teléfono"), lec.leeInt("ID del Puesto"),
					Date.valueOf(lec.leeString("Fecha de contratación")));
			
			if (success) txt = "Éxito al añadir el empleado.";
			else txt="Error al insertar el empleado. Comprueba que los datos son correctos.";
			msg.escribe(txt);
			
		//Controla que los tipos de datos se han introducido correctamente
		} catch(IllegalArgumentException e) {
			msg.escribe("Error al insertar el empleado. Comprueba que los datos están en el formato adecuado.");
			e.printStackTrace();
		}
	}
}
