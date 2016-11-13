package mainprovisional;

import Excepciones.QueryException;
import PruebasProvisionales.PruebaTarjetaDao;

public class mainprovisional {

	public static void main(String[] args) {
		try {
			PruebaTarjetaDao.pruebaInsUpDelCard();
		} catch (QueryException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
