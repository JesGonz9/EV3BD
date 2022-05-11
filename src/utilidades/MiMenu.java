package utilidades;

import java.util.*;

public class MiMenu {
	
	//Variables 
	private ArrayList <String> menu = new ArrayList<String>(); //objetos entrada
	private int opcion = 0; //Numero a partir del 1 asociado a cada entrada
	private String encabezado = "";
	private Scanner teclado = new Scanner(System.in);
	
	//Crea un menu con el encabezado y las opciones que le pasemos
	public MiMenu(String encabezado, String ... opciones) {
		
		this.encabezado = encabezado;
		
		for (int i = 0; i < opciones.length; i++) {
			add(opciones[i]);
		}
	}
	
	public int getOpcion() {
		return opcion;
	}

	public String getEntrada() {
		if (menu.isEmpty()==false)
			return menu.get(opcion-1);
		else
			return "N/A";
	}
	
	private void add(String entrada) {
		menu.add(entrada);
	}
	
	private int leerOpcion() {
		
		boolean sw = true;
		do {
			try {
				System.out.print("Opcion? (1-"+menu.size()+")? ");
				opcion = Integer.parseInt(teclado.nextLine());
				if (!(opcion>=1 && opcion<=menu.size()))
					throw new Exception("Introduzca un valor entre 1 y "+menu.size());
				sw = false;
			} catch (NumberFormatException e) {
				System.out.println("Error: introduzca un valor numerico");
			} catch (Exception e) {
				System.out.println("Error: "+e.getMessage());
			}
		} while (sw);
		System.out.println();
		return opcion;
	}
	
	public int ver() {
		this.opcion = 0;
		if (menu.size()==0) return this.opcion;
		
		// Encabezado 
		System.out.println("\n"+encabezado);
		for(int i=1;i<=encabezado.length();i++)
			System.out.print("=");
		
		// Men� de opciones
		for(int i=0;i<menu.size();i++)
		{
			System.out.printf("\n%3d. %s",i+1,menu.get(i));
		}
		System.out.println();
		return leerOpcion();
	}

	public boolean salirMenu() {
		return this.opcion == menu.size();
	}

	@Override
	public String toString() {
		return "Clase MiMenu (2018)";
	}	
}
