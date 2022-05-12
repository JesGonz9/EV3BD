package operaciones;

import java.sql.*;
import java.util.Scanner;

import utilidades.*;

public class Altas extends Conexion{
	
	private void altaDatos(String datos[]) {
		String query = "INSERT INTO bicicletas VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(PreparedStatement ps = conectarPS(query)) {
			
			
			ps.setString(1, datos[0]);
			System.out.println("ok0");
			ps.setString(2, datos[1]);
			System.out.println("ok1");
			ps.setString(3, datos[2]);
			System.out.println("ok2");
			ps.setString(4, datos[3]);
			System.out.println("ok3");
			ps.setString(5, datos[4]);
			System.out.println("ok4");
			ps.setString(6, datos[5]);
			System.out.println("ok5");
			ps.setBoolean(7, datos[6].contains("s"));
			System.out.println("ok6");
			ps.setBoolean(8, datos[7].contains("s"));
			System.out.println("ok7");
			ps.setInt(9, Integer.parseInt(datos[8]));
			System.out.println("ok8");
			ps.setDouble(10, Double.parseDouble(datos[9]));
			System.out.println("ok9");
/*LOG*/		System.out.println(ps);
			ps.executeUpdate();
			System.out.println("Listado actualizado!");
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
	
	public void alta() {
		
		Scanner teclado = new Scanner(System.in);
		
		String[] datos = new String[10];
		
		System.out.println("Introduce los siguiente datos:");
		
		System.out.print("Marca > ");
		datos[0] = teclado.nextLine().trim();

		System.out.print("\nModelo > ");
		datos[1] = teclado.nextLine().trim();
		
		System.out.print("\nTalla > ");
		datos[2] = teclado.nextLine().trim().toUpperCase();
		
		System.out.print("\nColor > ");
		datos[3] = teclado.nextLine().trim();
		
		System.out.print("\nFreno > ");
		datos[4] = teclado.nextLine().trim();
		
		System.out.print("\nMaterial > ");
		datos[5] = teclado.nextLine().trim();
		
		System.out.print("\nSuspension delantera? (s/n) > ");
		datos[6] = teclado.nextLine().trim();
		
		System.out.print("\nSuspension trasera? (s/n) > ");
		datos[7] = teclado.nextLine().trim();
		
		datos[8] = Validaciones.validaNumero("Stock > ");
				
		datos[9] = Validaciones.validaNumero("PVP > ");
		
		altaDatos(datos);
		
		
		
	}
}
