package DAOS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Excepciones.QueryException;
import Objetos.Tarjeta;

public class Tarjeta_DAO {
	private static final String selectAst = "SELECT * FROM Tarjeta";
	private static final String selectNCard = "SELECT * FROM Tarjeta WHERE ntarjeta = '";
	private static final String insertCard = "INSERT INTO Tarjeta (ntarjeta, npuntos, Usuarios_dni) "
			+ "VALUES ('";
	private static final String delCard = "DELETE FROM Tarjeta WHERE ntarjeta = '";
	private static final String updCard = "UPDATE Tarjeta SET ";

	/**
	 * Hace un select * de la tabla Tarjeta usando la constante selectAst
	 * 
	 * @return array con las tarjetas si no hay es un array vacio
	 * @throws QueryException
	 *             error con el mensaje a enviar al usuario
	 */
	public static Tarjeta[] getAllCards() throws QueryException {
		return select(selectAst);
	}

	/**
	 * Hace un select * de la tabla tarjeta obteniendo la tarjeta con su numero
	 * concreto
	 * 
	 * @param ntarjeta el numero de la tarjeta que quieres conseguir
	 * @return la tarjeta o null si no existe
	 * @throws QueryException
	 *             error al hacer la busqueda
	 */
	public static Tarjeta getTarjetaNCard(String ntarjeta) throws QueryException {
		Tarjeta card = null;
		Tarjeta[] cards = select(selectNCard + ntarjeta + "'");
		if (cards.length > 0) {
			card = cards[0];
		}
		return card;
	}

	/**
	 * Inserta una tarjeta en la BBDD
	 * 
	 * @param card
	 *            tarjeta a insertar
	 * @return true si se ha insertado false si no
	 * @throws QueryException
	 *             error en la query
	 */
	public static boolean setTarjeta(Tarjeta card) throws QueryException {
		String query = insertCard + card.getNTarjeta() + "', '" + card.getnPuntos() + "', '" + card.getUserDni() + "')";
		return update(query);
	}

	/**
	 * Elimina una tarjeta de la base de datos
	 * 
	 * @param card
	 *            tarjeta a eliminar
	 * @return devuelve true si ha ido bien false si ha ido mal
	 * @throws QueryException
	 *             error en la conexion
	 */
	public static boolean delTarjeta(Tarjeta card) throws QueryException {
		return update(delCard + card.getNTarjeta() + "'");
	}

	/**
	 * actualiza una tarjeta con los datos pasado por un objeto tarjeta
	 * @param card la tarjeta con los datos nuevos
	 * @return true si funciona false si no
	 * @throws QueryException error al hacer la query
	 */
	public static boolean updTarjeta(Tarjeta card) throws QueryException{
		String query = updCard + "npuntos = '" + card.getnPuntos() + "', "
				+ "Usuarios_dni = '" + card.getUserDni() + "' WHERE ntarjeta = '" + card.getNTarjeta() + "'";
		
		return update(query);
	}

	/**
	 * dado un resulset te crea una tarjeta con los datos conseguidos de la BBDD
	 * 
	 * @param rs
	 *            el resulset resultado de la query
	 * @return las tarjetas generado
	 * @throws SQLException
	 *             error al hacer la busqueda
	 */
	private static Tarjeta[] crearTarjeta(ResultSet rs) throws SQLException {
		ArrayList<Tarjeta> cards = new ArrayList<>();

		while (rs.next()) 
			cards.add(new Tarjeta(rs.getInt("ntarjeta"), rs.getInt("npuntos"), rs.getString("Usuarios_dni")));
		Tarjeta[] card = cards.toArray(new Tarjeta[cards.size()]);
		return card;
	}

	/**
	 * Hacer una select a la base de datos con el query pasado
	 * 
	 * @param query
	 *            la busqueda que se desea hacer
	 * @return Un array con las tarjetas encontrados
	 * @throws QueryException
	 *             excepcion si hay algun error que envia un mensaje al usuario
	 */
	private static Tarjeta[] select(String query) throws QueryException {
		Statement stmt = null;
		ResultSet rs = null;
		Tarjeta card[] = null;

		try {
			stmt = Conexion.conexion().createStatement();
			rs = stmt.executeQuery(query);
			card = crearTarjeta(rs);
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

		return card;
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
