package operaciones;

import java.sql.ResultSet;
import java.sql.SQLException;

import static utilidades.Conexion.conectarCal;

public final class Calculos {
	
	private Calculos() {}
	
	public static void calculoMedia(String marca) {
		
		try(ResultSet rs = conectarCal(marca)){
			
			int cont = 0;
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
			}
			
			System.out.println("Se han encontrado " + cont + " articulos con 0 stock de la marca " + marca);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
