package crucero.business;

// Importamos el modelo de dominio del empleado y el data mapper
import crucero.persistenceLayer.EmpleadoDataMapper;
import crucero.domain.Empleado;

import java.sql.Date;
import java.util.List;

/**
 * Clase con las operaciones de negocio del Personal.
 * Es con esta clase con la que la interfaz grafica (capa de presentacion)
 * se comunica.

 * @author Royal Cantabrian
 * @lastmodified 12/05/2022
 *
 */
public class PersonalBusiness {

	/**
	 * Metodo para devolver una lista de todos los empleados
	 */
	public List<Empleado> getEmpleadosActivos() {
		
		// Retorna con el data mapper a los empleados activos
		return (new EmpleadoDataMapper().selectEmpleadosActivos());
	}

	/**
	 * Metodo para devolver una lista de todos los empleados
	 */
	public List<Empleado> getAllEmpleados() {
		
		// Retorna con el data mapper a todos los empleados
		return (new EmpleadoDataMapper().selectAllEmpleados());
	}
	
	/**
	 * Metodo que inserta un nuevo empleado en la base de datos
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 * @param fechaNacimiento
	 * @param NIF
	 * @param email
	 * @param telefono
	 * @param idPuesto
	 * @param fechaContratacion
	 */
	public boolean newEmpleado(String nombre, String  apellido1, String apellido2, 
			Date fechaNacimiento, String NIF, String email, String telefono, int idPuesto,
			Date fechaContratacion) {
		Empleado e = new Empleado(nombre, apellido1, apellido2, fechaNacimiento, NIF, 
				email, telefono, idPuesto, fechaContratacion, null);
		// Inserta empleado con DataMapper
		return new EmpleadoDataMapper().insertEmpleado(e);
	}
}