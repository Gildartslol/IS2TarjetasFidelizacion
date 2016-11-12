package DAOS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Excepciones.QueryException;
import Objetos.Usuario;

public class User_DAO {
	private static final String selectAst = "SELECT * FROM Usuarios";
	private static final String selectDNI = "SELECT * FROM Usuarios WHERE dni = '";
	
	/**
	 * Hace un select * de la tabla Usuarios usando la constante selectAst
	 * @return resultado de la query
	 * @throws QueryException error con el mensaje a enviar al usuario
	 */
	public static Usuario getAllUsers() throws QueryException{
		return select(selectAst);
	}
	
	public static Usuario getUsuarioDni(String dni) throws QueryException{
		return select(selectDNI.concat(dni + "'"));
	}
	
	private static Usuario crearUsuario(ResultSet rs) throws SQLException{
		Usuario user = null;
		
		if(rs.next()){
			user = new Usuario(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"),
					rs.getString("direccion"), rs.getString("telefono"), rs.getString("email"));
		}
		
		return user;
	}
	
	/**
	 * Hacer una select a la base de datos con el query pasado
	 * @param query la busqueda que se desea hacer
	 * @return el result set con el resultado de la busqueda
	 * @throws QueryException excepcion si hay algun error que envia un mensaje al usuario
	 */
	private static Usuario select(String query) throws QueryException{
		Statement stmt = null;
		ResultSet rs = null;
		Usuario user = null;

		try {
		    stmt = Conexion.conexion().createStatement();
		    rs = stmt.executeQuery(query);
		    user = crearUsuario(rs);
		}
		catch (SQLException ex){
			throw new QueryException("No ha sido posible hacer la busqueda",ex);
		}
		finally {

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
	    
	    return user;
	}
}
