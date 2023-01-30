package crucero.presentation;

import fundamentos.Menu;

/**
 * Submenu "Gestionar los puestos de trabajo".
 * 
 * @author Royal Cantabrian
 * @lastmodified 12/05/2022
 *
 */
public class PuestosMenu {
	
	PuestosMenuOperations pmo = new PuestosMenuOperations();
	
	/**
	 * Metodo run al que se llama desde el exterior para lanzar el menu
	 */
	public void run() {
		
		int option;
		//Creamos un menu e insertamos las opciones
		Menu flotaMenu = new Menu("Administración de puestos de trabajo");
		flotaMenu.insertaOpcion("Listado de puestos de trabajo", 1);
		flotaMenu.insertaOpcion("Nuevo puesto de trabajo", 2);
		flotaMenu.insertaOpcion("Volver", 99);
		
		flotaMenu.println("Menú de administración de puestos de trabajo.");
		
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
			pmo.getAllPuestos(); // obtiene listado de puestos de trabajo
			break;
		case 2:
			pmo.newPuesto(); // anhade puesto de trabajo
			break;
		default:
			break;
		}
	}
}
