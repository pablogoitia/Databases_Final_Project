package crucero.presentation;

import fundamentos.*;

/**
 * Menu principal del aplicativo.
 * 
 * @author Royal Cantabrian
 * @lastmodified 11/05/2022
 *
 */
public class MainMenu {

	/**
	 * Metodo run al que se llama desde el exterior para lanzar el menu
	 */
	public void run() {
		
		int option;
		
		// Menu para manejar las acciones
		Menu mainMenu = new Menu("Menú principal");
		mainMenu.println("Bienvenido al sistema de gestión de Royal Cantabrian.");
		mainMenu.insertaOpcion("Gestionar la flota", 1);
		mainMenu.insertaOpcion("Gestión de personal", 2);
		mainMenu.insertaOpcion("Administración de puestos de trabajo", 3);
		mainMenu.insertaOpcion("Salir de la aplicación", 99);
		
		// No se cierra la aplicacion hasta que se selecciona "salir"
		do {
			option = mainMenu.leeOpcion();
			this.optionAction(option);
		} while(option!=99);
		
		// Finaliza la aplicacion
		mainMenu.cierra();
	}
	
	/**
	 * Metodo que maneja con un switch la opcion seleccionada en el menu
	 * @param option
	 */
	private void optionAction(int option) {
		switch(option) {
			case 1:
				new FlotaMenu().run(); //gestion flota
				break;
			case 2:
				new PersonalMenu().run(); //gestion personal
				break;
			case 3:
				new PuestosMenu().run(); //gestion puestos de trabajo
				break;
			default:
				break;
		}
	}
}
