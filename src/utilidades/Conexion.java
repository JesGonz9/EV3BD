package utilidades;

import java.sql.*;

public class Conexion {
	
	private String url = "jdbc:mysql://localhost:3306/dam1" +
			"?useUnicode=true&characterEncoding=UTF-8" +
			"&autoReconnect=true&useSSL=false";
	private String user = "root";
	private String password = "";
	
	//Statement
	protected ResultSet conectarST(String query) throws SQLException {
		
		Connection cn = conexion();
		Statement st = cn.createStatement();
		return st.executeQuery(query);
	}
	
	//Statement con ResultSet modificado
	protected ResultSet conectarSTmod(String query) throws SQLException {
		
		Connection cn = conexion();
		Statement st = cn.createStatement(
						ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
		return st.executeQuery(query);
	}
	
	//PreparedStatement
	protected PreparedStatement conectarPS(String query) throws SQLException {
		
		Connection cn = conexion();
		return cn.prepareStatement(query);
	}
	
	//Callable Statement
	protected ResultSet conectarCal(String filtro) throws SQLException {
		
		Connection cn = conexion();
		CallableStatement cst = cn.prepareCall("{call stockCero(?)}");
		cst.setString(1, filtro);
		return cst.executeQuery();
	}
	
	//Conexion
	private Connection conexion() throws SQLException {
		
		return DriverManager.getConnection(url, user, password);
	}	
}
