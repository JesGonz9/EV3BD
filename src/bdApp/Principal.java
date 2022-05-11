package bdApp;

import operaciones.*;
import utilidades.MiMenu;

/*TODO
 * Revisar modificadores de acceso
 * Meter opcion para el callable
 */



public class Principal {

	public static void main(String[] args) {
		
		MiMenu principal = new MiMenu("Cabecera", "uno", "dos", "tres");
		
		principal.ver();
		System.out.println(principal.getOpcion());
		System.out.println(principal.getEntrada());

		
		
		
		
	}
}
