package bdApp;

import java.util.Scanner;

import static operaciones.Altas.*;
import static operaciones.Bajas.*;
import static operaciones.Calculos.*;
import static operaciones.Listado.*;
import static operaciones.Modificaciones.*;
import static utilidades.Validaciones.*;
import utilidades.MiMenu;

public class Principal {

	public static void main(String[] args) {
		
		//Menus 
		MiMenu principal = new MiMenu("SISTEMA DE GESTION DE INVENTARIO", "Listado de productos", "Añadir productos", "Modificar productos", "Eliminar productos", "Productos a cero", "Salir");	
		MiMenu listado = new MiMenu("OPCIONES DE LISTADO", "Listado completo", "Listado Parcial", "Salir");
		MiMenu modif = new MiMenu("OPCIONS DE MODIFICACION", "En grupo", "Individuales", "Salir");
		MiMenu bajas = new MiMenu("OPCIONES DE ELIMINACION", "Individualmente", "Por grupos", "Salir");
		
		//Inicio de la app
		Scanner teclado = new Scanner(System.in);
		
			boolean sw = true;
			
			while (sw) {
				
				principal.ver();
				switch (principal.getOpcion()) {

				case 1:
					
					listado.ver();
					switch (listado.getOpcion()) {

					case 1:
						consultaListado();
						MiMenu.continuar();
						break;
					case 2:
						double lineas = validaNumero("Numero de Registros a mostrar > ");
						consultaListado((int)lineas);
						MiMenu.continuar();
						break;
					case 3: 
						break;
					}
					
				break;

				case 2:
					alta();
					MiMenu.continuar();
					break;

				case 3:
					modif.ver();

					switch (modif.getOpcion()) {

					case 1:
						modGrp();
						MiMenu.continuar();
						break;
					case 2:
						modInd();
						MiMenu.continuar();
						break;
					case 3: 
						break;
					}

					break;

				case 4:
					bajas.ver();
					switch (bajas.getOpcion()) {

					case 1:
						bajasInd();
						MiMenu.continuar();
						break;
					case 2:
						bajasGrp();
						MiMenu.continuar();
						break;
					case 3: 
						break;
					}
					
					break;

				case 5:
					System.out.println("Marca para buscar articulos en stock 0 \n");
					System.out.print("Marca > ");
					String marca = teclado.nextLine().trim();
					stockCero(marca);
					MiMenu.continuar();
					break;

				case 6:
					System.out.println("---------- FIN ----------");
					sw = false;
					break;
				}
			}
			
		
		
		
	}
}
