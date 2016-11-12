package DAOS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Excepciones.QueryException;
import Objetos.Usuario;

public class User_DAO {
	private static final String selectAst = "SELECT * FROM Usuarios";
	private static final String selectDNI = "SELECT * FROM Usuarios WHERE dni = '";
	private static final String insertUser = "INSERT INTO Usuarios (dni, nombre, apellidos, direccion, telefono, email) "
			+ "VALUES ('";
	private static final String delUser = "DELETE FROM Usuarios WHERE dni = '";
	private static final String updUser = "UPDATE Usuarios SET ";

	/**
	 * Hace un select * de la tabla Usuarios usando la constante selectAst
	 * 
	 * @return array con los usuarios si no hay es un array vacio
	 * @throws QueryException
	 *             error con el mensaje a enviar al usuario
	 */
	public static Usuario[] getAllUsers() throws QueryException {
		return select(selectAst);
	}

	/**
	 * Hace un select * de la tabla usuarios obteniendo al usuario con un dni
	 * concreto
	 * 
	 * @param dni
	 *            el dni del usario que quieres conseguir
	 * @return el usuario o null si no existe
	 * @throws QueryException
	 *             error al hacer la busqueda
	 */
	public static Usuario getUsuarioDni(String dni) throws QueryException {
		Usuario user = null;
		Usuario[] users = select(selectDNI + dni + "'");
		if (users.length > 0) {
			user = users[0];
		}
		return user;
	}

	/**
	 * Inserta un usuario en la BBDD
	 * 
	 * @param user
	 *            usuario a insertar
	 * @return true si se ha insertado false si no
	 * @throws QueryException
	 *             error en la query
	 */
	public static boolean setUsuario(Usuario user) throws QueryException {
		String query = insertUser + user.getDni() + "', '" + user.getNombre() + "', '" + user.getApellidos() + "', '"
				+ user.getDireccion() + "', '" + user.getTelefono() + "', '" + user.getEmail() + "')";
		return update(query);
	}

	/**
	 * Elimina un usuario de la base de datos
	 * 
	 * @param user
	 *            usuario a eliminar
	 * @return devuelve true si ha ido bien false si ha ido mal
	 * @throws QueryException
	 *             error en la conexion
	 */
	public static boolean delUsuario(Usuario user) throws QueryException {
		return update(delUser + user.getDni() + "'");
	}

	public static boolean updUsuario(Usuario user) throws QueryException{
		String query = updUser + "nombre = '" + user.getNombre() + "', apellidos = '" + user.getApellidos() + "', "
				+ "direccion = '" + user.getDireccion() + "', telefono = '" + user.getTelefono() + "', email = '"
				+ user.getEmail() + "' WHERE dni = '" + user.getDni() + "'";
		
		return update(query);
	}

	/**
	 * dado un resulset te crea un usuario con los datos conseguidos de la BBDD
	 * 
	 * @param rs
	 *            el resulset resultado de la query
	 * @return el usuario generado
	 * @throws SQLException
	 *             error al hacer la busqueda
	 */
	private static Usuario[] crearUsuario(ResultSet rs) throws SQLException {
		ArrayList<Usuario> users = new ArrayList<>();

		while (rs.next()) {
			users.add(new Usuario(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"),
					rs.getString("direccion"), rs.getString("telefono"), rs.getString("email")));
		}
		Usuario[] user = users.toArray(new Usuario[users.size()]);
		return user;
	}

	/**
	 * Hacer una select a la base de datos con el query pasado
	 * 
	 * @param query
	 *            la busqueda que se desea hacer
	 * @return Un array con los usuarios encontrados
	 * @throws QueryException
	 *             excepcion si hay algun error que envia un mensaje al usuario
	 */
	private static Usuario[] select(String query) throws QueryException {
		Statement stmt = null;
		ResultSet rs = null;
		Usuario user[] = null;

		try {
			stmt = Conexion.conexion().createStatement();
			rs = stmt.executeQuery(query);
			user = crearUsuario(rs);
		} catch (SQLException ex) {
			throw new QueryException("No ha sido posible hacer la busqueda", ex);
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
		}

		return user;
	}

	/**
	 * Metodo que hace querys del tipo insert, update o delete
	 * 
	 * @param query
	 *            sentencia a realizar
	 * @return true si se ha podido false si no
	 * @throws QueryException
	 *             error con la BBDD
	 */
	private static boolean update(String query) throws QueryException {
		Statement stmt = null;
		int result = 0;

		try {
			stmt = Conexion.conexion().createStatement();
			result = stmt.executeUpdate(query);
		} catch (SQLException ex) {
			throw new QueryException("No ha sido posible hacer la actualizacion", ex);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
		}
		return result > 0;
	}
}
