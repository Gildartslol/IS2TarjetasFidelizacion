package mainprovisional;

import DAOS.User_DAO;
import Excepciones.QueryException;
import Objetos.Usuario;

public class mainprovisional {

	public static void main(String[] args) {
		try {
			Usuario users[] = User_DAO.getAllUsers();
			for (Usuario usuarios : users) {
				System.out.println(usuarios);
			}
			
		} catch (QueryException e) {
			System.out.println(e.getMessage());
		}
			
	}

}
