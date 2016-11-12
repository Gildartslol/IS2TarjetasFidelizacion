package Objetos;

public class Usuario {
	private String dni, nombre, apellidos, direccion, telefono, email;	

	public Usuario(String dni, String nombre, String apellidos, String direccion, String telefono, String email) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Usuario [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ ", telefono=" + telefono + ", email=" + email + "]";
	}
}
