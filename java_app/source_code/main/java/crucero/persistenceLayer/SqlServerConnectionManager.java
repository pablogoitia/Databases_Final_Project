package crucero.persistenceLayer;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase para obtener la conexion al servidor SQL Server.
 * 
 * @author Pablo Sanchez Barreiro
 * @coauthor Diego Garcia Saiz
 * @lastMofidied 11/05/2022
 */
public class SqlServerConnectionManager {
	
	protected static Connection connection; //atributo estatico que almacena la conexion
	
	/*
	 * Atributos de acceso a la Base de Datos.
	 */
	protected static String dbName = "cruceroBD";
	protected static String user = "adminRoyalCantabrian";
	protected static String pass = "adminRoyalCantabrian";
	protected static String ipPort = "127.0.0.1:1433";
	
	/**
	 * Metodo estatico para obtener la conexion.
	 * Si el atributo "connection" no ha sido inicializado (null)
	 * se inicializa creando una nueva conexion con el servidor
	 * y la base de datos en cuestion
	 * @return
	 */
	public static Connection getConnection() {
		
		if (connection == null) { //Si la conexion no fue inicializada, lo hacemos ahora
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //comprueba que el driver de sql server esta instalado
				connection = DriverManager.getConnection("jdbc:sqlserver://"+ipPort, user, pass); // conexion a la BD
				SqlServerConnectionManager.executeSqlStatement("use "+dbName, "Error al hacer 'use "+dbName+"'"); //ejecutar "use" para usar la base de datos
			} catch (SQLException e) {
				System.out.println("Database connection not available");
				System.out.println("Error Code =" + e.getErrorCode());
				System.out.println("Error State = "+e.getSQLState());
				System.out.println(e);
			} catch (ClassNotFoundException e) {
				System.out.println("SQLServer connector driver not found");
			}
		}
		return connection; //retorna la conexion
	}
	
	/**
	 * Metodo estatico para ejecutar operaciones SQL y manejar los errores.
	 * Recibe como parametros la instruccion (statement) en formato tipo String
	 * y un mensaje de error personalizado para mostrarlo en caso de que la
	 * instruccion no funcione correctamente.
	 * 
	 * IMPORTANTE: este metodo solo puede ser llamado para operaciones de INSERT, UPDATE
	 * y DELETE. No debe usarse para realizar SELECTs ni llamadas a PROCEDIMIENTO, las ejecuciones
	 * de ese tipo de operaciones tendran que implementarse en sus respectivos metodos.
	 * 
	 * @param stringStatement
	 * @param exceptionMsg
	 */
	public static boolean executeSqlStatement(String stringStatement, String exceptionMsg) {
		Connection con = SqlServerConnectionManager.getConnection(); //conectamos con la base de datos
		try {
			System.out.println("Ejecutando: "+stringStatement);
			Statement stm = con.createStatement(); //nuevo statement
			stm.execute(stringStatement); //ejecuta el statement dado como parametro
			stm.close(); //cierra el statement
			
			
		} catch (SQLException e) {
			System.out.println("Error Code =" + e.getErrorCode());
			System.out.println("Error State = "+e.getSQLState());
			System.out.println("Error Messange = "+e.getMessage());
			System.out.println("User personalized error message: '"+exceptionMsg+"'"); //si se produce una excecion del SQL, se muestra el error personalizado del usuario
			e.printStackTrace();
			return false; //Si se llega hasta aqui, algo fallo en la ejecucion. Retornamos false.
		} 
		
		return true; //si se ha llegado hasta aqui es porque la ejecucion ha sido correcta
	}
	
	

}
