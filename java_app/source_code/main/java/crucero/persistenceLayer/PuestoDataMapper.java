package crucero.persistenceLayer;

import crucero.domain.PuestoTrabajo; //importamos el dominio del puesto de trabajo

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


/**
 * Clase para realizar las operaciones de seleccion y manipulacion
 * de datos concernientes a los puestos de trabajo.
 * 
 * @author Royal Cantabrian
 * @lastmodified 11/05/2022
 *
 */
public class PuestoDataMapper {
	
	/**
	 * Metodo para retornar todos los puestos.
	 * @return
	 */
	public List<PuestoTrabajo> selectAllPuestos() {
		List<PuestoTrabajo> puestos = null;
		
		Connection con = SqlServerConnectionManager.getConnection(); // nueva conexion BD
		try {
			Statement selectStm = con.createStatement(); // nuevo statement
			String selectStmText = "SELECT * FROM puestoTrabajo";
			ResultSet results = selectStm.executeQuery(selectStmText); // ejecucion
			puestos = resultSet2puestos(results);
			selectStm.close();
		} catch (SQLException e) {
			System.out.println("Excepción al obtener el listado de todos los puestos");
			e.printStackTrace();
		}
		return puestos;
	}
	
	/**
	 * Metodo auxiliar para extraer de un resultSet todos los Puestos
	 * resultantes de una consulta.
	 * @param results
	 * @return
	 */
	private List<PuestoTrabajo> resultSet2puestos(ResultSet results) {
		
		List<PuestoTrabajo> puestos = new LinkedList<PuestoTrabajo>();
		
		// Variables del metodo para almacenar los datos de cada elemento
		int idPuesto; String nombre; String descripcion;
		
		try {
			// Recorremos resultset
			while (results.next()) { // Cuando retorne false significa que no hay mas elementos
				idPuesto = results.getInt("idPuesto");
				nombre = results.getString("nombre");
				descripcion = results.getString("descripcion");
				
				puestos.add(new PuestoTrabajo(idPuesto, nombre, descripcion));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return puestos;
	}
	

	/**
	 * Metodo para insertar un puesto de trabajo en la base de datos
	 * @param b
	 */
	public boolean insertPuesto(PuestoTrabajo pt) {

		//Construye la instruccion insert
		String descripcion = pt.getDescripcion();
		if (descripcion == null) descripcion = "NULL";
		else descripcion = "'" + descripcion + "'";
		String insertStmText = "INSERT INTO puestoTrabajo(nombre, descripcion) "
				+ "VALUES ('" + pt.getNombre() + "', " + descripcion + ")";
		
		// Ejecuta la instruccion con "executeSqlStatement". Retorna false si hay algun error
		return SqlServerConnectionManager.executeSqlStatement(insertStmText, "Excepción al añadir el puesto " + pt.getNombre());
	}
}
