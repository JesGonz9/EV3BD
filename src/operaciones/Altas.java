package operaciones;

import java.sql.*;

import utilidades.*;

public class Altas extends Conexion{
	
	public void alta(String marca, String modelo, String talla, String color, String freno, String material, String susp_del, String susp_tras, int stock, double pvp) {
		
		String query = "INSERT INTO bicicletas VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(PreparedStatement ps = conectarPS(query)) {
			
			ps.setString(1, marca);
			ps.setString(2, modelo);
			ps.setString(3, talla);
			ps.setString(4, color);
			ps.setString(5, freno);
			ps.setString(6, material);
			ps.setBoolean(7, susp_del.toLowerCase().trim().charAt(0) == 's');
			ps.setBoolean(8, susp_tras.toLowerCase().trim().charAt(0) == 's');
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
}
