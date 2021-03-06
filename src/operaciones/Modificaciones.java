package operaciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static utilidades.Conexion.conectarSTmod;
import static utilidades.Conexion.conectarPS;
import static utilidades.Validaciones.*;


public final class Modificaciones {
	
	static Scanner teclado = new Scanner(System.in);
	
	private Modificaciones() {}
	
	
	public static void modInd() { //Modificaciones individuales

		System.out.println("Primera letra de la marca");
		String empieza = teclado.nextLine().trim().toLowerCase();
		if(cancelar(empieza)) return;
		
		String query = String.format("SELECT * FROM bicicletas WHERE marca LIKE '%s%c' ORDER BY marca", empieza, '%');
		
		try(ResultSet rs = conectarSTmod(query)){
			
			int cont = 0;
			int mod = 0;
			
			while(rs.next()) {
				
				cont++;
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

				System.out.println("Modificar registro? (s/N)");
				String resp = teclado.nextLine().trim().toLowerCase();
				if(resp.length() > 0) {
					if(resp.charAt(0) == 's') {
						String dato = "";
						mod++;
						
						System.out.println("Marca > ");
						dato = teclado.nextLine().trim();
						if(dato.length() > 0) rs.updateString("marca", dato);
						
						
						System.out.println("\nModelo > ");
						dato = teclado.nextLine().trim();
						if(dato.length() > 0) rs.updateString("modelo", dato);
						
						
						System.out.println("\nTalla > ");
						dato = teclado.nextLine().trim();
						if(dato.length() > 0) rs.updateString("talla", dato);
						
						System.out.println("\nColor > ");
						dato = teclado.nextLine().trim();
						if(dato.length() > 0) rs.updateString("Color", dato);
						
						System.out.println("\nFreno > ");
						dato = teclado.nextLine().trim();
						if(dato.length() > 0) rs.updateString("Freno", dato);
						
						System.out.println("\nMaterial > ");
						dato = teclado.nextLine().trim();
						if(dato.length() > 0) rs.updateString("material", dato);
						
						System.out.println("\nSuspension Delantera (Si/No) > ");
						dato = teclado.nextLine().trim();
						if(dato.length() > 0) rs.updateBoolean("susp_del", dato.charAt(0) == 's'? true:false);
						
						System.out.println("\nSuspension trasera (Si/No) > ");
						dato = teclado.nextLine().trim();
						if(dato.length() > 0) rs.updateBoolean("susp_tras", dato.charAt(0) == 's'? true:false);
						
						System.out.println("!!!!!! > Modificar stock? (s/N)");
						resp = teclado.nextLine().trim().toLowerCase();
						if(resp.length() > 0) {
							if(resp.charAt(0) == 's') {
								rs.updateInt("stock", (int)validaNumero("Stock > " ));
							}
						}
						
						System.out.println("!!!!!! > Modificar PVP? (s/N)");
						resp = teclado.nextLine().trim().toLowerCase();
						if(resp.length() > 0) {
							if(resp.charAt(0) == 's') {
								rs.updateDouble("pvp", validaNumero("PVP > " ));
							}
						}
						
						rs.updateRow();
					}
				}
			}
			
			if(cont > 0) {
				System.out.print("Registros mostrados : " + cont + " / Modificados: " + mod + "\n");
			}else {
				System.out.println("No se han encontrado registros");
			}
			
		} catch (SQLException e) {
			System.out.println("ERROR de conexion");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void modGrp() { //Modificaciones en grupo
		
		String query = "UPDATE bicicletas SET pvp = pvp * ? WHERE stock <= ?";
		
		int stockMin = 0;
		double descuento = 0;
		
		System.out.println("***LIQUIDACION ULTIMAS UNIDADES***\n");
			
		try(PreparedStatement ps = conectarPS(query)) {
			
			//Validar datos de entrada
			while(true) {
		
					try {
						
						System.out.print("Stock minimo para aplicar descuento > ");
						String entrada = teclado.nextLine();
						if(cancelar(entrada)) return;
						stockMin = Integer.parseInt(entrada);
						validaSigno(stockMin, 0);
						
						System.out.print("\nDescuento a aplicar (%) > ");
						descuento = Double.parseDouble(teclado.nextLine());
						validaRango((int)descuento, 1, 100);
						
						break;
						
					} catch (NumberFormatException e) {
						System.out.println("Introduce un valor numerico valido\n");
						
					} catch (Exception e){
						System.out.println(e.getMessage());
					}
			}
			
			//Mostrar los registros que se modificaran
			System.out.println("\nREGISTROS QUE VAN A SER MODIFICADOS\n");
			Listado.consultaListado(String.format("WHERE stock <= %d ORDER BY stock", stockMin));
			
			System.out.println("Modificar precio? (s/N)");
			String resp = teclado.nextLine().trim().toLowerCase();
			
			if(resp.length() > 0) {
				if(resp.charAt(0) == 's') {
					
					ps.setDouble(1, (100 - descuento) / 100);
					ps.setInt(2, stockMin);
					ps.executeUpdate();
					
					//Registros ya modificados
					System.out.println("\nRESULTADO DE LA MODIFICACION\n");
					Listado.consultaListado(String.format("WHERE stock <= %d ORDER BY stock", stockMin));
					
				} else {
					System.out.println("Modificacion cancelada");
				}
				
			} else {
				System.out.println("Modificacion cancelada");
			}
	 
		} catch (SQLException e) {
			System.out.println("ERROR de conexion");
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
