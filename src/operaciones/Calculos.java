package operaciones;

import java.sql.SQLException;

import static utilidades.Validaciones.*;

import static utilidades.Conexion.conectarCal;
import static operaciones.Listado.*;

public final class Calculos {
	
	private Calculos() {}
	
	public static void stockCero(String marca) {
		if(cancelar(marca)) return;

		try {
			
			consultaListado(conectarCal(marca));
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
