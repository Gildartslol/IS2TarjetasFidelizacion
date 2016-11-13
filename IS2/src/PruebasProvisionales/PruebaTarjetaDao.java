package PruebasProvisionales;

import DAOS.Tarjeta_DAO;
import Excepciones.QueryException;
import Objetos.Tarjeta;

public class PruebaTarjetaDao {
	public static void pruebaInsUpDelCard() throws QueryException{
		if (insertarCard()){
			mostrarTarjetas();
			if(updateCard())
				mostrarTarjetas();
			System.out.println(eliminarCard());
			System.out.println(PruebaUserDao.eliminarUser());
			mostrarTarjetas();
		}
	}
	
	private static void mostrarTarjetas() throws QueryException{
		Tarjeta[] cards = Tarjeta_DAO.getAllCards();
		if(cards.length == 0)
			System.out.println("vacia");
		else
			for (Tarjeta card : cards) 
				System.out.println(card);
			
	}
	
	private static boolean insertarCard() throws QueryException{
		Tarjeta card;
		boolean result = false;
		if(PruebaUserDao.insertarUser()){
			card = new Tarjeta(1,1000,"12345678h");
			result = Tarjeta_DAO.setTarjeta(card);
		}
		return result;
	}
	
	private static boolean eliminarCard() throws QueryException{
		Tarjeta card = new Tarjeta(1,20000,"12345678h");
		return Tarjeta_DAO.delTarjeta(card);
	}
	
	private static boolean updateCard() throws QueryException{
		Tarjeta card = new Tarjeta(1,20000,"12345678h");
		return Tarjeta_DAO.updTarjeta(card);
	}
}
