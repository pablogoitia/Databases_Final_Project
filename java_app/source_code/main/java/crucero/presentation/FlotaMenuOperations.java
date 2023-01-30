package crucero.presentation;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import crucero.business.FlotaBusiness;
import crucero.domain.Barco;
import fundamentos.Lectura;
import fundamentos.Mensaje;

/**
 * Clase que contiene las operaciones a realizar cuando se selecciona una opcion
 * en el menu de flota.
 * @author Royal Cantabrian
 * @lastmodified 22/05/2022
 *
 */
public class FlotaMenuOperations {
	
	/**
	 * Implementa la operacion para retornar a todos los barcos de la flota
	 */
	public void getAllBarcos() {
		
		// Crea una lista de barcos y llama al metodo de la capa de negocio
		List<Barco> barcos = new FlotaBusiness().getAllBarcos();
		String txt = new String();
		
		// Guarda la info de cada barco en txt
		for(Barco b: barcos) {
			txt = txt + b.toString() + "\n\n";
		}
		
		// Imprime la info de los barcos almacenada
		Mensaje msg = new Mensaje();
		msg.escribe(txt);
	}

	/**
	 * Implementa la operacion para retornar todos los barcos
	 */
	public void newBarco() {
		
		// Lectura de datos a insertar
		Lectura lec = new Lectura ("Proporciona los datos del barco a insertar.");
		Mensaje msg = new Mensaje();
		String txt;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		lec.creaEntrada("Nombre", "Something of the Seas");
		lec.creaEntrada("Número de camarotes", "1000");
		lec.creaEntrada("Fecha de adquisición", dtf.format(now));
		lec.esperaYCierra();
		
		try {
			// Llamada al metodo de la capa de negocio para insertar el barco
			String nombre = lec.leeString("Nombre");
			if (nombre.equals("")) throw new IllegalArgumentException();
			boolean success = new FlotaBusiness().newBarco(nombre, lec.leeInt("Número de camarotes"),
					Date.valueOf(lec.leeString("Fecha de adquisición")));
			
			if (success) txt = "Éxito al añadir el barco a la flota.";
			else txt="Error al insertar el barco. Comprueba que los datos son correctos.";
			msg.escribe(txt);
			
		//Controla que los tipos de datos se han introducido correctamente
		} catch(IllegalArgumentException e) {
			msg.escribe("Error al insertar el barco. Comprueba que los datos están en el formato adecuado.");
			e.printStackTrace();
		}
	}
}
