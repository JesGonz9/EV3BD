package operaciones;

import java.sql.*;
import java.util.Scanner;

import utilidades.*;

public class Altas extends Conexion{
	
	private void altaDatos(String datos[], int stock, double pvp) {
		String query = "INSERT INTO bicicletas VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(PreparedStatement ps = conectarPS(query)) {
			
			ps.setString(1, datos[0]);
			ps.setString(2, datos[1]);
			ps.setString(3, datos[2]);
			ps.setString(4, datos[3]);
			ps.setString(5, datos[4]);
			ps.setString(6, datos[5]);
			ps.setBoolean(7, datos[6].contains("s"));
			ps.setBoolean(8, datos[7].contains("s"));
			ps.setInt(9, stock);
			ps.setDouble(10, pvp);
			
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
		
		String[] datos = new String[8];
		
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
		
		int stock = (int)Validaciones.validaNumero("Stock > ");
				
		double pvp = Validaciones.validaNumero("PVP > ");
		
		altaDatos(datos, stock, pvp);
	
	}
}
