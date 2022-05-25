package operaciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static utilidades.Conexion.conectarSTmod;
import static utilidades.Conexion.conectarPS;
import static utilidades.Validaciones.*;

public final class Bajas {
	
	static Scanner teclado = new Scanner(System.in);
	
	private Bajas() {};
	
	public static void bajasInd() {
		
		System.out.print("Modelo que contenga > ");
		String contiene = teclado.nextLine().trim();
		if(cancelar(contiene)) return;
		
		String query = String.format("SELECT * FROM bicicletas WHERE modelo LIKE '%c%s%c' ORDER BY marca", '%', contiene, '%');
		
		try(ResultSet rs = conectarSTmod(query)) {
			
			int del = 0;
			int cont = 0;
			boolean swDatos = false;
			
			while(rs.next()) {
				
				swDatos = true;
				cont ++;
				
				System.out.printf("%8s %12s %7s %8s %15s %15s %10s %10s %7s %6s \n", "MARCA", "MODELO", "TALLA", "COLOR", "FRENO", "MATERIAL", "SUSP_DEL", "SUSP_TRAS", "STOCK", "PVP");
				System.out.printf(
					"%10s %10s %5s %10s %20s %10s %7s %10s %8d %,10.2f€ \n",
						rs.getString("marca"),
						rs.getString("modelo"),
						rs.getString("talla"),
						rs.getString("color"),
						rs.getString("freno"),
						rs.getString("material"),
						rs.getBoolean("susp_del")? "SI":"NO",
						rs.getBoolean("susp_tras")? "SI":"NO",
						rs.getInt("stock"),
						rs.getDouble("pvp")
						);
				
				System.out.println("Eliminar este resgistro? (s/N)");
				String resp = teclado.nextLine().trim().toLowerCase();
				if(resp.length() > 0) {
					if(resp.charAt(0) == 's') {
						rs.deleteRow();
						del++;
					}	
				}
			}
			
			if(swDatos) {
				System.out.print("Registros mostrados : " + cont + " / Eliminados: " + del + "\n");
			} else {
				System.out.println("No se han encontrado registros");
			}
			
		} catch (SQLException e) {
			System.out.println("ERROR de conexion");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	
	public static void bajasGrp() {
		
		String query = "DELETE FROM bicicletas WHERE marca = ?";
		
		System.out.println("***ELIMINACION DE ARTICULOS SEGUN MARCA ***\n");
		
		try(PreparedStatement ps = conectarPS(query)){
			
				System.out.print("Marca a eliminar > ");
				String eliminar = teclado.nextLine().trim();
				if(cancelar(eliminar)) return;
				
				ps.setString(1, eliminar);
				
				System.out.println("\nArticulos que se eliminaran:");
				int log = Listado.consultaListado(String.format("WHERE marca = '%s'", eliminar));
				
				if(log == 0) {
					System.out.println("Continuar? (s/N)");
					String resp = teclado.nextLine().trim().toLowerCase();
					if(resp.length() > 0) {
						if(resp.charAt(0) == 's') {
						
							int cont = ps.executeUpdate();
							System.out.println("Registros eliminados: " + cont);
						
						} else {
							System.out.println("Operacion cancelada");
						}
					
					} else {
						System.out.println("Operacion cancelada");
					}
				}
			
		} catch (SQLException e) {
			System.out.println("ERROR de conexion");
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
}
