package crucero.business;

// Importamos el modelo de dominio del barco y el data mapper
import crucero.persistenceLayer.PuestoDataMapper;
import crucero.domain.PuestoTrabajo;

import java.util.List;

/**
 * Clase con las operaciones de negocio de los Puestos.
 * Es con esta clase con la que la interfaz grafica (capa de presentacion)
 * se comunica.

 * @author Royal Cantabrian
 * @lastmodified 11/05/2022
 *
 */
public class PuestosBusiness {
	
	/**
	 * Metodo para devolver una lista de todos los puestos
	 */
	public List<PuestoTrabajo> getAllPuestos() {
		
		// Retorna con el data mapper a todos los barcos
		return (new PuestoDataMapper().selectAllPuestos());
	}
	
	/**
	 * Metodo que inserta un nuevo puesto en la base de datos
	 * @param nombre
	 * @param descripcion
	 */
	public boolean newPuesto(String nombre, String descripcion) {
		PuestoTrabajo p = new PuestoTrabajo(nombre, descripcion);
		// Inserta barco con DataMapper
		return new PuestoDataMapper().insertPuesto(p);
	}
}