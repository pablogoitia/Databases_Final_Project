package crucero.presentation;

import fundamentos.Menu;

/**
 * Submenu "Gestionar la plantilla".
 * 
 * @author Royal Cantabrian
 * @lastmodified 12/05/2022
 *
 */
public class PersonalMenu {
	
	PersonalMenuOperations pmo = new PersonalMenuOperations();
	
	/**
	 * Metodo run al que se llama desde el exterior para lanzar el menu
	 */
	public void run() {
		
		int option;
		//Creamos un menu e insertamos las opciones
		Menu flotaMenu = new Menu("Menú de gestión de la plantilla");
		flotaMenu.insertaOpcion("Listado de empleados activos", 1);
		flotaMenu.insertaOpcion("Histórico de empleados", 2);
		flotaMenu.insertaOpcion("Alta empleado", 3);
		flotaMenu.insertaOpcion("Volver", 99);
		
		flotaMenu.println("Menú de administración de los empleados de la plantilla.");
		
		//Mientras no se seleccione la opcion de salir del menu, se continua en el menu
		do {
			option = flotaMenu.leeOpcion(); //se lee la opcion seleccionada del menu
			this.optionAction(option); //llamamos al método para ejecutar la opcion seleccionada
		} while(option != 99);
		
		//Al salir del bucle se cierra el menu
		flotaMenu.cierra();
		
	}
	
	/**
	 * Metodo que maneja con un switch la opcion seleccionada en el menu
	 * @param option
	 */
	private void optionAction(int option) {
		
		switch(option) {
		case 1:
			pmo.getEmpleadosActivos(); // obtiene listado de empleados activos
			break;
		case 2:
			pmo.getAllEmpleados(); // obtiene listado de empleados en la plantilla
			break;
		case 3:
			pmo.newEmpleado(); // anhade empleado a la plantilla
			break;
		default:
			break;
		}
	}
}
