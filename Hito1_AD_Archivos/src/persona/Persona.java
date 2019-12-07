package persona;

public class Persona {
	public String Nombre,Apellido1,Apellido2;
	
	

	public Persona() {
		
	}

	public Persona(String nombre, String apellido1, String apellido2) {
		Nombre = nombre;
		Apellido1 = apellido1;
		Apellido2 = apellido2;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido1() {
		return Apellido1;
	}

	public void setApellido1(String apellido1) {
		Apellido1 = apellido1;
	}

	public String getApellido2() {
		return Apellido2;
	}

	public void setApellido2(String apellido2) {
		Apellido2 = apellido2;
	}

	@Override
	public String toString() {
		return "Nombre: " + Nombre + " || Apellido 1: " + Apellido1 + " || Apellido 2:" + Apellido2;
	}
	
	
}
