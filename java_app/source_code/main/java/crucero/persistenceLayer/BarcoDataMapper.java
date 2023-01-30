package crucero.persistenceLayer;

import crucero.domain.Barco; //importamos el dominio del barco

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
 * @lastmodified 22/05/2022
 *
 */
public class BarcoDataMapper {
	
	/**
	 * Metodo para retornar todos los barcos.
	 * @return
	 */
	public List<Barco> selectAllBarcos() {
		List<Barco> barcos = null;
		
		Connection con = SqlServerConnectionManager.getConnection(); // nueva conexion BD
		try {
			Statement selectStm = con.createStatement(); // nuevo statement
			String selectStmText = "SELECT * FROM Barco";
			ResultSet results = selectStm.executeQuery(selectStmText); // ejecucion
			barcos = resultSet2barcos(results); // metodo auxiliar para guardar los barcos retornados en una lista
			selectStm.close();
		} catch (SQLException e) {
			System.out.println("Excepción al obtener el listado de todos los barcos");
			e.printStackTrace();
		}
		return barcos;
	}
	
	/**
	 * Metodo auxiliar para extraer de un resultSet todos los Barcos
	 * resultantes de una consulta.
	 * @param results
	 * @return
	 */
	private List<Barco> resultSet2barcos(ResultSet results) {
		
		List<Barco> barcos = new LinkedList<Barco>();
		
		// Variables del metodo para almacenar los datos de cada barco
		int idBarco; String nombre; int numCamarotes; Date fechaAdquisicion;
		Date ultimaRenovacion; Date fechaBaja;
		
		try {
			// Recorremos resultset
			while (results.next()) { // Cuando retorne false significa que no hay mas elementos
				idBarco = results.getInt("idBarco");
				nombre = results.getString("nombre");
				numCamarotes = results.getInt("numCamarotes");
				fechaAdquisicion = results.getDate("fechaAdquisicion");
				ultimaRenovacion = results.getDate("ultimaRenovacion");
				fechaBaja = results.getDate("fechaBaja");
				
				barcos.add(new Barco(idBarco, nombre, numCamarotes, fechaAdquisicion, 
						ultimaRenovacion, fechaBaja));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return barcos;
	}
	

	/**
	 * Metodo para insertar un barco en la base de datos
	 * @param b
	 */
	public boolean insertBarco(Barco b) {

		// Construye la instruccion insert
		String insertStmText = "INSERT INTO Barco(nombre, numCamarotes, fechaAdquisicion) "
				+ "VALUES ('" + b.getNombre() + "', " + b.getNumCamarotes() + ", '" 
				+ b.getFechaAdquisicion() + "')";
		
		// Ejecuta la instruccion con "executeSqlStatement". Retorna false si hay algun error
		return SqlServerConnectionManager.executeSqlStatement(insertStmText, "Excepción al añadir el barco " + b.getNombre());
	}
}
