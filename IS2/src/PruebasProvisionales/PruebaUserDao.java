package PruebasProvisionales;

import DAOS.User_DAO;
import Excepciones.QueryException;
import Objetos.Usuario;

public class PruebaUserDao {
	public static void pruebaInsUpDelUser() throws QueryException{
		if (insertarUser()){
			mostrarUsuarios();
			if(updateUser())
				mostrarUsuarios();
			System.out.println(eliminarUser());
			mostrarUsuarios();
		}
	}
	
	private static void mostrarUsuarios() throws QueryException{
		Usuario[] users = User_DAO.getAllUsers();
		if(users.length == 0)
			System.out.println("vacia");
		else
			for (Usuario usuario : users) 
				System.out.println(usuario);
			
	}
	
	public static boolean insertarUser() throws QueryException{
		Usuario user = new Usuario("12345678h", "asd", "asd", "asd", "asdasd", "asd");
		return User_DAO.setUsuario(user);
	}
	
	public static boolean eliminarUser() throws QueryException{
		Usuario user = new Usuario("12345678h", "asd", "asd", "asd", "asdasd", "asd");
		return User_DAO.delUsuario(user);
	}
	
	private static boolean updateUser() throws QueryException{
		Usuario user = new Usuario("12345678h", "eeee", "eeee", "eeee", "d", "asdasdasd");
		return User_DAO.updUsuario(user);
	}
}
