package utilidades;

import java.sql.*;

public final class Conexion {
	
	private static String url = "jdbc:mysql://localhost:3306/dam1" +
			"?useUnicode=true&characterEncoding=UTF-8" +
			"&autoReconnect=true&useSSL=false";
	private static String user = "root";
	private static String password = "";
	
	private Conexion() {};
	
	//Statement
	public static ResultSet conectarST(String query) throws SQLException {
		
		Connection cn = conexion();
		Statement st = cn.createStatement();
		return st.executeQuery(query);
	}
	
	//Statement con ResultSet modificado
	public static ResultSet conectarSTmod(String query) throws SQLException {
		
		Connection cn = conexion();
		Statement st = cn.createStatement(
						ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
		return st.executeQuery(query);
	}
	
	//PreparedStatement
	public static PreparedStatement conectarPS(String query) throws SQLException {
		
		Connection cn = conexion();
		return cn.prepareStatement(query);
	}
	
	//Callable Statement
	public static ResultSet conectarCal(String marca) throws SQLException {
		
		Connection cn = conexion();
		CallableStatement cst = cn.prepareCall("{call stockCero(?)}");
		cst.setString(1, marca);
		
		return cst.executeQuery();
	}
	
	//Conexion
	private static Connection conexion() throws SQLException {
		
		return DriverManager.getConnection(url, user, password);
	}	
}
