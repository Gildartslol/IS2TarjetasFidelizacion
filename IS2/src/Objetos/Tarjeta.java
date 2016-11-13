package Objetos;

public class Tarjeta {
	private int nTarjeta,nPuntos;
	private String userDni;
	
	public Tarjeta(int nTarjeta, int nPuntos, String userDni) {
		this.nTarjeta = nTarjeta;
		this.nPuntos = nPuntos;
		this.userDni = userDni;
	}

	public int getNTarjeta() {
		return nTarjeta;
	}

	public void setNTarjeta(int nTarjeta) {
		this.nTarjeta = nTarjeta;
	}

	public int getnPuntos() {
		return nPuntos;
	}

	public void setnPuntos(int nPuntos) {
		this.nPuntos = nPuntos;
	}

	public String getUserDni() {
		return userDni;
	}

	public void setUserDni(String userDni) {
		this.userDni = userDni;
	}

	@Override
	public String toString() {
		return "Tarjeta [nTarjeta=" + nTarjeta + ", nPuntos=" + nPuntos + ", userDni=" + userDni + "]";
	}
}
