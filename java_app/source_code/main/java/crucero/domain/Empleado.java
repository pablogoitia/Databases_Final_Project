package crucero.domain;

import java.sql.Date;

/**
 * 
 * Clase del dominio que representa a los empleados de la plantilla.
 * 
 * @author Royal Cantabrian
 * @lastmodified 12/05/2022
 *
 */
public class Empleado {

	private int idEmpleado; //el tipo de dato en la bd es int
	private String nombre; //el tipo de dato en la bd es char o varchar
	private String apellido1; //el tipo de dato en la bd es char o varchar
	private String apellido2; //el tipo de dato en la bd es char o varchar
	private Date fechaNacimiento; //el tipo de dato en la bd es date
	private String NIF; //el tipo de dato en la bd es char
	private String email; //el tipo de dato en la bd es varchar
	private String telefono; //el tipo de dato en la bd es char
	private int idPuesto; //el tipo de dato en la bd es int
	private Date fechaContratacion; //el tipo de dato en la bd es date
	private Date fechaDespido; //el tipo de dato en la bd es date
	
	/**
	 * Constructor de la clase. Recibe el valor de todos los atributos, incluido el id.
	 * @param idEmpleado
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 * @param fechaNacimiento
	 * @param NIF
	 * @param email
	 * @param telefono
	 * @param idPuesto
	 * @param fechaContratacion
	 * @param fechaDespido
	 */
	public Empleado(int idEmpleado, String nombre, String apellido1, String apellido2,
			Date fechaNacimiento, String NIF, String email, String telefono, int idPuesto,
			Date fechaContratacion,	Date fechaDespido) {
		
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaNacimiento = fechaNacimiento;
		this.NIF = NIF;
		this.email = email;
		this.telefono = telefono;
		this.idPuesto = idPuesto;
		this.fechaContratacion = fechaContratacion;
		this.fechaDespido = fechaDespido;
	}

	/**
	 * Constructor de la clase. Recibe el valor de todos los atributos excepto el id.
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 * @param fechaNacimiento
	 * @param NIF
	 * @param email
	 * @param telefono
	 * @param idPuesto
	 * @param fechaContratacion
	 * @param fechaDespido
	 */
	public Empleado(String nombre, String apellido1, String apellido2,
			Date fechaNacimiento, String NIF, String email, String telefono, int idPuesto,
			Date fechaContratacion,	Date fechaDespido) {
		
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaNacimiento = fechaNacimiento;
		this.NIF = NIF;
		this.email = email;
		this.telefono = telefono;
		this.idPuesto = idPuesto;
		this.fechaContratacion = fechaContratacion;
		this.fechaDespido = fechaDespido;
	}
	
	
	/*
	 * Getters y Setters de los atributos de la clase
	 */
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNIF() {
		return NIF;
	}
	public void setNIF(String NIF) {
		this.NIF = NIF;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getIdPuesto() {
		return idPuesto;
	}
	public void setIdPuesto(int idPuesto) {
		this.idPuesto = idPuesto;
	}

	public Date getFechaContratacion() {
		return fechaContratacion;
	}
	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	public Date getFechaDespido() {
		return fechaDespido;
	}
	public void setFechaDespido(Date fechaDespido) {
		this.fechaDespido = fechaDespido;
	}
	
	/**
	 * Metodo toString() para devolver informacion del empleado.
	 * 
	 */
	@Override
	public String toString() {
		String segApellido = apellido2;
		String despido = "\nDespedido en " + fechaDespido;
		if (apellido2 == null) segApellido = "";
		if (fechaDespido == null) despido = "";
		
		return ("ID " + idEmpleado + ": " + nombre +" " + apellido1 + " " + segApellido + " - NIF: " + NIF + "\n" +
				"Fecha de nacimiento: " + fechaNacimiento + "\n" +
				"Contacto: Email: " + email + ", Tlf: " + telefono + "\n" +
				"Fecha de contratación: " + fechaContratacion + "\nID Puesto: " + idPuesto + despido + "\n");
	}

}
