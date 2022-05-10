package operaciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import utilidades.Conexion;

public class Bajas extends Conexion {
	
	Scanner teclado = new Scanner(System.in);	
	
	public void bajasInd() {
		
		System.out.print("Modelo que contenga > ");
		String contiene = teclado.nextLine().trim();
		String query = String.format("SELECT * FROM bicicletas WHERE modelo LIKE '%c%s%c' ORDER BY marca", '%', contiene, '%');
		
		try(ResultSet rs = conectarSTmod(query)) {
			
			int cont = 0;
			int del = 0;
			
			while(rs.next()) {
				
				cont++;
				System.out.printf("%8s %12s %7s %8s %15s %15s %10s %10s %7s %6s \n", "MARCA", "MODELO", "TALLA", "COLOR", "FRENO", "MATERIAL", "SUSP_DEL", "SUSP_TRAS", "STOCK", "PVP");
				System.out.printf(
					"%10s %10s %5s %10s %20s %10s %7s %10s %8d %10.2f \n",
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
			
			System.out.println("Registros eliminados: " + del);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public void bajasGrp() {
		
		String query = "DELETE FROM bicicletas WHERE stock ? ?";
		System.out.println("***ELIMINACION DE ARTICULOS SEGUN STOCK ***\n");
		System.out.print("Con stock superior (+) o inferior (-) a > ");
		
		try(PreparedStatement ps = conectarPS(query)){
			
		}
		
	}
	
	
	
}
