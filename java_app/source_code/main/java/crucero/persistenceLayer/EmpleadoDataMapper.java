package crucero.persistenceLayer;

import crucero.domain.Empleado; //importamos el dominio del empleado

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


/**
 * Clase para realizar las operaciones de seleccion y manipulacion
 * de datos concernientes a los barcos.
 * 
 * @author Royal Cantabrian
 * @lastmodified 12/05/2022
 *
 */
public class EmpleadoDataMapper {
	
	/**
	 * Metodo para retornar los empleados activos.
	 * @return
	 */
	public List<Empleado> selectEmpleadosActivos() {
		List<Empleado> empleados = null;
		
		Connection con = SqlServerConnectionManager.getConnection(); // nueva conexion BD
		try {
			Statement selectStm = con.createStatement(); // nuevo statement
			String selectStmText = "SELECT * FROM Empleado WHERE fechaDespido is null";
			ResultSet results = selectStm.executeQuery(selectStmText); // ejecucion
			empleados = resultSet2empleados(results); // metodo auxiliar para guardar los barcos retornados en una lista
			selectStm.close();
		} catch (SQLException e) {
			System.out.println("Excepción al obtener el listado de todos los empleados");
			e.printStackTrace();
		}
		return empleados;
	}
	
	/**
	 * Metodo para retornar todos los empleados.
	 * @return
	 */
	public List<Empleado> selectAllEmpleados() {
		List<Empleado> empleados = null;
		
		Connection con = SqlServerConnectionManager.getConnection(); // nueva conexion BD
		try {
			Statement selectStm = con.createStatement(); // nuevo statement
			String selectStmText = "SELECT * FROM Empleado";
			ResultSet results = selectStm.executeQuery(selectStmText); // ejecucion
			empleados = resultSet2empleados(results); // metodo auxiliar para guardar los barcos retornados en una lista
			selectStm.close();
		} catch (SQLException e) {
			System.out.println("Excepción al obtener el listado de todos los empleados");
			e.printStackTrace();
		}
		return empleados;
	}
	
	/**
	 * Metodo auxiliar para extraer de un resultSet todos los Empleados
	 * resultantes de una consulta.
	 * @param results
	 * @return
	 */
	private List<Empleado> resultSet2empleados(ResultSet results) {
		
		List<Empleado> empleados = new LinkedList<Empleado>();
		
		// Variables del metodo para almacenar los datos de cada empleado
		String nombre; String apellido1; String apellido2; Date fechaNacimiento; String NIF;
		String email; String telefono; int idPuesto; Date fechaContratacion; Date fechaDespido;
		int idEmpleado;
		
		try {
			// Recorremos resultset
			while (results.next()) { // Cuando retorne false significa que no hay mas elementos
				idEmpleado = results.getInt("idEmpleado");
				nombre = results.getString("nombre");
				apellido1 = results.getString("apellido1");
				apellido2 = results.getString("apellido2");
				fechaNacimiento = results.getDate("fechaNacimiento");
				NIF = results.getString("NIF");
				email = results.getString("email");
				telefono = results.getString("telefono");
				idPuesto = results.getInt("idPuesto");
				fechaContratacion = results.getDate("fechaContratacion");
				fechaDespido = results.getDate("fechaDespido");
				empleados.add(new Empleado(idEmpleado, nombre, apellido1, apellido2, fechaNacimiento,
						NIF, email, telefono, idPuesto, fechaContratacion, fechaDespido));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return empleados;
	}

	/**
	 * Metodo para insertar un empleado en la base de datos
	 * @param b
	 */
	public boolean insertEmpleado(Empleado e) {

		//Construye la instruccion insert
		String apellido2 = e.getApellido2();
		if (apellido2 == null) apellido2 = "NULL";
		else apellido2 = "'" + apellido2 + "'";
		String insertStmText = "INSERT INTO Empleado(nombre, apellido1, apellido2, "
				+ "fechaNacimiento, NIF, email, telefono, idPuesto, fechaContratacion, fechaDespido) "
				+ "VALUES ('" + e.getNombre() + "', '" + e.getApellido1() + "', " + apellido2
				+ ", '" + e.getFechaNacimiento() + "', '" + e.getNIF() + "', '" + e.getEmail()
				+ "', '" + e.getTelefono() + "', " + e.getIdPuesto() + ", '" + e.getFechaContratacion()
				+ "', NULL)";
		
		// Ejecuta la instruccion con "executeSqlStatement". Retorna false si hay algun error
		return SqlServerConnectionManager.executeSqlStatement(insertStmText, "Excepción al añadir el empleado " 
		+ e.getNombre());
	}
}
