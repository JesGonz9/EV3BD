package bdApp;

import java.util.Scanner;

import static operaciones.Altas.*;
import static operaciones.Bajas.*;
import static operaciones.Calculos.*;
import static operaciones.Listado.*;
import static operaciones.Modificaciones.*;
import utilidades.MiMenu;

/*TODO
 * Opciones de salir en los menus
 * Opciones de seguir haciendo gestiones
 * Parametrizar pruebas con JUnit
 */



public class Principal {

	public static void main(String[] args) {
		
		//Menus 
		MiMenu principal = new MiMenu("SISTEMA DE GESTION DE INVENTARIO", "Listado de productos", "Añadir productos", "Modificar productos", "Eliminar productos", "Calculo de medias", "Salir");	
		MiMenu listado = new MiMenu("OPCIONES DE LISTADO", "Listado completo", "Listado Parcial");
		MiMenu modif = new MiMenu("OPCIONS DE MODIFICACION", "En grupo", "Individuales");
		MiMenu bajas = new MiMenu("OPCIONES DE ELIMINACION", "Individualmente", "Por grupos");
		
		//Inicio de la app
		try (Scanner teclado = new Scanner(System.in)) {
			
			principal.ver();
			
			switch(principal.getOpcion()) {
			
			case 1:
				listado.ver();
				if(listado.getOpcion() == 1) {
					consultaListado();
					
				} else {
					System.out.println("Numero de Registros a mostrar > ");
					consultaListado(Math.abs(Integer.parseInt(teclado.nextLine())));
				}
				break;
			
			case 2: alta(); break;
				
			case 3: 
				modif.ver();
				
				switch(modif.getOpcion()) {
				
				case 1: modGrp(); break;
				case 2: modInd(); break;
				}

				break;
			
			case 4: 
				bajas.ver();
				switch(bajas.getOpcion()) {
				
				case 1: bajasInd(); break;
				case 2: bajasGrp(); break;
				}
				
				break;
			
			case 5:
				System.out.print("Marca > ");
				String marca = teclado.nextLine().trim();
				calculoMedia(marca);
				
				break;
			
			case 6:
				principal.salirMenu();
			
			}
		} catch (NumberFormatException e) {

			System.out.println(e.getMessage());
		}
		
		
		
	}
}
