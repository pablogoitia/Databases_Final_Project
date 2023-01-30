package crucero.presentation;

import fundamentos.Menu;

/**
 * Submenu "Gestionar la flota".
 * 
 * @author Royal Cantabrian
 * @lastmodified 22/05/2022
 *
 */
public class FlotaMenu {
	
	FlotaMenuOperations fmo = new FlotaMenuOperations();
	
	/**
	 * Metodo run al que se llama desde el exterior para lanzar el menu
	 */
	public void run() {
		
		int option;
		//Creamos un menu e insertamos las opciones
		Menu flotaMenu = new Menu("Menú de gestion de la flota");
		flotaMenu.insertaOpcion("Listado de barcos", 1);
		flotaMenu.insertaOpcion("Añadir barco a la flota", 2);
		flotaMenu.insertaOpcion("Volver", 99);
		
		flotaMenu.println("Menú de administración de los barcos de la flota.");
		
		//Mientras no se seleccione la opcion de salir del menu, se continua en el menu
		do {
			option = flotaMenu.leeOpcion(); //se lee la opcion seleccionada del menu
			this.optionAction(option); //llamamos al metodo para ejecutar la opcion seleccionada
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
			fmo.getAllBarcos(); // obtiene listado de barcos en la flota
			break;
		case 2:
			fmo.newBarco(); // anhade barco a la flota
			break;
		default:
			break;
		}
	}
}
