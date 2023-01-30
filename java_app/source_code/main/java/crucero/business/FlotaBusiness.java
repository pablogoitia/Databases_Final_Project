package crucero.business;

// Importamos el modelo de dominio del barco y el data mapper
import crucero.persistenceLayer.BarcoDataMapper;
import crucero.domain.Barco;

import java.sql.Date;
import java.util.List;

/**
 * Clase con las operaciones de negocio de la Flota.
 * Es con esta clase con la que la interfaz grafica (capa de presentacion)
 * se comunica.

 * @author Royal Cantabrian
 * @lastmodified 22/05/2022
 *
 */
public class FlotaBusiness {
	
	/**
	 * Metodo para devolver una lista de todos los barcos
	 */
	public List<Barco> getAllBarcos() {
		
		// Retorna con el data mapper a todos los barcos
		return (new BarcoDataMapper().selectAllBarcos());
	}
	
	/**
	 * Metodo que inserta un nuevo barco en la base de datos 
	 * @param nombre
	 * @param anhoConstruccion
	 * @param anhoRenovacion
	 * @param numCamarotes
	 */
	public boolean newBarco(String nombre, int numCamarotes, Date fechaAdquisicion) {
		Barco b = new Barco(nombre, numCamarotes, fechaAdquisicion, null, null);
		// Inserta barco con DataMapper
		return new BarcoDataMapper().insertBarco(b);
	}
}