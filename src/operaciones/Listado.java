package operaciones;

import java.sql.*;

import utilidades.Conexion;


public class Listado extends Conexion {
	
		private String query = "SELECT * FROM bicicletas";
		private int filas = -1;
		
		public void consultaListado(int filas) { //Controla filas
			this.filas = filas;
		}
	
		public String consultaListado(String condicion) { //Añade un filtro al listado base
			
			String temp = query;
			query += " " + condicion;
			consultaListado();
			query = temp; //Reestablece la query una vez usada con la condicion
			
			return query;
		}
			
		public void consultaListado() { //Consulta base sin filtro

			try {
				consultaListado(conectarST(query));
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		public void consultaListado(ResultSet rs) { //Maneja los resultsets

			try(rs) {
				
					//Cabecera
					System.out.printf("%8s %12s %7s %8s %15s %15s %10s %10s %7s %6s \n", "MARCA", "MODELO", "TALLA", "COLOR", "FRENO", "MATERIAL", "SUSP_DEL", "SUSP_TRAS", "STOCK", "PVP");
					
					int cont = 0;
					
					while(rs.next()) {
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
						cont++;
						
						if(cont == filas) break; //Controlar las filas que se imprimen
					}
					
					System.out.println("\n Hay " + cont + " bicicletas en lista");
					
				} catch (SQLException e) {
					
					System.out.println("ERROR de conexion");
					
				} catch (Exception e) {
					
					System.out.println(e.getMessage());
				}
		}

}
