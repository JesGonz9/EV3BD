package bdApp;

import java.util.Scanner;

import operaciones.*;
import utilidades.MiMenu;

/*TODO
 * Revisar modificadores de acceso
 * Meter opcion para el callable
 * Opciones de salir en los menus
 * Opciones de seguir haciendo gestiones
 * Cambiar clases a estaticas para no tener que crear objetos
 * Parametrizar pruebas con JUnit
 */



public class Principal {

	public static void main(String[] args) {
		
		//Menus 
		MiMenu principal = new MiMenu("SISTEMA DE GESTION DE INVENTARIO", "Listado de productos", "Añadir productos", "Eliminar productos");	
		MiMenu listado = new MiMenu("OPCIONES DE LISTADO", "Listado completo", "Listado Parcial");
		MiMenu bajas = new MiMenu("OPCIONES DE ELIMINACION", "Individualmente", "Por grupos");
		
		//Objetos que necesitaremos
		Scanner teclado = new Scanner(System.in);
		Listado ls = new Listado();
		Altas al = new Altas();
		Modificaciones md = new Modificaciones();
		Bajas bj = new Bajas();
		
		//Inicio de la app
		principal.ver();
		
		switch (principal.getOpcion()) {
		
		case 1:
			listado.ver();
			if(listado.getOpcion() == 1) {
				ls.consultaListado();
				
			} else {
				System.out.println("Numero de Registros a mostrar > ");
				ls.consultaListado(Math.abs(Integer.parseInt(teclado.nextLine())));
			}
			break;
		
		case 2:
			al.alta();

		default:
			break;
		}
		
		
		
	}
}
