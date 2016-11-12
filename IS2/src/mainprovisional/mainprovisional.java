package mainprovisional;

import DAOS.User_DAO;
import Excepciones.QueryException;

public class mainprovisional {

	public static void main(String[] args) {
		try {
			System.out.println(User_DAO.getAllUsers());
		} catch (QueryException e) {
			System.out.println(e.getMessage());
		}
			
	}

}
