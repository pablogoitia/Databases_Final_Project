package crucero.domain;

/**
 * 
 * Clase del dominio que representa a los puestos de trabajo.
 * 
 * @author Royal Cantabrian
 * @lastmodified 12/05/2022
 *
 */
public class PuestoTrabajo {

	private int idPuesto; //el tipo de dato en la bd es int
	private String nombre; //el tipo de dato en la bd es char o varchar
	private String descripcion; //el tipo de dato en la bd es char o varchar
	
	/**
	 * Constructor de la clase. Recibe el valor de todos los atributos, incluido el id.
	 * @param idPuesto
	 * @param nombre
	 * @param descripcion
	 */
	public PuestoTrabajo(int idPuesto, String nombre, String descripcion) {
		
		this.idPuesto = idPuesto;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	/**
	 * Constructor de la clase. Recibe el valor de todos los atributos excepto el id.
	 * @param nombre
	 * @param descripcion
	 */
	public PuestoTrabajo(String nombre, String descripcion) {
		
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	
	/*
	 * Getters y Setters de los atributos de clase
	 */
	public int getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(int idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	/**
	 * Metodo toString() para devolver informacion del puesto de trabajo.
	 * 
	 */
	@Override
	public String toString() {
		String descStr = "";
		if (descripcion != null)
			descStr += ": " + descripcion;
			
		
		return ("ID: " + idPuesto + " - " + nombre + descStr);
	}
}
