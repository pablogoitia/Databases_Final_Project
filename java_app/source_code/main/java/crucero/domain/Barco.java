package crucero.domain;

import java.sql.Date;

/**
 * 
 * Clase del dominio que representa a los barcos
 * de la flota.
 * 
 * @author Royal Cantabrian
 * @lastmodified 22/05/2022
 *
 */
public class Barco {

	private int idBarco; //el tipo de dato en la bd es int
	private String nombre; //el tipo de dato en la bd es char o varchar
	private int numCamarotes; //el tipo de dato en la bd es int
	private Date fechaAdquisicion; //el tipo de dato en la bd es date
	private Date ultimaRenovacion; //el tipo de dato en la bd es date
	private Date fechaBaja; //el tipo de dato en la bd es date
	
	/**
	 * Constructor de la clase. Recibe el valor de todos los atributos, incluido el id.
	 * @param idBarco
	 * @param nombre
	 * @param numCamarotes
	 * @param fechaAdquisicion
	 * @param ultimaRenovacion
	 * @param fechaBaja
	 */
	public Barco(int idBarco, String nombre, int numCamarotes, Date fechaAdquisicion, 
			Date ultimaRenovacion, Date fechaBaja) {
		
		this.idBarco = idBarco;
		this.nombre = nombre;
		this.numCamarotes = numCamarotes;
		this.fechaAdquisicion = fechaAdquisicion;
		this.ultimaRenovacion = ultimaRenovacion;
		this.fechaBaja = fechaBaja;
		
	}

	/**
	 * Constructor de la clase. Recibe el valor de todos los atributos excepto el id.
	 * @param nombre
	 * @param numCamarotes
	 * @param fechaAdquisicion
	 * @param ultimaRenovacion
	 * @param fechaBaja
	 */
	public Barco(String nombre, int numCamarotes, Date fechaAdquisicion, 
			Date ultimaRenovacion, Date fechaBaja) {
		
		this.nombre = nombre;
		this.numCamarotes = numCamarotes;
		this.fechaAdquisicion = fechaAdquisicion;
		this.ultimaRenovacion = ultimaRenovacion;
		this.fechaBaja = fechaBaja;
		
	}
	
	
	/*
	 * Getters y Setters de los atributos de clase
	 */
	public int getIdBarco() {
		return idBarco;
	}

	public void setIdBarco(int idBarco) {
		this.idBarco = idBarco;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumCamarotes() {
		return numCamarotes;
	}

	public void setNumCamarotes(int numCamarotes) {
		this.numCamarotes = numCamarotes;
	}

	public Date getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public Date getUltimaRenovacion() {
		return ultimaRenovacion;
	}

	public void setUltimaRenovacion(Date ultimaRenovacion) {
		this.ultimaRenovacion = ultimaRenovacion;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	/**
	 * Metodo toString() para devolver informacion del barco.
	 * 
	 */
	@Override
	public String toString() {
		String renovacion = "";
		String baja = "";
		if (ultimaRenovacion != null)
			renovacion = "\nFecha de renovación: " + ultimaRenovacion;
		if (fechaBaja != null)
			baja = "\nDado de baja: " + fechaBaja;
		
		return ("ID " + idBarco + ": " + nombre + 
				"\nNúmero de camarotes: " + numCamarotes) + 
				"\nFecha de adquisición: " + fechaAdquisicion + 
				renovacion + baja;
	}
}
