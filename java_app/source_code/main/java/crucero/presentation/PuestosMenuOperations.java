package crucero.presentation;

import java.util.List;

import crucero.business.PuestosBusiness;
import crucero.domain.PuestoTrabajo;
import fundamentos.Lectura;
import fundamentos.Mensaje;

/**
 * Clase que contiene las operaciones a realizar cuando se selecciona una opcion
 * en el menu de puestos de trabajo.
 * @author Royal Cantabrian
 * @lastmodified 11/05/2022
 *
 */
public class PuestosMenuOperations {
	
	/**
	 * Implementa la operacion para retornar todos los puestos
	 */
	public void getAllPuestos() {
		
		// Crea una lista de puestos y llama al metodo de la capa de negocio
		List<PuestoTrabajo> puestos = new PuestosBusiness().getAllPuestos();
		String txt = new String();
		
		// Guarda la info de cada barco en txt
		for(PuestoTrabajo p: puestos) {
			txt = txt + p.toString() + "\n";
		}
		
		// Imprime la info de los puestos almacenada
		Mensaje msg = new Mensaje();
		msg.escribe(txt);
	}

	/**
	 * Implementa la operacion para retornar a todos los puestos de trabajo
	 */
	public void newPuesto() {
		
		// Lectura de datos a insertar
		Lectura lec = new Lectura ("Proporciona los datos del puesto a crear.");
		Mensaje msg = new Mensaje();
		String txt;
		lec.creaEntrada("Nombre", "");
		lec.creaEntrada("Descripción", "");
		lec.esperaYCierra();
		
		String nombre = lec.leeString("Nombre");
		String descripcion = lec.leeString("Descripción");
		if(descripcion.isEmpty()) descripcion = null;
		
		try {
			if (nombre.equals("")) throw new IllegalArgumentException();
			// Llamada al metodo de la capa de negocio para insertar el barco
			boolean success = new PuestosBusiness().newPuesto(nombre, descripcion);
			
			if (success) txt = "Éxito al añadir el puesto de trabajo.";
			else txt="Error al insertar el puesto. Comprueba que los datos son correctos.";
			msg.escribe(txt);
			
		//Controla que los tipos de datos se han introducido correctamente
		} catch(IllegalArgumentException e) {
			msg.escribe("Error al insertar el puesto. Comprueba que los datos están en el formato adecuado.");
			e.printStackTrace();
		}
	}
}
